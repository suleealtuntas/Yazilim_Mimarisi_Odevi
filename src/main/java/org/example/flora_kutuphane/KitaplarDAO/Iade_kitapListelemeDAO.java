package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;
import Entities.Concrete.iade_kitaplar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Iade_kitapListelemeDAO implements IIade_kitapListeleme{

    DB_Connection conn = DB_Connection.ConnectAsSingleton();
    @Override
    public List<iade_kitaplar> iadeKitapListele() {
        List<iade_kitaplar> iadeKitapListe = new ArrayList<>();
        String sql = "SELECT * FROM iade_kitaplar";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    iade_kitaplar iadeKitaplar = new iade_kitaplar();
                    iadeKitaplar.setIdae_id(resultSet.getInt("iade_id"));
                    iadeKitaplar.setKitap_id(resultSet.getInt("kitap_id"));
                    iadeKitaplar.setOgr_id(resultSet.getInt("ogr_id"));
                    iadeKitaplar.setTarih(resultSet.getDate("tarih"));
                    iadeKitaplar.setDurumu(resultSet.getBoolean("durumu"));

                    iadeKitapListe.add(iadeKitaplar);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return iadeKitapListe;
    }
}
