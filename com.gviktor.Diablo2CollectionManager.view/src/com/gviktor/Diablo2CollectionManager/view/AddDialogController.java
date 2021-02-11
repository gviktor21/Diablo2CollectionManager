package com.gviktor.Diablo2CollectionManager.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDialogController {
    @FXML
    TextField text_newCollectionName;

    public String getcollectionName(){
        return text_newCollectionName.getText();
    }
}
