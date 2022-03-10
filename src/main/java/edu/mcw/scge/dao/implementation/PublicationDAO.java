package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.publications.AuthorQuery;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.LongListQuery;
import edu.mcw.scge.dao.spring.publications.ReferenceQuery;
import edu.mcw.scge.dao.spring.publications.ArticleIdQuery;
import edu.mcw.scge.datamodel.publications.ArticleId;
import edu.mcw.scge.datamodel.publications.Author;
import edu.mcw.scge.datamodel.publications.Reference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicationDAO extends AbstractDAO {
    public List<Long> getAllPMIDs() throws Exception {
        String sql="select identifier from pub_associations where identifier_type='pubmed'";
        LongListQuery query=new LongListQuery(this.getDataSource(), sql);
        return query.execute();
    }
    public List<Reference> getAllReferences() throws Exception {
        String sql="select * from publications";
        ReferenceQuery referenceQuery=new ReferenceQuery(this.getDataSource(), sql);
        return referenceQuery.execute();
    }
    public List<Author> getAuthorsByRefKey(int refKey) throws Exception {
        String sql="Select * from pub_authors authors, pub_author_associations assoc where authors.author_Key=assoc.author_key and assoc.ref_key=?";
        AuthorQuery authorQuery=new AuthorQuery(this.getDataSource(), sql);
        return execute(authorQuery,refKey);
    }
    public  List<ArticleId> getArticleIdsByRefKey(int refKey) throws Exception {
        String sql="select * from pub_associations where ref_Key=?";
        return getArticleIds(refKey, sql);
    }
    public List<ArticleId> getArticleIdsSCGEID(int scgeId) throws Exception {
        String sql="select * from pub_associations where scge_id=?";
        return getArticleIds(scgeId, sql);
    }

    private List<ArticleId> getArticleIds(int id, String sql) throws Exception {
        ArticleIdQuery query=new ArticleIdQuery(this.getDataSource(),sql);
         return execute(query,id);
    }

    public void updatePubAssociations(String idType, String id, int refKey )throws Exception{
        String sql="update pub_associations set identifier_type=?, identifier=? , ref_key where ref_key=?";
        update(sql,idType,id,refKey);
    }
    public void insertPubAssociations(int refKey, long scgeId, String identifier, String idType) throws Exception {
        String sql="insert into pub_associations (ref_key, " +
                "scge_id, " +
                "identifier_type, " +
                "identifier " +
                ") values (?,?,?,?) ";
        update(sql, refKey,scgeId, idType, identifier);
    }
    public Reference getPublicationsBySCGEId(int scgeId) throws Exception {
        String query = "select * from publications p where ref_key in (select distinct(ref_key) from pub_associations where scge_id=?) ";
        List<Reference> refs = executeRefQuery(query, scgeId);
        return refs.isEmpty() ? null : refs.get(0);
    }

    /**
     * Returns a Reference Object if the given doi for that reference object exists in the database.
     * @param doi DOI
     * @return Reference Object if present else returns null
     * @throws Exception if something unexpected happens in the framework
     */
    public Reference getPublicationByDOI(String doi) throws Exception {

        String query = "SELECT ref.* FROM references ref  WHERE  ref.DOI=?";

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

        String query = "SELECT ref.* FROM references ref "+
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
}
