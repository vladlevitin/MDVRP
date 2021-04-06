import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        //System.out.println(Math.random());
        ArrayList<Integer> integers=new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        System.out.println(integers.subList(0,1));
    }
}
