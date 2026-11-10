package com.oplus.zoom.common;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemProperties;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.SurfaceControl;
import android.view.animation.PathInterpolator;
import android.window.TransitionInfo;
import android.window.WindowOrganizer;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.WMShellBooster;
import com.android.wm.shell.common.SchedUtil;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.startingsurface.OplusShellStartingWindowUtils;
import com.android.wm.shell.transition.TransitionLog;
import com.android.wm.shell.transition.Transitions;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.os.LinearmotorVibrator;
import com.oplus.os.WaveformEffect;
import com.oplus.osense.OsenseResClient;
import com.oplus.osense.info.OsenseSaRequest;
import com.oplus.transition.ShellContinuousTransitionUtils;
import com.oplus.zoomwindow.OplusZoomTaskInfo;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ZoomUtil {
    private static final float DIFF_DP_VALUE = 0.5f;
    public static final int ENTER_ZOOM_ALPHA_DURATION;
    private static final String FEATURE_FOLD_REMAP_DISPLAY_DISABLED = "oplus.software.fold_remap_display_disabled";
    private static final String FEATURE_LMVIBRATOR_SUPPORT = "oplus.software.vibrator_lmvibrator";
    private static final float FLEXIBLE_SHADOW_OFFSET_X;
    private static final float FLEXIBLE_SHADOW_OFFSET_Y;
    private static final float FLEXIBLE_SHADOW_SPOT_ALPHA;
    private static final String FOLD_FEATURE = "oplus.hardware.type.fold";
    public static final String FOLD_MODE = "oplus_system_folding_mode";
    public static final String FOLD_OPEN_MODE = "1";
    public static final int MINI_ZOOM_LEFT_BOTTOM = 2;
    public static final int MINI_ZOOM_LEFT_TOP = 1;
    public static final int MINI_ZOOM_RIGHT_BOTTOM = 4;
    public static final int MINI_ZOOM_RIGHT_TOP = 3;
    private static final int ORMS_TIMEOUT = 500;
    private static final int RECENT_TASK_ANIM_LAYER = 100;
    private static final String TABLET_FEATURE = "oplus.hardware.type.tablet";
    private static final String TAG = "ZoomUtil";
    public static final boolean TEST;
    public static final int TRANSIT_ZOOM_CLOSE = 101;
    public static final int ZOOM_CORNER_RADIUS_PORTRAIT = 22;
    public static final float ZOOM_LANDSCAPE_APP_RATIO = 1.6666666f;
    public static final float ZOOM_LANDSCAPE_RATIO = 1.45f;
    public static final float ZOOM_SCALE_FOR_LANDSCAPE_DEFAULT = 0.6f;
    public static final float ZOOM_SCALE_FOR_PORTRAIT_DEFAULT = 0.667f;
    private static final COUIEaseInterpolator mEnterZoomAlphaInterpolator;
    private static Bundle mFlexibleBundle;
    private static SurfaceControl mZoomRootSurface;
    private static SurfaceControl mZoomSnapshotSurface;
    private static SurfaceControl mZoomSurface;
    public static OplusZoomTaskInfo sCurrentZoomTaskInfo;
    public static boolean sIsZoomLandApp;
    public static int sZoomTaskState;
    private static final long[] VIBRATER_ONCE = {0, 50};
    private static OsenseResClient sOsenseClient = null;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class COUIEaseInterpolator extends PathInterpolator {
        private static final float CONTROL_X1 = 0.33f;
        private static final float CONTROL_X2 = 0.67f;
        private static final float CONTROL_Y1 = 0.0f;
        private static final float CONTROL_Y2 = 1.0f;

        public COUIEaseInterpolator() {
            super(CONTROL_X1, 0.0f, CONTROL_X2, 1.0f);
        }
    }

    static {
        ENTER_ZOOM_ALPHA_DURATION = OplusShellStartingWindowUtils.LIGHT_OS_ANIM_LEVEL ? 600 : 400;
        TEST = SystemProperties.getBoolean("persist.sys.zoom_test", false);
        sIsZoomLandApp = false;
        sZoomTaskState = 0;
        mEnterZoomAlphaInterpolator = new COUIEaseInterpolator();
        FLEXIBLE_SHADOW_OFFSET_X = SystemProperties.getInt("persist.flexible.shadows.offsetX", 0);
        FLEXIBLE_SHADOW_OFFSET_Y = SystemProperties.getInt("persist.flexible.shadows.offsetY", 0);
        FLEXIBLE_SHADOW_SPOT_ALPHA = SystemProperties.getInt("persist.flexible.shadows.spotAlpha", 22) / 100.0f;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private static SurfaceControl.Transaction getZoomTransaction(SurfaceControl.Transaction transaction, Transitions transitions, Bundle bundle) {
        ArrayList arrayList;
        TransitionInfo transitionInfo;
        Transitions.ActiveTransition playingRecents = transitions.getPlayingRecents();
        if (playingRecents != null && (arrayList = playingRecents.mMerged) != null && !arrayList.isEmpty()) {
            ArrayList arrayList2 = playingRecents.mMerged;
            int i = bundle.getInt("flexibleTaskId");
            LogD.d(TAG, "getZoomTransaction merged size = " + arrayList2.size() + " taskId = " + i + " t = " + transaction.getId());
            for (int size = arrayList2.size() + (-1); size >= 0; size--) {
                Transitions.ActiveTransition activeTransition = (Transitions.ActiveTransition) arrayList2.get(size);
                if (activeTransition != null && activeTransition.mFinishT != transaction && (transitionInfo = activeTransition.mInfo) != null && transitionInfo.getChanges() != null && !activeTransition.mInfo.getChanges().isEmpty()) {
                    int size2 = activeTransition.mInfo.getChanges().size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ActivityManager.RunningTaskInfo taskInfo = ((TransitionInfo.Change) activeTransition.mInfo.getChanges().get(i2)).getTaskInfo();
                        if (taskInfo != null && taskInfo.taskId == i && activeTransition.mFinishT != null) {
                            LogD.d(TAG, "getZoomTransaction find finishT = " + activeTransition.mFinishT.getId());
                            return activeTransition.mFinishT;
                        }
                    }
                }
            }
        }
        return transaction;
    }

    public static boolean handleRecentEnterZoom(boolean z, SurfaceControl.Transaction transaction, Transitions transitions, Bundle bundle, SurfaceControl surfaceControl, SurfaceControl surfaceControl2, SurfaceControl surfaceControl3, Rect rect, Rect rect2) {
        Bundle bundle2;
        if (z && transaction != null && transitions != null && bundle != null) {
            LogD.i(TAG, "FinishZoom enterZoomFromRecent");
            if (bundle.getBoolean("isFlexibleTask", false)) {
                setEnterFlexibleFreeformLeashInfo(surfaceControl, surfaceControl2, surfaceControl3, rect, rect2, bundle);
                handleRecentToFlexibleAnim(surfaceControl, surfaceControl2, surfaceControl3, getZoomTransaction(transaction, transitions, bundle), rect, rect2, bundle);
                bundle2 = bundle;
            } else {
                bundle2 = bundle;
                updateCurrentLeash(surfaceControl3, surfaceControl);
                handleRecentToZoomAnim(surfaceControl, surfaceControl3, transaction, rect, rect2, bundle2);
            }
            bundle2.clear();
        }
        return false;
    }

    public static void handleRecentToFlexibleAnim(SurfaceControl surfaceControl, SurfaceControl surfaceControl2, SurfaceControl surfaceControl3, SurfaceControl.Transaction transaction, Rect rect, Rect rect2, Bundle bundle) {
        if (isLandSmallScreen() || !isValidEnterMiniFlexibleArgs(surfaceControl, surfaceControl3, rect, rect2, bundle)) {
            if (surfaceControl2 == null || !surfaceControl2.isValid()) {
                return;
            }
            transaction.remove(surfaceControl2);
            return;
        }
        float f = bundle.getFloat("flexibleScale");
        float f2 = bundle.getFloat("flexibleSnapshotScale");
        int i = bundle.getInt("flexibleRadius");
        int i2 = bundle.getInt("flexibleShadows");
        boolean z = bundle.getBoolean("isAlphaFloatAnim");
        int i3 = bundle.getInt("topTaskLayer");
        transaction.setPosition(surfaceControl3, rect.left, rect.top).setCrop(surfaceControl3, rect2).setMatrix(surfaceControl3, f, 0.0f, 0.0f, f).setCornerRadius(surfaceControl3, i).setLayer(surfaceControl3, i3 > 0 ? i3 - 1 : 100).reparent(surfaceControl3, surfaceControl).show(surfaceControl3);
        if (surfaceControl2 == null || !surfaceControl2.isValid()) {
            return;
        }
        if (!z) {
            transaction.remove(surfaceControl2);
        } else {
            transaction.setMatrix(surfaceControl2, f2, 0.0f, 0.0f, f2).setLayer(surfaceControl2, 2147483646).show(surfaceControl2);
            startSnapShotSurfaceAlphaAnim(new SurfaceControl.Transaction(), surfaceControl2, surfaceControl3, i2);
        }
    }

    public static void handleRecentToZoomAnim(SurfaceControl surfaceControl, SurfaceControl surfaceControl2, SurfaceControl.Transaction transaction, Rect rect, Rect rect2, Bundle bundle) {
        if (surfaceControl2 == null || !surfaceControl2.isValid() || surfaceControl == null || !surfaceControl.isValid() || rect == null || bundle == null) {
            return;
        }
        float f = bundle.getFloat("ZoomScale");
        boolean z = bundle.getBoolean("hideWindow");
        transaction.setMatrix(surfaceControl, f, 0.0f, 0.0f, f).setPosition(surfaceControl, rect.left, rect.top).setCrop(surfaceControl, rect2).setCornerRadius(surfaceControl, bundle.getInt("zoomRadius")).reparent(surfaceControl2, surfaceControl).setMatrix(surfaceControl2, 1.0f, 0.0f, 0.0f, 1.0f).setPosition(surfaceControl2, 0.0f, 0.0f).show(surfaceControl);
        transaction.show(surfaceControl2);
        if (z) {
            transaction.setAlpha(surfaceControl, 0.0f);
        } else {
            transaction.setAlpha(surfaceControl, 1.0f);
        }
    }

    public static boolean isFlipDevice() {
        return OplusFeatureConfigManager.getInstance().hasFeature(FEATURE_FOLD_REMAP_DISPLAY_DISABLED);
    }

    public static boolean isFoldDevice() {
        return OplusFeatureConfigManager.getInstance().hasFeature(FOLD_FEATURE);
    }

    public static boolean isFoldOpenMode(Context context) {
        return TextUtils.equals("1", Settings.Global.getString(context.getContentResolver(), FOLD_MODE));
    }

    private static boolean isLandSmallScreen() {
        return ZoomParameterHelper.getInstance().getScreenType() == 1 && !ZoomParameterHelper.getInstance().isPortScreen(ZoomParameterHelper.getInstance().getRotation());
    }

    public static boolean isRTL() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    public static boolean isSupportPocketStudio() {
        return OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.pocketstudio.support");
    }

    public static boolean isTabletDevice() {
        return OplusFeatureConfigManager.getInstance().hasFeature(TABLET_FEATURE);
    }

    private static boolean isValidEnterMiniFlexibleArgs(SurfaceControl surfaceControl, SurfaceControl surfaceControl2, Rect rect, Rect rect2, Bundle bundle) {
        return (surfaceControl2 == null || !surfaceControl2.isValid() || surfaceControl == null || !surfaceControl.isValid() || rect2 == null || rect == null || bundle == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startSnapShotSurfaceAlphaAnim$0(com.oplus.wrapper.view.SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, int i, SurfaceControl surfaceControl2, ValueAnimator valueAnimator) {
        try {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            transaction.setShadowParams(surfaceControl, i * animatedFraction, FLEXIBLE_SHADOW_OFFSET_X, FLEXIBLE_SHADOW_OFFSET_Y, FLEXIBLE_SHADOW_SPOT_ALPHA);
            if (surfaceControl2 != null && surfaceControl2.isValid()) {
                transaction.getTransaction().setAlpha(surfaceControl2, 1.0f - animatedFraction);
            }
            transaction.getTransaction().apply();
        } catch (Exception e) {
            LogD.d(TAG, "startSnapShotSurfaceAlphaAnim updateListener e = " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateFinishTransition$1(TransitionInfo.Change change) {
        if (change.getLeash().getLayerId() != mZoomSurface.getLayerId()) {
            return change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2;
        }
        return true;
    }

    public static void performHapticFeedback(Context context, int i, boolean z) {
        try {
            if (Settings.System.getIntForUser(context.getContentResolver(), CommonSettingsValueProxy.KEY_HAPTIC_FEEDBACK, 0, -2) == 0) {
                return;
            }
            if (!OplusFeatureConfigManager.getInstance().hasFeature(FEATURE_LMVIBRATOR_SUPPORT)) {
                ((Vibrator) context.getSystemService(Vibrator.class)).vibrate(VibrationEffect.createWaveform(VIBRATER_ONCE, -1));
                return;
            }
            LinearmotorVibrator linearmotorVibrator = (LinearmotorVibrator) context.getSystemService("linearmotor");
            WaveformEffect waveformEffectBuild = new WaveformEffect.Builder().setEffectType(i).setAsynchronous(z).build();
            LogD.d(TAG, "LinearmotorVibrator vibrator : " + linearmotorVibrator + " , effect : " + waveformEffectBuild);
            if (linearmotorVibrator == null || waveformEffectBuild == null) {
                return;
            }
            linearmotorVibrator.vibrate(waveformEffectBuild);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int px2dip(float f, float f2) {
        if (f == 0.0f) {
            return 0;
        }
        return (int) (f2 / f);
    }

    public static void requestGainFocus(int i) {
        try {
            ActivityTaskManager.getService().setFocusedTask(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAnimationThreadUx(boolean z) {
        LogD.d(TAG, "setAnimationThreadUx value:" + z);
        WMShellBooster.setUx(z, Process.myTid());
    }

    public static void setEnterFlexibleFreeformLeashInfo(SurfaceControl surfaceControl, SurfaceControl surfaceControl2, SurfaceControl surfaceControl3, Rect rect, Rect rect2, Bundle bundle) {
        mZoomSurface = surfaceControl3;
        mZoomRootSurface = surfaceControl;
        mZoomSnapshotSurface = surfaceControl2;
        if (bundle != null && !bundle.isEmpty()) {
            Bundle bundleDeepCopy = bundle.deepCopy();
            mFlexibleBundle = bundleDeepCopy;
            bundleDeepCopy.putParcelable("endRect", rect);
            mFlexibleBundle.putParcelable("windowCrop", rect2);
            return;
        }
        Bundle bundle2 = mFlexibleBundle;
        if (bundle2 != null) {
            bundle2.clear();
            mFlexibleBundle = null;
        }
    }

    public static void setRenderThreadUx(boolean z) {
        int iOplusActivityManager_getRenderThreadTid = ReflectionHelper.OplusActivityManager_getRenderThreadTid(Process.myPid());
        if (iOplusActivityManager_getRenderThreadTid == 0) {
            LogD.e(TAG, "setRenderThreadUx fail!");
            return;
        }
        LogD.d(TAG, "setRenderThreadUx value:" + z);
        WMShellBooster.setUx(z, iOplusActivityManager_getRenderThreadTid);
        SchedUtil.optimizeRenderThread(z);
    }

    public static void setupZoomRotationStartState(TransitionInfo.Change change, WindowOrganizer windowOrganizer, SurfaceControl.Transaction transaction) {
        boolean z = change.getEndRotation() == 0 || change.getEndRotation() == 2;
        SurfaceControl zoomTaskSurface = ((ShellTaskOrganizer) windowOrganizer).getZoomTaskSurface(change.getTaskInfo().getParentTaskId());
        ZoomPositionInfo defaultZoomPositionInfo = sZoomTaskState != 2 ? ZoomParameterHelper.getInstance().getDefaultZoomPositionInfo(sIsZoomLandApp, z) : ZoomParameterHelper.getInstance().getDefaultMiniPositionInfo(sIsZoomLandApp, z);
        Rect defaultZoomBound = ZoomParameterHelper.getInstance().getDefaultZoomBound(sIsZoomLandApp, z);
        float scale = defaultZoomPositionInfo.getScale();
        Rect scaleRect = defaultZoomPositionInfo.getScaleRect();
        if (zoomTaskSurface == null || !zoomTaskSurface.isValid()) {
            transaction.setPosition(change.getLeash(), scaleRect.left, scaleRect.top);
            return;
        }
        transaction.setScale(zoomTaskSurface, scale, scale);
        transaction.setAlpha(zoomTaskSurface, 1.0f);
        transaction.setPosition(zoomTaskSurface, scaleRect.left, scaleRect.top);
        transaction.setWindowCrop(zoomTaskSurface, defaultZoomBound.width(), defaultZoomBound.height());
        transaction.setCornerRadius(zoomTaskSurface, ZoomParameterHelper.getInstance().getZoomCornerRadius());
        transaction.show(zoomTaskSurface);
    }

    /* JADX WARN: Code duplicated, block: B:6:0x0022  */
    public static void setupZoomStartState(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, WindowOrganizer windowOrganizer) {
        SurfaceControl.Transaction transaction3;
        boolean zIsOpeningType = TransitionUtil.isOpeningType(transitionInfo.getType());
        int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1);
        while (iM >= 0) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change.hasFlags(65794)) {
                transaction3 = transaction;
            } else {
                SurfaceControl leash = change.getLeash();
                int mode = ((TransitionInfo.Change) transitionInfo.getChanges().get(iM)).getMode();
                if (mode == 3 && (change.getStartAbsBounds().height() != change.getEndAbsBounds().height() || change.getStartAbsBounds().width() != change.getEndAbsBounds().width())) {
                    Rect defaultZoomBound = ZoomParameterHelper.getInstance().getDefaultZoomBound(sIsZoomLandApp, ZoomParameterHelper.getInstance().isPortScreen(ZoomParameterHelper.getInstance().getRotation()));
                    transaction.setWindowCrop(leash, defaultZoomBound.width(), defaultZoomBound.height());
                }
                if (TransitionInfo.isIndependent(change, transitionInfo)) {
                    if (mode == 1 || mode == 3) {
                        transaction.show(leash);
                        transaction3 = transaction;
                        transaction3.setMatrix(leash, 1.0f, 0.0f, 0.0f, 1.0f);
                        if (zIsOpeningType && (change.getFlags() & 8) == 0) {
                            transaction3.setAlpha(leash, 0.0f);
                        }
                        transaction2.show(leash);
                    } else {
                        if (mode == 2 || mode == 4) {
                            transaction2.hide(leash);
                        } else if (zIsOpeningType && mode == 6) {
                            transaction.show(leash);
                        }
                        transaction3 = transaction;
                    }
                } else if (change.getTaskInfo() == null || change.getTaskInfo().getWindowingMode() != 100) {
                    if (mode == 1 || mode == 3 || mode == 6) {
                        transaction.show(leash);
                        transaction3 = transaction;
                        transaction3.setMatrix(leash, 1.0f, 0.0f, 0.0f, 1.0f);
                        transaction.setAlpha(leash, 1.0f);
                        transaction.setPosition(leash, change.getEndRelOffset().x, change.getEndRelOffset().y);
                    } else {
                        transaction3 = transaction;
                    }
                } else if (change.getTaskInfo() == null || change.getTaskInfo().getWindowingMode() != 100) {
                    transaction3 = transaction;
                } else {
                    if (mode == 6) {
                        transaction.reparent(leash, ((ShellTaskOrganizer) windowOrganizer).getZoomTaskSurface(change.getTaskInfo().getParentTaskId()));
                        transaction.show(leash);
                        if (change.getStartRotation() != change.getEndRotation()) {
                            setupZoomRotationStartState(change, windowOrganizer, transaction);
                        }
                    } else if (mode == 3) {
                        transaction.show(leash);
                        transaction3 = transaction;
                        transaction3.setMatrix(leash, 1.0f, 0.0f, 0.0f, 1.0f);
                        transaction.setAlpha(leash, 1.0f);
                        transaction.setPosition(leash, change.getEndRelOffset().x, change.getEndRelOffset().y);
                    }
                    transaction3 = transaction;
                }
            }
            iM--;
            transaction = transaction3;
        }
    }

    private static void startSnapShotSurfaceAlphaAnim(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, SurfaceControl surfaceControl2, int i) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        if (OplusShellStartingWindowUtils.LIGHT_OS_ANIM_LEVEL) {
            valueAnimatorOfFloat.setInterpolator(null);
        } else {
            valueAnimatorOfFloat.setInterpolator(mEnterZoomAlphaInterpolator);
        }
        com.oplus.wrapper.view.SurfaceControl.Transaction transaction2 = new com.oplus.wrapper.view.SurfaceControl.Transaction(transaction);
        ZoomUtil$$ExternalSyntheticLambda1 zoomUtil$$ExternalSyntheticLambda1 = new ZoomUtil$$ExternalSyntheticLambda1();
        zoomUtil$$ExternalSyntheticLambda1.f$0 = transaction2;
        zoomUtil$$ExternalSyntheticLambda1.f$1 = surfaceControl2;
        zoomUtil$$ExternalSyntheticLambda1.f$2 = i;
        zoomUtil$$ExternalSyntheticLambda1.f$3 = surfaceControl;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(zoomUtil$$ExternalSyntheticLambda1);
        valueAnimatorOfFloat.setDuration(ENTER_ZOOM_ALPHA_DURATION);
        valueAnimatorOfFloat.addListener(new 1(transaction2, surfaceControl2, i, surfaceControl));
        valueAnimatorOfFloat.start();
    }

    public static void updateCurrentLeash(SurfaceControl surfaceControl, SurfaceControl surfaceControl2) {
        mZoomSurface = surfaceControl;
        mZoomRootSurface = surfaceControl2;
    }

    public static void updateFinishTransition(Transitions.TransitionHandler transitionHandler, SurfaceControl.Transaction transaction, TransitionInfo transitionInfo) {
        SurfaceControl surfaceControl;
        SurfaceControl surfaceControl2;
        SurfaceControl.Transaction transaction2;
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION && (surfaceControl = mZoomSurface) != null && surfaceControl.isValid() && (surfaceControl2 = mZoomRootSurface) != null && surfaceControl2.isValid() && transitionInfo != null) {
            if ((transitionInfo.getType() == 1 || transitionInfo.getType() == 3) && transaction != null && (transitionHandler instanceof RecentsTransitionHandler) && transitionInfo.getChanges().stream().anyMatch(new ZoomUtil$$ExternalSyntheticLambda0())) {
                Bundle bundle = mFlexibleBundle;
                boolean z = false;
                if (bundle != null && bundle.getBoolean("isFlexibleTask", false)) {
                    z = true;
                }
                TransitionLog.always("updateFinishTransition isFlexible:" + z);
                for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                    if ((change.getMode() == 1 || change.getMode() == 3) && change.getLeash().getLayerId() == mZoomSurface.getLayerId()) {
                        if (!z) {
                            transaction2 = transaction;
                            transaction2.reparent(mZoomSurface, mZoomRootSurface);
                        } else if (isLandSmallScreen()) {
                            SurfaceControl surfaceControl3 = mZoomSurface;
                            if (surfaceControl3 != null && surfaceControl3.isValid()) {
                                LogD.d(TAG, "open anim merge to recent and into flexible when land small screen, will hide " + mZoomSurface);
                                transaction.hide(mZoomSurface);
                            }
                            SurfaceControl surfaceControl4 = mZoomSnapshotSurface;
                            if (surfaceControl4 != null && surfaceControl4.isValid()) {
                                LogD.d(TAG, "open anim merge to recent and into flexible when land small screen, will remove " + mZoomSnapshotSurface);
                                transaction.remove(mZoomSnapshotSurface);
                            }
                            transaction2 = transaction;
                        } else {
                            transaction2 = transaction;
                            handleRecentToFlexibleAnim(mZoomRootSurface, mZoomSnapshotSurface, mZoomSurface, transaction2, (Rect) mFlexibleBundle.getParcelable("endRect", Rect.class), (Rect) mFlexibleBundle.getParcelable("windowCrop", Rect.class), mFlexibleBundle);
                        }
                        TransitionLog.always("current leash was entering zoom mode, so reparent current leash to zoomRootTask, id= #" + transitionInfo.getDebugId() + " change= " + change);
                    } else {
                        transaction2 = transaction;
                        if ((change.getMode() == 4 || change.getMode() == 2) && change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) {
                            if (change.getLeash() != null && change.getLeash().isValid()) {
                                transaction2.show(change.getLeash());
                            }
                            TransitionLog.always("current leash was entering zoom mode, so show Launcher id= #" + transitionInfo.getDebugId() + " change= " + change);
                        }
                    }
                    transaction = transaction2;
                }
            }
        }
    }

    public static void zoomOrms() {
        if (sOsenseClient == null) {
            OsenseResClient osenseResClient = OsenseResClient.get(ZoomUtil.class);
            sOsenseClient = osenseResClient;
            if (osenseResClient == null) {
                return;
            }
        }
        sOsenseClient.osenseSetSceneAction(new OsenseSaRequest("", "OSENSE_ACTION_LAUNCH", ORMS_TIMEOUT));
    }

    public static int dip2px(float f, float f2) {
        return (int) ((f2 * f) + 0.5f);
    }
}
