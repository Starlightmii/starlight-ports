package com.oplus.systemui.notification.lockscreen.capsule;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MathUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.app.animation.Interpolators;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.SourceType;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewProxy;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.util.kotlin.UtilsKt;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.notification.aod.notification.NotificationDetailColorChanger;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.base.LockScreenNotificationKotlinExKt;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.LockScreenAmbientState;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallUtils;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleBlurHelper;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleScrimAlphaHelper;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplus.systemui.notification.lockscreen.effect.AutoRoundRectPathProvider;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.effect.OplusPhysicsPropertyAnimatorExtKt;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationContainerView;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.BaseViewBinder;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleItemView extends ExpandableView implements NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider {
    private static final String NAME_PREFIX = "cap:";
    public static final String TAG = "CapsuleItemView";
    public boolean _expandAnimationRunning;
    public int actualWidth;
    private final Set<Runnable> animationFinishedRunables;
    private final CapsuleBackgroundView bgView;
    private final Lazy bindDataInterceptor$delegate;
    private ExpandableView bindStackView;
    private NotificationDetailColorChanger colorChanger;
    private final FrameLayout contentContainer;
    private final Runnable delayResetDataInterceptTask;
    private ColorMatrixColorFilter dimColorFilter;
    private final ColorMatrix dimColorMatrix;
    public CapsuleItemData entry;
    private final FrameLayout flForegroundBlurView;
    private final AtomicBoolean isAppearAnimating;
    public boolean isHScrolling;
    private final CapsuleItemLocation location;
    private final Lazy lockscreenShadeTransitionController$delegate;
    private final AutoRoundRectPathProvider pathProvider;
    private final CapsuleItemProgressController progressCtrl;
    private final CapsuleScrimView scrimView;
    private CapsuleViewState.ScrollClipInfo scrollClipInfo;
    private final Lazy statusBarStateController$delegate;
    private StokeViewControl stokeViewControl;
    private final ExpandableViewState tempStackState;
    public boolean useSmoothRadius;
    public static final Companion Companion = new Companion();
    private static final SourceType BASE_VALUE = SourceType.Companion.from("BaseValue");
    private static final Lazy extStateController$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleItemView$$ExternalSyntheticLambda0(0));
    private static final Lazy fastFadeOutAlphaInterpolator$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleItemView$$ExternalSyntheticLambda0(1));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleItemLightOSProxy extends CapsuleItemProxy {
        public CapsuleItemLightOSProxy(CapsuleItemView capsuleItemView, boolean z, ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType extStateEventAnimType) {
            super(capsuleItemView, z, extStateEventAnimType);
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView.CapsuleItemProxy
        public void setAlpha(float f) {
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView.CapsuleItemProxy
        public void setCapsuleProgress(float f) {
            CapsuleLightOSController lightOSController;
            CapsuleItemView capsuleItemView = CapsuleItemView.this;
            NotificationCapsuleContainer capsuleContainer = capsuleItemView.getCapsuleContainer();
            if (capsuleContainer == null || (lightOSController = capsuleContainer.getLightOSController()) == null) {
                return;
            }
            if (f < 0.0f) {
                f = 0.0f;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            onExtStateCapsuleProgress(f);
            if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("setCapsuleProgress:LightOS: ", f, "CapsuleItemView-->", "SystemUi--Notification");
            }
            lightOSController.updateAnimProgress(f, this.isShowCapsule);
            if (capsuleItemView.progressCtrl.updateProgress(f).isStep1) {
                capsuleItemView.capsuleAlphaStep();
            } else {
                capsuleItemView.capsuleBlurStep(1.0f);
            }
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView.CapsuleItemProxy
        public void setClipRect(Rect rect) {
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView.CapsuleItemProxy
        public void setTopRadius(float f) {
            super.setTopRadius(f);
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView.CapsuleItemProxy
        public void setTranslationY(float f) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class CapsuleItemProxy implements OplusExpandableViewProxy {
        private final ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType animateType;
        private final CapsuleItemView capsuleItemView;
        private final ExtStateUnbindWatchdog extStateUnbindWatchdog = new ExtStateUnbindWatchdog();
        public boolean isShowCapsule;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class ExtStateUnbindWatchdog {
            public float lastCapsuleProgress = Float.NaN;
            public float lastImmersiveProgress = Float.NaN;
            public long lastProgressChangeTime;
            private final Runnable unbindTimeoutTask;

            public ExtStateUnbindWatchdog() {
                CapsuleItemView$delayResetDataInterceptTask$1 capsuleItemView$delayResetDataInterceptTask$1 = new CapsuleItemView$delayResetDataInterceptTask$1(1);
                capsuleItemView$delayResetDataInterceptTask$1.this$0 = this;
                VarHandle.storeStoreFence();
                this.unbindTimeoutTask = capsuleItemView$delayResetDataInterceptTask$1;
            }

            /* JADX WARN: Type inference failed for: r5v1, types: [android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
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
            public static final void access$handleUnbindTimeout(ExtStateUnbindWatchdog extStateUnbindWatchdog) {
                long jUptimeMillis = SystemClock.uptimeMillis() - extStateUnbindWatchdog.lastProgressChangeTime;
                ?? r5 = CapsuleItemView.this;
                if (jUptimeMillis < 800) {
                    r5.postDelayed(extStateUnbindWatchdog.unbindTimeoutTask, 800 - jUptimeMillis);
                } else {
                    Companion companion = CapsuleItemView.Companion;
                    r5.unBindStackView("extStateUnbindWatchdog timeout", true);
                }
            }

            /* JADX WARN: Type inference failed for: r1v0, types: [android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
            public final void arm() {
                this.lastProgressChangeTime = SystemClock.uptimeMillis();
                CapsuleItemProxy capsuleItemProxy = CapsuleItemProxy.this;
                ?? r1 = CapsuleItemView.this;
                r1.postDelayed(this.unbindTimeoutTask, 800L);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("extStateUnbindWatchdog arm: ", r1.getKey(), ",showCapsule=", capsuleItemProxy.isShowCapsule, "CapsuleItemView-->"));
                }
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
            public final void disarm(String str) {
                ?? r0 = CapsuleItemView.this;
                r0.removeCallbacks(this.unbindTimeoutTask);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("extStateUnbindWatchdog disarm: ", r0.getKey(), ", reason=", str, "CapsuleItemView-->"));
                }
            }
        }

        public CapsuleItemProxy(CapsuleItemView capsuleItemView, boolean z, ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType extStateEventAnimType) {
            this.capsuleItemView = capsuleItemView;
            this.isShowCapsule = z;
            this.animateType = extStateEventAnimType;
        }

        public final void armExtStateUnbindWatchdog() {
            this.extStateUnbindWatchdog.arm();
        }

        public final void disarmExtStateUnbindWatchdog(String str) {
            this.extStateUnbindWatchdog.disarm(str);
        }

        public final ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType getAnimateType() {
            return this.animateType;
        }

        public final CapsuleItemView getCapsuleItemView() {
            return this.capsuleItemView;
        }

        public String getProxyName() {
            return KeyguardLogger$.ExternalSyntheticOutline0.m(CapsuleItemView.NAME_PREFIX, CapsuleItemView.this.getKey(), ":", this.isShowCapsule);
        }

        public View getProxyView() {
            return this.capsuleItemView;
        }

        public final boolean isForCapsuleShowing() {
            return this.isShowCapsule;
        }

        public final void onExtStateCapsuleProgress(float f) {
            ExtStateUnbindWatchdog extStateUnbindWatchdog = this.extStateUnbindWatchdog;
            if (f == extStateUnbindWatchdog.lastCapsuleProgress) {
                return;
            }
            extStateUnbindWatchdog.lastCapsuleProgress = f;
            extStateUnbindWatchdog.lastProgressChangeTime = SystemClock.uptimeMillis();
        }

        public final void onStackViewRemoved() {
            CapsuleItemView capsuleItemView = CapsuleItemView.this;
            ExpandableView expandableView = capsuleItemView.bindStackView;
            if (expandableView != null) {
                capsuleItemView.unBindStackView("onStackViewRemoved", false);
                NotificationCapsuleContainer capsuleContainer = capsuleItemView.getCapsuleContainer();
                if (capsuleContainer != null) {
                    capsuleContainer.generateStackViewRemove(expandableView, capsuleItemView);
                }
            }
        }

        public void setAlpha(float f) {
        }

        public void setCapsuleProgress(float f) {
            onExtStateCapsuleProgress(f);
            CapsuleItemView capsuleItemView = CapsuleItemView.this;
            if (capsuleItemView.bindStackView == null) {
                capsuleItemView.setCapsuleProgress(f, true, this.animateType, "setCapsuleProgress");
            }
        }

        public void setClipRect(Rect rect) {
            if (rect.width() < 0 || rect.height() < 0) {
                return;
            }
            CapsuleItemView capsuleItemView = CapsuleItemView.this;
            if (capsuleItemView.getLocation().getClipLeft() != 0 || capsuleItemView.getLocation().getClipRight() != 0) {
                CapsuleItemLocation location = capsuleItemView.getLocation();
                location.clipLeft = 0;
                location.clipRight = 0;
                location.clipTop = 0;
                location.clipBottom = 0;
            }
            capsuleItemView.setActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(rect.width());
            capsuleItemView.setFinalActualHeight(rect.height());
            int contentWidth = capsuleItemView.getLocation().getContentWidth();
            float f = 1.0f;
            if (contentWidth > 0 && capsuleItemView.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() <= contentWidth) {
                float f2 = contentWidth;
                float fCoerceIn = RangesKt___RangesKt.coerceIn(capsuleItemView.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() / f2, 0.0f, 1.0f);
                ViewExpandKtKt.setPivotXY(capsuleItemView.getContentContainer(), f2 / 2.0f, capsuleItemView.getLocation().getContentHeight() / 2.0f);
                ViewExpandKtKt.setScaleXY(capsuleItemView.getContentContainer(), fCoerceIn, fCoerceIn);
                capsuleItemView.getContentContainer().setTranslationX(0.0f);
            }
            ExpandableView expandableView = capsuleItemView.bindStackView;
            if (expandableView != null) {
                Float fValueOf = Float.valueOf(expandableView.getScaleX());
                if (fValueOf.floatValue() <= 0.0f) {
                    fValueOf = null;
                }
                float fWidth = rect.width() / (fValueOf != null ? fValueOf.floatValue() : 1.0f);
                int width = expandableView.getWidth() - capsuleItemView.getLocation().getContentWidth();
                float contentWidth2 = fWidth - capsuleItemView.getLocation().getContentWidth();
                float f3 = 1.0f - (contentWidth2 / width);
                if (f3 > 1.0f) {
                    f3 = 1.0f;
                }
                if (f3 < 0.0f) {
                    f3 = 0.0f;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    int iWidth = rect.width();
                    int iHeight = rect.height();
                    int contentWidth3 = capsuleItemView.getLocation().getContentWidth();
                    StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(contentWidth2, width, "setClipRect: a:", " c:", " p:");
                    ChildHelper$.ExternalSyntheticOutline0.m(f3, iWidth, ",cw:", ",ch:", sbM);
                    Log.d("SystemUi--Notification", NotificationPanelViewController$.ExternalSyntheticOutline0.m(iHeight, contentWidth3, ",l=", "CapsuleItemView-->", sbM));
                }
                double d = f3;
                if (d <= 0.003d) {
                    f = 0.0f;
                } else if (d < 0.997d) {
                    f = f3;
                }
                capsuleItemView.setCapsuleProgress(f, this.isShowCapsule, this.animateType, "setClipRect");
            }
        }

        public void setImmersiveProgress(float f) {
            ExtStateUnbindWatchdog extStateUnbindWatchdog = this.extStateUnbindWatchdog;
            if (f == extStateUnbindWatchdog.lastImmersiveProgress) {
                return;
            }
            extStateUnbindWatchdog.lastImmersiveProgress = f;
            extStateUnbindWatchdog.lastProgressChangeTime = SystemClock.uptimeMillis();
        }

        public final void setShowCapsule(boolean z) {
            this.isShowCapsule = z;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.FrameLayout, com.android.systemui.statusbar.notification.row.ExpandableView, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
        public void setTopRadius(float f) {
            ContainerAnimatorViewHelper animatorHelper;
            ?? r0 = CapsuleItemView.this;
            NotificationCapsuleContainer capsuleContainer = r0.getCapsuleContainer();
            int capsuleFinalTransY = (capsuleContainer == null || (animatorHelper = capsuleContainer.getAnimatorHelper()) == null) ? 0 : animatorHelper.getCapsuleFinalTransY();
            if (this.isShowCapsule && Math.abs(((int) r0.getTranslationY()) - capsuleFinalTransY) < 40) {
                f = CapsuleConfig.INSTANCE.getDefaultRadius();
            }
            r0.getRoundableState().setMaxRadius(f);
        }

        public void setTranslationY(float f) {
            CapsuleItemView.this.setFinalTranslationY(f);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final ILockScreenExtStateController getExtStateController() {
            return (ILockScreenExtStateController) CapsuleItemView.extStateController$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Interpolator getFastFadeOutAlphaInterpolator() {
            return (Interpolator) CapsuleItemView.fastFadeOutAlphaInterpolator$delegate.getValue();
        }

        private static /* synthetic */ void getINVALIDATE_CAPSULE_RENDER_BOOSTER$annotations() {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView$bindToStackView$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements Runnable {
        public /* synthetic */ boolean $isNotRemoved;
        public /* synthetic */ CapsuleItemView this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.unBindStackView("nssl_animation_finish", this.$isNotRemoved);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CapsuleItemView(Context context, AttributeSet attributeSet) {
        super(KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, context, true, 4), attributeSet);
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        AutoRoundRectPathProvider autoRoundRectPathProvider = new AutoRoundRectPathProvider(capsuleConfig.getDefaultRadius());
        this.pathProvider = autoRoundRectPathProvider;
        CapsuleItemView$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new CapsuleItemView$.ExternalSyntheticLambda2();
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.bindDataInterceptor$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda2);
        this.statusBarStateController$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleItemView$$ExternalSyntheticLambda0(2));
        this.lockscreenShadeTransitionController$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleItemView$$ExternalSyntheticLambda0(3));
        this.tempStackState = new ExpandableViewState();
        int i = 0;
        this.isAppearAnimating = new AtomicBoolean(false);
        this.location = new CapsuleItemLocation(this);
        this.scrollClipInfo = new CapsuleViewState.ScrollClipInfo(i, i, 15);
        this.animationFinishedRunables = new HashSet();
        ColorMatrix colorMatrix = new ColorMatrix();
        this.dimColorMatrix = colorMatrix;
        this.dimColorFilter = new ColorMatrixColorFilter(colorMatrix);
        this.progressCtrl = new CapsuleItemProgressController();
        CapsuleItemView$delayResetDataInterceptTask$1 capsuleItemView$delayResetDataInterceptTask$1 = new CapsuleItemView$delayResetDataInterceptTask$1(i);
        capsuleItemView$delayResetDataInterceptTask$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.delayResetDataInterceptTask = capsuleItemView$delayResetDataInterceptTask$1;
        View viewInflate = LayoutInflater.from(((FrameLayout) this).mContext).inflate(2131558541, (ViewGroup) this, true);
        this.flForegroundBlurView = (FrameLayout) viewInflate.findViewById(2131363145);
        this.contentContainer = (FrameLayout) viewInflate.findViewById(2131362472);
        CapsuleBackgroundView capsuleBackgroundView = (CapsuleBackgroundView) viewInflate.findViewById(2131362471);
        this.bgView = capsuleBackgroundView;
        capsuleBackgroundView.initHelper(autoRoundRectPathProvider);
        this.scrimView = (CapsuleScrimView) viewInflate.findViewById(2131362473);
        StokeViewControl stokeViewControl = new StokeViewControl(viewInflate);
        this.stokeViewControl = stokeViewControl;
        stokeViewControl.initPathProvider(autoRoundRectPathProvider);
        updateBaseRoundness$2();
        ((ExpandableView) this).mExpandableViewEx.setAnimationCornerRadius(capsuleConfig.getDefaultRadius());
        setAlpha(0.0f);
        setClipChildren(false);
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.CAPSULE_CARD);
    }

    private final float getActualRadius() {
        float fMin = Math.min(this.location.getClipRect().height() / 2.0f, getMaxRadius());
        float itemHeight = this.useSmoothRadius ? 0.0f : CapsuleConfig.INSTANCE.getItemHeight() / 2.0f;
        return fMin < itemHeight ? itemHeight : fMin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CapsuleDataChangeInterceptor getBindDataInterceptor() {
        return (CapsuleDataChangeInterceptor) this.bindDataInterceptor$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setDimEffect(float f) {
        KeyguardUtils.Companion.getClass();
        if (f <= 1.0E-6f) {
            setRenderEffect(null);
            return;
        }
        float fCoerceIn = 1.0f - RangesKt___RangesKt.coerceIn((CapsuleScrimAlphaHelper.INSTANCE.computeScrimAlphaBase(CapsuleScrimAlphaHelper.ScrimAlphaType.CAPSULE_ITEM_FORCE_BACKGROUND, f) * f) / 255.0f, 0.0f, 1.0f);
        this.dimColorMatrix.setScale(fCoerceIn, fCoerceIn, fCoerceIn, 1.0f);
        this.dimColorFilter.setColorMatrix(this.dimColorMatrix);
        setRenderEffect(RenderEffect.createColorFilterEffect(this.dimColorFilter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void setFinalTranslationY(float f) {
        OplusPhysicsPropertyAnimatorExtKt.setFinalValue(this, OplusPhysicsPropertyAnimator.Y_TRANSLATION, f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applyLaunchAnimationParams(LaunchAnimationParameters launchAnimationParameters) {
        int top;
        if (launchAnimationParameters == null) {
            setTranslationX(0.0f);
            setFinalActualHeight(getIntrinsicHeight());
            setFinalTranslationY(getViewState().getYTranslation());
            return;
        }
        if (!launchAnimationParameters.getVisible()) {
            if (getVisibility() == 0) {
                setVisibility(4);
                return;
            }
            return;
        }
        setExtraWidthForClipping(launchAnimationParameters.getWidth() - getWidth());
        if (launchAnimationParameters.getStartRoundedTopClipping() > 0) {
            float interpolation = ((PathInterpolator) Interpolators.FAST_OUT_SLOW_IN).getInterpolation(launchAnimationParameters.getProgress(100L));
            int startNotificationTop = launchAnimationParameters.getStartNotificationTop();
            top = ((Integer) Double.valueOf(Math.min(MathUtils.lerp(startNotificationTop, launchAnimationParameters.getTop(), interpolation), startNotificationTop))).intValue();
        } else {
            top = launchAnimationParameters.getTop();
        }
        setFinalActualHeight(launchAnimationParameters.getBottom() - top);
        setActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(launchAnimationParameters.getWidth());
        int notificationParentTop = top - launchAnimationParameters.getNotificationParentTop();
        int startClipTopAmount = launchAnimationParameters.getStartClipTopAmount();
        int iLerp = (int) MathUtils.lerp(startClipTopAmount, 0, launchAnimationParameters.getProgress());
        if (startClipTopAmount != 0) {
            setClipTopAmount(iLerp);
        }
        setFinalTranslationY(notificationParentTop);
        setTranslationX(launchAnimationParameters.getCenterX() - (((getWidth() / 2.0f) + getLocationOnScreen()[0]) - getTranslationX()));
        invalidateOutline();
    }

    public final void applyRoundnessAndInvalidate() {
        float actualRadius = getActualRadius();
        AutoRoundRectPathProvider.updateMetaData$default(this.pathProvider, Float.valueOf(actualRadius), null, null, 6);
        this.bgView.setStrokeRadius(actualRadius, this.useSmoothRadius);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("applyBackgroundRoundness " + actualRadius + OplusPreventModeData.SEPARATOR + this.pathProvider.useSmoothRadius()));
        }
        super/*com.android.systemui.statusbar.notification.Roundable*/.applyRoundnessAndInvalidate();
    }

    public final void attachCapsuleEntry(CapsuleItemData capsuleItemData, String str) {
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("attachCapsuleEntry: " + capsuleItemData.getKey() + ", reason = " + str));
            }
            this.entry = capsuleItemData;
            View contentView = capsuleItemData.getContentView();
            ViewParent parent = contentView.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(contentView);
            }
            this.contentContainer.removeAllViews();
            this.contentContainer.addView(contentView);
            if (capsuleItemData.getContentView() instanceof CapsuleNotificationContainerView) {
                ((CapsuleNotificationContainerView) capsuleItemData.getContentView()).bindItemView(this);
            }
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final void attachStackViewProxy(ExpandableView expandableView, boolean z) {
        OplusExpandableViewProxy proxy = expandableView.mExpandableViewEx.getProxy();
        CapsuleItemProxy capsuleItemProxy = proxy instanceof CapsuleItemProxy ? (CapsuleItemProxy) proxy : null;
        if (capsuleItemProxy != null) {
            capsuleItemProxy.disarmExtStateUnbindWatchdog("replace_proxy");
        }
        ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType animateType = ((ILockScreenExtStateController.ExtStateEvent) Companion.getExtStateController().getExtStateEvent().getValue()).getAnimateType();
        CapsuleConfig.INSTANCE.getClass();
        CapsuleItemProxy capsuleItemLightOSProxy = CapsuleConfig.isLightOs ? new CapsuleItemLightOSProxy(this, z, animateType) : new CapsuleItemProxy(this, z, animateType);
        expandableView.mExpandableViewEx.setProxy(capsuleItemLightOSProxy);
        capsuleItemLightOSProxy.armExtStateUnbindWatchdog();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void bindToStackView(ExpandableView expandableView, boolean z, boolean z2, boolean z3) {
        Scalar capsule_to_immer;
        NotificationCapsuleContainerControllerImpl containerControllerImpl;
        NotificationCapsuleContainerControllerImpl containerControllerImpl2;
        OplusExpandableViewProxy proxy;
        NotificationListContainer notificationListContainer;
        OplusExpandableViewProxy proxy2;
        String proxyName;
        NotificationCapsuleContainerControllerImpl containerControllerImpl3;
        NotificationCapsuleContainerControllerImpl containerControllerImpl4;
        OplusExpandableViewEx oplusExpandableViewEx;
        OplusExpandableViewProxy proxy3;
        String proxyName2;
        OplusExpandableViewEx oplusExpandableViewEx2;
        OplusExpandableViewProxy proxy4;
        ExtStateAnimParams.CapsuleProgressRadio capsuleProgressRadio = ExtStateAnimParams.CapsuleProgressRadio.INSTANCE;
        if (z && z2) {
            capsule_to_immer = capsuleProgressRadio.getIMMER_TO_CAPSULE();
        } else if (z && !z3) {
            capsule_to_immer = capsuleProgressRadio.getSTACK_TO_CAPSULE();
        } else if (z) {
            capsule_to_immer = capsuleProgressRadio.getSTACK_TO_CAPSULE_FOR_MAP();
        } else {
            capsule_to_immer = z2 ? capsuleProgressRadio.getCAPSULE_TO_IMMER() : capsuleProgressRadio.getCAPSULE_TO_STACK();
        }
        float fFloatValue = capsule_to_immer.get().floatValue();
        if (fFloatValue < Float.MIN_VALUE) {
            fFloatValue = Float.MIN_VALUE;
        }
        this.progressCtrl.setTransition(fFloatValue, z);
        if (Intrinsics.areEqual(this.bindStackView, expandableView) && (oplusExpandableViewEx = expandableView.mExpandableViewEx) != null && (proxy3 = oplusExpandableViewEx.getProxy()) != null && (proxyName2 = proxy3.getProxyName()) != null && StringsKt__StringsJVMKt.startsWith(proxyName2, NAME_PREFIX, false) && (oplusExpandableViewEx2 = expandableView.mExpandableViewEx) != null && (proxy4 = oplusExpandableViewEx2.getProxy()) != null && proxy4.isForCapsuleShowing() == z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " bindToStackView:: same view ,skip"));
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(this.bindStackView, expandableView) && (proxy2 = expandableView.mExpandableViewEx.getProxy()) != null && (proxyName = proxy2.getProxyName()) != null && StringsKt__StringsJVMKt.startsWith(proxyName, NAME_PREFIX, false)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " bindToStackView::isShowCapsule=" + z));
            }
            if (z) {
                setContentWidth(getViewState().getContentWidth());
                if (CapsuleViewExpandKtKt.isBaseCard(this)) {
                    setTranslationZ(4.0f);
                }
            }
            unBindOtherProxyView(z);
            NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
            if (capsuleContainer != null && (containerControllerImpl4 = capsuleContainer.getContainerControllerImpl()) != null) {
                containerControllerImpl4.changeBindDirection(z);
            }
            attachStackViewProxy(expandableView, z);
            NotificationCapsuleContainer capsuleContainer2 = getCapsuleContainer();
            if (capsuleContainer2 == null || (containerControllerImpl3 = capsuleContainer2.getContainerControllerImpl()) == null) {
                return;
            }
            containerControllerImpl3.resetAllTargetShowHeight();
            return;
        }
        if (z) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) Dependency.sDependency.getDependencyInner(NotificationStackScrollLayoutController.class);
            if (notificationStackScrollLayoutController != null && (notificationListContainer = notificationStackScrollLayoutController.getNotificationListContainer()) != null) {
                notificationListContainer.resetExposedMenuView(false, true);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->reset notification or seedling expose menu");
            }
        }
        OplusExpandableViewEx oplusExpandableViewEx3 = expandableView.mExpandableViewEx;
        Object proxyView = (oplusExpandableViewEx3 == null || (proxy = oplusExpandableViewEx3.getProxy()) == null) ? null : proxy.getProxyView();
        CapsuleItemView capsuleItemView = proxyView instanceof CapsuleItemView ? (CapsuleItemView) proxyView : null;
        if (capsuleItemView != null && capsuleItemView != this) {
            capsuleItemView.unBindStackView("bindStackViewToOtherCapsuleItemView", true);
        }
        if (this.bindStackView != null) {
            unBindStackView("bind stack view two times", true);
        }
        unBindOtherProxyView(z);
        this.bindStackView = expandableView;
        removeCallbacks(this.delayResetDataInterceptTask);
        getBindDataInterceptor().intercept();
        CapsuleConfig.INSTANCE.getClass();
        if (CapsuleConfig.isMiddleOrHighAnimLevel) {
            this.bgView.setLayerType(0, null);
        }
        RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, this, null, 3), 11, false, 6);
        NotificationCapsuleContainer capsuleContainer3 = getCapsuleContainer();
        if (capsuleContainer3 != null && (containerControllerImpl2 = capsuleContainer3.getContainerControllerImpl()) != null) {
            containerControllerImpl2.onLightOsAnimStart(z);
        }
        if (!CapsuleConfig.isLightOs) {
            if (z) {
                setFinalTranslationY(expandableView.getTranslationY());
                setFinalActualHeight(expandableView.getActualHeight());
                setContentWidth(getViewState().getContentWidth());
                setUseSmoothRadius("initViewStateForBindAnimation:" + z, true);
                setActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(expandableView.getWidth() - expandableView.mExpandableViewEx.getClipWidth());
                ((ExpandableView) this).mExpandableViewEx.setAnimationCornerRadius(expandableView.mExpandableViewEx.getAnimationCornerRadius());
                if (CapsuleViewExpandKtKt.isBaseCard(this)) {
                    setTranslationZ(4.0f);
                }
                expandableView.mExpandableViewEx.setCapsuleProgress(0.0f);
            } else {
                setUseSmoothRadius("initViewStateForBindAnimation:hide", false);
                this.tempStackState.copyFrom(expandableView.getViewState());
                this.tempStackState.setYTranslation(getTranslationY(), "CapsuleItemView.initViewStateForBindAnimation");
                int horizontalClipTotal = this.actualWidth - this.scrollClipInfo.getHorizontalClipTotal();
                if (horizontalClipTotal < 0) {
                    horizontalClipTotal = 0;
                }
                int actualHeight = getActualHeight() - this.scrollClipInfo.getVerticalClipTotal();
                if (actualHeight < 0) {
                    actualHeight = 0;
                }
                int width = expandableView.getWidth() - horizontalClipTotal;
                expandableView.mExpandableViewEx.setTargetClipHeight(this.tempStackState.height - actualHeight);
                expandableView.mExpandableViewEx.setTargetClipWidth(width);
                NotificationCapsuleContainer capsuleContainer4 = getCapsuleContainer();
                if (capsuleContainer4 != null && (containerControllerImpl = capsuleContainer4.getContainerControllerImpl()) != null) {
                    containerControllerImpl.resetAllTargetShowHeight();
                }
                this.tempStackState.mExpandableViewStateEx.setClipWidth(width);
                this.tempStackState.setAlpha(1.0f, "CapsuleItemView.initViewStateForBindAnimation");
                this.tempStackState.mExpandableViewStateEx.setBlurRadio(70.0f);
                this.tempStackState.mExpandableViewStateEx.setAnimationCornerRadius(((ExpandableView) this).mExpandableViewEx.getAnimationCornerRadius());
                this.tempStackState.mExpandableViewStateEx.setCapsuleProgress(1.0f);
                this.tempStackState.applyToView(expandableView);
                getViewState().cancelAnimations(this);
                setContentScale(1.0f);
            }
        }
        updateEarTransitionY();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " bindToStackView:: stackView = " + LockScreenNotificationKotlinExKt.getLogKey(expandableView) + ", isShowingAnimator = " + z));
        }
        boolean z4 = expandableView.mOnHeightChangedListener == null;
        boolean z5 = !z4;
        if (getVisibility() != 0 && (!z4 || !z)) {
            setVisibility(0);
        }
        attachStackViewProxy(expandableView, z);
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        anonymousClass5.$isNotRemoved = z5;
        VarHandle.storeStoreFence();
        this.animationFinishedRunables.add(anonymousClass5);
    }

    public final void cancelStrokeAnimation() {
        this.stokeViewControl.cancelStrokeAnimation();
    }

    public final void capsuleAlphaStep() {
        CapsuleEarController earController = getEarController();
        if (earController != null && ((getViewState().getContentWidth() == CapsuleConfig.INSTANCE.getItemWidthMultiCard() || earController.isUsedSelfTransitionY(getKey())) && earController.getEarVisible())) {
            if (earController.isUsedSelfTransitionY(getKey())) {
                earController.setNeedIntercept(false);
                earController.setEarVisible(false);
                earController.setNeedIntercept(true);
            }
            if (CapsuleViewExpandKtKt.isBaseCard(this)) {
                WeakReference<CapsuleItemView> baseCard = earController.getBaseCard();
                if (!Intrinsics.areEqual(baseCard != null ? baseCard.get() : null, this)) {
                    earController.setBaseCard(new WeakReference<>(this));
                }
            }
        }
        updateEarTransitionY();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void capsuleBlurStep(float f) {
        CapsuleEarController earController;
        ExpandableView expandableView;
        setUseSmoothRadius("setCapsuleProgress:more", false);
        ExpandableView expandableView2 = this.bindStackView;
        if (expandableView2 != null && expandableView2.getVisibility() == 0) {
            CapsuleConfig.INSTANCE.getClass();
            if (!CapsuleConfig.isLightOs && getStatusBarStateController().getState() == 1 && !getLockscreenShadeTransitionController().isAnimatingToExpand() && (expandableView = this.bindStackView) != null) {
                expandableView.setVisibility(4);
            }
        }
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) this).mExpandableViewEx;
        Companion.getClass();
        oplusExpandableViewEx.setBlurRadio(f >= 0.99f ? 0.0f : 70.0f * (1.0f - f));
        if (getVisibility() == 4 && !((ViewState) getViewState()).hidden && !((ViewState) getViewState()).gone) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " capsule view is hidden,need show capsule view."));
            }
            setVisibility(0);
        }
        if (CapsuleViewExpandKtKt.isBaseCard(this) && (earController = getEarController()) != null && getViewState().getContentWidth() == CapsuleConfig.INSTANCE.getItemWidthMultiCard()) {
            earController.setNeedIntercept(false);
            NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
            earController.setEarVisible(capsuleContainer != null ? !capsuleContainer.getAnimatorHelper().getAmbientState().isStackState : false);
            earController.setNeedIntercept(true);
            WeakReference<CapsuleItemView> baseCard = earController.getBaseCard();
            if (!Intrinsics.areEqual(baseCard != null ? baseCard.get() : null, this)) {
                earController.setBaseCard(new WeakReference<>(this));
            }
        }
        updateEarTransitionY();
    }

    public ExpandableViewState createExpandableViewState() {
        return new CapsuleViewState();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        super/*android.widget.FrameLayout*/.dispatchDraw(canvas);
        NotificationSpotLightDelegate.Companion.draw(this, canvas, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean drawChild(Canvas canvas, View view, long j) {
        canvas.save();
        if (Intrinsics.areEqual(view, this.flForegroundBlurView) || (Intrinsics.areEqual(view, this.scrimView) && this.scrimView.getVisibility() == 0)) {
            canvas.clipPath(this.pathProvider.getClipPath());
        }
        boolean zDrawChild = super/*android.widget.FrameLayout*/.drawChild(canvas, view, j);
        canvas.restore();
        return zDrawChild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("=== CapsuleItemViewDumpInfo(" + getKey() + ") ===");
        super.dump(printWriter, strArr);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("visibility: ", getVisibility(), printWriter);
        printWriter.println("bindStackView: " + this.bindStackView);
        printWriter.println("CapsuleItemViewLocation: " + this.location);
        printWriter.println("CapsuleItemViewState: " + getViewState());
        printWriter.println(ListPopupWindow$.ExternalSyntheticOutline0.m("ContentScaleX: ", this.contentContainer.getScaleX(), ", ContentScaleY:", this.contentContainer.getScaleY(), "}"));
        printWriter.println("isHScrolling: " + this.isHScrolling + "}");
        this.bgView.dump(printWriter, strArr);
        this.pathProvider.dump(printWriter, strArr);
        printWriter.println();
    }

    public final int getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        return this.actualWidth;
    }

    public final CapsuleBackgroundView getBgView() {
        return this.bgView;
    }

    public final ViewGroup getBlurView() {
        return this.flForegroundBlurView;
    }

    public /* bridge */ /* synthetic */ float getBottomCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getBottomRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundness();
    }

    public /* bridge */ /* synthetic */ Set getBottomRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundnessSources();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final NotificationCapsuleContainer getCapsuleContainer() {
        ViewParent parent = getParent();
        if (parent instanceof NotificationCapsuleContainer) {
            return (NotificationCapsuleContainer) parent;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.material.NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider
    public NotificationSpotLightDelegate.ClipSpec getClipSpec() {
        return this.pathProvider.getSpotClipSpec();
    }

    public final FrameLayout getContentContainer() {
        return this.contentContainer;
    }

    public final float getContentScale() {
        return this.location.getContentScale();
    }

    public final int getContentWidth() {
        return this.location.getContentWidth();
    }

    public final CapsuleEarController getEarController() {
        NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
        if (capsuleContainer != null) {
            return capsuleContainer.getCapsuleEarController();
        }
        return null;
    }

    public final CapsuleItemData getEntry() {
        CapsuleItemData capsuleItemData = this.entry;
        if (capsuleItemData != null) {
            return capsuleItemData;
        }
        return null;
    }

    public int getIntrinsicHeight() {
        return CapsuleConfig.INSTANCE.getItemHeight();
    }

    public final int getIntrinsicWidth() {
        return CapsuleConfig.INSTANCE.getItemWidth();
    }

    public final String getKey() {
        Object obj;
        try {
            obj = Result.constructor-impl(getEntry().getKey());
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = "";
        }
        return (String) obj;
    }

    public final CapsuleItemLocation getLocation() {
        return this.location;
    }

    public final OplusLockscreenShadeTransitionControllerEx getLockscreenShadeTransitionController() {
        return (OplusLockscreenShadeTransitionControllerEx) this.lockscreenShadeTransitionController$delegate.getValue();
    }

    public /* bridge */ /* synthetic */ float getMaxRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getMaxRadius();
    }

    public final AutoRoundRectPathProvider getPathProvider() {
        return this.pathProvider;
    }

    public final CapsuleViewState.ScrollClipInfo getScrollClipInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        return this.scrollClipInfo;
    }

    public final StatusBarStateController getStatusBarStateController() {
        return (StatusBarStateController) this.statusBarStateController$delegate.getValue();
    }

    public /* bridge */ /* synthetic */ float getTopCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getTopRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundness();
    }

    public /* bridge */ /* synthetic */ Set getTopRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundnessSources();
    }

    public /* bridge */ /* synthetic */ float[] getUpdatedRadii() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getUpdatedRadii();
    }

    public CapsuleViewState getViewState() {
        return (CapsuleViewState) super.getViewState();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Rect getViewStateClipRect() {
        int clipTop = getViewState().getScrollClipInfo().getClipTop();
        int clipBottom = ((ExpandableViewState) getViewState()).height - getViewState().getScrollClipInfo().getClipBottom();
        int width = getWidth();
        return new Rect(getViewState().getScrollClipInfo().getClipLeft() + ((width - getViewState().getWidth()) / 2), clipTop, ((getViewState().getWidth() + width) / 2) - getViewState().getScrollClipInfo().getClipRight(), clipBottom);
    }

    public final boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void invalidate() {
        this.bgView.invalidate();
        super/*android.widget.FrameLayout*/.invalidate();
    }

    public final void invalidateItemLocation() {
        Rect clipRect = this.location.getClipRect();
        getClipRect().set(clipRect);
        AutoRoundRectPathProvider.updateMetaData$default(this.pathProvider, null, null, new RectF(clipRect), 3);
        invalidate();
    }

    public final AtomicBoolean isAppearAnimating() {
        return this.isAppearAnimating;
    }

    public final boolean isBindToStackView() {
        return this.bindStackView != null;
    }

    public final boolean isExpandAnimationRunning() {
        return this._expandAnimationRunning;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isInTouchArea(float f, float f2) {
        if (getVisibility() == 0) {
            float width = (getWidth() - this.location.getActualWidth()) / 2.0f;
            float actualWidth = (this.location.getActualWidth() + getWidth()) / 2.0f;
            if (getTranslationX() >= 0.0f) {
                width += getTranslationX();
            }
            if (getTranslationX() < 0.0f) {
                actualWidth += getTranslationX();
            }
            float translationY = getTranslationY() + this.location.getClipTop();
            float translationY2 = getTranslationY() + this.location.getActualHeight();
            float px = UtilsKt.toPx(8, getContext());
            float f3 = actualWidth + px;
            float f4 = translationY - px;
            float f5 = translationY2 + px;
            if (f >= width - px && f <= f3 && f2 >= f4 && f2 <= f5) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends BaseViewBinder> T makeEx() {
        if (((ExpandableView) this).mExpandableViewEx == null) {
            CapsuleItemViewExImpl capsuleItemViewExImpl = new CapsuleItemViewExImpl(((FrameLayout) this).mContext);
            ((ExpandableView) this).mExpandableViewEx = capsuleItemViewExImpl;
            capsuleItemViewExImpl.bind(this);
        }
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) this).mExpandableViewEx;
        if (oplusExpandableViewEx != null) {
            return oplusExpandableViewEx;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.FrameLayout*/.onAttachedToWindow();
        getBindDataInterceptor().register();
        CapsuleBackgroundView capsuleBackgroundView = this.bgView;
        NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
        capsuleBackgroundView.initSharedBgManager(capsuleContainer != null ? capsuleContainer.getSharedBgManager() : null);
    }

    public final void onClearTransient() {
        this.bgView.onClearTransient();
        NotificationDetailColorChanger notificationDetailColorChanger = this.colorChanger;
        if (notificationDetailColorChanger != null) {
            notificationDetailColorChanger.changeCapsuleViewColor(getEntry().getContentView(), 0.0f);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->onClearTransient: changeCapsuleViewColor(0f) called");
            }
            this.colorChanger = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001e  */
    public final void onClipInfoChange(CapsuleViewState.ScrollClipInfo scrollClipInfo) {
        boolean z;
        ContainerAnimatorViewHelper animatorHelper;
        LockScreenAmbientState ambientState;
        this.location.loadScrollClipInfo(scrollClipInfo);
        NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
        if (capsuleContainer != null && (animatorHelper = capsuleContainer.getAnimatorHelper()) != null && (ambientState = animatorHelper.getAmbientState()) != null) {
            z = ambientState.isHorizontalBackFling;
        }
        float contentWidth = 0.0f;
        this.contentContainer.setTranslationX(z ? 0.0f : this.location.getClipLeft());
        float fWidth = this.location.getClipRect().width() / this.location.getContentWidth();
        float interpolation = CapsuleMetaBallUtils.isSupportMetaBall(this.bgView.getContext()) ? CapsuleMetaBallParams.SwipeParam.INSTANCE.getCapsuleContentAlphaInterpolation().getInterpolation(RangesKt___RangesKt.coerceIn(fWidth, 0.0f, 1.0f)) : fWidth;
        if (this.location.getContentScale() == 1.0f) {
            NotificationCapsuleContainer capsuleContainer2 = getCapsuleContainer();
            if ((capsuleContainer2 != null ? capsuleContainer2.getOwnScrollX() : 0) < 0 && z) {
                contentWidth = this.contentContainer.getWidth();
            }
        } else {
            contentWidth = this.location.getContentWidth() / 2.0f;
        }
        ViewExpandKtKt.setPivotXY(this.contentContainer, contentWidth, this.location.getContentHeight() / 2.0f);
        ViewExpandKtKt.setScaleXY(this.contentContainer, fWidth, fWidth);
        this.contentContainer.setAlpha(interpolation);
        AutoRoundRectPathProvider.updateMetaData$default(this.pathProvider, Float.valueOf(CapsuleConfig.INSTANCE.getDefaultRadius()), Boolean.FALSE, null, 4);
        invalidateItemLocation();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String key = getKey();
            float actualRadius = getActualRadius();
            StringBuilder sb = new StringBuilder("onClipInfoChange[");
            sb.append(key);
            sb.append("]: clipInfo:");
            sb.append(scrollClipInfo);
            sb.append(OplusPreventModeData.SEPARATOR);
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, actualRadius, "CapsuleItemView-->", "SystemUi--Notification");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super/*android.widget.FrameLayout*/.onDetachedFromWindow();
        removeCallbacks(this.delayResetDataInterceptTask);
        if (this.bindStackView != null) {
            unBindStackView("onDetachedFromWindow", false);
        }
        getBindDataInterceptor().unregister();
        getBindDataInterceptor().unIntercept();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            childAt.layout(i, i2, i3, childAt.getMeasuredHeight() + i2);
        }
        invalidateItemLocation();
        setPivotX(getWidth() / 2.0f);
    }

    public final void onNsslAnimationEnd() {
        if (!this.animationFinishedRunables.isEmpty() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " onNsslAnimationEnd: runnable size = " + this.animationFinishedRunables.size()));
        }
        Iterator<Runnable> it = this.animationFinishedRunables.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.animationFinishedRunables.clear();
    }

    public void performAddAnimation(long j, long j2, boolean z, boolean z2, Runnable runnable) {
    }

    public long performRemoveAnimation(long j, long j2, float f, boolean z, boolean z2, Runnable runnable, Runnable runnable2, AnimatorListenerAdapter animatorListenerAdapter, ExpandableView.ClipSide clipSide) {
        return 0L;
    }

    public /* bridge */ /* synthetic */ boolean requestBottomRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestBottomRoundness(f, sourceType);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType);
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType);
    }

    public /* bridge */ /* synthetic */ boolean requestTopRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestTopRoundness(f, sourceType);
    }

    public final void resetClipInfoForTouch() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->resetClipInfoForTouch");
        }
        NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
        if (capsuleContainer != null) {
            capsuleContainer.requestCancelTouchFeedback(false);
        }
        ViewExpandKtKt.setScaleXY(this.contentContainer, 1.0f, 1.0f);
        this.contentContainer.setAlpha(1.0f);
        this.contentContainer.setTranslationX(0.0f);
        this.bgView.resetMetaBallParams();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExpandableViewState resetViewState() {
        ContainerAnimatorViewHelper animatorHelper;
        if (this.isAppearAnimating.get()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("CapsuleResetViewState:" + this + ": ignore by isAppearAnimating"));
            }
            return getViewState();
        }
        NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
        if (capsuleContainer != null && (animatorHelper = capsuleContainer.getAnimatorHelper()) != null && animatorHelper.readyToAnimAddORemove(this)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("CapsuleResetViewState:" + this + ": ignore by readyToAnimAddORemove"));
            }
            return getViewState();
        }
        ViewParent parent = getParent();
        NotificationCapsuleContainer notificationCapsuleContainer = parent instanceof NotificationCapsuleContainer ? (NotificationCapsuleContainer) parent : null;
        if (notificationCapsuleContainer == null) {
            return super.resetViewState();
        }
        CapsuleItemLocation capsuleItemLocation = this.location;
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        capsuleItemLocation.setContentHeight(capsuleConfig.getItemHeight());
        ExpandableViewState expandableViewStateResetViewState = super.resetViewState();
        CapsuleViewState capsuleViewState = expandableViewStateResetViewState instanceof CapsuleViewState ? (CapsuleViewState) expandableViewStateResetViewState : null;
        if (capsuleViewState != null) {
            capsuleViewState.setWidth(CapsuleViewExpandKtKt.getCurrentCardShowWidth(notificationCapsuleContainer));
            ((ExpandableViewState) capsuleViewState).height = capsuleConfig.getItemHeight();
            capsuleViewState.setContentScale(1.0f);
            int i = 0;
            ((ExpandableViewState) capsuleViewState).clipTopAmount = 0;
            ((ExpandableViewState) capsuleViewState).clipBottomAmount = 0;
            ((ExpandableViewState) capsuleViewState).mExpandableViewStateEx.setClipWidth(0);
            ((ExpandableViewState) capsuleViewState).mExpandableViewStateEx.setAnimationCornerRadius(capsuleConfig.getItemHeight() / 2.0f);
            ((ExpandableViewState) capsuleViewState).mExpandableViewStateEx.setCapsuleProgress(1.0f);
            capsuleViewState.setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(i, i, 15));
            getViewState().setScaleX(1.0f);
            getViewState().setScaleY(1.0f);
        }
        return expandableViewStateResetViewState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setActualHeight(int i, boolean z) {
        if (getActualHeight() != i) {
            setClipTopAmount(0);
            setClipBottomAmount(0);
        }
        setClipToActualHeight(true);
        this.location.setActualHeight(i);
        this.flForegroundBlurView.setTranslationY((i - this.location.getContentHeight()) / 2.0f);
        setPivotY(i / 2.0f);
        super.setActualHeight(i, z);
    }

    public final void setActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(int i) {
        if (this.actualWidth != i) {
            this.actualWidth = i;
            this.location.setActualWidth(i);
            invalidateItemLocation();
        }
    }

    public final void setCapsuleProgress(float f, boolean z, ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType extStateEventAnimType, String str) {
        ContainerAnimatorViewHelper animatorHelper;
        LockScreenAmbientState ambientState;
        CapsuleItemView baseCard;
        ExpandableViewState viewState;
        ExpandableView expandableView;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " setCapsuleProgress:" + this + ": isShowCapsule=" + z + ", progress=" + f + ", reason = " + str));
        }
        if (((ExpandableView) this).mExpandableViewEx.getCapsuleProgress() != f) {
            ((ExpandableView) this).mExpandableViewEx.setCapsuleProgress(f);
        }
        CapsuleItemProgressController.ProgressState progressStateUpdateProgress = this.progressCtrl.updateProgress(f);
        if (!progressStateUpdateProgress.isStep1) {
            setAlpha(1.0f);
            float stepProgress = progressStateUpdateProgress.getStepProgress();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " setCapsuleProgress: newProgress2=" + stepProgress + ", stepProgress=" + progressStateUpdateProgress.getStepProgress()));
            }
            Companion.getClass();
            if (stepProgress >= 0.99f) {
                if (z) {
                    resetClipInfoForTouch();
                    NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
                    if (capsuleContainer != null && (animatorHelper = capsuleContainer.getAnimatorHelper()) != null && (ambientState = animatorHelper.getAmbientState()) != null && (baseCard = ambientState.getBaseCard()) != null) {
                        baseCard.resetClipInfoForTouch();
                    }
                }
                setUseSmoothRadius("checkAlmostFinished", false);
                getRoundableState().setMaxRadius(CapsuleConfig.INSTANCE.getDefaultRadius());
            }
            capsuleBlurStep(stepProgress);
            return;
        }
        float stepProgress2 = progressStateUpdateProgress.getStepProgress();
        extStateEventAnimType.getClass();
        if (extStateEventAnimType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.IMMERSIVE_TO_STACK) {
            stepProgress2 = RangesKt___RangesKt.coerceIn(1.0f - Companion.getFastFadeOutAlphaInterpolator().getInterpolation(RangesKt___RangesKt.coerceIn(1.0f - stepProgress2, 0.0f, 1.0f)), 0.0f, 1.0f);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " setCapsuleProgress: newProgress=" + f + ", stepProgress=" + progressStateUpdateProgress.getStepProgress() + ", capsuleAlpha=" + stepProgress2));
        }
        setAlpha(stepProgress2);
        setUseSmoothRadius("setCapsuleProgress:less", true);
        ExpandableView expandableView2 = this.bindStackView;
        if (expandableView2 != null && (viewState = expandableView2.getViewState()) != null) {
            if (!((ViewState) viewState).gone && !((ViewState) viewState).hidden && ((expandableView = this.bindStackView) == null || expandableView.getVisibility() != 8)) {
                ExpandableView expandableView3 = this.bindStackView;
                if (expandableView3 != null) {
                    expandableView3.setVisibility(0);
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String key = getKey();
                boolean z2 = ((ViewState) viewState).gone;
                boolean z3 = ((ViewState) viewState).hidden;
                ExpandableView expandableView4 = this.bindStackView;
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(key + " setCapsuleProgress: item has gone(" + z2 + ") or hidden(" + z3 + ") or view is gone(" + (expandableView4 != null ? Integer.valueOf(expandableView4.getVisibility()) : null) + ")"));
            }
        }
        capsuleAlphaStep();
        resetClipInfoForTouch();
        ((ExpandableView) this).mExpandableViewEx.setBlurRadio(70.0f);
    }

    public final void setColorChanger(NotificationDetailColorChanger notificationDetailColorChanger) {
        if (this.colorChanger == null) {
            this.colorChanger = notificationDetailColorChanger;
        }
    }

    public final void setContentScale(float f) {
        this.location.setContentScale(f);
    }

    public final void setContentWidth(int i) {
        if (this.actualWidth < i) {
            setActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(i);
        }
        this.location.setContentWidth(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setExpandAnimationRunning(boolean z) {
        this._expandAnimationRunning = z;
        if (z) {
            getViewState().cancelAnimations(this);
        } else {
            setVisibility(0);
            setExtraWidthForClipping(0.0f);
        }
        invalidateItemLocation();
    }

    public final void setHScrolling(boolean z) {
        this.isHScrolling = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setScrimAlpha(float f) {
        if (CapsuleBlurHelper.INSTANCE.userNormalDrawable(getContext())) {
            this.scrimView.setScrimAlpha(f);
        } else {
            setDimEffect(f);
        }
        this.bgView.setScrimAlpha(f);
    }

    public final void setScrollClipInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(CapsuleViewState.ScrollClipInfo scrollClipInfo) {
        if (Intrinsics.areEqual(this.scrollClipInfo, scrollClipInfo)) {
            return;
        }
        this.scrollClipInfo = scrollClipInfo;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("CapsuleItemViewClipInfo: " + scrollClipInfo));
        }
        onClipInfoChange(scrollClipInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTranslationX(float f) {
        super/*android.widget.FrameLayout*/.setTranslationX(f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("CapsuleItemViewSetTranslationX[" + this + "]: " + f));
        }
    }

    public final void setUseSmoothRadius(String str, boolean z) {
        if (this.useSmoothRadius != z) {
            this.useSmoothRadius = z;
            this.bgView.useSmoothRadius(getKey(), z);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(getKey() + " setUseSmoothRadius: " + z + " reason =" + str));
            }
            if (!z) {
                this.bgView.enablePathProvider(z);
                float f = z ? 0.0f : 1.0f;
                this.bgView.setStrokeAndShadowAlpha(f, f, null);
            }
            AutoRoundRectPathProvider.updateMetaData$default(this.pathProvider, null, Boolean.valueOf(z), null, 5);
            applyRoundnessAndInvalidate();
            this.bgView.invalidate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setVisibility(int i) {
        super/*android.widget.FrameLayout*/.setVisibility(i);
        Log.d(TAG, "setVisibilityA: " + i + OplusPreventModeData.SEPARATOR + (i == 0));
    }

    public final void startStrokeAnimation(float f) {
        this.stokeViewControl.startStrokeAnimation(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        if (this.entry != null) {
            return Bundler$.ExternalSyntheticOutline0.m("CapsuleItemView(", getKey(), ")");
        }
        String string = super/*android.widget.FrameLayout*/.toString();
        string.getClass();
        return string;
    }

    public final void unBindOtherProxyView(boolean z) {
        NotificationCapsuleContainer capsuleContainer;
        ContainerAnimatorViewHelper animatorHelper;
        ILockScreenExtStateController iLockScreenExtStateController;
        NotificationCapsuleContainer capsuleContainer2;
        if (!z || (capsuleContainer = getCapsuleContainer()) == null || (animatorHelper = capsuleContainer.getAnimatorHelper()) == null || (iLockScreenExtStateController = animatorHelper.lockScreenExtStateController) == null || !iLockScreenExtStateController.isCapsuleState() || (capsuleContainer2 = getCapsuleContainer()) == null) {
            return;
        }
        capsuleContainer2.unBindToStackView(this, "unBindOtherProxyView");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void unBindStackView(String str, boolean z) {
        NotificationCapsuleContainerControllerImpl containerControllerImpl;
        NotificationCapsuleContainerControllerImpl containerControllerImpl2;
        OplusExpandableViewEx oplusExpandableViewEx;
        OplusExpandableViewEx oplusExpandableViewEx2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String key = getKey();
            ExpandableView expandableView = this.bindStackView;
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat(key + " unBindStackView: " + (expandableView != null ? LockScreenNotificationKotlinExKt.getLogKey(expandableView) : null) + OplusPreventModeData.SEPARATOR + z + ",reason = " + str));
        }
        ExpandableView expandableView2 = this.bindStackView;
        OplusExpandableViewProxy proxy = (expandableView2 == null || (oplusExpandableViewEx2 = expandableView2.mExpandableViewEx) == null) ? null : oplusExpandableViewEx2.getProxy();
        CapsuleItemProxy capsuleItemProxy = proxy instanceof CapsuleItemProxy ? (CapsuleItemProxy) proxy : null;
        if (capsuleItemProxy != null) {
            capsuleItemProxy.disarmExtStateUnbindWatchdog(str);
        }
        ExpandableView expandableView3 = this.bindStackView;
        if (expandableView3 != null && (oplusExpandableViewEx = expandableView3.mExpandableViewEx) != null) {
            oplusExpandableViewEx.setProxy((OplusExpandableViewProxy) null);
        }
        NotificationCapsuleContainer capsuleContainer = getCapsuleContainer();
        if (capsuleContainer != null && (containerControllerImpl2 = capsuleContainer.getContainerControllerImpl()) != null) {
            containerControllerImpl2.resetAllTargetShowHeight();
        }
        this.bindStackView = null;
        this.animationFinishedRunables.clear();
        RenderBooster renderBooster = RenderBooster.Companion.renderBooster(this);
        if (renderBooster != null) {
            renderBooster.requestStop(0);
        }
        CapsuleConfig.INSTANCE.getClass();
        if (CapsuleConfig.isMiddleOrHighAnimLevel) {
            this.bgView.setLayerType(2, null);
        }
        NotificationCapsuleContainer capsuleContainer2 = getCapsuleContainer();
        if (capsuleContainer2 != null && (containerControllerImpl = capsuleContainer2.getContainerControllerImpl()) != null) {
            containerControllerImpl.onLightOsAnimEnd();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("setupUnInterceptTask:" + this));
        }
        removeCallbacks(this.delayResetDataInterceptTask);
        postDelayed(this.delayResetDataInterceptTask, 100L);
        setUseSmoothRadius("unbindStackView", false);
        updateBaseRoundness$2();
        resetClipInfoForTouch();
        if (CapsuleViewExpandKtKt.isBaseCard(this)) {
            CapsuleEarController earController = getEarController();
            if (earController != null) {
                earController.setNeedIntercept(false);
            }
            CapsuleEarController earController2 = getEarController();
            if (earController2 != null) {
                earController2.setBaseCard(null);
            }
        }
        if (z) {
            getViewState().applyToView(this);
        }
        CapsuleEarController earController3 = getEarController();
        if (earController3 != null) {
            earController3.clearTransitionY(getKey());
        }
    }

    public final void updateBaseRoundness$2() {
        getRoundableState().setMaxRadius(CapsuleConfig.INSTANCE.getDefaultRadius());
        boolean zRequestRoundness = requestRoundness(1.0f, 1.0f, BASE_VALUE, false);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemView-->".concat("updateBaseRoundness changed: " + zRequestRoundness + ", actualRadius:" + getActualRadius()));
        }
    }

    public final void updateClipping$1() {
        this.location.loadViewStateClipInfo(getViewState());
        invalidateItemLocation();
    }

    public final void updateContentScale() {
        onClipInfoChange(this.scrollClipInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateEarTransitionY() {
        CapsuleEarController earController = getEarController();
        if (earController != null) {
            earController.setNeedIntercept(true);
        }
        CapsuleEarController earController2 = getEarController();
        if (earController2 != null) {
            earController2.requestTransitionY(getKey(), getTranslationY() + ((getActualHeight() - CapsuleConfig.INSTANCE.getItemHeight()) / 2));
        }
    }

    public final void updateHeight(float f) {
        if (f > this.location.getContentHeight()) {
            ((ExpandableViewState) getViewState()).height = (int) f;
            ((ExpandableViewState) getViewState()).clipTopAmount = 0;
            ((ExpandableViewState) getViewState()).clipBottomAmount = 0;
            return;
        }
        ((ExpandableViewState) getViewState()).height = this.location.getContentHeight();
        int i = (int) ((((ExpandableViewState) getViewState()).height - f) / 2.0f);
        int i2 = (((ExpandableViewState) getViewState()).height - ((int) f)) - i;
        ((ExpandableViewState) getViewState()).clipTopAmount = i;
        ((ExpandableViewState) getViewState()).clipBottomAmount = i2;
    }

    public /* bridge */ /* synthetic */ void updateRadii(float f, float f2, float[] fArr) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.updateRadii(f, f2, fArr);
    }

    public final void updateStrokeViewSize(int i, int i2) {
        this.stokeViewControl.updateStrokeViewSize(i, i2);
    }

    public final void updateWidth(float f) {
        getViewState().setWidth((int) f);
        ((ExpandableViewState) getViewState()).mExpandableViewStateEx.setClipWidth(0);
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType, boolean z) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestBottomRoundness(float f, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestBottomRoundness(f, sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestTopRoundness(float f, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestTopRoundness(f, sourceType, z);
    }

    public CapsuleItemView(Context context) {
        this(context, null);
    }
}
