#生成私钥
keytool -genkey -alias lgfishing -keyalg RSA -keystore lgfishing2.keystore -keysize 1024 -validity 36500
```
输入密钥库口令:
再次输入新口令:
您的名字与姓氏是什么?
  [Unknown]:  heikengdiaoyu.com
您的组织单位名称是什么?
  [Unknown]:  深圳金大米网络科技有限公司-老G钓鱼
您的组织名称是什么?
  [Unknown]:  成都极致思维网络科技有限公司-老G钓鱼
您所在的城市或区域名称是什么?
  [Unknown]:  成都
您所在的省/市/自治区名称是什么?
  [Unknown]:  四川
该单位的双字母国家/地区代码是什么?
  [Unknown]:  CN
CN=heikengdiaoyu.com, OU=深圳金大米网络科技有限公司-老G钓鱼, O=成都极致思维网络科技有限公司-老G钓鱼, L=成都, ST=四川, C=CN是否正确?
  [否]:  是
```
#生成公钥
keytool -export -alias lgfishing -keystore lgfishing2.keystore -storepass lgfishing2018 -rfc -file lgfishing2.cer