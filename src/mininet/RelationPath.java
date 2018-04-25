/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mininet;

/**
 *
 * @author Xinyu YE s3468489
 */
public class RelationPath 
{
    private String path="";
    private int length=0;

    public RelationPath(String path)
    {
        this.path = path;
        this.length = path.split("<").length;
    }
    
    public String getPath() 
    {
        return path;
    }

    public void setPath(String path) 
    {
        this.path = path;
    }

    public int getLength() 
    {
        return length;
    }

    public void setLength(int length) 
    {
        this.length = length;
    }   
}
