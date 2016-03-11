--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2015-12-02 22:58:22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 195 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 195
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 16418)
-- Name: agent; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE agent (
    matricule bigint NOT NULL,
    nom character varying,
    prenom character varying,
    id_struc bigint
);


ALTER TABLE agent OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16406)
-- Name: arret; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE arret (
    code character varying,
    libelle character varying
);


ALTER TABLE arret OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16975)
-- Name: caracterestique; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE caracterestique (
    id integer NOT NULL,
    code character varying NOT NULL,
    designation character varying,
    nature bigint,
    type_car bigint
);


ALTER TABLE caracterestique OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16973)
-- Name: caracterestique_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE caracterestique_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE caracterestique_id_seq OWNER TO postgres;

--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 178
-- Name: caracterestique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE caracterestique_id_seq OWNED BY caracterestique.id;


--
-- TOC entry 192 (class 1259 OID 17256)
-- Name: caracterestiques_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE caracterestiques_type (
    id_type bigint NOT NULL,
    id_car bigint NOT NULL
);


ALTER TABLE caracterestiques_type OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 17220)
-- Name: consommation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE consommation (
    id integer NOT NULL,
    id_res bigint,
    id_pc bigint,
    prix real,
    quantite real,
    dt_const date
);


ALTER TABLE consommation OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 17218)
-- Name: consommation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE consommation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE consommation_id_seq OWNER TO postgres;

--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 190
-- Name: consommation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE consommation_id_seq OWNED BY consommation.id;


--
-- TOC entry 194 (class 1259 OID 17505)
-- Name: formule; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE formule (
    id integer NOT NULL,
    code character varying NOT NULL,
    designation character varying,
    expression character varying,
    type_rapport character varying,
    id_type bigint
);


ALTER TABLE formule OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 17503)
-- Name: formule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE formule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE formule_id_seq OWNER TO postgres;

--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 193
-- Name: formule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE formule_id_seq OWNED BY formule.id;


--
-- TOC entry 185 (class 1259 OID 17109)
-- Name: gouvernerat; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gouvernerat (
    id integer NOT NULL,
    code character varying,
    designation character varying
);


ALTER TABLE gouvernerat OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 17107)
-- Name: gouvernerat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE gouvernerat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE gouvernerat_id_seq OWNER TO postgres;

--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 184
-- Name: gouvernerat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE gouvernerat_id_seq OWNED BY gouvernerat.id;


--
-- TOC entry 174 (class 1259 OID 16426)
-- Name: groupe; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE groupe (
    id bigint NOT NULL,
    designation character varying
);


ALTER TABLE groupe OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 17127)
-- Name: produitchimique; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produitchimique (
    id integer NOT NULL,
    code character varying,
    designation character varying
);


ALTER TABLE produitchimique OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 17125)
-- Name: produitchimique_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produitchimique_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE produitchimique_id_seq OWNER TO postgres;

--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 186
-- Name: produitchimique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produitchimique_id_seq OWNED BY produitchimique.id;


--
-- TOC entry 183 (class 1259 OID 17012)
-- Name: ressource; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ressource (
    id integer NOT NULL,
    designation character varying,
    region character varying,
    annee bigint,
    id_struc bigint,
    id_type bigint,
    id_gouv bigint
);


ALTER TABLE ressource OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 17010)
-- Name: ressource_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ressource_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ressource_id_seq OWNER TO postgres;

--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 182
-- Name: ressource_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ressource_id_seq OWNED BY ressource.id;


--
-- TOC entry 181 (class 1259 OID 16994)
-- Name: structure; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE structure (
    id integer NOT NULL,
    code character varying NOT NULL,
    designation character varying,
    type_struc bigint,
    id_struc bigint
);


ALTER TABLE structure OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16992)
-- Name: structure_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE structure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE structure_id_seq OWNER TO postgres;

--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 180
-- Name: structure_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE structure_id_seq OWNED BY structure.id;


--
-- TOC entry 177 (class 1259 OID 16957)
-- Name: type_ressource; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE type_ressource (
    id integer NOT NULL,
    code character varying NOT NULL,
    designation character varying,
    nature bigint,
    consommationpc boolean
);


ALTER TABLE type_ressource OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16955)
-- Name: type_ressource_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE type_ressource_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE type_ressource_id_seq OWNER TO postgres;

--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 176
-- Name: type_ressource_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE type_ressource_id_seq OWNED BY type_ressource.id;


--
-- TOC entry 175 (class 1259 OID 16577)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE utilisateur (
    login character varying NOT NULL,
    password character varying,
    etat boolean,
    groupe bigint,
    matricule bigint
);


