create table board(
	num number,
	name varchar2(20),
	pass varchar2(15),
	subject varchar2(50),
	content varchar2(2000),
	files varchar2(50),
	re_ref number,
	re_lev number,
	re_seq number,
	readcount number,
	regdate date,
	primary key(num)
);