CREATE TABLE t_user (
    id  NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) PRIMARY KEY,
    name  VARCHAR2(50) NOT NULL,
    email VARCHAR2(60) NOT NULL,
    password VARCHAR2(100) NOT NULL
);

CREATE TABLE T_PRODUCT (
    id_product    NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) PRIMARY KEY,
    nm_product    VARCHAR2(50) NOT NULL,
    dt_expiration DATE NOT NULL,
    nr_quantity   NUMBER(3) NOT NULL,
    status        VARCHAR2(15) NOT NULL,   
    user_id       NUMBER NOT NULL
);

ALTER TABLE T_PRODUCT
    ADD CONSTRAINT fk_user_product FOREIGN KEY ( user_id )
        REFERENCES t_user ( id );