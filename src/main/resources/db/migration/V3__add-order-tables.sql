-- Ensure that tables exist before trying to drop them
DROP TABLE IF EXISTS beer_order_line;
DROP TABLE IF EXISTS beer_order;

-- Create beer_order table
CREATE TABLE beer_order (
                            id                  VARCHAR(36)     NOT NULL,
                            created_date        DATETIME(6)     DEFAULT NULL,
                            customer_ref        VARCHAR(255)    DEFAULT NULL,
                            last_modified_date  DATETIME(6)     DEFAULT NULL,
                            version             BIGINT          DEFAULT NULL,
                            customer_id         VARCHAR(36)     DEFAULT NULL,
                            PRIMARY KEY (id),
                            CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer(id)
) ENGINE=InnoDB;

-- Create beer_order_line table
CREATE TABLE beer_order_line (
                                 id                  VARCHAR(36)     NOT NULL,
                                 beer_id             VARCHAR(36)     NOT NULL,
                                 created_date        DATETIME(6)     DEFAULT NULL,
                                 customer_ref        VARCHAR(255)    DEFAULT NULL,
                                 last_modified_date  DATETIME(6)     DEFAULT NULL,
                                 order_quantity      INT             DEFAULT NULL,
                                 quantity_allocated  INT             DEFAULT NULL,
                                 version             BIGINT          DEFAULT NULL,
                                 customer_id         VARCHAR(36)     DEFAULT NULL,
                                 beer_order_id       VARCHAR(36)     DEFAULT NULL,
                                 PRIMARY KEY (id),
                                 CONSTRAINT FOREIGN KEY (beer_order_id) REFERENCES beer_order(id),
                                 CONSTRAINT FOREIGN KEY (beer_id) REFERENCES beer(id)
) ENGINE=InnoDB;
