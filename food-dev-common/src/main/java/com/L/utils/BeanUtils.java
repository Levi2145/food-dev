package com.L.utils;

import java.lang.reflect.Field;

public class BeanUtils {

    public static <T> T copyProperties(Object source, Class<T> targetType) {
        try {
            T target = targetType.newInstance();
            for (Field tf : targetType.getDeclaredFields()) {
                try {
                    Field sf = source.getClass().getDeclaredField(tf.getName());
                    sf.setAccessible(true); tf.setAccessible(true); tf.set(target, sf.get(source));
                } catch (NoSuchFieldException ignored) { }
            }
            return target;
        } catch (Exception e) { throw new IllegalArgumentException("无法复制属性", e); }
    }
    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) return;
        for (Field tf : target.getClass().getDeclaredFields()) {
            try { Field sf = source.getClass().getDeclaredField(tf.getName()); sf.setAccessible(true); tf.setAccessible(true); tf.set(target, sf.get(source)); } catch (Exception ignored) { }
        }
    }

}
