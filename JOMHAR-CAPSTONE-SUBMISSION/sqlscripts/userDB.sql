--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:40:09

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
-- Name: sporting_events_user_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_user_schema;


ALTER SCHEMA sporting_events_user_schema OWNER TO pg_database_owner;

--
-- TOC entry 3328 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_user_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_user_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16405)
-- Name: users; Type: TABLE; Schema: sporting_events_user_schema; Owner: postgres
--

CREATE TABLE sporting_events_user_schema.users (
    user_id bigint NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    phone_number text NOT NULL,
    first_name text,
    middle_name text,
    last_name text
);


ALTER TABLE sporting_events_user_schema.users OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16412)
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: sporting_events_user_schema; Owner: postgres
--

ALTER TABLE sporting_events_user_schema.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_user_schema.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3321 (class 0 OID 16405)
-- Dependencies: 214
-- Data for Name: users; Type: TABLE DATA; Schema: sporting_events_user_schema; Owner: postgres
--

COPY sporting_events_user_schema.users (user_id, email, password, phone_number, first_name, middle_name, last_name) FROM stdin;
5	user@mail.com	$2a$10$/7nAmiMXVjASPdm/xyWsRenOqkbcbx4QXMuxcte3NmPgjhSyP6wgS	09922839293	USERFN	USERMN	USERLN
1	jomhar.samson@mail.com	$2a$10$/7nAmiMXVjASPdm/xyWsRenOqkbcbx4QXMuxcte3NmPgjhSyP6wgS	09123482730	JOMHAR	PIMENTEL	SAMSON
7	usertest@mail.com	$2a$10$Zs8AdiSd3i7BmalLEhQZd.XCouiqWQtCch94dKEXItRY4H9bhbft2	123123123	USERFN	USERMN	USERLN
8	usertest123@mail.com	$2a$10$D1RF4ZVu0D3ti.og12RvPuj5OLSnlJ0DesEszLokSX13NNzE9ohgC	333333333	USERFN	USERMN	USERLN
10	test@mail.com	$2a$10$seKnvUDNM5p6M6RlFErIseJlVbzAbh6AODxyADr1h.qtUD/liCoi6	32132423421	USERFN	USERMN	USERLN
11	test2@mail.com	$2a$10$zekMLOnpkCyArRNTPIyXPuEJ3FTZd4r5CGhJeEWMBXyQhysIqjNjy	5584943049	USERFN	USERMN	USERLN
\.


--
-- TOC entry 3329 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_user_id_seq; Type: SEQUENCE SET; Schema: sporting_events_user_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_user_schema.user_user_id_seq', 11, true);


--
-- TOC entry 3174 (class 2606 OID 16415)
-- Name: users email; Type: CONSTRAINT; Schema: sporting_events_user_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_user_schema.users
    ADD CONSTRAINT email UNIQUE (email);


--
-- TOC entry 3176 (class 2606 OID 16417)
-- Name: users phone_number; Type: CONSTRAINT; Schema: sporting_events_user_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_user_schema.users
    ADD CONSTRAINT phone_number UNIQUE (phone_number);


--
-- TOC entry 3178 (class 2606 OID 16411)
-- Name: users user_pkey; Type: CONSTRAINT; Schema: sporting_events_user_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_user_schema.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


-- Completed on 2023-05-18 16:40:10

--
-- PostgreSQL database dump complete
--

