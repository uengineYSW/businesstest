package businesstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import businesstest.config.kafka.KafkaProcessor;
import businesstest.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class AddPointTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        AddPointTest.class
    );

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MessageVerifier<Message<?>> messageVerifier;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:

        entity.set("회원 이름");
        entity.set("회원 이메일");
        entity.setPoint(100L);

        repository.save(entity);

        //when:

        PaymentCreated event = new PaymentCreated();

        event.setPaymentId("12345");
        event.setPaymentAmount(5000L);
        event.setPaymentStatus("결제 완료");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String msg = objectMapper.writeValueAsString(event);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build(),
                    "businesstest"
                );

            //then:

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "businesstest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );

            assertNotNull("Resulted event must be published", receivedMessage);

            AddPoint outputEvent = objectMapper.readValue(
                receivedMessage.getPayload(),
                AddPoint.class
            );

            LOGGER.info("Response received: {}", receivedMessage.getPayload());

            assertEquals(outputEvent.get(), "회원 이름");
            assertEquals(outputEvent.get(), "회원 이메일");
            assertEquals(outputEvent.getPoint(), 150L);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test1() {
        //given:

        entity.set("회원 이름");
        entity.set("회원 이메일");
        entity.setPoint(100L);

        repository.save(entity);

        //when:

        PaymentCreated event = new PaymentCreated();

        event.setPaymentId("67890");
        event.setPaymentAmount(20000L);
        event.setPaymentStatus("결제 완료");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String msg = objectMapper.writeValueAsString(event);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build(),
                    "businesstest"
                );

            //then:

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "businesstest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );

            assertNotNull("Resulted event must be published", receivedMessage);

            AddPoint outputEvent = objectMapper.readValue(
                receivedMessage.getPayload(),
                AddPoint.class
            );

            LOGGER.info("Response received: {}", receivedMessage.getPayload());

            assertEquals(outputEvent.get(), "회원 이름");
            assertEquals(outputEvent.get(), "회원 이메일");
            assertEquals(outputEvent.getPoint(), 1000L);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test2() {
        //given:

        entity.set("회원 이름");
        entity.set("회원 이메일");
        entity.setPoint(100L);

        repository.save(entity);

        //when:

        PaymentCreated event = new PaymentCreated();

        event.setPaymentId("54321");
        event.setPaymentAmount(75000L);
        event.setPaymentStatus("결제 완료");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String msg = objectMapper.writeValueAsString(event);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build(),
                    "businesstest"
                );

            //then:

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "businesstest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );

            assertNotNull("Resulted event must be published", receivedMessage);

            AddPoint outputEvent = objectMapper.readValue(
                receivedMessage.getPayload(),
                AddPoint.class
            );

            LOGGER.info("Response received: {}", receivedMessage.getPayload());

            assertEquals(outputEvent.get(), "회원 이름");
            assertEquals(outputEvent.get(), "회원 이메일");
            assertEquals(outputEvent.getPoint(), 7500L);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }
}
