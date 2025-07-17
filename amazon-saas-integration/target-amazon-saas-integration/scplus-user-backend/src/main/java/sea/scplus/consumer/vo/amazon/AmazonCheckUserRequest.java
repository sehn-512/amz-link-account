package sea.scplus.consumer.vo.amazon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class AmazonCheckUserRequest {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String operation;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String infoField1;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String infoField2;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String infoField3;
}
