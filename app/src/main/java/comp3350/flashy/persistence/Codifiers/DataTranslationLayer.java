package comp3350.flashy.persistence.Codifiers;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.persistence.Codifiers.Decoder;
import comp3350.flashy.persistence.Codifiers.Encoder;

public class DataTranslationLayer {
    private final String delimiter = "|";
    private Encoder dataEncoder = new Encoder(delimiter);
    private Decoder dataDecoder = new Decoder(delimiter);

    public Deck encodeDeck(Deck deck) {
        return dataEncoder.encodeDeck(deck);
    }

    public Deck decodeDeck(Deck deck) {
        return dataDecoder.decodeDeck(deck);
    }
}