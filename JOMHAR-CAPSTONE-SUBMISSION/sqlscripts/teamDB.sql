--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:39:02

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
-- Name: sporting_events_team_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_team_schema;


ALTER SCHEMA sporting_events_team_schema OWNER TO pg_database_owner;

--
-- TOC entry 3327 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_team_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_team_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16523)
-- Name: teams; Type: TABLE; Schema: sporting_events_team_schema; Owner: postgres
--

CREATE TABLE sporting_events_team_schema.teams (
    team_id bigint NOT NULL,
    team_name text NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE sporting_events_team_schema.teams OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16522)
-- Name: teams_team_id_seq; Type: SEQUENCE; Schema: sporting_events_team_schema; Owner: postgres
--

ALTER TABLE sporting_events_team_schema.teams ALTER COLUMN team_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_team_schema.teams_team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3321 (class 0 OID 16523)
-- Dependencies: 215
-- Data for Name: teams; Type: TABLE DATA; Schema: sporting_events_team_schema; Owner: postgres
--

COPY sporting_events_team_schema.teams (team_id, team_name, active) FROM stdin;
1	Pers Team	t
3	Second Team	t
4	test	t
5	test1	t
6	test3	t
7	test4	t
8	test2	f
14	test12345	t
15	test123456	t
16	test44	f
17	test441	f
\.


--
-- TOC entry 3328 (class 0 OID 0)
-- Dependencies: 214
-- Name: teams_team_id_seq; Type: SEQUENCE SET; Schema: sporting_events_team_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_team_schema.teams_team_id_seq', 17, true);


--
-- TOC entry 3175 (class 2606 OID 16531)
-- Name: teams team_name; Type: CONSTRAINT; Schema: sporting_events_team_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_team_schema.teams
    ADD CONSTRAINT team_name UNIQUE (team_name) INCLUDE (team_name);


--
-- TOC entry 3177 (class 2606 OID 16529)
-- Name: teams teams_pkey; Type: CONSTRAINT; Schema: sporting_events_team_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_team_schema.teams
    ADD CONSTRAINT teams_pkey PRIMARY KEY (team_id);


-- Completed on 2023-05-18 16:39:03

--
-- PostgreSQL database dump complete
--

