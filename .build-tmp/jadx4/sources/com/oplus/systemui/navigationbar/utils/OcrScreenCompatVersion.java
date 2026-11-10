package com.oplus.systemui.navigationbar.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OcrScreenCompatVersion {
    private static final String KEY_OCR_SCREEN_METADATA = "com.direct.home_navigation.enable";
    private static final String PACKAGENAME_DIRECT = "com.coloros.colordirectservice";
    private static final String TAG = "OcrScreenCompatVersion";
    public static boolean isLastMetaDataSupport;
    public static boolean isMetaDataSupport;
    public static final OcrScreenCompatVersion INSTANCE = new OcrScreenCompatVersion();
    private static final Lazy changeListeners$delegate = LazyKt__LazyJVMKt.lazy(new OcrScreenCompatVersion$$ExternalSyntheticLambda0());
    private static final OcrScreenCompatVersion$appChangeReceiver$1 appChangeReceiver = new OcrScreenCompatVersion$appChangeReceiver$1();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IMetaDataChange {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CopyOnWriteArraySet<IMetaDataChange> getChangeListeners() {
        return (CopyOnWriteArraySet) changeListeners$delegate.getValue();
    }

    public static boolean getOcrScreenMetaDataEnable(Context context) {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(PACKAGENAME_DIRECT, 128);
            boolean z = (applicationInfo == null || (bundle = applicationInfo.metaData) == null) ? false : bundle.getBoolean(KEY_OCR_SCREEN_METADATA, false);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "OcrScreenCompatVersion-->".concat("getOcrScreenEnable isSupport " + z));
            }
            return z;
        } catch (Throwable th) {
            if (ApplicationUtil$.ExternalSyntheticOutline0.m(th) != null) {
                LogUtil.e$default(8, LogUtil.TAG_NAVBAR, TAG, "failed to get ocrScreen metadata");
            }
            return false;
        }
    }

    public final void addMetaDataChangeListener(IMetaDataChange iMetaDataChange) {
        if (getChangeListeners().contains(iMetaDataChange)) {
            return;
        }
        getChangeListeners().add(iMetaDataChange);
    }

    public final void init(Context context) {
        boolean ocrScreenMetaDataEnable = getOcrScreenMetaDataEnable(context);
        isMetaDataSupport = ocrScreenMetaDataEnable;
        isLastMetaDataSupport = ocrScreenMetaDataEnable;
        if (OplusNavBarUtils.isSupportOcrScreen()) {
            OcrScreenCompatVersion$appChangeReceiver$1 ocrScreenCompatVersion$appChangeReceiver$1 = appChangeReceiver;
            if (ocrScreenCompatVersion$appChangeReceiver$1 == null) {
                List list = OpUtils.PACKAGE_LISTENERS;
                return;
            }
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) OpUtils.PACKAGE_LISTENERS;
            if (copyOnWriteArrayList.contains(ocrScreenCompatVersion$appChangeReceiver$1)) {
                return;
            }
            copyOnWriteArrayList.add(ocrScreenCompatVersion$appChangeReceiver$1);
            Log.i("OpUtils", "OpUtils: add pkg broadcast receiver, total: " + copyOnWriteArrayList.size());
        }
    }

    public final void removeMetaDataChangeListener(IMetaDataChange iMetaDataChange) {
        if (getChangeListeners().contains(iMetaDataChange)) {
            getChangeListeners().remove(iMetaDataChange);
        }
    }

    public final void setMetaDataSupport(boolean z) {
        isMetaDataSupport = z;
    }
}
