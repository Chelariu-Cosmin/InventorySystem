package ro.beenear.onlineshop.inventoryservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "quantity")
    private int quantityOnHand;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Lot lot = (Lot) o;

        return id != null && Objects.equals(id, lot.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
