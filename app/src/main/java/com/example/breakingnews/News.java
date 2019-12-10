package com.example.breakingnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class News extends AppCompatActivity {

        private void goToUrl(String url) {
            Uri uriUrl = Uri.parse(url);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);


        }
    Bundle bundle;
    String sk;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsAsync a=new newsAsync();

        ListView l=findViewById(R.id.list);
        int back= ContextCompat.getColor(this, R.color.white);
        l.setBackgroundColor(back);
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            findViewById(R.id.internet).setVisibility(View.GONE);}
         bundle=getIntent().getExtras();
       String  s=bundle.getString("category");
        sk="https://newsapi.org/v2/top-headlines?category="+s+"&country=in&apiKey=4e0ed9699cfd4357997c11b9968a3fd4";
         Log.v("PLEASE SEE",sk);
        a.execute(sk);
    }
    private class newsAsync extends AsyncTask<String,Void, ArrayList<Object>> {
        @Override
        protected ArrayList<Object> doInBackground(String... strings) {

            ArrayList<Object> b=Utility.extractNews(strings[0]);
            return b;

        }

        @Override
        protected void onPostExecute(ArrayList<Object> objects) {
            final CustomAdapter adapter=new CustomAdapter(News.this,objects);
            findViewById(R.id.wait).setVisibility(View.GONE);
            findViewById(R.id.loading_spinner).setVisibility(View.GONE);
            ListView list=(ListView)findViewById(R.id.list);


            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    goToUrl(adapter.url(position));
                }
            });
        }
    }
}
