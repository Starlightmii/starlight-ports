package com.oplus.systemui.notification.lockscreen.stack.updater;

import android.os.Bundle;
import android.util.Log;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.seedling.ImmersiveViewEntry;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingImmersiveViewState;
import com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImmersiveUpdater implements IStackViewUpdater {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ImmersiveUpdater";
    private final Lazy capsuleCardControl;
    private final Lazy lockScreenExtStateController;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private StackCardControl.StackType stackType = StackCardControl.StackType.NOT_STACK;
    private final Lazy statusBarKeyguardViewManagerLazy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ImmersiveUpdater(Lazy lazy, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, Lazy lazy2, Lazy lazy3) {
        this.capsuleCardControl = lazy;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.statusBarKeyguardViewManagerLazy = lazy2;
        this.lockScreenExtStateController = lazy3;
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
        return 2;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void setStackType(StackCardControl.StackType stackType) {
        this.stackType = stackType;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void updateToExt(List<? extends ExpandableView> list) {
        SeedlingImmersiveViewState viewState;
        if (((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() || ((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerLazy.get()).getQsExpansion() != 1.0f) {
            int i = 0;
            for (ExpandableView expandableView : list) {
                if (expandableView instanceof OplusCustomRow) {
                    OplusCustomRow oplusCustomRow = (OplusCustomRow) expandableView;
                    CustomRowEntry entry = oplusCustomRow.getEntry();
                    String key = entry != null ? entry.getKey() : null;
                    Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getExtra();
                    if (Intrinsics.areEqual(key, extra != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra) : null)) {
                        ExpandableViewState viewState2 = oplusCustomRow.getViewState();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "ImmersiveUpdater-->".concat("handleStackViewForImmersive: need show " + oplusCustomRow));
                        }
                        ((ViewState) viewState2).hidden = false;
                        viewState2.setAlpha(1.0f, "ImmersiveUpdater.handleStackViewForImmersive");
                        float stackY = ((CapsuleCardControl) this.capsuleCardControl.get()).getStackY();
                        Lazy lazy = this.capsuleCardControl;
                        if (stackY > -20.0f) {
                            viewState2.setYTranslation((((CapsuleCardControl) lazy.get()).getCapsuleTop() - viewState2.height) - CapsuleConfig.INSTANCE.getCardMarginBottom(), TAG);
                        } else {
                            viewState2.setYTranslation((((CapsuleCardControl) this.capsuleCardControl.get()).getStackY() + ((((CapsuleCardControl) lazy.get()).getCapsuleTop() - viewState2.height) - CapsuleConfig.INSTANCE.getCardMarginBottom())) - (-20.0f), "ImmersiveUpdater.handleStackViewForImmersive.less");
                        }
                        viewState2.mExpandableViewStateEx.setImmersiveProgress(1.0f);
                        ImmersiveViewEntry immersiveViewEntry = oplusCustomRow.getImmersiveViewEntry();
                        if (immersiveViewEntry != null && (viewState = immersiveViewEntry.getViewState()) != null) {
                            viewState.setImmersiveState(1);
                            viewState.setFromExtState(((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getLastExtState());
                            viewState.setToExtState(2);
                        }
                    }
                }
                ExpandableViewState viewState3 = expandableView.getViewState();
                int i2 = i + 1;
                float stackViewScaleInCapsuleState = StackCardControl.Companion.getStackViewScaleInCapsuleState(expandableView, i);
                ((ViewState) viewState3).hidden = true;
                viewState3.setAlpha(1.0f, "ImmersiveUpdater.hideExpandView");
                viewState3.setYTranslation((((1.0f - stackViewScaleInCapsuleState) * ((CapsuleCardControl) this.capsuleCardControl.get()).getItemHeight()) / 2.0f) + ((CapsuleCardControl) this.capsuleCardControl.get()).getCapsuleTop(), "TAG.handleStackViewForCapsule");
                int width = (int) (expandableView.getWidth() - (((CapsuleCardControl) this.capsuleCardControl.get()).getItemWidth() * stackViewScaleInCapsuleState));
                expandableView.mExpandableViewEx.setTargetClipHeight(viewState3.height - ((int) (((CapsuleCardControl) this.capsuleCardControl.get()).getItemHeight() * stackViewScaleInCapsuleState)));
                expandableView.mExpandableViewEx.setTargetClipWidth(width);
                viewState3.mExpandableViewStateEx.setClipWidth(width);
                viewState3.mExpandableViewStateEx.setBlurRadio(70.0f);
                viewState3.mExpandableViewStateEx.setAnimationCornerRadius(((CapsuleCardControl) this.capsuleCardControl.get()).getItemHeight() / 2.0f);
                expandableView.getViewState().mExpandableViewStateEx.setCapsuleProgress(1.0f);
                i = i2;
            }
        }
    }
}
