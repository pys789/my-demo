package cn.pys.Test;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @Description
 * @Date 2021/1/7 15:48
 * @Created by pengys
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("百丽");
        list.add("百丽/BELLE's");
        List<String> tmp = transformSingleQuotes(list);

        System.out.println(String.join("','", list));
        System.out.println(String.join("','", tmp));

    }

    public static List<String> transformSingleQuotes(List<String> list) {
        List<String> ret = new ArrayList<>();
        if (list!=null) {
            for (String str : list) {
                if (str.contains("'")) {
                    ret.add(str.replaceAll("'", "''"));
                } else {
                    ret.add(str);
                }
            }
        }
        return ret;
    }
}
