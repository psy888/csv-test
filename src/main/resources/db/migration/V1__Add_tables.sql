CREATE TABLE my_schema.file
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    device_type character varying(25) COLLATE pg_catalog."default" NOT NULL,
    file_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    upload_date_time timestamp without time zone NOT NULL,
    CONSTRAINT file_pkey PRIMARY KEY (id)
);
CREATE TABLE my_schema.net_dev
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    address character varying(255) COLLATE pg_catalog."default",
    state character varying(20) COLLATE pg_catalog."default" NOT NULL,
    ip_address character varying(15) COLLATE pg_catalog."default" NOT NULL,
    status boolean NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    proc_load double precision,
    file_id bigint,
    CONSTRAINT net_dev_pkey PRIMARY KEY (id),
    CONSTRAINT fkk352owxessy45nechmjpg6wlx FOREIGN KEY (file_id)
        REFERENCES my_schema.file (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE my_schema.spec_dev
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    address character varying(255) COLLATE pg_catalog."default",
    state character varying(20) COLLATE pg_catalog."default" NOT NULL,
    prod_date timestamp without time zone NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    file_id bigint,
    CONSTRAINT spec_dev_pkey PRIMARY KEY (id),
    CONSTRAINT fk68xtba4uewcnkl9pdtc5o2nfv FOREIGN KEY (file_id)
        REFERENCES my_schema.file (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
