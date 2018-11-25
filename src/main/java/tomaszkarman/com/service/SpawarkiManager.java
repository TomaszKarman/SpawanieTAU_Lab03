package tomaszkarman.com.service;

import java.sql.Connection;
import java.sql.SQLException;
import tomaszkarman.com.domain.*;

import java.util.List;





public interface SpawarkiManager {

    public Connection Connection();
    public void setConnection(Connection connection) throws SQLException;

    public void addSpawarka(SpawarkaCTR spawarka);
    public List<SpawarkaCTR> getAllSpawarki();
    public void removeSpawarka(SpawarkaCTR spawarka);
    public SpawarkaCTR getSpawarka ();

}