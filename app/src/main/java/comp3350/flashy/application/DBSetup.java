package comp3350.flashy.application;

public class DBSetup {
    private static String dbName = "FlashyDB";

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.JDBCDriver");
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("HSQL Driver Not Found");
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }
}
