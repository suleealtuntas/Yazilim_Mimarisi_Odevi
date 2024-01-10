package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;
import Entities.Concrete.kitaplar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLKitapListelemeDAO implements IKitapListelemeDAO{
    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    @Override
    public List<kitaplar> kitapListele() {
        List<kitaplar> kitapListe = new ArrayList<>();
        String sql = "SELECT * FROM kitaplar";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    kitaplar kitaplar = new kitaplar();
                    kitaplar.setKitap_id(resultSet.getInt("kitap_id"));
                    kitaplar.setKitap_adi(resultSet.getString("kitap_adi"));
                    kitaplar.setKonu(resultSet.getString("konu"));
                    kitaplar.setYazar(resultSet.getString("yazar"));
                    kitaplar.setYayinevi(resultSet.getString("yayinevi"));
                    kitaplar.setYil(resultSet.getInt("yil"));
                    kitaplar.setTur_id(resultSet.getInt("tur_id"));
                    kitaplar.setRaf_no(resultSet.getInt("raf_no"));
                    kitaplar.setStok_durumu(resultSet.getString("stok_durumu"));
                    kitapListe.add(kitaplar);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kitapListe;
    }
}
