/**
 * Created by khan on 30.03.16. Lab#6
 */

/* <A> ::= IDENT ( <B> ) | NUMBER | STRING
   <B> ::= <A> <B> | ε
*/

class Parser {
	private Token currentToken;

	public Parser(String text) throws SyntaxError {
		currentToken = new Token(text);
	}

	private void expect(TokenType type) throws SyntaxError {
		if (!currentToken.matches(type)) {
			currentToken.throwError(type + " expected");
		}
		nextToken();
	}

	private void nextToken() throws SyntaxError {
		currentToken = currentToken.next();
	}

	void parse() throws SyntaxError {
		parseA();
		expect(TokenType.END_OF_TEXT);
	}

	private void parseA() throws SyntaxError {
		if (currentToken.matches(TokenType.IDENT)) {
			System.out.println("<A> ::= IDENT ( <B> )");
			nextToken();
			expect(TokenType.LEFT_PAREN);
			parseB();
			expect(TokenType.RIGHT_PAREN);
		} else if (currentToken.matches(TokenType.NUMBER)) {
			System.out.println("A ::= NUMBER");
			nextToken();
		} else if (currentToken.matches(TokenType.STRING)) {
			System.out.println("A ::= STRING");
			nextToken();
		} else {
			currentToken.throwError("ident, number or string expected");
		}
	}

	private void parseB() throws SyntaxError {
		if (currentToken.matches(TokenType.STRING, TokenType.IDENT, TokenType.NUMBER)) {
			System.out.println("<B> ::= <A> <B>");
			parseA();
			parseB();
		} else {
			System.out.println("<B> ::= ε");
		}
	}
}