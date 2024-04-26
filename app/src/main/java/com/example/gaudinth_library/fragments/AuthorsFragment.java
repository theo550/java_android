package com.example.gaudinth_library.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gaudinth_library.MainBookActivity;
import com.example.gaudinth_library.R;
import com.example.gaudinth_library.classes.Author;
import com.example.gaudinth_library.classes.AuthorAdapter;
import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.classes.BookAdapter;
import com.example.gaudinth_library.interfaces.AddAuthorListener;
import com.example.gaudinth_library.interfaces.AddBookListener;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.repositories.AuthorRepository;
import com.example.gaudinth_library.viewModel.AuthorViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AuthorsFragment extends Fragment {
    public MainBookActivity activity;
    public AuthorViewModel authorViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AuthorsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainBookActivity) getActivity();

        //assert activity != null;
        //authorViewModel = new AuthorViewModel(new AuthorRepository(activity.getApplication()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_authors, container, false);
        _updateViewList(rootView);

        ListView authorsList = rootView.findViewById(R.id.authors_list);

        authorViewModel = new AuthorViewModel(new AuthorRepository(activity.getApplication()));

        ArrayAdapter<Author> authorArrayAdapter = new AuthorAdapter(activity, R.layout.list_item_layout, authorViewModel.authors);
        authorsList.setAdapter(authorArrayAdapter);

        compositeDisposable.add(
            authorViewModel.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                            authorArrayAdapter.notifyDataSetChanged();
                        },
                    throwable -> {
                        Log.e("data", throwable.getMessage());
                    }
                )
        );

        FloatingActionButton addBookButton = rootView.findViewById(R.id.add_new_author);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAuthorFragment bottomSheetFragment = new AddNewAuthorFragment();
                bottomSheetFragment.show(activity.getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        return rootView;
    }

    private void _updateViewList(View rootView) {

    }
}