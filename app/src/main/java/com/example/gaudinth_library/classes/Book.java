package com.example.gaudinth_library.classes;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    public long book_user_id;
    public String book_title;
    public String book_publisher;
    @Embedded
    public Author book_author;
    public int book_pages;
    public int book_mark;
    public boolean is_possessed;

    public Book(long book_user_id, String book_title, Author book_author) {
        this.book_user_id = book_user_id;
        this.book_title = book_title;
        this.book_author = book_author;
    }


    public Author getBook_author() {
        return book_author;
    }

    public String getBook_title() {
        return book_title;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public int getBook_pages() {
        return book_pages;
    }
    public int getMark() {
        return book_mark;
    }

    public boolean getIs_possessed() {
        return is_possessed;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public void setBook_author(Author book_author) {
        this.book_author = book_author;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public void setBook_pages(int book_pages) {
        this.book_pages = book_pages;
    }
    public void setBook_mark(int book_mark) {
        this.book_mark = book_mark;
    }

    public void setIs_possessed(boolean is_possessed) {
        this.is_possessed = is_possessed;
    }
}
