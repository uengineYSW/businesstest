package businesstest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancelPaymentCommand {

    private String paymentId;
}
