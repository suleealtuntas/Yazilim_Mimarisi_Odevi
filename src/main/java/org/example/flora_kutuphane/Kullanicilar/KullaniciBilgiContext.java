package org.example.flora_kutuphane.Kullanicilar;

public class KullaniciBilgiContext {
    private IKullaniciBilgisiStrategy kullaniciBilgisiStrategy;

    public void setUserInfoStrategy(IKullaniciBilgisiStrategy kullaniciBilgisiStrategy) {
        this.kullaniciBilgisiStrategy = kullaniciBilgisiStrategy;
    }

    public IKullaniciBilgisi getKullaniciBilgisi(int kullaniciId) {
        return kullaniciBilgisiStrategy.getKullaniciBilgisi(kullaniciId);
    }
}
