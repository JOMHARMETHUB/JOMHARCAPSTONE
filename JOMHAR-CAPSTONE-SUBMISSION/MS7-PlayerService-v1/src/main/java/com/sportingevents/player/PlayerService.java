package com.sportingevents.player;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {
    List<PlayerResponseModel> findAllPlayers(Pageable pageable);

    String savePlayer(PlayerRequestModel playerRequestModel);

    String updatePlayer(Integer playerId, PlayerRequestModel playerRequestModel);

    String deletePlayer(Integer playerId);

    PlayerResponseModel findPlayer(Integer playerId);

    List<PlayerResponseModel> findPlayers(String playerIds);

}
