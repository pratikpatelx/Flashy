package comp3350.flashy.logic;

public class CardIDHelper {

    public static String assembleID(String deckName, int position){
        String id = deckName;
        String num = "-"+position;
        return id.concat(num);
    }

    public static int retrieveNumber(String id){
        String[] token = id.split("-");
        return Integer.parseInt(token[1]);
    }

    public static String retrieveString(String id){
        String[] token = id.split("-");
        return(token[0]);
    }

}
