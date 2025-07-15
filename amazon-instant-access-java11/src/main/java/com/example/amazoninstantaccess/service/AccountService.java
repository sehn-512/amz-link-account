package com.example.amazoninstantaccess.service;

import com.example.amazoninstantaccess.dto.GetUserIdRequest;
import com.example.amazoninstantaccess.dto.GetUserIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    public GetUserIdResponse getUserId(GetUserIdRequest request) {
        log.info("Getting user ID for: {}", request.getInfoField1());

        GetUserIdResponse response = new GetUserIdResponse();
        response.setResponse("OK");
        response.setUserId("USER-" + System.currentTimeMillis());

        return response;
    }
}
