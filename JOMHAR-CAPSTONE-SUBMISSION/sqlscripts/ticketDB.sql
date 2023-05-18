--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:39:19

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
-- Name: sporting_events_ticket_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_ticket_schema;


ALTER SCHEMA sporting_events_ticket_schema OWNER TO pg_database_owner;

--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_ticket_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_ticket_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16444)
-- Name: tickets; Type: TABLE; Schema: sporting_events_ticket_schema; Owner: postgres
--

CREATE TABLE sporting_events_ticket_schema.tickets (
    ticket_id bigint NOT NULL,
    customer_name text NOT NULL,
    match_id bigint NOT NULL,
    ticket_price double precision NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE sporting_events_ticket_schema.tickets OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16443)
-- Name: ticket_ticket_id_seq; Type: SEQUENCE; Schema: sporting_events_ticket_schema; Owner: postgres
--

ALTER TABLE sporting_events_ticket_schema.tickets ALTER COLUMN ticket_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_ticket_schema.ticket_ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3319 (class 0 OID 16444)
-- Dependencies: 215
-- Data for Name: tickets; Type: TABLE DATA; Schema: sporting_events_ticket_schema; Owner: postgres
--

COPY sporting_events_ticket_schema.tickets (ticket_id, customer_name, match_id, ticket_price, active) FROM stdin;
37	EditTicket	1	500	f
1	Neptune	2	500	f
2	Mars	1	500	f
3	Jupiter	1	500	f
4	Saturn	1	1000	t
5	Mercury	1	1000	t
6	Earth	1	1000	t
7	Kepler	1	1000	t
8	Venus	1	500	t
9	Uranus	1	500	t
\.


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 214
-- Name: ticket_ticket_id_seq; Type: SEQUENCE SET; Schema: sporting_events_ticket_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_ticket_schema.ticket_ticket_id_seq', 37, true);


--
-- TOC entry 3175 (class 2606 OID 16450)
-- Name: tickets ticket_pkey; Type: CONSTRAINT; Schema: sporting_events_ticket_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_ticket_schema.tickets
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (ticket_id);


-- Completed on 2023-05-18 16:39:20

--
-- PostgreSQL database dump complete
--

