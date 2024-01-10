package org.example.flora_kutuphane.Kullanicilar;

import Connection.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KullaniciDAO {
    DB_Connection conn = DB_Connection.ConnectAsSingleton();

   public List<IKullaniciBilgisi> kullanicilariGetir() {
       List<IKullaniciBilgisi> users = new ArrayList<>();

       List<IKullaniciBilgisi> ogrenciler = getUsersForRole(KullaniciRolleri.Ogrenci);
       users.addAll(ogrenciler);

       List<IKullaniciBilgisi> ogretimUyeleri = getUsersForRole(KullaniciRolleri.OgretimUyesi);
       users.addAll(ogretimUyeleri);

       List<IKullaniciBilgisi> kutuphaneGorevlileri = getUsersForRole(KullaniciRolleri.KutuphaneGorevlisi);
       users.addAll(kutuphaneGorevlileri);

       return users;
   }
    private String getKullaniciIdColumnName(KullaniciRolleri rol) {
        switch (rol) {
            case Ogrenci:
                return "ogr_id";
            case OgretimUyesi:
                return "ogr_uye_id";
            case KutuphaneGorevlisi:
                return "gorevli_id";
            default:
                throw new IllegalArgumentException("Geçersiz kullanıcı rolü: " + rol);
        }
    }
    private IKullaniciBilgisi createUserInfoFromResultSet(ResultSet resultSet,KullaniciRolleri rol) throws SQLException {
        int kullaniciId = resultSet.getInt(getKullaniciIdColumnName(rol));
        String ad = resultSet.getString("ad");
        String soyad = resultSet.getString("soyad");
        //long ogr_no = resultSet.getLong("ogr_no");
        //int sinif = resultSet.getInt("sinif") ;
        //String bolum = resultSet.getString("bolum");
        String tel = resultSet.getString("tel");
        String e_posta = resultSet.getString("e_posta");
        int rol_id = resultSet.getInt("rol_id");
        String sifre = resultSet.getString("sifre");


        switch (rol) {
            case Ogrenci:
                return new OgrenciBilgisi(kullaniciId, ad,soyad,tel,e_posta,rol_id,sifre);
            case OgretimUyesi:
                return new OgretimUyesiBilgisi(kullaniciId, ad, soyad,tel,e_posta,rol_id,sifre);
            case KutuphaneGorevlisi:
                return new PersonelBilgisi(kullaniciId, ad,soyad,tel,e_posta,rol_id,sifre);
            default:
                throw new IllegalArgumentException("Geçersiz kullanıcı rolü: " + rol);
        }
    }

    private List<IKullaniciBilgisi> getUsersForRole(KullaniciRolleri rol) {
        String tableName = getTableNameForRole(rol);
        String sql = "SELECT * FROM " + tableName;

        List<IKullaniciBilgisi> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                IKullaniciBilgisi user = createUserInfoFromResultSet(resultSet, rol);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private String getTableNameForRole(KullaniciRolleri rol) {
        switch (rol) {
            case Ogrenci:
                return "ogrenciler";
            case OgretimUyesi:
                return "ogretim_uyeleri";
            case KutuphaneGorevlisi:
                return "kutuphane_gorevlileri";
            default:
                throw new IllegalArgumentException("Geçersiz kullanıcı rolü: " + rol);
        }
    }
}
