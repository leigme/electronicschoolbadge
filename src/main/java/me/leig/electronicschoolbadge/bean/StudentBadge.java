package me.leig.electronicschoolbadge.bean;

/**
 * @author leig
 */

public class StudentBadge {

    private int tagId;

    private long punchTime;

    private int punchType;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = Integer.parseInt(tagId, 16);
    }

    public long getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(long punchTime) {
        this.punchTime = punchTime;
    }

    public int getPunchType() {
        return punchType;
    }

    public void setPunchType(int punchType) {
        this.punchType = punchType;
    }
}
