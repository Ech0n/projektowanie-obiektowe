import Vapor
import Fluent

struct CategoryDTO: Content {
    var id: UUID?
    var name: String?

    init(id: UUID? = nil, name: String) {
        self.id = id
        self.name = name
    }

    init(_ model: Category) {
        self.id = model.id
        self.name = model.name
    }

    func toModel() -> Category {
        let category = Category()
        category.id = self.id
        if let name = self.name {
            category.name = name
        }
        return category
    }
}
