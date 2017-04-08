package letmesleep.uicp.cn.dao;

import java.util.List;

import beans.Node;


public interface NodeDao {
	
	public void addNodeData(Node node);
	
	public Node getNodeData();
	
	public void deleteNode(Node node);
	
	public void upDataNode(Node node);
	
	public List<Node> getAllNodeData();
	
}
