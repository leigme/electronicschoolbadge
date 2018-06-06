package me.leig.electronicschoolbadge.reader;


import me.leig.electronicschoolbadge.bean.Config;
import me.leig.electronicschoolbadge.bean.SchoolBadge;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 校徽读写器
 *
 * @author leig
 */

public class BadgeReader implements ReadListener {

    private Logger log = Logger.getLogger(BadgeReader.class);

    private Config mConfig;

    private ExecutorService pool;

    private String inActivateId;

    private String outActivateId;

    private ConcurrentHashMap<String, SchoolBadge.TagsBean> inMap;

    private ConcurrentHashMap<String, SchoolBadge.TagsBean> outMap;

    public BadgeReader(Config config) {
        this.mConfig = config;
        this.inActivateId = mConfig.getInLine();
        this.outActivateId = mConfig.getOutLine();
        inMap = new ConcurrentHashMap<>();
        outMap = new ConcurrentHashMap<>();
        pool = Executors.newFixedThreadPool(mConfig.getPoolSize());
    }

    public void startRead() {
        ReadBadge readBadge = new ReadBadge(this, mConfig.getPollingNum(), mConfig.getIntervalTime());
        pool.execute(readBadge);
        SendBadge sendBadge = new SendBadge(mConfig, inMap, outMap);
        pool.execute(sendBadge);
        pool.shutdown();
    }

    @Override
    public void getBadge(SchoolBadge schoolBadge) {
        List<SchoolBadge.TagsBean> tagsBeans = schoolBadge.getTags();
        for (SchoolBadge.TagsBean tagsBean: tagsBeans) {
            if (inActivateId.equals(tagsBean.getActivateId1())) {
                inMap.put(tagsBean.getTagID(), tagsBean);
            } else if (outActivateId.equals(tagsBean.getActivateId1())) {
                outMap.put(tagsBean.getTagID(), tagsBean);
            } else {
                log.info("无法判断: " + tagsBean.getTagID());
            }
            log.info("入校人数: " + inMap.size());
            log.info("出校人数: " + outMap.size());
        }
    }

}
