package com.gviktor.Diablo2CollectionManager.inputoutput;

import com.gviktor.Diablo2CollectionManager.model.Item;
import com.gviktor.Diablo2CollectionManager.model.ItemCategory;

import javafx.collections.FXCollections;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import javafx.collections.ObservableList;

public class ItemData {
    private static ItemData instance = new ItemData();

    public static ItemData getInstance(){
        return instance;
    }
    private HashMap<String, ItemCategory> uniqueItemsByCategory;
    private ObservableList<String> itemTypes;
    private  ObservableList<String> setNames;
    private  HashMap<String,ItemCategory> setItemsBySets;

    public  void loadItems(){
        uniqueItemsByCategory=new HashMap<String,ItemCategory>();
        setItemsBySets = new HashMap<String,ItemCategory>();
        itemTypes= FXCollections.observableArrayList();
        setNames = FXCollections.observableArrayList();
        //load uniques
        String uniqueItemPath="data/";
        loadExistingItems(uniqueItemsByCategory,Locations.UNIQUE_PATH,uniqueItemPath,itemTypes);
        loadXmls(uniqueItemsByCategory);
        //load sets
        String setItemPath = "data/sets/";
        loadExistingItems(setItemsBySets,Locations.SET_PATH,setItemPath,setNames);
        loadXmls(setItemsBySets);
        Collections.sort(itemTypes);
        Collections.sort(setNames);

    }
    private  void loadExistingItems(HashMap<String,ItemCategory> items,String mainPath,String itemPath,List<String> typeList){
        Path path = FileSystems.getDefault().getPath(mainPath);
        File file = path.toFile();
        try(BufferedReader reader=new BufferedReader(new FileReader( path.toFile()))){
            String line;
            while ( (line=reader.readLine()) != null) {
                String[] itemtypes=line.split(",");
                for (String i : itemtypes){
                    i=i.trim();
                    typeList.add(i);
                    String itempath =itemPath+i.toLowerCase()+"/";
                    ItemCategory itemCategory =new ItemCategory(i,itempath);
                    items.put(itemCategory.getCategoryName(),itemCategory);
                }
            }

        }catch (FileNotFoundException fnf){
            fnf.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void loadXmls(HashMap<String,ItemCategory> itemCollection){
        Iterator it =itemCollection.values().iterator();
        while (it.hasNext()){
            ItemCategory itemCategory = (ItemCategory) it.next();
            String path =itemCategory.getMainPath()+"/"+itemCategory.getCategoryName()+".xml";
            Path xmlPath = FileSystems.getDefault().getPath(path);
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            try{
                SAXParser saxParser = saxParserFactory.newSAXParser();
                ItemXmlHandler handler = new ItemXmlHandler(path.substring(0,path.lastIndexOf("/")));
                saxParser.parse(xmlPath.toFile(),handler);
                List<com.gviktor.Diablo2CollectionManager.model.Item> items=handler.getItemList();

                //check it if it has difficulty property then downcast it
                Item item  =items.get(0);
                if(!item.getDiff_level().equals(Item.ItemLevel.UNDEFINED)){
                    itemCategory.setItemCategoryLevelType(ItemCategory.ItemCategoryLevelType.DIFFICULTY);
                }
                itemCategory.setItemCategoryLevelType(ItemCategory.ItemCategoryLevelType.UNDEFINED);
                itemCategory.setCategoryItems(items);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  HashMap<String, ItemCategory> getUniqueItemsByCategory() {
        return uniqueItemsByCategory;
    }

    public ObservableList<String> getItemTypes() {
        return itemTypes;
    }

    public ObservableList<String> getSetNames() {
        return setNames;
    }

    public  HashMap<String, ItemCategory> getSetItemsBySets() {
        return setItemsBySets;
    }

}
