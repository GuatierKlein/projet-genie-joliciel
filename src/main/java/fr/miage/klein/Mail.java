package fr.miage.klein;

import java.util.regex.Pattern;

public class Mail {

    String mail;
    
    public Mail(String mail){
        if (!patternMatches(mail))
            throw new IllegalArgumentException();
        this.mail = mail;
    }

    private boolean patternMatches(String emailAddress) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        .matcher(emailAddress)
        .matches();
    }
}