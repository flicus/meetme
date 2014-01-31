package org.ffff.meetme.dao;

/**
 * Created by flicus on 27.01.14.
 */
public class DAOManager {

    private DAOInterface dao;

    private DAOManager() {
        dao = new TestDAO();
    }

    private static final class Singleton {
        public static final DAOManager instance = new DAOManager();
    }

    public static DAOManager getInstance() {
        return Singleton.instance;
    }
}
