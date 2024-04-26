package com.example.gaudinth_library.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.gaudinth_library.classes.Author;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.persistence.repositories.AuthorRepository;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Flowable;

public class AuthorViewModel extends ViewModel{
    public List<Author> authors = new LinkedList<>();

    private final AuthorRepository authorRepository;

    public AuthorViewModel(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Flowable<List<Author>> getAll() {
        return authorRepository.getAll().map(authorList -> {
            this.authors.clear();
            this.authors.addAll(authorList);
            return this.authors;
        });
    }

    public void insertAuthor(Author author) {
        authorRepository.insertAuthor(author);
    }
}
