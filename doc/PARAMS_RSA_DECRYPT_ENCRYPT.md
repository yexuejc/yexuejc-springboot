# 加密解密功能介绍

####    [公、私钥生成](RSA.md)

#### 客户端解密（服务器端加密出参data）

>API返回值的data部分会用私钥加密<br/>
>1.先使用公钥解密得到解密字符串       <br/>
>2.1返回data为Map类型:先转成Map，然后ASCII码排序得到->result1=xxxxx&result2=xxxxx，再MD5的到sign         <br/>
>2.2返回data为List类型:先转成List，然后JSON化，再MD5的到sign         <br/>
>2.3返回data为字符串或基础类型:先转成String，再MD5的到sign         <br/>


>客户端使用公钥解密

正常api返回值
```json
{
    "code": "S",
    "data":"xxxxx",
    "msg": [
        "操作成功"
    ]
}
```

加密后api返回值
```json
{
    "code": "S",
    "data":"LZ6ylSuKj36Pd5_TGfHmmJUifKQq_BLLD_CRyw17Z6y9jfcjpK.....",
    "sign":"dee2f5af75b50f99b16726784230afeb",
    "msg": [
        "请求成功"
    ]
}
```

解析代码
```
    //map
    String mapData = "cEPfMp7-rim76XFdbNDSIzDRXyHySpz0VOZP6HC8U-JPl-ZxNZKp6ethEQWWITcuUPzIhp4fHiGKNsHA7F6OxCxibpMLj5-ZsgJJvcczw8Liens5kYgciRF1UziR3LFy6vybN9H1CJnqXaddbl3t_41P-_1l5Ev7YYa8woWp7ulaRPeTCDjohEpmx2Vi6aPSrm3hjjmitkD9gb0O6vFDNnclyNhFepKV3oh93tNv50sEQQ_QSBUXSHUtCnhTiBX8VsRX3h58F2tie7bG8VSk-6KFuXI07OiqFZSNpcwDOuq-GfMlEfPL3pX-gYhoOORPNClRlQHwyfHXBJly3gRtNVpVksHWQjr1xutWgYfwRjQPHBHNZwfx4E0XoCTuz9qH1CzFmmz68i63GzCM286zJ-J26MkiTDO1zH4jhglo38tnzz9HLeDcbbCuJg1jzkvpFiWamM-6odWhtCg65BS1tJJVWg023kWygZMu5Ebrm5WBbbatN87_K5zn211tFpKwRq2oVjO_AfJRY90WlQGEIHnzZNz_cf8mAjlmilHDuNdjYlj3axTUqLfgLDVaIkasREnjMI7oe8oAtG2ju2aq-xSAQZ_U-7-rsyBpoy0jnwRmlyUxhXgIX0zrfBQNXEjzPtg-iJ14R5qz1iOAJL7NtQQeuYngGTj6msDlKGEd_MQTLAFDbpiVPwWX00jLT1Ll3_zhivpPCUAmC8Yz58khkqrqi4FdIxJTDkxd0PFOBH8DYicF7ls4UdOHT24mAKDwUF_TfZ32oiiKSzCD9MJB8GEXjzx7tDFok-HsdOjI6ZnSUJCOTj3wne2E6_a8Gq2_vp5CWyW12wthJbH79aa7JVfy5cx-cZmNid7oCe54KYclz1tdUgLPCQ1ajsEevbRJ_NBkTmY2wAmUpHODeocDaYt_2AwAU2DLiv2uZuaVszNSUy593Zrzxq5AaY-oWbEeD24SyEWJObJtz5knYzr4NxjZShcjx9ezwiwkRZMtLZpA_cCPFAK1nOrN8zHCOZquS17CCSLDySLvGbxNqYeBa_lGSq8cQuQo8yybd1WkuLKUjUiJecmH2XcZNTPCtdRe0eLlRtk5928AQGsQugwSig";
          String sign = StrUtil.toMD5(
                        StrUtil.getSignContent(
                                JsonUtil.json2Obj(mapData, Map.class)
                        )
                )
    //list
    String listData = "Sf_FO8YC9EUNTeM0n9EVuDzwvLz3DcxOuG4-5UZ9486lLHAx7IOuAhPgVHpQGsQiqJ7Y3fTaWFr6rRFPL12rVg";
        String sign = StrUtil.toMD5(
                        JsonUtil.obj2Json(
                                JsonUtil.json2Obj(listData, List.class)
                        )
                )
    //其他
    String strData = "K9Zyg82WDvIApFmXTxPwjQw_VA041jfBcxMIP6jpMM6xWe1XajGf3__7DqSLrS9MwCra9cYkidcjVJAKZn9cmQ";
            strData = RSA.publicDecrypt(strData, RSA.getPublicKey(properties.getPublicKey()));
    String sign =StrUtil.toMD5(JsonUtil.json2Obj(strData, String.class);
 
```


#### 客户端加密 (服务器端解密入参data)

>1. 客户端先使用公钥加密,参数为Map类型          <br/>
>   1.1 参数先ASCII码排序得到新的参数->result1=xxxxx&result2=xxxxx，再MD5的到sign         <br/>
>   1.2 加密新的参数得到data（也可以对原map JSON化后加密）
>
>2. API接收到json参数，解析为ParamsPO           <br/>
>ParamsPO.data部分进行解密操作，解密后得到原始参数(result1=xxxxx&result2=xxxxx)做MD5校验

原始参数
```
Map map = new HashMap();
map.put("page", 5);
map.put("size", 16);
map.put("content", "定制榻榻米垫竹编客厅茶几垫卧室地毯竹地毯飘窗垫日式榻榻米地毯");

//对参数ASCII码排序
String data = StrUtil.getSignContent(map);
//封装请求参数
ParamsPO params = new ParamsPO();
params.setSign(StrUtil.toMD5(datas));
params.setData(RSA.publicEncrypt(datas, RSA.getPublicKey(properties.getPublicKey())));
```

加密后的请求参数
```json
{
	"data":"pe0V05nr5bfUp7c/JL1b/b6qJHipA5Qx8vM8BRryu3k=",
	"sign":"dee2f5af75b50f99b16726784230afeb"
}
```


### 配置
1. 服务器配置私钥
```
#配置密钥方式
yexuejc.http.encrypt.private-key=私钥
#配置证书方式：方式二选一，两者都配置会选择配置密钥方式
yexuejc.http.encrypt.private-key-path=/lgfishing.keystore
yexuejc.http.encrypt.private-alias=lgfishing
yexuejc.http.encrypt.private-pwd=lgfishing2018

yexuejc.http.encrypt.encrypt=true //加密：默认false
yexuejc.http.encrypt.decrypt=true //解密：默认false
```

2.客户端请求
```
request:POST
header:
X-User-Agent:token
Content-Type:application/json
body:
{
"data":"pe0V05nr5bfUp7c/JL1b/b6qJHipA5Qx8vM8BRryu3k=",
"sign":"123456789"
}
```