package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PaymentCreated extends AbstractEvent {

    private String paymentId;
    private Long paymentAmount;
    private String paymentStatus;

    public PaymentCreated(Payment aggregate) {
        super(aggregate);
    }

    public PaymentCreated() {
        super();
    }
}
//>>> DDD / Domain Event
