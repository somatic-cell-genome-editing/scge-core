package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.dao.spring.publications.AuthorQuery;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.LongListQuery;
import edu.mcw.scge.dao.spring.publications.ReferenceQuery;
import edu.mcw.scge.dao.spring.publications.ArticleIdQuery;
import edu.mcw.scge.datamodel.publications.ArticleId;
import edu.mcw.scge.datamodel.publications.Author;
import edu.mcw.scge.datamodel.publications.Publication;
import edu.mcw.scge.datamodel.publications.Reference;
import edu.mcw.scge.process.pubmedProcessor.Extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicationDAO extends AbstractDAO {
    public List<Long> getAllPMIDs() throws Exception {
        String sql="select identifier from publication_ids where identifier_type='pubmed'";
        LongListQuery query=new LongListQuery(this.getDataSource(), sql);
        return query.execute();
    }
    public List<ArticleId> getAllPubmedIDs() throws Exception {
        String sql="select * from publication_ids where identifier_type='pubmed'";
        ArticleIdQuery query=new ArticleIdQuery(this.getDataSource(), sql);
        return query.execute();
    }
    public List<Reference> getAllReferences() throws Exception {
        String sql="select * from publications";
        ReferenceQuery referenceQuery=new ReferenceQuery(this.getDataSource(), sql);
        return referenceQuery.execute();
    }
    public List<Reference> getReferencesNotAssociatedByObjectId(long objectId) throws Exception {
        String sql="select * from publications where ref_key not in " +
                "(select ref_key from pub_associations where scge_id=?)";
        ReferenceQuery referenceQuery=new ReferenceQuery(this.getDataSource(), sql);
        return execute(referenceQuery, objectId);
    }
    public boolean existsAssociation(int refkey, long scgdId) throws Exception {
        String sql="select ref_key from pub_associations where ref_key=? and scge_id=?";
        IntListQuery referenceQuery=new IntListQuery(this.getDataSource(), sql);
        List<Integer> refKeys=execute(referenceQuery,refkey, scgdId);
        return refKeys.size() > 0;
    }
    public List<Author> getAuthorsByRefKey(int refKey) throws Exception {
        String sql="Select * from pub_authors authors, pub_author_associations assoc where authors.author_Key=assoc.author_key and assoc.ref_key=?";
        AuthorQuery authorQuery=new AuthorQuery(this.getDataSource(), sql);
        return execute(authorQuery,refKey);
    }
    public  List<ArticleId> getArticleIdsByRefKey(int refKey) throws Exception {
        String sql="select * from publication_ids where ref_Key=?";
        return getArticleIds(refKey, sql);
    }
    public List<ArticleId> getArticleIdsSCGEID(int scgeId) throws Exception {
        String sql="select * from publication_ids  where ref_key in (select ref_key from publications p , pub_associations a " +
                " where p.ref_key=a.ref_key and a.scge_id=?" ;
        return getArticleIds(scgeId, sql);
    }

    private List<ArticleId> getArticleIds(int id, String sql) throws Exception {
        ArticleIdQuery query=new ArticleIdQuery(this.getDataSource(),sql);
         return execute(query,id);
    }


    public void deletePubAssociation(long scgeId, int refKey )throws Exception{
        String sql="delete from pub_associations  where ref_key=? and scge_id=?";
        update(sql,refKey, scgeId);
    }

    public void insertPubIds(int pubIdKey, int refKey, String identifier, String idType) throws Exception {
        String sql="insert into publication_ids (pub_id_key, ref_key, " +
                "identifier_type, " +
                "identifier " +
                ") values (?,?,?,?) ";
        update(sql, pubIdKey, refKey,idType, identifier);
    }
    public void updatePubIds(int pubIdKey, int refKey, String identifier, String idType) throws Exception {
        String sql="update publication_ids set  ref_key=?, " +
                "identifier_type=?, " +
                "identifier =? where pub_id_key=?";
        update(sql, refKey,idType, identifier,pubIdKey);
    }
    public void insertXDB(int xdbKey,  String xdbName, String xdbURL) throws Exception {
        String sql="insert into xdb_urls (xdb_key, xdb_name, " +
                "xdb_url " +
                ") values (?,?,?) ";
        update(sql, xdbKey, xdbName,xdbURL);
    }
    public String getXDBUrl(String xdbName) throws Exception {
        String sql="select xdb_url from xdb_urls where xdb_name=?";
        StringListQuery query=new StringListQuery(this.getDataSource(),sql);
        List<String> urls=execute(query, xdbName);
        if(urls.size()>0) return urls.get(0);
        else return "";
    }
    public int getPubIdKey(String id, String idType) throws Exception {
        String sql="select pub_id_key from publication_ids where identifier=? and identifier_type=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        List<Integer> pubIdKeys=execute(query, id, idType);
        if(pubIdKeys.size()>0)
            return pubIdKeys.get(0);
        else return 0;
    }
    public void insertPubAssociations(int refKey, long scgeId,String type) throws Exception {
        String sql="insert into pub_associations (ref_key, " +
                "scge_id, " +
                "type " +
                ") values (?,?,?) ";
        update(sql, refKey,scgeId, type);
    }
    public List<Reference> getPublicationsBySCGEId(long scgeId) throws Exception {
        String query = " select * from publications p \n" +
                "   left outer join pub_associations pa on p.ref_key=pa.ref_key\n" +
                "   where scge_id=? ";
       return  executeRefQuery(query, scgeId);
    }
    public List<Reference> getAssociatedPublicationsBySCGEId(long scgeId) throws Exception {
        String query = "select * from publications p where ref_key in (select distinct(ref_key) from pub_associations where scge_id=? and type='associated') ";
        return executeRefQuery(query, scgeId);

    }
    public List<Reference> getRelatedPublicationsBySCGEId(long scgeId) throws Exception {
        String query = "select * from publications p where ref_key in (select distinct(ref_key) from pub_associations where scge_id=? and type='related') ";
        return executeRefQuery(query, scgeId);

    }

    /**
     * Returns a Reference Object if the given doi for that reference object exists in the database.
     * @param doi DOI
     * @return Reference Object if present else returns null
     * @throws Exception if something unexpected happens in the framework
     */
    public Reference getPublicationByDOI(String doi) throws Exception {

        String query = "SELECT ref.* FROM publications ref  WHERE  ref.DOI=?";

        List<Reference> refs = executeRefQuery(query, doi);
        return refs.isEmpty() ? null : refs.get(0);
    }

    /**
     * get reference given reference key
     * @param key reference key
     * @return Reference object
     * @throws Exception when reference key is invalid
     */
    public Reference getReferenceByKey(int key) throws Exception {

        String query = "SELECT ref.* FROM publications ref "+
                "WHERE ref.ref_key=?";

        List<Reference> refs = executeRefQuery(query, key);

        if (refs.size() == 0) {
            throw new Exception("Reference key " + key + " not found");
        }
        return refs.get(0);
    }



    /**
     * insert a new reference into database; new reference key is retrieved from REFERENCES_SEQ sequence
     * @param ref Reference object
     * @return count of rows affected
     * @throws Exception if something unexpected happens in the framework
     */
    public int insertPublication(Reference ref) throws Exception{

        String sql = "INSERT INTO publications (REF_KEY, TITLE, " +
                " VOLUME, ISSUE, PAGES, PUB_STATUS, PUB_DATE, NOTES, REFERENCE_TYPE, CITATION, " +
                "ABSTRACT, PUBLISHER, PUBLISHER_CITY, URL_WEB_REFERENCE, DOI) values " +
                "(?, ?,?,?,?,?,?,?,?,?, ?,?,?,?,?)";



        return upsertReference(ref, sql);
    }

    private int upsertReference(Reference ref, String sql) throws Exception{

        return update(sql, ref.getKey(), ref.getTitle(), ref.getVolume(), ref.getIssue(),
                ref.getPages(), ref.getPubStatus(), ref.getPubDate(), ref.getNotes(), ref.getReferenceType(),
                ref.getCitation(), ref.getRefAbstract(), ref.getPublisher(), ref.getPublisherCity(),
                ref.getUrlWebReference(), ref.getDoi());
    }
    /**
     * insert author data
     * @param auth
     * @return
     * @throws Exception
     */
    public int insertAuthor(Author auth) throws Exception{

        String sql = "INSERT INTO pub_authors (AUTHOR_KEY, AUTHOR_LAST_NAME, AUTHOR_FIRST_NAME, " +
                "EMAIL_ADDRESS, INSTITUTION, NOTES, AUTHOR_INITIALS, AUTHOR_SUFFIX) values " +
                "(?,?,?,?,?,?,?,?)";


        return update(sql, auth.getKey(), auth.getLastName(), auth.getFirstName(), auth.getEmailAddress(),
                auth.getInstitution(), auth.getNotes(), auth.getInitials(), auth.getSuffix());
    }

    public int updateAuthor(Author auth) throws Exception{

        String sql = "UPDATE pub_authors SET author_last_name=?, author_first_name=?, email_address=?, institution=?, " +
                "NOTES=?, AUTHOR_INITIALS=?, AUTHOR_SUFFIX=? where AUTHOR_KEY=?";

        return update(sql, auth.getLastName(), auth.getFirstName(), auth.getEmailAddress(),
                auth.getInstitution(), auth.getNotes(), auth.getInitials(), auth.getSuffix(), auth.getKey());
    }

    /**
     * return author associations for given reference
     * @param refKey key of reference
     * @return List of author keys in order
     * @throws Exception
     */
    public List<Integer> getPubAuthorAssociations(int refKey) throws Exception{
        String sql = "";
        return IntListQuery.execute(this, sql, refKey);
    }

    /**
     * insert reference author association for given author key and ref key
     * @param refKey
     * @param authorKey
     * @param authorOrder
     * @return number of rows inserted; return 0 if association already exists
     * @throws Exception
     */
    public int insertPubAuthorAssociation(int refKey, int authorKey, int authorOrder) throws Exception{
        String sql =
                "INSERT INTO pub_author_associations (ref_key, author_key, author_order)  values (?,?,?)" ;
        return update(sql, refKey, authorKey, authorOrder);
    }

    /**
     * update author in reference author list at given position
     * @param refKey
     * @param authorKeyOld
     * @param authorKeyNew
     * @param authorOrder
     * @return number of rows updated
     * @throws Exception
     */
    public int updatePubAuthorAssociation(int refKey, int authorKeyOld, int authorKeyNew, int authorOrder) throws Exception{
        String sql = "UPDATE pub_author_associations SET author_key=? WHERE ref_key=? AND author_key=? AND author_order=?";
        return update(sql, authorKeyNew, refKey, authorKeyOld, authorOrder);
    }

    /**
     * delete reference author association for given author key and ref key
     * @param refKey
     * @param authorKey
     * @param authorOrder
     * @return number of rows affected
     * @throws Exception
     */
    public int deletePubAuthorAssociation(int refKey, int authorKey, int authorOrder) throws Exception{
        String sql = "DELETE FROM pub_author_associations WHERE ref_key=? AND author_key=? AND author_order=?";
        return update(sql, refKey, authorKey, authorOrder);
    }

    /// Reference query implementation helper
    public List<Reference> executeRefQuery(String query, Object ... params) throws Exception {
        ReferenceQuery q = new ReferenceQuery(this.getDataSource(), query);
        return execute(q, params);
    }
    public int runPubmedProcesssor(int pmid) throws Exception {
        Extractor extractor=new Extractor();
        int refKey=getRefKey(pmid);
        if(refKey>0){
            return refKey;
        }else {
            String url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?" +
                    "db=pubmed" +
                    "&retmode=xml&rettype=abstract" +
                    "&id=" + pmid;
            return extractor.fetchArticle(pmid, url);
        }
    }
    public int getRefKey(int pmid) throws Exception {
        String sql="select ref_key from publication_ids where identifier_type='pubmed' and identifier=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> refKeys=execute(q, String.valueOf(pmid));
        return refKeys.size()>0?refKeys.get(0):0;
    }
    public List<Publication> getPublications(long scge_id) throws Exception {
        List<Reference> references = getPublicationsBySCGEId(scge_id);
        List<Publication> publications=new ArrayList<>();
        for(Reference ref:references){
            Publication publication=new Publication();
            publication.setReference(ref);
            publication.setAuthorList(getAuthorsByRefKey(ref.getKey()));
            publication.setArticleIds(getArticleIdsByRefKey(ref.getKey()));
            publications.add(publication);
        }
        return publications;
    }
    public List<Publication> getAssociatedPublications(long scge_id) throws Exception {
        List<Reference> references = getAssociatedPublicationsBySCGEId(scge_id);
        List<Publication> publications=new ArrayList<>();
        for(Reference ref:references){
            Publication publication=new Publication();
            publication.setReference(ref);
            publication.setAuthorList(getAuthorsByRefKey(ref.getKey()));
            publication.setArticleIds(getArticleIdsByRefKey(ref.getKey()));
            publications.add(publication);
        }
        return publications;
    }
    public List<Publication> getRelatedPublications(long scge_id) throws Exception {
        List<Reference> references = getRelatedPublicationsBySCGEId(scge_id);
        List<Publication> publications=new ArrayList<>();
        for(Reference ref:references){
            Publication publication=new Publication();
            publication.setReference(ref);
            publication.setAuthorList(getAuthorsByRefKey(ref.getKey()));
            publication.setArticleIds(getArticleIdsByRefKey(ref.getKey()));
            publications.add(publication);
        }
        return publications;
    }
}
