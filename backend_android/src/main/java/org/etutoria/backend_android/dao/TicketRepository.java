package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
