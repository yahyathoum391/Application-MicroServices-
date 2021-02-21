package sid.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sid.billingservice.models.Customer;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    Date billingDate ;
    @OneToMany(mappedBy = "bill")
    Collection<ProductItem> productItems;
    @Transient Customer customer ;
    Long customerID;


}
