package com.oplus.vfxsdk.common.perf;

import android.content.Context;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import java.io.File;
import java.io.FileWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class StatUtil {
    public static final Companion Companion = new Companion();
    private static Stat stats = new Stat();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final EngineStat getEngineStat(long j) {
            if (getStats().getEngineStats().get(Long.valueOf(j)) != null) {
                EngineStat engineStat = getStats().getEngineStats().get(Long.valueOf(j));
                engineStat.getClass();
                return engineStat;
            }
            EngineStat engineStat2 = new EngineStat();
            getStats().getEngineStats().put(Long.valueOf(j), engineStat2);
            return engineStat2;
        }

        public final FileStat getFileStat(String str) {
            if (getStats().getFileStats().get(str) != null) {
                FileStat fileStat = getStats().getFileStats().get(str);
                fileStat.getClass();
                return fileStat;
            }
            FileStat fileStat2 = new FileStat();
            getStats().getFileStats().put(str, fileStat2);
            return fileStat2;
        }

        public final Stat getStats() {
            return StatUtil.stats;
        }

        public final void save(Context context) {
            File externalCacheDir = context.getExternalCacheDir();
            externalCacheDir.getClass();
            String strM = Bundler$.ExternalSyntheticOutline0.m(externalCacheDir.getPath(), "/stat.json");
            Log.d("COETest", "filePath:".concat(strM));
            saveStatToJsonFile(strM, getStats());
            getStats().clear();
        }

        public final void saveStatToJsonFile(String str, Stat stat) {
            String string = stat.toJson().toString();
            FileWriter fileWriter = new FileWriter(new File(str));
            fileWriter.write(string);
            fileWriter.close();
        }

        public final void setStats(Stat stat) {
            StatUtil.stats = stat;
        }
    }
}
