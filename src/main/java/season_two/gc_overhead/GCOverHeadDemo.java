package season_two.gc_overhead;

import java.util.ArrayList;
import java.util.List;

/**
 * GCOverHead演示
 * 前提是将堆内存设置小一些，方便演示:
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 其中MaxDirectMemorySize表示直接内存
 */
public class GCOverHeadDemo {
     public static void main(String[] args) {
       int i=0;
       List<String> list = new ArrayList<>();
       try {
           while (true){
               list.add(String.valueOf(++i).intern());
           }
       }catch (Exception e){
           e.printStackTrace();
           throw e;
       }
     }
}
