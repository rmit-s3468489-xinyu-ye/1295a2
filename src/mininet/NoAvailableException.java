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
public class NoAvailableException extends Exception
{
    public NoAvailableException()
    {
        super("One or more persons are already spouse of others");
    }
}
