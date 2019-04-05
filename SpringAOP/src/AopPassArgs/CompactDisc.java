package AopPassArgs;

import java.util.List;

public interface CompactDisc {
    public void play();
    public void playTrack(int num);

    public void setTitle(String title);
    public void setArtist(String artist);
    public void setTracks(List track);


}
