package de.plimplom.gamba.server.alias;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alias")
@Getter
@Setter
public class AliasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alias;
    private String player;
}
