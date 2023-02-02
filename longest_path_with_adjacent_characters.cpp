class Solution {
public:
    int longestPath(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<int>> adj(n);
        for(int i=1; i<n; i++) adj[parent[i]].push_back(i);
        int res = 0;
        fun(0, adj, s, res);
        return res;
    }
    
    int fun(int root, vector<vector<int>> &adj, string &s, int &res) {
        int max1 = 0, max2 = 0;
        for(auto &ad: adj[root]) {
            int curr = fun(ad, adj, s, res);
            if(s[ad] != s[root]) {
                if(curr >= max1) {
                    max2 = max1;
                    max1 = curr;
                } else if(curr >= max2) {
                    max2 = curr;
                }
            }
        }
        res = max(res, max1+max2+1);
        return max1 + 1;
    }
};
