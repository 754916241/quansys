package com.wyj.quansystem.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wyj
 * @date 2018/4/12 12:02
 */
@Component
public class ScheduledComponent {

    @Scheduled(cron = "0/5")
    public void scheduled(){

    }
}
