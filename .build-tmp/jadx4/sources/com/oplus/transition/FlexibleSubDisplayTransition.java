package com.oplus.transition;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Slog;
import android.window.RemoteTransition;
import android.window.TransitionInfo;
import com.android.systemui.people.PeopleBackupFollowUpJob$;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FlexibleSubDisplayTransition {
    private static final String CLASSNAME_CAMERA = "com.oplus.camera.Camera";
    private static final String CLASSNAME_CAMERA_CONTINUE_REQUIRE = "com.oplus.camera.component.ContinueRequireActivity";
    private static final String CLASSNAME_GALLERY = "com.oppo.gallery3d.app.ViewGallery";
    private static final String GET_IS_SECONDARY_HOME_TO_FRONT_WHEN_UNFOLDING = "isSecondaryHomeToFrontWhenUnFolding";
    private static final int SECOND_DEFAULT_DISPLAY = 1;
    private static final String TAG = "FlexibleSubDisplayTransition";
    private static volatile Field sSubScenarioBundleField;
    private Context mContext;

    public FlexibleSubDisplayTransition(Context context) {
        this.mContext = context;
    }

    private boolean containCameraContinueRequireActivity(ActivityManager.RunningTaskInfo runningTaskInfo) {
        ComponentName componentName;
        return (runningTaskInfo == null || (componentName = runningTaskInfo.topActivity) == null || !CLASSNAME_CAMERA_CONTINUE_REQUIRE.equals(componentName.getClassName())) ? false : true;
    }

    private boolean containGalleryAndCamera(ActivityManager.RunningTaskInfo runningTaskInfo) {
        ComponentName componentName;
        return (runningTaskInfo == null || (componentName = runningTaskInfo.topActivity) == null || runningTaskInfo.baseActivity == null || !CLASSNAME_GALLERY.equals(componentName.getClassName()) || !CLASSNAME_CAMERA.equals(runningTaskInfo.baseActivity.getClassName())) ? false : true;
    }

    public static Bundle getSubScenarioBundleFromChange(TransitionInfo.Change change) {
        if (change == null) {
            return null;
        }
        try {
            Field declaredField = sSubScenarioBundleField;
            if (declaredField == null) {
                declaredField = change.getClass().getDeclaredField("mSubScenarioBundle");
                declaredField.setAccessible(true);
                sSubScenarioBundleField = declaredField;
            }
            return (Bundle) declaredField.get(change);
        } catch (Exception e) {
            PeopleBackupFollowUpJob$.ExternalSyntheticOutline0.m("getSubScenarioBundleFromChange e:", TAG, e);
            return null;
        }
    }

    public boolean avoidDelegateToSecondHomeRemote(TransitionInfo transitionInfo, RemoteTransition remoteTransition) {
        if (remoteTransition != null && "SecondaryHomeLaunch".equals(remoteTransition.getDebugName())) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (TransitionUtil.isOpeningType(change.getMode()) && taskInfo != null && containCameraContinueRequireActivity(taskInfo)) {
                    Slog.e(TAG, "Avoid delegate continue require open animation to SecondaryHomeLaunch!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cancelAnimationForGalleryMovedFromSubDisplay(TransitionInfo transitionInfo) {
        if (transitionInfo != null && transitionInfo.getType() == 1 && TransitionAnimationUtil.isFlipDevice()) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (TransitionUtil.isOpeningType(change.getMode()) && taskInfo != null && containGalleryAndCamera(taskInfo) && change.getStartDisplayId() == 1 && change.getEndDisplayId() == 0) {
                    Slog.e(TAG, "Cancel open animation due to Galley task moved from SubDisplay!");
                    return true;
                }
            }
        }
        return false;
    }

    public Bundle getAnimExtraBundle(TransitionInfo.Change change) {
        return getSubScenarioBundleFromChange(change);
    }

    public float getFlexibleScale(TransitionInfo.Change change) {
        Bundle subScenarioBundleFromChange = getSubScenarioBundleFromChange(change);
        if (subScenarioBundleFromChange != null) {
            return subScenarioBundleFromChange.getFloat(FlexibleTransitionUtils.KEY_FLEXIBLE_TASK_SCALE);
        }
        return -1.0f;
    }

    public int getFlexibleScenario(TransitionInfo.Change change) {
        Bundle subScenarioBundleFromChange = getSubScenarioBundleFromChange(change);
        if (subScenarioBundleFromChange != null) {
            return subScenarioBundleFromChange.getInt(FlexibleTransitionUtils.KEY_FLEXIBLE_TASK_SCENARIO);
        }
        return -1;
    }

    public Bundle getTaskAnimExtraBundle(TransitionInfo transitionInfo) {
        TransitionInfo.Change change = null;
        if (transitionInfo == null) {
            return null;
        }
        for (TransitionInfo.Change change2 : transitionInfo.getChanges()) {
            if (change2.getTaskInfo() != null) {
                Slog.w(TAG, "found task change:" + change2);
                change = change2;
            }
        }
        return getAnimExtraBundle(change);
    }

    public boolean shouldSkipMergeToSecondaryHome(TransitionInfo transitionInfo, RemoteTransition remoteTransition) {
        if (remoteTransition != null && "SecondaryHomeLaunch".equals(remoteTransition.getDebugName())) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (TransitionUtil.isOpeningType(change.getMode()) && taskInfo != null && containCameraContinueRequireActivity(taskInfo)) {
                    Slog.e(TAG, "Skip merge continue require open animation to SecondaryHomeLaunch!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean shouldSkipSecondaryHomeToFrontAnimation(TransitionInfo transitionInfo) {
        boolean z = false;
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_SECONDARY_HOME_TO_FRONT_WHEN_UNFOLDING, null, new Object[0]);
        if (bool != null && bool.booleanValue()) {
            z = true;
        }
        if (z) {
            Slog.e(TAG, "Skip secondary home ToFront animation when device unfolding!");
        }
        return z;
    }
}
