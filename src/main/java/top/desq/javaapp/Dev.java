package top.desq.javaapp;

import java.util.HashMap;
import java.util.HashSet;

public class Dev {
    public static void sum() {
        System.out.println("first");
    }

    public void plus() {

    }
}

class Client extends Dev{

    @Override
    public void plus() {
        super.plus();
    }

    public static void sum() {
        System.out.println("second");
    }

    public void go() {
        super.sum();
    }

    public static void main(String[] args) {
        //Client client = new Client();
        //client.go();

        HashSet<Integer> set = new HashSet<Integer>();
        set.add(null);
        set.add(null);
        set.add(1);
        set.add(1);
        //System.out.println(set);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(null, 1);
        map.put(null, 1);
        System.out.println(map);

    }
}