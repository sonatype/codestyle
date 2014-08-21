// BEGIN Tabs and Indents example
def foo(int arg) {
  label1:
    for (i in 1..10) {
      label2:
        foo(i)
    }
    return Math.max(arg,
        0)
}

class HelloSpock
    extends spock.lang.Specification
{
  def "length of Spock's and his friends' names"() {
    expect:
      name.size() == length

    where:
      name     | length | foo
      "Spock"  | 5
      "Kirk"   | 4      | xxx | yyy
      "Scotty" | 6      | dddddddddd | fff

      //aaa
      a | b | c
  }
}

// END Tabs and Indents example

// BEGIN Spaces example
class Foo
{
  @Annotation(param = "foo")
  @Ann([1, 2])
  public static <T1, T2> void foo(int x, int y) {
    for (int i = 0; i < x; i++) {
      y += (y ^ 0x123) << 2
    }

    10.times {
      print it
    }
    int j = 0
    while (j < 10) {
      try {
        if (0 < x && x < 10) {
          while (x != y) {
            x = f(x * 3 + 5)
          }
        }
        else {
          synchronized (this) {
            switch (e.getCode()) {
            //...
            }
          }
        }
      }
      catch (MyException e) {
        logError(method: "foo", exception: e)
      }
      finally {
        int[] arr = (int[]) g(y)
        x = y >= 0 ? arr[y] : -1
        y = [1, 2, 3] ?: 4
      }
    }
    def cl = { Math.sin(it) }
    print ckl(2)
  }

  def inject(x) { "cos($x) = ${Math.cos(x)}" }

}

// END Spaces example


// BEGIN Wrapping and Braces example
/*
 * This is a sample file.
 * NOTE: Many of the truncated lines are on purpose to demonstrate wrapping, not
 * the result of the formatter introducing a newline to enforce style.
 */

public class ThisIsASampleClass
    extends C1
    implements I1, I2, I3, I4,
        I5
{
  private int f1 = 1

  private String field2 = ""

  public void foo1(int i1,
                   int i2,
                   int i3,
                   int i4,
                   int i5,
                   int i6,
                   int i7)
  {}

  public
  static void longerMethod()
      throws Exception1,
             Exception2,
             Exception3
  {
    // todo something
    int
    i = 0
    int var1 = 1; int var2 = 2
    foo1(0x0051, 0x0052,
        0x0053, 0x0054,
        0x0055, 0x0056,
        0x0057)
    int x = (3 + 4 + 5 + 6) *
        (7 + 8 + 9 + 10) *
        (11 + 12 + 13 + 14 +
            0xFFFFFFFF)
    String s1, s2, s3
    s1 = s2 = s3 = "012345678901456"
    assert i + j + k + l + n +
        m <=
        2: "assert description"
    int y = 2 > 3 ? 7 + 8 +
        9 : 11 + 12 + 13
    super.getFoo().foo().
        getBar().bar()

    label:
      if (2 < 3) {
        return
      }
      else if (2 >
          3) {
        return
      }
      else {
        return
      }
      for (int i = 0;
           i < 0xFFFFFF;
           i += 2) {
        System.out
            .println(i)
      }
      print([l1    : expr1,
             label2: expr2
      ])
      while (
          x < 50000) {
        x++
      }
      switch (a) {
        case 0:
          doCase0()
          break
        default:
          doDefault()
      }
      try {
        doSomething()
      }
      catch (Exception e) {
        processException(e)
      }
      finally {
        processFinally()
      }
  }

  public static void test()
      throws Exception
  {
    foo.foo().bar("arg1",
        "arg2")
    new Object() {}
  }

  class TestInnerClass
  {}

  interface TestInnerInterface
  {}
}

enum Breed {
  Dalmatian(), Labrador(), Dachshund()
}

@Annotation1
@Annotation2
@Annotation3(param1 = "value1", param2 = "value2")
@Annotation4
class Foo
{
  @Annotation1
  @Annotation3(param1 = "value1", param2 = "value2")
  public static void foo() {
  }

  @Annotation1
  @Annotation3(param1 = "value1", param2 = "value2")
  public static int myFoo

  public void method(
      @Annotation1 @Annotation3(param1 = "value1", param2 = "value2") final int param)
  {
    @Annotation1 @Annotation3(param1 = "value1", param2 = "value2") final int localVariable
  }
}

// END Wrapping and Braces example

// BEGIN Blank lines example
/*
 * This is a sample file.
 */
package com.intellij.samples

public class Foo
{
  private int field1

  private int field2

  public void foo1() {
    new Runnable() {
      public void run() {
      }
    }
  }

  public class InnerClass
  {
  }
}

class AnotherClass
{
}

interface TestInterface
{
  int MAX = 10

  int MIN = 1

  def method1()

  void method2()
}

// END Blank lines example
