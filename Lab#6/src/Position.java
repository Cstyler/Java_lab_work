import java.util.function.Predicate;

/**
 * Created by khan on 30.03.16. Lab#6
 */

class Position {
	private final int index, line, col;
	private final String text;

	Position(String text) {
		this(text, 0, 1, 1);
	}

	private Position(String text, int index, int line, int col) {
		this.index = index;
		this.line = line;
		this.col = col;
		this.text = text;
	}

	int getChar() {
		return index < text.length() ? text.codePointAt(index) : -1;
	}

	Position skip() {
		int c = getChar();
		switch (c) {
			case -1:
				return this;
			case '\n':
				return new Position(text, index + 1, line + 1, 1);
			default:
				return new Position(text, index + (c > 0xFFFF ? 2 : 1), line, col + 1);
		}
	}

	boolean satisfies(Predicate<Integer> p) {
		return p.test(getChar());
	}

	Position skipWhile(Predicate<Integer> p) {
		Position pos = this;
		while (pos.satisfies(p)) {
			pos = pos.skip();
		}
		return pos;
	}

	String getSubString(Position pos) {
		return text.substring(index, pos.index);
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", line, col);
	}
}