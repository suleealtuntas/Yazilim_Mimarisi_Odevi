package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;
import Entities.Concrete.kitap_turleri;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLKitap_turleriDAO implements IKitapTurDAO {

    DB_Connection conn = DB_Connection.ConnectAsSingleton();
    @Override
    public boolean kitapTurEkle(kitap_turleri kitapTur) {
        String sql = "INSERT INTO kitap_turleri (tur_adi) VALUES (?)";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setString(1, kitapTur.getTur_adi());

            statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean kitapTurGuncelle(kitap_turleri kitapTur) {
        String sql = "UPDATE kitap_turleri SET tur_adi=? WHERE tur_id = ?";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setString(1, kitapTur.getTur_adi());
            statement.setInt(2, kitapTur.getTur_id());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0; // Güncelleme başarılıysa true döndürür
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Hata durumunda hatayı konsola yazdırabilirsiniz
            return false; // Hata durumunu başarısız olarak kabul et
        }
    }

    @Override
    public boolean kitapTurSil(int tur_id) {
        String sql = "DELETE FROM kitap_turleri WHERE tur_id = ?";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setInt(1, tur_id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0; // Silme başarılıysa true döndürür
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunu başarısız olarak kabul et
        }
    }

    @Override
    public List<kitap_turleri> kitapTurleriListele() {
        List<kitap_turleri> kitapTurListe = new ArrayList<>();
        String sql = "SELECT * FROM kitap_turleri";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    kitap_turleri kitap_turleri = new kitap_turleri();
                    kitap_turleri.setTur_adi(resultSet.getString("tur_adi"));

                    kitapTurListe.add(kitap_turleri);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kitapTurListe;
    }

    @Override
    public kitap_turleri kitapTurBilgisiGetir(int turId) {
        String sql = "SELECT * FROM kitap_turleri WHERE tur_id = ?";
        kitap_turleri kitapTur = null;

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setInt(1, turId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    kitapTur = new kitap_turleri();
                    kitapTur.setTur_id(resultSet.getInt("tur_id"));
                    kitapTur.setTur_adi(resultSet.getString("tur_adi"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kitapTur;
    }

    @Override
    public int getTurIdByTurAdi(String turAdi) {
        String sql = "SELECT tur_id FROM kitap_turleri WHERE tur_adi = ?";
        int turId = -1;

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setString(1, turAdi);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    turId = resultSet.getInt("tur_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turId;
    }
}
