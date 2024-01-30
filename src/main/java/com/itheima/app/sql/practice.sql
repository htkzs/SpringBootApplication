## 统计男性员工和女性员工的数量
select gender,COUNT(*) as number FROM employees GROUP BY gender;
## 查询薪资最高的员工
select emp_no,max(salary) from salaries;

select emp_no,min(salary) as salary from salaries;

select emp_no,salary,from_date,to_date FROM salaries where from_date BETWEEN CAST('1999-09-17' as DATE) AND CAST('1999-09-20' as DATE);

select emp_no,salary,from_date,to_date FROM salaries where from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE);

select emp_no,max(salary) as salary,from_date,to_date FROM salaries where from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE);


select emp_no,min(salary) as salary,from_date,to_date FROM salaries where from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE);

select avg(salary) as salary FROM salaries where from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE);

select COUNT(*) as number FROM salaries where from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE);

select COUNT(DISTINCT(salary)) as number FROM salaries where from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE);


select sum(salary) as count_salary FROM salaries where from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE);

## 2. where与having区别
## 执行时机不同: where是分组之前进行过滤，不满足where条件，不参与分组;而having是分组之后对结果进行过滤。判断条件不同:
## where不能对聚合函数进行判断，而having可以。
select gender,COUNT(*) as number FROM employees GROUP BY gender;

select emp_no,COUNT(*) as number FROM salaries GROUP BY emp_no;

select emp_no,COUNT(*) as number,SUM(salary) as salary_count FROM salaries where emp_no<10004 GROUP BY emp_no HAVING salary_count>400000;

select emp_no,COUNT(*) as number,SUM(salary) as salary_count FROM salaries where emp_no<10004 GROUP BY emp_no;


select dept_no,dept_name FROM departments ORDER BY dept_no ASC;
select dept_no,dept_name FROM departments ORDER BY dept_no DESC;

select dept_no,dept_name FROM departments ORDER BY dept_name DESC;

select emp_no,salary,from_date,to_date FROM salaries WHERE from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE) ORDER BY salary ASC;

## 根据日期进行排序 不需要进行转换
select emp_no,salary,from_date,to_date FROM salaries WHERE from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE) ORDER BY from_date ASC;
## 多字段排序
select emp_no,salary,from_date,to_date FROM salaries WHERE from_date >= CAST('1999-09-17' as DATE) AND to_date <= CAST('1999-10-20' as DATE) ORDER BY from_date ASC,salary DESC;

## 起始索引从o开始，起始索引=(查询页码-1)*每页显示记录数。
## 分页查询是数据库的方言，不同的数据库有不同的实现，MySQL中是LIMIT.如果查询的是第一页数据,起始索引可以省略，直接简写为limit 10.
select* from salaries limit 10;
select* from salaries limit 0,10;


SELECT* from departments WHERE dept_no in('d009','d005','d002') ORDER BY dept_no ASC;

### 查询性别为男 并且退休日期在1986-06-26 到 1987-06-26之间的 并且first_name为4个字符的信息
SELECT* from employees where gender='M' and hire_date BETWEEN CAST('1986-06-25' AS DATE) and CAST('1986-06-25'as DATE) and first_name LIKE '____';


SELECT* from employees where gender='M' and hire_date BETWEEN CAST('1986-06-25' AS DATE) and CAST('1986-06-25'as DATE) ORDER BY birth_date ASC LIMIT 1,5;

## 字符串拼接函数
select emp_no,birth_date,CONCAT(first_name,' ',last_name) as name,gender from employees where hire_date BETWEEN CAST('1986-06-25' AS DATE) and CAST('1986-06-25' AS DATE);

##

select emp_no,birth_date,CONCAT(first_name,' ',last_name) as name,gender from employees where emp_no=10001;
select emp_no,birth_date,UPPER(first_name) as firstname,UPPER(last_name) as last_name,gender from employees where emp_no=10001;


select emp_no,birth_date,LOWER(first_name) as firstname,gender from employees where emp_no=10001;

select emp_no,birth_date,CONCAT(UPPER(first_name),' ',LOWER(last_name)),gender from employees where emp_no=10001;
select emp_no,birth_date,CONCAT(UPPER(first_name),' ',LOWER(last_name)),gender from employees where emp_no in (10001,10002,10003,10004);

## 注意截取的字符串的开始位置为0
select emp_no,birth_date,SUBSTRING(first_name,1,3) as name,gender from employees where emp_no in (10001,10002,10003,10004);

select emp_no,birth_date,SUBSTRING(first_name FROM 1 FOR 3) as name,gender from employees where emp_no in (10001,10002,10003,10004);


select emp_no,birth_date,TRIM(first_name) from employees where emp_no in (10001,10002,10003,10004);


select emp_no,birth_date,LPAD(first_name,10,'-'),RPAD(last_name,10,'-') from employees where emp_no in (10001,10002,10003,10004);


SELECT CURDATE();
SELECT CURTIME();
SELECT NOW();
select YEAR('1986-06-25');
select MONTH('1986-06-25');
select DAY('1986-06-25');
select DATE_ADD(CURDATE(),INTERVAL 70 DAY);
select DATEDIFF('2023-12-21','1986-06-25');

## 查询10001员工的入职天数总和，并根据入职天数倒排序
SELECT emp_no,salary,DATEDIFF(to_date,from_date) as days from salaries where emp_no=10001 ORDER BY salary DESC;


SELECT emp_no,salary,DATEDIFF(CURDATE(),to_date) as days from salaries where emp_no=10001;

## 数值函数的使用

SELECT CEIL(1.2) as number;
SELECT CEIL(1.7) as number;
## 向下取整
SELECT FLOOR(1.2);
## 取模运算
SELECT MOD(6,4);
## 产生一个0-1之间的随机值
SELECT RAND();
## 求参数X四舍五入的值，保留y位小数
SELECT ROUND(4.345,2);

## 生成一个随机的6位验证码

SELECT LPAD(ROUND(RAND()*1000000),6,'0');

## 相当于三目运算符
SELECT IF(TRUE,"OK","ERROR");  ## 返回结果为OK
SELECT IF(FALSE,"OK","ERROR");

## 如果第一个参数不为NULL时，返回第一个参数，如果为NULL，返回第二个参数
SELECT IFNULL("ok","ERRORS");
SELECT IFNULL("","ERRORS");  ## 注意""不等于NULL


SELECT IFNULL(NULL,"ERRORS");


SELECT emp_no,CASE gender
                  WHEN 'M' THEN
                      '男'
                  ELSE
                      '女'
    END
    gender from employees where hire_date = CAST('1986-06-25' AS DATE);














