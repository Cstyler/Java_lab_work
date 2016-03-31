import java.util.Arrays;

/**
 * Created by khan on 30.03.16. Lab#6
 */

class Token {
	private final Position start;
	private Position follow;
	private TokenType tokenType;

	Token(String text) throws SyntaxError {
		this(new Position(text));
	}

	private Token(Position currentPos) throws SyntaxError {
		start = currentPos.skipWhile(Character::isWhitespace);
		follow = start.skip();
		switch (start.getChar()) {
			case '(':
				tokenType = TokenType.LEFT_PAREN;
				break;
			case ')':
				tokenType = TokenType.RIGHT_PAREN;
				break;
			case -1:
				tokenType = TokenType.END_OF_TEXT;
				break;
			case '"':
				follow = follow.skipWhile(c -> c != '"' && c != '\n' && c != -1);
				if (follow.getChar() != '"') {
					throw new SyntaxError("newline in string literal, \" expected", follow);
				}
				follow = follow.skip();
				tokenType = TokenType.STRING;
				break;
			default:
				if (start.satisfies(Character::isLetter)) {
					follow = follow.skipWhile(Character::isLetterOrDigit);
					tokenType = TokenType.IDENT;
				} else if (start.satisfies(Character::isDigit)) {
					follow = follow.skipWhile(Character::isDigit);
					if (follow.satisfies(Character::isLetter)) {
						throw new SyntaxError("delimiter expected", follow);
					}
					tokenType = TokenType.NUMBER;
				} else {
					throwError("invalid character");
				}
		}
	}

	boolean matches(TokenType... types) {
		return Arrays.stream(types).anyMatch(x -> x == tokenType);
	}

	void throwError(String msg) throws SyntaxError {
		throw new SyntaxError(msg, start);
	}

	Token next() throws SyntaxError {
		return new Token(follow);
	}
}