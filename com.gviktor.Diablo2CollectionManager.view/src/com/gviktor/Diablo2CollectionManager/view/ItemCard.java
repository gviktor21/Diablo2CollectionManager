package com.gviktor.Diablo2CollectionManager.view;

import com.gviktor.Diablo2CollectionManager.model.Item;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ItemCard {
    private WebView webView;
    private ImageView imageView;
    private VBox vBox;
    private Label labelItemName;
    private int number;
    private boolean selected;
    private Item currentItem;
    public  ItemCard(VBox vbox, WebView webView, ImageView view,Label label, int number){
        this.vBox=vbox;
        this.webView = webView;
        this.number=number;
        this.labelItemName=label;
        this.imageView = view;
        selected = false;
        clear();
    }

    public boolean isSelected() {
        return selected;
    }

    public void select(){
        if(!labelItemName.getText().equalsIgnoreCase("")) {
            vBox.setStyle("-fx-border-color: greenyellow;-fx-border-style: solid;-fx-border-radius: 4%;-fx-border-width: 10px");
            this.selected = true;
        }

    }
    public void deSelect(){
            vBox.setStyle("-fx-border-style: none");
            this.selected = false;

    }
    public WebView getWebView() {
        return webView;
    }

    public void setItem(Item item){
        imageView.setImage(loadImage(item.getImageUrl()));
        Path path = FileSystems.getDefault().getPath(item.getHtmlUrl());
        File f = path.toFile();
        WebEngine webEngine= webView.getEngine();
        labelItemName.setText(item.getName());
        webEngine.load(f.toURI().toString());
        currentItem=item;
    }
    private Image loadImage(String url) {
        Image img = null;
        try {
            FileInputStream istream = new FileInputStream(url);
            img = new Image(istream);
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if( null==img) {
                return img;
            }else{
                return img;
            }
        }
    }
    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public void clear() {
        imageView.setImage(null);
        webView.getEngine().loadContent("<body style=\"background-color:black; color:white\">");
        labelItemName.setText("");
        deSelect();
        currentItem=null;
    }
}
