package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle")
public class ProduktNotFoundException extends RuntimeException{

    public ProduktNotFoundException(){
        super(String.format("Produkt nie istnieje"));
    }

    public ProduktNotFoundException(Long id){
        super(String.format("Produkt o id %d nie istnieje", id));
    }
}
