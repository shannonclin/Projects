import java.awt.*;
import java.net.URL; 
import java.util.LinkedList;

/**
 * A class that represents a picture.  This class inherits from SimplePicture
 *  and allows the student to add functionality and picture effects.  
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu)
 *  (Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (lewis@cs.hmc.edu),
 *  Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 *  Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 *  and David Zeng (davidzeng@berkeley.edu).
 * 
 * 
 * Name(s): Shannon Lin
 * Course: CS60
 * 
 */

public class Picture extends SimplePicture 
{
 /////////////////////////// Static Variables //////////////////////////////
 // Different axes available to flip a picture.
 public static final int HORIZONTAL = 1;
 public static final int VERTICAL = 2;
 public static final int FORWARD_DIAGONAL = 3;
 public static final int BACKWARD_DIAGONAL = 4;

 //////////////////////////// Constructors /////////////////////////////////
 /**
  * A constructor that takes no arguments.
  */
 public Picture () {
	 super();
	 }
 
 /**
  * Creates a Picture from the file name provided.
  * 
  * @param fileName The name of the file to create the picture from.
  */
 public Picture(String fileName) {
  // Let the parent class handle this fileName.
	 super(fileName);
	 }
 
 /**
  * Creates a Picture from the width and height provided.
  * 
  * @param width the width of the desired picture.
  * @param height the height of the desired picture.
  */
 public Picture(int width, int height) {
  // Let the parent class handle this width and height.
	 super(width, height);
	 }
 
 /**
  * Creates a copy of the Picture provided.
  * 
  * @param pictureToCopy Picture to be copied.
  */
 public Picture(Picture pictureToCopy) {
  // Let the parent class do the copying
	 super(pictureToCopy);
	 }
 
 /**
  * Creates a copy of the SimplePicture provided.
  * 
  * @param pictureToCopy SimplePicture to be copied.
  */
 public Picture(SimplePicture pictureToCopy) {
  // Let the parent class do the copying.
	 super(pictureToCopy);
	 }
 
 /////////////////////////////// Methods ///////////////////////////////////
 //////////////////////////// Provided Methods /////////////////////////////
 /**
  * Helper method to determine if a x and y coordinate is valid (within the image) 
  * 
  * @param ix is the x value that might be outside of the image
  * @param iy is the y value that might be outside of the image
  * @return true if the x and y values are within the image and false otherwise
  */
 private boolean inImage(int ix, int iy) {
	 return ix >= 0 && ix < this.getWidth() && iy >= 0 && 
			iy < this.getHeight();
	 }
 
 /**
  * @return A string with information about the picture, such as 
  *  filename, height, and width.
  */
 public String toString() {
	 String output = "Picture, filename = " + this.getFileName() + "," + 
     " height = " + this.getHeight() + ", width = " + this.getWidth();
	 return output;
	 }
 
 /**
  * Equals method for two Picture objects. 
  * 
  * @param obj is an Object to compare to the current Picture object
  * @return true if obj is a Picture object with the same size as the
  *         original and with the same color at each Pixel
  */
 public boolean equals(Object obj) {
	 if (!(obj instanceof Picture)) {
		 return false;
		 }
	 
  Picture p = (Picture) obj;
  // Check that the two pictures have the same dimensions.
  if ((p.getWidth() != this.getWidth()) || 
		  (p.getHeight() != this.getHeight())) {
	  return false;
	  }
  
  // Check each pixel.
  for (int x = 0; x < this.getWidth(); x++) {
	  for(int y = 0; y < this.getHeight(); y++) {
		  if (!this.getPixel(x, y).equals(p.getPixel(x, y))) {
			  return false;
			  }
		  }
	  }
  return true;
  }
 
 /**
  * Helper method for loading a picture in the current directory.
  */
 protected static Picture loadPicture(String pictureName) {
	 URL url = Picture.class.getResource(pictureName);
	 return new Picture(url.getFile().replaceAll("%20", " "));
	 }
 
////////////////////////////Debugging Methods /////////////////////////////////
/**
* Method to print out a table of the intensity for each Pixel in an image
*/
public void printIntensity(){
	int pictureHeight = this.getHeight();
	int pictureWidth = this.getWidth();
	System.out.println("Intensity:");
	for(int y = 0; y < pictureHeight; y++) {
		System.out.print("[");
		for(int x = 0; x < pictureWidth; x++) {
			System.out.print(this.luminosityOfPixel(x, y) + "\t");
			}
		System.out.println("]");
		}
	}

/**
* Method to print out a table of the energy for each Pixel in an image
*/
public void printEnergy(){
	int pictureHeight = this.getHeight();
	int pictureWidth = this.getWidth();
	System.out.println("Energy:");
	for(int y = 0; y < pictureHeight; y++) {
		System.out.print("[");
		for(int x = 0; x < pictureWidth; x++) {
			System.out.print(this.getEnergy(x, y) + "\t");
			}
		System.out.println("]");
		}
	}

/**
* Prints a two dimensional array of ints
* @param array
*/
public void printArray(int[][] array) {
	int height = array.length;
	int width = array[0].length;
	for (int r = 0; r < width; ++r) {
		for (int c = 0; c < height; ++c) {
			System.out.print(array[c][r] + "\t");
			}
		System.out.println();
		}
	}

/**
* This method can be used like the other Picture methods, to create a
* Picture that shows what Pixels are different between two Picture objects.
* 
* @param picture2 is a Picture to compare the current Picture to
* @return returns a new Picture with red pixels indicating differences between 
*    the two Pictures
*/
public Picture showDifferences(Picture picture2){
	Picture newPicture = new Picture(this);
	int pictureHeight = this.getHeight();
	int pictureWidth = this.getWidth();
	Color red = new Color(255, 0, 0);
	for(int x = 0; x < pictureWidth; x++) {
		for(int y = 0; y < pictureHeight; y++) {
			if (!this.getPixel(x, y).equals(picture2.getPixel(x, y))) {
				Pixel p = newPicture.getPixel(x, y);
				p.setColor(red);
				}
			}
		}
	return newPicture;
	}
 
