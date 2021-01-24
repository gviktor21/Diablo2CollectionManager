package com.gviktor.Diablo2CollectionManager.view;

import javafx.scene.image.Image;

import java.io.FileInputStream;

 public class ImageLoader {
     public static final String ICON_MAIN_PATH ="icons/";
    public static Image loadImage(String url){
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
     public static Image loadImage(String url,double x,double y){
         Image img = null;
         try {
             FileInputStream istream = new FileInputStream(url);
             img = new Image(istream,x,y,false,false);
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
}
