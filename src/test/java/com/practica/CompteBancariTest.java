package com.practica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompteBancariTest {

    private CompteBancari compte;

    @BeforeEach
    public void setUp() {
        compte = new CompteBancari("Mariam Solano", "ES123456789", 100.0);
    }

    @Test
    public void testCreacioCorrecta() {
        CompteBancari nouCompte = new CompteBancari("Joan", "ES98765", 50.0);
        assertEquals("Joan", nouCompte.getTitular());
        assertEquals("ES98765", nouCompte.getIban());
        assertEquals(50.0, nouCompte.getSaldo(), 0.001);
    }

    @Test
    public void testErrorCreacioTitularBuit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari("", "ES123456789", 100.0);
        });
    }

    @Test
    public void testErrorCreacioIbanBuit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari("Mariam Solano", null, 100.0);
        });
    }

    @Test
    public void testErrorCreacioSaldoNegatiu() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari("Mariam Solano", "ES123456789", -10.0);
        });
    }

    @Test
    public void testIngresCorrecte() {
        compte.ingressar(50.0);
        assertEquals(150.0, compte.getSaldo(), 0.001);
    }

    @Test
    public void testErrorIngressarQuantitatNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.ingressar(-20.0);
        });
    }

    @Test
    public void testRetiradaCorrecta() {
        compte.retirar(40.0);
        assertEquals(60.0, compte.getSaldo(), 0.001);
    }

    @Test
    public void testErrorRetirarQuantitatNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.retirar(0);
        });
    }

    @Test
    public void testErrorRetirarMesDelDisponible() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.retirar(200.0);
        });
    }
}