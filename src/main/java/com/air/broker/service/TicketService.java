package com.air.broker.service;

import com.air.broker.model.Ticket;
import com.air.broker.model.TicketBookingRequest;
import com.air.broker.repositary.TicketBookingRepository;
import com.air.broker.repositary.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    TicketRepository repository;

    @Autowired
    TicketBookingRepository bookingRepository;

    public void deleteTicket(Long id) {
        repository.deleteById(id);
    }

    public Optional<Ticket> getTicket(Long id) {
        System.out.println(id);
        return repository.findById(id);
    }

    public Ticket saveTicket(Ticket ticket) {
        return  repository.save(ticket);
    }

    public TicketBookingRequest saveTicketBookingRequest(TicketBookingRequest ticket) {
        return bookingRepository.save(ticket);
    }
}
