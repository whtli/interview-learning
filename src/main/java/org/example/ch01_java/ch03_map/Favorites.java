package org.example.ch01_java.ch03_map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: whtli
 * @date: 2023/10/11
 * @description: 类型安全的异构容器 （typesafe heterogeneous container）
 */
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();


    public <T> void putFavorite(Class<?> type, T instance) {
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        Integer favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
        System.out.printf("%s %x %s\n", favoriteString, favoriteInteger, favoriteClass.getSimpleName());
    }
}
