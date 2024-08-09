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
        //implement business logic here:

        /** Example 1:  new item 
        회원 회원 = new 회원();
        repository().save(회원);

        AddPoint addPoint = new AddPoint(회원);
        addPoint.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCreated.get???()).ifPresent(회원->{
            
            회원 // do something
            repository().save(회원);

            AddPoint addPoint = new AddPoint(회원);
            addPoint.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
