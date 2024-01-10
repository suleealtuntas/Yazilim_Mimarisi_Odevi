package Entities.Concrete;

public class roller {
    private int rol_id;
    private String rol_adi;
    private boolean kitap_ekle;
    private boolean kitap_sil;
    private boolean kitap_guncelle;
    private boolean kitap_ara;
    private boolean odunc_al;
    private boolean kitap_iade;

    public int getRol_id() {
        return rol_id;
    }

    public String getRol_adi() {
        return rol_adi;
    }

    public void setRol_adi(String rol_adi) {
        this.rol_adi = rol_adi;
    }

    public boolean isKitap_ekle() {
        return kitap_ekle;
    }

    public void setKitap_ekle(boolean kitap_ekle) {
        this.kitap_ekle = kitap_ekle;
    }

    public boolean isKitap_sil() {
        return kitap_sil;
    }

    public void setKitap_sil(boolean kitap_sil) {
        this.kitap_sil = kitap_sil;
    }

    public boolean isKitap_guncelle() {
        return kitap_guncelle;
    }

    public void setKitap_guncelle(boolean kitap_guncelle) {
        this.kitap_guncelle = kitap_guncelle;
    }

    public boolean isKitap_ara() {
        return kitap_ara;
    }

    public void setKitap_ara(boolean kitap_ara) {
        this.kitap_ara = kitap_ara;
    }

    public boolean isOdunc_al() {
        return odunc_al;
    }

    public void setOdunc_al(boolean odunc_al) {
        this.odunc_al = odunc_al;
    }

    public boolean isKitap_iade() {
        return kitap_iade;
    }

    public void setKitap_iade(boolean kitap_iade) {
        this.kitap_iade = kitap_iade;
    }
}
