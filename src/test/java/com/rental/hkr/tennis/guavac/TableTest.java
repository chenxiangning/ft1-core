package com.rental.hkr.tennis.guavac;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 15:12
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description: Table代表一个特殊的映射，其中两个键可以在组合的方式被指定为单个值。它类似于创建映射的映射。
 */
public class TableTest {
    @Test
    public void democ() {
        Table table = HashBasedTable.create();

        table.put(1, "id", "001");
        table.put(1, "name", "陈湘宁");
        table.put(1, "sex", "男");

        table.put(2, "id", "002");
        table.put(2, "name", "秦立虎");
        table.put(2, "sex", "男");

        table.put(3, "id", "003");
        table.put(3, "name", "短长于");
        table.put(3, "sex", "男");
        table.put(4, "sex", "男");

        System.out.println(table);

        Map lihuInfo = table.row(2);
        System.out.println(lihuInfo);

        Set rows = table.rowKeySet();
        System.out.println(rows);

        Map columnMap = table.columnMap();
        System.out.println(columnMap);

        Map names = table.column("name");
        System.out.println(names);
        System.out.println(names.get(1));

        System.out.println(table.size());

        System.out.println(table.containsValue("男"));
        System.out.println(table.containsRow(4));
        System.out.println(table.containsColumn("name"));

        System.out.println(table.get(2, "id"));


    }
}
