package com.example.gaudinth_library.persistence.repositories;

import android.app.Application;

import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.dao.BookDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class BookRepository {
    public BookDao bookDao;

    public BookRepository(Application application) {
        AppDataBase db = AppDataBase.getAppDatabase(application);
        bookDao = db.bookDao();
    }

    public Completable insertBook(Book book) {
        return bookDao.insertBook(book);
    }
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }
    public Flowable<List<Book>> getAllUserBooks(long id) {
        return bookDao.loadUserBooks((int) id);
    }

    public Flowable<List<Book>> getAll() {
        return bookDao.getAll();
    }
}
