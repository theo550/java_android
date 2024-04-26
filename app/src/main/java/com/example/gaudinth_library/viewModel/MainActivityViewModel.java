package com.example.gaudinth_library.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.persistence.repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Flowable;

public class MainActivityViewModel extends ViewModel {
    public List<User> users = new LinkedList<>();
    private final UserRepository userRepository;

    public MainActivityViewModel (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flowable<List<User>> loadUsers() {
       return userRepository.getAllUsers().map(userList->{
           this.users.clear();
            this.users.addAll(userList);
            return this.users;
        });
    }
}
