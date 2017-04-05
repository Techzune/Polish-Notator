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

public enum Operator {
	NOT('~', false), AND('•', true), OR('∨', true), IF_THEN('⊃', true), ONLY_IF('≡', true);

	char symbol;
	boolean spaceable;

	Operator(char symbol, boolean spaceable) {
		this.symbol = symbol;
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

}
