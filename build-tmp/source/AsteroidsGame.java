import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip dude;
Star[] popStar;
ArrayList <Asteroid> rockLobster;
ArrayList <Bullet> dirtyDan;
public void setup() 
{
  size(750,750);
  dude = new SpaceShip();
  popStar = new Star[500];
  for (int i = 1; i < popStar.length; i++)
  {
    popStar[i] = new Star();
  }
  rockLobster = new ArrayList <Asteroid>();
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  rockLobster.add(new Asteroid());
  dirtyDan = new ArrayList <Bullet>();
}
public void draw() 
{
  background(0);
  dude.show();
  dude.move();
  for(int a = 1; a <popStar.length; a++)
  {
    popStar[a].show();
  }
  for (int r = 0; r < rockLobster.size(); r++)
  {
    rockLobster.get(r).show();
    rockLobster.get(r).rotate(5);
    rockLobster.get(r).move();
  }
  for(int g = 0; g < dirtyDan.size(); g++)
  {
    dirtyDan.get(g).show();
    dirtyDan.get(g).move();
    for (int r = 0; r < rockLobster.size(); r++)
    {
      if(dist(dirtyDan.get(g).getX(), dirtyDan.get(g).getY(), rockLobster.get(r).getX(), rockLobster.get(r).getY()) < 25)
      {
        rockLobster.remove(r);
        dirtyDan.remove(g);
        break;
      }
    }
  }
}
public void keyPressed()
{                                       
  if(key == 'a')                                       
  {                                       
    dude.accelerate(-0.5f);                                       
  }                                       
  if(key == 'd')                                       
  {                                       
    dude.accelerate(0.5f);                                       
  }                                       
  if(key == 'k')                                       
  {                                       
    dude.rotate(8);                                       
  }                                       
  if(key == 'l')                                       
  {                                       
    dude.rotate(-8);                                       
  }                                       
  if(key == 'h')                                       
  {                                       
    dude.setX((int)(Math.random()*width));                                       
    dude.setY((int)(Math.random()*height));                                       
    dude.setDirectionX(0);                                       
    dude.setDirectionY(0);                                       
    dude.setPointDirection((int)(Math.random()*360));                                       
  } 
  if(key == 's')
  {
    dirtyDan.add(new Bullet(dude));
  }                                      
}                                       
class Star                                       
{                                       
  private int myX, myY, myColor;                                       
  public Star()                                       
  {                                       
    myX = (int)(Math.random()*750);                                       
    myY = (int)(Math.random()*750);                                       
  }
  public void show()
  {
    ellipse(myX, myY, 1,1);
  }
}
class Asteroid extends Floater
{
  public Asteroid()
  {
      corners = 4;
      xCorners = new int[corners];
      yCorners = new int[corners];
      xCorners[0] = -15;
      yCorners[0] = -15;
      xCorners[1] = -15;
      yCorners[1] = 15;
      xCorners[2] = 15;
      yCorners[2] = 15;
      xCorners[3] = 15;
      yCorners[3] = -15;
      myColor = 255;
      myCenterX = ((int)(Math.random()*width));
      myCenterY = ((int)(Math.random()*height));
      myDirectionX = ((int)(Math.random()*10)-5);
      myDirectionY = ((int)(Math.random()*10)-5);
      myPointDirection = ((int)(Math.random()*360));
  }
  public void setX(int x){myCenterX = x;}  
  public int getX(){return (int)myCenterX;}   
  public void setY(int y){myCenterY = y;}   
  public int getY(){return (int)myCenterY;}   
  public void setDirectionX(double x){myDirectionX = x;}   
  public double getDirectionX(){return (int)myDirectionX;}   
  public void setDirectionY(double y){myDirectionY = y;}   
  public double getDirectionY(){return (int)myDirectionY;}   
  public void setPointDirection(int degrees){myPointDirection = degrees;}   
  public double getPointDirection(){return (int)myPointDirection;} 
}

class SpaceShip extends Floater  
{   
    public SpaceShip()
    {
      corners = 3;
      xCorners = new int[corners];
      yCorners = new int[corners];
      xCorners[0] = -8;
      yCorners[0] = -8;
      xCorners[1] = 16;
      yCorners[1] = 0;
      xCorners[2] = -8;
      yCorners[2] = 8;
      myColor = 255;
      myCenterX = 375;
      myCenterY = 375;
      myDirectionX = 0;
      myDirectionY = 0;
      myPointDirection = 0;
    }
  public void setX(int x){myCenterX = x;}  
  public int getX(){return (int)myCenterX;}   
  public void setY(int y){myCenterY = y;}   
  public int getY(){return (int)myCenterY;}   
  public void setDirectionX(double x){myDirectionX = x;}   
  public double getDirectionX(){return (int)myDirectionX;}   
  public void setDirectionY(double y){myDirectionY = y;}   
  public double getDirectionY(){return (int)myDirectionY;}   
  public void setPointDirection(int degrees){myPointDirection = degrees;}   
  public double getPointDirection(){return (int)myPointDirection;} 
}
class Bullet extends Floater
{
  public Bullet(SpaceShip theShip)
  {
    myCenterX = theShip.getX();
    myCenterY = theShip.getY();
    myPointDirection = theShip.getPointDirection();
    double dRadians = myPointDirection*(Math.PI/180);
    myDirectionX = 5*Math.cos(dRadians) + theShip.getDirectionX();
    myDirectionY = 5*Math.sin(dRadians) + theShip.getDirectionY();
  }
  public void show()
  {
    ellipse((float)myCenterX, (float)myCenterY, 10,10);
  }
  public void setX(int x){myCenterX = x;}  
  public int getX(){return (int)myCenterX;}   
  public void setY(int y){myCenterY = y;}   
  public int getY(){return (int)myCenterY;}   
  public void setDirectionX(double x){myDirectionX = x;}   
  public double getDirectionX(){return (int)myDirectionX;}   
  public void setDirectionY(double y){myDirectionY = y;}   
  public double getDirectionY(){return (int)myDirectionY;}   
  public void setPointDirection(int degrees){myPointDirection = degrees;}   
  public double getPointDirection(){return (int)myPointDirection;}
}
abstract class Floater  
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);  
  abstract public int getX();   
  abstract public void setY(int y);   
  abstract public int getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection();                    
  public void accelerate (double dAmount)           
  {                  
    //convert the current direction the floater is pointing to radians            
    double dRadians =myPointDirection*(Math.PI/180);             
    //change coordinates of direction of travel            
    myDirectionX += ((dAmount) * Math.cos(dRadians));            
    myDirectionY += ((dAmount) * Math.sin(dRadians));               
  }           
  public void rotate (int nDegreesOfRotation)           
  {             
    //rotates the floater by a given number of degrees            
    myPointDirection+=nDegreesOfRotation;           
  }           
  public void move ()   //move the floater in the current direction of travel          
  {              
    //change the x and y coordinates by myDirectionX and myDirectionY               
    myCenterX += myDirectionX;            
    myCenterY += myDirectionY;             
    //wrap around screen            
    if(myCenterX >width)          
    {             
      myCenterX = 0;            
    }            
    else if (myCenterX< 0)         
    {             
      myCenterX = width;            
    }            
    if(myCenterY >height)          
    {            
      myCenterY = 0;            
    }           
    else if (myCenterY < 0)          
    {             
      myCenterY = height;            
    }           
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
} 

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
