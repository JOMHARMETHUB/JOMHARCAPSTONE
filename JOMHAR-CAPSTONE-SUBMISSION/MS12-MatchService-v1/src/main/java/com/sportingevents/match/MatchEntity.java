package com.sportingevents.match;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "matches", schema = "sporting_events_match_schema")
@NoArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "match_id")
    private Integer matchId;

    @NonNull
    @Column(name = "field_id")
    private Integer fieldId;

    @NonNull
    @Column(name = "tournament_id")
    private Integer tournamentId;

    @NonNull
    @Column(name = "teams_id")
    private String teamsId;

    @NonNull
    @Column(name = "participants_id")
    private String participantsId;

    @NonNull
    @Column(name = "date_time")
    private String dateTime;

    @NonNull
    @Column(name = "active")
    private Boolean active = true;

}
