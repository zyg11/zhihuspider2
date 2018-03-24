package zhihuspider2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhihu {
	public String question;// 问题
	 public String questionDescription;// 问题描述
	 public String zhihuUrl;// 网页链接
	 public ArrayList<String> answers;// 存储所有回答的数组
	 public ArrayList<String> answersdetail;// 存储所有回答的数组
	 // 构造方法初始化数据
	 public Zhihu(String url) {
	  question = "";
	  questionDescription = "";
	  zhihuUrl = "";
	  answers = new ArrayList<String>();
	  answersdetail = new ArrayList<String>();
	  // 判断url是否合法
	  if (getRealUrl(url)) {
	   System.out.println("正在抓取" + zhihuUrl);
	   // 根据url获取该问答的细节
	   String content = spider.SendGet(zhihuUrl);
	   Pattern pattern;
	   Matcher matcher;
	   // 匹配标题
	   pattern = Pattern.compile("QuestionHeader-title\">(.+?)<");
	   matcher = pattern.matcher(content);
	   if (matcher.find()) {
	    question = matcher.group(1);
	   }
	   // 匹配描述
	   pattern = Pattern
	     .compile("text\">(.+?)<");//表达式有问题//7月4日更新已完成
	   matcher = pattern.matcher(content);
	   if (matcher.find()) {
	    questionDescription = matcher.group(1);
	   }
	   // 匹配答案数目
	   pattern = Pattern.compile("List-headerText\">(.+?)<");//表达式有问题
	   matcher = pattern.matcher(content);
	   boolean isFind = matcher.find();
	   while (isFind) {
	    answers.add(matcher.group(1));
	    isFind = matcher.find();
	   }
	   //匹配具体答案
	   pattern = Pattern.compile("RichContent RichContent--unescapable.+?<div.+?>(.*?)</div>");//表达式有问题
	   matcher = pattern.matcher(content);
	   boolean isFind1 = matcher.find();
	   while (isFind1) {
	    answersdetail.add(matcher.group(1));
	    isFind1 = matcher.find();
	   }
	  }
	 }
	 public String writeString() {  
	        String result = "";  
	        result += "问题：" + question + "\r\n";  
	        result += "描述：" + questionDescription + "\r\n";  
	        result += "链接：" + zhihuUrl + "\r\n";  	        
//	        
	        result += "回答数目" + answers.size()+ "：" +"\r\n";
//	        int asize=answersdetail.size();
//	        System.out.println(asize);
	        for (int i = 0; i < answers.size(); i++) {  
	        	result += "回答" + i + "：" + answersdetail.get(i) + "\r\n";  
	        }  
	        result += "\r\n\r\n\r\n\r\n";  
	        // 将其中的html标签进行筛选  
	        result = result.replaceAll("<br>", "\r\n");  
	        result = result.replaceAll("<.*?>", "");  
	        return result;  
	}  
	// 根据自己的url抓取自己的问题和描述和答案
	 public boolean getAll() {
	  return true;
	 }
	 // 处理url
	 boolean getRealUrl(String url) {
	  // 将http://www.zhihu.com/question/22355264/answer/21102139
	  // 转化成http://www.zhihu.com/question/22355264
	  // 否则不变
	  Pattern pattern = Pattern.compile("question/(.*?)/");
	  Matcher matcher = pattern.matcher(url);
	  if (matcher.find()) {
	   zhihuUrl = "https://www.zhihu.com/question/" + matcher.group(1);//没问题
	  } else {
	   return false;
	  }
	  return true;
	 }
	 @Override
	 public String toString() {
	  return "问题:" + question + "\n描述:" +questionDescription + "\n链接:" + zhihuUrl + "\n回答:" + answers + "\n"
			  +answersdetail;
	 }
	 // 处理url

}
