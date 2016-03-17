/**
 * Created by khan on 19.02.16. Lab#1Project
 */

public class Test {
    public static void main(String[] args) {
        Polygon p = new Polygon();
        Vertex v1 = new Vertex(5, 6);
        Vertex v2 = new Vertex(7, 17);
        Vertex v3 = new Vertex(9, 25);
        Vertex v4 = new Vertex(12, 10);
        p.addVertex(v1, 0);
        p.addVertex(v2, 1);
        p.addVertex(v3, 2);
        p.addVertex(v4, 2);
        p.removeVertex(2);
        System.out.println(p);
        System.out.println(p.checkConvex());
        p.addVertex(v4, 2);
        System.out.println(p.checkConvex());
    }
}
