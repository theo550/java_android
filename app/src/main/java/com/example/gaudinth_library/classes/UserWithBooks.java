package com.example.gaudinth_library.classes;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithBooks {
    @Embedded public User user;
    @Relation(
            parentColumn = "uid",
            entityColumn = "book_user_id"
    )
    public List<Book> bookList;
}
