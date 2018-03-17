package main;

import main.constant.MyConstant;
import main.exception.MyException;
import main.model.MySchedule;
import main.util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zuoyafei on 2018/3/15.
 */
public class MyMap {

    private static final String path = "myTreeMap.txt";
    //treeMap作为保存区间的容器,只能有一份，初始化时从文件系统中读出来
    private static TreeMap<MySchedule, MySchedule> treeMap = (TreeMap<MySchedule, MySchedule>) FileUtil.readObject(path);

    //单例模式，如果可以生成多个对象的话，就会有并发访问的问题
    private static class Holder {
        private static MyMap singleton = new MyMap();
    }

    private MyMap() {
    }

    public static MyMap getSingleton() {
        return Holder.singleton;
    }

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    /**
     * @param mySchedule
     * @return
     */
    public void put(MySchedule mySchedule) {

        if (!MySchedule.checkSchedule(mySchedule)) {
            throw new MyException(MyConstant.DATE_ILLEGAL);
        }
        w.lock();
        try {
            if (treeMap.containsKey(mySchedule)) {
                throw new MyException("interval conflict");
            }
            treeMap.put(mySchedule, mySchedule);
            FileUtil.writeObject(treeMap, path);
            return;
        } finally {
            w.unlock();
        }
    }

    /**
     * @param mySchedule
     * @return
     */
    public List<MySchedule> get(MySchedule mySchedule) {
        List<MySchedule> res = new ArrayList<>();
        try {
            if (!MySchedule.checkSchedule(mySchedule)) {
                throw new MyException(MyConstant.DATE_ILLEGAL);
            }
            //加共享锁
            r.lock();
            MySchedule ms = treeMap.get(mySchedule);
            if (mySchedule == null) {
                return res;
            }
            res.add(ms);
            MySchedule first = ms;
            //根据第一次获取的区间对treeMap的前后继区间进行遍历，直到前后继区间为null或者和当前区间没有交集为止
            while (ms != null) {
                ms = treeMap.lowerKey(ms);
                if (ms == null || ms.compareTo(mySchedule) == -1) {
                    break;
                }
                res.add(new MySchedule(ms));
            }
            while (first != null) {
                first = treeMap.higherKey(first);
                if (first == null || first.compareTo(mySchedule) == 1) {
                    break;
                }
                res.add(new MySchedule(first));
            }
        } finally {
            r.unlock();
        }
        return res;
    }

    /**
     * @param mySchedule
     * @return
     */
    public boolean judge(MySchedule mySchedule) {
        if (!MySchedule.checkSchedule(mySchedule)) {
            throw new MyException(MyConstant.DATE_ILLEGAL);
        }
        r.lock();
        try {
            return !treeMap.containsKey(mySchedule);
        } finally {
            r.unlock();
        }
    }

    /**
     * 移除区间，所有和该区间有交集的区间都被移除掉，遇到有冲突的区间就将其删除掉，然后重新遍历，直到不存在冲突的区间
     *
     * @param mySchedule
     * @return
     */
    public List<MySchedule> remove(MySchedule mySchedule) {
        List<MySchedule> res = new ArrayList<>();
        if (!MySchedule.checkSchedule(mySchedule)) {
            throw new MyException(MyConstant.DATE_ILLEGAL);
        }
        w.lock();
        try {
            MySchedule ms = treeMap.remove(mySchedule);
            while (ms != null) {
                res.add(new MySchedule(ms));
                ms = treeMap.remove(mySchedule);
            }
            FileUtil.writeObject(treeMap, path);
            return res;
        } finally {
            w.unlock();
        }
    }



    public TreeMap<MySchedule, MySchedule> getTreeMap() {
        return treeMap;
    }
}
