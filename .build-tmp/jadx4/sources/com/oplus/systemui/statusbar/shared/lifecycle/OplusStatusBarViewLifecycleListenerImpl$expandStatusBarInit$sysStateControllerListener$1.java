package com.oplus.systemui.statusbar.shared.lifecycle;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.window.SurfaceSyncGroup;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.animation.ViewRootSync;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.oplus.systemui.dagger.OplusHomeStatusBarComponent;
import com.oplus.systemui.statusbar.OplusStatusBarDependencyExImpl;
import com.oplus.systemui.statusbar.seeding.CapsulePluginContainerController;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1 implements SysUiStateController.ISysUiStateListener {
    public /* synthetic */ OplusHomeStatusBarComponent $component;
    public /* synthetic */ PhoneStatusBarView $phoneStatusBarView;
    public /* synthetic */ Ref.ObjectRef $phoneStatusBarViewShownAnimator;
    public boolean expanded;
    public SurfaceSyncGroup surfaceSyncGroup;
    public /* synthetic */ OplusStatusBarViewLifecycleListenerImpl this$0;

    public final boolean getExpanded() {
        return this.expanded;
    }

    public final SurfaceSyncGroup getSurfaceSyncGroup() {
        return this.surfaceSyncGroup;
    }

    public final void onPanelExpandedChanged(boolean z, View view) {
        PhoneStatusBarView phoneStatusBarView = this.$phoneStatusBarView;
        OplusStatusBarViewLifecycleListenerImpl oplusStatusBarViewLifecycleListenerImpl = this.this$0;
        boolean zIsFullscreen = ((OplusStatusBarDependencyExImpl) oplusStatusBarViewLifecycleListenerImpl.statusBarDependencyEx.get()).isFullscreen();
        boolean z2 = !zIsFullscreen;
        if (z != this.expanded) {
            if (oplusStatusBarViewLifecycleListenerImpl.keyguardStateController.mKeyguardFadingAway && z) {
                return;
            }
            this.expanded = z;
            SurfaceSyncGroup surfaceSyncGroup = this.surfaceSyncGroup;
            final int i = 0;
            final int i2 = 1;
            boolean z3 = (surfaceSyncGroup == null || surfaceSyncGroup.isComplete()) ? false : true;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", isStatusBarVisible=", "OplusStatusBarViewLifecycleListenerImpl-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("onPanelExpandedChanged isExpanded=", ", isInProgress=", " surfaceSyncGroup:", z, z3), this.surfaceSyncGroup == null, z2));
            }
            boolean z4 = (z3 || zIsFullscreen) ? false : true;
            phoneStatusBarView.getClass();
            if (view != null) {
                view.setVisibility(this.expanded ? 0 : 4);
                if (z4 && this.expanded) {
                    this.surfaceSyncGroup = ViewRootSync.INSTANCE.synchronizeNextDraw(phoneStatusBarView, view, new Function0() { // from class: com.oplus.systemui.statusbar.shared.lifecycle.OplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1$$ExternalSyntheticLambda0
                        public final Object invoke() {
                            switch (i) {
                                case 0:
                                    OplusSysUiSchedMgr.Companion.getInstance().schedForNotificationShadeExpand(false);
                                    break;
                            }
                            return Unit.INSTANCE;
                        }
                    });
                } else if (z4) {
                    this.surfaceSyncGroup = ViewRootSync.INSTANCE.synchronizeNextDraw(phoneStatusBarView, view, new Function0() { // from class: com.oplus.systemui.statusbar.shared.lifecycle.OplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1$$ExternalSyntheticLambda0
                        public final Object invoke() {
                            switch (i2) {
                                case 0:
                                    OplusSysUiSchedMgr.Companion.getInstance().schedForNotificationShadeExpand(false);
                                    break;
                            }
                            return Unit.INSTANCE;
                        }
                    });
                } else if (this.expanded) {
                    OplusSysUiSchedMgr.Companion.getInstance().schedForNotificationShadeExpand(false);
                }
            }
            CapsulePluginContainerController statusBarCapsuleContainerController = this.$component.getStatusBarCapsuleContainerController();
            if (z) {
                statusBarCapsuleContainerController.setCapsuleFakeContainerAlpha(1.0f);
                statusBarCapsuleContainerController.onSeamlessExpandStart();
            } else {
                statusBarCapsuleContainerController.setCapsuleFakeContainerAlpha(0.0f);
                statusBarCapsuleContainerController.onSeamlessCollapseEnd();
            }
            Ref.ObjectRef objectRef = this.$phoneStatusBarViewShownAnimator;
            boolean zBooleanValue = ((Boolean) oplusStatusBarViewLifecycleListenerImpl.shadeAnimationInteractor.isQsLaunchingActivity().getValue()).booleanValue();
            ValueAnimator valueAnimator = (ValueAnimator) objectRef.element;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            float f = z ? 0.0f : 1.0f;
            if (!zBooleanValue || Math.abs(f - phoneStatusBarView.getAlpha()) < 0.05f) {
                phoneStatusBarView.setAlpha(f);
                return;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(phoneStatusBarView, (Property<PhoneStatusBarView, Float>) View.ALPHA, f);
            objectAnimatorOfFloat.setDuration(320L);
            OplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$updatePhoneStatusBarViewShownState$lambda$11$.inlined.doOnEnd.1 r13 = new OplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$updatePhoneStatusBarViewShownState$lambda$11$.inlined.doOnEnd.1();
            r13.$phoneStatusBarViewShownAnimator$inlined = objectRef;
            VarHandle.storeStoreFence();
            objectAnimatorOfFloat.addListener(r13);
            objectAnimatorOfFloat.start();
            objectRef.element = objectAnimatorOfFloat;
        }
    }

    public final void setExpanded(boolean z) {
        this.expanded = z;
    }

    public final void setSurfaceSyncGroup(SurfaceSyncGroup surfaceSyncGroup) {
        this.surfaceSyncGroup = surfaceSyncGroup;
    }
}
