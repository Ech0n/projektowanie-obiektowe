package com.example.spring_boot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.context.annotation.Lazy

@RestController
@RequestMapping("/users")
class UserController  {

    @Autowired
    lateinit var eagerService: EagerService

    @Autowired
    @Lazy
    lateinit var lazyService: LazyService

    @GetMapping
    fun getAllUsers(): List<User> = usersList

    @PostMapping("/auth/lazy")
    fun authenticateLazy(@RequestBody authRequest: AuthorizationRequest): ResponseEntity<String> {
        return if (lazyService.authenticate(authRequest.username, authRequest.password)) {
            ResponseEntity.ok("Authenticated successfully")
        } else {
            ResponseEntity.status(401).body("Authentication failed")
        }
    }

    @PostMapping("/auth/eager")
    fun authenticateEager(@RequestBody authRequest: AuthorizationRequest): ResponseEntity<String> {
        return if (eagerService.authenticate(authRequest.username, authRequest.password)) {
            ResponseEntity.ok("Authenticated successfully")
        } else {
            ResponseEntity.status(401).body("Authentication failed")
        }
    }
}

data class AuthorizationRequest(
    val username: String,
    val password: String
)