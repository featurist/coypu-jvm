package Coypu;

public class StringJoiner {
    public static String join(String separator, String[] toJoin) {
        StringBuilder joined = new StringBuilder();
        for(int i = 0 ; i < toJoin.length; i ++) {
            joined.append(toJoin[i]);
            if (i < toJoin.length - 1)
                joined.append(separator);
        }
        return joined.toString();
    }
}
