
public class ChangeMakerTester {
     public static void main(String[] args) {
          ChangeMaker.main(new String[]{"9", "1", "4", "12"});
          ChangeMaker.main(new String[]{"1", "5", "10", "14", "25", "29"});
          ChangeMaker.main(new String[]{"3", "1", "9", "8", "14"});
          ChangeMaker.main(new String[]{"123", "132", "999", "1", "100000"});
          ChangeMaker.main(new String[]{"1", "2", "3", "4", "1231212"});
          ChangeMaker.main(new String[]{"123", "132", "999", "100000"});
          ChangeMaker.main(new String[]{"8", "13", "4", "9", "14"});
          try {
               ChangeMaker.main(new String[]{""});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               ChangeMaker.main(new String[]{"3"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               ChangeMaker.main(new String[]{"3", "12", "xyz", "396"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               ChangeMaker.main(new String[]{"3", "12", "-4"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               ChangeMaker.main(new String[]{"0", "12", "3", "63"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               ChangeMaker.main(new String[]{"10", "12", "3", "12", "63"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
     }
}
