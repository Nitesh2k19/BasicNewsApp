package com.example.breakingnews;

public class CategoryObject {
    int imageId;
    String category;
    CategoryObject(int a,String s){
        imageId=a;
        category=s;
    }
    public int getImage(){
        return imageId;
    }
    public String getTitle(){
        return category;
    }
}
