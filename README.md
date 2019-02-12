<!--

    Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
    Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
    "Sonatype" is a trademark of Sonatype, Inc.

-->
# Sonatype Codestyle

Contains codestyle configuration for:

* IDEA
* Eclipse

And some reference examples (which won't compile so don't even try) to help refine the style properly in
supported environments.

The reference examples should reflect the desired format.  The IDE configurations may not support or respect this, 
which is okay while we're developing the format and auto-formatters.

Also contains a set of [checkstyle](https://github.com/checkstyle/checkstyle) and [pmd](https://pmd.github.io/) rules that can be used to enforce code formatting/quality standards as part of the build.
Usage/release documentation [here](#checkstyle-and-pmd-rules).

# Goals, Intent and Working Agreement

## Goals
We want to present a unified approach to code styling across our entire suite of development efforts, automating away
the need to respond to deviations during integration of code from a large group of developers, both within and outside
of the Sonatype Organization.

## Intent
All of the development environments used by developers contributing to Sonatype code should be applying the same agreed
upon formatting rules consistently, so that we don't spend needless time aligning these rules along with the more
substantial (and important!) logical changes we make on a daily basis.

## Working Agreement
- Any change to existing rules should be discussed and agreed upon by the larger group before implementation. Minimally
this should result in a mail to the development group with some minimal time for feedback.
- Any noted deviation between development environments should result in the scheduling of work to correctly align with 
agreed upon standard. 


## Installation

### IntelliJ IDEA

See [the Intellij documentation](https://www.jetbrains.com/help/idea/copying-code-style-settings.html).

### Eclipse

See [the eclipse documentation](http://help.eclipse.org/kepler/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Freference%2Fpreferences%2Fjava%2Fcodestyle%2Fref-preferences-formatter.htm) for how to import the files into your IDE.
In addition, html indentation settings are not exported, but can be set to 2 spaces from Preferences -> Web -> HTML Files -> Editor -> "Indent using spaces". [Screenshot of the eclipse config here.](https://s3.amazonaws.com/uploads.hipchat.com/18157/88592/RSkQhq8UYnxf81Z/upload.png)

### Visual Studio

Import sonatype-visualstudio-settings.xml using Tools -> Import and Export Settings...

### Visual Studio ReSharper

Import sonatype-visualstudio-resharper-settings.xml using ReSharper -> Options... -> Manage... -> Import and Export -> Import from file...

## Basics

### General

120 column right margin

2 space indent for **ALL** formats

**NO TABS**

Newline at end of file

### Imports

Order:

* java
* javax
* javafx
* com.sonatype
* org.sonatype
* _other_
* _static_

Wildcard thresholds:

* 100 non-static
* 10 static

## Notes

Has similarities with "[The Elements of Java Style][java style pdf]" (book), Google and Eclipse styles with some minor changes
for clarity and readability:

* Class/intf/enum declarations are multiline
* if/try/while blocks have new-lines after '}'

Some minor differences between IDEA and Eclipse configurations are unavoidable.  The configurations here try to get
as close as possible, though if each time a file is reformatted there could be additional noise and thus such operation
should generally be avoided.  To avoid this pain recommend that reformat entire sources to normalize once,
then format discrete blocks where appropriate.

There is some _crude_ support to use Eclipse formatter from IDEA.  Unfortunately the Eclipse formatter is not
quite as smart as the IDEA formatter in some/many places.  The support also seems to have some strange additional
configuration for import handling, etc which is not ideal.

It should be possible to craft separate configurations which are _mostly_ compatible and thus avoid _most_ noise if
reformatting is done in a sane manner.

**DO NOT** reformat entire codebases, but reformat as sources are touched to normalize.   Or normalize and _santizie_
one by one, as the code formatters (IDEA or Eclipse) tend to create some formats which can and **SHOULD** be manually
cleaned up, else the code will end up **LESS** readable.

## Javascript

Follows "[Google Javascript Style Guide][javascript style page]" with 2 space indents to conform with Java code. Note that IDEA styles import at a global level such that sonatype-idea.xml will import both Java and Javascript style conventions.

[java style pdf]:  http://its.lnpu.edu.ua/edocs1/new_doc/en/Vermeulen%20A.The%20elements%20of%20Java%20style.2001.pdf
[javascript style page]: https://google.github.io/styleguide/javascriptguide.xml

## Scala

Follow [docs.scala-lang.org/style](https://docs.scala-lang.org/style/).
See some [differences](java-scala-diff.md) from Java.


# Checkstyle and PMD rules

## Releasing
After merging changes to the Checkstyle or PMD rulesets, a master snapshot build will run [here](https://jenkins.zion.aws.s/job/cdi/job/tools/job/codestyle/job/master-snapshot/).
Ensure that passes, then run the release job [here](https://jenkins.zion.aws.s/job/cdi/job/tools/job/codestyle/job/release/).
After building a new release, update any builds to use new rule versions as appropriate

## Usage

### Maven Sample
Checkstyle
```
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-checkstyle-plugin</artifactId>
        <version>3.0.0</version>
        <configuration>
          <consoleOutput>true</consoleOutput>
          <configLocation>sonatype/checkstyle-configuration.xml</configLocation>
          <includeTestSourceDirectory>true</includeTestSourceDirectory>
        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>check</goal>
            </goals>
          </execution>
        </executions>
        <dependencies>
          <dependency>
            <groupId>com.sonatype</groupId>
            <artifactId>checkstyle-checks</artifactId>
            <version>8</version>
          </dependency>
        </dependencies>
      </plugin>
```

PMD
```
      <plugin>
        <artifactId>maven-pmd-plugin</artifactId>
        <version>3.10.0</version>
        <configuration>
          <linkXRef>false</linkXRef>
          <printFailingErrors>true</printFailingErrors>
          <includeTests>true</includeTests>
          <rulesets>
            <ruleset>pmd-ruleset/ruleset.xml</ruleset>
          </rulesets>
        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>check</goal>
            </goals>
          </execution>
        </executions>
        <dependencies>
          <dependency>
            <groupId>com.sonatype</groupId>
            <artifactId>pmd-ruleset</artifactId>
            <version>3</version>
          </dependency>
        </dependencies>
      </plugin>
```
