package edu.mcw.scge.datamodel;

public class Image {

    private long scgeId;
    private String fileName;
    private byte[] image;
    private String bucket;
    private String legend;
    private String title;
    private String fileType;
    private int posIndex;

    public int getPosIndex() {
        return posIndex;
    }

    public void setPosIndex(int posIndex) {
        this.posIndex = posIndex;
    }

    public long getScgeId() {
        return scgeId;
    }

    public void setScgeId(long scgeId) {
        this.scgeId = scgeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }



}
