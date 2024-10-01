DROP TABLE IF EXISTS review_tbl;
DROP TABLE IF EXISTS reply_tbl;
DROP TABLE IF EXISTS inquiry_tbl;
DROP TABLE IF EXISTS pay_detail_tbl;
DROP TABLE IF EXISTS pay_tbl;
DROP TABLE IF EXISTS cart_tbl;
DROP TABLE IF EXISTS product_tbl;
DROP TABLE IF EXISTS category_tbl;
DROP TABLE IF EXISTS member_tbl;
DROP TABLE IF EXISTS bank_tbl;
DROP TABLE IF EXISTS card_company_tbl;
DROP TABLE IF EXISTS faq_tbl;

CREATE TABLE IF NOT EXISTS member_tbl (
    member_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '회원코드',
    member_id VARCHAR(50) NOT NULL COMMENT '회원아이디',
    member_pw VARCHAR(150) NOT NULL COMMENT '회원비밀번호',
    member_name VARCHAR(100) NOT NULL COMMENT '회원이름',
    member_email VARCHAR(100) NOT NULL COMMENT '회원이메일',
    member_phone VARCHAR(30) NOT NULL COMMENT '회원전화번호',
    member_address VARCHAR(255) NOT NULL COMMENT '회원배송주소',
    member_role VARCHAR(20) NOT NULL COMMENT '회원권한',
    member_delete_yn CHAR(1) NOT NULL COMMENT '회원탈퇴여부',
    CHECK (member_delete_yn IN ('Y', 'N'))
) ENGINE=INNODB COMMENT '회원';

CREATE TABLE IF NOT EXISTS category_tbl (
    category_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리코드',
    category_name VARCHAR(50) NOT NULL COMMENT '카테고리명'
) ENGINE=INNODB COMMENT '카테고리';

CREATE TABLE IF NOT EXISTS product_tbl (
    product_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '상품코드',
    category_code INT NOT NULL COMMENT '카테고리코드',
    product_name VARCHAR(100) NOT NULL COMMENT '상품명',
    product_image_original VARCHAR(255) NOT NULL COMMENT '상품이미지',
    product_image_thumbnail VARCHAR(255) NOT NULL COMMENT '상품썸네일',
    product_price INT NOT NULL COMMENT '상품가격',
    product_deliver_time VARCHAR(50) NOT NULL COMMENT '상품배송기간',
    product_deliever_cost INT NOT NULL COMMENT '상품배송비',
    product_size VARCHAR(100) NOT NULL COMMENT '상품사이즈',
    product_material VARCHAR(100) NOT NULL COMMENT '상품소재',
    product_description TEXT NOT NULL COMMENT '상품설명',
    product_delete_yn CHAR(1) NOT NULL COMMENT '상품삭제여부',
    FOREIGN KEY (category_code) REFERENCES category_tbl (category_code),
    CHECK (product_delete_yn IN ('Y', 'N'))
) ENGINE=INNODB COMMENT='상품';

CREATE TABLE IF NOT EXISTS cart_tbl (
    product_code INT COMMENT '상품코드',
    member_code INT COMMENT '회원코드',
    cart_cnt INT NOT NULL COMMENT '수량',
    cart_price INT NOT NULL COMMENT '상품가격',
    PRIMARY KEY (product_code, member_code),
    FOREIGN KEY (product_code) REFERENCES product_tbl (product_code),
    FOREIGN KEY (member_code) REFERENCES member_tbl (member_code)
) ENGINE=INNODB COMMENT '장바구니';

CREATE TABLE IF NOT EXISTS card_company_tbl (
    card_company_code INT PRIMARY KEY COMMENT '카드사코드',
    card_company_name VARCHAR(50) COMMENT '카드사명'
) ENGINE=INNODB COMMENT '카드사';

CREATE TABLE IF NOT EXISTS bank_tbl (
    bank_code INT PRIMARY KEY COMMENT '은행코드',
    bank_name VARCHAR(50) COMMENT '은행명'
) ENGINE=INNODB COMMENT '은행';

CREATE TABLE IF NOT EXISTS pay_tbl (
    pay_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '결제코드',
    member_code INT NOT NULL COMMENT '회원코드',
    pay_date VARCHAR(30) NOT NULL COMMENT '결제일자',
    pay_total_cnt INT NOT NULL COMMENT '총결제수량',
    pay_total_price INT NOT NULL COMMENT '총결제금액',
    pay_address VARCHAR(255) NOT NULL COMMENT '배송주소',
    pay_receiver VARCHAR(50) NOT NULL COMMENT '받는분성함',
    pay_deliver_phone VARCHAR(30) NOT NULL COMMENT '받는분연락처',
    pay_deliever_status VARCHAR(30) NOT NULL COMMENT '배송상태',
    pay_refund_yn CHAR(1) NOT NULL COMMENT '결제취소여부',
    pay_account_number VARCHAR(100) COMMENT '계좌번호',
    pay_card_number VARCHAR(100) COMMENT '카드번호',
    bank_code INT COMMENT '은행코드',
    card_company_code INT COMMENT '카드사코드',
    FOREIGN KEY (member_code) REFERENCES member_tbl (member_code),
    FOREIGN KEY (bank_code) REFERENCES bank_tbl (bank_code),
    FOREIGN KEY (card_company_code) REFERENCES card_company_tbl (card_company_code),
    CHECK (pay_refund_yn IN ('Y', 'N'))
) ENGINE=INNODB COMMENT '결제';

