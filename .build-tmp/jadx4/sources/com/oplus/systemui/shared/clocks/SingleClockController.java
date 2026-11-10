package com.oplus.systemui.shared.clocks;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.util.TimeZone;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockConfig;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockEventListeners;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockEvents;
import com.android.systemui.statusbar.notification.helper.NotificationCommHelper;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.ChargeUtils;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl;
import com.oplus.systemui.keyguard.view.ClockExtraTextView;
import com.oplus.systemui.keyguard.view.NumberFontVariationTextView;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleClockController extends OplusClockController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "SingleClockController";
    private final SysuiColorExtractor colorExtractor;
    private final Lazy config$delegate;
    private final ClockEventListeners eventListeners;
    private final ClockEvents events;
    public boolean mBatteryIsLow;
    public int mBatteryLevel;
    public int mChargingAnimState;
    private ClockExtraTextView mClockExtraTextViewBig;
    private ClockExtraTextView mClockExtraTextViewNormal;
    private Context mContext;
    private LinearLayout mDataChargeContainer;
    private NumberFontVariationTextView mDateView;
    private AnimatorSet mDischargingAnimSet;
    public boolean mIsCharging;
    private final LayoutInflater mLayoutInflater;
    private final SingleClockController$mListener$1 mListener;
    private SingleClockView mLockClockContainer;
    private AnimatorSet mScreenOnAnimSet;
    private final OplusClockFaceController smallClock;
    public boolean viewAttached;

    /* JADX INFO: renamed from: com.oplus.systemui.shared.clocks.SingleClockController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnAttachStateChangeListener {
        public /* synthetic */ SingleClockController this$0;

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            SingleClockController singleClockController = this.this$0;
            singleClockController.viewAttached = true;
            Context context = singleClockController.mContext;
            if (context != null) {
                ChargingAnimationImpl.getInstance(context).registerCallback(new WeakReference(this.this$0.mListener));
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            SingleClockController singleClockController = this.this$0;
            singleClockController.viewAttached = false;
            SingleClockView singleClockView = singleClockController.mLockClockContainer;
            if (singleClockView != null) {
                singleClockView.removeOnAttachStateChangeListener(this);
            }
            Context context = this.this$0.mContext;
            if (context != null) {
                ChargingAnimationImpl.getInstance(context).unregisterCallback(new WeakReference(this.this$0.mListener));
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SingleClockEvents extends OplusClockEvents {
        public /* synthetic */ SingleClockController this$0;

        public final void onColorChange() {
            SingleClockController singleClockController = this.this$0;
            Context context = singleClockController.mContext;
            if (context != null) {
                KeyguardUtils.Companion companion = KeyguardUtils.Companion;
                if (!companion.getKeyguardUtils(context).isClockGlobalThemeEnable || companion.isDefaultKeyguardInSettings(context)) {
                    boolean zSupportsDarkText = singleClockController.getColorExtractor().getNeutralColors().supportsDarkText();
                    OplusLockIconView$.ExternalSyntheticOutline0.m("onColorChange supportsDarkText: ", SingleClockController.TAG, zSupportsDarkText);
                    int iBlendARGB = ColorUtils.blendARGB(zSupportsDarkText ? -16777216 : -1, -1, 0.0f);
                    SingleClockView singleClockView = singleClockController.mLockClockContainer;
                    if (singleClockView != null) {
                        singleClockView.setTextColor(iBlendARGB);
                    }
                }
            }
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockEvents
        public final void onFinishedGoingToSleep() {
            KgdLogUtil.i(SingleClockController.TAG, "onFinishedGoingToSleep");
            SingleClockController.access$resetChargeAndShowView(this.this$0);
        }

        public final void onFinishedWakingUp() {
            SingleClockController singleClockController = this.this$0;
            KgdLogUtil.i(SingleClockController.TAG, "onFinishedWakingUp , mIsCharging : " + singleClockController.mIsCharging + " , mChargingAnimState : " + singleClockController.mChargingAnimState);
            if (singleClockController.mIsCharging && singleClockController.mChargingAnimState == 4) {
                ClockExtraTextView clockExtraTextView = singleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView != null) {
                    clockExtraTextView.setAlpha(1.0f);
                }
                KgdLogUtil.i(SingleClockController.TAG, "playScreenOnAnim in onFinishedWakingUp");
                SingleClockController.access$playScreenOnAnim(singleClockController);
            }
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockEvents
        public void onTimeZoneChanged(TimeZone timeZone) {
            SingleClockView singleClockView = this.this$0.mLockClockContainer;
            if (singleClockView != null) {
                singleClockView.onTimeZoneChanged(timeZone);
            }
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockEvents
        public final void onUserSwitchComplete(int i) {
            SingleClockView singleClockView = this.this$0.mLockClockContainer;
            if (singleClockView != null) {
                singleClockView.updateSwitchParametersState$1();
            }
        }
    }

    public SingleClockController(Context context, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        super(context);
        this.colorExtractor = sysuiColorExtractor;
        this.eventListeners = new ClockEventListeners();
        this.config$delegate = LazyKt__LazyJVMKt.lazy(new SingleClockController$$ExternalSyntheticLambda0());
        SingleClockController$mListener$1 singleClockController$mListener$1 = new SingleClockController$mListener$1();
        singleClockController$mListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mListener = singleClockController$mListener$1;
        this.mChargingAnimState = 4;
        Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, context, false, 6);
        this.mContext = contextUpdateDisplayConfiguration$default;
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextUpdateDisplayConfiguration$default);
        this.mLayoutInflater = layoutInflaterCloneInContext;
        this.smallClock = new SingleClockFaceController(this, (SingleClockView) layoutInflaterCloneInContext.inflate(2131558978, (ViewGroup) null));
        SingleClockView view = ((SingleClockFaceController) getSmallClock()).getView();
        this.mLockClockContainer = view;
        if (view != null) {
            view.setClipChildren(false);
        }
        SingleClockView singleClockView = this.mLockClockContainer;
        if (singleClockView != null) {
            singleClockView.setClipToPadding(false);
        }
        SingleClockView singleClockView2 = this.mLockClockContainer;
        this.mDataChargeContainer = singleClockView2 != null ? (LinearLayout) singleClockView2.findViewById(2131362780) : null;
        SingleClockView singleClockView3 = this.mLockClockContainer;
        this.mClockExtraTextViewBig = singleClockView3 != null ? (ClockExtraTextView) singleClockView3.findViewById(2131365810) : null;
        SingleClockView singleClockView4 = this.mLockClockContainer;
        this.mClockExtraTextViewNormal = singleClockView4 != null ? (ClockExtraTextView) singleClockView4.findViewById(2131365809) : null;
        SingleClockView singleClockView5 = this.mLockClockContainer;
        this.mDateView = singleClockView5 != null ? (NumberFontVariationTextView) singleClockView5.findViewById(2131362784) : null;
        SingleClockEvents singleClockEvents = new SingleClockEvents();
        singleClockEvents.this$0 = this;
        VarHandle.storeStoreFence();
        this.events = singleClockEvents;
        SingleClockView singleClockView6 = this.mLockClockContainer;
        if (singleClockView6 != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            singleClockView6.addOnAttachStateChangeListener(anonymousClass1);
        }
    }

    public static final void access$endDisChargingAnimIfNeed(SingleClockController singleClockController) {
        AnimatorSet animatorSet = singleClockController.mDischargingAnimSet;
        if (animatorSet == null || !animatorSet.isRunning()) {
            return;
        }
        KgdLogUtil.i(TAG, "end prev discharging anim and show date");
        AnimatorSet animatorSet2 = singleClockController.mDischargingAnimSet;
        if (animatorSet2 != null) {
            animatorSet2.end();
        }
        NumberFontVariationTextView numberFontVariationTextView = singleClockController.mDateView;
        if (numberFontVariationTextView != null) {
            numberFontVariationTextView.setVisibility(0);
        }
    }

    public static final void access$playDischargingAnim(SingleClockController singleClockController) {
        if (!singleClockController.viewAttached) {
            KgdLogUtil.w(TAG, "viewAttached is false");
            return;
        }
        if (!NotificationCommHelper.Companion.getInstance().isKeyguardShowing) {
            KgdLogUtil.w(TAG, "keyguard is hide, don't play anim");
            return;
        }
        if (singleClockController.mDischargingAnimSet == null) {
            singleClockController.mDischargingAnimSet = ChargeUtils.getDisChargingAnim(singleClockController.mClockExtraTextViewBig, singleClockController.mDataChargeContainer, singleClockController.mClockExtraTextViewNormal);
        }
        KgdLogUtil.i(TAG, "playDischargingAnim, mDischargingAnimSet: " + singleClockController.mDischargingAnimSet + ", this: " + singleClockController);
        AnimatorSet animatorSet = singleClockController.mDischargingAnimSet;
        if (animatorSet != null) {
            animatorSet.start();
        }
    }

    public static final void access$playScreenOnAnim(SingleClockController singleClockController) {
        if (!singleClockController.viewAttached) {
            KgdLogUtil.w(TAG, "viewAttached is false");
            return;
        }
        if (!NotificationCommHelper.Companion.getInstance().isKeyguardShowing) {
            KgdLogUtil.w(TAG, "keyguard is hide, don't play anim");
            return;
        }
        if (singleClockController.mScreenOnAnimSet == null) {
            singleClockController.mScreenOnAnimSet = ChargeUtils.getScreenOnAnim(singleClockController.mClockExtraTextViewBig, singleClockController.mDataChargeContainer);
        }
        KgdLogUtil.i(TAG, "playScreenOnAnim, this: " + singleClockController + ", mScreenOnAnimSet: " + singleClockController.mScreenOnAnimSet);
        AnimatorSet animatorSet = singleClockController.mScreenOnAnimSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            KgdLogUtil.i(TAG, "playScreenOnAnim is Running cancel");
            AnimatorSet animatorSet2 = singleClockController.mScreenOnAnimSet;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
        }
        AnimatorSet animatorSet3 = singleClockController.mScreenOnAnimSet;
        if (animatorSet3 != null) {
            animatorSet3.start();
        }
    }

    public static final void access$resetAnim(SingleClockController singleClockController) {
        AnimatorSet animatorSet = singleClockController.mScreenOnAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = singleClockController.mDischargingAnimSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        singleClockController.updateAlphaWhenCharging$1(false);
    }

    public static final void access$resetChargeAndShowView(SingleClockController singleClockController) {
        KgdLogUtil.i(TAG, "resetChargeAndShowView");
        AnimatorSet animatorSet = singleClockController.mScreenOnAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = singleClockController.mDischargingAnimSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        singleClockController.mChargingAnimState = 4;
        singleClockController.updateAlphaWhenCharging$1(singleClockController.mIsCharging);
    }

    public static final void access$resetChargeTipsIfNeeded(SingleClockController singleClockController, boolean z) {
        AnimatorSet animatorSet;
        if (z) {
            return;
        }
        if (singleClockController.mBatteryIsLow && ((animatorSet = singleClockController.mDischargingAnimSet) == null || !animatorSet.isRunning())) {
            ClockExtraTextView clockExtraTextView = singleClockController.mClockExtraTextViewNormal;
            if (Intrinsics.areEqual(clockExtraTextView != null ? Float.valueOf(clockExtraTextView.getAlpha()) : null, 0.0f)) {
                ClockExtraTextView clockExtraTextView2 = singleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView2 != null) {
                    clockExtraTextView2.setAlpha(1.0f);
                }
                KgdLogUtil.i(TAG, "ensure extra text visible when batter is low");
            }
        }
        if (singleClockController.mBatteryIsLow) {
            return;
        }
        AnimatorSet animatorSet2 = singleClockController.mDischargingAnimSet;
        if (animatorSet2 == null || !animatorSet2.isRunning()) {
            ClockExtraTextView clockExtraTextView3 = singleClockController.mClockExtraTextViewNormal;
            if (TextUtils.isEmpty(clockExtraTextView3 != null ? clockExtraTextView3.getText() : null)) {
                return;
            }
            ClockExtraTextView clockExtraTextView4 = singleClockController.mClockExtraTextViewNormal;
            if (clockExtraTextView4 != null) {
                clockExtraTextView4.setText("");
            }
            KgdLogUtil.i(TAG, "ensure extra text is empty when batter is discharging and not low");
        }
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public void dump(PrintWriter printWriter) {
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public int getClockOffsetY() {
        Resources resources;
        Context context = this.mContext;
        if (context == null || (resources = context.getResources()) == null) {
            return 0;
        }
        return resources.getDimensionPixelSize(2131172654);
    }

    public final SysuiColorExtractor getColorExtractor() {
        return this.colorExtractor;
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public ClockConfig getConfig() {
        return (ClockConfig) this.config$delegate.getValue();
    }

    public ClockEventListeners getEventListeners() {
        return this.eventListeners;
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public ClockEvents getEvents() {
        return this.events;
    }

    public final void release() {
        KgdLogUtil.i(TAG, "release resource");
        this.viewAttached = false;
        AnimatorSet animatorSet = this.mScreenOnAnimSet;
        if (animatorSet != null) {
            animatorSet.end();
        }
        AnimatorSet animatorSet2 = this.mDischargingAnimSet;
        if (animatorSet2 != null) {
            animatorSet2.end();
        }
        this.mLockClockContainer = null;
        this.mDataChargeContainer = null;
        this.mClockExtraTextViewBig = null;
        this.mClockExtraTextViewNormal = null;
        this.mChargingAnimState = 4;
        this.mDateView = null;
        this.mScreenOnAnimSet = null;
        this.mDischargingAnimSet = null;
    }

    public final void updateAlphaWhenCharging$1(boolean z) {
        LinearLayout linearLayout = this.mDataChargeContainer;
        if (z) {
            if (linearLayout != null) {
                linearLayout.setAlpha(0.0f);
            }
            ClockExtraTextView clockExtraTextView = this.mClockExtraTextViewBig;
            if (clockExtraTextView != null) {
                clockExtraTextView.setAlpha(1.0f);
            }
        } else {
            if (linearLayout != null) {
                linearLayout.setAlpha(1.0f);
            }
            ClockExtraTextView clockExtraTextView2 = this.mClockExtraTextViewBig;
            if (clockExtraTextView2 != null) {
                clockExtraTextView2.setAlpha(0.0f);
            }
        }
        KgdLogUtil.i(TAG, "updateAlphaWhenCharging , isCharging : " + z + " , mChargingAnimState : " + this.mChargingAnimState);
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public void updateContext(Configuration configuration) {
        Configuration configuration2;
        FeatureOption.INSTANCE.getClass();
        boolean z = FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled();
        Context context = this.mContext;
        if (context == null || !z) {
            return;
        }
        Resources resources = context.getResources();
        if (resources == null || (configuration2 = resources.getConfiguration()) == null || configuration.smallestScreenWidthDp != configuration2.smallestScreenWidthDp) {
            KeyguardUtils.Companion companion = KeyguardUtils.Companion;
            Context context2 = this.mContext;
            this.mContext = KeyguardUtils.Companion.updateDisplayConfiguration$default(companion, context2 != null ? context2.createConfigurationContext(configuration) : null, false, 6);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SingleClockFaceController extends OplusClockFaceController {
        private final OplusClockFaceEvents events;
        private final SingleClockView view;

        public SingleClockFaceController(SingleClockController singleClockController, SingleClockView singleClockView) {
            this.view = singleClockView;
            SingleClockController$SingleClockFaceController$events$1 singleClockController$SingleClockFaceController$events$1 = new SingleClockController$SingleClockFaceController$events$1();
            singleClockController$SingleClockFaceController$events$1.this$0 = singleClockController;
            VarHandle.storeStoreFence();
            this.events = singleClockController$SingleClockFaceController$events$1;
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockFaceController
        public OplusClockFaceEvents getEvents() {
            return this.events;
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockFaceController
        public SingleClockView getView() {
            return this.view;
        }
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public OplusClockFaceController getSmallClock() {
        return this.smallClock;
    }
}
