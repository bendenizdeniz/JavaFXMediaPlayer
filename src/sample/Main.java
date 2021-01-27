package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        BorderPane rootNode = loader.load();

        Scene scene  = new Scene(rootNode,600,400);
        stage.setTitle("Medya Oynatıcı - DenizCanALTUN");

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent doubleClicked) {
                if(doubleClicked.getClickCount() == 2)
                    stage.setFullScreen(true);
            }
        });
        stage.setScene(scene);
        stage.show();


        stage.setOpacity(0.9);
    }

    public void init() throws Exception{
        super.init();
    }
    public void stop() throws Exception{
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
