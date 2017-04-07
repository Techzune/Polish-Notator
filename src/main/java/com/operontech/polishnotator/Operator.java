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

import java.util.HashMap;
import java.util.Map;

public enum Operator {
	CLOSED_PAREN(')', ' ', 0, false), NOT('~', 'N', 10, false), AND('•', 'C', 2, true), OR('∨', 'D', 3, true), IF_THEN('⊃', 'I', 4, true), ONLY_IF('≡', 'B', 5, true);

	final int precedence;
	final char symbol;
	final char polishLetter;
	final boolean spaceable;

	private static final Map<Character, Operator> map = new HashMap<>();

	static {
		for (final Operator opEnum : Operator.values()) {
			map.put(opEnum.getSymbol(), opEnum);
		}
	}

	Operator(final char symbol, final char polishLetter, final int precedence, final boolean spaceable) {
		this.symbol = symbol;
		this.polishLetter = polishLetter;
		this.precedence = precedence;
		this.spaceable = spaceable;
	}

	/**
	 * Returns the symbol associated with the operator
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Returns the symbol with a space preceding and following the symbol.
	 * If the symbol is not "spaceable," only the symbol will be returned.
	 * @return the spaced/regular symbol
	 */
	public String getSpacedSymbol() {
		if (spaceable) {
			return " " + symbol + " ";
		}
		return String.valueOf(symbol);
	}

	/**
	 * Returns the Polish notation letter associated with the operator
	 * @return the letter
	 */
	public char getPolishLetter() {
		return polishLetter;
	}

	public int getPrecedence() {
		return precedence;
	}

	/**
	 * Gets the Operator associated with that character
	 * @param character the character to lookup
	 * @return the Operator that is associated with that character
	 */
	public static Operator valueOf(final char character) {
		return map.get(character);
	}
}
