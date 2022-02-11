
package mp3player.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="playlist")
@XmlAccessorType(XmlAccessType.NONE)
/**
 *
 * @author Cristòfol-Lluís Thwaite Rivas
 */
public class PlayList {
    
    @XmlElement
    private String title;
    @XmlElement
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
