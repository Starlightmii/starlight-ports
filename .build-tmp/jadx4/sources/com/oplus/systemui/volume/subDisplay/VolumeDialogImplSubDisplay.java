package com.oplus.systemui.volume.subDisplay;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.os.Debug;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.VibrationEffect;
import android.provider.Settings;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OplusBaseLayoutParamsAddon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.ViewRootImpl;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.WindowOnBackInvokedDispatcher;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.volume.Events;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.coui.appcompat.roundRect.COUIRoundDrawable;
import com.coui.appcompat.slideview.COUISlideDeleteAnimation$;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.utils.OplusOSAodManager;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStateListener;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStateObserver;
import com.oplus.systemui.opthreekey.utils.OpVolumeUtils;
import com.oplus.systemui.qs.SubDisplayQSController;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.subdisplay.dialog.DialogContentUpdateToSecondaryHomeController;
import com.oplus.systemui.volume.VolumeSeekBar;
import com.oplus.systemui.volume.ability.VibrateAbility;
import com.oplus.systemui.volume.repository.RingModeRepository;
import com.oplus.systemui.volume.utils.OplusVolumeExKt;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.GlobalActionsUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeDialogImplSubDisplay implements ConfigurationController.ConfigurationListener, View.OnClickListener, ThreeKeyStateObserver {
    public static final int mRingerModeMinLevel;
    public VolumeDialogImplSubDisplay$.ExternalSyntheticLambda1 mA11yServicesStateChangeListener;
    public AccessibilityManagerWrapper mAccessibilityMgr;
    public int mActiveStream;
    public AudioManager mAudioManager;
    public boolean mAutomute;
    public ColorDrawable mBackgroundColorDrawable;
    public VolumeDialogImplSubDisplay$.ExternalSyntheticLambda3 mBlurEnabledListener;
    public int mBlurRadius;
    public ConfigurationController mConfigurationController;
    public Context mContext;
    public VolumeDialogController mController;
    public 6 mControllerCallbackH;
    public boolean mCrossWindowBlurEnabled;
    public DialogContentUpdateToSecondaryHomeController mDialogContentUpdateToSecondaryHomeController;
    public VolumeDialogViewSubDisplay mDialogView;
    public boolean mDismissAnimPlaying;
    public DisplayChangeListener mDisplayChangeListener;
    public DisplayManager mDisplayManager;
    public H mHandler;
    public View mHearing;
    public KeyguardManager mKeyguard;
    public OplusSubDisplayUtils mOplusSubDisplayUtils;
    public OplusVolumeControllerEx mOplusVolumeController;
    public WindowManager.LayoutParams mParams;
    public PowerManager mPowerManager;
    public int mPrevActiveStream;
    public Context mResourceContext;
    public List mRows;
    public Object mSafetyWarningLock;
    public boolean mShowA11yStream;
    public boolean mShowAnimPlaying;
    public boolean mShowing;
    public boolean mSilentMode;
    public VolumeDialogController.State mState;
    public VolumeDialogImplSubDisplay$.ExternalSyntheticLambda2 mStateChangedListener;
    public int mSubDisplayRotation;
    public SysUiState mSysUiState;
    public int mTempImportantStrem;
    public int mThemeBgColor;
    public int mThemeIconColor;
    public int mThemeTextColor;
    public BackgroundBlurDrawable mThreeKeyBlurDrawable;
    public Dialog mThreeKeyDialog;
    public ViewGroup mThreeKeyDialogView;
    public ImageView mThreeKeyIcon;
    public LayerDrawable mThreeKeyLayerDrawable;
    public TextView mThreeKeyText;
    public Window mThreeKeyWindow;
    public int mThreeKeystate;
    public long mUpdateSliderTime;
    public ValueAnimator mValueAnimator;
    public LinearLayout mVerticalRowsContainer;
    public VibrateAbility mVibrateAbility;
    public VolumeRowAnimSubDisplay mVolumeRowAnimSubDisplay;
    public Context mWindowContext;
    public WindowManager.LayoutParams mWindowLayoutParams;
    public AnonymousClass1 viewOutlineProvider;

    /* JADX INFO: renamed from: com.oplus.systemui.volume.subDisplay.VolumeDialogImplSubDisplay$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends ViewOutlineProvider {
        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), GlobalActionsUtils.dp2px(view.getContext(), 2131170886));
        }
    }

    /* JADX INFO: renamed from: -$$Nest$minRangeOfView, reason: not valid java name */
    public static boolean m484$$Nest$minRangeOfView(VolumeDialogImplSubDisplay volumeDialogImplSubDisplay, VolumeSeekBarSubDisplay volumeSeekBarSubDisplay, MotionEvent motionEvent) {
        if (volumeSeekBarSubDisplay != null) {
            int[] iArr = new int[2];
            volumeSeekBarSubDisplay.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            if (motionEvent.getRawX() >= i && motionEvent.getRawX() <= volumeSeekBarSubDisplay.getHeight() + i && motionEvent.getRawY() <= i2 && motionEvent.getRawY() >= i2 - volumeSeekBarSubDisplay.getWidth()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: -$$Nest$mshowH, reason: not valid java name */
    public static void m485$$Nest$mshowH(VolumeDialogImplSubDisplay volumeDialogImplSubDisplay, int i) {
        try {
            volumeDialogImplSubDisplay.mSysUiState.setFlag(32768L, true).commitUpdate(0);
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "showH setFlag SYSUI_STATE_DIALOG_SHOWING true");
        } catch (IllegalArgumentException e) {
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "showH setFlag IllegalArgumentException: " + e);
        } catch (Exception e2) {
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "showH setFlag Exception: " + e2);
        }
        if (!volumeDialogImplSubDisplay.shouldShow$1() || volumeDialogImplSubDisplay.mShowAnimPlaying) {
            return;
        }
        volumeDialogImplSubDisplay.mHandler.obtainMessage(8, 0, 0).sendToTarget();
        VolumeRow activeRow = volumeDialogImplSubDisplay.getActiveRow();
        StringBuilder sb = new StringBuilder("showH r=");
        String[] strArr = Events.SHOW_REASONS;
        sb.append(i < strArr.length ? strArr[i] : strArr[0]);
        sb.append(", activeRow.stream: ");
        sb.append(activeRow.stream);
        sb.append(", showing: ");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", sb, volumeDialogImplSubDisplay.mShowing);
        if (volumeDialogImplSubDisplay.mOplusVolumeController.getUserSwitch()) {
            volumeDialogImplSubDisplay.mController.getState();
            volumeDialogImplSubDisplay.mOplusVolumeController.setUserSwitch(false);
        }
        volumeDialogImplSubDisplay.mHandler.removeMessages(1);
        volumeDialogImplSubDisplay.rescheduleTimeoutH();
        if (volumeDialogImplSubDisplay.mShowing) {
            if (activeRow.stream != volumeDialogImplSubDisplay.mActiveStream || volumeDialogImplSubDisplay.mShowA11yStream) {
                return;
            }
            VolumeDialogController.StreamState streamState = activeRow.ss;
            int i2 = streamState.level;
            if (i2 == streamState.levelMin || i2 == streamState.levelMax) {
                boolean z = i2 == streamState.levelMax;
                volumeDialogImplSubDisplay.mVolumeRowAnimSubDisplay.startTouchEffect(true, z, z, false);
            } else {
                volumeDialogImplSubDisplay.mVolumeRowAnimSubDisplay.startTouchEffect(false, i2 > activeRow.preLevel, false, false);
            }
            activeRow.preLevel = activeRow.ss.level;
            return;
        }
        activeRow.preLevel = activeRow.ss.level;
        volumeDialogImplSubDisplay.mTempImportantStrem = -1;
        boolean z2 = volumeDialogImplSubDisplay.mCrossWindowBlurEnabled;
        ColorDrawable colorDrawable = volumeDialogImplSubDisplay.mBackgroundColorDrawable;
        Context context = volumeDialogImplSubDisplay.mWindowContext;
        if (z2) {
            colorDrawable.setColor(context.getColor(2131104160));
        } else {
            colorDrawable.setColor(context.getColor(2131104161));
        }
        volumeDialogImplSubDisplay.updateRowsH(activeRow);
        if (volumeDialogImplSubDisplay.mWindowContext.getUserId() != OsBinderCacheUtils.getCurrentUserId()) {
            volumeDialogImplSubDisplay.mWindowContext = volumeDialogImplSubDisplay.mContext.createDisplayContext(volumeDialogImplSubDisplay.getDisplay()).createWindowContext(2038, null).createContextAsUser(new UserHandle(OsBinderCacheUtils.getCurrentUserId()), 1);
            volumeDialogImplSubDisplay.removeDialogViewFromWindow();
            ((WindowManager) volumeDialogImplSubDisplay.mWindowContext.getSystemService(WindowManager.class)).addView(volumeDialogImplSubDisplay.mDialogView, volumeDialogImplSubDisplay.mParams);
        }
        volumeDialogImplSubDisplay.mShowing = true;
        volumeDialogImplSubDisplay.mDialogView.setVisibility(0);
        if (volumeDialogImplSubDisplay.mVerticalRowsContainer.getVisibility() != 0) {
            volumeDialogImplSubDisplay.mVerticalRowsContainer.setVisibility(0);
        }
        volumeDialogImplSubDisplay.mDialogView.requestFocus();
        volumeDialogImplSubDisplay.mShowAnimPlaying = true;
        volumeDialogImplSubDisplay.mDismissAnimPlaying = false;
        volumeDialogImplSubDisplay.mValueAnimator.cancel();
        volumeDialogImplSubDisplay.mValueAnimator.start();
        volumeDialogImplSubDisplay.mVolumeRowAnimSubDisplay.startEnterAnim();
        Events.writeEvent(0, new Object[]{Integer.valueOf(i), Boolean.valueOf(volumeDialogImplSubDisplay.mKeyguard.isKeyguardLocked())});
        volumeDialogImplSubDisplay.mController.notifyVisible(true);
        volumeDialogImplSubDisplay.mController.getCaptionsComponentState(false);
        SubDisplayQSController.INSTANCE.setQsWindowDisable(volumeDialogImplSubDisplay.mContext, true, SeparateQSStatisicUtil.CLICK_QS_PANEL_TYPE_VOLUME);
    }

    static {
        FeatureOption.INSTANCE.getClass();
        mRingerModeMinLevel = FeatureOption.isOplusAlertSlider() ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VolumeRow getActiveRow() {
        for (VolumeRow volumeRow : this.mRows) {
            if (volumeRow.stream == this.mActiveStream) {
                return volumeRow;
            }
        }
        for (VolumeRow volumeRow2 : this.mRows) {
            if (volumeRow2.stream == 3) {
                return volumeRow2;
            }
        }
        return (VolumeRow) ((ArrayList) this.mRows).get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Display getDisplay() {
        return this.mDisplayManager.getDisplay(this.mOplusSubDisplayUtils.getSecondaryDisplayId());
    }

    public static int getImpliedLevel(VolumeSeekBar volumeSeekBar, int i) {
        int max = volumeSeekBar.getMax();
        int i2 = max / 100;
        int i3 = i2 - 1;
        if (i == 0) {
            return 0;
        }
        return i == max ? i2 : ((int) ((i / max) * i3)) + 1;
    }

    private void setSystemGestureExclusion(View view) {
        VolumeDialogImplSubDisplay$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda4(2);
        externalSyntheticLambda4.f$0 = view;
        VarHandle.storeStoreFence();
        view.post(externalSyntheticLambda4);
    }

    public final void addRow$1(int i, int i2, int i3) {
        VolumeRow volumeRow = new VolumeRow();
        volumeRow.requestedLevel = -1;
        volumeRow.jsonRes = "";
        volumeRow.lastAudibleLevel = 1;
        volumeRow.lastLevel = 0;
        volumeRow.preLevel = 0;
        volumeRow.stream = i;
        volumeRow.iconRes = i2;
        volumeRow.iconMuteRes = i3;
        volumeRow.lastIconRes = i2;
        View viewInflate = LayoutInflater.from(this.mContext).inflate(2131558789, (ViewGroup) null);
        volumeRow.view = viewInflate;
        viewInflate.setId(volumeRow.stream);
        volumeRow.view.setTag(volumeRow);
        VolumeSeekBarSubDisplay volumeSeekBarSubDisplayFindViewById = volumeRow.view.findViewById(2131364971);
        volumeRow.slider = volumeSeekBarSubDisplayFindViewById;
        VolumeSeekBarChangeListener volumeSeekBarChangeListener = new VolumeSeekBarChangeListener();
        volumeSeekBarChangeListener.this$0 = this;
        volumeSeekBarChangeListener.mAdjustCount = 0L;
        volumeSeekBarChangeListener.mRecordProgressWhenStartTouch = -1;
        volumeSeekBarChangeListener.mRow = volumeRow;
        VarHandle.storeStoreFence();
        volumeSeekBarSubDisplayFindViewById.setOnSeekBarChangeListener(volumeSeekBarChangeListener);
        volumeRow.anim = null;
        EffectiveAnimationView effectiveAnimationViewFindViewById = volumeRow.view.findViewById(2131366037);
        volumeRow.icon = effectiveAnimationViewFindViewById;
        effectiveAnimationViewFindViewById.setFailureListener(new 5());
        View viewFindViewById = volumeRow.view.findViewById(2131366038);
        volumeRow.iconWrapper = viewFindViewById;
        viewFindViewById.setEnabled(false);
        Drawable drawable = this.mWindowContext.getDrawable(i2);
        if (drawable != null) {
            drawable.setTint(-16777216);
            volumeRow.icon.setImageDrawable(drawable);
        }
        if (volumeRow.stream != 10) {
            VolumeSeekBarSubDisplay volumeSeekBarSubDisplay = volumeRow.slider;
            VolumeDialogImplSubDisplay$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda9();
            externalSyntheticLambda9.f$0 = this;
            externalSyntheticLambda9.f$1 = volumeRow;
            externalSyntheticLambda9.f$2 = i;
            VarHandle.storeStoreFence();
            volumeSeekBarSubDisplay.setOnClickListener(externalSyntheticLambda9);
        } else {
            volumeRow.icon.setImportantForAccessibility(2);
        }
        volumeRow.icon.setScaleType(ListPopupWindow$.ExternalSyntheticOutline0.m(this.mContext) == 1 ? ImageView.ScaleType.FIT_END : ImageView.ScaleType.FIT_START);
        volumeRow.lastLevel = 0;
        volumeRow.jsonRes = "";
        this.mRows.add(volumeRow);
    }

    public final void dismissH$1(int i) {
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "mDialog.dismiss() reason: ", " from: ");
        sbM.append(Debug.getCaller());
        sbM.append(" mShowing = ");
        sbM.append(this.mShowing);
        sbM.append(" , ");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", sbM, this.mDismissAnimPlaying);
        try {
            this.mSysUiState.setFlag(32768L, false).commitUpdate(0);
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "showH setFlag SYSUI_STATE_DIALOG_SHOWING false");
        } catch (IllegalArgumentException e) {
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "dismissH setFlag IllegalArgumentException: " + e);
        } catch (Exception e2) {
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", " dismissH setFlag Exception: " + e2);
        }
        if (this.mDismissAnimPlaying || !this.mShowing) {
            return;
        }
        if (this.mHearing.getVisibility() == 0) {
            this.mHearing.setVisibility(8);
        }
        this.mShowing = false;
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(1);
        this.mShowAnimPlaying = false;
        this.mDismissAnimPlaying = true;
        this.mValueAnimator.cancel();
        this.mValueAnimator.start();
        this.mVolumeRowAnimSubDisplay.startExitAnim();
        Events.writeEvent(1, new Object[]{Integer.valueOf(i)});
        SubDisplayQSController.INSTANCE.setQsWindowDisable(this.mContext, false, SeparateQSStatisicUtil.CLICK_QS_PANEL_TYPE_VOLUME);
        DialogContentUpdateToSecondaryHomeController dialogContentUpdateToSecondaryHomeController = this.mDialogContentUpdateToSecondaryHomeController;
        if (dialogContentUpdateToSecondaryHomeController != null) {
            dialogContentUpdateToSecondaryHomeController.updateDialogContent("volume_dialog", false);
        }
        AccessibilityManagerWrapper accessibilityManagerWrapper = this.mAccessibilityMgr;
        if (accessibilityManagerWrapper != null) {
            accessibilityManagerWrapper.mAccessibilityManager.removeAccessibilityServicesStateChangeListener(this.mA11yServicesStateChangeListener);
        }
    }

    public final VolumeRow findRow(int i) {
        for (VolumeRow volumeRow : this.mRows) {
            if (volumeRow.stream == i) {
                return volumeRow;
            }
        }
        return null;
    }

    public final Context getSubdisplayContext(Context context) {
        return context.createDisplayContext(getDisplay());
    }

    public final void init() {
        LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "init  VolumeDialogImplSubDisplay : ");
        initDialog$1();
        this.mController.addCallback(this.mControllerCallbackH, this.mHandler);
        this.mController.getState();
        this.mConfigurationController.addCallback(this);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f);
        this.mValueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setInterpolator(this.mVolumeRowAnimSubDisplay.getEaseInterpolator());
        this.mValueAnimator.setDuration(550L);
        ValueAnimator valueAnimator = this.mValueAnimator;
        VolumeDialogImplSubDisplay$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda5();
        externalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator.addUpdateListener(externalSyntheticLambda5);
        DeviceStateController.getInstance().addCallback(this.mStateChangedListener);
        if ("1".equals(VolumeFeatureOption.INSTANCE.getVolumeThreekeyLocation())) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isOplusAlertSlider()) {
                this.mThreeKeystate = Settings.Global.getInt(this.mContext.getContentResolver(), RingModeRepository.ALERT_SLIDER_MODE, -1);
                ThreeKeyStateListener.getInstance().addObserver(this.mContext, this);
                DisplayChangeListener displayChangeListener = new DisplayChangeListener();
                displayChangeListener.this$0 = this;
                VarHandle.storeStoreFence();
                this.mDisplayChangeListener = displayChangeListener;
                initThreeKeyDialog();
                this.mSubDisplayRotation = getDisplay().getRotation();
                ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mSubDisplayRotation, LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", new StringBuilder("init mSubDisplayRotation = "));
                this.mDisplayManager.registerDisplayListener(this.mDisplayChangeListener, this.mHandler, 4L);
            }
        }
        AccessibilityManagerWrapper accessibilityManagerWrapper = this.mAccessibilityMgr;
        if (accessibilityManagerWrapper != null) {
            accessibilityManagerWrapper.mAccessibilityManager.addAccessibilityServicesStateChangeListener(this.mA11yServicesStateChangeListener);
            H h = this.mHandler;
            VolumeDialogImplSubDisplay$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda4(0);
            externalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            h.post(externalSyntheticLambda4);
        }
    }

    public final void initDialog$1() {
        removeDialogViewFromWindow();
        this.mShowing = false;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mParams = layoutParams;
        layoutParams.screenOrientation = 2;
        layoutParams.flags = (layoutParams.flags & (-65539)) | 17696004;
        layoutParams.format = -3;
        layoutParams.gravity = 17;
        layoutParams.setBlurBehindRadius(this.mBlurRadius);
        WindowManager.LayoutParams layoutParams2 = this.mParams;
        layoutParams2.height = -1;
        layoutParams2.width = -1;
        layoutParams2.type = 2020;
        layoutParams2.layoutInDisplayCutoutMode = 1;
        OplusBaseLayoutParamsAddon.setOplusFlags(layoutParams2, 8);
        Context contextCreateContextAsUser = this.mContext.createDisplayContext(getDisplay()).createWindowContext(2038, null).createContextAsUser(new UserHandle(OsBinderCacheUtils.getCurrentUserId()), 1);
        this.mWindowContext = contextCreateContextAsUser;
        VolumeDialogViewSubDisplay volumeDialogViewSubDisplayInflate = LayoutInflater.from(contextCreateContextAsUser).inflate(2131559420, (ViewGroup) null);
        this.mDialogView = volumeDialogViewSubDisplayInflate;
        volumeDialogViewSubDisplayInflate.setVisibility(8);
        VolumeDialogViewSubDisplay volumeDialogViewSubDisplay = this.mDialogView;
        VolumeDialogImplSubDisplay$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda7();
        externalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        volumeDialogViewSubDisplay.setOnCancelListener(externalSyntheticLambda7);
        this.mHearing = this.mDialogView.findViewById(2131363347);
        LinearLayout linearLayout = (LinearLayout) this.mDialogView.findViewById(2131365959);
        this.mVerticalRowsContainer = linearLayout;
        2 r4 = new 2();
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        linearLayout.setOnTouchListener(r4);
        this.mVolumeRowAnimSubDisplay = new VolumeRowAnimSubDisplay(this.mVerticalRowsContainer);
        this.mDialogView.findViewById(2131362374).setOnClickListener(this);
        this.mDialogView.setClickable(true);
        this.mDialogView.setOnClickListener(this);
        ((TextView) this.mDialogView.findViewById(2131365797)).setText(this.mWindowContext.getText(2131955951));
        ((TextView) this.mDialogView.findViewById(2131365798)).setText(this.mWindowContext.getText(2131955950));
        ((TextView) this.mDialogView.findViewById(2131362374)).setText(this.mWindowContext.getText(2131955959));
        ((WindowManager) this.mWindowContext.getSystemService(WindowManager.class)).addView(this.mDialogView, this.mParams);
        ViewRootImpl parent = this.mDialogView.getRootView().getParent();
        if (parent != null) {
            VolumeDialogViewSubDisplay volumeDialogViewSubDisplay2 = this.mDialogView;
            WindowOnBackInvokedDispatcher onBackInvokedDispatcher = parent.getOnBackInvokedDispatcher();
            3 r6 = new 3();
            r6.this$0 = this;
            VarHandle.storeStoreFence();
            OplusNavBarUtils.invokedCallback(volumeDialogViewSubDisplay2, onBackInvokedDispatcher, r6);
        }
        Drawable cOUIRoundDrawable = new COUIRoundDrawable();
        cOUIRoundDrawable.setFillColor(this.mWindowContext.getColor(2131100930));
        cOUIRoundDrawable.setRadius(GlobalActionsUtils.dp2px(this.mContext, 22.0f));
        this.mDialogView.findViewById(2131362374).setBackground(cOUIRoundDrawable);
        ColorDrawable colorDrawable = new ColorDrawable(this.mWindowContext.getColor(2131104160));
        this.mBackgroundColorDrawable = colorDrawable;
        this.mDialogView.setBackground(colorDrawable);
        View viewFindViewById = this.mDialogView.findViewById(2131365965);
        VolumeDialogImplSubDisplay$.ExternalSyntheticLambda8 externalSyntheticLambda8 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda8();
        externalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        viewFindViewById.setOnTouchListener(externalSyntheticLambda8);
        if (this.mRows.isEmpty()) {
            if (!AudioSystem.isSingleVolume(this.mContext)) {
                addRow$1(10, 2131237390, 2131237390);
            }
            addRow$1(3, 2131237409, 2131237421);
            LogUtil.i(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "isOplusAssistantVolumeSupport: " + FeatureOption.isOplusAssistantVolumeSupport());
            if (FeatureOption.isOplusAssistantVolumeSupport()) {
                addRow$1(11, 2131237397, 2131237398);
            }
            if (!AudioSystem.isSingleVolume(this.mContext)) {
                addRow$1(2, 2131237434, 2131237437);
                addRow$1(5, 2131237427, 2131237429);
                addRow$1(4, 2131237392, 2131237392);
                addRow$1(0, 2131237450, 2131237450);
                addRow$1(6, 2131237403, 2131237403);
                addRow$1(1, 2131237434, 2131237437);
            }
        }
        ((WindowManager) this.mWindowContext.getSystemService(WindowManager.class)).addCrossWindowBlurEnabledListener(this.mBlurEnabledListener);
        updateRowsH(getActiveRow());
    }

    public final void initThreeKeyDialog() {
        LogUtil.i(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "initThreeKeyDialog");
        Dialog dialog = new Dialog(getSubdisplayContext(this.mContext));
        this.mThreeKeyDialog = dialog;
        Window window = dialog.getWindow();
        this.mThreeKeyWindow = window;
        window.requestFeature(1);
        this.mThreeKeyWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mThreeKeyWindow.clearFlags(2);
        this.mThreeKeyWindow.addFlags(17564008);
        this.mThreeKeyDialog.setCanceledOnTouchOutside(false);
        this.mThreeKeyWindow.setWindowAnimations(-1);
        WindowManager.LayoutParams attributes = this.mThreeKeyWindow.getAttributes();
        this.mWindowLayoutParams = attributes;
        attributes.screenOrientation = 2;
        attributes.type = 2020;
        attributes.format = -3;
        OplusBaseLayoutParamsAddon.setOplusFlags(attributes, 8);
        updateThreeKeyDialogLayoutY();
        this.mThreeKeyWindow.setAttributes(this.mWindowLayoutParams);
        this.mThreeKeyWindow.setSoftInputMode(48);
        this.mThreeKeyDialog.setContentView(2131559250);
        this.mThreeKeyDialogView = (ViewGroup) this.mThreeKeyDialog.findViewById(2131365658);
        updateThreeKeyDialogLayoutY();
        this.mThreeKeyIcon = (ImageView) this.mThreeKeyDialog.findViewById(2131365656);
        this.mThreeKeyText = (TextView) this.mThreeKeyDialog.findViewById(2131365661);
        Resources resources = this.mContext.getResources();
        this.mThemeTextColor = resources.getColor(2131103059);
        this.mThemeBgColor = resources.getColor(2131102973);
        this.mThemeIconColor = -16777216;
        TextView textView = this.mThreeKeyText;
        if (textView != null) {
            textView.setTextColor(this.mThemeTextColor);
        }
        ImageView imageView = this.mThreeKeyIcon;
        if (imageView != null) {
            imageView.setColorFilter(this.mThemeIconColor);
        }
        ViewGroup viewGroup = this.mThreeKeyDialogView;
        4 r1 = new 4();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        viewGroup.addOnAttachStateChangeListener(r1);
    }

    public final boolean isStreamMuted(VolumeDialogController.StreamState streamState) {
        return (this.mAutomute && streamState.level == 0) || streamState.muted;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view.getId() != 2131362374) {
            this.mHandler.obtainMessage(2, 1, 0).sendToTarget();
        } else {
            showSafetyWaring(false);
            this.mAudioManager.disableSafeMediaVolume();
        }
    }

    public final void onStateChangedH(VolumeDialogController.State state, int i) {
        int i2;
        int i3;
        if (state == null) {
            return;
        }
        VolumeDialogController.State state2 = this.mState;
        if (state2 != null && (i2 = state2.ringerModeInternal) != (i3 = state.ringerModeInternal) && i3 == 1 && i2 != -1) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isLinearVibratorSupport()) {
                this.mVibrateAbility.vibrateByLMVibrator(2, true);
            } else {
                this.mController.vibrate(VibrationEffect.get(5));
            }
        }
        this.mState = state;
        int i4 = this.mActiveStream;
        int i5 = state.activeStream;
        if (i4 != i5) {
            this.mPrevActiveStream = i4;
            this.mActiveStream = i5;
            updateRowsH(getActiveRow());
            rescheduleTimeoutH();
        }
        for (VolumeRow volumeRow : this.mRows) {
            if (volumeRow.stream == i) {
                updateVolumeRowH(volumeRow);
            }
        }
    }

    public final void onUiModeChanged() {
        this.mWindowContext.getTheme().applyStyle(this.mWindowContext.getThemeResId(), true);
    }

    public final void removeDialogViewFromWindow() {
        if (this.mDialogView != null) {
            try {
                ((WindowManager) this.mWindowContext.getSystemService(WindowManager.class)).removeView(this.mDialogView);
            } catch (Exception e) {
                LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "removeDialogViewFromWindow: " + e);
            }
        }
    }

    public final void rescheduleTimeoutH() {
        this.mHandler.removeMessages(2);
        int recommendedTimeoutMillis = this.mAccessibilityMgr.mAccessibilityManager.getRecommendedTimeoutMillis(1950, 4);
        H h = this.mHandler;
        h.sendMessageDelayed(h.obtainMessage(2, 3, 0), recommendedTimeoutMillis);
        this.mController.userActivity();
    }

    public final boolean shouldShow$1() {
        if (OplusOSAodManager.getInstance().mAodIsShow && !AodData.getInstance(this.mContext).mAodOptionsMgr.isCurrentFullScreen()) {
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "don't show volume dialog when aod is showing");
            return false;
        }
        boolean zIsOpenOrHalfOpen = DeviceStateController.getInstance().isOpenOrHalfOpen();
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("isOpenOrHalfOpen: ", LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", zIsOpenOrHalfOpen);
        return !zIsOpenOrHalfOpen;
    }

    public final void showSafetyWaring(boolean z) {
        if (!this.mShowing) {
            this.mShowing = true;
            this.mDialogView.setVisibility(0);
            if (this.mCrossWindowBlurEnabled) {
                this.mParams.setBlurBehindRadius(this.mBlurRadius);
                ((WindowManager) this.mWindowContext.getSystemService(WindowManager.class)).updateViewLayout(this.mDialogView, this.mParams);
            }
        }
        this.mValueAnimator.cancel();
        this.mShowAnimPlaying = false;
        this.mDismissAnimPlaying = false;
        if (z) {
            Drawable background = this.mDialogView.findViewById(2131362374).getBackground();
            if (background != null) {
                background.setTint(this.mWindowContext.getColor(2131100930));
            }
            this.mVerticalRowsContainer.clearAnimation();
            this.mVerticalRowsContainer.setVisibility(8);
            this.mHearing.setVisibility(0);
            this.mHearing.requestFocus();
            this.mBackgroundColorDrawable.setColor(this.mWindowContext.getColor(2131104162));
        } else {
            this.mHearing.clearAnimation();
            this.mHearing.setVisibility(8);
            this.mVerticalRowsContainer.setVisibility(0);
            this.mVerticalRowsContainer.requestFocus();
            this.mVolumeRowAnimSubDisplay.clear();
            this.mVolumeRowAnimSubDisplay.setEnter(true);
            boolean z2 = this.mCrossWindowBlurEnabled;
            ColorDrawable colorDrawable = this.mBackgroundColorDrawable;
            Context context = this.mWindowContext;
            if (z2) {
                colorDrawable.setColor(context.getColor(2131104160));
            } else {
                colorDrawable.setColor(context.getColor(2131104161));
            }
            rescheduleTimeoutH();
        }
        this.mDialogView.setAlpha(0.0f);
        ViewPropertyAnimator viewPropertyAnimatorAnimate = this.mDialogView.animate();
        viewPropertyAnimatorAnimate.cancel();
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(550L).setInterpolator(this.mVolumeRowAnimSubDisplay.getEaseInterpolator()).start();
    }

    @Override // com.oplus.systemui.opthreekey.observer.ThreeKeyStateObserver
    public final void stateChange(int i) {
        LogUtil.i(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "current threeKeyStatus : " + this.mThreeKeystate);
        if (OplusOSAodManager.getInstance().mAodIsShow && !AodData.getInstance(this.mContext).mAodOptionsMgr.isCurrentFullScreen()) {
            LogUtil.i(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "Black Screen AOD state");
            return;
        }
        if (AodData.getInstance(this.mContext).mAodOptionsMgr.isCurrentFullScreen()) {
            LogUtil.i(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "Full Screen AOD state");
            if (!this.mPowerManager.isInteractive()) {
                turnOnScreenAndVibrate(false);
            }
        }
        this.mThreeKeystate = i;
        this.mHandler.removeMessages(9);
        this.mHandler.removeMessages(8);
        this.mHandler.obtainMessage(9, 0).sendToTarget();
    }

    public final void turnOnScreenAndVibrate(boolean z) {
        PowerManager powerManager = this.mPowerManager;
        if (powerManager != null) {
            powerManager.wakeUp(SystemClock.uptimeMillis(), 2, "safetyVolume");
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "turn on screen for safety warning ");
        }
        if (z) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isLinearVibratorSupport()) {
                this.mVibrateAbility.vibrateByLMVibrator(2, true);
            } else {
                this.mController.vibrate(VibrationEffect.get(5));
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0030  */
    public final void updateRowsH(VolumeRow volumeRow) {
        boolean zIsMusicActive = this.mAudioManager.isMusicActive();
        for (VolumeRow volumeRow2 : this.mRows) {
            int i = volumeRow2.stream;
            int i2 = volumeRow.stream;
            boolean z = false;
            boolean z2 = i == i2;
            if (i == 10) {
                z = this.mShowA11yStream;
            } else if (i2 == 10 && i == this.mPrevActiveStream) {
                z = true;
            } else {
                if (z2 && i != 3 && i != 2 && i != 5 && i != 4 && i != 11) {
                    this.mTempImportantStrem = i;
                }
                if (z2 || (zIsMusicActive && ((i2 == 0 && i == 3) || (this.mTempImportantStrem == 0 && i2 == 3 && i == 0)))) {
                    z = true;
                }
            }
            if (z) {
                ViewGroup viewGroup = (ViewGroup) volumeRow2.view.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(volumeRow2.view);
                }
                this.mVerticalRowsContainer.addView(volumeRow2.view);
            }
            OplusVolumeExKt.setVisOrGone(volumeRow2.view, z);
        }
    }

    public final void updateThreeKeyDialogLayoutY() {
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mSubDisplayRotation, LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", new StringBuilder("updateThreeKeyDialogLayoutY--mSubDisplayRotation="));
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(2131170885);
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(2131170884);
        if (this.mThreeKeyDialogView != null) {
            int i = this.mSubDisplayRotation;
            if (i == 0) {
                WindowManager.LayoutParams layoutParams = this.mWindowLayoutParams;
                layoutParams.gravity = 8388659;
                layoutParams.y = OpVolumeUtils.getDimensionPixelSize(this.mContext.getResources(), 2131173659, 1080) - dimensionPixelSize;
                this.mThreeKeyDialogView.setPivotX(0.0f);
                this.mThreeKeyDialogView.setPivotY(dimensionPixelSize2 / 2.0f);
                this.mThreeKeyDialogView.setScaleX(0.8f);
                this.mThreeKeyDialogView.setScaleY(0.8f);
                return;
            }
            if (i == 2) {
                WindowManager.LayoutParams layoutParams2 = this.mWindowLayoutParams;
                layoutParams2.gravity = 8388661;
                layoutParams2.y = OpVolumeUtils.getDimensionPixelSize(this.mContext.getResources(), 2131173660, 1080) - dimensionPixelSize;
                ViewGroup viewGroup = this.mThreeKeyDialogView;
                viewGroup.setPivotX(viewGroup.getWidth());
                this.mThreeKeyDialogView.setPivotY(dimensionPixelSize2 / 2.0f);
                this.mThreeKeyDialogView.setScaleX(0.8f);
                this.mThreeKeyDialogView.setScaleY(0.8f);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:237:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:255:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:322:0x0483  */
    /* JADX WARN: Code duplicated, block: B:347:0x04d3  */
    /* JADX WARN: Code duplicated, block: B:468:0x06a0  */
    public final void updateVolumeRowH(VolumeRow volumeRow) {
        VolumeDialogController.StreamState streamState;
        boolean z;
        String str;
        String str2;
        boolean z2;
        float f;
        int i;
        int i2;
        int i3;
        ObjectAnimator objectAnimator;
        float f2;
        boolean z3;
        float f3;
        int i4;
        int i5;
        boolean z4;
        VolumeDialogController.State state = this.mState;
        if (state == null || (streamState = (VolumeDialogController.StreamState) state.states.get(volumeRow.stream)) == null) {
            return;
        }
        volumeRow.ss = streamState;
        if (streamState.level == volumeRow.requestedLevel) {
            volumeRow.requestedLevel = -1;
        }
        int i6 = volumeRow.stream;
        boolean z5 = i6 == 11;
        boolean z6 = i6 == 2;
        boolean z7 = i6 == 1;
        boolean z8 = i6 == 4;
        boolean z9 = i6 == 3;
        boolean z10 = i6 == 5;
        boolean z11 = z6 && this.mState.ringerModeInternal == 1;
        boolean z12 = z6 && this.mState.ringerModeInternal == 0;
        boolean z13 = z10 && this.mState.ringerModeInternal == 0;
        boolean z14 = z10 && this.mState.ringerModeInternal == 1;
        VolumeDialogController.State state2 = this.mState;
        int i7 = state2.ringerModeInternal;
        boolean z15 = i7 == 1;
        boolean z16 = i7 == 0;
        int i8 = state2.zenMode;
        boolean z17 = z12;
        boolean z18 = !(i8 == 3) ? !(i8 == 2) ? (i8 == 1) && ((z8 && state2.disallowAlarms) || ((z9 && state2.disallowMedia) || ((z6 && state2.disallowRinger) || (z7 && state2.disallowSystem)))) : z6 || z7 || z8 || z9 : !(z6 || z7);
        boolean z19 = i6 == 0;
        int i9 = streamState.levelMax * 100;
        if (i9 != volumeRow.slider.getMax()) {
            volumeRow.slider.setMax(i9);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusAlertSlider() && (z6 || z10)) {
            if (this.mState.ringerModeInternal == 2) {
                streamState.levelMin = 1;
            } else {
                streamState.levelMin = 0;
            }
        }
        int i10 = streamState.levelMin * 100;
        if (i10 != volumeRow.slider.getMin()) {
            volumeRow.slider.setMin(i10);
        }
        boolean z20 = (this.mAutomute || streamState.muteSupported) && !z18;
        volumeRow.slider.setClickable(z20);
        volumeRow.iconWrapper.setAlpha(z20 ? 1.0f : 0.5f);
        volumeRow.slider.setAlpha(z20 ? 1.0f : 0.5f);
        int i11 = volumeRow.iconRes;
        String str3 = "ic_volume_assistant_on";
        boolean z21 = z18;
        String str4 = "ic_volume_media";
        boolean z22 = z19;
        if (z6) {
            VolumeDialogController.State state3 = this.mState;
            int i12 = state3.ringerModeInternal;
            if (i12 == 0 || z21) {
                z = z13;
                if (FeatureOption.isOplusAlertSlider()) {
                    volumeRow.slider.setClickable(false);
                }
                i11 = 2131237437;
                str = "ic_volume_ringer_mute";
            } else {
                z = z13;
                if (i12 == 1) {
                    if (FeatureOption.isOplusAlertSlider()) {
                        volumeRow.slider.setClickable(false);
                    }
                    str = "ic_volume_ringer_vibrate_mute";
                    i11 = 2131237441;
                } else {
                    int i13 = this.mVibrateAbility.isVibrateWhenRingingOn(state3) ? 2131237439 : 2131237434;
                    String str5 = i13 == 2131237434 ? "ic_volume_ringer_on" : "ic_volume_ringer_vibrate_on";
                    if (FeatureOption.isOplusAlertSlider()) {
                        volumeRow.slider.setClickable(true);
                        volumeRow.icon.setAlpha(1.0f);
                    }
                    str = str5;
                    i11 = i13;
                }
            }
        } else {
            z = z13;
            if (z9) {
                if (isStreamMuted(volumeRow.ss) || z21) {
                    if (streamState.routedToWired) {
                        i11 = 2131237459;
                        str = "";
                    } else if (streamState.routedToBluetooth) {
                        str = "";
                        i11 = 2131237414;
                    } else {
                        str = "ic_volume_media";
                        i11 = 2131237421;
                    }
                } else if (streamState.routedToWired) {
                    i11 = 2131237455;
                    str = "";
                } else if (streamState.routedToBluetooth) {
                    str = "";
                    i11 = 2131237412;
                } else {
                    str = "ic_volume_media";
                    i11 = 2131237409;
                }
            } else if (z10) {
                if (isStreamMuted(volumeRow.ss)) {
                    i11 = volumeRow.iconMuteRes;
                    str2 = "ic_volume_notification_mute";
                } else {
                    str2 = "ic_volume_notification_on";
                }
                volumeRow.slider.setClickable((z15 || z16) ? false : true);
                volumeRow.icon.setAlpha(((z15 || z16) && volumeRow.ss.muted) ? 0.3f : 1.0f);
                str = str2;
            } else {
                if (z22) {
                    volumeRow.ss.name = this.mAudioManager.isSpeakerphoneOn() ? 2131956681 : 2131956680;
                    i11 = 2131237450;
                } else if (z8) {
                    str = "ic_volume_alarm";
                } else if (z5) {
                    boolean zIsStreamMuted = isStreamMuted(volumeRow.ss);
                    boolean z23 = streamState.routedToWired;
                    if (zIsStreamMuted) {
                        if (z23) {
                            i11 = 2131237460;
                        } else if (streamState.routedToBluetooth) {
                            i11 = 2131237415;
                        } else {
                            i11 = 2131237398;
                            str = "ic_volume_assistant_mute";
                        }
                    } else if (z23) {
                        i11 = 2131237458;
                    } else if (streamState.routedToBluetooth) {
                        i11 = 2131237413;
                    } else {
                        i11 = 2131237397;
                        str = "ic_volume_assistant_on";
                    }
                }
                str = "";
            }
        }
        if (volumeRow.lastIconRes != i11) {
            volumeRow.lastIconRes = i11;
            if (z9 && i11 != 2131237409 && i11 != 2131237421) {
                volumeRow.lastLevel = -1;
            }
            if (z9 && str.length() > 0 && this.mShowing) {
                z2 = z14;
            } else {
                Drawable drawable = this.mWindowContext.getDrawable(i11);
                z2 = z14;
                if (drawable != null) {
                    drawable.setTint(-16777216);
                    volumeRow.icon.setImageDrawable(drawable);
                }
                if (z9) {
                    volumeRow.jsonRes = "";
                }
            }
        } else {
            z2 = z14;
        }
        String str6 = "";
        if (str.length() > 0) {
            f = 0.0f;
            boolean z24 = z5;
            if (z9) {
                VolumeRow volumeRowFindRow = findRow(3);
                if (volumeRowFindRow != null) {
                    int i14 = z21 ? 0 : volumeRowFindRow.ss.level;
                    int i15 = volumeRowFindRow.lastLevel;
                    if (i14 == i15) {
                        z16 = z16;
                        z11 = z11;
                    } else {
                        if (i15 == -1) {
                            volumeRowFindRow.lastLevel = 0;
                        }
                        float f4 = volumeRowFindRow.ss.levelMax;
                        int i16 = (int) (0.33f * f4);
                        int i17 = (int) (f4 * 0.66f);
                        int i18 = volumeRowFindRow.lastLevel;
                        int i19 = 83;
                        if (i14 == 0) {
                            if (i18 > i17) {
                                str6 = "ic_volume_media_grade_3_to_0";
                            } else if (i18 > i16) {
                                str6 = "ic_volume_media_grade_2_to_0";
                            } else {
                                i5 = 84;
                                i19 = 120;
                                f2 = 1.0f;
                                z4 = true;
                                i4 = 84;
                                str6 = str6;
                            }
                            i4 = 0;
                            i5 = 0;
                            f2 = 1.0f;
                            z4 = false;
                        } else {
                            f2 = -1.0f;
                            int i20 = 60;
                            if (i14 <= i16) {
                                if (i18 == 0) {
                                    z11 = z11;
                                    str6 = str6;
                                    i19 = 36;
                                    i5 = 0;
                                    f2 = 1.0f;
                                    z4 = true;
                                    z16 = z16;
                                    i4 = 0;
                                } else if (i18 > i17) {
                                    i4 = 36;
                                    i19 = 83;
                                    i5 = 83;
                                    z4 = true;
                                    f2 = -4.0f;
                                    str6 = str6;
                                } else {
                                    if (i18 > i16) {
                                        i4 = 36;
                                        i19 = 60;
                                        i5 = 60;
                                        z4 = true;
                                    }
                                    str6 = str6;
                                    i4 = 0;
                                    i5 = 0;
                                    f2 = 1.0f;
                                    z4 = false;
                                }
                            } else if (i14 > i17) {
                                if (i18 == 0) {
                                    str6 = "ic_volume_media_grade_0_to_3";
                                } else {
                                    if (i18 <= i16) {
                                        f3 = 4.0f;
                                        i20 = 36;
                                        z3 = true;
                                    } else if (i18 <= i17) {
                                        z3 = true;
                                        f3 = 1.0f;
                                    }
                                    i4 = i20;
                                    i5 = i4;
                                    f2 = f3;
                                    i19 = 83;
                                    z4 = z3;
                                    str6 = str6;
                                }
                                z3 = false;
                                f3 = 1.0f;
                                i20 = 0;
                                i4 = i20;
                                i5 = i4;
                                f2 = f3;
                                i19 = 83;
                                z4 = z3;
                                str6 = str6;
                            } else if (i18 == 0) {
                                str6 = "ic_volume_media_grade_0_to_2";
                                i4 = 0;
                                i5 = 0;
                                f2 = 1.0f;
                                z4 = false;
                            } else if (i18 <= i16) {
                                z11 = z11;
                                str6 = str6;
                                i19 = 60;
                                i5 = 36;
                                f2 = 1.0f;
                                z4 = true;
                                z16 = z16;
                                i4 = 36;
                            } else {
                                if (i18 > i17) {
                                    i19 = 83;
                                    i5 = 83;
                                    i4 = 60;
                                    z4 = true;
                                }
                                str6 = str6;
                                i4 = 0;
                                i5 = 0;
                                f2 = 1.0f;
                                z4 = false;
                            }
                        }
                        if (str6.length() > 0) {
                            volumeRowFindRow.icon.setAnimation("volumeui/" + str6 + "_light.json");
                            volumeRowFindRow.icon.setSpeed(1.0f);
                            volumeRowFindRow.icon.playAnimation();
                        } else {
                            if (z4) {
                                if (volumeRowFindRow.icon.isAnimating() && volumeRowFindRow.jsonRes.equals("ic_volume_media") && i14 > 0 && volumeRowFindRow.lastLevel != 0) {
                                    int i21 = f2 > 0.0f ? i19 : i4;
                                    int frame = volumeRowFindRow.icon.getFrame();
                                    EffectiveAnimationView effectiveAnimationView = volumeRowFindRow.icon;
                                    if (i21 > frame) {
                                        effectiveAnimationView.setMaxFrame(i21);
                                        volumeRowFindRow.icon.setSpeed(((int) ((i21 - frame) / 30.0f)) + 2);
                                    } else {
                                        effectiveAnimationView.setMinFrame(i21);
                                        volumeRowFindRow.icon.setSpeed(((int) ((i21 - frame) / 30.0f)) - 2);
                                    }
                                    z4 = false;
                                }
                                if (z4) {
                                    if (!volumeRowFindRow.jsonRes.equals("ic_volume_media")) {
                                        volumeRowFindRow.icon.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    volumeRowFindRow.icon.setMinAndMaxFrame(i4, i19);
                                    volumeRowFindRow.icon.setSpeed(f2);
                                    volumeRowFindRow.icon.playAnimation();
                                    volumeRowFindRow.icon.setFrame(i5);
                                }
                            }
                            if (str4.length() > 0) {
                                volumeRowFindRow.jsonRes = str4;
                            }
                            volumeRowFindRow.lastLevel = i14;
                        }
                        str4 = str6;
                        if (str4.length() > 0) {
                            volumeRowFindRow.jsonRes = str4;
                        }
                        volumeRowFindRow.lastLevel = i14;
                    }
                } else {
                    z16 = z16;
                    z11 = z11;
                }
            } else {
                z16 = z16;
                z11 = z11;
                if (z24) {
                    volumeRow.jsonRes = str;
                    VolumeRow volumeRowFindRow2 = findRow(11);
                    if (volumeRowFindRow2 == null) {
                        LogUtil.e(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "updateAssistantStreamIcon VolumeRow is null!");
                    } else {
                        VolumeDialogController.StreamState streamState2 = volumeRowFindRow2.ss;
                        int i22 = streamState2.muted ? 0 : streamState2.level;
                        ClipboardOverlayView$.ExternalSyntheticOutline0.m(volumeRowFindRow2.lastLevel, LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", ListPopupWindow$.ExternalSyntheticOutline0.m(i22, "updateAssistantStreamIcon current level: ", ",last level: "));
                        int i23 = volumeRowFindRow2.lastLevel;
                        if (i22 != i23) {
                            if (i23 == -1) {
                                volumeRowFindRow2.lastLevel = 0;
                            }
                            int i24 = volumeRowFindRow2.lastLevel;
                            if (i22 == 0) {
                                if (i24 > i22) {
                                    str3 = "ic_volume_assistant_mute";
                                } else {
                                    str3 = str6;
                                }
                            } else if (i24 != 0 || i22 <= 0) {
                                str3 = str6;
                            }
                            if (str3.length() > 0) {
                                volumeRowFindRow2.icon.setAnimation("volumeui/" + str3 + "_light.json");
                                volumeRowFindRow2.icon.setSpeed(1.0f);
                                volumeRowFindRow2.icon.playAnimation();
                            }
                            if (str3.length() > 0) {
                                volumeRowFindRow2.jsonRes = str3;
                            }
                            volumeRowFindRow2.lastLevel = i22;
                        }
                    }
                } else {
                    FlavorOneFeatureOption.INSTANCE.getClass();
                    if (!FlavorOneFeatureOption.isFlavorOneDevice() && !str.equals(volumeRow.jsonRes)) {
                        volumeRow.jsonRes = str;
                        if (this.mShowing) {
                            volumeRow.icon.setAnimation("volumeui/" + str + "_light.json");
                            volumeRow.icon.playAnimation();
                        }
                    } else if (z8) {
                        VolumeDialogController.StreamState streamState3 = volumeRow.ss;
                        int i25 = streamState3.level;
                        int i26 = streamState3.levelMin;
                        if (i25 == i26 && volumeRow.lastAudibleLevel != i26) {
                            volumeRow.icon.setAnimation("volumeui/" + str + "_light.json");
                            volumeRow.icon.playAnimation();
                        }
                    }
                }
            }
        } else {
            z16 = z16;
            z11 = z11;
            f = 0.0f;
        }
        if (i11 == 2131237441) {
            i = 3;
        } else if (i11 == 2131237414 || i11 == volumeRow.iconMuteRes) {
            i = 2;
        } else {
            i = (i11 == 2131237412 || i11 == volumeRow.iconRes) ? 1 : 0;
        }
        volumeRow.iconState = i;
        int i27 = streamState.level;
        if (i27 > 0) {
            volumeRow.lastAudibleLevel = i27;
        }
        if (z21) {
            volumeRow.tracking = false;
        }
        boolean z25 = (z17 || z11 || z || z2) && FeatureOption.isOplusAlertSlider();
        boolean z26 = (z21 || (volumeRow.stream == 5 && (z15 || z16)) || z25) ? false : true;
        if (z25) {
            volumeRow.icon.setAlpha(0.5f);
        }
        VolumeDialogController.StreamState streamState4 = volumeRow.ss;
        int i28 = (streamState4.muted || z25) ? 0 : streamState4.level;
        int i29 = volumeRow.stream;
        if (i29 == 5 || i29 == 2) {
            if (z26 && volumeRow.slider.getDisabled()) {
                i2 = 0;
                volumeRow.slider.setDisabled(false);
            } else {
                i2 = 0;
            }
            if (!z26 && !volumeRow.slider.getDisabled()) {
                volumeRow.slider.setDisabled(true);
                volumeRow.slider.setProgress(i2);
            }
        }
        volumeRow.slider.setEnabled(z26);
        if (volumeRow.tracking) {
            return;
        }
        int progress = volumeRow.slider.getProgress();
        int impliedLevel = getImpliedLevel(volumeRow.slider, progress);
        boolean z27 = volumeRow.view.getVisibility() == 0;
        boolean z28 = SystemClock.uptimeMillis() - volumeRow.userAttempt < 1000;
        this.mHandler.removeMessages(3, volumeRow);
        boolean z29 = this.mShowing;
        if (z29 && z27 && z28) {
            LogUtil.d(LogUtil.TAG_VOLUME, "VolumeDialogImplSubDisplay", "inGracePeriod");
            H h = this.mHandler;
            h.sendMessageAtTime(h.obtainMessage(3, volumeRow), volumeRow.userAttempt + 1000);
            return;
        }
        if ((i28 == impliedLevel && z29 && z27 && ((objectAnimator = volumeRow.anim) == null || !objectAnimator.isRunning())) || progress == (i3 = i28 * 100)) {
            return;
        }
        if (volumeRow.stream == this.mActiveStream && this.mShowing) {
            if (System.currentTimeMillis() - this.mUpdateSliderTime <= 120 && System.currentTimeMillis() - this.mUpdateSliderTime >= 10) {
                if (volumeRow.stream != 2) {
                    VolumeDialogController.StreamState streamState5 = volumeRow.ss;
                    if (streamState5.levelMax == i28 || streamState5.levelMin == i28) {
                        this.mVibrateAbility.vibrateByLMVibrator(153, false);
                    } else {
                        this.mVibrateAbility.vibrateByLMVibrator(152, true);
                    }
                } else {
                    this.mVibrateAbility.vibrateByLMVibrator(152, true);
                }
            }
            this.mUpdateSliderTime = System.currentTimeMillis();
        }
        if (!this.mShowing || !z27) {
            ObjectAnimator objectAnimator2 = volumeRow.anim;
            if (objectAnimator2 != null) {
                objectAnimator2.cancel();
            }
            volumeRow.slider.setProgress(i3, true);
            return;
        }
        ObjectAnimator objectAnimator3 = volumeRow.anim;
        if (objectAnimator3 != null && objectAnimator3.isRunning() && volumeRow.animTargetProgress == i3) {
            return;
        }
        ObjectAnimator objectAnimator4 = volumeRow.anim;
        if (objectAnimator4 == null) {
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(volumeRow.slider, "progress", progress, i3);
            volumeRow.anim = objectAnimatorOfInt;
            COUISlideDeleteAnimation$.ExternalSyntheticOutline0.m(0.17f, 0.17f, f, 1.0f, objectAnimatorOfInt);
        } else {
            objectAnimator4.cancel();
            volumeRow.anim.setIntValues(progress, i3);
        }
        volumeRow.animTargetProgress = i3;
        volumeRow.anim.setDuration(300L);
        volumeRow.anim.start();
    }

    public final void onStateChangedH(VolumeDialogController.State state) {
        if (state == null) {
            return;
        }
        this.mState = state;
        int i = this.mActiveStream;
        int i2 = state.activeStream;
        if (i != i2) {
            this.mPrevActiveStream = i;
            this.mActiveStream = i2;
        }
        Iterator it = this.mRows.iterator();
        while (it.hasNext()) {
            updateVolumeRowH((VolumeRow) it.next());
        }
    }
}
