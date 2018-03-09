import java.util.ArrayList;
import java.util.List;

import javax.crypto.NullCipher;

import static java.lang.Math.min;

public class Imports
{
  public Imports() {
    List list = new ArrayList();
    new NullCipher();

    try {
      new Byte("hello world");
    }
    catch (NumberFormatException e) {
      handleError(e);
    }

    min(1, 2);
  }
}
