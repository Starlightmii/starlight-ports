package com.oplusos.systemui.aod.mediapanel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener$getResumeAction$1$;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplus.systemui.keyguard.helper.ScreenSizeHelper;
import com.oplus.systemui.notification.mediapanel.AodMediaPromptController;
import com.oplus.systemui.notification.mediapanel.aon.IAodMediaAonController;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplus.systemui.notification.mediapanel.widget.AodMediaPanelView;
import com.oplusos.systemui.common.util.ReflectionUtils;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaController {
    public static final Companion Companion = new Companion();
    private static final String KEY_ABORT_FLASK = "key_abort_flask";
    private static final String KEY_GOTO_EXPANDED = "key_goto_expanded";
    private static final String KEY_MEDIA_SHOWN = "key_media_shown";
    private static final String TAG = "AodMediaController";
    private final BaseAodClockLayoutController aodController;
    private Object aodManager;
    private final AodMediaPromptController aodMediaPromptController;
    private final AodMediaViewStateController aodMediaViewStateController;
    private MediaData boundMediaData;
    public final Context context;
    private final expandedAnimaListener.1 expandedAnimaListener;
    private String handlingKey;
    private final Runnable operateTimeoutAction;
    private AodMediaControlPanel player;
    private final Runnable requestAodCollapsedTimeoutAction;
    private final Runnable requestAodExpandedFromClickAction;
    private final Runnable requestAodExpandedTimeoutAction;
    private Integer screenWidth;
    private final Context srcContext;
    private final AodMediaPanelView.OnVisibilityAggregatedListener visibilityAggregatedListener;
    public final IAodMediaAonController aodMediaAonController = (IAodMediaAonController) DependencyEx.sDependency.getDependency(IAodMediaAonController.class);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public AodMediaController(BaseAodClockLayoutController baseAodClockLayoutController, Context context, AodMediaViewStateController aodMediaViewStateController, AodMediaPromptController aodMediaPromptController) {
        this.aodController = baseAodClockLayoutController;
        this.srcContext = context;
        this.aodMediaViewStateController = aodMediaViewStateController;
        this.aodMediaPromptController = aodMediaPromptController;
        Context fixContext = AodUtils.getFixContext(context);
        this.context = fixContext;
        this.screenWidth = 0;
        onTransFinish.1 r2 = new onTransFinish.1(1);
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        this.operateTimeoutAction = r2;
        visibilityAggregatedListener.1 r3 = new visibilityAggregatedListener.1();
        r3.this$0 = this;
        VarHandle.storeStoreFence();
        this.visibilityAggregatedListener = r3;
        onTransFinish.1 r4 = new onTransFinish.1(4);
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        this.requestAodExpandedTimeoutAction = r4;
        onTransFinish.1 r5 = new onTransFinish.1(2);
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        this.requestAodCollapsedTimeoutAction = r5;
        onTransFinish.1 r6 = new onTransFinish.1(3);
        r6.this$0 = this;
        VarHandle.storeStoreFence();
        this.requestAodExpandedFromClickAction = r6;
        this.expandedAnimaListener = new expandedAnimaListener.1(this);
        Size screenSize = ScreenSizeHelper.Companion.getInstance(fixContext).getScreenSize();
        this.screenWidth = screenSize != null ? Integer.valueOf(screenSize.getWidth()) : null;
    }

    public static final void access$onTransFinish(AodMediaController aodMediaController) {
        AodMediaPanelView playerView;
        if (aodMediaController.aodMediaViewStateController.getCurState() == 1) {
            AodMediaControlPanel aodMediaControlPanel = aodMediaController.player;
            if (aodMediaControlPanel != null && (playerView = aodMediaControlPanel.getPlayerView()) != null) {
                onTransFinish.1 r2 = new onTransFinish.1(0);
                r2.this$0 = aodMediaController;
                VarHandle.storeStoreFence();
                playerView.post(r2);
            }
            aodMediaController.aodMediaViewStateController.setCurMode(2);
        }
        aodMediaController.resetOperateTimeout(aodMediaController.aodMediaViewStateController.getCurState(), true);
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBlackScreenGestureControll().requestGestureMode(16, "AodMediaController#onTransFinish");
        aodMediaController.updateAonModeOrState();
    }

    public final void bindAodManager(Object obj) {
        this.aodManager = obj;
    }

    public final boolean bindMediaDataForView(ViewGroup viewGroup, MediaData mediaData, String str) {
        AodMediaPanelView playerView;
        OplusNotificationLogger.logAodMediaPanel(TAG, "bindMediaDataForView() called, key = " + str);
        if (viewGroup != null && str != null && mediaData != null) {
            CharSequence artist = mediaData.getArtist();
            CharSequence song = mediaData.getSong();
            if (!TextUtils.isEmpty(artist) || !TextUtils.isEmpty(song)) {
                this.boundMediaData = mediaData;
                this.handlingKey = str;
                this.aodMediaAonController.bindAodMediaController(this);
                this.aodMediaPromptController.onBindMediaDataForViewBefore(mediaData);
                AodMediaControlPanel aodMediaControlPanel = this.player;
                if ((aodMediaControlPanel != null ? aodMediaControlPanel.getPlayerView() : null) == null) {
                    OplusNotificationLogger.logAodMediaPanel(TAG, "bindMediaDataForView: ext = null, should create newPlayer");
                    AodMediaControlPanel aodMediaControlPanel2 = (AodMediaControlPanel) DependencyEx.sDependency.createNewInstance(AodMediaControlPanel.class);
                    aodMediaControlPanel2.attachPlayer(AodMediaViewHolder.Companion.create(LayoutInflater.from(this.context), viewGroup, this.aodMediaViewStateController), this);
                    aodMediaControlPanel2.bindPlayer(mediaData, str);
                    if (aodMediaControlPanel2.getPlayerView() == null) {
                        OplusNotificationLogger.logAodMediaPanel(TAG, "bindMediaDataForView: playerView is null!");
                        return false;
                    }
                    this.player = aodMediaControlPanel2;
                    onMediaShownStateChange(true);
                    aodMediaControlPanel2.tryAddPlayerToMediaViewGroup(viewGroup);
                } else {
                    OplusNotificationLogger.logAodMediaPanel(TAG, "bindMediaDataForView: ext != null");
                    onMediaShownStateChange(true);
                    AodMediaControlPanel aodMediaControlPanel3 = this.player;
                    if (aodMediaControlPanel3 != null) {
                        aodMediaControlPanel3.tryAddPlayerToMediaViewGroup(viewGroup);
                    }
                    AodMediaControlPanel aodMediaControlPanel4 = this.player;
                    if (aodMediaControlPanel4 != null) {
                        aodMediaControlPanel4.bindPlayer(mediaData, str);
                    }
                }
                AodMediaControlPanel aodMediaControlPanel5 = this.player;
                if (aodMediaControlPanel5 != null && (playerView = aodMediaControlPanel5.getPlayerView()) != null) {
                    bindMediaDataForView.1 r10 = new bindMediaDataForView.1();
                    r10.this$0 = this;
                    r10.$mediaContent = viewGroup;
                    VarHandle.storeStoreFence();
                    playerView.post(r10);
                }
                AodMediaViewStateController aodMediaViewStateController = this.aodMediaViewStateController;
                if (aodMediaViewStateController.aodLayoutDirection == 2 || aodMediaViewStateController.getRehearsing()) {
                    if (this.aodMediaViewStateController.curMode != 0) {
                        updateAodMediaMode(0, "updateExpandedEnableState-isAodMediaCapsule.not");
                    }
                } else if (this.aodMediaViewStateController.curMode == 0) {
                    updateAodMediaMode(1, "updateExpandedEnableState-isAodMediaCapsule");
                }
                if (this.aodMediaViewStateController.curMode == 0) {
                    setMediaPanelExpandedState(0, "updateExpandedEnableState-isAodMediaCapsule");
                }
                updateAonModeOrState();
                return true;
            }
        }
        CharSequence artist2 = mediaData != null ? mediaData.getArtist() : null;
        OplusNotificationLogger.logAodMediaPanel(TAG, "Error!! bindMediaDataForView() bind Failed!!! curData = " + mediaData + ", curKey = " + str + ", artist = " + ((Object) artist2) + "; song = " + (mediaData != null ? mediaData.getSong() : null));
        return false;
    }

    public final void cancelCapsuleProgressAnim(String str) {
        this.aodMediaViewStateController.cancelCapsuleProgressAnim(str);
    }

    public final MediaData getBoundMediaData() {
        return this.boundMediaData;
    }

    public final String getHandlingKey() {
        return this.handlingKey;
    }

    public final boolean hasMediaPlayer() {
        return this.player != null;
    }

    public final void initPrepare() {
        this.aodMediaViewStateController.setCurMode(0);
        this.aodMediaViewStateController.setCurState(0);
        this.aodMediaViewStateController.addTransListener(this.expandedAnimaListener);
    }

    public final boolean isAodPanelExpanded() {
        AodMediaViewStateController aodMediaViewStateController = this.aodMediaViewStateController;
        int curState = aodMediaViewStateController.getCurState();
        aodMediaViewStateController.getClass();
        return curState == 1;
    }

    public final void onMediaShownStateChange(boolean z) {
        if (z != this.aodMediaViewStateController.getShown()) {
            this.aodMediaViewStateController.setShown(z);
            OplusNotificationLogger.logAodMediaPanel(TAG, "onMediaShownStateChange shown: " + z + " hasAodManager: " + (this.aodManager != null));
            Object obj = this.aodManager;
            if (obj != null) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(KEY_MEDIA_SHOWN, this.aodMediaViewStateController.getShown());
                ReflectionUtils.methodInvoke(obj, "handleMessageFromAodViewManager", new Class[]{Integer.TYPE, Bundle.class}, 1011, bundle);
            }
            AodMediaStatisticUtil.collectAodMediaShown(this.context, z);
        }
        if (z) {
            return;
        }
        AodMediaStatisticUtil.setAodMediaCardExpandTime(false, this.context);
    }

    public final void rebindAodMediaPanelView() {
        BaseAodClockLayoutController baseAodClockLayoutController = this.aodController;
        bindMediaDataForView(baseAodClockLayoutController != null ? baseAodClockLayoutController.getMediaContainerView() : null, this.boundMediaData, this.handlingKey);
    }

    public final void registerBlackScreenGestureCallback() {
        AodMediaControlPanel aodMediaControlPanel = this.player;
        if (aodMediaControlPanel != null) {
            aodMediaControlPanel.registerBlackScreenGestureCallback();
        }
    }

    public final void release() {
        this.aodMediaViewStateController.removeTransListener(this.expandedAnimaListener);
        this.aodManager = null;
    }

    public final void requestWakeLockInMode() {
        this.aodMediaViewStateController.requestWakeLockInMode();
    }

    public final void resetOperateTimeout(int i, boolean z) {
        if (this.mainHandler.hasCallbacks(this.operateTimeoutAction)) {
            this.mainHandler.removeCallbacks(this.operateTimeoutAction);
        }
        this.aodMediaViewStateController.getClass();
        if (i != 1) {
            updateAodMediaMode(1, "resetOperateTimeout-capsule");
            return;
        }
        this.mainHandler.postDelayed(this.operateTimeoutAction, 5000L);
        updateAodMediaMode(2, "resetOperateTimeout-expanded");
        if (z) {
            this.aodMediaAonController.resetFaceGazeHEIfNeed();
        }
    }

    public final void setMediaPanelExpandedState(int i, String str, boolean z) {
        AodMediaControlPanel aodMediaControlPanel;
        if (i != this.aodMediaViewStateController.getCurState()) {
            this.aodMediaViewStateController.getClass();
            boolean z2 = i == 1;
            if (this.mainHandler.hasCallbacks(this.requestAodExpandedFromClickAction)) {
                this.mainHandler.removeCallbacks(this.requestAodExpandedFromClickAction);
            }
            Handler handler = this.mainHandler;
            if (z2) {
                if (handler.hasCallbacks(this.requestAodCollapsedTimeoutAction)) {
                    this.mainHandler.removeCallbacks(this.requestAodCollapsedTimeoutAction);
                }
                if (this.mainHandler.hasCallbacks(this.requestAodExpandedTimeoutAction)) {
                    OplusNotificationLogger.logAodMediaPanel(TAG, "Repeat goto panel expanded, cause:" + str);
                    return;
                }
            } else {
                if (handler.hasCallbacks(this.requestAodExpandedTimeoutAction)) {
                    this.mainHandler.removeCallbacks(this.requestAodExpandedTimeoutAction);
                }
                if (this.mainHandler.hasCallbacks(this.requestAodCollapsedTimeoutAction)) {
                    OplusNotificationLogger.logAodMediaPanel(TAG, "Repeat goto panel collapsed, cause:" + str);
                    return;
                }
            }
            if (TextUtils.isEmpty(this.handlingKey) || (aodMediaControlPanel = this.player) == null) {
                return;
            }
            OplusNotificationLogger.logAodMediaPanel(TAG, "Goto panel expanded => " + z2 + ", cause:" + str);
            if (z2) {
                this.aodMediaViewStateController.setCurMode(2);
            }
            resetOperateTimeout(i, true);
            if (this.aodManager != null) {
                Handler handler2 = this.mainHandler;
                if (z2) {
                    handler2.postDelayed(this.requestAodExpandedTimeoutAction, 50L);
                } else {
                    handler2.postDelayed(this.requestAodCollapsedTimeoutAction, 50L);
                }
                ReflectionUtils.methodInvoke(this.aodManager, "handleMessageFromAodViewManager", new Class[]{Integer.TYPE, Bundle.class}, 1010, MediaResumeListener$getResumeAction$1$.ExternalSyntheticOutline0.m(KEY_GOTO_EXPANDED, z2));
            } else {
                this.aodMediaViewStateController.cancelTransTo();
                int curState = this.aodMediaViewStateController.getCurState();
                this.aodMediaViewStateController.setCurState(i);
                this.aodMediaViewStateController.transitionTo(aodMediaControlPanel, curState, i);
            }
            if (z2) {
                AodMediaStatisticUtil.collectAodMediaExpanded(this.context, z);
                AodMediaStatisticUtil.setAodMediaStartUpExpandTime(true);
            }
        }
    }

    public final void setRehearsing(boolean z, boolean z2) {
        AodMediaControlPanel aodMediaControlPanel;
        if (this.aodMediaViewStateController.getRehearsing() != z) {
            this.aodMediaViewStateController.setRehearsing(z);
            if (!z2 || (aodMediaControlPanel = this.player) == null) {
                return;
            }
            aodMediaControlPanel.bindArtistText(this.boundMediaData);
        }
    }

    public final void startCapsuleProgressAnim(long j, Runnable runnable, String str) {
        AodMediaControlPanel aodMediaControlPanel;
        if (TextUtils.isEmpty(this.handlingKey) || (aodMediaControlPanel = this.player) == null) {
            return;
        }
        this.aodMediaViewStateController.startCapsuleProgressAnim(aodMediaControlPanel, j, runnable, str);
    }

    public final void toggleUpdatePanelExpandedState() {
        if (this.aodMediaViewStateController.getCurState() == 1) {
            setMediaPanelExpandedState(0, "toggleUpdatePanelExpandedState", true);
            return;
        }
        boolean z = this.aodMediaViewStateController.curMode == 2;
        Handler handler = this.mainHandler;
        Runnable runnable = this.requestAodExpandedFromClickAction;
        if (!z) {
            handler.postDelayed(runnable, 50L);
            updateAodMediaMode(2, "toggleUpdatePanelExpandedState");
        } else {
            if (handler.hasCallbacks(runnable)) {
                return;
            }
            this.requestAodExpandedFromClickAction.run();
        }
    }

    public final void unRegisterBlackScreenGestureCallback() {
        AodMediaControlPanel aodMediaControlPanel = this.player;
        if (aodMediaControlPanel != null) {
            aodMediaControlPanel.unRegisterBlackScreenGestureCallback();
        }
    }

    public final void unbindMediaData() {
        if (this.mainHandler.hasCallbacks(this.requestAodExpandedFromClickAction)) {
            this.mainHandler.removeCallbacks(this.requestAodExpandedFromClickAction);
        }
        if (this.mainHandler.hasCallbacks(this.operateTimeoutAction)) {
            this.mainHandler.removeCallbacks(this.operateTimeoutAction);
        }
        if (this.mainHandler.hasCallbacks(this.requestAodExpandedTimeoutAction)) {
            this.mainHandler.removeCallbacks(this.requestAodExpandedTimeoutAction);
        }
        if (this.mainHandler.hasCallbacks(this.requestAodCollapsedTimeoutAction)) {
            this.mainHandler.removeCallbacks(this.requestAodCollapsedTimeoutAction);
        }
        this.aodMediaViewStateController.cancelTransTo();
        onMediaShownStateChange(false);
        AodMediaControlPanel aodMediaControlPanel = this.player;
        if (aodMediaControlPanel != null) {
            aodMediaControlPanel.destroy();
        }
        this.player = null;
        this.boundMediaData = null;
        this.handlingKey = null;
        this.aodMediaViewStateController.setCurMode(0);
        this.aodMediaViewStateController.setCurState(0);
        requestWakeLockInMode();
        IAodMediaAonController iAodMediaAonController = this.aodMediaAonController;
        iAodMediaAonController.bindAodMediaController(null);
        iAodMediaAonController.applyModeOrState(0);
    }

    public final void updateAodAreaByType(int i, int[] iArr) {
        OplusNotificationLogger.logAodMediaPanel(TAG, "updateAodAreaByType type: " + i + " position: " + (iArr != null ? Arrays.toString(iArr) : null));
        if (i != 5 || iArr == null) {
            return;
        }
        if (!(iArr.length == 0)) {
            int i2 = iArr[0];
            if (i2 == 1) {
                if (!this.mainHandler.hasCallbacks(this.requestAodExpandedFromClickAction)) {
                    updateAonModeOrState();
                    return;
                } else {
                    this.mainHandler.removeCallbacks(this.requestAodExpandedFromClickAction);
                    this.requestAodExpandedFromClickAction.run();
                    return;
                }
            }
            if (i2 == 2) {
                if (this.mainHandler.hasCallbacks(this.requestAodCollapsedTimeoutAction)) {
                    this.mainHandler.removeCallbacks(this.requestAodCollapsedTimeoutAction);
                }
                if (this.mainHandler.hasCallbacks(this.requestAodExpandedTimeoutAction)) {
                    this.mainHandler.removeCallbacks(this.requestAodExpandedTimeoutAction);
                }
                this.requestAodExpandedTimeoutAction.run();
                return;
            }
            if (i2 != 3) {
                return;
            }
            if (this.mainHandler.hasCallbacks(this.requestAodExpandedTimeoutAction)) {
                this.mainHandler.removeCallbacks(this.requestAodExpandedTimeoutAction);
            }
            if (this.mainHandler.hasCallbacks(this.requestAodCollapsedTimeoutAction)) {
                this.mainHandler.removeCallbacks(this.requestAodCollapsedTimeoutAction);
            }
            this.requestAodCollapsedTimeoutAction.run();
        }
    }

    public final void updateAodMediaMode(int i, String str) {
        if (i == this.aodMediaViewStateController.getCurMode()) {
            this.aodMediaViewStateController.requestWakeLockInMode();
            return;
        }
        OplusNotificationLogger.logAodMediaPanel(TAG, "updateAodMediaMode newMode: " + i + ", cause: " + str);
        this.aodMediaViewStateController.setCurMode(i);
        this.aodMediaViewStateController.requestWakeLockInMode();
        Object obj = this.aodManager;
        if (obj != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(KEY_ABORT_FLASK, this.aodMediaViewStateController.curMode == 2);
            ReflectionUtils.methodInvoke(obj, "handleMessageFromAodViewManager", new Class[]{Integer.TYPE, Bundle.class}, 1009, bundle);
        }
    }

    public final void updateAonModeOrState() {
        int i;
        AodMediaViewStateController aodMediaViewStateController = this.aodMediaViewStateController;
        int i2 = aodMediaViewStateController.curMode;
        if (i2 == 2) {
            i = 8;
        } else {
            i = i2 == 1 ? 12 : 0;
        }
        this.aodMediaAonController.applyModeOrState(aodMediaViewStateController.getCurState() == 1 ? i | 3 : i | 1);
    }

    public final void setMediaPanelExpandedState(int i, String str) {
        setMediaPanelExpandedState(i, str, false);
    }
}
