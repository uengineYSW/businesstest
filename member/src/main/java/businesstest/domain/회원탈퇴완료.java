package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class 회원탈퇴완료 extends AbstractEvent {

    private String 이름;
    private String 이메일;
    private Long point;

    public 회원탈퇴완료(회원 aggregate) {
        super(aggregate);
    }

    public 회원탈퇴완료() {
        super();
    }
}
//>>> DDD / Domain Event