ALTER TABLE utilisateur OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 17199)
-- Name: valeurcaracterestique; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE valeurcaracterestique (
    id integer NOT NULL,
    id_car bigint,
    id_res bigint,
    dtsaisie date,
    valeur character varying NOT NULL,
    valide boolean,
    dtvalid date
);


ALTER TABLE valeurcaracterestique OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 17197)
-- Name: valeurcaracterestique_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE valeurcaracterestique_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE valeurcaracterestique_id_seq OWNER TO postgres;

--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 188
-- Name: valeurcaracterestique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE valeurcaracterestique_id_seq OWNED BY valeurcaracterestique.id;


--
-- TOC entry 1962 (class 2604 OID 16978)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY caracterestique ALTER COLUMN id SET DEFAULT nextval('caracterestique_id_seq'::regclass);


--
-- TOC entry 1968 (class 2604 OID 17223)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY consommation ALTER COLUMN id SET DEFAULT nextval('consommation_id_seq'::regclass);


--
-- TOC entry 1969 (class 2604 OID 17508)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY formule ALTER COLUMN id SET DEFAULT nextval('formule_id_seq'::regclass);


--
-- TOC entry 1965 (class 2604 OID 17112)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gouvernerat ALTER COLUMN id SET DEFAULT nextval('gouvernerat_id_seq'::regclass);


--
-- TOC entry 1966 (class 2604 OID 17130)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produitchimique ALTER COLUMN id SET DEFAULT nextval('produitchimique_id_seq'::regclass);


--
-- TOC entry 1964 (class 2604 OID 17015)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ressource ALTER COLUMN id SET DEFAULT nextval('ressource_id_seq'::regclass);


--
-- TOC entry 1963 (class 2604 OID 16997)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY structure ALTER COLUMN id SET DEFAULT nextval('structure_id_seq'::regclass);


--
-- TOC entry 1961 (class 2604 OID 16960)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY type_ressource ALTER COLUMN id SET DEFAULT nextval('type_ressource_id_seq'::regclass);


--
-- TOC entry 1967 (class 2604 OID 17202)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valeurcaracterestique ALTER COLUMN id SET DEFAULT nextval('valeurcaracterestique_id_seq'::regclass);







--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 178
-- Name: caracterestique_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('caracterestique_id_seq', 31, true);







--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 190
-- Name: consommation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('consommation_id_seq', 9, true);




--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 193
-- Name: formule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('formule_id_seq', 5, true);




--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 184
-- Name: gouvernerat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('gouvernerat_id_seq', 7, true);








--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 186
-- Name: produitchimique_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('produitchimique_id_seq', 5, true);




--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 182
-- Name: ressource_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ressource_id_seq', 36, true);


--
-- TOC entry 2134 (class 0 OID 16994)
-- Dependencies: 181
-- Data for Name: structure; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 180
-- Name: structure_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('structure_id_seq', 7, true);





--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 176
-- Name: type_ressource_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('type_ressource_id_seq', 39, true);







--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 188
-- Name: valeurcaracterestique_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('valeurcaracterestique_id_seq', 75, true);


--
-- TOC entry 1971 (class 2606 OID 16425)
-- Name: agent_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT agent_pkey PRIMARY KEY (matricule);


--
-- TOC entry 1981 (class 2606 OID 16985)
-- Name: caracterestique_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY caracterestique
    ADD CONSTRAINT caracterestique_code_key UNIQUE (code);


--
-- TOC entry 1983 (class 2606 OID 16983)
-- Name: caracterestique_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY caracterestique
    ADD CONSTRAINT caracterestique_pkey PRIMARY KEY (id);


--
-- TOC entry 1999 (class 2606 OID 17260)
-- Name: caracterestiques_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY caracterestiques_type
    ADD CONSTRAINT caracterestiques_type_pkey PRIMARY KEY (id_type, id_car);


--
-- TOC entry 1997 (class 2606 OID 17225)
-- Name: consommation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY consommation
    ADD CONSTRAINT consommation_pkey PRIMARY KEY (id);


--
-- TOC entry 2001 (class 2606 OID 17513)
-- Name: formule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY formule
    ADD CONSTRAINT formule_pkey PRIMARY KEY (id);


--
-- TOC entry 1991 (class 2606 OID 17117)
-- Name: gouvernerat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gouvernerat
    ADD CONSTRAINT gouvernerat_pkey PRIMARY KEY (id);


--
-- TOC entry 1973 (class 2606 OID 16433)
-- Name: groupe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groupe
    ADD CONSTRAINT groupe_pkey PRIMARY KEY (id);


--
-- TOC entry 1993 (class 2606 OID 17135)
-- Name: produitchimique_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produitchimique
    ADD CONSTRAINT produitchimique_pkey PRIMARY KEY (id);


