public class Deck {
     private Card[] cards;
     public Deck() {
          this.cards = new Card[52];

          int count = 0;
          while (count < 52) {
               for (int suit = 0; suit < 4; suit++) {
                    for (int value = 0; value < 13; value++) {
                         cards[count] = new Card(suit, value);
                         count++;
                    }
               }
          }



     }
     public void shuffle() {

     }
     // public Card cardAt(int i) {
     //
     // }
}
