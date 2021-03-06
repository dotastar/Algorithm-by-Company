package com.airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * John,Smith,john.smith@gmail.com,Los Angeles,1
 * Jane,Roberts,janer@msn.com,"San Francisco, CA",0
 * "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
 * """Alexandra Alex"""
 *
 * John|Smith|john.smith@gmail.com|Los Angeles|1
 * Jane|Roberts|janer@msn.com|San Francisco, CA|0
 * Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
 * "Alexandra Alex"
 *
 */
public class CSVParser2 {
    public static List<String> parseCSV(String str) {
        List<String> res = new ArrayList<String>();
        boolean inQuote = false;
        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(inQuote) {
                if(str.charAt(i) == '"') {
                    if(i == str.length() - 1) {
                        res.add(buffer.toString());
                        return res;
                    } else if(str.charAt(i + 1) == '"') {
                        buffer.append('"');
                        i++;
                    } else {
                        res.add(buffer.toString());
                        buffer.setLength(0);
                        inQuote = false;
                        i++;
                    }
                } else{
                    buffer.append(str.charAt(i));
                }
            } else {
                if(str.charAt(i) == '"') {
                    inQuote = true;
                } else if( str.charAt(i) == ',') {
                    res.add(buffer.toString());
                    buffer.setLength(0);
                } else {
                    buffer.append(str.charAt(i));
                }
            }
        }
        if(buffer.length() > 0) res.add(buffer.toString());
        return res;
    }

    public static String printStr(List<String> list) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < list.size()-1; i++) {
            res.append(list.get(i));
            res.append('|');
        }
        res.append(list.get(list.size()-1));
        return res.toString();
    }

    public static void main(String[] args){
        //#1
        List<String> output = parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1");
        String strOutput = printStr(output);
        System.out.println(strOutput);

        //#2
        output = parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
        strOutput = printStr(output);
        System.out.println(strOutput);
        output = parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1");
        strOutput = printStr(output);
        System.out.println(strOutput);
    }
}
