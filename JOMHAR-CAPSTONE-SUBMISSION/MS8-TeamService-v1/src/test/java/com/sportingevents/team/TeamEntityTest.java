package com.sportingevents.team;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamEntityTest {

    @Test
    public void whenTeamInitialized_returnTeam() {
        TeamEntity expected = new TeamEntity();
        expected.setTeamName("test");
        expected.setTeamId(1);
        expected.setActive(true);

        TeamEntity actual = getTeam();

        Assert.assertEquals(expected.getTeamId(), actual.getTeamId());
        Assert.assertEquals(expected.getTeamName(), actual.getTeamName());
        Assert.assertEquals(expected.getActive(), actual.getActive());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullTeamName_whenInitialized_returnException() {
        TeamEntity team = new TeamEntity();
        team.setTeamName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullTeamId_whenInitialized_returnException() {
        TeamEntity team = new TeamEntity();
        team.setTeamId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullActive_whenInitialized_returnException() {
        TeamEntity team = new TeamEntity();
        team.setActive(null);
    }

    private TeamEntity getTeam() {
        TeamEntity team = new TeamEntity();
        team.setTeamName("test");
        team.setTeamId(1);
        team.setActive(true);
        return team;
    }
}
