package com.gviktor.Diablo2CollectionManager.inputoutput;

import com.gviktor.Diablo2CollectionManager.model.CategoryOwnedData;
import com.gviktor.Diablo2CollectionManager.model.ItemCategory;
import com.gviktor.Diablo2CollectionManager.model.UserCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class CollectionLoader {
    private static CollectionLoader instance = new CollectionLoader();

    public static CollectionLoader getInstance(){
        return instance;
    }

    public static ObservableList<String>getCollections(){
        Path curdir= FileSystems.getDefault().getPath("./collections");
        ObservableList<String>  collectionFileNames=FXCollections.observableArrayList();
        try(DirectoryStream<Path> content = Files.newDirectoryStream(curdir)){
            for(Path file : content){
                if(file.toString().endsWith(".dat")){
                    String filename =file.getFileName().toString();
                    collectionFileNames.add(filename.substring(0,filename.lastIndexOf(".")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectionFileNames;
    }
    public void writeCollection(UserCollection userCollection) {
        Path path = FileSystems.getDefault().getPath(Locations.COLLECTION_PATH + userCollection.getCollectionName() + ".dat");
        File file = path.toFile();
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            //writeName
            br.write("name: ");
            br.write(userCollection.getCollectionName());
            br.newLine();
            //write uniques
            br.write("UNIQUES");
            br.write("\n");
            String itemData = userCollection.getOwnedUniqueItemString();
            br.write(itemData);
            //write sets
            br.write("SETS");
            br.write("\n");
            itemData = userCollection.getOwnedSetItemString();
            br.write(itemData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  static  UserCollection loadCollection(String path) throws FileNotFoundException {
        Path path1 = FileSystems.getDefault().getPath(Locations.COLLECTION_PATH + path + ".dat");
        File file = path1.toFile();
        UserCollection userCollection = null;
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            //LOAD COLLECTION NAME
            String name = bf.readLine();
            int lastIndexOfPoint = name.lastIndexOf(":");
            if (lastIndexOfPoint <= 0) {
                throw new RuntimeException();
            }
            name = name.substring(lastIndexOfPoint+1, name.length()).trim();
            userCollection = new UserCollection(name);
            String line;
            //LOAD COLLECTION UNIQUES, SETS
            HashMap<String, CategoryOwnedData> uniques = null;
            HashMap<String, CategoryOwnedData> sets = null;
            boolean loadUniques = false, loadSets = false;
            while ((line = bf.readLine()) != null) {
                if (loadUniques && !loadSets) {
                    CategoryOwnedData cod = loadItems(line, true);
                    if (cod != null) {
                        uniques.put(cod.getCategory().getCategoryName(), cod);
                    }
                } else if (!loadUniques && loadSets) {
                    CategoryOwnedData cod = loadItems(line, false);
                    if (cod != null) {
                        sets.put(cod.getCategory().getCategoryName(), cod);
                    }
                }
                if (line.trim().equalsIgnoreCase("uniques")) {
                    uniques = new HashMap<String, CategoryOwnedData>();
                    loadUniques = true;
                    loadSets = false;
                } else if (line.trim().equalsIgnoreCase("sets")) {
                    sets = new HashMap<String, CategoryOwnedData>();
                    loadUniques = false;
                    loadSets = true;
                }

            }
            if (uniques != null) {
                userCollection.setOwnedUniqueCategoryData(uniques);
            } else if (sets != null) {
                userCollection.setOwnedSetCategoryData(sets);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userCollection;
    }

    private static CategoryOwnedData loadItems(String itemsData, boolean isUniques) {
        //load itemType
        int indexOfPoint = itemsData.lastIndexOf(":");
        if (indexOfPoint <= 0) {
            return null;
        }
        String itemType = itemsData.substring(0, indexOfPoint).trim();
        ItemCategory category;
        if (isUniques) {
            category = ItemData.getInstance().getUniqueItemsByCategory().get(itemType);

        } else {
            category = ItemData.getInstance().getSetItemsBySets().get(itemType);
        }
        CategoryOwnedData cdata = new CategoryOwnedData(category);
        //load owned item indexes of that category
        String[] itemNumbers = itemsData.substring(indexOfPoint + 1).split(",");
        for (String index : itemNumbers) {
            int i = Integer.parseInt(index.trim());
            cdata.addItem(i);
        }
        return cdata;
    }
}
