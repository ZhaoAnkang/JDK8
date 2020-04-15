package per.study.map;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 一个映射键-值的对象。一个Map不能包含重复的键。
 * 每一个键最多只能映射一个值
 *
 * 这个接口代替了Dictionary类，Dictionary是一个完全抽象的类而不是接口
 *
 * Map接口提供了三种集合形式的访问，一个Map的内容分别可以以键的Set形式访问，
 * 值的Collection形式访问，以及键值对的Set形式访问。一个map的顺序被定义成这个map集合
 * 的迭代器访问并返回他们的元素的顺序。一些Map的实现，例如TreeMap类，能确保他们的顺序。
 * 而另外一些如HashMap的类则不能。
 *
 * 注意：如果使用可变对象作为Map的键则必须非常小心。如果改变了作为Map的键的对象并影响了equals的比较方式，
 * 则无法明确map的行为。
 *
 * 所有通用用途的map实现类应当提供两个标准构造方法：一个用于创造一个空Map的返回void的空参数的构造方法，
 * 以及一个构造与它们参数相同的键值映射的新Map的单Map类型参数的构造方法。
 */
public interface Map<K,V> {
    //查询 操作

    /**
     * 返回这个Map的键值映射对的数量，如果Map容器元素数量超过Integer.MAX_VALUE，则返回Integer.MAX_VALUE。
     * @return
     */
    int size();

    /**
     * 如果这个Map没有键值映射对则返回true。
     * @return
     */
    boolean isEmpty();

    /**
     * 如果这个Map包含一个指定键的映射，则返回true。更正式地说，
     * 当且仅当这个Map包含一个如（key == null? k == null : key.equals(k)）的key则返回true。
     * 可以最多拥有一个这样的映射。
     * @param key
     * @return
     */
    boolean containsKey(Object key);

    /**
     * 如果这个Map有一个或者更多的键映射到指定的值则返回true。更正式地说，
     * 当且仅当这个Map包含至少一个映射到如(value == null ? v == null : value.equals(v))的value时则返回true。
     * 对于Map接口的绝大多数实现来说，这个操作将可能需要与其大小相应的线性时间。
     * @param value
     * @return
     */
    boolean containsValue(Object value);

    /**
     * 返回指定key映射的value，这个Map不包含这个key时则返回null。
     * 更正式地说，如果这个Map包含一个由key(k)到value(v)的映射，满足（key == null?k == null:
     * key.equals(k)）,则这个方法返回v，否则返回null。（最多只能拥有一个这样的映射）
     *
     * 如果这个Map允许null值，那么返回一个null值不能充分表明这个Map不包含这个键的映射；
     * 还有可能是这个Map的key明确地映射到null。containsKey的操作也许能区分这两种情况。
     * @param key
     * @return
     */
    V get(Object key);

    /**
     * 在这个Map将指定的key和指定的value联结起来（可选操作）。
     * 如果这个map以前包含这个key的映射，旧的value会被指定value替代。
     * （当且仅当这个m.containsKey(k)方法返回true时则说明这个Map m包含这个key k）。
     * @param key
     * @param value
     * @return
     */
    V put(K key,V value);

    /**
     * 从这个map移除一个key的映射，如果它存在的话。
     * 更正式地说，如果这个Map包含一个由key k 到value v的映射，且满足
     * （key ==null ? k ==null : key.equals(k)），那这个映射会被移除。（Map最多只能拥有一个这样的映射）
     *
     * 返回的值是这个map先前与这个key联结的value。如果这个Map包含的key没有映射，则返回null。
     *
     * 如果这个Map允许null值，那么返回一个null值不能充分表明这个Map不包含这个键的映射；
     * 还有可能是这个Map的key明确地映射到null。
     *
     * 一旦调用返回，这个Map将不包含指定key的映射。
     * @param key
     * @return
     */
    V remove(Object key);

    /**
     * 将指定Map的所有映射复制到这个Map（可选操作）。
     * 调用这个方法的效果相当于这个Map依次调用put(Object object) put(k,v)，
     * 其中k和v是指定Map的每一个key到value的映射。
     * 如果指定Map在操作过程被修改，则这个操作的行为将无法明确。
     * * @param m
     */
    void putAll(Map<? extends K,? extends  V> m);

    /**
     * 移除这个Map的所有映射（可选操作）。
     * 在调用返回后这个Map将会变成空的。
     */
    void clear();

