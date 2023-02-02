class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        sort(points.begin(),points.end());
        for(int i = 0;i < points.size();i++){
            cout << points[i][0] << " " << points[i][1] << endl;
        }
        int ct = 0,p1 = -1,p2 = -1;
        for(int i = 0;i < points.size();i++){
            if(p1 == -1 && p2 == -1){
                ct++;
                p1 = points[i][0];
                p2 = points[i][1];
            }
            else{
                if(points[i][0] <= p2){
                    p1 = points[i][0];
                    p2 = min(p2 , points[i][1]);
                }
                else{
                    p1 = points[i][0];
                    p2 = points[i][1];
                    ct++;
                }
            }
        }

        return ct;
    }
};
