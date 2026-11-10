package com.oplus.systemui.volume;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import android.os.VibrationEffect;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.ui.spatial.RectList$;
import androidx.fragment.app.Fragment$5$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.topui.TopUiController;
import com.android.systemui.util.JankUtils;
import com.android.systemui.volume.ConfigurableTexts;
import com.android.systemui.volume.Events;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.coui.appcompat.slideview.COUISlideDeleteAnimation$;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.osense.OsenseResClient;
import com.oplus.osense.info.OsenseSaRequest;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStateListener;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStealthModeObserver;
import com.oplus.systemui.qs.OplusNoDisturbListener;
import com.oplus.systemui.qs.OplusNoDisturbObserver;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.volume.ability.IconAbility;
import com.oplus.systemui.volume.ability.VibrateAbility;
import com.oplus.systemui.volume.manager.VolumeManager;
import com.oplus.systemui.volume.manager.VolumeSupplyExportApi;
import com.oplus.systemui.volume.observer.AnimationScaleSettingsObserver;
import com.oplus.systemui.volume.repository.AudioDataRepository;
import com.oplus.systemui.volume.repository.MultiMediaRepository;
import com.oplus.systemui.volume.repository.OplusVolumeRepository;
import com.oplus.systemui.volume.repository.SafetyWarningRepository;
import com.oplus.systemui.volume.repository.SuperVolumeRepository;
import com.oplus.systemui.volume.utils.MultiVolumeKeyStateListener;
import com.oplus.systemui.volume.utils.OplusRMChangeVibrateHelper;
import com.oplus.systemui.volume.utils.VolumeAnimUtil;
import com.oplus.systemui.volume.utils.VolumePropertyAnimationUtil;
import com.oplus.systemui.volume.utils.material.VolumeBlurLightParams;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplus.systemui.volume.view.OplusVolumeDialogView;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplus.zoom.ui.tips.TipsManager;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogImpl implements VolumeDialog, ConfigurationController.ConfigurationListener, BluetoothCallback, Dumpable, OplusNoDisturbObserver, OplusVolumeDialogInterface {
    public static final int RINGER_MODE_MIN_LEVEL;
    public boolean firstShowUI;
    public boolean isAnimationStart;
    public OplusVolumeDialogImpl$$ExternalSyntheticLambda2 mA11yServicesStateChangeListener;
    public AccessibilityManagerWrapper mAccessibilityMgr;
    public int mActiveStream;
    public AudioDataRepository mAudioDataRepository;
    public AudioManager mAudioManager;
    public boolean mAutomute;
    public LocalBluetoothManager mBluetoothManager;
    public boolean mConfigChanged;
    public ConfigurationController mConfigurationController;
    public Context mContext;
    public VolumeDialogController mController;
    public boolean mDelaySliderShowForUserSwitch;
    public DeviceProvisionedController mDeviceProvisionedController;
    public boolean mDialogShowingForMultiMedia;
    public SparseBooleanArray mDynamic;
    public boolean mExpanded;
    public H mHandler;
    public boolean mHasSeenODICaptionsTooltip;
    public IconAbility mIconAbility;
    public int mInterruptTimeoutReasonCode;
    public boolean mIsCaptionComponentEnabled;
    public boolean mIsIconClicked;
    public boolean mIsMultiRowShowing;
    public KeyguardManager mKeyguard;
    public KeyguardStateController mKeyguardStateController;
    public AnonymousClass1 mLaunchSoundSettingsAfterDismissDelay;
    public MultiMediaRepository mMultiMediaRepository;
    public int mNowScreenOrientation;
    public OplusRMChangeVibrateHelper mOplusRMChangeVibrateHelper;
    public OplusVolumeControllerEx mOplusVolumeController;
    public OplusVolumeDialogView mOplusVolumeDialogView;
    public OplusVolumeRepository mOplusVolumeRepository;
    public OplusVolumeSnackBarController mOplusVolumeSnackBarController;
    public OplusVolumeTipsControler mOplusVolumeTipsControler;
    public OsenseResClient mOsenseResClient;
    public boolean mPendingLandscapeExpandedSettingsLaunch;
    public int mPrevActiveStream;
    public boolean mPreventSeekBar;
    public long mRequest;
    public List mRows;
    public SafetyWarningRepository mSafetyWarningRepository;
    public SetTopUiRunnable mSetTopUiRunnable;
    public int mSettingsButtonClickCount;
    public String mSettingsButtonClickTime;
    public boolean mShowA11yStream;
    public boolean mSilentMode;
    public OplusSingleAppVolumeButtonViewController mSingleAppVolumeButtonViewController;
    public String mSoundSettingsActivity;
    public VolumeDialogController.State mState;
    public OplusVolumeDialogImpl$$ExternalSyntheticLambda4 mStateChangedListener;
    public SuperVolumeRepository mSuperVolumeRepository;
    public UserContextProvider mUserContextProvider;
    public OplusVolumeDialogImpl$$ExternalSyntheticLambda0 mUserSwitchDelayedSliderShow;
    public VibrateAbility mVibrateAbility;
    public OplusVolumeCaptions mVolumeDialogEx;
    public VolumeManager mVolumeManager;
    public ActivityStarter mactivityStarter;

    /* JADX INFO: renamed from: com.oplus.systemui.volume.OplusVolumeDialogImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OplusVolumeDialogImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = this.this$0;
            if (oplusVolumeDialogImpl.mPendingLandscapeExpandedSettingsLaunch) {
                oplusVolumeDialogImpl.mPendingLandscapeExpandedSettingsLaunch = false;
                boolean zIsActivityAtTop = Util.isActivityAtTop(oplusVolumeDialogImpl.mSoundSettingsActivity);
                OplusVolumeDialogImpl oplusVolumeDialogImpl2 = this.this$0;
                if (!zIsActivityAtTop) {
                    oplusVolumeDialogImpl2.mactivityStarter.startActivity(new Intent("android.settings.SOUND_SETTINGS"), true);
                } else {
                    LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "mLaunchSoundSettingsAfterDismissDelay top activity name: " + Util.getCurrentTopActivityNameInActivityStack());
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.OplusVolumeDialogImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements OplusSingleAppVolumeButtonViewController.SingleAppVolumeButtonCallback {
        public /* synthetic */ OplusVolumeDialogImpl this$0;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            OplusVolumeDialogImpl oplusVolumeDialogImpl = OplusVolumeDialogImpl.this;
            switch (i) {
                case 1:
                    oplusVolumeDialogImpl.showH(message.arg1, false);
                    break;
                case 2:
                    oplusVolumeDialogImpl.dismissH$1(message.arg1);
                    break;
                case 3:
                    oplusVolumeDialogImpl.recheckH(message.obj);
                    break;
                case 4:
                    oplusVolumeDialogImpl.recheckH(null);
                    break;
                case 5:
                    int i2 = message.arg1;
                    boolean z = message.arg2 != 0;
                    for (OplusVolumeRow oplusVolumeRow : oplusVolumeDialogImpl.mRows) {
                        if (oplusVolumeRow.getStream() == i2) {
                            oplusVolumeRow.setImportant(z);
                            break;
                        }
                    }
                    break;
                case 6:
                    oplusVolumeDialogImpl.rescheduleTimeoutH();
                    break;
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                    oplusVolumeDialogImpl.onStateChangedH(oplusVolumeDialogImpl.mState);
                    break;
                case 8:
                    if (oplusVolumeDialogImpl.mOplusVolumeDialogView.isSliderShowing()) {
                        oplusVolumeDialogImpl.getVolumeListAdapter().startBackAim();
                    }
                    oplusVolumeDialogImpl.startKeyDownTransAnim(false);
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SetTopUiRunnable implements Runnable {
        public boolean mHasTopUi;
        public StatusBarStateController mStatusBarController;
        public TopUiController mTopUiController;

        @Override // java.lang.Runnable
        public final void run() {
            if (this.mStatusBarController.isExpanded()) {
                return;
            }
            OplusVolumeDialogImpl$$ExternalSyntheticLambda0 oplusVolumeDialogImpl$$ExternalSyntheticLambda0 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda0(4);
            oplusVolumeDialogImpl$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            DejankUtils.whitelistIpcs(oplusVolumeDialogImpl$$ExternalSyntheticLambda0);
        }

        public void setHasTopUi(boolean z) {
            this.mHasTopUi = z;
        }

        public void setTopUiController(TopUiController topUiController) {
            this.mTopUiController = topUiController;
        }
    }

    static {
        FeatureOption.INSTANCE.getClass();
        RINGER_MODE_MIN_LEVEL = FeatureOption.isOplusAlertSlider() ? 1 : 0;
    }

    public static boolean inRangeOfView(MotionEvent motionEvent, View view) {
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        rect2.inset(rect);
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "is touch in view viewInfo = " + view + ", view rect = " + rect2 + ", x = " + motionEvent.getRawX() + ", y = " + motionEvent.getRawY());
        return rect2.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
    }

    public final void addRow(int i, int i2, int i3, boolean z, boolean z2) {
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "Adding row for stream " + i);
        OplusVolumeRow oplusVolumeRow = new OplusVolumeRow(this.mContext, this, i, i2, i3, z, z2);
        oplusVolumeRow.initRow();
        this.mOplusVolumeDialogView.getVolumeListAdapter().addItem(oplusVolumeRow, null);
        this.mRows.add(oplusVolumeRow);
    }

    public final int computeTimeoutH$1() {
        if (this.mOplusVolumeDialogView.getMHovering()) {
            return this.mAccessibilityMgr.mAccessibilityManager.getRecommendedTimeoutMillis(16000, 4);
        }
        synchronized (this.mSafetyWarningRepository.getSafetyWarningLock()) {
            try {
                if (this.mSafetyWarningRepository.getSafetyWarning() != null) {
                    return this.mAccessibilityMgr.mAccessibilityManager.getRecommendedTimeoutMillis(TipsManager.DELAY_AUTO_HIDE_TIME, 6);
                }
                boolean z = this.mExpanded;
                AccessibilityManagerWrapper accessibilityManagerWrapper = this.mAccessibilityMgr;
                return z ? accessibilityManagerWrapper.mAccessibilityManager.getRecommendedTimeoutMillis(TipsManager.DELAY_AUTO_HIDE_TIME, 4) : accessibilityManagerWrapper.mAccessibilityManager.getRecommendedTimeoutMillis(3000, 4);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void destroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        OplusNoDisturbListener.getInstance().removeObserver(this.mContext, this);
        this.mConfigurationController.removeCallback(this);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
            MultiVolumeKeyStateListener.Companion.getInstance(this.mContext).unregisterKeyEvent(this.mContext);
        }
        LocalBluetoothManager localBluetoothManager = this.mBluetoothManager;
        if (localBluetoothManager != null) {
            localBluetoothManager.getEventManager().unregisterCallback(this);
        }
        this.mAudioDataRepository.destroy();
        this.mMultiMediaRepository.destroy();
        OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController = this.mSingleAppVolumeButtonViewController;
        if (oplusSingleAppVolumeButtonViewController != null) {
            oplusSingleAppVolumeButtonViewController.destroy();
        }
        DeviceStateController.getInstance().removeCallback(this.mStateChangedListener);
        AccessibilityManagerWrapper accessibilityManagerWrapper = this.mAccessibilityMgr;
        if (accessibilityManagerWrapper != null) {
            accessibilityManagerWrapper.mAccessibilityManager.removeAccessibilityServicesStateChangeListener(this.mA11yServicesStateChangeListener);
        }
    }

    public final void dismiss(int i) {
        if (i == 4) {
            this.mInterruptTimeoutReasonCode = i;
        }
        this.mHandler.obtainMessage(2, i, 0).sendToTarget();
    }

    public final void dismissH$1(int i) {
        this.mDelaySliderShowForUserSwitch = false;
        this.mHandler.removeCallbacks(this.mUserSwitchDelayedSliderShow);
        synchronized (this.mSafetyWarningRepository.getSafetyWarningLock()) {
            try {
                if (this.mSafetyWarningRepository.getSafetyWarning() != null && this.mSafetyWarningRepository.getSafetyWarning().getSafetyWarningDialog() != null) {
                    LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "SafetyWarning dismissed");
                    this.mSafetyWarningRepository.getSafetyWarning().getSafetyWarningDialog().dismiss();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (this.mSingleAppVolumeButtonViewController.isShowing()) {
            this.mSingleAppVolumeButtonViewController.hide();
        } else {
            this.mOplusVolumeDialogView.dismissH(i);
        }
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(1);
        this.mInterruptTimeoutReasonCode = -1;
        this.mExpanded = false;
        VolumeSupplyExportApi.INSTANCE.notifyVolumeDialogIsExpandChanged();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusVolumeDialogImpl state:");
        printWriter.print("  mShowing: ");
        printWriter.println(this.mOplusVolumeDialogView.isSliderShowing());
        printWriter.print("  mActiveStream: ");
        printWriter.println(this.mActiveStream);
        printWriter.print("  mDynamic: ");
        printWriter.println(this.mDynamic);
        printWriter.print("  mAutomute: ");
        printWriter.println(this.mAutomute);
        printWriter.print("  mSilentMode: ");
        printWriter.println(this.mSilentMode);
        printWriter.print(" mExpanded: ");
        printWriter.println(this.mExpanded);
        printWriter.print(" isShouldShow: ");
        printWriter.println(this.mOplusVolumeRepository.shouldShow());
        printWriter.print(" isShowVolumeSeekbar: ");
        printWriter.println(this.mVolumeManager.isShowVolumeSeekbar());
        if (this.mState != null) {
            printWriter.print(" mState: ");
            printWriter.println(this.mState.toString());
        }
    }

    public final void expandPanel() {
        LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "expandPanel");
        if (this.mOplusVolumeDialogView.isDismissing()) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "already dismiss");
            return;
        }
        this.mExpanded = true;
        this.mOplusVolumeController.setPanelExpandedSysUiFlag(true);
        VolumeSupplyExportApi.INSTANCE.notifyVolumeDialogIsExpandChanged();
        VolumeDialogController.State state = this.mState;
        if (state != null && state.zenMode == 1 && state.disallowMedia) {
            this.mOplusVolumeTipsControler.setLongTip(this.mContext.getString(2131953082), 630L, 0L);
        }
        this.mOplusVolumeDialogView.expandPanel();
        rescheduleTimeoutH();
    }

    public final OplusVolumeRow findRow(int i) {
        for (OplusVolumeRow oplusVolumeRow : this.mRows) {
            if (oplusVolumeRow.getStream() == i) {
                return oplusVolumeRow;
            }
        }
        return null;
    }

    public OplusVolumeRow getActiveRow() {
        for (OplusVolumeRow oplusVolumeRow : this.mRows) {
            if (oplusVolumeRow.getStream() == this.mActiveStream) {
                return oplusVolumeRow;
            }
        }
        for (OplusVolumeRow oplusVolumeRow2 : this.mRows) {
            if (oplusVolumeRow2.getStream() == 3) {
                return oplusVolumeRow2;
            }
        }
        return (OplusVolumeRow) ((ArrayList) this.mRows).getFirst();
    }

    public LayoutInflater getDialogInflater() {
        return this.mOplusVolumeDialogView.getVolumeDialog().getLayoutInflater();
    }

    public Boolean getExpandedState() {
        return Boolean.valueOf(this.mExpanded);
    }

    public OplusVolumeSeekBar getOnlyRow() {
        ArrayList<View> visRows = this.mOplusVolumeDialogView.getVisRows();
        if (!this.mDialogShowingForMultiMedia || this.mExpanded || visRows.size() != 1) {
            return null;
        }
        View view = (View) visRows.getFirst();
        if (view instanceof ViewGroup) {
            return (OplusVolumeSeekBar) view.findViewById(2131364971);
        }
        return null;
    }

    public OplusVolumeCaptions getOplusVolumeCaptionEx() {
        if (this.mVolumeDialogEx == null) {
            this.mVolumeDialogEx = new OplusVolumeCaptions();
        }
        return this.mVolumeDialogEx;
    }

    public VolumeListAdapter getVolumeListAdapter() {
        return this.mOplusVolumeDialogView.getVolumeListAdapter();
    }

    public final void init(int i, VolumeDialog.Callback callback) {
        OplusVolumeDialogView oplusVolumeDialogView = new OplusVolumeDialogView(this.mContext, this);
        this.mOplusVolumeDialogView = oplusVolumeDialogView;
        oplusVolumeDialogView.initDialog();
        AnimationScaleSettingsObserver.Companion.getInstance().registerObserver(this.mContext);
        this.mController.getState();
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "OplusVolumeDialogImpl init");
        OplusNoDisturbListener.getInstance().addObserver(this.mContext, this);
        this.mConfigurationController.addCallback(this);
        MultiVolumeKeyStateListener.Companion.getInstance(this.mContext).registerKeyEvent(this.mContext);
        LocalBluetoothManager localBluetoothManager = this.mBluetoothManager;
        if (localBluetoothManager != null) {
            localBluetoothManager.getEventManager().registerCallback(this);
        }
        DeviceStateController.getInstance().addCallback(this.mStateChangedListener);
        AccessibilityManagerWrapper accessibilityManagerWrapper = this.mAccessibilityMgr;
        if (accessibilityManagerWrapper != null) {
            accessibilityManagerWrapper.mAccessibilityManager.addAccessibilityServicesStateChangeListener(this.mA11yServicesStateChangeListener);
            H h = this.mHandler;
            OplusVolumeDialogImpl$$ExternalSyntheticLambda0 oplusVolumeDialogImpl$$ExternalSyntheticLambda0 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda0(0);
            oplusVolumeDialogImpl$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            h.post(oplusVolumeDialogImpl$$ExternalSyntheticLambda0);
        }
        OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController = new OplusSingleAppVolumeButtonViewController(this.mContext);
        this.mSingleAppVolumeButtonViewController = oplusSingleAppVolumeButtonViewController;
        if (!oplusSingleAppVolumeButtonViewController.isInitialized) {
            oplusSingleAppVolumeButtonViewController.init();
            OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController2 = this.mSingleAppVolumeButtonViewController;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            oplusSingleAppVolumeButtonViewController2.setCallback(anonymousClass2);
        }
        this.mOplusVolumeDialogView.addSingleAppVolumeButtonController(this.mSingleAppVolumeButtonViewController.getContainerView());
    }

    public final void isNeedToShowToastUi(int i) {
        OplusVolumeTipsControler oplusVolumeTipsControler = this.mOplusVolumeTipsControler;
        Context context = this.mContext;
        if (i == 3) {
            oplusVolumeTipsControler.showToast(context, this.mExpanded, 2131956814);
        } else {
            oplusVolumeTipsControler.showToast(context, this.mExpanded, 2131956825);
        }
    }

    public final boolean isShowing() {
        return this.mOplusVolumeDialogView.getVolumeDialog().isShowing();
    }

    @Override // com.oplus.systemui.volume.OplusVolumeDialogInterface
    public final void onAccessibilityModeChanged(Boolean bool) {
        this.mShowA11yStream = bool != null && bool.booleanValue();
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", new StringBuilder("onAccessibilityModeChanged mShowA11yStream="), this.mShowA11yStream);
        if (this.mOplusVolumeDialogView == null || this.mRows.isEmpty()) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "return mOplusVolumeDialogView=" + this.mOplusVolumeDialogView + " ,mRows:" + ((ArrayList) this.mRows).size());
            return;
        }
        OplusVolumeRow activeRow = getActiveRow();
        if (this.mShowA11yStream || 10 != activeRow.getStream()) {
            this.mOplusVolumeDialogView.updateRowsH(activeRow, true);
        } else {
            dismissH$1(7);
        }
    }

    public final void onConfigChanged(Configuration configuration) {
        Trace.traceBegin(4096L, "OplusVolumeDialogImpl_onConfigChanged");
        this.mExpanded = false;
        this.mOplusVolumeDialogView.onConfigChanged(configuration);
        Trace.traceEnd(4096L);
    }

    public final void onDistrubModeChange(VolumeDialogController.State state) {
        this.mState = state;
        if (state.zenMode == 1 && state.disallowMedia && this.mExpanded) {
            this.mOplusVolumeTipsControler.setLongTip(this.mContext.getString(2131953082), 200L, 0L);
            return;
        }
        boolean z = this.mExpanded;
        OplusVolumeTipsControler oplusVolumeTipsControler = this.mOplusVolumeTipsControler;
        if (z) {
            oplusVolumeTipsControler.setLongTip(null, 100L, 0L);
        } else {
            oplusVolumeTipsControler.clearTip();
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0071  */
    /* JADX WARN: Code duplicated, block: B:31:0x0094  */
    @Override // com.oplus.systemui.volume.OplusVolumeDialogInterface
    public final void onStateChangedH(VolumeDialogController.State state) {
        boolean z;
        OplusVolumeDialogImpl oplusVolumeDialogImpl;
        StringBuilder sb = new StringBuilder("onStateChangedH state: ");
        sb.append(state);
        sb.append(", isIconClicked = ");
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sb, this.mIsIconClicked);
        if (state == null) {
            return;
        }
        VolumeDialogController.State state2 = this.mState;
        int i = 0;
        if (state2 != null) {
            int i2 = state2.ringerModeInternal;
            int i3 = state.ringerModeInternal;
            if (i2 == i3 || i3 != 1) {
                z = false;
            } else {
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isOplusActionButton() || FeatureOption.isSupportGameHyperButton()) {
                    z = false;
                } else {
                    int threeKeyMode = ThreeKeyStateListener.getThreeKeyMode(this.mContext);
                    MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(threeKeyMode, "onStateChangedH() threeKeyState: ", LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl");
                    if ((this.mState.ringerModeInternal == -1 || CustomizeFeatureOption.sIsSupportThreekeyVibration) && threeKeyMode != 3) {
                        z = false;
                    } else {
                        if (FeatureOption.isLinearVibratorSupport()) {
                            this.mVibrateAbility.vibrateByLMVibrator(2, true);
                            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "onStateChangedH state,vibrateByLMVibrator WaveformEffect=2");
                        } else {
                            this.mController.vibrate(VibrationEffect.get(5));
                        }
                        z = true;
                    }
                }
            }
        } else {
            z = false;
        }
        onDistrubModeChange(state);
        this.mDynamic.clear();
        while (i < state.states.size()) {
            int iKeyAt = state.states.keyAt(i);
            if (((VolumeDialogController.StreamState) state.states.valueAt(i)).dynamic) {
                this.mDynamic.put(iKeyAt, true);
                if (this.findRow(iKeyAt) == null) {
                    oplusVolumeDialogImpl = this;
                    oplusVolumeDialogImpl.addRow(iKeyAt, 2131234630, 2131234631, false, false);
                    LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "add remote row");
                } else {
                    oplusVolumeDialogImpl = this;
                }
            } else {
                oplusVolumeDialogImpl = this;
            }
            i++;
            this = oplusVolumeDialogImpl;
        }
        OplusVolumeDialogImpl oplusVolumeDialogImpl2 = this;
        oplusVolumeDialogImpl2.refreshRows(state);
        Iterator it = oplusVolumeDialogImpl2.mRows.iterator();
        while (it.hasNext()) {
            oplusVolumeDialogImpl2.mIconAbility.updateVolumeRowH((OplusVolumeRow) it.next(), z, oplusVolumeDialogImpl2.mState);
        }
        oplusVolumeDialogImpl2.mOplusVolumeDialogView.updateState(oplusVolumeDialogImpl2.mIsCaptionComponentEnabled);
    }

    public final void onThemeChanged() {
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "theme change");
        this.mOplusVolumeDialogView.onThemeChanged();
    }

    public final void onUiModeChanged() {
        this.mContext.getTheme().applyStyle(this.mContext.getThemeResId(), true);
    }

    public final void performTouchDownAnim() {
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "onTouchDown: mExpanded:" + this.mExpanded + ",getVisRows().size():" + this.mOplusVolumeDialogView.getVisRows().size());
        startThickAnim(false);
        if (this.mExpanded || this.mOplusVolumeDialogView.getVisRows().size() != 1) {
            return;
        }
        float dimensionPixelOffset = this.mOplusVolumeDialogView.getNewContext().getResources().getDimensionPixelOffset(2131173975) * 0.33f;
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isOplusVolumeKeyInRight()) {
            dimensionPixelOffset *= -1.0f;
        }
        startTouchScaleAnim(true, dimensionPixelOffset, 1.1f, null);
    }

    public final void performTouchUpAnim() {
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "onTouchUp: mExpanded:" + this.mExpanded + ",getVisRows().size():" + this.mOplusVolumeDialogView.getVisRows().size());
        OplusVolumeDialogImpl$$ExternalSyntheticLambda0 oplusVolumeDialogImpl$$ExternalSyntheticLambda0 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda0(3);
        oplusVolumeDialogImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        if (this.mExpanded || this.mOplusVolumeDialogView.getVisRows().size() != 1) {
            oplusVolumeDialogImpl$$ExternalSyntheticLambda0.run();
        } else {
            startTouchScaleAnim(false, 0.0f, 1.0f, oplusVolumeDialogImpl$$ExternalSyntheticLambda0);
        }
    }

    public final void recheckH(Object obj) {
        IconAbility iconAbility = this.mIconAbility;
        if (obj == null) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "recheckH ALL");
            trimObsoleteH();
            Iterator it = this.mRows.iterator();
            while (it.hasNext()) {
                iconAbility.updateVolumeRowH((OplusVolumeRow) it.next(), false, this.mState);
            }
            return;
        }
        if (obj instanceof OplusVolumeRow) {
            StringBuilder sb = new StringBuilder("recheckH ");
            OplusVolumeRow oplusVolumeRow = (OplusVolumeRow) obj;
            sb.append(oplusVolumeRow.getStream());
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sb.toString());
            iconAbility.updateVolumeRowH(oplusVolumeRow, false, this.mState);
        }
    }

    public final void refreshRows(VolumeDialogController.State state) {
        int i = this.mActiveStream;
        int i2 = state.activeStream;
        boolean z = i != i2;
        if (z) {
            this.mPrevActiveStream = i;
            this.mActiveStream = i2;
        }
        OplusVolumeRow activeRow = getActiveRow();
        if (!this.mOplusVolumeDialogView.isSliderShowing()) {
            if (z) {
                this.mOplusVolumeDialogView.updateRowsH(activeRow, true);
            }
        } else {
            this.mOplusVolumeDialogView.updateRowsH(activeRow, true);
            if (z) {
                rescheduleTimeoutH();
            }
        }
    }

    public final void rescheduleTimeoutH() {
        this.mHandler.removeMessages(2);
        int i = this.mInterruptTimeoutReasonCode;
        if (i != -1) {
            dismissH$1(i);
            return;
        }
        int iComputeTimeoutH$1 = computeTimeoutH$1();
        synchronized (this.mSafetyWarningRepository.getSafetyWarningLock()) {
            try {
                if (this.mSafetyWarningRepository.getSafetyWarning() == null) {
                    H h = this.mHandler;
                    h.sendMessageDelayed(h.obtainMessage(2, 3, 0), iComputeTimeoutH$1);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.mController.userActivity();
    }

    public final void resetRowTrackingState() {
        for (OplusVolumeRow oplusVolumeRow : this.mRows) {
            if (oplusVolumeRow.getTracking()) {
                LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "reset row(" + oplusVolumeRow.getStream() + ") tracking to false");
                oplusVolumeRow.setTracking(false);
            }
        }
    }

    public void setAutomute(boolean z) {
        if (this.mAutomute == z) {
            return;
        }
        this.mAutomute = z;
        this.mHandler.sendEmptyMessage(4);
    }

    public void setHasTopUi(boolean z) {
        SetTopUiRunnable setTopUiRunnable = this.mSetTopUiRunnable;
        setTopUiRunnable.setHasTopUi(z);
        H h = this.mHandler;
        h.removeCallbacks(setTopUiRunnable);
        if (z) {
            setTopUiRunnable.run();
        } else {
            h.postDelayed(setTopUiRunnable, 1500L);
        }
    }

    public void setScoMaxLevel(boolean z) {
        this.mOplusVolumeController.setScoMaxLevel(z);
    }

    public void setSilentMode(boolean z) {
        if (this.mSilentMode == z) {
            return;
        }
        this.mSilentMode = z;
        this.mHandler.sendEmptyMessage(4);
    }

    public void setVolumeChangedAndFromKey(Boolean bool) {
        this.mOplusVolumeSnackBarController.setVolumeChangedAndFromKey(bool.booleanValue());
    }

    public final boolean shouldBeVisibleH(OplusVolumeRow oplusVolumeRow, OplusVolumeRow oplusVolumeRow2, boolean z) {
        StringBuilder sb = new StringBuilder("shouldBeVisibleH stream=");
        sb.append(oplusVolumeRow2.getStream());
        sb.append(" ,isMusicActive = ");
        AudioDataRepository audioDataRepository = this.mAudioDataRepository;
        sb.append(((AudioDataRepository.AudioState) audioDataRepository.getAudioState().getValue()).isMusicActive);
        sb.append(" ,row.stream=");
        sb.append(oplusVolumeRow.getStream());
        sb.append(" ,row.important=");
        sb.append(oplusVolumeRow.getImportant());
        sb.append(" ,isExpand=");
        sb.append(z);
        sb.append(" ,mAudioMode=");
        sb.append(((AudioDataRepository.AudioState) audioDataRepository.getAudioState().getValue()).getMode());
        sb.append(" ,mShowA11yStream=");
        sb.append(this.mShowA11yStream);
        sb.append(" ,hasActiveDynamicStream=");
        sb.append(this.mDynamic.get(this.mActiveStream, false));
        sb.append(" ,mIsStreamInBluetoothSco=");
        sb.append(((AudioDataRepository.AudioState) audioDataRepository.getAudioState().getValue()).isStreamInBluetoothSco);
        LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sb.toString());
        boolean z2 = oplusVolumeRow.getStream() == oplusVolumeRow2.getStream();
        if (oplusVolumeRow.getStream() == 10) {
            return this.mShowA11yStream;
        }
        return oplusVolumeRow.isMultiMediaVolume || (oplusVolumeRow2.getStream() == 10 && oplusVolumeRow.getStream() == this.mPrevActiveStream) || (!(oplusVolumeRow.getStream() == 4 && z && (audioDataRepository.isAudioCallMode(this.mActiveStream) || this.mShowA11yStream)) && (!(oplusVolumeRow.getStream() == 5 && z && audioDataRepository.isAudioCallMode(this.mActiveStream) && this.mShowA11yStream) && (!(z && oplusVolumeRow.getStream() == 11) && ((!z || oplusVolumeRow.getStream() < 100) && (z2 || ((((AudioDataRepository.AudioState) audioDataRepository.getAudioState().getValue()).isMusicActive && ((oplusVolumeRow2.getStream() == 0 && oplusVolumeRow.getStream() == 3) || (oplusVolumeRow2.getStream() == 3 && audioDataRepository.isAudioCallMode(this.mActiveStream) && oplusVolumeRow.getStream() == 0))) || (z && (oplusVolumeRow.getImportant() || (audioDataRepository.isAudioCallMode(this.mActiveStream) && oplusVolumeRow.getStream() == 0)))))))));
    }

    public final void showH(int i, boolean z) {
        OplusVolumeDialogImpl$$ExternalSyntheticLambda0 oplusVolumeDialogImpl$$ExternalSyntheticLambda0 = this.mUserSwitchDelayedSliderShow;
        VolumeDialogController volumeDialogController = this.mController;
        AudioDataRepository audioDataRepository = this.mAudioDataRepository;
        H h = this.mHandler;
        this.mIsIconClicked = false;
        boolean zShouldShow = this.mOplusVolumeRepository.shouldShow();
        boolean zIsShowVolumeSeekbar = this.mVolumeManager.isShowVolumeSeekbar();
        if (!zShouldShow || !zIsShowVolumeSeekbar) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("showH return  shouldShow:", " ,isShowVolumeSeekbar:", zShouldShow, zIsShowVolumeSeekbar));
            return;
        }
        Trace.traceBegin(8L, "OplusVolumeDialogImpl#showH");
        this.mOplusVolumeDialogView.cancelPendingTerminalHide(true);
        OplusSysUiSchedMgr.getInstance().schedForVolumeDialog(true);
        OplusVolumeRow activeRow = getActiveRow();
        StringBuilder sb = new StringBuilder("showH r=");
        String[] strArr = Events.SHOW_REASONS;
        sb.append(i < strArr.length ? strArr[i] : strArr[0]);
        sb.append(", mExpanded = ");
        sb.append(this.mExpanded);
        sb.append(", activeRow.stream: ");
        sb.append(activeRow.getStream());
        sb.append(", sldierShowing: ");
        sb.append(this.mOplusVolumeDialogView.isSliderShowing());
        sb.append(", singleAppVolumeButtonViewController.isShowing = ");
        sb.append(this.mSingleAppVolumeButtonViewController.isShowing());
        sb.append(", isOplusSuperVolume = ");
        VolumeFeatureOption.INSTANCE.getClass();
        sb.append(VolumeFeatureOption.isOplusSuperVolume());
        sb.append(", mIsMusicActive = ");
        sb.append(((AudioDataRepository.AudioState) audioDataRepository.getAudioState().getValue()).isMusicActive);
        sb.append(", audioMode = ");
        sb.append(((AudioDataRepository.AudioState) audioDataRepository.getAudioState().getValue()).getMode());
        sb.append(", isStreamInBluetoothSco = ");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", preventSeekBar = ", ", mVerticalRowIconParent.visible = ", sb, ((AudioDataRepository.AudioState) audioDataRepository.getAudioState().getValue()).isStreamInBluetoothSco, z);
        sb.append(this.mOplusVolumeDialogView.getMVerticalRowIconParent().getVisibility());
        LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sb.toString());
        OplusVolumeControllerEx oplusVolumeControllerEx = this.mOplusVolumeController;
        if (oplusVolumeControllerEx.getUserSwitch()) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "UserSwitch getState");
            this.mSingleAppVolumeButtonViewController.onUserSwitched();
            this.mOplusVolumeSnackBarController.onUserSwitchChange();
            volumeDialogController.getState();
            oplusVolumeControllerEx.setUserSwitch(false);
            this.mDelaySliderShowForUserSwitch = true;
        } else if (this.firstShowUI) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "firstShowUI getState");
            this.firstShowUI = false;
            volumeDialogController.getState();
        }
        h.removeMessages(1);
        rescheduleTimeoutH();
        if (this.mConfigChanged) {
            this.mOplusVolumeDialogView.initDialog();
            this.mOplusVolumeDialogView.addSingleAppVolumeButtonController(this.mSingleAppVolumeButtonViewController.getContainerView());
            ConfigurableTexts mConfigurableTexts = this.mOplusVolumeDialogView.getMConfigurableTexts();
            if (!mConfigurableTexts.mTexts.isEmpty()) {
                ((TextView) mConfigurableTexts.mTexts.keyAt(0)).post(mConfigurableTexts.mUpdateAll);
            }
            this.mSingleAppVolumeButtonViewController.onConfigurationChanged();
            this.mConfigChanged = false;
        }
        if (!isShowing()) {
            this.mOplusVolumeDialogView.beginGfxScene(this.mContext, 301);
            this.mOplusVolumeDialogView.getVolumeDialog().show();
        }
        this.mDialogShowingForMultiMedia = true;
        if (!z) {
            this.mSingleAppVolumeButtonViewController.hideImmediately(false);
            if (VolumeFeatureOption.isOplusSuperVolume()) {
                this.mIconAbility.updateVolumeRowH(activeRow, false, this.mState);
            }
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isOplusAlertSlider() && (activeRow.getStream() == 2 || activeRow.getStream() == 5)) {
                VolumeDialogController.State state = this.mState;
                if (state == null) {
                    LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "checkShowGuideUi: mState == null return");
                } else {
                    int i2 = ((VolumeDialogController.StreamState) state.states.get(activeRow.getStream())).level;
                    StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i2, "checkShowGuideUi: currentLevel=", ",lastLevel= ");
                    sbM.append(activeRow.getLastLevel());
                    LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sbM.toString());
                    if (this.mState.ringerModeInternal == 2 && i2 == activeRow.getLastLevel() && activeRow.getLastLevel() == RINGER_MODE_MIN_LEVEL) {
                        isNeedToShowToastUi(3);
                    }
                    activeRow.setLastLevel(i2);
                    if (this.mState.ringerModeInternal == 0 && activeRow.getSlider().getDisabled()) {
                        isNeedToShowToastUi(4);
                    }
                    if (this.mState.ringerModeInternal == 1 && activeRow.getSlider().getDisabled()) {
                        if (ThreeKeyStealthModeObserver.getInstance().isStealthModeOn()) {
                            isNeedToShowToastUi(4);
                        } else {
                            isNeedToShowToastUi(5);
                        }
                    }
                }
            }
            if (this.mOplusVolumeDialogView.isSliderShowing() && !this.mOplusVolumeDialogView.isDismissing()) {
                this.mOplusVolumeDialogView.setSliderState(OplusVolumeDialogView.SliderState.SHOWING);
                startThickAnim(true);
                h.removeMessages(8);
                startKeyDownTransAnim(true);
                Message messageObtain = Message.obtain();
                messageObtain.what = 8;
                h.sendMessageDelayed(messageObtain, 300L);
                if (activeRow.getStream() == this.mActiveStream && ((VolumeFeatureOption.isOplusSuperVolume() && activeRow.isSuperVolume) || ((!VolumeFeatureOption.isOplusSuperVolume() && activeRow.getSs().level == activeRow.getSs().levelMax) || activeRow.getSs().level == activeRow.getSlider().getMin()))) {
                    getVolumeListAdapter().startAnimAndBackAnim(activeRow.getStream());
                    getVolumeListAdapter().setAnimEnableState(true);
                }
                Trace.traceEnd(8L);
                return;
            }
            this.mPreventSeekBar = false;
            setHasTopUi(true);
            this.mRequest = this.mOsenseResClient.osenseSetSceneAction(new OsenseSaRequest("", "OSENSE_ACTION_SHOW_VOLUME_DIALOG", 0));
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "showVolumeDialog; mRequest: " + this.mRequest);
            this.mNowScreenOrientation = this.mContext.getResources().getConfiguration().orientation;
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mNowScreenOrientation, LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", new StringBuilder("showH mNowScreenOrientation:"));
            if (this.mDelaySliderShowForUserSwitch) {
                this.mDelaySliderShowForUserSwitch = false;
                h.removeCallbacks(oplusVolumeDialogImpl$$ExternalSyntheticLambda0);
                h.post(oplusVolumeDialogImpl$$ExternalSyntheticLambda0);
            } else {
                this.mOplusVolumeDialogView.showSliderVolume(activeRow);
                volumeDialogController.notifyVisible(true);
                volumeDialogController.getCaptionsComponentState(false);
            }
        } else if (!this.mOplusVolumeDialogView.isSliderShowing() && !this.mSingleAppVolumeButtonViewController.isShowing()) {
            this.mPreventSeekBar = true;
            this.mSingleAppVolumeButtonViewController.show();
        }
        Events.writeEvent(0, new Object[]{Integer.valueOf(i), Boolean.valueOf(this.mKeyguard.isKeyguardLocked())});
        Trace.traceEnd(8L);
        JankUtils.asyncLatencySceneEnd("system_server", 80, "dialogShow");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View, com.coui.appcompat.seekbar.COUIVerticalSeekBar, com.oplus.systemui.volume.OplusVolumeSeekBar] */
    public final void startKeyDownTransAnim(boolean z) {
        ?? onlyRow = getOnlyRow();
        if (onlyRow == 0) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "startKeyDownTransAnim toTrans:" + z + ",[seekbar = null]");
            return;
        }
        boolean z2 = false;
        if (VolumeAnimUtil.enableComplexAnim(onlyRow.mContext)) {
            if (onlyRow.mTransType != z && ((z && !onlyRow.mIsTouching && onlyRow.getHasEnlarge()) || !z)) {
                z2 = true;
            }
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("shouldTrans toTrans:", ",result:", z, z2));
        } else {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "shouldTrans enableComplexAnim=false");
        }
        if (z2) {
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("startKeyDownTransAnim toTrans:", LogUtil.TAG_VOLUME, "OplusVolume-Anim", z);
            float f = (1.0f - this.mOplusVolumeDialogView.getNewContext().getResources().getFloat(2131173974)) * this.mOplusVolumeDialogView.getNewContext().getResources().getDimensionPixelOffset(2131173975) * 0.5f;
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isOplusVolumeKeyInRight()) {
                f *= -1.0f;
            }
            onlyRow.doKeyDownTrans(onlyRow, f, z, z ? 1 : 0);
            EffectiveAnimationView effectiveAnimationView = onlyRow.mIcon;
            if (effectiveAnimationView != null) {
                onlyRow.doKeyDownTrans(effectiveAnimationView, f, z, z ? 1 : 0);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [android.view.View, android.widget.AbsSeekBar, com.coui.appcompat.seekbar.COUIVerticalSeekBar, com.oplus.systemui.volume.OplusVolumeSeekBar] */
    public final void startThickAnim(boolean z) {
        ?? onlyRow = getOnlyRow();
        if (onlyRow == 0) {
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "startThickAnim toThick:" + z + ",[seekbar = null]");
            return;
        }
        if (onlyRow.shouldThick(z)) {
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("startThickAnim toThick:", LogUtil.TAG_VOLUME, "OplusVolume-Anim", z);
            float f = (1.0f - this.mOplusVolumeDialogView.getNewContext().getResources().getFloat(2131173974)) * this.mOplusVolumeDialogView.getNewContext().getResources().getDimensionPixelOffset(2131173975) * 0.5f;
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isOplusVolumeKeyInRight()) {
                f *= -1.0f;
            }
            float f2 = f;
            OplusVolumeDialogImpl$$ExternalSyntheticLambda1 oplusVolumeDialogImpl$$ExternalSyntheticLambda1 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda1();
            oplusVolumeDialogImpl$$ExternalSyntheticLambda1.f$0 = this;
            oplusVolumeDialogImpl$$ExternalSyntheticLambda1.f$1 = z;
            VarHandle.storeStoreFence();
            long j = z ? 450L : 600L;
            COUISpringInterpolator cOUISpringInterpolator = z ? VolumeAnimUtil.VOLUME_START_THICK_INTERPOLATOR : VolumeAnimUtil.VOLUME_END_THICK_INTERPOLATOR;
            boolean z2 = false;
            if (z) {
                ((COUIVerticalSeekBar) onlyRow).mShowText = false;
                onlyRow.invalidate();
            }
            onlyRow.startEnlargeAnim(z ? 1 : 0, j, cOUISpringInterpolator);
            int volumeBarBlurStrengthMinPx = VolumeBlurManager.getVolumeBarBlurStrengthMinPx(onlyRow.getContext());
            int volumeBarBlurStrengthMaxPx = VolumeBlurManager.getVolumeBarBlurStrengthMaxPx(onlyRow.getContext());
            float f3 = z ? 0.01f : 1.0f;
            float f4 = z ? 0.125f : 1.0f;
            if (OplusVolumeSeekBar.sIsDefaultVolumeTheme && VolumeBlurLightParams.isLightEnabled(onlyRow.getContext(), OplusVolumeSeekBar.sIsDefaultVolumeTheme)) {
                z2 = true;
            }
            float f5 = onlyRow.mPlatformBlurStrokeWidthScale;
            float f6 = onlyRow.mPlatformOuterShadowScale;
            OplusVolumeSeekBar$$ExternalSyntheticLambda4 oplusVolumeSeekBar$$ExternalSyntheticLambda4 = new OplusVolumeSeekBar$$ExternalSyntheticLambda4();
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$0 = onlyRow;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$1 = z2;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$2 = f5;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$3 = f3;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$4 = f6;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$5 = f4;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$6 = f2;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$7 = volumeBarBlurStrengthMinPx;
            oplusVolumeSeekBar$$ExternalSyntheticLambda4.f$8 = volumeBarBlurStrengthMaxPx;
            VarHandle.storeStoreFence();
            OplusVolumeSeekBar$$ExternalSyntheticLambda5 oplusVolumeSeekBar$$ExternalSyntheticLambda5 = new OplusVolumeSeekBar$$ExternalSyntheticLambda5();
            oplusVolumeSeekBar$$ExternalSyntheticLambda5.f$0 = onlyRow;
            oplusVolumeSeekBar$$ExternalSyntheticLambda5.f$1 = oplusVolumeDialogImpl$$ExternalSyntheticLambda1;
            oplusVolumeSeekBar$$ExternalSyntheticLambda5.f$2 = z ? 1 : 0;
            oplusVolumeSeekBar$$ExternalSyntheticLambda5.f$3 = volumeBarBlurStrengthMinPx;
            oplusVolumeSeekBar$$ExternalSyntheticLambda5.f$4 = volumeBarBlurStrengthMaxPx;
            VarHandle.storeStoreFence();
            float f7 = z ? f2 : 0.0f;
            OplusVolumeSeekBar$$ExternalSyntheticLambda1 oplusVolumeSeekBar$$ExternalSyntheticLambda1 = new OplusVolumeSeekBar$$ExternalSyntheticLambda1(1);
            oplusVolumeSeekBar$$ExternalSyntheticLambda1.f$0 = onlyRow;
            oplusVolumeSeekBar$$ExternalSyntheticLambda1.f$1 = z ? 1 : 0;
            VarHandle.storeStoreFence();
            VolumeAnimUtil.startViewPropertyAnimation(onlyRow, 0L, j, cOUISpringInterpolator, f7, 0.0f, 0.0f, 0.0f, oplusVolumeSeekBar$$ExternalSyntheticLambda1, oplusVolumeSeekBar$$ExternalSyntheticLambda5, oplusVolumeSeekBar$$ExternalSyntheticLambda4, 1);
            long j2 = j;
            COUISpringInterpolator cOUISpringInterpolator2 = cOUISpringInterpolator;
            EffectiveAnimationView effectiveAnimationView = onlyRow.mIcon;
            if (effectiveAnimationView != null) {
                VolumeAnimUtil.startViewPropertyAnimation(effectiveAnimationView, 0L, j2, cOUISpringInterpolator2, z ? f2 : 0.0f, 0.0f, z ? 0.0f : 1.0f, z ? 0.0f : 1.0f, null, null, null, 13);
            }
            this.mOplusVolumeDialogView.startBtnThickAnim(f2, z);
        }
    }

    public final void startTouchScaleAnim(boolean z, float f, float f2, OplusVolumeDialogImpl$$ExternalSyntheticLambda0 oplusVolumeDialogImpl$$ExternalSyntheticLambda0) {
        VolumePropertyAnimationUtil.setPivotYTop(this.mOplusVolumeDialogView.getMVerticalRowIconParent());
        VolumeAnimUtil.startViewPropertyAnimation(this.mOplusVolumeDialogView.getMVerticalRowIconParent(), 0L, z ? 600L : 700L, z ? VolumeAnimUtil.VOLUME_TOUCH_DOWN_INTERPOLATOR : VolumeAnimUtil.VOLUME_TOUCH_UP_INTERPOLATOR, z ? -f : 0.0f, 0.0f, z ? f2 : 1.0f, 0.0f, null, null, null, 5);
        if (this.mOplusVolumeDialogView.getVolumeListAdapter().getSideShowList() == null || this.mOplusVolumeDialogView.getVolumeListAdapter().getSideShowList().isEmpty()) {
            if (oplusVolumeDialogImpl$$ExternalSyntheticLambda0 != null) {
                oplusVolumeDialogImpl$$ExternalSyntheticLambda0.run();
            }
        } else {
            View view = this.mOplusVolumeDialogView.getVolumeListAdapter().getSideShowList().get(0).getView();
            VolumePropertyAnimationUtil.setPivotYBottom(view);
            VolumeAnimUtil.startViewPropertyAnimation(view, 0L, z ? 600L : 700L, z ? VolumeAnimUtil.VOLUME_TOUCH_DOWN_INTERPOLATOR : VolumeAnimUtil.VOLUME_TOUCH_UP_INTERPOLATOR, z ? -f : 0.0f, 0.0f, z ? f2 : 1.0f, 0.0f, null, oplusVolumeDialogImpl$$ExternalSyntheticLambda0, null, 5);
        }
    }

    public final void trimObsoleteH() {
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "trimObsoleteH");
        Iterator it = this.mRows.iterator();
        while (it.hasNext()) {
            OplusVolumeRow oplusVolumeRow = (OplusVolumeRow) it.next();
            if (oplusVolumeRow.getSs() != null && oplusVolumeRow.getSs().dynamic && !this.mDynamic.get(oplusVolumeRow.getStream())) {
                this.mOplusVolumeDialogView.getVolumeListAdapter().removeItem(oplusVolumeRow);
                it.remove();
            }
        }
    }

    public final void updateAppVolumeAdjust() {
        if (this.mSingleAppVolumeButtonViewController.isShowing() && this.mMultiMediaRepository.getMultiMediaRows().isEmpty()) {
            this.mSingleAppVolumeButtonViewController.hide();
        } else {
            this.mOplusVolumeDialogView.updateAppVolumeAdjust();
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005f  */
    public final void updateSuperVolume(OplusVolumeRow oplusVolumeRow, boolean z, int i, int i2) {
        boolean z2;
        SuperVolumeRepository superVolumeRepository = this.mSuperVolumeRepository;
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolume()) {
            this.mOplusVolumeDialogView.refreshVisibleRow(getActiveRow());
            if (z && i == oplusVolumeRow.getSs().levelMax && oplusVolumeRow.getSlider().getProgress() == oplusVolumeRow.getSlider().getMax() && superVolumeRepository.getSuperVolumeState(oplusVolumeRow.getStream(), i2)) {
                z2 = true;
                if (oplusVolumeRow.getStream() != 0) {
                    int devicesForStream = this.mAudioManager.getDevicesForStream(oplusVolumeRow.getStream());
                    MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(devicesForStream, "devicesForStream: ", LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl");
                    if ((devicesForStream & 2) == 0) {
                        z2 = false;
                    }
                } else if (i2 != 1 && i2 != 2) {
                    z2 = false;
                }
            } else {
                z2 = false;
            }
            StringBuilder sb = new StringBuilder("row.stream = ");
            sb.append(oplusVolumeRow.getStream());
            sb.append(",mActiveStream = ");
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(this.mActiveStream, ",enable = ", ",vlevel = ", sb, z);
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i, ",shouldShowPercentage = ", ",isVerticalLayout = ", sb, z2);
            sb.append(getActiveRow().isVerticalLayout);
            sb.append(",mSuperVolumeRepository.getSuperVolumeState=");
            sb.append(superVolumeRepository.getSuperVolumeState(oplusVolumeRow.getStream(), i2));
            sb.append("row.getSlider().getProgress()=");
            sb.append(oplusVolumeRow.getSlider().getProgress());
            sb.append("row.getSlider().getMax()=");
            sb.append(oplusVolumeRow.getSlider().getMax());
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sb.toString());
            if (!this.mExpanded && this.mOplusVolumeDialogView.isSliderShowing() && oplusVolumeRow.getStream() == this.mActiveStream && oplusVolumeRow.isVerticalLayout && oplusVolumeRow.getSlider().getIsSuperVolumeOn() != z2) {
                oplusVolumeRow.getSlider().setIsSuperVolumeOn(z2);
                this.mOplusVolumeDialogView.setExpandSuperVolumeAnim(oplusVolumeRow, z2);
                superVolumeRepository.showSuperVolume(z2, oplusVolumeRow);
            } else {
                oplusVolumeRow.getSlider().setIsSuperVolumeOn(z2);
                superVolumeRepository.showSuperVolume(z2, oplusVolumeRow);
            }
            oplusVolumeRow.setSuperVolume(z2);
        }
    }

    public final void updateVolumeRowSliderH(final OplusVolumeRow oplusVolumeRow, boolean z, final int i, final int i2, boolean z2) {
        H h = this.mHandler;
        VibrateAbility vibrateAbility = this.mVibrateAbility;
        StringBuilder sb = new StringBuilder("updateVolumeRowSliderH, row = ");
        sb.append(oplusVolumeRow.getStream());
        sb.append(", isMultiMediaVolume = ");
        sb.append(oplusVolumeRow.isMultiMediaVolume);
        sb.append(", tracking = ");
        sb.append(oplusVolumeRow.getTracking());
        sb.append(", isIconClicked = ");
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", KeyguardLogger$.ExternalSyntheticOutline0.m(sb, this.mIsIconClicked, ", enable = ", z));
        if (oplusVolumeRow.isMultiMediaVolume) {
            return;
        }
        oplusVolumeRow.setEnable(z);
        updateSuperVolume(oplusVolumeRow, z, i, i2);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            this.mOplusVolumeDialogView.refreshVisibleRow(getActiveRow());
        }
        if (oplusVolumeRow.getStream() == 5 || oplusVolumeRow.getStream() == 2 || oplusVolumeRow.getStream() == 3) {
            if (z && oplusVolumeRow.getSlider().getDisabled()) {
                oplusVolumeRow.getSlider().setDisabled(false);
            }
            if (!z && !oplusVolumeRow.getSlider().getDisabled()) {
                oplusVolumeRow.getSlider().setDisabled(true);
                LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "updateVolumeRowSliderH notification ring music setProgress " + oplusVolumeRow.getNameText().getText().toString() + " 0");
                if (oplusVolumeRow.getAnim() != null && oplusVolumeRow.getAnim().isRunning()) {
                    oplusVolumeRow.getAnim().cancel();
                }
                if (oplusVolumeRow.getSlider().mIsStartTrack) {
                    oplusVolumeRow.getSlider().setStopTrackingTouch();
                }
                oplusVolumeRow.getSlider().setProgress(0);
            }
        }
        oplusVolumeRow.getSlider().setEnabled(z || (this.mExpanded && (oplusVolumeRow.getStream() == 5 || oplusVolumeRow.getStream() == 2 || oplusVolumeRow.getStream() == 3)));
        if (oplusVolumeRow.getStream() == this.mActiveStream) {
            oplusVolumeRow.getSlider().requestFocus();
        }
        if (oplusVolumeRow.getTracking()) {
            return;
        }
        int progress = oplusVolumeRow.getSlider().getProgress();
        int impliedLevel = OplusVolumeRow.Companion.getImpliedLevel(oplusVolumeRow.getSlider(), progress);
        StringBuilder sb2 = new StringBuilder("updateVolumeRowSliderH,vibrateByLMVibrator,row= ");
        sb2.append(oplusVolumeRow.getStream());
        sb2.append(", progress=");
        sb2.append(progress);
        sb2.append(" level = ");
        RectList$.ExternalSyntheticOutline0.m(impliedLevel, i, "  vLevle=", " row.lastVlevel=", sb2);
        sb2.append(oplusVolumeRow.getLastVlevel());
        sb2.append(", isVibrated=");
        sb2.append(z2);
        LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sb2.toString());
        boolean z3 = oplusVolumeRow.getView().getVisibility() == 0;
        boolean z4 = SystemClock.uptimeMillis() - oplusVolumeRow.getUserAttempt() < 1000;
        h.removeMessages(3, oplusVolumeRow);
        if (this.mOplusVolumeDialogView.isSliderShowing() && z3 && z4) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isOplusAlertSlider() || i != 0) {
                LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "inGracePeriod");
                h.sendMessageAtTime(h.obtainMessage(3, oplusVolumeRow), oplusVolumeRow.getUserAttempt() + 1000);
                return;
            }
        }
        if (i != impliedLevel || (oplusVolumeRow.getAnim() != null && oplusVolumeRow.getAnim().isRunning())) {
            final int i3 = i * 100;
            if (i == impliedLevel && progress == i3 && (oplusVolumeRow.getAnim() == null || !oplusVolumeRow.getAnim().isRunning())) {
                return;
            }
            if (progress != i3) {
                if (oplusVolumeRow.getStream() == this.mActiveStream && this.mOplusVolumeDialogView.isSliderShowing() && oplusVolumeRow.getLastVlevel() != i && !z2) {
                    LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", Fragment$5$.ExternalSyntheticOutline0.m(new StringBuilder("row.ss.levelMin="), oplusVolumeRow.getSs().levelMin, i, " vlevel="));
                    if (this.mIsIconClicked) {
                        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "icon clicked, no need vibrate");
                        this.mIsIconClicked = false;
                    } else if (oplusVolumeRow.getSs().levelMax == i || oplusVolumeRow.getSs().levelMin == i) {
                        vibrateAbility.vibrateByLMVibrator(153, false);
                        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "updateVolumeRowSliderH, vibrateByLMVibrator WaveformEffect=153");
                    } else {
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isLuxunVibratorSupport()) {
                            VibrateAbility.Companion companion = VibrateAbility.Companion;
                            int i4 = oplusVolumeRow.getSs().levelMax;
                            companion.getClass();
                            int vibratorStrengthWithLimit = VibrateAbility.Companion.getVibratorStrengthWithLimit(i, i4, ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION, 2400);
                            vibrateAbility.vibrateByLMVibrator(152, vibratorStrengthWithLimit, true);
                            ClipboardOverlayView$.ExternalSyntheticOutline0.m(vibratorStrengthWithLimit, LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", new StringBuilder("updateVolumeRowSliderH, vibrateByLMVibrator WaveformEffect=152strength ="));
                        } else {
                            vibrateAbility.vibrateByLMVibrator(152, true);
                            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "updateVolumeRowSliderH, vibrateByLMVibrator WaveformEffect=152");
                        }
                    }
                }
                if (!this.mOplusVolumeDialogView.isSliderShowing() || !z3 || !oplusVolumeRow.getSlider().isShown()) {
                    if (oplusVolumeRow.getAnim() != null) {
                        oplusVolumeRow.getAnim().cancel();
                    }
                    LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "updateVolumeRowSliderH else setProgress " + oplusVolumeRow.getNameText().getText().toString() + " " + i3);
                    oplusVolumeRow.getSlider().setProgress(i3);
                    if (i3 == oplusVolumeRow.getSlider().getMax()) {
                        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "updateVolumeRowSliderH row " + oplusVolumeRow.getStream() + " " + i3);
                        updateSuperVolume(oplusVolumeRow, z, i, i2);
                    }
                } else {
                    if (oplusVolumeRow.getAnim() != null && oplusVolumeRow.getAnim().isRunning() && oplusVolumeRow.getAnimTargetProgress() == i3) {
                        return;
                    }
                    if (oplusVolumeRow.getAnim() == null) {
                        oplusVolumeRow.setAnim(ObjectAnimator.ofInt(oplusVolumeRow.getSlider(), "progress", progress, i3));
                        COUISlideDeleteAnimation$.ExternalSyntheticOutline0.m(0.17f, 0.17f, 0.0f, 1.0f, oplusVolumeRow.getAnim());
                    } else {
                        oplusVolumeRow.getAnim().removeAllListeners();
                        oplusVolumeRow.getAnim().cancel();
                        oplusVolumeRow.getAnim().setIntValues(progress, i3);
                    }
                    oplusVolumeRow.setAnimTargetProgress(i3);
                    oplusVolumeRow.getAnim().setDuration(VolumeAnimUtil.DURATION_UPDATE_ANIMATION);
                    oplusVolumeRow.getSlider().stopPhysicsMove();
                    oplusVolumeRow.getSlider().endCustomDeformation();
                    oplusVolumeRow.getSlider().setAnimating(true);
                    oplusVolumeRow.getAnim().addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.volume.OplusVolumeDialogImpl.3
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            oplusVolumeRow.getSlider().setAnimating(false);
                            if (oplusVolumeRow.getAnimTargetProgress() == oplusVolumeRow.getSlider().getMax()) {
                                StringBuilder sb3 = new StringBuilder("updateVolumeRowSliderH onAnimationEnd row ");
                                sb3.append(oplusVolumeRow.getStream());
                                sb3.append(" ");
                                ClipboardOverlayView$.ExternalSyntheticOutline0.m(i3, LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sb3);
                                OplusVolumeDialogImpl oplusVolumeDialogImpl = OplusVolumeDialogImpl.this;
                                OplusVolumeRow oplusVolumeRow2 = oplusVolumeRow;
                                oplusVolumeDialogImpl.updateSuperVolume(oplusVolumeRow2, oplusVolumeRow2.getEnable(), i, i2);
                            }
                        }
                    });
                    oplusVolumeRow.getAnim().start();
                }
            }
            oplusVolumeRow.setLastVlevel(i);
        }
    }

    public final void vibrate() {
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isLinearVibratorSupport()) {
            this.mController.vibrate(VibrationEffect.get(5));
        } else {
            this.mVibrateAbility.vibrateByLMVibrator(2, true);
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "vibrate, vibrateByLMVibrator WaveformEffect=2");
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0071  */
    @Override // com.oplus.systemui.volume.OplusVolumeDialogInterface
    public final void onStateChangedH(VolumeDialogController.State state, int i) {
        boolean z;
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onstateChangedH : state ,stream: ", ", isIconClicked = "), this.mIsIconClicked);
        if (state == null) {
            return;
        }
        VolumeDialogController.State state2 = this.mState;
        if (state2 != null) {
            int i2 = state2.ringerModeInternal;
            int i3 = state.ringerModeInternal;
            if (i2 != i3) {
                z = true;
                if (i3 == 1) {
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isOplusActionButton() || FeatureOption.isSupportGameHyperButton() || this.mState.ringerModeInternal == -1 || CustomizeFeatureOption.sIsSupportThreekeyVibration) {
                        z = false;
                    } else {
                        LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "threekeyFeatureVibrator  isLinearVibratorSupport:" + FeatureOption.isLinearVibratorSupport());
                        if (FeatureOption.isLinearVibratorSupport()) {
                            this.mVibrateAbility.vibrateByLMVibrator(2, true);
                            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "onStateChangedH state, stream, vibrateByLMVibrator WaveformEffect=2");
                        } else {
                            this.mController.vibrate(VibrationEffect.get(5));
                        }
                    }
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        onDistrubModeChange(state);
        refreshRows(state);
        for (OplusVolumeRow oplusVolumeRow : this.mRows) {
            if (oplusVolumeRow.getStream() == i) {
                this.mIconAbility.updateVolumeRowH(oplusVolumeRow, z, this.mState);
            }
        }
    }
}
