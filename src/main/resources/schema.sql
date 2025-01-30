
CREATE TABLE IF NOT EXISTS user_types
(
    user_type_id BIGSERIAL PRIMARY KEY,
    type_name    VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id   BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    user_id      BIGSERIAL PRIMARY KEY,
    username     VARCHAR(50) UNIQUE  NOT NULL,
    email        VARCHAR(100) UNIQUE NOT NULL,
    password     VARCHAR(255)        NOT NULL,
    user_type_id INT REFERENCES user_types (user_type_id),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_role_id BIGSERIAL PRIMARY KEY,
    user_id      INT REFERENCES users (user_id),
    role_id      INT REFERENCES roles (role_id)
);

CREATE TABLE IF NOT EXISTS customers
(
    customer_id BIGSERIAL PRIMARY KEY,
    user_id     INT UNIQUE REFERENCES users (user_id),
    phone       VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS addresses
(
    address_id BIGSERIAL PRIMARY KEY,
    line1      VARCHAR(255),
    line2      VARCHAR(255),
    city       VARCHAR(100),
    state      VARCHAR(100),
    country    VARCHAR(100),
    zipcode    VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS restaurants
(
    restaurant_id BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    address_id    INT REFERENCES addresses (address_id),
    owner_id      INT REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS restaurant_details
(
    restaurant_detail_id BIGSERIAL PRIMARY KEY,
    restaurant_id        INT REFERENCES restaurants (restaurant_id),
    opening_time         TIME,
    closing_time         TIME
);

CREATE TABLE IF NOT EXISTS menus
(
    menu_id       BIGSERIAL PRIMARY KEY,
    restaurant_id INT REFERENCES restaurants (restaurant_id),
    menu_name     VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS menu_items
(
    menu_item_id BIGSERIAL PRIMARY KEY,
    menu_id      INT REFERENCES menus (menu_id),
    item_name    VARCHAR(100),
    price        DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS carts
(
    cart_id    BIGSERIAL PRIMARY KEY,
    user_id    INT UNIQUE REFERENCES users (user_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart_items
(
    cart_item_id BIGSERIAL PRIMARY KEY,
    cart_id      INT REFERENCES carts (cart_id),
    menu_item_id INT REFERENCES menu_items (menu_item_id),
    quantity     INT            NOT NULL,
    price        DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS payment_integration_types
(
    payment_integration_type_id BIGSERIAL PRIMARY KEY,
    type_name                   VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS preferred_payment_settings
(
    payment_setting_id          BIGSERIAL PRIMARY KEY,
    customer_id                 INT REFERENCES customers (customer_id),
    payment_integration_type_id INT REFERENCES payment_integration_types (payment_integration_type_id)
);

CREATE TABLE IF NOT EXISTS order_statuses
(
    order_status_id BIGSERIAL PRIMARY KEY,
    status          VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    order_id        BIGSERIAL PRIMARY KEY,
    customer_id     INT REFERENCES customers (customer_id),
    total_amount    DECIMAL(10, 2) NOT NULL,
    order_status_id INT REFERENCES order_statuses (order_status_id),
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_items
(
    order_item_id BIGSERIAL PRIMARY KEY,
    order_id      INT REFERENCES orders (order_id),
    menu_item_id  INT REFERENCES menu_items (menu_item_id),
    quantity      INT NOT NULL
);

CREATE TABLE IF NOT EXISTS order_tracking
(
    tracking_id BIGSERIAL PRIMARY KEY,
    order_id    INT REFERENCES orders (order_id),
    status      VARCHAR(100),
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS payment_statuses
(
    payment_status_id BIGSERIAL PRIMARY KEY,
    status            VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions
(
    transaction_id    BIGSERIAL PRIMARY KEY,
    order_id          INT REFERENCES orders (order_id),
    amount            DECIMAL(10, 2) NOT NULL,
    payment_status_id INT REFERENCES payment_statuses (payment_status_id),
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS transaction_details
(
    transaction_detail_id BIGSERIAL PRIMARY KEY,
    transaction_id        INT REFERENCES transactions (transaction_id),
    detail                TEXT
);

CREATE TABLE IF NOT EXISTS payment_type_configurations
(
    payment_type_config_id BIGSERIAL PRIMARY KEY,
    config_name            VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS auditing
(
    audit_id  BIGSERIAL PRIMARY KEY,
    user_id   INT REFERENCES users (user_id),
    action    VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);