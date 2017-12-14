import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class Example1
    extends Properties
    implements Serializable
{
  private static final long serialVersionUID = 4946535894651225728L;

  public int[] x = new int[]{1, 3, 5, 7, 9, 11};

  public static final int[] X = new int[]{1, 3, 5, 7, 9, 11};

  public Example1(boolean a, int x, int y, int z) throws IOException {
  }

  public Example1(MyReallyLongClassName foo,
                  MyOtherReallyLongClassName bar,
                  MyOtherOtherReallyLongClassName car,
                  MyOtherOtherReallyLongClassName zar) throws IOException
  {
  }

  public void foo(boolean a, int x, int y, int z) throws IOException {
    label1:
    do {
      try {
        if (x > 0) {
          int someVariable = a ? x : y;
          int anotherVariable = a ? x : y;
        }
        else if (x < 0) {
          int someVariable = y + z;
          someVariable = x = x + y;
        }
        else {
          label2:
          for (int i = 0; i < 5; i++) {
            doSomething(i);
          }
        }
        switch (a) {
          case 0:
            doCase0();
            break;
          default:
            doDefault();
        }
      }
      catch (Exception e) {
        processException(e.getMessage(), x + y, z, a);
      }
      finally {
        processFinally();
      }
    }
    while (true);

    if (2 < 3) {
      return;
    }
    if (3 < 4) {
      return;
    }
    do {
      x++;
    }
    while (x < 10000);
    while (x < 50000) {
      x++;
    }
    for (int i = 0; i < 5; i++) {
      System.out.println(i);
    }
  }

  public void multiLineMethod(MyReallyLongClassName foo,
                              MyOtherReallyLongClassName bar,
                              MyOtherOtherReallyLongClassName zar) throws IOException
  {
    // do stuff
  }

  private class InnerClass
      implements I1, I2
  {
    public void bar() throws E1, E2 {
    }
  }
}
