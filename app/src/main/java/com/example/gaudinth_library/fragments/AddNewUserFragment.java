package com.example.gaudinth_library.fragments;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;

import com.example.gaudinth_library.MainActivity;
import com.example.gaudinth_library.R;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.interfaces.AddUserListener;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.viewModel.UserViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;


public class AddNewUserFragment extends BottomSheetDialogFragment {

    private AddUserListener listener;
    public UserViewModel userViewModel;

    public void setAddUserListener(AddUserListener listener) {
        // Required empty public constructor
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_new_user, container, false);

        userViewModel = new UserViewModel(this.getActivity().getApplication());

        EditText editTextName = rootView.findViewById(R.id.editTextName);
        Button addUserButton = rootView.findViewById(R.id.addUserButton);

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                editTextName.setText("");

                userViewModel.insert(new User(name, "", ""));

                dismiss();
            }
        });
        return rootView;
    }
}