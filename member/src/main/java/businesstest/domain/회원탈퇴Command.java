package businesstest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class 회원탈퇴Command {

    private Email 이메일;
}
