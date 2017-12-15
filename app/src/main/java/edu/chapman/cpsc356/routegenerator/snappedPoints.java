package edu.chapman.cpsc356.routegenerator;

import com.google.gson.annotations.SerializedName;

public class snappedPoints
{
    @SerializedName("location")
    public String location;

    @SerializedName("originalIndex")
    public String originalIndex;

    @SerializedName("placeId")
    public String placeID;
}

//{
//        "snappedPoints": [
//        {
//        "location": {
//        "latitude": -35.278004899930188,
//        "longitude": 149.129531998742
//        },
//        "originalIndex": 0,
//        "placeId": "ChIJr_xl0GdNFmsRsUtUbW7qABM"
//        }
//}