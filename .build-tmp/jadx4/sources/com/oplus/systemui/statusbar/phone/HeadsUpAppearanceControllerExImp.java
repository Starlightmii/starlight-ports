package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceControllerEx;
import com.coui.appcompat.uiutil.ShadowUtils;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpAppearanceControllerExImp extends HeadsUpAppearanceControllerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpAppearanceControllerExImp";
    private final Context context;
    private final ViewBlurManager viewBlurManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public HeadsUpAppearanceControllerExImp(Context context, ViewBlurManager viewBlurManager) {
        super(context);
        this.context = context;
        this.viewBlurManager = viewBlurManager;
    }

    public void initViewController(HeadsUpAppearanceController headsUpAppearanceController) {
        headsUpAppearanceController.init();
    }

    public void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
        ExpandableNotificationRow row;
        if (z || (row = notificationEntry.getRow()) == null) {
            return;
        }
        row.mRowEx.setBoostFrameRate(false);
    }

    public void onHeadsUpUnPinned(NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow) {
        if (expandableNotificationRow == null || !Intrinsics.areEqual(expandableNotificationRow.getEntry(), notificationEntry)) {
            return;
        }
        NotificationUtil.sendCancelMotionEvent((ViewGroup) expandableNotificationRow.getRootView(), " onHeadsUpUnPinned");
    }

    public void updateBackgroundForGroupState(ExpandableNotificationRow expandableNotificationRow) {
        if (expandableNotificationRow.mIsSummaryWithChildren) {
            expandableNotificationRow.updateBackgroundForGroupState();
        }
    }

    public void updateHeadsUpBlurInternal(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[updateHeadsUpBlurInternal] needBlur=, " + expandableNotificationRow + " " + z, null, 8);
        List attachedChildren = expandableNotificationRow.getAttachedChildren();
        ViewBlurManager viewBlurManager = this.viewBlurManager;
        NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal;
        if (!z) {
            viewBlurManager.cancelBlurForHeadsUp(notificationBackgroundView, true);
            if (!expandableNotificationRow.mIsSummaryWithChildren || attachedChildren == null) {
                return;
            }
            Iterator it = attachedChildren.iterator();
            while (it.hasNext()) {
                this.viewBlurManager.cancelBlurForHeadsUp(((ActivatableNotificationView) ((ExpandableNotificationRow) it.next())).mBackgroundNormal, false);
            }
            expandableNotificationRow.updateBackgroundForGroupState();
            return;
        }
        viewBlurManager.resetCornerRadiusForView(notificationBackgroundView);
        this.viewBlurManager.blurForHeadsUp(((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal, true);
        if (!expandableNotificationRow.mIsSummaryWithChildren || attachedChildren == null) {
            return;
        }
        Iterator it2 = attachedChildren.iterator();
        while (it2.hasNext()) {
            this.viewBlurManager.blurForHeadsUp(((ActivatableNotificationView) ((ExpandableNotificationRow) it2.next())).mBackgroundNormal, false);
        }
        expandableNotificationRow.updateBackgroundForGroupState();
    }

    public void updateHeadsUpShadowEffect(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "HeadsUpAppearanceControllerExImp-->".concat("[updateHeadsUpShadowEffect] " + expandableNotificationRow + " " + z));
        }
        if (z) {
            ShadowUtils.setElevationToView(expandableNotificationRow, 3, 0, 0);
        } else {
            ShadowUtils.clearShadow(expandableNotificationRow);
        }
    }
}
