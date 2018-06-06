package me.leig.electronicschoolbadge.bean;

import java.util.List;

/**
 * 校徽信息对象
 *
 * @author leig
 */

public class SchoolBadge {

    /**
     * message : TagInfo
     * responseTS : 1528166693655
     * tags : [{"responseTS":1528166693655,"devID":"0058","tagID":"0573B7","tag Type":30,"activate":1,"voltage":0,"tamper":0,"button1":0,"button2":0,"gain":0,"through":0,"activateId1":4118,"lfrssi1":"2 5","reTime":1528166691753}]
     */

    private String message;
    private long responseTS;
    private List<TagsBean> tags;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getResponseTS() {
        return responseTS;
    }

    public void setResponseTS(long responseTS) {
        this.responseTS = responseTS;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * responseTS : 1528166693655
         * devID : 0058
         * tagID : 0573B7
         * tag Type : 30
         * activate : 1
         * voltage : 0
         * tamper : 0
         * button1 : 0
         * button2 : 0
         * gain : 0
         * through : 0
         * activateId1 : 4118
         * lfrssi1 : 2 5
         * reTime : 1528166691753
         */

        private long responseTS;
        private String devID;
        private String tagID;
        private int tagType;
        private int activate;
        private int voltage;
        private int tamper;
        private int button1;
        private int button2;
        private int gain;
        private int through;
        private String activateId1;
        private String lfrssi1;
        private long reTime;

        public long getResponseTS() {
            return responseTS;
        }

        public void setResponseTS(long responseTS) {
            this.responseTS = responseTS;
        }

        public String getDevID() {
            return devID;
        }

        public void setDevID(String devID) {
            this.devID = devID;
        }

        public String getTagID() {
            return tagID;
        }

        public void setTagID(String tagID) {
            this.tagID = tagID;
        }

        public int getTagType() {
            return tagType;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }

        public int getActivate() {
            return activate;
        }

        public void setActivate(int activate) {
            this.activate = activate;
        }

        public int getVoltage() {
            return voltage;
        }

        public void setVoltage(int voltage) {
            this.voltage = voltage;
        }

        public int getTamper() {
            return tamper;
        }

        public void setTamper(int tamper) {
            this.tamper = tamper;
        }

        public int getButton1() {
            return button1;
        }

        public void setButton1(int button1) {
            this.button1 = button1;
        }

        public int getButton2() {
            return button2;
        }

        public void setButton2(int button2) {
            this.button2 = button2;
        }

        public int getGain() {
            return gain;
        }

        public void setGain(int gain) {
            this.gain = gain;
        }

        public int getThrough() {
            return through;
        }

        public void setThrough(int through) {
            this.through = through;
        }

        public String getActivateId1() {
            return activateId1;
        }

        public void setActivateId1(String activateId1) {
            this.activateId1 = activateId1;
        }

        public String getLfrssi1() {
            return lfrssi1;
        }

        public void setLfrssi1(String lfrssi1) {
            this.lfrssi1 = lfrssi1;
        }

        public long getReTime() {
            return reTime;
        }

        public void setReTime(long reTime) {
            this.reTime = reTime;
        }
    }
}
