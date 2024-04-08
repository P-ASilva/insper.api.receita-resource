CREATE TABLE receita
(
    id_receita character varying(36) NOT NULL,
    tx_name character varying(256) NOT NULL,
    id_creator character varying(36) NOT NULL,

    CONSTRAINT receita_pkey PRIMARY KEY (id_receita)
);
