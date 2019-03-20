/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */
package com.sonatype.checks;

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import org.junit.Test;

import static com.sonatype.checks.ClassStructureEmptyLineCheck.PRECEDING_EMPTY_MESSAGE;
import static com.sonatype.checks.ClassStructureEmptyLineCheck.TRAILING_EMPTY_MESSAGE;

public class ClassStructureEmptyLineCheckTest
    extends AbstractModuleTestSupport
{
  private static final String[] EMPTY_MESSAGES = new String[]{};

  @Override
  protected String getPackageLocation() {
    return "ClassStructureEmptyLineCheckTest";
  }

  @Test
  public void testVisitToken_GoodSourceFiles() throws Exception {
    verifyNoMessages("goodClassSource.java");
    verifyNoMessages("goodInterfaceSource.java");
    verifyNoMessages("goodButEmptyClassSource.java");
    verifyNoMessages("goodMultipleClassSource.java");
  }

  @Test
  public void testVisitToken_trailingEmptyLines() throws Exception {
    verifyMessages("trailingEmptyLineClassSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE));
    verifyMessages("trailingEmptyLinesClassSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE));
    verifyMessages("trailingEmptyLineInterfaceSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE));
    verifyMessages("trailingEmptyLinesInterfaceSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE));
  }

  @Test
  public void testVisitToken_precedingEmptyLines() throws Exception {
    verifyMessages("precedingEmptyLineClassSource.java", lineMessage(16, PRECEDING_EMPTY_MESSAGE));
    verifyMessages("precedingEmptyLinesClassSource.java", lineMessage(20, PRECEDING_EMPTY_MESSAGE));
    verifyMessages("precedingEmptyLineInterfaceSource.java", lineMessage(16, PRECEDING_EMPTY_MESSAGE));
    verifyMessages("precedingEmptyLinesInterfaceSource.java", lineMessage(18, PRECEDING_EMPTY_MESSAGE));
  }

  @Test
  public void testVisitToken_trailingAndPrecedingEmptyLines() throws Exception {
    verifyMessages("bothEmptyLineClassSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE),
        lineMessage(17, PRECEDING_EMPTY_MESSAGE));
    verifyMessages("bothEmptyLinesClassSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE),
        lineMessage(23, PRECEDING_EMPTY_MESSAGE));
    verifyMessages("bothEmptyLineInterfaceSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE),
        lineMessage(17, PRECEDING_EMPTY_MESSAGE));
    verifyMessages("bothEmptyLinesInterfaceSource.java", lineMessage(11, TRAILING_EMPTY_MESSAGE),
        lineMessage(22, PRECEDING_EMPTY_MESSAGE));
    verifyMessages("bothEmptyMinimalClassSource.java", lineMessage(10, TRAILING_EMPTY_MESSAGE),
        lineMessage(12, PRECEDING_EMPTY_MESSAGE));
  }

  private String lineMessage(int lineNumber, String message) {
    return lineNumber + ": " + message;
  }

  private void verifyNoMessages(String fileName) throws Exception {
    verifyMessages(fileName, EMPTY_MESSAGES);
  }

  private void verifyMessages(String fileName, String... messages) throws Exception {
    DefaultConfiguration checkConfig = createModuleConfig(ClassStructureEmptyLineCheck.class);
    verify(checkConfig, getPath(fileName), messages);
    getStream().reset();
  }
}
