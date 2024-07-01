package org.example.inclusiveblog.controller;

import org.apache.coyote.Response;
import org.example.inclusiveblog.model.Post;
import org.example.inclusiveblog.model.User;
import org.example.inclusiveblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//CREATE

    //add User
    @PostMapping ("/users")
    public ResponseEntity<User> addUser (@RequestBody User user) throws Exception {
        try {
            User addedUser = userService.addUser(user);
            return ResponseEntity.ok(addedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//READ

    //getUserByID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try {
            User user = userService.getUserByID(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getAllUsers
    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    //getUserByEmail
    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> getUserByEmail (@PathVariable String email) {
        try {
            User user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getAllPostsByUserName
    @GetMapping("/users/{username}/posts")
    public ResponseEntity<List<Post>> getAllPostsByUserName(@PathVariable String username) throws Exception {
        try {
            return ResponseEntity.ok(userService.getAllPostsByUserName(username));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//UPDATE

    //updateUser
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) throws Exception {
       try {
           User updatedUser = userService.updateUser(id, user);
           return ResponseEntity.ok(updatedUser);
       } catch (Exception e) {
           return ResponseEntity.notFound().build();
       }
    }

//DELETE

    //deleteUser
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
    }


}
