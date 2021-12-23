package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Image;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class ImageQuery extends MappingSqlQuery {
    public ImageQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image i = new Image();
        i.setScgeId(rs.getLong("scge_id"));
        i.setFileName(rs.getString("file_name"));
        i.setImage(rs.getBytes("image"));
        i.setThumbnail(rs.getBytes("thumbnail"));
        i.setImage700Wide(rs.getBytes("image_700_wide"));
        i.setBucket(rs.getString("bucket"));
        i.setLegend(rs.getString("legend"));
        i.setTitle(rs.getString("title"));
        i.setFileType(rs.getString("file_type"));
        i.setPosIndex(rs.getInt("pos_index"));

        return i;
    }

    public static List<Image> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        ImageQuery q = new ImageQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
