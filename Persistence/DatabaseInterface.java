package Persistence;

import Logic.LogicInterface;

public interface DatabaseInterface {

    public void storeData(String identifier, LogicInterface newData);

    public LogicInterface getData(String identifier);
}