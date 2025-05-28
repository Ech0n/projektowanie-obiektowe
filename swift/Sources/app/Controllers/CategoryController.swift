import Fluent
import Vapor

struct CategoryController: RouteCollection {
    func boot(routes: any RoutesBuilder) throws {
        let categories = routes.grouped("categories")
        categories.get(use: self.view)

        categories.group("api") { api in
            api.get(use: self.index)
            api.post(use: self.create)
            api.group(":categoryID") { category in
                category.get(use: self.get)
                category.put(use: self.update)
                category.delete(use: self.delete)
            }
        }
    }

    @Sendable
    func view(req: Request) async throws -> View {
        let categories = try await Category.query(on: req.db).all()
        return try await req.view.render("categories", ["categories": categories])
    }

    @Sendable
    func index(req: Request) async throws -> [CategoryDTO] {
        try await Category.query(on: req.db).all().map { $0.toDTO() }
    }

    @Sendable
    func create(req: Request) async throws -> CategoryDTO {
        let dto = try req.content.decode(CategoryDTO.self)
        let category = dto.toModel()
        try await category.save(on: req.db)
        return category.toDTO()
    }

    @Sendable
    func get(req: Request) async throws -> CategoryDTO {
        guard let category = try await Category.find(req.parameters.get("categoryID"), on: req.db)
        else {
            throw Abort(.notFound)
        }
        return category.toDTO()
    }

    @Sendable
    func update(req: Request) async throws -> CategoryDTO {
        guard let category = try await Category.find(req.parameters.get("categoryID"), on: req.db)
        else {
            throw Abort(.notFound)
        }

        let input = try req.content.decode(CategoryDTO.self)
        if let name = input.name {
            category.name = name
        }
        try await category.update(on: req.db)
        return category.toDTO()
    }

    @Sendable
    func delete(req: Request) async throws -> HTTPStatus {
        guard let category = try await Category.find(req.parameters.get("categoryID"), on: req.db)
        else {
            throw Abort(.notFound)
        }
        try await category.delete(on: req.db)
        return .noContent
    }
}