 //////////////////////////// Grayscale Example /////////////////////////////////
 /*
  * Each of the methods below is constructive: in other words, each of the
  * methods below generates a new Picture, without permanently modifying the
  * original Picture.
  */

 /**
  * Converts the Picture into grayscale. Since any variation of gray
  *  is obtained by setting the red, green, and blue components to the same
  *  value, a Picture can be converted into its grayscale component
  *  by setting the red, green, and blue components of each pixel in the
  *  new picture to the same value: the average of the red, green, and blue
  *  components of the same pixel in the original.
  * 
  * @return A new Picture that is the grayscale version of this Picture.
  */
 public Picture grayscale() {
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 newPicture.setPixelToGray(x, y);
			 }
		 }
	 return newPicture;
 }
 
 /**
  * Helper method for grayscale() to set a pixel at (x, y) to be gray.
  * 
  * @param x The x-coordinate of the pixel to be set to gray.
  * @param y The y-coordinate of the pixel to be set to gray.
  */
 private void setPixelToGray(int x, int y) {
  Pixel currentPixel = this.getPixel(x, y);
  int average = Picture.averageOfRGB(currentPixel.getColor());
  currentPixel.setRed(average);
  currentPixel.setGreen(average);
  currentPixel.setBlue(average);  
 }
 
 /**
  * Helper method for grayscale() to calculate the
  * average value of red, green and blue.
  *
  * @param c is the Color to be averaged
  * @return The average of the red, green and blue values of this Color
  */
 private static int averageOfRGB(Color c) {
  int redComponent = c.getRed();
  int greenComponent = c.getGreen();
  int blueComponent = c.getBlue();
  int average = ((redComponent + greenComponent + blueComponent)/3);
  return average;
 }
 
 //////////////////////////// Change Colors Menu /////////////////////////////////
 //////////////////////////// Negate /////////////////////////////////
 /**
  * Converts the Picture into its photonegative version. The photonegative
  *  version of an image is obtained by setting each of the red, green,
  *  and blue components of every pixel to a value that is 255 minus their
  *  current values.
  * 
  * @return A new Picture that is the photonegative version of this Picture. 
  */
 public Picture negate() {
  Picture newPicture = new Picture(this);
  int pictureHeight = this.getHeight();
  int pictureWidth = this.getWidth();
  
  for(int x = 0; x < pictureWidth; x++) {
	  for(int y = 0; y < pictureHeight; y++) {
		  Pixel currentPixel = newPicture.getPixel(x,y);
		  
		  int redComponent = 255 - currentPixel.getRed();
		  int blueComponent = 255 - currentPixel.getBlue();
		  int greenComponent = 255 - currentPixel.getGreen();
		  
		  currentPixel.setRed(redComponent);
		  currentPixel.setGreen(greenComponent);
		  currentPixel.setBlue(blueComponent);
		  }
	  }
  return newPicture;
 }
 
 
 //////////////////////////// Lighten /////////////////////////////////
 
 /**
  * Creates an image that is lighter than the original image. The range of
  * each color component should be between 0 and 255 in the new image. The
  * alpha value should not be changed.
  * 
  * @return A new Picture that has every color value of the Picture increased
  *         by the lightenAmount.
  */
 public Picture lighten(int lightenAmount) {
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	  
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel currentPixel = newPicture.getPixel(x, y);
			 
			 int redComponent = currentPixel.getRed() + lightenAmount;
			 int blueComponent = currentPixel.getBlue() + lightenAmount;
			 int greenComponent = currentPixel.getGreen() + lightenAmount;
			 
			 currentPixel.setRed(Math.min(255,redComponent));
			 currentPixel.setBlue(Math.min(255,blueComponent));
			 currentPixel.setGreen(Math.min(255,greenComponent));
			 }
		 }
	 return newPicture;
	 }
   
 //////////////////////////// Darken /////////////////////////////////
 /**
  * Creates an image that is darker than the original image.The range of
  * each color component should be between 0 and 255 in the new image. The
  * alpha value should not be changed.
  * 
  * @return A new Picture that has every color value of the Picture decreased
  *         by the darkenenAmount.
  */
 public Picture darken(int darkenAmount) {
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	  
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel currentPixel = newPicture.getPixel(x, y);
			 
			 int redComponent = currentPixel.getRed() - darkenAmount;
			 int blueComponent = currentPixel.getBlue() - darkenAmount;
			 int greenComponent = currentPixel.getGreen() - darkenAmount;
			 
			 currentPixel.setRed(Math.max(0,redComponent));
			 currentPixel.setBlue(Math.max(0,blueComponent));
			 currentPixel.setGreen(Math.max(0,greenComponent));
			 }
		 }
	 return newPicture;
	 }
 
 //////////////////////////// Add[Blue,Green,Red] /////////////////////////////////
 /**
  * Creates an image where the blue value has been increased by amount.The range of
  * each color component should be between 0 and 255 in the new image. The
  * alpha value should not be changed.
  * 
  * @return A new Picture that has every blue value of the Picture increased
  *         by amount.
  */
 public Picture addBlue(int amount) {
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel currentPixel = newPicture.getPixel(x, y);
			 int blueComponent = currentPixel.getBlue() + amount;
			 
			 currentPixel.setBlue(Math.min(255,blueComponent));
			 }
		 }
	 return newPicture;
	 }
 
 /**
  * Creates an image where the red value has been increased by amount. The range of
  * each color component should be between 0 and 255 in the new image. The
  * alpha value should not be changed.
  * 
  * @return A new Picture that has every red value of the Picture increased
  *         by amount.
  */
 public Picture addRed(int amount) {
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel currentPixel = newPicture.getPixel(x, y);
			 int redComponent = currentPixel.getRed() + amount;
			 
			 currentPixel.setRed(Math.min(255,redComponent));
			 }
		 }
	 return newPicture;
	 }
 
 /**
  * Creates an image where the green value has been increased by amount. The range of
  * each color component should be between 0 and 255 in the new image. The
  * alpha value should not be changed.
  * 
  * @return A new Picture that has every green value of the Picture increased
  *         by amount.
  */
 public Picture addGreen(int amount) {
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel currentPixel = newPicture.getPixel(x, y);
			 int greenComponent = currentPixel.getGreen() + amount;
			 
			 currentPixel.setGreen(Math.min(255,greenComponent));
			 }
		 }
	 return newPicture;
	 }
 
 //////////////////////////// Rotate Right /////////////////////////////////
 /**
  * Returns a new picture where the Picture is rotated to the right by 90
  * degrees. If the picture was originally 50 Pixels by 70 Pixels, the new
  * Picture should be 70 Pixels by 50 Pixels.
  * 
  * @return a new Picture rotated right by 90 degrees
  */
 public Picture rotateRight() {
	 // normally this takes in width, height, but we swap because we are
	 // rotating
	 Picture newPicture = new Picture(this.getHeight(), this.getWidth());
	 
  for(int x = 0; x < this.getWidth(); x++) {
	  for(int y = 0; y < this.getHeight(); y++) {
		  Pixel oldPixel = this.getPixel(x, y);
		  Pixel currentPixel = newPicture.getPixel(this.getHeight() - 1 - y, x);
		  		  
		  currentPixel.setColor(oldPixel.getColor());
		  }
	  }
  return newPicture;
  }
 
 /**
  * Rotates this Picture by the integer multiple of 90 degrees provided.
  *  If the number of rotations provided is positive, then the picture
  *  is rotated clockwise; else, the picture is rotated counterclockwise.
  *  Multiples of four rotations (including zero) correspond to no
  *  rotation at all.
  * 
  * @param rotations The number of 90-degree rotations to rotate this
  *  image by.
  * 
  * @return A new Picture that is the rotated version of this Picture.
  */
 private Picture rotate(int rotations) {
	 int positiveRotations = ((rotations % 4) + 4) % 4;
	 Picture newPicture = new Picture(this);
	 while (positiveRotations > 0)
	 {
		 newPicture = newPicture.rotateRight();
		 positiveRotations --;
	 }
	 return newPicture;
 	}
 
 //////////////////////////// Seam Carving Methods /////////////////////////////////
 
 //////////////////////////// Luminosity /////////////////////////////////
 /**
  * Returns a Picture of a version of grayscale using luminosity instead
  * of a direct average. The Picture should be converted into its luminosity
  * version by setting the red, green, and blue components of each pixel in
  * the new picture to the same value: the luminosity of the red, green, and
  * blue components of the same pixel in the original. Where luminosity =
  * 0.21 * redness + 0.72 * greenness + 0.07 * blueness
  * 
  * @return A new Picture that is the luminosity version of this Picture.
  */
 public Picture luminosity(){
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
  
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel currentPixel = newPicture.getPixel(x,y);
			 int luminum = luminosityOfPixel(x,y);
			 
			 currentPixel.setRed(luminum);
			 currentPixel.setBlue(luminum);
			 currentPixel.setGreen(luminum);
		 }
	 }
	 return newPicture;
	 }
 
 /**
  * Helper method for grayscale() to calculate the
  * luminosity of a Color.
  *
  * @param p is the Pixel to be evaluated
  * @return The luminosity of this Color
  */
 private int luminosityOfPixel(int x, int y) {
	 Pixel currentPixel = this.getPixel(x, y);
	 int redComponent = currentPixel.getRed();
	 int blueComponent = currentPixel.getBlue();
	 int greenComponent = currentPixel.getGreen();
	 int luminosity = (int) (0.21*redComponent + 0.72*greenComponent + 0.07*blueComponent);
	 
	 return Math.min(255, luminosity);
 	}
 
 //////////////////////////// Energy /////////////////////////////////
 /**
  * Returns a Picture into a version of the energy of the Picture
  * 
  * @return A new Picture that is the energy version of this Picture.
  */
 public Picture energy(){
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel currentPixel = newPicture.getPixel(x,y);
			 int energum = this.getEnergy(x,y);
			 
			 currentPixel.setRed(energum);
			 currentPixel.setBlue(energum);
			 currentPixel.setGreen(energum);
			 }
		 }
	 return newPicture;
	 }
 
	 /**
	  * Helper method for energy() to calculate the
	  * energy of a Pixel.
	  *
	  * @param x is the x value of the Pixel to be evaluated
	  * @param y is the y value of the Pixel to be evaluated
	  * @return The energy of this Pixel
	  */
 private int getEnergy(int x, int y) {
	 int right = 0;
	 int bottom = 0;
	 
	 if (x < this.getWidth() - 1) {
		 right = this.luminosityOfPixel(x+1, y) -
				 this.luminosityOfPixel(x, y);
		 if (y < this.getHeight() - 1) {
			 bottom = this.luminosityOfPixel(x, y+1) -
					  this.luminosityOfPixel(x, y);
			 }
		 else if (y>0){
			 bottom = this.luminosityOfPixel(x, y) -
					 this.luminosityOfPixel(x, y-1);
			 }
		 }
	 
	 else if (x>0){
		 right = this.luminosityOfPixel(x, y) -
				 this.luminosityOfPixel(x-1, y);
		 
		 if (y < this.getHeight() - 1) {
			 bottom = this.luminosityOfPixel(x, y+1) -
					  this.luminosityOfPixel(x, y);
			 }
		 else if (y>0){
			 bottom = this.luminosityOfPixel(x, y) -
					  this.luminosityOfPixel(x, y-1);
			 }
		 }
	 int energy = Math.abs(right) + Math.abs(bottom);
	 return energy;
	 }
 
 //////////////////////////// Compute Seam /////////////////////////////////
 /**
  * private helper method computeSeam returns an int array with the
  * x-coordinates (columns) of the lowest-energy seam running from the top
  * row to the bottom row.
  * 
  * See the course assignment for additional details.
  */
 private int[] computeSeam() {
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 int rowMin = Integer.MAX_VALUE;
	 int pos_min = 0;
	 
	 int[][] table = new int [pictureWidth][pictureHeight];
	 int[][] parent = new int [pictureWidth][pictureHeight];
	 int[] seam = new int [pictureHeight];
	 
	 for (int y = 0; y < pictureHeight; y++) {
		 for (int x = 0; x < pictureWidth; x++) {
			 if (y == 0) {
				 table[x][0] = getEnergy(x,0);
				 }
			 
			 else {
				 if (x == 0) {
					 table[x][y] = getEnergy(x,y) + Math.min(table[x][y-1], 
							 table[x+1][y-1]);
					 
					 if (Math.min(table[x][y-1],
							 table[x+1][y-1]) == table[x][y-1]) {
						 parent[x][y] = x;
						 }
					 
					 else {
						 parent[x][y] = x+1;
						 }
					 }
				 
				 else if (x == pictureWidth-1) {
					 table[x][y] = getEnergy(x,y) + Math.min(table[x-1][y-1], 
							 table[x][y-1]);
					 
					 if (Math.min(table[x-1][y-1], table[x][y-1]) == table[x][y-1]) {
						 parent[x][y] = x;
						 }
					 
					 else {
						 parent[x][y] = x-1;
						 }
					 }
				 
				 else {
					 table[x][y] = getEnergy(x,y) + Math.min(table[x-1][y-1],
							 Math.min(table[x][y-1], table[x+1][y-1]));
					 
					 if (Math.min(table[x-1][y-1],
							 Math.min(table[x][y-1], table[x+1][y-1])) == 
							 table[x][y-1]) {
						 parent[x][y] = x;
						 }
					 
					 else if (Math.min(table[x-1][y-1], 
							 Math.min(table[x][y-1], table[x+1][y-1])) ==
							 table[x-1][y-1]) {
						 parent[x][y] = x-1;
						 }
					 else {
						 parent[x][y] = x+1;
						 }
					 }
				 }
			 }
		 }
	 
	 for (int x = 0; x < pictureWidth; x++) {
		 if (table[x][pictureHeight-1] < rowMin) {
			 rowMin = table[x][pictureHeight-1];
			 pos_min = x;
			 }
		 }
	 
	 seam[pictureHeight-1] = pos_min;
	 for (int y = pictureHeight-2; y >= 0; y--) {
		 seam[y] = parent[seam[y+1]][y+1];
		 }
	 return seam;
	 }
 
 //////////////////////////// Show Seam /////////////////////////////////
 /**
  * Returns a new image, with the lowest cost seam shown in red. The lowest
  * cost seam is calculated by calling computeSeam()
  * 
  * @return a new Picture
  */
 public Picture showSeam(){
	 Picture newPicture = new Picture(this);
	 int[] picSeam = newPicture.computeSeam();
	 
	 for (int y = 0; y < newPicture.getHeight(); y++) {
		 Pixel currentPixel = newPicture.getPixel(picSeam[y], y);
		 currentPixel.setRed(255);
		 currentPixel.setGreen(0);
		 currentPixel.setBlue(0);
		 }
	 
	 return newPicture;
	 }
 
 //////////////////////////// Carving /////////////////////////////////
 /**
  * Returns a new picture, where the seam identified by calling computeSeam() is
  * removed. The resulting image should be the same height as the original
  * but have a width that is one smaller than the original.
  */
 public Picture carve(){
	 Picture newPicture = new Picture(this.getWidth()-1, this.getHeight());
	 int[] picSeam = this.computeSeam();
	 
	 for (int x = 0; x < this.getWidth()-1; x++) {
		 for (int y = 0; y < this.getHeight(); y++) {
			 if (x < picSeam[y]) {
				 Pixel oldPixel = this.getPixel(x, y);
				 Pixel currentPixel = newPicture.getPixel(x, y);
				 int redComponent = oldPixel.getRed();
				 int blueComponent = oldPixel.getBlue();
				 int greenComponent = oldPixel.getGreen();
				 
				 currentPixel.setRed(redComponent);
				 currentPixel.setGreen(greenComponent);
				 currentPixel.setBlue(blueComponent);
				 }
			 
			 else {
				 Pixel oldPixel = this.getPixel(x+1, y);
				 Pixel currentPixel = newPicture.getPixel(x, y);
				 
				 int redComponent = oldPixel.getRed();
				 int blueComponent = oldPixel.getBlue();
				 int greenComponent = oldPixel.getGreen();
				 
				 currentPixel.setRed(redComponent);
				 currentPixel.setGreen(greenComponent);
				 currentPixel.setBlue(blueComponent);
				 }
			 }
		 }
	 return newPicture;
	 }    
 
 /**
  * This returns a new Picture that has a number of seams removed.
  * 
  * If the input is greater than the width of the Picture, print an error using
  * System.err instead of System.out:
  *  
  * System.err.println("Cannot call carveMany with argument " + numSeams + " on image of width " + this.getWidth()); 
  * 
  * @param numSeams is the number of times that carve should be called 
  * @return a new picture with numSeams removed
  */
 public Picture carveMany(int numSeams){
	 Picture pictureToReturn = this;
	 if (numSeams>this.getWidth()) {
		 System.err.println("Cannot call carveMany with argument " + 
	 numSeams + " on image of width " + this.getWidth()); 
		 }
	 
	 while (numSeams > 0){
		 pictureToReturn = pictureToReturn.carve();
		 numSeams--;
		 }
	 return pictureToReturn;
	 }

 
 //////////////////////////// More Effects /////////////////////////////////
 /** 
  * @param x x-coordinate of the pixel currently selected.
  * @param y y-coordinate of the pixel currently selected.
  * @param background Picture to use as the background.
  * @param threshold Threshold within which to replace pixels.
  * 
  * @return A new Picture where all the pixels in the original Picture,
  *  which differ from the currently selected pixel within the provided
  *  threshold (in terms of color distance), are replaced with the
  *  corresponding pixels in the background picture provided.
  * 
  *  If the two Pictures are of different dimensions, the new Picture will
  *  have length equal to the smallest of the two Pictures being combined,
  *  and height equal to the smallest of the two Pictures being combined.
  *  In this case, the Pictures are combined as if they were aligned at
  *  the top left corner (0, 0).
  */
 public Picture chromaKey(int xRef, int yRef, Picture background, int threshold) {
	 int minWidth = Math.min(this.getWidth(),   background.getWidth());
	 int minHeight = Math.min(this.getHeight(), background.getHeight());
	 Color referenceColor = this.getPixel(xRef, yRef).getColor();
	 Picture newPicture = new Picture(minWidth, minHeight);
	 
	 for(int x = 0; x < minWidth; x++) {
		 for(int y = 0; y < minHeight; y++) {
			 Pixel pix = newPicture.getPixel(x, y);
			 if (this.shouldRemove(x, y, referenceColor, threshold)) {
				 pix.setColor(background.getPixel(x, y).getColor());
			 }
			 else {
				 pix.setColor(this.getPixel(x, y).getColor());
			 }
		 }
	 }
	 return newPicture;
 	}
 
 	private boolean shouldRemove(int x, int y, Color refColor, int threshold) {
 		Color currentColor = this.getPixel(x, y).getColor();
 		return (threshold > Pixel.colorDistance(refColor, currentColor));
 	}

 //////////////////////////// Flip /////////////////////////////////
 /**
  * Flips this Picture about the given axis. The axis can be one of
  *  four static integer constants:
  * 
  *  (a) Picture.HORIZONTAL: The picture should be flipped about
  *   a horizontal axis passing through the center of the picture.
  *  (b) Picture.VERTICAL: The picture should be flipped about
  *   a vertical axis passing through the center of the picture.
  *  (c) Picture.FORWARD_DIAGONAL: The picture should be flipped about
  *   an axis that passes through the north-east and south-west
  *   corners of the picture.
  *  (d) Picture.BACKWARD_DIAGONAL: The picture should be flipped about
  *   an axis that passes through the north-west and south-east
  *   corners of the picture.
  * 
  * If the input is not one of these static variables, print an error using
  * System.err (instead of System.out):
  *     System.err.println("Invalid flip request");
  * 
  * 
  * @param axis Axis about which to flip the Picture provided.
  * 
  * @return A new Picture flipped about the axis provided.
  */
 	public Picture flip(int axis) {
 		switch(axis) {
 		case Picture.HORIZONTAL:
 			return this.flipHorizontal();
 		case Picture.VERTICAL:
 			return this.flipVertical();
 		case Picture.FORWARD_DIAGONAL:
 			return this.flipForwardDiagonal();
 		case Picture.BACKWARD_DIAGONAL:
 			return this.flipBackwardDiagonal();
 		}
 		
 		System.err.println("Invalid flip request"); // TODO: we should give them notes about how to handle bad input
 		return new Picture(this);
 	}
 	
 /**
  * Helper function to flip an image Vertically 
  * @return a new Picture with that is flipped along a Vertical axis.
  */
 private Picture flipVertical() {
	 Picture newPicture = new Picture(this);
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 
	 for(int y = 0; y < pictureHeight; y++) {
		 int xLeft = 0;
		 int xRight = pictureWidth - 1;
		 
		 while (xLeft < xRight) {
			 newPicture.swapPixels(xLeft, y, xRight, y);
			 xLeft++;
			 xRight--;
			 }
		 }
	 return newPicture;
	 }
 
 /**
  * Helper function to flip an image Horizontally
  * @return a new Picture with that is flipped along a Horizontal axis.
  */
 private Picture flipHorizontal() {
	 return this.rotate(1).flipVertical().rotate(-1);
	 }
 
 /**
  * Helper function to flip an image along a forward diagonal slash
  * @return a new Picture with that is flipped along a forward diagonal axis.
  */
 private Picture flipForwardDiagonal() {
	 return this.rotate(1).flipHorizontal();
	 }
 
 /**
  * Helper function to flip an image along a backward diagonal slash
  * @return a new Picture with that is flipped along a backward diagonal axis.
  */
 private Picture flipBackwardDiagonal() {
	 return this.rotate(-1).flipHorizontal();
 }
 
 /**
  * Modifies the Picture to have the colors from Pixels at (x1, y1) 
  * and (x2, y2) swapped.
  * 
  * @param x1 is the x position of the first Pixel
  * @param y1 is the y position of the first Pixel
  * @param x2 is the x position of the second Pixel
  * @param y2 is the y position of the second Pixel
  */
 private void swapPixels(int x1, int y1, int x2, int y2) {
	 Pixel pixel1 = this.getPixel(x1, y1);
	 Pixel pixel2 = this.getPixel(x2, y2);
	 Color color1 = pixel1.getColor();
	 pixel1.setColor(pixel2.getColor());
	 pixel2.setColor(color1);
 	}
 
 //////////////////////////// Show Edges /////////////////////////////////
 /**
  * @param threshold
  *            Threshold to use to determine the presence of edges.
  * 
  * @return A new Picture that contains only the edges of this Picture. For
  *         each pixel, we separately consider the color distance between
  *         that pixel and the one pixel to its left, and also the color
  *         distance between that pixel and the one pixel to the north, where
  *         applicable. As an example, we would compare the pixel at (3, 4)
  *         with the pixels at (3, 3) and the pixels at (2, 4). Also, since
  *         the pixel at (0, 4) only has a pixel to its north, we would only
  *         compare it to that pixel. If either of the color distances is
  *         larger than the provided color threshold, it is set to black
  *         (with an alpha of 255); otherwise, the pixel is set to white
  *         (with an alpha of 255). The pixel at (0, 0) will always be set to
  *         white.
  */
 
 public Picture showEdges(int threshold) {
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 Picture newPicture = new Picture(pictureWidth, pictureHeight);
	 
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel pix = newPicture.getPixel(x, y);
			 if (this.isEdge(x,y, threshold))
			 {
				 pix.setColor(new Color (0, 0, 0, 255)); 
			 }
			 
			 else
			 {
				 pix.setColor(new Color(255, 255, 255, 255)); 
			 }
		 }
		 }
	 return newPicture;
 	}
 
 /**
  * Returns true if the Pixel at (x,y) is an edge (a distinct change in color)
  * 
  * @param x is the x position of the Pixel
  * @param y is the y position of the Pixel
  * @param threshold is the maximum distance that the Color in the neighboring Pixels
  *    can be away from the Color at Pixel (x,y) using Pixel.colorDistance.
  * @return true if the Pixel is an edge.
  */
 private boolean isEdge(int x, int y, int threshold) {
	 Pixel currentPixel = this.getPixel(x, y);
	 Pixel northPixel = this.safeGetPixel(x, y - 1);
	 Pixel leftPixel = this.safeGetPixel(x - 1, y);
	 int northDistance = 0;
	 int leftDistance  = 0;
  
	 if (northPixel!= null) {
		 northDistance = (int) Pixel.colorDistance(currentPixel.getColor(), 
				 northPixel.getColor());
	 }
	 
	 if (leftPixel!= null) {
		 leftDistance = (int) Pixel.colorDistance(currentPixel.getColor(), leftPixel.getColor());
	 }
	 return ((northDistance > threshold) || (leftDistance > threshold));
 	}
 
 //////////////////////////////// Blur //////////////////////////////////
 /**
  * Blurs this Picture. To achieve this, the algorithm takes a pixel, and
  * sets it to the average value of all the pixels in a square of side (2 *
  * blurThreshold) + 1, centered at that pixel. For example, if blurThreshold
  * is 2, and the current pixel is at location (8, 10), then we will consider
  * the pixels in a 5 by 5 square that has corners at pixels (6, 8), (10, 8),
  * (6, 12), and (10, 12). If there are not enough pixels available -- if the
  * pixel is at the edge, for example, or if the threshold is larger than the
  * image -- then the missing pixels are ignored, and the average is taken
  * only of the pixels available.
  * 
  * The red, blue, green and alpha values should each be averaged separately.
  * 
  * @param blurThreshold
  *            Size of the blurring square around the pixel.
  * 
  * @return A new Picture that is the blurred version of this Picture, using
  *         a blurring square of size (2 * threshold) + 1.
  */
 public Picture blur(int blurThreshold) {
	 int pictureHeight = this.getHeight();
	 int pictureWidth = this.getWidth();
	 Picture newPicture = new Picture(pictureWidth, pictureHeight);
  
	 for(int x = 0; x < pictureWidth; x++) {
		 for(int y = 0; y < pictureHeight; y++) {
			 Pixel[] pixelsInRange = this.getPixels(x - blurThreshold, 
					 y - blurThreshold, x + blurThreshold, y + blurThreshold);
			 Color avgColor = Picture.averageColor(pixelsInRange);
			 Pixel newPixel = newPicture.getPixel(x, y);
			 newPixel.setColor(avgColor);
		 }
	 }
	 return newPicture;
 	}
 
 /**
  * Helper method to find the average color for a set of Pixels
  * 
  * @param pixelArray is an Array of Pixels with valid Colors, from which the Color
  * will be average
  */
 	private static Color averageColor(Pixel[] pixelArray) {
 		int greenTotal = 0;
 		int blueTotal = 0;
 		int redTotal = 0;
 		int alphaTotal = 0;
 		int pixelCount = 0;
 		
 		for (Pixel pix : pixelArray) {
 			if (pix != null)
 			{
 				greenTotal += pix.getGreen();
 				blueTotal  += pix.getBlue();
 				redTotal   += pix.getRed();
 				alphaTotal += pix.getAlpha();
 				pixelCount++;
 			}
 		}
 		
 		int greenAvg = greenTotal / pixelCount;
 		int blueAvg  = blueTotal  / pixelCount;
 		int redAvg   = redTotal   / pixelCount;
 		int alphaAvg = alphaTotal / pixelCount;
 		return new Color(redAvg, greenAvg, blueAvg, alphaAvg);
 	}
 	
 /**
  * Helper method to collect the Pixels within a certain range into 
  * a Pixel array. 
  * 
  * @param xLeft is the x position of the top left Pixel
  * @param yTop  is the y position of the top left Pixel
  * @param xRight is the x position of the right most Pixel 
  * @param yBottom is the y position of the bottom most Pixel
  * @return
  */
 	private Pixel[] getPixels(int xLeft, int yTop, int xRight, int yBottom) {
 		int pixelCount = (xRight - xLeft + 1)*(yBottom - yTop + 1);
 		Pixel[] pixelsInRange = new Pixel[pixelCount];
 		int pixelArrayIndex = 0;
  
 		for (int x = xLeft; x <= xRight; x++ ) {
 			for (int y = yTop; y <= yBottom; y++, pixelArrayIndex++) {
 				pixelsInRange[pixelArrayIndex] = safeGetPixel(x, y);
 			}
 		}
 		return pixelsInRange;
 	}
 	
 /**
  * Returns true if the given Pixel (x,y) is valid (within the Picture)
  * 
  * @param x is the potential x position of the Pixel
  * @param y is the potential y position of the Pixel
  * @return true if the Pixel (x,y) is a valid Pixel in the Picture
  */
 	private Pixel safeGetPixel(int x, int y) {
 		if (safeX(x) && safeY(y)) {
 			return this.getPixel(x, y);
 		}
 		
 		else {
 			return null;
 		}
 	}
 	
 /**
  * Returns true if x is within the Picture
  * 
  * @param x is the potential x value of the Pixel
  * @return true if x is within the Picture and false otherwise
  */
 private boolean safeX(int x) {
	 return (x < this.getWidth()) && (x >= 0);
	 }
 /**
  * Returns true if y is within the Picture
  * 
  * @param y is the potential y value of the Pixel
  * @return true if y is within the Picture and false otherwise
  */
 private boolean safeY(int y) {
	 return (y < this.getHeight()) && (y >= 0);
	 }

 //////////////////////////////// Paint Bucket //////////////////////////////////
 /**
  * @param x x-coordinate of the pixel currently selected.
  * @param y y-coordinate of the pixel currently selected.
  * @param threshold Threshold within which to delete pixels.
  * @param newColor New color to color pixels.
  * 
  * @return A new Picture where all the pixels connected to the currently
  *  selected pixel, and which differ from the selected pixel within the
  *  provided threshold (in terms of color distance), are colored with
  *  the new color provided. 
  */
 public Picture paintBucket(int x, int y, int threshold, Color newColor) {
	 Picture newPicture = new Picture (this);
	 Picture visitedMask = new Picture (this.getWidth(), this.getHeight());
	 Color oldColor = this.getPixel(x, y).getColor();
	 LinkedList<Point> queue = new LinkedList<Point>();
	 
	 queue.add(new Point(x, y));
	 newPicture.paintBucketHelper(oldColor, newColor, threshold, visitedMask, queue);
	 return newPicture;
	 }
 
 /**
  * Helper method for implementing the paint bucket. 
  * 
  * This doesn't work if you just do a simple depth first search - (which is kind of awesome!) 
  * @param oldColor
  * @param newColor
  * @param threshold
  * @param visitedMask
  * @param queue
  */
 private void paintBucketHelper(Color oldColor, Color newColor, 
		 int threshold, Picture visitedMask, LinkedList<Point> queue) {
	 while(!queue.isEmpty()) {
		 Point currentPoint = queue.removeFirst();
		 int x = currentPoint.x;
		 int y = currentPoint.y;
		 Pixel currentPixel = this.getPixel(x, y);
		 
		 if (Picture.withinThreshold(oldColor, currentPixel.getColor(), threshold)) {
			 currentPixel.setColor(newColor);
			 
			 for (int i = x -1; i <= x + 1; i++) {
				 for (int j = y -1; j <= y + 1; j++) {
					 if (!visitedMask.visited(i, j)) {
						 visitedMask.visit(i, j);
						 queue.add(new Point (i, j));
						 }
				 }
			 }
		 }
	 }
 	}
 
 /**
  * 
  * @param oldColor
  * @param currentColor
  * @param threshold
  * @return
  */
 private static boolean withinThreshold(Color oldColor, 
		 Color currentColor, int threshold) {
	 return threshold > Pixel.colorDistance(oldColor, currentColor);
	 }
 
 /**
  * 
  * @param x
  * @param y
  * @return
  */
 private boolean visited(int x, int y) {
	 Pixel p = this.safeGetPixel(x, y);
	 
	 if (p == null) {
		 return true;
	 }
	 return p.getColor().getBlue() == 0;
 	}
 
 /**
  * 
  * @param x
  * @param y
  */
 	private void visit(int x, int y) {
 		Pixel p = this.safeGetPixel(x, y);
 		
 		if (p == null) {
 			return;
 		}
 		
 		p.setColor(new Color (0, 0, 0));
 	}
 	
 //////////////////////////////// Main Method //////////////////////////////////
 public static void main(String[] args) {
  // Try this code as you start debugging... 
  //  Picture tiny   = Picture.loadPicture("Tiny.bmp");
  //  Picture tinyGray    = tiny.grayscale();
  //  tiny.explore(); // opens in the regular, zoomable window
  //  tinyGray.show(); // opens in a simpler window without the controls
  // This asks you to pick a file and then launches the PictureExplorer
	 Picture initialPicture = new Picture(
			 FileChooser.pickAFile(FileChooser.OPEN));
	 initialPicture.explore();
 	}


	} // End of Picture class
