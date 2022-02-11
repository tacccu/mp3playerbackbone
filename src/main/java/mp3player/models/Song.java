
package mp3player.models;

import java.io.Serializable;
import javafx.util.Duration;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="song")
@XmlAccessorType(XmlAccessType.NONE)
/**
 *
 * @author Cristòfol-Lluís Thwaite
 */
public class Song implements Serializable{

    @XmlElement
    private String title;
    @XmlElement
    private String genre;
    @XmlElement
    private String artist;
    @XmlElement
    private String album;
    @XmlElement
    private String time;
    private String timeFormat;
    @XmlElement
    private String songPath;

    public Song() {
    }

    public Song(
            String title,
            String genre,
            String artist,
            String album,
            String time,
            String songPath
    ) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.album = album;
        this.time = time;
        timeFormat = formatDuration(timeParse(time));
        this.songPath = songPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    @Override
    public String toString() {
        return "Song{" + "title=" + title + ", genre=" + genre + ", artist="
                + artist + ", album=" + album + ", time=" + time
                + ", songPath=" + songPath + '}';
    }

    //Metodo para pasar el time a String
    public static Duration timeParse(String time) {
        return Duration.valueOf(time);
    }
    
    public static String formatDuration(Duration duration) {
        double seconds = duration.toSeconds();
        int mm = (int) (seconds % 3600) / 60;
        int ss = (int) seconds % 60;
        return String.format("%02d:%02d", mm, ss);
    }

    
}
