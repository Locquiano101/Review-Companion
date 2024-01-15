package com.example.reviewcompanion;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager {
    private static MediaPlayer musicPlayer;
    private static boolean isLooping = true;

    public static void initialize(Context context) {
        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(context, R.raw.background_music);
            musicPlayer.setLooping(isLooping);
        }
    }

    public static void startMusic() {
        if (musicPlayer != null && !musicPlayer.isPlaying()) {
            musicPlayer.start();
        }
    }

    public static void stopMusic() {
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.stop();
            musicPlayer.release();
            musicPlayer = null;
        }
    }
}
//import android.content.Context;
//        import android.media.MediaPlayer;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//public class MusicManager {
//    private static List<MediaPlayer> musicPlayers = new ArrayList<>();
//    private static boolean isLooping = true;
//
//    public static void initialize(Context context, int[] musicResources) {
//        for (int resId : musicResources) {
//            MediaPlayer mediaPlayer = MediaPlayer.create(context, resId);
//            mediaPlayer.setLooping(isLooping);
//            musicPlayers.add(mediaPlayer);
//        }
//    }
//
//    public static void startMusic(int index) {
//        if (index >= 0 && index < musicPlayers.size()) {
//            MediaPlayer mediaPlayer = musicPlayers.get(index);
//            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
//                mediaPlayer.start();
//            }
//        }
//    }
//
//    public static void stopMusic(int index) {
//        if (index >= 0 && index < musicPlayers.size()) {
//            MediaPlayer mediaPlayer = musicPlayers.get(index);
//            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                musicPlayers.set(index, null);
//            }
//        }
//    }
//
//    public static void releaseAllMusic() {
//        for (MediaPlayer mediaPlayer : musicPlayers) {
//            if (mediaPlayer != null) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//            }
//        }
//        musicPlayers.clear();
//    }
//
//    // Other methods as needed for controlling tracks, setting looping, etc.
//}
