package businesstest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class 회원가입Command {

    private String 이름;
    private Email 이메일;
}
