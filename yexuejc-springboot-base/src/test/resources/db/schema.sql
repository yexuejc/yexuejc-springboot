CREATE TABLE consumer (
  consumer_id varchar(32)  NOT NULL DEFAULT NULL::character varying,
  mobile varchar(50)  NOT NULL DEFAULT NULL::character varying,
  pwd varchar(32)  DEFAULT NULL::character varying,
  is_enable bool DEFAULT true,
  is_non_expire bool DEFAULT true,
  is_non_lock bool DEFAULT true,
  wechat_id varchar(50)  DEFAULT NULL::character varying,
  qq_id varchar(50)  DEFAULT NULL::character varying,
  weibo_id varchar(50)  DEFAULT NULL::character varying,
  nickname varchar(50)  DEFAULT NULL::character varying,
  head varchar(255)  DEFAULT NULL::character varying,
  email varchar(32)  DEFAULT NULL::character varying,
  sex varchar(1)  DEFAULT NULL::character varying,
  roles varchar(255),
  pay_pwd varchar(32)  DEFAULT NULL::character varying,
  reg_type varchar(10) ,
  source_head varchar(255)
)
;
COMMENT ON COLUMN consumer.consumer_id IS '用户id';
COMMENT ON COLUMN consumer.mobile IS '手机号';
COMMENT ON COLUMN consumer.pwd IS '密码：md5';
COMMENT ON COLUMN consumer.is_enable IS '账号是否启用';
COMMENT ON COLUMN consumer.is_non_expire IS '账号是否没有过期';
COMMENT ON COLUMN consumer.is_non_lock IS '账号是否没有被锁定';
COMMENT ON COLUMN consumer.wechat_id IS '微信id';
COMMENT ON COLUMN consumer.qq_id IS 'qq id';
COMMENT ON COLUMN consumer.weibo_id IS '微博id';
COMMENT ON COLUMN consumer.nickname IS '昵称';
COMMENT ON COLUMN consumer.head IS '用户头像';
COMMENT ON COLUMN consumer.email IS '用户邮箱';
COMMENT ON COLUMN consumer.sex IS '用户姓别 ''男''，‘女’';
COMMENT ON COLUMN consumer.roles IS '角色、权限';
COMMENT ON COLUMN consumer.pay_pwd IS '支付密码';
COMMENT ON COLUMN consumer.reg_type IS '注册方式';
COMMENT ON COLUMN consumer.source_head IS '第三方源头像路径';

-- ----------------------------
-- Primary Key structure for table consumer
-- ----------------------------
ALTER TABLE consumer ADD CONSTRAINT consumer_pkey PRIMARY KEY (consumer_id);
