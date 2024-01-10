package org.example.flora_kutuphane.OturumIslemleri;

import Connection.DB_Connection;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.flora_kutuphane.UyariSayfasiController;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KullaniciGirisi{
    DB_Connection conn = DB_Connection.ConnectAsSingleton();
    private List<IBildirimObserver> errorObservers = new ArrayList<>();

    public void addErrorObserver(IBildirimObserver observer) {
        errorObservers.add(observer);
    }

    private void notifyErrorObservers(String bildirimMesaji) {
        for (IBildirimObserver observer : errorObservers) {
            observer.update(bildirimMesaji);

        }
    }

    private void uyariSayfasiGoster(String baslik, String icerik){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sayfalar/yurikutuphane/OturumIslemleri/uyari_sayfasi.fxml"));
            Parent root = loader.load();

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

    public void girisKullanici(String ePosta, String sifre)  {
        boolean isValid = checkCredentials(ePosta, sifre);

        if (isValid) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sayfalar/yurikutuphane/sayfalar.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Ana Sayfa");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Oturum açma başarısız, uyarı sayfasını göster
            notifyErrorObservers("Kullanıcı adı veya şifre hatalı!");

        }
    }

    @FXML
    private JFXRadioButton ogr_gorevRadioButton;
    @FXML
    private JFXRadioButton ogrRadioButton;
    @FXML
    private JFXRadioButton personelRadioButton;
    private boolean checkCredentials(String ePosta, String sifre) {
        String sql;
        if(ogr_gorevRadioButton.isSelected()){
            sql ="select * from ogretim_uyeleri where e_posta = ? and sifre = ?";
        } else if (ogrRadioButton.isSelected()) {
            sql = "SELECT * FROM ogrenciler WHERE e_posta = ? AND sifre = ?";
        } else if (personelRadioButton.isSelected()) {
            sql = "SELECT * FROM kutuphane_gorevlileri WHERE e_posta = ? AND sifre = ?";
        }else {
            sql=null;
            String icerik = "Kullanıcı tipi seçmeden işlem yapamazsınız!";
            uyariSayfasiGoster("Kullanıcı tipi seçiniz!",icerik);
        }

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
}
