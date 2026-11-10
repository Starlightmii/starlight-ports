package com.oplus.systemui.notification.lockscreen.stack.updater;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl;
import dagger.Lazy;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleUpdater implements IStackViewUpdater {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleUpdater";
    private final Lazy capsuleCardControl;
    private final Lazy capsuleContainer;
    private StackCardControl.StackType stackType = StackCardControl.StackType.NOT_STACK;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleUpdater(Lazy lazy, Lazy lazy2) {
        this.capsuleCardControl = lazy;
        this.capsuleContainer = lazy2;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public boolean getHasNotificationStacked() {
        return false;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public StackCardControl.StackType getStackType() {
        return this.stackType;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public int getToExtState() {
        return 1;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void setStackType(StackCardControl.StackType stackType) {
        this.stackType = stackType;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void updateToExt(List<? extends ExpandableView> list) {
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            ExpandableNotificationRow expandableNotificationRow = (ExpandableView) obj;
            CapsuleConfig.INSTANCE.getClass();
            if (CapsuleConfig.isLightOs) {
                ExpandableViewState viewState = expandableNotificationRow.getViewState();
                if (((NotificationCapsuleContainer) this.capsuleContainer.get()).isBindToStackView()) {
                    ((ViewState) viewState).hidden = false;
                    viewState.setYTranslation(expandableNotificationRow.getTranslationY(), "CapsuleUpdater.handleStackViewForCapsuleLightOS");
                } else {
                    ((ViewState) viewState).hidden = true;
                }
            } else {
                ExpandableViewState viewState2 = expandableNotificationRow.getViewState();
                float stackViewScaleInCapsuleState = StackCardControl.Companion.getStackViewScaleInCapsuleState(expandableNotificationRow, i);
                ((ViewState) viewState2).hidden = true;
                viewState2.setAlpha(1.0f, "CapsuleUpdater.hideExpandView");
                viewState2.setYTranslation(DrawerArrowDrawable$.ExternalSyntheticOutline0.m(1.0f, stackViewScaleInCapsuleState, ((CapsuleCardControl) this.capsuleCardControl.get()).getItemHeight(), ((CapsuleCardControl) this.capsuleCardControl.get()).getCapsuleTop()), "TAG.handleStackViewForCapsule");
                int width = (int) (expandableNotificationRow.getWidth() - (((CapsuleCardControl) this.capsuleCardControl.get()).getItemWidth() * stackViewScaleInCapsuleState));
                int itemHeight = (int) (((CapsuleCardControl) this.capsuleCardControl.get()).getItemHeight() * stackViewScaleInCapsuleState);
                ((ExpandableView) expandableNotificationRow).mExpandableViewEx.setTargetClipHeight(viewState2.height - itemHeight);
                ((ExpandableView) expandableNotificationRow).mExpandableViewEx.setTargetClipWidth(width);
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
                if (expandableNotificationRow2 != null && ((CapsuleCardControl) this.capsuleCardControl.get()).isLockScreenExtStateAnimRunning() && ((OplusExpandableViewExImpl) ((ExpandableView) expandableNotificationRow2).mExpandableViewEx).canUseTargetShowHeight()) {
                    ((ExpandableView) expandableNotificationRow).mExpandableViewEx.setTargetShowHeight(itemHeight);
                } else {
                    ((ExpandableView) expandableNotificationRow).mExpandableViewEx.setTargetShowHeight(0);
                }
                viewState2.mExpandableViewStateEx.setClipWidth(width);
                viewState2.mExpandableViewStateEx.setBlurRadio(70.0f);
                viewState2.mExpandableViewStateEx.setAnimationCornerRadius(((CapsuleCardControl) this.capsuleCardControl.get()).getItemHeight() / 2.0f);
            }
            expandableNotificationRow.getViewState().mExpandableViewStateEx.setCapsuleProgress(1.0f);
            i = i2;
        }
    }
}
