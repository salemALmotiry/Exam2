package com.example.exam2.Controller;


import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.Book;
import com.example.exam2.Service.BookService;
import com.example.exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/library-system/book")
public class BookController {

    private final BookService bookService;
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getBook(){

        if (bookService.getBooks().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("There are no books"));
        }
        return ResponseEntity.status(200).body(bookService.getBooks());
    }


    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }

        boolean isAdded = bookService.addBook(book);

        if (isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Book added"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("Book not added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid Book book, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }

        boolean isUpdated = bookService.updateBook(id,book);

        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Book updated"));

        }

        return ResponseEntity.status(400).body(new ApiResponse("Book not updated"));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){

        boolean isDeleted = bookService.deleteBook(id);

        if (isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Book deleted"));

        }

        return ResponseEntity.status(400).body(new ApiResponse("Book not deleted"));
    }


    //_________________________________________________________

    @GetMapping("get-book/{name}")
    public ResponseEntity getBook(@PathVariable String name){

        if (bookService.getOneBook(name) == null){
            return ResponseEntity.status(400).body(new ApiResponse("book not found"));

        }
        return ResponseEntity.status(200).body(bookService.getOneBook(name));

    }



    @GetMapping("get-book-with-category/{category}")
    public ResponseEntity getBooksCategory (@PathVariable String category ){

        if (bookService.getAllBookWithSameCategory(category ).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("books not found"));

        }
        return ResponseEntity.status(200).body(bookService.getAllBookWithSameCategory(category ));

    }

    @GetMapping("get-same-pages/{numberOfPages}")
    public ResponseEntity getBooksWithNumberOfPages (@PathVariable int numberOfPages ){

        if (bookService.getAllBookWithSameNumberOfPages(numberOfPages).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("books not found"));

        }
        return ResponseEntity.status(200).body(bookService.getAllBookWithSameNumberOfPages(numberOfPages));

    }


    @PutMapping("change-book-available/{userId}/{bookId}")
    public ResponseEntity unavailableBook(@PathVariable String userId,@PathVariable String bookId){

        boolean isUser = userService.isExits(userId);
        if (!isUser){
            return ResponseEntity.status(400).body(new ApiResponse("User is not exits"));
        }

        boolean isLibrarian = userService.isLibrarian(userId);

        if (!isLibrarian){
            return ResponseEntity.status(400).body(new ApiResponse("User is not librarian"));
        }

        boolean isBookExits = bookService.isExits(bookId);

        if (!isBookExits){
            return ResponseEntity.status(400).body(new ApiResponse("Book is not exits"));
        }

        boolean isUnavailable = bookService.checkUnavailable(bookId);

        if (!isUnavailable){
            return ResponseEntity.status(400).body(new ApiResponse("Book is already unavailable"));
        }

        boolean isChange = bookService.setUnavailable(bookId);
        if(isChange){
            return ResponseEntity.status(200).body(new ApiResponse("Book unavailable status has been changed"));

        }
        return  ResponseEntity.status(400).body(new ApiResponse("Something went wrong"));
    }

}
