package org.etutoria.backend_android.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.etutoria.backend_android.entities.Role;
import org.etutoria.backend_android.entities.Ticket;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true)
    private String username;
    private String email;

    @Column(nullable = true)
    private Integer age ;
    private String password;
    private Boolean enabled;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") , inverseJoinColumns = @JoinColumn(name="role_id"))
    @JsonIgnore
    private List<Role> roles;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();




}
