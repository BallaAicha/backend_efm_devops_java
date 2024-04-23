package org.etutoria.backend_android.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TicketInfo {
    private Place place;
    private double prix;
    private Projection projection;
}