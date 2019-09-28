package com.javaweb.util.help.lambda;

import java.util.function.Function;

/**
 * FunctionEnhance<T, R>中T是输入即参数，R是输出即返回值
 * Function<Integer,Function<Integer,Function<Integer,Integer>>> f = 
 *          x             -> y             -> z     ->0
 */
public interface FunctionEnhance<T, R> extends Function<T, R> {
	
	/**
	 * Function<String,Integer> f1 = x -> Integer.parseInt(x)+10;
	 * Function<Integer,String> f2 = x -> x+"10";
	 * String a = FunctionEnhance.andThen(f1,f2).apply("30");
	 * System.out.println(a);//4010
	 */
	static <T,R,V> Function<T,V> andThen(Function<T, R> f1,Function<R, V> f2) {
		return x -> f2.apply(f1.apply(x));
	}
	
	/**
	 * String a = FunctionEnhance.<String,Integer,String>andThen().apply(x->Integer.parseInt(x)+10).apply(x->x+"10").apply("30");
	 * System.out.println(a);//4010
	 */
	static <T,R,V> Function<Function<T, R>,Function<Function<R, V>,Function<T,V>>> andThen() {
		return x -> y -> x.andThen(y);
	}
	
	/**
	 * String a = FunctionEnhance.<String,Integer,String>higherAndThen().apply(x->Integer.parseInt(x)+10).apply(x->x+"10").apply("30");
	 * System.out.println(a);//4010
	 */
	static <T,R,V> Function<Function<T, R>,Function<Function<R, V>,Function<T,V>>> higherAndThen() {
		return x -> y -> z -> y.apply(x.apply(z));
	}
	
	/**
	 * Function<String,Integer> f1 = x -> Integer.parseInt(x)+10;
	 * Function<Integer,String> f2 = x -> x+"10";
	 * Integer a = FunctionEnhance.compose(f1,f2).apply(30);
	 * System.out.println(a);//3020
	 */
	static <T,R,V> Function<V,R> compose(Function<T, R> f1,Function<V, T> f2) {
		return x -> f1.apply(f2.apply(x));
	}
	
	/**
     * Integer a = FunctionEnhance.<Integer,String,Integer>compose().apply(x->x+"10").apply(x->Integer.parseInt(x)+10).apply(30);
	 * System.out.println(a);//3020
	 */
	static <T,R,V> Function<Function<T, R>,Function<Function<R, V>,Function<T,V>>> compose() {
		return x -> y -> y.compose(x);
	}
	
	/**
	 * Integer a = FunctionEnhance.<Integer,String,Integer>higherCompose().apply(x->x+"10").apply(x->Integer.parseInt(x)+10).apply(30);
	 * System.out.println(a);//3020
	 */
	static <T,R,V> Function<Function<T, R>,Function<Function<R, V>,Function<T,V>>> higherCompose() {
		return (Function<T, R> x) -> (Function<R, V> y) -> (T z) -> y.apply(x.apply(z));//x -> y -> z -> y.apply(x.apply(z));
	}

}
