package com.dsd.lottery.missanalyse.constain.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.dsd.lottery.missanalyse.constain.IMissComparator;
import com.dsd.lottery.util.ResourceUtil;

/**
 * 比较器工厂(享元模式，不过需要从内部去加共享对象)
 * 
 * @author daishengda
 *
 */
public class MissComparatorFactory {

    private static final Map<String, IMissComparator> factory =
            new ConcurrentHashMap<String, IMissComparator>();

    /**
     * 这个工厂类map的key格式，2_3表示2星组3
     */
    public static final String KEY_FORMAT = "{0}_{1}";

    /**
     * 默认的实例
     */
    public static final String DEFAULT_KEY = "5_4";

    private MissComparatorFactory() {

    }

    static {
        init();
    }

    public static IMissComparator getMissComparator(String key) {
        IMissComparator comparator = factory.get(key);
        if (comparator == null) {
            comparator = new GeneralMissComparator();
            factory.put(key, comparator);
        }
        return comparator;
    }

    private static void init() {
        // 2星组3实例
        factory.put(ResourceUtil.format(KEY_FORMAT, 2, 3), new TwentythreeComparator());
        // 3星组6实例
        factory.put(ResourceUtil.format(KEY_FORMAT, 3, 6), new ThirtysixComparator());

        factory.put(DEFAULT_KEY, new GeneralMissComparator());
    }
}
