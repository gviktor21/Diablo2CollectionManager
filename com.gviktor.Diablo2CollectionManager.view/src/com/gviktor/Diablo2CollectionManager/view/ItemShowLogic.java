package com.gviktor.Diablo2CollectionManager.view;

import com.gviktor.Diablo2CollectionManager.model.Item;
import com.gviktor.Diablo2CollectionManager.model.ItemCategory;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Iterator;
import java.util.List;

public class ItemShowLogic {
    Label label1,label2,label3;
    private List<Item> currentItemList;
    private int currentStartingIndex=0;
    private static final int NUMBER_OF_ITEM_SHOW = 8;
    private ItemCategory currentCategory;
    HandleLabelClick handleLabelClick;

    ItemShowLogic(Label label1, Label label2, Label label3, Button b_previous, Button b_next){
        handleLabelClick = new HandleLabelClick();
        this.label1=label1;
        this.label2=label2;
        this.label3=label3;
        label1.setOnMouseClicked(handleLabelClick);
        label2.setOnMouseClicked(handleLabelClick);
        label3.setOnMouseClicked(handleLabelClick);
        currentCategory=null;

        b_previous.setOnAction(new HandlePrevious());
        b_next.setOnAction(new HandleNext());
    }
    public void showItemCards(ItemCategory category){
        currentCategory=category;
        setHeaders(category);
        if(category.getItemCategoryLevelType() == ItemCategory.ItemCategoryLevelType.DIFFICULTY) {
            currentItemList =currentCategory.getNormalItems();
        }else{
            currentItemList=currentCategory.getCategoryItems();
        }
        currentStartingIndex=0;
        show();
    }
    public void show(){
        int itemIndex =currentStartingIndex;
        int i;
        for ( i = 0; i< Controller.getItemcards().size();i++){
            if(currentItemList.size() >itemIndex){
                Item item = currentItemList.get(itemIndex);
                Controller.getItemcards().get(i).setItem(item);
                if(CollectionManager.getCollectionManager().collectionContainItem(item)){
                    Controller.getItemcards().get(i).select();
                }
                itemIndex++;
            }else{
                break;
            }
        }
        //clear reamining cards
        while (i < Controller.getItemcards().size()){
            Controller.getItemcards().get(i).clear();
            i++;
        }
    }
    private void setHeaders(ItemCategory category){
        if(category.getItemCategoryLevelType() == ItemCategory.ItemCategoryLevelType.DIFFICULTY){
            label1.setText("Normal");
            label2.setText("Exceptional");
            label3.setText("Elite");
        }else{
            label1.setText("");
            label2.setText("");
            label3.setText("");
        }

    }
    public void clearItemCards(){
        for(ItemCard itemCard : Controller.getItemcards()){
            itemCard.clear();
        }

    }
    private class HandleNext implements EventHandler<javafx.event.ActionEvent> {
        public HandleNext(){
            super();

        }

        @Override
        public void handle(javafx.event.ActionEvent actionEvent) {
            //todo
            if(currentItemList.size() > currentStartingIndex+NUMBER_OF_ITEM_SHOW){
                currentStartingIndex+=NUMBER_OF_ITEM_SHOW;
                clearItemCards();
                show();
            }
        }
    }

    private class HandlePrevious implements EventHandler<javafx.event.ActionEvent> {
        public HandlePrevious(){
            super();

        }

        @Override
        public void handle(javafx.event.ActionEvent actionEvent) {
             //todo
            if(currentStartingIndex-NUMBER_OF_ITEM_SHOW>=0){
                currentStartingIndex-=NUMBER_OF_ITEM_SHOW;
                clearItemCards();
                show();
            }
        }
    }
    private class HandleLabelClick implements EventHandler{

        @Override
        public void handle(Event actionEvent) {
             //todo
            if(currentCategory!= null) {
                if (actionEvent.getSource().equals(label1)){
                    currentItemList =currentCategory.getNormalItems();
                }else if(actionEvent.getSource().equals(label2)){
                    currentItemList =currentCategory.getExceptionalItems();
            }else if(actionEvent.getSource().equals(label3)){
                currentItemList =currentCategory.getEliteItems();
            }
            currentStartingIndex=0;
                clearItemCards();
                show();
            }


        }
    }

}
