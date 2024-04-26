package com.example.gaudinth_library.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gaudinth_library.MainBookActivity;
import com.example.gaudinth_library.R;
import com.example.gaudinth_library.classes.Author;
import com.example.gaudinth_library.classes.AuthorAdapter;
import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.interfaces.AddBookListener;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.repositories.AuthorRepository;
import com.example.gaudinth_library.persistence.repositories.BookRepository;
import com.example.gaudinth_library.viewModel.AuthorViewModel;
import com.example.gaudinth_library.viewModel.BookViewModel;
import com.example.gaudinth_library.viewModel.UserViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;

public class AddNewBookFragment extends BottomSheetDialogFragment {
    public MainBookActivity activity;
    public BookViewModel bookViewModel;

    public void setAddBookListener() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainBookActivity) getActivity();

        assert activity != null;
        bookViewModel = new BookViewModel(new BookRepository(activity.getApplication()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_new_book, container, false);

        EditText bookTitle = rootView.findViewById(R.id.new_book_name);
        Button newBookButton = rootView.findViewById(R.id.addNewBook);

        Spinner spinner = rootView.findViewById(R.id.selectAuthor);
        AuthorViewModel authorViewModel = new AuthorViewModel(new AuthorRepository(activity.getApplication()));
        String[] authorNames = transformToNameArray(authorViewModel.getAll().blockingFirst());

        Log.i("authors", " " + authorViewModel.getAll());

        ArrayAdapter<String> authorAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, authorNames);
        spinner.setAdapter(authorAdapter);

        newBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = bookTitle.getText().toString().trim();
                final Author[] selectedAuthor = new Author[1];
                bookTitle.setText("");

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedAuthor[0] = authorViewModel.authors.get(position);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                bookViewModel.insertBook(new Book(activity.currentUser.uid, title, selectedAuthor[0]));

                dismiss();
            }
        });

        return rootView;
    }
    public static String[] transformToNameArray(List<Author> authors) {
        List<String> nameList = new ArrayList<>();
        for (Author author : authors) {
            nameList.add(author.getAuthor_name());
        }

        return nameList.toArray(new String[0]);
    }


    }