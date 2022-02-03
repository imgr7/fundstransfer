package sr283106.foundation.fundstransfer.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TransactionMessage implements Serializable {

    LocalDate timeStamp;
    String message;
    String status;
}
