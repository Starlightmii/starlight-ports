package com.oplus.systemui.preventmode;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.keyguard.OplusLockIconView$;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPreventModeData {
    public static final Companion Companion = new Companion();
    public static final String OPLUS_PREVENT_MODE_PACKAGES = "prevent_mode_packages";
    public static final String OPLUS_PREVENT_MODE_PREFERENCE = "prevent_mode_settings";
    public static final String OPLUS_PREVENT_MODE_SWITCH = "prevent_mode_switch";
    public static final String OPLUS_PREVENT_MODE_VERSION = "prevent_mode_version";
    public static final String SEPARATOR = ",";
    public static final String TAG = "OplusPreventModeData";
    private static volatile OplusPreventModeData sInstance;
    private final Context mContext;
    private ArrayList<String> preventModePackages;

    /* JADX INFO: renamed from: switch, reason: not valid java name */
    public boolean f0switch;
    public long verionID;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusPreventModeData getInstance(Context context) {
            if (OplusPreventModeData.sInstance == null) {
                synchronized (OplusPreventModeData.class) {
                    if (OplusPreventModeData.sInstance == null) {
                        OplusPreventModeData.sInstance = new OplusPreventModeData(context);
                    }
                }
            }
            OplusPreventModeData oplusPreventModeData = OplusPreventModeData.sInstance;
            oplusPreventModeData.getClass();
            return oplusPreventModeData;
        }
    }

    public OplusPreventModeData(Context context) {
        this.f0switch = true;
        this.preventModePackages = new ArrayList<>(CollectionsKt__CollectionsKt.listOf(new String[]{"com.heytap.pictorial", "com.kugou.android", "com.netease.cloudmusic", "com.tencent.qqmusic", "cn.kuwo.player", "cmccwm.mobilemusic", "com.heytap.music", "com.spotify.music", "com.bsbportal.music", "com.amazon.mp3", "com.amazon.ziggy.android", "com.google.android.apps.youtube.music", "com.gaana", "com.soundcloud.android", "com.dywx.larkplayer", "com.apple.android.music", "com.sec.android.app.music", "com.tencent.ibg.joox", "com.skysoft.kkbox.android", "com.aspiro.tidal", "com.starmakerinteractive.starmaker", "com.smule.singandroid"}));
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        SharedPreferences sharedPreferences = applicationContext != null ? applicationContext.getSharedPreferences(OPLUS_PREVENT_MODE_PREFERENCE, 0) : null;
        long j = sharedPreferences != null ? sharedPreferences.getLong(OPLUS_PREVENT_MODE_VERSION, 0L) : 0L;
        if (j <= this.verionID) {
            return;
        }
        this.verionID = j;
        this.f0switch = sharedPreferences != null ? sharedPreferences.getBoolean(OPLUS_PREVENT_MODE_SWITCH, true) : true;
        String string = sharedPreferences != null ? sharedPreferences.getString(OPLUS_PREVENT_MODE_PACKAGES, null) : null;
        List listEmptyList = (string == null || (listEmptyList = StringsKt__StringsKt.split$default(string, new String[]{SEPARATOR}, 0, 6)) == null) ? CollectionsKt__CollectionsKt.emptyList() : listEmptyList;
        ArrayList<String> arrayList = this.preventModePackages;
        if (arrayList != null) {
            arrayList.clear();
        }
        List list = listEmptyList;
        if (list.isEmpty()) {
            return;
        }
        this.preventModePackages = (ArrayList) CollectionsKt___CollectionsKt.toMutableList(list);
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter) {
        printWriter.println("OplusPreventModeData:");
        printWriter.println("\tverionID=" + this.verionID);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tswitch=", this.f0switch);
        printWriter.println("\tpreventModePackages=" + this.preventModePackages);
    }

    public final ArrayList<String> getPreventModePackages() {
        return this.preventModePackages;
    }

    public final long getVerionID() {
        return this.verionID;
    }

    public final void savePreventModeData(long j, boolean z, ArrayList<String> arrayList) {
        if (j < this.verionID) {
            KgdLogUtil.i(TAG, "the version is lower than current");
            return;
        }
        int size = arrayList.size();
        long length = 0;
        int i = 0;
        while (i < size) {
            String str = arrayList.get(i);
            i++;
            length += (long) str.getBytes(Charsets.UTF_8).length;
        }
        if (length > 1048576) {
            KgdLogUtil.i(TAG, "isExceedingSize");
            return;
        }
        Context context = this.mContext;
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences(OPLUS_PREVENT_MODE_PREFERENCE, 0) : null;
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, SEPARATOR, (CharSequence) null, (CharSequence) null, (Function1) null, 62);
        SharedPreferences.Editor editorEdit = sharedPreferences != null ? sharedPreferences.edit() : null;
        if (editorEdit != null) {
            editorEdit.putLong(OPLUS_PREVENT_MODE_VERSION, j);
        }
        if (editorEdit != null) {
            editorEdit.putBoolean(OPLUS_PREVENT_MODE_SWITCH, z);
        }
        if (editorEdit != null) {
            editorEdit.putString(OPLUS_PREVENT_MODE_PACKAGES, strJoinToString$default);
        }
        if (editorEdit != null) {
            editorEdit.apply();
        }
        updatePreventModeDataFromRUS(j, z, arrayList);
    }

    public final void updatePreventModeDataFromRUS(long j, boolean z, List<String> list) {
        if (j > this.verionID) {
            this.verionID = j;
            this.f0switch = z;
            ArrayList<String> arrayList = this.preventModePackages;
            if (arrayList != null) {
                arrayList.clear();
            }
            List<String> list2 = list;
            if (list2.isEmpty()) {
                return;
            }
            this.preventModePackages = (ArrayList) CollectionsKt___CollectionsKt.toMutableList(list2);
        }
    }
}
