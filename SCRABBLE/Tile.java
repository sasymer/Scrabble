public class Tile
{
  private String letterImage;
  private int letterScore;
  
  public Tile(String i)
  {
    letterImage = i;
    letterScore = 1;
    
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("D"))
    {
      letterScore = 2;
    }
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("G"))
    {
      letterScore = 2;
    }
    
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("B"))
    {
      letterScore = 3;
    }
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("C"))
    {
      letterScore = 3;
    }
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("G"))
    {
      letterScore = 3;
    }
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("M"))
    {
      letterScore = 3;
    }
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("P"))
    {
      letterScore = 3;
    }
    
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("F"))
    {
      letterScore = 4;
    }
    if (letterImage.substring(0,letterImage.indexOf(".")).equals("H"))
    {
      letterScore = 4;
    }
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("V"))
    {
      letterScore = 4;
    }
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("W"))
    {
      letterScore = 4;
    }
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("Y"))
    {
      letterScore = 4;
    }
   
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("K"))
    {
      letterScore = 5;
    }
   
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("J"))
    {
      letterScore = 8;
    }
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("X"))
    {
      letterScore = 8;
    }
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("Q"))
    {
      letterScore = 10;
    }
   if (letterImage.substring(0,letterImage.indexOf(".")).equals("Z"))
    {
      letterScore = 10;
    }
   
  }
  
  public String getImage()
  {
    return letterImage;
  }
  
  public String getLetter()
  {
    String letter = letterImage.substring(0, letterImage.indexOf("."));
    return letter;
  }
  
  public int getScore()
  {
    return letterScore;
  }
  
}