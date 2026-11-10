package com.oplus.systemui.shutdown;

import android.R;
import android.animation.AnimatorSet;
import android.app.Dialog;
import android.app.IActivityManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.app.trust.TrustManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Binder;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.EmergencyAffordanceManager;
import com.android.internal.util.ScreenshotHelper;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.MultiListLayout;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.model.SysUiState;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.plugins.GlobalActionsPanelPlugin;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shutdown.GlobalActionsDialogEx;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.topui.TopUiController;
import com.android.systemui.util.RingerModeTracker;
import com.android.systemui.util.leak.RotationUtils;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.os.OplusBuild;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.util.OplusEmergencySosUtils;
import com.oplus.systemui.wallpaperblur.WallpaperBlurDrawable;
import com.oplus.systemui.wallpaperblur.WallpaperBlurManager;
import com.oplus.transition.OplusAnimationController;
import com.oplus.view.OplusWindowAttributesManager;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.GlobalActionsUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusGlobalActionsDialog extends GlobalActionsDialogEx implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener, ConfigurationController.ConfigurationListener, GlobalActionsPanelPlugin.Callbacks, LifecycleOwner {
    static final String GLOBAL_ACTION_KEY_POWER = "power";
    public static OplusShutdownView mOplusShutdownView;
    public static ConstraintLayout mOplusShutdownViewContainer;
    public MyAdapter mAdapter;
    public final AudioManager mAudioManager;
    public AnonymousClass3 mAuthListener;
    public Runnable mAuthRecoverAction;
    public final Executor mBackgroundExecutor;
    public final AnonymousClass12 mBroadcastReceiver;
    public CancellationSignal mCancelSignal;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    protected ActionsDialog mDialog;
    public int mDialogPressDelay;
    public final IDreamManager mDreamManager;
    public final BaseGlobalActionsDialogExt mExt;
    public final AnonymousClass13 mHandler;
    public final boolean mHasVibrator;
    public final IActivityManager mIActivityManager;
    public final KeyguardStateController mKeyguardStateController;
    public final LockPatternUtils mLockPatternUtils;
    public final Handler mMainHandler;
    public final MetricsLogger mMetricsLogger;
    public final AnonymousClass3 mOnCancel;
    public final AnonymousClass3 mOnEmergency;
    public final AnonymousClass3 mOnReboot;
    public final AnonymousClass3 mOnShutdown;
    public final OplusSubDisplayUtils mOplusSubDisplayUtils;
    public final PanelInteractor mPanelInteractor;
    public final Resources mResources;
    public final RingerModeTracker mRingerModeTracker;
    public int mRotation;
    public final OplusSeparateNotificationAndQSState mSeparateNotificationAndQSState;
    public final OplusSeparateQSManager mSeparateQSManager;
    boolean mShowLockScreenCardsAndControls;
    public ShutdownViewControl mShutdownViewControl;
    public Action mSilentModeAction;
    public int mSmallestScreenWidthDp;
    public final AnonymousClass15 mStateChangedListener;
    public final IStatusBarService mStatusBarService;
    public final SysUiState mSysUiState;
    public final SysuiColorExtractor mSysuiColorExtractor;
    public final TelecomManager mTelecomManager;
    public final TopUiController mTopUiController;
    public final TrustManager mTrustManager;
    public final UiEventLogger mUiEventLogger;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public final GlobalActions.GlobalActionsManager mWindowManagerFuncs;
    public final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);
    protected final ArrayList<Action> mItems = new ArrayList<>();
    protected final ArrayList<Action> mOverflowItems = new ArrayList<>();
    protected final ArrayList<Action> mPowerItems = new ArrayList<>();
    public boolean mKeyguardShowing = false;
    public boolean mDeviceProvisioned = false;

    /* JADX INFO: renamed from: com.oplus.systemui.shutdown.OplusGlobalActionsDialog$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements KeyguardStateController.Callback {
        public /* synthetic */ OplusGlobalActionsDialog this$0;

        public final void onUnlockedChanged() {
            OplusGlobalActionsDialog oplusGlobalActionsDialog = this.this$0;
            if (oplusGlobalActionsDialog.mDialog != null) {
                oplusGlobalActionsDialog.mKeyguardStateController.getClass();
                ActionsDialog actionsDialog = oplusGlobalActionsDialog.mDialog;
                int i = ActionsDialog.$r8$clinit;
                actionsDialog.getClass();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shutdown.OplusGlobalActionsDialog$15, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass15 implements DeviceStateController.StateChangedListener {
        public /* synthetic */ OplusGlobalActionsDialog this$0;

        @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
        public final void onStateChanged(int i) {
            OplusGlobalActionsDialog oplusGlobalActionsDialog = this.this$0;
            if (i == 2 || i == 3 || i == 0) {
                ActionsDialog actionsDialog = oplusGlobalActionsDialog.mDialog;
                if (actionsDialog != null && actionsDialog.isShowing() && oplusGlobalActionsDialog.mOplusSubDisplayUtils.getSupportSubDisplay()) {
                    oplusGlobalActionsDialog.dismissDialog();
                    return;
                }
                if (i == 2 || i == 3) {
                    AnonymousClass13 anonymousClass13 = oplusGlobalActionsDialog.mHandler;
                    OplusGlobalActionsDialog$15$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusGlobalActionsDialog$15$.ExternalSyntheticLambda0(0);
                    externalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    anonymousClass13.post(externalSyntheticLambda0);
                    return;
                }
                AnonymousClass13 anonymousClass14 = oplusGlobalActionsDialog.mHandler;
                OplusGlobalActionsDialog$15$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new OplusGlobalActionsDialog$15$.ExternalSyntheticLambda0(1);
                externalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                anonymousClass14.post(externalSyntheticLambda1);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shutdown.OplusGlobalActionsDialog$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements ShutdownViewControl$OnCancelListener, ShutdownViewControl$OnEmergencyListener, AuthenticationListener, ShutdownViewControl$OnShutdownListener, ShutdownViewControl$OnRebootListener {
        public /* synthetic */ OplusGlobalActionsDialog this$0;

        public boolean handleAuthentication(Runnable runnable, Runnable runnable2) {
            OplusGlobalActionsDialog oplusGlobalActionsDialog = this.this$0;
            boolean zIsEnable = ShutdownBiometricPrompt.isEnable(oplusGlobalActionsDialog.mContext);
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("handleAuthentication isEnable = ", LogUtil.TAG_KEYGUARD, "GlobalActionsDialog", zIsEnable);
            if (!zIsEnable) {
                return false;
            }
            ActionsDialog actionsDialog = oplusGlobalActionsDialog.mDialog;
            if (actionsDialog != null) {
                actionsDialog.mShutDownOrRestart = true;
            }
            oplusGlobalActionsDialog.mAuthRecoverAction = runnable2;
            oplusGlobalActionsDialog.mCancelSignal = new CancellationSignal();
            ShutdownBiometricPrompt shutdownBiometricPromptCreate = ShutdownBiometricPrompt.create(oplusGlobalActionsDialog.mContext);
            CancellationSignal cancellationSignal = oplusGlobalActionsDialog.mCancelSignal;
            1 r4 = new 1();
            r4.this$1 = this;
            r4.val$action = runnable;
            r4.val$recoverAction = runnable2;
            VarHandle.storeStoreFence();
            shutdownBiometricPromptCreate.authenticate(false, cancellationSignal, r4);
            return true;
        }

        public void onCancel() {
            OplusGlobalActionsDialog oplusGlobalActionsDialog = this.this$0;
            ActionsDialog actionsDialog = oplusGlobalActionsDialog.mDialog;
            if (actionsDialog == null || !actionsDialog.mShutDownOrRestart) {
                oplusGlobalActionsDialog.dismissDialog();
            } else {
                LogUtil.d(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "mOnCancel mShutDownOrRestart is true");
            }
        }

        public void onEmergency() {
            OplusGlobalActionsDialog oplusGlobalActionsDialog = this.this$0;
            if (!oplusGlobalActionsDialog.mKeyguardStateController.mShowing) {
                oplusGlobalActionsDialog.mPanelInteractor.forceCollapsePanels();
            } else if (((Boolean) oplusGlobalActionsDialog.mSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
                OplusGlobalActionsDialog$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusGlobalActionsDialog$.ExternalSyntheticLambda1(1);
                externalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(externalSyntheticLambda1);
            } else {
                oplusGlobalActionsDialog.mPanelInteractor.animateCollapseQs();
            }
            try {
                oplusGlobalActionsDialog.mContext.startActivityAsUser(OplusEmergencySosUtils.createEmergencyDialerIntent(oplusGlobalActionsDialog.mContext, "from_power_page"), UserHandle.CURRENT);
            } catch (Exception e) {
                LogUtil.e(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "OnEmergencyListener, startActivityAsUser error, cannot launch emergency " + e);
            }
            ActionsDialog actionsDialog = oplusGlobalActionsDialog.mDialog;
            if (actionsDialog != null) {
                actionsDialog.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Action {
        View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater);

        Drawable getIcon(Context context);

        CharSequence getMessage();

        int getMessageResId();

        boolean isEnabled();

        void onPress();

        default boolean shouldShow() {
            return true;
        }

        boolean showBeforeProvisioning();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class ActionsDialog extends Dialog implements DialogInterface, ColorExtractor.OnColorsChangedListener {
        public static final /* synthetic */ int $r8$clinit = 0;
        public MyAdapter mAdapter;
        public ScrimDrawable mAlphaLayerDrawable;
        public AnimatorSet mAnimatorSetOnShow;
        public Drawable mBackgroundDrawable;
        public View mBlurBackgroundView;
        public SysuiColorExtractor mColorExtractor;
        public ViewGroup mContainer;
        public Context mContext;
        public Handler mDialogHandler;
        public TopUiController mDialogTopUiController;
        public MultiListLayout mGlobalActionsLayout;
        public boolean mKeyguardShowing;
        public OplusGlobalActionsDialog$.ExternalSyntheticLambda1 mOnRotateCallback;
        public float mScrimAlpha;
        public boolean mShowing;
        public boolean mShutDownOrRestart;
        public ShutdownFlashLightHelper mShutdownFlashLightHelper;
        public IStatusBarService mStatusBarService;
        public SysUiState mSysUiState;
        public IBinder mToken;
        public GlobalActions.GlobalActionsManager mWindowManagerFuncs;

        /* JADX INFO: renamed from: -$$Nest$mcompleteDismiss, reason: not valid java name */
        public static void m371$$Nest$mcompleteDismiss(ActionsDialog actionsDialog) {
            actionsDialog.mShowing = false;
            actionsDialog.setRotationSuggestionsEnabled(true);
            actionsDialog.mDialogTopUiController.setRequestTopUi(false, "GlobalActionsDialog");
            actionsDialog.mSysUiState.setFlag(32768L, false).commitUpdate(actionsDialog.mContext.getDisplayId());
            if (Dependency.sDependency.getDependencyInner(KeyguardStateController.class) != null && !((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
                ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().showOrHideFingerprintIconTemporarily(true, "GlobalActionsDialog, GlobalActionDialog completeDismiss");
            }
            actionsDialog.mShutdownFlashLightHelper.stop();
            if (ScrimUtil.isBAnimLevel() && !ScrimUtil.isWallpaperBlurDisable()) {
                ((WallpaperBlurManager) DependencyEx.sDependency.getDependency(WallpaperBlurManager.class)).releaseWallpaperBlurDrawableForView(actionsDialog.mBlurBackgroundView);
            }
            super.dismiss();
            Handler handler = actionsDialog.mDialogHandler;
            if (handler == null) {
                ShutdownBlurManager.INSTANCE.tryTakeScreenshotForShutdownBlur(actionsDialog.mContext, false);
                return;
            }
            OplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1 oplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1 = new OplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1(1);
            oplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1.f$0 = actionsDialog;
            VarHandle.storeStoreFence();
            handler.post(oplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1);
        }

        private int getGlobalActionsContainerWidth() {
            return this.mContext.getResources().getDimensionPixelSize(2131168375);
        }

        private int getLandscapeOffset() {
            if (RotationUtils.getRotation(this.mContext) == 1) {
                return getStatusBarHeight();
            }
            return 0;
        }

        private int getPortraitOffset() {
            return getStatusBarHeight();
        }

        private int getStatusBarHeight() {
            int identifier = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", OplusAnimationController.PACKAGE_SYSTEM);
            if (identifier > 0) {
                return this.mContext.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        }

        private void setManuallyLockedOn(boolean z) {
            GlobalActionsUtils.setManuallyLockedOn(z);
        }

        private void setRotationSuggestionsEnabled(boolean z) {
            try {
                this.mStatusBarService.disable2ForUser(z ? 0 : 16, this.mToken, this.mContext.getPackageName(), Binder.getCallingUserHandle().getIdentifier());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        @Override // android.app.Dialog, android.content.DialogInterface
        public final void dismiss() {
            if (this.mShutDownOrRestart) {
                this.mShutDownOrRestart = false;
                return;
            }
            OplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1 oplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1 = new OplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1(0);
            oplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            if (this.mShowing) {
                this.mShowing = false;
                oplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1.run();
            }
            if (Dependency.sDependency.getDependencyInner(KeyguardStateController.class) != null && !((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
                ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().showOrHideFingerprintIconTemporarily(true, "GlobalActionsDialog, GlobalActionDialog dismiss");
            }
            this.mShutdownFlashLightHelper.stop();
            Log.d("GlobalActionsDialog", "GlobalActionsDialog dismiss");
        }

        public final void initializeLayout() {
            setContentView(2131558861);
            LogUtil.i(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "GlobalActionsDialog initializeLayout");
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content);
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
            viewGroup2.setClipChildren(false);
            viewGroup2.setClipToPadding(false);
            MultiListLayout multiListLayoutFindViewById = findViewById(2131363244);
            this.mGlobalActionsLayout = multiListLayoutFindViewById;
            multiListLayoutFindViewById.setListViewAccessibilityDelegate(new 1(this));
            MultiListLayout multiListLayout = this.mGlobalActionsLayout;
            OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda3();
            externalSyntheticLambda3.f$0 = this;
            VarHandle.storeStoreFence();
            multiListLayout.setRotationListener(externalSyntheticLambda3);
            this.mGlobalActionsLayout.setAdapter(this.mAdapter);
            ViewGroup viewGroup3 = (ViewGroup) findViewById(2131363239);
            this.mContainer = viewGroup3;
            if (viewGroup3 != null) {
                OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda4();
                externalSyntheticLambda4.f$0 = this;
                VarHandle.storeStoreFence();
                viewGroup3.setOnClickListener(externalSyntheticLambda4);
                OplusGlobalActionsDialog.mOplusShutdownView.setContainerView(this.mContainer);
            }
            View decorView = getWindow().getDecorView();
            this.mBlurBackgroundView = decorView;
            if (this.mBackgroundDrawable == null) {
                this.mScrimAlpha = 1.0f;
                this.mBackgroundDrawable = ShutdownBlurManager.INSTANCE.getShutdownBackground(this.mContext, decorView, this.mDialogHandler);
            }
            Drawable drawable = this.mBackgroundDrawable;
            if (!(drawable instanceof AutoBlurDrawable) && !(drawable instanceof WallpaperBlurDrawable)) {
                getWindow().setBackgroundDrawable(this.mBackgroundDrawable);
                return;
            }
            ScrimDrawable scrimDrawable = this.mAlphaLayerDrawable;
            if (scrimDrawable == null) {
                ScrimDrawable scrimDrawable2 = new ScrimDrawable();
                this.mAlphaLayerDrawable = scrimDrawable2;
                scrimDrawable2.setColor(-16777216, false);
                this.mAlphaLayerDrawable.setAlpha(0);
                View view = this.mBlurBackgroundView;
                2 r1 = new 2();
                r1.this$0 = this;
                VarHandle.storeStoreFence();
                view.addOnAttachStateChangeListener(r1);
            } else {
                scrimDrawable.setAlpha(0);
            }
            ViewGroup viewGroup4 = this.mContainer;
            if (viewGroup4 != null) {
                viewGroup4.setBackground(this.mAlphaLayerDrawable);
            }
            OplusGlobalActionsDialog.mOplusShutdownView.setActionsBackgroundDrawable(this.mAlphaLayerDrawable);
            this.mBlurBackgroundView.setBackground(this.mBackgroundDrawable);
        }

        public final void onColorsChanged(ColorExtractor colorExtractor, int i) {
            if (this.mKeyguardShowing) {
                if ((i & 2) != 0) {
                    updateColors(colorExtractor.getColors(2), true);
                }
            } else if ((i & 1) != 0) {
                updateColors(colorExtractor.getColors(1), true);
            }
        }

        @Override // android.app.Dialog
        public final void onStart() {
            setCanceledOnTouchOutside(true);
            super.onStart();
            this.mGlobalActionsLayout.updateList();
            if (this.mBackgroundDrawable instanceof ScrimDrawable) {
                this.mColorExtractor.addOnColorsChangedListener(this);
                updateColors(this.mColorExtractor.getNeutralColors(), false);
            }
        }

        @Override // android.app.Dialog
        public final void onStop() {
            super.onStop();
            this.mColorExtractor.removeOnColorsChangedListener(this);
        }

        public final void refreshDialog() {
            LogUtil.i(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "GlobalActionsDialog refreshDialog");
            initializeLayout();
            this.mGlobalActionsLayout.updateList();
        }

        public void setAuthenticationListener(AuthenticationListener authenticationListener) {
        }

        public void setKeyguardShowing(boolean z) {
            this.mKeyguardShowing = z;
        }

        public void setShutdownView(OplusShutdownView oplusShutdownView) {
            OplusGlobalActionsDialog.mOplusShutdownView = oplusShutdownView;
        }

        @Override // android.app.Dialog
        public final void show() {
            ShutdownBlurManager.INSTANCE.tryTakeScreenshotForShutdownBlur(this.mContext, true);
            super.show();
            this.mShowing = true;
            this.mDialogTopUiController.setRequestTopUi(true, "GlobalActionsDialog");
            this.mSysUiState.setFlag(32768L, true).commitUpdate(this.mContext.getDisplayId());
            ViewGroup viewGroup = (ViewGroup) this.mGlobalActionsLayout.getRootView();
            OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            viewGroup.setOnApplyWindowInsetsListener(externalSyntheticLambda0);
            ShutdownFlashLightHelper shutdownFlashLightHelper = this.mShutdownFlashLightHelper;
            if (shutdownFlashLightHelper.isFlavorOneShutdownFlashLightEnable()) {
                LogUtil.d(LogUtil.TAG_GLOBALACTIONS, "ShutdownFlashLightHelper", "message show light");
                if (shutdownFlashLightHelper.mLightBgHandler == null) {
                    synchronized (shutdownFlashLightHelper) {
                        try {
                            if (shutdownFlashLightHelper.mLightBgHandler == null) {
                                HandlerThread handlerThread = new HandlerThread("ShutDownLight");
                                shutdownFlashLightHelper.mHandlerThread = handlerThread;
                                handlerThread.start();
                                shutdownFlashLightHelper.mLightBgHandler = new ShutdownFlashLightHelper.H(shutdownFlashLightHelper, shutdownFlashLightHelper.mHandlerThread.getLooper());
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                shutdownFlashLightHelper.mLightBgHandler.sendEmptyMessage(4);
            }
        }

        public final void updateColors(ColorExtractor.GradientColors gradientColors, boolean z) {
            ScrimDrawable scrimDrawable = this.mBackgroundDrawable;
            if (scrimDrawable instanceof ScrimDrawable) {
                scrimDrawable.setColor(this.mContext.getResources().getColor(2131103009), z);
                View decorView = getWindow().getDecorView();
                if (gradientColors.supportsDarkText()) {
                    decorView.setSystemUiVisibility(8208);
                } else {
                    decorView.setSystemUiVisibility(0);
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface AuthenticationListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MyAdapter extends MultiListLayout.MultiListAdapter {
        public MyAdapter() {
        }

        public final boolean areAllItemsEnabled() {
            return false;
        }

        public final int countItems$1(boolean z) {
            int i = 0;
            for (int i2 = 0; i2 < OplusGlobalActionsDialog.this.mItems.size(); i2++) {
                OplusGlobalActionsDialog.this.mItems.get(i2).getClass();
                if (!z) {
                    i++;
                }
            }
            return i;
        }

        public final int countSeparatedItems() {
            return countItems$1(true);
        }

        public int getCount() {
            return countItems$1(false) + countItems$1(true);
        }

        /* JADX INFO: renamed from: getItem, reason: merged with bridge method [inline-methods] */
        public final Action m372getItem(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < OplusGlobalActionsDialog.this.mItems.size(); i3++) {
                Action action = OplusGlobalActionsDialog.this.mItems.get(i3);
                if (OplusGlobalActionsDialog.this.shouldShowAction(action)) {
                    if (i2 == i) {
                        return action;
                    }
                    i2++;
                }
            }
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "position ", " out of range of showable actions, filtered count=");
            sbM.append(getCount());
            sbM.append(", keyguardshowing=");
            sbM.append(OplusGlobalActionsDialog.this.mKeyguardShowing);
            sbM.append(", provisioned=");
            sbM.append(OplusGlobalActionsDialog.this.mDeviceProvisioned);
            throw new IllegalArgumentException(sbM.toString());
        }

        public final long getItemId(int i) {
            return i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            Action actionM372getItem = m372getItem(i);
            Context context = OplusGlobalActionsDialog.this.mContext;
            View viewCreate = actionM372getItem.create(context, view, viewGroup, LayoutInflater.from(context));
            OplusGlobalActionsDialog$MyAdapter$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusGlobalActionsDialog$MyAdapter$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = this;
            externalSyntheticLambda0.f$1 = i;
            VarHandle.storeStoreFence();
            viewCreate.setOnClickListener(externalSyntheticLambda0);
            if (actionM372getItem instanceof LongPressAction) {
                OplusGlobalActionsDialog$MyAdapter$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusGlobalActionsDialog$MyAdapter$.ExternalSyntheticLambda1(0);
                externalSyntheticLambda1.f$0 = this;
                externalSyntheticLambda1.f$1 = i;
                VarHandle.storeStoreFence();
                viewCreate.setOnLongClickListener(externalSyntheticLambda1);
            }
            return viewCreate;
        }

        public final boolean isEnabled(int i) {
            return m372getItem(i).isEnabled();
        }

        public final boolean shouldBeSeparated(int i) {
            m372getItem(i).getClass();
            return false;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SilentModeToggleAction extends ToggleAction {
        public /* synthetic */ OplusGlobalActionsDialog this$0;

        @Override // com.oplus.systemui.shutdown.OplusGlobalActionsDialog.Action
        public final boolean showBeforeProvisioning() {
            return false;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class ToggleAction implements Action {
        public int mDisabledIconResid;
        public int mDisabledStatusMessageResId;
        public int mEnabledIconResId;
        public int mEnabledStatusMessageResId;
        public ToggleState mState;

        private int getIconResId() {
            ToggleState toggleState = this.mState;
            return (toggleState == ToggleState.On || toggleState == ToggleState.TurningOn) ? this.mEnabledIconResId : this.mDisabledIconResid;
        }

        @Override // com.oplus.systemui.shutdown.OplusGlobalActionsDialog.Action
        public final View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            View viewInflate = layoutInflater.inflate(2131558859, viewGroup, false);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.icon);
            TextView textView = (TextView) viewInflate.findViewById(R.id.message);
            boolean zIsEnabled = isEnabled();
            if (textView != null) {
                textView.setText(getMessageResId());
                textView.setEnabled(zIsEnabled);
                textView.setSelected(true);
            }
            if (imageView != null) {
                imageView.setImageDrawable(context.getDrawable(getIconResId()));
                imageView.setEnabled(zIsEnabled);
            }
            viewInflate.setEnabled(zIsEnabled);
            return viewInflate;
        }

        @Override // com.oplus.systemui.shutdown.OplusGlobalActionsDialog.Action
        public final Drawable getIcon(Context context) {
            return context.getDrawable(getIconResId());
        }

        @Override // com.oplus.systemui.shutdown.OplusGlobalActionsDialog.Action
        public CharSequence getMessage() {
            return null;
        }

        @Override // com.oplus.systemui.shutdown.OplusGlobalActionsDialog.Action
        public int getMessageResId() {
            ToggleState toggleState = this.mState;
            return (toggleState == ToggleState.On || toggleState == ToggleState.TurningOn) ? this.mEnabledStatusMessageResId : this.mDisabledStatusMessageResId;
        }

        @Override // com.oplus.systemui.shutdown.OplusGlobalActionsDialog.Action
        public final boolean isEnabled() {
            return !this.mState.inTransition();
        }

        @Override // com.oplus.systemui.shutdown.OplusGlobalActionsDialog.Action
        public final void onPress() {
            if (this.mState.inTransition()) {
                Log.w("GlobalActionsDialog", "shouldn't be able to toggle when in transition");
                return;
            }
            ToggleState toggleState = this.mState;
            ToggleState toggleState2 = ToggleState.On;
            boolean z = toggleState != toggleState2;
            AudioManager audioManager = ((SilentModeToggleAction) this).this$0.mAudioManager;
            if (z) {
                audioManager.setRingerMode(0);
            } else {
                audioManager.setRingerMode(2);
            }
            if (!z) {
                toggleState2 = ToggleState.Off;
            }
            this.mState = toggleState2;
        }
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [com.oplus.systemui.shutdown.OplusGlobalActionsDialog$13] */
    public OplusGlobalActionsDialog(Context context, GlobalActions.GlobalActionsManager globalActionsManager, AudioManager audioManager, IDreamManager iDreamManager, DevicePolicyManager devicePolicyManager, LockPatternUtils lockPatternUtils, BroadcastDispatcher broadcastDispatcher, TelephonyListenerManager telephonyListenerManager, GlobalSettings globalSettings, SecureSettings secureSettings, VibratorHelper vibratorHelper, Resources resources, ConfigurationController configurationController, UserTracker userTracker, KeyguardStateController keyguardStateController, UserManager userManager, TrustManager trustManager, IActivityManager iActivityManager, TelecomManager telecomManager, MetricsLogger metricsLogger, SysuiColorExtractor sysuiColorExtractor, IStatusBarService iStatusBarService, NotificationShadeWindowController notificationShadeWindowController, IWindowManager iWindowManager, Executor executor, UiEventLogger uiEventLogger, RingerModeTracker ringerModeTracker, SysUiState sysUiState, Handler handler, PackageManager packageManager, Optional<CentralSurfaces> optional, KeyguardUpdateMonitor keyguardUpdateMonitor, PanelInteractor panelInteractor, OplusSeparateQSManager oplusSeparateQSManager, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, DialogTransitionAnimator dialogTransitionAnimator, TopUiController topUiController) {
        boolean z = false;
        new ArrayList();
        this.mDialogPressDelay = 850;
        this.mShowLockScreenCardsAndControls = false;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnShutdown = anonymousClass3;
        AnonymousClass3 anonymousClass4 = new AnonymousClass3();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnReboot = anonymousClass4;
        AnonymousClass3 anonymousClass5 = new AnonymousClass3();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnCancel = anonymousClass5;
        AnonymousClass3 anonymousClass6 = new AnonymousClass3();
        anonymousClass6.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnEmergency = anonymousClass6;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.shutdown.OplusGlobalActionsDialog.12
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if (!"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) && !"android.intent.action.SCREEN_OFF".equals(action)) {
                    if ("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED".equals(action)) {
                        intent.getBooleanExtra("android.telephony.extra.PHONE_IN_ECM_STATE", false);
                        return;
                    }
                    return;
                }
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    LogUtil.d(LogUtil.TAG_COMMON, "GlobalActionsDialog", "receive ACTION_SCREEN_OFF start");
                }
                String stringExtra = intent.getStringExtra("reason");
                if (!"globalactions".equals(stringExtra)) {
                    AnonymousClass13 anonymousClass13 = OplusGlobalActionsDialog.this.mHandler;
                    anonymousClass13.sendMessage(anonymousClass13.obtainMessage(0, stringExtra));
                }
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    LogUtil.d(LogUtil.TAG_COMMON, "GlobalActionsDialog", "receive ACTION_SCREEN_OFF end");
                }
            }
        };
        this.mHandler = new Handler() { // from class: com.oplus.systemui.shutdown.OplusGlobalActionsDialog.13
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                OplusGlobalActionsDialog oplusGlobalActionsDialog = OplusGlobalActionsDialog.this;
                if (i != 0) {
                    if (i != 1) {
                        return;
                    }
                    oplusGlobalActionsDialog.refreshSilentMode$1();
                    oplusGlobalActionsDialog.mAdapter.notifyDataSetChanged();
                    return;
                }
                if (oplusGlobalActionsDialog.mDialog != null) {
                    if (oplusGlobalActionsDialog.mCancelSignal != null) {
                        LogUtil.d(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "cancelShutdownAuthentication");
                        oplusGlobalActionsDialog.mCancelSignal.cancel();
                        oplusGlobalActionsDialog.mCancelSignal = null;
                    }
                    oplusGlobalActionsDialog.mAuthRecoverAction = null;
                    boolean zEquals = "dream".equals(message.obj);
                    ActionsDialog actionsDialog = oplusGlobalActionsDialog.mDialog;
                    if (zEquals) {
                        ActionsDialog.m371$$Nest$mcompleteDismiss(actionsDialog);
                    } else {
                        if (actionsDialog.mShutDownOrRestart) {
                            LogUtil.i(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "forceDismiss clear mShutDownOrRestart");
                            actionsDialog.mShutDownOrRestart = false;
                        }
                        actionsDialog.dismiss();
                    }
                    oplusGlobalActionsDialog.mDialog = null;
                }
            }
        };
        AnonymousClass15 anonymousClass15 = new AnonymousClass15();
        anonymousClass15.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStateChangedListener = anonymousClass15;
        this.mContext = context;
        this.mPanelInteractor = panelInteractor;
        this.mSeparateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.mSeparateQSManager = oplusSeparateQSManager;
        this.mWindowManagerFuncs = globalActionsManager;
        this.mAudioManager = audioManager;
        this.mDreamManager = iDreamManager;
        this.mLockPatternUtils = lockPatternUtils;
        this.mKeyguardStateController = keyguardStateController;
        this.mResources = resources;
        this.mConfigurationController = configurationController;
        this.mUserTracker = userTracker;
        this.mIActivityManager = iActivityManager;
        this.mTelecomManager = telecomManager;
        this.mMetricsLogger = metricsLogger;
        this.mUiEventLogger = uiEventLogger;
        this.mTopUiController = topUiController;
        this.mSysuiColorExtractor = sysuiColorExtractor;
        this.mStatusBarService = iStatusBarService;
        this.mBackgroundExecutor = executor;
        this.mRingerModeTracker = ringerModeTracker;
        this.mSysUiState = sysUiState;
        this.mMainHandler = handler;
        this.mSmallestScreenWidthDp = resources.getConfiguration().smallestScreenWidthDp;
        this.mOplusSubDisplayUtils = OplusSubDisplayUtils.Companion.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED");
        broadcastDispatcher.registerReceiver(broadcastReceiver, intentFilter);
        context.getPackageManager().hasSystemFeature("android.hardware.telephony");
        if (vibratorHelper != null && vibratorHelper.hasVibrator()) {
            z = true;
        }
        this.mHasVibrator = z;
        if (!resources.getBoolean(17892058)) {
            LiveData ringerMode = ringerModeTracker.getRingerMode();
            OplusGlobalActionsDialog$$ExternalSyntheticLambda0 oplusGlobalActionsDialog$$ExternalSyntheticLambda0 = new OplusGlobalActionsDialog$$ExternalSyntheticLambda0();
            oplusGlobalActionsDialog$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            ringerMode.observe(this, oplusGlobalActionsDialog$$ExternalSyntheticLambda0);
        }
        new EmergencyAffordanceManager(context);
        new ScreenshotHelper(context);
        configurationController.addCallback(this);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        keyguardStateControllerImpl.getClass();
        keyguardStateControllerImpl.addCallback(anonymousClass1);
        ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor("power_menu_locked_show_content"), new ContentObserver(handler) { // from class: com.oplus.systemui.shutdown.OplusGlobalActionsDialog.2
            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
            }
        });
        this.mExt = BaseGlobalActionsDialogExt.getInstance();
    }

    private Action getAssistAction() {
        return new 4(this, 1);
    }

    private UserInfo getCurrentUser() {
        return this.mUserTracker.getUserInfo();
    }

    private Action getSettingsAction() {
        return new 4(this, 0);
    }

    private Action getVoiceAssistAction() {
        return new 4(this, 2);
    }

    private GlobalActionsPanelPlugin.PanelViewController getWalletViewController() {
        Log.d("GlobalActionsDialog", "getWalletViewController: mWallerPlugin is null");
        return null;
    }

    public void createActionItems() {
        if (this.mHasVibrator) {
            AudioManager audioManager = this.mAudioManager;
            AnonymousClass13 anonymousClass13 = this.mHandler;
            SilentModeTriStateAction silentModeTriStateAction = new SilentModeTriStateAction();
            silentModeTriStateAction.ITEM_IDS = new int[]{R.id.profile_badge, R.id.profile_button, R.id.profile_pager};
            silentModeTriStateAction.mAudioManager = audioManager;
            silentModeTriStateAction.mHandler = anonymousClass13;
            VarHandle.storeStoreFence();
            this.mSilentModeAction = silentModeTriStateAction;
        } else {
            SilentModeToggleAction silentModeToggleAction = new SilentModeToggleAction();
            silentModeToggleAction.this$0 = this;
            silentModeToggleAction.mState = ToggleState.Off;
            silentModeToggleAction.mEnabledIconResId = R.drawable.ic_cab_done_holo_dark;
            silentModeToggleAction.mDisabledIconResid = R.drawable.ic_cab_done_holo;
            silentModeToggleAction.mEnabledStatusMessageResId = R.string.indeterminate_progress_47;
            silentModeToggleAction.mDisabledStatusMessageResId = R.string.indeterminate_progress_46;
            VarHandle.storeStoreFence();
            this.mSilentModeAction = silentModeToggleAction;
        }
        ShutdownViewControl shutdownViewControl = new ShutdownViewControl(this.mContext);
        this.mShutdownViewControl = shutdownViewControl;
        mOplusShutdownView = shutdownViewControl.getShutdownView();
        mOplusShutdownViewContainer = this.mShutdownViewControl.getShutdownViewContainer();
        this.mItems.clear();
        this.mOverflowItems.clear();
        this.mPowerItems.clear();
        ArrayList<Action> arrayList = this.mItems;
        OplusShutdownAction oplusShutdownAction = new OplusShutdownAction();
        oplusShutdownAction.this$0 = this;
        VarHandle.storeStoreFence();
        arrayList.add(oplusShutdownAction);
    }

    public final void destroy() {
        this.mConfigurationController.removeCallback(this);
    }

    public final void dismissDialog() {
        removeMessages(0);
        sendEmptyMessage(0);
    }

    public ActionsDialog getActionsDialog() {
        return this.mDialog;
    }

    public String[] getDefaultActions() {
        return this.mResources.getStringArray(R.array.config_mainBuiltInDisplayCutoutSideOverride);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public int getMaxShownPowerItems() {
        return 0;
    }

    public final void handleShow() {
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager != null) {
            try {
                if (iDreamManager.isDreaming()) {
                    this.mDreamManager.awaken();
                }
            } catch (RemoteException unused) {
            }
        }
        createActionItems();
        this.mAdapter = new MyAdapter();
        new MyOverflowAdapter(this);
        new MyPowerOptionsAdapter(this);
        Context contextCreateConfigurationContext = this.mContext.createConfigurationContext(this.mContext.getResources().getConfiguration());
        MyAdapter myAdapter = this.mAdapter;
        SysuiColorExtractor sysuiColorExtractor = this.mSysuiColorExtractor;
        IStatusBarService iStatusBarService = this.mStatusBarService;
        OplusGlobalActionsDialog$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusGlobalActionsDialog$.ExternalSyntheticLambda1(0);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        boolean z = this.mKeyguardShowing;
        SysUiState sysUiState = this.mSysUiState;
        GlobalActions.GlobalActionsManager globalActionsManager = this.mWindowManagerFuncs;
        OplusShutdownView oplusShutdownView = mOplusShutdownView;
        ConstraintLayout constraintLayout = mOplusShutdownViewContainer;
        Handler handler = this.mMainHandler;
        TopUiController topUiController = this.mTopUiController;
        ActionsDialog actionsDialog = new ActionsDialog(contextCreateConfigurationContext, 2132019256);
        actionsDialog.mToken = new Binder();
        actionsDialog.mContext = contextCreateConfigurationContext;
        actionsDialog.mAdapter = myAdapter;
        actionsDialog.mColorExtractor = sysuiColorExtractor;
        actionsDialog.mStatusBarService = iStatusBarService;
        actionsDialog.mSysUiState = sysUiState;
        actionsDialog.mOnRotateCallback = externalSyntheticLambda1;
        actionsDialog.mKeyguardShowing = z;
        mOplusShutdownView = oplusShutdownView;
        mOplusShutdownViewContainer = constraintLayout;
        actionsDialog.mDialogHandler = handler;
        actionsDialog.mDialogTopUiController = topUiController;
        Window window = actionsDialog.getWindow();
        window.requestFeature(1);
        window.getDecorView();
        window.getAttributes().systemUiVisibility |= 1792;
        window.setLayout(-1, -1);
        window.clearFlags(2);
        window.addFlags(17629472);
        window.setType(2020);
        window.getAttributes().setFitInsetsTypes(0);
        actionsDialog.setTitle(R.string.indeterminate_progress_52);
        actionsDialog.mWindowManagerFuncs = globalActionsManager;
        if (Dependency.sDependency.getDependencyInner(KeyguardStateController.class) != null && !((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
            ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().showOrHideFingerprintIconTemporarily(false, "GlobalActionsDialog, Show GlobalActionDialog");
        }
        actionsDialog.mShutdownFlashLightHelper = new ShutdownFlashLightHelper(contextCreateConfigurationContext);
        actionsDialog.initializeLayout();
        VarHandle.storeStoreFence();
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mAuthListener = anonymousClass3;
        actionsDialog.setAuthenticationListener(anonymousClass3);
        actionsDialog.setCanceledOnTouchOutside(false);
        actionsDialog.setOnDismissListener(this);
        actionsDialog.setOnShowListener(this);
        this.mDialog = actionsDialog;
        refreshSilentMode$1();
        this.mAdapter.notifyDataSetChanged();
        this.mLifecycle.setCurrentState(Lifecycle.State.RESUMED);
        WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
        attributes.setTitle("ActionsDialog");
        attributes.layoutInDisplayCutoutMode = 3;
        if (!DeviceUtil.shouldUseLargeFoldConfig(this.mResources)) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet()) {
                attributes.screenOrientation = 1;
            }
        }
        int i = OplusBuild.VERSION.SDK_VERSION;
        if (i >= 27 && OplusBuild.VERSION.SDK_SUB_VERSION >= 19) {
            OplusWindowAttributesManager.setBackGestureRestriction(this.mDialog.getWindow(), 2);
        } else if (i >= 26) {
            try {
                Class<?> cls = Class.forName("com.oplus.view.OplusWindowAttributesManager");
                cls.getMethod("setBackGestureRestriction", Window.class, Integer.TYPE).invoke(cls, this.mDialog.getWindow(), 2);
            } catch (Exception e) {
                LogUtil.e(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "setBackGestureRestriction e=" + e);
            }
        }
        this.mDialog.getWindow().setAttributes(attributes);
        this.mDialog.getWindow().setFlags(131072, 131072);
        this.mDialog.show();
        this.mWindowManagerFuncs.onGlobalActionsShown();
    }

    public final boolean isGlobalActionsShowing() {
        ActionsDialog actionsDialog = this.mDialog;
        return actionsDialog != null && actionsDialog.isShowing();
    }

    public BugReportAction makeBugReportActionForTesting() {
        return new BugReportAction(this);
    }

    public EmergencyDialerAction makeEmergencyDialerActionForTesting() {
        return new EmergencyDialerAction(this);
    }

    public ScreenshotAction makeScreenshotActionForTesting() {
        return new ScreenshotAction(this);
    }

    public final void onConfigChanged(Configuration configuration) {
        int i;
        ActionsDialog actionsDialog = this.mDialog;
        if (actionsDialog != null && actionsDialog.isShowing() && ((i = configuration.smallestScreenWidthDp) != this.mSmallestScreenWidthDp || this.mRotation != configuration.orientation)) {
            this.mSmallestScreenWidthDp = i;
            ActionsDialog actionsDialog2 = this.mDialog;
            actionsDialog2.mContext.getResources().getConfiguration().orientation = this.mContext.getResources().getConfiguration().orientation;
            this.mDialog.refreshDialog();
        }
        this.mRotation = configuration.orientation;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        CancellationSignal cancellationSignal = this.mCancelSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            this.mCancelSignal = null;
        }
        this.mAuthRecoverAction = null;
        if (this.mDialog == dialogInterface) {
            this.mDialog = null;
        }
        this.mUiEventLogger.log(GlobalActionsEvent.GA_POWER_MENU_CLOSE);
        this.mWindowManagerFuncs.onGlobalActionsHidden();
        this.mLifecycle.setCurrentState(Lifecycle.State.CREATED);
        DeviceStateController.getInstance().removeCallback(this.mStateChangedListener);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        this.mMetricsLogger.visible(1568);
        this.mUiEventLogger.log(GlobalActionsEvent.GA_POWER_MENU_OPEN);
        DeviceStateController.getInstance().addCallback(this.mStateChangedListener);
        boolean z = Settings.System.getInt(this.mContext.getApplicationContext().getContentResolver(), "warning_tone_playing_state", 0) == 1;
        BluetoothUtils$.ExternalSyntheticOutline0.m("isWarningTonePlayingState: ", "GlobalActionsDialog", z);
        ShutdownViewControl shutdownViewControl = this.mShutdownViewControl;
        if (shutdownViewControl != null) {
            shutdownViewControl.setWarningTonePlayingState(z);
        }
    }

    public void onUiModeChanged() {
        this.mContext.getTheme().applyStyle(this.mContext.getThemeResId(), true);
        ActionsDialog actionsDialog = this.mDialog;
        if (actionsDialog == null || !actionsDialog.isShowing()) {
            return;
        }
        this.mDialog.refreshDialog();
    }

    public final void refreshSilentMode$1() {
        if (this.mHasVibrator) {
            return;
        }
        Integer num = (Integer) this.mRingerModeTracker.getRingerMode().getValue();
        boolean z = (num == null || num.intValue() == 2) ? false : true;
        ((ToggleAction) this.mSilentModeAction).mState = z ? ToggleState.On : ToggleState.Off;
    }

    public void setZeroDialogPressDelayForTesting() {
        this.mDialogPressDelay = 0;
    }

    public boolean shouldDisplayLockdown(UserInfo userInfo) {
        if (userInfo == null) {
            return false;
        }
        int i = userInfo.id;
        if (!this.mKeyguardStateController.mSecure) {
            return false;
        }
        int strongAuthForUser = this.mLockPatternUtils.getStrongAuthForUser(i);
        return strongAuthForUser == 0 || strongAuthForUser == 4;
    }

    public boolean shouldShowAction(Action action) {
        if (this.mKeyguardShowing) {
            action.getClass();
        }
        if (this.mDeviceProvisioned || action.showBeforeProvisioning()) {
            return action.shouldShow();
        }
        return false;
    }

    public final void showDialog(boolean z, boolean z2) {
        this.mKeyguardShowing = z;
        this.mDeviceProvisioned = z2;
        ActionsDialog actionsDialog = this.mDialog;
        if (actionsDialog == null || !actionsDialog.isShowing()) {
            handleShow();
        }
    }

    public final void showOrHideDialog(boolean z, boolean z2) {
        this.mKeyguardShowing = z;
        this.mDeviceProvisioned = z2;
        ActionsDialog actionsDialog = this.mDialog;
        if (actionsDialog == null || !actionsDialog.isShowing()) {
            handleShow();
            return;
        }
        if (!this.mDialog.mShutDownOrRestart && this.mCancelSignal == null) {
            this.mWindowManagerFuncs.onGlobalActionsShown();
            this.mDialog.dismiss();
            this.mDialog = null;
            return;
        }
        LogUtil.i(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "showOrHideDialog during auth, cancel auth and keep dialog");
        this.mWindowManagerFuncs.onGlobalActionsShown();
        if (this.mCancelSignal != null) {
            LogUtil.d(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialog", "cancelShutdownAuthentication");
            this.mCancelSignal.cancel();
            this.mCancelSignal = null;
        }
        Runnable runnable = this.mAuthRecoverAction;
        this.mAuthRecoverAction = null;
        if (runnable != null) {
            post(runnable);
            return;
        }
        ActionsDialog actionsDialog2 = this.mDialog;
        if (actionsDialog2 != null) {
            actionsDialog2.mShutDownOrRestart = false;
        }
    }

    public final void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent) {
    }
}
