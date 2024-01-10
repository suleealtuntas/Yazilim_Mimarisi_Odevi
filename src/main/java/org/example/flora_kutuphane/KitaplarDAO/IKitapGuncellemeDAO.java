package org.example.flora_kutuphane.KitaplarDAO;

import Entities.Concrete.kitaplar;

public interface IKitapGuncellemeDAO {
    boolean kitapGuncelleme(kitaplar kitaplar, int id);
}
