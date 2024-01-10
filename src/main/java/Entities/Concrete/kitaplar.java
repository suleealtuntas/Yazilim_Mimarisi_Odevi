package Entities.Concrete;

import java.util.Date;

public class kitaplar {
    private int kitap_id;

    private String kitap_adi;

    private String konu;
    private String yazar;
    private String yayinevi;
    private int raf_no;
    private String stok_durumu;
    private Date tarih;
    private boolean durumu;

    private int yil;
    private int tur_id;

    public int getYil() {
        return yil;
    }

    public int getTur_id() {
        return tur_id;
    }
    public void setYil(int yil) {
        this.yil = yil;
    }

    public void setTur_id(int turId) {
        this.tur_id = turId;
    }

    public void setKitap_id(int kitapId) {
        this.kitap_id = kitapId;
    }

    public kitaplar() {

    }

    public int getKitap_id() {
        return kitap_id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public boolean getDurumu() {
        return durumu;
    }

    public void setDurumu(boolean durumu) {
        this.durumu = durumu;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getYayinevi() {
        return yayinevi;
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    public int getRaf_no() {
        return raf_no;
    }

    public void setRaf_no(int raf_no) {
        this.raf_no = raf_no;
    }

    public String getStok_durumu() {
        return stok_durumu;
    }

    public void setStok_durumu(String stok_durumu) {
        this.stok_durumu = stok_durumu;
    }

}
