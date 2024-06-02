package com.example.train.service;

import com.example.train.model.Ticket;
import com.example.train.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket purchaseTicket(String from, String to, String firstName, String lastName, String email, String seat, String section) {
        int nextId = ticketRepository.getNextId();
        double price = 20.0;

        Ticket ticket = new Ticket(nextId, from, to, firstName, lastName, email, price, seat, section);
        ticketRepository.add(ticket);
        return ticket;
    }

    public Optional<Ticket> getReceipt(int id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getUsersBySection(String section) {
        return ticketRepository.findBySection(section);
    }

    public void removeUser(int id) {
        ticketRepository.deleteById(id);
    }

    public void modifySeat(int id, String newSeat, String newSection) {
        ticketRepository.updateSeat(id, newSeat, newSection);
    }
}
