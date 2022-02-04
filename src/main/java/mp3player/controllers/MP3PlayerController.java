package mp3player.controllers;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import mp3player.models.PlayList;
import mp3player.models.Song;
import mp3player.views.MP3PlayerView;

/**
 *
 * @author Cristòfol-Lluís Thwaite
 */
public class MP3PlayerController {

    private MP3PlayerView v;
    private List<Song> songList;
    private Song currentTrack;
    private int trackPosInPlayList;
    private MediaPlayer player;

    public MP3PlayerController(List<Song> songList, MP3PlayerView v) {
        this.songList = songList;
        this.v = v;
        player = null;
        initView();
    }

    public void initView() {
        v.getTitle().setCellValueFactory(new PropertyValueFactory<>("title"));
        v.getGenre().setCellValueFactory(new PropertyValueFactory<>("genre"));
        v.getArtist().setCellValueFactory(new PropertyValueFactory<>("artist"));
        v.getAlbum().setCellValueFactory(new PropertyValueFactory<>("album"));
        v.getTime().setCellValueFactory(new PropertyValueFactory<>("timeFormat"));
        PlayList p = new PlayList("All Songs", songList);
        v.getPlayListsList().getItems().add(p);
        getDataFromXml();
        /*songList.forEach(s -> {
            v.getTrackTable().getItems().add(s);
        });*/
        trackPosInPlayList = 0;
        currentTrack = songList.get(trackPosInPlayList);
        v.getCurrentTitle().setText(currentTrack.getTitle());
        v.getCurrentArtist().setText(currentTrack.getArtist());
        v.getTotalTime().setText(currentTrack.getTimeFormat());
        loadCurrentTrack();
        v.getAudioControls().getChildren().add(v.getMedia());

    }

    public void initController() {
        v.getPlayPause().setOnAction((e) -> playPausePlayer()); // evento play pausa

        v.getSlider().maxProperty().bind(Bindings.createDoubleBinding(
                () -> player.getTotalDuration().toSeconds(),
                player.totalDurationProperty()
        )
        );

        //Change label every time current time changes
        player.currentTimeProperty().addListener(
                (observableValue, oldDuration, newDuration) -> {
                    v.getSlider().setValue(newDuration.toSeconds());
                    v.getCurrentTime().setText(
                            Song.formatDuration(newDuration)
                    );
                }
        );

        v.getSlider().setOnMousePressed((e) -> {
            if (player.getStatus() == Status.PLAYING) {
                player.pause();
            }
        });

        v.getSlider().setOnMouseReleased((e) -> {
            player.seek(Duration.seconds(v.getSlider().getValue()));
            if (v.getPlayPause().getStyleClass().contains("pause")) {
                player.play();
            }
        });
    }

    private void playPausePlayer() {
        if (v.getPlayPause().getStyleClass().contains("play")) {
            v.getPlayPause().getStyleClass().remove("play");
            v.getPlayPause().getStyleClass().add("pause");
            v.getPlayPause().setGraphic(
                    GlyphsDude.createIcon(FontAwesomeIcon.PAUSE, "9px")
            );
            player.play();
        } else {
            v.getPlayPause().getStyleClass().remove("pause");
            v.getPlayPause().getStyleClass().add("play");
            v.getPlayPause().setGraphic(
                    GlyphsDude.createIcon(FontAwesomeIcon.PLAY, "9px")
            );
            player.pause();
        }
    }

    private void loadCurrentTrack() {
        try {
            Media media = new Media(
                    MP3PlayerController.class.getClassLoader()
                            .getResource(currentTrack.getSongPath())
                            .toURI().toString());
            player = new MediaPlayer(media);
            v.setMedia(new MediaView(player));
        } catch (URISyntaxException e) {
            System.err.println();
        }
    }

    private void getDataFromXml() {
        //TODO añadir los datos de las playlists del xml a java
        try {
            //JAVA bean context for Song class
            JAXBContext context = JAXBContext.newInstance(Song.class);
            //unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            
            Song s = (Song) unmarshaller.unmarshal(
                    new File("src/main/resources/configuration/songs.xml")
            );
            System.out.println(s);
            //PlayList p = new PlayList("All Songs", songList);
            /*songList.forEach(i -> {
                System.out.println(s);
                //v.getTrackTable().getItems().add(s);
            });*/
        } catch (JAXBException ex) {
            System.err.println(
                    "Error amb el serialitzador JAXB: " + ex.getMessage()
            );
        }
        //PlayList p = new PlayList("All Songs", songList);
    }

    private void javatojsonPlaylists() {
        //TODO poner los datos de las playlists en su archivo correspondiente
    }
}
