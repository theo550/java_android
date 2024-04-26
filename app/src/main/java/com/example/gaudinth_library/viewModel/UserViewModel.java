package com.example.gaudinth_library.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.persistence.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public UserRepository repository;
    public List<User> readAll;
    public User currentUser;

    public UserViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public List<User> getAllUsers() { return readAll; }

    public void insert(User user) { repository.insertUser(user); }

    public User setCurrentUser (long id) {
        currentUser = repository.loadUserById((int) id);
        return currentUser;
    }

    public void updateUser(User user, String name, String password) {
        user.setUser_name(name);
        user.setUser_password(password);
        repository.updateUser(user);
    }

    public int countUserBooks(long id) {
        return repository.countUserBooks((int) id);
    }

}
