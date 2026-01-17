package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BankStorage {
    private final Map<Integer, Klient> klients = new ConcurrentHashMap<>();

    public Klient save(Klient klient) {
        klients.put(klient.getId(), klient);
        return klient;
    }

    public Optional<Klient> findById(int id) {
        return Optional.ofNullable(klients.get(id));
    }
}
