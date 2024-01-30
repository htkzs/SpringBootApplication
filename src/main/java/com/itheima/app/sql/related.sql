DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

DELETE FROM `user`;

INSERT INTO `user` (id, name, age, email) VALUES
                                              (1, 'Jone', 18, 'test1@baomidou.com'),
                                              (2, 'Jack', 20, 'test2@baomidou.com'),
                                              (3, 'Tom', 28, 'test3@baomidou.com'),
                                              (4, 'Sandy', 21, 'test4@baomidou.com'),
                                              (5, 'Billie', 24, 'test5@baomidou.com');


select* FROM user;

delete FROM user where age=24;


TRUNCATE TABLE user;

/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 28/01/2024 22:51:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                         `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
                         `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
                         `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                         `is_deleted` int(2) UNSIGNED ZEROFILL NOT NULL COMMENT '逻辑删除的标记字段 0-未删除 1-已删除',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;


select * from user where name like '%o%' and age>23 or email is not null;

select * from user ORDER BY age asc,id DESC;

UPDATE user set name='zhangsan',age=24,email='17371167564@weibo.com',version = version+1 where id = 43 AND version=version;




CREATE TABLE score(
                      id int COMMENT 'ID',
                      name VARCHAR(20) COMMENT '姓名',
                      math int COMMENT '数学',
                      english int COMMENT '英语',
                      chinese int COMMENT '语文'
) COMMENT '学生成绩表';

INSERT INTO score(id,name,math,english,chinese) VALUES(1,'Tom',67,88,95),(2,'zhangsan',87,65,93),(3,'lisi',72,81,85),(4,'wangwu',76,83,75),(5,'anlone',79,79,88),(6,'jack',76,92,81);



TRUNCATE TABLE score;




## case when then ELSE使用

select id,name,
       (CASE WHEN math >= 85 THEN '优秀' WHEN math >= 60 THEN '及格' ELSE '不及格' END) as math,
       (CASE WHEN english >= 85 THEN '优秀' WHEN english >= 60 THEN '及格' ELSE '不及格' END) as english,
       (CASE WHEN chinese >= 85 THEN '优秀' WHEN chinese >= 60 THEN '及格' ELSE '不及格' END) as chinese
FROM score;




CREATE TABLE student(
                        id int PRIMARY key auto_increment,
                        name VARCHAR(10) NOT NULL UNIQUE,
                        age int,
                        status CHAR(1) DEFAULT 0,
                        gender CHAR(1)

);

drop table student;


CREATE TABLE if not EXISTS student (
                                       id int PRIMARY key auto_increment COMMENT '学生编号',
                                       name VARCHAR(10) NOT NULL UNIQUE COMMENT '姓名',
                                       age int COMMENT '年龄',
                                       status CHAR(1) DEFAULT '0' COMMENT '状态',
                                       gender CHAR(1)  COMMENT '性别'

) ENGINE=INNODB DEFAULT charset=utf8;

## 检查约束  在mysql8.0.16版本支持
CREATE TABLE if not EXISTS student (
                                       id int auto_increment COMMENT '学生编号',
                                       name VARCHAR(10) NOT NULL UNIQUE COMMENT '姓名',
                                       age int CHECK(age>=0 && age<=120) COMMENT '年龄',
                                       status CHAR(1) DEFAULT '0' COMMENT '状态',
                                       gender CHAR(1)  COMMENT '性别',
                                       PRIMARY KEY(id)
);


CREATE TABLE if not EXISTS department(
                                         id INT auto_increment COMMENT 'ID',
                                         name VARCHAR(50) not null COMMENT '部门名称',
                                         PRIMARY KEY(id)
) COMMENT '部门表';

insert into department values(1,'研发部'),(2,'市场部'),(3,'财务部'),(4,'销售部'),(5,'总经办');

## 在创建表的同时增加外键
create table if not EXISTS employees(
                                        id int auto_increment COMMENT 'ID' PRIMARY key,
                                        name VARCHAR(50) not null COMMENT '姓名',
                                        age int COMMENT '年龄',
                                        job VARCHAR(20) COMMENT '职位',
                                        salary int COMMENT '薪资',
                                        entrydate date COMMENT '入职日期',
                                        managerid int COMMENT '直属领导ID',
                                        dept_id int COMMENT '部门ID',
                                        FOREIGN KEY(dept_id) REFERENCES department(id)
)COMMENT '员工表';

INSERT into employees(name,age,job,salary,entrydate,managerid,dept_id) VALUES('金庸',34,'总裁',20000,'2000-01-01',1,5),('张三丰',45,'项目经理',19000,'2005-12-05',1,1),('杨逍',33,'开发',8400,'2000-11-03',2,1),('韦一笑',48,'开发',110000,'2002-02-05',2,1),('常遇春',43,'开发',10500,'2004-09-07',3,1),('小昭',19,'程序员鼓励师',6600,'2004-10-12',2,1);



## 在表创建完成后增加外键
ALTER TABLE employees ADD CONSTRAINT fk_emp_dept FOREIGN KEY(dept_id) REFERENCES department(id);

##删除外键
ALTER TABLE employees DROP FOREIGN KEY fk_emp_dept;


##删除父表的数据 employees 子 department父

ALTER TABLE employees ADD CONSTRAINT fk_emp_dept FOREIGN KEY(dept_id) REFERENCES department(id) ON UPDATE CASCADE ON DELETE CASCADE;



ALTER TABLE employees ADD CONSTRAINT fk_emp_dept FOREIGN KEY(dept_id) REFERENCES department(id) ON UPDATE set null ON DELETE set null;

# -----------------------------------------------------------------------------多对多关系实现-----------------------------------------------------------------------------------------------------

create table if not EXISTS course(
                                     id int auto_increment PRIMARY KEY COMMENT '课程ID',
                                     name VARCHAR(10) COMMENT '课程名称'
) COMMENT '课程表' ENGINE=INNODB DEFAULT charset=utf8;

INSERT INTO course values(null,'java'),(null,'PHP'),(null,'mysql'),(null,'hadoop'),(null,'hbase');


CREATE TABLE if not EXISTS student (
                                       id int PRIMARY key auto_increment COMMENT '学生编号',
                                       name VARCHAR(10) NOT NULL UNIQUE COMMENT '姓名',
                                       age int COMMENT '年龄',
                                       status CHAR(1) DEFAULT '0' COMMENT '状态',
                                       gender CHAR(1)  COMMENT '性别'

) COMMENT '学生表' ENGINE=INNODB DEFAULT charset=utf8;

insert into student(name,age,status,gender) values('zhangsan',23,'1','男'),('lisi',24,'0','女'),('wangwu',27,'1','男'),('zhaoliu',28,'1','男'),('小白',24,'1','女'),('小红',23,'0','女'),('小张',23,'1','男');



## 创建多对多关系的中间表
create table if not EXISTS student_course(
                                             id int auto_increment PRIMARY KEY COMMENT '主键ID',
                                             student_id int not null COMMENT '学生ID',
                                             course_id int not null COMMENT '课程ID',
                                             CONSTRAINT fk_student_id FOREIGN KEY(student_id) REFERENCES student(id),
                                             CONSTRAINT fk_course_id FOREIGN KEY(course_id) REFERENCES course(id)
) COMMENT '学生课程关联表' ENGINE=INNODB DEFAULT charset=utf8;

INSERT INTO student_course VALUES(null,1,1),(null,1,2),(null,1,3),(null,2,2),(null,2,3),(null,2,4);

# ---------------------------------------------------------------------------------------一对一关系实现---------------------------------------------------------------------------------------------------


create table tb_user(
                        id int auto_increment primary key COMMENT '主键ID',
                        nane varchar(10) COMMENT'姓名',
                        age int COMMENT'年龄',
                        gender char(1) COMMENT '1:男,2:女',
                        phone char(11) COMMENT '手机号'
)COMMENT '用户基本信息表';

INSERT INTO tb_user(id,nane,age,gender,phone) VALUES(null,'黄渤',45,'1','13333333333'),(null,'范冰冰',46,'2','17777777777'),(null,'马云',47,'1','13999999777'),(null,'李彦宏',38,'1','15555555555');

create table tb_user_edu
(
    id            int auto_increment primary key COMMENT "主键ID",
    degree        varchar(20) COMMENT '学历',
    major         varchar(50) COMMENT '专业',
    primaryschool varchar(50) COMMENT '小学',
    middiLeschool varchar(50) COMMENT '中学',
    university    varchar(50) COMMENT '大学',
    userid        int unique COMMENT '用户ID',
    CONSTRAINT fk_userid foreign key (userid) references tb_user(id)
)COMMENT '用户教育临息表';

INSERT INTO tb_user_edu VALUES(null,'本科','舞蹈','晋安区第一小学','晋安区第一中学','北京舞蹈学院',1),(null,'硕士','表演','朝阳区第一小学','朝阳区第一中学','北京电影学院',2),(null,'本科','英语','杭州市第一小学','杭州市第一中学','杭州师范大学',3),(null,'本科','应用数学','阳泉第一小学','阳泉第一中学','清华大学',4);

# --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


## 多表联查
## 内连接查询 显式内连接和隐式内连接

select* FROM employees,department;

## 隐式内连接
select employees.`name`,department.`name` FROM employees,department where employees.dept_id = department.id;
##显式内连接
select employees.`name`,department.`name` FROM employees INNER JOIN department ON employees.dept_id = department.id;

## 外连接

select* FROM employees LEFT OUTER JOIN department ON employees.dept_id = department.id;

select* FROM employees RIGHT OUTER JOIN department ON employees.dept_id = department.id;

##自连接

## 查询员工及其所属领导的名字，实质上是查询同一张表，因为领导也是公司的员工

select e.name '员工',b.name '直接领导' FROM employees e,employees b where e.managerid = b.id;


## 查询员工及其所属领导的名字，如果一个员工没有直接领导也展示出来
select e.name '员工',b.name '直接领导' FROM employees e left OUTER join employees b ON e.managerid = b.id;


### 联合查询

select * from employees where salary>20000
union all
select * from employees where job='开发';


select * from employees where salary>20000
union
select * from employees where job='开发';


##子查询
## 查询 财务部下面的所有员工的信息
select id from department where name='研发部';
select * from employees where dept_id = 1;


select * from employees where dept_id = (select id from department where name='研发部');

## 查询某个员工入职日期之后入职的员工信息

select entrydate from employees WHERE name = '杨逍';

select * from employees where entrydate > (select entrydate from employees WHERE name = '杨逍');

## 查询研发部和总经办的所有员工的信息
## 首先从department 查询销售部和市场部的部门ID，再通过部门ID找到具体的员工
select id from department where name in('研发部','总经办');
select * from employees where dept_id IN (select id from department where name in('研发部','总经办'));

## 查询比财务部所有员工工资都高的员工信息
## 首先找出财务部所有员工的工资

select id from department where name='财务部';

select salary from employees where dept_id IN (select id from department where name='财务部');

select * from employees where salary>all(select salary from employees where dept_id IN (select id from department where name='财务部'));



## 查询比研发部其中任何一人工资高的员工信息

select salary from employees where dept_id IN (select id from department where name='研发部');

select * from employees where salary > ANY(select salary from employees where dept_id IN (select id from department where name='研发部'));
##或者
select * from employees where salary > SOME(select salary from employees where dept_id IN (select id from department where name='研发部'));

## 行子查询
#3 查询与'张无忌'薪资及直属领导相同的员工信息

select salary,managerid from employees where name ='张无忌';
select * from employees where (salary,managerid)=(select salary,managerid from employees where name ='张无忌');

## 查询入职时间是'2006-01-01' 之后的其员工信息及部门信息

select * from employees where entrydate>'2006-01-01';
select e.*,d.* from (select * from employees where entrydate>'2006-01-01') e LEFT JOIN department d ON e.dept_id = d.id;



## 查询与'鹿杖客' 或 '宋远桥'的职位和薪资相同的员工信息
select job,salary from employees where name='鹿杖客' or name='宋远桥';

select* from employees where (job,salary)in (select job,salary from employees where name='鹿杖客' or name='宋远桥');





