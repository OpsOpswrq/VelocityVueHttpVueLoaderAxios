package cn.mtttt.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUtils {
    public static ArrayList<String> getRoles(int i){
        ArrayList<String> roles  = new ArrayList<>();
        if(i == 1){
            roles.add("vip1");
        }
        if(i == 2) {
            roles.add("vip2");
        }
        if(i == 3){
            roles.add("vip3");
        }
        return roles;
    }
}
