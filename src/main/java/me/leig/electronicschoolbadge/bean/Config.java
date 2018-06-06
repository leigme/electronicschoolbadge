package me.leig.electronicschoolbadge.bean;

import me.leig.electronicschoolbadge.comm.Constant;

/**
 * @author leig
 */

public class Config {

    private int poolSize = Constant.poolSize;
    private int pollingNum = Constant.pollingNum;
    private long intervalTime = Constant.intervalTime;
    private String inLine;
    private String outLine;

    private String server = Constant.server;
    private int port = Constant.port;

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getPollingNum() {
        return pollingNum;
    }

    public void setPollingNum(int pollingNum) {
        this.pollingNum = pollingNum;
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getInLine() {
        return inLine;
    }

    public void setInLine(String inLine) {
        this.inLine = inLine;
    }

    public String getOutLine() {
        return outLine;
    }

    public void setOutLine(String outLine) {
        this.outLine = outLine;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
