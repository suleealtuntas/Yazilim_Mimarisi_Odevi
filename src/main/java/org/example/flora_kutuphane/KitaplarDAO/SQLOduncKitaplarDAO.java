package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SQLOduncKitaplarDAO implements IOduncKitaplarDAO{
    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    @Override
    public boolean oduncAl(int kitapId, int ogrId) {
        String insertQuery = "INSERT INTO odunc_kitaplar (kitap_id, ogr_id, tarih, durumu) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(insertQuery)) {
            statement.setInt(1, kitapId);
            statement.setInt(2, ogrId);
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            statement.setBoolean(4, true); // durumu değeri true olarak ayarlanır

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0; // Ekleme başarılıysa true, değilse false döndürür
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndürür
        }
    }
}
