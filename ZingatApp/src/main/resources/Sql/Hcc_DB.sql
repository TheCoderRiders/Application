-- ------------------------------------------------------------------------------------------------------------------------
-- Installation Mysql 5.7  (https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-14-04)
-- ------------------------------------------------------------------------------------------------------------------------

-- ----------------------------------------
-- Remove Old Mysql
-- ----------------------------------------

find / -name *mysql* 

sudo apt-get remove --purge mysql*
sudo apt-get autoremove               
sudo apt-get autoclean

sudo apt-get purge mysql-server mysql-client mysql-common mysql-server-core-5.5 mysql-client-core-5.5
sudo rm -rf /etc/mysql /var/lib/mysql
sudo apt-get autoremove
sudo apt-get autoclean

sudo apt-get remove --purge mysql*
sudo apt-get autoremove
sudo apt-get autoclean

sudo -i
service mysql stop
killall -KILL mysql mysqld_safe mysqld
apt-get --yes purge mysql-server mysql-client
apt-get --yes autoremove --purge
apt-get autoclean
deluser --remove-home mysql
delgroup mysql
rm -rf /etc/apparmor.d/abstractions/mysql /etc/apparmor.d/cache/usr.sbin.mysqld /etc/mysql /var/lib/mysql /var/log/mysql* /var/log/upstart/mysql.log* /var/run/mysqld
updatedb
exit
rm ~/.mysql_history
awk -F : '{ print($6 "/.mysql_history"); }' /etc/passwd | xargs -r -d '\n' -- sudo rm -f --
sudo find / -name .mysql_history -delete

find / -name *mysql* 

-- -------------------------------
-- Installed Mysql 5.7
-- -------------------------------

wget http://dev.mysql.com/get/mysql-apt-config_0.6.0-1_all.deb
sudo dpkg -i mysql-apt-config_0.6.0-1_all.deb
sudo apt-get update
sudo apt-get install mysql-server
sudo mysql_secure_installation

mysql --version

-- Login

mysql -uroot -pP@ssw0rd@123

-- -------------------------------
-- IF Rquired Installation Mysql 5.6
-- -------------------------------

sudo apt-get install mysql-client-core-5.6
sudo apt-get install mysql-server-core-5.6

-- Data Folder

/var/lib/mysql

-- Login

mysql -uroot -pP@ssw0rd@123

-- Search

sudo find / -name "*.cnf"
sudo find / -name "mysql-5.6"


-- If  Face Erroer

service mysql stop
vi /etc/mysql/my.cnf   -- and comment out "bind-address"

-- -------------------
-- Testing
-- -------------------

Drop Database hcc_reportmaster;
create database hcc_reportmaster DEFAULT CHARSET=utf8;
use hcc_reportmaster;

CREATE TABLE t1 (name varchar(50),jdoc JSON);

INSERT INTO t1 VALUES('Abhijit','{"age": "37", "bday": "Monday"}');

-- Map/Array (Key:Value)

select * from t1;
+---------+---------------------------------+
| name    | jdoc                            |
+---------+---------------------------------+
| Abhijit | {"age": "37", "bday": "Monday"} |
+---------+---------------------------------+
1 row in set (0.00 sec)

-- List 

INSERT INTO t1 VALUES('Abhijit','["a1","a2"]');
mysql> select * from t1;
+---------+------------------+
| name    | jdoc             |
+---------+------------------+
| Abhijit | {"bdat": "xxxx"} |
| Abhijit | ["a1", "a2"]     |
+---------+------------------+
2 rows in set (0.00 sec)


-- -------------------
-- DB Structure
-- -------------------

SET FOREIGN_KEY_CHECKS = 0; 

