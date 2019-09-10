// placing just one letter
// check with dictionary
// score
// switch turns
// send letters back to tray when invalid
// first word

import java.io.*;
import java.util.*;

public class SCRABBLEGame
{
  private GridDisplay display;  //contains file names of tiles to show
  private String[][] board;  //these are the original images (before letters placed)
  public static final String LETTERS = "AAAAAAAABBCDDDDDEEEEEEEFFGGGHHIIIIIJKKKLLLLLMMMNNNNNNOOOOOPPQRRRRRRRRSSSSSSSSTTTTTTTTUUUVVXYZ";
  private ArrayList<Tile> unused;
  private ArrayList<Tile> allTiles;  //need???
  private ArrayList<Tile> player1; //never more than 7
  private ArrayList<Tile> player2; //never more than 7
  private ArrayList<Location> placed;
  private ArrayList<Location> prevPlaced;  //all locations that used to have tiles
  private ArrayList<Location> word;  //locations of new word (includes placed and prevPlaced tiles)
  private Location first; //first location clicked
  private int turn;
  private ArrayList<String> dictionary;
  private int score1;
  private int score2;
  
  public SCRABBLEGame()
  {
    loadDictionary();
    
    score1 = 0; score2 = 0;
    //create GridDisplay with 3 rows and 5 columns, and title "Game"
    display = new GridDisplay(16, 15); display.setTitle("Game | Turn: 1 | Player1 | Score: 0");
    first = null;
    turn = 1;
    
    placed = new ArrayList<Location>();
    prevPlaced = new ArrayList<Location>();
    word = new ArrayList<Location>();
    
    allTiles = new ArrayList<Tile>(); 
    int i = 0;
    while (i < 8)
    {
      allTiles.add(i, new Tile("A.jpg"));
      i++;
    }
    while (i<10)
    {
      allTiles.add(i, new Tile("B.jpg"));
      i++;
    }
    allTiles.add(i, new Tile("C.jpg"));
    i++;
    while (i < 16)
    {
      allTiles.add(i, new Tile("D.jpg"));
      i++;
    }
    while (i < 23)
    {
      allTiles.add(i, new Tile("E.jpg"));
      i++;
    }
    while (i < 25)
    {
      allTiles.add(i, new Tile("F.jpg"));
      i++;
    }
    
    while (i < 28)
    {
      allTiles.add(i, new Tile("G.jpg"));
      i++;
    }
    
    while (i < 30)
    {
      allTiles.add(i, new Tile("H.jpg"));
      i++;
    }
    
    while (i < 35)
    {
      allTiles.add(i, new Tile("I.jpg"));
      i++;
    }
    
    allTiles.add(i, new Tile("J.jpg"));
    i++;
    
    while (i < 39)
    {
      allTiles.add(i, new Tile("K.jpg"));
      i++;
    }
    
    while (i < 44)
    {
      allTiles.add(i, new Tile("L.jpg"));
      i++;
    }
    
    while(i < 47)
    {
      allTiles.add(i, new Tile("M.jpg"));
      i++;
    }
    
    while (i < 53)
    {
      allTiles.add(i, new Tile("N.jpg"));
      i++;
    }
    
    while (i < 58)
    {
      allTiles.add(i, new Tile("O.jpg"));
      i++;
    }
    
    while (i < 60)
    {
      allTiles.add(i, new Tile("P.jpg"));
      i++;
    }
    
    allTiles.add(i, new Tile("Q.jpg"));
    i++;
    
    while (i < 69)
    {
      allTiles.add(i, new Tile("R.jpg"));
      i++;
    }
    
    while (i < 77)
    {
      allTiles.add(i, new Tile("S.jpg"));
      i++;
    }
    
    while (i < 85)
    {
      allTiles.add(i, new Tile("T.jpg"));
      i++;
    }
    
    while (i < 88)
    {
      allTiles.add(i, new Tile("U.jpg"));
      i++;
    }
    
    while (i < 90)
    {
      allTiles.add(i, new Tile("V.jpg"));
      i++;
    }
    
    allTiles.add(i, new Tile("W.jpg"));
    i++;
    allTiles.add(i, new Tile("X.jpg"));
    i++;
    allTiles.add(i, new Tile("Y.jpg"));
    i++;
    allTiles.add(i, new Tile("Z.jpg"));
    
    unused = new ArrayList<Tile>();
    for (int k = 0; k < allTiles.size(); k++)
    {
      unused.add(allTiles.get(k));
    }
    
    for (int row = 0; row < 16; row++)
    {
      for (int col = 0; col < 15; col++)
      {
        display.setImage(new Location(row, col), "blank.jpg");
      }
    }
    
    for (int col = 0; col < 15; col++)
    {
      display.setImage(new Location(15, col), "black.jpg");
    }
    
    for (int row = 0; row < 15; row++)
    {
      for (int col = 0; col < 15; col++)
      {
        display.setImage(new Location(row, col), "blank.jpg");
      }
    }
    
    display.setImage(new Location(0, 0), "tripleword.jpg");
    display.setImage(new Location(0, 3), "doubleletter.jpg");
    display.setImage(new Location(0, 7), "tripleword.jpg"); 
    for (int j = 1; j <= 4; j++)
      display.setImage(new Location(j, j), "doubleword.jpg");    
    display.setImage(new Location(1, 5), "tripleletter.jpg");    
    display.setImage(new Location(2, 6), "doubleletter.jpg");
    display.setImage(new Location(3, 0), "doubleletter.jpg");    
    display.setImage(new Location(3, 7), "doubleletter.jpg");    
    display.setImage(new Location(5, 1), "tripleletter.jpg");
    display.setImage(new Location(5, 5), "tripleletter.jpg");
    display.setImage(new Location(6, 2), "doubleletter.jpg");    
    display.setImage(new Location(6, 6), "doubleletter.jpg");    
    display.setImage(new Location(7, 0), "tripleword.jpg");
    display.setImage(new Location(7, 3), "doubleletter.jpg");    
    display.setImage(new Location(7, 7), "derpystar.jpg");    
    
    for (int r = 0; r < 7; r++)
    {
      for (int c = 0; c < 7; c++)
      {
        String image = display.getImage(new Location(r, c));
        display.setImage(new Location(14 - r, c), image);
        display.setImage(new Location(r, 14 - c), image);
        display.setImage(new Location(14 - r, 14 - c), image);
      }
    }
    
    display.setImage(new Location(7,11), "doubleletter.jpg");
    display.setImage(new Location(11,7), "doubleletter.jpg");
    
    board = new String[16][15];
    for (int r = 0; r < 16; r++)
    {
      for (int c = 0; c < 15; c++)
        board[r][c] = display.getImage(new Location(r, c));
    }
    
    
    player1 = new ArrayList<Tile>();
    player2 = new ArrayList<Tile>();
    
    setLetters();
    display.showMessageDialog("Click a letter tile and then the board to place tile. \nPress RETURN to enter word. \nPress SPACE to shuffle letters, ending your turn.");
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
 
  public void setLetters()
  {
    int col = 4;
    while (col < 11 && unused.size() > 0)
    {
      int i = (int) (Math.random() * unused.size());
      display.setImage(new Location(15, col), unused.get(i).getImage());
      player1.add(unused.get(i));
      unused.remove(i);
      col++;
    }
    
    int num = 0;
    while (num < 7)
    {
      int k = (int) (Math.random() * unused.size());
      player2.add(unused.get(k));
      unused.remove(k);
      num++;
    }
  }
  
  //set new Tiles after turn, howmany ever you placed
  public void placeLetters(ArrayList<Tile> player)
  {
    int col = 4;
    int i = 0;
    while (col < 11 && i < 7)
    {
      display.setImage(new Location(15, col), player.get(i).getImage());
      col++;
      i++;
    }
  }
  
  private boolean isValid(Location loc)
  {
    boolean valid = true;
    if (loc.getRow() >= 0 && loc.getRow() < 16 && loc.getCol() >= 0 && loc.getCol() < 15)
    {
      if (loc != null)
      {
        for (int i = 0; i < allTiles.size(); i++)
        {
          if (display.getImage(loc).equals(allTiles.get(i)))
            valid = false;
        }
      }
    }
    else
      valid = false;
    return valid;
  }
  
  private boolean isTile(Location loc)
  {
    boolean tile = false;
    if (loc != null)
    {
      for (int i = 0; i < allTiles.size(); i++)
      {
        if (display.getImage(loc).equals(allTiles.get(i).getImage()))
          tile = true;
      }
    }
    return tile;
  }
  
  public static int indexOfMin(ArrayList<Integer> a, int startIndex)
  {
    int smallest = a.get(startIndex);
    int smallindex = startIndex;
    
    for (int i = startIndex; i < a.size(); i++)
    {
      if (a.get(i) < smallest)
      {
        smallest = a.get(i);
        smallindex = i;
      }
    }
    return smallindex;
  }
  
  public static boolean inList(ArrayList<Location> a, Location loc)
  {
    boolean value = false;
    for (int i = 0; i < a.size(); i++)
    {
      if (a.get(i).equals(loc))
        value = true;
    }
    return value;
  }
  
  public int indexInList(ArrayList<Location> a, Location loc)
  {
    for (int i = 0; i < a.size(); i++)
    {
      if (a.get(i).equals(loc))
        return i;
    }
    return -1;
  }
  
  public boolean inPrevPlaced(Location loc)
  {
    for (int i = 0; i < prevPlaced.size(); i++)
    {
      if (prevPlaced.get(i).equals(loc))
        return true;
    }
    return false;
  }
  
  public int getScore(ArrayList<Location> w)
  {
    int score = 0;
    for (int i = 0; i < w.size(); i++) // GET SCORE
    {
      Location loc = new Location(w.get(i).getRow(), w.get(i).getCol());
      Tile t = new Tile(display.getImage(loc));  
      if (board[loc.getRow()][loc.getCol()].equals("doubleletter.jpg"))
        score += t.getScore() * 2;
      else if(board[loc.getRow()][loc.getCol()].equals("tripleletter.jpg"))
        score += t.getScore() * 3;
      else
        score += t.getScore(); //adds tile letter score to score
    }
    for (int i = 0; i < w.size(); i++)
    {
      Location loc = new Location(w.get(i).getRow(), w.get(i).getCol());
      if (board[loc.getRow()][loc.getCol()].equals("doubleword.jpg"))
        score *= 2;
      if (board[loc.getRow()][loc.getCol()].equals("tripleword.jpg"))
        score *= 3;
    }
    return score;
  }
  
  public ArrayList<String> getAllWords()
  {
    System.out.println("GETALLWORDS");
    System.out.println("placed:  " + placed);
    
    ArrayList<String> words = new ArrayList<String>();
    boolean allRealWords = true;
    int score = 0;
    
    if (placed.size() > 0)
    {
      ArrayList<Location> mainword = wORD();
      System.out.println("RETURN FROM wORD:  " + placed);
    System.out.println("RETURN FROM mainword:  " + mainword);
    String main = "";
    for (int i = 0; i < mainword.size(); i++)
    {
      String letter = display.getImage(mainword.get(i));
      letter = letter.substring(0, letter.indexOf("."));
      main += letter;
    }
    
    System.out.println("MAINWORD: " + main);
    
    boolean onDerpyS = true;
    /////// check if theres something on derpy star
    if (turn == 1)
    {
      //must be a letter on star
      if (!inList(placed, new Location(7,7)))  
      {
        System.out.println("not word");
        onDerpyS = false;
      }
    }
    
    if (turn == 1 && onDerpyS == false)
      allRealWords = false;
    
    if (inDictionary(main))
    {
      words.add(main);
      score += getScore(mainword);
    }
    else
      allRealWords = false;
    
    boolean horiz = true;
    boolean vert = true;
    
    int row = placed.get(0).getRow(); //row of first placed
    int col = placed.get(0).getCol(); //col of first placed
    
    //checks if horiz or vert
    for (int i = 0; i < placed.size(); i++)
    {
      if (row != placed.get(i).getRow())
        horiz = false;
      else if (col != placed.get(i).getCol())
        vert = false;
    }
    
    if (placed.size() == 1)
    {
      Location loc = placed.get(0);
      int r = loc.getRow(); int c = loc.getCol();
     
      //CHANGE THIS MAYBE IF NECESSARY
      if (isValid(new Location(r + 1, c)) && isValid(new Location(r -1 , c)))
      {
        if (isTile(new Location(r+1, c)) || isTile(new Location(r-1, c)))
          horiz = false;
      }
      else
        allRealWords = false;
      if (isValid(new Location(r, c+1)) && isValid(new Location(r, c-1)))
      {
        if (isTile(new Location(r, c+1)) || isTile(new Location(r, c-1)))
          vert = false;  
      }
      else
        allRealWords = false;
    }
    
    for (int i = 0; i < placed.size(); i++)
    {
      if (horiz) //horiz
      {
        ArrayList<Location> word = getVWord(placed.get(i));
        String vword = "";
        for (int k = 0; k < word.size(); k++)
        {
          String letter = display.getImage(word.get(k));
          letter = letter.substring(0, letter.indexOf("."));
          vword += letter;
        }
        
        if (vword.length() > 1 && main.length() > 2 && inDictionary(vword))
        {
          words.add(vword);
          score += getScore(word);
        }
        else if (vword.length() == 1)
        {
        }
        else
          allRealWords = false;
        System.out.println("VWORD: " + vword);
        
      }
      else if (vert) //vert
      {
        ArrayList<Location> word = getHWord(placed.get(i));
        String hword = "";
        for (int j = 0; j < word.size(); j++)
        {
          String letter = display.getImage(word.get(j));
          letter = letter.substring(0, letter.indexOf("."));
          hword += letter;
        }
        if (hword.length() > 1 && main.length() > 2 && inDictionary(hword))
        {
          words.add(hword);
          score += getScore(word);
        }
        else if (hword.length() == 1)
        {
        }
        else
          allRealWords = false;
        
        System.out.println("HWORD: " + hword);
      }
      else
      {
      }
    }
    if (allRealWords == false)
      putTilesBack(placed);
    }
    else
      allRealWords = false;
    
    if (turn % 2 ==1)
      score1 += score;
    else
      score2 += score;
    System.out.println("words: " + words);
    return words;
  }
  
  public ArrayList<Location> getHWord(Location loc)
  {
    ArrayList<Location> word = new ArrayList<Location>();
    int colbefore = loc.getCol() - 1;
    int row = loc.getRow(); 
    while (colbefore >= 0 && isTile(new Location(row, colbefore)))
    {
      word.add(0, new Location(row, colbefore));
      colbefore--;
    }
    word.add(loc);
    int colafter = loc.getCol() + 1;
    while (colafter < 15 && isTile(new Location(row, colafter)))
    {
      word.add(new Location(row, colafter));
      colafter++;
    }
    
    String hWord = "";
    for (int i = 0; i < word.size(); i++)
    {
      String image = display.getImage(word.get(i));
      String letter = image.substring(0, image.indexOf("."));
      hWord += letter;
    }
    
    System.out.println(hWord);
    return word;
  }
  //TO DO: for each placed, if horiz get its vert. if vert get its horiz
  
  
  public ArrayList<Location> getVWord(Location loc)
  {
    ArrayList<Location> word = new ArrayList<Location>();
    int rowbefore = loc.getRow() - 1;
    int col = loc.getCol(); 
    while (rowbefore >= 0 && isTile(new Location(rowbefore, col)))
    {
      word.add(0, new Location(rowbefore, col));
      rowbefore--;
    }
    word.add(loc);
    int rowafter = loc.getRow() + 1;
    while (rowafter < 15 && isTile(new Location(rowafter, col)))
    {
      word.add(new Location(rowafter, col));
      rowafter++;
    }
    
    String hWord = "";
    for (int i = 0; i < word.size(); i++)
    {
      String image = display.getImage(word.get(i));
      String letter = image.substring(0, image.indexOf("."));
      hWord += letter;
    }
    
    System.out.println(hWord);
    return word;
  }
  
  public boolean inDictionary(String word)
  {
    return dictionary.indexOf(word.toLowerCase()) != -1;
  }
  
  private ArrayList<Location> wORD()
  {
    System.out.println("wORD");
    
    System.out.println("prevPlaced:  " + prevPlaced);
    
    boolean isword = false;
    boolean horiz = true;
    boolean vert = true;
    
    int row = placed.get(0).getRow(); //row of first placed
    int col = placed.get(0).getCol(); //col of first placed
    
    System.out.println("after inList:  " + placed);
    
    //checks to see if vert or horiz
    for (int i = 0; i < placed.size(); i++)
    {
      if (row != placed.get(i).getRow())
        horiz = false;
      else if (col != placed.get(i).getCol())
        vert = false;
    }
    
    if (placed.size() == 1)
    {
      Location loc = placed.get(0);
      int r = loc.getRow(); int c = loc.getCol();
     
      //CHANGE THIS MAYBE IF NECESSARY
      if (isValid(new Location(r + 1, c)) && isValid(new Location(r -1 , c)))
      {
        if (isTile(new Location(r+1, c)) || isTile(new Location(r-1, c)))
          horiz = false;
      }
      else
        isword = false;
      if (isValid(new Location(r, c+1)) && isValid(new Location(r, c-1)))
      {
        if (isTile(new Location(r, c+1)) || isTile(new Location(r, c-1)))
          vert = false;  
      }
      else
        isword = false;
    }
    
    if (horiz)
    {
      ArrayList<Integer> cols = new ArrayList<Integer>();
      // makes arraylist of all placed tiles' cols
      for (int i = 0; i < placed.size(); i++)
      {
        cols.add(placed.get(i).getCol());
      }
      
      // sorts cols in ascending order
      for (int i = 0; i < cols.size(); i++)
      {
        int min = indexOfMin(cols, i);
        int first = cols.get(i);
        int smallest = cols.get(min);
        
        cols.set(i, smallest); //puts the smallest in index 0
        cols.set(min, first);
        System.out.println("sorted");
      }
      
      // sorted cols
      
      // the locations are sorted
      for (int i = 0; i < cols.size(); i++)
      {
        placed.set(i, new Location(row, cols.get(i)));
      }
      
      
      System.out.println("placed 17:  " + placed);
      
      
      boolean hole = false;
      ArrayList<Location> holes = new ArrayList<Location>();
      ArrayList<Location> prevInWord = new ArrayList<Location>();
      
      // checks to see if there are holes in the placed tiles, and adds hole location to arraylist holes
      for (int i = 1; i < placed.size(); i++)
      {
        if (placed.get(i-1).getCol() != placed.get(i).getCol() - 1)
        {
          hole = true;
          holes.add(new Location(row, placed.get(i).getCol() - 1)); // this is the missing locations
          System.out.println("holes!");
        }
      }
      
     
      
      if (holes.size() > 0) //if there are holes
      {
        //check in the holes
        
        
        
        //for every hole checks through every location in prevPlaced
        for (int k = 0; k < holes.size(); k++) //checks each hole
        {
          if (prevPlaced.contains(holes.get(k)))
          {
            holes.remove(k);
            k--;
          }
        }
      }
      
      if (holes.size() > 0) //if there still are holes left
      {
        isword = false; //CANNOT BE A WORD
      
      }
      else
      {
        isword = true;
        
        for (int i = placed.get(0).getCol(); i <= placed.get(placed.size() -1).getCol(); i++) //from the first to last
        {
          word.add(new Location(row, i));
        }
        
        
//check before to see if there are previously placed tiles
        int colbefore = placed.get(0).getCol() - 1;
        while (colbefore >= 0 && isTile(new Location(row, colbefore)))
        {
          if (inList(prevPlaced, new Location(row, colbefore)))
            word.add(0, new Location(row, colbefore));
          colbefore--;
          System.out.println("colbefore: " + colbefore);
        }
        
        //check after
        int colafter = placed.get(placed.size()-1).getCol() + 1;
        while (colafter < 15 && isTile(new Location(row, colafter)))
        {
          if (inList(prevPlaced, new Location(row, colafter)))
          {
            word.add(new Location(row, colafter));
            System.out.println("colafter: " + colafter);
          }
          colafter++;
        }
      }
    }
    
    ////////////////////////
    ////////////////////////
    ////////////////////////
    ////////////////////////
    else if (vert)
    {
      ArrayList<Integer> rows = new ArrayList<Integer>();
      // makes arraylist of all placed tiles' rows
      for (int i = 0; i < placed.size(); i++)
      {
        rows.add(placed.get(i).getRow());
      }
      
      // sorts rows in ascending order
      for (int i = 0; i < rows.size(); i++)
      {
        int min = indexOfMin(rows, i);
        int first = rows.get(i);
        int smallest = rows.get(min);
        
        rows.set(i, smallest); //puts the smallest in index 0
        rows.set(min, first);
        System.out.println("sorted");
      }
      
      // sorted rows
      
      // the locations are sorted
      for (int i = 0; i < rows.size(); i++)
        placed.set(i, new Location(rows.get(i), col)); 
      
      System.out.println("placed 2367:  " + placed);
      
      
      boolean hole = false;
      ArrayList<Location> holes = new ArrayList<Location>();
      
      // checks to see if there are holes in the placed tiles, and adds hole location to arraylist holes
      for (int i = 1; i < placed.size(); i++)
      {
        if (placed.get(i-1).getRow() != placed.get(i).getRow() - 1)
        {
          hole = true;
          holes.add(new Location(placed.get(i).getRow() - 1, col)); // this is the missing locations
          System.out.println("holes!");
        }
      }
      
      System.out.println("holes:  " + holes);
      
      if (holes.size() > 0) //if there are holes
      {
        //check in the holes
        
        
        
        //for every hole checks through every location in prevPlaced
        for (int k = 0; k < holes.size(); k++) //checks each hole
        {
          if (prevPlaced.contains(holes.get(k)))
          {
            holes.remove(k);
            k--;
          }
        }
      }
      
      if (holes.size() > 0) //if there still are holes left
      {
        isword = false; //CANNOT BE A WORD
        System.out.println("not word");
        System.out.println("holes: " + holes);
      }
      else
      {
        isword = true;
        
        for (int i = placed.get(0).getRow(); i <= placed.get(placed.size() -1).getRow(); i++) //from the first to last
        {
          word.add(new Location(i, col));
        }
        System.out.println("Vertical Word. Word - " + word);
        
        //check before to see if there are previously placed tiles
        int rowbefore = placed.get(0).getRow() - 1;
        while (rowbefore >= 0 && isTile(new Location(rowbefore, col)))
        {
          if (inList(prevPlaced, new Location(rowbefore, col)))
            word.add(0, new Location(rowbefore, col));
          rowbefore--;
          System.out.println("rowbefore: " + rowbefore);
        }
        
        // check in between
        
        //check after
        int rowafter = placed.get(placed.size()-1).getRow() + 1;
        while (rowafter < 15 && isTile(new Location(rowafter, col)))
        {
          if (inList(prevPlaced, new Location(rowafter, col)))
          {
            word.add(new Location(rowafter, col));
            System.out.println("rowafter: " + rowafter);
          }
          rowafter++;
        }
      }
    }
    else
    {
      isword = false;
    }
    
    System.out.println("placed 2317:  " + placed);
    
    
    if (isword)
    {
      String letters = "";
      for (int i = 0; i < word.size(); i++)
      {
        String letter = display.getImage(word.get(i));
        letter = letter.substring(0, letter.indexOf("."));
        letters += letter;
      }
//      if (inDictionary("dictionary.txt", letters)) how to do dictionary?
      System.out.println(letters);
    }
    
    System.out.println("placed 2323235:  " + placed);
    return word;
  }
  
  private boolean isBlack(Location loc)
  {
    boolean black = false;
    if (loc != null)
    {
      for (int i = 0; i < allTiles.size(); i++)
      {
        if (display.getImage(loc).equals("black.jpg"))
          black = true;
      }
    }
    return black;
  }
  
  
//called when the user clicks on a location.
  //that location is passed to this method.
  private void locationClicked(Location loc)
  {
    display.setColor(loc, new Color(255, 0, 0));
    if (first != null)
    {
      System.out.println("second click:  " + loc);
      //second click
      
      if (isTile(first) && !inPrevPlaced(first) && !isTile(loc) && board[first.getRow()][first.getCol()].equals("black.jpg")) // IS TILE, moving from black to board
      {
        display.setImage(loc, display.getImage(first));
        placed.add(new Location(loc.getRow(), loc.getCol())); //puts the set tile in placed
        display.setImage(first, board[first.getRow()][first.getCol()]);  //showing original square from beneath tile
      }
      else if (isTile(first) && !inPrevPlaced(first) && !isTile(loc) && !board[loc.getRow()][loc.getCol()].equals("black.jpg")) //tile, moving from board to board
      {
        display.setImage(loc, display.getImage(first));
        placed.remove(indexInList(placed, first)); //remove the previous location from the placed
        placed.add(new Location(loc.getRow(), loc.getCol()));
        display.setImage(first, board[first.getRow()][first.getCol()]);
      }
      else if (isTile(first) && !inPrevPlaced(first) && !isTile(loc) && board[loc.getRow()][loc.getCol()].equals("black.jpg")) //tile, moving from board to black
      {
        display.setImage(loc, display.getImage(first));
        placed.remove(indexInList(placed,first));
        display.setImage(first, board[first.getRow()][first.getCol()]);
      }
      else
      {
      }
      first = null;
    }
    else
    {
      System.out.println("first click:  " + loc);
      //first click
//      display.setColor(first, new Color(255, 0, 0));
      first = loc;
    }
  }
  
  //called when the user presses a key.
  //each key on the keyboard has a unique key code.
  //that key code is passed to this method.
  private void keyPressed(int key)
  {
    //print key code
    System.out.println("key code:  " + key);
    
    if (key == 10)
    {
      //enter was pressed
      getAllWords();
      if (placed.size() > 0) //was a valid word
      {
        //add to prevPlaced
        for (int i = 0; i < placed.size(); i++)
          prevPlaced.add(placed.get(i));
        
        //take out placed from player1
        for (int i = 0; i < placed.size(); i++)
        {
          int k = 0;
          if (turn % 2 == 1)
          {
            while (k < player1.size())
            {
              String tile = display.getImage(placed.get(i));
              //if the tile is on board (location of tile is in placed) and tile in placed is in player1
              if (tile.equals(player1.get(k).getImage()))
              {
                player1.remove(k); 
                System.out.println("           PLAYER1REMOVED");
              }
              else
                k++;
            }
            for (int a = 0; a < placed.size(); a++)
            {
              int b = (int) (Math.random() * unused.size());
              if (unused.size() > 0)
              {
                player1.add(unused.get(b));
                unused.remove(b);
              }
            }
            
            
          }
          else ///player2
          {
            while (k < player2.size())
            {
              String tile = display.getImage(placed.get(i));
              //if the tile is on board (location of tile is in placed) and tile in placed is in player1
              if (tile.equals(player2.get(k).getImage()))
                player2.remove(k);
              else
                k++;
            }
            for (int a = 0; a < placed.size(); a++)
            {
              int b = (int) (Math.random() * unused.size());
              if (unused.size() > 0)
              {
                player2.add(unused.get(b));
                unused.remove(b);
              }
            }
          }
        }
        turn++;
        display.showMessageDialog("Player1 Score: " + score1 + "\nPlayer2 Score: " + score2);
      }
      
      String player = "";
      int score;
      if (turn % 2 == 1)
      {
        player = "Player1";
        score = score1;
      }
      else
      {
        player = "Player2";
        score = score2;
      }
      display.setTitle("Game | Turn: " + turn + " | Player: " + player + " | Score: " + score);
      
      
      System.out.println("RESET PLACED");
      
      placed = new ArrayList<Location>();
      word = new ArrayList<Location>();
      
      if (turn % 2 == 1)
        placeLetters(player1);
      else
        placeLetters(player2);
    }
    else if (key == 32)
    {
      //space
      if (turn % 2 == 1) //player1
      {
        int i = 0;
        while (i < player1.size())
        {
          unused.add(player1.get(i));
          player1.remove(player1.get(i));
        }
        
        int col = 4;
        while (col < 11 && unused.size() > 0)
        {
          int k = (int) (Math.random() * unused.size());
          display.setImage(new Location(15, col), unused.get(k).getImage());
          player1.add(unused.get(k));
          unused.remove(k);
          col++;
        }
      }
      else
      {
        int i = 0;
        while (i < player2.size())
        {
          unused.add(player2.get(i));
          player2.remove(player2.get(i));
        }
        
        int col = 4;
        while (col < 11 && unused.size() > 0)
        {
          int k = (int) (Math.random() * unused.size());
          display.setImage(new Location(15, col), unused.get(k).getImage());
          player2.add(unused.get(k));
          unused.remove(k);
          col++;
        }
      }
      turn++;
      
      String player = "";
      int score;
      if (turn % 2 == 1)
      {
        player = "Player1";
        score = score1;
      }
      else
      {
        player = "Player2";
        score = score2;
      }
      display.setTitle("Game | Turn: " + turn + " | Player: " + player + " | Score: " + score);
      display.showMessageDialog("Player1 Score: " + score1 + "\nPlayer2 Score: " + score2);
      System.out.println("RESET PLACED");
      
      placed = new ArrayList<Location>();
      word = new ArrayList<Location>();
      
      if (turn % 2 == 1)
        placeLetters(player1);
      else
        placeLetters(player2);
    }
    
    else
    {
      //some other key was pressed
      
      //show help message
      display.showMessageDialog("Press RETURN to enter word. \nPress SPACE to shuffle letters, ending your turn.");
    }
  }
  
  public void putTilesBack(ArrayList<Location> locs)
  {
    ArrayList<Location> inPlaced = new ArrayList<Location>();
    for (int i = 0; i < locs.size(); i++)
      inPlaced.add(locs.get(i)); //inplaced == locs
    
    System.out.println("INPLACED:           " + inPlaced.size());
                         
    int i = 4;
    int index = 0;
    while (i<15 && inPlaced.size() > 0)
    {
      if (!isTile(new Location(15, i)))
      {
        display.setImage(new Location(15, i), display.getImage(inPlaced.get(index)));
        display.setImage(inPlaced.get(index), board[inPlaced.get(index).getRow()][inPlaced.get(index).getCol()]);
        inPlaced.remove(index);
        System.out.println("INPLACED:           " + inPlaced.size());
      }
      i++;
    }
    placed = new ArrayList<Location>(); 
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
    SCRABBLEGame g = new SCRABBLEGame();
    g.play();
  }
  
  public void loadDictionary()
  {
    try
    {
      dictionary = new ArrayList<String>();
      
      BufferedReader in = new BufferedReader(new FileReader("words.txt"));
      String line = in.readLine();
      while (line != null)
      {
        dictionary.add(line);
        line = in.readLine();
      }
      in.close();
    }
    catch(IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}
