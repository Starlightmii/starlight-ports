package com.oplus.systemui.notification.row;

import android.content.Context;
import com.android.systemui.DependencyEx;
import com.android.systemui.notification.stackednotification.StackedNotificationEx;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.OplusStackScrollAlgorithmEx;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.LargeScreenUtils;
import com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController;
import com.oplus.systemui.panelanimation.platformblur.MaterialBlurStateManager;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusShelfExImpl implements OplusShelfEx {
    private final Context context;
    private final HeadsUpManager headsUpManager;
    private final MaterialBlurStateManager materialBlurStateManager;
    private final OplusNotificationTiltShiftBlurController notificationTiltShiftBlurController;
    public boolean splitNotificationShade;

    public OplusShelfExImpl(Context context, MaterialBlurStateManager materialBlurStateManager, OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController, HeadsUpManager headsUpManager) {
        this.context = context;
        this.materialBlurStateManager = materialBlurStateManager;
        this.notificationTiltShiftBlurController = oplusNotificationTiltShiftBlurController;
        this.headsUpManager = headsUpManager;
    }

    public final boolean hasPinnedHeadsUp() {
        return this.headsUpManager.hasPinnedHeadsUp();
    }

    @Override // com.oplus.systemui.notification.row.OplusShelfEx
    public void tryForceDisableFeature(NotificationShelf notificationShelf, NotificationIconContainer notificationIconContainer) {
        if (notificationShelf != null) {
            notificationShelf.setVisibility(4);
        }
        if (notificationIconContainer != null) {
            notificationIconContainer.setVisibility(4);
        }
    }

    @Override // com.oplus.systemui.notification.row.OplusShelfEx
    public boolean updateAppearanceJustClipChildren(NotificationShelf notificationShelf, NotificationStackScrollLayout notificationStackScrollLayout, AmbientState ambientState, int i, Function3 function3, Function1 function1) {
        if (notificationShelf == null || notificationStackScrollLayout == null || ambientState == null) {
            return true;
        }
        float translationY = notificationShelf.getTranslationY();
        float fCalculateNotificationStackDrawStart = ((OplusStackScrollAlgorithmEx) DependencyEx.sDependency.getDependency(OplusStackScrollAlgorithmEx.class)).calculateNotificationStackDrawStart(notificationStackScrollLayout, ambientState);
        boolean z = this.materialBlurStateManager.getTiltShiftBlurEnabled() && this.notificationTiltShiftBlurController.isShiftBlurViewVisible();
        Integer numValueOf = null;
        int iMax = 0;
        int iIntValue = 0;
        for (ActivatableNotificationView activatableNotificationView : SequencesKt___SequencesKt.filter(ConvenienceExtensionsKt.getChildren(notificationStackScrollLayout), OplusShelfExImpl$updateAppearanceJustClipChildren$$inlined$filterIsInstance$1.INSTANCE)) {
            if (activatableNotificationView.needsClippingToShelf() && activatableNotificationView.getVisibility() != 8) {
                float translationY2 = activatableNotificationView.getTranslationY();
                updateNotificationTopClip(activatableNotificationView, fCalculateNotificationStackDrawStart, z);
                iMax = z ? 0 : Math.max(((Number) function3.invoke(activatableNotificationView, Float.valueOf(translationY), Integer.valueOf(iIntValue))).intValue(), iMax);
                if (activatableNotificationView instanceof ExpandableNotificationRow) {
                    if (translationY2 >= translationY && numValueOf == null) {
                        numValueOf = Integer.valueOf(iIntValue);
                    }
                    iIntValue++;
                }
                if (activatableNotificationView instanceof ActivatableNotificationView) {
                    notificationShelf.updateCornerRoundnessOnScroll(activatableNotificationView, translationY2, translationY);
                }
            }
        }
        notificationShelf.clipTransientViews();
        notificationShelf.setClipTopAmount(iMax);
        notificationShelf.setVisibility(4);
        if (numValueOf != null) {
            iIntValue = numValueOf.intValue();
        }
        function1.invoke(Integer.valueOf(iIntValue));
        StackedNotificationEx stackedNotificationEx = (StackedNotificationEx) DependencyEx.sDependency.getDependency(StackedNotificationEx.class);
        if (!stackedNotificationEx.isStackedNotification$1()) {
            return true;
        }
        Sequence sequenceFilter = SequencesKt___SequencesKt.filter(ConvenienceExtensionsKt.getChildren(notificationStackScrollLayout), OplusShelfExImpl$updateAppearanceJustClipChildren$$inlined$filterIsInstance$1.INSTANCE$1);
        OplusShelfExImpl$transientExpandable$iterator$1 oplusShelfExImpl$transientExpandable$iterator$1 = new OplusShelfExImpl$transientExpandable$iterator$1();
        oplusShelfExImpl$transientExpandable$iterator$1.$nssl = notificationStackScrollLayout;
        oplusShelfExImpl$transientExpandable$iterator$1.currIndex = -1;
        VarHandle.storeStoreFence();
        OplusShelfExImpl$transientExpandable$$inlined$Sequence$1 oplusShelfExImpl$transientExpandable$$inlined$Sequence$1 = new OplusShelfExImpl$transientExpandable$$inlined$Sequence$1();
        oplusShelfExImpl$transientExpandable$$inlined$Sequence$1.$iterator$inlined = oplusShelfExImpl$transientExpandable$iterator$1;
        VarHandle.storeStoreFence();
        stackedNotificationEx.updateAppearanceForStackedNotification(sequenceFilter, oplusShelfExImpl$transientExpandable$$inlined$Sequence$1, notificationShelf);
        return true;
    }

    public final void updateNotificationTopClip(ExpandableView expandableView, float f, boolean z) {
        expandableView.setClipTopAmount(z ? 0 : Math.max((int) (expandableView.getHeight() - ((expandableView.getTranslationY() + expandableView.getHeight()) - f)), 0));
    }

    public final void updateResource() {
        this.splitNotificationShade = LargeScreenUtils.shouldUseSplitNotificationShade(this.context.getResources());
    }
}
