package me.leig.electronicschoolbadge.reader;

import me.leig.electronicschoolbadge.bean.Config;
import me.leig.electronicschoolbadge.bean.SchoolBadge;
import me.leig.electronicschoolbadge.bean.StudentBadge;
import me.leig.electronicschoolbadge.comm.Constant;
import me.leig.electronicschoolbadge.contact.UDPSocket;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leig
 */

public class SendBadge implements Runnable {

    private Logger log = Logger.getLogger(SendBadge.class);

    private Config mConfig;

    private int i = 0;

    private int total= 0;

    private long sleepNum = 0L;

    private ConcurrentHashMap<String, SchoolBadge.TagsBean> inMap;

    private ConcurrentHashMap<String, SchoolBadge.TagsBean> outMap;

    private UDPSocket mSocket;

    public SendBadge(ConcurrentHashMap<String, SchoolBadge.TagsBean> inMap, ConcurrentHashMap<String, SchoolBadge.TagsBean> outMap) {
        this(new Config(), inMap, outMap);
    }

    public SendBadge(Config config, ConcurrentHashMap<String, SchoolBadge.TagsBean> inMap, ConcurrentHashMap<String, SchoolBadge.TagsBean> outMap) {
        this.mConfig = config;
        this.total = mConfig.getPollingNum();
        this.sleepNum = mConfig.getIntervalTime();
        this.inMap = inMap;
        this.outMap = outMap;
        mSocket = new UDPSocket();
    }

    @Override
    public void run() {

        mSocket.startUDPSocket();

        while (Constant.pollingNum == total || i < total) {
            log.info("执行了.... {" + inMap.size() + "}{" + outMap.size() + "}");
            i += 1;
            try {
                execute();
                Thread.sleep(sleepNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("捕获异常: " + e.getMessage());
            }
        }


    }

    private void execute() {
        log.info("当前集合中的变量是: " + "{" + inMap.size() + "}{" + outMap.size() + "}");
        for (SchoolBadge.TagsBean tagsBean: inMap.values()) {
            StudentBadge studentBadge = new StudentBadge();
            studentBadge.setTagId(tagsBean.getTagID());
            studentBadge.setPunchTime(tagsBean.getResponseTS());
            SchoolBadge.TagsBean outTagsBean = outMap.get(tagsBean.getTagID());
            if (null != outTagsBean && tagsBean.getResponseTS() > outTagsBean.getResponseTS()) {
                studentBadge.setPunchType(2);
                outMap.remove(tagsBean.getTagID());
            } else {
                studentBadge.setPunchType(1);
                inMap.remove(tagsBean.getTagID());
            }
            mSocket.sendMsg(studentBadge);
            log.info("[进校学生]: " + studentBadge.getTagId());
        }

        for (SchoolBadge.TagsBean tagsBean: outMap.values()) {
            StudentBadge studentBadge = new StudentBadge();
            studentBadge.setTagId(tagsBean.getTagID());
            studentBadge.setPunchTime(tagsBean.getResponseTS());
            SchoolBadge.TagsBean inTagsBean = inMap.get(tagsBean.getTagID());
            if (null != inTagsBean && tagsBean.getResponseTS() > inTagsBean.getResponseTS()) {
                studentBadge.setPunchType(1);
                inMap.remove(tagsBean.getTagID());
            } else {
                studentBadge.setPunchType(2);
                outMap.remove(tagsBean.getTagID());
            }
            mSocket.sendMsg(studentBadge);
            log.info("[出校学生]: " + studentBadge.getTagId());
        }
    }

}
