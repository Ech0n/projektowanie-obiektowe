import Fluent
import struct Foundation.UUID
import Fluent

/// Property wrappers interact poorly with `Sendable` checking, causing a warning for the `@ID` property
/// It is recommended you write your model with sendability checking on and then suppress the warning
/// afterwards with `@unchecked Sendable`.

final class Product: Model, @unchecked Sendable {
    static let schema = "products"

    @ID(key: .id)
    var id: UUID?

    @Field(key: "name")
    var name: String

    @Field(key: "price")
    var price: Double
    
    @Parent(key: "category_id")
    var category: Category

    init() {}

    init(id: UUID? = nil, name: String, price: Double) {
        self.id = id
        self.name = name
        self.price = price
    }

    func toDTO() -> ProductDTO {
        .init(
            id: self.id,
            name: self.name,
            price: self.price,
            categoryID: self.$category.id,
            categoryName: try? self.category.name
        )
    }
}