CREATE TABLE IF NOT EXISTS pay_detail_tbl (
    pay_detail_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '결제상세코드',
    pay_code INT NOT NULL COMMENT '결제코드',
    product_code INT NOT NULL COMMENT '상품코드',
    pay_detail_cnt INT NOT NULL COMMENT '결제수량',
    pay_detail_price INT NOT NULL COMMENT '결제금액',
    review_yn CHAR(1) NOT NULL COMMENT '리뷰존재여부',
    FOREIGN KEY (product_code) REFERENCES product_tbl (product_code),
    FOREIGN KEY (pay_code) REFERENCES pay_tbl (pay_code)
) ENGINE=INNODB COMMENT '결제상세';

CREATE TABLE IF NOT EXISTS review_tbl (
    review_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰코드',
    pay_detail_code INT NOT NULL COMMENT '결제상세코드',
    product_code INT NOT NULL COMMENT '상품코드',
    member_code INT NOT NULL COMMENT '회원코드',
    review_title VARCHAR(100) NOT NULL COMMENT '리뷰제목',
    review_detail TEXT NOT NULL COMMENT '리뷰내용',
    review_photo_original VARCHAR(255) COMMENT '리뷰원본사진',
    review_photo_thumbnail VARCHAR(255) COMMENT '리뷰썸네일',
    review_rating INT NOT NULL COMMENT '리뷰별점',
    review_date VARCHAR(30) NOT NULL COMMENT '리뷰생성일',
    review_edit_date VARCHAR(30) COMMENT '리뷰수정일',
    FOREIGN KEY (pay_detail_code) REFERENCES pay_detail_tbl (pay_detail_code),
    FOREIGN KEY (product_code) REFERENCES product_tbl (product_code),
    FOREIGN KEY (member_code) REFERENCES member_tbl (member_code)
) ENGINE=INNODB COMMENT '리뷰';

CREATE TABLE IF NOT EXISTS inquiry_tbl (
    inquiry_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '문의코드',
    member_code INT NOT NULL COMMENT '회원코드',
    inquiry_title VARCHAR(100) NOT NULL COMMENT '문의제목',
    inquiry_detail TEXT NOT NULL COMMENT '문의내용',
    inquiry_date VARCHAR(30) NOT NULL COMMENT '문의날짜',
    inquiry_edit_date VARCHAR(30) COMMENT '문의수정일',
    FOREIGN KEY (member_code) REFERENCES member_tbl (member_code)
) ENGINE=INNODB COMMENT '문의';

CREATE TABLE IF NOT EXISTS reply_tbl (
    reply_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '답변코드',
    member_code INT NOT NULL COMMENT '회원코드',
    inquiry_code INT NOT NULL COMMENT '문의코드',
    reply_detail TEXT NOT NULL COMMENT '답변내용',
    reply_date VARCHAR(30) NOT NULL COMMENT '답변생성일',
    reply_edit_date VARCHAR(30) COMMENT '답변 수정일',
    FOREIGN KEY (member_code) REFERENCES member_tbl (member_code),
    FOREIGN KEY (inquiry_code) REFERENCES inquiry_tbl (inquiry_code)
) ENGINE=INNODB COMMENT '답변';

CREATE TABLE IF NOT EXISTS faq_tbl (
    faq_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '공지코드',
    faq_title VARCHAR(255) NOT NULL COMMENT '공지제목',
    faq_detail TEXT NOT NULL COMMENT '공지내용'
) ENGINE=INNODB COMMENT '공지';

INSERT INTO member_tbl (member_id, member_pw, member_name, member_email, member_phone, member_address, member_role, member_delete_yn)
VALUES ('admin', '$2a$10$SOW/Hm3eQC1BL/iB3YV5DOc2r6ii4EkO/ioyfi9rrpe0Bk/4oP.ym', 'admin',
        'admin@admin.com', '01000000000', 'Some Address', 'ADMIN', 'N');

INSERT INTO category_tbl (category_code, category_name)
VALUES (1, 'Chair'),
       (2, 'Sofa'),
       (3, 'Bed'),
       (4, 'Table'),
       (5, 'Accessories');

