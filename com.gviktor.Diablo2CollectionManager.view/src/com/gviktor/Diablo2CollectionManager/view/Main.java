package com.gviktor.Diablo2CollectionManager.view;

import com.gviktor.Diablo2CollectionManager.inputoutput.ItemData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Diablo2CollectionManager");
        Image mainIcon = loadMainIcon();
        System.out.println(mainIcon.getUrl());
        primaryStage.getIcons().add(mainIcon);
        primaryStage.setScene(new Scene(root,   1000, 600));
        primaryStage.show();
    }

    public  Image loadMainIcon() {
        Image img = null;
        try {
            FileInputStream istream = new FileInputStream("icons/weapons.png");
            img = new Image(istream);
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if( null==img) {
                return img;
            }else{
                return img;
            }
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
        ItemData.getInstance().loadItems();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
