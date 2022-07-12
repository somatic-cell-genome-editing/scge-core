package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.GuideQuery;
import edu.mcw.scge.datamodel.Guide;

import java.util.List;

public class GuideDao extends AbstractDAO {
    public List<Guide> getGuideById(long guideId) throws Exception {
        String sql="select * from guide g left outer join genome_info gi on g.guide_id=gi.genome_id where g.guide_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }

    public List<Guide> getGuideByFullGuideSequence(String guideSequence) throws Exception {
        String sql="select * from guide g left outer join genome_info gi on g.guide_id=gi.genome_id where g.full_guide=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, guideSequence);
    }



    public List<Guide> getGuides() throws Exception {
        String sql="select * from guide g left outer join genome_info gi on g.guide_id=gi.genome_id order by guide";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public List<Guide> getGuidesByEditor(long editorId) throws Exception {
      /*  String sql="select * from guide g left outer join genome_info gi on g.guide_id=gi.genome_id\n" +
                "inner join guide_associations ga on ga.guide_id=g.guide_id\n" +
                "inner join experiment_record e on e.experiment_record_id=ga.experiment_record_id and e.editor_id=?";
       */ String sql="select * from guide g" +
                " left outer join genome_info gi on g.guide_id=gi.genome_id" +
                " where guide_id in ( " +
                "select distinct(guide_id) from guide_associations where experiment_record_id in " +
                "(select experiment_record_id from experiment_record where editor_id =? " +
                ") " +
                ")";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q,editorId);
	}
	public List<Guide> getDistinctGuidesByExperimentId(long experimentId) throws Exception {
        String sql="select * from guide where guide_id in ( " +
                "select distinct(guide_id) from guide_associations where experiment_record_id in " +
                "(select experiment_record_id from experiment_record where experiment_id =? " +
                ") " +
                ")";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q,experimentId);
    }
    public long insertGuide(Guide guide) throws Exception{

        String sql = "insert into guide ( guide_id, species, source,  " +
                "pam,grna_lab_id , guide_format, spacer_sequence, spacer_length, repeat_sequence," +
                " guide,guide_description, forward_primer, reverse_primer, linker_sequence, " +
                "anti_repeat_sequence, stemloop_1_sequence, stemloop_2_sequence, stemloop_3_sequence, " +
                "standard_scaffold_sequence, modifications,tier,ivt_construct_source," +
                "vector_id,vector_name,vector_description,vector_type,annotated_map,specificity_ratio,full_guide,guide_compatibility ) " +
                "values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        long guideId = this.getNextKeyFromSequenceLong("guide_seq");


        update(sql, guideId, guide.getSpecies(), guide.getSource(),
                guide.getPam(),guide.getGrnaLabId(),guide.getGuideFormat(),guide.getSpacerSequence(),guide.getSpacerLength(),guide.getRepeatSequence(),
                guide.getGuide(),guide.getGuideDescription(), guide.getForwardPrimer(), guide.getReversePrimer(), guide.getLinkerSequence(),
                guide.getAntiRepeatSequence(),guide.getStemloop1Sequence(),guide.getStemloop2Sequence(),guide.getStemloop3Sequence(),
                guide.getStandardScaffoldSequence(), guide.getModifications(),guide.getTier(),guide.getIvtConstructSource(),
                guide.getVectorId(),guide.getVectorName(),guide.getVectorDescription(),guide.getVectorType(),guide.getAnnotatedMap(),
                guide.getSpecificityRatio(),guide.getFullGuide(),guide.getGuideCompatibility());

        return guideId;
    }
    public void updateGuide(Guide guide) throws Exception{

        String sql = "update guide set species=?, source=?, pam=? ,grna_lab_id=? , " +
                "guide_format=?, spacer_sequence=?, spacer_length=?, repeat_sequence=?," +
                " guide=?, guide_description=?, forward_primer=?, reverse_primer=?, linker_sequence=?, " +
                "anti_repeat_sequence=?, stemloop_1_sequence=?, stemloop_2_sequence=?, stemloop_3_sequence=?, " +
                "standard_scaffold_sequence=?, modifications=?, tier=?, ivt_construct_source=?," +
                "vector_id=?, vector_name=?, vector_description=?, vector_type=?," +
                "annotated_map=?, specificity_ratio=?,full_guide =? ,guide_compatibility=? where guide_id=?";

        update(sql,guide.getSpecies(), guide.getSource(),
                guide.getPam(),guide.getGrnaLabId(),guide.getGuideFormat(),guide.getSpacerSequence(),guide.getSpacerLength(),guide.getRepeatSequence(),
                guide.getGuide(),guide.getGuideDescription(), guide.getForwardPrimer(), guide.getReversePrimer(), guide.getLinkerSequence(),
                guide.getAntiRepeatSequence(),guide.getStemloop1Sequence(),guide.getStemloop2Sequence(),guide.getStemloop3Sequence(),
                guide.getStandardScaffoldSequence(), guide.getModifications(),guide.getTier(),guide.getIvtConstructSource(),
                guide.getVectorId(),guide.getVectorName(),guide.getVectorDescription(),guide.getVectorType(),guide.getAnnotatedMap(),
                guide.getSpecificityRatio() ,guide.getFullGuide(),guide.getGuideCompatibility(),guide.getGuide_id() );
    }

