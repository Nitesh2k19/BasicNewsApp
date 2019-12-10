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

public class CustomAdapter extends ArrayAdapter<Object> {

    public CustomAdapter(Activity con, ArrayList<Object> arr){
        super(con,0,arr);
    }
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.style, parent,false);
        }
        Object b = getItem(position);
        ImageView c=(ImageView)listItemView.findViewById(R.id.img);
        c.setImageBitmap(b.getImage());
        TextView c1=(TextView)listItemView.findViewById(R.id.title);
        c1.setText(b.getTitle());
        TextView d=(TextView)listItemView.findViewById(R.id.publishedAt);
        d.setText(b.getPublishedAt());
        TextView e=(TextView)listItemView.findViewById(R.id.description);
        e.setText(b.getDescription());
        return listItemView;

    }
    public String url(int position){
        Object b=getItem(position);
        String s=b.getUrl();
        return s;
    }

}
