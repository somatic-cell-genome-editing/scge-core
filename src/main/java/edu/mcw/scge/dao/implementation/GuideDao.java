package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.GuideQuery;
import edu.mcw.scge.datamodel.Guide;

import java.util.List;

public class GuideDao extends AbstractDAO {
    public List<Guide> getGuideById(long guideId) throws Exception {
        String sql="select * from guide where guide_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }

	public List<Guide> getGuides() throws Exception {
        String sql="select * from guide";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public List<Guide> getGuidesByEditor(long editorId) throws Exception {
        String sql="select distinct(g.*) from guide g inner join guide_associations ga on ga.guide_id=g.guide_id\n" +
                "inner join experiment_record e on e.experiment_record_id=ga.experiment_record_id and e.editor_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q,editorId);
	}
	
    public long insertGuide(Guide guide) throws Exception{

        String sql = "insert into guide ( guide_id, species, source, target_locus, target_sequence, " +
                "pam, assembly, chr,start, stop, strand," +
                " grna_lab_id , guide_format, spacer_sequence, spacer_length, repeat_sequence," +
                " guide,guide_description, forward_primer, reverse_primer, linker_sequence, " +
                "anti_repeat_sequence, stemloop_1_sequence, stemloop_2_sequence, stemloop_3_sequence, " +
                "standard_scaffold_sequence, modifications,tier,ivt_construct_source," +
                "vector_id,vector_name,vector_description,vector_type,annotated_map,specificity_ratio ) " +
                "values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        long guideId = this.getNextKeyFromSequence("guide_seq");


        update(sql, guideId, guide.getSpecies(), guide.getSource(), guide.getTargetLocus(),guide.getTargetSequence(),
                guide.getPam(),guide.getAssembly(),guide.getChr(),guide.getStart(),guide.getStop(), guide.getStrand(),
                guide.getGrnaLabId(),guide.getGuideFormat(),guide.getSpacerSequence(),guide.getSpacerLength(),guide.getRepeatSequence(),
                guide.getGuide(),guide.getGuideDescription(), guide.getForwardPrimer(), guide.getReversePrimer(), guide.getLinkerSequence(),
                guide.getAntiRepeatSequence(),guide.getStemloop1Sequence(),guide.getStemloop2Sequence(),guide.getStemloop3Sequence(),
                guide.getStandardScaffoldSequence(), guide.getModifications(),guide.getTier(),guide.getIvtConstructSource(),
                guide.getVectorId(),guide.getVectorName(),guide.getVectorDescription(),guide.getVectorType(),guide.getAnnotatedMap(),
                guide.getSpecificityRatio());

        return guideId;
    }

    public void insertGuideAssoc(long expRecId,long guideId) throws Exception{
        String sql = "insert into guide_associations ( experiment_record_id, guide_id ) values (?,?)";

        update(sql,expRecId,guideId);
    }

    public long getGuideId(Guide guide) throws Exception {

        String sql = "select * from guide where species =? and target_locus=? and target_sequence=? and pam=? and spacer_sequence=? and spacer_length=?";

        List<Guide> list = GuideQuery.execute(this,sql,guide.getSpecies(),  guide.getTargetLocus(),guide.getTargetSequence(),
                guide.getPam(),guide.getSpacerSequence(),guide.getSpacerLength() );
        return list.isEmpty() ? 0 : list.get(0).getGuide_id();
    }
    public void updateGuideTier(int tier, long guideId) throws Exception{
        String sql="update guide set tier=? where guide_id=?";
        update(sql, tier, guideId);
    }

    public List<Guide> getGuidesByExpRecId(long expRecId) throws Exception {
        String sql="select distinct g.* from guide g inner join guide_associations ga on g.guide_id = ga.guide_id where ga.experiment_record_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }
}
