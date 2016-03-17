
/**
 * Created by khan on 11.03.16. Lab#2
 */

import java.util.Iterator;
import java.util.Stack;

public class Graph implements Iterable<Integer> {
    private final boolean[][] matrixOfAdjacency;

    enum Marks {WHITE, GRAY, BLACK}

    public Graph(boolean[][] matrixOfAdjacency) {
        this.matrixOfAdjacency = matrixOfAdjacency;
    }

    public Iterator<Integer> iterator() {
        return new VertexIterator();
    }

    private class VertexIterator implements Iterator<Integer> {
        private int currentVertex, grayCount;
        private final Stack<Integer> stack;
        private final Marks[] vertices;

        private VertexIterator() {
            stack = new Stack<>();
            vertices = new Marks[matrixOfAdjacency.length];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = Marks.WHITE;
            }
            currentVertex = 0;
            grayCount = 0;
        }

        public boolean hasNext() {
            return grayCount < vertices.length;
        }

        public Integer next() {
            if (vertices[currentVertex] == Marks.WHITE) {
                if (stack.empty()) {
                    stack.push(currentVertex);
                }

                while (!stack.empty()) {
                    currentVertex = stack.pop();
                    if (vertices[currentVertex] == Marks.WHITE) {
                        vertices[currentVertex] = Marks.GRAY;
                        grayCount++;
                        pushAdjacentVertices();
                        popNotWhiteVertices();
                        break;
                    } else if (vertices[currentVertex] == Marks.GRAY) {
                        vertices[currentVertex] = Marks.BLACK;
                    }
                }
                Integer temp = currentVertex;
                if (stack.empty()) {
                    if (hasNext()) {
                        currentVertex++;
                    }
                } else {
                    currentVertex = stack.peek();
                }
//                 Tracing
//                System.out.println(Arrays.toString(vertices));
//                System.out.println(stack);
                return temp;
            } else {
                currentVertex++;
                return next();
            }
        }

        private void pushAdjacentVertices() {
            for (int i = 0; i < matrixOfAdjacency.length; i++) {
                if (matrixOfAdjacency[currentVertex][i] && vertices[i] == Marks.WHITE) {
                    stack.push(i);
                }
            }
        }

        private void popNotWhiteVertices() {
            while (!stack.empty() && vertices[stack.peek()] == Marks.GRAY) {
                vertices[stack.pop()] = Marks.BLACK;
            }
        }
    }
}