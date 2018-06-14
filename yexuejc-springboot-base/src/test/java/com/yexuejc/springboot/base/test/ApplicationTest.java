package com.yexuejc.springboot.base.test;

import com.yexuejc.base.encrypt.RSA;
import com.yexuejc.base.encrypt.RSA2;
import com.yexuejc.base.pojo.ParamsPO;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.ApplicationRun;
import com.yexuejc.springboot.base.filter.RsaProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
public class ApplicationTest {
    @Autowired
    RsaProperties properties;

    /**
     * 客户端加密
     *
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void contextLoads() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Map map = new HashMap();
        map.put("page", 5);
        map.put("size", 16);
        map.put("content", "定制榻榻米垫竹编客厅茶几垫卧室地毯竹地毯飘窗垫日式榻榻米地毯");
        map.put("content2", map.get("content"));
        map.put("content3", map.get("content"));
        map.put("content4", map.get("content"));
        map.put("content5", map.get("content"));
        map.put("content6", map.get("content"));

        long t = System.currentTimeMillis();
        String datas = StrUtil.getSignContent(map);
        System.out.println(datas);
        ParamsPO params = new ParamsPO();
        params.setSign(StrUtil.toMD5(datas));
        params.setData(RSA.publicEncrypt(datas, RSA.getPublicKey(properties.getPublicKey())));
        System.out.println("耗时" + (System.currentTimeMillis() - t));
        System.out.println(JsonUtil.obj2Json(params));

        //{"datas":"GfobmjZv8naQoy6Cy1DVVPQTmXoSkAXKdv8XvxBFCDYcY2khmpOQyiM8Jc5DEeBEoGC_JwyG_f89jRhcHRy4WQ",
        // "sign":"d46b089cdea6ddbe3a747a27454ae090"}
    }

    /**
     * 客户端解密
     *
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void t2() throws InvalidKeySpecException, NoSuchAlgorithmException {
//        String strData = "KrlXChF8LE94EEnycvbi8AygpaZiHKaXH_OmC5sGhGQlvYp1arNk6WW7yR7kAWMLugCS5TKf8FIiYXnyuI8vjA";
//        String strData = "MNqGKU5oYTXs77mnRgIkN_DkwBgNHUx3uiJ72QmTmyqkF9Rqf2m_q3mc4wTxLRjXLs8KaH_UExiXMhH0Xm63gA";
//        String strData = "HgTMgxEEFSr6zRLvZQ3U5CAKjJqwKf0lfruZTi32iWObkbJA5mHuOTKU4rXYkej4UsPfArYUA45GfxatwFoB4Q";
        String strData = "K9Zyg82WDvIApFmXTxPwjQw_VA041jfBcxMIP6jpMM6xWe1XajGf3__7DqSLrS9MwCra9cYkidcjVJAKZn9cmQ";


        String mapData = "cEPfMp7-rim76XFdbNDSIzDRXyHySpz0VOZP6HC8U-JPl-ZxNZKp6ethEQWWITcuUPzIhp4fHiGKNsHA7F6OxCxibpMLj5-ZsgJJvcczw8Liens5kYgciRF1UziR3LFy6vybN9H1CJnqXaddbl3t_41P-_1l5Ev7YYa8woWp7ulaRPeTCDjohEpmx2Vi6aPSrm3hjjmitkD9gb0O6vFDNnclyNhFepKV3oh93tNv50sEQQ_QSBUXSHUtCnhTiBX8VsRX3h58F2tie7bG8VSk-6KFuXI07OiqFZSNpcwDOuq-GfMlEfPL3pX-gYhoOORPNClRlQHwyfHXBJly3gRtNVpVksHWQjr1xutWgYfwRjQPHBHNZwfx4E0XoCTuz9qH1CzFmmz68i63GzCM286zJ-J26MkiTDO1zH4jhglo38tnzz9HLeDcbbCuJg1jzkvpFiWamM-6odWhtCg65BS1tJJVWg023kWygZMu5Ebrm5WBbbatN87_K5zn211tFpKwRq2oVjO_AfJRY90WlQGEIHnzZNz_cf8mAjlmilHDuNdjYlj3axTUqLfgLDVaIkasREnjMI7oe8oAtG2ju2aq-xSAQZ_U-7-rsyBpoy0jnwRmlyUxhXgIX0zrfBQNXEjzPtg-iJ14R5qz1iOAJL7NtQQeuYngGTj6msDlKGEd_MQTLAFDbpiVPwWX00jLT1Ll3_zhivpPCUAmC8Yz58khkqrqi4FdIxJTDkxd0PFOBH8DYicF7ls4UdOHT24mAKDwUF_TfZ32oiiKSzCD9MJB8GEXjzx7tDFok-HsdOjI6ZnSUJCOTj3wne2E6_a8Gq2_vp5CWyW12wthJbH79aa7JVfy5cx-cZmNid7oCe54KYclz1tdUgLPCQ1ajsEevbRJ_NBkTmY2wAmUpHODeocDaYt_2AwAU2DLiv2uZuaVszNSUy593Zrzxq5AaY-oWbEeD24SyEWJObJtz5knYzr4NxjZShcjx9ezwiwkRZMtLZpA_cCPFAK1nOrN8zHCOZquS17CCSLDySLvGbxNqYeBa_lGSq8cQuQo8yybd1WkuLKUjUiJecmH2XcZNTPCtdRe0eLlRtk5928AQGsQugwSig";


        //        String listData = "X65trOhRSpeaN-2qP0zhdYi2jeJDcrTz2JHkc6UFG17xAho-VO0fkD0cA8wxoxcqyTaulOiSzaidZ2VeIvKjuinlKT2r23kdMJxjJodOZojssxgGSxm5gnry2tq5X8dbP7n-jodvAvLE9Gtq7AaBQ36ZQBQ2RcFB3TiHKHGin0gfn6T6A80orYD7i-Bdc0rh_pBEdLwGt1wWY_C8QuxeBmMWh0jmLVfpa3ZZOXVc9I7wIxzc1taQ7f-8Om9SNfXc";
        String listData = "Sf_FO8YC9EUNTeM0n9EVuDzwvLz3DcxOuG4-5UZ9486lLHAx7IOuAhPgVHpQGsQiqJ7Y3fTaWFr6rRFPL12rVg";

        // 解密步骤
        strData = RSA.publicDecrypt(strData, RSA.getPublicKey(properties.getPublicKey()));
        mapData = RSA.publicDecrypt(mapData, RSA.getPublicKey(properties.getPublicKey()));
        listData = RSA.publicDecrypt(listData, RSA.getPublicKey(properties.getPublicKey()));
        System.out.println(strData);
        System.out.println(mapData);
        System.out.println(listData);

        //其他
        assertThat(StrUtil.toMD5(JsonUtil.json2Obj(strData, String.class))
//                ).isEqualTo("fc4ead323d52f2b1122d1a9634c865c6");
//                ).isEqualTo("c4ca4238a0b923820dcc509a6f75849b");
//                ).isEqualTo("b326b5062b2f0e69046810717534cb09");
        ).isEqualTo("c977050805d8d1ebaa1e03525cbaee15");

        //map
        assertThat(
                StrUtil.toMD5(
                        StrUtil.getSignContent(
                                JsonUtil.json2Obj(mapData, Map.class)
                        )
                )
        ).isEqualTo("f721e7b0d5415302f5fe7dc5beb2938a");


        //list
        assertThat(
                StrUtil.toMD5(
                        JsonUtil.obj2Json(
                                JsonUtil.json2Obj(listData, List.class)
                        )
                )
//        ).isEqualTo("2a8d8eccabadc897ad65c04940bd833b");
        ).isEqualTo("efe23825367fc9997a9667463e34753a");

    }


    /**
     * 证书操作
     *
     * @throws CertificateException
     * @throws IOException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    @Test
    public void file() throws CertificateException, IOException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, InvalidKeySpecException {
        String publicKey = this.getClass().getResource("/lgfishing.cer").getFile().toString();
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX9uSsfJeIDIPzxCtwwFh5vrIKar69i3DPUTDEiKPRdtmADa8Ls6KAsPVpzgtQYoYVpYBoMbBtp0cGRoQULO83NWIAhmsU2cvd0nmGlB2xPRz+uGYW1rsYyCM8RSvfAwCVNsJD10A9voLhRQuYHPIDmay1sBA/casvEvYwMqDZtQIDAQAB";
        String privateKey = this.getClass().getResource("/lgfishing.keystore").getFile().toString();

        String privatePwd = "lgfishing2018";
        String privateAlias = "lgfishing";

//        String dataStr = "{\"ret\":\"0\",\"expireTime\":\"2015/10/28 23:59:59\",\"rettxt\":\"OK\",\"token\":\"69296128A59798E2D423D3B1A9F766F4\"}";
        String dataStr = "{\"foodsCode\":\"49\",\"latlng\":\"22.5,114.0\",\"pageIndex\":1,\"pageSize\":10}";



/***************************************************************************************************************************************************************************************************************************************/
        //客户端公钥加密
//        String publicEncryptResult = RSA.publicEncrypt(dataStr, RSA2.getPublicKey(publicKey));
//        System.out.println(publicEncryptResult);
//        System.out.println(StrUtil.toMD5(dataStr));

