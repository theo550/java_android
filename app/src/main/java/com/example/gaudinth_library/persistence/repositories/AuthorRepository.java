package com.example.gaudinth_library.persistence.repositories;

import android.app.Application;

import com.example.gaudinth_library.classes.Author;
import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.persistence.AppDataBase;
import com.example.gaudinth_library.persistence.dao.AuthorDao;
import com.example.gaudinth_library.persistence.dao.BookDao;

import java.util.List;

import io.reactivex.Flowable;

public class AuthorRepository {
    public AuthorDao authorDao;

    public AuthorRepository(Application application) {
        AppDataBase db = AppDataBase.getAppDatabase(application);
        authorDao = db.authorDao();
    }

    public void insertAuthor(Author author) {
        authorDao.insertAuthor(author);
    }
    public void updateAuthor(Author author) {
        authorDao.updateAuthor(author);
    }
    public void deleteAuthor(Author author) {
        authorDao.deleteAuthor(author);
    }
    public Author getAuthorById(long id) {
        return authorDao.loadAuthorById((int) id);
    }

    public Flowable<List<Author>> getAll() {
        return authorDao.getAll();
    }
}
