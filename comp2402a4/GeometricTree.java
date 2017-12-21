package comp2402a4;

import java.util.*;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

	public GeometricTree() {
		super (new GeometricTreeNode());
	}

	public void inorderDraw() {
		assignLevels();

		GeometricTreeNode j= firstNode();
		for(int i=0;j!=null; i++){
			j.position.x=i;
			j=nextNode(j);
		}
	}



	protected void randomX(GeometricTreeNode u, Random r) {
		if (u == null) return;
		u.position.x = r.nextInt(60);
		randomX(u.left, r);
		randomX(u.right, r);
	}


	/**
	 * Draw each node so that it's x-coordinate is as small
	 * as possible without intersecting any other node at the same level
	 * the same as its parent's
	 */
	public void leftistDraw() {
		assignLevels();
		Queue<GeometricTreeNode> que= new LinkedList<GeometricTreeNode>();
		Map<Integer,Integer> count=new HashMap<Integer, Integer>();
		que.add(r);
		while(!que.isEmpty()){
			GeometricTreeNode u =que.remove();
			count.put(u.position.y, count.containsKey(u.position.y) ? count.get(u.position.y)+1:0);
			if(u.left !=null) que.add(u.left);
			if(u.right!=null) que.add(u.right);
			u.position.x=count.get(u.position.y);
		}
	}

	int maxWidth=0;
	Map<GeometricTreeNode,Integer>nodeMap;

	public void balancedDraw() {
		assignLevels();
		nodeMap = mapNodeTreeSize(r);
		NodeSort(r,0,0);
	    maxWidth=0;
	}

	public void NodeSort(GeometricTreeNode u, int x, int y){
		if(u==null) return;

		if(x > maxWidth){
			maxWidth=x;
		}
		u.position.x=x;
		u.position.y=y;

		if(u.left!=null && u.right == null){
			this.NodeSort(u.left, maxWidth+1,y);
		}
		else if(u.left!=null && u.right!=null){
			if(nodeMap.get(u.left)>nodeMap.get(u.right)){
				this.NodeSort(u.right,x,y+1);
				this.NodeSort(u.left,maxWidth+1,y);
			}
			else{
				this.NodeSort(u.left,x ,y+1);
				this.NodeSort(u.right,maxWidth+1,y);
			}
		}
		else{
			this.NodeSort(u.right, maxWidth+1, y);
		}
	}

	public HashMap<GeometricTreeNode,Integer> mapNodeTreeSize(GeometricTreeNode t){
		HashMap<GeometricTreeNode,Integer> nodeMap= new HashMap<GeometricTreeNode,Integer>();
		GeometricTreeNode u =t,prev=null,next;

		while(u!=null){
			if(prev==u.parent){
				if(u.left!=null)
					next=u.left;
				else if	(u.right!=null)
					next=u.right;
				else{
					nodeMap.put(u,(u.left!=null ? nodeMap.get(u.left):1)+(u.right!=null ? nodeMap.get(u.right):1)+1);
					next=u.parent;
				}
			}
			else if(prev==u.left){
				if(u.right!=null)
					next=u.right;
				else{
					nodeMap.put(u,(u.left!=null ? nodeMap.get(u.left):1)+(u.right!=null ? nodeMap.get(u.right):1)+1);
					next=u.parent;
				}
			}
			else{
				nodeMap.put(u,(u.left!=null ? nodeMap.get(u.left):1)+(u.right!=null ? nodeMap.get(u.right):1)+1);
				next=u.parent;
			}
			prev=u;
			u=next;
		}
		return nodeMap;
	}

	protected void assignLevels() {
		assignLevels(r, 0);
	}

	protected void assignLevels(GeometricTreeNode u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}

	public static void main(String[] args) {
		GeometricTree t = new GeometricTree();
		galtonWatsonTree(t, 100);
		System.out.println(t);
		t.inorderDraw();
		System.out.println(t);
	}

}
