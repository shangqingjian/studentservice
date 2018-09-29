
create table if not exists tbl_student
(
	s_id INT(16) primary key auto_increment,
	s_number BIGINT(32) default 0,
	s_pwd VARCHAR(32) default "",
	s_name VARCHAR(32) default "",
	s_birthday BIGINT(32) default 0,
	s_age INT(32) default 0,
	s_sex VARCHAR(10) default "",
	s_telphone VARCHAR(32) default "",
	s_createby VARCHAR(32) default "",
	s_createtime BIGINT(32) default 0,
	s_modifiedby VARCHAR(32) default "",
	s_modifiedtime BIGINT(32) default 0,
	index (s_number)
)



