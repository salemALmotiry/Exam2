package com.example.exam2.Service;

import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();


    public ArrayList<User> getUsers(){

        return this.users;
    }


    public boolean addUser(User user){

        for (User tem : this.users){
            if (tem.getId().equals(user.getId())){
                return false;
            }
        }

        this.users.add(user);
        return true;
    }

    public boolean updateUser(String id, User user){

        for (int i = 0; i < this.users.size(); i++) {

            if (this.users.get(i).getId().equals(id)){
                this.users.set(i,user);
                return true;
            }
        }

        return false;
    }


    public boolean deleteUser(String id){

     for (User user : users ){
         if (user.getId().equals(id)){
             users.remove(user);
             return true;
         }
     }

     return false;
    }

    public ArrayList<User> getAllBalance(double balance ){
        ArrayList<User> temp= new ArrayList<>();

        for (User user : this.users){

            if (user.getBalance() >= balance){
                temp.add(user);
            }
        }

        return temp;
    }


    public ArrayList<User> getAllAges(int age){
        ArrayList<User> temp= new ArrayList<>();

        for (User user : this.users){

            if (user.getAge()>= age){
                temp.add(user);
            }
        }

        return temp;
    }

    public boolean isExits(String id){
        for (User user : this.users){
            if (user.getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    public boolean isLibrarian(String id){

        for (User user : this.users){
            if (user.getId().equals(id) && user.getRole().equals("librarian")){
                return true;
            }
        }
        return false;
    }




}
