package com.example.demo;

public class Przelew {
      int wartosc;
      PrzelewStatus status;
      int newSaldo;
      String message;

    public Przelew(int wartosc, PrzelewStatus status, int newSaldo, String message) {
        this.wartosc = wartosc;
        this.status = status;
        this.newSaldo = newSaldo;
        this.message = message;
    }

    public int getWartosc() { return wartosc; }
    public PrzelewStatus getStatus() { return status; }
    public int getNewSaldo() { return newSaldo; }
    public String getMessage() { return message; }
}
