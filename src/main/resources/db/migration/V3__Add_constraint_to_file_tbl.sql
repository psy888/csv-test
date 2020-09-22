ALTER TABLE my_schema.file
    ADD CONSTRAINT dev_type FOREIGN KEY (device_type)
        REFERENCES my_schema.device_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;