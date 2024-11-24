package com.example.exam2.Controller;


import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.User;
import com.example.exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/library-system/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUsers(){

        if (userService.getUsers().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("There are no users"));
        }
        return ResponseEntity.status(200).body(userService.getUsers());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isAdded = userService.addUser(user);

        if(isAdded){
         return ResponseEntity.status(200).body(new ApiResponse("User added"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User not added"));

    }


    @PutMapping("update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user , Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = userService.updateUser(id,user);

        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User not updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){

        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted){

            return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User not deleted"));

    }

    //__________________________________________________________________-


    @GetMapping("/get-balance/{balance}")
    public ResponseEntity getSameBalance(@PathVariable double balance){

        if (userService.getAllBalance(balance).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There not user with this  balance or above"));
        }

        return ResponseEntity.status(200).body(userService.getAllBalance(balance));

    }


    @GetMapping("get-ages/{age}")
    public ResponseEntity getSameAges(@PathVariable int age){

        if (userService.getAllAges(age).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There not user with this age or above"));
        }

        return ResponseEntity.status(200).body(userService.getAllAges(age));

    }
}
