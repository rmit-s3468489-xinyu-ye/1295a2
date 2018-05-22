package mininet;

import java.util.Hashtable;
/**
 *
 * @author Xinyu YE s346849
 */
public class PathNode 
{
    private String name;
    private String relation;
    private PathNode parent;
    private Hashtable<String,PathNode> children;
    
    public PathNode(String name,String relation,PathNode parent)
    {
        this.name = name;
        this.relation = relation;
        this.parent = parent;
        this.children = new Hashtable<String,PathNode>();
    }
    
    public PathNode getParent() 
    {
        return parent;
    }

    public void setParent(PathNode parent) 
    {
        this.parent = parent;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public Hashtable<String,PathNode> getChildren() 
    {
        return children;
    }

    public void setChildren(Hashtable<String,PathNode> children) 
    {
        this.children = children;
    }

    public String getRelation() 
    {
        return relation;
    }

    public void setRelation(String relation) 
    {
        this.relation = relation;
    }
}
