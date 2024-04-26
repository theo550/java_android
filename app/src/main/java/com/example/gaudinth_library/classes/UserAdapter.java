package com.example.gaudinth_library.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gaudinth_library.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public int layoutResource;

    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        layoutResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        User user = getItem(position);

        if (user != null) {
            TextView nameTextView = view.findViewById(R.id.textView_userName);

            nameTextView.setText(user.getUser_name());
        }

        return view;
    }
}
