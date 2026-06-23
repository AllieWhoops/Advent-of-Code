package Util;

import java.util.List;

public class NumberStuff {
    public static long DigitsToLong(List<Integer> digits){
        long res = 0;
        for(int i = 0; i < digits.size(); i++){
            res += digits.get(i) * Math.pow(10, digits.size() - 1 - i);
        }
        return res;
    }
}
