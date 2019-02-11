package comp3350.flashy.persistence;

public class Stub {
    String[][] arr;

    public Stub() {
        arr = new String[3][2];
        arr[0][0] = "test1";
        arr[0][1] = "test2";
        arr[1][0] = "test3";
        arr[1][1] = "test4";
        arr[2][0] = "test5";
        arr[2][1] = "test6";
    }

    public String[][] getStub() {
        return arr;
    }
}
