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


CREATE TABLE IF NOT EXISTS member_tbl (
    member_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '회원코드',
    member_id VARCHAR(50) UNIQUE NOT NULL COMMENT '회원아이디',
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
    FOREIGN KEY (product_code) REFERENCES product_tbl (product_code),
    FOREIGN KEY (pay_code) REFERENCES pay_tbl (pay_code)
) ENGINE=INNODB COMMENT '결제상세';

CREATE TABLE IF NOT EXISTS review_tbl (
    review_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰코드',
    product_code INT NOT NULL COMMENT '상품코드',
    member_code INT NOT NULL COMMENT '회원코드',
    review_title VARCHAR(100) NOT NULL COMMENT '리뷰제목',
    review_detail TEXT NOT NULL COMMENT '리뷰내용',
    review_photo_original VARCHAR(255) COMMENT '리뷰원본사진',
    review_photo_thumbnail VARCHAR(255) COMMENT '리뷰썸네일',
    review_rating INT NOT NULL COMMENT '리뷰별점',
    review_date VARCHAR(30) NOT NULL COMMENT '리뷰생성일',
    review_edit_date VARCHAR(30) COMMENT '리뷰수정일',
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
        'admin@admin.com', '010-0000-0000', 'Some Address', 'ADMIN', 'N');

INSERT INTO category_tbl (category_code, category_name)
VALUES (2, 'Sofa'),
       (3, 'Bed'),
       (4, 'Table'),
       (5, 'Accessories');

