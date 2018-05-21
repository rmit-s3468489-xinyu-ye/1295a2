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
