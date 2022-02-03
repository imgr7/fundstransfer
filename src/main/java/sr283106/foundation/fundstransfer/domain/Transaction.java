package sr283106.foundation.fundstransfer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long transactionId;
    @NotNull
    Long fromAccount;
    @NotNull
    Long toAccount;
    @NotNull
    BigDecimal transferAmount;
    String reference;
    LocalDate transactionDate;
}
