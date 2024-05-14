import java.util.*;

public class AgenteLogico {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static boolean isValid(int[][] maze, int x, int y) {
        int rows = maze.length;
        int cols = maze[0].length;
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 1;
    }

    static List<Point> findPath(int[][] maze, Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Point> parent = new HashMap<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == end.x && current.y == end.y)
                break;

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (isValid(maze, nextX, nextY)) {
                    Point nextPoint = new Point(nextX, nextY);
                    queue.add(nextPoint);
                    maze[nextX][nextY] = -1; // Mark as visited
                    parent.put(nextPoint, current);
                }
            }
        }

        List<Point> path = new ArrayList<>();
        Point current = end;
        while (parent.containsKey(current)) {
            path.add(current);
            current = parent.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        Point start = new Point(0, 0);
        Point end = new Point(4, 4);

        List<Point> path = findPath(maze, start, end);
        if (path.size() > 0) {
            System.out.println("Caminho encontrado:");
            for (Point p : path) {
                System.out.println("(" + p.x + ", " + p.y + ")");
            }
        } else {
            System.out.println("Não foi possível encontrar um caminho.");
        }
    }
}
