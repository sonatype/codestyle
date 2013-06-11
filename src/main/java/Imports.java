import java.lang.Byte;
import java.lang.NumberFormatException;

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
    }

    min(1, 2);
  }
}
