import java.util.*;


public class BST{
    public BSTNode root;

    public BST(){
        root=null;
    }
    
    
    

    

    public BSTNode inserthelp(BSTNode ref,int num){
        if(ref==null) {
        BSTNode b=new BSTNode(num);
        return b;
        }
        if(ref.value>num){
            ref.left=inserthelp(ref.left,num);
        }
        else{
            ref.right=inserthelp(ref.right,num);
        }
        // ref.height=1+max(height1(ref.left),height1(ref.right));
        return ref;
        
    }

    public void insert(int num){
        root=inserthelp(root,num);
        // BSTNode g=this.root;
        // updateheight(g,num);
    }
    

    // public int finalheight(BSTNode f){
    //     if(f==null){
    //         return 0;
    //     }
    //     return 1+max(finalheight(f.left),finalheight(f.right));
    // }

    // public void updateheight(BSTNode b,int num){
    //     if(b==null){
    //         return;
    //     }
    //     b.height=finalheight(b);
    //     if(b.value>num){
    //         updateheight(b.left,num);
    //     }
    //     else{
    //         updateheight(b.right,num);
    //     }

    // }



    // BSTNode deletehelper(int val,BSTNode no){
    //     if (no==null){ 
    //         return null;
    //     }
    //     if(no.value<val){
    //         no.right=deletehelper(val,no.right);
    //         no.height=1+max(height1(no.left),height1(no.right));
    //         return no;
    //     } 
    //     else if(no.value>val){
    //         no.left=deletehelper(val,no.left);
    //         no.height=1+max(height1(no.left),height1(no.right));
    //         return no;
    //     } 
    //     else{ 
    //         if(no.left==null && no.right==null){ 
            
    //            return null;
    //         }

    //         else if(no.left==null){ 
    //             BSTNode ref=no.right;
    //             no.right=null;
    //             no.height=1+max(height1(no.left),height1(no.right));
    //             return ref;
    //         } 

    //         else if(no.right==null){ 
    //             BSTNode ref=no.left;
    //             no.left=null;
    //             no.height=1+max(height1(no.left),height1(no.right));
        
    //             return ref;
    //         }

            
            
    //         else { 
    //             BSTNode minno=no.right;
    //             while(minno.left!=null) { 
    //                 minno=minno.left; 
    //             }
    //             int rightMin=minno.value;
    //             no.value=rightMin;
    //             no.right=deletehelper(rightMin,no.right);
    //             no.height=1+max(height1(no.left),height1(no.right));
    //             return no;
    //         }
    //     }
    // }

    public BSTNode deletehelper(int val, BSTNode ref){
        if(ref==null){
            return null;
        }
        if(val<ref.value){
            ref.left=deletehelper(val,ref.left);
            // ref.height=1+max(height1(ref.left),height1(ref.right));
            return ref;
        }
        else if(val>ref.value){
            ref.right=deletehelper(val,ref.right);
            // ref.height=1+max(height1(ref.left),height1(ref.right));
            return ref;
        }
        else
        { 
            if(ref.left==null && ref.right==null){ 
                return null;
            }
            else if(ref.left==null){ 
                BSTNode rtno=ref.right;
                ref.right=null;
                // ref.height=1+max(height1(ref.left),height1(ref.right));
                return rtno;
            }
            else if(ref.right==null) {
                BSTNode ltno=ref.left;
                ref.left=null;
                // ref.height=1+max(height1(ref.left),height1(ref.right));
                return ltno;
            } else { 
                BSTNode check=ref.left;
                while(check.right!=null) {
                    check=check.right;
                }
                ref.value=check.value;
                ref.left=deletehelper(check.value,ref.left);
                // ref.height=1+max(height1(ref.left),height1(ref.right));
                return ref;
            }
        }
    }


    // int height1(BSTNode no) {
    // if (no==null) {
    //     return 0;
    // }
    // return no.height;
    // }



    public boolean delete(int num){
        int y=0;
        if(search(num)){
            y=1;
        }
        else{
            return false;
        }
        
        root = deletehelper(num,root);
        
            return true;
    
       

    }

    public int max(int a,int b){
        if(a>b){
            return a;
        }
        return b;
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

    public boolean search(int num){
        
        return searchhelp(root,num);
    }




    public void inorderhelp(BSTNode ref,ArrayList<Integer> l){
        if(ref==null){
            return;
        }
        inorderhelp(ref.left,l);
        l.add(ref.value);
        inorderhelp(ref.right,l);
    }
    

    public ArrayList<Integer> inorder(){
        
		ArrayList<Integer> al = new ArrayList<>();
        inorderhelp(root,al);
		return al;
    }

    public void preorderhelp(BSTNode ref,ArrayList<Integer> l){
        if(ref==null){
            return;
        }
        l.add(ref.value);
        preorderhelp(ref.left,l);
        
        preorderhelp(ref.right,l);
    }

   

    public ArrayList<Integer> preorder() {
        
		ArrayList<Integer> al = new ArrayList<>();
        preorderhelp(root,al);
		return al;
    }


    public void postorderhelp(BSTNode ref,ArrayList<Integer> l){
        if(ref==null){
            return;
        }
        
        postorderhelp(ref.left,l);
        
        postorderhelp(ref.right,l);
        l.add(ref.value);
    }


    public ArrayList<Integer> postorder() {
        
		ArrayList<Integer> al = new ArrayList<>();
        postorderhelp(root,al);
		return al;
    }
}

