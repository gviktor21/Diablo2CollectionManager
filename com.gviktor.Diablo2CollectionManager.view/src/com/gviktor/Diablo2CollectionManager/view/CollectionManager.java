package com.gviktor.Diablo2CollectionManager.view;


import com.gviktor.Diablo2CollectionManager.model.Item;
import com.gviktor.Diablo2CollectionManager.model.ItemCategory;
import com.gviktor.Diablo2CollectionManager.model.UserCollection;

public class CollectionManager {
    private UserCollection currentCollection;
    private ItemCategory currentcategory;
    boolean isSetCategory;

    public CollectionManager(UserCollection currentCollection) {
        this.currentCollection = currentCollection;
        isSetCategory=false;
    }

    public ItemCategory getCurrentcategory() {
        return currentcategory;
    }

    public void setCurrentcategory(ItemCategory currentcategory) {
        this.currentcategory = currentcategory;
    }

    public UserCollection getCurrentCollection() {
        return currentCollection;
    }

    public void setCurrentCollection(UserCollection currentCollection) {
        this.currentCollection = currentCollection;
    }

    public void setSetCategory(boolean setCategory) {
        isSetCategory = setCategory;
    }

    public void setCurrentCategory(ItemCategory category) {
    }

    public void select(Item currentItem) {
        if(currentItem != null){
            int index = currentcategory.getCategoryItems().indexOf(currentItem);
            if(isSetCategory){
                currentCollection.addSetItem(currentcategory,index);
            }else{
                currentCollection.addUniqueItem(currentcategory,index);
            }
        }
    }

    public void deSelect(Item currentItem) {
        int index = currentcategory.getCategoryItems().indexOf(currentItem);
        if(isSetCategory){
            currentCollection.removeSetItem(currentcategory,index);
        }else{
            currentCollection.removeUniqueItem(currentcategory,index);
        }

    }
}
