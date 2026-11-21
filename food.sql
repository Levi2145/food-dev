-- Food 电商项目数据库（MySQL 8 / Navicat 导入版）
CREATE DATABASE IF NOT EXISTS food DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE food;
CREATE TABLE IF NOT EXISTS users (id varchar(64) primary key, username varchar(32) not null unique, password varchar(128) not null, nickname varchar(32), face varchar(255), mobile varchar(20), email varchar(64), sex tinyint default 2, created_time datetime default current_timestamp, updated_time datetime default current_timestamp on update current_timestamp);
CREATE TABLE IF NOT EXISTS category (id int primary key, name varchar(32) not null, type tinyint not null, father_id int default 0, logo varchar(255), sod int default 1);
CREATE TABLE IF NOT EXISTS carousel (id int primary key, image_url varchar(255), background_color varchar(32), item_id varchar(64), is_show tinyint default 1, sort int default 1);
CREATE TABLE IF NOT EXISTS items (id varchar(64) primary key, item_name varchar(64) not null, cat_id int, root_cat_id int, sell_counts int default 0, price decimal(10,2) default 0, main_img varchar(255), content text, created_time datetime default current_timestamp);
CREATE TABLE IF NOT EXISTS items_img (id varchar(64) primary key, item_id varchar(64), url varchar(255), sort int default 1, is_main tinyint default 0);
CREATE TABLE IF NOT EXISTS items_spec (id varchar(64) primary key, item_id varchar(64), name varchar(64), stock int default 0, discounts decimal(10,2) default 0, price_discount decimal(10,2) default 0);
CREATE TABLE IF NOT EXISTS user_address (id varchar(64) primary key, user_id varchar(64), receiver varchar(32), mobile varchar(20), province varchar(32), city varchar(32), district varchar(32), detail varchar(255), is_default tinyint default 0, created_time datetime default current_timestamp, updated_time datetime default current_timestamp on update current_timestamp);
CREATE TABLE IF NOT EXISTS orders (id varchar(64) primary key, order_id varchar(64) unique, user_id varchar(64), receiver_name varchar(32), receiver_mobile varchar(20), total_amount decimal(10,2), pay_method tinyint, status tinyint default 10, created_time datetime default current_timestamp, pay_time datetime);
CREATE TABLE IF NOT EXISTS order_items (id varchar(64) primary key, order_id varchar(64), item_id varchar(64), item_name varchar(64), item_spec_id varchar(64), item_spec_name varchar(64), price decimal(10,2), buy_counts int, total_amount decimal(10,2));
CREATE TABLE IF NOT EXISTS order_status (id varchar(64) primary key, order_id varchar(64), order_status tinyint, created_time datetime default current_timestamp, remark varchar(255));
CREATE TABLE IF NOT EXISTS items_comments (id varchar(64) primary key, user_id varchar(64), item_id varchar(64), order_id varchar(64), comment_level tinyint, content varchar(500), created_time datetime default current_timestamp);
INSERT IGNORE INTO category VALUES (1,'热销水果',1,0,'',1),(2,'新鲜蔬菜',1,0,'',2),(3,'苹果',2,1,'',1),(4,'叶菜',2,2,'',2);
INSERT IGNORE INTO users(id,username,password,nickname,mobile,email,sex) VALUES ('demo-user','demo','demo','演示用户','13800000000','demo@example.com',2);
INSERT IGNORE INTO items(id,item_name,cat_id,root_cat_id,sell_counts,price,main_img,content) VALUES ('demo-apple','红富士苹果',3,1,128,19.90,'/images/apple.jpg','脆甜多汁，新鲜采摘');
INSERT IGNORE INTO items_spec(id,item_id,name,stock,discounts,price_discount) VALUES ('spec-apple-1','demo-apple','2斤装',100,19.90,19.90);
