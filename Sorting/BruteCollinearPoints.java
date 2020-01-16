import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> seg = new ArrayList<LineSegment>();
    public BruteCollinearPoints(Point[] points) {
        if (points == null) { throw new IllegalArgumentException("Input can not be null!");}
        int len = points.length;
        for(int i = 0; i< len; i++) {
            if(points[i] == null) {
                throw new IllegalArgumentException("Points can not be null!");
            }
        }
        Point[] copy = new Point[len];
        for (int i =0; i<len; i++) {
            copy[i] = points[i];
        }
        Arrays.sort(copy);
        for (int i = 0 ; i < len-3 ; i++) {
            for (int j = i+1 ; j < len-2 ; j++) {
                if (copy[i].compareTo(copy[j]) == 0) {
                    throw new IllegalArgumentException("Duplicate Points!");
                }
                for (int k = j+1 ; k < len-1 ; k++) {
                    if (copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[k]) ) {
                        for (int l = k+1 ; l < len; l++) {
                            if (copy[i].slopeTo(copy[l]) == copy[i].slopeTo(copy[k]) ) {
                                seg.add(new LineSegment(copy[i],copy[l]));
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return seg.size();
    }

    public LineSegment[] segments(){
        LineSegment[] Array_seg = seg.toArray( new LineSegment[seg.size()]);
        return Array_seg;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
