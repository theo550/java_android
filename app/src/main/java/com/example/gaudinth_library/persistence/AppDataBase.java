package com.example.gaudinth_library.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gaudinth_library.classes.Author;
import com.example.gaudinth_library.classes.Book;
import com.example.gaudinth_library.classes.User;
import com.example.gaudinth_library.persistence.dao.AuthorDao;
import com.example.gaudinth_library.persistence.dao.BookDao;
import com.example.gaudinth_library.persistence.dao.UserDao;

@Database(entities = {User.class, Book.class, Author.class}, version = 4)
public abstract class AppDataBase extends RoomDatabase {
    private static volatile AppDataBase INSTANCE;
    public abstract UserDao userDao();
    public abstract BookDao bookDao();
    public abstract AuthorDao authorDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized(AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "library_db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
