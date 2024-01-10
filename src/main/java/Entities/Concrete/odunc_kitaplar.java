package Entities.Concrete;

import javafx.beans.property.*;

import java.util.Date;

public class odunc_kitaplar {
    private final StringProperty tarih;
    private BooleanProperty durumu;
    private StringProperty kitap_adi;

    public odunc_kitaplar(String kitap_adi,String tarih, boolean durumu) {
        this.kitap_adi = new SimpleStringProperty(kitap_adi);
        this.tarih = new SimpleStringProperty(tarih);
        this.durumu = new SimpleBooleanProperty(durumu);
    }


    public StringProperty kitap_adiProperty(){
        return kitap_adi;
    }
    public StringProperty tarihProperty() {
        return tarih;
    }

    public BooleanProperty durumuProperty() {
        return durumu;
    }
}
