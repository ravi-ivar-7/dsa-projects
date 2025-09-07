public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;
    public CellTower ct;

    public PointQuadtree() {
        this.root = null;
        ct=new CellTower(0, 0, 999999999);
    }

    public PointQuadtreeNode inserthelp(PointQuadtreeNode root,CellTower a){
        if(root==null){
            PointQuadtreeNode pt=new PointQuadtreeNode(a);
            return pt;
        }
        if(a.x<root.celltower.x && a.y<root.celltower.y || a.x==root.celltower.x && a.y<root.celltower.y){
            root.quadrants[2]=inserthelp(root.quadrants[2],a);
        }
        if(a.x>root.celltower.x && a.y<root.celltower.y || a.x>root.celltower.x && a.y==root.celltower.y){
            root.quadrants[3]=inserthelp(root.quadrants[3],a);
        }
        if(a.x>root.celltower.x && a.y>root.celltower.y || a.x==root.celltower.x && a.y>root.celltower.y ){
            root.quadrants[1]=inserthelp(root.quadrants[1],a);
        }
        if(a.x<root.celltower.x && a.y>root.celltower.y || a.x<root.celltower.x && a.y==root.celltower.y ){
            root.quadrants[0]=inserthelp(root.quadrants[0],a);
        }
        return root;

    }

    public boolean insert(CellTower a) {
      
        if(root==null){
            PointQuadtreeNode pt=new PointQuadtreeNode(a);
            root=pt;
            return true;
        }
        if(cellTowerAt(a.x, a.y)){
            return false;
        }
        // while(vb!=null){
        //     if(a.x==vb.celltower.x && a.y==vb.celltower.y){
        //         return false;
        //     }
        //     if(a.x<vb.celltower.x && a.y<vb.celltower.y || a.x==vb.celltower.x && a.y<vb.celltower.y){
        //         vb=vb.quadrants[2];
        //     }
        //     if(a.x>vb.celltower.x && a.y<vb.celltower.y ){
        //         vb=vb.quadrants[3];
        //     }
        //     if(a.x>vb.celltower.x && a.y>vb.celltower.y || a.x>vb.celltower.x && a.y==vb.celltower.y){
        //         vb=vb.quadrants[1];
        //     }
        //     if(a.x<vb.celltower.x && a.y>vb.celltower.y || a.x<vb.celltower.x && a.y==vb.celltower.y || a.x==vb.celltower.x && a.y>vb.celltower.y){
        //         vb=vb.quadrants[0];
        //     }
        //     if(a.x==vb.celltower.x && a.y<vb.celltower.y){
        //         vb=vb.quadrants[3];
        //     }
        // }
        root=inserthelp(root,a);
        return true;
    }

    public boolean cellTowerAt(int x, int y) {
        // TO be completed by students
        if(root==null){
            return false;
        }
        PointQuadtreeNode vb=root;
        while(vb!=null){
            if(x==vb.celltower.x && y==vb.celltower.y){
                return true;
            }
            else if(x<vb.celltower.x && y<vb.celltower.y || x==vb.celltower.x && y<vb.celltower.y){
                vb=vb.quadrants[2];
            }
            else if(x>vb.celltower.x && y<vb.celltower.y || x>vb.celltower.x && y==vb.celltower.y ){
                vb=vb.quadrants[3];
            }
            else if(x>vb.celltower.x && y>vb.celltower.y || x==vb.celltower.x && y>vb.celltower.y ){
                vb=vb.quadrants[1];
            }
            else if(x<vb.celltower.x && y>vb.celltower.y || x<vb.celltower.x && y==vb.celltower.y ){
                vb=vb.quadrants[0];
            }
        }
        

        return false;
    }




    






    public void celltowerhelp(int x,int y,int r,PointQuadtreeNode vb){
        if(vb==null){
            return;
        }
        if(vb.celltower.distance(x,y)<=r){
            if(ct.cost>=vb.celltower.cost){
                ct=vb.celltower;
            }
        }
        if(x==vb.celltower.x && y==vb.celltower.y){
            if(ct.cost>vb.celltower.cost){
                ct=vb.celltower;
            }
            celltowerhelp(x,y,r,vb.quadrants[0]);
            celltowerhelp(x,y,r,vb.quadrants[1]);
            celltowerhelp(x,y,r,vb.quadrants[2]);
            celltowerhelp(x,y,r,vb.quadrants[3]);
        }
        else if(x<vb.celltower.x && y<vb.celltower.y || x==vb.celltower.x && y<vb.celltower.y){
            if(vb.celltower.x-x>=r && y-vb.celltower.y>r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
            }
            else if(vb.celltower.x-x>=r && vb.celltower.y-y<=r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
            }
            else if(vb.celltower.x-x<r && vb.celltower.y-y>r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
            }
            else if(vb.celltower.x-x<r && vb.celltower.y-y<=r && vb.celltower.distance(x,y)<r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
            else if(vb.celltower.x-x<r && vb.celltower.y-y<=r && vb.celltower.distance(x,y)>r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
            }


        }
        else if(x>vb.celltower.x && y<vb.celltower.y || x>vb.celltower.x && y==vb.celltower.y ){
            if(x-vb.celltower.x>r && vb.celltower.y-y>=r){
                celltowerhelp(x,y,r,vb.quadrants[3]);
            }
            else if(x-vb.celltower.x>r && vb.celltower.y-y<r){
                celltowerhelp(x,y,r,vb.quadrants[1]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
            }
            else if(x-vb.celltower.x<=r && vb.celltower.y-y>=r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
            }
            else if(x-vb.celltower.x<=r && vb.celltower.y-y<r && vb.celltower.distance(x,y)<r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
            else if(x-vb.celltower.x<=r && vb.celltower.y-y<r && vb.celltower.distance(x,y)>=r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
        }
        else if(x>vb.celltower.x && y>vb.celltower.y || x==vb.celltower.x && y>vb.celltower.y){
            if(x-vb.celltower.x>=r && y-vb.celltower.y>r){
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
            else if(x-vb.celltower.x>=r && y-vb.celltower.y<=r){
                celltowerhelp(x,y,r,vb.quadrants[1]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
            }
            else if(x-vb.celltower.x<r && y-vb.celltower.y>r){
                celltowerhelp(x,y,r,vb.quadrants[1]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
            }
            else if(x-vb.celltower.x<r && y-vb.celltower.y<=r && vb.celltower.distance(x,y)<r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
            else if(x-vb.celltower.x<r && vb.celltower.y-y<=r && vb.celltower.distance(x,y)>=r){
                celltowerhelp(x,y,r,vb.quadrants[0]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
        }
        else if(x<vb.celltower.x && y>vb.celltower.y || x<vb.celltower.x && y==vb.celltower.y ){
            if(vb.celltower.x-x>r && y-vb.celltower.y>=r){
                celltowerhelp(x,y,r,vb.quadrants[0]);
            }
            else if(vb.celltower.x-x>r && y-vb.celltower.y<r){
                celltowerhelp(x,y,r,vb.quadrants[0]);
                celltowerhelp(x,y,r,vb.quadrants[2]);
            }
            else if(vb.celltower.x-x<=r && y-vb.celltower.y>=r){
                celltowerhelp(x,y,r,vb.quadrants[1]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
            }
            else if(vb.celltower.x-x<=r && y-vb.celltower.y<r && vb.celltower.distance(x,y)<r){
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[3]);
                celltowerhelp(x,y,r,vb.quadrants[0]);
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
            else if(vb.celltower.x-x<=r && vb.celltower.y-y<r && vb.celltower.distance(x,y)>=r){
                celltowerhelp(x,y,r,vb.quadrants[0]);
                celltowerhelp(x,y,r,vb.quadrants[2]);
                celltowerhelp(x,y,r,vb.quadrants[1]);
            }
        }
    }





    public CellTower chooseCellTower(int x, int y, int r) {
        // TO be completed by students
        ct.cost=999999999;
        celltowerhelp(x,y,r,root);
        
        if(ct.cost==999999999){
            return null;
        }
        System.out.println(ct.x+" "+ ct.y);
        System.out.println(x+" "+y);
        return ct;
    }








    public static void main(String[] args) {
        PointQuadtree obj = new PointQuadtree();
    CellTower c1 = new CellTower(0,0,5);
    CellTower c2 = new CellTower(-2,0,4);
    CellTower c3 = new CellTower(2,3,10);
    CellTower c4 = new CellTower(-4,6,9);
    obj.insert(c1);
    obj.insert(c2);
    obj.insert(c3);
    System.out.println(obj.cellTowerAt(-2,0)); // returns true
    System.out.println(obj.cellTowerAt(2,4)); // returns false
    System.out.println(obj.chooseCellTower(0, 6, 5)); // returns c3
    System.out.println(obj.insert(c4));
    System.out.println(obj.chooseCellTower(0, 6, 5)); // returns c4
// The current tree is shown in Figure 3.
    CellTower c5 = new CellTower(-3,7,5);
    CellTower c6 = new CellTower(-3,3,4);
    CellTower c7 = new CellTower(-6,7,2);
    CellTower c8 = new CellTower(-5,4,9);
    obj.insert(c5);
    obj.insert(c6);
    obj.insert(c7);
    obj.insert(c8);
    System.out.println(obj.insert(c3)); // should fail
    System.out.println(obj.chooseCellTower(-2, 6, 2)); // returns c5

    }
    
    
    }
    
    
    
    


