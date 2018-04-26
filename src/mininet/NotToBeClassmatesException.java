/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mininet;

/**
 *
 * @author Yifan ZHANG s3615625
 */
public class NotToBeClassmatesException extends Exception
{
    public NotToBeClassmatesException()
    {
        super("This child is too yound "
                + "to be classmates of others !");
    }
}
