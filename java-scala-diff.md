<!--
    Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
    Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
    "Sonatype" is a trademark of Sonatype, Inc.
-->
### Some differences between Java and Scala styles

#### Wrapping long lines
```java
// in Java, indent 4 spaces
stringBuilder
    .append("one ")
    .append("two ")
    .append("three");
doSomnething(
    param1,
    param2, 
    param3);
```
```scala
// in Scala, indent 2 spaces
stringBuilder
  .append("one ")
  .append("two ")
  .append("three")
doSomnething(
  param1,
  param2, 
  param3)

// but 4 spaces for constructor parameters
class Person(
    name: String,
    birthDate: Date,
    shoeSize: Double)
  extends Entity {
  def firstMethod: Foo = ...
}
```
```java
// in Java, inflix operator on next line
int x = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9
    + 10 + 11 + 12 + 13 + 14 + 15 + 16
    + 17 + 18 + 19 + 20;
```
```scala
// in Scala, inflix method on previous line, otherwise compiler might infer semicolon unexpectedly
val x = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 +
  10 + 11 + 12 + 13 + 14 + 15 + 16 + 17 +
  18 + 19 + 20
```

#### Wrapping long parameter lists
```scala
// in Scala, each parameter on separate line, indented from first line (not parenthesis)
foo(
  someVeryLongFieldName,
  andAnotherVeryLongFieldName,
  "this is a string",
  3.1415926)
```

#### Upper/lower case
```java
// in Java, classes, interfaces, annotations are in UpperCamelCase
@MyAnnotation
class MyClass implements MyInterface { ... }
```
```scala
// in Scala, classes and traits are in UpperCamelCase, but annotations are in lowerCamelCase
@myAnnotation
class MyClass extends MyTrait { ... }

// objects are usually in UpperCamelCase but can be in lowerCamelCase if they mimic a package or a function
object ast {
  sealed trait Expr

  case class Plus(e1: Expr, e2: Expr) extends Expr { ... }
}

object inc {
 def apply(x: Int): Int = x + 1
}
```
```java
// in Java, constants are in UPPER_CASE
class Foo {
  static final int MY_CONSTANT = 239;
}
```
```scala
// in Scala, constants are in UpperCamelCase
object Foo {
  val MyConstant = 239
}
```

#### Getters, setters
```java
// in Java, “get”/“set” prefix
class Foo {
  int getBar() {...}

  void setBar(int bar) {...}
}
```
```scala
// in Scala, just the name of a property
class Foo {
  def bar: Int = ...

  def bar_=(bar: Int) { ... }
}

// in rare cases when it’s necessary to back getter/setter by a field, underscore prefix to disambiguate
class Foo {
  private var _bar = -1
}
```

#### Methods without parameters
```scala
// in Scala, no parentheses if a method acts like a getter and has no side effects
def nickname = firstName
```

#### Type parameters
```java
// in Java, usually named T or another single upper-case letter
void <T, K, V> doSomething(List<T> list, Map<K, V> map) { ... }
```
```scala
// in Scala, usually first letters of alphabet or words in UpperCamelCase
def doSomething[A, Key, Value](list: List[A], map: Map[Key, Value]): Unit = ...
```

#### Curly braces
```java
// in Java, surrounding spaces are optional
a = new int[] {5, 6};
b = new int[] { 5, 6 };
```
```scala
// in Scala, separate by spaces
tuples.filter { case (s1, s2) => s1 == s2 }
```
```java
// in Java, opening brace may be on next line
class Foo
{
  ...
}
```
```scala
// in Scala, opening brace on same line, otherwise compiler might infer semicolon unexpectedly
class Foo {
  ...
}
```
```java
// in Java, always use with if/else/for/do/while
if (foo) {
  doSomething();
}
else {
  doSomethingElse();
}
```
```scala
// in Scala, omit if a pure-functional operation and all branches are single-line
val x = if (foo)
  doSomething()
else
  doSomethingElse()

// don’t omit if no “else”
if (foo) {
  doSomething()
}

// always omit in “case”
val x = foo match {
  case 1 =>
    doSomething()
    doSomethingMore()
  case 2 => doSomethingElse()
}

// omit if “yield”
for (i <- 1 to 5) yield i

// don’t omit if no “yield”
for (i <- 1 to 5) {
  println(i)
}

// omit in one-line methods
def add(a: Int, b: Int): Int = a + b

// OK to put it on the next line
def sum(ls: List[String]): Int =
  ls.map(_.toInt).foldLeft(0)(_ + _)

// omit in methods consisting of a single “match”
def sum(ls: List[Int]): Int = ls match {
  case hd :: tail => hd + sum(tail)
  case Nil => 0
}

```

#### Ternary operator
```java
// in Java
x = foo ? bar : baz;
```
```scala
// in Scala
x = if (foo) bar else baz
```

#### Class contents order
In Java, “some logical order”. In Scala, fields before methods.

#### Annotations
```java
// in Java, may be on the same line
@Override public int hashCode() {
  ...
}

@Partial @Mock DataLoader loader;
```
```scala
// in Scala, each annotatin on its own line
@transaction
@throws(classOf[IOException])
override protected final def foo(): Unit = {
  ...
}
```

#### Modifier order
```java
// in Java
public protected private abstract default static final transient volatile synchronized native strictfp
```
```scala
// in Scala
override protected private implicit final lazy
```

#### Javadoc, Scaladoc
```java
// Javadoc

/**
 * Does something.
 *
 * Some more details.
 */
void doSomething() {
  ...
}
```
```scala
// Scaladoc with asterisks in either second column

/** Does something.
 *
 *  Some more details.
 */
def doSomething(): Unit = {
  ...
}

// or third

/** Does something.
  *
  * Some more details.
  */
def doSomething(): Unit = {
  ...
}
```
