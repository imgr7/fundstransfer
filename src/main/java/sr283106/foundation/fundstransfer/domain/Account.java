package sr283106.foundation.fundstransfer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {
    @Id
    Long accountNumber;
    BigDecimal balanceAmount;
    String accountOwnerName;
    String branchName;
    String bankName;
    String ifscCode;
    String branchAddress;
    String accountType;
}
