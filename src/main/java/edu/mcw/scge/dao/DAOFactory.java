package edu.mcw.scge.dao;

/**
 * Created by jthota on 8/16/2019.
 */
public class DAOFactory {

    private static DAOFactory factory = new DAOFactory();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return factory;
    }

    /**
     *  Returns a data access object based on the key passed in.  Key must be the class name.  This
     *  method will attempt find the class in edu.mcw.scge.dao.impl and return an instance.
     * @param key
     * @return
     * @throws Exception
     */
    public DAO getDAO(String key) throws Exception{
        return (DAO) Class.forName("edu.mcw.scge.dao." + key).newInstance();
    }
}
