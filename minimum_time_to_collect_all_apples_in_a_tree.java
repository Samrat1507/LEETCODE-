class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        
        Map<Integer, TreeNode> nodes = new HashMap<>();
        for (int i=0; i<n; i++) {
            nodes.put(i, new TreeNode(i, hasApple.get(i)));
        }

        // Build Tree
        for (int i=0; i<edges.length; i++) {
            nodes.get(edges[i][0]).addNext(nodes.get(edges[i][1]));
            nodes.get(edges[i][1]).addNext(nodes.get(edges[i][0]));
        }
        boolean[] visited = new boolean[n];

        return getTime(nodes.get(0), visited);
    }

    private int getTime(TreeNode p, boolean[] visited) {
        // If already visited, need not count again
        if (visited[p.n])
            return 0;
        
        // Mark the node visited
        visited[p.n] = true;
        
        // If no node
        if (p==null)
            return 0;
        
        int total = 0;
        for (TreeNode t : p.l) {
            // Add time to aollect all apples in this subtree
            total += getTime(t, visited);
        }
        
        // If any apple is collected or this node hasApple add 2 to total
        // 1 to come and 1 to go
        // If the node is 0, no need to count
        if (p.n != 0 && (p.hasApple || total != 0))
            total += 2;
        return total;
    }

    class TreeNode {
        int n;
        boolean hasApple;
        List<TreeNode> l;

        public TreeNode(int n, boolean hasApple) {
            this.n = n;
            this.hasApple = hasApple;
            this.l = new ArrayList<TreeNode>();
        }

        public void addNext(TreeNode n) {
            this.l.add(n);
        }
    }
}
