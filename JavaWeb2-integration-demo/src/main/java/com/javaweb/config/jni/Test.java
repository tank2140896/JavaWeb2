package com.javaweb.config.jni;

public class Test {
	
	static {
		//dll文件名，改文件要放在jdk的bin目录下
		System.loadLibrary("test");
	}
	
	public native int justTestJNI(int x);
	
	public static void main(String[] args) {
		Test test = new Test();
		System.out.println(test.justTestJNI(10));
	}

}

/**
步骤：
1、编写Test.java
2、编译Test.java生成Test.class
3、生成JNI头文件Test.h（命令为：javah Test）
4、编写C/C++程序，如Test.cpp
#include "Test.h"
JNIEXPORT jint JNICALL Java_Test_justTestJNI(JNIEnv *env,jobject obj,jint x){
	int init = 10;
	if(x==10){
		return x*x/init;
	}else{
		return init;
	}
}
void main(){}
5、除了源文件目录下有Test.cpp，还要把jni.h（在%JAVA_HOME%/include/下）、jni_md.h（在%JAVA_HOME%/include/win32/下）、Test.h（步骤3的文件）包含在头文件目录下
6、编译生成dll
7、将test.dll放到jdk的bin目录下，然后运行java Test
*/