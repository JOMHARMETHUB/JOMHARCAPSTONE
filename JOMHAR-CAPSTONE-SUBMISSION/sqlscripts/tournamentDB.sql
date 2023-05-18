--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:39:52

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
-- Name: sporting_events_tournament_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_tournament_schema;


ALTER SCHEMA sporting_events_tournament_schema OWNER TO pg_database_owner;

--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_tournament_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_tournament_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16542)
-- Name: tournaments; Type: TABLE; Schema: sporting_events_tournament_schema; Owner: postgres
--

CREATE TABLE sporting_events_tournament_schema.tournaments (
    tournament_id bigint NOT NULL,
    tournament_name text NOT NULL,
    sports_category text NOT NULL,
    tournament_style text NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE sporting_events_tournament_schema.tournaments OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16541)
-- Name: tournaments_tournament_id_seq; Type: SEQUENCE; Schema: sporting_events_tournament_schema; Owner: postgres
--

ALTER TABLE sporting_events_tournament_schema.tournaments ALTER COLUMN tournament_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_tournament_schema.tournaments_tournament_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3319 (class 0 OID 16542)
-- Dependencies: 215
-- Data for Name: tournaments; Type: TABLE DATA; Schema: sporting_events_tournament_schema; Owner: postgres
--

COPY sporting_events_tournament_schema.tournaments (tournament_id, tournament_name, sports_category, tournament_style, active) FROM stdin;
1	testt	testcat	teststyle	t
2	Dota 2	ESports	1v1	t
4	tournamentName	sportCategory	tournamentStyle	t
5	tournamentName	sportCategory	tournamentStyle	t
3	tournamentName1	sportCategory1	tournamentStyle1	f
6	tournamentName	sportCategory	tournamentStyle	t
7	tournamentName	sportCategory	tournamentStyle	t
8	tournamentName	sportCategory	tournamentStyle	t
\.


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 214
-- Name: tournaments_tournament_id_seq; Type: SEQUENCE SET; Schema: sporting_events_tournament_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_tournament_schema.tournaments_tournament_id_seq', 8, true);


--
-- TOC entry 3175 (class 2606 OID 16548)
-- Name: tournaments tournaments_pkey; Type: CONSTRAINT; Schema: sporting_events_tournament_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_tournament_schema.tournaments
    ADD CONSTRAINT tournaments_pkey PRIMARY KEY (tournament_id);


-- Completed on 2023-05-18 16:39:53

--
-- PostgreSQL database dump complete
--

