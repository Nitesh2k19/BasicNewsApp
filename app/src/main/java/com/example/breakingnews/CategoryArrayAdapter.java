package com.example.breakingnews;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CategoryArrayAdapter extends ArrayAdapter<CategoryObject> {

    public CategoryArrayAdapter(Activity con, ArrayList<CategoryObject> arr){
        super(con,0,arr);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.category_style, parent,false);
        }
        CategoryObject b = getItem(position);
        ImageView c=(ImageView)listItemView.findViewById(R.id.img);
        c.setImageResource(b.getImage());
        TextView c1=(TextView)listItemView.findViewById(R.id.category);
        c1.setText(b.getTitle());
        return listItemView;

    }
}
