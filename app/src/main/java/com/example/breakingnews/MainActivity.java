package com.example.breakingnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<CategoryObject> arr = new ArrayList<>();
        arr.add(new CategoryObject(R.drawable.sports, "Sports"));
        arr.add(new CategoryObject(R.drawable.science, "Science"));
        arr.add(new CategoryObject(R.drawable.entertainment, "Entertainment"));
        arr.add(new CategoryObject(R.drawable.technology, "Technology"));
        arr.add(new CategoryObject(R.drawable.business, "Business"));
        arr.add(new CategoryObject(R.drawable.health, "Health"));

        CategoryArrayAdapter adapter = new CategoryArrayAdapter(this, arr);
        GridView g=findViewById(R.id.grid);
        g.setAdapter(adapter);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category(position);
            }
        });
    }
    public void category(int position){
        Intent i=new Intent(this,News.class);
        switch(position){
            case 0:
                i.putExtra("category","sports");
                break;
            case 1:
                i.putExtra("category","science");
                break;
            case 2:
                i.putExtra("category","entertainment");
                break;
            case 3:
                i.putExtra("category","technology");
                break;
            case 4:
                i.putExtra("category","business");
                break;
            case 5:
                i.putExtra("category","health");
                break;

        }
        startActivity(i);
    }
}
