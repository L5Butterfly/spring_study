package com.oxygen.mybatis.v2.cache;


/**
 * 计算SQL查询的缓存Key,Key的生成算法和思想
 * @author:oxygen
 * @date 20200515
 */
public class CacheKey {

    /**
     *  MyBatis也是这么设计的思想
     *  17和37都是质数，可以减少hash碰撞
     */
    private static final int DEFAULT_HASHCODE = 17; // 默认哈希值
    private static final int DEFAULT_MULTIPLIER = 37; // 倍数

    /**
     * hashCode
     */
    private int hashCode;

    /**
     * 调用update方法的次数
     */
    private int count;

    /**
     * 计算的乘法倍数
     */
    private int multiplier;

    /**
     * 构造函数
     */
    public CacheKey() {
        this.hashCode = DEFAULT_HASHCODE;
        this.count = 0;
        this.multiplier = DEFAULT_MULTIPLIER;
    }

    /**
     * 返回CacheKey的值
     * @return
     */
    public int getCode() {
        return hashCode;
    }

    /**
     * 计算CacheKey中的HashCode，减少哈希碰撞
     * @param object
     */
    public void update(Object object) {
        int baseHashCode = object == null ? 1 : object.hashCode();
        count++;
        baseHashCode *= count;
        hashCode = multiplier * hashCode + baseHashCode;
    }
}
