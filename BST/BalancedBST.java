public class BalancedBST extends BST{

    

    public BSTNode inserthelper(BSTNode ref,int val){
        if(ref==null){
            BSTNode b=new BSTNode(val);
            return b;
        }

        if(ref.value>val){
            ref.left=inserthelper(ref.left,val);
        }
        else if(val>ref.value){
            ref.right=inserthelper(ref.right,val);
        }

        int bal=height(ref.left)-height(ref.right);
        if(bal>1 && val<ref.left.value){
            return rightrot(ref);
        }
        
        if(bal<-1 && val>ref.right.value){
            return leftrot(ref);
        }
        
        if(bal>1 && val>ref.left.value){
            ref.left=leftrot(ref.left);
            return rightrot(ref);
        }
        if(bal<-1 && val<ref.right.value){
            ref.right=rightrot(ref.right);
            return leftrot(ref);
        }

        ref.height=1+max(height(ref.left),height(ref.right));

        return ref;
    }


    
    
    public BSTNode leftrot(BSTNode ref){
        BSTNode rtno=ref.right;
        BSTNode rtltno=rtno.left;
        rtno.left=ref;
        ref.right=rtltno;
        ref.height=1+max(height(ref.left),height(ref.right));
        rtno.height=1+max(height(rtno.left),height(rtno.right));
        return rtno;
    }

    public int height(BSTNode ref){
        if(ref==null){
            return 0;
        }
        return ref.height;
    }

    public int max(int a,int b){
        if(a>b){
            return a;
        }
        return b;
    }

    public BSTNode rightrot(BSTNode ref){
        BSTNode ltno=ref.left;
        BSTNode ltrtno=ltno.right;
        ltno.right=ref;
        ref.left=ltrtno;
        ref.height=1+max(height(ref.left),height(ref.right));
        ltno.height=1+max(height(ltno.left),height(ltno.right));
        return ltno;
    }


    public void insert(int key){
        root=inserthelper(root,key);
    }



    public boolean searchhelp(BSTNode ref,int num){
        if(ref==null){
            return false;
        }
        else if(ref.value==num){
            return true;
        }
        else if(ref.value<num){
          return searchhelp(ref.right,num);
        }
        else{
          return searchhelp(ref.left,num);
        }
    }

    public boolean search1(int num){
        
        return searchhelp(root,num);
    }






    public boolean delete(int key){
        int y=0;
        if(search1(key)){
            y=1;
        }
        else{
            return false;
        }
        
        root=deletehelper(root,key);
        
            return true;
        
    }

    public int findbal(BSTNode ref){
        if (ref==null){
            return 0;
        }
        return height(ref.left)-height(ref.right);
    }


    public BSTNode deletehelper(BSTNode ref,int val){
        if(ref==null){
            return null;
        }
        if(val<ref.value){
            ref.left=deletehelper(ref.left,val);
        }
        else if(val>ref.value){
            ref.right=deletehelper(ref.right,val);
        }
        else{
            if(ref.left==null && ref.right==null){
                ref=null;
            } 
            else if(ref.left==null){
                ref=ref.right;
            }
            else if(ref.right==null){
                ref=ref.left;
            }
            else{
                BSTNode kl=findmax(ref.left);
                ref.value=kl.value;
                ref.left=deletehelper(ref.left,kl.value);
            }
        }
        if(ref==null){
            return null;
        }
        ref.height=1+max(height(ref.left),height(ref.right));
        int bal=findbal(ref);
        if(bal>1 && findbal(ref.left)>=0){
            return rightrot(ref);
        }
        if(bal>1 && findbal(ref.left)<0){
            ref.left=leftrot(ref.left);
            return rightrot(ref);
        }
        if(bal<-1 && findbal(ref.right)<=0){
            return leftrot(ref);
        }
        if(bal<-1 && findbal(ref.right)>0){
            ref.right=rightrot(ref.right);
            return leftrot(ref);
        }
        return ref;
    }
    
    public BSTNode findmax(BSTNode ref){
        while(ref.right!=null){
            ref=ref.right;
        }
        return ref;
    }

    
    
    // public BSTNode deletehelper(BSTNode ref,int val) {
    //     if (ref==null){
    //         return null;
    //     }
    //     if (val<ref.value){
    //         ref.left=deletehelper(ref.left,val);
    //     }
    //     else if(val>ref.value){
    //         ref.right=deletehelper(ref.right,val);
    //     }
    //     else{
    //         if(ref.left==null && ref.right==null) {
    //             ref=null;
    //         }
    //         else if(ref.left==null) {
    //             ref=ref.right;
    //         }
    //         else if(ref.right==null){
    //             ref=ref.left;
    //         }
    //         else{
    //             BSTNode kl=findmin(ref.right);
    //             ref.value=kl.value;
    //             ref.right=deletehelper(ref.right, kl.value);
    //         }
    //     }
    //     if (ref==null) {
    //         return null;
    //     }
    //     ref.height=1+max(height(ref.left),height(ref.right));
    //     int bal=findbal(ref);
    //     if (bal>1 && findbal(ref.left)>=0) {
    //         return rightrot(ref);
    //     }
    //     if (bal>1 && findbal(ref.left)<0) {
    //         ref.left=leftrot(ref.left);
    //         return rightrot(ref);
    //     }
    //     if (bal<-1 && findbal(ref.right)<=0) {
    //         return leftrot(ref);
    //     }
    //     if (bal<-1 && findbal(ref.right)>0) {
    //         ref.right=rightrot(ref.right);
    //         return leftrot(ref);
    //     }
    //     return ref;
    // }
    
    
    
    
    
}
