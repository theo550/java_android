package com.example.gaudinth_library.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.persistence.repositories.BookRepository;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Flowable;

public class BookViewModel extends ViewModel {
    public List<Book> books = new LinkedList<>();
    private final BookRepository bookRepository;

    public BookViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flowable<List<Book>> getAll() {
        return bookRepository.getAll().map(bookList->{
            this.books.addAll(bookList);
            return this.books;
        });
    }

    public void insertBook(Book book) { bookRepository.insertBook(book);}

    public Flowable<List<Book>> loadUserBooks(long id) {
        return bookRepository.getAllUserBooks(id).map(bookList->{
            this.books.clear();
            this.books.addAll(bookList);
            return this.books;
        });
    }
}
