create table User(
user_id int not null auto_increment,
primary key (user_id),
username varchar(20) not null,
email varchar(30),
password varchar(20) not null,
telephone_no int,
highest_score int not null
);
drop table questions;
create table Questions(
question_id int not null auto_increment,
primary key (question_id),
category varchar(10) not null,
question varchar(100) not null,
option1 varchar(100) not null,
option2 varchar(100) not null,
option3 varchar(100) not null,
option4 varchar(100) not null,
answer varchar(100) not null,
priority int not null
);

insert into User values(null,"rohan","r@hotmail.com","rohan123",380984824,0);
insert into User values(null,"mohan","m6@hotmail.com","er123",21323424,0);
insert into User values(null,"rohit","rohit56@hotmail.com","werty123",380984856,0);
select * from User;

insert into questions values(null,"DSA","What is the worst case run-time
 complexity of binary search algorithm?","O(n2)","O(n)","O(1)","O(nlogn)","O(n)",1);
insert into questions values(null,"DSA","Queue data structure works on","LIFO","FILO","FIFO","None of the above","FIFO",2);
insert into questions values(null,"DSA","Visiting root node after 
	visiting left and right sub-trees is called","In order","Pre order","Post order","Out order","Post Order",3);
insert into questions values(null,"DSA","What about recursion is true 
	in comparison with iteration?","memory wise expensive","low performance","high time complexity",
		"All of the above","memory wise expensive",4);
insert into questions values(null,"DSA","Quick sort running time depends 
	on the selection of","size of array","pivot","sequence of values","None of the above","size of array",17);
insert into questions values(null,"DSA","Program with highest run-time complexity is",
	"Tower of Hanoi","Fibonacci Series","Prime Number Series","None of the Above","Fibonacci Series",18);
insert into questions values(null,"DSA","The following formula will produce 
	: Fn = Fn-1 + Fn-2 ","Armstrong No","Prime No","Fibonacci Series","Euler No","Fibonacci Series",19);
insert into questions values(null,"DSA","Which of the sorting algorithms
	can be used to sort linked list with min time complexity?","Insertion","Quick","Heap","Merge","Merge",20);
insert into questions values(null,"DSA","The number of comparisons in worst case
	needed to search a singly linked list","2n","n","logn","n2","n",33);
insert into questions values(null,"DSA","What is the time complexity of
 Quick Sort","O(n)","O(n2)","O(nlogn)","O(logn)","O(nlogn)",34);
 select * from questions where category="DSA";
 select * from user where username = "rohan" and password = "rohan123";

insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",5);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",6);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",7);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",8);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",21);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",22);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",23);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",24);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",35);
insert into questions values(null,"OOP","question1","option1","option1","option1","option1","answer",36);

insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",9);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",10);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",11);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",12);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",25);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",26);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",27);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",28);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",37);
insert into questions values(null,"CN","question1","option1","option1","option1","option1","answer",38);

insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",13);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",14);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",15);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",16);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",29);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",30);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",31);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",32);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",39);
insert into questions values(null,"DB","question1","option1","option1","option1","option1","answer",40);

select * from questions where category in ("DSA","OOP") limit 10;


