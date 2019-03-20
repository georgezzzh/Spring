package AopPassArgs;

import java.util.List;

public class BlankDisc implements CompactDisc {
    private String title;
    private String artist;
    private List<String> tracks;
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public void setArtist(String artist){
        this.artist=artist;
    }
    @Override
    public void setTracks(List tracks){
        this.tracks=tracks;
    }
    @Override
    public void play(){
        System.out.println("Playing "+title+" By "+artist);
        for(String track:tracks)
            System.out.println("-Tracks: "+track);
    }
    @Override
    public void playTrack(int trackNumber){
        System.out.println("Now playing "+trackNumber+"-th tracks");
    }
}
