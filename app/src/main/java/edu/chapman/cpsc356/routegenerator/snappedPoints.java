package edu.chapman.cpsc356.routegenerator;

import com.google.gson.annotations.SerializedName;

//returned as "snappedPoints" from API
public class snappedPoints
{
    @SerializedName("location")
    public String location;

    @SerializedName("originalIndex")
    public String originalIndex;

    @SerializedName("placeId")
    public String placeID;
}