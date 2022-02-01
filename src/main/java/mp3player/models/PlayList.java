
package mp3player.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristòfol-Lluís Thwaite Rivas
 */
public class PlayList {
    
    private String title;
    private List<Song> songList;
    
    public PlayList() {
        songList = new ArrayList<>();
    }

    public PlayList(String title, List<Song> songList) {
        this.title = title;
        this.songList = songList;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return title;
    }
    
}
