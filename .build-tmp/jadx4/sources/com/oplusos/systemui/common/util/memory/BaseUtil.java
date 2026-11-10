package com.oplusos.systemui.common.util.memory;

import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;
import androidx.activity.result.ActivityResultRegistry$;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseUtil {
    public static void countInstancesForClass(PrintWriter printWriter, Class cls) {
        printWriter.println("Dump " + cls.getCanonicalName() + " amount in SystemUi : " + Debug.getInstanceCount(cls));
    }

    public static void dumpClassObjectSimply(PrintWriter printWriter, Class cls) {
        printWriter.println("");
        countInstancesForClass(printWriter, cls);
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            String canonicalName = obj.getClass().getCanonicalName();
            if (TextUtils.isEmpty(canonicalName)) {
                canonicalName = "Unknown-Class";
            }
            increaseCountsInMap(map, canonicalName);
        }
        for (String str : new TreeSet(map.keySet())) {
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("\t", str, ",  amount=");
            sbM.append(map.get(str));
            printWriter.println(sbM.toString());
        }
    }

    public static void increaseCountsInMap(HashMap map, String str) {
        map.put(str, Integer.valueOf(Integer.parseInt(((Integer) map.getOrDefault(str, 0)).toString()) + 1));
    }

    public ArrayList<Thread> getThreadsInGroup() {
        ArrayList<Thread> arrayList = new ArrayList<>();
        try {
            int iActiveCount = Thread.activeCount();
            Thread[] threadArr = new Thread[iActiveCount + (iActiveCount / 2)];
            int iEnumerate = Thread.enumerate(threadArr);
            for (int i = 0; i < iEnumerate; i++) {
                arrayList.add(threadArr[i]);
            }
            return arrayList;
        } catch (Exception e) {
            Log.w("BaseUtil", "getInstancesOfClassInProc: Exception ", e);
            return arrayList;
        }
    }
}
