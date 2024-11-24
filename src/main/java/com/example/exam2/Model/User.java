package com.example.exam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
//
//    ID , name , age ,  balance , role
//    1. all should not be empty
//    2. balance should be a valid number
//    3. role is   customer OR libraian



    @NotEmpty(message = "User id cannot be null")
    @Size(min = 10,max = 35, message = "User id must be between 10 and 35")
    private String id;

    @NotEmpty(message = "User name cannot be null")
    @Size(min = 5 , max = 20 , message = "User name must be between 5 and 20")
    @Pattern(regexp = "^[a-zA-Z]+ [a-zA-Z]+$", message = "User first and last name is required. No numbers allow")
    private String name;

    @NotNull(message = "User age cannot be null")
    @Positive(message = "User age accept positive number only")
    @Min(value = 18 , message = "User age must start from 18")
    private int age;

    @NotNull(message = "User balance cannot be null")
    @Positive(message = "User balance accept only positive")
    @Min(value = 0,message = "User balance cannot be under zero")
    private double balance;

    @NotEmpty(message = "User role cannot be null")
    @Pattern(regexp = "^(customer|librarian)" , message = "User role can be customer OR librarian only")
    private String role;
}
