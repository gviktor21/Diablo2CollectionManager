package com.gviktor.Diablo2CollectionManager.view;


import com.gviktor.Diablo2CollectionManager.model.Item;
import com.gviktor.Diablo2CollectionManager.model.ItemCategory;
import com.gviktor.Diablo2CollectionManager.model.UserCollection;
import javafx.scene.control.Label;

public class CollectionManager {
    private UserCollection currentCollection;
    private ItemCategory currentCategory;
    Label uniqueLabel;
    boolean isSetCategory;
    private static  CollectionManager instance = null;

    public static CollectionManager getCollectionManager(){
        if(instance == null){
            instance = new CollectionManager();
        }
        return instance;
    }
    private CollectionManager() {

    }
    public void setDefault(UserCollection currentCollection, Label label_Collection){
        this.currentCollection = currentCollection;
        isSetCategory=false;
        uniqueLabel = label_Collection;
        uniqueLabel.setText("Collection: "+currentCollection.getCollectionName());
    }
    public ItemCategory getCurrentCategory() {
        return currentCategory;
    }

    public UserCollection getCurrentCollection() {
        return currentCollection;
    }

    public void setCurrentCollection(UserCollection currentCollection) {
        this.currentCollection = currentCollection;
        uniqueLabel.setText("Collection: "+currentCollection.getCollectionName());
    }

    public void setSetCategory(boolean setCategory) {
        isSetCategory = setCategory;
    }

    public void setCurrentCategory(ItemCategory category) {
        this.currentCategory = category;
    }

    public void select(Item currentItem) {
        if(currentItem != null){
            int index = currentCategory.getCategoryItems().indexOf(currentItem);
            if(isSetCategory){
                currentCollection.addSetItem(currentCategory,index);
            }else{
                currentCollection.addUniqueItem(currentCategory,index);
            }
        }
    }
    public boolean collectionContainItem(Item item){
        if(currentCategory == null || currentCollection == null){
            return false;
        }else{
            return currentCollection.hasItem(currentCategory,currentCategory.getCategoryItems().indexOf(item));
        }
    }
    public void deSelect(Item currentItem) {
        int index = currentCategory.getCategoryItems().indexOf(currentItem);
        if(isSetCategory){
            currentCollection.removeSetItem(currentCategory,index);
        }else{
            currentCollection.removeUniqueItem(currentCategory,index);
        }

    }
}
