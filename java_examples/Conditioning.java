public class Conditioning{
    public static void main(String[] args){
        int x = 1;
        while (x < 10) {
            System.out.println("Inside while loop");
            if (x == 3) {
                System.out.println("x is 3");
            } else {
                System.out.println("x is not 3");
                System.out.println("x is " + x);
            
            }
            x = x + 1;
        }
    }
}