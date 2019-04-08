package comp3350.flashy.persistence.Translators;

import comp3350.flashy.domain.Deck;

public class DataTranslationLayer {
    private final String delimiter = "|";
    private Encoder dataEncoder = new Encoder(delimiter);
    private Decoder dataDecoder = new Decoder(delimiter);

    /**
     * Calls the appropriate function in the Encoder to encode the deck
     *
     * @param deck
     * @return
     */
    public Deck encodeDeck(Deck deck) {
        return dataEncoder.encodeDeck(deck);
    }

    /**
     * Calls the appropriate function in the Decoder to decode the deck
     * @param deck
     * @return
     */
    public Deck decodeDeck(Deck deck) {
        return dataDecoder.decodeDeck(deck);
    }
}