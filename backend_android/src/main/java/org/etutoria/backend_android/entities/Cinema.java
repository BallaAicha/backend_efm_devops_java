package org.etutoria.backend_android.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private long id;
    private String name;
    private double  longitude;
    private double latitude;
    private double altitude;
    private int nombreSalles;
    @ManyToOne
    @JsonIgnore
    private Ville ville;
    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;


}
