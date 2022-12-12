import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LRUPageReplacement {
    static int pageFaults(int pages[], int n, int capaity){
        HashSet<Integer> set=new HashSet<>(capaity);
        HashMap<Integer,Integer> indexes=new HashMap<>();

        int page_faults=0;
        for(int i=0;i<n;i++){
            if(set.size()<capaity){
                if(!set.contains(pages[i])){
                    set.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }else{
                if(!set.contains(pages[i])){
                    int lru=Integer.MAX_VALUE,val=Integer.MIN_VALUE;
                    Iterator<Integer> itr=set.iterator();

                    while(itr.hasNext()){
                        int temp=itr.next();
                        if(indexes.get(temp)<lru){
                            lru=indexes.get(temp);
                            val=temp;
                        }
                    }

                    set.remove(val);
                    indexes.remove(val);
                    set.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);

            }
        }

        return page_faults;
    }
    public static void main(String[] args) {
        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2,1,2,0,1,7,0,1};
        
        int capacity = 4;
        int miss_ratio=pageFaults(pages, pages.length, capacity);
        int hit_ratio=pages.length-miss_ratio;
        System.out.println("Hit Ratio "+hit_ratio);
        System.out.println("Miss Ratio "+miss_ratio);
    }
}
