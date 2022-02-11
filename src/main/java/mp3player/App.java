package mp3player;

import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mp3player.models.Song;
import mp3player.views.MP3PlayerView;
import mp3player.controllers.MP3PlayerController;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MP3PlayerView view = new MP3PlayerView();
        MP3PlayerController c = new MP3PlayerController(startingSongList(), view);
        c.initController();
        var scene = new Scene(view.getRootPane(), 1280, 720);
        scene.getStylesheets().add(
                App.class.getClassLoader().getResource("css/MP3PlayerView.css")
                        .toExternalForm()
        );
        stage.setScene(scene);
        stage.setTitle("MP3 Player");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private static List<Song> startingSongList() {
        List<Song> songList = new ArrayList<>();
        Song s = new Song(
                "Hells Bells",
                "Rock",
                "AC/DC",
                "Back in Black",
                "311s",
                "audios/hells_bells.mp3"
        );
        songList.add(s);
        return songList;
    }
}