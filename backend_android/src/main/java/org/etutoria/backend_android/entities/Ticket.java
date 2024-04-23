package org.etutoria.backend_android.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private double prix;
    @Column(unique = true, nullable = false)
    private String codePayment;
    private boolean reserve;
    @ManyToOne
    @JsonIgnore
    private Place place;
    @ManyToOne
    @JsonIgnore
    private Projection projection;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}
