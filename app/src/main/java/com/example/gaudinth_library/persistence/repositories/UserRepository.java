package com.example.gaudinth_library.persistence.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.dao.UserDao;

import java.util.List;

import io.reactivex.Flowable;
import kotlinx.coroutines.flow.Flow;

public class UserRepository {
    public UserDao userDao;

    public UserRepository(Application application) {
        AppDataBase db = AppDataBase.getAppDatabase(application);
        userDao = db.userDao();
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }
    public void updateUser(User users) {
        userDao.updateUser(users);
    }
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
    public Flowable<List<User>> getAllUsers() {
        return userDao.getAll();
    }
    public User loadUserById(int id) {
        return userDao.loadUserById(id);
    }
    public int countUserBooks(int id) { return userDao.countUserBooks(id); }
}
