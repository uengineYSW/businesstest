package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AddPoint extends AbstractEvent {

    private String 이름;
    private String 이메일;
    private Long point;

    public AddPoint(회원 aggregate) {
        super(aggregate);
    }

    public AddPoint() {
        super();
    }
}
//>>> DDD / Domain Event
