package edu.mcw.scge.datamodel.publications;

import java.util.List;
import java.util.Map;

public class Publication {
    private Reference reference;
    private List<Author> authorList;
    private List<ArticleId> articleIds;

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<ArticleId> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<ArticleId> articleIds) {
        this.articleIds = articleIds;
    }
}
