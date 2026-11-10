package com.oplus.systemui.statusbar.icon.ui.viewmodel;

import android.graphics.drawable.Icon;
import android.util.Log;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconInfo;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaViewModel$icons$1$2 implements Function2 {
    public /* synthetic */ OplusNotificationIconAreaViewModel this$0;

    /* JADX WARN: Code duplicated, block: B:23:0x0072  */
    public final Object invoke(Object obj, Object obj2) {
        boolean zAreEqual;
        NotificationIconsViewData notificationIconsViewData = (NotificationIconsViewData) obj2;
        OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel = this.this$0;
        List visibleIcons = ((NotificationIconsViewData) obj).getVisibleIcons();
        List visibleIcons2 = notificationIconsViewData.getVisibleIcons();
        int iconLimit = notificationIconsViewData.getIconLimit();
        oplusNotificationIconAreaViewModel.getClass();
        boolean z = false;
        if (visibleIcons2.isEmpty() && visibleIcons.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationIconAreaViewModel-->checkNeedFreshIcon: false, empty");
            }
        } else if (visibleIcons2.isEmpty() || visibleIcons.size() != visibleIcons2.size()) {
            z = true;
        } else {
            int size = visibleIcons2.size();
            if (size <= iconLimit) {
                iconLimit = size;
            }
            int i = 0;
            while (true) {
                if (i < iconLimit) {
                    Icon sourceIcon = ((NotificationIconInfo) visibleIcons2.get(i)).getSourceIcon();
                    Icon sourceIcon2 = ((NotificationIconInfo) visibleIcons.get(i)).getSourceIcon();
                    if (Intrinsics.areEqual(sourceIcon, sourceIcon2)) {
                        zAreEqual = true;
                    } else if (sourceIcon.getType() == sourceIcon2.getType()) {
                        int type = sourceIcon.getType();
                        if (type != 2) {
                            if (type == 4 || type == 6) {
                                zAreEqual = Intrinsics.areEqual(sourceIcon.getUriString(), sourceIcon2.getUriString());
                            }
                        } else if (Intrinsics.areEqual(sourceIcon.getResPackage(), sourceIcon2.getResPackage()) && sourceIcon.getResId() == sourceIcon2.getResId()) {
                            zAreEqual = true;
                        }
                        zAreEqual = false;
                    } else {
                        zAreEqual = false;
                    }
                    if (!zAreEqual) {
                        break;
                    }
                    i++;
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("checkNeedFreshIcon: false, size: ", visibleIcons.size(), iconLimit, ", showIconNum: ", "OplusNotificationIconAreaViewModel-->"));
                }
            }
            z = true;
        }
        return Boolean.valueOf(!z);
    }
}
