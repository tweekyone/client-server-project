CREATE SEQUENCE message_id_seq START 1 INCREMENT 1;

CREATE TABLE Messages (
    message_id BIGINT NOT NULL ,
    message VARCHAR(255) NOT NULL ,
    date_time TIMESTAMP
)