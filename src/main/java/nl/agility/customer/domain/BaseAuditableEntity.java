package nl.agility.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditableEntity extends BaseEntity {

    @JsonIgnore
    @CreatedDate
    @Column(name = "CREATED", insertable = true, updatable = false, nullable = false)
    private LocalDateTime created;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "LAST_UPDATED")
    private LocalDateTime lastUpdated;

}
