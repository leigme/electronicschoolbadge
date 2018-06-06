package me.leig.electronicschoolbadge.reader;

import com.google.gson.Gson;
import com.rw.testutil.api.WebApi;
import me.leig.electronicschoolbadge.comm.Constant;
import me.leig.electronicschoolbadge.bean.SchoolBadge;
import org.apache.log4j.Logger;

/**
 * 读写线程
 *
 * @author leig
 */

public class ReadBadge implements Runnable {

    private Logger log = Logger.getLogger(ReadBadge.class);

    private int i = 0;

    private int total= 0;

    private long sleepNum = 0L;

    private ReadListener mReadListener;

    public ReadBadge(ReadListener readListener) {
        this(readListener, Constant.pollingNum);
    }

    public ReadBadge(ReadListener readListener, int total) {
        this(readListener, total, Constant.intervalTime);
    }

    public ReadBadge(ReadListener readListener, long sleepNum) {
        this(readListener, Constant.pollingNum, sleepNum);
    }

    public ReadBadge(ReadListener readListener, int total, long sleepNum) {
        this.mReadListener = readListener;
        this.total = total;
        this.sleepNum = sleepNum;
    }

    @Override
    public void run() {

        Gson gson = new Gson();

        while (Constant.pollingNum == total || i < total) {
            i += 1;
            try {
                String json = WebApi.dataOperate();
                if (null != json && !"".equals(json)) {
                    SchoolBadge schoolBadge = gson.fromJson(json, SchoolBadge.class);
                    if (null != schoolBadge && null != mReadListener) {
                        mReadListener.getBadge(schoolBadge);
                    }
                }
                Thread.sleep(sleepNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("捕获异常: " + e.getMessage());
            }
        }
    }
}
