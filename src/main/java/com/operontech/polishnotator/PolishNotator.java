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
	public static String convertToEnglish(final String input) {
		final StringBuilder result = new StringBuilder();
		char inChar;
		for (int i = 0; i < input.length(); i++) {
			inChar = input.charAt(i);
			if (inChar == '(') {
				final String end = goUntilParenthesis(input.substring(i + 1));
				//@formatter:off
				result.append('(')
					  .append(convertToEnglish(end))
					  .append(')');
				//@formatter:on
				System.out.println(end);
				i += end.length() + 1;
			} else if (inChar == Operator.NOT.getSymbol()) {
				result.append("NOT ");
			} else if (inChar == Operator.AND.getSymbol()) {
				result.append("AND ");
			} else if (inChar == Operator.OR.getSymbol()) {
				result.append("OR ");
			} else if (inChar == Operator.IF_THEN.getSymbol()) {
				result.append("IF_THEN ");
			} else if (inChar == Operator.ONLY_IF.getSymbol()) {
				result.append("ONLY_IF ");
			} else {
				result.append(inChar).append(' ');
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
