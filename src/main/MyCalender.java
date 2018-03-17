package main;

import main.constant.MyConstant;
import main.exception.MyException;
import main.model.MySchedule;
import main.model.Response;

import java.util.HashMap;
import java.util.List;

public class MyCalender {

    private HashMap<String, MyMap> hm = new HashMap<>();

    private MyMap myMap = MyMap.getSingleton();

    /**
     * 根据区间的开始时间和结束时间设定该区间为忙碌，如果该区间和已有的忙碌区间有交集，则返回区间冲突的信息
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Response addInterval(long startTime, long endTime) {
        Response response = new Response();
        response.setFalse();
        try {
            if (!check(startTime, endTime)) {
                response.setMsg(MyConstant.DATE_ILLEGAL);
                return response;
            }
            myMap.put(new MySchedule(startTime, endTime));
            response.setTrue();
            response.setMsg(MyConstant.INSERT_SUCCESS);
        } catch (MyException e) {
            response.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg(MyConstant.SYSTEM_ERR);
        }
        return response;
    }

    /**
     * 根据给定区间判断该区间是否可用，只要有相交的区间，就认为本区间是忙碌的，返回给用户false,否则返回true
     * 如果需要具体的信息，可以利用get接口获取冲突的区间的list
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Response judgeInterval(long startTime, long endTime) {
        Response response = new Response();
        response.setFalse();
        try {
            if (!check(startTime, endTime)) {
                response.setMsg(MyConstant.DATE_ILLEGAL);
                return response;
            }
            boolean res = myMap.judge(new MySchedule(startTime, endTime));
            response.setTrue();
            response.setData(res);
        } catch (MyException e) {
            response.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg(MyConstant.SYSTEM_ERR);
        }
        return response;
    }

    /**
     * 根据给定区间查找与本区间冲突的忙碌区间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Response getInterval(long startTime, long endTime) {
        Response response = new Response();
        response.setFalse();
        try {
            if (!check(startTime, endTime)) {
                response.setMsg(MyConstant.DATE_ILLEGAL);
                return response;
            }
            List<MySchedule> res = myMap.get(new MySchedule(startTime, endTime));
            response.setTrue();
            response.setData(res);
        } catch (MyException e) {
            response.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg(MyConstant.SYSTEM_ERR);
        }
        return response;
    }

    /**
     * 根据给定区间删除掉所有和本区间相交的区间，并且把删除掉的区间返回给用户
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Response removeInterval(long startTime, long endTime) {
        Response response = new Response();
        response.setFalse();
        try {
            if (!check(startTime, endTime)) {
                response.setMsg(MyConstant.DATE_ILLEGAL);
                return response;
            }
            List<MySchedule> res = myMap.remove(new MySchedule(startTime, endTime));
            response.setTrue();
            response.setData(res);
        } catch (MyException e) {
            response.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg(MyConstant.SYSTEM_ERR);
        }
        return response;
    }

    public boolean check(long startTime, long endTime) {
        //2118年1月1日，本日历只支持到2118年
        long max = 4670409600l;
        return (startTime <= 0 || endTime <= 0 || startTime >= endTime || endTime >= max) ? false : true;
    }
}
