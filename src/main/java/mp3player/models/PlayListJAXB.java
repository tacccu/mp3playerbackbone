/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3player.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="reproductor")
@XmlAccessorType(XmlAccessType.NONE)
/**
 *
 * @author Ivan
 */
public class PlayListJAXB implements Serializable{
    @XmlElementWrapper(name="songs")
    @XmlElement(name="song")
    private List<Song> songList;
    @XmlElementWrapper(name="playlists")
    @XmlElement(name="playlist")
    private Map<String, Song> playList;

    public PlayListJAXB() {
        songList = new ArrayList<>();
        playList = new HashMap<>();
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public Map<String, Song> getPlayList() {
        return playList;
    }

    public void setClients(Map<String, Song> playList) {
        this.playList = playList;
    }
    
    public Song getSong(int i) {
        return songList.get(i);
    }
    
    public void addSong(Song b) {
        songList.add(b);
    }
    
    public Song removeSong(int i) {
        return songList.remove(i);
    }

    @Override
    public String toString() {
        return "PlayListJAXB{" + "songList=" + songList + '}';
    }

    
}
