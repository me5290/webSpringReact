package web.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity // 해당 클래스와 연동DB내 테이블과 매핑/연결 (ORM)
@Builder
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class BoardEntity { // 테이블
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
//    @Column(name = "title", length = 10, nullable = false , unique = true)
    private String btitle;

    @JoinColumn // fk
    @ManyToOne // 다수가 하나에게
    private MemberEntitiy memberEntitiy;

//    private boolean 필드0;
//
//    @Column
//    private byte 필드1;
//    private short 필드2;
//    private long 필드3;
//
//    private char 필드4;
//    private float 필드5;
//    private double 필드6;
//
//    private Date 필드7;
//    private LocalDateTime 필드8;
}

/*
    Member
@Entity
@Table(name="member")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int mno;
    @Column(nullable = false,unique = true,length = 12)
    private String mid;
    @Column(nullable = false,length = 16)
    private String mpw;
    @Column(nullable = false,length = 20)
    private String mname;
    @Column(nullable = false,unique = true,length = 30)
    private String memail;
    @Column(nullable = false,unique = true,length = 14)
    private String mphone;
    @Column(nullable = false,length = 8)
    private String mbirth;
    @Column(nullable = false,length = 2)
    private String msex;
    @Column(nullable = false,length = 30)
    private String maddress;

    // private LocalDateTime mdate;
    @Column(columnDefinition = "datetime default now()")
    private String mdate;


    @Column(columnDefinition = "varchar(255) default 'default.jpg'")
    private String mimg;
    @Column(columnDefinition = "int default 0")
    private int mstate;
}
Store
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int sno;
    @Column(nullable = false, columnDefinition = "varchar(50)", unique = true)
    private String sname;
    @Column(columnDefinition = "varchar(13)")
    private String sphone;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg1;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg2;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg3;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg4;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String sadress ;
    @Column(nullable = false, columnDefinition = "text")
    private String scontent ;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int sstate;
    @Column(nullable = false, columnDefinition = "varchar(20)", unique = true)
    private String snumber;
    @Column(nullable = false)
    private int categorya;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int categoryb;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String slat;
    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String slng;
    @Column(nullable = false, columnDefinition = "char(8) default '12345678'")
    private String scode ;
    @Column(nullable = false, columnDefinition = "int unsigned")
    private int mno;
}
Board
public class BoardEntity2 {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno; //unsigned auto_increment primary key,   # 게시글 식별번호PK

    @Column(nullable = false , columnDefinition = "varchar(20)")
    private String bname;// varchar(20) not null,                  # 게시글 제목

    @Column(columnDefinition = "longtext")
    private String bcontent;// longtext,                        # 게시글 내용(사진,글가능)

    @Column(columnDefinition = "int default 0")
    private int bcount; // int default 0,          # 게시글 조회수

    @Column(columnDefinition = "datetime default now()")
    private String bdate; // datetime default now(),    # 게시글 최초등록날짜

    @Column(columnDefinition = "int unsigned")
    private int mno;//   int unsigned    # 등록한 사람 회원번호

    //@Column(nullable = true)
    private int categorya; // int not null,      # 카테고리 지역 (인덱스번호로 식별함)
    @Column(columnDefinition = "int default 0")
    private int categoryb;// int default 0

}
Reply
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rpno; //int auto_increment primary key         # 댓글번호
    @Column(nullable = false, length = 100)
    private String rpcontent; //    varchar(100) not null,   # 댓글내용
    @Column(columnDefinition = "datetime default now()")
    private String rpdate;  //default now(),      # 댓글 최초등록일
    @Column(columnDefinition = "int unsigned")
    private int mno; //int unsigned,               # 댓글작성자 FK
    @Column(columnDefinition = "int unsigned")
    private int bno; // int unsigned,               # 게시글 식별번호 FK
    @Column(nullable = false, columnDefinition = "int unsigned default 0")
    private int rpindex; // unsigned default 0 not null,
}

*/