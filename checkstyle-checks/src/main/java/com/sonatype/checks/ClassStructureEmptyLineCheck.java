/*
 * Copyright (c) 2007-present Sonatype, Inc. All rights reserved.
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which accompanies this
 * distribution and is available at https://www.eclipse.org/legal/epl-2.0/.
 */
package com.sonatype.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ClassStructureEmptyLineCheck
    extends AbstractCheck
{
  static final String TRAILING_EMPTY_MESSAGE =
      "Empty lines not allowed after left curly in class or interface definition.";

  static final String PRECEDING_EMPTY_MESSAGE =
      "Empty lines not allowed before right curly at end of class or interface definition.";

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
