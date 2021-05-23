package com.s1cket.labs.client.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.*;
import java.util.Arrays;

@Service

public class KeyService {
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

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
        PrivateKey privateKey = createPrivateKey(privateKeyBytes);
        PublicKey publicKey = createPublicKey(publicKeyBytes);

        return new KeyPair(publicKey, privateKey);
    }

    public PrivateKey createPrivateKey(byte[] privateKeyBytes) {
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

        return privateKey;
    }

    public PublicKey createPublicKey(byte[] publicKeyBytes) {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = null;
        try {
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return publicKey;
    }

    public PrivateKey createPrivateKey(String privateKey) {
        return createPrivateKey(hexToBytes(privateKey));
    }

    public PublicKey createPublicKey(String publicKey) {
        return createPublicKey(hexToBytes(publicKey));
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