--
-- TOC entry 1989 (class 2606 OID 17020)
-- Name: ressource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ressource
    ADD CONSTRAINT ressource_pkey PRIMARY KEY (id);


--
-- TOC entry 1985 (class 2606 OID 17004)
-- Name: structure_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT structure_code_key UNIQUE (code);


--
-- TOC entry 1987 (class 2606 OID 17002)
-- Name: structure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT structure_pkey PRIMARY KEY (id);


--
-- TOC entry 1977 (class 2606 OID 16967)
-- Name: type_ressource_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY type_ressource
    ADD CONSTRAINT type_ressource_code_key UNIQUE (code);


--
-- TOC entry 1979 (class 2606 OID 16965)
-- Name: type_ressource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY type_ressource
    ADD CONSTRAINT type_ressource_pkey PRIMARY KEY (id);


--
-- TOC entry 1975 (class 2606 OID 16584)
-- Name: utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (login);


--
-- TOC entry 1995 (class 2606 OID 17207)
-- Name: valeurcaracterestique_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY valeurcaracterestique
    ADD CONSTRAINT valeurcaracterestique_pkey PRIMARY KEY (id);


--
-- TOC entry 2002 (class 2606 OID 17236)
-- Name: agent_id_struc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT agent_id_struc_fkey FOREIGN KEY (id_struc) REFERENCES structure(id);


--
-- TOC entry 2013 (class 2606 OID 17261)
-- Name: caracterestiques_type_id_car_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY caracterestiques_type
    ADD CONSTRAINT caracterestiques_type_id_car_fkey FOREIGN KEY (id_car) REFERENCES caracterestique(id) ON DELETE CASCADE;


--
-- TOC entry 2014 (class 2606 OID 17266)
-- Name: caracterestiques_type_id_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY caracterestiques_type
    ADD CONSTRAINT caracterestiques_type_id_type_fkey FOREIGN KEY (id_type) REFERENCES type_ressource(id) ON DELETE CASCADE;


--
-- TOC entry 2011 (class 2606 OID 17226)
-- Name: consommation_id_pc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY consommation
    ADD CONSTRAINT consommation_id_pc_fkey FOREIGN KEY (id_pc) REFERENCES produitchimique(id) ON DELETE CASCADE;


--
-- TOC entry 2012 (class 2606 OID 17231)
-- Name: consommation_id_res_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY consommation
    ADD CONSTRAINT consommation_id_res_fkey FOREIGN KEY (id_res) REFERENCES ressource(id) ON DELETE CASCADE;


--
-- TOC entry 2015 (class 2606 OID 17514)
-- Name: formule_id_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY formule
    ADD CONSTRAINT formule_id_type_fkey FOREIGN KEY (id_type) REFERENCES type_ressource(id);


--
-- TOC entry 2006 (class 2606 OID 17120)
-- Name: ressource_id_gouv_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ressource
    ADD CONSTRAINT ressource_id_gouv_fkey FOREIGN KEY (id_gouv) REFERENCES gouvernerat(id);


--
-- TOC entry 2007 (class 2606 OID 17021)
-- Name: ressource_id_struc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ressource
    ADD CONSTRAINT ressource_id_struc_fkey FOREIGN KEY (id_struc) REFERENCES structure(id);


--
-- TOC entry 2008 (class 2606 OID 17026)
-- Name: ressource_id_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ressource
    ADD CONSTRAINT ressource_id_type_fkey FOREIGN KEY (id_type) REFERENCES type_ressource(id);


--
-- TOC entry 2005 (class 2606 OID 17005)
-- Name: structure_id_struc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT structure_id_struc_fkey FOREIGN KEY (id_struc) REFERENCES structure(id);


--
-- TOC entry 2003 (class 2606 OID 16585)
-- Name: utilisateur_groupe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT utilisateur_groupe_fkey FOREIGN KEY (groupe) REFERENCES groupe(id);


--
-- TOC entry 2004 (class 2606 OID 16590)
-- Name: utilisateur_matricule_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT utilisateur_matricule_fkey FOREIGN KEY (matricule) REFERENCES agent(matricule);


--
-- TOC entry 2009 (class 2606 OID 17208)
-- Name: valeurcaracterestique_id_car_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valeurcaracterestique
    ADD CONSTRAINT valeurcaracterestique_id_car_fkey FOREIGN KEY (id_car) REFERENCES caracterestique(id) ON DELETE CASCADE;


--
-- TOC entry 2010 (class 2606 OID 17213)
-- Name: valeurcaracterestique_id_res_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valeurcaracterestique
    ADD CONSTRAINT valeurcaracterestique_id_res_fkey FOREIGN KEY (id_res) REFERENCES ressource(id) ON DELETE CASCADE;


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-12-02 22:58:23

--
-- PostgreSQL database dump complete
--

