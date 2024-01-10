package Entities.Concrete;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class iade_kitaplar {

    private  StringProperty tarih;
    private  BooleanProperty durumu;
    private StringProperty kitap_adi;

    private int iade_id;
    private Date tarihDate;
    private boolean durumuBool;
    private int kitap_id;
    private int ogr_id;

    public iade_kitaplar() {
        this.tarih = new SimpleStringProperty("");
        this.durumu = new SimpleBooleanProperty(false);
        this.kitap_adi = new SimpleStringProperty("");
    }

    public iade_kitaplar(int iade_id, Date tarih, int kitap_id, int ogr_id, boolean durumu,String kitap_adi) {
        this.iade_id = iade_id;
        this.tarihDate = tarih;
        this.kitap_id = kitap_id;
        this.ogr_id = ogr_id;
        this.durumuBool = durumu;

        String tarihStr = (tarih != null) ? tarih.toString() : "";
        this.tarih = new SimpleStringProperty(tarihStr);
        this.durumu = new SimpleBooleanProperty(durumu);
        this.kitap_adi = new SimpleStringProperty(kitap_adi);
    }

    public String getTarih() {
        return tarih.get();
    }

    public StringProperty tarihProperty() {
        return tarih;
    }

    public BooleanProperty durumuProperty() {
        return durumu;
    }

    public StringProperty kitap_adiProperty() {
        return kitap_adi;
    }

    public int getIade_id() {
        return iade_id;
    }

    public void setIade_id(int iade_id) {
        this.iade_id = iade_id;
    }

    public Date getTarihDate() {
        return tarihDate;
    }

    public void setTarihDate(Date tarihDate) {
        this.tarihDate = tarihDate;
    }

    public boolean isDurumuBool() {
        return durumuBool;
    }

    public void setDurumuBool(boolean durumuBool) {
        this.durumuBool = durumuBool;
    }

    public int getKitap_id() {
        return kitap_id;
    }

    public void setKitap_id(int kitap_id) {
        this.kitap_id = kitap_id;
    }

    public int getOgr_id() {
        return ogr_id;
    }

    public void setOgr_id(int ogr_id) {
        this.ogr_id = ogr_id;
    }

    public void setIdae_id(int iade_id) {
        this.iade_id = iade_id;
    }

    public void setTarih(Date tarih) {
        this.tarihDate = tarih;
        String tarihStr = (tarih != null) ? tarih.toString() : "";
        this.tarih = new SimpleStringProperty(tarihStr);
    }

    public void setDurumu(boolean durumu) {
        this.durumuBool = durumu;
        this.durumu = new SimpleBooleanProperty(durumu);
    }
}
