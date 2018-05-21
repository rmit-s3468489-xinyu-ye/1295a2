package mininet;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public class NotToBeColleaguesException extends Exception
{
    public NotToBeColleaguesException()
    {
        super("A child cannot be in the colleague relation !");
    }
}
