
CREATE TABLE score (
stu_num NUMBER(10),
stu_name VARCHAR(20) NOT NULL,
kor NUMBER(3)NOT NULL,
eng NUMBER(3)NOT NULL,
math NUMBER(3)NOT NULL,
total NUMBER(3),
average NUMBER(5,2),--소수점 자리 포함해서 작성100.00
CONSTRAINT pk_score PRIMARY KEY(stu_num)
);


--연속된 숫자생성, values넣을 때 .nextval쓰면 자동으로 숫자 커져서 들어감
CREATE SEQUENCE seq_score;

SELECT * FROM score;

INSERT INTO score VALUES(seq_score.nextval, '홍길동', 90,90,90,270,90);
INSERT INTO score VALUES(seq_score.nextval, '김철수', 80,80,80,240,80);
INSERT INTO score VALUES(seq_score.nextval, '박영희', 100,100,100,300,100);
COMMIT;--커밋 꼭 해야함!!!
