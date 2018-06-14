package com.yexuejc.springboot.base.test;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
/**
 * 生成公钥工具; http://bijian1013.iteye.com/blog/2339874
 * @ClassName: KeyStoreHelper
 * @Description:
 * @author: maxf
 * @date: 2018/6/13 20:50
 */
public class KeyStoreHelper {

    public static void main(String[] args) throws Exception {

        String privatePath = "D:/testPri.key"; // 准备导出的私钥
        String publicPath = "D:/testPub.key"; // 准备导出的公钥
        KeyStoreHelper k = new KeyStoreHelper();
        String privateKeyPath = k.getClass().getResource("/lgfishing.keystore").getFile().toString();
        String publicKeyPath = k.getClass().getResource("/lgfishing.cer").getFile().toString();
//        PrivateKey privateKey = getPrivateKeyFromStore(privateKeyPath);
//        createKeyFile(privateKey, privatePath);
        PublicKey publicKey = getPublicKeyFromCrt(publicKeyPath);
        createKeyFile(publicKey, publicPath);

        byte[] publicKeyBytes = publicKey.getEncoded();
//        byte[] privateKeyBytes = privateKey.getEncoded();

        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
//        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);

        System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
        System.out.println("publicKeyBase64:" + publicKeyBase64);

//        System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
//        System.out.println("privateKeyBase64:" + privateKeyBase64);
    }

    private static PrivateKey getPrivateKeyFromStore(String storePathP) throws Exception {
        String alias = "lgfishing"; // KeyTool中生成KeyStore时设置的alias
        String storeType = "PKCS12"; // KeyTool中生成KeyStore时设置的storetype
        char[] pw = "lgfishing2018".toCharArray(); // KeyTool中生成KeyStore时设置的storepass
        String storePath = storePathP; // KeyTool中已生成的KeyStore文件
        storeType = null == storeType ? KeyStore.getDefaultType() : storeType;
        KeyStore keyStore = KeyStore.getInstance(storeType);
        InputStream is = new FileInputStream(storePath);
        keyStore.load(is, pw);
        // 由密钥库获取密钥的两种方式
//         KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(pw));
//         return pkEntry.getPrivateKey();
        return (PrivateKey) keyStore.getKey(alias, pw);
    }

    private static PublicKey getPublicKeyFromCrt(String crtPathP) throws CertificateException, FileNotFoundException {
        String crtPath = crtPathP; // KeyTool中已生成的证书文件
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream(crtPath);
        Certificate crt = cf.generateCertificate(in);
        PublicKey publicKey = crt.getPublicKey();
        return publicKey;
    }

    private static void createKeyFile(Object key, String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(key);
        oos.flush();
        oos.close();
    }
}