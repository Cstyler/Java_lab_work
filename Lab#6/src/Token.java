import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by khan on 30.03.16. Lab#6
 */

class Token {
	private final Position start;
	private Position follow;
	private TokenType tokenType;
	private static final HashMap<String, TokenType> keywords = new HashMap<String, TokenType>() {{
		put("template", TokenType.TEMPLATE);
		put("typename", TokenType.TYPENAME);
		put("int", TokenType.INT);
		put("class", TokenType.CLASS);
	}};

	Token(String text) throws SyntaxError {
		this(new Position(text));
	}

	private Token(Position currentPos) throws SyntaxError {
		start = currentPos.skipWhile(Character::isWhitespace);
		follow = start.skip();
		switch (start.getChar()) {
			case -1:
				tokenType = TokenType.END_OF_TEXT;
				break;
			case ',':
				tokenType = TokenType.COMMA;
				break;
			case '<':
				tokenType = TokenType.LEFT_BRACKET;
				break;
			case '>':
				tokenType = TokenType.RIGHT_BRACKET;
				break;
			default:
				if (start.satisfies(Character::isLetter)) {
					follow = follow.skipWhile(Character::isLetterOrDigit);
					TokenType keywordType = keywords.get(start.getSubString(follow));
					tokenType = keywordType != null ? keywordType : TokenType.IDENT;
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