--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:37:40

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
-- Name: sporting_events_log_schema; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA sporting_events_log_schema;


ALTER SCHEMA sporting_events_log_schema OWNER TO pg_database_owner;

--
-- TOC entry 3324 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA sporting_events_log_schema; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA sporting_events_log_schema IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16624)
-- Name: logs; Type: TABLE; Schema: sporting_events_log_schema; Owner: postgres
--

CREATE TABLE sporting_events_log_schema.logs (
    log_id bigint NOT NULL,
    service_name text NOT NULL,
    uri text NOT NULL,
    email text NOT NULL,
    method text NOT NULL,
    date_time text NOT NULL
);


ALTER TABLE sporting_events_log_schema.logs OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16623)
-- Name: logs_log_id_seq; Type: SEQUENCE; Schema: sporting_events_log_schema; Owner: postgres
--

ALTER TABLE sporting_events_log_schema.logs ALTER COLUMN log_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME sporting_events_log_schema.logs_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3318 (class 0 OID 16624)
-- Dependencies: 215
-- Data for Name: logs; Type: TABLE DATA; Schema: sporting_events_log_schema; Owner: postgres
--

COPY sporting_events_log_schema.logs (log_id, service_name, uri, email, method, date_time) FROM stdin;
57	player-service	/api/v1/players	test2@mail.com	GET	2023-05-11 22:05:32
58	player-service	/api/v1/players	test2@mail.com	POST	2023-05-11 22:05:44
59	player-service	/api/v1/players/10	test2@mail.com	PUT	2023-05-11 22:05:54
60	player-service	/api/v1/players/12	test2@mail.com	DELETE	2023-05-11 22:06:03
61	player-service	/api/v1/players/2	test2@mail.com	GET	2023-05-11 22:06:12
62	player-service	/api/v1/players/multiple	test2@mail.com	GET	2023-05-11 22:06:23
63	player-service	/api/v1/players	test2@mail.com	GET	2023-05-11 22:25:29
64	player-service	/api/v1/players	test2@mail.com	GET	2023-05-11 22:28:10
65	team-service	/api/v1/teams/1	test2@mail.com	GET	2023-05-11 22:33:18
66	team-service	/api/v1/teams	test2@mail.com	POST	2023-05-11 22:33:45
67	team-service	/api/v1/teams/1	test2@mail.com	PUT	2023-05-11 22:33:48
68	team-service	/api/v1/teams	test2@mail.com	GET	2023-05-11 22:34:09
69	team-service	/api/v1/teams/10	test2@mail.com	GET	2023-05-11 22:34:50
70	team-service	/api/v1/teams/9	test2@mail.com	GET	2023-05-11 22:34:55
71	team-service	/api/v1/teams/8	test2@mail.com	GET	2023-05-11 22:34:59
72	team-service	/api/v1/teams/10	test2@mail.com	DELETE	2023-05-11 22:35:18
73	team-service	/api/v1/teams/8	test2@mail.com	DELETE	2023-05-11 22:35:24
74	team-service	/api/v1/teams/8	test2@mail.com	DELETE	2023-05-11 22:38:11
75	team-service	/api/v1/teams/8	test2@mail.com	DELETE	2023-05-11 22:38:14
76	team-service	/api/v1/teams/8	test2@mail.com	DELETE	2023-05-11 22:38:48
77	team-service	/api/v1/teams/1	test2@mail.com	PUT	2023-05-11 22:38:51
78	team-service	/api/v1/teams	test2@mail.com	POST	2023-05-11 22:38:53
79	team-service	/api/v1/teams/multiple	test2@mail.com	GET	2023-05-11 22:38:56
80	player-service	/api/v1/players	test2@mail.com	GET	2023-05-11 22:44:55
81	team-service	/api/v1/teams/8	test2@mail.com	GET	2023-05-11 22:45:01
82	team-service	/api/v1/teams/8	test2@mail.com	GET	2023-05-11 22:47:01
83	player-service	/api/v1/players	test2@mail.com	GET	2023-05-11 22:47:04
84	player-service	/api/v1/players/12	test2@mail.com	DELETE	2023-05-11 22:47:06
85	team-service	/api/v1/teams/8	test2@mail.com	DELETE	2023-05-11 22:47:08
86	player-service	/api/v1/players	jomhar.samson@mail.com	POST	2023-05-12 10:24:21
87	team-service	/api/v1/teams/1	jomhar.samson@mail.com	GET	2023-05-12 10:24:21
88	player-service	/api/v1/players	jomhar.samson@mail.com	POST	2023-05-12 10:25:16
89	team-service	/api/v1/teams/1	jomhar.samson@mail.com	GET	2023-05-12 10:25:16
90	player-service	/api/v1/players/12	jomhar.samson@mail.com	DELETE	2023-05-12 10:26:40
91	player-service	/api/v1/players/2	jomhar.samson@mail.com	GET	2023-05-12 10:27:45
92	player-service	/api/v1/players/multiple	jomhar.samson@mail.com	GET	2023-05-12 10:28:35
93	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-12 10:29:15
94	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-12 10:29:17
95	team-service	/api/v1/teams/1	jomhar.samson@mail.com	PUT	2023-05-12 10:29:19
96	team-service	/api/v1/teams/8	jomhar.samson@mail.com	DELETE	2023-05-12 10:29:28
97	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-12 10:30:24
98	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-12 10:30:35
99	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-12 10:30:39
100	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-12 10:31:03
101	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-12 12:06:47
102	player-service	/api/v1/players	jomhar.samson@mail.com	GET	2023-05-12 12:06:57
103	player-service	/api/v1/players/2	jomhar.samson@mail.com	GET	2023-05-12 12:07:01
104	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-12 12:07:05
105	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-12 12:07:07
106	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-12 12:11:26
107	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	POST	2023-05-12 12:11:39
108	ticket-service	/api/v1/tickets/3	jomhar.samson@mail.com	PUT	2023-05-12 12:11:41
109	ticket-service	/api/v1/tickets/10	jomhar.samson@mail.com	DELETE	2023-05-12 12:11:43
110	ticket-service	/api/v1/tickets/3	jomhar.samson@mail.com	GET	2023-05-12 12:11:44
111	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-12 13:58:18
112	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-12 13:58:18
113	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-12 13:58:18
114	player-service	/api/v1/players	jomhar.samson@mail.com	GET	2023-05-12 15:19:29
115	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-12 15:19:34
116	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-12 15:19:38
117	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-12 15:21:00
118	match-service	/api/v1/matches/1	jomhar.samson@mail.com	GET	2023-05-12 15:21:15
119	match-service	/api/v1/matches	jomhar.samson@mail.com	POST	2023-05-12 15:21:17
120	team-service	/api/v1/teams/multiple	jomhar.samson@mail.com	GET	2023-05-12 15:21:18
121	player-service	/api/v1/players/multiple	jomhar.samson@mail.com	GET	2023-05-12 15:21:18
122	match-service	/api/v1/matches/10	jomhar.samson@mail.com	DELETE	2023-05-12 15:21:30
123	match-service	/api/v1/matches/9	jomhar.samson@mail.com	PUT	2023-05-12 15:21:32
124	team-service	/api/v1/teams/multiple	jomhar.samson@mail.com	GET	2023-05-12 15:21:33
125	player-service	/api/v1/players/multiple	jomhar.samson@mail.com	GET	2023-05-12 15:21:33
126	field-service	/api/v1/fields	jomhar.samson@mail.com	GET	2023-05-12 15:25:55
127	field-service	/api/v1/fields	jomhar.samson@mail.com	POST	2023-05-12 15:25:59
128	field-service	/api/v1/fields/3	jomhar.samson@mail.com	PUT	2023-05-12 15:26:01
129	field-service	/api/v1/fields/3	jomhar.samson@mail.com	DELETE	2023-05-12 15:26:03
130	field-service	/api/v1/fields/3	jomhar.samson@mail.com	GET	2023-05-12 15:26:05
131	player-service	/api/v1/players	jomhar.samson@mail.com	GET	2023-05-12 15:34:07
132	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-12 15:34:10
133	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-12 15:34:13
134	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-12 15:34:15
135	field-service	/api/v1/fields	jomhar.samson@mail.com	GET	2023-05-12 15:34:17
136	tournament-service	/api/v1/tournaments	jomhar.samson@mail.com	GET	2023-05-12 15:34:25
137	player-service	/api/v1/players	jomhar.samson@mail.com	GET	2023-05-15 12:49:54
138	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-15 12:54:13
139	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 12:54:17
140	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 12:54:24
141	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 12:54:25
142	team-service	/api/v1/teams/1	jomhar.samson@mail.com	PUT	2023-05-15 12:54:30
143	team-service	/api/v1/teams/1	jomhar.samson@mail.com	PUT	2023-05-15 12:54:34
144	team-service	/api/v1/teams/8	jomhar.samson@mail.com	DELETE	2023-05-15 12:54:37
145	team-service	/api/v1/teams/8	jomhar.samson@mail.com	GET	2023-05-15 16:18:44
146	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 16:18:48
147	team-service	/api/v1/teams	jomhar.samson@mail.com	GET	2023-05-15 16:18:51
148	team-service	/api/v1/teams/16	jomhar.samson@mail.com	DELETE	2023-05-15 16:18:59
149	team-service	/api/v1/teams/16	jomhar.samson@mail.com	DELETE	2023-05-15 16:19:01
150	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 16:19:09
151	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 16:19:10
152	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 16:19:12
153	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 16:19:14
154	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 16:19:16
155	team-service	/api/v1/teams/17	jomhar.samson@mail.com	GET	2023-05-15 16:19:21
156	team-service	/api/v1/teams/17	jomhar.samson@mail.com	DELETE	2023-05-15 16:19:27
157	team-service	/api/v1/teams/17	jomhar.samson@mail.com	GET	2023-05-15 16:19:28
158	team-service	/api/v1/teams	jomhar.samson@mail.com	POST	2023-05-15 16:19:32
159	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-15 17:09:37
160	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-15 17:30:52
161	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-15 17:30:53
162	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-15 17:30:53
163	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-15 17:55:00
164	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-15 17:55:06
165	player-service	/api/v1/players	jomhar.samson@mail.com	GET	2023-05-15 17:55:12
166	field-service	/api/v1/fields	jomhar.samson@mail.com	GET	2023-05-15 17:55:18
167	tournament-service	/api/v1/tournaments	jomhar.samson@mail.com	GET	2023-05-15 17:55:22
168	tournament-service	/api/v1/tournaments	jomhar.samson@mail.com	GET	2023-05-15 17:55:28
169	player-service	/api/v1/players	jomhar.samson@mail.com	GET	2023-05-15 18:01:37
170	team-service	/api/v1/teams/17	jomhar.samson@mail.com	GET	2023-05-15 18:01:42
171	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-15 18:01:47
172	team-service	/api/v1/teams	jomhar.samson@mail.com	GET	2023-05-15 18:01:49
173	match-service	/api/v1/matches	jomhar.samson@mail.com	GET	2023-05-15 18:01:56
174	field-service	/api/v1/fields	jomhar.samson@mail.com	GET	2023-05-15 18:01:59
175	tournament-service	/api/v1/tournaments	jomhar.samson@mail.com	GET	2023-05-15 18:02:04
176	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-16 17:38:19
177	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-16 17:50:07
178	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 10:03:43
179	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 10:14:29
180	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 10:47:43
181	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 10:54:16
182	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 10:56:15
183	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 10:56:20
184	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 11:15:11
185	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 14:32:13
186	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	POST	2023-05-17 14:32:47
187	match-service	/api/v1/matches/1000	jomhar.samson@mail.com	GET	2023-05-17 14:32:47
188	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	POST	2023-05-17 14:33:20
189	match-service	/api/v1/matches/1	jomhar.samson@mail.com	GET	2023-05-17 14:33:20
190	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	POST	2023-05-17 14:33:27
191	match-service	/api/v1/matches/1	jomhar.samson@mail.com	GET	2023-05-17 14:33:27
192	ticket-service	/api/v1/tickets/3	jomhar.samson@mail.com	PUT	2023-05-17 14:33:44
193	match-service	/api/v1/matches/1000	jomhar.samson@mail.com	GET	2023-05-17 14:33:44
194	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 14:33:53
195	ticket-service	/api/v1/tickets/11111	jomhar.samson@mail.com	PUT	2023-05-17 14:34:08
196	match-service	/api/v1/matches/1	jomhar.samson@mail.com	GET	2023-05-17 14:34:08
197	ticket-service	/api/v1/tickets/37	jomhar.samson@mail.com	PUT	2023-05-17 14:34:15
198	match-service	/api/v1/matches/1	jomhar.samson@mail.com	GET	2023-05-17 14:34:15
199	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-17 14:34:24
200	ticket-service	/api/v1/tickets/37	jomhar.samson@mail.com	GET	2023-05-17 14:34:40
201	ticket-service	/api/v1/tickets/37	jomhar.samson@mail.com	DELETE	2023-05-17 14:34:54
202	ticket-service	/api/v1/tickets/37	jomhar.samson@mail.com	DELETE	2023-05-17 14:35:00
203	ticket-service	/api/v1/tickets/37	jomhar.samson@mail.com	GET	2023-05-17 14:35:05
204	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-18 13:54:04
205	ticket-service	/api/v1/tickets	jomhar.samson@mail.com	GET	2023-05-18 13:54:10
\.


--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 214
-- Name: logs_log_id_seq; Type: SEQUENCE SET; Schema: sporting_events_log_schema; Owner: postgres
--

SELECT pg_catalog.setval('sporting_events_log_schema.logs_log_id_seq', 205, true);


--
-- TOC entry 3174 (class 2606 OID 16630)
-- Name: logs logs_pkey; Type: CONSTRAINT; Schema: sporting_events_log_schema; Owner: postgres
--

ALTER TABLE ONLY sporting_events_log_schema.logs
    ADD CONSTRAINT logs_pkey PRIMARY KEY (log_id);


-- Completed on 2023-05-18 16:37:40

--
-- PostgreSQL database dump complete
--

