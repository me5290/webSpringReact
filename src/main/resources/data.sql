insert into member(memail , mpassword , mname) values ('qwe1' , '1234' , '유재석'),
    ('qwe2' , '1234' , '강호동'),
    ('qwe3' , '1234' , '이수근'),
    ('qwe4' , '1234' , '박명수'),
    ('qwe5' , '1234' , '신동엽');

insert into board(bcontent,mno_fk) values("게시물내용1",1),
    ("게시물내용2",2),
    ("게시물내용3",3),
    ("게시물내용4",4),
    ("게시물내용5",1),
    ("생매장 시점",3);

insert into reply(rcontent,bno_fk,mno_fk) values('댓글1' , 1 , 2),
    ('댓글2' , 1 , 3),
    ('댓글3' , 3 , 2),
    ('댓글4' , 4 , 2),
    ('댓글5' , 3 , 1),
    ('댓글6' , 3 , 4);

insert into file(bfile,bno_fk) values('1.jpg' , 1 ),
    ('2.jpg' , 1 ),
    ('3.jpg' , 2 ),
    ('4.jpg' , 3 ),
    ('5.jpg' , 4 ),
    ('6.jpg' , 5 ),
    ('7.jpg' , 6 );