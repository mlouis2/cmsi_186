public class Card {

     private long suit;
     private long value;

     static final int ACE = 1;
     static final int DEUCE = 2;
     static final int THREE = 3;
     static final int FOUR = 4;
     static final int FIVE = 5;
     static final int SIX = 6;
     static final int SEVEN = 7;
     static final int EIGHT = 8;
     static final int NINE = 9;
     static final int TEN = 10;
     static final int JACK = 11;
     static final int QUEEN = 12;
     static final int KING = 13;

     static final int HEARTS = 1;
     static final int SPADES = 2;
     static final int CLUBS = 3;
     static final int DIAMONDS = 4;

     public Card(long suit, long value) {
          if (!isValidSuit(suit) || !isValidValue(value)) {
               throw new IllegalArgumentException("Invalid Card!");
          }
          this.suit = suit;
          this.value = value;
     }

     public long getSuit() {
          return this.suit;
     }

     public boolean isValidSuit(long suit) {
          return (suit > 0 && suit < 5);
     }

     public boolean isValidValue(long value) {
          return (value > 0 && value < 14);
     }
}
