package mininet;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public class NoParentException extends Exception
{
    public NoParentException()
    {
        super("A dependent cannot have no parents or only one parent !");
    }
}
