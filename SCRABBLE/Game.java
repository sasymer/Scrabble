import java.util.*;

public class Game //Scrabble
{
  private GridDisplay display;
  private Location heroLoc;
  private String heroImage;
  private Location[][] locations;
  public static final String LETTERS = "AAAAAAAABBCDDDDDEEEEEEEFFGGGHHIIIIIJKKKLLLLLMMMNNNNNNOOOOOPPQRRRRRRRRSSSSSSSSTTTTTTTTUUUVVXYZ";
  private ArrayList<Tile> unused;
  private ArrayList<Tile> player1;
  private ArrayList<Tile> player2;
  private Location first;
  
  public Game()
  {
    //create GridDisplay with 3 rows and 5 columns, and title "Game"
    display = new GridDisplay(16, 15);
    display.setTitle("Game");
 
    unused = new ArrayList<Tile>(); 
    int i = 0;
    while (i < 8)
    {
      unused.add(i, new Tile("A.jpg"));
      i++;
    }
    while (i<10)
    {
      unused.add(i, new Tile("B.jpg"));
      i++;
    }
    unused.add(i, new Tile("C.jpg"));
    i++;
    
    while (i < 16)
    {
      unused.add(i, new Tile("D.jpg"));
      i++;
    }
    
    while (i < 23)
    {
      unused.add(i, new Tile("E.jpg"));
      i++;
    }
    
    while (i < 25)
    {
      unused.add(i, new Tile("F.jpg"));
      i++;
    }
    
    while (i < 28)
    {
      unused.add(i, new Tile("G.jpg"));
      i++;
    }
    
    while (i < 30)
    {
      unused.add(i, new Tile("H.jpg"));
      i++;
    }
    
    while (i < 35)
    {
      unused.add(i, new Tile("I.jpg"));
      i++;
    }
    
    unused.add(i, new Tile("J.jpg"));
    i++;
    
    while (i < 39)
    {
      unused.add(i, new Tile("K.jpg"));
      i++;
    }
    
    while (i < 44)
    {
      unused.add(i, new Tile("L.jpg"));
      i++;
    }
    
    while(i < 47)
    {
      unused.add(i, new Tile("M.jpg"));
      i++;
    }
    
    while (i < 53)
    {
      unused.add(i, new Tile("N.jpg"));
      i++;
    }
    
    while (i < 58)
    {
      unused.add(i, new Tile("O.jpg"));
      i++;
    }
    
    while (i < 60)
    {
      unused.add(i, new Tile("P.jpg"));
      i++;
    }
    
    unused.add(i, new Tile("Q.jpg"));
    i++;
      
    while (i < 69)
    {
      unused.add(i, new Tile("R.jpg"));
      i++;
    }
    
    while (i < 77)
    {
      unused.add(i, new Tile("S.jpg"));
      i++;
    }
    
    while (i < 85)
    {
      unused.add(i, new Tile("T.jpg"));
      i++;
    }
    
    while (i < 88)
    {
      unused.add(i, new Tile("U.jpg"));
      i++;
    }
    
    while (i < 90)
    {
      unused.add(i, new Tile("V.jpg"));
      i++;
    }
    
    unused.add(i, new Tile("W.jpg"));
    i++;
    unused.add(i, new Tile("X.jpg"));
    i++;
    unused.add(i, new Tile("Y.jpg"));
    i++;
    unused.add(i, new Tile("Z.jpg"));
    
    
    locations = new Location[16][15];
    for (int row = 0; row < locations.length; row++)
    {
      for (int col = 0; col < locations[0].length; col++)
      {
        Location loc = new Location(row, col);
        locations[row][col] = loc;
      }
    }
    
    for (int row = 0; row < locations.length; row++)
    {
      for (int col = 0; col < locations[0].length; col++)
      {
        display.setImage(locations[row][col], "blank.jpg");
      }
    }
    
    for (int row = 15; row < locations.length; row++)
    {
      for (int col = 0; col < locations[15].length; col++)
      {
        display.setImage(locations[row][col], "black.jpg");
      }
    }
    
    display.setImage(locations[0][0], "tripleword.jpg");
    display.setImage(locations[7][0], "tripleword.jpg");
    display.setImage(locations[14][0], "tripleword.jpg");
    display.setImage(locations[0][7], "tripleword.jpg");
    display.setImage(locations[0][14], "tripleword.jpg");
    display.setImage(locations[7][7], "derpystar.jpg");
    display.setImage(locations[7][14], "tripleword.jpg");
    display.setImage(locations[14][14], "tripleword.jpg");
    display.setImage(locations[14][7], "tripleword.jpg");
    
    display.setImage(locations[3][0], "doubleletter.jpg");
    display.setImage(locations[11][0], "doubleletter.jpg");
    display.setImage(locations[0][3], "doubleletter.jpg");
    display.setImage(locations[0][11], "doubleletter.jpg");
    display.setImage(locations[6][2], "doubleletter.jpg");
    display.setImage(locations[7][3], "doubleletter.jpg");
    display.setImage(locations[8][2], "doubleletter.jpg");
    display.setImage(locations[14][3], "doubleletter.jpg");
    display.setImage(locations[2][6], "doubleletter.jpg");
    display.setImage(locations[6][6], "doubleletter.jpg");
    display.setImage(locations[8][6], "doubleletter.jpg");
    display.setImage(locations[12][6], "doubleletter.jpg");
    display.setImage(locations[3][7], "doubleletter.jpg");
    display.setImage(locations[11][7], "doubleletter.jpg");
    display.setImage(locations[2][8], "doubleletter.jpg");
    display.setImage(locations[6][8], "doubleletter.jpg");
    display.setImage(locations[8][8], "doubleletter.jpg");
    display.setImage(locations[12][8], "doubleletter.jpg");
    display.setImage(locations[0][11], "doubleletter.jpg");
    display.setImage(locations[7][11], "doubleletter.jpg");
    display.setImage(locations[14][11], "doubleletter.jpg");
    display.setImage(locations[6][12], "doubleletter.jpg");
    display.setImage(locations[8][12], "doubleletter.jpg");
    display.setImage(locations[3][14], "doubleletter.jpg");
    display.setImage(locations[11][14], "doubleletter.jpg");
    
    display.setImage(locations[1][1], "doubleword.jpg");
    display.setImage(locations[2][2], "doubleword.jpg");
    display.setImage(locations[3][3], "doubleword.jpg");
    display.setImage(locations[4][4], "doubleword.jpg");
    display.setImage(locations[10][10], "doubleword.jpg");
    display.setImage(locations[11][11], "doubleword.jpg");
    display.setImage(locations[12][12], "doubleword.jpg");
    display.setImage(locations[13][13], "doubleword.jpg");
    display.setImage(locations[1][13], "doubleword.jpg");
    display.setImage(locations[2][12], "doubleword.jpg");
    display.setImage(locations[3][11], "doubleword.jpg");
    display.setImage(locations[4][10], "doubleword.jpg");
    display.setImage(locations[10][4], "doubleword.jpg");
    display.setImage(locations[11][3], "doubleword.jpg");
    display.setImage(locations[12][2], "doubleword.jpg");
    display.setImage(locations[13][1], "doubleword.jpg");
    
    display.setImage(locations[1][5], "tripleletter.jpg");
    display.setImage(locations[1][9], "tripleletter.jpg");
    display.setImage(locations[5][1], "tripleletter.jpg");
    display.setImage(locations[5][5], "tripleletter.jpg");
    display.setImage(locations[5][9], "tripleletter.jpg");
    display.setImage(locations[5][13], "tripleletter.jpg");
    display.setImage(locations[9][1], "tripleletter.jpg");
    display.setImage(locations[9][5], "tripleletter.jpg");
    display.setImage(locations[9][9], "tripleletter.jpg");
    display.setImage(locations[9][13], "tripleletter.jpg");
    display.setImage(locations[13][10], "tripleletter.jpg");
    display.setImage(locations[13][10], "tripleletter.jpg");
    
   setLetters();


  }
  
