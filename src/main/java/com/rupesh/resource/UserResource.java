package com.rupesh.resource;

import com.rupesh.model.UserDTO;
import com.rupesh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final IUserService userService;


    @Autowired
    public UserResource(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody final UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping(path = "/by-user-id")
    public ResponseEntity<?> getByUserId(@RequestParam(name = "userId") final String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @GetMapping(path = "/by-username")
    public ResponseEntity<?> getByUsername(@RequestParam(name = "username") final String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping(path = "/")
    public ResponseEntity<?> updateUser(
            @RequestBody final UserDTO userDTO,
            @RequestParam(name = "username") final String username
    ) {
        return ResponseEntity.ok(userService.updateUser(userDTO, username));
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<?> deleteUser(@RequestParam(name = "userId") final String userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
