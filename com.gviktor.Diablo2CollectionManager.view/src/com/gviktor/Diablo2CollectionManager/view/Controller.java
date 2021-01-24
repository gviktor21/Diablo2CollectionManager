package com.gviktor.Diablo2CollectionManager.view;

import com.gviktor.Diablo2CollectionManager.inputoutput.CollectionLoader;
import com.gviktor.Diablo2CollectionManager.inputoutput.ItemData;
import com.gviktor.Diablo2CollectionManager.model.Item;
import com.gviktor.Diablo2CollectionManager.model.ItemCategory;
import com.gviktor.Diablo2CollectionManager.model.UserCollection;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static javafx.stage.Modality.*;

public class Controller {
    //Todo Eltüntetni a imageLoadot
    //todo: Felső rész kitalálása
    //Todo: Működési logika kitalálása.
    //Todo: Kollecciókezelés
    public static final String URL_PREV_IMAGE="icons/botmenu-prev-ovr.gif";
    public static final String URL_NEXT_IMAGE="icons/botmenu-next-ovr.gif";
    static HashMap<String, ItemCategory> uniqueItemsByCategory;
    static ObservableList<String> itemTypes;
    static ObservableList<String> setNames;
    static HashMap<String, ItemCategory> setItemsBySets;
    @FXML
    Button b_previous;
    @FXML
    Button b_next;
    @FXML
    ImageView imageViewPrevButton;
    @FXML
    ImageView imageViewNextButton;
    @FXML
    GridPane vboxContainer;
    @FXML
    ChoiceBox<String>  choiceBox_Uniques;
    @FXML
    ChoiceBox<String>  choiceBox_Sets;

    UserCollection currentCollection;
    LinkedList<VBox> itemBoxes;
    LinkedList<ItemCard> itemCards;
    HashMap<VBox,ItemCard> itemCardsOfVBoxes;
    public void initialize(){
        Image imgPrev = loadImage(URL_PREV_IMAGE);
        imageViewPrevButton.setImage(imgPrev);
        imageViewPrevButton.setCache(true);
        Image imgNext =loadImage(URL_NEXT_IMAGE);
        imageViewNextButton.setImage(imgNext);
        imageViewNextButton.setCache(true);
        ObservableList<String> collections=CollectionLoader.getCollections();
        fillItems();
        populateitemBoxesList();
        populateChoiceBoxes();
    }
    private void populateChoiceBoxes(){
        choiceBox_Uniques.setItems( itemTypes);
        choiceBox_Sets.setItems( setNames);
    }
    private void populateitemBoxesList(){
        itemBoxes = new LinkedList<VBox>();
        itemCards = new LinkedList<ItemCard>();
        itemCardsOfVBoxes= new HashMap<VBox,ItemCard>();
        ObservableList<Node> gridchildren= vboxContainer.getChildren();
        int number=0;
        for(Node n : gridchildren){
            if (n  instanceof VBox ) {
                VBox vbox = (VBox) n;
                itemBoxes.add(vbox);
                WebView webView = null;
                ImageView imageView = null;
                Label label =null;
                ObservableList<Node> childrens= vbox.getChildren();
                for(Node vNode : childrens){
                    if (vNode  instanceof WebView ){
                        webView = (WebView) vNode;
                    }else if( vNode instanceof ImageView){
                        imageView = (ImageView) vNode;
                    }else if(vNode  instanceof Label){
                        label=(Label) vNode;
                    }
                }
                ItemCard itemCard = new ItemCard(vbox,webView,imageView,label,number);
                itemCardsOfVBoxes.put(vbox,itemCard);
                itemCards.add(itemCard);
                System.out.println(itemCards.size());

                number++;
            }
        }

    }
    private Image loadImage(String url) {
        Image img = null;
        try {
            FileInputStream istream = new FileInputStream(url);
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
    private void fillItems(){
        uniqueItemsByCategory = ItemData.getInstance().getUniqueItemsByCategory();
        setItemsBySets = ItemData.getInstance().getSetItemsBySets();
        itemTypes =ItemData.getInstance().getItemTypes();
        setNames = ItemData.getInstance().getSetNames();
    }
    private void showItemCards(ItemCategory category){
        List<Item>  items= category.getCategoryItems();
        Iterator iterator = items.iterator();
        int i=0;
        for ( i = 0; i< itemCards.size();i++){
            if(iterator.hasNext()){
                Item item = (Item) iterator.next();
                itemCards.get(i).setItem(item);
            }else{
                break;
            }
        }
        //clear reamining cards
        while (i < itemCards.size()){
            itemCards.get(i).clear();
            i++;
        }


    }
    private void clearItemCards(){
        for(ItemCard itemCard : itemCards){
            itemCard.clear();
        }

    }
    public void handleExit(){
        Platform.exit();
    }

    public void vbox1clicked(MouseEvent mouseEvent) {
        Node children = (Node) mouseEvent.getSource();
        if (children instanceof VBox){
            VBox vbox = (VBox)children;
            ItemCard itemCard= itemCardsOfVBoxes.get(vbox);
            if(!itemCard.isSelected()){
                itemCard.select();
            }else{
                itemCard.deSelect();
            }
        }
    }

    public void gridPaneCLicked(MouseEvent mouseEvent) {

    }

    public void pressedNext(ActionEvent actionEvent) {
    }

    public void pressedPrevious(ActionEvent actionEvent) {
    }

    public void action_UniqueChoice(ActionEvent actionEvent) {
        String selectedItem =choiceBox_Uniques.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            clearItemCards();
            ItemCategory category = uniqueItemsByCategory.get(selectedItem);
            showItemCards(category);
        }

    }

    public void action_SetChoice(ActionEvent actionEvent) {
        String selectedItem =choiceBox_Sets.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            System.out.println("selecting");
            clearItemCards();
            ItemCategory category = setItemsBySets.get(selectedItem);
            showItemCards(category);
        }

    }

    public void handleCollection(ActionEvent actionEvent) {
        Parent root;
        Window ownerWindow = ((MenuItem)actionEvent.getTarget()).getParentPopup().getOwnerWindow();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Collections.fxml"));
            root= fxmlLoader.load();
            CollectionViewController controller = fxmlLoader.getController();
            controller.addCollections();
            Stage stage = new Stage();
            stage.setTitle("Collections");
            stage.setScene(new Scene(root,500,500));
            stage.initOwner(ownerWindow);
            stage.initModality(APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
