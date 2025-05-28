import NIOSSL
import Fluent
import FluentSQLiteDriver
import Leaf
import Vapor
import Redis

// configures your application
public func configure(_ app: Application) async throws {
    // uncomment to serve files from /Public folder
    // app.middleware.use(FileMiddleware(publicDirectory: app.directory.publicDirectory))

    // app.databases.use(DatabaseConfigurationFactory.sqlite(.file("/db/database.sqlite")), as: .sqlite)
    app.databases.use(
        DatabaseConfigurationFactory.sqlite(.file("./db/database.sqlite")), as: .sqlite)
    app.migrations.add(CreateProduct())
    app.migrations.add(CreateCategory())

    let redisHostname = "localhost"
    app.redis.configuration = try RedisConfiguration(hostname: redisHostname)
    
    try await app.autoMigrate()

    app.views.use(.leaf)

    // register routes
    try routes(app)
}
