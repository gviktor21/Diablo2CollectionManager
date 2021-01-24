package com.gviktor.Diablo2CollectionManager.model;

import java.util.List;

public class ItemCategory {
    protected String categoryName;
    protected List<Item> categoryItems;
    protected String mainPath;
    protected int numberOfItems;
    public static enum ItemCategoryLevelType  {DIFFICULTY,UNDEFINED}
    protected ItemCategoryLevelType itemCategoryLevelType;
    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ItemCategory(String categoryName, String mainPath) {
        this.categoryName = categoryName;
        this.mainPath = mainPath;
        numberOfItems=0;
        itemCategoryLevelType=ItemCategoryLevelType.UNDEFINED;
    }

    public void setCategoryItems(List<Item> categoryItems) {
        this.categoryItems = categoryItems;
        if(categoryItems!=null){
            this.numberOfItems =categoryItems.size();
            Item item = categoryItems.get(0);

        }

    }

    public List<Item> getCategoryItems() {
        return categoryItems;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMainPath() {
        return mainPath;
    }

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }
}
