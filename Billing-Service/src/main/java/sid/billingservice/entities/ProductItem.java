package sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sid.billingservice.models.Product;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ProductItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id ;
    @Transient
    Product product ;
    Long productId;
    double price ;
    double quantity ;
    @ManyToOne @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Bill bill;
}
