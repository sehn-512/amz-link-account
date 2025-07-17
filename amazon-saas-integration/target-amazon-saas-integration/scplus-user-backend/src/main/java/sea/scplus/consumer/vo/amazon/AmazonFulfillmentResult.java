package sea.scplus.consumer.vo.amazon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmazonFulfillmentResult {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Response response;

    public enum Response {
        OK("OK"),
        FAIL_USER_NOT_ELIGIBLE("User is not eligible to purchase this item"),
        FAIL_USER_INVALID("User is invalid"),
        FAIL_INVALID_SUBSCRIPTION("Invalid values are in the JSON fields 'subscriptionId' or 'numberOfLincenses'"),
        FAIL_INVALID_SKU("Invalid value is in the JSON fields 'productId'"),
        FAIL_OTHER("This is a catch-all error code");

        Response(String description) {}
    }
}


