
class SyntaxError extends Exception {
	SyntaxError(String message, Position pos) {
		super(String.format("Syntax error at %s: %s", pos.toString(), message));
	}
}