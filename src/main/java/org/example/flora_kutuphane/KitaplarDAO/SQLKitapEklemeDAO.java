package org.example.flora_kutuphane.KitaplarDAO;

import Connection.DB_Connection;
import Entities.Concrete.kitaplar;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLKitapEklemeDAO implements IKitapEklemeDAO{

    DB_Connection conn = DB_Connection.ConnectAsSingleton();

    @Override
    public boolean kitapEkle(kitaplar kitaplar) {
        String sql = "INSERT INTO kitaplar (kitap_adi,konu,yazar,yayinevi,yil,raf_no,stok_durumu,tur_id) VALUES (?, ?, ?, ?, ?,?,?,?)";

        try (PreparedStatement statement = conn.getConnection().prepareStatement(sql)) {
            statement.setString(1, kitaplar.getKitap_adi());
            statement.setString(2, kitaplar.getKonu());
            statement.setString(3, kitaplar.getYazar());
            statement.setString(4, kitaplar.getYayinevi());
            statement.setInt(5, kitaplar.getYil());
            statement.setInt(6, kitaplar.getRaf_no());
            statement.setString(7, kitaplar.getStok_durumu());
            statement.setInt(8,kitaplar.getTur_id());

            statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}
