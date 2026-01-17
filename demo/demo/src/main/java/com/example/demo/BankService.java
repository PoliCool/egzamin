package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BankService {

    private final BankStorage storage;
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public BankService(BankStorage storage) {
        this.storage = storage;
    }


    public Klient createKlient(int initialSaldo) {
        int id = idGenerator.incrementAndGet();
        Klient klient = new Klient(id, new Saldo(initialSaldo));
        return storage.save(klient);
    }


    public Optional<Klient> getKlient(int id) {
        return storage.findById(id);
    }


    public Przelew wplata(int id, int wartosc) {
        if (wartosc <= 0) {
            return new Przelew(wartosc, PrzelewStatus.DECLINED, 0, "Kwota musi byc > 0");
        }

        Klient klient = storage.findById(id).orElse(null);
        if (klient == null) {
            return new Przelew(wartosc, PrzelewStatus.DECLINED, 0, "klient nie istnieje");
        }

        int newSaldo = klient.getSaldo().getValue() + wartosc;
        klient.setSaldo(new Saldo(newSaldo));
        storage.save(klient);

        return new Przelew(wartosc, PrzelewStatus.ACCEPTED, newSaldo, "wplata przyjeta");
    }


    public Przelew przelew(int id, int wartosc) {
        if (wartosc <= 0) {
            return new Przelew(wartosc, PrzelewStatus.DECLINED, 0, "wota musi byc > 0");
        }

        Klient klient = storage.findById(id).orElse(null);
        if (klient == null) {
            return new Przelew(wartosc, PrzelewStatus.DECLINED, 0, "llient nie istnieje");
        }

        int saldo = klient.getSaldo().getValue();
        if (saldo < wartosc) {
            return new Przelew(wartosc, PrzelewStatus.DECLINED, saldo, "brak srodkuw");
        }

        int newSaldo = saldo - wartosc;
        klient.setSaldo(new Saldo(newSaldo));
        storage.save(klient);

        return new Przelew(wartosc, PrzelewStatus.ACCEPTED, newSaldo, "przelew zrobiony uwaga na skarbokwe");
    }
}
