package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Ticket;
import org.etutoria.backend_android.entities.TicketProjection;

import java.util.Collection;
import java.util.Dictionary;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);
    Ticket updateTicket(Ticket ticket);
    void deleteTicket(Ticket ticket);
    Ticket getTicket(Long id);
    Collection<Ticket> getAllTickets();


}
