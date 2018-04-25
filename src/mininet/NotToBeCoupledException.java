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
public class NotToBeCoupledException extends Exception
{
    public NotToBeCoupledException()
    {
        super("At leaste one person is not adult,"
                + " failed to coupling them");
    }
}
