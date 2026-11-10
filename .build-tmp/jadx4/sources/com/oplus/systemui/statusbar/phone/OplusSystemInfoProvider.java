package com.oplus.systemui.statusbar.phone;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.OplusStatusBarDependencyInstance;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.dynamicevent.DynamicEventRepository;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSystemInfoProvider extends ContentProvider {
    public static final Companion Companion = new Companion();
    private static final int EVENT_TYPE_INVALID = -1;
    private static final String KEY_UPDATE_RESULT = "Result";
    private static final String METHOD_UPDATE_STATUS_BAR_TIP = "updateStatusBarTip";
    private static final int RESULT_FAILED = 1;
    private static final int RESULT_SUCCESS = 0;
    private static final String TAG = "OplusSystemInfoProvider";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("call method = ", str, ", callingPackage = ", getCallingPackage(), "OplusSystemInfoProvider-->"));
        }
        if (!Intrinsics.areEqual(str, METHOD_UPDATE_STATUS_BAR_TIP)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("call not support method: ", str, "OplusSystemInfoProvider-->", "SystemUi--Statusbar");
            }
            bundle2.putInt(KEY_UPDATE_RESULT, 1);
            return bundle2;
        }
        if (bundle == null) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "call not support empty extras");
            bundle2.putInt(KEY_UPDATE_RESULT, 1);
            return bundle2;
        }
        int i = bundle.getInt(DynamicEventRepository.EXTRA_EVENT_TYPE, -1);
        if (i == -1) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "call not support eventType: " + i);
            bundle2.putInt(KEY_UPDATE_RESULT, 1);
            return bundle2;
        }
        String callingPackage = getCallingPackage();
        if (callingPackage != null) {
            OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getOplusStatusBarInfoControllerEx().sendDynamicEvent(callingPackage, i, bundle);
            bundle2.putInt(KEY_UPDATE_RESULT, 0);
        }
        return bundle2;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            return true;
        }
        Log.d("SystemUi--Statusbar", "OplusSystemInfoProvider-->onCreate");
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
