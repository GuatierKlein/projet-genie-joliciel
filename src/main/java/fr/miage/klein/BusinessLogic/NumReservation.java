package fr.miage.klein.BusinessLogic;

public class NumReservation {
    private int num;

    public NumReservation(int num){
        if(!verifNumReservation(num))
            throw new IllegalArgumentException();
        this.num = num;
    }

    public boolean verifNumReservation(int num){
        return num >= 0 && num <= 99999;
    }
}
