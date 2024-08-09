package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentCreated extends AbstractEvent {

    private String paymentId;
    private Long paymentAmount;
    private String paymentStatus;
}
