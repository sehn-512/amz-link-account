package sea.scplus.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sea.scplus.consumer.service.amazon.AmazonService;
import sea.scplus.consumer.vo.amazon.AmazonCheckUserRequest;
import sea.scplus.consumer.vo.amazon.AmazonCheckUserResult;
import sea.scplus.consumer.vo.amazon.AmazonFulfillmentRequest;
import sea.scplus.consumer.vo.amazon.AmazonFulfillmentResult;


@Controller
@RequestMapping("/amazon")
public class AmazonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AmazonController.class);

    @Autowired
    AmazonService amazonService;

    @RequestMapping(value = "/check-user", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody AmazonCheckUserResult checkUser(@RequestBody AmazonCheckUserRequest request) throws Exception {
        return amazonService.checkUser(request);
    }

    @RequestMapping(value = "/fulfillment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody AmazonFulfillmentResult fulfillment(@RequestBody AmazonFulfillmentRequest request) throws Exception {
        return amazonService.fulfillment(request);
    }

}
