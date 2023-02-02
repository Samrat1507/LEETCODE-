class LFUCache {
    //using 3 hashmaps,
    //1 normal hashmap for key and value
    //1 hashmap for key and count
    //1 hashmap for count and keys having the same count
    HashMap<Integer,Integer>map;
    HashMap<Integer,Integer>count;
    HashMap<Integer,LinkedHashSet<Integer>>freq;
    int min_freq=0;
    int cap=0;
    public LFUCache(int capacity) {
        this.cap=capacity;
        map=new HashMap<>();
        count=new HashMap<>();
        freq=new HashMap<Integer,LinkedHashSet<Integer>>();
        freq.put(1,new LinkedHashSet<>());
        
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        int cnt=count.get(key);
        count.put(key,cnt+1);//we need to update the count of the key since it has been accessed
        freq.get(cnt).remove(key);//removing the element that is present in this count
        //for example freq<1,<2,3,4,5>>,elements 2,3,4,5 had the frequency 1
        //we need to increase the frequency of current key say 3 as it was accessed so its frequency(of accessability) must be increased
        //but first we need to remove it from frequency 1 list
        if(min_freq==cnt && freq.get(cnt).size()==0)
            min_freq++;
        //suppose 3 was the only element with freq 1,now that it has been accessed again its frequency(of accessibility) has increased to 2,no the list of elements having frequency 1 is empty,so we need to increase the min frequency 
        if(!freq.containsKey(cnt+1))
            freq.put(cnt+1,new LinkedHashSet<Integer>());
        freq.get(cnt+1).add(key);
        //adding the key to the new frequency set;
        return map.get(key);
        
    }
    
    public void put(int key, int value) {
        if(cap<=0)
            return;
        if(map.containsKey(key)){
            map.put(key,value);
            get(key);//this will make necessary changes in the count map and freq map list
            return;
        }
        else if(map.size()>=cap){
            //the map has reached its cap size
            //now we must delete an elemnt which has the min freq and is leas rrecently accesd
            //suppose the min freq is 1
            //freq<1,<3,1,4,7>>
            //here 3 is least recently accessed
            //so 3 must be delete,although 1,4,7 also have the same access frequency,but they are most recently accessed than 3
           int element= freq.get(min_freq).iterator().next();
            freq.get(min_freq).remove(element);
            map.remove(element);
            //we must also remove it from the map so that if it is tried to access it should return -1
        }
        map.put(key,value);
        count.put(key,1);
        min_freq=1;//now the min will be 1 as this is a new element added whose frequency is of accessability is 1 
        freq.get(min_freq).add(key);
        
    }
}
