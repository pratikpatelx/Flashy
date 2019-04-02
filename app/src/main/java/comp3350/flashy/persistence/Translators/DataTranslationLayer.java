package comp3350.flashy.persistence.Translators;

import comp3350.flashy.domain.Deck;

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