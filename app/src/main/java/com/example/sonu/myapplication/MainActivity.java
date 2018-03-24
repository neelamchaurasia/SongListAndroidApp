package com.example.sonu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Song> songs;
    RecyclerView recyclerView;
    SongsAdapter adapter;

    final String LOG_TAG="LOG";
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper=new  SqliteHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.songsrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        songs = new ArrayList<>();
        getSongs();
    }
       /* songsList.add(new Song("afreen","rahat"));
        songsList.add(new Song("aa","bb"));
        songsList.add(new Song("cc","dd"));
        songsList.add(new Song("ee","ff"));
        songsList.add(new Song("gg","hh"));*/

    public void getSongs() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, "http://starlord.hackerearth.com/studio", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v(LOG_TAG, response);
                try {
                    JSONArray root = new JSONArray(response);
                    for (int i = 0; i < root.length(); i++) {
                        JSONObject songInfo = root.optJSONObject(i);
                        String song_name = songInfo.optString("song");
                        String artist_name = songInfo.optString("artists");
//                        songs.add(new Song(song_name, artist_name));
                        sqliteHelper.insertSong(new Song(song_name, artist_name));

                    }
                    songs=sqliteHelper.getAllSongs();
                    adapter = new SongsAdapter(songs, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(LOG_TAG, "error");
                error.printStackTrace();
                songs=sqliteHelper.getAllSongs();
                adapter = new SongsAdapter(songs, MainActivity.this);
                recyclerView.setAdapter(adapter);

            }
        });
        queue.add(request);
    }
}
