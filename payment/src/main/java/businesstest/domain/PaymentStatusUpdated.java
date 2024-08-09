package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PaymentStatusUpdated extends AbstractEvent {

    private String paymentId;
    private String paymentStatus;

    public PaymentStatusUpdated(Payment aggregate) {
        super(aggregate);
    }

    public PaymentStatusUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
