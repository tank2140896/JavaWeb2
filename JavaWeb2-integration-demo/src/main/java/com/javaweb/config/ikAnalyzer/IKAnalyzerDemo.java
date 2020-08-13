package com.javaweb.config.ikAnalyzer;

public class IKAnalyzerDemo {
	
	public static void main(String[] args) throws Exception {
		/* ----------------------------- 方式一 ----------------------------- */
		/**
	    <dependency>
		    <groupId>com.jianggujin</groupId>
		    <artifactId>IKAnalyzer-lucene</artifactId>
		    <version>8.0.0</version>
		</dependency>
		*/
		/**
		//加载扩展词典：ext.dic、加载扩展停止词典：stopword.dic默认创建在src/main/resources下
		//1.创建一个Analyzer对象
		Analyzer analyzer=new IKAnalyzer();
		//2.调用Analyzer对象的tokenStream方法获取TokenStream对象，此对象包含了所有的分词结果
		TokenStream tokenStream = analyzer.tokenStream("", "刺客信条奥德赛");
		//3.给tokenStream对象设置一个指针，指针在哪当前就在哪一个分词上
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		//4.调用tokenStream对象的reset方法，重置指针，不调用会报错
		tokenStream.reset();
		//5.利用while循环，拿到分词列表的结果 incrementToken方法返回值如果为false代表读取完毕 true代表没有读取完毕
		while (tokenStream.incrementToken()){
			System.out.println(charTermAttribute.toString());
		}
		//6.关闭
		tokenStream.close();
		analyzer.close();
		*/
		/* ----------------------------- 方式二 ----------------------------- */
		/**
		<dependency>
	      	<groupId>com.janeluo</groupId>
	      	<artifactId>ikanalyzer</artifactId>
	      	<version>2012_u6</version>
	    </dependency>
		*/
		/**
		String string = "塑料手套" ;
        StringReader reader = new StringReader(string);
        IKSegmenter ik = new  IKSegmenter(reader, true);// 当为true时，分词器进行最大词长切分
        Lexeme lexeme = null;
        while ((lexeme = ik.next()) != null){
            System.out.print(lexeme.getLexemeText()+"|");
        }
		*/
	}

}
