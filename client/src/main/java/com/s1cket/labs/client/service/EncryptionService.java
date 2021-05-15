package com.s1cket.labs.client.service;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

@Service
public class EncryptionService {

    public byte[] encrypt(PublicKey publicKey, String text) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("ECIES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] input = text.getBytes(StandardCharsets.UTF_8);
        byte[] output = new byte[0];
        cipher.update(input);

        try {
            output = cipher.doFinal();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return output;
    }

    public String decrypt(PrivateKey privateKey, byte[] text) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("ECIES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] output = new byte[0];
        cipher.update(text);

        try {
            output = cipher.doFinal();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        String decText = "";

        try {
            decText = new String(output, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return decText;
    }
}

