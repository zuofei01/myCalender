import main.MyCalender;
import main.MyMap;
import main.model.MySchedule;
import main.model.Response;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zuoyafei on 2018/3/15.
 */
public class MyTest {

    private MyCalender myCalender = new MyCalender();

    private  MyMap myMap = MyMap.getSingleton();

    @Test
    public void testPut() {

        myMap.put(new MySchedule(5, 10));
        myMap.put(new MySchedule(6, 9));
        Iterator iter = myMap.getTreeMap().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            System.out.println("key is " + key);
        }
    }

    @Test
    public void testJudge() {

        myMap.put(new MySchedule(5, 10));
        myMap.put(new MySchedule(15, 18));
        myMap.put(new MySchedule(20, 25));
        myMap.put(new MySchedule(26, 30));
        boolean flag = myMap.judge(new MySchedule(10, 15));
        System.out.println("flag is " + flag);
    }

    @Test
    public void testRemove() {

//        myMap.put(new MySchedule(5, 10));
//        myMap.put(new MySchedule(15, 18));
//        myMap.put(new MySchedule(20, 25));
//        myMap.put(new MySchedule(26, 30));
        List<MySchedule> res = myMap.remove(new MySchedule(100, 150));
        for (int i = 0; i < res.size(); i++) {
            System.out.println("shcdule is " + res.get(i));
        }

    }

    @Test
    public void testGet() {
        myMap.put(new MySchedule(5, 10));
        myMap.put(new MySchedule(15, 18));
        myMap.put(new MySchedule(20, 25));
        myMap.put(new MySchedule(26, 30));
        List<MySchedule> res = myMap.get(new MySchedule(10, 15));
        for (int i = 0; i < res.size(); i++) {
            System.out.println("shcdule is " + res.get(i));
        }

    }

    @Test
    public void getData(){
        Iterator iter = myMap.getTreeMap().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            System.out.println("key is " + key);
        }
    }

    @Test
    public void testSetInterval(){
        long start = 500;
        long end = 4670309599l;
        Response response = myCalender.addInterval(start, end);
        System.out.println("response is "+ response);
        long start1 = 150;
        long end1 = 200;
        Response response1 = myCalender.addInterval(start1, end1);
        System.out.println("response is "+ response1);
    }

    @Test
    public void testJudgeAndGetInterval(){
        long start = 100;
        long end = 210;
        Response response = myCalender.judgeInterval(start, end);
        System.out.println("response is "+ response);
        Response response1 = myCalender.getInterval(start, end);
        System.out.println("response is "+ response1);
    }

    @Test
    public void testRemoveInterval(){
        long start = 100;
        long end = 210;
        Response response = myCalender.removeInterval(start, end);
        System.out.println("response is "+ response);

//        long start1 = 50;
//        long end1 = 100;
//        Response response1 = myCalender.addInterval(start1, end1);
//        long start2 = 150;
//        long end2 = 200;
//        Response response2 = myCalender.addInterval(start2, end2);
    }




}
