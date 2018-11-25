package tomaszkarman.com.service;

import tomaszkarman.com.domain.SpawarkaCTR;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SpawarkiManagerImpl implements SpawarkiManager {


    @Override
    public Connection Connection() {
        return null;
    }

    @Override
    public void setConnection(Connection connection) throws SQLException {

    }

    @Override
    public void addSpawarka(SpawarkaCTR spawarka) {

    }

    @Override
    public List<SpawarkaCTR> getAllSpawarki() {
        return null;
    }

    @Override
    public void removeSpawarka(SpawarkaCTR spawarka) {

    }

    @Override
    public SpawarkaCTR getSpawarka() {
        return null;
    }
}
