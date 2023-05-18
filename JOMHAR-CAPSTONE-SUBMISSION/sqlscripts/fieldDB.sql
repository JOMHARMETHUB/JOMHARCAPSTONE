--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:37:22

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
-- Name: sporting_events_field_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_field_schema;


ALTER SCHEMA sporting_events_field_schema OWNER TO pg_database_owner;

--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_field_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_field_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16551)
-- Name: fields; Type: TABLE; Schema: sporting_events_field_schema; Owner: postgres
--

CREATE TABLE sporting_events_field_schema.fields (
    field_id bigint NOT NULL,
    field_name text NOT NULL,
    field_address text NOT NULL,
    capacity bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE sporting_events_field_schema.fields OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16550)
-- Name: fields_field_id_seq; Type: SEQUENCE; Schema: sporting_events_field_schema; Owner: postgres
--

ALTER TABLE sporting_events_field_schema.fields ALTER COLUMN field_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_field_schema.fields_field_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3319 (class 0 OID 16551)
-- Dependencies: 215
-- Data for Name: fields; Type: TABLE DATA; Schema: sporting_events_field_schema; Owner: postgres
--

COPY sporting_events_field_schema.fields (field_id, field_name, field_address, capacity, active) FROM stdin;
1	Octagon	123 sample st.	500	t
2	battlefield	432 sample st.	200	t
4	newField	fieldAddress	1000	t
3	newFieldU	fieldAddressU	1000	f
5	newField	fieldAddress	1000	t
6	newField	fieldAddress	1000	t
7	newField	fieldAddress	1000	t
8	newField	fieldAddress	1000	t
9	newField	fieldAddress	1000	t
10	newField	fieldAddress	1000	t
11	newField	fieldAddress	1000	t
\.


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 214
-- Name: fields_field_id_seq; Type: SEQUENCE SET; Schema: sporting_events_field_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_field_schema.fields_field_id_seq', 11, true);


--
-- TOC entry 3175 (class 2606 OID 16558)
-- Name: fields fields_pkey; Type: CONSTRAINT; Schema: sporting_events_field_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_field_schema.fields
    ADD CONSTRAINT fields_pkey PRIMARY KEY (field_id);


-- Completed on 2023-05-18 16:37:23

--
-- PostgreSQL database dump complete
--

