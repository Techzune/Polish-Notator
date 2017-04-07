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

import java.util.Stack;

/**
 * Handles the conversion of notations from Standard to Polish
 */
public class PolishNotator {

	public static String convertToStandard(final String input) {
		// Reverse the input to loop over
		final String rExpression = new StringBuilder(input).reverse().toString();

		// Create stacks for the operands and the operators
		final StringBuilder result = new StringBuilder();
		final Stack<Operator> stack = new Stack<>();

		// Create a holder variable for the operator
		Operator asOp;

		// This is the shunting-yard algorithm in reverse

		for (final char token : rExpression.toCharArray()) {
			// Assign the holder variable to the Operator of the token
			// if this token is not an operator, this will just be null
			asOp = Operator.valueOf(token);

			if (token == ')') {
				// Check if the token is a CLOSED_PAREN operator
				// push it to the operand stack
				stack.push(asOp);
				result.append(' ');

			} else if (token == '(') {
				// Check if the token is an open parenthesis operator
				// if the stack is not empty, append until CLOSED_PAREN
				while (!stack.isEmpty()) {
					final Operator top = stack.pop();
					if (top == Operator.CLOSED_PAREN) {
						break;
					}
					result.append(top.getPolishLetter());
				}

			} else if (asOp == null) {
				// If the token is not an operator, add it to the result
				result.append(Character.toLowerCase(token));

			} else {
				// Otherwise, pop existing higher-precedence operators and push the new one
				while (!stack.isEmpty() && stack.peek().getPrecedence() > asOp.getPrecedence()) {
					result.append(stack.pop().getPolishLetter());
				}
				stack.push(asOp);
			}
		}

		// Append any remaining operators
		while (!stack.isEmpty()) {
			result.append(stack.pop().getPolishLetter());
		}

		// Reverse the result string and return it
		return result.reverse().toString();
	}
}
