package com.server.pin.global.common.validator;

public class RegexUtils {
    public static boolean hasSpecialChar(String str){
        return str.matches ("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*");
    }
}