    //视图
    /**
     * 返回这个Map的key的一个Set集合。
     * 这个Set以Map为依据，因此当Map发生变化时将会影响到Set，反之亦然。
     * 如果在对Set进行迭代时修改了Map，则迭代的结果是无法明确地。（除了迭代器自己的删除操作）。
     *这个Set支持元素移除，即从Map中移除对应映射，通过Iterator.remove,Set.remove,removeAll,retainAll,
     * 和clear操作。不支持add或者addAll操作。
     *
     * @return
     */
    Set<K> keySet();

    /**
     * 返回这个Map包含的value的Collection形式的视图。
     * 这个collection以Map为依据，因此当Map发生变化时将会影响到collection，反之亦然。
     * 如果在对collection进行迭代时修改了Map，则迭代的结果是无法明确地。（除了迭代器自己的删除操作）。
     * 这个collection支持元素移除，即从Map中移除对应映射，通过Iterator.remove,Collection.remove,removeAll,retainAll,
     * 和clear操作。不支持add或者addAll操作。
     * @return
     */
    Collection<V> values();

    /**
     * 返回这个Map包含的映射的Set形式的视图。
     * 这个Set以Map为依据，因此当Map发生变化时将会影响到Set，反之亦然。
     * 如果在对Set进行迭代时修改了Map，则迭代的结果是无法明确地。（除了迭代器自己的删除操作和迭代器返回的Map的Entry的setValue操作）。
     *这个Set支持元素移除，即从Map中移除对应映射，通过Iterator.remove,Set.remove,removeAll,retainAll,
     * 和clear操作。不支持add或者addAll操作。
     * @return
     */
    Set<Map.Entry<K,V>> entrySet();

    /**
     * 一个Map的项（key-value 对）。Map的entrySet方法返回一个Map的集合视图，其元素属于此类。
     * 从这个集合视图迭代是获取一个Map项引用的唯一方式。
     * 这些Map的项仅在迭代期间有效。
     * 更正式地说，如果迭代器返回项后此Map已被修改，则这个Map的项的行为是无法明确的。除了通过Map项的setValue操作。
     * @param <K>
     * @param <V>
     */
    interface Entry<K,V>{
        /**
         * 返回与此项对应的key
         * @return
         */
        K getKey();

        /**
         * 返回与此项对应的value。如果其依据的Map已经移除此映射（通过迭代器的remove操作），
         * 则此调用的结果将无法明确。
         * @return
         */
        V getValue();

        /**
         * 把此项对应的value替换成指定的value（可选操作）。（通过Map写入）
         * 如果其依据的Map已经移除此映射（通过迭代器的remove操作），
         * 则此调用的结果将无法明确。
         * @param value
         * @return
         */
        V setValue(V value);

        /**
         * 将指定的对象与此项进行相等性比较。
         * 如果给定的对象也是一个Map项且两个项表示相同的映射则返回true。
         * 更为确切地说，两个项e1和e2表示相同的映射
         * if
         *  (e1.getKey() == null ? e2.getKey() == null : e1.getKey().equals(e2.getKey()))
         *  (e1.getValue() == null ? e2.getValue() == null : e1.getValue().equals(e2.getValue()))
         *这将确保equals方法在Map.Entry接口不同的实现之间正确工作。
         * @param o
         * @return
         */
        boolean equals(Object o);

        /**
         * 返回这个Map项的哈希码的值。这个项e的哈希码被定义如下：
         * (e.getKey() == null? 0 : e.getKey().hashCode()) ^
         * (e,getValue() == null ? 0 : e.getValue().hashCode())
         * 这将确保e1.equals(e2)方法意味着对于任意两个项e1,e2之间都有e1.hashCode() == e2.hashCode()，
         * 其根据Object.hashCode()方法的通用契约。
         * @return
         */
        int hashCode();

        /**
         * 返回一个key的自然顺序比较的比较器Comparator
         * 返回的comparator是被序列化过的，当比较一个null key的项时则抛出空指针异常
         * @param <K>
         * @param <V>
         * @return
         */
        public static <K extends Comparable<? super K>,V> Comparator<Entry<K,V>> comparingByKey(){
            return (Comparator<Map.Entry<K,V>> & Serializable)
                    (c1,c2) -> c1.getKey().compareTo(c2.getKey());
        }

        /**
         * 返回一个value的自然顺序比较的比较器Comparator
         * 返回的comparator是被序列化过的，当比较一个null value的项时则抛出空指针异常
         * @param <K>
         * @param <V>
         * @return
         */
        public static <K,V extends Comparable<? super V>> Comparator<Map.Entry<K,V>> comparingByValue(){
            return (Comparator<Entry<K, V>> & Serializable)
                    (c1,c2) -> c1.getValue().compareTo(c2.getValue());
        }

