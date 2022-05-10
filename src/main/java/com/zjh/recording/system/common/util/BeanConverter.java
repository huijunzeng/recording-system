package com.zjh.recording.system.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanMap;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author zjh
 * @Description bean工具类
 * @date 2021/01/15 17:37
 */
public class BeanConverter {

    /**
     * 拷贝指定源列表 到 指定目标bean类型，并返回目标bean列表
     * 用法：List<UserDTO> userDtos = BeanConverter.copyForList(users, UserDTO.class);
     *
     * @param sourceList  资源bean 列表
     * @param targetClazz 目标bean 类型
     * @param <S>         资源bean类型
     * @param <T>         目标bean类型
     * @return 返回指定目标bean类型的列表
     */
    public static <S, T> List<T> copyForList(List<S> sourceList, Class<T> targetClazz) {
        return copyForList(sourceList, targetClazz, false);
    }

    /**
     * 拷贝指定源列表 到 指定目标bean类型，并返回目标bean列表
     * 用法：List<UserDTO> userDtos = BeanConverter.copyForList(users, UserDTO.class, false);
     *
     * @param sourceList           资源bean 列表
     * @param targetClazz          目标bean 类型
     * @param isIgnoreNullProperty 拷贝时是否忽略资源bean中的null字段
     * @param <S>                  资源bean类型
     * @param <T>                  目标bean类型
     * @return 返回指定目标bean类型的列表
     */
    public static <S, T> List<T> copyForList(List<S> sourceList, Class<T> targetClazz, boolean isIgnoreNullProperty) {
        if (Objects.isNull(sourceList) || Objects.isNull(targetClazz)) {
            return null;
        }
        return sourceList.stream().filter(Objects::nonNull).map(s -> {
            T t = null;
            try {
                //使用反射构建对象
                t = targetClazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (Objects.nonNull(t)) {
                copyForBean(s, t, isIgnoreNullProperty);
            }
            return t;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 拷贝指定源列表 到 指定目标bean类型，并返回目标bean列表
     * 用法：List<UserDTO> userDtos = BeanConverter.copyForList(users, UserDTO::new);
     *
     * @param sourceList     资源bean 列表
     * @param targetSupplier 目标bean对象提供者
     * @param <S>            资源bean类型
     * @param <T>            目标bean类型
     * @return 返回指定目标bean类型的列表
     */
    public static <S, T> List<T> copyForList(List<S> sourceList, Supplier<T> targetSupplier) {
        return copyForList(sourceList, targetSupplier, false);
    }

    /**
     * 拷贝指定资源列表 到 指定目标bean类型，并返回目标bean列表
     * 用法：List<UserDTO> userDtos = BeanConverter.copyForList(users, UserDTO::new, false);
     *
     * @param sourceList           资源bean 列表
     * @param targetSupplier       目标bean对象提供者
     * @param isIgnoreNullProperty 拷贝时是否忽略资源bean中的null字段
     * @param <S>                  资源bean类型
     * @param <T>                  目标bean类型
     * @return 返回指定目标bean类型的列表
     */
    public static <S, T> List<T> copyForList(List<S> sourceList, Supplier<T> targetSupplier, boolean isIgnoreNullProperty) {
        if (Objects.isNull(sourceList) || Objects.isNull(targetSupplier)) {
            return null;
        }
        return sourceList.stream().filter(Objects::nonNull).map(s ->
                copyForBean(s, targetSupplier, isIgnoreNullProperty))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 拷贝指定资源bean 到目标bean
     * 用法：UserDTO userDto = BeanConverter.copyForBean(user, UserDTO::new);
     *
     * @param source         资源bean
     * @param targetSupplier 目标bean对象提供者
     * @param <S>            资源bean类型
     * @param <T>            目标bean类型
     * @return
     */
    public static <S, T> T copyForBean(S source, Supplier<T> targetSupplier) {
        return copyForBean(source, targetSupplier, false);
    }

    /**
     * 拷贝指定资源bean 到目标bean
     * 用法：UserDTO userDto = BeanConverter.copyForBean(user, UserDTO::new, false);
     *
     * @param source               资源bean
     * @param targetSupplier       目标bean
     * @param isIgnoreNullProperty 拷贝时是否忽略资源bean中的null字段
     * @param <S>                  资源bean类型
     * @param <T>                  目标bean类型
     * @return
     */
    public static <S, T> T copyForBean(S source, Supplier<T> targetSupplier, boolean isIgnoreNullProperty) {
        if (Objects.isNull(targetSupplier) || Objects.isNull(source)) {
            return null;
        }
        T target;
        target = targetSupplier.get();
        if (Objects.nonNull(target)) {
            copyForBean(source, target, isIgnoreNullProperty);
        }
        return target;
    }

    /**
     * 拷贝指定资源bean 到目标bean
     * 用法：UserDTO userDto = BeanConverter.copyForBean(user, UserDTO::new);
     *
     * @param source 资源bean
     * @param target 目标bean
     * @param <S>    资源bean类型
     * @param <T>    目标bean类型
     * @return
     */
    public static <S, T> T copyForBean(S source, T target) {
        return copyForBean(source, target, false);
    }

    /**
     * 拷贝指定资源bean 到目标bean
     * 用法：UserDTO userDto = BeanConverter.copyForBean(user, UserDTO::new, false);
     *
     * @param source               资源bean
     * @param target               目标bean
     * @param isIgnoreNullProperty 拷贝时是否忽略资源bean中的null字段
     * @param <S>                  资源bean类型
     * @param <T>                  目标bean类型
     * @return
     */
    public static <S, T> T copyForBean(S source, T target, boolean isIgnoreNullProperty) {
        if (Objects.isNull(target) || Objects.isNull(source)) {
            return null;
        }
        if (isIgnoreNullProperty) {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } else {
            BeanUtils.copyProperties(source, target);
        }
        return target;
    }

    /**
     * 拷贝指定资源bean 到目标bean
     * 用法：UserDTO userDto = BeanConverter.copyForBean(user, UserDTO::new, new String[]{"id"});
     *
     * @param source               资源bean
     * @param targetSupplier       目标bean
     * @param ignoreProperties     拷贝时忽略资源bean中的字段
     * @param <S>                  资源bean类型
     * @param <T>                  目标bean类型
     * @return
     */
    public static <S, T> T copyForBean(S source, Supplier<T> targetSupplier, String... ignoreProperties) {
        if (Objects.isNull(targetSupplier) || Objects.isNull(source)) {
            return null;
        }
        T target;
        target = targetSupplier.get();
        if (Objects.nonNull(target)) {
            copyForBean(source, target, ignoreProperties);
        }
        return target;
    }

    /**
     * 拷贝指定资源bean 到目标bean
     * 用法：UserDTO userDto = BeanConverter.copyForBean(user, UserDTO.class, new String[]{"id"});
     *
     * @param target              目标bean
     * @param source              资源bean
     * @param ignoreProperties    拷贝时忽略资源bean中的字段
     * @param <S>                 资源bean类型
     * @param <T>                 目标bean类型
     * @return
     */
    public static <S, T> T copyForBean(S source, T target, String... ignoreProperties) {
        if (Objects.isNull(target) || Objects.isNull(source)) {
            return null;
        }
        BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }

    /**
     * 获取值为null的属性
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * bean 转 map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        Map<String, Object> map = new HashMap<>();
        beanMap.forEach((key, value) -> {
            map.put(String.valueOf(key), value);
        });
        return map;
    }

    /**
     * map 转 bean
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
        T bean = clazz.newInstance();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 对象list 转 map list
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map;
            T bean;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * map list 转 对象list
     *
     * @param maps
     * @param clazz
     * @return
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                T bean = mapToBean(map, clazz);
                list.add(bean);
            }
        }
        return list;
    }
}
