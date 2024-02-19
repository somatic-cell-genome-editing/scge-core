package edu.mcw.scge.process.pubmedProcessor;

import edu.mcw.scge.dao.implementation.PublicationDAO;
import edu.mcw.scge.datamodel.publications.Author;
import edu.mcw.scge.datamodel.publications.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Processor {
    PublicationDAO publicationDAO=new PublicationDAO();

    public int insertReference( Reference reference) throws Exception {
        int refKey=publicationDAO.getNextKeyFromSequence("PUBLICATION_SEQ");
        reference.setKey(refKey);
        publicationDAO.insertPublication(reference);
        return refKey;
    }
    public void insertAuthor(List<Author> authors, int  refKey) throws Exception {

        for(Author author:authors) {
            int authorKey=  publicationDAO.getAuthorKey(author);
            if(authorKey==0) {
                authorKey = publicationDAO.getNextKey("pub_authors_seq");
                author.setKey(authorKey);
                publicationDAO.insertAuthor(author);
            }
            publicationDAO.insertPubAuthorAssociation(refKey,authorKey, 0);
        }

    }

    public List<Integer> updateArticleIds(Map<String, String> articleIdMap, int refKey) throws Exception {
        List<Integer> pubIdKeys=new ArrayList<>();
        for(Map.Entry entry:articleIdMap.entrySet()){
            String idType= (String) entry.getKey();
            String id= (String) entry.getValue();
            int pubIdKey=publicationDAO.getPubIdKey(id, idType.toLowerCase());
            if(pubIdKey==0){
                pubIdKey=publicationDAO.getNextKey("pub_id_key_seq");
                publicationDAO.insertPubIds(pubIdKey,refKey,id, idType);

            }else{
                publicationDAO.updatePubIds(pubIdKey,refKey,id, idType);
            }
            pubIdKeys.add(pubIdKey);
        }
        return pubIdKeys;
    }
}
