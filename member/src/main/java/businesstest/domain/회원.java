package businesstest.domain;

import businesstest.MemberApplication;
import businesstest.domain.AddPoint;
import businesstest.domain.회원가입완료;
import businesstest.domain.회원탈퇴완료;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "회원_table")
@Data
//<<< DDD / Aggregate Root
public class 회원 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String 이름;

    private String 이메일;

    private Long point;

    @PostPersist
    public void onPostPersist() {
        회원가입완료 회원가입완료 = new 회원가입완료(this);
        회원가입완료.publishAfterCommit();

        AddPoint addPoint = new AddPoint(this);
        addPoint.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreRemove
    public void onPreRemove() {
        회원탈퇴완료 회원탈퇴완료 = new 회원탈퇴완료(this);
        회원탈퇴완료.publishAfterCommit();
    }

    public static 회원Repository repository() {
        회원Repository 회원Repository = MemberApplication.applicationContext.getBean(
            회원Repository.class
        );
        return 회원Repository;
    }

    //<<< Clean Arch / Port Method
    public static void addPoint(PaymentCreated paymentCreated) {
        repository()
            .findById(paymentCreated.getPaymentId())
            .ifPresent(회원 -> {
                long pointToAdd = 0L;
                if (paymentCreated.getPaymentAmount() <= 10000) {
                    pointToAdd = paymentCreated.getPaymentAmount() * 3 / 100;
                } else if (paymentCreated.getPaymentAmount() <= 50000) {
                    pointToAdd = paymentCreated.getPaymentAmount() * 5 / 100;
                } else if (paymentCreated.getPaymentAmount() > 50000) {
                    pointToAdd = paymentCreated.getPaymentAmount() * 10 / 100;
                }
                회원.setPoint(회원.getPoint() + pointToAdd);
                repository().save(회원);
                AddPoint addPoint = new AddPoint(회원);
                addPoint.publishAfterCommit();
            });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
