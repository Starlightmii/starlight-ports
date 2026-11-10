package com.oplus.systemui.qs.slider;

import android.R;
import android.os.Looper;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.ui.spatial.RectList$;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.android.systemui.plugins.qs.NullableObservableField;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.brightness.ToggleSlider;
import com.android.systemui.qs.deprecated.brightness.BrightnessController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.settings.brightness.ui.BrightnessWarningToast;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.coui.appcompat.seekbar.DeformedValueBean;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.qs.base.seek.ClipBrightnessView;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar;
import com.oplus.systemui.qs.base.util.Gefingerpoken;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundKt;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.qs.observer.VisualComfortSwitchObserver;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.receiver.ThemeReceiver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQsBrightnessSliderController extends ViewController implements IBrightnessSliderController {
    private static final String BRIGHTNESS_NEW_ANIMATION = "ro.display.brightness.new.animation";
    public static final Companion Companion = new Companion();
    private final ActivityStarter activityStarter;
    private final String adapterTag;
    public float bottomDeformed;
    private final ClipBrightnessView brightnessIcon;
    private final OplusQsBrightnessSliderController$deformedListener$1 deformedListener;
    private DeformedValueBean deformedValueBean;
    public int distanceFromBottom;
    private final FalsingManager falsingManager;
    public boolean firstInit;
    private CharSequence iconContentDescription;
    private final Gefingerpoken interceptListener;
    private final NonNullObservableField<Boolean> isAutoModeDisabled;
    public boolean isListening;
    private final NonNullObservableField<Boolean> isRestricted;
    private final NonNullObservableField<Boolean> isSlidersDisabled;
    public float lastDuration;
    public long lastStartTime;
    public int lastStartValue;
    public int latestUpdateBrightness;
    private final View.OnLayoutChangeListener layoutChangedListener;
    public boolean mCheckChangeFromInner;
    private final NullableObservableField<RestrictedLockUtils.EnforcedAdmin> mEnforcedAdmin;
    public boolean mIsMirror;
    public final boolean mIsScreenBrightnessAnimationSupported;
    public int mLastProgress;
    private ToggleSlider.Listener mListener;
    private ToggleSlider mMirror;
    private MirrorController mMirrorController;
    private String mStartBrightnessValue;
    private final OplusQsBrightnessSliderController$mSuperPowerObserver$1 mSuperPowerObserver;
    public boolean mTracking;
    public boolean needResetLastAnimation;
    private final QSLogger qsLogger;
    private CopyOnWriteArrayList<COUIVerticalSeekBar.OnSeekBarChangeListener> seekBarListener;
    private final COUIVerticalSeekBar.OnSeekBarChangeListener seekListener;
    private final OplusSeparateNotificationAndQSState separateStateManager;
    private CharSequence sliderContentDescription;
    private final View.OnTouchListener sliderTouchListener;
    public final String tag;
    private final IReceiverListener themeChangeListener;
    private final View.OnClickListener toggleClickListener;
    public float topDeformed;
    private final SysUIViewDialogInteractor viewDialogInteractor;
    private final NonNullObservableField<Unit> viewEnableUpdate;
    private final BrightnessWarningToast warningToast;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController$setMirror$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQsBrightnessSliderController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    ToggleSlider toggleSlider = this.this$0.mMirror;
                    if (toggleSlider != null) {
                        toggleSlider.setMax(((OplusQsBaseToggleSliderLayout) ((ViewController) this.this$0).mView).getMax());
                    }
                    ToggleSlider toggleSlider2 = this.this$0.mMirror;
                    if (toggleSlider2 != null) {
                        toggleSlider2.setValue(((OplusQsBaseToggleSliderLayout) ((ViewController) this.this$0).mView).getProgress(), 0.0f, 0, 0L);
                    }
                    break;
                case 1:
                    OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
                    ((OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView).updateColor(QsViewBackgroundKt.isMixColorSupport(oplusQsBrightnessSliderController.getContext(), GaussBlurUtils.isQSGaussBlurDisabled()), false, null);
                    break;
                default:
                    this.this$0.updateViewEnable();
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$Hr3tX3l-JhJWEDZdRvBdKNeuknA, reason: not valid java name */
    public static boolean m307$r8$lambda$Hr3tX3lJhJWEDZdRvBdKNeuknA(OplusQsBrightnessSliderController oplusQsBrightnessSliderController) {
        return oplusQsBrightnessSliderController.mEnforcedAdmin.get() != null;
    }

    /* JADX INFO: renamed from: $r8$lambda$LXP1-dZuFUGn8vQQu98mWNiYUcE, reason: not valid java name */
    public static Unit m308$r8$lambda$LXP1dZuFUGn8vQQu98mWNiYUcE(OplusQsBrightnessSliderController oplusQsBrightnessSliderController, float f, float f2, float f3) {
        if (oplusQsBrightnessSliderController.isNeedtouchFeekback()) {
            OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView;
            oplusQsBaseToggleSliderLayout.setTranslationY(f);
            oplusQsBaseToggleSliderLayout.setScaleX(f2);
            oplusQsBaseToggleSliderLayout.setScaleY(f3);
            BrightnessMirrorController brightnessMirrorController = oplusQsBrightnessSliderController.mMirrorController;
            if (brightnessMirrorController != null) {
                brightnessMirrorController.mToggleSliderController.syncTouchAnimationFromOriginSlider(f, f2, f3);
            }
        }
        return Unit.INSTANCE;
    }

    public OplusQsBrightnessSliderController(OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout, FalsingManager falsingManager, ActivityStarter activityStarter, BrightnessWarningToast brightnessWarningToast, QSLogger qSLogger, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, SysUIViewDialogInteractor sysUIViewDialogInteractor) {
        super(oplusQsBaseToggleSliderLayout);
        this.falsingManager = falsingManager;
        this.activityStarter = activityStarter;
        this.warningToast = brightnessWarningToast;
        this.qsLogger = qSLogger;
        this.separateStateManager = oplusSeparateNotificationAndQSState;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        int i = 0;
        int i2 = 1;
        this.mIsScreenBrightnessAnimationSupported = SystemProperties.getInt(BRIGHTNESS_NEW_ANIMATION, 0) == 1;
        this.firstInit = true;
        this.latestUpdateBrightness = -1;
        this.adapterTag = hashCode() + "_brightness";
        this.seekBarListener = new CopyOnWriteArrayList<>();
        View toggleView = oplusQsBaseToggleSliderLayout.getToggleView();
        this.brightnessIcon = toggleView instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView : null;
        this.mStartBrightnessValue = "";
        NullableObservableField<RestrictedLockUtils.EnforcedAdmin> nullableObservableField = new NullableObservableField<>((Object) null, (ObservableField[]) null, false, (Function1) null, 15, (DefaultConstructorMarker) null);
        this.mEnforcedAdmin = nullableObservableField;
        OplusQsBrightnessSliderController$$ExternalSyntheticLambda0 oplusQsBrightnessSliderController$$ExternalSyntheticLambda0 = new OplusQsBrightnessSliderController$$ExternalSyntheticLambda0(i);
        oplusQsBrightnessSliderController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        NonNullObservableField<Boolean> nonNullObservableField = new NonNullObservableField<>(new ObservableField[]{nullableObservableField}, false, oplusQsBrightnessSliderController$$ExternalSyntheticLambda0, 2, (DefaultConstructorMarker) null);
        this.isRestricted = nonNullObservableField;
        Boolean bool = Boolean.FALSE;
        NonNullObservableField<Boolean> nonNullObservableField2 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isSlidersDisabled = nonNullObservableField2;
        NonNullObservableField<Boolean> nonNullObservableField3 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isAutoModeDisabled = nonNullObservableField3;
        OplusQsBrightnessSliderController$$ExternalSyntheticLambda0 oplusQsBrightnessSliderController$$ExternalSyntheticLambda1 = new OplusQsBrightnessSliderController$$ExternalSyntheticLambda0(i2);
        oplusQsBrightnessSliderController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.viewEnableUpdate = new NonNullObservableField<>(new ObservableField[]{nonNullObservableField, nonNullObservableField2, nonNullObservableField3}, false, oplusQsBrightnessSliderController$$ExternalSyntheticLambda1);
        this.tag = getClass().getSimpleName();
        OplusQsBrightnessSliderController$interceptListener$1 oplusQsBrightnessSliderController$interceptListener$1 = new OplusQsBrightnessSliderController$interceptListener$1();
        oplusQsBrightnessSliderController$interceptListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.interceptListener = oplusQsBrightnessSliderController$interceptListener$1;
        OplusQsBrightnessSliderController$seekListener$1 oplusQsBrightnessSliderController$seekListener$1 = new OplusQsBrightnessSliderController$seekListener$1();
        oplusQsBrightnessSliderController$seekListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.seekListener = oplusQsBrightnessSliderController$seekListener$1;
        OplusQsBrightnessSliderController$themeChangeListener$1 oplusQsBrightnessSliderController$themeChangeListener$1 = new OplusQsBrightnessSliderController$themeChangeListener$1();
        oplusQsBrightnessSliderController$themeChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.themeChangeListener = oplusQsBrightnessSliderController$themeChangeListener$1;
        OplusQsBrightnessSliderController$toggleClickListener$1 oplusQsBrightnessSliderController$toggleClickListener$1 = new OplusQsBrightnessSliderController$toggleClickListener$1();
        oplusQsBrightnessSliderController$toggleClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.toggleClickListener = oplusQsBrightnessSliderController$toggleClickListener$1;
        OplusQsBrightnessSliderController$deformedListener$1 oplusQsBrightnessSliderController$deformedListener$1 = new OplusQsBrightnessSliderController$deformedListener$1();
        oplusQsBrightnessSliderController$deformedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.deformedListener = oplusQsBrightnessSliderController$deformedListener$1;
        OplusQsBrightnessSliderController$layoutChangedListener$1 oplusQsBrightnessSliderController$layoutChangedListener$1 = new OplusQsBrightnessSliderController$layoutChangedListener$1();
        oplusQsBrightnessSliderController$layoutChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutChangedListener = oplusQsBrightnessSliderController$layoutChangedListener$1;
        OplusQsBrightnessSliderController$sliderTouchListener$1 oplusQsBrightnessSliderController$sliderTouchListener$1 = new OplusQsBrightnessSliderController$sliderTouchListener$1();
        oplusQsBrightnessSliderController$sliderTouchListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sliderTouchListener = oplusQsBrightnessSliderController$sliderTouchListener$1;
        OplusQsBrightnessSliderController$mSuperPowerObserver$1 oplusQsBrightnessSliderController$mSuperPowerObserver$1 = new OplusQsBrightnessSliderController$mSuperPowerObserver$1();
        oplusQsBrightnessSliderController$mSuperPowerObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSuperPowerObserver = oplusQsBrightnessSliderController$mSuperPowerObserver$1;
    }

    public static final boolean access$enforceAdminBlocked(OplusQsBrightnessSliderController oplusQsBrightnessSliderController) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = (RestrictedLockUtils.EnforcedAdmin) oplusQsBrightnessSliderController.mEnforcedAdmin.get();
        if (enforcedAdmin == null) {
            return false;
        }
        oplusQsBrightnessSliderController.getContext();
        oplusQsBrightnessSliderController.activityStarter.postStartActivityDismissingKeyguard(RestrictedLockUtils.getShowAdminSupportDetailsIntent$1(enforcedAdmin), 0);
        return true;
    }

    private final void setAutoBrightnessInner(boolean z) {
        ClipBrightnessView clipBrightnessView = this.brightnessIcon;
        if (clipBrightnessView != null) {
            clipBrightnessView.setAutoMode(z);
        }
        ClipBrightnessView clipBrightnessView2 = this.brightnessIcon;
        if (clipBrightnessView2 != null) {
            clipBrightnessView2.updateIconConfig();
        }
    }

    private final void setCustomProgressAnimDuration(float f) {
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setCustomProgressAnimDuration(f);
    }

    private final void setDefaultAnimation(int i) {
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setProgress(i, 0, false);
        int distanceFromBottom = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getDistanceFromBottom(i, -1);
        this.distanceFromBottom = distanceFromBottom;
        setBrightnessIconAnimator(i, distanceFromBottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLatestBrightnessRequest(int i) {
        if (this.mIsMirror) {
            return;
        }
        this.latestUpdateBrightness = i;
    }

    private final void setMirror(ToggleSlider toggleSlider) {
        this.mMirror = toggleSlider;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) this).mView;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        oplusQsBaseToggleSliderLayout.post(anonymousClass1);
    }

    public void addOnSeekBarChangeListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        if (this.seekBarListener.contains(onSeekBarChangeListener)) {
            return;
        }
        this.seekBarListener.add(onSeekBarChangeListener);
    }

    public final ClipBrightnessView getBrightnessIcon() {
        return this.brightnessIcon;
    }

    public final View getExpandableView() {
        return ((ViewController) this).mView;
    }

    public final boolean getMIsMirror() {
        return this.mIsMirror;
    }

    public final int getMLastProgress() {
        return this.mLastProgress;
    }

    public final boolean getMTracking() {
        return this.mTracking;
    }

    public int getMax() {
        return ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getMax();
    }

    public final boolean getNeedResetLastAnimation() {
        return this.needResetLastAnimation;
    }

    public View getRootView() {
        return (View) ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getParent();
    }

    public int getValue() {
        return ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getProgress();
    }

    public View getView() {
        return ((ViewController) this).mView;
    }

    public boolean isNeedtouchFeekback() {
        return this.isListening;
    }

    public boolean mirrorTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onViewAttached() {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "onViewAttached mIsMirror=" + this.mIsMirror + " mView=" + ((ViewController) this).mView, null, 8);
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).addOnSeekBarChangeListener(this.seekListener);
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setOnInterceptListener(this.interceptListener);
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).addOnLayoutChangeListener(this.layoutChangedListener);
        if (!this.mIsMirror) {
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setOnToggleClickListener(this.toggleClickListener);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setDeformedListener(this.deformedListener);
        }
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setSliderTouchListener(this.sliderTouchListener);
        ThemeReceiver.Companion.getInstance().addListener(this.themeChangeListener);
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setSliderContentDescription(getContext().getResources().getString(2131951736));
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).updateColor(QsViewBackgroundKt.isMixColorSupport(getContext(), GaussBlurUtils.isQSGaussBlurDisabled()), false, null);
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this.mSuperPowerObserver);
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVisualComfort()) {
            VisualComfortSwitchObserver.Companion.getInstance().registerForUserSwitch(getContext(), -1, false);
        }
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) this).mView;
        OplusQsBrightnessSliderController$$ExternalSyntheticLambda2 oplusQsBrightnessSliderController$$ExternalSyntheticLambda2 = new OplusQsBrightnessSliderController$$ExternalSyntheticLambda2();
        oplusQsBrightnessSliderController$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        oplusQsBaseToggleSliderLayout.setOnTouchAnimationListener(oplusQsBrightnessSliderController$$ExternalSyntheticLambda2);
        this.viewEnableUpdate.attach();
    }

    public void onViewDetached() {
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).removeOnSeekBarChangeListener(this.seekListener);
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setOnInterceptListener(null);
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).removeOnLayoutChangeListener(this.layoutChangedListener);
        if (!this.mIsMirror) {
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setOnToggleClickListener(null);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setDeformedListener(null);
        }
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setSliderTouchListener(null);
        ThemeReceiver.Companion.getInstance().removeListener(this.themeChangeListener);
        SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(this.mSuperPowerObserver);
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVisualComfort()) {
            VisualComfortSwitchObserver.Companion.getInstance().unregister(getContext());
        }
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setOnTouchAnimationListener(null);
        this.viewEnableUpdate.detach();
    }

    public final void refreshEnableState() {
        boolean z;
        NonNullObservableField<Boolean> nonNullObservableField = this.isSlidersDisabled;
        QSFeatureOption.INSTANCE.getClass();
        boolean z2 = false;
        try {
            try {
                z = CustomizeFeatureOption.sIsAutoBrightnessDisabled;
            } catch (Exception e) {
                LogUtil.e(LogUtil.TAG_KEYGUARD, "FeatureOption", "isBrightnessSlidersDisabled error", e);
            }
        } catch (Exception e2) {
            LogUtil.e(LogUtil.TAG_KEYGUARD, "FeatureOption", "isAutoBrightnessModeDisabled error", e2);
            z = false;
        }
        boolean z3 = z ? CustomizeFeatureOption.sIsBrightnessSlidersDisabled : false;
        nonNullObservableField.set(Boolean.valueOf(z3));
        NonNullObservableField<Boolean> nonNullObservableField2 = this.isAutoModeDisabled;
        QSFeatureOption.INSTANCE.getClass();
        try {
            z2 = CustomizeFeatureOption.sIsAutoBrightnessDisabled;
        } catch (Exception e3) {
            LogUtil.e(LogUtil.TAG_KEYGUARD, "FeatureOption", "isAutoBrightnessModeDisabled error", e3);
        }
        nonNullObservableField2.set(Boolean.valueOf(z2));
    }

    public void removeOnSeekBarChangeListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.seekBarListener.remove(onSeekBarChangeListener);
    }

    public void setAutoBrightness(boolean z) {
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) this).mView;
        int value = getValue();
        OplusQsBaseToggleSliderLayout.Companion companion = OplusQsBaseToggleSliderLayout.Companion;
        int distanceFromBottom = oplusQsBaseToggleSliderLayout.getDistanceFromBottom(value, -1);
        this.distanceFromBottom = distanceFromBottom;
        ClipBrightnessView clipBrightnessView = this.brightnessIcon;
        if (clipBrightnessView != null) {
            clipBrightnessView.updateIconColorWidthMarginBottom(distanceFromBottom);
        }
        this.mCheckChangeFromInner = true;
        setAutoBrightnessInner(z);
        this.mCheckChangeFromInner = false;
        IBrightnessSliderController iBrightnessSliderController = this.mMirror;
        IBrightnessSliderController iBrightnessSliderController2 = iBrightnessSliderController instanceof IBrightnessSliderController ? iBrightnessSliderController : null;
        if (iBrightnessSliderController2 != null) {
            iBrightnessSliderController2.setAutoBrightness(z);
        }
    }

    public final void setBrightnessIconAnimator(int i, int i2) {
        float max;
        int max2;
        if (((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getLayoutDirection() == 1) {
            max = getMax() - i;
            max2 = getMax();
        } else {
            max = i;
            max2 = getMax();
        }
        updateIconRotation((max / max2) * 360.0f, i2);
    }

    public void setDeformedParams(DeformedValueBean deformedValueBean) {
        OplusQsVerticalSeekBar slider;
        if (deformedValueBean == null || (slider = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getSlider()) == null) {
            return;
        }
        slider.setDeformedParams(deformedValueBean);
    }

    public final void setDeformedTranslation(float f, float f2) {
        float f3 = Math.abs(f) > Math.abs(f2) ? f - f2 : f2 - f;
        QSLogger qSLogger = this.qsLogger;
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("setDeformedTranslation topDeformed: ", this.topDeformed, " bottomDeformed: ", this.bottomDeformed, " value: ");
        sbM.append(f3);
        qSLogger.d(this.tag, sbM.toString());
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setDeformedTranslation(f3);
    }

    public void setEnforcedAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mEnforcedAdmin.set(enforcedAdmin);
        refreshEnableState();
    }

    public void setIsEditable(boolean z) {
        CharSequence contentDescription;
        CharSequence contentDescription2;
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setIsEditable(z);
        ClipBrightnessView clipBrightnessView = this.brightnessIcon;
        if (clipBrightnessView != null && (contentDescription2 = clipBrightnessView.getContentDescription()) != null) {
            this.iconContentDescription = contentDescription2;
        }
        ClipBrightnessView clipBrightnessView2 = this.brightnessIcon;
        if (clipBrightnessView2 != null) {
            ViewExtKt.setTalkBackEnabled(clipBrightnessView2, !z, this.iconContentDescription);
        }
        IQSSpotLightConfig slider = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getSlider();
        if (slider != null && (contentDescription = slider.getContentDescription()) != null) {
            this.sliderContentDescription = contentDescription;
        }
        IQSSpotLightConfig slider2 = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getSlider();
        if (slider2 != null) {
            ViewExtKt.setTalkBackEnabled(slider2, !z, this.sliderContentDescription);
        }
    }

    public void setIsMirror(boolean z) {
        this.mIsMirror = z;
    }

    public void setListening(boolean z) {
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout;
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "setListening listening:" + z + " mView: " + ((ViewController) this).mView + " value:" + getValue(), null, 8);
        this.isListening = z;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout2 = (OplusQsBaseToggleSliderLayout) ((ViewController) this).mView;
        if (oplusQsBaseToggleSliderLayout2 != null) {
            oplusQsBaseToggleSliderLayout2.setDisallowInterceptTouchEvent(z);
        }
        this.latestUpdateBrightness = -1;
        if (this.isListening || (oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) this).mView) == null) {
            return;
        }
        oplusQsBaseToggleSliderLayout.setTranslationY(0.0f);
        oplusQsBaseToggleSliderLayout.setScaleX(1.0f);
        oplusQsBaseToggleSliderLayout.setScaleY(1.0f);
    }

    public final void setLongClickListener(View.OnLongClickListener onLongClickListener) {
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setLongClickListener(onLongClickListener);
    }

    public final void setMIsMirror(boolean z) {
        this.mIsMirror = z;
    }

    public final void setMLastProgress(int i) {
        this.mLastProgress = i;
    }

    public final void setMTracking(boolean z) {
        this.mTracking = z;
    }

    public void setMax(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "setMax: max= "), null, 8);
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setMax(i);
        ToggleSlider toggleSlider = this.mMirror;
        if (toggleSlider != null) {
            toggleSlider.setMax(i);
        }
    }

    public void setMirrorControllerAndMirror(MirrorController mirrorController) {
        ToggleSlider toggleSlider;
        this.mMirrorController = mirrorController;
        if (mirrorController == null || (toggleSlider = mirrorController.getToggleSlider()) == null) {
            return;
        }
        setMirror(toggleSlider);
    }

    public final void setNeedResetLastAnimation(boolean z) {
        this.needResetLastAnimation = z;
    }

    public void setOnChangedListener(ToggleSlider.Listener listener) {
        BrightnessController brightnessController;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
            sbM.append("setOnChangedListener mListener:" + this.mListener + " l:" + listener + " mTracking:" + this.mTracking);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (listener == null && (brightnessController = this.mListener) != null && this.mTracking && brightnessController != null) {
            brightnessController.onChanged(getValue(), false, true);
        }
        this.mListener = listener;
    }

    public final void setOnLayoutClickListener(View.OnClickListener onClickListener) {
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setOnLayoutClickListener(onClickListener);
    }

    /* JADX WARN: Code duplicated, block: B:54:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:56:? A[RETURN, SYNTHETIC] */
    public final void setValue(int i, float f, int i2, long j) {
        String str;
        String str2;
        int progress;
        ToggleSlider toggleSlider;
        QSLogger qSLogger = this.qsLogger;
        boolean z = this.isListening;
        View view = ((ViewController) this).mView;
        boolean z2 = this.mIsMirror;
        boolean z3 = this.mTracking;
        int i3 = this.latestUpdateBrightness;
        StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(f, i, "setValue: ", " screenBrightnessDuration: ", " isListening: ");
        sbM.append(z);
        sbM.append(" mView: ");
        sbM.append(view);
        sbM.append(" mIsMirror:");
        KeyguardLogger$.ExternalSyntheticOutline0.m(" mTracking:", ", latestUpdateBrightness:", sbM, z2, z3);
        sbM.append(i3);
        qSLogger.d(sbM.toString());
        if (this.mTracking) {
            return;
        }
        if (i == this.latestUpdateBrightness) {
            this.latestUpdateBrightness = -1;
        }
        int i4 = this.latestUpdateBrightness;
        String str3 = this.tag;
        if (i4 != -1) {
            LogUtil.i$default(LogUtil.TAG_QS, str3, TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i4, "setValue: ", " != "), null, 8);
            return;
        }
        if (this.mIsMirror || !this.mIsScreenBrightnessAnimationSupported) {
            str = str3;
            str2 = LogUtil.TAG_QS;
        } else {
            boolean z4 = this.isListening;
            float f2 = this.lastDuration;
            long j2 = this.lastStartTime;
            int max = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getMax();
            int progress2 = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getProgress();
            boolean z5 = this.needResetLastAnimation;
            StringBuilder sbM2 = KeyguardLogger$.ExternalSyntheticOutline0.m("setProgressAnimation value: ", i, " isListening: ", "  screenBrightnessDuration: ", z4);
            ChildHelper$.ExternalSyntheticOutline0.m(f, i2, "  startValue: ", " startTime: ", sbM2);
            sbM2.append(j);
            sbM2.append(" lastDuration: ");
            sbM2.append(f2);
            DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM2, " lastStartTime: ", j2, "  max: ");
            RectList$.ExternalSyntheticOutline0.m(max, progress2, " sliderProgress: ", " needResetLastAnimation: ", sbM2);
            sbM2.append(z5);
            String string = sbM2.toString();
            str = str3;
            str2 = LogUtil.TAG_QS;
            LogUtil.i$default(str2, str, string, null, 8);
        }
        if (f > 0.0f) {
            this.lastDuration = f;
            this.lastStartTime = j;
            this.lastStartValue = i2;
            this.needResetLastAnimation = false;
        }
        if (!this.mIsMirror) {
            if (this.isListening || this.firstInit) {
                if (f == 0.0f) {
                    if (this.lastDuration == 0.0f) {
                        setDefaultAnimation(i);
                    } else {
                        float fCoerceIn = RangesKt___RangesKt.coerceIn((SystemClock.elapsedRealtime() - this.lastStartTime) / this.lastDuration, 0.0f, 1.0f);
                        if (this.needResetLastAnimation) {
                            progress = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getProgress();
                        } else {
                            int i5 = this.lastStartValue;
                            progress = (int) (((i - i5) * fCoerceIn) + i5);
                        }
                        setDefaultAnimation(progress);
                        float f3 = (1.0f - fCoerceIn) * this.lastDuration;
                        if (!this.mIsMirror) {
                            int i6 = this.lastStartValue;
                            int progress3 = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getProgress();
                            StringBuilder sbM3 = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(fCoerceIn, progress, "handleNoDuration proportion: ", " currentProgress: ", " duration: ");
                            ChildHelper$.ExternalSyntheticOutline0.m(f3, i6, " lastStartValue: ", " sliderProgress: ", sbM3);
                            sbM3.append(progress3);
                            LogUtil.i$default(str2, str, sbM3.toString(), null, 8);
                        }
                        if (f3 == 0.0f) {
                            setDefaultAnimation(i);
                        } else {
                            setCustomProgressAnimDuration(f3);
                            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setProgress(i, 0, true);
                            int distanceFromBottom = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getDistanceFromBottom(getValue(), -1);
                            this.distanceFromBottom = distanceFromBottom;
                            setBrightnessIconAnimator(f3, i, distanceFromBottom);
                        }
                        if (this.needResetLastAnimation) {
                            this.needResetLastAnimation = false;
                            this.lastDuration = 0.0f;
                            this.lastStartValue = 0;
                        }
                    }
                } else if (f > 0.0f) {
                    setCustomProgressAnimDuration(f);
                    ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setProgress(i, 0, true);
                    int distanceFromBottom2 = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getDistanceFromBottom(i, -1);
                    this.distanceFromBottom = distanceFromBottom2;
                    setBrightnessIconAnimator(f, i, distanceFromBottom2);
                } else {
                    setDefaultAnimation(i);
                }
            }
            toggleSlider = this.mMirror;
            if (toggleSlider != null) {
                toggleSlider.setValue(i, 0.0f, 0, 0L);
            }
        }
        setDefaultAnimation(i);
        this.firstInit = false;
        toggleSlider = this.mMirror;
        if (toggleSlider != null) {
            toggleSlider.setValue(i, 0.0f, 0, 0L);
        }
    }

    public final void showToast() {
        if (this.warningToast.isToastActive()) {
            return;
        }
        this.warningToast.show(((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getContext(), R.string.config_bandwidthEstimateSource);
    }

    public final void stopPhysicsMove() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(this.tag, "-->stopPhysicsMove: ", "SystemUi--QuickSettings");
        }
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).stopPhysicsMove();
        this.needResetLastAnimation = true;
        this.latestUpdateBrightness = -1;
    }

    public final void syncTouchAnimationFromOriginSlider(float f, float f2, float f3) {
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) this).mView;
        oplusQsBaseToggleSliderLayout.setTranslationY(f);
        oplusQsBaseToggleSliderLayout.setScaleX(f2);
        oplusQsBaseToggleSliderLayout.setScaleY(f3);
    }

    public final void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        View toggleView = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getToggleView();
        ClipBrightnessView clipBrightnessView = toggleView instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView : null;
        boolean z = clipBrightnessView != null && clipBrightnessView.getAutoMode();
        IBrightnessSliderController.updateSliderColor$default(this, false, colorType, 1);
        if (colorType == QSSpecialModeUpdateMonitor.ColorType.UI_MODE && clipBrightnessView != null) {
            clipBrightnessView.init();
            clipBrightnessView.requestLayout();
        }
        if (clipBrightnessView != null) {
            clipBrightnessView.updateIconConfig();
        }
        setAutoBrightness(z);
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateColor mIsMirror: ", " automatic: ", this.mIsMirror, z), null, 8);
    }

    public void updateIconRotation(float f, int i) {
        if (((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getToggleView() instanceof ClipBrightnessView) {
            View toggleView = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getToggleView();
            ClipBrightnessView clipBrightnessView = toggleView instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView : null;
            if (clipBrightnessView != null) {
                clipBrightnessView.updateRotation(f, i);
            }
        }
    }

    public void updateIconRotationWithAnimation(float f, float f2, int i) {
        if (((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getToggleView() instanceof ClipBrightnessView) {
            View toggleView = ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getToggleView();
            ClipBrightnessView clipBrightnessView = toggleView instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView : null;
            if (clipBrightnessView != null) {
                clipBrightnessView.updateRotationAnimation(f, f2, i);
            }
        }
    }

    public void updateSliderColor(boolean z, QSSpecialModeUpdateMonitor.ColorType colorType) {
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).updateColor(QsViewBackgroundKt.isMixColorSupport(getContext(), GaussBlurUtils.isQSGaussBlurDisabled()), z, colorType);
    }

    public final void updateSliderCornerRadius() {
        ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).updateCornerRadius();
    }

    public final void updateViewEnable() {
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) this).mView;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQsBaseToggleSliderLayout.post(anonymousClass1);
            return;
        }
        if (((Boolean) this.isRestricted.get()).booleanValue()) {
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setSliderEnabled(false);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setToggleViewEnabled(1.0f, false);
            return;
        }
        if (((Boolean) this.isSlidersDisabled.get()).booleanValue()) {
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setSliderEnabled(false);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setToggleViewEnabled(1.0f, false);
            return;
        }
        boolean zBooleanValue = ((Boolean) this.isAutoModeDisabled.get()).booleanValue();
        View view = ((ViewController) this).mView;
        if (zBooleanValue) {
            ((OplusQsBaseToggleSliderLayout) view).setSliderEnabled(true);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setToggleViewEnabled(1.0f, false);
        } else {
            ((OplusQsBaseToggleSliderLayout) view).setSliderEnabled(true);
            ((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).setToggleViewEnabled(1.0f, true);
        }
    }

    public final void setBrightnessIconAnimator(float f, int i, int i2) {
        float max;
        int max2;
        if (((OplusQsBaseToggleSliderLayout) ((ViewController) this).mView).getLayoutDirection() == 1) {
            max = getMax() - i;
            max2 = getMax();
        } else {
            max = i;
            max2 = getMax();
        }
        updateIconRotationWithAnimation((max / max2) * 360.0f, f, i2);
    }

    public void setValue(int i) {
        setValue(i, 0.0f, 0, 0L);
    }
}
