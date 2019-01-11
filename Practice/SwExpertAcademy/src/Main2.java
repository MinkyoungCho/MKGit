//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Iterator;
//
//class Photo implements Comparable<Photo>{
//	String ext, city;
//	int year, month, date, hh, mm, ss;
//	String photoOrder; // 예 : 01, 11, 012 -> 사진 찍힌 시간 순
//	
//	Photo(String ext, String city, int year, int month, int date, int hh, int mm, int ss) {
//		this.ext = ext;
//		this.city = city;
//		this.year = year;
//		this.month = month;
//		this.date = date;
//		this.hh = hh;
//		this.mm = mm;
//		this.ss = ss;
//	}
//
//	@Override
//	public int compareTo(Photo o) {
//		if (this.year != o.year) {
//			return (this.year - o.year);
//		} else {
//			if (this.month != o.month) {
//				return (this.month - o.month);
//			} else {
//				if (this.date != o.date) {
//					return (this.date - o.date);
//				} else {
//					if (this.hh != o.hh) {
//						return (this.hh - o.hh);
//					} else {
//						if (this.mm != o.mm) {
//							return (this.mm - o.mm);
//						} else {
//							return (this.ss - o.ss);
//						}
//					}
//				}
//			}
//		}
//	}
//}
//
//class Solution {
//	
//	static public int checkSize(int n) {//자릿수 리턴
//		int res = 1;
//		
//		while (n / 10 > 0) {
//			res ++;
//			n /= 10;
//		}
//		
//		return res;
//	}
//	
//    static public String solution(String S) {
//        String[] inputs = S.split("\n");
//        String result = "";
//        
//        ArrayList<Photo> gallery = new ArrayList<Photo>();
//        HashMap<String, ArrayList<Photo>> map = new HashMap<>();
//        
//        for (int idx = 0; idx < inputs.length; idx++) { // 객체 만들어 AL에 넣기
//        	String[] items = inputs[idx].split(", ");
//        	String ext, city;
//        	int year, month, date, hh, mm, ss;
//        	
//        	String[] first = items[0].split("[.]");
//        	ext = first[1];
//        	city = items[1];
//        	
//        	String[] times = items[2].split(" ");
//        	String[] ymd = times[0].split("-");
//        	year = Integer.parseInt(ymd[0]);
//        	month = Integer.parseInt(ymd[1]);
//        	date = Integer.parseInt(ymd[2]);
//        	
//        	String[] hms = times[1].split(":");
//        	hh = Integer.parseInt(hms[0]);
//        	mm = Integer.parseInt(hms[1]);
//        	ss = Integer.parseInt(hms[2]);
//        	
//        	Photo current = new Photo(ext, city, year, month, date, hh, mm, ss);
//        	gallery.add(current);
//        	
//        	if (map.containsKey(city)) { // 해당 AL에 추가
//        		ArrayList<Photo> list = (ArrayList<Photo>) map.get(city);
//        		list.add(current);
//        		map.put(city, list);
//        	} else { // 새로 AL만들고 현재꺼 1개 추가해서 MAP에 PUT
//        		ArrayList<Photo> list = new ArrayList<>();
//        		list.add(current);
//        		map.put(city, list);
//        	}
//        }
//        
//        Iterator iter = map.keySet().iterator();
//        while (iter.hasNext()) { 
//        	ArrayList<Photo> currentList = (ArrayList<Photo>) map.get(iter.next());
//        	Collections.sort(currentList);
//        	int sizeOfList = checkSize(currentList.size()); //자릿수계산
//        	int sizeOfIdx = 0;
//        	String order = "";
//        	
//        	for (int idx = 0; idx < currentList.size(); idx++) {
//        		order = "";
//        		sizeOfIdx = checkSize(idx + 1); //순서의 자릿수
//        		for (int j = 0; j < (sizeOfList - sizeOfIdx); j++) {
//        			order = order.concat("0");
//        		}
//        		order = order.concat(Integer.toString(idx + 1));
//        		currentList.get(idx).photoOrder = order;
//        	}
//        }
//        
//        for (Photo p : gallery) {
//        	result += p.city + p.photoOrder + "." + p.ext + "\n";
//        }
//        result = result.trim();
//        
//        return result;
//    }
//}
//
//public class Main2 {
//	public static void main(String args[]) {
//		String input = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" + 
//"john.png, London, 2015-06-20 15:13:22\n" +
//"myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
//"Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
//"pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
//"BOB.jpg, London, 2015-08-05 00:02:03\n" +
//"notredame.png, Paris, 2015-09-01 12:00:00\n" +
//"me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
//"a.png, Warsaw, 2016-02-13 13:33:50\n" +
//"b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
//"c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
//"d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
//"e.png, Warsaw, 2016-01-02 09:49:09\n" +
//"f.png, Warsaw, 2016-01-02 10:55:32\n" +
//"g.jpg, Warsaw, 2016-02-29 22:13:11";
//		
//		System.out.println(Solution.solution(input));
//	}
//}