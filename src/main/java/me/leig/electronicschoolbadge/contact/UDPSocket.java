package me.leig.electronicschoolbadge.contact;

import me.leig.electronicschoolbadge.bean.Config;
import me.leig.electronicschoolbadge.bean.StudentBadge;
import org.apache.log4j.Logger;

import java.net.*;
import java.nio.ByteBuffer;

/**
 * 通信服务
 *
 * @author leig
 */

public class UDPSocket {

    private Logger log = Logger.getLogger(UDPSocket.class);

    private Config mConfig;

    private InetAddress mAddress;

    private int mPort;

    private DatagramSocket mSocket = null;

    public UDPSocket() {
        this(new Config());
    }

    public UDPSocket(Config config) {
        this.mConfig = config;

    }

    public void startUDPSocket() {
        if (mSocket != null) return;
        try {

            mAddress = InetAddress.getByName(mConfig.getServer());

            mPort = mConfig.getPort();
            // 表明这个 Socket 在设置的端口上监听数据。
            mSocket = new DatagramSocket(20201);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("捕获异常: " + e.getMessage());
        }
    }

    public void sendMsg(StudentBadge studentBadge) {

        try {

            ByteBuffer byteBuffer = msgConvert(studentBadge);

            log.info("====>" + byteBuffer.array().length);

            DatagramPacket outPacket = new DatagramPacket(byteBuffer.array(), byteBuffer.arrayOffset(), mAddress, mPort);

            mSocket.send(outPacket);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("捕获异常: " + e.getMessage());
        }
    }

    private ByteBuffer msgConvert(StudentBadge studentBadge) {

        /**
         *
         * int version 9001
         * int total 90
         * int tagId 00357303
         * long punchTime 1000000000
         * int punchType 1 进校 2离校
         *
         */

        int total = 4 + 4 + 4 + 8 + 4;

        ByteBuffer byteBuffer = ByteBuffer.allocate(32);

        byteBuffer.putInt(9001);
        byteBuffer.putInt(total);
        byteBuffer.putInt(studentBadge.getTagId());
        byteBuffer.putLong(studentBadge.getPunchTime());
        byteBuffer.putInt(studentBadge.getPunchType());

        byteBuffer.flip();

        return byteBuffer;
    }

}
