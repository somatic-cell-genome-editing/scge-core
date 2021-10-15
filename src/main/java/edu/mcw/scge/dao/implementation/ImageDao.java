package edu.mcw.scge.dao.implementation;


import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.EditorQuery;
import edu.mcw.scge.dao.spring.ImageQuery;
import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Image;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class ImageDao extends AbstractDAO {

    public List<Image> getAllImages() throws Exception {
        String sql="select * from images";
        ImageQuery q=new ImageQuery(this.getDataSource(), sql);
        return (List<Image>)q.execute();
    }

    public List<Image> getImage(long scgeId, String bucket) throws Exception {
        String sql="select * from images where scge_id=? and bucket=?";
        ImageQuery q=new ImageQuery(this.getDataSource(), sql);
        return execute(q, scgeId, bucket);
    }

    public void deleteImage(long scgeId, String bucket) throws Exception {
        String sql="delete from images where scge_id=? and bucket=?";
        //ImageQuery q=new ImageQuery(this.getDataSource(), sql);
        update(sql, scgeId, bucket);
    }

    public void insertImage(Image image) throws Exception{

        String sql = "insert into images ( scge_id, file_name, image, bucket, legend, title, file_type, pos_index )\n" +
                "values (" +
                image.getScgeId() +
                ",'" + image.getFileName() + "'" +
                ",?"  +
                ",'" + image.getBucket() + "'" +
                ",'" + image.getLegend() + "'" +
                ",'" + image.getTitle() + "'" +
                ",'" + image.getFileType() + "'" +
                "," + image.getPosIndex() + ")";


//        update(sql, image.getScgeId(),image.getFileName(),image.getImage(), image.getBucket(),image.getLegend(),image.getTitle(),image.getFileType(),image.getPosIndex() );


        Connection conn = this.getDataSource().getConnection();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setBinaryStream(1,new ByteArrayInputStream(image.getImage()),image.getImage().length);

        pst.executeUpdate();

        System.out.println("inserted image");

        //return editorId;
    }

}
