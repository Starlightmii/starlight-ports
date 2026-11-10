package com.oplus.systemui.separate;

import android.content.Context;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.settings.UserTracker;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsCenterModeSelectDialogController$userCallback$1 implements UserTracker.Callback {
    public /* synthetic */ QsCenterModeSelectDialogController this$0;

    public final void onBeforeUserSwitching(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, "QsCenterModeSelectDialogController", ListPopupWindow$.ExternalSyntheticOutline0.m(i, " user change : "), null, 8);
        QsCenterModeSelectDialogController.access$handleDialogDismiss(this.this$0);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    public /* bridge */ /* synthetic */ void onUserChanged(int i, Context context) {
        super.onUserChanged(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }
}
