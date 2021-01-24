package com.gviktor.Diablo2CollectionManager.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemCategoryByDifficulty extends ItemCategory {
    ItemCategoryLevelType itemCategoryLevelType;

    public ItemCategoryByDifficulty(String categoryName, String mainPath) {
        super(categoryName, mainPath);
        itemCategoryLevelType = ItemCategoryLevelType.DIFFICULTY;
    }

    public List<Item> getNormalItems() {
        List<Item> normalItems = new LinkedList<Item>();
        normalItems = this.categoryItems.stream().filter(item -> item.getCategory().equals(Item.ItemLevel.NORMAL)).collect(Collectors.toList());
        return normalItems;
    }

    public List<Item> getExceptionalItems() {
        List<Item> ExceptionalItems = new LinkedList<Item>();
        ExceptionalItems = this.categoryItems.stream().filter(item -> item.getCategory().equals(Item.ItemLevel.EXCEPTIONAL)).collect(Collectors.toList());
        return ExceptionalItems;
    }

    public List<Item> getEliteItems() {
        List<Item> eliteItems = new LinkedList<Item>();
        eliteItems = this.categoryItems.stream().filter(item -> item.getCategory().equals(Item.ItemLevel.ELITE)).collect(Collectors.toList());
        return eliteItems;
    }
}
