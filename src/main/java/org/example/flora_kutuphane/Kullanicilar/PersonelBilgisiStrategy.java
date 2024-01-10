package org.example.flora_kutuphane.Kullanicilar;

import Connection.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonelBilgisiStrategy implements IKullaniciBilgisiStrategy{

    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    @Override
    public IKullaniciBilgisi getKullaniciBilgisi(int kullaniciId) {

        String sql = "SELECT * FROM kutuphane_gorevlileri WHERE ogorevli_id = ?";

        try (PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql)) {

            preparedStatement.setInt(1, kullaniciId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    kullaniciId = resultSet.getInt("gorevli_id");
                    String ad = resultSet.getString("ad");
                    String soyad = resultSet.getString("soyad");
                    String tel = resultSet.getString("tel");
                    String e_posta = resultSet.getString("e_posta");
                    int rol_id = resultSet.getInt("rol_id");
                    String sifre = resultSet.getString("sifre");

                    return new PersonelBilgisi(kullaniciId,ad,soyad,tel,e_posta,rol_id,sifre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

