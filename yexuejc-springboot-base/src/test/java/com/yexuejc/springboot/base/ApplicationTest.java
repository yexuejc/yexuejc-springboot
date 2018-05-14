package com.yexuejc.springboot.base;

import com.yexuejc.base.pojo.ParamsPO;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.filter.RsaProperties;
import com.yexuejc.springboot.base.util.RSA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
public class ApplicationTest {
    @Autowired
    RsaProperties properties;

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

}
