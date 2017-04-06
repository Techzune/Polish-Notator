/*
 * Copyright (c) 2017 Jordan Stremming
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.operontech.polishnotator;

/**
 * Handles the conversion of notations from Standard to Polish
 */
public class PolishNotator {
	public static String convertFromStandard(final String input) {
		// Builds the String for the return
		final StringBuilder result = new StringBuilder();

		// Hold current index, current character, and the matching operator
		int curIndex = 0;
		Character inChar = null;
		Operator curOperator = null;

		// Search for an operator
		while ((curOperator == null || curOperator == Operator.NOT) && curIndex < input.length()) {
			inChar = input.charAt(curIndex);
			curOperator = Operator.valueOf(inChar);
			curIndex++;
		}

		if (inChar != null) {
			// If the current character is an operator
			if (curOperator != null) {
				// Don't add a space beforehand if it's a NOT operator
				if (curOperator != Operator.NOT) {
					result.append(" ");
				}

				// Add the current operator to the return string
				result.append(curOperator.getPolishLetter());

			} else {
				result.append(Character.toLowerCase(inChar));
			}

			// As long as the current operator isn't a NOT operator
			if (curOperator != Operator.NOT) {
				try {
					final String leftBin = input.substring(0, curIndex - 1);
					if (leftBin.length() > 0) {
						result.append(convertFromStandard(leftBin));
					}
				} catch (final Exception e) {
					System.out.println("Exception!");
				}
			}

			try {
				final String rightBin = input.substring(curIndex);
				if (rightBin.length() > 0) {
					result.append(convertFromStandard(rightBin));
				}
			} catch (final Exception e) {
				System.out.println("Exception!");
			}
		}

		return result.toString();
	}

	private static String goUntilParenthesis(final String str) {
		final StringBuilder result = new StringBuilder();
		for (final char curChar : str.toCharArray()) {
			if (curChar != ')') {
				result.append(curChar);
			} else {
				break;
			}
		}
		return result.toString();
	}
}
