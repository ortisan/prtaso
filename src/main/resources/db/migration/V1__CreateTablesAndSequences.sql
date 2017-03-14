-- TABLES
CREATE TABLE "user"
(
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.user_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE public.user_seq
    OWNER TO postgres;


CREATE TABLE topic
(
    id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    message VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    send_date TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.topic_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 999999999
CACHE 1;

ALTER SEQUENCE public.topic_seq
OWNER TO postgres;


CREATE TABLE subscription
(
    id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);

CREATE SEQUENCE public.subscription_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 999999999
CACHE 1;

ALTER SEQUENCE public.subscription_seq
OWNER TO postgres;

CREATE TABLE subscription_user
(
    subscription_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (subscription_id, user_id),
    FOREIGN KEY (subscription_id) REFERENCES subscription(id),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);
