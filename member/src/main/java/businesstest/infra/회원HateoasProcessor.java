package businesstest.infra;

import businesstest.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class 회원HateoasProcessor
    implements RepresentationModelProcessor<EntityModel<회원>> {

    @Override
    public EntityModel<회원> process(EntityModel<회원> model) {
        return model;
    }
}
