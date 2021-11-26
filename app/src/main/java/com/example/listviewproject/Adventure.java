package com.example.listviewproject;

import java.io.Serializable;

public class Adventure implements Serializable
{

    int image = 0;
    String title = "";
    String place = "";
    int thrill = 0;
    String description = "";

    public Adventure(int image, String title, String place, int thrill, String description)
    {
        this.image = image;
        this.title = title;
        this.place = place;
        this.thrill = thrill;
        this.description = description;
    }

    public int getImage()
    {

        return image;


    }

    public String getTitle()
    {
        return title;
    }

    public String getLoc()
    {
        return place;
    }

    public int getThrill()
    {
        return thrill;
    }

    public String getDesc()
    {
        return description;
    }



}
