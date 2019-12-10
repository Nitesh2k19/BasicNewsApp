package com.example.breakingnews;

import android.graphics.Bitmap;

public class Object {

    String title,publishedAt,description,url;
    Bitmap image;
    public   Object(String a,String b,String c,Bitmap d,String e){
        title=a;
        publishedAt=b;
        description=c;
        image=d;
        url=e;
    }
    public String getTitle(){
        return title;
    }
    public String getPublishedAt()
    {
        return publishedAt;
    }
    public String getDescription()
    {
        return description;
    }
    public Bitmap getImage(){
        return image;
    }
    public String getUrl(){
        return url;
    }
}
