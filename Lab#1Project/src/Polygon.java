
import java.util.Arrays;

public class Polygon {
    private Vertex[] vertices;

    public Polygon() {
        vertices = new Vertex[0];
    }

    private void insertArray(int i, Vertex vertex) {
        Vertex[] b = new Vertex[vertices.length];
        System.arraycopy(vertices, i, b, 0, vertices.length - i);
        vertices = Arrays.copyOf(vertices, vertices.length + 1);
        vertices[i] = vertex;
        System.arraycopy(b, 0, vertices, i + 1, b.length - i);
    }

    private void removeArray(int i) {
        Vertex[] temp = new Vertex[vertices.length - 1];
        for (int j = 0; j < temp.length; j++) {
            temp[j] = j != i ? vertices[j] : vertices[j + 1];
        }
        vertices = Arrays.copyOf(temp, temp.length);
    }

    public void addVertex(Vertex v, int ind) {
        insertArray(ind, v);
        System.out.println(Arrays.toString(vertices));
    }

    public void removeVertex(int i) {
        removeArray(i);
        System.out.println(Arrays.toString(vertices));
    }

    private int calcDeterminant(Vertex a1, Vertex a2, Vertex a3) {
        return (a1.x - a2.x) * (a3.y - a2.y) - (a1.y - a2.y) * (a3.x - a2.x);
    }

    public boolean checkConvex() {
        int n = vertices.length, sign = 2;
        for (int i = 0; i < n; i++) {
            Vertex a1 = i == 0 ? vertices[n - 1] : vertices[i - 1], a2 = vertices[i], a3 = i == n - 1 ? vertices[0] : vertices[i + 1];
            if (sign == 2) {
                sign = Integer.signum(calcDeterminant(a1, a2, a3));
            } else {
                if (Integer.signum(calcDeterminant(a1, a2, a3)) != sign) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        String s = "";
        for (Vertex vertex : vertices) {
            s += "[" + vertex.x + " " + vertex.y + "]\n";
        }
        return s;
    }
}

class Vertex {
    public final int x;
    public final int y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}