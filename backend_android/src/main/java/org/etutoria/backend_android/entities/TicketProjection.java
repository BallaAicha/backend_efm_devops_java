package org.etutoria.backend_android.entities;
import org.springframework.data.rest.core.config.Projection;
//cette classe est une interface qui permet de projeter les attributs de
// la classe Ticket cad de selectionner les attributs que l'on veut afficher sur le front
@Projection(name = "ticketProj",types = Ticket.class)
public interface TicketProjection {

    public Long getId();
    String getUsername();
    public double getPrix();
    public Integer getCodePayment();
    public boolean getReserve();
    public Place getPlace();

}
