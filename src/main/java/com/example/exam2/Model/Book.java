package com.example.exam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    //  ID , name , number_of_pages , price  , category  , isAvailable

    @NotEmpty(message = "Book id cannot be null")
    @Size(min = 5,max = 35, message = "Book id length should be longer than 5 and less than 35")
    private String id;

    @NotEmpty(message = "Book name cannot be null")
    @Size(min = 5,max = 20,message = "Book name length must be between 5 and 20")
    private String name;

    @NotNull(message = "number of pages cannot be null")
    @Positive(message = "number of pages accept only positive numbers")
    @Min(value = 1,message = "number of pages must be longer than 0")
    private int number_of_pages;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price accept only positive numbers")
    @Min(value = 0,message = "Book price can start from zero")
    private double price;

    @NotEmpty(message = "Category cannot be null")
    @Pattern(regexp = "^(novel|academic)",message = "Category can be novel OR academic only")
    private String category;

    @NotNull(message = "Book available cannot be null")
    @AssertTrue(message = "you can added new book when it is available")
    private boolean isAvailable;
}
