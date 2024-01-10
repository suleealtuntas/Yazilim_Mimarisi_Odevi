package org.example.flora_kutuphane;

import Connection.DB_Connection;
import Entities.Concrete.ogrenciler;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXRadioButton;
import org.example.flora_kutuphane.OturumIslemleri.IBildirimObserver;

public class OturumController implements Initializable {

    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    private Stage oturumStage;
    private Scene scene;
    private Parent root;

    @FXML
    private SplitPane splitPane = new SplitPane();
    @FXML
    private TextField girisYapEpostaText;
    @FXML
    private TextField girisYapSifreText;

    @FXML
    private JFXRadioButton ogr_gorevRadioButton;
    @FXML
    private JFXRadioButton ogrRadioButton;
    @FXML
    private JFXRadioButton personelRadioButton;
    @FXML
    private JFXRadioButton ogr_gorevUyeRadioButton;
    @FXML
    private JFXRadioButton ogrUyeRadioButton;
    @FXML
    private JFXRadioButton personelUyeRadioButton;
    @FXML
    private TextField uyeAd;
    @FXML
    private TextField uyeSoyad;
    @FXML
    private TextField uyeTelNo;
    @FXML
    private TextField uyeSifre;
    @FXML
    private TextField uyeBolum;
    @FXML
    private TextField uyeOgrNo;
    @FXML
    private TextField uyeEPosta;
    @FXML
    private TextField uyeSinif;
    @FXML
    private Label uyeBolumLbl;
    @FXML
    private Label uyeOgrNoLbl;
    @FXML
    private Label uyeSinifLbl;



    @FXML
    private void ogr_gorevUyeRadioButtonEvent(ActionEvent event){
        uyeOgrNoLbl.setVisible(false);
        uyeOgrNo.setVisible(false);
        uyeSinif.setVisible(false);
        uyeSinifLbl.setVisible(false);
    }

    @FXML
    private void personelUyeRadioButtonEvent(ActionEvent event){
        uyeOgrNoLbl.setVisible(false);
        uyeOgrNo.setVisible(false);
        uyeBolum.setVisible(false);
        uyeBolumLbl.setVisible(false);
        uyeSinif.setVisible(false);
        uyeSinifLbl.setVisible(false);
    }

    @FXML
    private void ogrlUyeRadioButtonEvent(ActionEvent event){
        uyeOgrNoLbl.setVisible(true);
        uyeOgrNo.setVisible(true);
        uyeBolum.setVisible(true);
        uyeBolumLbl.setVisible(true);
        uyeSinif.setVisible(true);
        uyeSinifLbl.setVisible(true);
    }

    @FXML
    private void closeRightPane(ActionEvent event) {
        splitPane.setDividerPosition(0,872);
    }

    @FXML
    private void girisYapEvent(ActionEvent event) throws SQLException {
        String ePosta = girisYapEpostaText.getText();
        String sifre = girisYapSifreText.getText();

        girisKullanici(ePosta,sifre);

        notifyErrorObservers("Giriş Yapılamadı!");

    }



    @FXML
    private void uyeOlEvent(ActionEvent event){
        if(ogr_gorevUyeRadioButton.isSelected()){
            uyeOgretimGorevli(uyeAd.getText(),uyeSoyad.getText(),uyeBolum.getText(),uyeTelNo.getText(),uyeEPosta.getText(),uyeSifre.getText());

        } else if (ogrUyeRadioButton.isSelected()) {
            uyeOgrenci(uyeAd.getText(),uyeSoyad.getText(),Integer.parseInt(uyeOgrNo.getText()),Integer.parseInt(uyeSinif.getText()),uyeBolum.getText(),uyeTelNo.getText(),uyeEPosta.getText(),uyeSifre.getText());
        } else if (personelUyeRadioButton.isSelected()) {
            uyeOgrNoLbl.setVisible(false);
            uyeOgrNo.setVisible(false);
            uyeBolum.setVisible(false);
            uyeBolumLbl.setVisible(false);
            uyeSinif.setVisible(false);
            uyeSinifLbl.setVisible(false);
            uyePersonel(uyeAd.getText(),uyeSoyad.getText(),uyeTelNo.getText(),uyeEPosta.getText(),uyeSifre.getText());
        }else{
            String icerik ="Lütfen kullanıcı türü seçiniz." ;
            uyariSayfasiGoster("Kullanıcı türü seçmediniz!",icerik);
            notifyErrorObservers("Girdiğiniz bilgileri kontrol ediniz!");
        }
    }


    private List<IBildirimObserver> errorObservers = new ArrayList<>();

    public void addErrorObserver(IBildirimObserver observer) {
        errorObservers.add(observer);
    }

    private void notifyErrorObservers(String bildirimMesaji) {
        for (IBildirimObserver observer : errorObservers) {
            observer.update(bildirimMesaji);

        }
    }


