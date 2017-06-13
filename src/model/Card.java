/* CSC 335 Summer 2017 Project 1
 * Tyler Horvat
 * 
 * This class is used to model one card in
 * a deck of cards. No duplicates will be allowed.
 * Contributed by Rick Mercer
 */

package model;
 
public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    // Construct a Car with its rank and suit
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Return the Suit enum
    public Suit getSuit() {
        return suit;
    }

    // Return the Rank enum
    public Rank getRank() {
        return rank;
    }

    // Return the integer value of this card, which could be the integers
    // 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 (Jack), 12, 13, 14 (Ace)
    public int getValue() {
        return rank.getValue();
    }

    // Return a textual version of this Card.
    public String toString() {
      //    Use these for the solid suit chars
      char suitChar = '\u2663';
      if (suit == Suit.DIAMONDS)
          suitChar = '\u2666';
      if (suit == Suit.HEARTS)
          suitChar = '\u2665';
      if (suit == Suit.SPADES)
          suitChar = '\u2660';

        int value = getValue();
        String cardChar = "" + (value);
        if (value == 10)
            cardChar = "10";
        if (value == 11)
            cardChar = "J";
        if (value == 12)
            cardChar = "Q";
        if (value == 13)
            cardChar = "K";
        if (value == 14)
            cardChar = "A";

        return cardChar + suitChar;
    }

    @Override
    // Return a negative integer id this Card is < other, 0 if equal
    public int compareTo(Card other) {
        return this.getValue() - other.getValue();
    }

    @Override
    // Return true if other is a non null Card object with the same
    // Rank and Value as this Card
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof Card))
            return false;

        return this.getRank() == ((Card) other).getRank()
            && this.getValue() == ((Card) other).getValue();
    }
}