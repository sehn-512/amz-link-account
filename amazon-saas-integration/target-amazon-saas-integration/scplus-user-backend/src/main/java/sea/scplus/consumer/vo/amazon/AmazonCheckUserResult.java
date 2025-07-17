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
public class AmazonCheckUserResult {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String response;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String userId;

    public enum Response {
        OK, FAIL_ACCOUNT_INVALID
    }

}


