package com.javaweb.util.core;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PatternUtil {
    
    public static boolean isPattern(String str,Pattern pattern) {
        Predicate<String> predicate = obj -> pattern.matcher(obj).matches();
        return predicate.test(str);
    }

}
