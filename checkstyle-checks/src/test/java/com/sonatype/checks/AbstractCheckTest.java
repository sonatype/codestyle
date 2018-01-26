/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */

package com.sonatype.checks;

import java.io.File;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessages;

public class AbstractCheckTest
{
  protected LocalizedMessages processFile(File sourceFile, AbstractCheck check) throws Exception {

    FileContents fileContents = new FileContents(new FileText(sourceFile, "UTF-8"));

    DetailAST rootAST = TreeWalker.parse(fileContents);

    LocalizedMessages messages = new LocalizedMessages();
    check.configure(new DefaultConfiguration("local"));
    check.setFileContents(fileContents);
    check.setMessages(messages);

    iterateNodes(rootAST, check);

    return messages;
  }

  private void iterateNodes(DetailAST root, AbstractCheck check) throws CheckstyleException {
    DetailAST curNode = root;

    while (curNode != null) {
      if (typeIsAcceptable(check.getAcceptableTokens(), curNode.getType())) {
        check.visitToken(curNode);
      }
      DetailAST toVisit = curNode.getFirstChild();
      while (curNode != null && toVisit == null) {
        toVisit = curNode.getNextSibling();
        if (toVisit == null) {
          curNode = curNode.getParent();
        }
      }
      curNode = toVisit;
    }

  }

  private boolean typeIsAcceptable(int[] acceptableTypes, int type) {
    for (int t : acceptableTypes) {
      if (t == type) {
        return true;
      }
    }
    return false;
  }
}