        /**
         *返回一个比较器Comparator，该比较器使用给定的key进行比较
         * 如果指定的Comparator是序列化的，则返回的Comparator也是被序列化过的
         * @param cmp
         * @param <K>
         * @param <V>
         * @return
         */
        public static <K,V> Comparator<Map.Entry<K,V>> comparingByKey(Comparator<? super K> cmp){
            Objects.requireNonNull(cmp);
            return (Comparator<Entry<K, V>> & Serializable)
                    (c1,c2) -> cmp.compare(c1.getKey(),c2.getKey());
        }

        /**
         * 返回一个比较器Compatator，该比较器使用给定的value进行比较
         * 如果指定的Comparator是序列化的，则返回的Comparator也是被序列化过的
         * @param cmp
         * @param <K>
         * @param <V>
         * @return
         */
        public static <K,V> Comparator<Map.Entry<K,V>> comparingByValue(Comparator<? super V> cmp){
            Objects.requireNonNull(cmp);
            return (Comparator<Map.Entry<K,V>> & Serializable)
                    (c1,c2) -> cmp.compare(c1.getValue(),c2.getValue());
        }
    }

    /**
     * 比较给定的Object与这个Map是否相等。如果给定的对象也是一个Map，
     * 且这两个Map表示相同的映射，则返回true。
     * 更正式地说，两个Map，m1和m2，如果m1.entrySet().equals(m2.entrySet())则表示相同的映射。
     * 这确保了equals方法能够在Map接口不同的实现之间正常地工作。
     * @param o
     * @return
     */
    boolean equals(Object o);

    /**
     * 返回这个Map的hash码值，这个Map的hash码被定义成这个Map的entrySet()视图里
     * 的每一个entry的hash码的和。这确保了对于任意两个Map，m1和m2，m1.equals(m2)意味着
     * m1.hashCode() == m2.hashCode()，依据Object的hashCode的通用约定。
     * @return
     */
    int hashCode();

    //默认方法

    /**
     * 返回给定key映射的value，如果这个Map不包含这个key的映射则返回defaultValue。
     *
     * 默认实现不保证这个方法的同步或者原子性。任何提供原子性保证的实现都必须覆写此方法
     * 并记载其并发特性
     * @param key
     * @param defaultValue
     * @return
     */
    default V getOrDefault(Object key,V defaultValue){
        V v;
        return (((v = get(key)) != null) || containsKey(key)) ? v : defaultValue;
    }


    /**
     * 对这个Map所有的entry执行给定的action，直到所有的entry都被处理或者这个action抛出了一个异常。
     * 除非其实现类另有规定，actions按照entry的Set迭代顺序执行（假设迭代顺序已经被指定了）。
     *
     * 对于这个Map来说默认实现等价于：
     * for(Map.Entry<K,V> entry : map.entrySet()){
     *     action.accept(entry.getKey(),entry.getValue());
     * }
     *
     * 默认实现不保证这个方法的同步或者原子性。任何提供原子性保证的实现都必须覆写此方法
     * 并记载其并发特性
     * @param action
     */
    default void forEach(BiConsumer<? super K,? super V> action){
        Objects.requireNonNull(action);
        for(Map.Entry<K,V> entry: entrySet()){
            K k;
            V v;
            try{
                k = entry.getKey();
                v = entry.getValue();
            }catch(IllegalStateException ise){
                //这通常意味着这个entry不再在这个Map中
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k,v);
        }
    }

