/*
 * Hostel Management System
 * Author: Jackson Isaac, Vijay, Subromanion
 * S5 CSE A Group 11
 */

create table warden (
	wid		varchar(15) primary key,
	wname		varchar(30),
	experience	int,
	salary		double precision
);

create table hostel(
	hid		varchar(15) primary key,
	hname		varchar(30),
	wid		varchar(15) references warden,
	capacity	int,
	floors		int,
	washrooms	int
);

create table rooms(
	roomno		varchar(15) primary key,
	hid		varchar(15) references hostel,
	beds		int,
	cupboards	int,
	fans		int,
	lights		int
);

create table dept(
	deptid		varchar(15) primary key,
	deptname	varchar(40),
	budget		double precision,
	location	varchar(25)
);

drop table batch cascade;

create table batch(
	bid		varchar(10),
	year		int,
	sem		int,
	deptid		varchar(15) references dept,
	location 	varchar(25),
	primary key (bid, year, sem)
);

drop table student cascade

create table student(
	sid		varchar(15) primary key,
	sname		varchar(35),
	dob		date,
	state		varchar(25),
	city		varchar(25),
	hid		varchar(15) references hostel,
	roomno 		varchar(15) references rooms,
	deptid		varchar(15) references dept,
	bid		varchar(10),
	year		int,
	sem		int,
	foreign key (bid, year, sem) references batch
);

drop table student;

create table faculty(
	fid		varchar(15) primary key,
	fname		varchar(35),
	salary		double precision,
	experience	int,
	hid 		varchar(15) references hostel,
	deptid 		varchar(15) references dept
);


drop table faculty;

create table teaching(
	fid		varchar(15) references faculty,
	qualification	varchar(40),
	course		varchar(30)
);

drop table teaching;

create table nonteaching(
	fid		varchar(15) references faculty,
	profession	varchar(40)
);

drop table attendancelog 

create table attendancelog(
	wid		varchar(15) references warden,
	sid		varchar(15) references student,
	attdate		date,
	attstatus	boolean,
	primary key (sid, attdate, attstatus)
);

insert into warden values ('w01','Sumesh' ,2,5000);
insert into warden values ('w02','Ramesh',1,4000);
insert into warden values ('w03','Ganesh',4,7000);
insert into warden values ('w04','Lokesh',3,4500);
insert into warden values ('w05','Rakesh',8,10000);

select * from warden;

insert into hostel values ('h01','Shivam','w01',400,4,80);
insert into hostel values ('h02','Sundarm','w02',300,4,60);
insert into hostel values ('h03','Sanadhanam','w03',600,4,120);
insert into hostel values ('h04','Prasadam','w04',400,4,80);
insert into hostel values ('h05','Ashokam','w05',700,4,140);

select * from hostel;

insert into rooms values (101,'h01',4,2,2);
insert into rooms values (102,'h02',4,2,2);
insert into rooms values (103,'h03',4,2,2);
insert into rooms values (104,'h04',4,2,2);
insert into rooms values (105,'h05',4,2,2);
insert into rooms values (106,'h01',4,2,2);
insert into rooms values (107,'h02',4,2,2);
insert into rooms values (108,'h03',4,2,2);
insert into rooms values (109,'h04',4,2,2);
insert into rooms values (110,'h05',4,2,2);

select * from rooms;

insert into dept values ('Mech','Mechanical Engineering',20000,'g0');
insert into dept values ('EEE','Electrical Engineering',30000,'g1');
insert into dept values ('ECE','Electronics and Comunication Engineering',40000,'g2');
insert into dept values ('CSE','Computer Science Engineering Engineering',50000,'g3');

select * from dept;

insert into batch values ('Mech-A',2012,5,'Mech','A201');
insert into batch values ('EEE-B',2014,4,'EEE','A202');
insert into batch values ('ECE-A',2013,3,'ECE','S101');
insert into batch values ('CSE-B',2012,2,'CSE','S203');
insert into batch values ('CSE-A',2012,5,'CSE','N207');

select * from batch;

insert into student values ('s01','Vijay','1994-10-13','Andhra','Vizag','h01',101,'CSE', 'CSE-B',2012,2);
insert into student values ('s02','Jackson','1994-11-06','Maharashtra','Mumbai','h02',102,'CSE', 'CSE-A',2012,5);
insert into student values ('s03','Karthik','1995-9-5','Tamilnadu','Coimbatore','h03',103,'ECE', 'ECE-A',2013,3);
insert into student values ('s04','Nanda','1994-6-2','Tamilnadu','Coimbatore','h04',104,'CSE', 'CSE-B',2012,2);
insert into student values ('s05','Gaurav','1994-9-9','Delhi','Uttar Pradesh','h05',105,'ECE', 'ECE-A',2013,3);

select * from student;

