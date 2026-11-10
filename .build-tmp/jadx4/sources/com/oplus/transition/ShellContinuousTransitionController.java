package com.oplus.transition;

import android.R;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.window.BackNavigationInfo;
import android.window.RemoteTransition;
import android.window.TransitionFilter;
import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import android.window.WindowOrganizer;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.runtime.OpaqueKey$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.people.PeopleBackupFollowUpJob$;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.activityembedding.ActivityEmbeddingController$;
import com.android.wm.shell.activityembedding.OplusActivityEmbeddingAnimationRunner;
import com.android.wm.shell.activityembedding.OplusEmbedReflectionUtils;
import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.flexibletask.FlexibleTaskTransitionUtils;
import com.android.wm.shell.keyguard.KeyguardTransitionHandler;
import com.android.wm.shell.pip2.phone.PipTransition;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.AdaptiveSmoothShellAnimManager;
import com.android.wm.shell.transition.DefaultTransitionHandler;
import com.android.wm.shell.transition.OplusPanoramaWorkBranchAnimController;
import com.android.wm.shell.transition.RemoteTransitionHandler;
import com.android.wm.shell.transition.TransitionAnimationHelper;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import com.android.wm.shell.transition.TransitionLog;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.transition.WindowAnimation;
import com.android.wm.shell.util.ReflectionUtils;
import com.google.android.collect.Sets;
import com.oplus.flexibletask.animation.OplusFlexibleTaskRotationController;
import com.oplus.flexibletask.utils.FlexibleUtils;
import com.oplus.splitscreen.ReflectionHelper;
import com.oplus.splitscreen.SplitToggleHelper;
import com.oplus.uifirst.OplusUIFirstManager;
import com.oplus.zoom.common.ZoomUtil;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ShellContinuousTransitionController {
    public static final int ACTIVITY_LEVEL_ABORT = 2;
    private static final float ALPHA_0 = 0.0f;
    private static final long ALPHA_ANIM_DUARATION = 350;
    private static final float ALPHA_END = 0.5f;
    private static final int CHANGE_LIMIT_SIZE = 2;
    public static final int CONTINUOUS_TRACK = 10;
    public static final int CONTINUOUS_TYPE_CROSS_PROCESS_DIFF_TASK = 4;
    public static final int CONTINUOUS_TYPE_CROSS_PROCESS_SAME_TASK = 3;
    public static final int CONTINUOUS_TYPE_NONE = 0;
    public static final int CONTINUOUS_TYPE_SAME_PROCESS_DIFF_TASK = 2;
    public static final int CONTINUOUS_TYPE_SAME_PROCESS_SAME_TASK = 1;
    public static final int CONTINUOUS_TYPE_SECONDARY_PAGE_SWITCH = 5;
    private static final float DEFAULT_ALPHA_1 = 1.0f;
    public static final int DEFAULT_CONTINUOUS_BACKGROUND_MODE = 1000;
    private static final int DEFUALT_TASK_ID_FROM_RECENTS = 0;
    private static final String EXT = "mExt";
    private static final String GET_ABORT_BY_REMOTE_FROM_HOME = "isAbortByRemoteFromHome";
    private static final String GET_ABORT_REASON = "getAbortTransitionOnShellReason";
    private static final String GET_ALPHA = "getAlpha";
    private static final String GET_AVOID_INTERCEPT_KEY_EVENT_TO_HOME = "getAvoidInterceptKeyEventToHome";
    private static final String GET_BACK_ANIM_CONTINUE_ALPHA = "getBackAnimContinueAlpha";
    private static final String GET_BACK_ANIM_CONTINUE_LEASH = "getBackAnimContinueLeash";
    private static final String GET_BACK_ANIM_CONTINUE_POSITION = "getBackAnimContinuePosition";
    private static final String GET_BACK_ANIM_CONTINUOUS = "getBackAnimContinuous";
    private static final String GET_CLOSE_WALLPAPER_REMOVED = "getHasCloseWallpaperRemoved";
    private static final String GET_CONTENT_RADIUS = "getContentRadius";
    public static final String GET_END_FIXED_ROTATION_FOR_INFO = "getEndFixedRotationForInfo";
    private static final String GET_EXTENDED_INFO = "getExtendedInfo";
    private static final String GET_IS_BAL_ALLOW = "getIsBalAllow";
    public static final String GET_IS_CLICK_HOME_KEY = "isClickHomeKey";
    private static final String GET_IS_FROM_HOME = "getIsFromHome";
    public static final String GET_IS_FROM_KEYGUARD_START_ACTIVITY = "getIsFromKeyguardStartActivity";
    private static final String GET_IS_LAUNCHFROM_SPRUCE = "getIsLaunchFromSpruce";
    private static final String GET_IS_LAUNCH_FROM_DRIVE = "getIsLaunchFromDrive";
    private static final String GET_IS_LAUNCH_FROM_SPRUCE = "getIsLaunchFromSpruce";
    private static final String GET_IS_MATCH_REMOTE_FILTER = "isMatchRemoteFilter";
    private static final String GET_IS_NEED_APPLY_START_T_FOR_EMBEDDED = "getIsNeedApplyStartTForEmbedded";
    private static final String GET_IS_OPENING_STK_DIALOG_ACTIVITY = "getIsOpeningStkDialogActivity";
    private static final String GET_IS_TASK_NOT_IN_RECENTS = "getIsTaskNotInRecents";
    private static final String GET_IS_TRANSLUCENT_ACTIVITY_TO_HOME = "getIsTranslucentActivityToHome";
    private static final String GET_LAUNCH_FROM_ASSISTANT_BY_DEFAULT = "getLaunchFromAssistantByDefault";
    private static final String GET_MERGE_SECOND_TASK_ANIMATION = "getMergeSecondTaskAnimation";
    private static final String GET_PARENT_LEASH_FOR_BG_COLOR = "getParentLeashForBgColor";
    private static final String GET_PRE_READY_ACTIVITY_ROTATION = "getPreReadyActivityRotation";
    private static final String GET_RADIUS = "getRadius";
    private static final String GET_RECTF = "getRectF";
    private static final String GET_REMOTE_INTERRUPT = "getRemoteInterrupt";
    private static final String GET_REMOVED_TASK_ID = "getRemovedTaskId";
    private static final String GET_REPARENT_TO_RECENTS_SURFACE = "getReparentToRecentsSurface";
    private static final String GET_REQUEST_TRANSITION_TASK_ID = "getRequestTransitionTaskId";
    private static final String GET_SCALE_VALUE = "getScaleValue";
    private static final String GET_SKIP_REMOTE_TRANSITION = "getSkipRemoteTransition";
    private static final String GET_TASK_ID_START_FROM_RECENTS = "getTaskIdStartFromRecents";
    private static final String GET_TASK_NOT_IN_RECENT_ON_EXTINFO = "getTaskNotInRecent";
    private static final String GET_TRANSITION_CONTINUOUS_TYPE = "getTransitionContinuousType";
    private static final String GET_WCT = "getWCT";
    private static final String GET_WEIGHT = "getWeight";
    private static final String GET_WINDOW_CROP = "getWindowCrop";
    private static final String IS_ASSIST_SCREEN_SHOWING = "isAssistScreenShowing";
    private static final String IS_CONVERT_TO_TRANSLUCENT = "lsConvertToTranslucent";
    private static final String IS_GLOBALSEARCH_SWITCH_TO_HOME_COLLECT = "isGlobalSearchPageSwitchToHomeCollect";
    private static final String IS_IN_PREREADY = "getInPreReady";
    private static final String IS_OPLUS_ANIM_RES = "isOplusAnimRes";
    private static final String IS_PREEMPTED_BY_RECENTS = "isPreemptedByRecents";
    private static final String IS_START_AHEAD_SPRUCE = "isStartAheadSpruce";
    private static final String KEEP_BG_FOR_CONTINUOUS = "keepBgForContinuous";
    private static final String KEY_HAS_FIX_ROTATION_LEASH = "has-fix-rotation-leash";
    private static final String KEY_HAS_MAIN_WINDOW = "has-main-window";
    private static final String KEY_LAUNCH_FROM_SPRUCE = "launch-from-spruce";
    private static final String KEY_RECENTS_ANIM_BUNDLE_TRANSITION_INFO = "transition_info";
    private static final String LAUNCHER_DEBUG_NAME = "QuickstepLaunch";
    private static final String LAUNCHER_KEY_MODE_DEBUG_NAME = "QuickstepLaunchHome";
    private static final String LAUNCHER_MENU_DEBUG_NAME = "AnimationProvider";
    private static final int MATRIX_ARRAY_LEN = 9;
    private static final String METHOD_GET_ANIMATION = "getAnimation";
    private static final String METHOD_GET_IGNORE_ANIMATOR_UPDATE = "getIgnoreAnimatorUpdate";
    private static final String METHOD_GET_TRANSITION_MODE = "getTransitionMode";
    private static final String METHOD_SET_ANIMATION = "setAnimation";
    private static final String METHOD_SET_IGNORE_ANIMATOR_UPDATE = "setIgnoreAnimatorUpdate";
    private static final String METHOD_SET_TRANSITION_MODE = "setTransitionMode";
    private static final String NEED_KEEP_BG_FOR_CONTINUOUS = "needKeepBgForContinuous";
    public static final int NORMAL_ABORT = 1;
    private static final int OPLUS_MIRAGE_DISPLAY = 10000;
    private static final int OPLUS_MIRAGE_TVDISPLAY = 2020;
    private static final String PKG_LAUNCHER = "com.android.launcher";
    private static final float RADIUS_0 = 0.0f;
    public static final int RECENTS_FINISH_ABORT = 3;
    private static final String SETTINGS_PKG = "com.android.settings";
    private static final String SET_ALPHA = "setAlpha";
    private static final String SET_APPEARED_ACTIVITY_ROTATION = "setAppearedActivityRotation";
    private static final String SET_BACK_ANIM_CONTINUE_ALPHA = "setBackAnimContinueAlpha";
    private static final String SET_BACK_ANIM_CONTINUE_LEASH = "setBackAnimContinueLeash";
    private static final String SET_BACK_ANIM_CONTINUE_POSITION = "setBackAnimContinuePosition";
    private static final String SET_BACK_ANIM_CONTINUOUS = "setBackAnimContinuous";
    private static final String SET_CLOSE_WALLPAPER_REMOVED = "setHasCloseWallpaperRemoved";
    private static final String SET_CONTENTRADIUS = "setContentRadius";
    private static final String SET_GLOBAL_SEARCH_PAGE_SWITCH = "setGlobalSearchPageSwitch";
    private static final String SET_INTERMEDIATE_TASK_LEASH = "setIntermediateTaskLeash";
    private static final String SET_IN_PREREADY = "setInPreReady";
    private static final String SET_IS_BAL_ALLOW = "setIsBalAllow";
    private static final String SET_IS_FROM_SPRUCE = "setIsFromSpruce";
    private static final String SET_IS_REMOTE_FROM_LAUNCHER = "setIsRemoteFromLauncher";
    private static final String SET_IS_TASK_NOT_IN_RECENTS = "setIsTaskNotInRecents";
    private static final String SET_MERGE_BACK_ANIMATION_TO_RECENTS = "setMergeBackAnimationToRecents";
    private static final String SET_MERGE_SECOND_TASK_ANIMATION = "setMergeSecondTaskAnimation";
    private static final String SET_MERGE_SECOND_TASK_ID = "setMergeSecondTaskId";
    private static final String SET_PREEMPTED_BY_RECENTS = "setPreemptedByRecents";
    private static final String SET_RADIUS = "setRadius";
    private static final String SET_RECENTS_CONTROLLER = "setRecentsController";
    private static final String SET_RECENTS_ROOT = "setRecentsRoot";
    private static final String SET_RECENT_FINISH_INPUT_CONSUMER_ENABLED = "setRecentFinishInputConsumerEnabled";
    private static final String SET_RECENT_FINISH_SEQ = "setRecentFinishSeq";
    private static final String SET_RECENT_FINISH_TO_HOME = "setRecentFinishToHome";
    private static final String SET_RECTF = "setRectF";
    private static final String SET_REMOTE_INTERRUPT = "setRemoteInterrupt";
    private static final String SET_REQUEST_TRANSITION_TASK_ID = "setRequestTransitionTaskId";
    private static final String SET_SCALE_VALUE = "setScaleValue";
    private static final String SET_SHELL_APPLY_TOKEN = "setShellApplyToken";
    private static final String SET_SWIPE_UP_BROWSER_SCREEN_SHOWING = "setSwipeUpBrowserScreenShowing";
    private static final String SET_TASK_ID_START_FROM_RECENTS = "setTaskIdStartFromRecents";
    private static final String SET_TRANSITION_CONTINUOUS_TYPE = "setTransitionContinuousType";
    private static final String SET_TRANSITION_ID = "setTransitionId";
    private static final String SET_WCT = "setWCT";
    private static final String SET_WEIGHT = "setWeight";
    private static final String SET_WINDOW_CROP = "setWindowCrop";
    public static final int SHELL_ANIM_STATE_FINISH = 3;
    public static final int SHELL_ANIM_STATE_INIT = 0;
    public static final int SHELL_ANIM_STATE_PREFINISH = 2;
    public static final int SHELL_ANIM_STATE_READY = 1;
    public static final int SINGLE_TARGET_ABORT = 4;
    private static final String SPRUCE_DEBUG_NAME = "SpruceLauncher";
    private static final int STATE_NEW_TASK = 1;
    public static final String TAG = "ShellContinuousTransitionController";
    private static final String TASKFRAGMENT = "TaskFragment";
    private static final String TASK_FRAGMENT = "TaskFragment";
    public static final int TRANSFORM_INDEX_BACKGROUND = 2;
    public static final int TRANSFORM_INDEX_CLOSE_TARGET = 0;
    public static final int TRANSFORM_INDEX_OPEN_TARGET = 1;
    public static final int TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE = 1048576;
    private static final int WINDOWING_MODE_COMPACTWINDOW = 120;
    private static volatile Method sGetExtendedInfoMethod;
    private static volatile Field sInfoExtField;
    private static volatile Method sSetRecentsControllerMethod;
    private BackAnimationController.BackTransitionHandler mBackAnimHandler;
    private boolean mBackPredictiveToRemoteContinuous;
    private boolean mDefaultContinuous;
    private boolean mIsLandScapeExitScene;
    private long mRecentFinishSeqId;
    private boolean mRecentFinishToHome;
    private boolean mRemoteToRecentContinuous;
    private boolean mRemoteToRemoteContinuous;
    private IBinder mRemoteTransitionToken;
    private Transitions mTransitions;
    private static final Set<String> MATCH_REMOTE_APP = Sets.newHashSet(new String[]{"com.oppo.store", "com.oplus.encryption", "com.coloros.familyguard"});
    private static final Set<String> MATCH_REMOTE_CMP = Sets.newHashSet(new String[]{"com.tencent.mm/.plugin.offline.ui.WalletOfflineEntranceUI"});
    public static final Set<String> PHONE_MANAGER_PKG = Sets.newArraySet(new String[]{"com.oplus.phonemanagerlite", "com.coloros.phonemanager"});
    private static final Set<ComponentName> COMPONENTS_WITHOUT_REMOTE_ANIMATION = Sets.newHashSet(new ComponentName[]{ComponentName.unflattenFromString("com.google.android.apps.googleassistant/.AssistantActivity")});
    private static final Set<ComponentName> COMPONENTS_SKIP_REMOTE_ANIMATION = Sets.newHashSet(new ComponentName[]{ComponentName.unflattenFromString("com.oplus.screenshot/com.oplus.screenshot.editor.activity.EditorActivity"), ComponentName.unflattenFromString("com.coloros.assistantscreen/com.oplus.assistantscreen.card.supermusic.ui.MusicAggregateActivity"), ComponentName.unflattenFromString("com.heytap.opluscarlink/.carcontrol.view.MultiCarChangeActivity"), ComponentName.unflattenFromString("com.oplus.matrix/.transparent.TransparentActivity")});
    private static final Set<String> SKIP_DEFAULT_TRANSITION_COMPONENT = Sets.newHashSet(new String[]{"com.tencent.androidqqmail/com.tencent.qqmail.utilities.ui.QMOpenFileActivity", "com.eg.android.AlipayGphone/com.alipay.android.phone.wallet.outlauncher.page.UserGrowthActivity"});
    private static final Set<String> IS_MATCH_CUSTOM_COMPONENT = Sets.newHashSet(new String[]{"com.oplus.upgradeguide/com.oplus.upgrade.guide.activity.NewFeaturePage", "com.coloros.bootreg/com.coloros.bootreg.settings.activity.CompletePage", "com.oplus.upgradeguide/com.oplus.upgrade.ota.ui.OtaCompletePage"});
    private static final Set<String> IGNORE_ABORT_TRANSITION_PKGS = Sets.newHashSet(new String[]{"com.google.android.apps.nbu.paisa.user", "com.pinterest"});
    private static final Set<ComponentName> ENCRYPTION_MAIN_ACTIVITY = Set.of(new ComponentName("com.oplus.encryption", "com.oplus.encryption.brand.oneplus.safe.view.OpEncryptionMainActivity"), new ComponentName("com.oplus.encryption", "com.oplus.encryption.main.activity.EncryptionMainActivity"));
    private static final Set<ComponentName> ENCRYPTION_NEXT_ACTIVITY = Set.of(new ComponentName("com.oplus.account", "com.platform.account.open.UserCenterContainerActivity"));
    private static final boolean ENABLE_SAMEPROCESS_SAMETASK = SystemProperties.getBoolean("persist.sys.sameprocess_sameTask.enable", false);
    private ConcurrentHashMap<IBinder, Integer> mRemoteTransitionStateMap = new ConcurrentHashMap<>();
    private int mRemoteMergeToRemote = 0;
    private ConcurrentHashMap<Integer, List<SurfaceControl>> mTransitionBgMap = new ConcurrentHashMap<>();

    private boolean abortForGlobalPageSearchSwitch(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2) {
        if (activeTransition != null && activeTransition.mInfo != null && activeTransition2 != null) {
            boolean zIsGlobalSearchPageSwitch = OplusAnimationController.isGlobalSearchPageSwitch(activeTransition2.mInfo);
            List changes = activeTransition.mInfo.getChanges();
            if (changes.size() == 1 && isActivityLevelOnly(activeTransition.mInfo)) {
                return ((TransitionInfo.Change) changes.get(0)).hasFlags(4) && zIsGlobalSearchPageSwitch && TransitionUtil.isClosingType(activeTransition.mInfo.getType());
            }
            if (zIsGlobalSearchPageSwitch && (activeTransition.mInfo.getFlags() & 128) == 0 && TransitionAnimationUtil.isTaskLevelTransition(activeTransition.mInfo) && !ShellContinuousTransitionUtils.isTaskTransitionPairIgnoreWallpaper(activeTransition2.mInfo, activeTransition.mInfo) && !isRemoteTransition(activeTransition.mToken, activeTransition.mInfo) && !KeyguardTransitionHandler.handles(activeTransition.mInfo) && !FlexibleTaskTransitionUtils.flexibleFullScreenChange(activeTransition.mInfo)) {
                for (TransitionInfo.Change change : activeTransition.mInfo.getChanges()) {
                    if (change != null && change.getMode() == 1) {
                        activeTransition.mFinishT.show(change.getLeash());
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean abortForNoWindowActivitySwitch(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2) {
        TransitionInfo transitionInfo;
        if (activeTransition == null || (transitionInfo = activeTransition.mInfo) == null || activeTransition2 != null || transitionInfo.getChanges().size() != 2) {
            return false;
        }
        boolean z = true;
        boolean z2 = true;
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change.getActivityComponent() == null) {
                z = false;
            }
            Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
            boolean z3 = windowInfoBundleFromChange != null && windowInfoBundleFromChange.getBoolean(KEY_HAS_MAIN_WINDOW, true);
            if (change.getMode() == 2 && !z3) {
                z2 = false;
            }
        }
        return z && !z2;
    }

    private boolean abortOpenTransitForReorderHome(Transitions.ActiveTransition activeTransition) {
        TransitionInfo transitionInfo;
        boolean z = false;
        if (activeTransition != null && activeTransition.mWillReorderHome && activeTransition.mLastOpenTaskForRecent != -1 && (transitionInfo = activeTransition.mInfo) != null && transitionInfo.getType() == 1) {
            int i = activeTransition.mLastOpenTaskForRecent;
            activeTransition.mWillReorderHome = false;
            activeTransition.mLastOpenTaskForRecent = -1;
            boolean z2 = false;
            boolean z3 = false;
            for (TransitionInfo.Change change : activeTransition.mInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (taskInfo != null && taskInfo.getActivityType() == 2 && TransitionUtil.isOpeningType(change.getMode())) {
                    z2 = true;
                } else if (taskInfo != null && TransitionUtil.isClosingType(change.getMode()) && taskInfo.taskId == i) {
                    z3 = true;
                }
            }
            if (z2 && z3) {
                z = true;
            }
            TransitionLog.always("Abort transition for reorder home " + z + ", transition=" + activeTransition + ", lastOpenTask=" + i);
        }
        return z;
    }

    private boolean abortRelaunch(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2) {
        TransitionInfo transitionInfo;
        if (activeTransition == null || (transitionInfo = activeTransition.mInfo) == null || activeTransition2 == null || transitionInfo.getType() != 5 || !(activeTransition2.mHandler instanceof RecentsTransitionHandler)) {
            return false;
        }
        TransitionLog.always("Abort relaunch transition " + activeTransition + " when recent " + activeTransition2 + " is playing");
        return true;
    }

    private boolean abortToFrontTransitionForReorderHome(Transitions.ActiveTransition activeTransition) {
        TransitionInfo transitionInfo;
        RecentsTransitionHandler recentsTransitionHandler;
        ActivityManager.RunningTaskInfo taskInfo;
        ActivityManager.RunningTaskInfo taskInfo2;
        if (activeTransition == null || (transitionInfo = activeTransition.mInfo) == null || transitionInfo.getType() != 3) {
            return false;
        }
        Iterator it = activeTransition.mInfo.getChanges().iterator();
        while (it.hasNext()) {
            ActivityManager.RunningTaskInfo taskInfo3 = ((TransitionInfo.Change) it.next()).getTaskInfo();
            if (taskInfo3 != null && taskInfo3.getActivityType() == 2) {
                return false;
            }
        }
        Transitions.ActiveTransition playingRecents = this.mTransitions.getPlayingRecents();
        if (playingRecents == null || playingRecents.mInfo == null || (((recentsTransitionHandler = playingRecents.mHandler) != null && recentsTransitionHandler.getInputConsumerEnabled()) || this.mTransitions.getTmpRemoteFromRecent(playingRecents) == null)) {
            return false;
        }
        for (TransitionInfo.Change change : activeTransition.mInfo.getChanges()) {
            if (change.getMode() == 6 && (taskInfo = change.getTaskInfo()) != null && !OplusPanoramaWorkBranchAnimController.getInstance().isFlexibleTaskAndHasCaption(taskInfo)) {
                for (TransitionInfo.Change change2 : playingRecents.mInfo.getChanges()) {
                    if (change2.getMode() != 3 && (taskInfo2 = change2.getTaskInfo()) != null && taskInfo2.taskId == taskInfo.taskId) {
                        TransitionLog.always("the to front content is the same to the tmpremote.");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean abortTranslucentEmbed(Transitions.ActiveTransition activeTransition) {
        TransitionInfo transitionInfo;
        if (activeTransition != null && (transitionInfo = activeTransition.mInfo) != null && transitionInfo.getType() == 1) {
            for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(activeTransition.mInfo, 1); iM >= 0; iM--) {
                TransitionInfo.Change change = (TransitionInfo.Change) activeTransition.mInfo.getChanges().get(iM);
                if (change.hasAllFlags(com.oplus.wrapper.window.TransitionInfo.FLAG_IS_BEHIND_STARTING_WINDOW | 1540) && change.getActivityComponent() != null) {
                    TransitionLog.always("Abort transition " + activeTransition + " when translucent embed activities transit.");
                    return true;
                }
            }
        }
        return false;
    }

    private void addFlagInRecentOnRemoteTargets(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, ArrayList<RemoteAnimationTarget> arrayList) {
        Class[] clsArr;
        if (transitionInfo == null || transitionInfo2 == null || arrayList == null) {
            return;
        }
        int debugId = transitionInfo.getDebugId();
        boolean zTransitionInPreReadyRemote = AdaptiveSmoothShellAnimManager.getInstance().transitionInPreReadyRemote(transitionInfo);
        SurfaceControl rootLeash = transitionInfo2.getRootLeash();
        Class[] clsArr2 = null;
        boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_MERGE_SECOND_TASK_ANIMATION, null, new Object[0])).booleanValue();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            RemoteAnimationTarget remoteAnimationTarget = arrayList.get(i);
            i++;
            RemoteAnimationTarget remoteAnimationTarget2 = remoteAnimationTarget;
            if (remoteAnimationTarget2 == null || remoteAnimationTarget2.mode == 1) {
                clsArr = clsArr2;
            } else {
                if (((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TASK_NOT_IN_RECENT_ON_EXTINFO, clsArr2, new Object[0])).booleanValue()) {
                    OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_IS_TASK_NOT_IN_RECENTS, new Class[]{Boolean.TYPE}, Boolean.TRUE);
                    TransitionLog.always("Appeared opening task " + remoteAnimationTarget2.taskId + " is not in recents, notify remote to close recents anim, notInRecent = " + OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, GET_IS_TASK_NOT_IN_RECENTS, clsArr2, new Object[0]));
                }
                if (zBooleanValue) {
                    OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_MERGE_SECOND_TASK_ANIMATION, new Class[]{Boolean.TYPE}, Boolean.TRUE);
                    TransitionLog.always("setMergeSecondTaskAnimation:" + OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, GET_MERGE_SECOND_TASK_ANIMATION, clsArr2, new Object[0]));
                }
                Integer num = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_REQUEST_TRANSITION_TASK_ID, clsArr2, new Object[0]);
                int iIntValue = num.intValue();
                if (iIntValue != -1) {
                    OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_REQUEST_TRANSITION_TASK_ID, new Class[]{Integer.TYPE}, num);
                    TransitionLog.always("Appeared opening task " + remoteAnimationTarget2.taskId + " set request transition task id " + iIntValue);
                }
                Integer num2 = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TASK_ID_START_FROM_RECENTS, null, new Object[0]);
                int iIntValue2 = num2.intValue();
                if (iIntValue2 != 0) {
                    OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_TASK_ID_START_FROM_RECENTS, new Class[]{Integer.TYPE}, num2);
                    TransitionLog.always("Appeared opening task " + remoteAnimationTarget2.taskId + " set taskId start from recents " + iIntValue2);
                }
                if (zTransitionInPreReadyRemote) {
                    OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_IN_PREREADY, new Class[]{Boolean.TYPE}, Boolean.TRUE);
                    if (rootLeash != null) {
                        OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_RECENTS_ROOT, new Class[]{SurfaceControl.class}, rootLeash);
                    }
                }
                OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_TRANSITION_ID, new Class[]{Integer.TYPE}, Integer.valueOf(debugId));
                clsArr = null;
                if (((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, "getIsLaunchFromSpruce", null, new Object[0])).booleanValue()) {
                    OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_IS_FROM_SPRUCE, new Class[]{Boolean.TYPE}, Boolean.TRUE);
                }
                TransitionLog.always("Appeared opening task " + remoteAnimationTarget2.taskId + " set transition id " + debugId);
            }
            clsArr2 = clsArr;
        }
    }

    private void adjustAppearedTargets(ArrayList<RemoteAnimationTarget> arrayList, TransitionInfo transitionInfo, IBinder iBinder) {
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || arrayList == null || transitionInfo == null) {
            return;
        }
        boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_BAL_ALLOW, null, new Object[0])).booleanValue();
        boolean zIsRemoteOpenRequestedFrom = this.mTransitions.isRemoteOpenRequestedFrom(iBinder, "QuickstepLaunch");
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            RemoteAnimationTarget remoteAnimationTarget = arrayList.get(i);
            i++;
            RemoteAnimationTarget remoteAnimationTarget2 = remoteAnimationTarget;
            int i2 = remoteAnimationTarget2.mode;
            if (i2 == 0 || i2 == 2) {
                ActivityManager.RunningTaskInfo runningTaskInfo = remoteAnimationTarget2.taskInfo;
                if (runningTaskInfo != null && runningTaskInfo.topActivityType == 1) {
                    if (zBooleanValue && !zIsRemoteOpenRequestedFrom) {
                        OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_IS_BAL_ALLOW, new Class[]{Boolean.TYPE}, Boolean.TRUE);
                        TransitionLog.debug("set remoteTarget: " + remoteAnimationTarget2 + " is balallow");
                    } else if (zIsRemoteOpenRequestedFrom) {
                        OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget2, SET_IS_REMOTE_FROM_LAUNCHER, new Class[]{Boolean.TYPE}, Boolean.TRUE);
                    }
                }
            }
        }
    }

    private void adjustBgLayerIfNeed(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, boolean z, String str) {
        if (!z || transitionInfo == null || transitionInfo.getChanges().size() < 2 || this.mTransitionBgMap.isEmpty()) {
            return;
        }
        TransitionInfo.Change change = null;
        for (TransitionInfo.Change change2 : transitionInfo.getChanges()) {
            if (change2.getTaskInfo() != null && change2.getTaskInfo().getActivityType() == 2) {
                change = change2;
            }
        }
        List<SurfaceControl> list = this.mTransitionBgMap.get(Integer.valueOf(transitionInfo.getTrack()));
        if (list != null) {
            for (SurfaceControl surfaceControl : list) {
                if (change != null && change.getLeash() != null && change.getLeash().isValid()) {
                    transaction.setRelativeLayer(surfaceControl, change.getLeash(), 1);
                    if ("forInitialize".equals(str)) {
                        transaction.setAlpha(surfaceControl, 0.0f);
                    }
                }
            }
        }
    }

    private SurfaceControl.Transaction adjustFinishChangeInfoForBackAnim(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, RemoteAnimationTarget[] remoteAnimationTargetArr, Transitions.ActiveTransition activeTransition) {
        SurfaceControl surfaceControl;
        SurfaceControl surfaceControl2;
        if (remoteAnimationTargetArr == null || remoteAnimationTargetArr.length <= 1) {
            surfaceControl = null;
            surfaceControl2 = null;
        } else {
            surfaceControl = null;
            surfaceControl2 = null;
            for (int length = remoteAnimationTargetArr.length - 1; length >= 0; length--) {
                RemoteAnimationTarget remoteAnimationTarget = remoteAnimationTargetArr[length];
                int i = remoteAnimationTarget.mode;
                if (i == 0) {
                    surfaceControl = remoteAnimationTarget.leash;
                } else if (i == 1) {
                    surfaceControl2 = remoteAnimationTarget.leash;
                }
            }
        }
        if (surfaceControl != null && surfaceControl2 != null) {
            for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM += -1) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
                SurfaceControl surfaceControl3 = TransitionUtil.isOpeningMode(change.getMode()) ? surfaceControl2 : TransitionUtil.isClosingMode(change.getMode()) ? surfaceControl : null;
                if (SpringSlideAnimationController.ENABLE_SPRING_SLIDE_ANIM) {
                    SpringSlideAnimationController.ContinueValue matchedContinueValue = AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController().getMatchedContinueValue(activeTransition.mToken, change, true);
                    if (matchedContinueValue != null && change.getLeash() != null && change.getLeash().isValid() && surfaceControl3 != null) {
                        transaction.reparent(change.getLeash(), surfaceControl3).setAlpha(change.getLeash(), 1.0f).setPosition(surfaceControl3, matchedContinueValue.posX, 0.0f).setAlpha(surfaceControl3, matchedContinueValue.alpha);
                        if (matchedContinueValue.cornerRadius > 0.0f) {
                            transaction.setCornerRadius(change.getLeash(), matchedContinueValue.cornerRadius).setCrop(change.getLeash(), change.getEndAbsBounds());
                        }
                    }
                } else {
                    ArrayMap backAnimContinueTransformMap = Transitions.getBackAnimContinueTransformMap(activeTransition.mToken);
                    if (backAnimContinueTransformMap == null) {
                        break;
                    }
                    Transformation transformation = TransitionUtil.isClosingMode(change.getMode()) ? (Transformation) backAnimContinueTransformMap.get(0) : TransitionUtil.isOpeningMode(change.getMode()) ? (Transformation) backAnimContinueTransformMap.get(1) : null;
                    if (transformation != null && change.getLeash() != null && change.getLeash().isValid()) {
                        float windowCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(ActivityThread.currentActivityThread().getSystemUiContext());
                        float[] fArr = new float[9];
                        transaction.reparent(change.getLeash(), surfaceControl3);
                        if (TransitionUtil.isOpeningMode(change.getMode())) {
                            transaction.setCornerRadius(change.getLeash(), windowCornerRadius);
                        }
                        transaction.setCrop(change.getLeash(), change.getEndAbsBounds());
                        transaction.setAlpha(change.getLeash(), 1.0f);
                        transaction.setMatrix(surfaceControl3, transformation.getMatrix(), fArr);
                        transaction.setAlpha(surfaceControl3, transformation.getAlpha());
                    }
                }
                if (TransitionUtil.isClosingMode(change.getMode()) && change.getLeash() != null && change.getLeash().isValid()) {
                    transaction.show(change.getLeash()).setLayer(surfaceControl, -1);
                }
                TransitionLog.always("adjustFinishChangeInfoForBackAnim, c.getLeash()=" + change.getLeash() + ", continueAnimLeash=" + surfaceControl3);
            }
        }
        return transaction;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x019a  */
    /* JADX WARN: Multi-variable type inference failed */
    private SurfaceControl.Transaction adjustFinishChangeInfoForDefaultAnim(TransitionInfo transitionInfo, Transitions.ActiveTransition activeTransition, SurfaceControl.Transaction transaction) {
        boolean z;
        String str;
        SpringSlideAnimationController springSlideAnimationController;
        boolean z2;
        Transformation transformation;
        String str2;
        ShellContinuousTransitionController shellContinuousTransitionController = this;
        TransitionInfo transitionInfo2 = activeTransition.mInfo;
        if (transitionInfo2 != null) {
            SpringSlideAnimationController springSlideAnimationController2 = AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController();
            SurfaceControl.Transaction transaction2 = activeTransition.mStartT;
            boolean z3 = false;
            boolean z4 = true;
            Object[] objArr = (transaction2 == null || transaction2.mNativeObject == 0) ? false : true;
            ArrayMap<Integer, Transformation> backAnimContinueTransformMap = Transitions.getBackAnimContinueTransformMap(activeTransition.mToken);
            int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1);
            while (iM >= 0) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
                int iRootIndexFor = TransitionUtil.rootIndexFor(change, transitionInfo2);
                SurfaceControl leash = transitionInfo2.getRoot(iRootIndexFor).getLeash();
                if (leash.isValid()) {
                    SpringSlideAnimationController.ContinueValue matchedContinueValue = springSlideAnimationController2.getMatchedContinueValue(activeTransition.mToken, change, z3);
                    z = z3;
                    if (matchedContinueValue != null) {
                        int useSpringSlideAnimResId = springSlideAnimationController2.getUseSpringSlideAnimResId(change);
                        str = TAG;
                        if (useSpringSlideAnimResId != -1) {
                            TransitionInfo.Change readyChangeBasedOnActiveChange = ShellContinuousTransitionUtils.getReadyChangeBasedOnActiveChange(transitionInfo2, change);
                            if (shellContinuousTransitionController.isValidReadyChange(readyChangeBasedOnActiveChange)) {
                                SurfaceControl leash2 = readyChangeBasedOnActiveChange.getLeash();
                                springSlideAnimationController = springSlideAnimationController2;
                                transaction.setPosition(leash2, matchedContinueValue.posX, 0.0f).setAlpha(leash2, matchedContinueValue.alpha).reparent(leash2, leash).show(leash2).show(transitionInfo2.getRoot(iRootIndexFor).getLeash());
                                float f = matchedContinueValue.cornerRadius;
                                if (f > 0.0f) {
                                    OplusTransitionReflectionHelper.setCornerRadiusExt(transaction, leash2, f, 2.5f, 1);
                                    transaction.setCrop(leash2, readyChangeBasedOnActiveChange.getEndAbsBounds());
                                }
                                if (objArr != false) {
                                    TransitionLog.always(leash2 + "ready.mStartT = " + activeTransition.mStartT + " setPosition = " + matchedContinueValue.posX + " setAlpha = " + matchedContinueValue.alpha);
                                    activeTransition.mStartT.setPosition(leash2, matchedContinueValue.posX, 0.0f);
                                    activeTransition.mStartT.setAlpha(leash2, matchedContinueValue.alpha);
                                }
                            } else {
                                springSlideAnimationController = springSlideAnimationController2;
                            }
                            str2 = str;
                            z2 = true;
                        }
                        Log.d(str2, "adjustFinishChangeInfoForDefaultClose change: " + change);
                    } else {
                        str = TAG;
                    }
                    springSlideAnimationController = springSlideAnimationController2;
                    if (backAnimContinueTransformMap != null) {
                        if (TransitionUtil.isClosingMode(change.getMode())) {
                            transformation = backAnimContinueTransformMap.get(Integer.valueOf(z ? 1 : 0));
                            z2 = true;
                        } else {
                            z2 = true;
                            transformation = TransitionUtil.isOpeningMode(change.getMode()) ? backAnimContinueTransformMap.get(1) : null;
                        }
                        if (transformation != null) {
                            float continueCornerRadius = ShellContinuousTransitionUtils.getContinueCornerRadius(transitionInfo2, TransitionAnimationUtil.getComponentNameFromChange(change), change);
                            TransitionInfo.Change readyChangeBasedOnActiveChange2 = ShellContinuousTransitionUtils.getReadyChangeBasedOnActiveChange(transitionInfo2, change);
                            if (shellContinuousTransitionController.isValidReadyChange(readyChangeBasedOnActiveChange2)) {
                                Transformation transformation2 = transformation;
                                SurfaceControl leash3 = readyChangeBasedOnActiveChange2.getLeash();
                                TransitionLog.always(leash3 + " reparent to parent = " + leash + " setAlpha = " + transformation2.getAlpha());
                                if (objArr != false) {
                                    activeTransition.mStartT.setMatrix(leash3, transformation2.getMatrix(), new float[9]);
                                    activeTransition.mStartT.setAlpha(leash3, transformation2.getAlpha());
                                }
                                shellContinuousTransitionController = this;
                                str2 = str;
                                shellContinuousTransitionController.updateContinueLeashState(transformation2, leash3, leash, continueCornerRadius, transaction, readyChangeBasedOnActiveChange2.getEndAbsBounds());
                            } else {
                                str2 = str;
                            }
                        } else {
                            str2 = str;
                        }
                        Log.d(str2, "adjustFinishChangeInfoForDefaultClose change: " + change);
                    }
                } else {
                    Log.w(TAG, "no need to reparent if Transition Root B is invalid");
                    springSlideAnimationController = springSlideAnimationController2;
                    z = z3;
                    z2 = z4;
                }
                iM--;
                z3 = z;
                z4 = z2;
                springSlideAnimationController2 = springSlideAnimationController;
            }
            shellContinuousTransitionController.reparentAnimBackgroundToNextRoot(transitionInfo, activeTransition, transaction, backAnimContinueTransformMap);
            return transaction;
        }
        return transaction;
    }

    private boolean applyStartTOnAbortForEmbeddedIfNeed(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2) {
        TransitionInfo transitionInfo;
        Transitions.TransitionHandler transitionHandler = activeTransition.mHandler;
        if (transitionHandler == null || activeTransition2.mStartT == null || (transitionInfo = activeTransition2.mInfo) == null || !(transitionHandler instanceof DefaultTransitionHandler)) {
            return false;
        }
        return ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_NEED_APPLY_START_T_FOR_EMBEDDED, null, new Object[0])).booleanValue();
    }

    private boolean avoidToFrontContinuousSpruce(TransitionInfo transitionInfo, Transitions.ActiveTransition activeTransition) {
        if (transitionInfo == null || activeTransition == null || activeTransition.mInfo == null || ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TASK_ID_START_FROM_RECENTS, null, new Object[0])).intValue() == 0 || !((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(activeTransition.mInfo, "getIsLaunchFromSpruce", null, new Object[0])).booleanValue()) {
            return false;
        }
        TransitionLog.always("ShellContinuousTransitionControlleravoiding a toFront triggered by recents to continuous spruce anim");
        return true;
    }

    private SurfaceControl.Transaction fixFinishTBackAnimContinue(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, Transitions.ActiveTransition activeTransition, WindowContainerTransaction windowContainerTransaction) {
        if (!ShellContinuousTransitionUtils.ENABLE_PREDICTIVE_CONTINUOUS_REMOTE || activeTransition == null || activeTransition.mInfo == null || !SharedReflectionHelper.getBackAnimContinuous(windowContainerTransaction) || (!AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController().hasContinueInfo(activeTransition.mToken) && Transitions.getBackAnimContinueTransformMap(activeTransition.mToken) == null)) {
            return null;
        }
        if (activeTransition.mInfo.getType() == 13) {
            BackAnimationController.BackTransitionHandler backTransitionHandler = activeTransition.mHandler;
            if (backTransitionHandler instanceof BackAnimationController.BackTransitionHandler) {
                return adjustFinishChangeInfoForBackAnim(transitionInfo, transaction, backTransitionHandler.getRemoteAnimationTargets(), activeTransition);
            }
        }
        if ((TransitionUtil.isClosingType(activeTransition.mInfo.getType()) || TransitionUtil.isOpeningMode(activeTransition.mInfo.getType())) && isDefaultContinuous()) {
            return adjustFinishChangeInfoForDefaultAnim(transitionInfo, activeTransition, transaction);
        }
        return null;
    }

    private void fixStartTOnRecentsMerge(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, ArrayList<RemoteAnimationTarget> arrayList) {
        if (transitionInfo != null && arrayList == null) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                setAlphaForOpenActivityIfNeeded(transitionInfo, change, transaction);
                if (change.getStartRotation() != change.getEndRotation()) {
                    setPositionForFixRotation(transitionInfo, change, transaction);
                }
            }
        }
    }

    private int getBgColorForActivityTransition(TransitionInfo transitionInfo, TransitionInfo.Change change, Animation animation) {
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            return TransitionAnimationHelper.getTransitionBackgroundColorIfSet(change, animation, ActivityThread.currentActivityThread().getSystemUiContext().getColor(R.color.primary_text_disable_only_holo_dark));
        }
        return 0;
    }

    private float getDefaultCornerRadius() {
        return ScreenDecorationsUtils.getWindowCornerRadius(ActivityThread.currentActivityThread().getSystemUiContext());
    }

    private int getLastOpenTaskIdInInterrupt() {
        Transitions.ActiveTransition playingRecents = this.mTransitions.getPlayingRecents();
        if (playingRecents == null) {
            return -1;
        }
        ArrayList arrayList = new ArrayList();
        Transitions.ActiveTransition tmpRemoteFromRecent = this.mTransitions.getTmpRemoteFromRecent(playingRecents);
        ArrayList arrayList2 = playingRecents.mMerged;
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
        }
        if (tmpRemoteFromRecent != null) {
            arrayList.addFirst(tmpRemoteFromRecent);
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            for (TransitionInfo.Change change : ((Transitions.ActiveTransition) arrayList.get(size)).mInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (taskInfo != null && TransitionUtil.isOpeningType(change.getMode()) && taskInfo.getActivityType() != 2) {
                    return taskInfo.taskId;
                }
            }
        }
        return -1;
    }

    private void handleLeashBeforeStartTApply(TransitionInfo.Change change, Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2) {
        SurfaceControl leash;
        boolean z = false;
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(activeTransition.mInfo, GET_MERGE_SECOND_TASK_ANIMATION, null, new Object[0]);
        Boolean bool2 = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(activeTransition.mInfo, GET_ABORT_BY_REMOTE_FROM_HOME, null, new Object[0]);
        boolean z2 = bool != null && bool.booleanValue();
        if (bool2 != null && bool2.booleanValue()) {
            z = true;
        }
        if (z2 && z && TransitionUtil.isOpeningMode(change.getMode()) && change.getTaskInfo() != null && (activeTransition2.mHandler instanceof RecentsTransitionHandler) && (leash = change.getLeash()) != null && leash.isValid()) {
            activeTransition.mStartT.hide(change.getLeash());
            Log.d(TAG, "hide for " + change + " when mergeSecondTaskAnimation && abortByRemoteFromHome.");
        }
    }

    private boolean hasRotationChange(TransitionInfo transitionInfo) {
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getMode() == 6 && change.hasFlags(32)) {
                int startRotation = change.getStartRotation();
                int endRotation = change.getEndRotation();
                TransitionLog.always("isRotationChange and startRotation: " + startRotation + " endRotation: " + endRotation);
                if (startRotation != endRotation) {
                    return true;
                }
            }
        }
        return false;
    }

    private void hideTaskForMultiTask(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, SurfaceControl.Transaction transaction) {
        if (transitionInfo != null && transitionInfo.getChanges() != null && transitionInfo2 != null && transitionInfo2.getChanges() != null && transitionInfo.getChanges().size() == 2 && transitionInfo2.getChanges().size() == 1 && isSecondTaskAnimationToRecents(transitionInfo2) && transitionInfo.getType() == 1 && transitionInfo2.getType() == 1) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(0);
            TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo2.getChanges().get(0);
            if (change == null || change.getLeash() == null || change2 == null || change.getMode() != 1 || change2.getMode() != 3 || !change2.hasFlags(TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) || !change2.hasFlags(4) || change.getTaskInfo() == null || change.getTaskInfo().baseActivity == null || change2.getTaskInfo() == null || change2.getTaskInfo().baseActivity == null || !TextUtils.equals(change.getTaskInfo().baseActivity.getPackageName(), change2.getTaskInfo().baseActivity.getPackageName())) {
                return;
            }
            TransitionLog.always(" hideTaskForMultiTask taskLeash = " + change.getLeash());
            transaction.hide(change.getLeash());
        }
    }

    private boolean isActivityLevelOnly(TransitionInfo transitionInfo) {
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            if (((TransitionInfo.Change) transitionInfo.getChanges().get(iM)).getActivityComponent() == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isActivityLevelOnlyOrTaskFragmentOnly(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if ((transitionInfo2.getFlags() & 64) != 0 || (transitionInfo.getFlags() & 256) == 0) {
            return false;
        }
        return isActivityLevelOnly(transitionInfo2) || isTaskFragmentOnly(transitionInfo2);
    }

    private boolean isArOrTranslucentTransition(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = true;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() != null && change.getTaskInfo().getActivityType() != 2) {
                return false;
            }
            if (change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) {
                z3 = true;
            }
            if (change.getTaskInfo() == null && !change.hasFlags(2)) {
                z4 = TransitionUtil.isOpeningMode(change.getMode()) && change.hasFlags(4);
                z = true;
            }
            if (change.getTaskInfo() == null && TransitionUtil.isOpeningType(transitionInfo.getType()) && TransitionUtil.isOpeningType(change.getMode())) {
                transaction.setAlpha(change.getLeash(), 1.0f);
            }
            if (OplusActivityEmbeddingAnimationRunner.getInstance().isSettingsTaskFragment(change) && ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change, "TaskFragment") && change.hasFlags(OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) && TransitionUtil.isOpeningMode(change.getMode()) && change.hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) && transitionInfo.getChanges().size() == 3) {
                z2 = true;
            } else if (!OplusActivityEmbeddingAnimationRunner.getInstance().isSettingsTaskFragment(change) || !ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change, "TaskFragment") || change.hasFlags(OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) || !TransitionUtil.isClosingMode(change.getMode()) || !change.hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION)) {
                z5 = false;
            }
        }
        return z3 ? z4 : (z && z2 && z5) ? false : z;
    }

    private boolean isBackToHome(TransitionInfo transitionInfo) {
        boolean z;
        boolean z2;
        if (transitionInfo != null && transitionInfo.getChanges() != null) {
            int size = transitionInfo.getChanges().size();
            if (size == 2 && transitionInfo.getType() == 2) {
                z = false;
                z2 = false;
                for (int i = size - 1; i >= 0; i--) {
                    TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
                    if (change == null) {
                        return false;
                    }
                    if (change.getMode() == 3 && change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) {
                        TransitionLog.debug("isHomeToFront");
                        z = true;
                    } else if (change.getMode() == 2) {
                        TransitionLog.debug("isOpeningAppToClose");
                        z2 = true;
                    }
                }
            } else {
                z = false;
                z2 = false;
            }
            if (z && z2) {
                return true;
            }
        }
        return false;
    }

    private boolean isCanvasTask(ActivityManager.RunningTaskInfo runningTaskInfo) {
        return (runningTaskInfo == null || (TransitionAnimationUtil.getOplusFlags(runningTaskInfo.baseIntent) & 16384) == 0) ? false : true;
    }

    private boolean isChangeAnimationFromLandToPort(TransitionInfo transitionInfo) {
        if (transitionInfo != null && transitionInfo.getType() == 6) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if (change.getMode() == 6 && change.hasFlags(32)) {
                    int startRotation = change.getStartRotation();
                    int endRotation = change.getEndRotation();
                    boolean z = startRotation == 1 || startRotation == 3;
                    boolean z2 = endRotation == 0 || endRotation == 2;
                    if (!z || !z2) {
                        break;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isChangeInTransition(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        Stream stream = transitionInfo.getChanges().stream();
        ShellContinuousTransitionController$$ExternalSyntheticLambda0 shellContinuousTransitionController$$ExternalSyntheticLambda0 = new ShellContinuousTransitionController$$ExternalSyntheticLambda0(0);
        shellContinuousTransitionController$$ExternalSyntheticLambda0.f$0 = change;
        VarHandle.storeStoreFence();
        return stream.anyMatch(shellContinuousTransitionController$$ExternalSyntheticLambda0);
    }

    private boolean isEmbeddedSecondHopWithRedundantHomeToFront(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (transitionInfo == null || transitionInfo2 == null || transitionInfo.getType() != 1) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (change != null) {
                if (isSameOpeningHomeOrRecentsTaskInPlayingTransition(change, transitionInfo2)) {
                    z = true;
                } else {
                    if (!change.hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION)) {
                        return false;
                    }
                    int mode = change.getMode();
                    if (!TransitionUtil.isOpeningType(mode) && mode != 6) {
                        return false;
                    }
                    z2 = true;
                }
            }
        }
        return z && z2;
    }

    private boolean isIgnoreAbortPkg(TransitionInfo.Change change) {
        if (change == null || change.getLeash() == null) {
            TransitionLog.w("ignoreAbort, change = " + change + ", return false");
            return false;
        }
        String string = change.getLeash().toString();
        if (string == null) {
            TransitionLog.w("ignoreAbort, leash is null, return false");
            return false;
        }
        TransitionLog.debug("ignoreAbort isIgnoreAbortPkg, leash: " + string);
        Iterator<String> it = IGNORE_ABORT_TRANSITION_PKGS.iterator();
        while (it.hasNext()) {
            if (string.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean isInvalidTaskTransition(TransitionInfo transitionInfo) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() == null) {
                return false;
            }
            if (TransitionUtil.isOpeningMode(change.getMode())) {
                z = true;
            }
            if (change.getMode() == 6) {
                z2 = true;
            }
            if (FlexibleTaskTransitionUtils.isFlexibleTaskChange(change)) {
                z3 = true;
            }
        }
        TransitionLog.always("ShellContinuousTransitionControllerisInvalidTaskTransition, transition " + transitionInfo.getDebugId() + ": hasOpenChange = " + z + " ,hasChangeModeChange =" + z2 + ", hasFlexibleTask = " + z3);
        return (z || z2 || z3) ? false : true;
    }

    private boolean isLandAppOpen(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            int endFixedRotation = ((TransitionInfo.Change) transitionInfo.getChanges().get(iM)).getEndFixedRotation();
            if (transitionInfo.getType() == 1 && (endFixedRotation == 1 || endFixedRotation == 3)) {
                TransitionLog.debug("isLandAppOpen: endFixedRotation = " + endFixedRotation);
                return true;
            }
        }
        return false;
    }

    private static boolean isLargeSmallScreenSwitchAnim(TransitionInfo transitionInfo) {
        if (transitionInfo.getType() != 6) {
            return false;
        }
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.hasFlags(32) && change.getStartRotation() == change.getEndRotation()) {
                return true;
            }
        }
        return false;
    }

    private boolean isPureLauncherTransition(TransitionInfo transitionInfo) {
        boolean z = true;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (!ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change, "com.android.launcher") || (change.getMode() != 1 && change.getMode() != 4)) {
                z = false;
            }
        }
        return z;
    }

    private boolean isRotationChange(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        if (transitionInfo.getType() == 10 || transitionInfo.getType() == 1 || transitionInfo.getType() == 2) {
            return hasRotationChange(transitionInfo);
        }
        return false;
    }

    private boolean isSameLevelTransition(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, boolean z) {
        if (transitionInfo != null && transitionInfo2 != null) {
            boolean z2 = DefaultTransitionHandler.isActivityLevelOnly(transitionInfo) && DefaultTransitionHandler.isActivityLevelOnly(transitionInfo2);
            boolean z3 = DefaultTransitionHandler.isTaskTransition(transitionInfo) && DefaultTransitionHandler.isTaskTransition(transitionInfo2) && !z;
            boolean z4 = DefaultTransitionHandler.isTaskTransition(transitionInfo) && isTaskLevelForPredictiveBack(transitionInfo2) && z;
            boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_OPLUS_ANIM_RES, null, new Object[0])).booleanValue();
            if ((z2 || z3 || z4) && isTargetMatch(transitionInfo, transitionInfo2) && zBooleanValue) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameOpeningHomeOrRecentsTaskInPlayingTransition(TransitionInfo.Change change, TransitionInfo transitionInfo) {
        if (change.getTaskInfo() != null && TransitionUtil.isOpeningType(change.getMode())) {
            for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
                TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
                ActivityManager.RunningTaskInfo taskInfo = change2.getTaskInfo();
                if (taskInfo != null && taskInfo.topActivityType == 2 && change2.getContainer() != null && change2.getContainer().equals(change.getContainer()) && TransitionUtil.isOpeningType(change2.getMode())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSystemApp(ActivityManager.RunningTaskInfo runningTaskInfo) {
        String packageName;
        if (runningTaskInfo == null) {
            return true;
        }
        ComponentName componentName = runningTaskInfo.baseActivity;
        if (componentName != null) {
            packageName = componentName.getPackageName();
        } else {
            ComponentName componentName2 = runningTaskInfo.realActivity;
            packageName = componentName2 != null ? componentName2.getPackageName() : null;
        }
        if (packageName == null) {
            packageName = OplusAnimationController.PACKAGE_SYSTEM;
        }
        return TransitionAnimationUtil.isSystemApp(packageName);
    }

    private boolean isTargetMatch(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (transitionInfo == null || transitionInfo2 == null || transitionInfo.getChanges().size() != transitionInfo2.getChanges().size() || transitionInfo.getChanges().size() != 2) {
            return false;
        }
        return TransitionAnimationUtil.hasSameTaskIdOrMatchInfo(transitionInfo, transitionInfo2) || TransitionAnimationUtil.hasSameActivityInBothTransition(transitionInfo, transitionInfo2);
    }

    private boolean isTaskFragmentOnly(TransitionInfo transitionInfo) {
        if (transitionInfo.getChanges() == null || transitionInfo.getChanges().size() < 1) {
            return false;
        }
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change != null && change.getLeash() != null && !ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change, "TaskFragment")) {
                return false;
            }
        }
        return true;
    }

    private boolean isTaskLevelForPredictiveBack(TransitionInfo transitionInfo) {
        if (transitionInfo.getChanges().size() != 2) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change.getTaskInfo() == null) {
                return false;
            }
            int mode = change.getMode();
            z |= mode == 3;
            z2 |= mode == 6;
        }
        return z && z2;
    }

    private boolean isTaskLevelOnly(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            if (((TransitionInfo.Change) transitionInfo.getChanges().get(iM)).getTaskInfo() == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isTranslucentActivityToHome(TransitionInfo transitionInfo) {
        boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_TRANSLUCENT_ACTIVITY_TO_HOME, null, new Object[0])).booleanValue();
        if (transitionInfo.getChanges().size() == 1) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(0);
            if (zBooleanValue && needMatchRemotePkg(change)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidReadyChange(TransitionInfo.Change change) {
        return (change == null || change.getLeash() == null || !change.getLeash().isValid()) ? false : true;
    }

    private boolean isWillFinishToHome(TransitionInfo transitionInfo) {
        boolean z = false;
        boolean z2 = false;
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change.getTaskInfo() != null) {
                if (change.getTaskInfo().getActivityType() == 2 && (change.getMode() == 3 || change.getMode() == 6)) {
                    z = true;
                } else if (change.getTaskInfo().getActivityType() != 2 && TransitionUtil.isClosingMode(change.getMode())) {
                    z2 = true;
                }
            }
        }
        return z && z2;
    }

    private boolean keyBackPressWithAvoidQuickBack(TransitionInfo transitionInfo) {
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            return ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_AVOID_INTERCEPT_KEY_EVENT_TO_HOME, null, new Object[0])).booleanValue() || isBackToHome(transitionInfo);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isChangeInTransition$1(TransitionInfo.Change change, TransitionInfo.Change change2) {
        return (change2.getMode() == 1 || change2.getMode() == 3) && change2.getContainer().equals(change.getContainer());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$showOpenTaskInFinishIfNeeded$0(WindowContainerToken windowContainerToken, TransitionInfo.Change change) {
        return windowContainerToken.equals(change.getContainer());
    }

    private void markMergeSecondTaskId(Transitions.ActiveTransition activeTransition, boolean z, int i) {
        TransitionInfo transitionInfo;
        if (activeTransition == null || (transitionInfo = activeTransition.mInfo) == null || !z || i == -1) {
            return;
        }
        OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_MERGE_SECOND_TASK_ID, new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    private void markReorderHome() {
        ArrayList pendingTransitions = this.mTransitions.getPendingTransitions();
        if (pendingTransitions.size() == 0) {
            return;
        }
        int lastOpenTaskIdInInterrupt = getLastOpenTaskIdInInterrupt();
        int size = pendingTransitions.size();
        int i = 0;
        while (i < size) {
            Object obj = pendingTransitions.get(i);
            i++;
            Transitions.ActiveTransition activeTransition = (Transitions.ActiveTransition) obj;
            if (activeTransition != null) {
                activeTransition.mWillReorderHome = true;
                activeTransition.mLastOpenTaskForRecent = lastOpenTaskIdInInterrupt;
            }
        }
    }

    private boolean matchContinuous(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, SurfaceControl.Transaction transaction) {
        SurfaceControl.Transaction transaction2;
        if (transitionInfo == null || transitionInfo2 == null) {
            Log.w(TAG, "no merge remote");
            return false;
        }
        boolean z = TransitionAnimationUtil.hasSameTaskIdOrMatchInfo(transitionInfo, transitionInfo2) || matchContinuousForOpenTranslucent(transitionInfo, transitionInfo2);
        BluetoothUtils$.ExternalSyntheticOutline0.m("matchContinuous ", TAG, z);
        if (!z) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if (change.getTaskInfo() == null || change.getTaskInfo().getActivityType() == 2 || OplusPanoramaWorkBranchAnimController.getInstance().shouldSkipResetFlexibleTaskPosition(change) || change.getLeash() == null || !change.getLeash().isValid()) {
                    transaction2 = transaction;
                } else {
                    Log.d(TAG, "not matchContinuous, reset");
                    transaction.setCrop(change.getLeash(), new Rect());
                    transaction2 = transaction;
                    transaction2.setMatrix(change.getLeash(), 1.0f, 0.0f, 0.0f, 1.0f);
                    transaction2.setCornerRadius(change.getLeash(), 0.0f);
                    transaction2.setPosition(change.getLeash(), 0.0f, 0.0f);
                    transaction2.setAlpha(change.getLeash(), 1.0f);
                }
                transaction = transaction2;
            }
        }
        return z;
    }

    private boolean matchContinuousForOpenTranslucent(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (transitionInfo == null || transitionInfo2 == null || !TransitionUtil.isOpeningMode(transitionInfo.getType()) || transitionInfo2.getType() != 13) {
            return false;
        }
        ArrayList taskIdFromTargets = TransitionAnimationUtil.getTaskIdFromTargets(transitionInfo);
        ArrayList taskIdFromTargets2 = TransitionAnimationUtil.getTaskIdFromTargets(transitionInfo2);
        if (taskIdFromTargets.isEmpty() || !taskIdFromTargets2.containsAll(taskIdFromTargets)) {
            return false;
        }
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() != null && change.getTaskInfo().getActivityType() != 2 && (change.getFlags() & 4) != 0) {
                Log.d(TAG, "matchContinuousForOpenTranslucent true");
                return true;
            }
        }
        return false;
    }

    private boolean misMatchActiveTask(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, TransitionInfo.Change change) {
        ComponentName activityComponent = change.getActivityComponent();
        if (activityComponent == null) {
            return false;
        }
        if (isActivityLevelOnly(transitionInfo2)) {
            Slog.d(TAG, "isActivityLevelOnly, mergedChangeComponentName:" + activityComponent);
            return false;
        }
        String packageName = activityComponent.getPackageName();
        for (TransitionInfo.Change change2 : transitionInfo.getChanges()) {
            if (change2.getMode() != 3 && change2.getTaskInfo() != null) {
                ComponentName componentName = change2.getTaskInfo().baseActivity;
                if (componentName == null) {
                    return false;
                }
                String packageName2 = componentName.getPackageName();
                Slog.d(TAG, "activeChange task:" + packageName2 + ",mergedChange task:" + packageName);
                return !packageName2.equals(packageName);
            }
        }
        return false;
    }

    private boolean needMatchRemoteCmp(TransitionInfo.Change change) {
        ComponentName componentName;
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        return (taskInfo == null || (componentName = ((TaskInfo) taskInfo).realActivity) == null || !MATCH_REMOTE_CMP.contains(componentName.flattenToShortString())) ? false : true;
    }

    private boolean needMatchRemotePkg(TransitionInfo.Change change) {
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        return taskInfo != null && MATCH_REMOTE_APP.contains(TransitionAnimationUtil.getPackageNameFromTaskInfo(taskInfo));
    }

    private boolean nextWillCloseLauncher(int i) {
        Transitions.ActiveTransition activeTransitionNextReadyTransition;
        TransitionInfo transitionInfo;
        TransitionInfo.Change change;
        ActivityManager.RunningTaskInfo taskInfo;
        if (i >= 0 && (activeTransitionNextReadyTransition = this.mTransitions.nextReadyTransition(i)) != null && (transitionInfo = activeTransitionNextReadyTransition.mInfo) != null) {
            List changes = transitionInfo.getChanges();
            if (changes.size() > 0 && changes.size() <= 2 && TransitionUtil.isOpeningMode(transitionInfo.getType()) && (taskInfo = (change = (TransitionInfo.Change) changes.get(0)).getTaskInfo()) != null && taskInfo.getActivityType() != 2 && !TransitionUtil.isClosingType(change.getMode())) {
                Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
                boolean z = windowInfoBundleFromChange != null ? windowInfoBundleFromChange.getBoolean(KEY_LAUNCH_FROM_SPRUCE, false) : false;
                boolean zIsStartFromSysUIPlugin = OplusAnimationController.isStartFromSysUIPlugin(transitionInfo);
                if ((changes.size() == 2 && (z || zIsStartFromSysUIPlugin)) || changes.size() == 1) {
                    TransitionLog.always("nextWillCloseLauncher, Prevent reorder launcherTask, changes.size=" + changes.size() + ", fromSpruce = " + z + ", fromSysUIPlugin = " + zIsStartFromSysUIPlugin);
                    return true;
                }
            }
            for (int i2 = 0; i2 < changes.size(); i2++) {
                TransitionInfo.Change change2 = (TransitionInfo.Change) changes.get(i2);
                if (change2.getTaskInfo() != null && change2.getTaskInfo().getActivityType() == 2 && TransitionUtil.isClosingType(change2.getMode())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean normalSameTaskLevelTransition(Transitions.ActiveTransition activeTransition, IBinder iBinder, TransitionInfo transitionInfo, boolean z) {
        String pkgNameFromChange;
        if (transitionInfo == null) {
            return false;
        }
        if (isRemoteTransition(iBinder, transitionInfo)) {
            Slog.d(TAG, "ifMergeIntoRemote, transiton " + transitionInfo.getDebugId() + " is remote, do not merge into remote.");
            return false;
        }
        String str = null;
        int i = -1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = true;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (!z || !change.hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) || (pkgNameFromChange = OplusEmbedReflectionUtils.getPkgNameFromChange(change)) == null || !PHONE_MANAGER_PKG.contains(pkgNameFromChange)) {
                if (change.getTaskInfo() == null) {
                    return false;
                }
                String packageNameFromTaskInfo = TransitionAnimationUtil.getPackageNameFromTaskInfo(change.getTaskInfo());
                if ("com.android.launcher".equals(packageNameFromTaskInfo)) {
                    return false;
                }
                if (str == null) {
                    str = packageNameFromTaskInfo;
                } else if (!str.equals(packageNameFromTaskInfo)) {
                    Set<String> set = PHONE_MANAGER_PKG;
                    if (!set.contains(str) || !set.contains(packageNameFromTaskInfo)) {
                        z5 = false;
                    }
                }
                if (TransitionUtil.isOpeningType(change.getMode())) {
                    i = change.getTaskInfo().taskId;
                }
                if (change.hasFlags(OplusAnimationController.FLAG_CANCEL_FIXED_ROTATION)) {
                    z2 = true;
                }
                if ((transitionInfo.getType() == 1 || transitionInfo.getType() == 2) && ((change.getMode() == 2 || change.getMode() == 4) && change.getFlags() == 0)) {
                    z3 = true;
                }
                if (transitionInfo.getType() == 1 && change.getMode() == 1 && change.hasFlags(4)) {
                    z4 = true;
                }
            }
        }
        if (OplusPanoramaWorkBranchAnimController.getInstance().isOnlyFlexibleCloseAnim(transitionInfo)) {
            Slog.d(TAG, "close transition only has flexible target");
            z5 = false;
        }
        markMergeSecondTaskId(activeTransition, z5, i);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("ifMergeIntoRemote, normalTaskLevelSamePkgName hasSamePackage:", ", hasNoAnimFlag:", ", hasNoneFlagInOpenType:", z5, z2);
        sbM.append(z3);
        sbM.append(", hasTranslucentFlagInOpenType:");
        sbM.append(z4);
        Slog.d(TAG, sbM.toString());
        return z5 && (z2 || z3 || z4);
    }

    private void notifyRemoteContinuous(IBinder iBinder, IBinder iBinder2, WindowOrganizer windowOrganizer) {
        windowOrganizer.notifyRemoteInterrupt(iBinder, iBinder2);
    }

    private void notifyStartCompleted(TransitionInfo transitionInfo, boolean z, WindowOrganizer windowOrganizer) {
        if (TransitionUtil.isOpeningType(transitionInfo.getType())) {
            ActivityManager.RunningTaskInfo runningTaskInfo = null;
            for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (taskInfo != null) {
                    if (runningTaskInfo != null) {
                        break;
                    }
                    if (TransitionUtil.isOpeningType(change.getMode()) && taskInfo.isVisibleRequested && taskInfo.topActivityType != 2) {
                        runningTaskInfo = taskInfo;
                    }
                }
            }
            if (runningTaskInfo != null) {
                windowOrganizer.notifyStartCompleted(runningTaskInfo.token, z);
            }
        }
    }

    private void reparentAnimBackgroundToNextRoot(TransitionInfo transitionInfo, Transitions.ActiveTransition activeTransition, SurfaceControl.Transaction transaction, ArrayMap<Integer, Transformation> arrayMap) {
        if (activeTransition.mInfo == null || arrayMap == null || arrayMap.isEmpty()) {
            return;
        }
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, NEED_KEEP_BG_FOR_CONTINUOUS, null, new Object[0]);
        if (bool == null || !bool.booleanValue()) {
            TransitionLog.always("no need to keep bg for continuous");
            return;
        }
        Transformation transformation = arrayMap.get(2);
        List<SurfaceControl> list = this.mTransitionBgMap.get(Integer.valueOf(transitionInfo.getTrack()));
        SurfaceControl surfaceControl = (SurfaceControl) OplusTransitionReflectionHelper.methodInvokeOnExt(activeTransition.mInfo, GET_PARENT_LEASH_FOR_BG_COLOR, null, new Object[0]);
        if (transformation == null || surfaceControl == null || !surfaceControl.isValid() || list == null) {
            TransitionLog.always("abnormal scene, no need to reparent");
            return;
        }
        for (SurfaceControl surfaceControl2 : list) {
            transaction.setAlpha(surfaceControl2, transformation.getAlpha());
            transaction.reparent(surfaceControl2, surfaceControl);
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0031  */
    private boolean requestAbortFromWmCore(Transitions.ActiveTransition activeTransition, Transitions transitions) {
        TransitionInfo transitionInfo;
        boolean zIsActivityLevelOnly = false;
        if (activeTransition != null && (transitionInfo = activeTransition.mInfo) != null && transitions != null) {
            int iIntValue = ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_ABORT_REASON, null, new Object[0])).intValue();
            Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_MERGE_SECOND_TASK_ANIMATION, null, new Object[0]);
            if (iIntValue != 1) {
                if (iIntValue == 2) {
                    zIsActivityLevelOnly = isActivityLevelOnly(transitionInfo);
                } else if (iIntValue == 3 ? !(transitions.getPlayingRecents() != null || (!isWillFinishToHome(transitionInfo) && !activeTransition.mWillReorderHome)) : iIntValue == 4) {
                    zIsActivityLevelOnly = true;
                }
            } else if (bool == null || !bool.booleanValue()) {
                zIsActivityLevelOnly = true;
            }
            TransitionLog.always("Abort transition " + activeTransition + " WMCore requested. isMergeSecondTaskAnimation: " + bool + " reason: " + iIntValue + "; abortTransition: " + zIsActivityLevelOnly);
        }
        return zIsActivityLevelOnly;
    }

    private void resetPositionForRotationLeash(TransitionInfo transitionInfo, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        SurfaceControl leash = change.getLeash();
        if (TransitionInfo.isIndependent(change, transitionInfo) && leash != null && leash.isValid()) {
            boolean z = change.getParent() != null;
            int iRootIndexFor = TransitionUtil.rootIndexFor(change, transitionInfo);
            if (z) {
                return;
            }
            transaction.setPosition(leash, change.getStartAbsBounds().left - transitionInfo.getRoot(iRootIndexFor).getOffset().x, change.getStartAbsBounds().top - transitionInfo.getRoot(iRootIndexFor).getOffset().y);
            TransitionLog.debug("set position for " + change + ", when has fix rotation");
        }
    }

    private void setAlphaForOpenActivityIfNeeded(TransitionInfo transitionInfo, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        int mode = change.getMode();
        if ((mode == 1 || mode == 3) && TransitionUtil.isOpeningType(transitionInfo.getType()) && (change.getFlags() & 8) == 0 && change.getTaskInfo() == null) {
            TransitionLog.debug("set alpha 1 of activity:" + change);
            transaction.setAlpha(change.getLeash(), 1.0f);
        }
    }

    private void setPositionForFixRotation(TransitionInfo transitionInfo, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
        if (windowInfoBundleFromChange == null || !windowInfoBundleFromChange.getBoolean(KEY_HAS_FIX_ROTATION_LEASH, false)) {
            return;
        }
        resetPositionForRotationLeash(transitionInfo, change, transaction);
    }

    private void setPositionForFixedRotationLeashIfNeed(TransitionInfo transitionInfo, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        if (transaction == null || change.getActivityComponent() == null) {
            return;
        }
        boolean z = true;
        boolean z2 = transitionInfo.getType() == 2 || transitionInfo.getType() == 3;
        if (change.getMode() != 2 && change.getMode() != 3) {
            z = false;
        }
        if (z2 || z) {
            SurfaceControl leash = change.getLeash();
            if (change.getParent() == null && leash != null && leash.isValid() && shouldSetPositionForFixRotationOnAbort(transitionInfo, change)) {
                setPositionForFixRotation(transitionInfo, change, transaction);
            }
        }
    }

    private static void setRecentsController(TransitionInfo transitionInfo, RecentsTransitionHandler.RecentsController recentsController) {
        if (transitionInfo == null || recentsController == null) {
            return;
        }
        try {
            Field declaredField = sInfoExtField;
            if (declaredField == null) {
                declaredField = transitionInfo.getClass().getDeclaredField(EXT);
                declaredField.setAccessible(true);
                sInfoExtField = declaredField;
            }
            Object obj = declaredField.get(transitionInfo);
            if (obj != null) {
                Method declaredMethod = sGetExtendedInfoMethod;
                if (declaredMethod == null) {
                    Class[] clsArr = new Class[0];
                    declaredMethod = obj.getClass().getDeclaredMethod(GET_EXTENDED_INFO, null);
                    declaredMethod.setAccessible(true);
                    sGetExtendedInfoMethod = declaredMethod;
                }
                Object objInvoke = declaredMethod.invoke(obj, null);
                if (objInvoke != null) {
                    Method declaredMethod2 = sSetRecentsControllerMethod;
                    if (declaredMethod2 == null) {
                        declaredMethod2 = objInvoke.getClass().getDeclaredMethod(SET_RECENTS_CONTROLLER, IBinder.class);
                        declaredMethod2.setAccessible(true);
                        sSetRecentsControllerMethod = declaredMethod2;
                    }
                    declaredMethod2.invoke(objInvoke, recentsController);
                }
            }
        } catch (Exception e) {
            PeopleBackupFollowUpJob$.ExternalSyntheticOutline0.m("setRecentsController error : ", TAG, e);
        }
    }

    private void setRemoteToRecentContinuous(boolean z) {
        this.mRemoteToRecentContinuous = z;
        TransitionUtil.setRemoteToRecentContinuous(z);
    }

    private boolean shouldSetPositionForFixRotationOnAbort(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        if (change.getStartRotation() != change.getEndRotation() || TransitionUtil.isOpeningMode(change.getMode())) {
            return change.getStartRotation() == change.getEndRotation() || !TransitionUtil.isOpeningMode(change.getMode()) || DefaultTransitionHandler.isActivityLevelOnly(transitionInfo);
        }
        return false;
    }

    private boolean shouldSkipRemoteAnim(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        if (!TransitionAnimationUtil.isFoldDevice() && !TransitionAnimationUtil.isTabletDevice()) {
            return false;
        }
        List<TransitionInfo.Change> changes = transitionInfo.getChanges();
        if ((transitionInfo.getFlags() & TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) == 0 || changes.size() != 2) {
            return false;
        }
        for (TransitionInfo.Change change : changes) {
            if (change.getTaskInfo() != null) {
                return false;
            }
            if (change.getActivityComponent() != null && change.hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION)) {
                Slog.d(TAG, "shouldSkipRemoteAnim, change=" + change);
                return true;
            }
        }
        return false;
    }

    private boolean skipMergeConvertToTranslucent(TransitionInfo transitionInfo) {
        ComponentName componentName;
        if (transitionInfo == null || !((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_CONVERT_TO_TRANSLUCENT, null, new Object[0])).booleanValue() || transitionInfo.getChanges().size() != 1) {
            return false;
        }
        TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(0);
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (change.getMode() == 3 && taskInfo != null && (componentName = ((TaskInfo) taskInfo).topActivity) != null && ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(componentName.flattenToShortString())) {
            ArrayList playingTransitions = this.mTransitions.getPlayingTransitions();
            for (int i = 0; i < playingTransitions.size(); i++) {
                Transitions.ActiveTransition activeTransition = (Transitions.ActiveTransition) playingTransitions.get(i);
                if (activeTransition.mInfo != null) {
                    boolean z = false;
                    boolean z2 = false;
                    for (int i2 = 0; i2 < activeTransition.mInfo.getChanges().size(); i2++) {
                        TransitionInfo.Change change2 = (TransitionInfo.Change) activeTransition.mInfo.getChanges().get(i2);
                        ActivityManager.RunningTaskInfo taskInfo2 = change2.getTaskInfo();
                        if (taskInfo2 != null && ((TaskInfo) taskInfo2).topActivity != null) {
                            if (change2.getMode() == 4 && ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(((TaskInfo) taskInfo2).topActivity.flattenToShortString())) {
                                z = true;
                            }
                            if (change2.hasFlags(33554432) && change2.getMode() == 1 && "com.heytap.speechassist/.launcher.SpeechAssistMainActivity".equals(((TaskInfo) taskInfo2).topActivity.flattenToShortString())) {
                                z2 = true;
                            }
                            if (z && z2) {
                                TransitionLog.always("ShellContinuousTransitionController do not merge for translucent SpeechAssistMainActivity");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean skipMergeForArChange(TransitionInfo transitionInfo, RemoteTransition remoteTransition, Transitions.ActiveTransition activeTransition) {
        TransitionInfo transitionInfo2;
        if (transitionInfo != null && remoteTransition != null && activeTransition != null && (transitionInfo2 = activeTransition.mInfo) != null && transitionInfo2.getType() == 1 && transitionInfo.getType() == 1 && ShellContinuousTransitionUtils.SYSUI_DEBUG_NAME.equals(remoteTransition.getDebugName())) {
            boolean z = false;
            for (TransitionInfo.Change change : transitionInfo2.getChanges()) {
                if (change.getActivityComponent() == null) {
                    return false;
                }
                if (change.getMode() == 1 && change.getLeash() != null && ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change, "OplusSettingsHomepageActivity")) {
                    z = true;
                }
            }
            boolean z2 = false;
            for (TransitionInfo.Change change2 : transitionInfo.getChanges()) {
                if (change2.getActivityComponent() == null) {
                    return false;
                }
                if (change2.getMode() == 4 && change2.getLeash() != null && ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change2, "OplusSettingsHomepageActivity")) {
                    z2 = true;
                }
            }
            if (z2 && z) {
                return true;
            }
        }
        return false;
    }

    private boolean skipMergeForPip(IBinder iBinder, TransitionInfo transitionInfo) {
        Transitions.ActiveTransition activeTransition;
        TransitionInfo transitionInfo2;
        Transitions.ActiveTransition activeTransition2 = this.mTransitions.getActiveTransition(iBinder);
        if (activeTransition2 != null && (activeTransition2.mHandler instanceof PipTransition)) {
            TransitionLog.always("ShellContinuousTransitionControllertransition " + transitionInfo.getDebugId() + " handler is PipTransition, do not merge into remote.");
            return true;
        }
        if (transitionInfo.getType() == 1001 && (activeTransition = this.mTransitions.getActiveTransition(transitionInfo.getTrack())) != null && (transitionInfo2 = activeTransition.mInfo) != null) {
            boolean z = isRemoteFrom(activeTransition.mToken, transitionInfo2, "QuickstepLaunch") || isRemoteFrom(activeTransition.mToken, activeTransition.mInfo, "QuickstepLaunchHome");
            TransitionAnimationUtil.hasLastParentTaskIdBeforePip(activeTransition.mInfo, transitionInfo);
            if (z) {
                TransitionLog.always("ShellContinuousTransitionControllertransition " + transitionInfo.getDebugId() + " playing is Launcher remote and contains same Pip task which is exiting");
                return true;
            }
        }
        return false;
    }

    private boolean skipMergeGoogleHomeInControlsActivity(Transitions.ActiveTransition activeTransition, TransitionInfo transitionInfo) {
        ComponentName componentName;
        ComponentName componentName2;
        if (transitionInfo != null && transitionInfo.getType() == 1) {
            for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (change.hasFlags(33554432) && change.getMode() == 1 && taskInfo != null && (componentName = ((TaskInfo) taskInfo).topActivity) != null && "com.google.android.apps.chromecast.app/.systemcontrol.panel.GoogleHomePanelActivity".equals(componentName.flattenToShortString())) {
                    StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(((TaskInfo) taskInfo).taskId, "ShellContinuousTransitionController ", " transition ");
                    sbM.append(transitionInfo.getDebugId());
                    sbM.append(" ");
                    sbM.append(((TaskInfo) taskInfo).topActivity);
                    sbM.append(" flattenToShortString:");
                    TransitionLog.always(sbM.toString());
                    ArrayList playingTransitions = this.mTransitions.getPlayingTransitions();
                    boolean z = false;
                    for (int i2 = 0; i2 < playingTransitions.size(); i2++) {
                        Transitions.ActiveTransition activeTransition2 = (Transitions.ActiveTransition) playingTransitions.get(i2);
                        if (activeTransition2.mInfo != null) {
                            for (int i3 = 0; i3 < activeTransition2.mInfo.getChanges().size(); i3++) {
                                TransitionInfo.Change change2 = (TransitionInfo.Change) activeTransition2.mInfo.getChanges().get(i3);
                                ActivityManager.RunningTaskInfo taskInfo2 = change2.getTaskInfo();
                                if (change2.hasFlags(33554432) && change2.getMode() == 3 && taskInfo2 != null && (componentName2 = ((TaskInfo) taskInfo2).topActivity) != null && "com.android.systemui/.controls.ui.ControlsActivity".equals(componentName2.flattenToShortString())) {
                                    StringBuilder sbM2 = ListPopupWindow$.ExternalSyntheticOutline0.m(((TaskInfo) taskInfo2).taskId, "ShellContinuousTransitionController ", " transition ");
                                    sbM2.append(transitionInfo.getDebugId());
                                    sbM2.append(" ");
                                    sbM2.append(((TaskInfo) taskInfo2).topActivity);
                                    sbM2.append(" flattenToShortString:");
                                    TransitionLog.always(sbM2.toString());
                                    z = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!z) {
                        break;
                    }
                    TransitionLog.always("ShellContinuousTransitionControllerskipMergeGoogleHomeInControlsActivity transition " + transitionInfo.getDebugId() + " no need to merge");
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    private boolean skipMergeIntoRecentsForCanvas(TransitionInfo transitionInfo, RecentsTransitionHandler.RecentsController recentsController) {
        boolean z;
        TransitionInfo playingTransitionInfo = recentsController == null ? null : recentsController.getPlayingTransitionInfo();
        boolean z2 = false;
        if (recentsController == null || transitionInfo == null || playingTransitionInfo == null) {
            return false;
        }
        Transitions.ActiveTransition activeTransition = this.mTransitions.getActiveTransition(playingTransitionInfo.getTrack());
        if (activeTransition == null) {
            return false;
        }
        int i = 0;
        boolean z3 = false;
        boolean z4 = false;
        List listFlexibleWindowManager_getEmbeddedChildren = null;
        int i2 = -1;
        while (i < transitionInfo.getChanges().size()) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
            if (taskInfo == null) {
                z = z2;
            } else {
                if (isCanvasTask(taskInfo)) {
                    z = z2;
                    if (change.getMode() == 3) {
                        i2 = taskInfo.taskId;
                        listFlexibleWindowManager_getEmbeddedChildren = ReflectionHelper.FlexibleWindowManager_getEmbeddedChildren(i2);
                        z3 = true;
                    }
                } else {
                    z = z2;
                }
                if (taskInfo.getActivityType() == 2 && change.getMode() == 4) {
                    z4 = true;
                }
            }
            i++;
            z2 = z;
        }
        boolean z5 = z2;
        boolean z6 = z5;
        boolean z7 = z6;
        boolean z8 = z7 ? 1 : 0;
        List listFlexibleWindowManager_getEmbeddedChildren2 = null;
        int i3 = -1;
        ?? r1 = z6;
        boolean z9 = z7;
        while (r1 < playingTransitionInfo.getChanges().size()) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) playingTransitionInfo.getChanges().get(r1);
            ActivityManager.RunningTaskInfo taskInfo2 = change2.getTaskInfo();
            if (taskInfo2 != null) {
                if (isCanvasTask(taskInfo2) && change2.getMode() == 4) {
                    z9 = z9;
                    z9 = z9;
                    i3 = taskInfo2.taskId;
                    listFlexibleWindowManager_getEmbeddedChildren2 = ReflectionHelper.FlexibleWindowManager_getEmbeddedChildren(i3);
                    z9 = true;
                }
                z9 = z9;
                z9 = z9;
                z9 = z9;
                if (taskInfo2.getActivityType() == 2 && change2.getMode() == 3) {
                    z8 = true;
                }
            }
            r1++;
            z9 = z9;
            z8 = z8;
        }
        if (listFlexibleWindowManager_getEmbeddedChildren == null || listFlexibleWindowManager_getEmbeddedChildren.isEmpty() || listFlexibleWindowManager_getEmbeddedChildren2 == null || listFlexibleWindowManager_getEmbeddedChildren2.isEmpty() || i2 == i3 || i2 == -1 || i3 == -1) {
            return z5;
        }
        boolean z10 = (z3 && z4 && z9 && z8) ? true : z5;
        boolean zRetainAll = listFlexibleWindowManager_getEmbeddedChildren.retainAll(listFlexibleWindowManager_getEmbeddedChildren2);
        if (!z10 || !zRetainAll) {
            return z5;
        }
        ArrayList arrayList = activeTransition.mMerged;
        if (arrayList != null && !arrayList.isEmpty()) {
            Transitions.ActiveTransition activeTransition2 = (Transitions.ActiveTransition) activeTransition.mMerged.getLast();
            TransitionInfo transitionInfo2 = activeTransition2 != null ? activeTransition2.mInfo : null;
            if (transitionInfo2 != null) {
                for (TransitionInfo.Change change3 : transitionInfo2.getChanges()) {
                    if (change3.getTaskInfo() != null && change3.getTaskInfo().getActivityType() == 2 && change3.getLeash() != null && change3.getLeash().isValid()) {
                        activeTransition2.mFinishT.show(change3.getLeash());
                    }
                }
            }
        }
        return true;
    }

    private boolean skipMergeIntoRecentsForEmbedded(TransitionInfo transitionInfo) {
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (transitionInfo.getType() == 1 && change.getMode() == 1 && change.hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) && !change.hasFlags(OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE)) {
                z = true;
            } else if (change.getTaskInfo() != null && change.getMode() == 6 && change.getStartRotation() != change.getEndRotation()) {
                z2 = true;
            }
            if (z && z2) {
                return true;
            }
        }
        return false;
    }

    private boolean skipMergeIntoRecentsForEmbeddedInTable(TransitionInfo transitionInfo) {
        if (!SplitToggleHelper.getInstance().mIsTabletDevice) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if ((TransitionUtil.isOpeningMode(transitionInfo.getType()) && TransitionUtil.isOpeningMode(change.getMode()) && change.hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) && !change.hasFlags(OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE)) || (TransitionUtil.isOpeningMode(transitionInfo.getType()) && TransitionUtil.isOpeningMode(change.getMode()) && OplusEmbedReflectionUtils.getWindowModeFromChange(change) == WINDOWING_MODE_COMPACTWINDOW)) {
                z = true;
            } else if (change.getTaskInfo() != null && change.getStartRotation() != change.getEndRotation()) {
                z2 = true;
            } else if (change.hasFlags(32) && change.getMode() == 6) {
                z3 = true;
            }
        }
        return z && z2 && z3;
    }

    private boolean skipMergeIntoRecentsForStkStart(TransitionInfo transitionInfo) {
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_OPENING_STK_DIALOG_ACTIVITY, null, new Object[0]);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private boolean supportRecentTransition(TransitionInfo transitionInfo) {
        int i;
        boolean zIsAllFlexibleTasksTransition = OplusPanoramaWorkBranchAnimController.isAllFlexibleTasksTransition(transitionInfo);
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < transitionInfo.getChanges().size(); i2++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i2);
            if (!TransitionUtil.isWallpaper(change)) {
                if (TransitionUtil.isClosingType(change.getMode())) {
                    z2 = true;
                } else {
                    ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                    if (taskInfo != null && ((i = taskInfo.topActivityType) == 3 || i == 2)) {
                        z = taskInfo.token != null;
                    }
                }
            }
        }
        return z || z2 || zIsAllFlexibleTasksTransition;
    }

    private boolean translucentActivityLevelTransition(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        String packageName = null;
        String packageName2 = null;
        String packageNameFromTaskInfo = null;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() == null) {
                if (change.getMode() == 1 && change.getActivityComponent() != null) {
                    packageName = change.getActivityComponent().getPackageName();
                }
                if (change.getMode() == 4 && change.hasFlags(4) && change.getActivityComponent() != null) {
                    packageName2 = change.getActivityComponent().getPackageName();
                }
            } else if (change.getMode() == 4) {
                packageNameFromTaskInfo = TransitionAnimationUtil.getPackageNameFromTaskInfo(change.getTaskInfo());
            }
        }
        return packageName != null && packageName.equals(packageName2) && packageName.equals(packageNameFromTaskInfo);
    }

    private boolean translucentSameTaskLevelTransition(Transitions.ActiveTransition activeTransition, TransitionInfo transitionInfo) {
        boolean z = false;
        if (transitionInfo == null) {
            return false;
        }
        String str = null;
        int i = -1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() == null) {
                return false;
            }
            String packageNameFromTaskInfo = TransitionAnimationUtil.getPackageNameFromTaskInfo(change.getTaskInfo());
            if (str == null) {
                str = packageNameFromTaskInfo;
            } else if (str.equals(packageNameFromTaskInfo)) {
                z2 = true;
            }
            if (change.hasFlags(4) && change.getMode() == 2) {
                z3 = true;
            }
            if ("com.android.launcher".equals(packageNameFromTaskInfo) && change.getMode() == 4) {
                z5 = true;
            }
            if (transitionInfo.getType() == 2 && change.getMode() == 1 && change.hasFlags(TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE)) {
                z4 = true;
            }
            if (TransitionUtil.isOpeningType(change.getMode()) && !"com.android.launcher".equals(packageNameFromTaskInfo)) {
                i = change.getTaskInfo().taskId;
            }
        }
        if (z2 && z3 && z4 && z5) {
            z = true;
        }
        if (z) {
            markMergeSecondTaskId(activeTransition, true, i);
        }
        return z;
    }

    private void updateContinueLeashState(Transformation transformation, SurfaceControl surfaceControl, SurfaceControl surfaceControl2, float f, SurfaceControl.Transaction transaction, Rect rect) {
        transaction.setMatrix(surfaceControl, transformation.getMatrix(), new float[9]);
        transaction.setAlpha(surfaceControl, transformation.getAlpha());
        if (f > 0.0f) {
            OplusTransitionReflectionHelper.setCornerRadiusExt(transaction, surfaceControl, f, 2.5f, 1);
            transaction.setCrop(surfaceControl, rect);
        }
        transaction.reparent(surfaceControl, surfaceControl2);
        transaction.show(surfaceControl);
        transaction.show(surfaceControl2);
    }

    public boolean abortTransitionIfNeeded(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2, Transitions transitions) {
        return abortRelaunch(activeTransition, activeTransition2) || abortTranslucentEmbed(activeTransition) || requestAbortFromWmCore(activeTransition, transitions) || abortOpenTransitForReorderHome(activeTransition) || abortToFrontTransitionForReorderHome(activeTransition) || abortForGlobalPageSearchSwitch(activeTransition, activeTransition2) || abortForNoWindowActivitySwitch(activeTransition, activeTransition2);
    }

    public void addBackgroundColorOnTask(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_OPLUS_ANIM_RES, null, new Object[0])).booleanValue();
            boolean z = transitionInfo.getType() == 1 || transitionInfo.getType() == 2;
            Color colorValueOf = Color.valueOf(-16777216);
            float[] fArr = {colorValueOf.red(), colorValueOf.green(), colorValueOf.blue()};
            SurfaceControl surfaceControl = (SurfaceControl) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_PARENT_LEASH_FOR_BG_COLOR, null, new Object[0]);
            boolean zHasBackgroundAlready = hasBackgroundAlready(transitionInfo.getTrack());
            boolean zIsTaskLevelTransition = TransitionAnimationUtil.isTaskLevelTransition(transitionInfo);
            TransitionLog.always("addBackgroundColorOnTask, , isTaskLevelTransition = " + zIsTaskLevelTransition + ", hasBgAlready = " + zHasBackgroundAlready);
            if (zHasBackgroundAlready && zIsTaskLevelTransition) {
                TransitionLog.always("adjust bg layer for continuous on the second transition");
                adjustBgLayerIfNeed(transitionInfo, transaction, true, "forContinuous");
            }
            if (surfaceControl == null || !z || zHasBackgroundAlready) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < transitionInfo.getRootCount(); i++) {
                SurfaceControl.Builder colorLayer = new SurfaceControl.Builder().setName("oplus-slide-anim-background").setCallsite("DefaultTransitionHandler").setColorLayer();
                colorLayer.setParent(surfaceControl);
                TransitionLog.always("addBackgroundColorOnTask bgColor=" + colorValueOf + ", parentSC=" + surfaceControl + ", isOplusAnimRes=" + zBooleanValue + ", info=" + transitionInfo + ", colorLayerBuilder=" + colorLayer);
                SurfaceControl surfaceControlBuild = colorLayer.build();
                transaction.setColor(surfaceControlBuild, fArr).setLayer(surfaceControlBuild, Integer.MIN_VALUE).show(surfaceControlBuild);
                arrayList.add(surfaceControlBuild);
            }
            cacheBgSurface(transitionInfo, transaction2, arrayList);
            adjustBgLayerIfNeed(transitionInfo, transaction, zIsTaskLevelTransition, "forInitialize");
        }
    }

    public void adjustBackAnimChangeLeashStateIfNeed(RemoteAnimationTarget remoteAnimationTarget, SurfaceControl surfaceControl, IBinder iBinder, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        Transformation transformation;
        TransitionLog.always("adjustBackAnimChangeLeashStateIfNeed, transition=" + iBinder + ", change=" + change);
        if (SpringSlideAnimationController.ENABLE_SPRING_SLIDE_ANIM) {
            SpringSlideAnimationController.ContinueValue matchedContinueValue = AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController().getMatchedContinueValue(iBinder, change, true);
            if (matchedContinueValue != null) {
                transaction.setPosition(surfaceControl, matchedContinueValue.posX, 0.0f).setAlpha(surfaceControl, matchedContinueValue.alpha);
                float f = matchedContinueValue.cornerRadius;
                if (f > 0.0f) {
                    transaction.setCornerRadius(surfaceControl, f).setCrop(surfaceControl, change.getEndAbsBounds());
                }
                if (remoteAnimationTarget != null) {
                    OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget, SET_BACK_ANIM_CONTINUE_LEASH, new Class[]{SurfaceControl.class}, change.getLeash());
                    return;
                }
                return;
            }
            return;
        }
        ArrayMap backAnimContinueTransformMap = Transitions.getBackAnimContinueTransformMap(iBinder);
        if (backAnimContinueTransformMap == null) {
            return;
        }
        if (change.getMode() == 6) {
            transformation = (Transformation) backAnimContinueTransformMap.get(1);
        } else {
            transformation = TransitionUtil.isOpeningMode(change.getMode()) ? (Transformation) backAnimContinueTransformMap.get(0) : null;
        }
        if (transformation != null) {
            float windowCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(ActivityThread.currentActivityThread().getSystemUiContext());
            transaction.setMatrix(surfaceControl, transformation.getMatrix(), new float[9]);
            transaction.setAlpha(surfaceControl, transformation.getAlpha());
            if (!TransitionUtil.isOpeningMode(change.getMode())) {
                transaction.setCornerRadius(surfaceControl, windowCornerRadius);
            }
            transaction.setCrop(surfaceControl, change.getEndAbsBounds());
            if (remoteAnimationTarget != null) {
                OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget, SET_BACK_ANIM_CONTINUE_LEASH, new Class[]{SurfaceControl.class}, change.getLeash());
            }
        }
    }

    public SurfaceControl.Transaction adjustFinishTAndAnimState(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, Transitions.ActiveTransition activeTransition, WindowContainerTransaction windowContainerTransaction) {
        TransitionInfo transitionInfo2 = activeTransition != null ? activeTransition.mInfo : null;
        SparseArray sparseArrayTempRemoveFlexibleRotationChangesIfNeed = OplusFlexibleTaskRotationController.getInstance().tempRemoveFlexibleRotationChangesIfNeed(transitionInfo2);
        try {
            boolean zMatchContinuousScene = matchContinuousScene(transitionInfo, transaction, transitionInfo2, windowContainerTransaction);
            SurfaceControl.Transaction transactionFixFinishTransaction = fixFinishTransaction(transitionInfo, transaction, activeTransition, windowContainerTransaction, zMatchContinuousScene);
            if (zMatchContinuousScene) {
                passAnimStateToNextTransition(transitionInfo, transitionInfo2, windowContainerTransaction);
            }
            return transactionFixFinishTransaction != null ? transactionFixFinishTransaction : transaction;
        } finally {
            OplusFlexibleTaskRotationController.getInstance().restoreFlexibleRotationChangesForInfo(transitionInfo2, sparseArrayTempRemoveFlexibleRotationChangesIfNeed);
        }
    }

    public SurfaceControl.Transaction adjustFinishTransactionForPredictive(IBinder iBinder, int i, SurfaceControl.Transaction transaction) {
        Transitions.ActiveTransition activeTransition;
        ArrayList arrayList;
        Transitions.ActiveTransition activeTransition2;
        SurfaceControl.Transaction transaction2;
        if (transaction == null || transaction.mNativeObject == 0 || (activeTransition = this.mTransitions.getActiveTransition(i)) == null || activeTransition.mToken != iBinder || (arrayList = activeTransition.mMerged) == null || arrayList.isEmpty() || (activeTransition2 = (Transitions.ActiveTransition) activeTransition.mMerged.getLast()) == null || activeTransition2.mInfo.getType() != 13 || (transaction2 = activeTransition2.mFinishT) == null || transaction2.mNativeObject == 0) {
            return transaction;
        }
        Log.d(TAG, "adjustFinishTransactionForPredictive " + activeTransition2.mFinishT.getId() + " finishTransaction " + transaction.getId());
        return activeTransition2.mFinishT;
    }

    public SurfaceControl adjustLeashAtRecentStart(TransitionInfo.Change change, SurfaceControl.Transaction transaction, ArrayMap<SurfaceControl, SurfaceControl> arrayMap) {
        SurfaceControl leash = change.getLeash();
        transaction.setAlpha(change.getLeash(), 1.0f);
        transaction.show(change.getLeash());
        if (arrayMap != null) {
            arrayMap.put(change.getLeash(), leash);
        }
        return leash;
    }

    public void applyDefaultContinueTransformation(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, ValueAnimator valueAnimator, Animation animation, Bundle bundle) {
        Transformation transformation;
        Transformation transformation2;
        if (bundle != null) {
            IBinder binder = bundle.getBinder("transitionToken");
            ArrayMap backAnimContinueTransformMap = Transitions.getBackAnimContinueTransformMap(binder);
            int modeFromAnimator = getModeFromAnimator(valueAnimator);
            if (binder == null || modeFromAnimator == -1 || backAnimContinueTransformMap == null) {
                return;
            }
            if (TransitionUtil.isClosingMode(modeFromAnimator)) {
                transformation = (Transformation) backAnimContinueTransformMap.get(1);
            } else {
                transformation = TransitionUtil.isOpeningMode(modeFromAnimator) ? (Transformation) backAnimContinueTransformMap.get(0) : null;
            }
            if (transformation != null) {
                float[] fArr = new float[9];
                transformation.getMatrix().getValues(fArr);
                float f = fArr[2];
                float f2 = fArr[5];
                float alpha = transformation.getAlpha();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                float[] fArr2 = new float[9];
                Transformation transformation3 = new Transformation();
                animation.getTransformation(animation.getDuration(), transformation3);
                transformation3.getMatrix().getValues(fArr2);
                float alpha2 = transformation3.getAlpha();
                float f3 = fArr2[2];
                float f4 = fArr2[5];
                Transformation transformation4 = new Transformation();
                animation.getTransformation(0L, transformation4);
                transformation4.getMatrix().getValues(new float[9]);
                transformation4.getAlpha();
                transaction.setPosition(surfaceControl, ((f3 - f) * animatedFraction) + f, DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f4, f2, animatedFraction, f2));
                transaction.setAlpha(surfaceControl, ((alpha2 - alpha) * animatedFraction) + alpha);
            }
            if (modeFromAnimator != 1000 || (transformation2 = (Transformation) backAnimContinueTransformMap.get(2)) == null) {
                return;
            }
            float alpha3 = transformation2.getAlpha();
            float animatedFraction2 = valueAnimator.getAnimatedFraction();
            Transformation transformation5 = new Transformation();
            animation.getTransformation(animation.getDuration(), transformation5);
            transaction.setAlpha(surfaceControl, ((transformation5.getAlpha() - alpha3) * animatedFraction2) + alpha3);
        }
    }

    public void applyStartTransactionOnAbortIfNeed(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2) {
        TransitionInfo transitionInfo;
        TransitionInfo transitionInfo2;
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            OplusActivityEmbeddingAnimationRunner.getInstance().settingsArAnimApplyStartTWhenAbortIfNeed(activeTransition, activeTransition2);
            return;
        }
        Transitions.TransitionHandler transitionHandler = activeTransition.mHandler;
        if (transitionHandler != null && !(transitionHandler instanceof DefaultTransitionHandler) && activeTransition2.mStartT != null && (transitionInfo2 = activeTransition2.mInfo) != null) {
            if (transitionInfo2.getChanges().isEmpty() && activeTransition2.mInfo.getType() == 2) {
                return;
            }
            for (TransitionInfo.Change change : activeTransition2.mInfo.getChanges()) {
                if (change.getMode() == 2 || change.getMode() == 4) {
                    OplusActivityEmbeddingAnimationRunner.getInstance().hideTaskFragmentSurfaceOnAbortIfNeed(activeTransition2.mStartT, activeTransition2.mInfo);
                    break;
                }
            }
            Iterator it = activeTransition2.mInfo.getChanges().iterator();
            while (it.hasNext()) {
                setPositionForFixedRotationLeashIfNeed(activeTransition2.mInfo, (TransitionInfo.Change) it.next(), activeTransition2.mStartT);
            }
            if (activeTransition2.mInfo.getType() == 1) {
                for (TransitionInfo.Change change2 : activeTransition2.mInfo.getChanges()) {
                    handleLeashBeforeStartTApply(change2, activeTransition2, activeTransition);
                    if (change2.getActivityComponent() != null) {
                        Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change2);
                        boolean z = false;
                        boolean z2 = windowInfoBundleFromChange != null && windowInfoBundleFromChange.getBoolean(KEY_HAS_FIX_ROTATION_LEASH, false);
                        if (change2.getStartRotation() == -1 && change2.getStartRotation() != change2.getEndRotation()) {
                            z = true;
                        }
                        if (change2.hasAllFlags(com.oplus.wrapper.window.TransitionInfo.FLAG_IS_BEHIND_STARTING_WINDOW) && z2 && z) {
                            TransitionLog.always("reset rotation-leash position when abort");
                            resetPositionForRotationLeash(activeTransition2.mInfo, change2, activeTransition2.mStartT);
                        }
                    }
                }
            }
            activeTransition2.mStartT.apply();
            TransitionLog.always("apply startT of aborted " + activeTransition2 + ";  before merged into " + activeTransition);
        }
        if (applyStartTOnAbortForEmbeddedIfNeed(activeTransition, activeTransition2)) {
            activeTransition2.mStartT.apply();
            StringBuilder sb = new StringBuilder("apply startT ");
            sb.append(activeTransition2);
            sb.append(" that was aborted due to no transition roots;  before merged into ");
            sb.append(activeTransition);
            ExifInterface$.ExternalSyntheticOutline1.m(sb, " containing canvas to front", TAG);
        }
        TransitionInfo transitionInfo3 = activeTransition2.mInfo;
        if (transitionInfo3 != null && transitionInfo3.getType() == 4 && activeTransition2.mInfo.getChanges().isEmpty() && (transitionInfo = activeTransition.mInfo) != null && transitionInfo.getType() == 13) {
            BackAnimationController.BackTransitionHandler backTransitionHandler = activeTransition.mHandler;
            if ((backTransitionHandler instanceof BackAnimationController.BackTransitionHandler) && backTransitionHandler.getOpenTransitionInfo() != null && activeTransition.mMerged == null && activeTransition.mHandler.getOnAnimationFinishCallback() == null) {
                Log.d(TAG, "finish predictive back transition");
                activeTransition.mHandler.applyFinishOpenTransition();
            }
        }
    }

    public boolean avoidReturningToAppIfNeed(int i) {
        if (i < 0) {
            return false;
        }
        Transitions.ActiveTransition activeTransitionNextReadyTransition = this.mTransitions.nextReadyTransition(i);
        TransitionInfo transitionInfo = activeTransitionNextReadyTransition != null ? activeTransitionNextReadyTransition.mInfo : null;
        if (transitionInfo != null && TransitionUtil.isOpeningMode(transitionInfo.getType())) {
            if (this.mTransitions.isRemoteOpenRequestedFrom(activeTransitionNextReadyTransition.mToken, SPRUCE_DEBUG_NAME)) {
                Slog.d(TAG, "skipReturningToApp, next remote is open from sprucer");
                return true;
            }
            if (OplusAnimationController.isStartFromSysUIPlugin(transitionInfo)) {
                Slog.d(TAG, "skipReturningToApp, next is open from SysUI plugin");
                return true;
            }
        }
        return false;
    }

    public void buildAlphaAnimationForBackground(TransitionInfo transitionInfo, IBinder iBinder, ArrayList<WindowAnimation> arrayList, Consumer<WindowAnimation> consumer, TransactionPool transactionPool, ShellExecutor shellExecutor) {
    }

    public void cacheBgSurface(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, List<SurfaceControl> list) {
        if (transitionInfo == null || transaction == null || list == null || list.isEmpty()) {
            return;
        }
        this.mTransitionBgMap.put(Integer.valueOf(transitionInfo.getTrack()), list);
    }

    public boolean cancelStartRecentsTransition(RecentsTransitionHandler.RecentsController recentsController, Transitions transitions) {
        if (recentsController == null) {
            return false;
        }
        Transitions.ActiveTransition activeTransitionNextReadyTransition = transitions.nextReadyTransition(0);
        if (activeTransitionNextReadyTransition != null && activeTransitionNextReadyTransition.mInfo.getDebugId() == this.mRemoteMergeToRemote) {
            TransitionLog.always("merge interrupt : " + this.mRemoteMergeToRemote + "; don't cancel recents");
            return false;
        }
        Transitions.ActiveTransition activeTransition = transitions.getActiveTransition(0);
        if (activeTransition != null && (activeTransition.mHandler instanceof RemoteTransitionHandler)) {
            for (TransitionInfo.Change change : activeTransition.mInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (taskInfo != null && taskInfo.topActivityType == 2 && TransitionUtil.isOpeningType(change.getMode())) {
                    recentsController.cancel("startRecentsTransition");
                    TransitionLog.always("remote transiton of start launcher is runing, don't start recents transiton");
                    return true;
                }
            }
        }
        return false;
    }

    public void clearRemoteTransitonState() {
        this.mRemoteTransitionStateMap.clear();
    }

    public void constructCloseRemoteAnimationTarget(ArrayList<RemoteAnimationTarget> arrayList, ArrayList<TransitionInfo.Change> arrayList2) {
        if (arrayList2 == null || arrayList2.size() <= 0 || arrayList == null) {
            return;
        }
        TransitionInfo.Change change = (TransitionInfo.Change) OpaqueKey$.ExternalSyntheticOutline0.m(arrayList2, 1);
        for (int size = arrayList2.size() - 1; size >= 0; size += -1) {
            TransitionInfo.Change change2 = arrayList2.get(size);
            ActivityManager.RunningTaskInfo taskInfo = change2.getTaskInfo();
            if (!FlexibleUtils.isFlexibleTaskAndHasCaption(taskInfo) && !FlexibleUtils.isFlexibleEmbedded(taskInfo)) {
                change = change2;
                break;
            }
            TransitionLog.always("constructCloseRemoteAnimationTarget c: " + change2);
        }
        ActivityManager.RunningTaskInfo taskInfo2 = change.getTaskInfo();
        RemoteAnimationTarget remoteAnimationTarget = new RemoteAnimationTarget(taskInfo2.taskId, -1, (SurfaceControl) null, false, (Rect) null, (Rect) null, -1, (Point) null, (Rect) null, (Rect) null, taskInfo2.configuration.windowConfiguration, false, (SurfaceControl) null, (Rect) null, taskInfo2, false);
        arrayList.add(remoteAnimationTarget);
        OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget, SET_MERGE_BACK_ANIMATION_TO_RECENTS, new Class[]{Boolean.TYPE}, Boolean.TRUE);
        TransitionLog.debug("setMergeBackAnimationToRecents true");
    }

    public void continueRecents(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2, RecentsTransitionHandler recentsTransitionHandler, WindowOrganizer windowOrganizer) {
        if (recentsTransitionHandler == null) {
            return;
        }
        RecentsTransitionHandler.RecentsController recentController = recentsTransitionHandler.getRecentController(activeTransition2.mToken);
        TransitionLog.always("continueRecent track.mActiveTransition: " + activeTransition2 + ", controller: " + recentController);
        if (recentController != null) {
            ArrayList taskIdFromTargets = TransitionAnimationUtil.getTaskIdFromTargets(activeTransition2.mInfo);
            recentController.setTmpRemote(activeTransition);
            TransitionInfo transitionInfo = recentController.getTmpRemote().mInfo;
            Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(activeTransition2.mInfo, IS_START_AHEAD_SPRUCE, null, new Object[0]);
            boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
            Boolean bool2 = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(activeTransition2.mInfo, IS_PREEMPTED_BY_RECENTS, null, new Object[0]);
            boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : false;
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if (change != null && change.getLeash() != null && change.getLeash().isValid() && (change.getMode() == 1 || change.getMode() == 3)) {
                    if (!ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change, "TaskFragment")) {
                        recentController.getTmpRemote().mFinishT.hide(change.getLeash());
                        Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
                        boolean z = ((windowInfoBundleFromChange != null ? windowInfoBundleFromChange.getBoolean(KEY_LAUNCH_FROM_SPRUCE, false) : false) && zBooleanValue) || (change.getTaskInfo() != null && taskIdFromTargets.contains(Integer.valueOf(change.getTaskInfo().taskId))) || zBooleanValue2;
                        if (change.getTaskInfo() != null && change.getTaskInfo().topActivityType != 2 && !z && !recentController.getTmpRemote().mIsNormalTaskMergeIntoRemote) {
                            activeTransition2.mStartT.hide(change.getLeash());
                            TransitionLog.always("The change: " + change + " of remote transiton don't participate recents, so hide task surface");
                        }
                    }
                }
            }
        }
        setRemoteToRecentContinuous(true);
        notifyRemoteContinuous(activeTransition.mToken, activeTransition2.mToken, windowOrganizer);
    }

    public void finishTmpRemoteTransition(Transitions.ActiveTransition activeTransition, SurfaceControl.Transaction transaction, RecentsTransitionHandler recentsTransitionHandler, WindowContainerTransaction windowContainerTransaction, ArrayList<Transitions.TransitionObserver> arrayList, ShellTaskOrganizer shellTaskOrganizer) {
        Transitions.ActiveTransition tmpRemote;
        ArrayList arrayList2;
        if (recentsTransitionHandler == null || recentsTransitionHandler.getRecentController(activeTransition.mToken) == null || (tmpRemote = recentsTransitionHandler.getRecentController(activeTransition.mToken).getTmpRemote()) == null) {
            return;
        }
        boolean zIsRecentFinishToHome = SharedReflectionHelper.isRecentFinishToHome(windowContainerTransaction);
        long recentFinishSeq = SharedReflectionHelper.getRecentFinishSeq(windowContainerTransaction);
        boolean recentFinishInputConsumerEnabled = SharedReflectionHelper.getRecentFinishInputConsumerEnabled(windowContainerTransaction);
        TransitionLog.always("finishTmpRemoteTransition " + tmpRemote + ", wct=" + windowContainerTransaction + ", isRecentToHome=" + zIsRecentFinishToHome + ", seqId=" + recentFinishSeq);
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).onTransitionFinished(tmpRemote.mToken, tmpRemote.mAborted);
        }
        if (tmpRemote.mMerged != null) {
            for (int i3 = 0; i3 < tmpRemote.mMerged.size(); i3++) {
                Transitions.ActiveTransition activeTransition2 = (Transitions.ActiveTransition) tmpRemote.mMerged.get(i3);
                SurfaceControl.Transaction transaction2 = activeTransition2.mStartT;
                if (transaction2 != null) {
                    transaction.merge(transaction2);
                }
                SurfaceControl.Transaction transaction3 = activeTransition2.mFinishT;
                if (transaction3 != null) {
                    transaction.merge(transaction3);
                }
            }
        }
        WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
        recordRecentFinishState(windowContainerTransaction2, zIsRecentFinishToHome, recentFinishSeq, recentFinishInputConsumerEnabled);
        tmpRemote.mInfo.releaseAnimSurfaces();
        AdaptiveSmoothShellAnimManager.getInstance().releaseSurfaces(tmpRemote.mInfo);
        OplusUIFirstManager.getInstance().setBinderThreadUxFlag(-1, 1);
        shellTaskOrganizer.finishTransition(tmpRemote.mToken, windowContainerTransaction2);
        if (tmpRemote.mMerged != null) {
            while (true) {
                int size = tmpRemote.mMerged.size();
                arrayList2 = tmpRemote.mMerged;
                if (i >= size) {
                    break;
                }
                Transitions.ActiveTransition activeTransition3 = (Transitions.ActiveTransition) arrayList2.get(i);
                shellTaskOrganizer.finishTransition(activeTransition3.mToken, windowContainerTransaction2);
                activeTransition3.mInfo.releaseAnimSurfaces();
                AdaptiveSmoothShellAnimManager.getInstance().releaseSurfaces(activeTransition3.mInfo);
                i++;
            }
            arrayList2.clear();
        }
        recentsTransitionHandler.getRecentController(activeTransition.mToken).setTmpRemote((Transitions.ActiveTransition) null);
    }

    public void fixAlphaInRecentStart(TransitionInfo.Change change, ArrayMap<SurfaceControl, SurfaceControl> arrayMap, SurfaceControl.Transaction transaction) {
        if (ShellContinuousTransitionUtils.ENABLE_CONTINUOUS_REMOTE && isRemoteToRemoteContinuous() && change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) {
            if (change.getMode() == 1 || change.getMode() == 3) {
                transaction.setAlpha(arrayMap.get(change.getLeash()), 1.0f);
                Log.d(TAG, arrayMap.get(change.getLeash()) + " setAlpha 1");
            }
        }
    }

    public SurfaceControl.Transaction fixFinishTransaction(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, Transitions.ActiveTransition activeTransition, WindowContainerTransaction windowContainerTransaction, boolean z) {
        if (activeTransition == null || transitionInfo == null || transaction == null) {
            return null;
        }
        boolean zMatchPredictiveContinuous = matchPredictiveContinuous(transitionInfo, activeTransition.mInfo);
        boolean zHasSameTaskIdOrMatchInfo = TransitionAnimationUtil.hasSameTaskIdOrMatchInfo(transitionInfo, activeTransition.mInfo);
        boolean z2 = false;
        boolean z3 = zMatchPredictiveContinuous && zHasSameTaskIdOrMatchInfo;
        if (zMatchPredictiveContinuous && !zHasSameTaskIdOrMatchInfo) {
            z2 = true;
        }
        boolean zNextPredictiveReturnToHome = nextPredictiveReturnToHome(activeTransition.mInfo);
        if (z || z3) {
            setRemoteToBackAnimContinuous(true);
            Log.d(TAG, "merge remote, reparent leash to Transition Root B, secondInfo: " + activeTransition.mInfo);
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if (change.getTaskInfo() == null) {
                    Log.w(TAG, "no need to reparent non-task to Transition Root B, target: " + change.getLeash());
                } else {
                    int iRootIndexFor = TransitionUtil.rootIndexFor(change, activeTransition.mInfo);
                    SurfaceControl leash = activeTransition.mInfo.getRoot(iRootIndexFor).getLeash();
                    SurfaceControl leash2 = change.getLeash();
                    if (leash.isValid() && leash2.isValid()) {
                        if ((!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || !ShellContinuousTransitionUtils.ENABLE_REMOTE_TO_REMOTE || ShellContinuousTransitionUtils.isLaunchUnityStart(transitionInfo, activeTransition.mInfo)) && !z3) {
                            transaction.reparent(change.getLeash(), activeTransition.mInfo.getRoot(iRootIndexFor).getLeash());
                        }
                        transaction.show(activeTransition.mInfo.getRoot(iRootIndexFor).getLeash());
                        if (change.getMode() == 2 || change.getMode() == 4 || (change.getMode() == 6 && change.hasFlags(131072))) {
                            transaction.show(change.getLeash());
                        }
                    } else {
                        Log.w(TAG, "no need to reparent released layer to Transition Root B, target: " + change.getLeash());
                    }
                }
            }
        } else {
            if ((ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION && ShellContinuousTransitionUtils.ENABLE_REMOTE_TO_REMOTE && SharedReflectionHelper.getRemoteContinuous(windowContainerTransaction) && !ShellContinuousTransitionUtils.isLaunchUnityStart(transitionInfo, activeTransition.mInfo)) || z2) {
                setRemoteToBackAnimContinuous(true);
                TransitionLog.always("merge remote, reparent leashs to Transition Root B, secondInfo: " + activeTransition.mInfo);
                return transaction;
            }
            if (zNextPredictiveReturnToHome) {
                for (TransitionInfo.Change change2 : transitionInfo.getChanges()) {
                    if (change2.getTaskInfo() != null && change2.getTaskInfo().topActivity != null && ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change2.getTaskInfo().topActivity.flattenToShortString()) && change2.getLeash() != null && change2.getLeash().isValid()) {
                        transaction.show(change2.getLeash());
                        Log.d(TAG, "need to opposite of finishT.show in setupStartState target: " + change2.getLeash());
                    }
                }
            }
            SurfaceControl.Transaction transactionFixFinishTBackAnimContinue = fixFinishTBackAnimContinue(transitionInfo, transaction, activeTransition, windowContainerTransaction);
            if (transactionFixFinishTBackAnimContinue != null) {
                return transactionFixFinishTBackAnimContinue;
            }
            SurfaceControl.Transaction tmpFinishT = SharedReflectionHelper.getTmpFinishT(transitionInfo);
            if (tmpFinishT != null) {
                tmpFinishT.merge(transaction);
                return tmpFinishT;
            }
        }
        return transaction;
    }

    public Animation getAnimationFromAnimator(ValueAnimator valueAnimator) {
        if (valueAnimator != null) {
            return (Animation) OplusTransitionReflectionHelper.methodInvokeOnExt(valueAnimator, METHOD_GET_ANIMATION, null, new Object[0]);
        }
        return null;
    }

    public boolean getBackAnimContinuous(WindowContainerTransaction windowContainerTransaction) {
        if (windowContainerTransaction != null) {
            return ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_BACK_ANIM_CONTINUOUS, null, new Object[0])).booleanValue();
        }
        return false;
    }

    public boolean getIgnoreUpdateToAnimator(ValueAnimator valueAnimator) {
        if (valueAnimator != null) {
            return ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(valueAnimator, METHOD_GET_IGNORE_ANIMATOR_UPDATE, null, new Object[0])).booleanValue();
        }
        return false;
    }

    public int getModeFromAnimator(ValueAnimator valueAnimator) {
        if (valueAnimator != null) {
            return ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(valueAnimator, METHOD_GET_TRANSITION_MODE, null, new Object[0])).intValue();
        }
        return -1;
    }

    public RecentsTransitionHandler.RecentsController getRecentsController(int i, RecentsTransitionHandler recentsTransitionHandler) {
        if (recentsTransitionHandler != null && i > 0) {
            ArrayList recentsControllers = recentsTransitionHandler.getRecentsControllers();
            int size = recentsControllers.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = recentsControllers.get(i2);
                i2++;
                RecentsTransitionHandler.RecentsController recentsController = (RecentsTransitionHandler.RecentsController) obj;
                TransitionInfo playingTransitionInfo = recentsController.getPlayingTransitionInfo();
                if (playingTransitionInfo != null && playingTransitionInfo.getDebugId() == i) {
                    return recentsController;
                }
            }
        }
        return null;
    }

    public boolean getWallPaperChangeRemoved(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_CLOSE_WALLPAPER_REMOVED, null, new Object[0]);
        if (bool != null) {
            Slog.d(TAG, " getWallPaperChangeRemoved, hasRemoved=" + bool);
        }
        return bool != null && bool.booleanValue();
    }

    public RecentsTransitionHandler.RecentsController getZoomFinishRecentsController(Bundle bundle, RecentsTransitionHandler recentsTransitionHandler) {
        if (bundle == null) {
            return null;
        }
        int i = bundle.getInt("zoomFinishRecentId", -1);
        RecentsTransitionHandler.RecentsController recentsController = getRecentsController(i, recentsTransitionHandler);
        TransitionLog.always("When zoomFinishRecentId is " + i + ", RecentsController is " + recentsController);
        return recentsController;
    }

    public boolean hasBackgroundAlready(int i) {
        return (this.mTransitionBgMap.get(Integer.valueOf(i)) == null || this.mTransitionBgMap.get(Integer.valueOf(i)).isEmpty()) ? false : true;
    }

    public void hideMergedToRecentsTask(TransitionInfo transitionInfo, int i) {
        Transitions.ActiveTransition activeTransition;
        ArrayList arrayList;
        if (transitionInfo == null || i <= 0 || (activeTransition = this.mTransitions.getActiveTransition(transitionInfo.getTrack())) == null || !(activeTransition.mHandler instanceof RecentsTransitionHandler) || (arrayList = activeTransition.mMerged) == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Transitions.ActiveTransition activeTransition2 = (Transitions.ActiveTransition) activeTransition.mMerged.get(size);
            for (TransitionInfo.Change change : activeTransition2.mInfo.getChanges()) {
                if (change != null && change.getLeash() != null && change.getLeash().isValid()) {
                    int mode = change.getMode();
                    if (change.getTaskInfo() != null && change.getTaskInfo().taskId == i && (mode == 1 || mode == 3 || mode == 6)) {
                        activeTransition2.mFinishT.hide(change.getLeash());
                        TransitionLog.always("hide closing task: " + change.getLeash() + "in transition: " + activeTransition2.mInfo.getDebugId());
                        return;
                    }
                }
            }
        }
    }

    public void hideOpenTaskInFinishIfNeeded() {
        Transitions.ActiveTransition tmpRemoteFromRecent;
        ArrayList arrayList;
        Transitions.ActiveTransition activeTransition;
        Integer num;
        Transitions.ActiveTransition playingRecents = this.mTransitions.getPlayingRecents();
        if (playingRecents == null || playingRecents.mInfo == null || (tmpRemoteFromRecent = this.mTransitions.getTmpRemoteFromRecent(playingRecents)) == null || (arrayList = tmpRemoteFromRecent.mMerged) == null || arrayList.size() == 0 || (activeTransition = (Transitions.ActiveTransition) tmpRemoteFromRecent.mMerged.get(0)) == null || activeTransition.mInfo == null || (num = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(playingRecents.mInfo, GET_REMOVED_TASK_ID, null, new Object[0])) == null) {
            return;
        }
        for (TransitionInfo.Change change : activeTransition.mInfo.getChanges()) {
            if (change != null && change.getLeash() != null && change.getLeash().isValid() && change.getTaskInfo() != null && change.getTaskInfo().taskId == num.intValue()) {
                activeTransition.mFinishT.hide(change.getLeash());
                TransitionLog.always("Hide removed task: " + num.intValue() + " WMCore requested for Remote transition: " + activeTransition.mInfo.getDebugId());
            }
        }
    }

    public void hookDefaultMerge(TransitionInfo transitionInfo) {
        Transitions.ActiveTransition activeTransition;
        if (transitionInfo == null || (activeTransition = this.mTransitions.getActiveTransition(transitionInfo.getTrack())) == null) {
            return;
        }
        boolean zIsGlobalSearchPageSwitch = OplusAnimationController.isGlobalSearchPageSwitch(activeTransition.mInfo);
        boolean zIsSwipeUpBrowserPageSwitch = OplusAnimationController.isSwipeUpBrowserPageSwitch(activeTransition.mInfo);
        if (zIsGlobalSearchPageSwitch) {
            TransitionLog.always("playingIsGlobalSearchPageSwitch true, ready = " + transitionInfo);
            OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_GLOBAL_SEARCH_PAGE_SWITCH, new Class[]{Boolean.TYPE}, Boolean.TRUE);
        }
        if (zIsSwipeUpBrowserPageSwitch) {
            TransitionLog.always("playingIsSwipeUpBrowserPageSwitch true, ready = " + transitionInfo);
            OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_SWIPE_UP_BROWSER_SCREEN_SHOWING, new Class[]{Boolean.TYPE}, Boolean.TRUE);
        }
    }

    public boolean hookForceMergeActivityOnly(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (transitionInfo != null && transitionInfo2 != null) {
            Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_GLOBALSEARCH_SWITCH_TO_HOME_COLLECT, null, new Object[0]);
            if (transitionInfo.getDebugId() < transitionInfo2.getDebugId() || (bool != null && bool.booleanValue())) {
                boolean z = false;
                boolean z2 = true;
                for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
                    TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
                    ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                    if (change.getMode() == 6 && (change.getFlags() & TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0 && taskInfo != null && taskInfo.topActivityType == 2) {
                        z = true;
                    } else if (change.getActivityComponent() == null) {
                        z2 = false;
                    }
                }
                if (z && z2) {
                    TransitionLog.always("ShellContinuousTransitionController force merge activityonly anim");
                    return true;
                }
                if (isEmbeddedSecondHopWithRedundantHomeToFront(transitionInfo, transitionInfo2)) {
                    Slog.i(TAG, "Force merge embedded second-hop as activity-only, transitionId=" + transitionInfo.getDebugId());
                    return true;
                }
            }
        }
        return false;
    }

    public void hookRecentsMerge(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, SurfaceControl.Transaction transaction, ArrayList<RemoteAnimationTarget> arrayList, IBinder iBinder) {
        fixStartTOnRecentsMerge(transitionInfo, transaction, arrayList);
        adjustAppearedTargets(arrayList, transitionInfo, iBinder);
        addFlagInRecentOnRemoteTargets(transitionInfo, transitionInfo2, arrayList);
    }

    public boolean ifInvokeCallBackInContinuous(boolean z, ArrayList<RemoteAnimationTarget> arrayList, IBinder iBinder, TransitionInfo transitionInfo) {
        if (iBinder != null && transitionInfo != null) {
            boolean zIsRemoteOpenRequestedFrom = this.mTransitions.isRemoteOpenRequestedFrom(iBinder, ShellContinuousTransitionUtils.SYSUI_DEBUG_NAME);
            boolean zIsRemoteOpenRequestedFrom2 = this.mTransitions.isRemoteOpenRequestedFrom(iBinder, "QuickstepLaunch");
            boolean zIsRemoteOpenRequestedFrom3 = this.mTransitions.isRemoteOpenRequestedFrom(iBinder, SPRUCE_DEBUG_NAME);
            boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_BAL_ALLOW, null, new Object[0])).booleanValue();
            boolean zBooleanValue2 = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_ASSIST_SCREEN_SHOWING, null, new Object[0])).booleanValue();
            boolean z2 = (zIsRemoteOpenRequestedFrom2 || zIsRemoteOpenRequestedFrom || zIsRemoteOpenRequestedFrom3) ? false : true;
            boolean z3 = (zBooleanValue || zBooleanValue2) ? false : true;
            if (!z && (arrayList == null || (z2 && z3))) {
                return true;
            }
        }
        return false;
    }

    public boolean ifMergeIntoKeyguard(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || transitionInfo2 == null || transitionInfo == null) {
            return false;
        }
        boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_FROM_KEYGUARD_START_ACTIVITY, null, new Object[0])).booleanValue();
        boolean zIsOpeningType = TransitionUtil.isOpeningType(transitionInfo2.getType());
        boolean z = transitionInfo2.getType() == 12;
        boolean zIsActivityLevelOnlyOrTaskFragmentOnly = isActivityLevelOnlyOrTaskFragmentOnly(transitionInfo, transitionInfo2);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("ShellContinuousTransitionController ifMergeIntoKeyguard isFromKeyguardStartActivity:", " isOpeningType:", " isSleepType:", zBooleanValue, zIsOpeningType);
        sbM.append(z);
        sbM.append(" isActivityLevelOnlyOrTaskFragmentOnly:");
        sbM.append(zIsActivityLevelOnlyOrTaskFragmentOnly);
        TransitionLog.always(sbM.toString());
        return ((zBooleanValue || zIsActivityLevelOnlyOrTaskFragmentOnly) && zIsOpeningType) || z;
    }

    public boolean ifMergeIntoRemote(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, Transitions.TransitionFinishCallback transitionFinishCallback, boolean z, RemoteTransition remoteTransition) {
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION && !ShellContinuousTransitionUtils.isKeyguardAppearTransition(transitionInfo)) {
            Transitions.ActiveTransition activeTransition = this.mTransitions.getActiveTransition(transitionInfo.getTrack());
            if (!skipMergeGoogleHomeInControlsActivity(activeTransition, transitionInfo)) {
                Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_MERGE_SECOND_TASK_ANIMATION, null, new Object[0]);
                Boolean bool2 = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_LAUNCH_FROM_ASSISTANT_BY_DEFAULT, null, new Object[0]);
                boolean z2 = bool != null && bool.booleanValue();
                boolean z3 = bool2 != null && bool2.booleanValue();
                TransitionLog.always("mergeSecondTaskAnimation: " + z2 + "launchFromAssistantByDefault" + z3);
                if ((normalSameTaskLevelTransition(activeTransition, iBinder, transitionInfo, z2) || translucentSameTaskLevelTransition(activeTransition, transitionInfo) || translucentActivityLevelTransition(transitionInfo) || avoidToFrontContinuousSpruce(transitionInfo, activeTransition)) && !z2 && !z3) {
                    TransitionLog.always("ShellContinuousTransitionController transition " + transitionInfo.getDebugId() + " have same packageName and only normal task level, merge into remote.");
                    if (activeTransition != null) {
                        activeTransition.mIsNormalTaskMergeIntoRemote = true;
                    }
                    transitionFinishCallback.onTransitionFinished((WindowContainerTransaction) null);
                    return true;
                }
                if (OplusActivityEmbeddingAnimationRunner.getInstance().isTfAnimationNeedMergeRemote(transitionInfo, transaction)) {
                    TransitionLog.always("ShellContinuousTransitionController transition " + transitionInfo.getDebugId() + " tf animation need merge, merge into remote.");
                    transaction.apply();
                    transaction.clear();
                    transitionFinishCallback.onTransitionFinished((WindowContainerTransaction) null);
                    return true;
                }
                if (!isPureLauncherTransition(transitionInfo)) {
                    if (isLargeSmallScreenSwitchAnim(transitionInfo)) {
                        TransitionLog.always("ShellContinuousTransitionController transition " + transitionInfo.getDebugId() + " isLargeSmallScreenSwitchAnim, do not merge into remote.");
                        return false;
                    }
                    if (skipMergeForArChange(transitionInfo, remoteTransition, activeTransition)) {
                        TransitionLog.always("ShellContinuousTransitionControllertransition skipMergeForArChange " + transitionInfo.getDebugId());
                        return false;
                    }
                    if (remoteTransition != null && "View Seamless".equals(remoteTransition.getDebugName()) && (transitionInfo.getFlags() & TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) == 0) {
                        TransitionLog.always("ShellContinuousTransitionController transition " + transitionInfo.getDebugId() + " not View Seamless, skip merge into View Seamless remote.");
                        return false;
                    }
                    if (skipMergeForPip(iBinder, transitionInfo)) {
                        TransitionLog.always("ShellContinuousTransitionControllertransition " + transitionInfo.getDebugId() + " PIP do not merge into remote.");
                        return true;
                    }
                    if ((isArOrTranslucentTransition(transitionInfo, transaction) && z) || isInvalidTaskTransition(transitionInfo)) {
                        transaction.apply();
                        transaction.clear();
                        TransitionLog.always("ShellContinuousTransitionControllertransition " + transitionInfo.getDebugId() + " merge into remote.");
                        transitionFinishCallback.onTransitionFinished((WindowContainerTransaction) null);
                        return true;
                    }
                    if (OplusPanoramaWorkBranchAnimController.getInstance().skipMergeIntoRemote(transitionInfo) || skipMergeConvertToTranslucent(transitionInfo)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean ignoreAbort(TransitionInfo transitionInfo) {
        if (transitionInfo.getChanges() != null && transitionInfo.getChanges().size() != 0) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(0);
            if (transitionInfo.getType() == 1 && isIgnoreAbortPkg(change)) {
                return true;
            }
        }
        return false;
    }

    public void init(Transitions transitions) {
        this.mTransitions = transitions;
    }

    public RemoteAnimationTarget initTargetWithTaskLeash(TransitionInfo.Change change, int i, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, ArrayMap<SurfaceControl, SurfaceControl> arrayMap, TransitionInfo transitionInfo2) {
        RemoteAnimationTarget remoteAnimationTargetNewTarget = TransitionUtil.newTarget(change, i, transitionInfo, transaction, arrayMap);
        transaction.reparent(remoteAnimationTargetNewTarget.leash, transitionInfo2.getRoot(TransitionUtil.rootIndexFor(change, transitionInfo2)).getLeash());
        transaction.setLayer(remoteAnimationTargetNewTarget.leash, i);
        transaction.hide(remoteAnimationTargetNewTarget.leash);
        return remoteAnimationTargetNewTarget;
    }

    public boolean isCustomFilter(TransitionInfo transitionInfo) {
        ComponentName componentName;
        if (transitionInfo != null && transitionInfo.getChanges() != null) {
            for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
                if (change != null) {
                    TransitionInfo.AnimationOptions animationOptions = change.getAnimationOptions();
                    int type = animationOptions != null ? animationOptions.getType() : 0;
                    ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                    if (taskInfo != null && type == 1 && (componentName = ((TaskInfo) taskInfo).baseActivity) != null && componentName != null && IS_MATCH_CUSTOM_COMPONENT.contains(componentName.flattenToString())) {
                        TransitionLog.debug("isCustomFilter = " + componentName.flattenToString());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isDefaultContinuous() {
        return this.mDefaultContinuous;
    }

    public boolean isDisplayChangeTransition(IBinder iBinder) {
        Transitions.ActiveTransition activeTransition;
        TransitionInfo transitionInfo;
        if (iBinder == null || (activeTransition = (Transitions.ActiveTransition) this.mTransitions.getKnownTransitions().get(iBinder)) == null || (transitionInfo = activeTransition.mInfo) == null || (transitionInfo.getFlags() & 47360) != 0 || (activeTransition.mInfo.getFlags() & 128) != 0) {
            return false;
        }
        return hasRotationChange(activeTransition.mInfo);
    }

    public boolean isLandScapeExitScene(IBinder iBinder, Transitions.TransitionHandler transitionHandler, ShellTaskOrganizer shellTaskOrganizer, RemoteTransitionHandler remoteTransitionHandler, TransitionInfo transitionInfo, Context context) {
        boolean z;
        boolean z2;
        if (transitionHandler != null && context != null) {
            boolean z3 = transitionHandler instanceof RecentsTransitionHandler;
            if (z3) {
                z = false;
                z2 = false;
            } else {
                z = (transitionInfo.getType() == 1 || transitionInfo.getType() == 4) && this.mRemoteTransitionToken == iBinder;
                z2 = transitionInfo.getType() == 6 && isChangeAnimationFromLandToPort(transitionInfo);
            }
            if (z3 || z || z2) {
                TransitionLog.always("isRecentsAnimation is: " + z3 + " isRemoteAnimation " + z);
                int rotation = context.getResources().getConfiguration().windowConfiguration.getRotation();
                boolean z4 = rotation == 1 || rotation == 3 || isLandAppOpen(transitionInfo);
                if (z2 && this.mIsLandScapeExitScene) {
                    this.mIsLandScapeExitScene = false;
                    TransitionLog.always("isLandScapeExitScene and mIsLandScapeExitScene is: " + this.mIsLandScapeExitScene + ", change animation from land to port, notify launcher to cancel listener");
                    shellTaskOrganizer.onLandScapeSceneExit(true);
                    return true;
                }
                if (z4) {
                    this.mIsLandScapeExitScene = true;
                    TransitionLog.always("isLandScapeExitScene and mIsLandScapeExitScene is: " + this.mIsLandScapeExitScene);
                    return true;
                }
                if (z3) {
                    this.mIsLandScapeExitScene = false;
                    TransitionLog.always("isLandScapeExitScene and mIsLandScapeExitScene is: " + this.mIsLandScapeExitScene + ", notify launcher to cancel listener");
                    shellTaskOrganizer.onLandScapeSceneExit(false);
                }
            }
        }
        return false;
    }

    public boolean isOpenReadyMergeToRecents(IBinder iBinder) {
        Transitions transitions;
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || (transitions = this.mTransitions) == null) {
            return false;
        }
        return transitions.isOpenFromRecentsSlide(iBinder);
    }

    public boolean isPreemptedAndHasSameNonHomeTask(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (transitionInfo != null && transitionInfo2 != null) {
            Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_PREEMPTED_BY_RECENTS, null, new Object[0]);
            boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
            OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_PREEMPTED_BY_RECENTS, new Class[]{Boolean.TYPE}, Boolean.FALSE);
            TransitionLog.debug("isPreemptedAndHasSameNonHomeTask, set isPreemptedByRecents: " + zBooleanValue + " to false");
            ArraySet arraySet = new ArraySet();
            for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
                ActivityManager.RunningTaskInfo taskInfo = ((TransitionInfo.Change) transitionInfo.getChanges().get(i)).getTaskInfo();
                if (taskInfo != null && taskInfo.topActivityType != 2) {
                    arraySet.add(Integer.valueOf(taskInfo.taskId));
                }
            }
            if (arraySet.isEmpty()) {
                return false;
            }
            boolean z = false;
            for (int i2 = 0; i2 < transitionInfo2.getChanges().size(); i2++) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo2.getChanges().get(i2);
                ActivityManager.RunningTaskInfo taskInfo2 = change.getTaskInfo();
                if (taskInfo2 != null && taskInfo2.topActivityType != 2 && arraySet.contains(Integer.valueOf(taskInfo2.taskId)) && (TransitionUtil.isOpeningMode(change.getMode()) || change.getMode() == 6)) {
                    TransitionLog.debug("Has same non-home task, mergeInfo = " + transitionInfo2.getDebugId() + ", curInfo = " + transitionInfo.getDebugId());
                    z = true;
                }
            }
            if (zBooleanValue && z) {
                return true;
            }
        }
        return false;
    }

    public boolean isRecentFinishToHome() {
        return this.mRecentFinishToHome;
    }

    public boolean isRecentsTransition(IBinder iBinder, TransitionInfo transitionInfo) {
        if (transitionInfo != null && (transitionInfo.getFlags() & 128) != 0) {
            return true;
        }
        Transitions transitions = this.mTransitions;
        return (transitions == null || transitions.getRecentsTransitionHandler() == null || this.mTransitions.getRecentsTransitionHandler().getRecentController(iBinder) == null) ? false : true;
    }

    public boolean isRemoteFrom(IBinder iBinder, TransitionInfo transitionInfo, String str) {
        RemoteTransition remoteTransition;
        if (this.mTransitions.getRemoteTransitionHandler() == null) {
            return false;
        }
        ArrayMap requestedRemotes = this.mTransitions.getRemoteTransitionHandler().getRequestedRemotes();
        ArrayList filters = this.mTransitions.getRemoteTransitionHandler().getFilters();
        RemoteTransition remoteTransition2 = (RemoteTransition) requestedRemotes.get(iBinder);
        if (remoteTransition2 != null && str.equals(remoteTransition2.getDebugName())) {
            return true;
        }
        for (int size = filters.size() - 1; transitionInfo != null && size >= 0; size--) {
            if (((TransitionFilter) ((Pair) filters.get(size)).first).matches(transitionInfo) && (remoteTransition = (RemoteTransition) ((Pair) filters.get(size)).second) != null && str.equals(remoteTransition.getDebugName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isRemoteReadyMergeToRecents(IBinder iBinder, String str) {
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            return this.mTransitions.isRemoteOpenRequestedFrom(iBinder, str);
        }
        return false;
    }

    public boolean isRemoteToRecentContinuous() {
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            return this.mRemoteToRecentContinuous;
        }
        return false;
    }

    public boolean isRemoteToRemoteContinuous() {
        if (ShellContinuousTransitionUtils.ENABLE_CONTINUOUS_REMOTE) {
            return this.mRemoteToRemoteContinuous;
        }
        return false;
    }

    public boolean isRemoteTransition(IBinder iBinder, TransitionInfo transitionInfo) {
        RemoteTransition remoteTransition;
        boolean z;
        if (this.mTransitions.getRemoteTransitionHandler() != null && !isRecentsTransition(iBinder, transitionInfo) && (transitionInfo == null || !KeyguardTransitionHandler.handles(transitionInfo))) {
            if (this.mTransitions.getRemoteTransitionHandler().getRequestedRemotes().get(iBinder) != null) {
                return true;
            }
            ArrayList filters = this.mTransitions.getRemoteTransitionHandler().getFilters();
            int size = filters.size() - 1;
            while (true) {
                if (transitionInfo != null && size >= 0) {
                    if (((TransitionFilter) ((Pair) filters.get(size)).first).matches(transitionInfo) && ((Pair) filters.get(size)).second != null) {
                        remoteTransition = (RemoteTransition) ((Pair) filters.get(size)).second;
                        z = true;
                        break;
                    }
                    size--;
                } else {
                    remoteTransition = null;
                    z = false;
                    break;
                }
            }
            boolean zAvoidDelegateToRemote = AdaptiveSmoothShellAnimManager.getInstance().avoidDelegateToRemote(remoteTransition, transitionInfo);
            if (z && !zAvoidDelegateToRemote) {
                return true;
            }
        }
        return false;
    }

    public boolean isSecondTaskAnimationToRecents(TransitionInfo transitionInfo) {
        Boolean bool;
        return ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION && transitionInfo != null && (bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_MERGE_SECOND_TASK_ANIMATION, null, new Object[0])) != null && bool.booleanValue();
    }

    public boolean isToHomeAnimation(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            return false;
        }
        List<TransitionInfo.Change> changes = transitionInfo.getChanges();
        Transitions.ActiveTransition activeTransition = this.mTransitions.getActiveTransition(transitionInfo2.getTrack());
        if (transitionInfo.getType() == 4 && changes.size() == 1 && activeTransition != null && activeTransition.mMerged != null) {
            TransitionInfo.Change change = (TransitionInfo.Change) changes.get(0);
            if (change.getTaskInfo() != null && change.getMode() == 4) {
                return true;
            }
        }
        if (transitionInfo.getType() == 2 && changes.size() == 1 && activeTransition != null) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) changes.get(0);
            if (change2.getTaskInfo() != null && change2.getMode() == 2) {
                return true;
            }
        }
        boolean z = false;
        for (TransitionInfo.Change change3 : changes) {
            if (change3.getTaskInfo() != null && change3.getTaskInfo().getActivityType() == 2 && (TransitionUtil.isOpeningType(change3.getMode()) || change3.getMode() == 6)) {
                z = true;
            } else if (TransitionUtil.isOpeningType(change3.getMode()) || change3.getActivityComponent() != null) {
                return false;
            }
        }
        return z;
    }

    public boolean isWallpaperTransitionIntraClose(TransitionInfo transitionInfo) {
        boolean z = false;
        boolean z2 = false;
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
            if ((change.getFlags() & 1) != 0 && taskInfo != null) {
                if (TransitionUtil.isOpeningType(change.getMode())) {
                    z = true;
                } else if (TransitionUtil.isClosingType(change.getMode()) && !isSystemApp(taskInfo)) {
                    z2 = true;
                }
            }
        }
        if (!z || !z2 || transitionInfo.getType() != 2) {
            return false;
        }
        TransitionLog.always("wallpaperTransit type is WALLPAPER_TRANSITION_INTRA_CLOSE");
        return true;
    }

    public void keepBgSurfaceForContinuous(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (transitionInfo == null || transitionInfo2 == null) {
            return;
        }
        SurfaceControl surfaceControl = (SurfaceControl) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_PARENT_LEASH_FOR_BG_COLOR, null, new Object[0]);
        SurfaceControl surfaceControl2 = (SurfaceControl) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo2, GET_PARENT_LEASH_FOR_BG_COLOR, null, new Object[0]);
        boolean z = isActivityLevelOnly(transitionInfo) && isActivityLevelOnly(transitionInfo2);
        boolean z2 = isDefaultContinuous() && TransitionAnimationUtil.hasSameTaskIdInBothTransition(transitionInfo, transitionInfo2);
        TransitionLog.always("keepBgSurfaceForContinuous, bothActivityLevel = " + z + ", matchTaskSwitchScene = " + z2);
        if ((!z && !z2) || surfaceControl == null || surfaceControl2 == null) {
            return;
        }
        OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, KEEP_BG_FOR_CONTINUOUS, new Class[]{Boolean.TYPE}, Boolean.TRUE);
    }

    public void logFullSceneContinuousData(TransitionInfo transitionInfo) {
        WindowContainerTransaction windowContainerTransaction;
        if (!ShellContinuousTransitionUtils.ENABLE_FULL_SCENE_CONTINUOUS || transitionInfo == null) {
            return;
        }
        int iIntValue = ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TRANSITION_CONTINUOUS_TYPE, null, new Object[0])).intValue();
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(iIntValue, "trying to invoke on remote start, fullSceneContinuousType = ", ", info hash = ");
        sbM.append(transitionInfo.hashCode());
        TransitionLog.always(sbM.toString());
        if (iIntValue != 3 || (windowContainerTransaction = (WindowContainerTransaction) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_WCT, null, new Object[0])) == null) {
            return;
        }
        float fFloatValue = ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_RADIUS, null, new Object[0])).floatValue();
        float fFloatValue2 = ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_ALPHA, null, new Object[0])).floatValue();
        float fFloatValue3 = ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_WEIGHT, null, new Object[0])).floatValue();
        float fFloatValue4 = ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_CONTENT_RADIUS, null, new Object[0])).floatValue();
        float fFloatValue5 = ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_SCALE_VALUE, null, new Object[0])).floatValue();
        Rect rect = (Rect) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_WINDOW_CROP, null, new Object[0]);
        RectF rectF = (RectF) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_RECTF, null, new Object[0]);
        boolean zBooleanValue = ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_REMOTE_INTERRUPT, null, new Object[0])).booleanValue();
        StringBuilder sbM2 = MonoSpline$.ExternalSyntheticOutline0.m("hookRemoteStart, radius = ", fFloatValue, ", alpha = ", fFloatValue2, ", weight = ");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, fFloatValue3, ", contentRadius = ", fFloatValue4, ", scaleValue = ");
        sbM2.append(fFloatValue5);
        sbM2.append(", windowCrop = ");
        sbM2.append(rect);
        sbM2.append(", rectF = ");
        sbM2.append(rectF);
        sbM2.append(", remoteInterrupt = ");
        sbM2.append(zBooleanValue);
        TransitionLog.always(sbM2.toString());
    }

    public void markFullSceneContinuousType(SurfaceControl.Transaction transaction, IBinder iBinder, RecentsTransitionHandler recentsTransitionHandler, TransitionInfo transitionInfo) {
        Transitions.ActiveTransition activeTransition;
        TransitionInfo transitionInfo2;
        if (!ShellContinuousTransitionUtils.ENABLE_FULL_SCENE_CONTINUOUS || transitionInfo == null || this.mTransitions.getActiveTransition(transitionInfo.getTrack()) == null || (transitionInfo2 = (activeTransition = this.mTransitions.getActiveTransition(transitionInfo.getTrack())).mInfo) == null) {
            return;
        }
        boolean z = isRemoteFrom(activeTransition.mToken, transitionInfo2, "QuickstepLaunch") || isRemoteFrom(activeTransition.mToken, activeTransition.mInfo, "QuickstepLaunchHome");
        boolean z2 = isRemoteFrom(iBinder, transitionInfo, "QuickstepLaunch") || isRemoteFrom(iBinder, transitionInfo, "QuickstepLaunchHome");
        boolean z3 = TransitionAnimationUtil.hasSameTaskIdOrMatchInfo(activeTransition.mInfo, transitionInfo) || TransitionAnimationUtil.hasSamePkgNamesInBothTransition(activeTransition.mInfo, transitionInfo, true);
        boolean z4 = (recentsTransitionHandler == null || recentsTransitionHandler.getRecentController(iBinder) == null) ? false : true;
        boolean zIsRemoteFrom = isRemoteFrom(iBinder, transitionInfo, LAUNCHER_MENU_DEBUG_NAME);
        boolean zIsRemoteTransition = isRemoteTransition(iBinder, transitionInfo);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("markFullSceneContinuousType, playingIsLauncherRemote = ", ", nextIsLauncherRemote = ", ", nextIsRecent = ", z, z2);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", nextIsMenuRemote = ", ", nextIsRemoteTransition = ", sbM, z4, zIsRemoteFrom);
        sbM.append(zIsRemoteTransition);
        sbM.append(", hasSameTasksOrPackagesInTransition = ");
        sbM.append(z3);
        TransitionLog.always(sbM.toString());
        if (!z) {
            if (z || !z3 || zIsRemoteFrom) {
                return;
            }
            if (z4 || z2) {
                Class cls = Integer.TYPE;
                OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_TRANSITION_CONTINUOUS_TYPE, new Class[]{cls}, 3);
                OplusTransitionReflectionHelper.methodInvokeOnExt(activeTransition.mInfo, SET_TRANSITION_CONTINUOUS_TYPE, new Class[]{cls}, 3);
                return;
            }
            return;
        }
        if (zIsRemoteTransition && !z2 && ENABLE_SAMEPROCESS_SAMETASK) {
            if (!z3) {
                OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_TRANSITION_CONTINUOUS_TYPE, new Class[]{Integer.TYPE}, 2);
                return;
            }
            OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_TRANSITION_CONTINUOUS_TYPE, new Class[]{Integer.TYPE}, 1);
            SurfaceControl.Transaction transaction2 = activeTransition.mFinishT;
            if (transaction2 == null || transaction2.mNativeObject == 0) {
                return;
            }
            TransitionLog.always("merge startT " + transaction.getId() + " to " + activeTransition.mFinishT.getId() + " in same process continuing scene");
            activeTransition.mFinishT.merge(transaction);
        }
    }

    public boolean matchContinuousScene(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, TransitionInfo transitionInfo2, WindowContainerTransaction windowContainerTransaction) {
        return SharedReflectionHelper.getRemoteContinuous(windowContainerTransaction) && matchContinuous(transitionInfo, transitionInfo2, transaction) && ShellContinuousTransitionUtils.ENABLE_CONTINUOUS_REMOTE;
    }

    public boolean matchPredictiveContinuous(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (!this.mBackPredictiveToRemoteContinuous || !ShellContinuousTransitionUtils.ENABLE_PREDICTIVE_CONTINUOUS_REMOTE || transitionInfo == null || transitionInfo2 == null) {
            return false;
        }
        Log.d(TAG, "matchPredictiveContinuous activeInfo " + transitionInfo + " readyInfo " + transitionInfo2);
        for (int size = transitionInfo.getChanges().size() - 1; size >= 0; size--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(size);
            if (!change.hasFlags(131072) && !change.hasFlags(2)) {
                return false;
            }
        }
        return true;
    }

    public boolean matchRemoteFilter(TransitionInfo transitionInfo) {
        if (transitionInfo == null || transitionInfo.getChanges() == null) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change != null) {
                if (change.getMode() == 6 && ((transitionInfo.getType() == 4 || transitionInfo.getType() == 1 || transitionInfo.getType() == 2) && change.hasFlags(TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) && change.getTaskInfo() != null && change.getTaskInfo().topActivity != null && ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change.getTaskInfo().topActivity.flattenToShortString()))) {
                    TransitionLog.debug("matchTranslucentRemote");
                    z = true;
                }
                TransitionInfo.AnimationOptions animationOptions = change.getAnimationOptions();
                int type = animationOptions != null ? animationOptions.getType() : 0;
                if (TransitionUtil.isClosingMode(change.getMode()) && ((1 != type || needMatchRemoteCmp(change)) && (change.hasFlags(4) || needMatchRemotePkg(change)))) {
                    TransitionLog.debug("hasTranslucentFlag");
                    if (change.getTaskInfo() == null || !COMPONENTS_SKIP_REMOTE_ANIMATION.contains(change.getTaskInfo().realActivity)) {
                        z2 = true;
                    } else {
                        TransitionLog.debug("skipRemoteCmp");
                        z2 = true;
                        z3 = true;
                    }
                }
                if ((transitionInfo.getType() == 1 || transitionInfo.getType() == 3) && change.getTaskInfo() != null) {
                    if (change.getMode() == 6 && change.hasFlags(TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) && change.getTaskInfo().topActivity != null && ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change.getTaskInfo().topActivity.flattenToShortString())) {
                        TransitionLog.debug("launcher mode is change, in open transition");
                        z6 = true;
                    } else if ((TransitionUtil.isOpeningType(change.getMode()) && change.getTaskInfo().getActivityType() != 2) || (TransitionUtil.isClosingMode(change.getMode()) && change.getTaskInfo() != null && COMPONENTS_WITHOUT_REMOTE_ANIMATION.contains(change.getTaskInfo().realActivity))) {
                        TransitionLog.debug("has open task:" + change + ", in open transition");
                        z7 = true;
                    }
                }
                if (ZoomUtil.sZoomTaskState == 1) {
                    if (change.getMode() == 3 && transitionInfo.getType() == 2 && change.hasFlags(1) && change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) {
                        TransitionLog.debug("matchChangeOrderErrorRemote");
                        z4 = true;
                    }
                    if (change.getMode() == 2 && (change.getFlags() == 0 || change.getFlags() == 1073741824)) {
                        TransitionLog.debug("hasNoneFlag change.getFlags() = " + change.getFlags());
                        z5 = true;
                    }
                }
            }
        }
        if (z && z2 && !z3) {
            return true;
        }
        if ((z4 && z5) || isTranslucentActivityToHome(transitionInfo)) {
            return true;
        }
        return z6 && !z7;
    }

    public boolean matchSecondaryContinuous(TransitionInfo transitionInfo, TransitionInfo.Change change, Animation animation) {
        if (transitionInfo == null || change == null) {
            return false;
        }
        Integer num = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TRANSITION_CONTINUOUS_TYPE, null, new Object[0]);
        return num == null || num.intValue() != 5 || (ShellContinuousTransitionUtils.checkHasXTranslateOnly(animation) && !(TransitionUtil.isClosingType(transitionInfo.getType()) && TransitionUtil.isClosingMode(change.getMode()) && ShellContinuousTransitionUtils.hasAlphaChange(animation)));
    }

    public boolean matchSecondaryReverseContinuous(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, IBinder iBinder, IBinder iBinder2) {
        if (ShellContinuousTransitionUtils.ENABLE_ACTIVITY_OR_TASK_SECONDARY_CONTINUOUS && !ShellContinuousTransitionUtils.isInNotSupportMode(transitionInfo) && !isRemoteTransition(iBinder2, transitionInfo2) && !isRecentsTransition(iBinder2, transitionInfo2) && !KeyguardTransitionHandler.handles(transitionInfo2) && ShellContinuousTransitionUtils.matchReverseContinuous(transitionInfo, transitionInfo2)) {
            int flags = ShellContinuousTransitionUtils.getFlags(transitionInfo2);
            boolean z = (flags & 4) != 0;
            boolean z2 = (flags & 2) != 0;
            if (!z2 && !z) {
                return true;
            }
            TransitionLog.debug("isSecondaryContinueFunClose = " + z2 + " isInSecondaryContinueBlackList = " + z);
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x010b  */
    public boolean mergeContinuousBackTransitionIfNeed(IBinder iBinder, TransitionInfo transitionInfo, ArrayList<WindowAnimation> arrayList, BackAnimationController.BackTransitionHandler backTransitionHandler, IBinder iBinder2, TransitionInfo transitionInfo2) {
        ValueAnimator animator;
        float f;
        Animation animation;
        int i = 0;
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || !ShellContinuousTransitionUtils.ENABLE_PREDICTIVE_CONTINUOUS_REMOTE || transitionInfo == null || transitionInfo2 == null) {
            return false;
        }
        int i2 = 1;
        boolean z = transitionInfo.getType() == 1;
        boolean zIsClosingType = TransitionUtil.isClosingType(transitionInfo2.getType());
        boolean zShouldTriggerNormalBackAnim = backTransitionHandler.shouldTriggerNormalBackAnim(iBinder2, transitionInfo2);
        boolean zIsSameLevelTransition = isSameLevelTransition(transitionInfo, transitionInfo2, zShouldTriggerNormalBackAnim);
        boolean zMatchSecondaryReverseContinuous = matchSecondaryReverseContinuous(transitionInfo, transitionInfo2, iBinder, iBinder2);
        if ((!zIsSameLevelTransition || !z || !zShouldTriggerNormalBackAnim) && !zMatchSecondaryReverseContinuous) {
            return false;
        }
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("mergeContinuousBackTransitionIfNeed, isNormalBackTransit=", ", isPredictiveBackTransit=", ", isSecondaryReverseContinuous=", zIsClosingType, zShouldTriggerNormalBackAnim);
        sbM.append(zMatchSecondaryReverseContinuous);
        sbM.append(", activeToken=");
        sbM.append(iBinder);
        sbM.append(", readyToken=");
        sbM.append(iBinder2);
        sbM.append(", readyInfo=");
        sbM.append(transitionInfo2);
        sbM.append(", activeAnims=");
        sbM.append(arrayList);
        TransitionLog.always(sbM.toString());
        if (zMatchSecondaryReverseContinuous) {
            OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo2, SET_TRANSITION_CONTINUOUS_TYPE, new Class[]{Integer.TYPE}, 5);
        }
        ConcurrentHashMap<String, Float> concurrentHashMap = new ConcurrentHashMap<>();
        if (SpringSlideAnimationController.ENABLE_SPRING_SLIDE_ANIM && AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController().saveBackAnimContinuePair(iBinder, iBinder2, concurrentHashMap)) {
            setDefaultContinuous(true);
            Transitions.saveDefaultTransitionContinueState(iBinder, true);
            Transitions.saveBackAnimContinueCornerRadiusMap(concurrentHashMap);
            TransitionLog.always("mergeContinuousBackTransitionIfNeed, spring setDefaultContinuous");
            return true;
        }
        ArrayMap arrayMap = new ArrayMap();
        float defaultCornerRadius = getDefaultCornerRadius();
        if (arrayList == null) {
            return false;
        }
        int size = arrayList.size() - 1;
        while (size >= 0) {
            WindowAnimation windowAnimation = arrayList.get(size);
            if (windowAnimation == null || (animator = windowAnimation.getAnimator()) == null) {
                i = i;
                f = defaultCornerRadius;
            } else if (animator.isStarted() && animator.isRunning()) {
                int i3 = i2;
                f = defaultCornerRadius;
                long currentPlayTime = animator.getCurrentPlayTime();
                Animation animationFromAnimator = AdaptiveSmoothShellAnimManager.getInstance().getAnimationFromAnimator(animator);
                int modeFromAnimator = AdaptiveSmoothShellAnimManager.getInstance().getModeFromAnimator(animator);
                if (animationFromAnimator == null) {
                    i = i;
                } else {
                    Transformation transformation = new Transformation();
                    animationFromAnimator.getTransformation(currentPlayTime, transformation);
                    if (zShouldTriggerNormalBackAnim || (zMatchSecondaryReverseContinuous && ShellContinuousTransitionUtils.matchAnimationMatrix(transformation.getMatrix()))) {
                        ComponentName componentNameByMode = ShellContinuousTransitionUtils.getComponentNameByMode(modeFromAnimator, transitionInfo);
                        if (modeFromAnimator == -1 || !TransitionUtil.isClosingMode(modeFromAnimator)) {
                            if (modeFromAnimator != -1 && TransitionUtil.isOpeningMode(modeFromAnimator)) {
                                arrayMap.put(Integer.valueOf(i3), transformation);
                                animation = animationFromAnimator;
                                concurrentHashMap.put(TransitionAnimationUtil.getMapKeyByComponentAndMode(componentNameByMode, i3), Float.valueOf(animation.hasRoundedCorners() ? f : 0.0f));
                            }
                            if (!(animation instanceof AlphaAnimation) && modeFromAnimator == 1000) {
                                arrayMap.put(2, transformation);
                            }
                        } else {
                            arrayMap.put(Integer.valueOf(i), transformation);
                            concurrentHashMap.put(TransitionAnimationUtil.getMapKeyByComponentAndMode(componentNameByMode, i), Float.valueOf(animationFromAnimator.hasRoundedCorners() ? f : 0.0f));
                        }
                        animation = animationFromAnimator;
                        if (!(animation instanceof AlphaAnimation)) {
                        }
                    } else {
                        i = i;
                    }
                }
            } else {
                i = i;
                f = defaultCornerRadius;
                TransitionLog.always("mergeContinuousBackTransitionIfNeed, valueAnimator is not running");
            }
            size--;
            defaultCornerRadius = f;
            i = i;
            i2 = 1;
        }
        if (arrayMap.size() >= 2) {
            setDefaultContinuous(true);
            Transitions.saveDefaultTransitionContinueState(iBinder, true);
            Transitions.saveBackAnimContinueCornerRadiusMap(concurrentHashMap);
            Transitions.saveBackAnimContinueTransformMap(iBinder2, arrayMap);
            TransitionLog.always("mergeContinuousBackTransitionIfNeed, default setDefaultContinuous");
        }
        return true;
    }

    public boolean needCancelToHome(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (transitionInfo != null && transitionInfo2 != null && TransitionAnimationUtil.hasSameTaskIdInBothTransition(transitionInfo, transitionInfo2) && transitionInfo.getType() == 3 && transitionInfo2.getType() == 1) {
            ArrayList arrayList = new ArrayList();
            TransitionInfo.Change change = null;
            TransitionInfo.Change change2 = null;
            for (TransitionInfo.Change change3 : transitionInfo.getChanges()) {
                if (change3.getTaskInfo() == null || change3.getTaskInfo().topActivity == null || !ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change3.getTaskInfo().topActivity.flattenToShortString())) {
                    if (change3.getMode() == 4 && change3.getFlags() == 0 && change3.getTaskInfo() != null) {
                        arrayList.add(Integer.valueOf(change3.getTaskInfo().taskId));
                    }
                } else if (change3.getMode() == 3) {
                    change2 = change3;
                }
            }
            if (change2 != null && !arrayList.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                for (TransitionInfo.Change change4 : transitionInfo2.getChanges()) {
                    if (change4.getTaskInfo() == null || change4.getTaskInfo().topActivity == null || !ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change4.getTaskInfo().topActivity.flattenToShortString())) {
                        if (change4.getMode() == 6 && (change4.getFlags() & TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0 && change4.getTaskInfo() != null) {
                            arrayList2.add(change4);
                        }
                    } else if (change4.getMode() == 4) {
                        change = change4;
                    }
                }
                if (change != null && !arrayList2.isEmpty()) {
                    int size = arrayList2.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = arrayList2.get(i);
                        i++;
                        if (!arrayList.contains(Integer.valueOf(((TransitionInfo.Change) obj).getTaskInfo().taskId))) {
                            return false;
                        }
                    }
                    Slog.d(TAG, "needCancelToHome readyInfo:" + transitionInfo2 + " playingInfo:" + transitionInfo);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean nextPredictiveReturnToHome(TransitionInfo transitionInfo) {
        if (!ShellContinuousTransitionUtils.ENABLE_PREDICTIVE_CONTINUOUS_REMOTE || transitionInfo == null || transitionInfo.getChanges() == null || transitionInfo.getType() != 13) {
            return false;
        }
        boolean z = false;
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (!change.hasFlags(131072) && !change.hasFlags(2)) {
                return false;
            }
            if (change.getTaskInfo() != null && change.getTaskInfo().topActivity != null && ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change.getTaskInfo().topActivity.flattenToShortString()) && TransitionUtil.isOpeningMode(change.getMode())) {
                z = true;
            }
        }
        return z;
    }

    public boolean notReparentLeash(SurfaceControl surfaceControl, SurfaceControl surfaceControl2) {
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || surfaceControl == null || surfaceControl2 == null || !surfaceControl.toString().contains("WindowedMagnification") || !surfaceControl2.toString().contains("Transition Root: Task")) {
            return false;
        }
        TransitionLog.always("notReparentLeash leash:" + surfaceControl + ", rootLeash: " + surfaceControl2);
        return true;
    }

    public boolean notifyLauncherStartActivity(TransitionInfo transitionInfo, ShellTaskOrganizer shellTaskOrganizer, IBinder iBinder, RemoteTransitionHandler remoteTransitionHandler) {
        if (this.mIsLandScapeExitScene) {
            if (transitionInfo.getType() == 6 || isRotationChange(transitionInfo)) {
                TransitionLog.always("Transition Change excuted by last landscape application  and transition type is: " + transitionInfo.getType());
                this.mIsLandScapeExitScene = false;
                shellTaskOrganizer.onLandScapeSceneExit(true);
                return true;
            }
            ArrayMap requestedRemotes = remoteTransitionHandler.getRequestedRemotes();
            if (transitionInfo.getType() == 1 && requestedRemotes != null && requestedRemotes.get(iBinder) != null) {
                this.mIsLandScapeExitScene = false;
                TransitionLog.always("reset mIsLandScapeExitScene");
                return true;
            }
        }
        return false;
    }

    public void notifyTransitionMerged(IBinder iBinder, IBinder iBinder2, boolean z, WindowOrganizer windowOrganizer) {
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || iBinder == null || iBinder2 == null || !z) {
            return;
        }
        windowOrganizer.notifyTransitionMerged(iBinder, iBinder2);
    }

    public void notifyTransitionRemoveTask(IBinder iBinder, boolean z, IBinder iBinder2, TransitionInfo transitionInfo, int i, WindowOrganizer windowOrganizer) {
        if (!ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION || iBinder == null || iBinder2 == null || transitionInfo == null || !z) {
            return;
        }
        windowOrganizer.notifyTransitionRemoveTask(iBinder, iBinder2, i);
    }

    public void onBackAnimationAbort(TransitionInfo transitionInfo) {
        if (transitionInfo == null || this.mBackAnimHandler == null || transitionInfo.getType() != 13) {
            return;
        }
        Log.d(TAG, "onBackAnimationAbort, finish predictive back transition");
        this.mBackAnimHandler.onTransitionAbort();
    }

    public boolean partialMatchFilter(TransitionFilter transitionFilter, TransitionInfo transitionInfo) {
        TransitionFilter.Requirement[] requirementArr;
        if (transitionFilter == null || (requirementArr = transitionFilter.mRequirements) == null || requirementArr.length == 0 || transitionInfo == null || transitionInfo.getChanges().size() != 1 || ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_MATCH_REMOTE_FILTER, null, new Object[0])) != Boolean.TRUE) {
            return false;
        }
        try {
            for (TransitionFilter.Requirement requirement : transitionFilter.mRequirements) {
                if (requirement.mActivityType == 1 && ReflectionUtils.methodInvoke(requirement, "matches", new Class[]{TransitionInfo.class}, new Object[]{transitionInfo}) == Boolean.TRUE) {
                    return true;
                }
            }
        } catch (Throwable th) {
            Log.w(TAG, "partialMatchFilter error: " + th.getMessage());
        }
        return false;
    }

    public void passAnimStateToNextTransition(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, WindowContainerTransaction windowContainerTransaction) {
        if (!ShellContinuousTransitionUtils.ENABLE_FULL_SCENE_CONTINUOUS || transitionInfo2 == null || windowContainerTransaction == null || transitionInfo == null || ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TRANSITION_CONTINUOUS_TYPE, null, new Object[0])).intValue() != 3) {
            return;
        }
        Float f = (Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_RADIUS, null, new Object[0]);
        float fFloatValue = f.floatValue();
        Float f2 = (Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_ALPHA, null, new Object[0]);
        float fFloatValue2 = f2.floatValue();
        Float f3 = (Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_WEIGHT, null, new Object[0]);
        float fFloatValue3 = f3.floatValue();
        Float f4 = (Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_CONTENT_RADIUS, null, new Object[0]);
        float fFloatValue4 = f4.floatValue();
        Float f5 = (Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_SCALE_VALUE, null, new Object[0]);
        float fFloatValue5 = f5.floatValue();
        Rect rect = (Rect) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_WINDOW_CROP, null, new Object[0]);
        RectF rectF = (RectF) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_RECTF, null, new Object[0]);
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_REMOTE_INTERRUPT, null, new Object[0]);
        boolean zBooleanValue = bool.booleanValue();
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("passAnimStateToNextTransition, radius = ", fFloatValue, ", alpha = ", fFloatValue2, ", weight = ");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, fFloatValue3, ", contentRadius = ", fFloatValue4, ", scaleValue = ");
        sbM.append(fFloatValue5);
        sbM.append(", windowCrop = ");
        sbM.append(rect);
        sbM.append(", rectF = ");
        sbM.append(rectF);
        sbM.append(", remoteInterrupt = ");
        sbM.append(zBooleanValue);
        sbM.append(", readyInfo = ");
        sbM.append(transitionInfo2.hashCode());
        TransitionLog.always(sbM.toString());
        WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
        Class cls = Float.TYPE;
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_RADIUS, new Class[]{cls}, f);
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_ALPHA, new Class[]{cls}, f2);
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_WEIGHT, new Class[]{cls}, f3);
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_CONTENTRADIUS, new Class[]{cls}, f4);
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_SCALE_VALUE, new Class[]{cls}, f5);
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_WINDOW_CROP, new Class[]{Rect.class}, rect);
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_RECTF, new Class[]{RectF.class}, rectF);
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction2, SET_REMOTE_INTERRUPT, new Class[]{Boolean.TYPE}, bool);
        OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo2, SET_WCT, new Class[]{WindowContainerTransaction.class}, windowContainerTransaction2);
        OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo2, SET_TRANSITION_CONTINUOUS_TYPE, new Class[]{Integer.TYPE}, 3);
    }

    public void passExtraDataOnBundleIfNeed(TransitionInfo transitionInfo, Bundle bundle) {
        if (!ShellContinuousTransitionUtils.ENABLE_FULL_SCENE_CONTINUOUS || transitionInfo == null) {
            return;
        }
        try {
            int iIntValue = ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TRANSITION_CONTINUOUS_TYPE, null, new Object[0])).intValue();
            TransitionLog.always("extra data to recent, info = " + transitionInfo.hashCode());
            if (iIntValue == 3) {
                WindowContainerTransaction windowContainerTransaction = (WindowContainerTransaction) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_WCT, null, new Object[0]);
                if (windowContainerTransaction != null) {
                    TransitionLog.always("passExtraDataOnBundleIfNeed, radius = " + ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_RADIUS, null, new Object[0])).floatValue() + ", alpha = " + ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_ALPHA, null, new Object[0])).floatValue() + ", weight = " + ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_WEIGHT, null, new Object[0])).floatValue() + ", contentRadius = " + ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_CONTENT_RADIUS, null, new Object[0])).floatValue() + ", scaleValue = " + ((Float) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_SCALE_VALUE, null, new Object[0])).floatValue() + ", windowCrop = " + ((Rect) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_WINDOW_CROP, null, new Object[0])) + ", rectF = " + ((RectF) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_RECTF, null, new Object[0])) + ", remoteInterrupt = " + ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, GET_REMOTE_INTERRUPT, null, new Object[0])).booleanValue());
                }
                bundle.putParcelable(KEY_RECENTS_ANIM_BUNDLE_TRANSITION_INFO, transitionInfo);
            }
        } catch (Exception unused) {
            TransitionLog.always("passExtraDataOnBundleIfNeed error");
        }
    }

    public void prepareBackAnimContinueIfNeed(IBinder iBinder, RemoteAnimationTarget[] remoteAnimationTargetArr, TransitionInfo transitionInfo) {
        Transformation transformation;
        if (remoteAnimationTargetArr == null || remoteAnimationTargetArr.length <= 0) {
            return;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        float alpha = 0.0f;
        float alpha2 = 0.0f;
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            SpringSlideAnimationController.ContinueValue matchedContinueValue = AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController().getMatchedContinueValue(iBinder, change, true);
            if (!SpringSlideAnimationController.ENABLE_SPRING_SLIDE_ANIM || matchedContinueValue == null) {
                ArrayMap backAnimContinueTransformMap = Transitions.getBackAnimContinueTransformMap(iBinder);
                if (backAnimContinueTransformMap == null) {
                    return;
                }
                if (change.getMode() == 6) {
                    Transformation transformation2 = (Transformation) backAnimContinueTransformMap.get(1);
                    if (transformation2 != null) {
                        float[] fArr = new float[9];
                        transformation2.getMatrix().getValues(fArr);
                        f2 = fArr[2];
                        alpha2 = transformation2.getAlpha();
                    }
                } else if (TransitionUtil.isOpeningMode(change.getMode()) && (transformation = (Transformation) backAnimContinueTransformMap.get(0)) != null) {
                    float[] fArr2 = new float[9];
                    transformation.getMatrix().getValues(fArr2);
                    f = fArr2[2];
                    alpha = transformation.getAlpha();
                }
            } else if (change.getMode() == 6) {
                f2 = matchedContinueValue.posX;
                alpha2 = matchedContinueValue.alpha;
            } else if (TransitionUtil.isOpeningMode(change.getMode())) {
                f = matchedContinueValue.posX;
                alpha = matchedContinueValue.alpha;
            }
        }
        for (int length = remoteAnimationTargetArr.length - 1; length >= 0; length--) {
            StringBuilder sb = new StringBuilder("prepareBackAnimContinueIfNeed#, target=");
            sb.append(remoteAnimationTargetArr[length]);
            sb.append(", mode=");
            IconProvider$.ExternalSyntheticOutline0.m(f, remoteAnimationTargetArr[length].mode, ", openingContinuePosition=", ", closingContinuePosition=", sb);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f2, ", openingContinueAlpha=", alpha, ", closingContinueAlpha=");
            sb.append(alpha2);
            TransitionLog.always(sb.toString());
            RemoteAnimationTarget remoteAnimationTarget = remoteAnimationTargetArr[length];
            int i = remoteAnimationTarget.mode;
            if (i == 0) {
                Class cls = Float.TYPE;
                OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget, SET_BACK_ANIM_CONTINUE_POSITION, new Class[]{cls}, Float.valueOf(f));
                OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTargetArr[length], SET_BACK_ANIM_CONTINUE_ALPHA, new Class[]{cls}, Float.valueOf(alpha));
            } else if (i == 1) {
                Class cls2 = Float.TYPE;
                OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget, SET_BACK_ANIM_CONTINUE_POSITION, new Class[]{cls2}, Float.valueOf(f2));
                OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTargetArr[length], SET_BACK_ANIM_CONTINUE_ALPHA, new Class[]{cls2}, Float.valueOf(alpha2));
            }
        }
    }

    public void recordRecentFinishState(WindowContainerTransaction windowContainerTransaction, boolean z, long j, boolean z2) {
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Class cls = Boolean.TYPE;
            OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, SET_RECENT_FINISH_TO_HOME, new Class[]{cls}, Boolean.valueOf(z));
            OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, SET_RECENT_FINISH_SEQ, new Class[]{Long.TYPE}, Long.valueOf(j));
            OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, SET_RECENT_FINISH_INPUT_CONSUMER_ENABLED, new Class[]{cls}, Boolean.valueOf(z2));
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("recordRecentFinishState error : "), TAG);
        }
    }

    public void removeTransitionBackground(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        if (transitionInfo == null || transaction == null || this.mTransitionBgMap.isEmpty()) {
            return;
        }
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, NEED_KEEP_BG_FOR_CONTINUOUS, null, new Object[0]);
        if ((bool == null || !bool.booleanValue()) && this.mTransitionBgMap.get(Integer.valueOf(transitionInfo.getTrack())) != null) {
            for (SurfaceControl surfaceControl : this.mTransitionBgMap.remove(Integer.valueOf(transitionInfo.getTrack()))) {
                if (surfaceControl != null && surfaceControl.isValid()) {
                    TransitionLog.always("removeTransitionBackground removing background " + surfaceControl);
                    transaction.remove(surfaceControl);
                }
            }
        }
    }

    public void reorderHomeWhenFinish(int i, WindowContainerToken windowContainerToken, int i2, WindowContainerTransaction windowContainerTransaction) {
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            setRecentFinishToHome(true);
            if (i != 1 || windowContainerToken == null || nextWillCloseLauncher(i2)) {
                return;
            }
            TransitionLog.always("recents reorderHomeWhenFinish");
            windowContainerTransaction.reorder(windowContainerToken, true);
            markReorderHome();
        }
    }

    public void reparentTaskSurface(TransitionInfo transitionInfo, RemoteAnimationTarget remoteAnimationTarget, SurfaceControl.Transaction transaction) {
        if (transitionInfo == null || remoteAnimationTarget == null || transaction == null) {
            return;
        }
        SurfaceControl surfaceControl = (SurfaceControl) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_REPARENT_TO_RECENTS_SURFACE, null, new Object[0]);
        if (remoteAnimationTarget.leash == null || surfaceControl == null) {
            return;
        }
        OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget, SET_INTERMEDIATE_TASK_LEASH, new Class[]{SurfaceControl.class}, surfaceControl);
        transaction.reparent(surfaceControl, remoteAnimationTarget.leash);
        TransitionLog.always("reparent " + surfaceControl + " to " + remoteAnimationTarget.leash + " for when recents request move collecting to waiting");
    }

    public void resetBackTransitionHandler(Transitions.ActiveTransition activeTransition) {
        TransitionInfo transitionInfo;
        if (this.mBackAnimHandler == null || activeTransition == null || (transitionInfo = activeTransition.mInfo) == null || transitionInfo.getType() != 4 || !activeTransition.mInfo.getChanges().isEmpty()) {
            return;
        }
        Log.d(TAG, " resetBackTransitionHandler for ready " + activeTransition + " if need");
        this.mBackAnimHandler.resetCloseTransitionRequestedIfNeed();
    }

    public void resetRemoteContinuous() {
        if (isRemoteToRecentContinuous()) {
            setRemoteToRecentContinuous(false);
        }
    }

    public void resetRemoteToRemoteContinuous() {
        if (isRemoteToRemoteContinuous()) {
            setRemoteToBackAnimContinuous(false);
        }
    }

    public void resetSurfacePositionForAnimationLeash(SurfaceControl.Transaction transaction, TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return;
        }
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            SurfaceControl leash = change.getLeash();
            if (leash != null && leash.isValid() && change.hasFlags(131072)) {
                transaction.setPosition(leash, 0.0f, 0.0f);
                TransitionLog.always("resetSurfacePositionForAnimationLeash for: " + leash);
                return;
            }
        }
    }

    public void restoreCompactActivityScaleIfNeeded(Transitions.ActiveTransition activeTransition, Transitions.ActiveTransition activeTransition2) {
        TransitionInfo transitionInfo;
        if (((activeTransition.mHandler instanceof RecentsTransitionHandler) || this.mTransitions.isRemoteOpenRequestedFrom(activeTransition.mToken, "QuickstepLaunch")) && (transitionInfo = activeTransition2.mInfo) != null && transitionInfo.getType() == 1 && OplusActivityEmbeddingAnimationRunner.getInstance().isCompactWindowMode(activeTransition2.mInfo)) {
            List changes = activeTransition2.mInfo.getChanges();
            for (int i = 0; i < changes.size(); i++) {
                TransitionInfo.Change change = (TransitionInfo.Change) changes.get(i);
                if (change.getActivityComponent() != null && change.getEndAbsBounds().left == 0 && change.getEndAbsBounds().top == 0) {
                    SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                    if (change.getLeash() != null && change.getLeash().isValid()) {
                        float f = OplusEmbedReflectionUtils.TransitionInfo_Change_PriBundle(change).getFloat("tf_compact_scale", 1.0f);
                        StringBuilder sb = new StringBuilder("revert compact scale ");
                        float f2 = 1.0f / f;
                        sb.append(f2);
                        sb.append(" for restoring ");
                        sb.append(change.getLeash());
                        TransitionLog.always(sb.toString());
                        transaction.setMatrix(change.getLeash(), f2, 0.0f, 0.0f, f2);
                        transaction.apply();
                    }
                }
            }
        }
    }

    public void saveAnimationToAnimator(ValueAnimator valueAnimator, Animation animation) {
        if (valueAnimator != null) {
            OplusTransitionReflectionHelper.methodInvokeOnExt(valueAnimator, METHOD_SET_ANIMATION, new Class[]{Animation.class}, animation);
        }
    }

    public void saveIgnoreUpdateToAnimator(ValueAnimator valueAnimator, boolean z) {
        if (valueAnimator != null) {
            OplusTransitionReflectionHelper.methodInvokeOnExt(valueAnimator, METHOD_SET_IGNORE_ANIMATOR_UPDATE, new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void saveModeToAnimator(ValueAnimator valueAnimator, int i) {
        if (valueAnimator != null) {
            OplusTransitionReflectionHelper.methodInvokeOnExt(valueAnimator, METHOD_SET_TRANSITION_MODE, new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void sendRecentsController(IBinder iBinder, RecentsTransitionHandler recentsTransitionHandler, TransitionInfo transitionInfo) {
        RecentsTransitionHandler.RecentsController recentController;
        if (ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION && recentsTransitionHandler != null && (recentController = recentsTransitionHandler.getRecentController(iBinder)) != null && supportRecentTransition(transitionInfo)) {
            setRecentsController(transitionInfo, recentController);
            TransitionLog.debug("sendRecentsController, info:" + transitionInfo);
        }
    }

    public void setActivityOrientationForRemoteAnimationTargets(RemoteAnimationTarget[] remoteAnimationTargetArr, TransitionInfo transitionInfo) {
        TransitionInfo.Change change;
        ActivityManager.RunningTaskInfo taskInfo;
        if (remoteAnimationTargetArr == null || transitionInfo == null) {
            TransitionLog.always("setStartActivityRotation invalid appearedTargets: " + remoteAnimationTargetArr + ", TransitionInfo: " + transitionInfo);
            return;
        }
        if (transitionInfo.getChanges() == null) {
            change = null;
            taskInfo = null;
            break;
        }
        int i = 0;
        while (true) {
            if (i >= transitionInfo.getChanges().size()) {
                change = null;
                taskInfo = null;
                break;
            }
            change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (change != null && !FlexibleTaskTransitionUtils.isFlexibleTaskChange(change) && (taskInfo = change.getTaskInfo()) != null && taskInfo.topActivityType == 1) {
                TransitionLog.always("setStartActivityRotation find target startChange: " + change.getLeash() + ", taskInfo: " + taskInfo + ", endRotation: " + change.getEndRotation() + ", endFixedRotation: " + change.getEndFixedRotation());
                break;
            }
            i++;
        }
        if (taskInfo == null || change == null) {
            TransitionLog.always("setStartActivityRotation no target found");
            return;
        }
        Integer num = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_PRE_READY_ACTIVITY_ROTATION, null, new Object[0]);
        int iIntValue = num != null ? num.intValue() : -1;
        for (RemoteAnimationTarget remoteAnimationTarget : remoteAnimationTargetArr) {
            if (remoteAnimationTarget != null && taskInfo.taskId == remoteAnimationTarget.taskId) {
                int endFixedRotation = change.getEndFixedRotation() != -1 ? change.getEndFixedRotation() : iIntValue != -1 ? iIntValue : change.getEndRotation();
                TransitionLog.always("setStartActivityRotation success for RemoteAnimationTarget: " + remoteAnimationTarget + " ,endRotation" + endFixedRotation);
                OplusTransitionReflectionHelper.methodInvokeOnExt(remoteAnimationTarget, SET_APPEARED_ACTIVITY_ROTATION, new Class[]{Integer.TYPE}, Integer.valueOf(endFixedRotation));
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0032 A[EDGE_INSN: B:18:0x0032->B:19:0x0033 BREAK  A[LOOP:0: B:13:0x0024->B:17:0x002f]] */
    public void setBackAnimClosingLeash(TransitionInfo transitionInfo) {
        SurfaceControl surfaceControl;
        RemoteAnimationTarget[] remoteAnimationTargets;
        if (transitionInfo == null) {
            return;
        }
        Transitions.ActiveTransition activeTransitionNextReadyTransition = this.mTransitions.nextReadyTransition(transitionInfo.getTrack());
        if (activeTransitionNextReadyTransition == null) {
            surfaceControl = null;
            break;
        }
        BackAnimationController.BackTransitionHandler backTransitionHandler = activeTransitionNextReadyTransition.mHandler;
        if (!(backTransitionHandler instanceof BackAnimationController.BackTransitionHandler) || (remoteAnimationTargets = backTransitionHandler.getRemoteAnimationTargets()) == null || remoteAnimationTargets.length <= 1) {
            surfaceControl = null;
            break;
        }
        int length = remoteAnimationTargets.length - 1;
        while (true) {
            if (length < 0) {
                surfaceControl = null;
                break;
            }
            RemoteAnimationTarget remoteAnimationTarget = remoteAnimationTargets[length];
            if (remoteAnimationTarget.mode == 1) {
                surfaceControl = remoteAnimationTarget.leash;
                break;
            }
            length--;
        }
        if (surfaceControl == null) {
            return;
        }
        boolean z = false;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getMode() == 3 && change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) {
                TransitionLog.debug("setBackAnimClosingLeash: isHomeToFront");
                z = true;
            }
        }
        if (z) {
            SharedReflectionHelper.setBackAnimClosingLeash(transitionInfo, surfaceControl);
        }
    }

    public void setBackAnimContinuous(WindowContainerTransaction windowContainerTransaction, boolean z) {
        if (windowContainerTransaction == null) {
            return;
        }
        OplusTransitionReflectionHelper.methodInvokeOnExt(windowContainerTransaction, SET_BACK_ANIM_CONTINUOUS, new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
    }

    public void setBackAnimTransitionHandler(BackAnimationController.BackTransitionHandler backTransitionHandler) {
        this.mBackAnimHandler = backTransitionHandler;
    }

    public void setBackPredictiveToRemoteContinuous(boolean z) {
        this.mBackPredictiveToRemoteContinuous = z;
    }

    public void setDefaultContinuous(boolean z) {
        this.mDefaultContinuous = z;
    }

    public void setRecentFinishToHome(boolean z) {
        this.mRecentFinishToHome = z;
    }

    public void setRemoteToBackAnimContinuous(boolean z) {
        this.mRemoteToRemoteContinuous = z;
        TransitionUtil.setRemoteContinuous(z);
    }

    public void setRemoteTransitionToInfo(IBinder iBinder, RemoteTransitionHandler remoteTransitionHandler, TransitionInfo transitionInfo) {
        RemoteTransition remoteTransition;
        if (!((ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION && ShellContinuousTransitionUtils.ENABLE_REMOTE_TO_REMOTE) || ShellContinuousTransitionUtils.ENABLE_PREDICTIVE_CONTINUOUS_REMOTE) || iBinder == null || remoteTransitionHandler == null || transitionInfo == null) {
            return;
        }
        ArrayMap requestedRemotes = remoteTransitionHandler.getRequestedRemotes();
        RemoteTransition remoteTransition2 = null;
        if (!requestedRemotes.isEmpty() && (remoteTransition = (RemoteTransition) requestedRemotes.get(iBinder)) != null && "QuickstepLaunch".equals(remoteTransition.getDebugName())) {
            remoteTransition2 = remoteTransition;
        }
        if (remoteTransition2 == null) {
            ArrayList filters = remoteTransitionHandler.getFilters();
            boolean zIsWallpaperTransitionIntraClose = isWallpaperTransitionIntraClose(transitionInfo);
            boolean zMatchRemoteFilter = matchRemoteFilter(transitionInfo);
            boolean zIsCustomFilter = isCustomFilter(transitionInfo);
            for (int size = filters.size() - 1; size >= 0; size--) {
                if (((((TransitionFilter) ((Pair) filters.get(size)).first).matches(transitionInfo) && !zIsWallpaperTransitionIntraClose) || zMatchRemoteFilter) && !zIsCustomFilter) {
                    TransitionLog.debug("Found filter" + filters.get(size));
                    remoteTransition2 = (RemoteTransition) ((Pair) filters.get(size)).second;
                }
            }
        }
        if (remoteTransition2 != null) {
            OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, "setRemoteTransition", new Class[]{IBinder.class}, remoteTransition2.asBinder());
            this.mRemoteMergeToRemote = transitionInfo.getDebugId();
            TransitionLog.always("set pendingRemote: " + remoteTransition2 + ", mRemoteMergeToRemote: " + this.mRemoteMergeToRemote + "\n" + transitionInfo);
        }
    }

    public void setRemoteTransitionToken(IBinder iBinder) {
        this.mRemoteTransitionToken = iBinder;
    }

    public void setShellApplyToken(TransitionInfo transitionInfo, IBinder iBinder) {
        if (transitionInfo == null || ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, "getIsFromHome", null, new Object[0])).booleanValue()) {
            return;
        }
        OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_SHELL_APPLY_TOKEN, new Class[]{IBinder.class}, iBinder);
    }

    public void setTaskLeashForRemoteAnimationTargets(RemoteAnimationTarget[] remoteAnimationTargetArr, TransitionInfo transitionInfo) {
        TransitionInfo.Change change;
        ActivityManager.RunningTaskInfo taskInfo;
        if (remoteAnimationTargetArr == null || transitionInfo == null) {
            TransitionLog.always("setTaskLeash invalid appearedTargets: " + remoteAnimationTargetArr + ", openTransitionInfo: " + transitionInfo);
            return;
        }
        if (transitionInfo.getChanges() == null) {
            change = null;
            taskInfo = null;
            break;
        }
        int i = 0;
        while (true) {
            if (i >= transitionInfo.getChanges().size()) {
                change = null;
                taskInfo = null;
                break;
            }
            change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (change != null && (taskInfo = change.getTaskInfo()) != null && taskInfo.topActivityType == 1) {
                TransitionLog.always("setTaskLeash find target predictiveChange: " + change + ", taskInfo: " + taskInfo);
                break;
            }
            i++;
        }
        if (taskInfo == null || change == null) {
            TransitionLog.always("setTaskLeash no target found");
            return;
        }
        if (remoteAnimationTargetArr.length == 1 && remoteAnimationTargetArr[0] == null) {
            ActivityManager.RunningTaskInfo runningTaskInfo = taskInfo;
            RemoteAnimationTarget remoteAnimationTarget = new RemoteAnimationTarget(taskInfo.taskId, -1, (SurfaceControl) null, false, (Rect) null, (Rect) null, -1, (Point) null, (Rect) null, (Rect) null, taskInfo.configuration.windowConfiguration, false, (SurfaceControl) null, (Rect) null, runningTaskInfo, false);
            taskInfo = runningTaskInfo;
            remoteAnimationTargetArr[0] = remoteAnimationTarget;
            TransitionLog.always("setTaskLeash construct open RemoteAnimationTarget");
        }
        for (RemoteAnimationTarget remoteAnimationTarget2 : remoteAnimationTargetArr) {
            if (remoteAnimationTarget2 != null && taskInfo.taskId == remoteAnimationTarget2.taskId) {
                SharedReflectionHelper.setRemoteTaskLeash(remoteAnimationTarget2, change.getLeash());
                SharedReflectionHelper.setIsPredictiveBackAnimation(remoteAnimationTarget2, true);
                TransitionLog.always("setTaskLeash success for RemoteAnimationTarget: " + remoteAnimationTarget2);
                return;
            }
        }
    }

    public void setTriggerPreBootLimitSize(int i, WindowOrganizer windowOrganizer, IBinder iBinder) {
        if (i < 0) {
            return;
        }
        windowOrganizer.setTriggerPreBootLimitSize(i, iBinder);
    }

    public void setWallPaperChangeRemoved(TransitionInfo.Change change, TransitionInfo transitionInfo) {
        if (change == null || transitionInfo == null) {
            return;
        }
        if (!((change.getFlags() & 1) == 0 && (change.getFlags() & 2) == 0) && TransitionUtil.isClosingType(change.getMode())) {
            OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, SET_CLOSE_WALLPAPER_REMOVED, new Class[]{Boolean.TYPE}, Boolean.TRUE);
        }
    }

    public boolean shouldApplyStartTInAdvance(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, SurfaceControl.Transaction transaction) {
        return OplusActivityEmbeddingAnimationRunner.getInstance().shouldApplyStartTInAdvance(transitionInfo, transitionInfo2, transaction);
    }

    public boolean shouldCancelAnimationForDefaultOpen(TransitionInfo transitionInfo, BackNavigationInfo backNavigationInfo) {
        if (backNavigationInfo != null && backNavigationInfo.getType() == 1 && transitionInfo != null && TransitionUtil.isOpeningType(transitionInfo.getType())) {
            ArrayList playingTransitions = this.mTransitions.getPlayingTransitions();
            int i = -1;
            for (int i2 = 0; i2 < playingTransitions.size(); i2++) {
                Transitions.ActiveTransition activeTransition = (Transitions.ActiveTransition) playingTransitions.get(i2);
                if (activeTransition.mInfo.getType() == 13) {
                    for (int i3 = 0; i3 < activeTransition.mInfo.getChanges().size(); i3++) {
                        TransitionInfo.Change change = (TransitionInfo.Change) activeTransition.mInfo.getChanges().get(i3);
                        if (change.hasFlags(131072) && change.getMode() == 6 && change.getTaskInfo() != null) {
                            i = change.getTaskInfo().taskId;
                            break;
                        }
                    }
                }
            }
            if (i == -1) {
                return false;
            }
            for (int i4 = 0; i4 < transitionInfo.getChanges().size(); i4++) {
                TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i4);
                if (change2.hasFlags(131072) && change2.getMode() == 3 && change2.getTaskInfo() != null && change2.getTaskInfo().getActivityType() != 2) {
                    int i5 = change2.getTaskInfo().taskId;
                    Slog.d(TAG, " shouldCancelAnimationForDefaultOpen backTaskId: " + i + " openTaskId: " + i5);
                    return i == i5;
                }
            }
        }
        return false;
    }

    public boolean shouldMergeAnimationForPredictive(TransitionInfo transitionInfo, BackNavigationInfo backNavigationInfo, boolean z) {
        if (ShellContinuousTransitionUtils.ENABLE_PREDICTIVE_CONTINUOUS_REMOTE && !ShellContinuousTransitionUtils.isLaunchUnityStartOnly(null, transitionInfo)) {
            return (transitionInfo.getType() == 1 || transitionInfo.getType() == 3) && backNavigationInfo != null && backNavigationInfo.getType() == 1 && z;
        }
        return false;
    }

    public boolean shouldMergeToRemote(TransitionInfo transitionInfo, Transitions transitions) {
        ComponentName componentName;
        if (TransitionAnimationUtil.isTabletDevice() && transitionInfo != null && !ShellContinuousTransitionUtils.ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION) {
            Transitions.ActiveTransition activeTransition = transitions.getActiveTransition(transitionInfo.getTrack());
            TransitionInfo transitionInfo2 = activeTransition != null ? activeTransition.mInfo : null;
            if (transitionInfo2 != null) {
                Iterator it = transitionInfo2.getChanges().iterator();
                while (it.hasNext()) {
                    ActivityManager.RunningTaskInfo taskInfo = ((TransitionInfo.Change) it.next()).getTaskInfo();
                    if (taskInfo != null && (componentName = taskInfo.realActivity) != null && SETTINGS_PKG.equals(componentName.getPackageName())) {
                        Iterator it2 = transitionInfo.getChanges().iterator();
                        while (it2.hasNext()) {
                            if (((TransitionInfo.Change) it2.next()).hasFlags(OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION)) {
                                TransitionLog.debug("activeTransiton  " + activeTransition + " has setting, don't merge " + transitionInfo);
                                return true;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean shouldShowOpeningSurface(TransitionInfo.Change change, TransitionInfo transitionInfo) {
        boolean zTransitionInPreReadyRemote = AdaptiveSmoothShellAnimManager.getInstance().transitionInPreReadyRemote(transitionInfo);
        if (change.getTaskInfo() == null || !zTransitionInPreReadyRemote) {
            return true;
        }
        TransitionLog.always("ShellContinuousTransitionController transition " + transitionInfo.getDebugId() + " in preready, startT don't show change " + change + " on setupStartState");
        return false;
    }

    public boolean shouldSkipMergeForCanvasTransition(TransitionInfo transitionInfo) {
        Transitions.ActiveTransition activeTransition;
        ArrayList arrayList;
        int i;
        TransitionInfo transitionInfo2;
        if (transitionInfo != null && transitionInfo.getType() == 3 && (activeTransition = this.mTransitions.getActiveTransition(transitionInfo.getTrack())) != null && activeTransition.mInfo != null && (arrayList = activeTransition.mMerged) != null && !arrayList.isEmpty()) {
            Transitions.ActiveTransition activeTransition2 = (Transitions.ActiveTransition) activeTransition.mMerged.getLast();
            if (activeTransition2 == null || (transitionInfo2 = activeTransition2.mInfo) == null || transitionInfo2.getType() != 6) {
                i = 0;
            } else {
                i = 0;
                for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(activeTransition2.mInfo, 1); iM >= 0; iM--) {
                    TransitionInfo.Change change = (TransitionInfo.Change) activeTransition2.mInfo.getChanges().get(iM);
                    if (change != null && change.getTaskFragmentToken() != null && change.getMode() == 6) {
                        i++;
                    }
                }
            }
            boolean z = false;
            for (int iM2 = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM2 >= 0; iM2--) {
                TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(iM2);
                if (change2 != null) {
                    ActivityManager.RunningTaskInfo taskInfo = change2.getTaskInfo();
                    int scenario = taskInfo != null ? TransitionAnimationUtil.getScenario(taskInfo.getConfiguration()) : 0;
                    if (change2.getMode() == 2 && taskInfo != null && scenario == 1 && isCanvasTask(taskInfo)) {
                        z = true;
                    }
                }
            }
            TransitionLog.always("shouldSkipMergeForCanvasTransition isFlexibleCanvasInTransition=" + z + " isTaskFragmentChangeSize=" + i);
            if (z && i == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean shouldSkipMergeForEncryption(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        ComponentName activityComponent;
        ComponentName activityComponent2;
        if (transitionInfo != null && transitionInfo.getType() == 1 && transitionInfo2 != null && transitionInfo2.getType() == 1) {
            List changes = transitionInfo.getChanges();
            for (int size = changes.size() - 1; size >= 0; size--) {
                TransitionInfo.Change change = (TransitionInfo.Change) changes.get(size);
                if (change.getMode() == 1 && (activityComponent = change.getActivityComponent()) != null && ENCRYPTION_MAIN_ACTIVITY.contains(activityComponent)) {
                    List changes2 = transitionInfo2.getChanges();
                    for (int size2 = changes2.size() - 1; size2 >= 0; size2--) {
                        TransitionInfo.Change change2 = (TransitionInfo.Change) changes2.get(size2);
                        if (change2.getMode() == 1 && (activityComponent2 = change2.getActivityComponent()) != null && ENCRYPTION_NEXT_ACTIVITY.contains(activityComponent2)) {
                            TransitionLog.always("skip merge: activeInfo contains EncryptionMainActivity OPEN anim");
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public boolean shouldSkipMergePendingTransitions(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        TransitionInfo transitionInfo3;
        if (transitionInfo != null && transitionInfo2 != null) {
            boolean zIsTaskLevelOnly = isTaskLevelOnly(transitionInfo);
            boolean zIsActivityLevelOnly = DefaultTransitionHandler.isActivityLevelOnly(transitionInfo);
            boolean zIsTaskLevelOnly2 = isTaskLevelOnly(transitionInfo2);
            boolean zIsActivityLevelOnly2 = DefaultTransitionHandler.isActivityLevelOnly(transitionInfo2);
            if (zIsTaskLevelOnly && zIsActivityLevelOnly2) {
                return true;
            }
            if (zIsActivityLevelOnly && zIsTaskLevelOnly2) {
                return true;
            }
            Transitions.ActiveTransition activeTransitionNextReadyTransition = this.mTransitions.nextReadyTransition(transitionInfo2.getTrack());
            if (activeTransitionNextReadyTransition != null && (transitionInfo3 = activeTransitionNextReadyTransition.mInfo) != null && transitionInfo3.getDebugId() == transitionInfo.getDebugId() && (isRemoteTransition(activeTransitionNextReadyTransition.mToken, activeTransitionNextReadyTransition.mInfo) || (activeTransitionNextReadyTransition.mHandler instanceof RecentsTransitionHandler) || ShellContinuousTransitionUtils.isKeyguardAppearTransition(transitionInfo))) {
                Slog.d(TAG, " shouldSkipMergePendingTransitions for recent or remote transition");
                return true;
            }
        }
        return false;
    }

    public boolean shouldSkipRemoteTransition(IBinder iBinder, TransitionInfo transitionInfo, RemoteTransition remoteTransition) {
        if (remoteTransition != null) {
            if (shouldSkipRemoteAnim(transitionInfo)) {
                return true;
            }
            Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_SKIP_REMOTE_TRANSITION, null, new Object[0]);
            if (bool != null && bool.booleanValue()) {
                try {
                    remoteTransition.getRemoteTransition().onTransitionConsumed(iBinder, true);
                    TransitionLog.debug("shouldSkipRemoteTransition: " + iBinder);
                    return true;
                } catch (Exception e) {
                    TransitionLog.always("shouldSkipRemoteTransition Error is: " + e);
                }
            }
        }
        return false;
    }

    public boolean shouldSkipRemoveChangeForBackAnimation(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        if (!TransitionUtil.isClosingType(transitionInfo.getType()) || change.getTaskInfo() == null || change.getTaskInfo().topActivity == null || !ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change.getTaskInfo().topActivity.flattenToShortString())) {
            return false;
        }
        if (transitionInfo.findRootIndex(change.getEndDisplayId() >= 0 ? change.getEndDisplayId() : change.getStartDisplayId()) < 0) {
            TransitionLog.always("When the virtual screen closes without root access to the home screen, the launcher can be removed from the transition." + change);
            return false;
        }
        TransitionLog.always(" shouldSkipRemoveChangeForBackAnimation for change " + change);
        return true;
    }

    public void showLeashOnFinishIfNeed(SurfaceControl.Transaction transaction, TransitionInfo.Change change) {
        if (change.getLeash() != null && change.getTaskInfo() != null && change.getTaskInfo().topActivity != null && change.hasFlags(TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) && change.getTaskInfo().getActivityType() == 2 && change.getLeash().isValid() && ShellContinuousTransitionUtils.LAUNCHER_CMP.equals(change.getTaskInfo().topActivity.flattenToShortString())) {
            TransitionLog.debug(" showLeashOnFinishIfNeed  leash = " + change.getLeash());
            transaction.show(change.getLeash());
        }
    }

    public boolean showOpenTaskInFinishIfNeeded(int i, WindowContainerToken windowContainerToken, IBinder iBinder) {
        Transitions.ActiveTransition activeTransitionNextReadyTransition;
        int i2 = 1;
        if (windowContainerToken != null && iBinder != null && (activeTransitionNextReadyTransition = this.mTransitions.nextReadyTransition(i)) != null && activeTransitionNextReadyTransition.mInfo != null && activeTransitionNextReadyTransition.mToken.equals(iBinder)) {
            boolean zTransitionInPreReadyRemote = AdaptiveSmoothShellAnimManager.getInstance().transitionInPreReadyRemote(activeTransitionNextReadyTransition.mInfo);
            boolean zIsRemoteOpenRequestedFrom = this.mTransitions.isRemoteOpenRequestedFrom(activeTransitionNextReadyTransition.mToken, "QuickstepLaunch");
            boolean zIsRemoteOpenRequestedFrom2 = this.mTransitions.isRemoteOpenRequestedFrom(activeTransitionNextReadyTransition.mToken, SPRUCE_DEBUG_NAME);
            TransitionInfo transitionInfo = activeTransitionNextReadyTransition.mInfo;
            if ((zIsRemoteOpenRequestedFrom || zIsRemoteOpenRequestedFrom2) && !zTransitionInPreReadyRemote) {
                Stream stream = transitionInfo.getChanges().stream();
                ShellContinuousTransitionController$$ExternalSyntheticLambda0 shellContinuousTransitionController$$ExternalSyntheticLambda0 = new ShellContinuousTransitionController$$ExternalSyntheticLambda0(i2);
                shellContinuousTransitionController$$ExternalSyntheticLambda0.f$0 = windowContainerToken;
                VarHandle.storeStoreFence();
                if (stream.anyMatch(shellContinuousTransitionController$$ExternalSyntheticLambda0)) {
                    TransitionLog.always("readyTransiton: " + activeTransitionNextReadyTransition + " don't merge into recents, but task:" + windowContainerToken + " add to openingtask, so don't show it when recents finish");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean skipDefaultTransitionIfNeed(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        if (change.getMode() == 6 && (change.getFlags() & 1) != 0 && change.getAnimationOptions() != null) {
            for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
                TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
                if (change2.getMode() != 6 && change2.hasFlags(4) && TransitionUtil.isClosingType(change2.getMode())) {
                    TransitionLog.debug("skip TRANSIT_CHANGE and FLAG_SHOW_WALLPAPER animation");
                    return true;
                }
            }
        }
        if (change.getTaskInfo() != null || change.getMode() != 2 || !change.hasFlags(4) || change.getActivityComponent() == null || !SKIP_DEFAULT_TRANSITION_COMPONENT.contains(change.getActivityComponent().flattenToString())) {
            return false;
        }
        TransitionLog.always("skip default transition change: " + change + " for anim layer: Right Edge Extension");
        return true;
    }

    public boolean skipHideLauncher(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, TransitionInfo.Change change) {
        if (transitionInfo == null || transitionInfo2 == null || change == null || transitionInfo.getDebugId() >= transitionInfo2.getDebugId() || change.getTaskInfo() != null || change.getLeash() == null || !ActivityEmbeddingController$.ExternalSyntheticOutline0.m(change, ShellContinuousTransitionUtils.LAUNCHER_CMP)) {
            return false;
        }
        Slog.i(TAG, "skipHideLauncher when mergeActivityOnly, change=" + change);
        return true;
    }

    public boolean skipMergeIntoRecentsIfNeed(TransitionInfo transitionInfo, IBinder iBinder, WindowContainerToken windowContainerToken, RecentsTransitionHandler.RecentsController recentsController) {
        if (windowContainerToken == null) {
            recentsController.cancel("cancel_by_continuous");
            TransitionLog.always("ShellContinuousTransitionController skip merge into recents, when recentsTask is null");
            return true;
        }
        if (this.mTransitions.isRemoteOpenRequestedFrom(iBinder, "SysUIPluginLaunch") || this.mTransitions.isRemoteOpenRequestedFrom(iBinder, ShellContinuousTransitionUtils.SYSUI_DEBUG_NAME)) {
            TransitionLog.always("ShellContinuousTransitionController skip merge into recents, when remote open debugname is SysUIPluginLaunch or SysUILaunch");
            return true;
        }
        if (skipMergeIntoRecentsForCanvas(transitionInfo, recentsController)) {
            recentsController.cancel("cancel_by_canvas");
            TransitionLog.always("ShellContinuousTransitionController skip merge into recents, when canvas switch");
            return true;
        }
        if (skipMergeIntoRecentsForEmbedded(transitionInfo)) {
            TransitionLog.always("ShellContinuousTransitionController skip merge into recents, when activity embedded open");
            return true;
        }
        if (skipMergeIntoRecentsForEmbeddedInTable(transitionInfo)) {
            recentsController.cancel("cancel_by_embedded");
            TransitionLog.always("ShellContinuousTransitionController skip merge into recents, when activity embedded open in Table");
            return true;
        }
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_LAUNCH_FROM_DRIVE, null, new Object[0]);
        if (bool != null && bool.booleanValue()) {
            TransitionLog.always("ShellContinuousTransitionController skip merge into recents, when drive activity open from flexible window.");
            return true;
        }
        if (skipMergeIntoRecentsForStkStart(transitionInfo)) {
            recentsController.cancel("cancel_by_dialog");
            TransitionLog.always("ShellContinuousTransitionController skip merge into recents, when stk dialog activity open!");
            return true;
        }
        if (!FlexibleTaskTransitionUtils.flexibleFullScreenChange(transitionInfo)) {
            return false;
        }
        recentsController.cancel("cancel_by_flexible");
        TransitionLog.always("ShellContinuousTransitionController Skip merging into recents due to flexible fullscreen transition");
        return true;
    }

    public void updateMergeRemoteTaskVisible(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, SurfaceControl.Transaction transaction) {
        if (transaction == null || transitionInfo2 == null) {
            return;
        }
        hideTaskForMultiTask(transitionInfo, transitionInfo2, transaction);
        if (isRecentFinishToHome()) {
            if (transitionInfo2.getType() == 1 || transitionInfo2.getType() == 3 || transitionInfo2.getType() == 2) {
                TransitionLog.always("not apply merged finishT, merged=" + transitionInfo2.getDebugId());
                Transitions.ActiveTransition activeTransitionNextReadyTransition = this.mTransitions.nextReadyTransition(transitionInfo2.getTrack());
                boolean z = activeTransitionNextReadyTransition != null && AdaptiveSmoothShellAnimManager.getInstance().transitionInPreReadyRemote(activeTransitionNextReadyTransition.mInfo);
                for (TransitionInfo.Change change : transitionInfo2.getChanges()) {
                    if (change.getEndDisplayId() < OPLUS_MIRAGE_DISPLAY && change.getEndDisplayId() != OPLUS_MIRAGE_TVDISPLAY && change.getLeash() != null && change.getLeash().isValid()) {
                        if ((TransitionUtil.isOpeningType(change.getMode()) || (change.getMode() == 6 && (change.getFlags() & TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0)) && (((change.getTaskInfo() != null && change.getTaskInfo().getActivityType() != 2) || misMatchActiveTask(transitionInfo, transitionInfo2, change)) && !OplusPanoramaWorkBranchAnimController.getInstance().isFlexibleTaskAndHasCaption(change.getTaskInfo()) && (!z || !isChangeInTransition(activeTransitionNextReadyTransition.mInfo, change)))) {
                            transaction.hide(change.getLeash());
                        }
                        boolean z2 = (change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) || (change.getActivityComponent() != null && "com.android.launcher".equals(change.getActivityComponent().getPackageName()));
                        if (TransitionUtil.isClosingType(change.getMode()) && z2) {
                            transaction.show(change.getLeash());
                        }
                    }
                }
            }
        }
    }

    public void updateRemoteTransitionState(IBinder iBinder, int i) {
        if (i < 0 || i > 3) {
            return;
        }
        if (i == 3) {
            this.mRemoteTransitionStateMap.remove(iBinder);
        }
        Integer num = this.mRemoteTransitionStateMap.get(iBinder);
        if (num == null || num.intValue() < i) {
            this.mRemoteTransitionStateMap.put(iBinder, Integer.valueOf(i));
        }
    }
}
