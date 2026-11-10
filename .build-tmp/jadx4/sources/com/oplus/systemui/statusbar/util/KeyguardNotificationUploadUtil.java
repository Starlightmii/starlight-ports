package com.oplus.systemui.statusbar.util;

import android.content.Context;
import android.util.Log;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardNotificationUploadUtil {
    private static final String EVENT_ID_KEYGUARD_NOTIFICATION_OPERATE = "keyguard_notification_operate_action";
    private static final String FIRST_USER_OPERATION = "first_user_operation";
    private static final String SHOW_NOTIFICATION_COUNT = "show_notification_count";
    private static final String TAG = "KeyguardNotificationUploadUtil";
    private static final String USER_CLOSED_COUNT = "user_closed_count";
    private static final String USER_EXPANSION_COUNT = "user_expansion_count";
    public int maxAllowedKeyguardNotifications;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new KeyguardNotificationUploadUtil$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final KeyguardNotificationUploadUtil getInstance() {
            return (KeyguardNotificationUploadUtil) KeyguardNotificationUploadUtil.instance$delegate.getValue();
        }
    }

    public final void collectStackNotificationData(List<? extends ExpandableView> list) {
        ExpandableViewState viewState;
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            ExpandableView expandableView = (ExpandableView) obj;
            if (((expandableView == null || (viewState = expandableView.getViewState()) == null || (oplusExpandableViewStateEx = viewState.mExpandableViewStateEx) == null) ? 0.0f : oplusExpandableViewStateEx.getNotificationStackedProgress()) > 0.0f && !((ViewState) expandableView.getViewState()).hidden) {
                arrayList.add(obj);
            }
        }
        arrayList.size();
    }

    public final int getMaxAllowedKeyguardNotifications() {
        return this.maxAllowedKeyguardNotifications;
    }

    public final void setMaxAllowedKeyguardNotifications(int i) {
        this.maxAllowedKeyguardNotifications = i;
    }

    public final void uploadData(Context context) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "KeyguardNotificationUploadUtil-->The user has no operation for the first time");
        }
    }
}