insert into faculty values ('f01','Manjusha',20000,5,'h05','Mech');
insert into faculty values ('f02','Hariprasad',40000,7,'h04','CSE');
insert into faculty values ('f03','Dhaneesh',20000,5,'h03','ECE');
insert into faculty values ('f04','Veena',70000,12,'h03','EEE');
insert into faculty values ('f05','Shakti Prasad',60000,8,'h01','CSE');
insert into faculty values ('f06','Pramod',10000,8,'h02','Mech');
insert into faculty values ('f07','Shiva',10000,8,'h05','EEE');
insert into faculty values ('f08','Binu',10000,8,'h04','CSE');
insert into faculty values ('f09','Subash',10000,8,'h01','EEE');

select * from faculty;

insert into teaching values ('f01','M.tech','Maths');
insert into teaching values ('f02','M.tech','Signals');
insert into teaching values ('f03','M.tech','Circuit Theory');
insert into teaching values ('f04','Phd','Algorithms');
insert into teaching values ('f05','M.tech','Machines');

select * from teaching;

insert into nonteaching values ('f06','Lab Assistant');
insert into nonteaching values ('f07','Lab Assistant');
insert into nonteaching values ('f08','Lab Assistant');
insert into nonteaching values ('f09','Lab Assistant'); 

select * from nonteaching;
/*
insert into attendancelog values ('w01','s01','2010-10-9','True');
insert into attendancelog values ('w02','s02','2010-10-9','True');
insert into attendancelog values ('w03','s03','2010-10-9','False');
insert into attendancelog values ('w04','s04','2010-10-9','False');
insert into attendancelog values ('w05','s05','2010-10-9','True');*/

select * from attendancelog;

/* Queries */
/* Function to insert Present Student */
--drop FUNCTION att_present(wid varchar(15), sid varchar(15)) 
create or replace function att_present(wid varchar(15), sid varchar(15)) RETURNS text as
$$
	DECLARE
		res text;
		att CURSOR FOR Select a.sid, a.attdate, a.attstatus from attendancelog a where a.sid = sid;
	BEGIN
		insert into attendancelog values(wid, sid, now(), true);
		res = 'Attendance updated successfully';
		return res;
	END;
$$ Language plpgsql

/* Function to insert Absent Student */
create or replace function att_absent(wid varchar(15), sid varchar(15)) RETURNS text as
$$
	DECLARE
		res text;
		att CURSOR FOR Select a.sid, a.attdate, a.attstatus from attendancelog a where a.sid = sid;
	BEGIN
		insert into attendancelog values(wid, sid, now(), false);
		res = 'Attendance updated successfully';
		return res;
	END;
$$ Language plpgsql

Select * from attendancelog;


/* With Clause, Set, Outer Join */
with all_dept as ((Select deptid from student) UNION (Select deptid from faculty)),
	common_dept as ((Select deptid from student) INTERSECT (Select deptid from faculty))
Select a.deptid as "Student Department", c.deptid as "Faculty Department" from all_dept a left outer join common_dept c on a.deptid = c.deptid;

create table att_percent(
	sid		varchar(15) references student,
	att_perc	double precision,
	update_time	timestamp
);

/* Trigger to update attendance percent when update on student attendence */
create or replace function update_att_percent() RETURNS TRIGGER as
$$
	DECLARE
		stud	CURSOR FOR Select * from attendancelog a where a.sid = NEW.sid;
		st_log	CURSOR FOR Select * from att_percent ap where ap.sid = NEW.sid;
		slog_r	att_percent%ROWTYPE;
		tot	double precision = 0;
		pres	double precision = 0;
		st_row	attendancelog%ROWTYPE;
		per	numeric(5,2);
	BEGIN
		open stud;
		fetch stud into st_row;
		while found loop
			tot := tot + 1;
			if st_row.attstatus = true then
				pres := pres + 1;
			end if;
			fetch stud into st_row;
		end loop;
		per := (pres / tot) * 100;
			RAISE NOTICE '%',per;
		open st_log;
		fetch st_log into slog_r;
		if found then
			update att_percent set att_perc = per where sid = NEW.sid;
		else
			insert into att_percent values(NEW.sid, per, now());
		end if;

		RETURN NEW;
	END;
$$ Language plpgsql

create trigger att_percent_update after update or insert or delete on attendancelog
	FOR EACH ROW EXECUTE PROCEDURE update_att_percent();

Select att_present('w02','s03');

Select * from att_percent;

with stud_count as (Select hid, 
	deptid, count(sid) as "studcount"
	from student group by deptid, hid),
	faculty_count as (Select hid, 
	deptid as "Department Name", count(fid) as "fac_count"
	from faculty group by deptid, hid)
Select s.hid as "Hostel", s.deptid as "Department Name", s.studcount as "Number of Students", 
	f.fac_count as "Number of Faculty" from stud_count s left outer join faculty_count f on s.hid = f.hid order by s.hid;