    public void girisKullanici(String ePosta, String sifre)  {
        boolean isValid = girisBilgiKontrol(ePosta, sifre);

        if (isValid) {
            try {
                if(personelRadioButton.isSelected()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_paneli.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Ana Sayfa");
                    stage.setScene(scene);
                    stage.show();
                }else if(ogr_gorevRadioButton.isSelected()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("kutuphaneler.fxml"));
                    Parent root = loader.load();
                    HelloController helloController = loader.getController();

                    DB_Connection dbConnection = DB_Connection.ConnectAsSingleton();
                    Statement statement = dbConnection.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM ogretim_uyeleri WHERE e_posta = '" + ePosta + "'");

                    if(resultSet.next()){
                        String ad = resultSet.getString("ad");
                        String soyad = resultSet.getString("soyad");
                        String bolum = resultSet.getString("bolum");
                        String tel = resultSet.getString("tel");

                        helloController.setUserInfo(ad, soyad, null, null, bolum, tel, ePosta,true);
                    }


                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Profil");
                    stage.setScene(scene);
                    stage.show();
                }
                else if(ogrRadioButton.isSelected()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("kutuphaneler.fxml"));
                    Parent root = loader.load();

                    HelloController helloController = loader.getController();

                    DB_Connection dbConnection = DB_Connection.ConnectAsSingleton();
                    Statement statement = dbConnection.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM ogrenciler WHERE e_posta = '" + ePosta + "'");

                    if(resultSet.next()){
                        String ad = resultSet.getString("ad");
                        String soyad = resultSet.getString("soyad");
                        Integer ogr_no = resultSet.getInt("ogr_no");
                        Integer sinif = resultSet.getInt("sinif");
                        String bolum = resultSet.getString("bolum");
                        String tel = resultSet.getString("tel");

                        helloController.setUserInfo(ad, soyad, ogr_no, sinif, bolum, tel, ePosta,false);

                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Profil");
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            String icerik ="Girdiğiniz bilgileri kontrol ediniz!" ;
            uyariSayfasiGoster("Hatalı Bilgi Girişi!",icerik);
            notifyErrorObservers("Girdiğiniz bilgileri kontrol ediniz!");
        }
    }



    private boolean girisBilgiKontrol(String ePosta, String sifre) {

        String table_name = null;

        if(ogr_gorevRadioButton.isSelected()){
            table_name = "ogretim_uyeleri";
        } else if (ogrRadioButton.isSelected()) {
            table_name ="ogrenciler";
        } else if (personelRadioButton.isSelected()) {
            table_name = "kutuphane_gorevlileri";
        }

        String sql = "SELECT * FROM " + table_name + " WHERE e_posta = ? AND sifre = ?";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setString(1, ePosta);
            statement.setString(2, sifre);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next(); // Eğer kayıt varsa true, yoksa false dönecek
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Hata durumunda hatayı konsola yazdırabilirsiniz
            return false; // Hata durumunu başarısız olarak kabul et
        }
    }

    private boolean uyeOgrenci(String ad, String soyad, Integer ogr_no, int sinif, String bolum,String tel,String e_posta,String sifre){

        String sql = "INSERT INTO ogrenciler (ad, soyad, bolum, e_posta, ogr_no, rol_id, sifre, sinif, tel) VALUES ('"
                + ad + "', '" + soyad + "', '" + bolum + "', '" + e_posta + "', '" + ogr_no + "', 1, '" + sifre + "', " + sinif + ", '" + tel + "')";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {

            statement.executeUpdate();
            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Hata durumunda hatayı konsola yazdırabilirsiniz
            return false; // Hata durumunu başarısız olarak kabul et
        }
    }

    private boolean uyeOgretimGorevli(String ad, String soyad, String bolum,String tel,String e_posta,String sifre){

        String sql = "INSERT INTO ogretim_uyeleri (ad, soyad, bolum, e_posta, rol_id, sifre, tel) VALUES ('"
                + ad + "', '" + soyad + "', '" + bolum + "', '" + e_posta + "', 3, '" + sifre + "', '" + tel + "')";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {

            statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Hata durumunda hatayı konsola yazdırabilirsiniz
            return false; // Hata durumunu başarısız olarak kabul et
        }
    }

    private boolean uyePersonel(String ad, String soyad, String tel,String e_posta,String sifre){

        String sql = "INSERT INTO kutuphane_gorevlileri (ad, soyad, e_posta, rol_id, sifre, tel) VALUES ('"
                + ad + "', '" + soyad + "', '" + e_posta + "', 2, '" + sifre + "', '" + tel + "')";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {

            statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Hata durumunda hatayı konsola yazdırabilirsiniz
            return false; // Hata durumunu başarısız olarak kabul et
        }
    }

    private void uyariSayfasiGoster(String baslik, String icerik){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sayfalar/yurikutuphane/uyari_sayfasi.fxml"));
            Parent root = loader.load(getClass().getResource("/sayfalar/yurikutuphane/uyari_sayfasi.fxml").openStream());

            UyariSayfasiController controller = loader.getController();
            controller.setUyari(baslik, icerik);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Uyarı");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}