INSERT INTO product_tbl(category_code, product_name, product_image_original, product_image_thumbnail, product_price, product_deliver_time, product_deliever_cost, product_size, product_material, product_description, product_delete_yn)
VALUES (1, 'CLIP Chair', '/uploadedFiles/img/eb7afb1c658a4b0d833b75279b5c9158.jpg', '/uploadedFiles/img/eb7afb1c658a4b0d833b75279b5c9158_thumbnail.jpg', 28100, '주문 후 2-4주 소요, 택배출고', 0, '425W x 552D x 752H', '좌판소재 : 아쿠아클린 패브릭, 세미아날린 가죽', '투명한 광택을 자랑하는 스테인리스 스틸, \n그리고 깊이가 느껴지는 블랙 분체도장 마감의 프레임은 클래식한 향수와 현대적 감성을 동시에 불러일으킵니다.', 'N'),
       (1, 'Burt Chair', '/uploadedFiles/img/aaf5e64924a24c8d92443166cb9a3521.jpeg', '/uploadedFiles/img/aaf5e64924a24c8d92443166cb9a3521_thumbnail.jpeg', 30200, '주문 후 2-4주 소요, 택배출고', 0, '452W x 552D x 725H', '좌판소재 : 클린터치, HPL, 천연 건식 무늬목, 자작나무 합판', '특유의 조형미를 가진 버트 체어는 \n다양한 컬러와 질감의 좌판과 자작나무, 그리고 \n스테인리스 프레임 (SUS304)의 조화가 어우러져 \n클래식하면서도 모던한 분위기로 모든 공간에서 \n빛을 발합니다.', 'N'),
       (1, 'Oliver Stool', '/uploadedFiles/img/f6c4999de4674c719b1529f0248ab45f.jpg', '/uploadedFiles/img/f6c4999de4674c719b1529f0248ab45f_thumbnail.jpg', 30700, '주문 후 2-4주 소요, 택배출고', 0, '430W x 430D x 457H ', '좌판소재 : 클린터치, HPL, 천연 건식 무늬목, 자작나무 합판', '다양한 컬러와 질감의 E0 자작나무 합판과 \n스테인리스 스틸 (SUS304)  다리로 제작된 \n올리버 스툴은 가벼운 경쾌함과 묵직한 \n안정감을 동시에 연출합니다.', 'N'),
       (2, 'JELLO Sofa Module', '/uploadedFiles/img/347b1be6379849e296ece96452fc51d3.jpg', '/uploadedFiles/img/347b1be6379849e296ece96452fc51d3_thumbnail.jpg', 80000, '주문 후 3-6주 소요', 0, '1000W x 900D x 700H', '패브릭 : ALTA 패브릭, 세미 아닐린 가죽', '균일하고 일정하게 흐르는 곡선, 그리고 장식적 요소가 억제된 선명한 형태. \nJELLO를 통해 표현하는 디자인 언어는 ‘부드러움’입니다. \n부드러운 젤리가 좌석을 감싸는 듯한 형상은 안락한 착좌감을 강조하며 컬러보다는 질감이 강조된 원단과 가죽으로 마감되는 표면은 일상의 공간에 반영될 모두의 취향을 아름답게 표현합니다.', 'N'),
       (2, 'Bolster Sofa Module', '/uploadedFiles/img/7ab9ff08e5fc488e951129ca5acdf775.jpg', '/uploadedFiles/img/7ab9ff08e5fc488e951129ca5acdf775_thumbnail.jpg', 84000, '주문 후 3-6주 소요', 0, '1000W x 1000D x 650H', '패브릭 : Como Pro  가죽 : Semi Aniline  프레임 : 스테인리스 스틸 (SUS304)', '네모반듯한 육각형에 동그랗고 푹신한 사이드 쿠션이 주변을 감싸고 도는 볼스터 소파.\n직선이 강조된 모듈러형 볼스터 소파에 가미된 디테일과 \n부드러운 볼륨감은 다채로운 매력을 일상에 불어넣습니다.', 'N'),
       (2, 'MARINA Sofa', '/uploadedFiles/img/f3fba1dcaae845a5b65a2051089e0bbb.jpg', '/uploadedFiles/img/f3fba1dcaae845a5b65a2051089e0bbb_thumbnail.jpg', 177000, '주문 후 3-6주 소요', 0, '860W x 880D x 810H', '소재 : 천연 건식 무늬목, 스테인리스 스틸 (SUS304) ', '경쾌하면서도 안락한 디자인.\n클래식 보트를 연상시키는 소재와 질감의 매치로\n모든 각도에서 특유의 매력을 발산합니다.', 'N'),
       (3, '겟우드 수납침대 싱글', '/uploadedFiles/img/a1819c0981854e858152214371379925.jpg', '/uploadedFiles/img/a1819c0981854e858152214371379925_thumbnail.jpg', 170000, '주문 후 3-6주 소요', 0, 'W 1020 * H 950 * D 2085 (mm)', '철제, 나무', '튼튼한 철제프레임에 진한 나뭇결의 느낌을 더한 베드보드로 \n고급스러움을 배가시킨 수납침대입니다.', 'N'),
       (4, 'CLIP Round Table Magicor', '/uploadedFiles/img/94c688c97e094e2eaae8f0c920480fc2.jpg', '/uploadedFiles/img/94c688c97e094e2eaae8f0c920480fc2_thumbnail.jpg', 77850, '주문 후 3-6주 소요', 0, '1000W x 1000D x 740H', '상판소재 : 클린터치, 자작합판', 'HPL과 클린터치 대비 4배 이상 두꺼운 마감 층을 자랑하는 상판 소재는 \n극도로 높아진 내구성을 자랑하며 \n세련되게 다듬어진 형상으로 각 사이즈에서 더 넓은 폭의 공간을 제공합니다.', 'N'),
       (4, 'Plank Table', '/uploadedFiles/img/74afb6af2d6b4775895a3505b36b1141.jpg', '/uploadedFiles/img/74afb6af2d6b4775895a3505b36b1141_thumbnail.jpg', 93650, '주문 후 3-6주 소요', 0, '2100W x 770D x 740H', '클린터치, HPL, 천연 건식 무늬목, 자작나무 합판', '차원이 다른 특유의 디테일과 \n견고함을 실용적인 테이블에 녹여낸 플랭크 테이블.', 'N'),
       (4, 'Stick Round Table', '/uploadedFiles/img/a2eb26193b4d402697d385e8e45e33fc.jpg', '/uploadedFiles/img/a2eb26193b4d402697d385e8e45e33fc_thumbnail.jpg', 66200, '주문 후 3-6주 소요', 0, '1200Φ x 740H', '상판소재 : 천연 건식 무늬목, 클린터치, 자작나무 합판', '테이블 탑을 지지하는 네 개의 막대기. \n오크 특유의 강성을 간결하고 우아한 구조로 표현한 스틱 테이블은 \n궁극의 미니멀리즘을 상징합니다.', 'N'),
       (5, 'Pond of Light x bluehour', '/uploadedFiles/img/e166464f80dc4ed7bc60f85d302ff5ff.jpg', '/uploadedFiles/img/e166464f80dc4ed7bc60f85d302ff5ff_thumbnail.jpg', 32000, '주문 후 약8-10주 평일 직배송', 0, 'SM 670W x 1000H =', '100% Pure Wool from New Zealand', '독창적인 형상과 컬러 조합으로 \n기존의 러그에서는 볼 수 없었던 색다른 매력을 발산합니다.', 'N'),
       (5, 'Finn Side Console', '/uploadedFiles/img/5c5e0faa35c54a628b3d9eb071f7d841.jpg', '/uploadedFiles/img/5c5e0faa35c54a628b3d9eb071f7d841_thumbnail.jpg', 40000, '주문 후 3-6주 소요', 0, '430W x 420D x 520H', '소재 : 클린터치, HPL, 천연 건식 무늬목, 자작나무 합판', '북유럽 감성의 클래식한 디자인이 \n컬러풀한 언어로 재해석되어 \n모던한 매력을 선보입니다.', 'N'),
       (5, 'TUBE Mirror', '/uploadedFiles/img/83d395a92a554a9f83383f8a2aca0834.jpg', '/uploadedFiles/img/83d395a92a554a9f83383f8a2aca0834_thumbnail.jpg', 45000, '주문 후 3-6주 소요', 5000, '634W x 34D x 2029H', '스테인리스 스틸 (SUS304), 고밀도 MDF', '깊은 빛을 자랑하는 스테인리스 스틸과 5mm 두께의 거울을 견고하게 받치는 스테인리스 프레임, \n그리고 수축 및 팽창이 없는 고밀도 MDF의 사용으로 특유의 내구성을 자랑합니다.', 'N');

INSERT INTO bank_tbl (bank_code, bank_name)
VALUES (1, '선택하지않음'),
       (2, '우리'),
       (3, '신한'),
       (4, '국민'),
       (5, '기업'),
       (6,'농협'),
       (7,'하나'),
       (8,'SC제일'),
       (9,'우체국'),
       (10,'케이뱅크');


INSERT INTO card_company_tbl (card_company_code, card_company_name)
VALUES (1, '선택하지않음'),
       (2,'BC'),
       (3,'우리'),
       (4,'삼성'),
       (5,'롯데'),
       (6,'신한'),
       (7,'KB국민'),
       (8,'NH농협'),
       (9,'카카오뱅크'),
       (10,'씨티'),
       (11,'하나');