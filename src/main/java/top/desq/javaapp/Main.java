package top.desq.javaapp;

import top.desq.javaapp.model.Brand;


public class Main {

    public static void main(String[] args) {
        Brand brand = Brand.valueOf("VW");
        System.out.println(brand);
    }

}
