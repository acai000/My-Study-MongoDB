package com.example.mongodb.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Update;

public class ReflectionUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	
	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })  
    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {  
        return getSuperClassGenricType(clazz, 0);  
    }
	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. 
	 * 如public UserDao extends HibernateDao<User,Long>
	 * @param clazz
	 * @param index
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}
	
	/**
	 * 根据对象获得mongodb Update语句 除id字段以外，所有被赋值的字段都会成为修改项
	 */
	public static Update getUpdateObj(final Object obj) {
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		Update update = null;
		boolean isFirst = true;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(obj);
				if (value != null) {
					if ("id".equals(field.getName().toLowerCase())
							|| "serialversionuid".equals(field.getName()
									.toLowerCase()))
						continue;
					if (isFirst) {
						update = Update.update(field.getName(), value);
						isFirst = false;
					} else {
						update = update.set(field.getName(), value);
					}
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return update;
	}
}
