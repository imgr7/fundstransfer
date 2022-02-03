package sr283106.foundation.fundstransfer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name="customerSeq",initialValue = 205)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customerSeq")
    Long customerId;
    String customerName;
    String contactNumber;
    @Email(message = "Please enter valid Email Id")
    String emailId;
    @Past
    LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "customerAddressId")
    CustomerAddress customerAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "accountNumber")
    Account account;
}
