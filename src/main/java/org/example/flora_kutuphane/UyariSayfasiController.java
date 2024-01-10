package org.example.flora_kutuphane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UyariSayfasiController implements Initializable{
    @FXML
    private Label uyariBaslikLabel;
    @FXML
    private Label uyariIcerikLabel;

    public void setUyari(String baslik, String icerik){
        uyariBaslikLabel.setText(baslik);
        uyariIcerikLabel.setText(icerik);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
