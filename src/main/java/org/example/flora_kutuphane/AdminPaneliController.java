package org.example.flora_kutuphane;

import Entities.Concrete.kitap_turleri;
import Entities.Concrete.kitaplar;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.flora_kutuphane.KitaplarDAO.*;
import org.example.flora_kutuphane.Kullanicilar.IKullaniciBilgisi;
import org.example.flora_kutuphane.Kullanicilar.KullaniciDAO;
import org.example.flora_kutuphane.KitaplarDAO.*;
import org.example.flora_kutuphane.Kullanicilar.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPaneliController implements Initializable {

    @FXML
    private TableView<kitaplar> kitaplarListBox;

    @FXML
    private TableColumn<kitaplar, String> kitap_adi;

    @FXML
    private TableColumn<kitaplar, String> yazar;

    @FXML
    private TableColumn<kitaplar, String> yayinevi;

    @FXML
    private TableColumn<kitaplar, Integer> raf_no;

    @FXML
    private TableColumn<kitaplar, String> stok_durumu;

    @FXML
    private JFXTextArea kitapAdiText;
    @FXML
    private JFXTextArea kitapTuruText;
    @FXML
    private JFXTextArea yazarText;
    @FXML
    private JFXTextArea yayineviText;
    @FXML
    private JFXTextArea yilText;
    @FXML
    private JFXTextArea rafNumarasiText;
    @FXML
    private TextField konuText;
    @FXML
    private JFXTextArea stokDurumuText;
    @FXML
    private TableView<IKullaniciBilgisi> kullanıcıListBox;

    @FXML
    private TableColumn<IKullaniciBilgisi, String> kullaniciAdi;

    @FXML
    private TableColumn<IKullaniciBilgisi, String> kullaniciSoyadi;

    @FXML
    private TableColumn<IKullaniciBilgisi, String> kullaniciEposta;
    @FXML
    private TableColumn<IKullaniciBilgisi, String> kullaniciSifre;

    @FXML
    private TableColumn<IKullaniciBilgisi, Integer> kullaniciRol;


    private IKitapListelemeDAO kitapListelemeDAO = new SQLKitapListelemeDAO();
    private IKitapEklemeDAO kitapEklemeDAO = new SQLKitapEklemeDAO();
    private IKitapTurDAO kitapTurDAO = new SQLKitap_turleriDAO();
    private IKitapGuncellemeDAO kitapGuncellemeDAO = new SQLKitapGuncellemeDAO();
    private IKitapSilmeDAO kitapSilmeDAO = new SQLKitapSilmeDAO();
    private KullaniciDAO kullaniciDAO = new KullaniciDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        kitap_adi.setCellValueFactory(new PropertyValueFactory<>("kitap_adi"));
        yazar.setCellValueFactory(new PropertyValueFactory<>("yazar"));
        yayinevi.setCellValueFactory(new PropertyValueFactory<>("yayinevi"));
        raf_no.setCellValueFactory(new PropertyValueFactory<>("raf_no"));
        stok_durumu.setCellValueFactory(new PropertyValueFactory<>("stok_durumu"));



        kitaplarListBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                kitapAdiText.setText(newValue.getKitap_adi());
                yazarText.setText(newValue.getYazar());
                yayineviText.setText(newValue.getYayinevi());
                rafNumarasiText.setText(String.valueOf(newValue.getRaf_no()));
                konuText.setText(String.valueOf(newValue.getKonu()));
                yilText.setText(String.valueOf(newValue.getYil()));
                stokDurumuText.setText(String.valueOf((newValue.getStok_durumu())));
                setKitapTuruToTextArea(newValue);
            }
        });

        listAllBooks();
        initializeTableView();
        listAllUsers();


    }

    private void initializeTableView() {
        kullaniciRol.setCellValueFactory(new PropertyValueFactory<>("rolId"));
        kullaniciAdi.setCellValueFactory(new PropertyValueFactory<>("ad"));
        kullaniciSoyadi.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        kullaniciEposta.setCellValueFactory(new PropertyValueFactory<>("ePosta"));
        kullaniciSifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));

    }

    private void listAllUsers() {

        List<IKullaniciBilgisi> users = kullaniciDAO.kullanicilariGetir();
        kullanıcıListBox.getItems().addAll(users);
    }



    private void listAllBooks() {
        List<kitaplar> kitaplar = kitapListelemeDAO.kitapListele();
        ObservableList<kitaplar> kitapObservableList = FXCollections.observableArrayList(kitaplar);
        kitaplarListBox.setItems(kitapObservableList);

    }

    private void setKitapTuruToTextArea(kitaplar kitap) {
        int kitapTurId = kitap.getTur_id();
        kitap_turleri kitapTur = kitapTurDAO.kitapTurBilgisiGetir(kitapTurId);

        if (kitapTur != null) {
            kitapTuruText.setText(kitapTur.getTur_adi());
        }
    }

    private int seciliKitapId(kitaplar seciliKitap){
        if (seciliKitap != null) {
            int kitapId = seciliKitap.getKitap_id();
            return kitapId;
        }
        else {
            return 0;
        }
    }

    @FXML
    void kitapEkleEvent() {
        try {

            kitaplar yeniKitap = new kitaplar();

            String turAdi = kitapTuruText.getText();
            String kitapAdi = kitapAdiText.getText();

            int turId = getTurIdByTurAdi(turAdi);

            yeniKitap.setTur_id(turId);

            yeniKitap.setKitap_adi(kitapAdi);
            yeniKitap.setKonu(konuText.getText());
            yeniKitap.setYazar(yazarText.getText());
            yeniKitap.setYayinevi(yayineviText.getText());
            yeniKitap.setYil(Integer.parseInt(yilText.getText()));
            yeniKitap.setRaf_no(Integer.parseInt(rafNumarasiText.getText()));
            yeniKitap.setStok_durumu(stokDurumuText.getText());

            boolean eklemeBasarili = kitapEklemeDAO.kitapEkle(yeniKitap);

            if (eklemeBasarili) {
                showAlert("Kitap ekleme başarılı", "Kitap başarıyla eklendi.");
                listAllBooks();
            } else {
                showAlert("Kitap ekleme hatası", "Kitap eklenirken bir hata oluştu.");
            }
        } catch (NumberFormatException e) {
            showAlert("Hata", "Yıl ve Raf No alanlarına sadece sayı girebilirsiniz.");
        }
    }

    @FXML
    void kitapDuzenleEvent() {
        kitaplar seciliKitap = kitaplarListBox.getSelectionModel().getSelectedItem();

        if (seciliKitap != null) {

            String kitapAdi = kitapAdiText.getText();
            String konu = konuText.getText();
            String yazar = yazarText.getText();
            String yayinevi = yayineviText.getText();
            int yil = Integer.parseInt(yilText.getText());
            int rafNo = Integer.parseInt(rafNumarasiText.getText());
            String stokDurumu = stokDurumuText.getText();
            int id = seciliKitapId(seciliKitap);


            String turAdi = kitapTuruText.getText();
            int turId = getTurIdByTurAdi(turAdi);

            seciliKitap.setTur_id(turId);
            seciliKitap.setKitap_adi(kitapAdi);
            seciliKitap.setKonu(konu);
            seciliKitap.setYazar(yazar);
            seciliKitap.setYayinevi(yayinevi);
            seciliKitap.setYil(yil);
            seciliKitap.setRaf_no(rafNo);
            seciliKitap.setStok_durumu(stokDurumu);


            boolean guncellemeBasarili = kitapGuncellemeDAO.kitapGuncelleme(seciliKitap,id);


            if (guncellemeBasarili) {
                showAlert("Kitap güncelleme başarılı", "Kitap başarıyla güncellendi.");
                listAllBooks();
            } else {
                showAlert("Kitap güncelleme hatası", "Kitap güncellenirken bir hata oluştu.");
            }



        } else {
            showAlert("Hata", "Lütfen güncellenecek kitabı seçin.");
        }
    }

    @FXML
    void kitapSilEvent() {

        kitaplar seciliKitap = kitaplarListBox.getSelectionModel().getSelectedItem();

        if (seciliKitap != null) {
            int id = seciliKitap.getKitap_id();
            boolean silmeBasarili = kitapSilmeDAO.kitapSilme(id);


            if (silmeBasarili) {
                showAlert("Kitap silme başarılı", "Kitap başarıyla silindi.");
            } else {
                showAlert("Kitap silme hatası", "Kitap silinirken bir hata oluştu.");
            }


            listAllBooks();
        } else {
            showAlert("Hata", "Lütfen silinecek kitabı seçin.");
        }
    }

    private int getTurIdByTurAdi(String turAdi) {

        return kitapTurDAO.getTurIdByTurAdi(turAdi);
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
