package com.example.gaudinth_library.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gaudinth_library.classes.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import kotlinx.coroutines.flow.Flow;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User user);

    @Update
    public void updateUser(User users);

    @Delete
    public void deleteUser(User user);

    @Query("SELECT * FROM User")
    public Flowable<List<User>> getAll();

    @Query("SELECT * FROM user WHERE uid = :id")
    public User loadUserById(int id);

    @Query("SELECT COUNT(*) FROM book WHERE book_user_id = :id")
    public  int countUserBooks(int id);

}
