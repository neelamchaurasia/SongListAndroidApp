package com.example.sonu.myapplication;

/**
 * Created by Sonu on 20-01-2018.
 */

public class Song {
    public Song() {
    }

    String song,artist;

    public Song(String song, String artist) {
        this.song = song;
        this.artist = artist;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
