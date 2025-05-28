import Fluent
import Vapor
import Redis

    struct ProductController: RouteCollection {
        func boot(routes: any RoutesBuilder) throws {
            let products = routes.grouped("products")
            products.get(use: self.view)
            products.group("api") { api in
            api.get(use: self.index)
            api.post(use: self.create)
            api.group(":productID") { product in
                product.get( use: self.get)
                product.put( use: self.update)
                product.delete( use: self.delete)
            }

            }
        }
        // app.get("products-view") { req async throws in

        // }
        struct ProductPageContext: Content {
            let products: [ProductDTO]
            let categories: [Category]
        }
        
        @Sendable
        func view(req: Request) async throws -> View {

            let products = try await Product.query(on: req.db).with(\.$category)
                .all()
            let categories = try await Category.query(on: req.db).all()
            let productDTOs = products.map { $0.toDTO() }
            // let categoryDTOs = categories.map { $0.toDTO() }
            let context = ProductPageContext(products: productDTOs, categories: categories)

            return try await req.view.render("products",context)
        }
        @Sendable
        func index(req: Request) async throws -> [ProductDTO] {
            let redisKey: RedisKey = "products:all"

            // try await Product.query(on: req.db).all().map { $0.toDTO() }
            let products = try await Product.query(on: req.db)
                .with(\.$category)
                .all()
            let productDTOs = products.map { $0.toDTO() }


            return productDTOs
        }

        @Sendable
        func create(req: Request) async throws -> ProductDTO {
            let dto = try req.content.decode(ProductDTO.self)
            let product = dto.toModel()
            guard
                let savedProduct = try await Product.query(on: req.db)
                    .with(\.$category)
                    .filter(\.$id == product.id!)
                    .first()
            else {
                throw Abort(.internalServerError, reason: "Failed to load product after save")
            }

            return savedProduct.toDTO()
        }

        @Sendable
        func get(req: Request) async throws -> ProductDTO {
            guard let product = try await Product.find(req.parameters.get("productID"), on: req.db)
            else {
                throw Abort(.notFound)
            }
            return product.toDTO()
        }

        @Sendable
        func update(req: Request) async throws -> ProductDTO {
            guard let product = try await Product.find(req.parameters.get("productID"), on: req.db) else {
                throw Abort(.notFound)
            }

            let input = try req.content.decode(ProductDTO.self)
            if let name = input.name {
                product.name = name
            }
            if let price = input.price {
                product.price = price
            }
            try await product.update(on: req.db)

            return product.toDTO()
        }

        @Sendable
        func delete(req: Request) async throws -> HTTPStatus {
            guard let product = try await Product.find(req.parameters.get("productID"), on: req.db) else {
                throw Abort(.notFound)
            }
            try await product.delete(on: req.db)

            return .noContent
        }
    }