DROP TABLE `client_master` ;
CREATE TABLE client_master (
   `client_id` int(11) NOT NULL AUTO_INCREMENT ,
   `client_name` text DEFAULT NULL,
   `client_short_name` varchar(50) DEFAULT NULL,
   `client_address_1` varchar(50) DEFAULT NULL,
   `client_address_2` varchar(50) DEFAULT NULL,
   `client_city` varchar(50) DEFAULT NULL,
   `client_state` varchar(50) DEFAULT NULL,
   `client_zipcode` varchar(15) DEFAULT NULL,
   `client_country` varchar(30) DEFAULT NULL,
   `client_timezone` varchar(30) DEFAULT NULL,
   `client_primary_phone_number` varchar(20) DEFAULT NULL,
   `client_secondary_phone_number` varchar(20) DEFAULT NULL,
   `client_email_id` varchar(50) DEFAULT NULL,
   `client_alternate_email_id` varchar(50) DEFAULT NULL,
   `client_fax_number` varchar(20) DEFAULT NULL,
   `client_pager_number` varchar(20) DEFAULT NULL,
   PRIMARY KEY (`client_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

INSERT INTO client_master (client_name,client_short_name) VALUES ('Sun Gard System','SGS');

DROP TABLE `role_master` ;
CREATE TABLE role_master (
    role_id int(11) NOT NULL AUTO_INCREMENT ,
    role_name varchar(50) DEFAULT NULL,
   PRIMARY KEY (`role_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

INSERT INTO role_master (role_id,role_name) VALUES (1,'Allocater');
INSERT INTO role_master (role_id,role_name) VALUES (2,'TL_Allocater');
INSERT INTO role_master (role_id,role_name) VALUES (3,'TL_Allocater_Coder');
INSERT INTO role_master (role_id,role_name) VALUES (4,'Coder');
INSERT INTO role_master (role_id,role_name) VALUES (5,'Admin');


DROP TABLE `user_master` ;
CREATE TABLE `user_master` (
   `user_id` int(11) NOT NULL AUTO_INCREMENT,
   `username` varchar(50) DEFAULT NULL,
   `first_name` varchar(65) DEFAULT NULL,
   `middle_name` varchar(65) DEFAULT NULL,
   `last_name` varchar(65) DEFAULT NULL,
   `gender` varchar(10) DEFAULT NULL,
   `date_of_birth` date DEFAULT NULL,
   `credential` varchar(10) DEFAULT NULL,
   `email_id` varchar(50) DEFAULT NULL,
   `alternate_email_id` varchar(50) DEFAULT NULL,
   `address_1` varchar(50) DEFAULT NULL,
   `address_2` varchar(50) DEFAULT NULL,
   `city` varchar(50) DEFAULT NULL,
   `state` varchar(50) DEFAULT NULL,
   `zipcode` varchar(15) DEFAULT NULL,
   `country` varchar(30) DEFAULT NULL,
   `timezone` varchar(30) DEFAULT NULL,
   `primary_phone_number` varchar(20) DEFAULT NULL,
   `secondary_phone_number` varchar(20) DEFAULT NULL,
   `fax_number` varchar(20) DEFAULT NULL,
   `pager_number` varchar(20) DEFAULT NULL,
   `client_id` int(11) NOT NULL,
   `client_name` varchar(50) DEFAULT NULL,
   `role_id` int(11) NOT NULL,
   `role_name` varchar(50) DEFAULT NULL,
   `active_flag` int(11) DEFAULT '1',
   `delete_flag` int(11) DEFAULT '0',
   `updated_datetime` datetime DEFAULT NULL,
   `user_log_description` varchar(1000) DEFAULT NULL,
   PRIMARY KEY (`user_id`),
   UNIQUE KEY `username` (`username`),
   FOREIGN KEY (role_id) REFERENCES role_master(role_id),
   FOREIGN KEY (client_id) REFERENCES client_master(client_id)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


INSERT INTO user_master (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(1,'abhi_allocater','Abhijit','B.','Chikhalikar','M','1988-11-05','abhijit.chikhalikar9028@gmail.com','Pune','MH','412105','9028553747',1,'SGS',1,'Allocater',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(2,'abhi_tl_allocater','Abhi','B.','Chikar','M','1988-11-05','chikhalikarabhijit@gmail.com','Pune','MH','412105','9028553747',1,'SGS',2,'TL_Allocater',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(3,'abhi_tl_allo_coder','@bhi','B.','Chikr','M','1988-11-05','chikhalikarabhijit@gmail.com','Pune','MH','412105','9028553747',1,'SGS',3,'TL_Allocater_Coder',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(4,'abhi_coder','@b#i','B.','Chikarrr','M','1988-11-05','chikhalikarabhijit@gmail.com','Pune','MH','412105','9028553747',1,'SGS',4,'Coder',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(5,'abhi_admin','Abhijit','B.','Chikarrr','M','1988-11-05','abhijit.chikhalikar9028@gmail.com','Pune','MH','412105','9028553747',1,'SGS',5,'Admin',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(6,'abhi_rule_admin','Abhijit','B.','Chikarrr','M','1988-11-05','abhijit.chikhalikar9028@gmail.com','Pune','MH','412105','9028553747',1,'SGS',5,'Admin',1,0,'2016-06-07','New User Adding');

DROP TABLE `user_master_log` ;
CREATE TABLE `user_master_log` (
   `user_id` int(11) NOT NULL AUTO_INCREMENT,
   `username` varchar(50) DEFAULT NULL,   
   `first_name` varchar(65) DEFAULT NULL,
   `middle_name` varchar(65) DEFAULT NULL,
   `last_name` varchar(65) DEFAULT NULL,
   `gender` varchar(10) DEFAULT NULL,
   `date_of_birth` date DEFAULT NULL,
   `credential` varchar(10) DEFAULT NULL,
   `email_id` varchar(50) DEFAULT NULL,
   `alternate_email_id` varchar(50) DEFAULT NULL,
   `address_1` varchar(50) DEFAULT NULL,
   `address_2` varchar(50) DEFAULT NULL,
   `city` varchar(50) DEFAULT NULL,
   `state` varchar(50) DEFAULT NULL,
   `zipcode` varchar(15) DEFAULT NULL,
   `country` varchar(30) DEFAULT NULL,
   `timezone` varchar(30) DEFAULT NULL,
   `primary_phone_number` varchar(20) DEFAULT NULL,
   `secondary_phone_number` varchar(20) DEFAULT NULL,
   `fax_number` varchar(20) DEFAULT NULL,
   `pager_number` varchar(20) DEFAULT NULL,
   `client_id` int(11) NOT NULL,
   `client_name` varchar(50) DEFAULT NULL,
   `role_id` int(11) NOT NULL,
   `role_name` varchar(50) DEFAULT NULL,
   `active_flag` int(11) DEFAULT '1',
   `delete_flag` int(11) DEFAULT '0',
   `updated_datetime` datetime NOT NULL,
   `user_log_description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`updated_datetime`),
  FOREIGN KEY (role_id) REFERENCES role_master(role_id),
  FOREIGN KEY (client_id) REFERENCES client_master(client_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;


INSERT INTO user_master_log (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(1,'abhi_allocater','Abhijit','B.','Chikhalikar','M','1988-11-05','abhijit.chikhalikar9028@gmail.com','Pune','MH','412105','9028553747',1,'SGS',1,'Allocater',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master_log (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(2,'abhi_tl_allocater','Abhi','B.','Chikar','M','1988-11-05','chikhalikarabhijit@gmail.com','Pune','MH','412105','9028553747',1,'SGS',2,'TL_Allocater',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master_log (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(3,'abhi_tl_allo_coder','@bhi','B.','Chikr','M','1988-11-05','chikhalikarabhijit@gmail.com','Pune','MH','412105','9028553747',1,'SGS',3,'TL_Allocater_Coder',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master_log (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(4,'abhi_coder','@b#i','B.','Chikarrr','M','1988-11-05','chikhalikarabhijit@gmail.com','Pune','MH','412105','9028553747',1,'SGS',4,'Coder',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master_log (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(5,'abhi_admin','Abhijit','B.','Chikarrr','M','1988-11-05','abhijit.chikhalikar9028@gmail.com','Pune','MH','412105','9028553747',1,'SGS',5,'Admin',1,0,'2016-06-07','New User Adding');

INSERT INTO user_master_log (user_id,username,first_name,middle_name,last_name,gender,date_of_birth,email_id,city,state,zipcode,primary_phone_number,client_id,client_name,role_id,role_name,active_flag,delete_flag,updated_datetime,user_log_description) VALUES
(6,'abhi_rule_admin','Abhijit','B.','Chikarrr','M','1988-11-05','abhijit.chikhalikar9028@gmail.com','Pune','MH','412105','9028553747',1,'SGS',5,'Admin',1,0,'2016-06-07','New User Adding');

DROP TABLE `user_authentication` ;
CREATE TABLE `user_authentication` 
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_name` varchar(250) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `active_flag` int(11) DEFAULT '1',
  `delete_flag` int(11) DEFAULT '0',
  `password_expiry_date` date DEFAULT NULL,
  `password_reset_flag` tinyint(1) NOT NULL DEFAULT '1',
  `max_count_lock` tinyint(2) unsigned NOT NULL DEFAULT '5',
  `lock_expiration_time_in_min` smallint(5) unsigned NOT NULL DEFAULT '10',
  `last_login_attempt_datetime` datetime DEFAULT NULL,
  `password_expire_days` smallint(5) unsigned NOT NULL DEFAULT '90',
  `failed_count` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `user_state` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES user_master(user_id),
  FOREIGN KEY (role_id) REFERENCES role_master(role_id),
  FOREIGN KEY (client_id) REFERENCES client_master(client_id)  
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

INSERT INTO user_authentication (id,user_id,username,password,client_id,client_name,email_id,role_id,role_name,active_flag,delete_flag,password_expiry_date,password_reset_flag,user_state) VALUES
(1,1,'abhi_allocater',password('P@ssw0rd@123'),1,'SGS','abhijit.chikhalikar9028@gmail.com',1,'Allocater',1,0,'2016-12-31',0,'Active');

INSERT INTO user_authentication (id,user_id,username,password,client_id,client_name,email_id,role_id,role_name,active_flag,delete_flag,password_expiry_date,password_reset_flag,user_state) VALUES
(2,2,'abhi_tl_allocater',password('P@ssw0rd@123'),1,'SGS','chikhalikarabhijit@gmail.com',2,'TL_Allocater',1,0,'2016-12-31',0,'Active');

INSERT INTO user_authentication (id,user_id,username,password,client_id,client_name,email_id,role_id,role_name,active_flag,delete_flag,password_expiry_date,password_reset_flag,user_state) VALUES
(3,3,'abhi_tl_allo_coder',password('P@ssw0rd@123'),1,'SGS','chikhalikarabhijit@gmail.com',3,'TL_Allocater_Coder',1,0,'2016-12-31',0,'Active');

INSERT INTO user_authentication (id,user_id,username,password,client_id,client_name,email_id,role_id,role_name,active_flag,delete_flag,password_expiry_date,password_reset_flag,user_state) VALUES
(4,4,'abhi_coder',password('P@ssw0rd@123'),1,'SGS','chikhalikarabhijit@gmail.com',4,'Coder',1,0,'2016-12-31',0,'Active');

INSERT INTO user_authentication (id,user_id,username,password,client_id,client_name,email_id,role_id,role_name,active_flag,delete_flag,password_expiry_date,password_reset_flag,user_state) VALUES
(5,5,'abhi_admin',password('P@ssw0rd@123'),1,'SGS','abhijit.chikhalikar9028@gmail.com',5,'Admin',1,0,'2016-12-31',0,'Active');

INSERT INTO user_authentication (id,user_id,username,password,client_id,client_name,email_id,role_id,role_name,active_flag,delete_flag,password_expiry_date,password_reset_flag,user_state) VALUES
(6,6,'abhi_rule_admin',password('P@ssw0rd@123'),1,'SGS','abhijit.chikhalikar9028@gmail.com',5,'Admin',1,0,'2016-12-31',0,'Active');


DROP TABLE `event_log` ;
CREATE TABLE `event_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `action_type` varchar(50) DEFAULT NULL,
  `execution_date` datetime NOT NULL,
  `completion_date` datetime DEFAULT NULL,
  `session_id` varchar(1000) NOT NULL DEFAULT '',
  `ip_address` varchar(15) NOT NULL DEFAULT '',
  `is_active` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES user_master(user_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;

INSERT INTO event_log (id,user_id,action_type,execution_date,session_id,ip_address,is_active) VALUES
(1,1,'Login','2016-06-07 03:14:07','asdafad15478','192.168.17.88','1');

INSERT INTO event_log (id,user_id,action_type,completion_date,session_id,ip_address,is_active,execution_date) VALUES
(2,2,'Logout','2016-06-07 05:14:07','asdafad154','192.168.17.88','0','2016-06-07 05:14:07');


DROP TABLE `country_state_map` ;
CREATE TABLE `country_state_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(50) DEFAULT NULL,
  `state_name` varchar(50) DEFAULT NULL,
  `city_name` varchar(50) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `state_name` (`state_name`,`city_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


DROP TABLE `roles_default_settings` ;
CREATE TABLE `roles_default_settings` ( 
    id int(11) NOT NULL AUTO_INCREMENT,
    role_id int(11),
    role_name text,
    document_allocation int(11) DEFAULT '0',
    edit_document int(11) DEFAULT '0',
    reject_document int(11) DEFAULT '0',
    view_document int(11) DEFAULT '0',
    draft_document int(11) DEFAULT '0',
    complete_confirmation_message int(11) DEFAULT '0',
    draft_confirmation_message int(11) DEFAULT '0',
    continue_the_samebucket int(11) DEFAULT '0',
    view_report int(11) DEFAULT '0',
    add_user int(11) DEFAULT '0',
    delete_user int(11) DEFAULT '0',
    modified_user int(11) DEFAULT '0',
    add_rule int(11) DEFAULT '0',
    delete_rule int(11) DEFAULT '0',
    modified_rule int(11) DEFAULT '0',
    client_id int(11),
    client_name text,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role_master(role_id),
    FOREIGN KEY (client_id) REFERENCES client_master(client_id)
);
    
INSERT INTO roles_default_settings 
(id,role_id,role_name,document_allocation,view_document,view_report,client_id,client_name) VALUES
(1,1,'Allocater',1,1,1,1,'SGS');
   
INSERT INTO roles_default_settings 
(id,role_id,role_name,document_allocation,view_document,view_report,client_id,client_name) VALUES
(2,2,'TL_Allocater',1,1,1,1,'SGS');
 
INSERT INTO roles_default_settings 
(id,role_id,role_name,document_allocation,view_document,edit_document,reject_document,draft_document,complete_confirmation_message,draft_confirmation_message,continue_the_samebucket,view_report,client_id,client_name) VALUES
(3,3,'TL_Allocater_Coder',1,1,1,1,1,1,1,1,1,1,'SGS');

INSERT INTO roles_default_settings 
(id,role_id,role_name,edit_document,reject_document,draft_document,complete_confirmation_message,draft_confirmation_message,continue_the_samebucket,view_report,client_id,client_name) VALUES
(4,4,'Coder',1,1,1,1,1,1,1,1,'SGS');

INSERT INTO roles_default_settings 
(id,role_id,role_name,view_report,add_user,delete_user,modified_user,add_rule,delete_rule,modified_rule,client_id,client_name) VALUES
(5,5,'Admin',1,1,1,1,1,1,1,1,'SGS');


DROP TABLE `user_permitions` ;
CREATE TABLE `user_permitions` ( 
    id int(11) NOT NULL AUTO_INCREMENT,
    user_id int(11) DEFAULT NULL,
    username varchar(50) DEFAULT NULL,    
    role_id int(11),
    role_name text,
    document_allocation int(11) DEFAULT '0',
    edit_document int(11) DEFAULT '0',
    reject_document int(11) DEFAULT '0',
    view_document int(11) DEFAULT '0',
    draft_document int(11) DEFAULT '0',
    complete_confirmation_message int(11) DEFAULT '0',
    draft_confirmation_message int(11) DEFAULT '0',
    continue_the_samebucket int(11) DEFAULT '0',
    view_report int(11) DEFAULT '0',
    add_user int(11) DEFAULT '0',
    delete_user int(11) DEFAULT '0',
    modified_user int(11) DEFAULT '0',
    add_rule int(11) DEFAULT '0',
    delete_rule int(11) DEFAULT '0',
    modified_rule int(11) DEFAULT '0',
    client_id int(11),
    client_name text,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role_master(role_id),
    FOREIGN KEY (client_id) REFERENCES client_master(client_id),
    FOREIGN KEY (user_id) REFERENCES user_master(user_id)  
);

INSERT INTO user_permitions 
(id,user_id,username,role_id,role_name,document_allocation,view_document,view_report,client_id,client_name) VALUES
(1,1,'abhi_allocater',1,'Allocater',1,1,1,1,'SGS');

INSERT INTO user_permitions 
(id,user_id,username,role_id,role_name,document_allocation,view_document,view_report,client_id,client_name) VALUES
(2,2,'abhi_tl_allocater',2,'TL_Allocater',1,1,1,1,'SGS');
   
INSERT INTO user_permitions 
(id,user_id,username,role_id,role_name,document_allocation,view_document,edit_document,reject_document,draft_document,complete_confirmation_message,draft_confirmation_message,continue_the_samebucket,view_report,client_id,client_name) VALUES
(3,3,'abhi_tl_allo_coder',3,'TL_Allocater_Coder',1,1,1,1,1,1,1,1,1,1,'SGS');
   
INSERT INTO user_permitions 
(id,user_id,username,role_id,role_name,edit_document,reject_document,draft_document,complete_confirmation_message,draft_confirmation_message,continue_the_samebucket,view_report,client_id,client_name) VALUES
(4,4,'abhi_coder',4,'Coder',1,1,1,1,1,1,1,1,'SGS');

INSERT INTO user_permitions 
(id,user_id,username,role_id,role_name,view_report,add_user,delete_user,modified_user,client_id,client_name) VALUES
(5,5,'abhi_admin',1,'Admin',1,1,1,1,1,'SGS');

INSERT INTO user_permitions 
(id,user_id,username,role_id,role_name,add_rule,delete_rule,modified_rule,client_id,client_name) VALUES
(6,6,'abhi_rule_admin',1,'Admin',1,1,1,1,'SGS');


DROP TABLE `allocater_tl_map` ;
CREATE TABLE `allocater_tl_map` ( 
  `allocater_id` int(11) NOT NULL AUTO_INCREMENT,
  `allocater_firstname` varchar(50) DEFAULT NULL,
  `allocater_middlename` varchar(50) DEFAULT NULL,
  `allocater_lastname` varchar(50) DEFAULT NULL,
  `tl_id` int(11) NOT NULL DEFAULT '0',
  `tl_firstname` varchar(50) DEFAULT NULL,
  `tl_middlename` varchar(50) DEFAULT NULL,
  `tl_lastname` varchar(50) DEFAULT NULL,
  `allocater_client_id` int(11) DEFAULT NULL,
  `allocater_client_name` varchar(50) DEFAULT NULL,
  `tl_client_id` int(11) DEFAULT NULL,
  `tl_client_name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`allocater_id`,`tl_id`),
  FOREIGN KEY (tl_id) REFERENCES user_master(user_id),
  FOREIGN KEY (allocater_id) REFERENCES user_master(user_id),
  FOREIGN KEY (tl_client_id) REFERENCES client_master(client_id),
  FOREIGN KEY (allocater_client_id) REFERENCES client_master(client_id)    
);

INSERT INTO allocater_tl_map (allocater_id,allocater_firstname,allocater_lastname,tl_id,tl_firstname,tl_lastname,allocater_client_id,allocater_client_name,tl_client_id,tl_client_name) 
VALUES (1,'Abhijit','Chikhalikar',2,'Abhi','Chikar',1,'SGS',1,'SGS');
INSERT INTO allocater_tl_map (allocater_id,allocater_firstname,allocater_lastname,tl_id,tl_firstname,tl_lastname,allocater_client_id,allocater_client_name,tl_client_id,tl_client_name) 
VALUES (1,'Abhijit','Chikhalikar',3,'@bhi','Chikr',1,'SGS',1,'SGS');


DROP TABLE `tl_coder_map` ;
CREATE TABLE `tl_coder_map` ( 
  `tl_id` int(11) NOT NULL AUTO_INCREMENT,
  `tl_firstname` varchar(50) DEFAULT NULL,
  `tl_middlename` varchar(50) DEFAULT NULL,
  `tl_lastname` varchar(50) DEFAULT NULL,
  `coder_id` int(11) NOT NULL DEFAULT '0',
  `coder_firstname` varchar(50) DEFAULT NULL,
  `coder_middlename` varchar(50) DEFAULT NULL,
  `coder_lastname` varchar(50) DEFAULT NULL,
  `tl_client_id` int(11) DEFAULT NULL,
  `tl_client_name` varchar(50) DEFAULT NULL,
  `coder_client_id` int(11) DEFAULT NULL,
  `coder_client_name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`tl_id`,`coder_id`),
  FOREIGN KEY (tl_id) REFERENCES user_master(user_id),
  FOREIGN KEY (coder_id) REFERENCES user_master(user_id),
  FOREIGN KEY (tl_client_id) REFERENCES client_master(client_id),
  FOREIGN KEY (coder_client_id) REFERENCES client_master(client_id)    
);

INSERT INTO tl_coder_map (tl_id,tl_firstname,tl_lastname,coder_id,coder_firstname,coder_lastname,tl_client_id,tl_client_name,coder_client_id,coder_client_name) 
VALUES (2,'Abhi','Chikar',4,'@b#i','Chikarrrr',1,'SGS',1,'SGS');
INSERT INTO tl_coder_map (tl_id,tl_firstname,tl_lastname,coder_id,coder_firstname,coder_lastname,tl_client_id,tl_client_name,coder_client_id,coder_client_name) 
VALUES (3,'@bhi','Chikr',4,'@b#i','Chikarrrr',1,'SGS',1,'SGS');



DROP TABLE `status_master` ;
CREATE TABLE `status_master` ( 
    status_id int(11) NOT NULL AUTO_INCREMENT,
    status_value text,
    status_description text,
    PRIMARY KEY (status_id)
);

INSERT INTO status_master (status_id,status_value) VALUES (111,'Ready For Allocation');
INSERT INTO status_master (status_id,status_value) VALUES (222,'Rejected');
INSERT INTO status_master (status_id,status_value) VALUES (333,'Allocat to TL');
INSERT INTO status_master (status_id,status_value) VALUES (444,'Allocat to Coder');
INSERT INTO status_master (status_id,status_value) VALUES (555,'Draft');
INSERT INTO status_master (status_id,status_value) VALUES (666,'Coding Completed');
INSERT INTO status_master (status_id,status_value) VALUES (777,'Not Yet Suggested');
INSERT INTO status_master (status_id,status_value) VALUES (888,'Erroer Files');


DROP TABLE `bucket_master` ;
CREATE TABLE `bucket_master` ( 
    bucket_id int(11) NOT NULL AUTO_INCREMENT,
    bucket_value text,
    bucket_description text,
    PRIMARY KEY (bucket_id)   
);

INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (1,'New');
INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (2,'Allocated');
INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (3,'Draft');
INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (4,'Completed');
INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (5,'Ready For Allocation');
INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (6,'Not Yet Suggested');
INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (7,'Erroer Files');
INSERT INTO bucket_master (bucket_id,bucket_value) VALUES (8,'Rejected');



DROP TABLE `role_bucket_status_map` ;
CREATE TABLE `role_bucket_status_map` ( 
    id int(11) NOT NULL AUTO_INCREMENT,
    role_id int(11),
    role_name text,
    bucket_id Int(11) DEFAULT NULL,
    bucket_value varchar(50) DEFAULT NULL,
    status_id int(11) NOT NULL  ,
    status_css_class text,    
    status_value text,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role_master(role_id),
    FOREIGN KEY (bucket_id) REFERENCES bucket_master(bucket_id),  
    FOREIGN KEY (status_id) REFERENCES status_master(status_id)  
);
 
-- Allocater

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (1,1,'Allocater',5,'Ready For Allocation',111,'Ready For Allocation','readyForAllocationBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (2,1,'Allocater',6,'Not Yet Suggested',777,'Not Yet Suggested','rejectedBg');

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (3,1,'Allocater',7,'Erroer Files',888,'Erroer Files','rejectedBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (4,1,'Allocater',8,'Rejected',222,'Rejected','rejectedBg');

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (5,1,'Allocater',2,'Allocated',333,'Allocat to TL','allocateToTLBg');

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (6,1,'Allocater',2,'Allocated',444,'Allocat to Coder','allocateToCoderBg');

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (7,1,'Allocater',4,'Completed',666,'Coding Completed','codingCompletedBg');



-- TL_Allocater

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (16,2,'TL_Allocater',1,'New',333,'Allocat to TL','allocateToTLBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (8,2,'TL_Allocater',2,'Allocated',444,'Allocat to Coder','allocateToCoderBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (9,2,'TL_Allocater',4,'Completed',666,'Coding Completed','codingCompletedBg');

-- TL_Allocater_Coder

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (10,3,'TL_Allocater_Coder',1,'New',333,'Allocat to TL','allocateToTLBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (11,3,'TL_Allocater_Coder',3,'Draft',555,'Draft','draftBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (12,3,'TL_Allocater_Coder',4,'Completed',666,'Coding Completed','codingCompletedBg');

-- Coder

INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (13,4,'Coder',1,'New',444,'Allocat to Coder','allocateToTLBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (14,4,'Coder',3,'Draft',555,'Draft','draftBg');
INSERT INTO role_bucket_status_map (id,role_id,role_name,bucket_id,bucket_value,status_id,status_value,status_css_class) 
VALUES (15,4,'Coder',4,'Completed',666,'Coding Completed','codingCompletedBg');


DROP TABLE `rejection_reason_list` ;
CREATE TABLE `rejection_reason_list` ( 
    rejection_reason_list_id int(11) NOT NULL AUTO_INCREMENT,
    rejection_reason_list text,
    PRIMARY KEY (rejection_reason_list_id)
);
  
INSERT INTO rejection_reason_list (rejection_reason_list_id,rejection_reason_list) VALUES(1,'Text Not Clear.');
INSERT INTO rejection_reason_list (rejection_reason_list_id,rejection_reason_list) VALUES(2,'Codes Not Suggested Properly.');

DROP TABLE `sort_list_master` ;
CREATE TABLE `sort_list_master` ( 
    id int(11) NOT NULL AUTO_INCREMENT,
    sort_list varchar(50),
    default_flag varchar(1),
    sequence int,
    PRIMARY KEY (id)
);
  
INSERT INTO sort_list_master (id,sort_list,default_flag,sequence) VALUES (1,'Recived Date','Y',1);
INSERT INTO sort_list_master (id,sort_list,default_flag,sequence) VALUES (2,'Report Type','N',2);
INSERT INTO sort_list_master (id,sort_list,default_flag,sequence) VALUES (3,'Assigned Date','N',3);


DROP TABLE `role_bucket_rightside_map` ;
CREATE TABLE `role_bucket_rightside_map` ( 
    id int(11) NOT NULL AUTO_INCREMENT,
    role_id int(11) NOT NULL  ,
    role_name varchar(50) DEFAULT NULL,
    bucket_id Int(11) DEFAULT NULL,
    bucket_value varchar(50) DEFAULT NULL,
    rightside_column_key varchar(50) DEFAULT NULL  ,
    rightside_column_name varchar(50) DEFAULT NULL  ,
    rightside_column_sequence int(1) DEFAULT NULL  ,    
    PRIMARY KEY (id),
    FOREIGN KEY (bucket_id) REFERENCES bucket_master(bucket_id),  
    FOREIGN KEY (role_id) REFERENCES role_master(role_id)  
);

-- Allocater

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name) VALUES (1,'New',1,'Allocater');

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',1,'Allocater','File Assigned To : ','document_assigned_name',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',1,'Allocater','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',1,'Allocater','Work Started Date : ','document_start_datetime',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',1,'Allocater','File Current Status : ','document_current_status',4);

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',1,'Allocater','File Assigned To : ','document_assigned_name',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',1,'Allocater','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',1,'Allocater','Work Started Date : ','document_start_datetime',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',1,'Allocater','Work End Date : ','document_end_datetime',4);

-- TL_Allocater

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name) VALUES (1,'New',2,'TL_Allocater');

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',2,'TL_Allocater','File Assigned To : ','document_assigned_name',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',2,'TL_Allocater','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',2,'TL_Allocater','Work Started Date : ','document_start_datetime',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',2,'TL_Allocater','File Current Status : ','document_current_status',4);

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',2,'TL_Allocater','File Assigned To : ','document_assigned_name',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',2,'TL_Allocater','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',2,'TL_Allocater','Work Started Date : ','document_start_datetime',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',2,'TL_Allocater','Work End Date : ','document_end_datetime',4);

-- TL_Allocater_Coder

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (1,'New',3,'TL_Allocater_Coder','File Recived Date : ','document_recived_datetime',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (1,'New',3,'TL_Allocater_Coder','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (1,'New',3,'TL_Allocater_Coder','File Assigned By : ','document_assignee_name',3);

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',3,'TL_Allocater_Coder','File Assigned To : ','document_assigned_name',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',3,'TL_Allocater_Coder','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',3,'TL_Allocater_Coder','Work Started Date : ','document_start_datetime',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (2,'Allocated',3,'TL_Allocater_Coder','File Current Status : ','document_current_status',4);

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',3,'TL_Allocater_Coder','File Recived Date : ','document_recived_datetime',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',3,'TL_Allocater_Coder','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',3,'TL_Allocater_Coder','File Assigned By : ','document_assignee_name',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',3,'TL_Allocater_Coder','Work Started Date : ','document_start_datetime',4);

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',3,'TL_Allocater_Coder','File Recived Date : ','document_recived_datetime',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',3,'TL_Allocater_Coder','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',3,'TL_Allocater_Coder','File Assigned By : ','document_assignee_name',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',3,'TL_Allocater_Coder','Work Started Date : ','document_start_datetime',4);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',3,'TL_Allocater_Coder','Work End Date : ','document_end_datetime',5);

-- Coder

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (1,'New',4,'Coder','File Recived Date : ','document_recived_datetime',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (1,'New',4,'Coder','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (1,'New',4,'Coder','File Assigned By : ','document_assignee_name',3);

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',4,'Coder','File Recived Date : ','document_recived_datetime',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',4,'Coder','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',4,'Coder','File Assigned By : ','document_assignee_name',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (3,'Draft',4,'Coder','Work Started Date : ','document_start_datetime',4);

INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',4,'Coder','File Recived Date : ','document_recived_datetime',1);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',4,'Coder','File Assigned Date : ','document_assigned_datetime',2);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',4,'Coder','File Assigned By : ','document_assignee_name',3);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',4,'Coder','Work Started Date : ','document_start_datetime',4);
INSERT INTO role_bucket_rightside_map (bucket_id,bucket_value,role_id,role_name,rightside_column_key,rightside_column_name,rightside_column_sequence) 
VALUES (4,'Completed',4,'Coder','Work End Date : ','document_end_datetime',5);


DROP TABLE `document_master` ;
CREATE TABLE `document_master` ( 
    
    id int(11) NOT NULL AUTO_INCREMENT,
    document_id varchar(50) NOT NULL,
    document_name varchar(50) NOT NULL,
    document_path varchar(150) NOT NULL,

    document_rejection_comment varchar(100) DEFAULT NULL,
    document_rejection_reason varchar(50) DEFAULT NULL,

    document_current_status varchar(50) NOT NULL,
    document_current_status_id int(5) NOT NULL,

    document_recived_datetime datetime DEFAULT NULL,
    document_parsed_datetime datetime DEFAULT NULL,
    code_suggested_date datetime DEFAULT NULL,
    document_assigned_datetime datetime DEFAULT NULL,
    document_start_datetime datetime DEFAULT NULL ,
    document_rejected_datetime datetime DEFAULT NULL ,
    document_end_datetime datetime DEFAULT NULL,

    document_html_contents LONGTEXT,
    document_section_contents JSON,
    
    document_suggested_codes JSON,
    document_accepted_codes JSON,
    document_rejected_codes JSON,
    document_final_codes JSON,
    
    
    document_locked varchar(1) DEFAULT NULL,
    document_locked_by varchar(50) DEFAULT NULL,
    document_locked_by_id int(11) DEFAULT NULL,
    
    document_assigned_id varchar(50) DEFAULT NULL,
    document_assigned_name varchar(50) DEFAULT NULL,
    document_assignee_id varchar(50) NOT NULL,
    document_assignee_name varchar(50) NOT NULL,

    client_id int(11) DEFAULT NULL,
    client_name varchar(50) DEFAULT NULL,
    
    physician_name varchar(50) DEFAULT NULL,
    
    report_type varchar(50) DEFAULT NULL,
    code_suggested_flag varchar(1) DEFAULT 'N',

    updated_by varchar(50) DEFAULT NULL,
    updated_by_id int(11) DEFAULT NULL,
    updated_date datetime,
    PRIMARY KEY (id,document_id)
) ;

    Alter Table document_master ADD document_html_contents text;
    Alter Table document_master ADD document_section_contents JSON;
    
    Alter Table document_master CHANGE document_html_contents document_html_contents LONGTEXT;

Truncate table document_master;

  INSERT INTO document_master (document_id,document_name,document_path,document_current_status,document_current_status_id,document_assigned_id,document_assigned_name,document_assignee_id,document_assignee_name,document_recived_datetime,document_parsed_datetime,document_assigned_datetime,document_start_datetime,document_end_datetime,document_contents,document_suggested_codes,document_accepted_codes,document_rejected_codes,document_locked,client_id,client_name,physician_name,report_type,updated_by_id,updated_by,updated_date)
  VALUES
  (1,'abc.txt','/home/local/EZDI/abhijit.c','Ready For Allocation',111,1,'Chikhalikar B. Abhijit',11111,'System User','2015-06-15 01:50:33','2015-06-15 02:50:33',NULL,NULL,NULL,'asdfgjklhggnj',NULL, NULL,NULL,'N',1,'SGS','Rahul','HCC',11,'Application User','2015-06-17 10:50:33'),
  (2,'abc1.txt','/home/local/EZDI/abhijit.c','Ready For Allocation',111,1,'Chikhalikar B. Abhijit',11111,'System User','2015-06-15 01:50:33','2015-06-15 02:50:33',NULL,NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',11,'Application User','2015-06-17 10:50:33'),
  (3,'abc11.txt','/home/local/EZDI/abhijit.c','Ready For Allocation',111,1,'Chikhalikar B. Abhijit',11111,'System User','2015-06-15 01:50:33','2015-06-15 02:50:33',NULL,NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',11,'Application User','2015-06-17 10:50:33'),
  (4,'pqr.txt','/home/local/EZDI/abhijit.c','Allocat to TL',333,2,'Chikar B. Abhi',1,'Chikhalikar B. Abhijit','2015-06-15 01:50:33','2015-06-15 02:50:33',NULL,NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',2,'Chikar B. Abhi','2015-06-17 10:50:33'),
  (5,'pqr1.txt','/home/local/EZDI/abhijit.c','Allocat to TL',333,2,'Chikar B. Abhi',1,'Chikhalikar B. Abhijit','2015-06-15 01:50:33','2015-06-15 02:50:33',NULL,NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',2,'Chikar B. Abhi','2015-06-17 10:50:33'),
  (6,'pqr11.txt','/home/local/EZDI/abhijit.c','Allocat to TL',333,2,'Chikar B. Abhi',1,'Chikhalikar B. Abhijit','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',2,'Chikar B. Abhi','2015-06-17 10:50:33'),
  (7,'xyz.txt','/home/local/EZDI/abhijit.c','Allocat to TL',333,2,'Chikar B. Abhi',1,'Chikhalikar B. Abhijit','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',2,'Chikar B. Abhi','2015-06-17 10:50:33'),
  (8,'xyz1.txt','/home/local/EZDI/abhijit.c','Allocat to Coder',444,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (9,'xyz11.txt','/home/local/EZDI/abhijit.c','Allocat to Coder',444,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (10,'lmn.txt','/home/local/EZDI/abhijit.c','Allocat to Coder',444,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (11,'qwe.txt','/home/local/EZDI/abhijit.c','Draft',555,3,'Chikr B. @bhi',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',3,'Chikr B. @bhi','2015-06-17 10:50:33'),
  (12,'wer.txt','/home/local/EZDI/abhijit.c','Draft',555,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (13,'ert.txt','/home/local/EZDI/abhijit.c','Draft',555,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33',NULL,NULL,'asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (14,'lmn1.txt','/home/local/EZDI/abhijit.c','Rejected',222,3,'Chikr B. @bhi',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33','2015-06-15 04:50:33','2015-06-15 05:00:33','asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',3,'Chikr B. @bhi','2015-06-17 10:50:33'),
  (15,'lmn11.txt','/home/local/EZDI/abhijit.c','Rejected',222,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33','2015-06-15 04:50:33','2015-06-15 05:00:33','asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (16,'efg.txt','/home/local/EZDI/abhijit.c','Coding Completed',666,3,'Chikr B. @bhi',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33','2015-06-15 04:50:33','2015-06-16 05:00:33','asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',3,'Chikr B. @bhi','2015-06-17 10:50:33'),
  (17,'efg1.txt','/home/local/EZDI/abhijit.c','Coding Completed',666,3,'Chikr B. @bhi',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33','2015-06-15 04:50:33','2015-06-16 05:00:33','asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',3,'Chikr B. @bhi','2015-06-17 10:50:33'),
  (18,'efg11.txt','/home/local/EZDI/abhijit.c','Coding Completed',666,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33','2015-06-15 04:50:33','2015-06-16 05:00:33','asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (19,'efg111.txt','/home/local/EZDI/abhijit.c','Coding Completed',666,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33','2015-06-15 04:50:33','2015-06-16 05:00:33','asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33'),
  (20,'efg1111.txt','/home/local/EZDI/abhijit.c','Coding Completed',666,4,'Chikarrr B. @b#i',3,'Chikr B. @bhi','2015-06-15 01:50:33','2015-06-15 02:50:33','2015-06-15 03:50:33','2015-06-15 04:50:33','2015-06-16 05:00:33','asdfgjklhggnj',NULL,NULL,NULL,'N',1,'SGS','Rahul','HCC',4,'Chikarrr B. @b#i','2015-06-17 10:50:33');

update document_master set code_suggested_flag = 'Y';

DROP TABLE `final_accepted_codes` ;
CREATE TABLE `final_accepted_codes` ( 
    id int(11) NOT NULL AUTO_INCREMENT,
    code_id varchar(20) NOT NULL,
    code_evidance varchar(250),
    code_description varchar(500),
    documen_id varchar(100), 
    updated_user_id int(11),
    updated_user_name varchar(100),
    updated_datetime datetime,
    PRIMARY KEY (id),
    UNIQUE KEY `code_id` (`code_id`),
    FOREIGN KEY (updated_user_id) REFERENCES user_master(user_id)
) ;


DROP TABLE `patient_master` ;
CREATE TABLE `patient_master` ( 
    id int(11) NOT NULL AUTO_INCREMENT,
    document_id varchar(50) NOT NULL,
    document_name varchar(50) NOT NULL,
    document_path varchar(50) NOT NULL,

    patient_name varchar(50) DEFAULT NULL,
    patient_mrn varchar(50) DEFAULT NULL,
    patient_ssn varchar(50) DEFAULT NULL,
    patient_accountno varchar(50) DEFAULT NULL,
    patient_dob datetime,
    patient_gender varchar(5) DEFAULT NULL,
    patient_age int(2),
    patient_address varchar(50) DEFAULT NULL,
    patient_city varchar(50) DEFAULT NULL,
    patient_state varchar(50) DEFAULT NULL,
    patient_zip varchar(50) DEFAULT NULL,
    patient_location varchar(50) DEFAULT NULL,

    patient_admitdate datetime,
    patient_dischargdate datetime,
    PRIMARY KEY (id,document_id)
) ;


-- -----------

DROP TABLE `code_status_master` ;
CREATE TABLE `code_status_master` 
( 
    code_status_id int(5),
    code_status_description varchar(20),
    PRIMARY KEY (code_status_id)
);

INSERT INTO code_status_master (code_status_id,code_status_description) values (11,'Suggested');
INSERT INTO code_status_master (code_status_id,code_status_description) values (22,'Accepted');
INSERT INTO code_status_master (code_status_id,code_status_description) values (33,'Rejected');
INSERT INTO code_status_master (code_status_id,code_status_description) values (44,'Manual Coded');
 

DROP TABLE `document_codes` ;
CREATE TABLE `document_codes` 
( 
    id int(11) NOT NULL AUTO_INCREMENT,
    documen_id varchar(100), 
    code_id varchar(20) NOT NULL,
    code_evidance varchar(250),
    code_description varchar(500),
    start_character int(11),
    end_character int(11),
    code_positions JSON,
    code_status_id int(5),
    code_status_description varchar(20),
    updated_user_id int(11),
    updated_user_name varchar(100),
    updated_datetime datetime,
    PRIMARY KEY (id),
    UNIQUE KEY `comman_id` (code_id,documen_id),
    FOREIGN KEY (updated_user_id) REFERENCES user_master(user_id),
    FOREIGN KEY (code_status_id) REFERENCES code_status_master(code_status_id)
);

SET FOREIGN_KEY_CHECKS = 1; 


-- http://192.168.17.88:8983/solr/hcc_document_master/update?stream.body=%3Cdelete%3E%3Cquery%3E*:*%3C/query%3E%3C/delete%3E&commit=true

DROP TABLE `document_codes` ;
CREATE TABLE `document_codes` 
( 
id int(11) NOT NULL AUTO_INCREMENT,
diagnosis_code varchar(100), 
description text,
synonyms JSON,
PRIMARY KEY (id)
);


-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Doc Locker
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


DROP TABLE `user_master` ;
CREATE TABLE `user_master` (
   `user_id` int(11) NOT NULL AUTO_INCREMENT,
   `username` varchar(50) DEFAULT NULL,
   `first_name` varchar(65) NOT NULL,
   `middle_name` varchar(65) DEFAULT NULL,
   `last_name` varchar(65) NOT NULL,
   `gender` varchar(10) NOT NULL,
   `dob` date NOT NULL,
   `email_id` varchar(50) NOT NULL,
   `alternate_email_id` varchar(50) DEFAULT NULL,
   `address_1` varchar(50) DEFAULT NULL,
   `address_2` varchar(50) DEFAULT NULL,
   `city` varchar(50) DEFAULT NULL,
   `state` varchar(50) DEFAULT NULL,
   `zipcode` varchar(15) NOT NULL,
   `country` varchar(30) DEFAULT NULL,
   `phone_number` varchar(20) NOT NULL,
   `secondary_phone_number` varchar(20) DEFAULT NULL,
   `fax_number` varchar(20) DEFAULT NULL,
   `pager_number` varchar(20) DEFAULT NULL,
   `organisation_name` varchar(20) DEFAULT NULL,
   `active_flag` int(11) DEFAULT '1',
   `delete_flag` int(11) DEFAULT '0',
   `active_datetime` datetime DEFAULT NOW(),
   `dactive_datetime` datetime DEFAULT NOW(),
   `updated_datetime` datetime DEFAULT NOW(),
   `user_log_description` varchar(1000) DEFAULT NULL,
   PRIMARY KEY (`user_id`),
   UNIQUE KEY `username` (`username`)
   ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


DROP TABLE `user_master_log` ;
CREATE TABLE `user_master_log` (
   `user_id` int(11) NOT NULL AUTO_INCREMENT,
   `username` varchar(50) DEFAULT NULL,
   `first_name` varchar(65) NOT NULL,
   `middle_name` varchar(65) DEFAULT NULL,
   `last_name` varchar(65) NOT NULL,
   `gender` varchar(10) NOT NULL,
   `dob` date NOT NULL,
   `email_id` varchar(50) NOT NULL,
   `alternate_email_id` varchar(50) DEFAULT NULL,
   `address_1` varchar(50) DEFAULT NULL,
   `address_2` varchar(50) DEFAULT NULL,
   `city` varchar(50) DEFAULT NULL,
   `state` varchar(50) DEFAULT NULL,
   `zipcode` varchar(15) NOT NULL,
   `country` varchar(30) DEFAULT NULL,
   `phone_number` varchar(20) NOT NULL,
   `secondary_phone_number` varchar(20) DEFAULT NULL,
   `fax_number` varchar(20) DEFAULT NULL,
   `pager_number` varchar(20) DEFAULT NULL,
   `organisation_name` varchar(20) DEFAULT NULL,
   `active_flag` int(11) DEFAULT '1',
   `delete_flag` int(11) DEFAULT '0',
   `active_datetime` datetime DEFAULT NOW(),
   `dactive_datetime` datetime DEFAULT NOW(),
   `updated_datetime` datetime DEFAULT NOW(),
   `user_log_description` varchar(1000) DEFAULT NULL,
   PRIMARY KEY (`user_id`,`updated_datetime`),
   UNIQUE KEY `username` (`username`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


DROP TABLE `zipcode_mysql_user_mapping` ;
CREATE TABLE `zipcode_mysql_user_mapping` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mysql_username` varchar(50) DEFAULT NULL,
  `mysql_password` varchar(50) DEFAULT NULL,
  `zipcode` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;


DROP TABLE `user_authentication` ;
CREATE TABLE `user_authentication` 
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `active_flag` int(11) DEFAULT '1',
  `delete_flag` int(11) DEFAULT '0',
  `mysql_username` varchar(50) DEFAULT NULL,
  `mysql_password` varchar(50) DEFAULT NULL,
  `current_otp` int(11) DEFAULT NULL,
  `password_expiry_date` date DEFAULT NULL,
  `password_reset_flag` tinyint(1) NOT NULL DEFAULT '1',
  `max_count_lock` tinyint(2) unsigned NOT NULL DEFAULT '5',
  `lock_expiration_time_in_min` smallint(5) unsigned NOT NULL DEFAULT '10',
  `last_login_attempt_datetime` datetime DEFAULT NULL,
  `password_expire_days` smallint(5) unsigned NOT NULL DEFAULT '90',
  `failed_count` tinyint(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES user_master(user_id),
   UNIQUE KEY `UK_opt` (`current_otp`)
  ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


DROP TABLE `event_log` ;
CREATE TABLE `event_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `otp` int(11) NOT NULL,
  `action_type` varchar(50) DEFAULT NULL,
  `execution_date` datetime NOT NULL,
  `completion_date` datetime DEFAULT NULL,
  `session_id` varchar(1000) NOT NULL DEFAULT '',
  `ip_address` varchar(15) NOT NULL DEFAULT '',
  `is_active` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES user_master(user_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;


DROP TABLE `document_list_master` ;
CREATE TABLE `document_list_master` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `documents_name` varchar(15) NOT NULL DEFAULT '',
  `sequence` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NOW(),
  PRIMARY KEY (`request_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;



DROP TABLE `user_document_master` ;
CREATE TABLE `user_document_master` (
  `document_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_full_name` varchar(100) NOT NULL,
  documents_name JSON NOT NULL,
  document_path varchar(100) NOT NULL COMMENT 's3_bucket/user_id/',
  document_upload_datetime datetime DEFAULT NULL,
  document_upload_status varchar(50) DEFAULT NULL,
  document_update_datetime datetime DEFAULT NULL,
  PRIMARY KEY (`document_id`),
   UNIQUE KEY `UK_Users_Doc` (`user_id`,`document_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;
-- Letest Document Added Record

DROP TABLE `user_document_log` ;
CREATE TABLE `user_document_log` (
  `document_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_full_name` varchar(100) NOT NULL,
  documents_name JSON NOT NULL,
  document_path varchar(100) NOT NULL COMMENT 's3_bucket/user_id/',
  document_upload_datetime datetime DEFAULT NULL,
  document_upload_status varchar(50) DEFAULT NULL,
  document_update_datetime datetime DEFAULT NULL,
  PRIMARY KEY (`document_id`,`document_update_datetime`),
   UNIQUE KEY `UK_Users_Doc` (`user_id`,`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;
-- Log OF Privious Documents


DROP TABLE `user_document_request_master` ;
CREATE TABLE `user_document_request_master` (
  `request_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `requester_user_id` int(11) DEFAULT NULL,
  `requester_full_name` varchar(100) DEFAULT NULL,
  `requester_ip` varchar(50) NOT NULL,
  `requester_location` varchar(50) NOT NULL,
  `requested_datetime` datetime DEFAULT NULL,
  `requested_user_id` int(11) NOT NULL,
  `requested_user_full_name` varchar(100) NOT NULL,
  `requested_document_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `requested_documents_name` JSON NOT NULL,
  `requested_document_path` varchar(100) NOT NULL COMMENT 's3_bucket/user_id/',
  `requested_document_upload_datetime` datetime DEFAULT NULL,
  `requested_document_upload_status` varchar(50) DEFAULT NULL,
  `requested_document_update_datetime` datetime DEFAULT NULL,
  `request_document_status` varchar(50) DEFAULT NULL,
  `request_completed_datetime` datetime DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NOW(),
  PRIMARY KEY (`request_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;
-- Letest Request Record


DROP TABLE `user_document_request_log` ;
CREATE TABLE `user_document_request_log` (
  `request_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `requester_user_id` int(11) DEFAULT NULL,
  `requester_full_name` varchar(100) DEFAULT NULL,
  `requester_ip` varchar(50) NOT NULL,
  `requester_location` varchar(50) NOT NULL,
  `requested_datetime` datetime DEFAULT NULL,
  `requested_user_id` int(11) NOT NULL,
  `requested_user_full_name` varchar(100) NOT NULL,
  `requested_document_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `requested_documents_name` JSON NOT NULL,
  `requested_document_path` varchar(100) NOT NULL COMMENT 's3_bucket/user_id/',
  `requested_document_upload_datetime` datetime DEFAULT NULL,
  `requested_document_upload_status` varchar(50) DEFAULT NULL,
  `requested_document_update_datetime` datetime DEFAULT NULL,
  `request_document_status` varchar(50) DEFAULT NULL,
  `request_completed_datetime` datetime DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NOW(),
  PRIMARY KEY (`request_id`,`updated_datetime`)
  ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ;
-- Old Request Record