        String  publicEncryptResult="MxyCtlTnkvh+0vOPsDDL1+hO9NZ+pDps+uVt8NwJvn4SZoYfjuj2a1WYZrvDk/sUC41zRQAE85/c\ndm9IC0BtFr7CtHSnbJfExSBwHtkG3/pE4hd5ysrdQiaFmlvENJ24cVYX+4WBEZ6bfh9jB3e1QXQi\n05o+uwxOX1UW6VENEx0\u003d\n";

        //服务器私钥解密
        String privateDecryptResult = RSA.privateDecrypt(publicEncryptResult, RSA2.getPrivateKey(privateKey, privateAlias, privatePwd));
        System.out.println(privateDecryptResult);
/***************************************************************************************************************************************************************************************************************************************/
//        //服务器端私钥加密
//        String privateEncryptResult = RSA.privateEncrypt(dataStr, RSA2.getPrivateKey(privateKey, privateAlias, privatePwd));
//        System.out.println(privateEncryptResult);
//
//        //客户端公钥解密
//        String publicDecryptResult = RSA.publicDecrypt(privateEncryptResult, RSA2.getPublicKey(publicKey));
//        System.out.println(publicDecryptResult);
//
//        System.out.println(Base64.getEncoder().encodeToString(RSA2.getPublicKey(publicKey).getEncoded()));

    }


    /**
     * key加解密
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    @Test
    public void a() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX9uSsfJeIDIPzxCtwwFh5vrIKar69i3DPUTDEiKPRdtmADa8Ls6KAsPVpzgtQYoYVpYBoMbBtp0cGRoQULO83NWIAhmsU2cvd0nmGlB2xPRz+uGYW1rsYyCM8RSvfAwCVNsJD10A9voLhRQuYHPIDmay1sBA/casvEvYwMqDZtQIDAQAB";
        System.out.println(publicKey.length());


//        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAiSo5blJ9-QJ0_QElcy5AaRTq-3oO4lJ8PvIOIt-Xr5SUFODVj3DUbiy6_0bxQYO3NiYHlXPb37UVV3jjlXJsXwIDAQABAkBE0WOJH2hGs93gRl_0vwLf9ffDfkTTdlER_73p70aad3QZRslEkinQH7G5aE_DgBm5m72TCeH-PD2FZ2lwtavBAiEAvnRown5Lpqbl0tN_OUxr_e1u9d_-8dNL_JEETO7BZCECIQC4XtY-18j0bVVLxaXPjKQ00D59yntwObihDNyRK0nAfwIgHPHEGgrnpGQo-Wl7JFIg925mNqfcLxRVsAS6CpcefQECIQCUsLdsmy6QIhTmNRJSXoSXq1KatE_05DhIekzwLs8eFQIgfMawMiu52ZxBI5_pZ7ancQZ6Dsxl45utFqJShzV1pio";
////        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIkqOW5SffkCdP0BJXMuQGkU6vt6DuJSfD7yDiLfl6-UlBTg1Y9w1G4suv9G8UGDtzYmB5Vz29-1FVd445VybF8CAwEAAQ";
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX9uSsfJeIDIPzxCtwwFh5vrIKar69i3DPUTDEiKPRdtmADa8Ls6KAsPVpzgtQYoYVpYBoMbBtp0cGRoQULO83NWIAhmsU2cvd0nmGlB2xPRz+uGYW1rsYyCM8RSvfAwCVNsJD10A9voLhRQuYHPIDmay1sBA/casvEvYwMqDZtQIDAQAB";
//
//        String privatePwd = "lgfishing2018";
//        String privateAlias = "lgfishing";
//
//        String dataStr = "{\"ret\":\"0\",\"ExpireTime\":\"2015/10/28 23:59:59\",\"rettxt\":\"OK\",\"Token\":\"69296128A59798E2D423D3B1A9F766F4\"}'";
//
///***************************************************************************************************************************************************************************************************************************************/
//        //客户端公钥加密
//        String publicEncryptResult = RSA.publicEncrypt(dataStr, RSA.getPublicKey(publicKey));
//        System.out.println(publicEncryptResult);

        //服务器私钥解密
//        String privateDecryptResult = RSA.privateDecrypt(publicEncryptResult, RSA.getPrivateKey(privateKey));
//        System.out.println(privateDecryptResult);
/***************************************************************************************************************************************************************************************************************************************/
//        //服务器端私钥加密
//        String privateEncryptResult = RSA.privateEncrypt(dataStr, RSA.getPrivateKey(privateKey));
//        System.out.println(privateEncryptResult);
//
//        //客户端公钥解密
//        String publicDecryptResult = RSA.publicDecrypt(privateEncryptResult, RSA.getPublicKey(publicKey));
//        System.out.println(publicDecryptResult);
//
//        System.out.println(Base64.getEncoder().encodeToString(RSA.getPublicKey(publicKey).getEncoded()));

    }
}
