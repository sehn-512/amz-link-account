package sea.scplus.consumer.service.impl;

import sea.scplus.consumer.persistence.api.APITargetMapper;
import sea.scplus.consumer.service.APITargetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APITargetServiceImpl implements APITargetService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(APILoggingServiceImpl.class);
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private APITargetMapper apiTargetMapper;

	@Override
	public int selectVerifyAPITarget(String systemid, String password) throws Exception {

		int cnt = apiTargetMapper.selectVerifyAPITarget(systemid, password);

		return cnt;
	}
	
}
