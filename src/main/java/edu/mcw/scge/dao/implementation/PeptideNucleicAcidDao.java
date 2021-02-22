package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.HRDonorQuery;
import edu.mcw.scge.dao.spring.PeptideNucleicAcidQuery;
import edu.mcw.scge.datamodel.HRDonor;
import edu.mcw.scge.datamodel.PeptideNucleicAcid;

import java.util.List;

/**
 * Created by hsnalabolu on 2/5/2021.
 */
public class PeptideNucleicAcidDao extends AbstractDAO {
    public List<PeptideNucleicAcid> getAllPeptides() throws Exception {
        String sql="select * from peptide_nucleic_acid";
        PeptideNucleicAcidQuery q=new PeptideNucleicAcidQuery(this.getDataSource(), sql);
        return (List<PeptideNucleicAcid>)q.execute();
    }
    public List<PeptideNucleicAcid> getPeptideById(int id) throws Exception {
        String sql="select * from peptide_nucleic_acid where pna_id=?";
        PeptideNucleicAcidQuery q=new PeptideNucleicAcidQuery(this.getDataSource(), sql);
        return execute(q, id);
    }

    public int insertPeptide(PeptideNucleicAcid peptide) throws Exception{

        String sql = "insert into peptide_nucleic_acid (pna_id,lab_id,source,target_sequence,sequence,orientation,description  )" +
                " values (?,?,?,?,?,?,?)";

        int peptideId = this.getNextKeyFromSequence("peptide_seq");


        update(sql, peptideId,peptide.getLabId(),peptide.getSource(),peptide.getTargetSequence(),peptide.getSequence(),
                peptide.getOrientation(),peptide.getDescription());

        return peptideId;
    }

    public int getPeptideId(PeptideNucleicAcid peptide) throws Exception {

        String sql = "select * from peptide_nucleic_acid where sequence=? and target_sequence = ?";

        List<PeptideNucleicAcid> list = PeptideNucleicAcidQuery.execute(this,sql, peptide.getSequence(),peptide.getTargetSequence());
        return list.isEmpty() ? 0 : list.get(0).getId();
    }
}
