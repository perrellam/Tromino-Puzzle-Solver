package tromino;

/**
 * 
 * Main class that instantiates a new controller and runs application.
 * 
 * @author Mason Perrella
 * @version 1.0 (November 2016)
 * 
 */

public class MVC
{

    private Controller myController;

    public static void main(String[] args)
    {
        new MVC();

    }

    public MVC()
    {
        setController(new Controller());
    }

    public void setController(Controller controller)
    {
        myController = controller;
    }

    public Controller getController()
    {
        return myController;
    }

}
