package com.example.listexample;

import android.media.Image;

/**
 * Created by S_peyman on 3/12/2018.
 */

public class ListObject {
    public String getCity() {
        return City;
    }

    private String names;
    private String City;
    public ListObject(String url_image) {

    }

    private String url_image;


    public ListObject(String names, String  image,String mCity) {
        this.names = names;
        this.url_image = image;
        this.City=mCity;
    }

    public String getImage_url() {
        return url_image;
    }

    public String getNames() {
        return names;
    }
}
