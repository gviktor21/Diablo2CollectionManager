package com.gviktor.Diablo2CollectionManager.model;

import java.util.HashMap;
import java.util.Iterator;

public class UserCollection {
    private HashMap<String,CategoryOwnedData> ownedUniqueCategoryData;
    private HashMap<String,CategoryOwnedData> ownedSetCategoryData;

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

    private String collectionName;

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
    private  void addUniqueItem(String categoryName, int itemNumber){
        CategoryOwnedData itemCategory =ownedUniqueCategoryData.get(categoryName);
        itemCategory.addItem(itemNumber);
    }
    private void removeUniqueItem(String categoryName, int itemNumber){
        CategoryOwnedData itemCategory =ownedUniqueCategoryData.get(categoryName);
        itemCategory.removeItem(itemNumber);
    }
    private  void addSetItem(String categoryName, int itemNumber){
        CategoryOwnedData itemCategory =ownedSetCategoryData.get(categoryName);
        itemCategory.addItem(itemNumber);
    }
    private void removeSetItem(String categoryName, int itemNumber){
        CategoryOwnedData itemCategory =ownedSetCategoryData.get(categoryName);
        itemCategory.removeItem(itemNumber);
    }
}
