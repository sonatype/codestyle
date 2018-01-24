import javax.crypto.NullCipher;

import static java.lang.Math.min;

public class Imports
{
  public Imports() {

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
