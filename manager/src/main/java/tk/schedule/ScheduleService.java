package tk.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/8/21
 * @Version: 1.0
 * @Description:
 */
@Service
public class ScheduleService {

    private Logger LOG = LoggerFactory.getLogger(ScheduleService.class);

    public void tokenRefresh() {
        LOG.debug("定时推送体力");


    }

}
