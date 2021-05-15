package com.s1cket.labs.client.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.*;
import java.util.Arrays;

@Service

public class KeyService {
    public KeyService() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public KeyPair generateKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
        SecureRandom secureRandom = new SecureRandom();

        try {
            keyPairGenerator.initialize(ecGenParameterSpec, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return keyPairGenerator.generateKeyPair();
    }

    public KeyPair createKeyPair(byte[] publicKeyBytes, byte[] privateKeyBytes) {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = null;
        try {
            privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = null;
        try {
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return new KeyPair(publicKey, privateKey);
    }

    public byte[] getAddress(PublicKey publicKey) {
        MessageDigest digest256 = null;
        try {
            digest256 = MessageDigest.getInstance("Keccak-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest256.digest(publicKey.getEncoded());
        return Arrays.copyOfRange(hash, hash.length - 20, hash.length); // right most 160 bits
    }
}
