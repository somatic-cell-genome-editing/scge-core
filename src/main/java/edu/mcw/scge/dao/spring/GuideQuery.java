package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Guide;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideQuery extends MappingSqlQuery<Guide> {
    public GuideQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Guide mapRow(ResultSet rs, int rowNum) throws SQLException {

        Guide g=new Guide();
        g.setGuide_id(rs.getInt("guide_id"));
        g.setSpecies(rs.getString("species"));
        g.setTargetLocus(rs.getString("target_locus"));
        g.setTargetSequence(rs.getString("target_sequence"));
        g.setPam(rs.getString("pam"));
        g.setAssembly(rs.getString("assembly"));
        g.setChr(rs.getString("chr"));


        //g.setStart(rs.getLong("start"));
        //g.setStop(rs.getLong("stop"));


        g.setStrand(rs.getString("strand"));
        g.setGrnaLabId(rs.getString("grna_lab_id"));
        g.setgRnaFormat(rs.getString("grna_format"));
        g.setSpacerSequence(rs.getString("spacer_sequence"));
        g.setSpacerLength(rs.getString("spacer_length"));
        g.setRepeatSequence(rs.getString("repeat_sequence"));
        g.setGuide(rs.getString("guide"));
        g.setDetectionMethod(rs.getString("detection_method"));
        g.setForwardPrimer(rs.getString("forward_primer"));
        g.setLinkerSequence(rs.getString("linker_sequence"));
        g.setAntiRepeatSequence(rs.getString("anti_repeat_sequence"));
        g.setStemloop1Sequence(rs.getString("stemloop_1_sequence"));
        g.setStemloop2Sequence(rs.getString("stemloop_2_sequence"));
        g.setStemloop3Sequence(rs.getString("stemloop_3_sequence"));
        g.setSource(rs.getString("source"));
        return g;
    }
}
