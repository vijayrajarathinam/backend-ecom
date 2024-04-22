package com.onlineshopping.backend.controller;

import com.onlineshopping.backend.exception.UserNotFoundException;
import com.onlineshopping.backend.model.User;
import com.onlineshopping.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUsersByEmail(
            @PathVariable("email") String email
    ){
        try{
            User user = userService.getUser(email);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable("userId") Long userId
    ){
        try{
            userService.deleteUserById(userId);
            return ResponseEntity.ok("user deleted successfully");
        } catch (UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
    }
}
