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
public class TooYoungException extends Exception 
{
    public TooYoungException()
      {
          super("The selected kid is too yound to make friends");
      }
    
}
