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
    pay_delete_yn CHAR(1) NOT NULL COMMENT '결제취소여부',
    CHECK (pay_delete_yn IN ('Y', 'N')),
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

INSERT INTO faq_tbl (faq_code, faq_title, faq_detail)
    VALUES (1, '배송 기간이 어떻게 되나요?', '결제 후 영업일 기준 약 1-8주로 제품별 상이하며 제주 도서 산간지역은 추가 배송기간이 소요됩니다. 상품 페이지에서 평균 배송기간 및 운송수단 확인이 가능합니다. 주문 제품이나 제작상의 이유 및 배송과 기상 상황 등으로 배송 기간은 유동적일 수 있습니다'),
           (2, '먼저 주문하고, 입주 후 받을 수 있나요', '평균 배송기간 이후부터의 평일 중 희망 배송주간 신청이 가능하며 시간 지정은 불가합니다. 제품 파손의 우려가 있는 이사 당일에는 배송이 어렵습니다'),
           (3, '화이트와 오이스터 컬러 중 고민입니다', '‘화이트’는 산뜻한 대비감으로 공간 속 포인트 컬러가 되어줍니다. ''오이스터''는 부담 없이 유니크한 연그레이 특유의 부드러운 무드를 선사합니다. 두 컬러 모두 오염이나 충격 방어력이 우수한 클린터치 소재라 생활 관리가 쉽습니다.'),
           (4, '상판쉐입 오벌과 사각 중 고민입니다.', '사각 상판은 특유의 단정함으로 클래식한 매력을 가집니다. 오벌은 시각적으로 여백을 주며 곡면까지 둘러앉을 수 있기에 사이즈 대비 실용적입니다. 기존에 사각 상판을 써보셨다면 매직볼트 테이블을 기회로 새로운 오벌 쉐입도 추천드립니다.'),
           (5, '클립 테이블 사이즈를 추천해 주세요.', '한 면에 2명씩 앉을 수 있는 4-6인용으로 클립 테이블 1700 사이즈부터 추천드립니다. 정원형 쉐입의 클립 테이블 라운드는 3-4인용으로 1000Φ를, 4-5인용은 1100Φ를 추천드립니다. 더 궁금하신 점 무브먼트랩 고객센터(1644-2709)와 매직볼트 카카오톡 채팅상담을 통해 친절히 안내드리겠습니다.'),
           (6, '테이블에 맞는 벤치 사이즈를 추천해 주세요.', '자사 클립 테이블 기준으로, 테이블 1400-1700 사이즈에는 매직볼트 벤치 1200-1390mm를, 테이블 2000 사이즈부터는 벤치 1500-1690mm를 추천드립니다. 더 궁금하신 점 무브먼트랩 고객센터(1644-2709)와 매직볼트 카카오톡 채팅상담을 통해 친절히 안내드리겠습니다.'),
           (7, '상판을 다른 색으로 바꿔보고 싶어요.', '첫 구매한 테이블 컬러를 충분히 즐기시다가 새로운 컬러로 상판 변경이 가능합니다. 공식 사이트에서 상판만 별도 구매 후 전문 기사님이 새 상판으로 교체해 드립니다. 매직볼트만의 견고한 프레임 제작 기술로 취향껏 즐겨보세요!');

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
       (1, 'NANCY Chair', '/uploadedFiles/img/7288d43c0cd840549a59778b0d49f14c.jpg', '/uploadedFiles/img/7288d43c0cd840549a59778b0d49f14c_thumbnail.jpg', 31200, '주문 후 2-4주 소요, 택배출고', 0, '446W x 546D x 775H', '좌판소재 : 천연 건식 무늬목(Veneer), 자작나무 합판', '입체감을 자랑하는 착좌감. \n가볍고 컴팩트하게 다듬어진 낸시체어는 \n변하지 않는 실용성과 아름다움, \n그리고 내구성을 추구합니다', 'N'),
       (1, 'Oliver Bar Stool', '/uploadedFiles/img/f9c240bfee944d43a2a1fd8b6d197d66.jpg', '/uploadedFiles/img/f9c240bfee944d43a2a1fd8b6d197d66_thumbnail.jpg', 15800, '주문 후 2-4주 소요, 택배출고', 0, '456W x 456D x 660H', '좌판소재 : 천연 건식 무늬목(Veneer), 자작나무 합판', '다양한 컬러와 질감의 E0 자작나무 합판과 \n스테인리스 스틸 (SUS304)  다리로 제작된 \n올리버 바 스툴은 가벼운 경쾌함과 묵직한 \n안정감을 동시에 연출합니다.', 'N'),
       (1, 'Modern Chair', '/uploadedFiles/img/bbce4e88f46d426588b671a3b0b4376d.png', '/uploadedFiles/img/bbce4e88f46d426588b671a3b0b4376d_thumbnail.png', 49000, '주문 후 2-4주 소요, 택배출고', 0, '450W x 410D x 850H', '가죽 : Semi Aniline 프레임 : 스테인리스 스틸 (SUS304)', '투명한 광택을 자랑하는 스테인리스 스틸, \n그리고 고급스러운 디자인의 \n모던체어는 깔끔한 분위기를 연출해줍니다.', 'N'),
       (2, 'JELLO Sofa Module', '/uploadedFiles/img/347b1be6379849e296ece96452fc51d3.jpg', '/uploadedFiles/img/347b1be6379849e296ece96452fc51d3_thumbnail.jpg', 80000, '주문 후 3-6주 소요', 0, '1000W x 900D x 700H', '패브릭 : ALTA 패브릭, 세미 아닐린 가죽', '균일하고 일정하게 흐르는 곡선, 그리고 장식적 요소가 억제된 선명한 형태. \nJELLO를 통해 표현하는 디자인 언어는 ‘부드러움’입니다. \n부드러운 젤리가 좌석을 감싸는 듯한 형상은 안락한 착좌감을 강조하며 컬러보다는 질감이 강조된 원단과 가죽으로 마감되는 표면은 일상의 공간에 반영될 모두의 취향을 아름답게 표현합니다.', 'N'),
       (2, 'Bolster Sofa Module', '/uploadedFiles/img/7ab9ff08e5fc488e951129ca5acdf775.jpg', '/uploadedFiles/img/7ab9ff08e5fc488e951129ca5acdf775_thumbnail.jpg', 84000, '주문 후 3-6주 소요', 0, '1000W x 1000D x 650H', '패브릭 : Como Pro  가죽 : Semi Aniline  프레임 : 스테인리스 스틸 (SUS304)', '네모반듯한 육각형에 동그랗고 푹신한 사이드 쿠션이 주변을 감싸고 도는 볼스터 소파.\n직선이 강조된 모듈러형 볼스터 소파에 가미된 디테일과 \n부드러운 볼륨감은 다채로운 매력을 일상에 불어넣습니다.', 'N'),
       (2, 'MARINA Sofa', '/uploadedFiles/img/f3fba1dcaae845a5b65a2051089e0bbb.jpg', '/uploadedFiles/img/f3fba1dcaae845a5b65a2051089e0bbb_thumbnail.jpg', 177000, '주문 후 3-6주 소요', 0, '860W x 880D x 810H', '소재 : 천연 건식 무늬목, 스테인리스 스틸 (SUS304) ', '경쾌하면서도 안락한 디자인.\n클래식 보트를 연상시키는 소재와 질감의 매치로\n모든 각도에서 특유의 매력을 발산합니다.', 'N'),
       (3, 'HEMNES Storage Bed', '/uploadedFiles/img/a1819c0981854e858152214371379925.jpg', '/uploadedFiles/img/a1819c0981854e858152214371379925_thumbnail.jpg', 170000, '주문 후 3-6주 소요', 0, 'W 1020 * H 950 * D 2085 (mm)', '철제, 고무나무 원목', '튼튼한 철제프레임에 진한 나뭇결의 느낌을 더한 베드보드로 \n침실에 고급스러운 분위기를 연출해주는 수납침대입니다.', 'N'),
       (3, 'BREEZ Motion Bed', '/uploadedFiles/img/b568fae3bc30487a8ce0bea576ad7d6d.jpeg', '/uploadedFiles/img/b568fae3bc30487a8ce0bea576ad7d6d_thumbnail.jpeg', 350000, '주문 후 3-6주 소요', 0, 'W 1020 * H 950 * D 2085 (mm)', '스테인리스 스틸 (SUS304), 천연 건식 무늬목', '3개의 모터로 제작되어 다양한 각도 조절이 가능하며 \n다양한 각도로 휴식과 수면을 제공해주는 모션 베드입니다.', 'N'),
       (3, 'MALM Lighting Bed', '/uploadedFiles/img/5ac95b0754e14a59a9f9c80da8f290e4.jpg', '/uploadedFiles/img/5ac95b0754e14a59a9f9c80da8f290e4_thumbnail.jpg', 250000, '주문 후 3-6주 소요', 0, 'W 1135 * H 2175 * D 1110 (mm)', '고무나무 원목, 무늬목', '3개의 서랍이 있어 효율적으로 수납할 수 있고, \n은은한 조명등이 있어 침실 분위기를 아늑하고 \n편안하게 만들어주는 침대입니다.', 'N'),
       (4, 'CLIP Round Table Magicor', '/uploadedFiles/img/94c688c97e094e2eaae8f0c920480fc2.jpg', '/uploadedFiles/img/94c688c97e094e2eaae8f0c920480fc2_thumbnail.jpg', 77850, '주문 후 3-6주 소요', 0, '1000W x 1000D x 740H', '상판소재 : 클린터치, 자작합판', 'HPL과 클린터치 대비 4배 이상 두꺼운 마감 층을 자랑하는 상판 소재는 \n극도로 높아진 내구성을 자랑하며 \n세련되게 다듬어진 형상으로 각 사이즈에서 더 넓은 폭의 공간을 제공합니다.', 'N'),
       (4, 'Plank Table', '/uploadedFiles/img/74afb6af2d6b4775895a3505b36b1141.jpg', '/uploadedFiles/img/74afb6af2d6b4775895a3505b36b1141_thumbnail.jpg', 93650, '주문 후 3-6주 소요', 0, '2100W x 770D x 740H', '클린터치, HPL, 천연 건식 무늬목, 자작나무 합판', '차원이 다른 특유의 디테일과 \n견고함을 실용적인 테이블에 녹여낸 플랭크 테이블.', 'N'),
       (4, 'Stick Round Table', '/uploadedFiles/img/a2eb26193b4d402697d385e8e45e33fc.jpg', '/uploadedFiles/img/a2eb26193b4d402697d385e8e45e33fc_thumbnail.jpg', 66200, '주문 후 3-6주 소요', 0, '1200Φ x 740H', '상판소재 : 천연 건식 무늬목, 클린터치, 자작나무 합판', '테이블 탑을 지지하는 네 개의 막대기. \n오크 특유의 강성을 간결하고 우아한 구조로 표현한 스틱 테이블은 \n궁극의 미니멀리즘을 상징합니다.', 'N'),
       (4, 'SWIRL Round Table', '/uploadedFiles/img/1c5830827bbe4b3db1f46f46fcd0d103.jpg', '/uploadedFiles/img/1c5830827bbe4b3db1f46f46fcd0d103_thumbnail.jpg', 12600, '주문 후 3-6주 소요', 0, '1000W x 1000D x 740H', '상판소재 : 클린터치 보드', '회전하는 듯한 형상의 다리, \n그리고 구조가 숨겨진 상판. \n스월은 테이블이 가질 수 있는 가장 심플한 형태에 \n유기적이고 동적인 리듬감을 더합니다.', 'N'),
       (4, 'Alex Table', '/uploadedFiles/img/aa255f5e37724665adc819dac14db221.jpg', '/uploadedFiles/img/aa255f5e37724665adc819dac14db221_thumbnail.jpg', 54000, '주문 후 3-6주 소요', 0, '572 x 602 x 740H', '상판소재 : 클린터치, HPL, 천연 건식 무늬목, 자작나무 합판', 'Alexander Calder의 드로잉에서 영감을 받은 알렉스는 \n비정형의 형상을 매직볼트® 특유의 컬러와 품질, 그리고 \n304 스테인리스 스틸이 뽐내는 투명함으로 \n오랜 시간 질리지 않는 아름다움을 공간에 더합니다.', 'N'),
       (4, 'AMETHYST Dining Table', '/uploadedFiles/img/cff230d1a7c4488c88e8e27126596d15.jpeg', '/uploadedFiles/img/cff230d1a7c4488c88e8e27126596d15_thumbnail.jpeg', 65000, '주문 후 3-6주 소요', 0, '42"d x 42"w x 29.5"h', '상판소재 : 클린터치, 스테인리스 스틸 (SUS304)', '304 스테인리스 스틸이 뽐내는 투명함과 \n 슬림한 디자인으로 어느 공간에서든 스타일리쉬한 분위기를 연출해줄 수 있는 테이블입니다.', 'N'),
       (5, 'Pond of Light x bluehour', '/uploadedFiles/img/e166464f80dc4ed7bc60f85d302ff5ff.jpg', '/uploadedFiles/img/e166464f80dc4ed7bc60f85d302ff5ff_thumbnail.jpg', 32000, '주문 후 약 8-10주 평일 직배송', 0, 'SM 670W x 1000H =', '100% Pure Wool from New Zealand', '독창적인 형상과 컬러 조합으로 \n기존의 러그에서는 볼 수 없었던 색다른 매력을 발산합니다.', 'N'),
       (5, 'Finn Side Console', '/uploadedFiles/img/5c5e0faa35c54a628b3d9eb071f7d841.jpg', '/uploadedFiles/img/5c5e0faa35c54a628b3d9eb071f7d841_thumbnail.jpg', 40000, '주문 후 3-6주 소요', 0, '430W x 420D x 520H', '소재 : 클린터치, HPL, 천연 건식 무늬목, 자작나무 합판', '북유럽 감성의 클래식한 디자인이 \n컬러풀한 언어로 재해석되어 \n모던한 매력을 선보입니다.', 'N'),
       (5, 'TUBE Mirror', '/uploadedFiles/img/83d395a92a554a9f83383f8a2aca0834.jpg', '/uploadedFiles/img/83d395a92a554a9f83383f8a2aca0834_thumbnail.jpg', 45000, '주문 후 3-6주 소요', 5000, '634W x 34D x 2029H', '스테인리스 스틸 (SUS304), 고밀도 MDF', '깊은 빛을 자랑하는 스테인리스 스틸과 5mm 두께의 거울을 견고하게 받치는 스테인리스 프레임, \n그리고 수축 및 팽창이 없는 고밀도 MDF의 사용으로 특유의 내구성을 자랑합니다.', 'N'),
       (5, 'Fluid Rug', '/uploadedFiles/img/8592b044b7c84bf2a9dc2e900d2c4543.jpg', '/uploadedFiles/img/8592b044b7c84bf2a9dc2e900d2c4543_thumbnail.jpg', 51900, '주문 후 약 8-10주 평일 직배송', 0, '1000W x 1000H', '100% Pure Wool from New Zealand', '플루이드 러그는 비정형의 형상이 자아내는 분위기로 \n모든 공간을 더욱 특별하게 만들어줍니다.', 'N'),
       (5, 'Bubble Dresser', '/uploadedFiles/img/81d22269887444f9aafbd7111a00eca5.jpg', '/uploadedFiles/img/81d22269887444f9aafbd7111a00eca5_thumbnail.jpg', 69000, '주문 후 3-6주 소요', 0, '520W x 420D x 1571H', '소재 : 클린터치, HPL, 자작나무 합판', '단순한 서랍장에서 조형적 의미를 추구합니다. \n넉넉한 수납공간은 터치-오픈식 서랍으로 개방되며 \n어느 공간에서든 위트와 따스함이 넘치는 형태로 \n모두의 시선을 사로잡습니다.', 'N'),
       (5, 'MAREN Stand Lighting', '/uploadedFiles/img/0425b373d2e4453e999548732cf71a73.jpeg', '/uploadedFiles/img/0425b373d2e4453e999548732cf71a73_thumbnail.jpeg', 36000, '주문 후 2-4주 소요, 택배출고', 0, '24 * 39 * 44 (cm)', '소재 : 스테인리스 스틸 (SUS304)', '모던함을 담은 디자인이 매력적인 \nMAREN 스탠드 조명은 블랙의 시크함이 더해져 \n현대적인 오브제 같은 조명입니다.', 'N');

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