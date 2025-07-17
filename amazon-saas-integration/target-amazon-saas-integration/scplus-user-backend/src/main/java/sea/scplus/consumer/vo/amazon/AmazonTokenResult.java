package sea.scplus.consumer.vo.amazon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class AmazonTokenResult {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private boolean success;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private int code;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String access_token;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String expire_time;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String msg;
}
