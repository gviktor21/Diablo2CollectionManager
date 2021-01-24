package com.gviktor.Diablo2CollectionManager.view;

import com.gviktor.Diablo2CollectionManager.inputoutput.CollectionLoader;
import com.gviktor.Diablo2CollectionManager.model.UserCollection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class CollectionViewController {

    @FXML
    ListView<String> collectionList;
    @FXML
    ImageView bView_add;
    @FXML
    ImageView bView_open;
    @FXML
    ImageView bView_delete;
    public void addCollections(){
        ObservableList<String> collectionNames=CollectionLoader.getCollections();
        collectionList.setItems(collectionNames);
    }
    public void initialize() {
        bView_add.setImage(ImageLoader.loadImage(ImageLoader.ICON_MAIN_PATH+"new.png",30,30));
        bView_open.setImage(ImageLoader.loadImage(ImageLoader.ICON_MAIN_PATH+"open.png",30,30));
        bView_delete.setImage(ImageLoader.loadImage(ImageLoader.ICON_MAIN_PATH+"delete.png",30,30));

    }
    public UserCollection loadCollection() throws FileNotFoundException {
        String collectionName = collectionList.getSelectionModel().getSelectedItem();
        UserCollection collection=null;
        if(collectionName != null){
            collection= CollectionLoader.loadCollection(collectionName);
        }
        return collection;
    }
    public void handleDelete(){
        String selected = collectionList.getSelectionModel().getSelectedItem();
        if(selected != null){
            //show a comfirmation window
            //if yes execute the deletion
        }
    }
    public void handleAdd(){
        String collectionName;
        //get a collectionName from user

        //check if this name is still exist
        //add an empty collection and select it as a current collection
    }
    public void onListClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                String selected = collectionList.getSelectionModel().getSelectedItem();
                if(selected != null){

                }
            }
        }
    }

    public void handleOpen(ActionEvent actionEvent) {
        String selected = collectionList.getSelectionModel().getSelectedItem();
        if(selected != null){
            //open collection and close this window
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }else {
            //show user a dialog that he/she not selected a thing
        }
    }
}
