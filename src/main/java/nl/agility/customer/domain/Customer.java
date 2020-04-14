package nl.agility.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @JsonIgnore
    @Column(name = "CREATED")
    private LocalDateTime created;

    @JsonIgnore
    @Column(name = "LAST_UPDATED")
    private LocalDateTime lastUpdated;

    @NotBlank
    @Column(name = "NAME")
    private String name;
}
