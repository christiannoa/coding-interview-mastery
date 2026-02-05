import java.util.*;

class GridBFS {
    public int shortestPath(int[][] grid) {
        // grid [i][j] = 0 means walkable, 1 means wall
        // FInd the shortest path from (0,0) to (n-1,m-1)

        int rows = grid.length;
        int cols = grid[0].length;

        // if start or end is blocked
        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) {
            return -1;
        }

        // queue store {row, col, distance}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0, 0 }); // start at (0,0) with dstance 0

        // Tracks visited cells to avoid revisiting
        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;

        // four directions: up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // BFS exploration
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];

            // check if we reached the destination
            if (row == rows - 1 && col == cols - 1) {
                return dist;
            }

            // explore all 4 neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if neighbor is valid, unvisited, and walkable
                if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        !visited[newRow][newCol] &&
                        grid[newRow][newCol] == 0) {

                    // Mark as visited
                    visited[newRow][newCol] = true;

                    // Add queue with increment distance
                    queue.offer(new int[] { newRow, newCol, dist + 1 });
                }
            }
        }
        return -1; // destination not reachable, no path found
    }
}

/*
 * 
 * Time Complexity: O(rows × cols) - Visit each cell at most once
 * Space Complexity: O(rows × cols) - Queue and visited array
 * 
 * 
 */