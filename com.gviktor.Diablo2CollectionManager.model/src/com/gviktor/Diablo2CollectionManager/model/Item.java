package com.gviktor.Diablo2CollectionManager.model;

public class Item {
   private String name;
   private String imageUrl;
   private String htmlUrl;
   private String type;
   private String stats;
   private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public static enum ItemLevel{NORMAL,EXCEPTIONAL,ELITE,UNDEFINED};
    private ItemLevel diff_level;

    public Item(String name, String imageUrl, String type) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.htmlUrl = imageUrl.substring(0,imageUrl.indexOf("."))+".html";
        if(imageUrl.indexOf(".")>0){
            this.htmlUrl = imageUrl.substring(0,imageUrl.indexOf("."))+".html";
        }else{
            System.out.println(imageUrl);
        }
        this.type = type;
    }
    private String difficultyS(){
        if(diff_level==null){
            return "";
        }else {
            switch (diff_level){
            case NORMAL: return "Normal";
            case EXCEPTIONAL: return "Exceptional";
            case ELITE: return "Elite";
            case UNDEFINED: return "Undefined";
            }
        }
        return " f";
    }
    public Item() {

    }

    public void setDiff_level(ItemLevel diff_level) {
        this.diff_level = diff_level;
    }

    public ItemLevel getDiff_level() {
        return diff_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name+"("+this.type+") "+"("+difficultyS()+")";
    }
}
