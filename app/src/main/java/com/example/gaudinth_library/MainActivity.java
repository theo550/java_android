package com.example.gaudinth_library;

import android.content.Intent;
import android.os.Bundle;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.classes.UserAdapter;
import com.example.gaudinth_library.databinding.ActivityMainBinding;
import com.example.gaudinth_library.fragments.AddNewUserFragment;
import com.example.gaudinth_library.interfaces.AddUserListener;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.dao.UserDao;
import com.example.gaudinth_library.persistence.repositories.UserRepository;
import com.example.gaudinth_library.viewModel.MainActivityViewModel;
import com.example.gaudinth_library.viewModel.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public MainActivityViewModel mainActivityViewModel;
    public UserViewModel userViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserRepository userRepository = new UserRepository(getApplication());
        mainActivityViewModel = new MainActivityViewModel(userRepository);
        userViewModel = new UserViewModel(this.getApplication());

        this.users = mainActivityViewModel.users;
        ArrayAdapter<User> userAdapter = new UserAdapter(MainActivity.this, R.layout.list_item_layout, this.users);
        binding.listView.setAdapter(userAdapter);

        compositeDisposable.add(
            mainActivityViewModel.loadUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    userAdapter.notifyDataSetChanged();
            },
            throwable -> {
                Log.e("data", throwable.getMessage());
            })
        );

        binding.addUserProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddNewUserFragment bottomSheetFragment = new AddNewUserFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MainBookActivity.class);
                intent.putExtra("USER", mainActivityViewModel.users.get(position).getUid());
                startActivity(intent);
            }
        });

    }
}