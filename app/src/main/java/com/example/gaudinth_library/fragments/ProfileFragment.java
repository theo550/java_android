package com.example.gaudinth_library.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gaudinth_library.MainActivity;
import com.example.gaudinth_library.MainBookActivity;
import com.example.gaudinth_library.R;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.repositories.UserRepository;
import com.example.gaudinth_library.viewModel.UserViewModel;

public class ProfileFragment extends Fragment {
    public MainBookActivity activity;
    public UserViewModel userViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainBookActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        EditText userNameInput = rootView.findViewById(R.id.profile_user_name);
        EditText userPasswordInput = rootView.findViewById(R.id.profile_user_password);

        userViewModel = new UserViewModel(activity.getApplication());

        userNameInput.setText(activity.currentUser.getUser_name());
        userPasswordInput.setText(activity.currentUser.getUser_password());

        TextView nbOfBooks = rootView.findViewById(R.id.books_number);
        nbOfBooks.setText("Vous avez " + userViewModel.countUserBooks(activity.currentUser.getUid()) + " livres.");

        rootView.findViewById(R.id.save_user_changes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUserName = userNameInput.getText().toString();
                String newUserPassword = userPasswordInput.getText().toString();

                userViewModel.updateUser(activity.currentUser, newUserName, newUserPassword);
            }
        });

        rootView.findViewById(R.id.disconnect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}