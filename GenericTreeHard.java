import java.util.*;
class Trees
{
 public static class Node
 {
  ArrayList<Node> children;
  int data;

    public Node()
    {
      this.children = new ArrayList<>();
      this.data = 0;
    }
   public Node(int data)
   {
     this.children = new ArrayList<>();
     this.data = data;

   }

}

public static Node construct(Integer[] arr)
{
     Node node = null;
     Stack<Node> st = new Stack<>();
     for(int i=0;i<arr.length;i++)
        {
            Integer data = arr[i];
            if(data != null)
            {
               Node nn = new Node(data);
               if(st.size()==0)
               {
                     st.push(nn);
                     node = nn;
               }
               else
               {
                        
                        st.peek().children.add(nn);
                        st.push(nn);
               }

            }
            else
            {
              st.pop();
            }

        }
     return node;

}
public static void display(Node root)
{
   
      String str = " [ "+ root.data+" ] "+" -> ";
      for(Node child : root.children)
      {
              str+=child.data+" , ";
      }
      System.out.println(str + ".");
      for(int i=0;i<root.children.size();i++)
      {
               
             Node child = root.children.get(i);
             display(child);


      }

}
public static int size(Node root)
{
   int sz = 0;
   for(Node child : root.children)
   {
         sz += size(child);
   }
   return sz+1;


}
public static int MaxValue(Node root)
{
    int mx = Integer.MIN_VALUE;
    for(Node child : root.children)
    {
       mx = Math.max(mx, MaxValue(child));
    }


    return Math.max(mx,root.data);

}
public static int MinValue(Node root)
{
   int mn = Integer.MAX_VALUE;
   for(Node child : root.children)
   {
        mn = Math.min(mn,MinValue(child));
   }
  return Math.min(mn,root.data);
}
public static int  height(Node root)
{ 
  int ht = -1;
  for(Node child : root.children)
  {

       ht = Math.max(ht,height(child));
  }

  return ht+1;
}
public static void traversal(Node node)
{

     System.out.println("Node pre "+ node.data);

     for(Node child : node.children)
     {
        System.out.println("Edge pre "+ node.data +" -- "+child.data);
        traversal(child);
        System.out.println("Edge post "+node.data +" -- "+child.data);
     }

     System.out.println("Node post "+ node.data);


}
public static void levelOrder(Node node)
{
   Queue<Node> qu = new ArrayDeque<>(); //tried with stack as well from left to right, right to left
   qu.add(node); //node 10 ka data hi hai idhar
   while(qu.size()>0)
   {
       //remove from queue
       Node rem = qu.remove();
       //print th removed node
       System.out.print(rem.data+" ");
       //Add the children of that node
       for(Node child : rem.children)
       {
           qu.add(child);
       }

   }
   System.out.println(".");

}
public static void levelOrderLineWise(Node node)
{
    Queue<Node> mainQ = new ArrayDeque<>();
    Queue<Node> childQ = new ArrayDeque<>();
    mainQ.add(node);
    while(mainQ.size()>0)
    {
        Node rem = mainQ.remove();//remove from main queue
        System.out.print(rem.data+" ");//print from main queue
      for(Node child : rem.children)
      {
           childQ.add(child);
      }
      if(mainQ.size()==0)
      {
          System.out.println();
          Queue<Node> tempQ = mainQ ;
          mainQ = childQ;
          childQ = tempQ;

      }

    }
    

}
public static void levelOrderDelimeterNull(Node node)
{
     //there is another approach using -1, see video.
      //using linnked list because we will use null here to seperate lines
      Queue<Node> qu = new LinkedList<>();
      qu.add(node);
      qu.add(null);
      while(qu.size()>0)
      {
         Node rem = qu.remove();//remove
         if(rem == null)
         {
             System.out.println();
             if(qu.size()>0)
               qu.add(null);
         }
         else
         {
          System.out.print(rem.data +" ");
          for(Node child: rem.children)
          {
                qu.add(child);
          }
         }

      }
}
public static void levelOrderSizeApproach(Node node)
{
      Queue<Node> qu = new ArrayDeque<>(); // can use LL as well.
       qu.add(node);
       while(qu.size()>0)
       {
           int sz = qu.size(); //humesha new size banega naye level par
           while(sz-- > 0) //pehle size decrease hoga fir print hoga
           {
              //remove
              Node rem = qu.remove();
              //print
              System.out.print(rem.data+" ");
              //add children
              for(Node child: rem.children)
              {
                  qu.add(child); //queue mai add kar rhae hai , sz ek alag hi duniya ka variable hai jo apne aapp chalta hai.
              }

           }
           System.out.println();

       }







}
public static void mirror(Node node)
{
     for(Node child : node.children)
     {
          mirror(child);
     }

int left = 0;
int right = node.children.size()-1;

while(left < right)
{
      
Node temp = node.children.get(left);
node.children.set(left, node.children.get(right));
node.children.set(right, temp);
left++;
right--;

}

}
public static void removeLeaf(Node node)
{

    //  for(Node child :node.children) //concurrent modification exception aega jis list mai remove kar rahe hai usi mai change kar rhe , 
    //qki ye iterator use karta hai internally and itertor gets confi=used if list is being changed.
    //  {
    //     if(child.children.size()==0)
    //     {
    //          node.children.remove(child);
    //     }
    //  }
    //  for(int i=0;i<node.children.size();i++)// hum 0 th index se travel nahi karenge qki index shift hota hai remove mai
    //  {
    //        Node child = node.children.get(i); 
    //        if(child.children.size()==0)
    //        {
    //                node.children.remove(child);//also direct object pass nahi karenge index pass karenge , object se complexxity badhta hai.
    //        }
    //  }
    for(int i= node.children.size()-1;i>=0;i--) // aur ek approach hai arraylist mai pakad lo isko
    {
           Node child = node.children.get(i);
           if(child.children.size()==0)
           {
            node.children.remove(i);
           }
    }

  for(Node child : node.children)
  {
         removeLeaf(child);
  }


}

public static boolean findData(Node node, int data) //see how global variable works in java
{
     //there is another approach to do it using global variable.
     if(node.data == data)
     {
      return true;
     }
   boolean res = false; // will work if node has no child

   for(Node child : node.children)
   {
       res = findData(child,data);
       if(res==true)
       {
           return true;
       }
   }
return res;
}

public static ArrayList<Integer> NodeToRootPath(Node node, int data)
{
    if(node.data == data)
    {
      ArrayList<Integer> bres = new ArrayList<>();
      bres.add(node.data);
      return bres;

    }
    for(Node child : node.children)
    {
           ArrayList<Integer> rres = NodeToRootPath(child, data);
           if(rres.size()>0)
           {
                 rres.add(node.data);
                 return rres;
           }
    }

   return new ArrayList<>();
}
public static int lca(Node node, int d1,int d2)
{
      ArrayList<Integer> arr1 = NodeToRootPath(node,d1);
      ArrayList<Integer> arr2 = NodeToRootPath(node,d2);
      for(Integer nums : arr1)
      {
         System.out.print(nums+" ");
      }
      System.out.println();
      for(Integer nums : arr2)
      {
         System.out.print(nums+" ");
      }
      System.out.println();
      int i = arr1.size()-1;
      int j = arr2.size()-1;
     int res = 0;
     while(i>=0 && j>=0 && arr1.get(i) == arr2.get(j))
     {
              res = arr1.get(i);
              i--;
              j--;
     }

   return res;
}


public static void main(String[] args)
{
        //Integer[] arr = {10,20,null,30,50,null,60,null,null,40,null,null};
       Integer[] arr = {10,20,50,null,60,null,null,30,70,null,80,110,null,120,null,null,90,null,null,40,100,null,null,null};
       // Integer[] arr = {10,20,null,30,100,null,null,40,50,null,60,null,null,70,null,80,null,null};
        //System.out.println("BEFORE: ");
        Node root = construct(arr);
        System.out.println("lca : "+lca(root,60,90));

        // ArrayList<Integer> rres = NodeToRootPath(root,80);
        // for(Integer num : rres)
        // {
        //    System.out.print(num + ",");       
        // }
        //boolean res = findData(root,110);
       // System.out.println(findData(root,500));

        //display(root);
       // removeLeaf(root);
        //System.out.println("AFTER: ");
       // display(root);
       // System.out.println("BEFORE: ");
        //mirror(root);
        //System.out.println("AFTER: ");
        //display(root);
        //levelOrderSizeApproach(root);
       //levelOrderLineWise(root);
        //levelOrder(root);
        //traversal(root);
        //System.out.println("AFTER TRAVERSAL");
        //display(root);
        //System.out.println(size(root));
        //System.out.println("MAX:"+MaxValue(root));
        //System.out.println("MIN:"+MinValue(root));
        //System.out.println("height : "+height(root));

}

}