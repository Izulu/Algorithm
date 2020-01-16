import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> seg = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) { throw new IllegalArgumentException("Input can not be null!");}
        int len = points.length;
        Point[] copy = new Point[len];

        for(int i=0; i < len; i++) {
            copy[i] = points[i];
        }
        Arrays.sort(copy);
        for (int i = 0; i < len; i++) {
            Point current = copy[i];
            Point[] other = new Point[len-i-1];
            for (int j = i+1; j < len; j++) {
                if (copy[i].compareTo(copy[j]) == 0) {
                    throw new IllegalArgumentException("Duplicate Points!");
                }
                other[j-i-1] = copy[j];
            }
            Arrays.sort(other, current.slopeOrder());
            for (int k = 0; k < len-i-3; k++) {
                if ( current.slopeTo(other[k]) == current.slopeTo(other[k+1]) ) {
                    if ( current.slopeTo(other[k]) == current.slopeTo(other[k+2]) ) {
                        ArrayList<Point> Line = new ArrayList<Point>();
                        Line.add(current);
                        Line.add(other[k]);
                        Line.add(other[k+1]);
                        Line.add(other[k+2]);
                        for (int l = k+3; l < len-i-3; l++ ) {
                            if (current.slopeTo(other[k]) == current.slopeTo(other[l])) {
                                Line.add(other[l]);
                            }
                        }
                        Point[] line = Line.toArray(new Point[Line.size()]);
                        Arrays.sort(line);
                        seg.add(new LineSegment(line[0],line[Line.size()-1]));
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return seg.size();
    }

    public LineSegment[] segments() {
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
