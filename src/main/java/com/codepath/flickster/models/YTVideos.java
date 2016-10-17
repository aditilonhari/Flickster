package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aditi on 10/15/2016.
 */

public class YTVideos {

    public String getVideoKey() {
        return videoKey;
    }

    String videoKey;

    public YTVideos(JSONObject jsonObject) throws JSONException {
        this.videoKey = jsonObject.getString("key");
    }

    public static ArrayList<YTVideos> fromJSONArray(JSONArray array){
        ArrayList<YTVideos> videos = new ArrayList<>();

        for(int i=0; i < array.length(); i++){
            try {
                videos.add(new YTVideos(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return videos;
    }
}
