package com.example.gaudinth_library.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gaudinth_library.classes.Author;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface AuthorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAuthor(Author author);

    @Update
    public void updateAuthor(Author author);

    @Delete
    public void deleteAuthor(Author author);

    @Query("SELECT * FROM Author")
    public Flowable<List<Author>> getAll();

    @Query("SELECT * FROM Author WHERE author_uid = :id")
    public Author loadAuthorById(int id);

}
