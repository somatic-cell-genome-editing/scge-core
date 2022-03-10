package edu.mcw.scge.datamodel.publications;

public class ArticleId  {
    private String id;
    private String idType;
    private String url;
    private long scgeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getScgeId() {
        return scgeId;
    }

    public void setScgeId(long scgeId) {
        this.scgeId = scgeId;
    }
}
