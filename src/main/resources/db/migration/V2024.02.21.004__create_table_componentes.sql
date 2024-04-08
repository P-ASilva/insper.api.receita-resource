CREATE TABLE componente
(
    id_componente character varying(36) NOT NULL,
    id_receita character varying(36) NOT NULL,
    id_ingrediente character varying(36) NOT NULL,
    qnt integer NOT NULL,

    CONSTRAINT id_componente_pkey PRIMARY KEY (id_componente)
);
