package com.purplemovies.instagramclient;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.purplemovies.instagramclient.datastructures.InstagramMedia;
import com.purplemovies.instagramclient.datastructures.InstagramMediaAdapter;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;


public class InstagramActivity extends ActionBarActivity {

    public static final String CLIENT_ID = "4beb3be8ea644a97a1c217a2144e5ab9";
    private ArrayList<InstagramMedia> media;
    private InstagramMediaAdapter instagramMediaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        media = new ArrayList<>();

        instagramMediaAdapter = new InstagramMediaAdapter(this, media);

        ListView mediaListView = (ListView) findViewById(R.id.photosListView);

        mediaListView.setAdapter(instagramMediaAdapter);

        // send request
        fetchPopularPhotos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram, menu);
        return true;
    }

    public void fetchPopularPhotos() {

        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        AsyncHttpClient client = new AsyncHttpClient();

        final RequestHandle requestHandle = client.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray mediaJson;
                try {
                    mediaJson = response.getJSONArray("data");

                    for (int i = 0; i < mediaJson.length(); i++) {
                        final JSONObject mJson = mediaJson.getJSONObject(i);

                        final JSONObject userJsonObject = mJson.getJSONObject("user");
                        final String authorName = userJsonObject.getString("username");
                        final String profileImageUrl = userJsonObject.getString("profile_picture");
                        final String caption = mJson.getJSONObject("caption").getString("text");
                        final String createdTimeString = mJson.getString("created_time");
                        final String imageUrl = mJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        final int imageHeight = mJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        final int likesCount = mJson.getJSONObject("likes").getInt("count");
                        final Date createdTime = new Date(Long.parseLong(createdTimeString) * 1000);

                        InstagramMedia m = new InstagramMedia("photo", caption, imageUrl, authorName, profileImageUrl, imageHeight, likesCount, createdTime);
                        media.add(m);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                instagramMediaAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
