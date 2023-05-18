package com.sportingevents.match;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatchEntityTest {

    @Test
    public void whenInitialized_returnMatch() {
        MatchEntity expected = new MatchEntity();
        expected.setTournamentId(1);
        expected.setFieldId(1);
        expected.setMatchId(1);
        expected.setTeamsId("1,2");
        expected.setParticipantsId("1,2,3,4");
        expected.setDateTime("2023-05-20 10:10:23");
        expected.setActive(true);

        MatchEntity actual = getMatch();
        Assert.assertEquals(expected.getTournamentId(), actual.getTournamentId());
        Assert.assertEquals(expected.getFieldId(), actual.getFieldId());
        Assert.assertEquals(expected.getMatchId(), actual.getMatchId());
        Assert.assertEquals(expected.getTeamsId(), actual.getTeamsId());
        Assert.assertEquals(expected.getParticipantsId(), actual.getParticipantsId());
        Assert.assertEquals(expected.getDateTime(), actual.getDateTime());
        Assert.assertEquals(expected.getActive(), actual.getActive());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullTournamentId_whenInitialized_returnException() {
        MatchEntity match = new MatchEntity();
        match.setTournamentId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullMatchId_whenInitialized_returnException() {
        MatchEntity match = new MatchEntity();
        match.setMatchId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullDateTime_whenInitialized_returnException() {
        MatchEntity match = new MatchEntity();
        match.setDateTime(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullActive_whenInitialized_returnException() {
        MatchEntity match = new MatchEntity();
        match.setActive(null);
    }


    @Test(expected = NullPointerException.class)
    public void givenNullParticipantsId_whenInitialized_returnException() {
        MatchEntity match = new MatchEntity();
        match.setParticipantsId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullTeamsId_whenInitialized_returnException() {
        MatchEntity match = new MatchEntity();
        match.setTeamsId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullFieldId_whenInitialized_returnException() {
        MatchEntity match = new MatchEntity();
        match.setFieldId(null);
    }

    private MatchEntity getMatch() {
        MatchEntity match = new MatchEntity();
        match.setTournamentId(1);
        match.setFieldId(1);
        match.setMatchId(1);
        match.setTeamsId("1,2");
        match.setParticipantsId("1,2,3,4");
        match.setDateTime("2023-05-20 10:10:23");
        match.setActive(true);
        return match;
    }

}
