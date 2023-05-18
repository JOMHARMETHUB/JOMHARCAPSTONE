package com.sportingevents.player;

import com.sportingevents.common.util.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<PlayerResponseModel> findAllPlayers(Pageable pageable) {
        List<PlayerEntity> playerEntityList = playerRepository.findByActiveTrue(pageable).getContent();
        if(playerEntityList.isEmpty())
            throw new PlayerException(PlayerMessage.NO_PLAYERS_FOUND);
        return playerEntityList.stream().map(this::mapToResponseModel).collect(Collectors.toList());
    }

    @Override
    public String savePlayer(PlayerRequestModel playerRequestModel) {
        if(!NameUtil.isValidName(playerRequestModel.getFirstName()))
            throw new PlayerException(PlayerMessage.INVALID_NAME);
        if(!NameUtil.isValidName(playerRequestModel.getLastName()))
            throw new PlayerException(PlayerMessage.INVALID_NAME);

        PlayerEntity playerEntity = new PlayerEntity();

        playerEntity.setFirstName(playerRequestModel.getFirstName().toUpperCase());
        playerEntity.setLastName(playerRequestModel.getLastName().toUpperCase());
        playerEntity.setCountry(playerRequestModel.getCountry().toUpperCase());
        playerEntity.setTeamId(playerRequestModel.getTeamId());

        playerRepository.saveAndFlush(playerEntity);
        return PlayerMessage.PLAYER_SAVE_SUCCESS;
    }

    @Override
    public String updatePlayer(Integer playerId, PlayerRequestModel playerRequestModel) {
        Optional<PlayerEntity> playerEntityOptional = playerRepository.findByPlayerIdAndActiveTrue(playerId);
        if(!playerEntityOptional.isPresent())
            throw new PlayerException(PlayerMessage.PLAYER_NOT_FOUND);

        if(!NameUtil.isValidName(playerRequestModel.getFirstName()))
            throw new PlayerException(PlayerMessage.INVALID_NAME);
        if(!NameUtil.isValidName(playerRequestModel.getLastName()))
            throw new PlayerException(PlayerMessage.INVALID_NAME);

        PlayerEntity playerEntity = playerEntityOptional.get();
        playerEntity.setFirstName(playerRequestModel.getFirstName().toUpperCase());
        playerEntity.setLastName(playerRequestModel.getLastName().toUpperCase());
        playerEntity.setCountry(playerRequestModel.getCountry().toUpperCase());
        playerEntity.setTeamId(playerRequestModel.getTeamId());

        playerRepository.saveAndFlush(playerEntity);
        return PlayerMessage.PLAYER_UPDATE_SUCCESS;
    }

    @Override
    public String deletePlayer(Integer playerId) {
        Optional<PlayerEntity> playerEntityOptional = playerRepository.findByPlayerIdAndActiveTrue(playerId);
        if(!playerEntityOptional.isPresent())
            throw new PlayerException(PlayerMessage.PLAYER_NOT_FOUND);
        PlayerEntity playerEntity = playerEntityOptional.get();
        playerEntity.setActive(false);
        playerRepository.saveAndFlush(playerEntity);
        return PlayerMessage.PLAYER_DELETE_SUCCESS;
    }

    @Override
    public PlayerResponseModel findPlayer(Integer playerId) {
        Optional<PlayerEntity> playerEntityOptional = playerRepository.findByPlayerIdAndActiveTrue(playerId);
        if(!playerEntityOptional.isPresent())
            throw new PlayerException(PlayerMessage.PLAYER_NOT_FOUND);
        return mapToResponseModel(playerEntityOptional.get());
    }

    @Override
    public List<PlayerResponseModel> findPlayers(String playerIds) {
        String[] playerId = playerIds.split(",");
        List<PlayerResponseModel> playerResponseModels = new ArrayList<>();
        Arrays.stream(playerId).forEach(id -> {
            Optional<PlayerEntity> playerEntityOptional = playerRepository.findById(Integer.parseInt(id));
            if(!playerEntityOptional.isPresent())
                throw new PlayerException(PlayerMessage.PLAYER_NOT_FOUND);
            playerResponseModels.add(mapToResponseModel(playerEntityOptional.get()));
        });
        return playerResponseModels;
    }

    private PlayerResponseModel mapToResponseModel(PlayerEntity playerEntity) {
        PlayerResponseModel playerResponseModel = new PlayerResponseModel();
        playerResponseModel.setPlayerId(playerEntity.getPlayerId());
        playerResponseModel.setFirstName(playerEntity.getFirstName());
        playerResponseModel.setLastName(playerEntity.getLastName());
        playerResponseModel.setCountry(playerEntity.getCountry());
        playerResponseModel.setTeamId(playerEntity.getTeamId());
        playerResponseModel.setActive(playerEntity.getActive());
        return playerResponseModel;
    }


}
