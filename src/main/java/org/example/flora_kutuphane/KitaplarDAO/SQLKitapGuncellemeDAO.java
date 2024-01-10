package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;
import Entities.Concrete.kitaplar;
import org.example.flora_kutuphane.AdminPaneliController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLKitapGuncellemeDAO implements IKitapGuncellemeDAO{
    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    @Override
    public boolean kitapGuncelleme(kitaplar kitaplar, int id) {
        String sql = "UPDATE kitaplar SET kitap_adi = ?, konu = ?, yazar = ?, yayinevi = ?,yil=?,raf_no=?,stok_durumu=?,tur_id=? WHERE kitap_id = ?";
        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setString(1, kitaplar.getKitap_adi());
            statement.setString(2, kitaplar.getKonu());
            statement.setString(3, kitaplar.getYazar());
            statement.setString(4, kitaplar.getYayinevi());
            statement.setInt(5, kitaplar.getYil());
            statement.setInt(6, kitaplar.getRaf_no());
            statement.setString(7, kitaplar.getStok_durumu());
            statement.setInt(8,kitaplar.getTur_id());
            statement.setInt(9, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}
