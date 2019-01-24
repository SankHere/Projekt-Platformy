package edu.uph.ii.platformy.controllers.commands;

import edu.uph.ii.platformy.models.Produkt;
import lombok.Data;

@Data
public class ProduktyWKoszyku {

    Produkt produkt;

    int ilosc;

    public ProduktyWKoszyku(Produkt produkt, int ilosc){
        this.produkt=produkt;
        this.ilosc=ilosc;
    }
}
