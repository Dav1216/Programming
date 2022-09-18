import java.util.Scanner;

class Circle{

    double xPoint;
    double yPoint;
    double radius;

    public Circle(double circleXPoint, double circleYPoint, double radius){
        
        this.xPoint = circleXPoint;
        this.yPoint = circleYPoint;
        this.radius = radius;

    }

    public boolean run(double x, double y){

       double d = Math.sqrt((this.xPoint - x) * (this.xPoint - x) + (this.yPoint - y) * (this.yPoint - y));
       return d <= this.radius;
       
    }
    public static void main(String[] args){

        Scanner myObj = new Scanner(System.in);

        double xValueC1 = myObj.nextDouble();
        double yValueC1 = myObj.nextDouble();
        double valueOfRadiusC1 = myObj.nextDouble();
        double xValueC2 = myObj.nextDouble();
        double yValueC2 = myObj.nextDouble();
        double valueOfRadiusC2 = myObj.nextDouble();
        double xValueP = myObj.nextDouble();
        double yValueP = myObj.nextDouble();

        
        if(valueOfRadiusC1 < 0 || valueOfRadiusC2 < 0){
            System.out.println("input error");
        }else {

            boolean result1 = (new Circle(xValueC1, yValueC1, valueOfRadiusC1)).run(xValueP, yValueP);
            boolean result2 = (new Circle(xValueC2, yValueC2, valueOfRadiusC2)).run(xValueP, yValueP);
            
            if(result1 && result2){
                System.out.println("The point lies in both circles");
            }else if(result1){
                System.out.println("The point lies in the first circle");
            }else if(result2){
                System.out.println("The point lies in the second circle");
            }else{
                System.out.println("The point does not lie in either circle");
            }
        } 
    }
 }

