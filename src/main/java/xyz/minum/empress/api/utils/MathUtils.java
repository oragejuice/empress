package xyz.minum.empress.api.utils;

public class MathUtils {

    public static int true_mod(int x, int a){
        return (((x % a) + a) % a);
    }
}
