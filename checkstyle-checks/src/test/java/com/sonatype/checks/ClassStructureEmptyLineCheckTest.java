/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */
package com.sonatype.checks;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessages;
import org.junit.Test;

import static com.sonatype.checks.ClassStructureEmptyLineCheck.PRECEDING_EMPTY_MESSAGE;
import static com.sonatype.checks.ClassStructureEmptyLineCheck.TRAILING_EMPTY_MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsNull.nullValue;

public class ClassStructureEmptyLineCheckTest
    extends AbstractCheckTest
{
  private File baseDir = new File("src/test/resources/ClassStructureEmptyLineCheckTest");

  private ClassStructureEmptyLineCheck check = new ClassStructureEmptyLineCheck();

  @Test
  public void testVisitToken_GoodSourceFiles() throws Exception {
    assertNoMessages("goodClassSource.java");
    assertNoMessages("goodInterfaceSource.java");
    assertNoMessages("goodButEmptyClassSource.java");
    assertNoMessages("goodMultipleClassSource.java");
  }

  @Test
  public void testVisitToken_trailingEmptyLines() throws Exception {
    assertOneMessage("trailingEmptyLineClassSource.java", TRAILING_EMPTY_MESSAGE);
    assertOneMessage("trailingEmptyLinesClassSource.java", TRAILING_EMPTY_MESSAGE);
    assertOneMessage("trailingEmptyLineInterfaceSource.java", TRAILING_EMPTY_MESSAGE);
    assertOneMessage("trailingEmptyLinesInterfaceSource.java", TRAILING_EMPTY_MESSAGE);
  }

  @Test
  public void testVisitToken_precedingEmptyLines() throws Exception {
    assertOneMessage("precedingEmptyLineClassSource.java", PRECEDING_EMPTY_MESSAGE);
    assertOneMessage("precedingEmptyLinesClassSource.java", PRECEDING_EMPTY_MESSAGE);
    assertOneMessage("precedingEmptyLineInterfaceSource.java", PRECEDING_EMPTY_MESSAGE);
    assertOneMessage("precedingEmptyLinesInterfaceSource.java", PRECEDING_EMPTY_MESSAGE);
  }

  @Test
  public void testVisitToken_trailingAndPrecedingEmptyLines() throws Exception {
    List<String> expectedMessages = Arrays.asList(
        PRECEDING_EMPTY_MESSAGE, TRAILING_EMPTY_MESSAGE
    );

    assertTwoMessages("bothEmptyLineClassSource.java", expectedMessages);
    assertTwoMessages("bothEmptyLinesClassSource.java", expectedMessages);
    assertTwoMessages("bothEmptyLineInterfaceSource.java", expectedMessages);
    assertTwoMessages("bothEmptyLinesInterfaceSource.java", expectedMessages);
    assertTwoMessages("bothEmptyMinimalClassSource.java", expectedMessages);
  }

  private void assertNoMessages(String fileName) throws Exception {
    File sourceFile = new File(baseDir, fileName);
    LocalizedMessages messages = processFile(sourceFile, check);
    assertThat(messages, is(not(nullValue())));
    assertThat(messages.getMessages().isEmpty(), is(true));
  }

  private void assertOneMessage(String fileName, String expectedMessage) throws Exception {
    File sourceFile = new File(baseDir, fileName);
    LocalizedMessages messages = processFile(sourceFile, check);

    assertThat(messages.getMessages().isEmpty(), is(false));
    assertThat(messages.getMessages().size(), is(1));
    assertThat(messages.getMessages().first().getMessage(), is(expectedMessage));
  }

  private void assertTwoMessages(String fileName, List<String> expectedMessages) throws Exception {
    File sourceFile = new File(baseDir, fileName);
    LocalizedMessages messages = processFile(sourceFile, check);

    assertThat(messages.getMessages().isEmpty(), is(false));
    assertThat(messages.getMessages().size(), is(2));

    List<String> receivedMessages = new ArrayList<>();
    for (LocalizedMessage message : messages.getMessages()) {
      receivedMessages.add(message.getMessage());
    }

    assertThat(receivedMessages, containsInAnyOrder(expectedMessages.toArray()));
  }
}
