package edu.mcw.scge.process;

import edu.mcw.scge.dao.implementation.OntologyXDAO;
import edu.mcw.scge.datamodel.ontologyx.Term;

import java.util.List;

public class TermMapper {
    public static void main(String[] args) throws Exception {
        OntologyXDAO dao=new OntologyXDAO();
        String system="Reproductive System";
        String parent_term="Ovary";
        String term="Follicles";
        String ontId= "UBERON";
       String systemAccId= dao.getTermAccByOntId(system.toLowerCase(),ontId );
        List<Term> descendants=dao.getAllActiveTermDescendants(systemAccId);
       System.out.println("SYSTEM ACC ID: "+ systemAccId);
       for(Term t:descendants){
           if(t.getTerm().trim().toLowerCase().equalsIgnoreCase(parent_term.toLowerCase())){
             List<Term> terms=  dao.getAllActiveTermDescendants(t.getAccId());
             if(terms.size()>0){
                 for(Term term1: terms){
                     if(term1.getTerm().trim().toLowerCase().equalsIgnoreCase(term))
                     System.out.println(term1.getAccId() +"\t"+term1.getTerm());
                 }
             }
           }
        //   System.out.println(t.getAccId()+"\t"+t.getTerm());
       }
    }
}
