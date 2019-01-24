package edu.uph.ii.platformy.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
public class ProduktFilter {

    private String phrase;

    @Positive
    private double cena;


    public boolean isEmpty(){
        return StringUtils.isEmpty(phrase) && cena == 0.0;
    }

    public void clear(){
        this.phrase = "";
        this.cena = 0.0;
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }

}
