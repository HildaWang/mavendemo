/*Table structure for table `user` */

create table t_user(
       id number(9) not null primary key,
       name varchar2(40) not null,
       age number(3) default null
);    

CREATE SEQUENCE t_user_sequence
　　INCREMENT BY 1 -- 每次加几个 
 　　START WITH 1 -- 从1开始计数 
     minvalue 0
 　　NOMAXVALUE -- 不设置最大值 
 　　NOCYCLE -- 一直累加，不循环 
 　　CACHE 100; 

DROP SEQUENCE t_user_sequence;

create trigger t_user_trigger before
insert on t_user for each row when (new.id is null)
begin
 select t_user_sequence.nextval into:new.id from dual;
 end;


drop trigger t_user_trigger;


insert into t_user(id,name) values(t_user_sequence.nextval,'name1');

delete from t_user t where t.id=0;

select * from t_user;