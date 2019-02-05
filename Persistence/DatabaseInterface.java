package Persistence;

import Logic.LogicInterface;

public interface DatabaseInterface {
    void storeData(LogicInterface newData);
    LogicInterface getData();
}