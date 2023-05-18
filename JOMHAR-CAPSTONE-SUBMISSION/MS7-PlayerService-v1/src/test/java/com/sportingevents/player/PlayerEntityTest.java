package com.sportingevents.player;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlayerEntityTest {

    @Test
    public void whenPlayerInitialized_returnPlayer() {
        PlayerEntity expected = new PlayerEntity();
        expected.setPlayerId(1);
        expected.setActive(true);
        expected.setCountry("test");
        expected.setFirstName("test");
        expected.setLastName("test");
        expected.setTeamId(1);

        PlayerEntity actual = getPlayer();

        Assert.assertEquals(expected.getPlayerId(), actual.getPlayerId());
        Assert.assertEquals(expected.getActive(), actual.getActive());
        Assert.assertEquals(expected.getCountry(), actual.getCountry());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getTeamId(), actual.getTeamId());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPlayerId_whenInitialized_returnException() {
        PlayerEntity player = new PlayerEntity();
        player.setPlayerId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullCountry_whenInitialized_returnException() {
        PlayerEntity player = new PlayerEntity();
        player.setCountry(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullActive_whenInitialized_returnException() {
        PlayerEntity player = new PlayerEntity();
        player.setActive(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullFirstName_whenInitialized_returnException() {
        PlayerEntity player = new PlayerEntity();
        player.setFirstName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullLastName_whenInitialized_returnException() {
        PlayerEntity player = new PlayerEntity();
        player.setLastName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullTeamId_whenInitialized_returnException() {
        PlayerEntity player = new PlayerEntity();
        player.setTeamId(null);
    }

    private PlayerEntity getPlayer() {
        PlayerEntity player = new PlayerEntity();
        player.setPlayerId(1);
        player.setActive(true);
        player.setCountry("test");
        player.setFirstName("test");
        player.setLastName("test");
        player.setTeamId(1);
        return player;
    }
}
