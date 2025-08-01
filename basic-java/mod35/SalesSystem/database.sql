-- SEQUENCES
CREATE SEQUENCE customer_seq START 1;
CREATE SEQUENCE address_seq START 1;
CREATE SEQUENCE contact_info_seq START 1;
CREATE SEQUENCE product_seq START 1;
CREATE SEQUENCE stock_seq START 1;
CREATE SEQUENCE stock_item_seq START 1;
CREATE SEQUENCE sale_seq START 1;
CREATE SEQUENCE sale_item_seq START 1;

-- CUSTOMERS
CREATE TABLE customers (
    id BIGINT PRIMARY KEY DEFAULT nextval('customer_seq'),
    name VARCHAR(255) NOT NULL,
    ssn BIGINT UNIQUE NOT NULL
);

-- ADDRESSES
CREATE TABLE addresses (
    id BIGINT PRIMARY KEY DEFAULT nextval('address_seq'),
    customer_id BIGINT REFERENCES customers(id) ON DELETE CASCADE,
    street VARCHAR(255),
    number INTEGER,
    city VARCHAR(100),
    state VARCHAR(100),
    zip_code VARCHAR(20)
);

-- CONTACT INFO
CREATE TABLE contact_info (
    id BIGINT PRIMARY KEY DEFAULT nextval('contact_info_seq'),
    customer_id BIGINT REFERENCES customers(id) ON DELETE CASCADE,
    phone BIGINT,
    email VARCHAR(255)
);

-- PRODUCTS
CREATE TABLE products (
    id BIGINT PRIMARY KEY DEFAULT nextval('product_seq'),
    code VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(12, 2) NOT NULL
);

-- STOCKS
CREATE TABLE stocks (
    id BIGINT PRIMARY KEY DEFAULT nextval('stock_seq')
);

-- STOCK ITEMS
CREATE TABLE stock_items (
    id BIGINT PRIMARY KEY DEFAULT nextval('stock_item_seq'),
    stock_id BIGINT REFERENCES stocks(id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES products(id),
    quantity INTEGER NOT NULL
);

-- SALES
CREATE TABLE sales (
    id BIGINT PRIMARY KEY DEFAULT nextval('sale_seq'),
    code VARCHAR(100) UNIQUE NOT NULL,
    customer_id BIGINT REFERENCES customers(id),
    total NUMERIC(12, 2),
    date_of_sale TIMESTAMP NOT NULL,
    status VARCHAR(50)
);

-- SALE ITEMS
CREATE TABLE sale_items (
    id BIGINT PRIMARY KEY DEFAULT nextval('sale_item_seq'),
    sale_id BIGINT REFERENCES sales(id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES products(id),
    quantity INTEGER NOT NULL
);
