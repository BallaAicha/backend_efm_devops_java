package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.TicketRepository;
import org.etutoria.backend_android.entities.Ticket;
import org.etutoria.backend_android.entities.TicketProjection;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TicketServiceImpl implements TicketService{
    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);

    }

    @Override
    public Ticket getTicket(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Ticket ID cannot be null");
        }
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket with ID " + id + " not found"));
    }


    @Override
    public Collection<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


}
