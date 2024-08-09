package businesstest.domain;

import businesstest.PaymentApplication;
import businesstest.domain.PaymentCancelled;
import businesstest.domain.PaymentCreated;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
//<<< DDD / Aggregate Root
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String paymentId;

    private Long paymentAmount;

    private String paymentStatus;

    @PostPersist
    public void onPostPersist() {
        PaymentCreated paymentCreated = new PaymentCreated(this);
        paymentCreated.publishAfterCommit();

        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
        paymentCancelled.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    //<<< Clean Arch / Port Method
    public void updatePaymentStatus(
        UpdatePaymentStatusCommand updatePaymentStatusCommand
    ) {
        //implement business logic here:

        PaymentStatusUpdated paymentStatusUpdated = new PaymentStatusUpdated(
            this
        );
        paymentStatusUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
