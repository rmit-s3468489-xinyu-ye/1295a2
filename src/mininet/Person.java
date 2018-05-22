package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public abstract class Person 
{
    private String name, photoPath, status, state;
    private char gender;
    private int age;
    
    protected Person(String name, String photoPath, String status,
            char gender, int age, String state)
    {
        this.name = name;
        this.photoPath = photoPath;
        this.status = status;
        this.gender = gender;
        this.age = age;
        this.state = state;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    public String getPhotoPath() 
    {
        return photoPath;
    }
    
    public void setPhotoPath(String photoPath) 
    {
        this.photoPath = photoPath;
    }
    
    public String getStatus() 
    {
        return status;
    }
    
    public void setStatus(String status) 
    {
        this.status = status;
    }
    
    public char getGender()
    {
        return gender;
    }
    
    public void setGender(char gender)
    {
        this.gender = gender;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getState()
    {
        return state;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }
    
    /**
     * Method to compare the names of persons
     * in the MiniNet alphabetically
     */
    public static Comparator<Person> nameComparator = new Comparator<Person>() 
    {
        @Override
        public int compare(Person p1, Person p2) 
        {
            return (p1.getName().compareTo(p2.getName()));
        }
    };
}
