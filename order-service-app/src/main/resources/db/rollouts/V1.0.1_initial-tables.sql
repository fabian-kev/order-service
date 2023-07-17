DROP CAST IF EXISTS (character varying as order_status);
DROP TYPE IF EXISTS order_status;

CREATE TYPE order_status as ENUM
    ('PENDING', 'SERVING', 'DELIVERY','COMPLETED');
CREATE CAST (character varying as order_status) WITH INOUT AS IMPLICIT;


CREATE TABLE orders
(
    id           UUID           NOT NULL DEFAULT public.uuid_generate_v4(),
    customer_id  varchar(100)   NOT NULL,
    currency     VARCHAR(10)    NOT NULL,
    total_amount numeric(10, 2) NOT NULL,
    note         VARCHAR(100)   NOT NULL,
    status       order_status   NOT NULL,
    created_date TIMESTAMPTZ    NOT NULL DEFAULT now(),
    updated_date TIMESTAMPTZ    NOT NULL DEFAULT now(),

    CONSTRAINT orders_pk PRIMARY KEY (id)
);

CREATE TABLE items
(
    id           UUID           NOT NULL DEFAULT public.uuid_generate_v4(),
    order_id     UUID           NOT NULL,
    currency     VARCHAR(10)    NOT NULL,
    quality      numeric(3)     NOT NULL,
    amount       numeric(10, 2) NOT NULL,
    product_id   VARCHAR(100)   NOT NULL,
    name         VARCHAR(255)   NOT NULL,
    code         VARCHAR(100)   NOT NULL,
    description  VARCHAR(255)   NOT NULL,
    created_date TIMESTAMPTZ    NOT NULL DEFAULT now(),
    updated_date TIMESTAMPTZ    NOT NULL DEFAULT now(),

    CONSTRAINT items_pk PRIMARY KEY (id),
    CONSTRAINT items_order_id_fk FOREIGN KEY (order_id) REFERENCES orders (id)
);