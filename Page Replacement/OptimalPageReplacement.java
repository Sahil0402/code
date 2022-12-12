public class OptimalPageReplacement {

    static void optimalPage(int pages[], int n,int capacity){
        int frames[]=new int[capacity];
        int hit=0;
        int index=0;

        for(int i=0;i<n;i++){
            if(search(pages[i], frames)){
                hit++;
                continue;
            }

            //if page not found
            //if space is available
            if(index<capacity){
                frames[index++]=pages[i];
            }else //find page to be replaced
            {
                int j=predict(pages, frames, n, i+1);
                frames[j]=pages[i]; 
            }

        }
        System.out.println("No. of hits = " + hit);
        System.out.println("No. of misses = " + (n - hit));
    }

    static int predict(int pages[], int frames[], int n,int frame_no){
            int res=-1,farthest=frame_no;
            for(int i=0;i<frames.length;i++){
                int j;
                for(j=frame_no;j<n;j++){
                    if(frames[i]==pages[j]){
                        if(j>farthest){
                            farthest=j;
                            res=i;
                        }
                        break;
                    }
                }


                //if page is never reffered in futur
                if(j==n){
                    return i;
                }
            }
            // If all of the frames were not in future,
        // return any of them, we return 0. Otherwise
        // we return res.
        return (res==-1)?0:res;
    }

    static boolean search(int key, int frames[]){
        for(int i=0;i<frames.length;i++){
            if(key==frames[i]){
                return true;
            }
            
        }
        return false;
    }
    public static void main(String[] args) {
        int pages[]
            = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2  ,1,2,0,1,7,0,1};
        
        int capacity = 4;
        optimalPage(pages, pages.length, capacity);
    }
}
