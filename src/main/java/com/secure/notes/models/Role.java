package com.secure.notes.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.secure.notes.models.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name",length = 20)
    private AppRole roleName;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private Set<User> users =new HashSet<>();

    public Role(AppRole roleName) {
        this.roleName = roleName;
    }

}
