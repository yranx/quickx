package com.ranx.chowder.reflects;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description 反射的 Utils 函数集合
 * @author ranx
 * @date 2018年12月10日 下午4:20:48
 *  提供访问私有变量, 获取泛型类型 Class, 提取集合中元素属性等 Utils 函数
 */
public class ReflectionUtils {

	/**
	 * 通过反射，获得定义Class时声明的父类的泛型参数的类型
	 * 如: public EmployeeDao extends BaseDao<Employee, String>
	 * @param clazz
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unckecked")
	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * 通过反射，获得Class定义中声明的父类的泛型参数类型
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unckecked")
	public static<T> Class<T> getSuperGenericType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 循环向上转型，获取对象的DeclaredMethod
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static Method getDeclareMethod(Object object, String methodName, Class<?>[] parameterTypes) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException | SecurityException e) {
				//Method 不在当前类定义，继续向上转型
			}
		}
		return null;
	}
	
	
	/**
	 * 循环向上转型，获取对象的DeclaredField
	 * @param object
	 * @param filedName
	 * @return
	 */
	public static Field getDeclaredField(Object object, String filedName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(filedName);
			} catch (NoSuchFieldException | SecurityException e) {
				//Field 不在当前类定义，继续向上转型
			}
		}
		return null;
	}
	
	/**
	 * 直接调用对象方法，而忽略修饰符（private, Protected)
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @param parameters
	 * @return
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes,
			Object[] parameters) {
		Method method = getDeclareMethod(object, methodName, parameterTypes);
		
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}
		method.setAccessible(true);
		
		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("不可能抛出的异常");
		}
		return null;
	}
	
	/**
	 *  使得field变为可访问
	 * @param field
	 */
	public static void makeAccessible(Field field) {
		if (!Modifier.isPublic(field.getModifiers())) {
			field.setAccessible(true);
		}
	}
	
	/**
	 * 直接设置对象属性值，忽略private/protected修饰符，也不经过setter
	 * @param object
	 * @param fieldName
	 * @param value
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		
		makeAccessible(field);
		
		try {
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println("不可能抛出的异常");
		}
	}
	
	/**
	 * 直接设置对象属性值，忽略private/protected修饰符，也不经过setter
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object object, String fieldName) {
		Field field = getDeclaredField(object, fieldName);
		
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println("不可能抛出的异常");
		}
		
		return result;
	}
}
