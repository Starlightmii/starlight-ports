package com.oplus.systemui.statusbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.OplusStatusBarIconViewEx;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarIconViewExImpl implements OplusStatusBarIconViewEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarIconView";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public Drawable reloadIconFromContext(int i, Context context, StatusBarIcon statusBarIcon) {
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarIconView-->".concat("load Icon failed, try to reload from app context, icon = " + statusBarIcon.icon));
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            Drawable drawableLoadDrawableAsUser = statusBarIcon.icon.loadDrawableAsUser(context.createPackageContext(statusBarIcon.pkg, 0), i);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarIconView-->".concat("reload from app context - time = " + (System.currentTimeMillis() - jCurrentTimeMillis)));
            }
            return drawableLoadDrawableAsUser;
        } catch (Exception e) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                return null;
            }
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("reload Icon failed!!! error = ", e.getMessage(), "OplusStatusBarIconView-->", "SystemUi--Notification");
            return null;
        }
    }
}
