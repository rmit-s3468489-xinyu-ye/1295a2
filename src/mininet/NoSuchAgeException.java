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
public class NoSuchAgeException extends Exception
{
    public NoSuchAgeException()
    {
        super("No such age, a valid age must be between 0 and 150");
    }
}
