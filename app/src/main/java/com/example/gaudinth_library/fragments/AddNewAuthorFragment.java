package com.example.gaudinth_library.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gaudinth_library.MainBookActivity;
import com.example.gaudinth_library.R;
import com.example.gaudinth_library.classes.Author;
import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.interfaces.AddAuthorListener;
import com.example.gaudinth_library.interfaces.AddBookListener;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.repositories.AuthorRepository;
import com.example.gaudinth_library.viewModel.AuthorViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class AddNewAuthorFragment extends BottomSheetDialogFragment {
    public MainBookActivity activity;
    public AuthorViewModel authorViewModel;

    public void setAddAuthorListener() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainBookActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_new_author, container, false);

        assert activity != null;
        authorViewModel = new AuthorViewModel(new AuthorRepository(activity.getApplication()));

        EditText authorTitle = rootView.findViewById(R.id.new_author_name);
        Button newAuhtorButton = rootView.findViewById(R.id.addNewAuthor);

        newAuhtorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = authorTitle.getText().toString().trim();
                authorTitle.setText("");

                authorViewModel.insertAuthor(new Author(name));

                dismiss();
            }
        });

        return rootView;
    }
}