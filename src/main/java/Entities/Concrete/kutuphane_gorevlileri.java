package Entities.Concrete;

public class kutuphane_gorevlileri {
    private int gorevli_id;
    private String ad;
    private String soyad;
    private String tel;
    private String e_posta;
    private String sifre;

    public int getGorevli_id() {
        return gorevli_id;
    }
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
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
}
