package org.example.flora_kutuphane.OturumIslemleri;

public class BildirimPaneli implements IBildirimObserver{
    @Override
    public void update(String mesaj) {

        displayError(mesaj);
    }

    private void displayError(String uyariTuru)  {
        // Hata bildirim panelinde hatayı göster

        // Burada JavaFX veya diğer GUI bileşenleri kullanarak paneli güncelleyebilirsiniz
    }
}
