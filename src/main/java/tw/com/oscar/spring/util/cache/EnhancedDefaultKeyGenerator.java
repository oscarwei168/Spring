/**
 * EnhancedDefaultKeyGenerator.java
 * Title: Oscar Wei Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/27
 * <p>
 * H i s t o r y
 * 2015/7/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.cache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * <p>
 * Title: EnhancedDefaultKeyGenerator.java
 * </p>
 * <strong>Description:</strong> A class that implement Ehcache KeyGenerator interface for custom cache key
 * generation
 * for
 * initial Ehcache cache manager<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/27
 * @since 2015/7/27
 */
public class EnhancedDefaultKeyGenerator implements KeyGenerator {

    public static final int NO_PARAM_KEY = 0;
    public static final int NULL_PARAM_KEY = 53;

    private static final HashSet<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    /**
     * A method for generating cache key value
     *
     * @param target a target object
     * @param method a Method object
     * @param params list of parameter values
     * @return a cache key
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (params.length == 1 && isWrapperType(params[0].getClass())) {
            return (params[0] == null ? NULL_PARAM_KEY : params[0]);
        }
        if (params.length == 0) {
            return NO_PARAM_KEY;
        }
        int hashCode = 17;
        for (Object object : params) {
            hashCode = 31 * hashCode + (object == null ? NULL_PARAM_KEY : object.hashCode());
        }
        return Integer.valueOf(hashCode);
    }

    /**
     * Checking wrapper types
     *
     * @param clazz a class that will be checking
     * @return true if checking success, or false otherwise
     */
    public static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    /**
     * A method for obtain wrapper types listing
     *
     * @return the wrapper types listing
     */
    private static HashSet<Class<?>> getWrapperTypes() {
        HashSet<Class<?>> ret = new HashSet<>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
}
