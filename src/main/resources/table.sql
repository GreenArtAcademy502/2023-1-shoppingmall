CREATE TABLE t_customer(
    icustomer BIGINT UNSIGNED AUTO_INCREMENT
    , nm VARCHAR(15) NOT NULL
    , addr VARCHAR(50) NOT NULL
    , ph CHAR(13) NOT NULL
    , PRIMARY KEY(icustomer)
);

CREATE TABLE t_product(
    iproduct BIGINT UNSIGNED AUTO_INCREMENT
    , price INT UNSIGNED NOT NULL
    , ctnt TEXT NOT NULL
    , nm VARCHAR(30) NOT NULL
    , main_pic VARCHAR(100) NOT NULL
    , brand VARCHAR(20) NOT NULL
    , created_at DATETIME DEFAULT current_timestamp
    , PRIMARY KEY (iproduct)
);

CREATE TABLE t_product_pic(
    iproduct_pic BIGINT UNSIGNED AUTO_INCREMENT
    , iproduct BIGINT UNSIGNED NOT NULL
    , pic VARCHAR(100) NOT NULL
    , PRIMARY KEY(iproduct_pic)
    , FOREIGN KEY (iproduct) REFERENCES t_product(iproduct)
);

CREATE TABLE t_buy(
    ibuy BIGINT UNSIGNED AUTO_INCREMENT
    , iproduct BIGINT UNSIGNED NOT NULL
    , icustomer BIGINT UNSIGNED NOT NULL
    , quantity INT UNSIGNED NOT NULL
    , buy_at DATETIME DEFAULT CURRENT_TIMESTAMP
    , PRIMARY KEY (ibuy)
    , FOREIGN KEY (iproduct) REFERENCES t_product(iproduct)
    , FOREIGN KEY (icustomer) REFERENCES t_customer(icustomer)
);


CREATE TABLE t_review(
    ireview BIGINT UNSIGNED AUTO_INCREMENT
    , ibuy BIGINT UNSIGNED NOT NULL
    , ctnt TEXT NOT NULL
    , star TINYINT NOT NULL
    , created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    , PRIMARY KEY (ireview)
    , FOREIGN KEY (ibuy) REFERENCES t_buy(ibuy)
);

CREATE TABLE t_review_pic(
    ireview_pic BIGINT UNSIGNED AUTO_INCREMENT
    , ireview BIGINT UNSIGNED NOT NULL
    , pic VARCHAR(100) NOT NULL
    , PRIMARY KEY (ireview_pic)
    , FOREIGN KEY (ireview) REFERENCES t_review(ireview)
);