    /**
     * 将每个entry的value替换为在其entry上调用function的结果，直到所有的entry都被处理完毕或者
     * function抛出了一个异常。function抛出的异常将被转发给调用者。
     *
     * 对于这个Map来说，默认实现等价于：
     * for(Map.Entry<K,V> entry : map.entrySet()){
     *     entry.setValue(function.apply(entry.getKey(),entry.getValue()));
     * }
     *
     * 默认实现不保证这个方法的同步或者原子性。任何提供原子性保证的实现都必须覆写此方法
     * 并记载其并发特性
     * @param function
     */
    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function){
        Objects.requireNonNull(function);
        for(Map.Entry<K,V> entry : entrySet()){
            K k;
            V v;
            try{
                k = entry.getKey();
                v = entry.getValue();
            }catch(IllegalStateException ise){
                //这通常意味着这个entry不再在这个Map中
                throw new ConcurrentModificationException(ise);
            }

            v = function.apply(k,v);

            try{
                entry.setValue(v);
            }catch(IllegalStateException ise){
                //这通常意味着这个entry不再在这个Map中
                throw new ConcurrentModificationException(ise);
            }
        }
    }

    /**
     * 如果给定的key还未关联一个value(或者映射为Null)，将其与给定的value关联起来并返回null，
     * 否则返回当前值
     *
     * 对于这个Map来说，默认实现等价于：
     * {V v = map.get(key);
     * if(v == null) v = map.put(key,value);
     * return v;}
     *
     * 默认实现不保证这个方法的同步或者原子性。任何提供原子性保证的实现都必须覆写此方法
     * 并记载其并发特性
     * @param key
     * @param value
     * @return
     */
    default V putIfAbsent(K key,V value){
        V v = get(key);
        if(v == null){
            v = put(key,value);
        }
        return v;
    }

    /**
     * 如果给定的key当前映射给定的value,则移除此entry
     *
     * 对于这个Map来说，默认实现等价于：
     * if(map.containsKey(key) && Objects.equals(map.get(key),value)){
     *     map.remove(key);
     *     return true;
     * }else{
     *     return false;
     * }
     *
     * 默认实现不保证这个方法的同步或者原子性。任何提供原子性保证的实现都必须覆写此方法
     * 并记载其并发特性
     * @param key
     * @param value
     * @return
     */
    default boolean remove(Object key,Object value){
        Object curValue = get(key);
        if(Objects.equals(curValue,value) || (curValue == null && !containsKey(key))){
            return false;
        }
        remove(key);
        return true;
    }

    /**
     * 仅当给定的key映射给定的value情况下替换其entry
     *
     * 对于这个Map来说，默认的实现等价于：
     * if(map.containsKey(key) && Objects.equals(map.get(key),value)){
     *      map.put(key,newValue);
     *      return true;
     * }else{
     *     return false;
     * }
     *
     * 如果oldValue为null，则默认实现不会为不支持空值的Maps抛出NullPointerException
     * 除非newValue也为null
     *
     * 默认实现不保证这个方法的同步或者原子性。任何提供原子性保证的实现都必须覆写此方法
     * 并记载其并发特性
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    default boolean replace(K key,V oldValue,V newValue){
        Object curValue = get(key);
        if(Objects.equals(curValue,oldValue) || (curValue == null && !containsKey(key))){
            return false;
        }
        put(key,newValue);
        return true;
    }

    /**
     * 如果给定的key的entry映射一些值则替换之。
     *
     * 对于这个Map来说，默认的实现等价于：
     * if(map.containsKey(key)){
     *     return map.put(key, value);
     * }else{
     *     return null;
     * }
     *
     * 默认实现不保证这个方法的同步或者原子性。任何提供原子性保证的实现都必须覆写此方法
     * 并记载其并发特性
     * @param key
     * @param value
     * @return
     */
    default V replace(K key,V value){
        V curValue;
        if(((curValue = get(key)) != null) || containsKey(key)){
            curValue = put(key,value);
        }
        return curValue;
    }

    /**
     * 如果指定的key尚未关联一个value(或者其映射至null)，则尝试使用给定的映射function计算其值后并放入其中
     * 除非计算出的值是null
     *
     * 如果函数返回null，则不记录映射。如果函数自己抛出一个未经检查的异常，则异常会重新抛出，且
     * 不记录映射。最常见的用法是构造一个对象作为一个初始映射值或者记录结果，如：
     * map.computeIfAbsent(key, k -> new Value(f(k)));
     *
     * 或者实现一个多值Map(Map<K,Collection<V>>),支持一个key多个值：
     * map.computeIfAbsent(key,k -> new HashSet<V>()).add(v);
     *
     * 默认实现等价于这个Map的下述步骤，然后返回当前Value，如果现在不存在，则返回null。
     *
     * if(map.get(key) == null){
     *     V newValue = mappingFunction.apply(key);
     *     if(newValue != null)
     *        map.put(key,newValue);
     * }
     *
     * 这个方法的默认实现不保证同步或者原子性，任何提供原子性保证的实现必须覆写此方法且记载其并发特性。
     * 特别的，所有java.util.concurrent.ConcurrentMap的子接口实现必须记载其函数是否仅当value不存在时
     * 会被自动应用一次。
     * @param key
     * @param mappingFunction
     * @return
     */
    default V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction){
        Objects.requireNonNull(mappingFunction);
        V v;
        if((v = get(key)) == null){
            V newValue;
            if((newValue = mappingFunction.apply(key)) != null){
                put(key, newValue);
                return newValue;
            }
        }
        return v;
    }

    /**
     * 如果给定key的value存在且不为null，则尝试使用给定的key和其当前的value计算一个新的映射。
     *
     * 如果函数返回null，则此映射已被移除。如果函数自己抛出一个未经检查的异常，则检查会被上抛，
     * 且当前映射保持不变。
     *
     * 默认实现等价于执行这个Map的下述步骤，然后返回当前value，如果不存在的话则返回null：
     *if(map.get(key) != null){
     *     V oldValue = map.get(key);
     *     V newValue = remappingFunction.apply(key,oldValue);
     *     if(newValue != null) map.put(key,newValue);
     *     else map.remove(key);
     *}
     *
     * 这个方法的默认实现不保证同步或者原子性，任何提供原子性保证的实现必须覆写此方法且记载其并发特性。
     * 特别的，所有java.util.concurrent.ConcurrentMap的子接口实现必须记载其函数是否仅当value不存在时
     * 会被自动应用一次。
     * @param key
     * @param remappingFunction
     * @return
     */
    default V computeIfPresend(K key,BiFunction<? super K,? super V,? extends V> remappingFunction){
        Objects.requireNonNull(remappingFunction);
        V oldValue;
        if((oldValue = get(key)) != null){
            V newValue = remappingFunction.apply(key,oldValue);
            if(newValue != null){
                put(key,newValue);
                return newValue;
            }else{
                remove(key);
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * 尝试使用给定的key和其当前value(如果其没有当前映射则为null)计算一个映射。
     * 例如，创建或者拼接一个字符串消息到一个value映射：
     * {map.compute(key,(k,v) -> (v == null) ? msg : v.concat(msg))}
     *
     * 如果函数返回null,则映射已经被移除（或者不存在的值初始化后仍然不存在）。如果函数自己抛出
     * 一个未经检查的异常，则异常会被上抛，且当前映射保持不变。
     *
     * 默认实现等价于执行这个Map的下述步骤，然后返回当前value，如果不存在的话则返回null：
     * {
     *     V oldValue = map.get(key);
     *     V newValue = remappingFunction.apply(key,oldValue);
     *     if(oldValue != null){
     *         if(newValue != null)
     *            map.put(key,newValue);
     *         else
     *           map.remove(key);
     *     }else{
     *         if(newValue != null)
     *           map.put(key,newValue);
     *         else
     *           return null;
     *     }
     * }
     *
     * 这个方法的默认实现不保证同步或者原子性，任何提供原子性保证的实现必须覆写此方法且记载其并发特性。
     * 特别的，所有java.util.concurrent.ConcurrentMap的子接口实现必须记载其函数是否仅当value不存在时
     * 会被自动应用一次。
     *
     * @param key
     * @param remappingFunction
     * @return
     */
    default V compute(K key,BiFunction<? super K,? super V,? extends V> remappingFunction){
        Objects.requireNonNull( remappingFunction);
        V oldValue = get(key);

        V newValue = remappingFunction.apply(key,oldValue);
        if(newValue == null){
            //删除映射
            if(oldValue != null || containsKey(key)){
                //一些移除的操作
                remove(key);
                return null;
            }else{
                //什么也不做，让事情保持原样。
                return null;
            }
        }else{
            //新增或者替代旧的映射
            put(key ,newValue);
            return newValue;
        }
    }

    /**
     * 如果给定的key尚未关联一个value或者关联了null，将其与给定的非null的value关联。
     * 否则，使用给定的重映射函数的结果替换关联value，如果结果是null则移除此key。这个方法
     * 可用于组合一个key的多个映射value。例如，创建或者拼接一个String msg到一个value的映射：
     * {map.merge(key,msg,String::concat)}
     *
     * 如果函数返回null则此映射已被移除。如果函数自己抛出一个未经检查的异常，则此异常会被上抛，且当前
     * 映射保持不变。
     *
     * 默认实现等价于执行这个Map的下述步骤，然后返回当前值，如果不存在则返回null:
     * {
     * V oldValue = map.get(key);
     * V newValue = (oldValue == null) ? value : remappingFunction.apply(oldValue,value);
     * if(newValue == null)
     *   map.remove(key);
     * else
     *   map.put(key,newValue);
     * }
     *
     * 这个方法的默认实现不保证同步或者原子性，任何提供原子性保证的实现必须覆写此方法且记载其并发特性。
     * 特别的，所有java.util.concurrent.ConcurrentMap的子接口实现必须记载其函数是否仅当value不存在时
     * 会被自动应用一次。
     *
     * @param key
     * @param value
     * @param remappingFunction
     * @return
     */
    default V merge(K key,V value,BiFunction<? super V,? super V,? extends V> remappingFunction){
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = get(key);
        V newValue = (oldValue == null) ? value : remappingFunction.apply(oldValue,value);
        if(newValue == null){
            remove(key);
        }else{
            put(key,newValue);
        }
        return newValue;
    }
}
