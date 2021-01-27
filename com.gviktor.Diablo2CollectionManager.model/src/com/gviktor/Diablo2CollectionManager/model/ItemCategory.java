package com.gviktor.Diablo2CollectionManager.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemCategory {
    protected String categoryName;
    protected List<Item> categoryItems;
    protected String mainPath;
    protected int numberOfItems;
    public enum ItemCategoryLevelType  {DIFFICULTY,UNDEFINED}
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

    public ItemCategoryLevelType getItemCategoryLevelType() {
        return itemCategoryLevelType;
    }

    public void setItemCategoryLevelType(ItemCategoryLevelType itemCategoryLevelType) {
        this.itemCategoryLevelType = itemCategoryLevelType;
    }

    public List<Item> getNormalItems() {
        if(this.itemCategoryLevelType == ItemCategoryLevelType.UNDEFINED){
            throw new RuntimeException("Not categorized by difficulty");
        }
        List<Item> normalItems = new LinkedList<Item>();
        normalItems = this.categoryItems.stream().filter(item -> item.getCategory().equals(Item.ItemLevel.NORMAL)).collect(Collectors.toList());
        return normalItems;
    }

    public List<Item> getExceptionalItems() {
        if(this.itemCategoryLevelType == ItemCategoryLevelType.UNDEFINED){
            throw new RuntimeException("Not categorized by difficulty");
        }
        List<Item> ExceptionalItems = new LinkedList<Item>();
        ExceptionalItems = this.categoryItems.stream().filter(item -> item.getCategory().equals(Item.ItemLevel.EXCEPTIONAL)).collect(Collectors.toList());
        return ExceptionalItems;
    }

    public List<Item> getEliteItems() {
        if(this.itemCategoryLevelType == ItemCategoryLevelType.UNDEFINED){
            throw new RuntimeException("Not categorized by difficulty");
        }
        List<Item> eliteItems = new LinkedList<Item>();
        eliteItems = this.categoryItems.stream().filter(item -> item.getCategory().equals(Item.ItemLevel.ELITE)).collect(Collectors.toList());
        return eliteItems;
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
