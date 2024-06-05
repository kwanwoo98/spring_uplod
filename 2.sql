CREATE TABLE member (
    member_id VARCHAR(50) PRIMARY KEY,
    member_pw VARCHAR(50) NOT NULL,
    name VARCHAR(50),
    phone VARCHAR(20),
    Email1 VARCHAR(50),
    Email2 VARCHAR(50),
    gender VARCHAR(5) CHECK (gender IN ('Male', 'Female')),
    agree BOOLEAN,
    create_date DATE DEFAULT CURRENT_DATE
);
tbl_member
CREATE TABLE notice (
    no INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    count INT,
    create_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE program (
    no INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    text VARCHAR(300),
    subrtext VARCHAR(300),
    schedule VARCHAR(100),
    img VARCHAR(300),
    create_date DATE DEFAULT CURRENmemberT_DATE
);



SELECT * FROM member;

SELECT * FROM notice;

SELECT * FROM program;

COMMIT;


INSERT INTO member (member_id, member_pw, name, phone, Email1, Email2, gender, agree, create_date) 
VALUES 
('test', '1234', 'test', '01012345678', 'test', 'test@test.test', 'Male', TRUE, CURDATE());




INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('이응노 미술관', '고암 이응노 화백의 예술세계와 작품들을 감상할 수 있다.', '대전에 위치한 이응노 미술관에서는 대전과 파리, 한국과 프랑스, 아시아와 유럽의 커뮤니케이션이 일어난다.', '2019.01 ~ 2019.03', 'img_place_01.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('김유정문학촌', '강원도 춘천시에서는 김유정이 우리나라 제일의 소설가인 것처럼 정성을 다해 김유정 문학촌을 꾸몄다.', '기념관은 물론이고 소설의 마지막 장면을 실제크기 동상으로 재연하기도 했다. 우리를 감동시킨 많은 다른 문학가, 예술인들도 해당 지자체에서 성의를 다해 기렸으면 하는 바램이다.', '2019.01 ~ 2019.03', 'img_place_02.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('책과 인쇄 박물관', '생각보다 작은 공간이지만 활자가 문명에 끼친 어마어마한 영향력이 초판본들에서 느껴진다.', '박물만 볼 수 있는 것이 아니라 아직까지도 그 시절에 인쇄 과정을 손수 체험 할 수 있는 시간을 제공하고 있다.', '2019.01 ~ 2019.03', 'img_place_03.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('백사실 현통사', '서울 종로구 부암동에서는 주택가에서 길 하나만 건너 들어가면 깨끗하고 맑은 계곡을 걸을 수 있다. ', '백사실 가는 길에 사찰도 있어주지만 더 들어가면 조선후기 별서 백석동천을 만날 수 있다.', '2018.11 ~ 2018.12', 'img_place_04.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('통영국제음악당', '작곡가 윤이상을 기리는 음악당으로 소리와 함께 수려한 경치도 즐길 수 있다.', '봄과 가을에 통영국제음악제가 열리며 가을에는 윤이상국제음악콩쿠르가 열린다.', '2019.01 ~ 2019.03', 'img_place_05.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('아침고요수목원', '경기도 가평군 축령산에는 조용한 아침의 나라의 아기자기한 정원이 있다.', '너무 거대하지 않으면서도 수려한 산림욕장을 원한다면 아침고요수목원을 산책하라.', '2019.01 ~ 2019.02', 'img_place_06.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('제천 리솜포레스트', '제천에는 촬영지 이상의 자연 친화 팬션이 있다. ', '리솜 포레스트는 아름드리 피톤치드 정글속에 모던한 빌라 하나씩을 꽂아 놓은 듯한 구조를 하고 있다.', '2019.02 ~ 2019.03', 'img_place_07.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('정동진', '동해 바다를 가장 가까운 코스로 가고 싶다면 역시 정동진.', '정동진역은 세계에서 가장 바닷가에 가까운 기차역으로 기네스북에 등재되어 있다고 한다.', '2019.04 ~ 2019.05', 'img_place_08.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('센트럴파크', '센트럴파크는 뉴욕을 방문할 때 빼놓을 수 없는 코스이다.', '여름이면 여름, 겨울이면 겨울 어느 때라도 실망시키지 않는 영화와 현실의 명소이다.', '2019.04 ~ 2019.04', 'img_place_09.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('석파정', '흥선대원군 별서에 딸린 정자로서 서울미술관 내에 있다.', '자연과 역사, 현대미술을 동시에 원한다면 석파정으로 오라.', '2019.06 ~ 2019.06', 'img_place_10.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('뉴욕공립도서관', '뉴욕에는 미술관이나 박물관처럼 보이는 도서관이 있다.', '외관 뿐 아니라 내부도 성이나 성당 못지 않은 중후한 멋을 뽐내는 이 곳은 주만들이 무료로 이용할 수 있는 도서관이다.', '2019.07 ~ 2019.07', 'img_place_11.jpg');
INSERT INTO program (title, text, subrtext, schedule, img) VALUES ('강천산 계곡', '보성 녹차밭 근처에는 최초의 군립공원이 있다.', '산으로 올라가는 길에는 맨발로 걸을 수 있는 황톳길이 있고 꼭대기에는 다리가 떨리는 구름다리가 있다.', '2019.07 ~ 2019.08', 'img_place_12.jpg');


LOAD DATA INFILE '/java_web/webtest1/src/main/webapp/programdata.csv'
INTO TABLE program
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(title, text, subrtext, schedule, img);


