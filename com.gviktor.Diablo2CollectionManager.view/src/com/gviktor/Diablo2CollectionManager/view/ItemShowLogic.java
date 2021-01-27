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
    HandleLabelClick handleLabelClick;
    private int remaining=0;
    ItemShowLogic(Label label1, Label label2, Label label3, Button b_previous, Button b_next){
        handleLabelClick = new HandleLabelClick();
        this.label1=label1;
        this.label2=label2;
        this.label3=label3;
        label1.setOnMouseClicked(handleLabelClick);
        label2.setOnMouseClicked(handleLabelClick);
        label3.setOnMouseClicked(handleLabelClick);

        b_previous.setOnAction(new HandlePrevious());
        b_next.setOnAction(new HandleNext());
    }
    public void showItemCards(ItemCategory category){
        setHeaders(category);
        if(category.getItemCategoryLevelType() == ItemCategory.ItemCategoryLevelType.DIFFICULTY) {
        }




        List<Item> items= category.getCategoryItems();
        Iterator iterator = items.iterator();
        int i=0;
        for ( i = 0; i< Controller.getItemcards().size();i++){
            if(iterator.hasNext()){
                Item item = (Item) iterator.next();
                Controller.getItemcards().get(i).setItem(item);
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
            label1.setText("Norlmal");
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
               System.out.println("Next button clicked");
        }
    }

    private class HandlePrevious implements EventHandler<javafx.event.ActionEvent> {
        public HandlePrevious(){
            super();

        }

        @Override
        public void handle(javafx.event.ActionEvent actionEvent) {
             //todo
            System.out.println("Previous button clicked");
        }
    }
    private class HandleLabelClick implements EventHandler{

        @Override
        public void handle(Event actionEvent) {
             //todo
            if (actionEvent.getSource().equals(label1)){
                System.out.println("label1 clicked");
            }else if(actionEvent.getSource().equals(label2)){
                System.out.println("label2 clicked");
            }else if(actionEvent.getSource().equals(label3)){
                 System.out.println("label3 clicked");
            }

        }
    }

}
