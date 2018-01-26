/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */

package com.sonatype.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ClassStructureBlankLineCheck
    extends AbstractCheck
{
  static final String TRAILING_EMPTY_MESSAGE =
      "Left curly brace as part of a class or interface definition should not be followed by any blank lines.";

  static final String PRECEDING_EMPTY_MESSAGE =
      "Right curly at the end of the class or interface definition should not be preceded by any blank lines.";

  @Override
  public int[] getDefaultTokens() {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }

  @Override
  public int[] getAcceptableTokens() {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }

  @Override
  public void visitToken(final DetailAST ast) {
    final DetailAST objectBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    final DetailAST firstLeftCurly = objectBlock.findFirstToken(TokenTypes.LCURLY);
    final DetailAST lastRightCurly = objectBlock.getLastChild();

    if (firstLeftCurly.getLineNo() != lastRightCurly.getLineNo()) {
      if (lineAfterIsEmpty(firstLeftCurly)) {
        log(firstLeftCurly.getLineNo(), TRAILING_EMPTY_MESSAGE);
      }

      if (lineBeforeIsEmpty(lastRightCurly)) {
        log(lastRightCurly.getLineNo(), PRECEDING_EMPTY_MESSAGE);
      }
    }
  }

  private boolean lineBeforeIsEmpty(final DetailAST token) {
    // token.getLineNo starts at 1, getLine starts at 0 so subtract 2 to get previous line
    String previousLine = getLine(token.getLineNo() - 2);
    return isEmptyString(previousLine);
  }

  private boolean lineAfterIsEmpty(final DetailAST token) {
    // token.getLineNo starts at 1, getLine starts at 0 so this is the next line
    String nextLine = getLine(token.getLineNo());
    return isEmptyString(nextLine);
  }

  private boolean isEmptyString(String string) {
    return string != null && string.trim().isEmpty();
  }
}
