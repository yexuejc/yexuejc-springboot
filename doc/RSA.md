#生成私钥
keytool -genkey -alias test -keyalg RSA -keystore test.keystore -keysize 1024 -validity 36500
```
输入密钥库口令:
再次输入新口令:
您的名字与姓氏是什么?
  [Unknown]:  1107047387@qq.com
您的组织单位名称是什么?
  [Unknown]:  yexuejc
您的组织名称是什么?
  [Unknown]:  成都极致思维网络科技有限公司
您所在的城市或区域名称是什么?
  [Unknown]:  成都
您所在的省/市/自治区名称是什么?
  [Unknown]:  四川
该单位的双字母国家/地区代码是什么?
  [Unknown]:  CN
CN=1107047387@qq.com, OU=yexuejc, O=成都极致思维网络科技有限公司, L=成都, ST=四川, C=CN是否正确?
  [否]:  是
```
#生成公钥
keytool -export -alias test -keystore test.keystore -storepass test2018 -rfc -file test2018.cer