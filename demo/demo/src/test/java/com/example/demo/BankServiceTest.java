package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BankServiceTest {

    @MockitoBean
    BankStorage bankStorage;


    @Autowired
    BankService bankService;

    @Test
    void createKlient() {
        Saldo saldo = new Saldo(20);
        when(bankStorage.save(any(Klient.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Klient klient = bankService.createKlient(20);


        assertNotNull(klient);
        assertEquals(20, klient.getSaldo().getValue());
        verify(bankStorage).save(any(Klient.class));
    }
@Test
    void getKlient() {

    Optional<Klient> fetched = bankService.getKlient(1);





    assertTrue(fetched.isPresent());
    assertEquals(1, fetched.get().getId());
  //  assertEquals(20, fetched.get().getSaldo()
    verify(bankStorage).findById(1);
}

//noo i tyle  zmojej wiedzy



}