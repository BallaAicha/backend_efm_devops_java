package org.etutoria.backend_android.entities;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class TicketForm {
    private User user;
    private String codePayment;
    private List<Ticket> tickets = new ArrayList<>();
}
