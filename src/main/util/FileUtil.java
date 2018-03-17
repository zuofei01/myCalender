package main.util;

import main.model.MySchedule;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zuoyafei on 2018/3/16.
 */
public class FileUtil {

    /**
     * 向文件中写入map对象，没有加读写锁
     *
     * @param map
     * @param path 文件路径
     */
    public static void writeObject(TreeMap<MySchedule, MySchedule> map, String path) {
        try {
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream outStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.flush();
            objectOutputStream.close();
            outStream.close();
            System.out.println("write successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取map对象
     *
     * @param path
     * @return
     */
    public static Map readObject(String path) {
        Map map = new TreeMap();
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.createNewFile();
                return map;
            }
            FileInputStream in = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            map = (TreeMap<MySchedule, MySchedule>) objectInputStream.readObject();
            objectInputStream.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }
}
