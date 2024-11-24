package com.example.exam2.Service;

import com.example.exam2.Model.Book;
import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {

    ArrayList<Book> books = new ArrayList<>();


    public ArrayList<Book> getBooks(){
        return this.books;
    }

    public boolean addBook(Book book){

        for (Book tem:this.books){
            if (tem.getId().equals(book.getId())){
                return false;
            }
        }

        books.add(book);
        return true;
    }


    public boolean updateBook(String id , Book book){

        for (int i = 0; i < books.size(); i++) {

            if (this.books.get(i).getId().equals(id)){
                this.books.set(i,book);
                return true;
            }

        }
        return false;
    }


    public boolean deleteBook(String id){

        for (Book book:books){
            if (book.getId().equals(id)){
                books.remove(book);
                return true;
            }
        }

        return false;
    }

    public Book getOneBook(String name){

        for (Book book:books){
            if (book.getName().equalsIgnoreCase(name)){
                return book;
            }
        }

        return null;
    }



    public ArrayList<Book> getAllBookWithSameCategory(String category ){
        ArrayList<Book> tem = new ArrayList<>();
        for (Book book : books){

            if (book.getCategory().equalsIgnoreCase(category)){
                tem.add(book);
            }
        }


        return tem;
    }


    public ArrayList<Book> getAllBookWithSameNumberOfPages(int numberOfPages){
        ArrayList<Book> tem = new ArrayList<>();

        for (Book book : books){
            if (book.getNumber_of_pages() >= numberOfPages){
                tem.add(book);
            }
        }

        return tem;
    }

    public boolean checkUnavailable(String id){

        for (Book book : books){

            if (book.getId().equals(id)){

                if (book.isAvailable()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setUnavailable(String id){

        for (Book book : books){

            if (book.getId().equals(id)){
                    book.setAvailable(false);
                    return true;
                }
            }

        return false;
    }

    public boolean isExits(String id){
        for (Book book : this.books){
            if (book.getId().equals(id)){
                return true;
            }
        }

        return false;
    }









}
