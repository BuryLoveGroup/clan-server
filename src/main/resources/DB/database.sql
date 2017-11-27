--users--
CREATE TABLE users(
uid SERIAL PRIMARY KEY ,
clan_id SERIAL NOT NULL ,
name VARCHAR(16) NOT NULL ,
phone VARCHAR(11) NOT NULL ,
password VARCHAR(64) not NULL ,
sex SMALLINT NOT NULL ,
area VARCHAR(16) NOT NULL ,
avatar text NOT NULL ,
autograph VARCHAR(64) ,
grade SMALLINT DEFAULT 0 ,
birthday DATE DEFAULT 1970-01-01 ,
regist_time TIMESTAMP NOT NULL DEFAULT now() ,
del_time TIMESTAMP ,
vip SMALLINT DEFAULT 0 ,
vip_expires TIMESTAMP ,
)

-- 设置自增长初始值 --
select setval('users_uid_seq',0,false);
select setval('users_clan_id_seq',10000,false);

-- modify --
CREATE TABLE modify_record(
type SMALLINT NOT NULL ,
modify_column VARCHAR(16) NOT NULL ,
old_value text NOT NULL ,
new_value text NOT NULL ,
);