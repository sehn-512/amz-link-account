package sea.scplus.consumer.service.amazon;

import org.springframework.stereotype.Service;
import sea.scplus.consumer.vo.amazon.AmazonCheckUserRequest;
import sea.scplus.consumer.vo.amazon.AmazonCheckUserResult;
import sea.scplus.consumer.vo.amazon.AmazonFulfillmentRequest;
import sea.scplus.consumer.vo.amazon.AmazonFulfillmentResult;

import java.io.IOException;

@Service
public interface AmazonService {
    AmazonCheckUserResult checkUser(final AmazonCheckUserRequest request) throws IOException;

    AmazonFulfillmentResult fulfillment(final AmazonFulfillmentRequest request) throws IOException;

}