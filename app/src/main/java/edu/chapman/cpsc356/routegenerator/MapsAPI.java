package edu.chapman.cpsc356.routegenerator;


import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MapsAPI {

    public static final String API_KEY = "AIzaSyDOMndWLUCRZKt4bK_JLdURrKoFjNwxTis";
    public static final String BASE_URL = "https://roads.googleapis.com";

    public static void Lookup(String input, final Callback callback)
    {
        String url = BASE_URL + "/v1/snapToRoads?" + input;

        OkHttpClient client = new OkHttpClient();

        Request req = new Request.Builder()
                .url(url)
                .build();

        client.newCall(req).enqueue(new com.squareup.okhttp.Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException
            {
                String json = response.body().string();

                Gson gson = new Gson();
                snappedPoints[] sp = gson.fromJson(json, snappedPoints[].class);

                callback.onResult(sp);
            }
        });
    }

    public interface Callback
    {
        void onFailure(Exception e);
        void onResult(snappedPoints[] results);
    }


}
