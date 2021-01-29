package com.gviktor.Diablo2CollectionManager.view;


import com.gviktor.Diablo2CollectionManager.model.Item;
import com.gviktor.Diablo2CollectionManager.model.ItemCategory;
import com.gviktor.Diablo2CollectionManager.model.UserCollection;

public class CollectionManager {
    private UserCollection currentCollection;
    private ItemCategory currentCategory;
    boolean isSetCategory;

    public CollectionManager(UserCollection currentCollection) {
        this.currentCollection = currentCollection;
        isSetCategory=false;
    }

    public ItemCategory getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(ItemCategory currentCategory) {
        this.currentCategory = currentCategory;
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

    public void deSelect(Item currentItem) {
        int index = currentCategory.getCategoryItems().indexOf(currentItem);
        if(isSetCategory){
            currentCollection.removeSetItem(currentCategory,index);
        }else{
            currentCollection.removeUniqueItem(currentCategory,index);
        }

    }
}
