package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLKitapSilmeDAO implements IKitapSilmeDAO{
    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    @Override
    public boolean kitapSilme(int id) {

        try {

            conn.kitapKisitlamalariGeciciKaldır();


            boolean kitapSilmeBasarili = kitaplarTablosuSilme(id);

            if (kitapSilmeBasarili) {
                return true;
            } else {

                return false;
            }
        } finally {

            conn.kitapKisitlamalariGeriEkle();
        }
    }

    private boolean kitaplarTablosuSilme(int id) {
        String sql = "DELETE FROM kitaplar WHERE kitap_id = ?";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
