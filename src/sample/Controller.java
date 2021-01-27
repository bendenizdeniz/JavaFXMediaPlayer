package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;


    private String filePath;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private Slider slider;

    @FXML
    private Slider seekSlider;

    public Label lblCounter;

    public double counterVideo;

    @FXML
    public void handleButtonAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Bir Dosya Seçiniz (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();

        if(filePath != null) {
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();

            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

            slider.setValue(mediaPlayer.getVolume() * 100);
            slider.valueProperty().addListener(new InvalidationListener() {

                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                }
            });

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                    seekSlider.setValue(t1.toSeconds());
                }
            });

         /*   seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            }); */

            seekSlider.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });

            seekSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });


            mediaPlayer.setOnReady(new Runnable() {

                @Override
                public void run() {
                    Duration total = media.getDuration();
                    seekSlider.setMax(total.toSeconds());
                    counterVideo = total.toMinutes();
                    System.out.println(counterVideo);
                    lblCounter.setText(String.valueOf(counterVideo));
                }
            });
            mediaPlayer.play();
        }
    }


    @FXML
    private void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }

    @FXML
    private void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }


    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }

    @FXML
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }


    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }


    @FXML
    private void slowVideo(ActionEvent event){
        mediaPlayer.setRate(0.75);
    }


    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }


    @FXML
    private void exitVideo(ActionEvent event){
        System.exit(0);
    }

}
