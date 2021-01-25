package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.GuideQuery;
import edu.mcw.scge.datamodel.Guide;

import java.util.List;

public class GuideDao extends AbstractDAO {
    public List<Guide> getGuideById(int guideId) throws Exception {
        String sql="select * from guide where guide_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }
	public List<Guide> getGuides() throws Exception {
        String sql="select * from guide";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public List<Guide> getGuidesByEditor(int editorId) throws Exception {
        String sql="select g.* from experiment_record e, guide g where e.guide_id=g.guide_id and e.editor_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q,editorId);
	}
	
    public int insertGuide(Guide guide) throws Exception{

        String sql = "insert into guide ( guide_id, species, source, target_locus, target_sequence, pam, assembly, chr," +
                "start, stop, strand, grna_lab_id , grna_format, spacer_sequence, spacer_length, repeat_sequence, guide," +
                "detection_method, forward_primer, reverse_primer, linker_sequence, anti_repeat_sequence, " +
                "stemloop_1_sequence, stemloop_2_sequence, stemloop_3_sequence ) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                ",?,?,?,?,?,?,?,?,?,?)";

        int guideId = this.getNextKeyFromSequence("guide_seq");


        update(sql, guideId,guide.getSpecies(), guide.getSource(), guide.getTargetLocus(),guide.getTargetSequence(),
                guide.getPam(),guide.getAssembly(),guide.getChr(),guide.getStart(),guide.getStop(), guide.getStrand(),
                guide.getGrnaLabId(),guide.getgRnaFormat(),guide.getSpacerSequence(),guide.getSpacerLength(),guide.getRepeatSequence(),
                guide.getGuide(),guide.getDetectionMethod(), guide.getForwardPrimer(), guide.getReversePrimer(), guide.getLinkerSequence(),
                guide.getAntiRepeatSequence(),guide.getStemloop1Sequence(),guide.getStemloop2Sequence(),guide.getStemloop3Sequence());

        return guideId;
    }

    public int getGuideId(Guide guide) throws Exception {

        String sql = "select * from guide where species =? and target_locus=? and target_sequence=? and pam=? and spacer_sequence=? and spacer_length=?";

        List<Guide> list = GuideQuery.execute(this,sql,guide.getSpecies(),  guide.getTargetLocus(),guide.getTargetSequence(),
                guide.getPam(),guide.getSpacerSequence(),guide.getSpacerLength() );
        return list.isEmpty() ? 0 : list.get(0).getGuide_id();
    }
}
