package com.oplus.systemui.statusbar.phone;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusUsbDialogInfoProvider extends ContentProvider {
    public static final String EXTRA_BLOCKED = "blocked";
    private static final String KEY_RESULT = "result";
    public static final String METHOD_CONFIRM_USB_DIALOG_BLOCKED = "confirmUsbDialogBlocked";
    public static final int RESET_BLOCKED_VALUE = -1;
    public static final int RESULT_FAILED = 0;
    public static final int RESULT_SUCCESS = 1;
    private static final String TAG = "OplusUsbDialogInfoProvider";
    public static final Companion Companion = new Companion();
    private static final Uri USB_DIALOG_INFO_PROVIDER_URI = Uri.parse("content://com.oplus.systemui.OplusUsbDialogInfoProvider");
    private static final AtomicInteger sCurrentBlockedValue = new AtomicInteger(-1);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getCurrentBlockedValue() {
            return OplusUsbDialogInfoProvider.sCurrentBlockedValue.get();
        }
    }

    public static final int getCurrentBlockedValue() {
        return Companion.getCurrentBlockedValue();
    }

    public static final void resetCurrentBlockedValue() {
        Companion.getClass();
        sCurrentBlockedValue.set(-1);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        ContentResolver contentResolver;
        Bundle bundle2 = new Bundle();
        if (!Intrinsics.areEqual(str, METHOD_CONFIRM_USB_DIALOG_BLOCKED)) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "Unsupported method: ", str, LogUtil.TAG_STATUSBAR, TAG);
            bundle2.putInt(KEY_RESULT, 0);
            return bundle2;
        }
        if (bundle == null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "Extras cannot be null for ", str, LogUtil.TAG_STATUSBAR, TAG);
            bundle2.putInt(KEY_RESULT, 0);
            return bundle2;
        }
        int i = bundle.getInt(EXTRA_BLOCKED, -1);
        sCurrentBlockedValue.set(i);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "confirmUsbDialogBlocked: blocked = ", "OplusUsbDialogInfoProvider-->", "SystemUi--Statusbar");
        }
        Context context = getContext();
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            contentResolver.notifyChange(USB_DIALOG_INFO_PROVIDER_URI, null);
        }
        bundle2.putInt(KEY_RESULT, 1);
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
        Log.d("SystemUi--Statusbar", "OplusUsbDialogInfoProvider-->onCreate");
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
