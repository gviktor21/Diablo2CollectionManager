package com.gviktor.Diablo2CollectionManager.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class UserCollection {
    private HashMap<String,CategoryOwnedData> ownedUniqueCategoryData;
    private HashMap<String,CategoryOwnedData> ownedSetCategoryData;
    private String collectionName;


    public HashMap<String, CategoryOwnedData> getOwnedUniqueCategoryData() {
        return ownedUniqueCategoryData;
    }

    public void setOwnedUniqueCategoryData(HashMap<String, CategoryOwnedData> ownedUniqueCategoryData) {
        this.ownedUniqueCategoryData = ownedUniqueCategoryData;
    }

    public HashMap<String, CategoryOwnedData> getOwnedSetCategoryData() {
        return ownedSetCategoryData;
    }

    public void setOwnedSetCategoryData(HashMap<String, CategoryOwnedData> ownedSetCategoryData) {
        this.ownedSetCategoryData = ownedSetCategoryData;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public UserCollection(String collectionName) {
        this.collectionName = collectionName;
        ownedUniqueCategoryData = new HashMap<String, CategoryOwnedData>();
        ownedSetCategoryData = new HashMap<String, CategoryOwnedData>();
    }
    public void addOwnedUniqueDataCategory(String categoryName, CategoryOwnedData cdata){
        ownedUniqueCategoryData.put(categoryName,cdata);
    }
    public void addOwnedSetDataCategory(String categoryName, CategoryOwnedData cdata){
        ownedSetCategoryData.put(categoryName,cdata);
    }
    private  boolean isUniqueCategoryExists(String categoryName){
        if(ownedUniqueCategoryData.containsKey(categoryName)){
            return true;
        }else {
            return false;
        }

    }
    private boolean isSetCategoryExists(String categoryName){
        if(ownedSetCategoryData.containsKey(categoryName)){
            return true;
        }else{
            return false;
        }
    }
    public void printOwnedUniqueItems(){
        Iterator it = ownedUniqueCategoryData.values().iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    public String getOwnedUniqueItemString(){
        Iterator iterator=ownedUniqueCategoryData.values().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()){
            CategoryOwnedData  categoryOwnedData= (CategoryOwnedData) iterator.next();
            sb.append(categoryOwnedData.getOwnedItemsStringData());
            sb.append("\n");
        }
        return  sb.toString();
    }
    public String getOwnedSetItemString(){
        Iterator iterator=ownedSetCategoryData.values().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()){
            CategoryOwnedData  categoryOwnedData= (CategoryOwnedData) iterator.next();
            sb.append(categoryOwnedData.getOwnedItemsStringData());
            sb.append("\n");
        }
        return  sb.toString();
    }
    public   void addUniqueItem(ItemCategory category, int itemNumber){
        CategoryOwnedData ownedItemCategory;
        if(!isUniqueCategoryExists(category.getCategoryName())){
             ownedItemCategory = new CategoryOwnedData(category);
             ownedUniqueCategoryData.put(category.getCategoryName(),ownedItemCategory);
        } else{
             ownedItemCategory =ownedUniqueCategoryData.get(category.getCategoryName());
        }
        ownedItemCategory.addItem(itemNumber);
    }
    public void removeUniqueItem(ItemCategory category, int itemNumber){
        removeUniqueItem(category.getCategoryName(),itemNumber);
    }

    public void removeUniqueItem(String categoryName, int itemNumber){
        CategoryOwnedData itemCategory =ownedUniqueCategoryData.get(categoryName);
        itemCategory.removeItem(itemNumber);
    }
    public  void addSetItem(ItemCategory category, int itemNumber){
        CategoryOwnedData ownedItemCategory;
        if(!isSetCategoryExists(category.getCategoryName())){
            ownedItemCategory = new CategoryOwnedData(category);
            ownedSetCategoryData.put(category.getCategoryName(),ownedItemCategory);
        } else{
            ownedItemCategory =ownedSetCategoryData.get(category.getCategoryName());
        }
        ownedItemCategory.addItem(itemNumber);
    }
    public void removeSetItem(ItemCategory category, int itemNumber){
        removeSetItem(category.getCategoryName(),itemNumber);
    }
    public void removeSetItem(String categoryName, int itemNumber){
        CategoryOwnedData itemCategory =ownedSetCategoryData.get(categoryName);
        itemCategory.removeItem(itemNumber);
    }
}
