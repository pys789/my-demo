package cn.pys.contants;

/**
 * @Description
 * @Date 2020/11/10 16:45
 * @Created by pengys
 */
public class SqlCondition {
    /**
     * 等于
     */
    public static final String EQUAL = "%s=#{%s}";
    /**
     * 不等于
     */
    public static final String NOT_EQUAL = "%s&lt;&gt;#{%s}";
    /**
     * % 两边 %
     */
    public static final String LIKE = "%s LIKE CONCAT('%%',#{%s},'%%')";
    /**
     * % 左
     */
    public static final String LIKE_LEFT = "%s LIKE CONCAT('%%',#{%s})";
    /**
     * 右 %
     */
    public static final String LIKE_RIGHT = "%s LIKE CONCAT(#{%s},'%%')";
}
