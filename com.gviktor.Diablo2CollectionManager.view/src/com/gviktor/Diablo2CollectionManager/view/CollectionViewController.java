package com.gviktor.Diablo2CollectionManager.view;

import com.gviktor.Diablo2CollectionManager.inputoutput.CollectionLoader;
import com.gviktor.Diablo2CollectionManager.model.UserCollection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete ");
            alert.setHeaderText("Collection to delete: " + selected);
            alert.setContentText("Are you sure to delete selected item?");
            Optional<ButtonType> result =alert.showAndWait();
            if(result.isPresent()&& result.get()==ButtonType.OK){
                try {
                    CollectionLoader.getInstance().deleteCollection(selected);
                    addCollections();// update list
                } catch (IOException ioException) {
                    //todo kiirni valamit a usernek
                }
            }
        }
    }
    public void handleAdd(){
        String collectionName;
        //todo get a collectionName from user
        Dialog<ButtonType> addDialog = new Dialog<>();
        addDialog.setTitle("Add Collection");
        addDialog.initOwner(collectionList.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddCollectionDialog.fxml"));
        try{
            addDialog.getDialogPane().setContent(loader.load());
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        addDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        addDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = addDialog.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            AddDialogController addItemController = loader.getController();
            String name = addItemController.getcollectionName();
            if(name != null && !collectionList.getItems().contains(name)){
                //todo add an empty collection
                UserCollection userCollection = new UserCollection(name);
                CollectionLoader.getInstance().writeCollection(userCollection);
                //Update Collections
                addCollections();
            }
        }
    }
    public void onListClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                String selected = collectionList.getSelectionModel().getSelectedItem();
                if(selected != null){
                    //open collection and close this window
                    openCollection();
                }
            }
        }
    }

    public void handleOpen(ActionEvent actionEvent) {
        String selected = collectionList.getSelectionModel().getSelectedItem();
        if(selected != null){
            //open collection and close this window
            openCollection();
        }else {
            // todo show user a dialog that he/she not selected a thing
        }
    }
    private  void openCollection(){
            try {
                UserCollection collection = loadCollection();
                CollectionManager.getCollectionManager().setCurrentCollection(collection);
                Stage stage = (Stage) collectionList.getScene().getWindow();
                stage.close();
            }catch(FileNotFoundException fnfe){
                //todo write error to user

            }

    }
}
