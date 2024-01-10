package org.example.flora_kutuphane.Kullanicilar;

import Connection.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OgrenciBilgisiStrategy implements IKullaniciBilgisiStrategy{

    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    @Override
    public IKullaniciBilgisi getKullaniciBilgisi(int kullaniciId) {

        String sql = "SELECT * FROM ogrenciler WHERE ogrenci_id = ?";

        try (PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql)) {

            preparedStatement.setInt(1, kullaniciId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    kullaniciId = resultSet.getInt("ogr_id");
                    String ad = resultSet.getString("ad");
                    String soyad = resultSet.getString("soyad");
                    //int ogr_no = resultSet.getInt("ogr_no");
                    int sinif = resultSet.getInt("sinif") ;
                    String bolum = resultSet.getString("bolum");
                    String tel = resultSet.getString("tel");
                    String e_posta = resultSet.getString("e_posta");
                    int rol_id = resultSet.getInt("rol_id");
                    String sifre = resultSet.getString("sifre");

                    return new OgrenciBilgisi(kullaniciId,ad,soyad,tel,e_posta,rol_id,sifre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
