package comp3350.flashy.application;

public class Main {
    private static String dbName = "FlashyDB";

    /**
     * Returns the DB's path
     *
     * @return
     */
    public static String getDBPathName() {
        return dbName;
    }

    /**
     * sets the DB's path
     * @param name
     */
    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }
}
