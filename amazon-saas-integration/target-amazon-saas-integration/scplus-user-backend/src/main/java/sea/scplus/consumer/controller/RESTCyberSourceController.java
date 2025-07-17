package sea.scplus.consumer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sea.scplus.consumer.common.web.JsonResponse;
import sea.scplus.consumer.service.RESTCommonService;
import sea.scplus.consumer.service.RESTCyberSourceService;
import sea.scplus.consumer.vo.Meta;
import sea.scplus.consumer.vo.RequestContractCancel;
import sea.scplus.consumer.vo.RequestContractCreate;
import sea.scplus.consumer.vo.RequestContractStatus;
import sea.scplus.consumer.vo.RequestSerialNoValidation;
import sea.scplus.consumer.vo.RequestSpcValidation;
import sea.scplus.consumer.vo.ResponseContractCancel;
import sea.scplus.consumer.vo.ResponseContractCreate;
import sea.scplus.consumer.vo.ResponseContractStatus;
import sea.scplus.consumer.vo.ResponseSerialNoValidation;
import sea.scplus.consumer.vo.ResponseSpcValidation;
import sea.scplus.consumer.vo.cancel.RequestCancel;
import sea.scplus.consumer.vo.cancel.ResponseCancel;
import sea.scplus.consumer.vo.payment.RequestPayment;
import sea.scplus.consumer.vo.payment.ResponsePayment;

@Controller
@RequestMapping("/cyber")
public class RESTCyberSourceController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RESTCyberSourceController.class);

    @Autowired
	private MessageSourceAccessor messageSourceAccessor;
    
    @Autowired
	private RESTCyberSourceService restCyberSourceService;
    
    /**
     * https://apitest.cybersource.com/pts/v2/payments/
     * @param RequestContractCeate
     * {
		    "clientReferenceInformation": {
		        "code": "TC50171_3"
		    },
		    "processingInformation": {
		        "capture": true,
		        "commerceIndicator": "internet"
		    },
		    "paymentInformation": {
		        "card": {
		            "number": "4111111111111111",
		            "securityCode": "123",
		            "expirationMonth": "12",
		            "expirationYear": "2031"
		        }
		    },
		    "orderInformation": {
		        "amountDetails": {
		            "totalAmount": "102.21",
		            "currency": "USD"
		        },
		        "billTo": {
		            "firstName": "John",
		            "lastName": "Doe",
		            "address1": "1 Market St",
		            "locality": "san francisco",
		            "administrativeArea": "CA",
		            "postalCode": "94105",
		            "country": "US",
		            "email": "test@cybs.com",
		            "phoneNumber": "4158880000"
		        }
		    }
		}
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/payment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody JsonResponse payment(@RequestBody RequestPayment requestPayment) throws Exception { 
		Meta meta = new Meta();
		meta.setResult("SUCCESS");
		
		ResponsePayment response  = restCyberSourceService.payment(requestPayment);
		
		if ("fail".equals(response)) {
			meta.setResult("FAIL");
		}
		
		return new JsonResponse(meta, response);
	}
    
    /**
     * https://apitest.cybersource.com/pts/v2/payments/{id}/refunds
     * @param RequestContractCeate
request
{
    "clientReferenceInformation": {
        "code": "test_void"
    }
}
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody JsonResponse cancel(@RequestBody RequestCancel requestCancel) throws Exception { 
		Meta meta = new Meta();
		meta.setResult("SUCCESS");
		
		ResponseCancel response  = restCyberSourceService.cancel(requestCancel);
		
		if ("fail".equals(response)) {
			meta.setResult("FAIL");
		}
		
		return new JsonResponse(meta, response);
	}
    
}
