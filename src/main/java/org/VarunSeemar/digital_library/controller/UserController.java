package org.VarunSeemar.digital_library.controller;

import jakarta.validation.Valid;
import org.VarunSeemar.digital_library.adapter.UserAdapter;
import org.VarunSeemar.digital_library.entities.input.UserInputEntity;
import org.VarunSeemar.digital_library.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserAdapter userAdapter;

    @Autowired
    public UserController(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id){
        UserModel user = this.userAdapter.getUser(id);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserInputEntity userInputEntity){
        UserModel user = this.userAdapter.save(userInputEntity);
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Able To Create User.");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserInputEntity userInputEntity){
        UserModel user = this.userAdapter.update(userInputEntity);
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Able To Update User.");
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        boolean deleted = this.userAdapter.deleteUser(id);
        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found.");
        }
        return ResponseEntity.ok("Deleted Successfully.");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        List<UserModel> userModels = this.userAdapter.getAll();
        return ResponseEntity.ok(userModels);
    }
}
