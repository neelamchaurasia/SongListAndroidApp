package com.example.sonu.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sonu on 20-01-2018.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {
    List<Song> songsList;
    Context context;


    public SongsAdapter(List<Song> songsList,Context context) {
        this.songsList = songsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Song someSong = songsList.get(position);

        holder.song.setText(someSong.getSong());
        holder.artist.setText(someSong.getArtist());

    }

    @Override
    public int getItemCount() {
        if(songsList == null)
            return 0;
        else return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView song,artist;

        public ViewHolder(View itemView) {
            super(itemView);

            song = (TextView) itemView.findViewById(R.id.song_name);
            artist = (TextView) itemView.findViewById(R.id.artist_name);

        }
    }
}
