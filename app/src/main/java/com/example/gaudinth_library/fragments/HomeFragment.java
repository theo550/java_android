package com.example.gaudinth_library.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaudinth_library.MainActivity;
import com.example.gaudinth_library.MainBookActivity;
import com.example.gaudinth_library.R;
import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.classes.BookAdapter;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.classes.UserAdapter;
import com.example.gaudinth_library.interfaces.AddBookListener;
import com.example.gaudinth_library.interfaces.AddUserListener;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.repositories.BookRepository;
import com.example.gaudinth_library.persistence.repositories.UserRepository;
import com.example.gaudinth_library.viewModel.BookViewModel;
import com.example.gaudinth_library.viewModel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    public MainBookActivity activity;
    public BookViewModel bookViewModel;
    public UserViewModel userViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public List<Book> books;

    public HomeFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView helloText = rootView.findViewById(R.id.hello_user);
        helloText.setText("Bonjour " + activity.currentUser.getUser_name());

        ListView bookList = rootView.findViewById(R.id.book_list);

        BookRepository bookRepository = new BookRepository(activity.getApplication());
        bookViewModel = new BookViewModel(bookRepository);

        ArrayAdapter<Book> bookArrayAdapter = new BookAdapter(activity, R.layout.list_item_layout, bookViewModel.books);
        bookList.setAdapter(bookArrayAdapter);

        compositeDisposable.add(
            bookViewModel.loadUserBooks(activity.currentUser.getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                            bookArrayAdapter.notifyDataSetChanged();
                        },
                    throwable -> {
                        Log.e("data", throwable.getMessage());
                    }
                )
        );

        FloatingActionButton addBookButton = rootView.findViewById(R.id.fab);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewBookFragment bottomSheetFragment = new AddNewBookFragment();
                bottomSheetFragment.show(activity.getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        return rootView;
    }

}