package tromino;

public class Controller
{
    private View myView;
    private Tromino myTromino;

    /**
     * Controller constructor; view must be passed in since controller has
     * responsibility to notify view when some event takes place.
     */
    public Controller()
    {
        myView = new View(this);
        
    }

    public void reset()
    {
    	myView = new View(this);

    }

    public void solve()
    {
        
    
        myTromino = new Tromino(myView.getGridSize(), myView.getX(), myView.getY());
       myTromino.tile();
      int[][] tiledGrid = myTromino.getGrid();
      for (int i=0; i<tiledGrid.length; i++) {
			for (int j=0; j<tiledGrid.length; j++) {
				
				if(tiledGrid[i][j] != -1)
				{
					myView.changeImage(i, j);
				}
			}
				
		}
      

    }
    public int[][] getGrid()
    {
    	return myTromino.getGrid();
    }
   
    
}
