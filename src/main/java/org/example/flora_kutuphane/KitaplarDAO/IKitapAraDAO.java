package org.example.flora_kutuphane.KitaplarDAO;

import Entities.Concrete.kitaplar;
import javafx.collections.ObservableList;

public interface IKitapAraDAO {
    public ObservableList<kitaplar> kitapAra(String aramaKelimesi);
}
