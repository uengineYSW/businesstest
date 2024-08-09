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
public class UpdatePaymentStatusTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        UpdatePaymentStatusTest.class
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

    @Autowired
    public PaymentRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        Payment existingEntity = new Payment();

        existingEntity.setPaymentId("paymentIdExample");
        existingEntity.setPaymentAmount(1000L);
        existingEntity.setPaymentStatus("paymentStatusExample");

        repository.save(existingEntity);

        //when:

        try {
            UpdatePaymentStatus command = new UpdatePaymentStatusCommand();

            command.setPaymentId("paymentIdExample");
            command.setPaymentStatus("paymentStatusExample");

            existingEntity.updatepaymentstatus(command);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(newEntity)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .build(),
                    "businesstest"
                );

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "businesstest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );
            assertNotNull("Resulted event must be published", receivedMessage);

            //then:
            String receivedPayload = (String) receivedMessage.getPayload();

            PaymentStatusUpdated outputEvent = objectMapper.readValue(
                receivedPayload,
                PaymentStatusUpdated.class
            );
            String receivedPayload = (String) receivedMessage.getPayload();

            PaymentStatusUpdated outputEvent = objectMapper.readValue(
                receivedPayload,
                PaymentStatusUpdated.class
            );

            LOGGER.info("Response received: {}", outputEvent);

            assertEquals(outputEvent.getPaymentId(), "generatedValue");
            assertEquals(outputEvent.getPaymentStatus(), "N/A");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }
}
