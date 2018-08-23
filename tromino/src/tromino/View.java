
package tromino;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class View extends Frame{

	public int myNumColumns = 4;
	public int myNumRows = 4;
	private Can[][] myGrid;
	private Panel myColumnsPanel;
	private Controller myController;
	private Image myBlankImage;
	private Image myBlackImage;
	private Image myBlueImage;
	private Image myGreenImage;
	private Image myPurpleImage;
	private Image myOrangeImage;
	private Image myYellowImage;
	private Image myPinkImage;
	private Image myRedImage;
	private Image myCyanImage;
	private Image myBrownImage;
	private Image myDRedImage;

	private ButtonListener myResetListener;
    private ButtonListener mySolveListener;
    private Button myResetButton;
    private Button mySolveButton;
    private JLabel myTitle;
    private int mySize;
    private int myX;
    private int myY;

    



	/**
	 * View constructor used to lay out the view
	 *
	 * <pre>
	 * pre:  none
	 * post: the view is set up and initialized
	 * </pre>
	 */
	public View(Controller controller) {
		myController = controller;
		// set size and color scheme for frame
		this.setSize(800, 800);
		this.setLayout(null);
		this.setBackground(Color.darkGray);	
		this.associateListeners(controller);
		
		// instantiate board image
		myBlankImage = Toolkit.getDefaultToolkit().getImage("images/white.png");
		myBlackImage = Toolkit.getDefaultToolkit().getImage("images/blacks.png");
		myRedImage = Toolkit.getDefaultToolkit().getImage("images/red2.png");
		myBlueImage = Toolkit.getDefaultToolkit().getImage("images/blue2.jpg");
		myGreenImage = Toolkit.getDefaultToolkit().getImage("images/green.png");
		myPurpleImage = Toolkit.getDefaultToolkit().getImage("images/purp.png");
		myOrangeImage = Toolkit.getDefaultToolkit().getImage("images/orange.png");
		myYellowImage = Toolkit.getDefaultToolkit().getImage("images/yellow.jpg");
		myPinkImage = Toolkit.getDefaultToolkit().getImage("images/pink2.png");
		myCyanImage = Toolkit.getDefaultToolkit().getImage("images/cyan.png");
		myBrownImage = Toolkit.getDefaultToolkit().getImage("images/brown.jpg");
		myDRedImage = Toolkit.getDefaultToolkit().getImage("images/red3.JPG");
	
		
		myTitle = new JLabel("TROMINO PUZLLE SOLVER");
        myTitle.setLocation(200, 5);
        myTitle.setFont(new Font("Serif", Font.BOLD, 30));
        myTitle.setSize(500, 200);
        myTitle.setForeground(Color.red);
        
		getInfo();
		myColumnsPanel = new Panel();
		initBoard();

		 	myResetButton = new Button("New Tromino");
	        myResetButton.setSize(100, 35);
	        myResetButton.setLocation(700, 765);

	        mySolveButton = new Button("Solve");
	        mySolveButton.setSize(100, 35);
	        mySolveButton.setLocation(0, 765);
	        
		
		myResetButton.addMouseListener(myResetListener);
        mySolveButton.addMouseListener(mySolveListener);
        
        
        
        
		this.add(myColumnsPanel);
		this.add(myResetButton);
		this.add(mySolveButton);
		this.add(myTitle);
		this.setVisible(true);
		this.addWindowListener(new AWindowListener());
        

	}
	public void associateListeners(Controller controller)
    {
        Class<? extends Controller> controllerClass;
        Method resetMethod, solveMethod;

        controllerClass = controller.getClass();

        resetMethod = null;
        solveMethod = null;

        try
        {
            resetMethod = controllerClass.getMethod("reset", (Class<?>[]) null);
            solveMethod = controllerClass.getMethod("solve", (Class<?>[]) null);
        } catch (NoSuchMethodException exception)
        {
            String error;

            error = exception.toString();
            System.out.println(error);
        } catch (SecurityException exception)
        {
            String error;

            error = exception.toString();
            System.out.println(error);
        }

        myResetListener = new ButtonListener(controller, resetMethod, null);
        mySolveListener = new ButtonListener(controller, solveMethod, null);
    }

    /**
     * Updates Labels with the String text.
     *
     * @param text
     *            the text string to use in updating the text field
     */
	public void changeImage(int col, int row) {

		Image[] colors = new Image[10];
		int[][] tilegrid = adjustGrid(myController.getGrid());

		colors[0] = myRedImage;
		colors[1] = myBlueImage;
		colors[2] = myGreenImage;
		colors[3] = myPurpleImage;
		colors[4] = myOrangeImage;
		colors[5] = myCyanImage;
		colors[6] = myBrownImage;
		colors[7] = myPinkImage;
		colors[8] = myYellowImage;
		colors[9] = myDRedImage;
		
		
		myGrid[row][col].setImage(colors[tilegrid[col][row]]);
		

	}

	public void initBoard() //need getInfo before use
	{
		
	
		
		myColumnsPanel.setLayout(new GridLayout(mySize, mySize));
		myColumnsPanel.setSize(600, 500);
		myColumnsPanel.setLocation(100, 150);
		
		// Add blank images to array of Squares
	      myGrid = new Can[mySize][mySize];
	 		for (int i = 0; i < mySize; i++) {
	 			for (int j = 0; j < mySize; j++) {
	 				if(i == myY && j == myX)
	 				{
	 					myGrid[i][j] =new Can(myBlackImage);
	 					myColumnsPanel.add(myGrid[i][j]);

	 				}else
	 				myGrid[i][j] = new Can(myBlankImage);
	 				myColumnsPanel.add(myGrid[i][j]);


	 			}
	 		}
	 		
	}
	public void getInfo()
	{
		        mySize = Integer.parseInt(JOptionPane.showInputDialog("Input a Size for your Tromino Board\n Choices:2(2x2), 4(4x4), 8(8x8), 16(16x16), 64(64x64)"));
		        myX = Integer.parseInt(JOptionPane.showInputDialog("Input X-Coordinate for the blank space \n ->(0,0) is upper left block<-\n\nEx:\n    0    1    2    3    4\n0  -    -    -    -    - \n1  -    -    -    -    - \n2  -    -    -    -    - \n3  -    -    -    -    - \n4  -    -    -    -    -"));
				myY = Integer.parseInt(JOptionPane.showInputDialog("Input Y-Coordinate for the blank space\n ->(0,0) is upper left block<-\n\nEx:\n    0    1    2    3    4\n0  -    -    -    -    - \n1  -    -    -    -    - \n2  -    -    -    -    - \n3  -    -    -    -    - \n4  -    -    -    -    -"));
				
	}
	 public int getGridSize() {
		   return mySize;
	   }
	   public int getX() {
		   return myX;
	   }
	   public int getY() {
		   return myY;
	   }
	   
	   public int[][] adjustGrid(int[][] oldGrid)
	   {
		   int[][] newGrid;
		   newGrid = oldGrid;
		   
		   for (int i = 0; i < mySize; i++) {
	 			for (int j = 0; j < mySize; j++) {
	 				if(newGrid[i][j] > 9)
	 				{
	 					newGrid[i][j] = newGrid[i][j]%10;
	 				
	 				}

	 			}
	 		}
	 		
		return newGrid;
	   }
	  
   
}