  public void play()
  {
    while (true)
    {
      //wait 100 milliseconds for mouse or keyboard
      display.pause(100);
      
      //check if any locations clicked or keys pressed
      Location clickLoc = display.checkLastLocationClicked();
      int key = display.checkLastKeyPressed();
      
      if (clickLoc != null)
      {
        //a location was clicked
        locationClicked(clickLoc);
      }
      
      if (key != -1)
      {
        //a key was pressed
        keyPressed(key);
      }
      
      step();
    }
  }

  //
  //
  ///
  ///
  //
  //
  //
  public void setLetters()
  {
    int col = 4;
    while (col < 11 && unused.size() > 0)
    {
      int i = (int) (Math.random() * unused.size());
      display.setImage(locations[15][col], unused.get(i).getImage());
      unused.remove(i);
      col++;
    }
    
  }
  //
  //
  //
  ///
  ///
  //
  ///
  
  
  //called when the user clicks on a location.
  //that location is passed to this method.
  private void locationClicked(Location loc)
  {
    boolean match = false;
    if (first != null)
    {
      System.out.println("second click:  " + loc);
      //second click
     
      if (display.getImage(loc) != null && display.getImage(loc).equals(display.getImage(first)))
      {
        display.setImage(loc, null);
        display.setImage(first, null);
        matches = matches + 1;
        if (matches == 12)
        {
          display.showMessageDialog("turns =  " + turn);
          GridDisplay x = new GridDisplay("win.jpg");
        //  x.setImage(new Location(0,0), 
        }
      }
     
      else
      {
        System.out.println("mismatch");
        display.pause(1000);
        match = false;
        turnDown(loc);
        turnDown(first);
        
      }
      first = null;
      turn = turn + 1;
      System.out.println("turn:  " + turn);
    }
    else
    {
      System.out.println("first click:  " + loc);
      //first click
      turnUp(loc);
      first = loc;
    }



//change color at loc
    display.setColor(loc, new Color(255, 0, 0));
  }
  
  //called when the user presses a key.
  //each key on the keyboard has a unique key code.
  //that key code is passed to this method.
  private void keyPressed(int key)
  {
    //print key code
    System.out.println("key code:  " + key);
        
    if (key == 32)
    {
      //space key was pressed

      //pick new location for hero
      int col = heroLoc.getCol();
      Location newLoc;
      if (col == 4)
        newLoc = new Location(2, 0);  //bottom-left cell
      else
        newLoc = new Location(2, col + 1);  //next cell to right
      
      //erase image at old location
      display.setImage(heroLoc, null);
      
      //remember new location
      heroLoc = newLoc;
      
      //show image at new location
      display.setImage(heroLoc, heroImage);
    }
    else
    {
      //some other key was pressed
      
      //show help message
      display.showMessageDialog("Press SPACE to move.\nClick to change color.");
    }
  }
  
  //this method is called at regular intervals
  public void step()
  {
    //maybe change color of random location to black
    if (Math.random() < 0.1)
    {
      int row = (int)(Math.random() * 3);
      int col = (int)(Math.random() * 5);
      display.setColor(new Location(row, col), new Color(0, 0, 0));
    }
  }
  
  //this code starts a game when you click the run button
  public static void main(String[] args)
  {
    Game g = new Game();
    g.play();
  }
}
