public class PiSolverTester {
     public static void main(String[] args) {
          PiSolver.main(new String[]{"10"});
          PiSolver.main(new String[]{"100"});
          PiSolver.main(new String[]{"1000000"});
          PiSolver.main(new String[]{});
          try {
               PiSolver.main(new String[]{"apple"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               PiSolver.main(new String[]{"3", "5"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               PiSolver.main(new String[]{"3", "12", "xyz", "396"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               PiSolver.main(new String[]{"3", "12", "-4"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               PiSolver.main(new String[]{"0", "12", "3", "63"});
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
     }
}
