package com.example.gaudinth_library.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gaudinth_library.R;

import java.util.List;

import io.reactivex.Flowable;

public class AuthorAdapter extends ArrayAdapter<Author> {
    private final int layoutResource;

    public AuthorAdapter(Context context, int resource, List<Author> objects) {
        super(context, resource, objects);
        layoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        Author author = getItem(position);

        if (author != null) {
            TextView titleTextView = view.findViewById(R.id.textView_userName);

            titleTextView.setText(author.getAuthor_name());
        }

        return view;
    }
}
