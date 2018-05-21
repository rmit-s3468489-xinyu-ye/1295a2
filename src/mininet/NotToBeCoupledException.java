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
                + " failed to coupling them !");
    }
}
