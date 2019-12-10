package com.example.breakingnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;

public  class Utility {

    private Utility()
    {

    }

    public static String fetch(String s1){
        URL url=null;
        if(s1!=null){
            url = createUrl(s1);}
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("ERRORS", "Error closing input stream", e);
        }


        return jsonResponse;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("Error", "Error with creating URL ", e);
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("ERROR", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("ERROR", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public static Bitmap getBitmap(String string){
        InputStream in =null;
        Bitmap bmp=null;
        HttpURLConnection con=null;
        int responseCode = -1;
        try{

            URL url = new URL(string);//"http://192.xx.xx.xx/mypath/img1.jpg
            con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //download
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
                con.disconnect();
            }

        }
        catch(Exception ex){
            Log.e("Exception",ex.toString());
        }
        return bmp;

    }

    public static ArrayList<Object> extractNews(String string) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Object> news = new ArrayList<>();
       Log.v("LISTEN TO ME12345",string);
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            String jsonResponse=fetch(string);
            for(int i=0;i<12;i++) {
                JSONObject root=new JSONObject(jsonResponse);
                JSONArray jarr=root.getJSONArray("articles");
                JSONObject j=jarr.getJSONObject(i);
                String title=j.getString("title");
                String published=j.getString("publishedAt");
                int index=published.indexOf('T');
                published=published.substring(0,index);
                StringTokenizer tr=new StringTokenizer(published,"-");
                String s1=tr.nextToken();
                String s2=tr.nextToken();
                String s3=tr.nextToken();
                String strbf=s3+"-"+s2+"-"+s1;
                String publishedAt="PUBLISHED AT : "+strbf;
                String description=j.getString("content");
                String imageUrl=j.getString("urlToImage");
                Bitmap bit=getBitmap(imageUrl);
                String url=j.getString("url");
                news.add(new Object(title,publishedAt,description,bit,url));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return news;
    }
}
