package org.example.flora_kutuphane.KitaplarDAO;


import Entities.Concrete.kitap_turleri;

import java.util.List;

public interface IKitapTurDAO {
    boolean kitapTurEkle(kitap_turleri kitapTur);
    boolean kitapTurGuncelle(kitap_turleri kitapTur);
    boolean kitapTurSil(int tur_id);
    List<kitap_turleri> kitapTurleriListele();
    kitap_turleri kitapTurBilgisiGetir(int turId);
    int getTurIdByTurAdi(String turAdi);
}
