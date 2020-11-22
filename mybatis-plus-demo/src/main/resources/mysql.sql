DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  user_id int(11) NOT NULL COMMENT '用户id',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS t_goods;
CREATE TABLE t_goods
(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  goods_id int(11) NOT NULL COMMENT '商品ID',
	goods_name VARCHAR(50) DEFAULT NULL COMMENT '商品名称',
	barcode VARCHAR(20) DEFAULT NULL COMMENT '条码',
  price DOUBLE(10,2) DEFAULT NULL COMMENT '价格',
	create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (id),
  KEY idx(goods_id)
);

DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order
(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  user_id int(11) NOT NULL COMMENT '用户id',
  goods_id int(11) NOT NULL COMMENT '商品ID',
	deal_num int(11) NOT NULL COMMENT '成交数量',
	create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (id),
  KEY idx_id(user_id,goods_id)
);
