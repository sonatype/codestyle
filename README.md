<!--

    Sonatype Nexus (TM) Open Source Version
    Copyright (c) 2007-2013 Sonatype, Inc.
    All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.

    This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
    which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.

    Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
    of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
    Eclipse Foundation. All other trademarks are the property of their respective owners.

-->
# Sonatype Codestyle

Contains codestyle configuration for:

* IDEA
* Eclipse

And some reference examples (which won't compile so don't even try) to help refine the style properly in
supported environments.

The reference examples should reflect the desired format.  The IDE configurations may not support or respect this, 
which is okay while we're developing the format and auto-formatters.

## Installation

### IntelliJ IDEA

Linux: `cp sonatype-idea.xml ~/.IntelliJIdea14/config/codestyles`.

OSX: `cp sonatype-idea.xml ~/Library/Preferences/IntelliJIdea14/codestyles/`

- Restart IDEA
- Go to _Preferences->Code Style_ and ensure that **Sonatype** is selected as the **Scheme**

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
