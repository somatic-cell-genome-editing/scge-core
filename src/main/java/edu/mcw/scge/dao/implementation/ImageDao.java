package edu.mcw.scge.dao.implementation;


import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ImageQuery;
import edu.mcw.scge.datamodel.Image;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class ImageDao extends AbstractDAO {

    public static final int NATIVE_SIZE = 1;
    public static final int WIDE_700 = 2;
    public static final int HEIGHT_75 = 3;



    public List<Image> getAllImages() throws Exception {
        String sql="select * from images";
        ImageQuery q=new ImageQuery(this.getDataSource(), sql);
        return (List<Image>)q.execute();
    }

    public byte[] getImageBytes(long scgeId, String bucket, int imageSize) throws Exception {

        String column = "";
        if (imageSize == 1) {
            column="image";
        }else if (imageSize==2) {
            column="image_700_wide";
        }else if (imageSize==3) {
            column="thumbnail";
        }

        String sql="select " + column + " from images where scge_id=" + scgeId + " and bucket='" + bucket + "'";
        byte[] image = null;
        try(Connection conn = this.getDataSource().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);) {

            if (rs.next()) {
                image = rs.getBytes(column);
            }

        }

        return image;

    }

    public List<Image> getImage(long scgeId, String bucket) throws Exception {
        String sql="select * from images where scge_id=? and bucket=?";
        ImageQuery q=new ImageQuery(this.getDataSource(), sql);
        return execute(q, scgeId, bucket);
    }

    public List<Image> getImage(long scgeId) throws Exception {
        String sql="select * from images where scge_id=? order by bucket";
        ImageQuery q=new ImageQuery(this.getDataSource(), sql);
        return execute(q, scgeId);
    }


    public void deleteImage(long scgeId, String bucket) throws Exception {
        String sql="delete from images where scge_id=? and bucket=?";
        //ImageQuery q=new ImageQuery(this.getDataSource(), sql);
        update(sql, scgeId, bucket);
    }

    public void updateImageLegend(long scgeId, String bucket, String legend) throws Exception{
        String sql = "update images set legend=? where scge_id=? and bucket=?";
        update(sql, legend,scgeId,bucket );
    }

    public void updateImage(Image image) throws Exception {
        String sql = "update images set file_name='" + image.getFileName() + "', image=?, thumbnail=?,image_700_wide=?, legend='" + image.getLegend() + "', " +
                " title='" + image.getTitle() + "', file_type='" + image.getFileType() + "', pos_index=" + image.getPosIndex() + " where scge_id=" + image.getScgeId() + " and bucket='" + image.getBucket() + "'";

        try(Connection conn = this.getDataSource().getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);) {

            pst.setBinaryStream(1, new ByteArrayInputStream(image.getImage()), image.getImage().length);
            pst.setBinaryStream(2, new ByteArrayInputStream(image.getThumbnail()), image.getThumbnail().length);
            pst.setBinaryStream(3, new ByteArrayInputStream(image.getImage700Wide()), image.getImage700Wide().length);
            pst.executeUpdate();
        }
        //return editorId;


    }


    public void insertImage(Image image) throws Exception{

        String sql = "insert into images ( scge_id, file_name, image, thumbnail,image_700_wide,bucket, legend, title, file_type, pos_index )\n" +
                "values (" +
                image.getScgeId() +
                ",'" + image.getFileName() + "'" +
                ",?"  +
                ",?"  +
                ",?"  +
                ",'" + image.getBucket() + "'" +
                ",'" + image.getLegend() + "'" +
                ",'" + image.getTitle() + "'" +
                ",'" + image.getFileType() + "'" +
                "," + image.getPosIndex() + ")";




        try(Connection conn = this.getDataSource().getConnection();
            PreparedStatement  pst = conn.prepareStatement(sql);) {

            pst.setBinaryStream(1, new ByteArrayInputStream(image.getImage()), image.getImage().length);
            pst.setBinaryStream(2, new ByteArrayInputStream(image.getThumbnail()), image.getThumbnail().length);
            pst.setBinaryStream(3, new ByteArrayInputStream(image.getImage700Wide()), image.getImage700Wide().length);
            pst.executeUpdate();
        }
        //return editorId;
    }

}
