package businesstest.infra;

import businesstest.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class PaymentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Payment>> {

    @Override
    public EntityModel<Payment> process(EntityModel<Payment> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "//updatepaymentstatus"
                )
                .withRel("/updatepaymentstatus")
        );

        return model;
    }
}
