package businesstest.infra;

import businesstest.config.kafka.KafkaProcessor;
import businesstest.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    회원Repository 회원Repository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='회원탈퇴완료'"
    )
    public void whenever회원탈퇴완료_탈퇴정책(
        @Payload 회원탈퇴완료 회원탈퇴완료
    ) {
        회원탈퇴완료 event = 회원탈퇴완료;
        System.out.println(
            "\n\n##### listener 탈퇴정책 : " + 회원탈퇴완료 + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentCreated'"
    )
    public void wheneverPaymentCreated_AddPoint(
        @Payload PaymentCreated paymentCreated
    ) {
        PaymentCreated event = paymentCreated;
        System.out.println(
            "\n\n##### listener AddPoint : " + paymentCreated + "\n\n"
        );

        // Comments //
        //결제 금액이 10000원 이하일 때, 금액의 3%를 포인트로 적립 / 결제 금액이 50000원 이하일 때, 금액의 5%를 포인트로 적립  / 결제 금액이 50000원 초과일 때, 금액의 10%를 포인트로 적립

        // Sample Logic //
        회원.addPoint(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
