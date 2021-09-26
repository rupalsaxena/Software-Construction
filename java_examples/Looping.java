public class Looping{
    public static void main(String[] args){
        int x = 1;
        System.out.println("Begin looping");
        while (x < 3) {
            System.out.println("In the loop");
            System.out.println("Value of x is " + x);
            x = x + 1;
        }
        System.out.println("Loop will end here");
    }
}