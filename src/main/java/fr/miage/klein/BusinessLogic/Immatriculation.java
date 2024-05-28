package fr.miage.klein.BusinessLogic;

import java.util.regex.Pattern;

public class Immatriculation {
    private String immat;
    
    public Immatriculation(String immat){
        if (!patternMatches(immat))
            throw new IllegalArgumentException();
        this.immat = immat;
    }

    /**
     * Nous concidérons que les plaques d'immatriculations sont française (nouvelles et anciennes).
     * 
     * @param immat
     * @return
     */
    public boolean patternMatches(String immat) {
        return Pattern.compile("^[A-Z]{2}-\\d{3}-[A-Z]{2}$").matcher(immat).matches() 
            || Pattern.compile("\\d{1,4}\\s?[A-Z]{1,3}\\s?\\d{2}").matcher(immat).matches();
    }


    public String getImmat() {
        return this.immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

}
