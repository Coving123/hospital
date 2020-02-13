package test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.RequireMentDao;
import dao.impl.RequireMentDaoImpl;
import entity.AppointMent;
import entity.Medicine;
import entity.Patient;
import entity.RequireMent;
import exception.ServiceException;
import service.UserService;
import tools.Pager;
import service.AppointMentService;
import service.RequireMentService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;


public class Test {

	public static long endTime;
	public static Date date;
	public static long startTime;
	public static long midTime;
	public static Calendar c;
	public static int time = 24*60*60;
	
	public static String formatDuring(long mss) {
	      long days = mss / (1000 * 60 * 60 * 24);
	      long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
	      long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
	      long seconds = (mss % (1000 * 60)) / 1000;
	      return days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds ";
	 }
	
	
	/**
	 * 方式一： 给定时长倒计时
	 */
	private static void time1() {
		int dd = 3, a = time;  
		while (dd>0) {
		      a--;
		      try {
		         Thread.sleep(999);
		         int hh = a / 60 / 60 % 60;
		         int mm = a / 60 % 60;
		         int ss = a % 60;
		         if(hh==0&&mm==0&&ss==0) {a = time;dd--;}
		         int c = dd-1;if(dd==0) {c=0;}
		         System.out.println("请于"+ c +"天" + hh + "小时" + mm + "分钟" + ss + "秒内领取药物，过期失效");		         
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
		   }
		
		}
	

	/**
	 * 方式二： 设定时间戳，倒计时
	 */
	private static void time2() {

	            while (midTime > 0) {
	             midTime--;
	                   long hh = midTime / 60 / 60 % 60;
	                 long mm = midTime / 60 % 60;
	                   long ss = midTime % 60;
	                   System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");
	                  try {
	                       Thread.sleep(1000);

	                      } catch (InterruptedException e) {
	                          e.printStackTrace();
	                      }
	               }
	        }
	
	/**
	 * 方式三： 使用java.util.Timer类进行倒计时
	 */
	private static void time3() {
	   Timer timer = new Timer();
	   timer.schedule(new TimerTask() {
	      public void run() {
	         midTime--;
	         long hh = midTime / 60 / 60 % 60;
	         long mm = midTime / 60 % 60;
	         long ss = midTime % 60;
	         System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");
	      }
	   }, 0, 1000);
	}
	
	public static Date addDate(Date date,int day) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
//      rightNow.add(Calendar.YEAR,-1);//日期减1年
//      rightNow.add(Calendar.MONTH,3);//日期加3个月
//      rightNow.add(Calendar.DAY_OF_YEAR,5);//日期加5天
        rightNow.add(Calendar.DAY_OF_YEAR,day);
        Date dt1=rightNow.getTime();
        System.out.println(dt1);
		return dt1;
	}
	
	 public static String formatDuring(Date begin, Date end) {
	      return formatDuring(end.getTime() - begin.getTime());
	 }
	
	public static void main(String[] args) throws Exception {
		

//		   c = Calendar.getInstance();
//		   endTime = c.getTimeInMillis();
//		   date = new Date();
//		   startTime = date.getTime();
//		   midTime = (endTime - startTime) / 1000;
//		   time1();		   
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String eff = new String("1996-08-23 00:00:00");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(eff));
		c.add(Calendar.DAY_OF_MONTH,3);//三天后的日期

		String payMonth = sdf.format(c.getTime());

