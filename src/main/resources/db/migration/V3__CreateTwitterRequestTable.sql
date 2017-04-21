CREATE TABLE twitter_request_token
(
    id             BIGINT NOT NULL,
    request_token  VARCHAR (255) NOT NULL,
    token_secret   VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.twitter_request_token_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 999999999
CACHE 1;

ALTER SEQUENCE public.twitter_request_token_seq
OWNER TO postgres;
