package com.oplus.systemui.shared.clocks;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockConfig;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockEventListeners;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockEvents;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockFaceConfig;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockTickRate;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.ChargeUtils;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl;
import com.oplus.systemui.keyguard.view.ClockExtraTextView;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RedHorizontalSingleClockController extends OplusClockController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "RedHorizontalSingleClockController";
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
    private TextView mDateView;
    private AnimatorSet mDischargingAnimSet;
    public boolean mIsCharging;
    private final LayoutInflater mLayoutInflater;
    private final RedHorizontalSingleClockController$mListener$1 mListener;
    private RedHorizontalSingleClockView mLockClockContainer;
    private AnimatorSet mScreenOnAnimSet;
    private final OplusClockFaceController smallClock;
    public boolean viewAttached;

    /* JADX INFO: renamed from: com.oplus.systemui.shared.clocks.RedHorizontalSingleClockController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnAttachStateChangeListener {
        public /* synthetic */ RedHorizontalSingleClockController this$0;

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            RedHorizontalSingleClockController redHorizontalSingleClockController = this.this$0;
            redHorizontalSingleClockController.viewAttached = true;
            Context context = redHorizontalSingleClockController.mContext;
            if (context != null) {
                ChargingAnimationImpl.getInstance(context).registerCallback(new WeakReference(this.this$0.mListener));
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            RedHorizontalSingleClockController redHorizontalSingleClockController = this.this$0;
            redHorizontalSingleClockController.viewAttached = false;
            RedHorizontalSingleClockView redHorizontalSingleClockView = redHorizontalSingleClockController.mLockClockContainer;
            if (redHorizontalSingleClockView != null) {
                redHorizontalSingleClockView.removeOnAttachStateChangeListener(this);
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
    public final class RedHorizontalSingleClockEvents extends OplusClockEvents {
        public /* synthetic */ RedHorizontalSingleClockController this$0;

        public final void onColorChange() {
            RedHorizontalSingleClockController redHorizontalSingleClockController = this.this$0;
            Context context = redHorizontalSingleClockController.mContext;
            if (context != null) {
                KeyguardUtils.Companion companion = KeyguardUtils.Companion;
                if (!companion.getKeyguardUtils(context).isClockGlobalThemeEnable || companion.isDefaultKeyguardInSettings(context)) {
                    int iBlendARGB = ColorUtils.blendARGB(redHorizontalSingleClockController.getColorExtractor().getNeutralColors().supportsDarkText() ? -16777216 : -1, -1, 0.0f);
                    RedHorizontalSingleClockView redHorizontalSingleClockView = redHorizontalSingleClockController.mLockClockContainer;
                    if (redHorizontalSingleClockView != null) {
                        redHorizontalSingleClockView.setTextColor(iBlendARGB);
                    }
                }
            }
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockEvents
        public final void onFinishedGoingToSleep() {
            KgdLogUtil.i(RedHorizontalSingleClockController.TAG, "onFinishedGoingToSleep");
            RedHorizontalSingleClockController.access$resetChargeAndShowView(this.this$0);
        }

        public final void onFinishedWakingUp() {
            RedHorizontalSingleClockController redHorizontalSingleClockController = this.this$0;
            KgdLogUtil.i(RedHorizontalSingleClockController.TAG, "onFinishedWakingUp , mIsCharging : " + redHorizontalSingleClockController.mIsCharging + " , mChargingAnimState : " + redHorizontalSingleClockController.mChargingAnimState);
            if (redHorizontalSingleClockController.mIsCharging && redHorizontalSingleClockController.mChargingAnimState == 4) {
                ClockExtraTextView clockExtraTextView = redHorizontalSingleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView != null) {
                    clockExtraTextView.setAlpha(1.0f);
                }
                KgdLogUtil.i(RedHorizontalSingleClockController.TAG, "playScreenOnAnim in onFinishedWakingUp");
                RedHorizontalSingleClockController.access$playScreenOnAnim(redHorizontalSingleClockController);
            }
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockEvents
        public final void onUserSwitchComplete(int i) {
            RedHorizontalSingleClockView redHorizontalSingleClockView = this.this$0.mLockClockContainer;
            if (redHorizontalSingleClockView != null) {
                redHorizontalSingleClockView.updateSwitchParametersState();
            }
        }
    }

    public RedHorizontalSingleClockController(Context context, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        super(context);
        this.colorExtractor = sysuiColorExtractor;
        this.eventListeners = new ClockEventListeners();
        this.config$delegate = LazyKt__LazyJVMKt.lazy(new RedHorizontalSingleClockController$$ExternalSyntheticLambda0());
        this.mChargingAnimState = 4;
        RedHorizontalSingleClockController$mListener$1 redHorizontalSingleClockController$mListener$1 = new RedHorizontalSingleClockController$mListener$1();
        redHorizontalSingleClockController$mListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mListener = redHorizontalSingleClockController$mListener$1;
        Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, context, false, 6);
        this.mContext = contextUpdateDisplayConfiguration$default;
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextUpdateDisplayConfiguration$default);
        this.mLayoutInflater = layoutInflaterCloneInContext;
        this.smallClock = new RedHorizontalSingleClockFaceController(this, (RedHorizontalSingleClockView) layoutInflaterCloneInContext.inflate(2131558953, (ViewGroup) null));
        RedHorizontalSingleClockView view = ((RedHorizontalSingleClockFaceController) getSmallClock()).getView();
        this.mLockClockContainer = view;
        if (view != null) {
            view.setClipChildren(false);
        }
        RedHorizontalSingleClockView redHorizontalSingleClockView = this.mLockClockContainer;
        if (redHorizontalSingleClockView != null) {
            redHorizontalSingleClockView.setClipToPadding(false);
        }
        RedHorizontalSingleClockView redHorizontalSingleClockView2 = this.mLockClockContainer;
        this.mDataChargeContainer = redHorizontalSingleClockView2 != null ? (LinearLayout) redHorizontalSingleClockView2.findViewById(2131362780) : null;
        RedHorizontalSingleClockView redHorizontalSingleClockView3 = this.mLockClockContainer;
        this.mClockExtraTextViewBig = redHorizontalSingleClockView3 != null ? (ClockExtraTextView) redHorizontalSingleClockView3.findViewById(2131365810) : null;
        RedHorizontalSingleClockView redHorizontalSingleClockView4 = this.mLockClockContainer;
        this.mClockExtraTextViewNormal = redHorizontalSingleClockView4 != null ? (ClockExtraTextView) redHorizontalSingleClockView4.findViewById(2131365809) : null;
        RedHorizontalSingleClockView redHorizontalSingleClockView5 = this.mLockClockContainer;
        this.mDateView = redHorizontalSingleClockView5 != null ? (TextView) redHorizontalSingleClockView5.findViewById(2131365803) : null;
        RedHorizontalSingleClockEvents redHorizontalSingleClockEvents = new RedHorizontalSingleClockEvents();
        redHorizontalSingleClockEvents.this$0 = this;
        VarHandle.storeStoreFence();
        this.events = redHorizontalSingleClockEvents;
        RedHorizontalSingleClockView redHorizontalSingleClockView6 = this.mLockClockContainer;
        if (redHorizontalSingleClockView6 != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            redHorizontalSingleClockView6.addOnAttachStateChangeListener(anonymousClass1);
        }
    }

    public static final void access$endDisChargingAnimIfNeed(RedHorizontalSingleClockController redHorizontalSingleClockController) {
        AnimatorSet animatorSet = redHorizontalSingleClockController.mDischargingAnimSet;
        if (animatorSet == null || !animatorSet.isRunning()) {
            return;
        }
        KgdLogUtil.i(TAG, "end prev discharging anim and show date");
        AnimatorSet animatorSet2 = redHorizontalSingleClockController.mDischargingAnimSet;
        if (animatorSet2 != null) {
            animatorSet2.end();
        }
        TextView textView = redHorizontalSingleClockController.mDateView;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    public static final void access$playDischargingAnim(RedHorizontalSingleClockController redHorizontalSingleClockController) {
        KgdLogUtil.i(TAG, "playDischargingAnim");
        if (!redHorizontalSingleClockController.viewAttached) {
            KgdLogUtil.w(TAG, "viewAttached is false");
            return;
        }
        if (redHorizontalSingleClockController.mDischargingAnimSet == null) {
            redHorizontalSingleClockController.mDischargingAnimSet = ChargeUtils.getDisChargingAnim(redHorizontalSingleClockController.mClockExtraTextViewBig, redHorizontalSingleClockController.mDataChargeContainer, redHorizontalSingleClockController.mClockExtraTextViewNormal);
        }
        AnimatorSet animatorSet = redHorizontalSingleClockController.mDischargingAnimSet;
        if (animatorSet != null) {
            animatorSet.start();
        }
    }

    public static final void access$playScreenOnAnim(RedHorizontalSingleClockController redHorizontalSingleClockController) {
        if (!redHorizontalSingleClockController.viewAttached) {
            KgdLogUtil.w(TAG, "viewAttached is false");
            return;
        }
        if (redHorizontalSingleClockController.mScreenOnAnimSet == null) {
            redHorizontalSingleClockController.mScreenOnAnimSet = ChargeUtils.getScreenOnAnim(redHorizontalSingleClockController.mClockExtraTextViewBig, redHorizontalSingleClockController.mDataChargeContainer);
        }
        AnimatorSet animatorSet = redHorizontalSingleClockController.mScreenOnAnimSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            KgdLogUtil.i(TAG, "playScreenOnAnim is Running cancel");
            AnimatorSet animatorSet2 = redHorizontalSingleClockController.mScreenOnAnimSet;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
        }
        AnimatorSet animatorSet3 = redHorizontalSingleClockController.mScreenOnAnimSet;
        if (animatorSet3 != null) {
            animatorSet3.start();
        }
    }

    public static final void access$resetAnim(RedHorizontalSingleClockController redHorizontalSingleClockController) {
        AnimatorSet animatorSet;
        AnimatorSet animatorSet2;
        AnimatorSet animatorSet3 = redHorizontalSingleClockController.mScreenOnAnimSet;
        if (animatorSet3 != null && animatorSet3.isRunning() && (animatorSet2 = redHorizontalSingleClockController.mScreenOnAnimSet) != null) {
            animatorSet2.cancel();
        }
        AnimatorSet animatorSet4 = redHorizontalSingleClockController.mDischargingAnimSet;
        if (animatorSet4 != null && animatorSet4.isRunning() && (animatorSet = redHorizontalSingleClockController.mDischargingAnimSet) != null) {
            animatorSet.cancel();
        }
        redHorizontalSingleClockController.updateAlphaWhenCharging(false);
    }

    public static final void access$resetChargeAndShowView(RedHorizontalSingleClockController redHorizontalSingleClockController) {
        KgdLogUtil.i(TAG, "resetChargeAndShowView");
        AnimatorSet animatorSet = redHorizontalSingleClockController.mScreenOnAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = redHorizontalSingleClockController.mDischargingAnimSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        redHorizontalSingleClockController.mChargingAnimState = 4;
        redHorizontalSingleClockController.updateAlphaWhenCharging(redHorizontalSingleClockController.mIsCharging);
    }

    public static final void access$resetChargeTipsIfNeeded(RedHorizontalSingleClockController redHorizontalSingleClockController, boolean z) {
        AnimatorSet animatorSet;
        if (z) {
            return;
        }
        if (redHorizontalSingleClockController.mBatteryIsLow && ((animatorSet = redHorizontalSingleClockController.mDischargingAnimSet) == null || !animatorSet.isRunning())) {
            ClockExtraTextView clockExtraTextView = redHorizontalSingleClockController.mClockExtraTextViewNormal;
            if (Intrinsics.areEqual(clockExtraTextView != null ? Float.valueOf(clockExtraTextView.getAlpha()) : null, 0.0f)) {
                ClockExtraTextView clockExtraTextView2 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView2 != null) {
                    clockExtraTextView2.setAlpha(1.0f);
                }
                KgdLogUtil.i(TAG, "ensure extra text visible when batter is low");
            }
        }
        if (redHorizontalSingleClockController.mBatteryIsLow) {
            return;
        }
        AnimatorSet animatorSet2 = redHorizontalSingleClockController.mDischargingAnimSet;
        if (animatorSet2 == null || !animatorSet2.isRunning()) {
            ClockExtraTextView clockExtraTextView3 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
            if (TextUtils.isEmpty(clockExtraTextView3 != null ? clockExtraTextView3.getText() : null)) {
                return;
            }
            ClockExtraTextView clockExtraTextView4 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
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
        return resources.getDimensionPixelSize(2131172301);
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

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public final void initialize(boolean z, float f, float f2) {
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

    public final void updateAlphaWhenCharging(boolean z) {
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
    public final class RedHorizontalSingleClockFaceController extends OplusClockFaceController {
        private final ClockFaceConfig config = new ClockFaceConfig((ClockTickRate) null, false, false, false, 15, (DefaultConstructorMarker) null);
        private final OplusClockFaceEvents events;
        private final RedHorizontalSingleClockView view;

        public RedHorizontalSingleClockFaceController(RedHorizontalSingleClockController redHorizontalSingleClockController, RedHorizontalSingleClockView redHorizontalSingleClockView) {
            this.view = redHorizontalSingleClockView;
            RedHorizontalSingleClockController$RedHorizontalSingleClockFaceController$events$1 redHorizontalSingleClockController$RedHorizontalSingleClockFaceController$events$1 = new RedHorizontalSingleClockController$RedHorizontalSingleClockFaceController$events$1();
            redHorizontalSingleClockController$RedHorizontalSingleClockFaceController$events$1.this$0 = redHorizontalSingleClockController;
            VarHandle.storeStoreFence();
            this.events = redHorizontalSingleClockController$RedHorizontalSingleClockFaceController$events$1;
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockFaceController
        public ClockFaceConfig getConfig() {
            return this.config;
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockFaceController
        public OplusClockFaceEvents getEvents() {
            return this.events;
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockFaceController
        public RedHorizontalSingleClockView getView() {
            return this.view;
        }
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockController
    public OplusClockFaceController getSmallClock() {
        return this.smallClock;
    }
}
