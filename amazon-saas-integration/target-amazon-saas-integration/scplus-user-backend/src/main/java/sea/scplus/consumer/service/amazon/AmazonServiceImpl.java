package sea.scplus.consumer.service.amazon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sea.scplus.consumer.service.RESTAPIService;
import sea.scplus.consumer.vo.amazon.AmazonCheckUserRequest;
import sea.scplus.consumer.vo.amazon.AmazonCheckUserResult;
import sea.scplus.consumer.vo.amazon.AmazonFulfillmentRequest;
import sea.scplus.consumer.vo.amazon.AmazonFulfillmentResult;

import java.io.IOException;

@Slf4j
@Service
public class AmazonServiceImpl implements AmazonService {

    @Autowired
    private RESTAPIService restapiService;

    @Value("${scplus.baseUrl}")
    private String spBaseUrl;

    @Value("${scplus.username}")
    private String scpus_username;

    @Value("${scplus.password}")
    private String scpus_password;

    @Value("${scplus.amazonFulfillmentURL}")
    private String scplusFulfillmentURLL;

    @Value("${scplus.amazonCheckUserURL}")
    private String scplusAmazonCheckUserURL;

    public AmazonCheckUserResult checkUser(final AmazonCheckUserRequest request) throws IOException {
        //Get a Token
        final String channelToken = restapiService.getTokenFromSCPUS(scpus_username, scpus_password);

        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        final String url = spBaseUrl + scplusAmazonCheckUserURL;
        log.info("url = {}", url);
        headers.set("X-AUTH-TOKEN", channelToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        AmazonCheckUserResult amazonCheckUserResult;
        try {
            final ResponseEntity<AmazonCheckUserResult> amazonCheckUserResultResponseEntity = restTemplate.postForEntity(url, new HttpEntity<>(request, headers), AmazonCheckUserResult.class);
            amazonCheckUserResult = amazonCheckUserResultResponseEntity.getBody();
        } catch (Exception e) {
            amazonCheckUserResult = AmazonCheckUserResult
                    .builder()
                    .response(AmazonCheckUserResult.Response.FAIL_ACCOUNT_INVALID.toString())
                    .build();
            log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> error to call checkUser");
            log.error(e.getMessage());
        }
        return amazonCheckUserResult;
    }

    public AmazonFulfillmentResult fulfillment(final AmazonFulfillmentRequest request) throws IOException {
        //Get a Token
        final String channelToken = restapiService.getTokenFromSCPUS(scpus_username, scpus_password);

        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        final String url = spBaseUrl + scplusFulfillmentURLL;
        log.info("url = {}", url);
        headers.set("X-AUTH-TOKEN", channelToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        AmazonFulfillmentResult amazonFulfillmentResult;
        try {
            final ResponseEntity<AmazonFulfillmentResult> amazonFulfillmentResultResponseEntity = restTemplate.postForEntity(url, new HttpEntity<>(request, headers), AmazonFulfillmentResult.class);
            amazonFulfillmentResult = amazonFulfillmentResultResponseEntity.getBody();
        } catch (Exception e) {
            amazonFulfillmentResult = AmazonFulfillmentResult
                    .builder()
                    .response(AmazonFulfillmentResult.Response.FAIL_OTHER)
                    .build();
            log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> error to call fulfillment");
            log.error(e.getMessage());
        }
        return amazonFulfillmentResult;
    }

}
