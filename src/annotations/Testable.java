//: annotations/Testable.java
package annotations;
import net.mindview.atunit.*;

public class Testable {
  public void execute() {
    System.out.println("Executing..");
  }
  @src.net.mindview.atunit.Test
  void testExecute() { execute(); }
} ///:~
