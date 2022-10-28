package com.xiaomi.stream;

//import com.sun.tools.javac.util.List;

import java.util.stream.Stream;

/**
 * Stream综合案例
 * 现在有两个 ArrayList 集合存储队伍当中的多个成员姓名，要求使用传统的for循环（或增强for循环）依次进行以下
 * 若干操作步骤：
 * 1. 第一个队伍只要名字为3个字的成员姓名；
 * 2. 第一个队伍筛选之后只要前3个人；
 * 3. 第二个队伍只要姓张的成员姓名；
 * 4. 第二个队伍筛选之后不要前2个人；
 * 5. 将两个队伍合并为一个队伍；
 * 6. 根据姓名创建 Person 对象；
 * 7. 打印整个队伍的Person对象信息。
 *
 * @author xiaomi
 */
public class GetStreamDemo07 {
    public static void main(String[] args) {
        // 第一个队伍
//        List<String> list1 = List.of("丰臣秀吉", "德川家康", "织田信长", "朱重八", "朱五四", "朱初一", "朱四九", "朱百六");
        // 第二个队伍
//        List<String> list2 = List.of("岸信介", "伊藤博文", "山本五十六", "李世民", "李隆基", "李治", "李显", "李旦", "朱厚照", "朱高炽", "赵匡胤");


        // 第一个队伍只要名字为3个字的成员姓名
        // 第一个队伍筛选之后只要前3个人
//        Stream<String> stream1 = list1.stream().filter(x -> x.length() == 3).limit(3);

        // 第二个队伍只要姓张的成员姓名
        // 第二个队伍筛选之后不要前2个人
//        Stream<String> stream2 = list2.stream().filter(s -> s.startsWith("李")).skip(2);

        // 将两个队伍合并为一个队伍
//        Stream<String> stream12 = Stream.concat(stream1, stream2);
        // 根据姓名创建person对象
        // 打印整个队伍的person对象信息
//        stream12.map(Person::new).forEach(System.out::println);
    }
}
