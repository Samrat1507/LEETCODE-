class Solution {
public:
    string convert(string s, int n) {
        //If we have our n==1 then the ans would be simply s
        if(n==1){
            return s;
        }
        // We have created a ans in which we are gonna store the ans string
        string ans="";
        //0P      6I       12N
        //1A   5L 7S   11I 13G
        //2Y 4A   8H  10R
        //3P      9I
        //if we observe that for first row and last we have difference as 2*n-2
        //but for other row it is decremented by 2 from the above row and goes in 
        // alternate pattern of i and 2*n-2-i
        int val=0;
        for(int i=2*(n-1);i>=0;i=i-2){
            int j=val;
            int turn=true;
            //we are adding the char in our ans till the last
            while(j<s.size()){
                
                ans+=s[j];
                //for the alternate pattern 
                j+=(i!=0&&i!=2*n-2?turn?i:2*n-2-i:2*n-2);
                //toggling the turn pattern
                turn=!turn;
            }
            val++;
        }
        //return the ans
        return ans;
    }
};
