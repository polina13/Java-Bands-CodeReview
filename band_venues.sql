--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: alexnenchev
--

CREATE TABLE bands (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE bands OWNER TO alexnenchev;

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: alexnenchev
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_id_seq OWNER TO alexnenchev;

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alexnenchev
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: bands_played; Type: TABLE; Schema: public; Owner: alexnenchev
--

CREATE TABLE bands_played (
    id integer NOT NULL,
    band_id integer,
    venue_id integer
);


ALTER TABLE bands_played OWNER TO alexnenchev;

--
-- Name: bands_played_id_seq; Type: SEQUENCE; Schema: public; Owner: alexnenchev
--

CREATE SEQUENCE bands_played_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_played_id_seq OWNER TO alexnenchev;

--
-- Name: bands_played_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alexnenchev
--

ALTER SEQUENCE bands_played_id_seq OWNED BY bands_played.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: alexnenchev
--

CREATE TABLE venues (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE venues OWNER TO alexnenchev;

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: alexnenchev
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venues_id_seq OWNER TO alexnenchev;

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alexnenchev
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: alexnenchev
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: alexnenchev
--

ALTER TABLE ONLY bands_played ALTER COLUMN id SET DEFAULT nextval('bands_played_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: alexnenchev
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: alexnenchev
--

COPY bands (id, name) FROM stdin;
20	test13
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alexnenchev
--

SELECT pg_catalog.setval('bands_id_seq', 21, true);


--
-- Data for Name: bands_played; Type: TABLE DATA; Schema: public; Owner: alexnenchev
--

COPY bands_played (id, band_id, venue_id) FROM stdin;
3	2	1
4	2	1
16	20	1
17	20	1
18	20	1
22	20	4
\.


--
-- Name: bands_played_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alexnenchev
--

SELECT pg_catalog.setval('bands_played_id_seq', 22, true);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: alexnenchev
--

COPY venues (id, name) FROM stdin;
1	mmmmmmaaa
2	test1
3	test2
4	test44
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alexnenchev
--

SELECT pg_catalog.setval('venues_id_seq', 4, true);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: alexnenchev
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: bands_played_pkey; Type: CONSTRAINT; Schema: public; Owner: alexnenchev
--

ALTER TABLE ONLY bands_played
    ADD CONSTRAINT bands_played_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: alexnenchev
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: alexnenchev
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM alexnenchev;
GRANT ALL ON SCHEMA public TO alexnenchev;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

