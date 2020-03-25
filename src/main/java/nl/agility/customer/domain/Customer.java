package nl.agility.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = AUTO)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String version;

    @JsonIgnore
    private LocalDate created;

    @JsonIgnore
    private LocalDate lastUpdated;

    private String name;
}
