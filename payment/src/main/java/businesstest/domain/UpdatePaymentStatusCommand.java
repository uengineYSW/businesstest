package businesstest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdatePaymentStatusCommand {

    private String paymentId;
    private String paymentStatus;
}
