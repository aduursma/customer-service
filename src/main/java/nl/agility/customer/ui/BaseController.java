package nl.agility.customer.ui;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import static nl.agility.customer.ui.BaseController.CUSTOMER_V1;

@Validated
@RequestMapping(path = "/api", produces = CUSTOMER_V1)
public class BaseController {

    public static final String CUSTOMER_V1 = "application/vnd.customer.api.v1+json";

}
