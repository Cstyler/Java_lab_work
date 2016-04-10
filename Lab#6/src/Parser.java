
/**
 * Created by khan on 30.03.16. Lab#6
 */

/*
<Tm> ::= template < <Args> >
<Args> ::= <Arg> <Tail>
<Tail> ::= , <Args> |
			e
<Arg> ::= typename <Name>
		| int <Name>
		| <Tm> <Class>
<Name> ::= IDENT |
		e
<Class> ::= class IDENT |
			e
*/

class Parser {
    private Token sym;

    Parser(String text) throws SyntaxError {
        sym = new Token(text);
    }

    private void expect(TokenType type) throws SyntaxError {
        if (!sym.matches(type)) {
            sym.throwError(type + " expected");
        }
        nextToken();
    }

    private void nextToken() throws SyntaxError {
        sym = sym.next();
    }

    void parse() throws SyntaxError {
        tm();
        expect(TokenType.END_OF_TEXT);
    }

    private void tm() throws SyntaxError {
        System.out.println("<Tm> ::= template < <Args> >");
        expect(TokenType.TEMPLATE);
        expect(TokenType.LEFT_BRACKET);
        args();
        expect(TokenType.RIGHT_BRACKET);
    }

    private void args() throws SyntaxError {
        System.out.println("<Args> ::= <Arg> <Tail>");
        arg();
        tail();
    }

    private void tail() throws SyntaxError {
        if (sym.matches(TokenType.COMMA)) {
            System.out.println("<Tail> ::= , <Args>");
            nextToken();
            args();
        } else {
            System.out.println("<Tail> ::= e");
        }
    }

    private void arg() throws SyntaxError {
        if (sym.matches(TokenType.TYPENAME)) {
            System.out.println("<Arg> ::= typename <Name>");
            nextToken();
            name();
        } else if (sym.matches(TokenType.INT)) {
            System.out.println("<Arg> ::= int <Name>");
            nextToken();
            name();
        } else {
            tm();
            class_f();
        }
    }

    private void name() throws SyntaxError {
        if (sym.matches(TokenType.IDENT)) {
            nextToken();
            System.out.println("<Name> ::= IDENT");
        } else {
            System.out.println("<Name> ::= e");
        }
    }

    private void class_f() throws SyntaxError {
        if (sym.matches(TokenType.CLASS)) {
            System.out.println("<Class> ::= class IDENT ");
            nextToken();
            expect(TokenType.IDENT);
        } else {
            System.out.println("<Class> ::= e");
        }
    }
}