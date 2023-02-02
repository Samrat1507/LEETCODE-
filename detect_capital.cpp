class Solution {
public:
bool detectCapitalUse(string word)
{
int l=word.length();

    int cap=0;int lower=0;
    for(int i=0;i<l;i++)
    {
        if(islower(word[i]))    lower++;
        else cap++;

    }
    if(cap==l||lower==l||(isupper(word[0])&&lower==l-1))
        return true;
    else    return false;
    
}
};
