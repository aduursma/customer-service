package nl.agility.customer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="customers")
public class Customer extends BaseAuditableEntity {

    @NotBlank
    @Column(name = "NAME")
    private String name;

}
