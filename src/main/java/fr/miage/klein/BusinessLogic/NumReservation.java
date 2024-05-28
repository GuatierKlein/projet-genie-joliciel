package fr.miage.klein.BusinessLogic;

public class NumReservation {
    private int num;

    public NumReservation(int num){
        if(!verifNumReservation(num))
            throw new IllegalArgumentException();
        this.num = num;
    }

    public NumReservation(String value){
        int num = Integer.parseInt(value);
        if(!verifNumReservation(num))
            throw new IllegalArgumentException();
        this.num = num;
    }

    public boolean verifNumReservation(int num){
        return num >= 0 && num <= 99999;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
