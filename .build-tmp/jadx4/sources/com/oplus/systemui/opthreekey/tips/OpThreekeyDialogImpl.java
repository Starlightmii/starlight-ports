package com.oplus.systemui.opthreekey.tips;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.hardware.display.DisplayManagerGlobal;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.slice.widget.RowView$;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.utils.OplusOSAodManager;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleServiceCallbackImpl;
import com.oplus.systemui.opthreekey.dagger.OpThreekeyComponent;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStateObserver;
import com.oplus.systemui.opthreekey.seeding.OpThreeKeySeedling;
import com.oplus.systemui.opthreekey.utils.ConvertThreeKeyStateUtil;
import com.oplus.systemui.opthreekey.utils.OpVolumeUtils;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.GlobalActionsUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.util.OpUtils;
import com.oplusos.systemui.volume.VolumeSettingsValueProxy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OpThreekeyDialogImpl implements Plugin, ConfigurationController.ConfigurationListener, ThreeKeyStateObserver, ScreenLifecycle.Observer {
    public static final String[] DISMISS_REASONS = {"stealth_mode", "seedling_tip", "timeout"};
    public boolean isRightTk;
    public final InterestingConfigChanges mConfigChanges;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public Dialog mDialog;
    public int mDialogPosition;
    public ViewGroup mDialogView;
    public final KeyguardStyleServiceCallbackImpl mKeyguardStyleServiceCallback;
    public OpThreekeyComponent mListener;
    public final OpThreeKeySeedling mOpThreeKeySeedling;
    public final AnonymousClass3 mOrientationListener;
    public final ScreenLifecycle mScreenLifecycle;
    public final SeedlingPluginManager mSeedlingPluginManager;
    public BackgroundBlurDrawable mThreeKeyBlurDrawable;
    public ImageView mThreeKeyIcon;
    public LayerDrawable mThreeKeyLayerDrawable;
    public TextView mThreeKeyText;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final AnonymousClass1 mWakefulnessObserver;
    public Window mWindow;
    public WindowManager.LayoutParams mWindowLayoutParams;
    public int mWindowType;
    public final AnonymousClass4 viewOutlineProvider;
    public final H mHandler = new H();
    public int mThreeKeystate = -1;
    public int mOrientationType = 0;
    public int mThemeIconColor = 0;
    public int mThemeTextColor = 0;
    public int mThemeBgColor = 0;
    public int mAccentColor = 0;
    public int mThemeMode = 0;
    public boolean mIsREDVersion = false;
    public boolean mIsDeviceOn = false;
    public boolean mIsThreekeyStateChangedWhenScreenOff = false;
    public SmoothTransitionController.SmoothTransitionUiState mAODType = SmoothTransitionController.SmoothTransitionUiState.UNLOCK;

    /* JADX INFO: renamed from: com.oplus.systemui.opthreekey.tips.OpThreekeyDialogImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements WakefulnessLifecycle.Observer {
        public /* synthetic */ OpThreekeyDialogImpl this$0;

        public final void onFinishedGoingToSleep() {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "WakefulnessLifecycle, onFinishedGoingToSleep");
            this.this$0.mIsDeviceOn = false;
        }

        public final void onFinishedWakingUp() {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "WakefulnessLifecycle, onFinishedWakingUp");
            OpThreekeyDialogImpl opThreekeyDialogImpl = this.this$0;
            opThreekeyDialogImpl.mIsDeviceOn = true;
            if (opThreekeyDialogImpl.mIsThreekeyStateChangedWhenScreenOff) {
                opThreekeyDialogImpl.mIsThreekeyStateChangedWhenScreenOff = false;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.opthreekey.tips.OpThreekeyDialogImpl$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 extends ViewOutlineProvider {
        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), GlobalActionsUtils.dp2px(view.getContext(), 2131170886));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.opthreekey.tips.OpThreekeyDialogImpl$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements View.OnAttachStateChangeListener {
        public /* synthetic */ OpThreekeyDialogImpl this$0;

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "mDialogView onViewAttachedToWindow");
            int i = this.this$0.mIsREDVersion ? 2131233355 : 2131233353;
            if (OpUtils.isPacManVersion()) {
                i = 2131233354;
            }
            OpThreekeyDialogImpl opThreekeyDialogImpl = this.this$0;
            GradientDrawable gradientDrawable = (GradientDrawable) ((LayerDrawable) opThreekeyDialogImpl.mContext.getResources().getDrawable(i)).getDrawable(0);
            gradientDrawable.setColor(opThreekeyDialogImpl.mThemeBgColor);
            gradientDrawable.setCornerRadius(opThreekeyDialogImpl.mContext.getResources().getDimension(2131170886));
            if (VolumeBlurManager.INSTANCE.isGaussianBlurDisable(this.this$0.mContext)) {
                this.this$0.mDialogView.setBackgroundDrawable(gradientDrawable);
                this.this$0.mDialogView.requestLayout();
                LogUtil.i(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "three key dialog disable blur");
                return;
            }
            int dimensionPixelOffset = view.getContext().getResources().getDimensionPixelOffset(2131170886);
            int iDp2px = GlobalActionsUtils.dp2px(view.getContext(), 120.0f);
            this.this$0.mThreeKeyBlurDrawable = view.getViewRootImpl().createBackgroundBlurDrawable();
            this.this$0.mThreeKeyBlurDrawable.setCornerRadius(dimensionPixelOffset);
            this.this$0.mThreeKeyBlurDrawable.setBlurRadius(iDp2px);
            this.this$0.mThreeKeyLayerDrawable = new LayerDrawable(new Drawable[]{this.this$0.mThreeKeyBlurDrawable, gradientDrawable});
            OpThreekeyDialogImpl opThreekeyDialogImpl2 = this.this$0;
            ViewGroup viewGroup = opThreekeyDialogImpl2.mDialogView;
            if (viewGroup != null) {
                viewGroup.setBackground(opThreekeyDialogImpl2.mThreeKeyLayerDrawable);
                OpThreekeyDialogImpl opThreekeyDialogImpl3 = this.this$0;
                opThreekeyDialogImpl3.mDialogView.setOutlineProvider(opThreekeyDialogImpl3.viewOutlineProvider);
                this.this$0.mDialogView.requestLayout();
                LogUtil.i(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "three key dialog set blur");
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "mDialogView onViewDetachedFromWindow");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            OpThreekeyDialogImpl opThreekeyDialogImpl = OpThreekeyDialogImpl.this;
            if (i == 1) {
                OpThreekeyDialogImpl.m109$$Nest$mshowH(opThreekeyDialogImpl, message.arg1);
                return;
            }
            if (i == 2) {
                opThreekeyDialogImpl.dismissH(message.arg1);
                return;
            }
            if (i == 3) {
                opThreekeyDialogImpl.rescheduleTimeoutH();
                return;
            }
            if (i != 4) {
                return;
            }
            int i2 = message.arg1;
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i2, "stateInnerChange ", LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl");
            if (i2 != opThreekeyDialogImpl.mThreeKeystate) {
                opThreekeyDialogImpl.mThreeKeystate = i2;
                if (opThreekeyDialogImpl.shouldShow()) {
                    opThreekeyDialogImpl.updateThreekeyLayout();
                }
                OpThreekeyComponent opThreekeyComponent = opThreekeyDialogImpl.mListener;
                if (opThreekeyComponent != null) {
                    opThreekeyComponent.mKeyguardViewMediator.userActivity();
                }
            }
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mshowH, reason: not valid java name */
    public static void m109$$Nest$mshowH(OpThreekeyDialogImpl opThreekeyDialogImpl, int i) {
        int rotation;
        H h = opThreekeyDialogImpl.mHandler;
        SeedlingPluginManager seedlingPluginManager = opThreekeyDialogImpl.mSeedlingPluginManager;
        boolean z = seedlingPluginManager.isPluginAvailable;
        if (opThreekeyDialogImpl.shouldShow()) {
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "showH r=", LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl");
            h.removeMessages(1);
            h.removeMessages(2);
            if (seedlingPluginManager.isPluginAvailable) {
                opThreekeyDialogImpl.mOpThreeKeySeedling.updateThreeKeySeeding(opThreekeyDialogImpl.mContext, opThreekeyDialogImpl.getThreeKeyStateForSeedling());
                h.obtainMessage(2, 1, 0).sendToTarget();
                return;
            }
            opThreekeyDialogImpl.rescheduleTimeoutH();
            opThreekeyDialogImpl.registerOrientationListener(true);
            Display realDisplay = DisplayManagerGlobal.getInstance().getRealDisplay(0);
            if (realDisplay != null && (rotation = realDisplay.getRotation()) != opThreekeyDialogImpl.mOrientationType) {
                opThreekeyDialogImpl.mOrientationType = rotation;
                opThreekeyDialogImpl.updateThreekeyLayout();
            }
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(opThreekeyDialogImpl.mOrientationType, LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", new StringBuilder("showH mOrientationType="));
            opThreekeyDialogImpl.mDialog.show();
            OpThreekeyComponent opThreekeyComponent = opThreekeyDialogImpl.mListener;
            if (opThreekeyComponent != null) {
                opThreekeyComponent.mKeyguardViewMediator.userActivity();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.oplus.systemui.opthreekey.tips.OpThreekeyDialogImpl$3] */
    public OpThreekeyDialogImpl(Context context, ConfigurationController configurationController, ScreenLifecycle screenLifecycle, SeedlingPluginManager seedlingPluginManager, OpThreeKeySeedling opThreeKeySeedling, WakefulnessLifecycle wakefulnessLifecycle) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mWakefulnessObserver = anonymousClass1;
        this.mKeyguardStyleServiceCallback = new KeyguardStyleServiceCallbackImpl() { // from class: com.oplus.systemui.opthreekey.tips.OpThreekeyDialogImpl.2
            public void onUiStateChanged(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState, boolean z) {
                OpThreekeyDialogImpl.this.mAODType = smoothTransitionUiState;
            }
        };
        this.mConfigChanges = new InterestingConfigChanges(-1073741308);
        this.isRightTk = "".equals(VolumeFeatureOption.INSTANCE.getVolumeThreekeyLocation());
        this.viewOutlineProvider = new AnonymousClass4();
        this.mContext = context;
        this.mConfigurationController = configurationController;
        this.mOrientationListener = new OrientationEventListener(context) { // from class: com.oplus.systemui.opthreekey.tips.OpThreekeyDialogImpl.3
            @Override // android.view.OrientationEventListener
            public final void onOrientationChanged(int i) {
                int rotation;
                OpThreekeyDialogImpl opThreekeyDialogImpl = OpThreekeyDialogImpl.this;
                Display realDisplay = DisplayManagerGlobal.getInstance().getRealDisplay(0);
                if (realDisplay == null || (rotation = realDisplay.getRotation()) == opThreekeyDialogImpl.mOrientationType) {
                    return;
                }
                opThreekeyDialogImpl.mOrientationType = rotation;
                opThreekeyDialogImpl.updateThreekeyLayout();
            }
        };
        this.mScreenLifecycle = screenLifecycle;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mSeedlingPluginManager = seedlingPluginManager;
        this.mOpThreeKeySeedling = opThreeKeySeedling;
    }

    private int getThreeKeyStateForSeedling() {
        int i = this.mThreeKeystate;
        if (i == 2) {
            boolean z = VolumeSettingsValueProxy.getVibrateWhenSilentToggleState(this.mContext) != 0;
            VolumeFeatureOption.INSTANCE.getClass();
            if (!VolumeFeatureOption.isSupportsStealthMode() || z) {
                return 1;
            }
        } else if (i != 1) {
            return 2;
        }
        return 0;
    }

    public final void dismissH(int i) {
        LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "dismissH r=" + DISMISS_REASONS[i]);
        H h = this.mHandler;
        h.removeMessages(1);
        h.removeMessages(2);
        registerOrientationListener(false);
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    public final void initDialog() {
        Dialog dialog = this.mDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mDialog.dismiss();
        }
        Dialog dialog2 = new Dialog(this.mContext);
        this.mDialog = dialog2;
        Window window = dialog2.getWindow();
        this.mWindow = window;
        window.requestFeature(1);
        this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mWindow.clearFlags(2);
        this.mWindow.addFlags(17563944);
        this.mDialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        this.mWindowLayoutParams = attributes;
        attributes.type = this.mWindowType;
        attributes.format = -3;
        attributes.setTitle("OpThreekeyDialogImpl");
        WindowManager.LayoutParams layoutParams = this.mWindowLayoutParams;
        layoutParams.gravity = 8388661;
        layoutParams.y = this.mDialogPosition;
        this.mWindow.setAttributes(layoutParams);
        this.mWindow.setSoftInputMode(48);
        this.mDialog.setContentView(2131559250);
        this.mDialogView = (ViewGroup) this.mDialog.findViewById(2131365658);
        this.mThreeKeyIcon = (ImageView) this.mDialog.findViewById(2131365656);
        this.mThreeKeyText = (TextView) this.mDialog.findViewById(2131365661);
        updateTheme(true);
        ViewGroup viewGroup = this.mDialogView;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        viewGroup.addOnAttachStateChangeListener(anonymousClass5);
    }

    public final void onConfigChanged(Configuration configuration) {
        StringBuilder sb = new StringBuilder("onConfigChanged, isDark:");
        Context context = this.mContext;
        int i = OpVolumeUtils.sOplusScreenResolution;
        sb.append(OpUtils.isGoogleDarkTheme(context));
        LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", sb.toString());
        if (this.mConfigChanges.applyNewConfig(this.mContext.getResources())) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "onConfigChanged and initThreekeyDialog");
            initDialog();
        }
        updateTheme(false);
        updateThreekeyLayout();
    }

    public final void onDensityOrFontScaleChanged() {
        dismissH(1);
        initDialog();
        updateThreekeyLayout();
    }

    public final void onScreenTurningOff() {
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    public final void registerOrientationListener(boolean z) {
        AnonymousClass3 anonymousClass3 = this.mOrientationListener;
        if (anonymousClass3.canDetectOrientation() && z) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "Can detect orientation");
            anonymousClass3.enable();
        } else {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "Cannot detect orientation");
            anonymousClass3.disable();
        }
    }

    public final void rescheduleTimeoutH() {
        H h = this.mHandler;
        h.removeMessages(2);
        h.sendMessageDelayed(h.obtainMessage(2, 2, 0), 3000L);
        LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "rescheduleTimeout 3000");
        OpThreekeyComponent opThreekeyComponent = this.mListener;
        if (opThreekeyComponent != null) {
            opThreekeyComponent.mKeyguardViewMediator.userActivity();
        }
    }

    public final boolean shouldShow() {
        if (this.mThreeKeystate == 4) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "show send state for stealth guard");
            return true;
        }
        VolumeFeatureOption volumeFeatureOption = VolumeFeatureOption.INSTANCE;
        volumeFeatureOption.getClass();
        if (VolumeFeatureOption.isSupportsStealthMode() && this.mThreeKeystate == 1) {
            return false;
        }
        if (AodData.getInstance(this.mContext).mAodIsInShow && this.mAODType == SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "show threekey tips when showing PANORAMIC AOD");
            return true;
        }
        if (OplusOSAodManager.getInstance().mAodIsShow) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "don't show threekey tips when aod is showing, mAODType:" + this.mAODType);
            return false;
        }
        if ("1".equals(volumeFeatureOption.isOplusVolumeThreeKeyLocation())) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isFoldDevice()) {
                boolean zIsOpenOrHalfOpen = DeviceStateController.getInstance().isOpenOrHalfOpen();
                StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("Major isOpenOrHalfOpen: ", ", mAODType:", zIsOpenOrHalfOpen);
                sbM.append(this.mAODType);
                LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", sbM.toString());
                return zIsOpenOrHalfOpen;
            }
        }
        return true;
    }

    @Override // com.oplus.systemui.opthreekey.observer.ThreeKeyStateObserver
    public final void stateChange(int i) {
        int iConvertThreeKeyStatus = ConvertThreeKeyStateUtil.convertThreeKeyStatus(i);
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", ListPopupWindow$.ExternalSyntheticOutline0.m(iConvertThreeKeyStatus, "onThreeKeyStatus:", ", mIsDeviceOn:"), this.mIsDeviceOn);
        H h = this.mHandler;
        h.obtainMessage(4, iConvertThreeKeyStatus, 0).sendToTarget();
        int i2 = this.mThreeKeystate;
        if (i2 == -1 || i2 == iConvertThreeKeyStatus) {
            return;
        }
        if (!this.mIsDeviceOn) {
            this.mIsThreekeyStateChangedWhenScreenOff = true;
        }
        h.obtainMessage(1, 0, 0).sendToTarget();
    }

    public final void updateTheme(boolean z) {
        Context context = this.mContext;
        int i = OpVolumeUtils.sOplusScreenResolution;
        boolean zIsGoogleDarkTheme = OpUtils.isGoogleDarkTheme(context);
        int themeAccentColor = OpUtils.getThemeAccentColor(this.mContext, false);
        Context context2 = OpUtils.sContext;
        boolean z2 = context2 != null && !OpUtils.isGoogleDarkTheme(context2) && -201206 == OpUtils.sContext.getResources().getColor(2131103141) && -16715521 == OpUtils.sContext.getResources().getColor(2131104165);
        boolean z3 = (this.mThemeMode == zIsGoogleDarkTheme && this.mAccentColor == themeAccentColor && this.mIsREDVersion == z2) ? false : true;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateTheme change:", ",force:", ",theme:", z3, z);
        sbM.append(zIsGoogleDarkTheme ? 1 : 0);
        sbM.append(",mIsREDVersion:");
        LogUtil.i(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, this.mIsREDVersion, ",isREDVersion:", z2));
        if (z3 || z) {
            if (zIsGoogleDarkTheme) {
                this.mThemeMode = 1;
            } else {
                this.mThemeMode = 2;
            }
            this.mIsREDVersion = z2;
            this.mAccentColor = themeAccentColor;
            Resources resources = this.mContext.getResources();
            if (this.mThemeMode != 1) {
                this.mThemeIconColor = this.mIsREDVersion ? resources.getColor(2131102994) : resources.getColor(2131103059);
                this.mThemeTextColor = this.mIsREDVersion ? resources.getColor(2131102994) : resources.getColor(2131103059);
                this.mThemeBgColor = this.mIsREDVersion ? resources.getColor(2131102971) : resources.getColor(2131103120);
            } else {
                this.mThemeIconColor = resources.getColor(2131103057);
                this.mThemeTextColor = resources.getColor(2131103057);
                this.mThemeBgColor = resources.getColor(2131103121);
            }
            TextView textView = this.mThreeKeyText;
            if (textView != null) {
                textView.setTextColor(this.mThemeTextColor);
                this.mThreeKeyText.setTextSize(0, OpVolumeUtils.getDimensionPixelSize(resources, 2131173667, OpVolumeUtils.getScreenResolution()));
            }
            ImageView imageView = this.mThreeKeyIcon;
            if (imageView != null) {
                imageView.setColorFilter(this.mThemeIconColor);
            }
        }
    }

    public final void updateThreekeyLayout() {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        int i;
        int i2;
        int i3;
        int dimensionPixelSize3;
        int i4;
        VolumeFeatureOption volumeFeatureOption = VolumeFeatureOption.INSTANCE;
        if (ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH.equals(volumeFeatureOption.getVolumeThreekeyLocation())) {
            if (DeviceStateController.getInstance().isFolding()) {
                LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "The Screen is on STATE_CLOSED, isRightTk = true");
                this.isRightTk = true;
            } else {
                LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "The Screen is on STATE_NOT_CLOSED, isRightTk = false");
                this.isRightTk = false;
            }
        }
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        Resources resources = context.getResources();
        int i5 = OpVolumeUtils.sOplusScreenResolution;
        OpVolumeUtils.sOplusScreenResolution = OpUtils.updateResolution();
        if (resources == null || this.mDialogView == null || this.mThreeKeyIcon == null || this.mThreeKeyText == null) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "return, reason= res:" + resources + ",mDialogView:" + this.mDialogView + ",mThreeKeyIcon:" + this.mThreeKeyIcon + ",mThreeKeyText:" + this.mThreeKeyText);
            return;
        }
        updateTheme(false);
        this.mDialogView.requestLayout();
        int i6 = this.mWindowLayoutParams.y;
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mThreeKeystate, LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", new StringBuilder(" updateThreekeyLayout "));
        int i7 = this.mThreeKeystate;
        int i8 = 2131957185;
        int i9 = 2131235806;
        if (i7 != 1) {
            if (i7 == 2) {
                boolean z = VolumeSettingsValueProxy.getVibrateWhenSilentToggleState(this.mContext) != 0;
                if (!VolumeFeatureOption.isSupportsStealthMode() || z) {
                    i9 = 2131235807;
                    i8 = 2131957227;
                }
            } else {
                if (i7 != 3) {
                    return;
                }
                i9 = 2131235805;
                i8 = 2131957184;
            }
        } else if (VolumeFeatureOption.isSupportsStealthMode()) {
            Dialog dialog = this.mDialog;
            if (dialog == null || !dialog.isShowing()) {
                return;
            }
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "cancel current dialog tips for stealth guard");
            H h = this.mHandler;
            h.sendMessageDelayed(h.obtainMessage(2, 0, 0), 0L);
            return;
        }
        int i10 = this.mOrientationType;
        if (i10 != 1) {
            if (i10 == 2) {
                LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "Surface.ROTATION_180");
                if (this.isRightTk) {
                    dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_size_subhead_material) + OpVolumeUtils.getDimensionPixelSize(resources, 2131173657, 1080);
                    i3 = 83;
                } else {
                    dimensionPixelSize = OpVolumeUtils.getDimensionPixelSize(resources, 2131173657, 1080);
                    i3 = 85;
                }
                i = i3;
            } else if (i10 != 3) {
                LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "Surface.ROTATION_0");
                if (this.isRightTk) {
                    dimensionPixelSize = ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH.equals(volumeFeatureOption.getVolumeThreekeyLocation()) ? OpVolumeUtils.getDimensionPixelSize(resources, 2131173657, 1080) : OpVolumeUtils.getDimensionPixelSize(resources, 2131173657, 1080) + resources.getDimensionPixelSize(R.dimen.text_size_subhead_material);
                    i = 53;
                } else {
                    dimensionPixelSize = OpVolumeUtils.getDimensionPixelSize(resources, 2131173657, 1080);
                    i = 51;
                }
            } else {
                LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "Surface.ROTATION_270");
                dimensionPixelSize = OpVolumeUtils.getDimensionPixelSize(resources, 2131173665, 1080);
                if (this.isRightTk) {
                    dimensionPixelSize3 = OpVolumeUtils.getDimensionPixelSize(resources, 2131173658, 1080);
                    i4 = 85;
                } else {
                    dimensionPixelSize3 = OpVolumeUtils.getDimensionPixelSize(resources, 2131173658, 1080);
                    dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_size_subhead_material) + dimensionPixelSize;
                    i4 = 53;
                }
                i2 = dimensionPixelSize3;
                i = i4;
            }
            i2 = 0;
        } else {
            LogUtil.d(LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", "Surface.ROTATION_90");
            dimensionPixelSize = OpVolumeUtils.getDimensionPixelSize(resources, 2131173665, 1080);
            if (this.isRightTk) {
                dimensionPixelSize2 = OpVolumeUtils.getDimensionPixelSize(resources, 2131173658, 1080);
                dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_size_subhead_material) + dimensionPixelSize;
                i = 51;
            } else {
                dimensionPixelSize2 = OpVolumeUtils.getDimensionPixelSize(resources, 2131173658, 1080);
                i = 83;
            }
            i2 = dimensionPixelSize2;
        }
        if (this.mThreeKeystate != -1) {
            ImageView imageView = this.mThreeKeyIcon;
            if (imageView != null) {
                imageView.setImageResource(i9);
            }
            if (this.mThreeKeyText != null) {
                String string = resources.getString(i8);
                this.mThreeKeyText.setTypeface(Typeface.DEFAULT);
                this.mThreeKeyText.setText(string);
            }
            this.mDialogPosition = dimensionPixelSize;
        }
        int dimensionPixelSize4 = resources.getDimensionPixelSize(2131170885);
        WindowManager.LayoutParams layoutParams = this.mWindowLayoutParams;
        layoutParams.gravity = i;
        layoutParams.y = dimensionPixelSize - dimensionPixelSize4;
        layoutParams.x = i2 - dimensionPixelSize4;
        this.mWindow.setAttributes(layoutParams);
        rescheduleTimeoutH();
        StringBuilder sb = new StringBuilder("updateThreekeyLayout mThreeKeystate:");
        sb.append(this.mThreeKeystate);
        sb.append(" height ");
        sb.append(this.mWindowLayoutParams.height);
        sb.append(" width ");
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mWindowLayoutParams.width, LogUtil.TAG_VOLUME, "OpThreekeyDialogImpl", sb);
    }
}
