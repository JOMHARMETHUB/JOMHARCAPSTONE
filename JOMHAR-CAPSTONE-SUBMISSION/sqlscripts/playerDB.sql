--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:38:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: sporting_events_player_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_player_schema;


ALTER SCHEMA sporting_events_player_schema OWNER TO pg_database_owner;

--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_player_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_player_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16503)
-- Name: players; Type: TABLE; Schema: sporting_events_player_schema; Owner: postgres
--

CREATE TABLE sporting_events_player_schema.players (
    player_id bigint NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    country text NOT NULL,
    team_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE sporting_events_player_schema.players OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16502)
-- Name: player_player_id_seq; Type: SEQUENCE; Schema: sporting_events_player_schema; Owner: postgres
--

ALTER TABLE sporting_events_player_schema.players ALTER COLUMN player_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_player_schema.player_player_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3319 (class 0 OID 16503)
-- Dependencies: 215
-- Data for Name: players; Type: TABLE DATA; Schema: sporting_events_player_schema; Owner: postgres
--

COPY sporting_events_player_schema.players (player_id, first_name, last_name, country, team_id, active) FROM stdin;
2	RAHMOJ	NOSMAS	PHILIPPINES	1	t
4	FDSFSF	ZXCZXCCZCX	PHILIPPINES	1	t
3	TESTFNA	TESTLNA	TESTCA	1	t
1	JOMHAR	SAMSON	PHILIPPINES	1	f
5	QQWEQW	ASDQWEEQW	PHILIPPINES	3	t
6	FDFCVXVCCV	DSFKJK	PHILIPPINES	3	t
7	AASDQWEQ	XZCZXDFVDF	PHILIPPINES	3	t
8	AASDQWEQ	GFDGDFGGFD	PHILIPPINES	3	t
9	AASDQWEQ	GFDGDFGGFD	PHILIPPINES	3	t
13	GOODPLAYERRR	BESTPLAYERRRR	PHILIPPINES	1	t
11	UPDATEFN	UPDATELN	PHILIPPINES	1	f
14	GOODPLAYERRR	BESTPLAYERRRR	PHILIPPINES	1	t
15	GOODPLAYERRR	BESTPLAYERRRR	PHILIPPINES	3	t
12	UPDATEFN	UPDATELN	PHILIPPINES	3	f
16	GOODPLAYERRR	BESTPLAYERRRR	PHILIPPINES	3	t
17	GOODPLAYERRR	BESTPLAYERRRR	PHILIPPINES	3	t
10	UPDATEFN	UPDATELN	PHILIPPINES	1	t
18	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	1	t
19	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	1	t
20	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
21	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
22	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
23	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
24	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
25	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
26	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
27	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	1	t
28	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
29	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
30	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
31	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
32	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
33	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
34	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINES	3	t
35	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINESS	3	t
36	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINESS	1	t
37	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINESS	1	t
38	GOODPLAYERRRS	BESTPLAYERRRRS	PHILIPPINESS	1	t
39	APACHEDEMO	BESTPLAYERRRRS	PHILIPPINESS	1	t
40	APACHEDEMO	BESTPLAYERRRRS	PHILIPPINESS	1	t
\.


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 214
-- Name: player_player_id_seq; Type: SEQUENCE SET; Schema: sporting_events_player_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_player_schema.player_player_id_seq', 40, true);


--
-- TOC entry 3175 (class 2606 OID 16509)
-- Name: players player_pkey; Type: CONSTRAINT; Schema: sporting_events_player_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_player_schema.players
    ADD CONSTRAINT player_pkey PRIMARY KEY (player_id);


-- Completed on 2023-05-18 16:38:34

--
-- PostgreSQL database dump complete
--

