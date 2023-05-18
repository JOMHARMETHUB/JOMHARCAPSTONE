--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:38:18

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
-- Name: sporting_events_match_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_match_schema;


ALTER SCHEMA sporting_events_match_schema OWNER TO pg_database_owner;

--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_match_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_match_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16562)
-- Name: matches; Type: TABLE; Schema: sporting_events_match_schema; Owner: postgres
--

CREATE TABLE sporting_events_match_schema.matches (
    match_id bigint NOT NULL,
    field_id bigint NOT NULL,
    tournament_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL,
    date_time text DEFAULT CURRENT_TIMESTAMP NOT NULL,
    teams_id text,
    participants_id text
);


ALTER TABLE sporting_events_match_schema.matches OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16561)
-- Name: matches_match_id_seq; Type: SEQUENCE; Schema: sporting_events_match_schema; Owner: postgres
--

ALTER TABLE sporting_events_match_schema.matches ALTER COLUMN match_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_match_schema.matches_match_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3320 (class 0 OID 16562)
-- Dependencies: 215
-- Data for Name: matches; Type: TABLE DATA; Schema: sporting_events_match_schema; Owner: postgres
--

COPY sporting_events_match_schema.matches (match_id, field_id, tournament_id, active, date_time, teams_id, participants_id) FROM stdin;
8	2	1	t	2023-05-05 10:23:00	1,2	1,2,3,4,5,6
11	1	4	t	2023-05-05 10:23:00	1,2	1,2,3,4,5,6
12	1	1	t	2023-05-05 10:23:00	1,2	1,2,3,4,5,6
13	1	1	t	2023-05-05 16:16:00	1,2	1,2,3,4,5,6
14	1	1	t	2023-05-20 16:16:00	1,2	1,2,3,4,5,6
15	1	1	t	2023-05-20 16:16:00	1,2	1,2,3,4,5,6
16	1	1	t	2023-05-20 16:16:00	1,2	1,2,3,4,5,6
17	1	1	t	2023-05-20 16:16:00	1,3	5,6
10	1	2	f	2023-05-05 10:23:00	1,2	1,2,3,4,5,6
9	2	1	t	2023-06-08 10:10:23	1,3	3,4
18	1	1	t	2023-05-20 16:16:00	1,3	5,6
19	1	1	t	2023-05-20 16:16:00	1,3	5,6
20	1	1	t	2023-05-20 16:16:00	1,3	5,6
21	1	1	t	2023-05-20 16:16:00	1,3	5,6
22	2	1	t	2023-05-20 16:16:00	1,3	5,6
23	2	1	t	2023-05-20 16:16:00	1,3	5,6
24	2	1	t	2023-05-20 16:16:00	1,3	5,6
1	1	1	t	2023-05-03 11:43:15.270345+08	1,2	1,2,3,4,5,6
2	2	1	t	2023-05-20 10:10:23	1,2	1,2,3,4,5,6
3	1	1	f	2023-05-03 11:43:15.270345+08	1,2	1,2,3,4,5,6
4	1	1	f	2050-05-05 10:00:00	1,2	1,2,3,4,5,6
5	1	1	t	2024-05-07 10:00:00	1,2	1,2,3,4,5,6
6	1	1	t	2023-05-03 17:23:00	1,2	1,2,3,4,5,6
7	1	1	t	2023-05-03 17:23:00	1,2	1,2,3,4,5,6
25	2	1	t	2023-05-20 16:16:00	1,3	5,6
\.


--
-- TOC entry 3327 (class 0 OID 0)
-- Dependencies: 214
-- Name: matches_match_id_seq; Type: SEQUENCE SET; Schema: sporting_events_match_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_match_schema.matches_match_id_seq', 25, true);


--
-- TOC entry 3176 (class 2606 OID 16568)
-- Name: matches matches_pkey; Type: CONSTRAINT; Schema: sporting_events_match_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_match_schema.matches
    ADD CONSTRAINT matches_pkey PRIMARY KEY (match_id);


-- Completed on 2023-05-18 16:38:18

--
-- PostgreSQL database dump complete
--

