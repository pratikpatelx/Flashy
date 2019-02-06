package comp3350.flashy.persistence;
//package comp3350.flashy.logic;


import comp3350.flashy.logic.LogicInterface;

public interface DatabaseInterface {

    public void storeData(String identifier, LogicInterface newData);

    public LogicInterface getData(String identifier);
}