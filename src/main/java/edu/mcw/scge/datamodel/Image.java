package edu.mcw.scge.datamodel;

public class Image {

    private long scgeId;
    private String fileName;
    private byte[] image;
    private byte[] thumbnail;
    private byte[] image700Wide;
    private String bucket;
    private String legend;
    private String title;
    private String fileType;
    private int posIndex;

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public byte[] getImage700Wide() {
        return image700Wide;
    }

    public void setImage700Wide(byte[] image700Wide) {
        this.image700Wide = image700Wide;
    }

    //http://localhost:8080/toolkit/store/18000000013/aboveExperimentTable1/sonth.jpg
    public String getPath() {
        return "/toolkit/store/" + this.scgeId + "/" + this.bucket + "/" + this.fileName;
    }

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
