/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.criptografia;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author sfurbino
 */
public class GeradorTokenRandomico {

    public String proximaString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    private static final String MAIUSCULO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String MINUSCULO = "abcdefghijklmnopqrstuvwxyz";

    private static final String DIGITOS = "0123456789";

    private static final String ALPHANUMERICO = MAIUSCULO + MINUSCULO + DIGITOS;

    private final Random random;

    private final char[] symbols;

    private final char[] buf;

    public GeradorTokenRandomico(int length, Random random, String symbols) {
        if (length < 1) {
            throw new IllegalArgumentException();
        }
        if (symbols.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public GeradorTokenRandomico(int length, Random random) {
        this(length, random, ALPHANUMERICO);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public GeradorTokenRandomico(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public GeradorTokenRandomico() {
        this(21);
    }

}