    public void insertGenomeInfo(Guide guide) throws Exception{
        String sql = "insert into genome_info (genome_id, target_locus, target_sequence, assembly, " +
                "chromosome, start, stop, strand, species) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        update(sql, guide.getGuide_id(),guide.getTargetLocus(),guide.getTargetSequence(),guide.getAssembly(),
                guide.getChr(),guide.getStart(),guide.getStop(),guide.getStrand(),guide.getSpecies());

    }
    public void updateGenomeInfo(Guide guide) throws Exception{
        String sql = "update genome_info set target_locus=?, target_sequence=?, assembly=?, " +
                "chromosome=?, start=?, stop=?, strand=?, species=? " +
                "where genome_id = ?";

        update(sql, guide.getTargetLocus(),guide.getTargetSequence(),guide.getAssembly(),
                guide.getChr(),guide.getStart(),guide.getStop(),guide.getStrand(),guide.getSpecies(),guide.getGuide_id());

    }
    public void insertGuideAssoc(long expRecId,long guideId) throws Exception{
        String sql = "insert into guide_associations ( experiment_record_id, guide_id ) values (?,?)";

        update(sql,expRecId,guideId);
    }

    public long getGuideId(Guide guide) throws Exception {

        String sql = "select * from guide where species =? and guide=? and pam=? and modifications = ?";

        List<Guide> list = GuideQuery.execute(this,sql,guide.getSpecies(),  guide.getGuide(),
                guide.getPam(), guide.getModifications());
        return list.isEmpty() ? 0 : list.get(0).getGuide_id();
    }
    public void updateGuideTier(int tier, long guideId) throws Exception{
        String sql="update guide set tier=? where guide_id=?";
        update(sql, tier, guideId);
    }

    public List<Guide> getGuidesByExpRecId(long expRecId) throws Exception {
        String sql="select distinct g.*, gi.* from guide g left outer join genome_info gi on g.guide_id=gi.genome_id" +
                " inner join guide_associations ga on g.guide_id = ga.guide_id where ga.experiment_record_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }
    public List<Guide> getAllGuidesByRange(int start , int stop) throws Exception {
        String sql="select * from guide g left outer join genome_info gi on g.guide_id=gi.genome_id where CAST (gi.start AS int8) >= ? and gi.start <> ''\n" +
                "and CAST (gi.start AS int8) < ? and CAST (gi.stop AS int8) > ? and gi.start <> ''\n" +
                "and CAST (gi.start AS int8) <= ? ";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, start,stop,start, stop);
    }
}
