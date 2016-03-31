import java.util.Arrays;

/**
 * Created by khan on 31.03.16. Lab#6
 */

enum TokenType2 {
	IDENT, NUMBER, LEFT_PAREN, RIGHT_PAREN, LEFT_BRACKET, RIGHT_BRACKET, STAR, END_OF_TEXT, INT, FLOAT
}

class Token2 {
	private final Position start;
	private Position follow;
	private TokenType2 tokenType;

	Token2(String text) throws SyntaxError {
		this(new Position(text));
	}

	private Token2(Position currentPos) throws SyntaxError {
		start = currentPos.skipWhile(Character::isWhitespace);
		follow = start.skip();
		switch (start.getChar()) {
			case '(':
				tokenType = TokenType2.LEFT_PAREN;
				break;
			case ')':
				tokenType = TokenType2.RIGHT_PAREN;
				break;
			case '[':
				tokenType = TokenType2.LEFT_BRACKET;
				break;
			case ']':
				tokenType = TokenType2.RIGHT_BRACKET;
				break;
			case '*':
				tokenType = TokenType2.STAR;
				break;
			case -1:
				tokenType = TokenType2.END_OF_TEXT;
				break;
			default:
				if (start.satisfies(Character::isLetter)) {
					follow = follow.skipWhile(Character::isLetterOrDigit);
					tokenType = start.getSubString(follow).equals("int") ? TokenType2.INT : (start.getSubString(follow).equals("float") ? TokenType2.FLOAT : TokenType2.IDENT);
				} else if (start.satisfies(Character::isDigit)) {
					follow = follow.skipWhile(Character::isDigit);
					if (follow.satisfies(Character::isLetter)) {
						throw new SyntaxError("delimiter expected", follow);
					}
					tokenType = TokenType2.NUMBER;
				} else {
					throwError("invalid character");
				}
		}
	}

	boolean matches(TokenType2... types) {
		return Arrays.stream(types).anyMatch(x -> x == tokenType);
	}

	void throwError(String msg) throws SyntaxError {
		throw new SyntaxError(msg, start);
	}

	Token2 next() throws SyntaxError {
		return new Token2(follow);
	}
}

class Parser2 {
	private Token2 sym;

	Parser2(String text) throws SyntaxError {
		sym = new Token2(text);
	}

	private void expect(TokenType2 type) throws SyntaxError {
		if (!sym.matches(type)) {
			sym.throwError(type + " expected");
		}
		nextToken();
	}

	private void nextToken() throws SyntaxError {
		sym = sym.next();
	}

	void parse() throws SyntaxError {
		decl();
		expect(TokenType2.END_OF_TEXT);
	}

	private void decl() throws SyntaxError {
		System.out.println("Decl ::= <Type> <Ptr>");
		type();
		ptr();
	}

	private void type() throws SyntaxError {
		if (sym.matches(TokenType2.INT)) {
			System.out.println("Type ::= int");
			nextToken();
		} else if (sym.matches(TokenType2.FLOAT)) {
			System.out.println("Type ::= float");
			nextToken();
		} else {
			sym.throwError("int or float expected");
		}
	}

	private void ptr() throws SyntaxError {
		if (sym.matches(TokenType2.STAR)) {
			System.out.println("<Ptr> ::= * <Ptr>");
			nextToken();
			ptr();
		} else {
			System.out.println("<Ptr> ::= <Prim> <Dims>");
			prim();
			dims();
		}
	}

	private void prim() throws SyntaxError {
		if (sym.matches(TokenType2.IDENT)) {
			System.out.println("<Prim> ::= IDENT");
			nextToken();
		} else if (sym.matches(TokenType2.LEFT_PAREN)) {
			System.out.println("<Prim> ::= ( <Ptr> )");
			nextToken();
			ptr();
			expect(TokenType2.RIGHT_PAREN);
		} else {
			sym.throwError("ident or left paren expected");
		}
	}

	private void dims() throws SyntaxError {
		if (sym.matches(TokenType2.LEFT_BRACKET)) {
			System.out.println("<Dims> ::= [ NUMBER ] <Dims>");
			nextToken();
			expect(TokenType2.NUMBER);
			expect(TokenType2.RIGHT_BRACKET);
			dims();
		} else {
			System.out.println("<Dims> ::= ε");
		}
	}
}
