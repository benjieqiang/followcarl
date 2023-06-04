import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-04  15:38
 * @Description: https://zhuanlan.zhihu.com/p/54004622
 * 实现Comparator接口，必须实现下面这个函数：
 *
 * @Override
 * public int compare(CommentVo o1, CommentVo o2) {
 *            return o1.getTime().compareTo(o2.getTime());
 * }
 * 这里o1表示位于前面的对象，o2表示后面的对象
 *
 * 返回-1（或负数），表示不需要交换01和02的位置，o1排在o2前面，asc
 * 返回1（或正数），表示需要交换01和02的位置，o1排在o2后面，desc
 * @Version: 1.0
 */
public class CompareTest {
    @Test
    //通过匿名内部类实现排序
    public void testCompare() {
        Integer[] nums = {9,4,3,11,23,2,1};
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(nums));
    }

    @Test
    //lambda表达式来实现降序
    public void testCompare2() {
        Integer[] nums = {9,4,3,11,23,2,1};
        Arrays.sort(nums, (a, b) -> (b - a));
        System.out.println(Arrays.toString(nums));
    }

    @Test
    // Int数组转Integer数组，完成排序之后转成int数组；
    public void testInt2Integer() {
        int[] nums = {9,4,3,11,23,2,1};
//        //数组元素转换为数值流
//        IntStream stream = Arrays.stream(nums);
//        //流中元素全部装箱
//        Stream<Integer> st = stream.boxed();
//        //将流转换为数组
//        Integer[] num = st.toArray(Integer[]::new);


        //合一
        Integer[] integers = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(integers, (a, b) -> (b - a));
        System.out.println(Arrays.toString(integers));

        // 或者
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(nums));
    }

    @Test

    public void testIntegerAbsSort() {
        int[] nums = {2,-3,-1,5,-4};
        Integer[] integers = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(integers, (a, b) -> (Math.abs(b) - Math.abs(a)));
        System.out.println(Arrays.toString(integers));


    }
}
