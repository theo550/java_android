package com.example.gaudinth_library.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gaudinth_library.classes.Book;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertBook(Book book);

    @Update
    public void updateBook(Book book);

    @Delete
    public void deleteBook(Book book);

    @Query("SELECT * FROM Book")
    public Flowable<List<Book>> getAll();

    @Query("SELECT * FROM Book WHERE uid = :id")
    public Book loadBookById(int id);

    @Query("SELECT * FROM Book WHERE book_user_id = :id")
    public Flowable<List<Book>> loadUserBooks(int id);
}
