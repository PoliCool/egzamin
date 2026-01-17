package com.example.demo;

public class Klient {
    int id;
    Saldo saldo;

    public Klient(int id, Saldo saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public void setSaldo(Saldo saldo) {
        this.saldo = saldo;
    }
}
