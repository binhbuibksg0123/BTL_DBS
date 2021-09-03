package com.example.test0;

import android.content.Intent;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class dbBook {
    public static dbBook instance;
    public static ArrayList<Book> books;
    public static ArrayList<Book> alreadyRead = new ArrayList<>();
    public static ArrayList<Book> currentRead = new ArrayList<>();
    public static ArrayList<Book> favour = new ArrayList<>();
    public static ArrayList<Book> wishList = new ArrayList<>();
    public ArrayList<Book> getWishList() {
        return wishList;
    }

    private dbBook() {
        books = new ArrayList<>();
        initData();
    }

    public  ArrayList<Book> getAlreadyRead() {
        return alreadyRead;
    }

    public  ArrayList<Book> getCurrentRead() {
        return currentRead;
    }

    public  ArrayList<Book> getFavour() {
        return favour;
    }

    public static synchronized dbBook getInstance() {
        if(instance==null){
            instance = new dbBook();
        }
        return instance;
    }
    public Book getBookByID(String id){
        for(Book a: books){
            if(a.getId().equals(id)) return a;
        }
        return null;
    }
    public ArrayList<Book> getBooks() {
        return books;
    }
    private void initData() {
        Connection connect = ConnectionHelper.getConnect();
        try {
            Statement smt = connect.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM [MyOurBook].[EBook].[Book]");
            while(rs.next()){
                Book a = new Book(rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                        );
                books.add(a);
            }
        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }
    }
    public boolean addAlreadyRead(Book a){
        return alreadyRead.add(a);
    }
    public boolean addReading(Book a){
        return currentRead.add(a);
    }
    public boolean addWishList(Book a){
        return wishList.add(a);
    }
    public boolean addFavour(Book a){
        return favour.add(a);
    }
    public boolean delAlreadyRead(Book a){
        return alreadyRead.remove(a);
    }
    public boolean delReading(Book a){
        return currentRead.remove(a);
    }
    public boolean delWishList(Book a){
        return wishList.remove(a);
    }
    public boolean delFavour(Book a){
        return favour.remove(a);
    }
}
