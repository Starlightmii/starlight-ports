package com.oplus.systemui.separate;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LightBarControllerImpl;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.Clock;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.base.res.drawable.GradientTileDrawable;
import com.oplus.systemui.qs.base.res.model.DrawableStateKt;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.pipeline.data.repository.WellBeingAssistantRepositoryImpl;
import com.oplus.systemui.qs.res.domain.util.ResourcesUtilKt;
import com.oplus.systemui.qs.widget.OplusQSClock;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplus.systemui.statusbar.clock.ui.ClockSecondsViewBinder;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.MaskBlurDrawable;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String DISABLE_STATUS_BAR_ANIMATE_TAG = "notif_panel_clock";
    public static final String GOOGLE_CALENDAR_PKG_NAME = "com.google.android.calendar";
    public static final String KEY_CALENDAR_ENTER_TYPE = "calendar_enter_type";
    public static final String KEY_CHECK_PASSWORD = "KEY_CHECK_PASSWORD";
    public static final String TAG = "OplusQSSimpleHeader";
    private ActivityStarter activityStarter;
    private final Lazy clockSecondsViewBinder$delegate;
    private OplusQSClock clockView;
    private OplusQSDateView dateView;
    private final OplusQSSimpleHeader$elementShowingChangeListener$1 elementShowingChangeListener;
    private ViewGroup headerController;
    public boolean isInSuperPowerSaveMode;
    public boolean isInWellBeingAssistantMode;
    private final Lazy lightBarController$delegate;
    private final Lazy oplusQSActivityStarter$delegate;
    private final Lazy panelAnimationEx$delegate;
    private final OplusQSSimpleHeader$panelCollapseListener$1 panelCollapseListener;
    private ViewGroup qsButtonContainer;
    private final Lazy qsFakeController$delegate;
    private ViewGroup qsNITZContainer;
    private ViewGroup qsStatusIconsContainer;
    private ImageView settingsButton;
    private ViewGroup settingsContainer;
    private ViewGroup simpleQSContainer;
    private final View.OnLayoutChangeListener simpleQsLayoutChangeListener;
    public boolean statusBarAnimateDisabledForClock;
    private OplusQSSimpleHeader$superPowerListener$1 superPowerListener;
    private final Lazy viewBlurManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSSimpleHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        OplusQSSimpleHeader$simpleQsLayoutChangeListener$1 oplusQSSimpleHeader$simpleQsLayoutChangeListener$1 = new OplusQSSimpleHeader$simpleQsLayoutChangeListener$1();
        oplusQSSimpleHeader$simpleQsLayoutChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.simpleQsLayoutChangeListener = oplusQSSimpleHeader$simpleQsLayoutChangeListener$1;
        final int i = 0;
        this.viewBlurManager$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.separate.OplusQSSimpleHeader$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 1:
                        OplusQSSimpleHeader.Companion companion2 = OplusQSSimpleHeader.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    case 2:
                        OplusQSSimpleHeader.Companion companion3 = OplusQSSimpleHeader.Companion;
                        return (OplusSimpleQSFakeController) DependencyEx.sDependency.getDependency(OplusSimpleQSFakeController.class);
                    case 3:
                        OplusQSSimpleHeader.Companion companion4 = OplusQSSimpleHeader.Companion;
                        return (OplusQSActivityStarter) DependencyEx.sDependency.getDependency(OplusQSActivityStarter.class);
                    case 4:
                        OplusQSSimpleHeader.Companion companion5 = OplusQSSimpleHeader.Companion;
                        return (ClockSecondsViewBinder) DependencyEx.sDependency.getDependency(ClockSecondsViewBinder.class);
                    default:
                        OplusQSSimpleHeader.Companion companion6 = OplusQSSimpleHeader.Companion;
                        return (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
                }
            }
        });
        OplusQSSimpleHeader$superPowerListener$1 oplusQSSimpleHeader$superPowerListener$1 = new OplusQSSimpleHeader$superPowerListener$1();
        oplusQSSimpleHeader$superPowerListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.superPowerListener = oplusQSSimpleHeader$superPowerListener$1;
        final int i2 = 1;
        this.panelAnimationEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.separate.OplusQSSimpleHeader$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 1:
                        OplusQSSimpleHeader.Companion companion2 = OplusQSSimpleHeader.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    case 2:
                        OplusQSSimpleHeader.Companion companion3 = OplusQSSimpleHeader.Companion;
                        return (OplusSimpleQSFakeController) DependencyEx.sDependency.getDependency(OplusSimpleQSFakeController.class);
                    case 3:
                        OplusQSSimpleHeader.Companion companion4 = OplusQSSimpleHeader.Companion;
                        return (OplusQSActivityStarter) DependencyEx.sDependency.getDependency(OplusQSActivityStarter.class);
                    case 4:
                        OplusQSSimpleHeader.Companion companion5 = OplusQSSimpleHeader.Companion;
                        return (ClockSecondsViewBinder) DependencyEx.sDependency.getDependency(ClockSecondsViewBinder.class);
                    default:
                        OplusQSSimpleHeader.Companion companion6 = OplusQSSimpleHeader.Companion;
                        return (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
                }
            }
        });
        final int i3 = 2;
        this.qsFakeController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.separate.OplusQSSimpleHeader$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 1:
                        OplusQSSimpleHeader.Companion companion2 = OplusQSSimpleHeader.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    case 2:
                        OplusQSSimpleHeader.Companion companion3 = OplusQSSimpleHeader.Companion;
                        return (OplusSimpleQSFakeController) DependencyEx.sDependency.getDependency(OplusSimpleQSFakeController.class);
                    case 3:
                        OplusQSSimpleHeader.Companion companion4 = OplusQSSimpleHeader.Companion;
                        return (OplusQSActivityStarter) DependencyEx.sDependency.getDependency(OplusQSActivityStarter.class);
                    case 4:
                        OplusQSSimpleHeader.Companion companion5 = OplusQSSimpleHeader.Companion;
                        return (ClockSecondsViewBinder) DependencyEx.sDependency.getDependency(ClockSecondsViewBinder.class);
                    default:
                        OplusQSSimpleHeader.Companion companion6 = OplusQSSimpleHeader.Companion;
                        return (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
                }
            }
        });
        final int i4 = 3;
        this.oplusQSActivityStarter$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.separate.OplusQSSimpleHeader$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 1:
                        OplusQSSimpleHeader.Companion companion2 = OplusQSSimpleHeader.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    case 2:
                        OplusQSSimpleHeader.Companion companion3 = OplusQSSimpleHeader.Companion;
                        return (OplusSimpleQSFakeController) DependencyEx.sDependency.getDependency(OplusSimpleQSFakeController.class);
                    case 3:
                        OplusQSSimpleHeader.Companion companion4 = OplusQSSimpleHeader.Companion;
                        return (OplusQSActivityStarter) DependencyEx.sDependency.getDependency(OplusQSActivityStarter.class);
                    case 4:
                        OplusQSSimpleHeader.Companion companion5 = OplusQSSimpleHeader.Companion;
                        return (ClockSecondsViewBinder) DependencyEx.sDependency.getDependency(ClockSecondsViewBinder.class);
                    default:
                        OplusQSSimpleHeader.Companion companion6 = OplusQSSimpleHeader.Companion;
                        return (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
                }
            }
        });
        final int i5 = 4;
        this.clockSecondsViewBinder$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.separate.OplusQSSimpleHeader$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 1:
                        OplusQSSimpleHeader.Companion companion2 = OplusQSSimpleHeader.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    case 2:
                        OplusQSSimpleHeader.Companion companion3 = OplusQSSimpleHeader.Companion;
                        return (OplusSimpleQSFakeController) DependencyEx.sDependency.getDependency(OplusSimpleQSFakeController.class);
                    case 3:
                        OplusQSSimpleHeader.Companion companion4 = OplusQSSimpleHeader.Companion;
                        return (OplusQSActivityStarter) DependencyEx.sDependency.getDependency(OplusQSActivityStarter.class);
                    case 4:
                        OplusQSSimpleHeader.Companion companion5 = OplusQSSimpleHeader.Companion;
                        return (ClockSecondsViewBinder) DependencyEx.sDependency.getDependency(ClockSecondsViewBinder.class);
                    default:
                        OplusQSSimpleHeader.Companion companion6 = OplusQSSimpleHeader.Companion;
                        return (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
                }
            }
        });
        final int i6 = 5;
        this.lightBarController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.separate.OplusQSSimpleHeader$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 1:
                        OplusQSSimpleHeader.Companion companion2 = OplusQSSimpleHeader.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    case 2:
                        OplusQSSimpleHeader.Companion companion3 = OplusQSSimpleHeader.Companion;
                        return (OplusSimpleQSFakeController) DependencyEx.sDependency.getDependency(OplusSimpleQSFakeController.class);
                    case 3:
                        OplusQSSimpleHeader.Companion companion4 = OplusQSSimpleHeader.Companion;
                        return (OplusQSActivityStarter) DependencyEx.sDependency.getDependency(OplusQSActivityStarter.class);
                    case 4:
                        OplusQSSimpleHeader.Companion companion5 = OplusQSSimpleHeader.Companion;
                        return (ClockSecondsViewBinder) DependencyEx.sDependency.getDependency(ClockSecondsViewBinder.class);
                    default:
                        OplusQSSimpleHeader.Companion companion6 = OplusQSSimpleHeader.Companion;
                        return (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
                }
            }
        });
        OplusQSSimpleHeader$panelCollapseListener$1 oplusQSSimpleHeader$panelCollapseListener$1 = new OplusQSSimpleHeader$panelCollapseListener$1();
        oplusQSSimpleHeader$panelCollapseListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panelCollapseListener = oplusQSSimpleHeader$panelCollapseListener$1;
        OplusQSSimpleHeader$elementShowingChangeListener$1 oplusQSSimpleHeader$elementShowingChangeListener$1 = new OplusQSSimpleHeader$elementShowingChangeListener$1();
        oplusQSSimpleHeader$elementShowingChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowingChangeListener = oplusQSSimpleHeader$elementShowingChangeListener$1;
    }

    public static final void access$updateTiltShiftBlurHeight(OplusQSSimpleHeader oplusQSSimpleHeader) {
        int bottom;
        ViewGroup viewGroup = oplusQSSimpleHeader.simpleQSContainer;
        if (viewGroup != null && (bottom = viewGroup.getBottom()) > 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(viewGroup.getHeight(), "OplusQSSimpleHeader-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("updateTiltShiftBlurHeight, bottom: ", bottom, viewGroup.getTop(), ", top: ", ", measuredHeight: "));
            }
            oplusQSSimpleHeader.getQsFakeController().updateQsSimpleHeaderHeight(bottom);
        }
    }

    private final ClockSecondsViewBinder getClockSecondsViewBinder() {
        return (ClockSecondsViewBinder) this.clockSecondsViewBinder$delegate.getValue();
    }

    private final LightBarController getLightBarController() {
        return (LightBarController) this.lightBarController$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusQSActivityStarter getOplusQSActivityStarter() {
        return (OplusQSActivityStarter) this.oplusQSActivityStarter$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PanelAnimationEx getPanelAnimationEx() {
        return (PanelAnimationEx) this.panelAnimationEx$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusSimpleQSFakeController getQsFakeController() {
        return (OplusSimpleQSFakeController) this.qsFakeController$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewBlurManager getViewBlurManager() {
        return (ViewBlurManager) this.viewBlurManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStatusBarAnimateChangeDisabled(boolean z) {
        if (this.statusBarAnimateDisabledForClock == z) {
            return;
        }
        this.statusBarAnimateDisabledForClock = z;
        LightBarControllerImpl lightBarController = getLightBarController();
        lightBarController.mDisableStatusBarAppearanceAnimate = z;
        lightBarController.mDisableTag = DISABLE_STATUS_BAR_ANIMATE_TAG;
    }

    public final void applyHeaderLayoutFromResources() {
        updateSimpleQsContainerPadding();
        int iFixedDimen = fixedDimen(2131170594);
        int iFixedDimen2 = fixedDimen(2131170595);
        ViewGroup viewGroup = this.qsButtonContainer;
        int i = 0;
        if (viewGroup != null) {
            viewGroup.setPadding(0, iFixedDimen2, 0, iFixedDimen);
        }
        int iFixedDimen3 = fixedDimen(2131170593);
        ViewGroup viewGroup2 = this.qsButtonContainer;
        if (viewGroup2 != null) {
            ViewGroup.LayoutParams layoutParams = viewGroup2.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            if (marginLayoutParams != null && marginLayoutParams.bottomMargin != iFixedDimen3) {
                marginLayoutParams.bottomMargin = iFixedDimen3;
                viewGroup2.setLayoutParams(marginLayoutParams);
            }
        }
        ViewGroup viewGroup3 = this.settingsContainer;
        ViewGroup.LayoutParams layoutParams2 = viewGroup3 != null ? viewGroup3.getLayoutParams() : null;
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131170602);
        if (layoutParams2 != null) {
            layoutParams2.height = fixedDimen(2131170592);
        }
        ViewGroup viewGroup4 = this.settingsContainer;
        if (viewGroup4 != null) {
            viewGroup4.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
        }
        Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
        OplusQSSimpleHeader$updateButtonAndDateView$1 oplusQSSimpleHeader$updateButtonAndDateView$1 = new OplusQSSimpleHeader$updateButtonAndDateView$1(i);
        oplusQSSimpleHeader$updateButtonAndDateView$1.this$0 = this;
        VarHandle.storeStoreFence();
        executor.execute(oplusQSSimpleHeader$updateButtonAndDateView$1);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131170602);
        int iFixedDimen4 = fixedDimen(2131170596);
        ViewGroup viewGroup5 = this.headerController;
        if (viewGroup5 != null) {
            viewGroup5.setPaddingRelative(dimensionPixelSize2, 0, dimensionPixelSize2, 0);
            ViewGroup.LayoutParams layoutParams3 = viewGroup5.getLayoutParams();
            layoutParams3.height = iFixedDimen4;
            viewGroup5.setLayoutParams(layoutParams3);
        }
    }

    public final int fixedDimen(int i) {
        return ResourcesUtilKt.lockDpi(i, getContext());
    }

    public final OplusQSClock getClockView() {
        return this.clockView;
    }

    public final OplusQSDateView getDateView() {
        return this.dateView;
    }

    public final ViewGroup getNITZContainer() {
        return this.qsNITZContainer;
    }

    public final ImageView getSettingsButton() {
        return this.settingsButton;
    }

    public final int getSimpleQSHeight() {
        ViewGroup viewGroup = this.simpleQSContainer;
        ViewGroup.LayoutParams layoutParams = viewGroup != null ? viewGroup.getLayoutParams() : null;
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        int i = layoutParams2 != null ? layoutParams2.topMargin : 0;
        ViewGroup viewGroup2 = this.simpleQSContainer;
        return (viewGroup2 != null ? viewGroup2.getHeight() : 0) + i;
    }

    public final ViewGroup getStatusIconsContainer() {
        return this.qsStatusIconsContainer;
    }

    public final void onAZoneHeightChanged(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "updateMarginTop onAZoneHeightChanged height: ", "OplusQSSimpleHeader-->", "SystemUi--Notification");
        }
        updateMarginTop("aZoneHeightChanged", Integer.valueOf(i));
        getQsFakeController().updateFakeMarginTop("aZoneHeightChanged", Integer.valueOf(i));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup viewGroup = this.simpleQSContainer;
        if (viewGroup != null) {
            viewGroup.addOnLayoutChangeListener(this.simpleQsLayoutChangeListener);
        }
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this.superPowerListener);
        getPanelAnimationEx().getElementShowState().addCallback(this.elementShowingChangeListener);
        getPanelAnimationEx().getPanelExpandFraction().addCallback(this.panelCollapseListener);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        this.isInSuperPowerSaveMode = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
        WellBeingAssistantRepositoryImpl.Companion.getClass();
        this.isInWellBeingAssistantMode = WellBeingAssistantRepositoryImpl.isWellBeingAssistantState;
        updateClickAbilities();
        updateMarginTop("configChange", null);
        getQsFakeController().updateFakeMarginTop("configChange", null);
        updateTextColor();
        applyHeaderLayoutFromResources();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ViewGroup viewGroup = this.simpleQSContainer;
        if (viewGroup != null) {
            viewGroup.removeOnLayoutChangeListener(this.simpleQsLayoutChangeListener);
        }
        super.onDetachedFromWindow();
        SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(this.superPowerListener);
        getPanelAnimationEx().getElementShowState().removeCallback(this.elementShowingChangeListener);
        getPanelAnimationEx().getPanelExpandFraction().removeCallback(this.panelCollapseListener);
        setStatusBarAnimateChangeDisabled(false);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onFinishInflate", null, 8);
        onInit();
    }

    public final void onInit() {
        ViewTreeObserver viewTreeObserver;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onInit", null, 8);
        this.simpleQSContainer = (ViewGroup) findViewById(2131365224);
        this.qsStatusIconsContainer = (ViewGroup) findViewById(2131364818);
        this.qsNITZContainer = (ViewGroup) findViewById(2131364721);
        this.qsButtonContainer = (ViewGroup) findViewById(2131362434);
        this.settingsContainer = (ViewGroup) findViewById(2131365157);
        this.headerController = (ViewGroup) findViewById(2131363336);
        this.clockView = (OplusQSClock) findViewById(2131364766);
        this.dateView = (OplusQSDateView) findViewById(2131364311);
        this.settingsButton = (ImageView) findViewById(2131365156);
        this.activityStarter = (ActivityStarter) Dependency.sDependency.getDependencyInner(ActivityStarter.class);
        updateMarginTop("onFinishInflate", null);
        getQsFakeController().initView(this);
        getQsFakeController().updateFakeMarginTop("onFinishInflate", null);
        Clock clock = this.clockView;
        if (clock != null) {
            OplusQSSimpleHeader$initDateView$1 oplusQSSimpleHeader$initDateView$1 = new OplusQSSimpleHeader$initDateView$1(1);
            oplusQSSimpleHeader$initDateView$1.this$0 = this;
            VarHandle.storeStoreFence();
            clock.setOnClickListener(oplusQSSimpleHeader$initDateView$1);
        }
        Clock clock2 = this.clockView;
        if (clock2 != null && (viewTreeObserver = clock2.getViewTreeObserver()) != null) {
            OplusQSSimpleHeader$initClockView$2 oplusQSSimpleHeader$initClockView$2 = new OplusQSSimpleHeader$initClockView$2();
            oplusQSSimpleHeader$initClockView$2.this$0 = this;
            VarHandle.storeStoreFence();
            viewTreeObserver.addOnGlobalLayoutListener(oplusQSSimpleHeader$initClockView$2);
        }
        OplusQSDateView oplusQSDateView = this.dateView;
        if (oplusQSDateView != null) {
            OplusQSSimpleHeader$initDateView$1 oplusQSSimpleHeader$initDateView$2 = new OplusQSSimpleHeader$initDateView$1(0);
            oplusQSSimpleHeader$initDateView$2.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQSDateView.setOnClickListener(oplusQSSimpleHeader$initDateView$2);
        }
        ImageView imageView = this.settingsButton;
        if (imageView != null) {
            OplusQSSimpleHeader$initDateView$1 oplusQSSimpleHeader$initDateView$3 = new OplusQSSimpleHeader$initDateView$1(2);
            oplusQSSimpleHeader$initDateView$3.this$0 = this;
            VarHandle.storeStoreFence();
            imageView.setOnClickListener(oplusQSSimpleHeader$initDateView$3);
        }
        ImageView imageView2 = this.settingsButton;
        if (imageView2 != null) {
            OplusQSSimpleHeader$initSettingsButton$2 oplusQSSimpleHeader$initSettingsButton$2 = new OplusQSSimpleHeader$initSettingsButton$2();
            oplusQSSimpleHeader$initSettingsButton$2.this$0 = this;
            VarHandle.storeStoreFence();
            imageView2.addOnAttachStateChangeListener(oplusQSSimpleHeader$initSettingsButton$2);
        }
        updateTextColor();
        getQsFakeController().resetAllViewsTransitionAlpha(getPanelAnimationEx().getPanelExpandFraction().getRawFraction());
        OplusQSClock oplusQSClock = this.clockView;
        if (oplusQSClock != null) {
            getClockSecondsViewBinder().bindQsClock(oplusQSClock);
        }
        updateClickAbilities();
        applyHeaderLayoutFromResources();
    }

    public final void refreshSettingsButtonBackground() {
        ViewBlurProxy.BlurType blurType;
        ImageView imageView = this.settingsButton;
        if (imageView != null && imageView.getVisibility() == 0) {
            QsColorUtil.INSTANCE.getClass();
            if (QsColorUtil.isSuperPowerSaveState) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusQSSimpleHeader-->refreshSettingsButtonBackground");
            }
            if (NotificationColorUtil.isGlobalThemeApplied(getContext())) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusQSSimpleHeader-->applyGlobalThemeBackground");
                }
                ImageView imageView2 = this.settingsButton;
                if (imageView2 == null) {
                    return;
                }
                getViewBlurManager().releaseBlurProxyForView(imageView2, true, "simpleQsHeader globalTheme");
                GradientTileDrawable gradientTileDrawable = (GradientTileDrawable) QSDrawableBuilder.build$default(new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), Integer.valueOf(getContext().getColor(2131103738)))})), imageView2, true, 4);
                imageView2.setBackground(gradientTileDrawable);
                gradientTileDrawable.setPathProvider(new RoundRectOutlineProvider(fixedDimen(2131172639) / 2, (Float) null));
                imageView2.setImageTintList(ColorStateList.valueOf(((FrameLayout) this).mContext.getColor(2131103746)));
                return;
            }
            if (SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState || ScrimUtil.isLowGaussianLevel(getContext())) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusQSSimpleHeader-->applyNormalColorBackground");
                }
                Drawable drawable = AppCompatResources.getDrawable(getContext(), 2131236574);
                ImageView imageView3 = this.settingsButton;
                if (imageView3 == null) {
                    return;
                }
                getViewBlurManager().releaseBlurProxyForView(imageView3, true, "simpleQsHeader nonPlatform");
                imageView3.setBackground(drawable);
                imageView3.setImageTintList(ColorStateList.valueOf(NotificationColorUtil.getDateTimeDefaultColor(getContext())));
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusQSSimpleHeader-->applyMixColorBackground");
            }
            Drawable drawable2 = AppCompatResources.getDrawable(getContext(), 2131236574);
            ImageView imageView4 = this.settingsButton;
            if (imageView4 == null) {
                return;
            }
            ViewBlurProxy viewBlurProxyRequireBlurProxyForView = getViewBlurManager().requireBlurProxyForView(imageView4, ViewBlurManager.CardType.SETTINGS);
            if (ScrimUtil.isBPAnimLevel()) {
                blurType = ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE;
            } else {
                blurType = GaussBlurUtils.isGaussBlurDisabled() ? ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE : ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
            }
            viewBlurProxyRequireBlurProxyForView.setBlurType(blurType);
            int iFixedDimen = fixedDimen(2131172639);
            BlurConfig blurConfig = viewBlurProxyRequireBlurProxyForView.getBlurConfig();
            blurConfig.setCornerRadius(iFixedDimen >> 1);
            blurConfig.setRadiusWeight(null);
            NotificationEffectRepository.Companion.getClass();
            if (!NotificationEffectRepository.isSupportBlurStroke ? false : ScrimUtil.isMiddleOrHighAnimLevel(getContext())) {
                GradientStrokeLineAdapter.INSTANCE.adaptGradientStrokeParams(blurConfig, blurConfig.getCornerRadius(), blurConfig.getRadiusWeight(), iFixedDimen, iFixedDimen, true, GradientStrokeLineAdapter.getNotificationBlurLightConfig(true, getContext().getResources().getConfiguration().isNightModeActive()));
            } else {
                GradientStrokeLineAdapter.clearGradientStrokeParams(blurConfig);
                blurConfig.setGradientStrokeCornerParam(SmoothRoundEx.INSTANCE.createCornerParams(blurConfig.getCornerRadius(), false));
            }
            viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
            Drawable background = imageView4.getBackground();
            MaskBlurDrawable maskBlurDrawable = background instanceof MaskBlurDrawable ? (MaskBlurDrawable) background : null;
            if (maskBlurDrawable != null) {
                maskBlurDrawable.setDefaultDrawable(drawable2);
                maskBlurDrawable.setViewBlurProxy(viewBlurProxyRequireBlurProxyForView);
                maskBlurDrawable.setMaskColor(0);
            } else {
                imageView4.setBackground(new MaskBlurDrawable(viewBlurProxyRequireBlurProxyForView, drawable2, 0));
            }
            imageView4.setImageTintList(ColorStateList.valueOf(NotificationColorUtil.getDateTimeDefaultColor(getContext())));
        }
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        getQsFakeController().updateQSFakeFixedContainerTy(f);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateClickAbilities() {
        boolean zIsElementShowing = getPanelAnimationEx().isElementShowing();
        boolean z = this.isInWellBeingAssistantMode;
        boolean z2 = (z || this.isInSuperPowerSaveMode) ? false : true;
        boolean z3 = z2 && zIsElementShowing;
        boolean z4 = this.isInSuperPowerSaveMode;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("deviceAllowsClick: ", ", shouldClick: ", ", isInSuperPowerSaveMode: ", z2, z3);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isInWellBeingAssistantMode: ", ", isElementShowing = ", sbM, z4, z);
        sbM.append(zIsElementShowing);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        Clock clock = this.clockView;
        if (clock != null) {
            clock.setClickable(z3);
        }
        OplusQSDateView oplusQSDateView = this.dateView;
        if (oplusQSDateView != null) {
            oplusQSDateView.setClickable(z3);
        }
        ImageView imageView = this.settingsButton;
        if (imageView != null) {
            imageView.setClickable(z3);
        }
        ImageView imageView2 = this.settingsButton;
        if (imageView2 != null) {
            imageView2.setVisibility(z2 ? 0 : 4);
        }
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0003 A[PHI: r7
      0x0003: PHI (r7v11 java.lang.Integer) = (r7v0 java.lang.Integer), (r7v5 java.lang.Integer) binds: [B:3:0x0001, B:9:0x001a] A[DONT_GENERATE, DONT_INLINE]] */
    public final void updateMarginTop(String str, Integer num) {
        int dimensionPixelSize;
        ViewGroup viewGroup;
        if (num == null) {
            OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx = (OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class);
            num = oplusSeparateNotificationAndQSEx != null ? oplusSeparateNotificationAndQSEx.getStatusBarTopSpacing() : null;
            if (num != null) {
                dimensionPixelSize = num.intValue();
            } else {
                dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131170599);
            }
        } else {
            dimensionPixelSize = num.intValue();
        }
        ViewGroup viewGroup2 = this.simpleQSContainer;
        ViewGroup.LayoutParams layoutParams = viewGroup2 != null ? viewGroup2.getLayoutParams() : null;
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        int i = layoutParams2 != null ? layoutParams2.topMargin : 0;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(dimensionPixelSize, "header updateMarginTop, reason: ", str, ", newMarginTop: ", " currentMarginTop ");
        sbM.append(i);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (i == dimensionPixelSize || (viewGroup = this.simpleQSContainer) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams3 = viewGroup.getLayoutParams();
        FrameLayout.LayoutParams layoutParams4 = layoutParams3 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams3 : null;
        if (layoutParams4 != null) {
            layoutParams4.topMargin = dimensionPixelSize;
        }
        viewGroup.setLayoutParams(viewGroup.getLayoutParams());
    }

    public final void updateSimpleQsContainerPadding() {
        int iFixedDimen = fixedDimen(2131170595);
        ViewGroup viewGroup = this.simpleQSContainer;
        if (viewGroup != null) {
            viewGroup.setPaddingRelative(0, iFixedDimen, 0, 0);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateTextColor() {
        refreshSettingsButtonBackground();
        if (QsColorUtil.isTextNeedUseLightColorWhenLight(getContext(), QsColorUtil.getQsColorState())) {
            int dateTimeDefaultColor = NotificationColorUtil.getDateTimeDefaultColor(getContext());
            Clock clock = this.clockView;
            if (clock != null) {
                clock.setTextColor(dateTimeDefaultColor);
            }
            OplusQSDateView oplusQSDateView = this.dateView;
            if (oplusQSDateView != null) {
                oplusQSDateView.setTextColor(dateTimeDefaultColor);
                return;
            }
            return;
        }
        boolean zIsGlobalThemeApplied = NotificationColorUtil.isGlobalThemeApplied(getContext());
        Context context = ((FrameLayout) this).mContext;
        if (zIsGlobalThemeApplied) {
            int color = context.getColor(2131103705);
            Clock clock2 = this.clockView;
            if (clock2 != null) {
                clock2.setTextColor(color);
            }
            OplusQSDateView oplusQSDateView2 = this.dateView;
            if (oplusQSDateView2 != null) {
                oplusQSDateView2.setTextColor(color);
                return;
            }
            return;
        }
        int color2 = context.getColor(2131103703);
        int color3 = ((FrameLayout) this).mContext.getColor(2131103702);
        Clock clock3 = this.clockView;
        if (clock3 != null) {
            clock3.setTextColor(color3);
        }
        OplusQSDateView oplusQSDateView3 = this.dateView;
        if (oplusQSDateView3 != null) {
            oplusQSDateView3.setTextColor(color2);
        }
    }
}
