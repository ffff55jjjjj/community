create table QUESTIONREPLY
(
	id int auto_increment,
	questionId int auto_increment,
	replyerId int not null,
	replyText text,
	constraint QUESTIONREPLY_pk
		primary key (questionId)
);

