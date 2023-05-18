package com.sportingevents.ticket;

import com.sportingevents.common.util.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public List<TicketResponseModel> getAllTickets(Pageable pageable) {
        List<TicketEntity> ticketEntityList = ticketRepository.findByActiveTrue(pageable).getContent();
        if(ticketEntityList.isEmpty())
            throw new TicketException(TicketMessage.NO_TICKETS_FOUND);
        return ticketEntityList.parallelStream().map(this::mapTicketToResponseModel).collect(Collectors.toList());
    }

    @Override
    public TicketResponseModel getTicket(Integer ticketId) {
        Optional<TicketEntity> ticketEntityOptional = ticketRepository.findByTicketIdAndActiveTrue(ticketId);
        if(!ticketEntityOptional.isPresent())
            throw new TicketException(TicketMessage.NO_TICKETS_FOUND);
        return mapTicketToResponseModel(ticketEntityOptional.get());
    }

    private TicketResponseModel mapTicketToResponseModel(TicketEntity ticketEntity) {
        TicketResponseModel ticketResponseModel = new TicketResponseModel();
        ticketResponseModel.setTicketId(ticketEntity.getTicketId());
        ticketResponseModel.setCustomerName(ticketEntity.getCustomerName());
        ticketResponseModel.setTicketPrice(ticketEntity.getTicketPrice());
        ticketResponseModel.setMatchId(ticketEntity.getMatchId());
        return ticketResponseModel;
    }

    @Override
    public String saveTicket(TicketRequestModel ticketRequestModel) {
        if(!NameUtil.isValidName(ticketRequestModel.getCustomerName()))
            throw new TicketException(TicketMessage.INVALID_NAME);
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setCustomerName(ticketRequestModel.getCustomerName());
        ticketEntity.setMatchId(ticketRequestModel.getMatchId());
        ticketEntity.setTicketPrice(ticketRequestModel.getTicketPrice());
        ticketRepository.saveAndFlush(ticketEntity);
        return TicketMessage.TICKET_SAVE_SUCCESS;
    }

    @Override
    public String updateTicket(Integer ticketId, TicketRequestModel ticketRequestModel) {
        Optional<TicketEntity> ticketEntityOptional = ticketRepository.findByTicketIdAndActiveTrue(ticketId);
        if(!ticketEntityOptional.isPresent())
            throw new TicketException(TicketMessage.NO_TICKETS_FOUND);
        if(!NameUtil.isValidName(ticketRequestModel.getCustomerName()))
            throw new TicketException(TicketMessage.INVALID_NAME);
        TicketEntity ticketEntity = ticketEntityOptional.get();
        ticketEntity.setCustomerName(ticketRequestModel.getCustomerName());
        ticketEntity.setMatchId(ticketRequestModel.getMatchId());
        ticketEntity.setTicketPrice(ticketRequestModel.getTicketPrice());
        ticketRepository.saveAndFlush(ticketEntity);
        return TicketMessage.TICKET_UPDATE_SUCCESS;
    }

    @Override
    public String deleteTicket(Integer ticketId) {
        Optional<TicketEntity> ticketEntityOptional = ticketRepository.findByTicketIdAndActiveTrue(ticketId);
        if(!ticketEntityOptional.isPresent())
            throw new TicketException(TicketMessage.NO_TICKETS_FOUND);
        TicketEntity ticketEntity = ticketEntityOptional.get();
        ticketEntity.setActive(false);
        ticketRepository.saveAndFlush(ticketEntity);
        return TicketMessage.TICKET_DELETE_SUCCESS;
    }
}
