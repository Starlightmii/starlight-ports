package com.oplus.systemui.notification.envelope;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WeChatEnvelopeFieldList {
    public AnonymousClass2 mBootCompletedReceiver;
    public Context mContext;
    public AnonymousClass1 mEnvelopeObserver;
    public boolean mHasBootCompleted;
    public boolean mHasInitialized;
    public boolean mReadEnvelopeData;
    public int mUpdateEnvelopeCount;
    public static final String[] DEFAULT_ENVELOPE_INFO = {"0", "MainUI_User_Last_Msg_Type", "436207665", "Main_User", "@chatroom", "]", ": [微信红包]", "[微信红包]"};
    public static final List ADAPTATION_ENVELOPE_INFOS = new ArrayList();
    public static final Object M_OBJECT = new Object();
    public static volatile WeChatEnvelopeFieldList sWeChatEnvelopeFieldList = null;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.envelope.WeChatEnvelopeFieldList$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends Thread {
        public AnonymousClass3() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Cursor cursor = null;
            try {
                try {
                    Cursor cursorQuery = WeChatEnvelopeFieldList.this.mContext.getContentResolver().query(Uri.parse("content://com.android.systemui/envelope"), null, null, null, null);
                    if (cursorQuery == null || cursorQuery.getCount() == 0) {
                        Log.w("WeChatEnvelopeFieldList", "updateEnvelopeInfoFromDB cursor is null or count is 0.");
                    } else {
                        synchronized (WeChatEnvelopeFieldList.M_OBJECT) {
                            try {
                                WeChatEnvelopeFieldList.ADAPTATION_ENVELOPE_INFOS.clear();
                                while (cursorQuery.moveToNext()) {
                                    AdaptationEnvelopeInfo adaptationEnvelopeInfo = new AdaptationEnvelopeInfo();
                                    adaptationEnvelopeInfo.mPkgVersion = cursorQuery.getString(cursorQuery.getColumnIndex("pkg_version"));
                                    adaptationEnvelopeInfo.mEnvelopeFilterField = cursorQuery.getString(cursorQuery.getColumnIndex("envelope_filter_field"));
                                    adaptationEnvelopeInfo.mEnvelopeFilterValue = cursorQuery.getString(cursorQuery.getColumnIndex("envelope_filter_value"));
                                    adaptationEnvelopeInfo.mEnvelopeUserField = cursorQuery.getString(cursorQuery.getColumnIndex("envelope_user_field"));
                                    adaptationEnvelopeInfo.mEnvelopeGroupTag = cursorQuery.getString(cursorQuery.getColumnIndex("envelope_group_tag"));
                                    adaptationEnvelopeInfo.mEnvelopeUserNameTagFirst = cursorQuery.getString(cursorQuery.getColumnIndex("envelope_user_name_tag_first"));
                                    adaptationEnvelopeInfo.mEnvelopeUserNameTagLast = cursorQuery.getString(cursorQuery.getColumnIndex("envelope_user_name_tag_last"));
                                    adaptationEnvelopeInfo.mEnvelopeContentTag = cursorQuery.getString(cursorQuery.getColumnIndex("envelope_content_tag"));
                                    WeChatEnvelopeFieldList.ADAPTATION_ENVELOPE_INFOS.add(adaptationEnvelopeInfo);
                                }
                                WeChatEnvelopeFieldList.this.mReadEnvelopeData = true;
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception e) {
                    LogUtil.e(LogUtil.TAG_COMMON, "WeChatEnvelopeFieldList", "mAdaptationEnvelopeInfoList query error:" + e.getMessage(), e);
                    if (0 != 0) {
                        cursor.close();
                    }
                }
            } catch (Throwable th2) {
                if (0 == 0) {
                    throw th2;
                }
                cursor.close();
                throw th2;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AdaptationEnvelopeInfo {
        public String mEnvelopeContentTag;
        public String mEnvelopeFilterField;
        public String mEnvelopeFilterValue;
        public String mEnvelopeGroupTag;
        public String mEnvelopeUserField;
        public String mEnvelopeUserNameTagFirst;
        public String mEnvelopeUserNameTagLast;
        public String mPkgVersion;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [com.oplus.systemui.notification.envelope.WeChatEnvelopeFieldList$1] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.oplus.systemui.notification.envelope.WeChatEnvelopeFieldList$2] */
    public static WeChatEnvelopeFieldList getInstance() {
        if (sWeChatEnvelopeFieldList == null) {
            synchronized (WeChatEnvelopeFieldList.class) {
                try {
                    if (sWeChatEnvelopeFieldList == null) {
                        WeChatEnvelopeFieldList weChatEnvelopeFieldList = new WeChatEnvelopeFieldList();
                        weChatEnvelopeFieldList.mEnvelopeObserver = new EnvelopeObserver() { // from class: com.oplus.systemui.notification.envelope.WeChatEnvelopeFieldList.1
                            @Override // com.oplusos.systemui.common.observer.AbstractObserver
                            public final void onChange(boolean z) {
                                WeChatEnvelopeFieldList weChatEnvelopeFieldList2 = WeChatEnvelopeFieldList.this;
                                if (weChatEnvelopeFieldList2.mHasInitialized) {
                                    weChatEnvelopeFieldList2.new AnonymousClass3().start();
                                } else {
                                    Log.w("WeChatEnvelopeFieldList", "color navigation bar util isn't init.");
                                }
                            }
                        };
                        weChatEnvelopeFieldList.mBootCompletedReceiver = new BootCompletedReceiver() { // from class: com.oplus.systemui.notification.envelope.WeChatEnvelopeFieldList.2
                            @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
                            public final void onReceive(Context context, Intent intent) {
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "WeChatEnvelopeFieldList", "ACTION_LOCKED_BOOT_COMPLETED: register systembar provider");
                                WeChatEnvelopeFieldList weChatEnvelopeFieldList2 = WeChatEnvelopeFieldList.this;
                                Context context2 = weChatEnvelopeFieldList2.mContext;
                                if (context2 != null) {
                                    weChatEnvelopeFieldList2.mEnvelopeObserver.register(context2, true);
                                }
                                weChatEnvelopeFieldList2.mHasBootCompleted = true;
                            }
                        };
                        weChatEnvelopeFieldList.mHasInitialized = false;
                        weChatEnvelopeFieldList.mReadEnvelopeData = false;
                        weChatEnvelopeFieldList.mUpdateEnvelopeCount = 0;
                        weChatEnvelopeFieldList.mHasBootCompleted = false;
                        VarHandle.storeStoreFence();
                        sWeChatEnvelopeFieldList = weChatEnvelopeFieldList;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sWeChatEnvelopeFieldList;
    }

    public static void updateEnvelopeDefaultInfo() {
        synchronized (M_OBJECT) {
            AdaptationEnvelopeInfo adaptationEnvelopeInfo = new AdaptationEnvelopeInfo();
            String[] strArr = DEFAULT_ENVELOPE_INFO;
            adaptationEnvelopeInfo.mPkgVersion = strArr[0];
            adaptationEnvelopeInfo.mEnvelopeFilterField = strArr[1];
            adaptationEnvelopeInfo.mEnvelopeFilterValue = strArr[2];
            adaptationEnvelopeInfo.mEnvelopeUserField = strArr[3];
            adaptationEnvelopeInfo.mEnvelopeGroupTag = strArr[4];
            adaptationEnvelopeInfo.mEnvelopeUserNameTagFirst = strArr[5];
            adaptationEnvelopeInfo.mEnvelopeUserNameTagLast = strArr[6];
            adaptationEnvelopeInfo.mEnvelopeContentTag = strArr[7];
            ADAPTATION_ENVELOPE_INFOS.add(adaptationEnvelopeInfo);
        }
    }

    public final String getEnvelopeInfo(String str) {
        int i;
        List list;
        boolean z = this.mHasInitialized;
        if (!z) {
            Log.w("WeChatEnvelopeFieldList", "color navigation bar util isn't init.");
            return "";
        }
        if (this.mHasBootCompleted && !this.mReadEnvelopeData && this.mUpdateEnvelopeCount < 20) {
            if (z) {
                new AnonymousClass3().start();
            } else {
                Log.w("WeChatEnvelopeFieldList", "color navigation bar util isn't init.");
            }
            this.mUpdateEnvelopeCount++;
        }
        synchronized (M_OBJECT) {
            try {
                if (((ArrayList) ADAPTATION_ENVELOPE_INFOS).size() == 0) {
                    updateEnvelopeDefaultInfo();
                }
                String version = AppInfoUtil.getVersion(this.mContext, "com.tencent.mm");
                if (version != null && !version.isEmpty()) {
                    int i2 = -1;
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        i = i2;
                        i2 = i4;
                        list = ADAPTATION_ENVELOPE_INFOS;
                        if (i2 >= ((ArrayList) list).size() || !AppInfoUtil.compareVersion(((AdaptationEnvelopeInfo) ((ArrayList) list).get(i2)).mPkgVersion, version)) {
                            break;
                        }
                        i3 = i2 + 1;
                    }
                    if (i >= 0) {
                        if (((ArrayList) list).size() == 0) {
                            updateEnvelopeDefaultInfo();
                        }
                        switch (str.hashCode()) {
                            case -2021563734:
                                if (str.equals("envelope_user_name_tag_last")) {
                                    return ((AdaptationEnvelopeInfo) ((ArrayList) list).get(i)).mEnvelopeUserNameTagLast;
                                }
                                break;
                            case -1252397227:
                                if (str.equals("envelope_user_field")) {
                                    return ((AdaptationEnvelopeInfo) ((ArrayList) list).get(i)).mEnvelopeUserField;
                                }
                                break;
                            case -1030876117:
                                if (str.equals("envelope_group_tag")) {
                                    return ((AdaptationEnvelopeInfo) ((ArrayList) list).get(i)).mEnvelopeGroupTag;
                                }
                                break;
                            case -25660187:
                                if (str.equals("envelope_content_tag")) {
                                    return ((AdaptationEnvelopeInfo) ((ArrayList) list).get(i)).mEnvelopeContentTag;
                                }
                                break;
                            case 836559234:
                                if (str.equals("envelope_filter_field")) {
                                    return ((AdaptationEnvelopeInfo) ((ArrayList) list).get(i)).mEnvelopeFilterField;
                                }
                                break;
                            case 851104249:
                                if (str.equals("envelope_filter_value")) {
                                    return ((AdaptationEnvelopeInfo) ((ArrayList) list).get(i)).mEnvelopeFilterValue;
                                }
                                break;
                            case 1750730012:
                                if (str.equals("envelope_user_name_tag_first")) {
                                    return ((AdaptationEnvelopeInfo) ((ArrayList) list).get(i)).mEnvelopeUserNameTagFirst;
                                }
                                break;
                        }
                    }
                }
                return "";
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
