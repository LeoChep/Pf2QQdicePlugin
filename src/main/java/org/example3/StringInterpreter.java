package org.example3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringInterpreter {
    public static final StringInterpreter INSTANCE = new StringInterpreter();

    public boolean parse(String targetName, String target, String arg) {
        target=target.toUpperCase();
        targetName=targetName.toUpperCase();
        arg=arg.toUpperCase();
        String args[] = arg.split(" ");
        List<String> contains = new ArrayList<>();
        boolean isExact = false;
        boolean haveContains = false;
        Map<String, String> followMap = new HashMap<>();
        String searchName = args[0];
        if (args[args.length - 1].equals("EXACT")) isExact = true;
        int index = 0;
        while (index <= args.length - 1&&!args[index].equals("CONTAINS")) index++;
        if (index<args.length-1) haveContains = true;
        index ++;
        while (haveContains && index <= args.length - 1) {
            if (!args[index].equals("EXACT") && !args[index].equals("FOLLOW")) {
                if (index + 2 <= args.length && args[index + 1].equals("FOLLOW")) {
                    contains.add(args[index]);
                    followMap.put(args[index], args[index + 2]);
                    index += 3;
                } else {
                    contains.add(args[index]);
                    index++;
                }
            }
        }

        boolean isNameRight = false;
        if (args[0].equals("CONTAINS")||args[0].equals("FOLLOW")) isNameRight=true;
        if (isExact && targetName.equals(searchName)) isNameRight = true;
        if (!isExact && targetName.contains(searchName)) isNameRight = true;

        boolean isContainsRight = true;
        boolean isFollowRight = true;


        for (String contain : contains) {
            if (!target.contains(contain)) isContainsRight = false;
            if (followMap.get(contain) != null) {
                int behindIndex = target.indexOf(followMap.get(contain));
                int followIndex = target.lastIndexOf(contain);
                boolean haveBehind=behindIndex >= 0;
                boolean isOrderRigth=followIndex >= behindIndex;
                boolean isFollowNear=followIndex-behindIndex-contain.length()<=20;
                if (!(haveBehind&&isOrderRigth&&isFollowNear)) isFollowRight = false;
            }

        }

        return (isNameRight && isContainsRight && isFollowRight);
    }
}
