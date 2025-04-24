-- Customer table
create table tb_customer (
    id bigint,
    name varchar(50) not null,
    ssn bigint not null, -- Social Security Number (equivalent to CPF)
    constraint pk_id_customer primary key(id)
);

create table tb_address(
    id bigint,
    street varchar(200),
    number bigint,
    city varchar(50),
    state varchar(50),
    zipcode bigint not null
    constraint pk_id_address primary key(id)
);

create table tb_contact_info(
    id bigint,
    phone bigint not null,
    e-mail varchar(100) not null
);

-- Product table
create table tb_product(
    id bigint,
    code varchar(10) not null,
    name varchar(50) not null,
    description varchar(100) not null,
    price numeric(10,2) not null,
    constraint pk_id_product primary key(id)
);

-- Sale table
create table tb_sale(
    id bigint,
    code varchar(10) not null,
    customer_id_fk bigint not null,
    total_value numeric(10,2) not null,
    sale_date TIMESTAMPTZ not null,
    sale_status varchar(50) not null,
    constraint pk_id_sale primary key(id),
    constraint fk_id_customer_sale foreign key(customer_id_fk) references tb_customer(id)
);

-- Product quantity table (for sale items)
create table tb_product_quantity(
    id bigint,
    product_id_fk bigint not null,
    sale_id_fk bigint not null,
    quantity int not null,
    total_value numeric(10,2) not null,
    constraint pk_id_prod_sale primary key(id),
    constraint fk_id_prod_sale foreign key(product_id_fk) references tb_product(id),
    constraint fk_id_sale_prod foreign key(sale_id_fk) references tb_sale(id)
);

-- Sequences for auto-increment
create sequence seq_customer
start 1
increment 1
owned by tb_customer.id;

create sequence seq_product
start 1
increment 1
owned by tb_product.id;

create sequence seq_sale
start 1
increment 1
owned by tb_sale.id;

create sequence seq_product_quantity
start 1
increment 1
owned by tb_product_quantity.id;

create sequence seq_address_id
start 1
increment 1
owned by tb_address.id;

create sequence seq_contact_info
start 1
increment 1
owned by tb_contact_info;

-- Unique constraints
ALTER TABLE tb_customer
ADD CONSTRAINT uk_ssn_customer UNIQUE (ssn);

ALTER TABLE tb_product
ADD CONSTRAINT uk_code_product UNIQUE (code);

ALTER TABLE tb_sale
ADD CONSTRAINT uk_code_sale UNIQUE (code);

-- Query to retrieve sale, customer and product quantity data
SELECT
    s.id AS sale_id,
    s.code,
    s.customer_id_fk,
    s.total_value,
    s.sale_date,
    s.sale_status,
    c.id AS customer_id,
    c.name,
    c.ssn,
    c.phone,
    c.address,
    c.number,
    c.city,
    c.state,
    pq.id AS prod_qty_id,
    pq.quantity,
    pq.total_value AS prod_qty_total_value
FROM
    tb_sale s
    INNER JOIN tb_customer c ON s.customer_id_fk = c.id
    INNER JOIN tb_product_quantity pq ON pq.sale_id_fk = s.id
WHERE
    s.code = 'A1';

-- Query to retrieve product quantity and product data
SELECT
    pq.id,
    pq.quantity,
    pq.total_value,
    p.id AS product_id,
    p.code,
    p.name,
    p.description,
    p.price
FROM
    tb_product_quantity pq
    INNER JOIN tb_product p ON p.id = pq.product_id_fk;