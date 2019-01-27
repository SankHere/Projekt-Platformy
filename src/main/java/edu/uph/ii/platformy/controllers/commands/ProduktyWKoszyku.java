package edu.uph.ii.platformy.controllers.commands;

import edu.uph.ii.platformy.models.Produkt;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.Objects;

@Data
public class ProduktyWKoszyku {

    Produkt produkt;

    @Positive
    int ilosc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktyWKoszyku that = (ProduktyWKoszyku) o;
        return produkt.getId() == that.produkt.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(produkt.getId());
    }

    public ProduktyWKoszyku(Produkt produkt, int ilosc){
        this.produkt=produkt;
        this.ilosc=ilosc;
    }

}
