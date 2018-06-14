package com.yexuejc.springboot.base.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class CoverToPfx {
    public static final String PKCS12 = "PKCS12";
    public static final String JKS = "JKS";
    public static final String PFX_KEYSTORE_FILE = "D:/idea_work/yexuejc/yexuejc-springboot/yexuejc-springboot-base/src/test/resources/lgfishing.pfx";
    public static final String KEYSTORE_PASSWORD = "lgfishing2018";
    public static final String JKS_KEYSTORE_FILE = "D:/idea_work/yexuejc/yexuejc-springboot/yexuejc-springboot-base/src/test/resources/lgfishing.keystore";

    public static void coverTokeyStore() {
        try {
            KeyStore inputKeyStore = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(PFX_KEYSTORE_FILE );
            char[] nPassword = null;

            if (( KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("" )) {
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }

            inputKeyStore.load(fis, nPassword);
            fis.close();

            KeyStore outputKeyStore = KeyStore.getInstance("JKS");

            outputKeyStore.load( null, KEYSTORE_PASSWORD.toCharArray());

            Enumeration<String> enums = inputKeyStore.aliases();

            while (enums.hasMoreElements()) { // we are readin just one
                // certificate.

                String keyAlias = enums.nextElement();

                System. out.println( "alias=[" + keyAlias + "]");

                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, nPassword);
                    Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);

                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD.toCharArray(), certChain);
                }
            }

            FileOutputStream out = new FileOutputStream(JKS_KEYSTORE_FILE );

            outputKeyStore.store(out, nPassword);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void coverToPfx() {
        try {
            KeyStore inputKeyStore = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(JKS_KEYSTORE_FILE );
            char[] nPassword = null;

            if (( KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("" )) {
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }

            inputKeyStore.load(fis, nPassword);
            fis.close();

            KeyStore outputKeyStore = KeyStore.getInstance("PKCS12");

            outputKeyStore.load( null, KEYSTORE_PASSWORD.toCharArray());

            Enumeration<String> enums = inputKeyStore.aliases();

            while (enums.hasMoreElements()) { // we are readin just one
                // certificate.

                String keyAlias = enums.nextElement();

                System. out.println( "alias=[" + keyAlias + "]");

                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, nPassword);
                    Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);

                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD.toCharArray(), certChain);
                }
            }

            FileOutputStream out = new FileOutputStream(PFX_KEYSTORE_FILE );

            outputKeyStore.store(out, nPassword);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        coverToPfx();
    }
}