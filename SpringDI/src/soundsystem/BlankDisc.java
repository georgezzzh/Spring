package soundsystem;
import java.util.List;
public class BlankDisc implements CompactDisc{
    private String title;
    private String artist;
    private List<String> tracks;
    public void setTitle(String title) {
        this.title = title;
    }
    public void setArtist(String artist){
        this.artist=artist;
    }
    public void setTracks(List tracks){
        this.tracks=tracks;
    }

    public void play(){
        System.out.println("Playing "+title+" By "+artist);
        for(String track:tracks)
            System.out.println("-Tracks: "+track);
    }
    public void playTrack(int trackNumber){

    }
}