		System.out.println(payMonth);
		   
		   
		
		
		
		
//		   c.add(Calendar.DAY_OF_YEAR,5);
//		   SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd");
//		   System.out.println(c);
		   
		   
//		    Scanner sc = new Scanner(System.in);
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//			Date date = df.parse(sc.nextLine());
//			Date now =df.parse("2019-4-28");
//			long l=date.getTime()-now.getTime();
//			long day=l/(24*60*60*1000);
//			System.out.println(day);

		
//		Date b=new Date();
//        b.setDate(1);
//        b.setHours(23);
//        b.setMinutes(59);
//        b.setSeconds(59);
//        
//        while(true)
//        {
//        //现在的时间
//        Date now=new Date();
//        
//        System.out.println(b);
//        
//        long a1=now.getTime();
//        long b1=b.getTime();
//        
//        long c=b1-a1;
//        //把毫秒转化成时分秒
//        long m1=c/1000%60;//不足一分钟的秒
//        long fz1=c/1000/60%60;//不足一小时的分钟数
//        long t=c/1000/60/60/24; //获得天数
//        long t1=c/1000/60/60%24;//不足一天的小时数
//        
//        System.out.println("距离具体时间还有"+t+"天"+t1+"小时"+fz1+"分钟"+m1+"秒");
//        }


		
		
		
		
		
//		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext*.xml");
//		
//		    RequireMentService requireMentService=(RequireMentService) ac.getBean("requireMentServiceImpl");
//		    RequireMentDao requireMentDao = (RequireMentDao) ac.getBean("requireMentDaoImpl");
//		    RequireMentDaoImpl dao = new RequireMentDaoImpl();
//		    addOrModifyRequireMent
//			List<RequireMent> map = null;
//			try {
//				map = dao.getRequireMentByPatientAndPage(1,1,1);
//			} catch (ServiceException e) {
//				e.printStackTrace();
//			}
//			for(int i = 0 ; i < map.size() ; i++) {
//				  System.out.println(map.get(i));
//			}
			
//			Pager<RequireMent> pager = null;
//			try {
//				pager = requireMentService.findRequireMentByNurseAndPage(1, 1, 1);
//			} catch (ServiceException e) {
//				e.printStackTrace();
//			}
//		    
		    
		    
		    
		    
//			List<Map> list =new ArrayList<Map>();
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("code", 0);
//			map.put("msg", "");
//			map.put("count", pager.getPageCount());
//			map.put("data", pager.getContent());
//			list.add(map);
//			return map;
//			for(int i = 0 ; i < a.size() ; i++) {
//				  System.out.println(a.get(i));
//				}
			
//			Map<String, String> map=new HashMap<String, String>();
//	         map.put("张三1", "男");
//	         map.put("张三2", "男");
//	         map.put("张三3", "男");
//	         map.put("张三4", "男");
//	         map.put("张三5", "男");
//	         
//	         //第一种遍历map的方法，通过加强for循环map.keySet()，然后通过键key获取到value值
//	         for(String s:map.keySet()){
//	             System.out.println("key : "+s+" value : "+map.get(s));
//	         }
//	         System.out.println("====================================");
//	         
//	         //第二种只遍历键或者值，通过加强for循环
//	         for(String s1:map.keySet()){//遍历map的键
//	             System.out.println("键key ："+s1);
//	         }
//	         for(Object s2:map.values()){//遍历map的值
//	             System.out.println("值value ："+s2);
//	         }
//	         System.out.println("====================================");    
//	         
//	         //第三种方式Map.Entry<String, String>的加强for循环遍历输出键key和值value
//	         for(Map.Entry<String, String> entry : map.entrySet()){
//	             System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
//	         }
//	         System.out.println("====================================");
	         
	         //第四种Iterator遍历获取，然后获取到Map.Entry<String, String>，再得到getKey()和getValue()
//	         Iterator<Map.Entry<String, Object>> it= map.entrySet().iterator();
//	         while(it.hasNext()){
//	             Map.Entry<String, Object> entry=it.next();
//	             System.out.println("键key ："+entry.getKey()+" value ："+entry.getValue());
//	         }
//	         System.out.println("====================================");
			
			
//		User user=new User();
//		user.setUsername("aaa");
//		user.setPassword("111");
//		user.setPhone("11111111");
		
//		UserService userService=(UserService) ac.getBean("userServiceImpl");
		
		
//		try {
//			userService.regist(user);
//			System.out.println("注册成功");
//		} catch (DuplicateUsernameException e) {
//			System.out.println(e.getMessage());
//		}
		
//		User user=userService.findById(1);
		
//		System.out.println(user.getId()+" "+user.getLoginName()+" "+user.getPassword());
		
		
		
		/*
		AppointMentService appoint=(AppointMentService) ac.getBean("AppointMentServiceImpl");
		
		try {
			appoint.findAppointMentById(1);
			System.out.println("success");
	        } catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		*/
		
		
		
		
		
		
		
		
	}
}
