CREATE TABLE my_schema.device_type
(
    id bigint NOT NULL,
    type_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT device_type_pkey PRIMARY KEY (id),
    CONSTRAINT unique_type UNIQUE (type_name)
);

ALTER TABLE my_schema.file ALTER COLUMN device_type TYPE bigint USING (device_type::integer);