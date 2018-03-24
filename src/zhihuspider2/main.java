package zhihuspider2;

import java.util.ArrayList;

public class main {
	public static void main(String[] args) {
		// 定义即将访问的链接
		  String url = "https://www.zhihu.com/explore/recommendations";
		  // 访问链接并获取页面内容
		  String content = spider.SendGet(url);//没问题
		  // 获取编辑推荐
		  ArrayList<Zhihu> myZhihu = spider.GetRecommendations(content);
		  // 打印结果
//		  System.out.println(myZhihu);
		   for (Zhihu zhihu : myZhihu) {  
	            FileReadwriter.writeIntoFile(zhihu.writeString(),  
	                    "D:/知乎_编辑推荐.txt", true);  
		 }
}
}