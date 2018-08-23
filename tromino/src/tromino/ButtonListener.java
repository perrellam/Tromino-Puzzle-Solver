package tromino;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ButtonListener extends MouseAdapter
{
	//properties
    
    
    private Object myController;
    private Method myMethod;
    private Object[] myArguments;
    
   //methods
    /**
     * Only constructor, giving the method that should be invoked
     * when the mouse is released over the button; has the 
     * responsibility of notifying controller when mouse released.
     *
     * <pre>
     * pre:  controller and method passed in are valid
     * post: myController and myMethod are set
     * </pre>
     *
     * @param controller the controller this listener has a
     *        responsible to
     * @param method the method to invoke when button is pushed
     * @param args the array of objects that are the parameters
     *        for the method that will be invoked by the controller
     */
    public ButtonListener(Object controller, Method method, Object[] args)
    {
        myController = controller;
        myMethod = method;
        myArguments = args;
    }

    /**
     * Invokes the appropriate method when the mouse
     * button is released.
     *
     * <pre>
     * pre:  a valid MouseEvent has taken place, and the controller
     *       and method this listener is responsible to and how, are
     *       set to value values
     * post: the associated method is invoked
     * </pre>
     *
     * @param event a mouse event
     */
    public void mouseReleased(MouseEvent event)
    {
        Method method;
        Object controller;
        Object[] arguments;
    
        method = this.getMethod();
        controller = this.getController();
        arguments = this.getArguments();
        
        try
        {
            method.invoke(controller, arguments);
        }
        catch(InvocationTargetException exception)
        {
        	exception.printStackTrace();
            System.out.println("InvocationTargetException");
        }
        catch(IllegalAccessException exception)
        {
            System.out.println("IllegalAccessException");
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println("IllegalArgumentException");
        }
    }
  
    //////////////////////////
    //   Accessor Methods   //
    //////////////////////////

    protected Method getMethod()
    {
        return myMethod;
    }

    protected void setMethod(Method method)
    {
        myMethod = method;
    }

    protected Object getController()
    {
        return myController;
    }
      
    protected void setController(Object controller)
    {
        myController = controller;
    }
    
    protected Object[] getArguments()
    {
        return myArguments;
    }
    
    protected void setArguments(Object[] arguments)
    {
        myArguments = arguments;
    }

}
