package com.gviktor.Diablo2CollectionManager.model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CategoryOwnedData {
    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    ItemCategory category;
    Set<Integer> ownedItemLocationNumbers;

    public CategoryOwnedData(ItemCategory category) {
        this.category = category;
        ownedItemLocationNumbers= new TreeSet<Integer>();
    }

    public void addItem(int itemNumber){
        if(itemNumber > category.getNumberOfItems()||itemNumber < 0){
            //no such an item
        }else{
            ownedItemLocationNumbers.add(itemNumber);
        }
    }
    public void removeItem(int itemNumber){
        ownedItemLocationNumbers.remove(itemNumber);
    }
    public boolean hasItem(int itemIndex){
        return ownedItemLocationNumbers.contains(itemIndex);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Category: " + category.getCategoryName()).append("\n");
        if (ownedItemLocationNumbers.size() > 0) {
            List<Item> itemList = category.getCategoryItems();
            for (int i : ownedItemLocationNumbers) {
                Item item = itemList.get(i);
                stringBuilder.append(item.toString()).append("\n");
            }
            return stringBuilder.toString();
        }
        return stringBuilder.append(" has no item owned").toString();
    }
    public  StringBuilder getOwnedItemsStringData(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(category.getCategoryName()).append(": ");
        if(ownedItemLocationNumbers.size() > 0) {
            for(int i : ownedItemLocationNumbers){
                stringBuilder.append(Integer.toString(i));
                stringBuilder.append(", ");
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }
        return stringBuilder;
    }
    public  void printOwnedItems(){
        //System.out.println("Category: "+category.getCategoryName());
        if(ownedItemLocationNumbers.size() > 0){
            List<Item> itemList = category.getCategoryItems();
            for(int i : ownedItemLocationNumbers){
                Item item = itemList.get(i);
                System.out.println(item);
            }
        }

    }
}
