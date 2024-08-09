package businesstest.domain;

import businesstest.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "회원", path = "회원")
public interface 회원Repository
    extends PagingAndSortingRepository<회원, String> {}
