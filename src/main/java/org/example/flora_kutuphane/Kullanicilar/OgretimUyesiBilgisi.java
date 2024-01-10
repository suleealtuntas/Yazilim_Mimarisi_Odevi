package org.example.flora_kutuphane.Kullanicilar;

public class OgretimUyesiBilgisi implements IKullaniciBilgisi{

    private int kullaniciId;
    private String ad;
    private String soyad;
    private String bolum;
    private String tel;
    private String ePosta;
    private int rolId;
    private String sifre;

    public OgretimUyesiBilgisi(int kullaniciId,String ad,String soyad,String tel,String ePosta,int rolId,String sifre){
        this.setKullaniciId(kullaniciId);
        this.setAd(ad);
        this.setSoyad(soyad);
        //this.setBolum(bolum);
        this.setTel(tel);
        this.setePosta(ePosta);
        this.setRolId(rolId);
        this.setSifre(sifre);
    }
    @Override
    public int getKullaniciId() {
        return kullaniciId;
    }

    @Override
    public String getAd() {
        return ad;
    }

    @Override
    public String getSoyad() {
        return soyad;
    }

    @Override
    public String getTel() {
        return sifre;
    }

    @Override
    public String getEPosta() {
        return ePosta;
    }

    @Override
    public int getRolId() {
        return rolId;
    }

    @Override
    public String getSifre() {
        return sifre;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getePosta() {
        return ePosta;
    }

    public void setePosta(String ePosta) {
        this.ePosta = ePosta;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
