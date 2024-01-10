package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;
import Entities.Concrete.kitaplar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLKitapAraDAO implements IKitapAraDAO{

    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    public ObservableList<kitaplar> kitapAra(String aramaKelimesi)  {
        ObservableList<kitaplar> kitapListesi = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM kitaplar WHERE kitap_adi LIKE ? OR yazar LIKE ? OR yayinevi LIKE ? OR raf_no LIKE ? OR stok_durumu LIKE ?";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "%" + aramaKelimesi + "%");
            preparedStatement.setString(2, "%" + aramaKelimesi + "%");
            preparedStatement.setString(3, "%" + aramaKelimesi + "%");
            preparedStatement.setString(4, "%" + aramaKelimesi + "%");
            preparedStatement.setString(5, "%" + aramaKelimesi + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String kitapAdi = resultSet.getString("kitap_adi");
                String yazar = resultSet.getString("yazar");
                String yayinevi = resultSet.getString("yayinevi");
                int raf_no = resultSet.getInt("raf_no");
                String stok_durumu = resultSet.getString("stok_durumu");

                kitapListesi.add(new kitaplar());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kitapListesi;
    }
}
