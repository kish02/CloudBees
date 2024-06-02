package com.example.train.repository;

import com.example.train.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TicketRepository {
    private List<Ticket> tickets = new ArrayList<>();
    private AtomicInteger nextId = new AtomicInteger(1);

    public Ticket add(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }
    public int getNextId() {
        return nextId.getAndIncrement();
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets);
    }

    public Optional<Ticket> findById(int id) {
        return tickets.stream().filter(ticket -> ticket.getId() == id).findFirst();
    }

    public List<Ticket> findBySection(String section) {
        return tickets.stream().filter(ticket -> ticket.getSection().equalsIgnoreCase(section)).collect(Collectors.toList());
    }

    public void deleteById(int id) {
        tickets.removeIf(ticket -> ticket.getId() == id);
    }

    public void updateSeat(int id, String newSeat, String newSection) {
        findById(id).ifPresent(ticket -> {
            ticket.setSeat(newSeat);
            ticket.setSection(newSection);
        });
    }
}
