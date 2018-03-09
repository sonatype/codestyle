@Annotation(param1 = "value1", param2 = "value2")
@SuppressWarnings({"ALL"})
public class Example2<T, U>
{
  int[] x = new int[]{1, 3, 5, 6, 7, 87, 1213, 2};

  public void foo(int x, int y) {
    Runnable r = () -> {
    };
    Runnable r1 = this::bar;
    for (int i = 0; i < x; i++) {
      y += (y ^ 0x123) << 2;
    }
    do {
      try (MyResource r1 = getResource(); MyResource r2 = null) {
        if (0 < x && x < 10) {
          while (x != y) {
            x = f(x * 3 + 5);
          }
        }
        else {
          synchronized (this) {
            switch (e.getCode()) {
              case 0:
                doCase0();
                break;
              default:
                doDefault();
            }
          }
        }
      }
      catch (MyException e) {
        processException(e.getMessage(), x + y, z, a);
      }
      finally {
        int[] arr = (int[]) g(y);
        x = y >= 0 ? arr[y] : -1;
      }
    }
    while (true);
  }

  void bar() {
    {
      return;
    }
  }
}

class Example2a {}
