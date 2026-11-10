package com.oplus.vfxsdk.common.perf;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt__IterablesKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FileStat {
    public int fileSize;
    public int loadAnimatorTime;
    public int loadFileOpenTime;
    public int loadGLResourceTime;
    public int loadPrepareTime;
    public int loadProtocolTime;
    public int loadTime;
    private String fileName = "";
    private ArrayList<ImgStat> loadImgTime = new ArrayList<>();
    private final ArrayList<AnimStat> playAnim = new ArrayList<>();

    public final String getFileName() {
        return this.fileName;
    }

    public final int getFileSize() {
        return this.fileSize;
    }

    public final int getLoadAnimatorTime() {
        return this.loadAnimatorTime;
    }

    public final int getLoadFileOpenTime() {
        return this.loadFileOpenTime;
    }

    public final int getLoadGLResourceTime() {
        return this.loadGLResourceTime;
    }

    public final ArrayList<ImgStat> getLoadImgTime() {
        return this.loadImgTime;
    }

    public final int getLoadPrepareTime() {
        return this.loadPrepareTime;
    }

    public final int getLoadProtocolTime() {
        return this.loadProtocolTime;
    }

    public final int getLoadTime() {
        return this.loadTime;
    }

    public final ArrayList<AnimStat> getPlayAnim() {
        return this.playAnim;
    }

    public final void setFileName(String str) {
        this.fileName = str;
    }

    public final void setFileSize(int i) {
        this.fileSize = i;
    }

    public final void setLoadAnimatorTime(int i) {
        this.loadAnimatorTime = i;
    }

    public final void setLoadFileOpenTime(int i) {
        this.loadFileOpenTime = i;
    }

    public final void setLoadGLResourceTime(int i) {
        this.loadGLResourceTime = i;
    }

    public final void setLoadImgTime(ArrayList<ImgStat> arrayList) {
        this.loadImgTime = arrayList;
    }

    public final void setLoadPrepareTime(int i) {
        this.loadPrepareTime = i;
    }

    public final void setLoadProtocolTime(int i) {
        this.loadProtocolTime = i;
    }

    public final void setLoadTime(int i) {
        this.loadTime = i;
    }

    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("fileName", this.fileName);
        jSONObject.put("fileSize", this.fileSize);
        jSONObject.put("loadTime", this.loadTime);
        jSONObject.put("loadPrepareTime", this.loadPrepareTime);
        jSONObject.put("loadFileOpenTime", this.loadFileOpenTime);
        jSONObject.put("loadProtocolTime", this.loadProtocolTime);
        jSONObject.put("loadGLResourceTime", this.loadGLResourceTime);
        ArrayList<ImgStat> arrayList = this.loadImgTime;
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            ImgStat imgStat = arrayList.get(i2);
            i2++;
            arrayList2.add(StatKt.toJson(imgStat));
        }
        jSONObject.put("loadImgTime", new JSONArray((Collection) arrayList2));
        jSONObject.put("loadAnimatorTime", this.loadAnimatorTime);
        ArrayList<AnimStat> arrayList3 = this.playAnim;
        ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList3, 10));
        int size2 = arrayList3.size();
        while (i < size2) {
            AnimStat animStat = arrayList3.get(i);
            i++;
            arrayList4.add(StatKt.toJson(animStat));
        }
        jSONObject.put("playAnim", new JSONArray((Collection) arrayList4));
        return jSONObject;
    }
}
