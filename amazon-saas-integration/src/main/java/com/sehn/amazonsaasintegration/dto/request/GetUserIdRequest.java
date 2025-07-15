package com.sehn.amazonsaasintegration.dto.request;
import lombok.Data;

@Data
public class GetUserIdRequest {
    private String operation;
    private String infoField1;
    private String infoField2;
    private String infoField3;
}
