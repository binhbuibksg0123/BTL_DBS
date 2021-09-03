package com.example.test0;

public class Book {
    private String Name;
    private String urlImg;
    private String Desc;
    private String id;
    private String currentBookPrice;
    private String bookPrice;
    private String publisher;
    private String publishYear;
    private String publishTime;

    public Book(){

    }
    public Book(String name, String urlImg, String desc, String id, String currentBookPrice, String bookPrice, String publisher, String publishYear, String publishTime) {
        Name = name;
        this.urlImg = urlImg;
        Desc = desc;
        this.id = id;
        this.currentBookPrice = currentBookPrice;
        this.bookPrice = bookPrice;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.publishTime = publishTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }


    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCurrentBookPrice() {
        return currentBookPrice;
    }

    public void setCurrentBookPrice(String currentBookPrice) {
        this.currentBookPrice = currentBookPrice;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Name='" + Name + '\'' +
                ", urlImg='" + urlImg + '\'' +
                ", Desc='" + Desc + '\'' +
                ", id=" + id +
                ", currentBookPrice='" + currentBookPrice + '\'' +
                ", bookPrice='" + bookPrice + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", publishTime='" + publishTime + '\'' +
                '}';
    }
}
