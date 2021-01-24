package com.gviktor.Diablo2CollectionManager.inputoutput;

import com.gviktor.Diablo2CollectionManager.model.Item;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ItemXmlHandler extends DefaultHandler {
    private boolean isNormal =false;
    private boolean isExceptional = false;
    private boolean isElite = false;
    private boolean isUndefined=false;
    private String path;

    public ItemXmlHandler(String path) {
        this.path=path;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    private List<Item> itemList;
    private Item item;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("item")) {
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            String imgurl = attributes.getValue("imgsrc");
            item = new Item(name, path+imgurl, type);
            if (itemList == null) {
                itemList = new ArrayList<Item>();
            }

        }else if(qName.equalsIgnoreCase("normal")){
            isNormal=true;
        }else if(qName.equalsIgnoreCase("exceptional")){
            isExceptional=true;
        }else if(qName.equalsIgnoreCase("elite")){
            isElite=true;
        }else if(qName.equalsIgnoreCase("undefined")){
            isUndefined=true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("item")){
            determineDifficulty();
            itemList.add(item);
        }else if(qName.equalsIgnoreCase("normal")){
            isNormal=false;
        }else if(qName.equalsIgnoreCase("exceptional")){
            isExceptional=false;
        }else if(qName.equalsIgnoreCase("elite")){
            isElite=false;
        }else if(qName.equalsIgnoreCase("undefined")) {
            isUndefined=false;
        }
    }
    private void determineDifficulty() {
        if (isNormal) {
            item.setDiff_level(Item.ItemLevel.NORMAL);
        } else if (isExceptional) {
            item.setDiff_level(Item.ItemLevel.EXCEPTIONAL);
        } else if (isElite) {
            item.setDiff_level(Item.ItemLevel.ELITE);
        } else if (isUndefined) {
            item.setDiff_level(Item.ItemLevel.UNDEFINED);
        }
    }
}
