package sea.scplus.consumer.vo.amazon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class AmazonFulfillmentRequest {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Operation operation;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String subscriptionId;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String productId;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String userId;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Period period;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Reason reason;

    public enum Operation {
        SubscriptionActivate, SubscriptionDeactivate
    }

    public enum Period {
        GRACE_PERIOD("Action occurred during the risk-free period"),
        REGULAR("Action occurred while the subscription was valid"),
        NOT_STARTED("The subscription was purchased but not activated"),
        FREE_TRIAL("Used for free trial subscriptions");

        Period(String description) {
        }
    }

    public enum Reason {
        NOT_RENEWED("The subscription reached end of its term"),
        USER_REQUEST("The user deactivated the subscription vai Amazon.com"),
        CUSTOMER_SERVICE_REQUEST("Deactivation initiated by Amazon Customer Service"),
        PAYMENT_PROBLEM("Deactivation initiated due to an error in processing the customer's payment"),
        UNABLE_TO_FULFILL("Subscription was canceled due to a problem activating the subscription"),
        TESTING("Test request, please ignore");

        Reason(String description) {
        }
    }
}
