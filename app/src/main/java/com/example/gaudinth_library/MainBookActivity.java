package com.example.gaudinth_library;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.classes.BookAdapter;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.classes.UserAdapter;
import com.example.gaudinth_library.databinding.ActivityMainBookBinding;
import com.example.gaudinth_library.fragments.AddNewBookFragment;
import com.example.gaudinth_library.fragments.AddNewUserFragment;
import com.example.gaudinth_library.fragments.AuthorsFragment;
import com.example.gaudinth_library.fragments.HomeFragment;
import com.example.gaudinth_library.fragments.ProfileFragment;
import com.example.gaudinth_library.interfaces.AddBookListener;
import com.example.gaudinth_library.interfaces.AddUserListener;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.viewModel.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainBookActivity extends AppCompatActivity {
    private ActivityMainBookBinding binding;
    public User currentUser;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        long userId = getIntent().getLongExtra("USER", 0);
        UserViewModel userViewModel = new UserViewModel(MainBookActivity.this.getApplication());
        currentUser = userViewModel.setCurrentUser(userId);

        // Set home page on load
        _replaceFragment(new HomeFragment());

        // handle switch navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.toString()) {
                case "Home":
                    _replaceFragment(new HomeFragment());
                    break;
                case "Authors":
                    _replaceFragment(new AuthorsFragment());
                    break;
                case "User":
                    _replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });

    }

    private void _replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }

}