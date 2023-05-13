package epi.chapter5;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
public class RectangleIntersection {
  @EpiUserType(ctorParams = {int.class, int.class, int.class, int.class})
  public static class Rect {
    int x, y, width, height;

    public Rect(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rect rectangle = (Rect)o;

      if (x != rectangle.x) {
        return false;
      }
      if (y != rectangle.y) {
        return false;
      }
      if (width != rectangle.width) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + width + ", " + height + "]";
    }
  }
  @EpiTest(testDataFile = "rectangle_intersection.tsv")
  public static Rect intersectRectangle(Rect r1, Rect r2) {
    // TODO - you fill in here.
	  
	//from rectangle r1
	int x1=r1.x;
	int x2=r1.x+r1.width;
	int y1=r1.y;
	int y2=r1.y+r1.height;
	
	//from rectangle r2
	int x3=r2.x;
	int x4=r2.x+r2.width;
	int y3=r2.y;
	int y4=r2.y+r2.height;
	
	boolean isXAxisIntersect=false;
	boolean isYAxisIntersect=false;
	
	/*
	 * This problem is a variant of interval intersection here every rectangles x
	 * Coordinates form one interval.class Every rectangles y coordinate forms
	 * another interval
	 * 
	 * The below block finds out if two interval intersect with each other.
	 */
	if ((x3<=x2)&&(x1<=x4))
		isXAxisIntersect=true;
	if ((y3<=y2)&&(y1<=y4))
		isYAxisIntersect=true;
	
	if(isXAxisIntersect && isYAxisIntersect)
	{
		int x5 = Math.max(x1, x3);
		int x6 = Math.min(x2, x4);
		int y5 = Math.max(y1, y3);
		int y6 = Math.min(y2, y4);
		return new Rect(x5, y5, x6-x5, y6-y5);
	}
	else
		//in case there is no intersection we set width and height as -1 and -1
		return new Rect(0, 0, -1, -1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RectangleIntersection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
