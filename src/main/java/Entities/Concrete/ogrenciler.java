package Entities.Concrete;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class ogrenciler extends RecursiveTreeObject<ogrenciler> {
    private int ogrenci_id;
    private StringProperty ad;
    private StringProperty soyad;
    private Integer ogr_no;

    private Integer sinif;
    private String bolum;
    private String tel;
    private String e_posta;
    private String sifre;

    public ogrenciler(String ad, String soyad, String bolum) {
        this.ad = new SimpleStringProperty(ad);
        this.soyad = new SimpleStringProperty(soyad);
        this.bolum = bolum;
    }

    public int getOgrenci_id() {
        return ogrenci_id;
    }

    public String getAd() {
        return ad.get();
    }

    public void setAd(String ad) {
        this.ad.set(ad);
    }

    public StringProperty adProperty(){
        return ad;
    }

    public Integer getOgr_no() {
        return ogr_no;
    }

    public void setOgr_no(Integer ogr_no) {
        this.ogr_no = ogr_no;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getE_posta() {
        return e_posta;
    }

    public void setE_posta(String e_posta) {
        this.e_posta = e_posta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public Integer getSinif() {
        return sinif;
    }

    public void setSinif(Integer sinif) {
        this.sinif = sinif;
    }
}
