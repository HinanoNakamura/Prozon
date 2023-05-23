CREATE TABLE protain (
	id INT NOT NULL AUTO_INCREMENT    	COMMENT '商品コード',
	name VARCHAR(64)					COMMENT '商品名',
	flavorcode INT NOT NULL             COMMENT '味コード',
	purposecode INT NOT NULL            COMMENT '目的コード',
	componentcode INT NOT NULL          COMMENT '成分コード',
	price INT NOT NULL                  COMMENT '価格',
	pricecode INT NOT NULL              COMMENT '価格コード',
	img VARCHAR(500) NOT NULL           COMMENT '画像パス',
	detail VARCHAR(500) NOT NULL           COMMENT '商品詳細',
	PRIMARY KEY (id)
);

CREATE TABLE users (
	id VARCHAR(64) NOT NULL             COMMENT 'ユーザーID',
	password VARCHAR(16) NOT NULL       COMMENT 'パスワード',
	icon VARCHAR(500)                   COMMENT 'アイコン画像パス',
	mail VARCHAR(500) NOT NULL          COMMENT 'メールアドレス',
	homenumber VARCHAR(8) NOT NULL      COMMENT '郵便番号',
	homeaddress VARCHAR(500) NOT NULL   COMMENT '住所',
	PRIMARY KEY (id)
);

CREATE TABLE  products(
	Userid VARCHAR(64) NOT NULL             COMMENT 'ユーザーID',
	Proteinid INT NOT NULL                 	COMMENT 'プロテインID',
	PRIMARY KEY (Userid,Proteinid)
);