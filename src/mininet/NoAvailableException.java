package mininet;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public class NoAvailableException extends Exception
{
    public NoAvailableException()
    {
        super("One or more persons are already spouse of others !");
    }
}
