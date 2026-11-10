package com.oplus.systemui.notification.mediapanel;

import android.app.AlarmManager;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.UserHandle;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.util.AlarmTimeout;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController;
import com.oplus.systemui.notification.collection.drivingmode.DrivingModeHelper;
import com.oplusos.systemui.aod.mediapanel.AodMediaController;
import com.oplusos.systemui.aod.mediapanel.AodMediaDataListener;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaPromptController implements DrivingModeHelper.Callback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusAodMediaDataExtImp";
    private BaseAodClockLayoutController aodController;
    private AodMediaController aodMediaController;
    private final Lazy aodMediaDataListener;
    private AudioManager audioManager;
    private final BroadcastDispatcher broadcastDispatcher;
    private final Runnable checkTriggerSpotifyMediaServiceAction;
    public int connectServiceCheckCycleTimes;
    private final Context context;
    private final Runnable doMediaPromptTimeoutAction;
    private final Handler mainHandler;
    private final Lazy mediaDataLoader;
    private final Lazy mediaDataManager;
    public long mediaPromptExpiration;
    private AlarmTimeout mediaPromptTimeoutTicker;
    private final Lazy mediaUiEventLogger;
    private Receiver receiver;
    private final AtomicInteger routedToBluetoothOrWiredState;
    private final scheduleCheckMusicInactiveConnectSpotifyServiceAction.1 scheduleCheckMusicInactiveConnectSpotifyServiceAction;
    private final spotifyPluginCallBack.1 spotifyPluginCallBack;
    private StatusBarStateController statusBarStateController;
    private final statusBarStateListener.1 statusBarStateListener;

    public AodMediaPromptController(Context context, Handler handler, BroadcastDispatcher broadcastDispatcher, Lazy lazy, Lazy lazy2, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Lazy lazy3, Lazy lazy4) {
        this.context = context;
        this.mainHandler = handler;
        this.broadcastDispatcher = broadcastDispatcher;
        this.mediaDataManager = lazy;
        this.mediaDataLoader = lazy2;
        this.mediaUiEventLogger = lazy3;
        this.aodMediaDataListener = lazy4;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        this.routedToBluetoothOrWiredState = atomicInteger;
        this.mediaPromptExpiration = Long.MAX_VALUE;
        this.connectServiceCheckCycleTimes = -1;
        doMediaPromptTimeoutAction.1 r2 = new doMediaPromptTimeoutAction.1(1);
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        this.checkTriggerSpotifyMediaServiceAction = r2;
        scheduleCheckMusicInactiveConnectSpotifyServiceAction.1 r3 = new scheduleCheckMusicInactiveConnectSpotifyServiceAction.1();
        r3.this$0 = this;
        VarHandle.storeStoreFence();
        this.scheduleCheckMusicInactiveConnectSpotifyServiceAction = r3;
        doMediaPromptTimeoutAction.1 r4 = new doMediaPromptTimeoutAction.1(0);
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        this.doMediaPromptTimeoutAction = r4;
        spotifyPluginCallBack.1 r1 = new spotifyPluginCallBack.1();
        r1.this$0 = this;
        r1.$backgroundScope = coroutineScope;
        r1.$backgroundDispatcher = coroutineDispatcher;
        VarHandle.storeStoreFence();
        this.spotifyPluginCallBack = r1;
        statusBarStateListener.1 r8 = new statusBarStateListener.1();
        r8.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = r8;
        SpotifyMediaBrowserHelper.Companion.getInstance().init(context);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            Object systemService = context.getSystemService("audio");
            this.audioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
            Receiver receiver = new Receiver(this);
            this.receiver = receiver;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.STREAM_DEVICES_CHANGED_ACTION");
            BroadcastDispatcher.registerReceiverWithHandler$default(broadcastDispatcher, receiver, intentFilter, new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER)), (UserHandle) null, 0, 56);
            DrivingModeHelper.Companion.getInstance().addCallback(this);
            Object systemService2 = context.getSystemService("alarm");
            AlarmManager alarmManager = systemService2 instanceof AlarmManager ? (AlarmManager) systemService2 : null;
            if (alarmManager != null) {
                1 r0 = new 1();
                r0.$tmp0 = this;
                VarHandle.storeStoreFence();
                this.mediaPromptTimeoutTicker = new AlarmTimeout(alarmManager, r0, "prompt_media_timeout", handler);
            }
            StatusBarStateController statusBarStateController = (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
            this.statusBarStateController = statusBarStateController;
            if (statusBarStateController != null) {
                statusBarStateController.addCallback(r8);
            }
            atomicInteger.set(getRoutedToBluetoothOrWiredState());
        }
    }

    public static final boolean access$canShowSpotifyMediaPrompt(AodMediaPromptController aodMediaPromptController, PlaybackState playbackState) {
        if (aodMediaPromptController.aodController == null || !SpotifyMediaBrowserHelper.Companion.getInstance().isPaused(playbackState)) {
            return false;
        }
        AodMediaDataListener aodMediaDataListener = (AodMediaDataListener) aodMediaPromptController.aodMediaDataListener.get();
        if (aodMediaDataListener != null && aodMediaDataListener.hasAodMediaData()) {
            return false;
        }
        AodMediaController aodMediaController = aodMediaPromptController.aodMediaController;
        return aodMediaController == null || !aodMediaController.isAodPanelExpanded();
    }

    public static final void access$doMediaPromptTimeout(AodMediaPromptController aodMediaPromptController) {
        if (aodMediaPromptController.mediaPromptExpiration != Long.MAX_VALUE) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = aodMediaPromptController.mediaPromptExpiration;
            if (j > jElapsedRealtime) {
                long j2 = j - jElapsedRealtime;
                OplusNotificationLogger.logAodMediaPanel(TAG, "doMediaPromptTimeout scheduleTimeout: " + j2);
                aodMediaPromptController.scheduleMediaPromptTimeout(j2, jElapsedRealtime);
                return;
            }
        }
        OplusNotificationLogger.logAodMediaPanel(TAG, "doMediaPromptTimeout");
        aodMediaPromptController.cancelMediaPromptTimeout();
        if (!aodMediaPromptController.isSpotifyActive()) {
            SpotifyMediaBrowserHelper.Companion.getInstance().requestDisconnectMediaService();
            return;
        }
        BaseAodClockLayoutController baseAodClockLayoutController = aodMediaPromptController.aodController;
        if (baseAodClockLayoutController != null) {
            baseAodClockLayoutController.unbindAodMedia();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getRoutedToBluetoothOrWiredState() {
        AudioManager audioManager = this.audioManager;
        if (audioManager == null) {
            return 0;
        }
        int devicesForStream = audioManager.getDevicesForStream(3);
        OplusNotificationLogger.logAodMediaPanel(TAG, "getRoutedToBluetoothOrWiredState devices: " + devicesForStream);
        return devicesForStream & 603980796;
    }

    public final void cancelMediaPromptTimeout() {
        if (this.mediaPromptExpiration != Long.MAX_VALUE) {
            this.mediaPromptExpiration = Long.MAX_VALUE;
        }
        if (this.mainHandler.hasCallbacks(this.doMediaPromptTimeoutAction)) {
            this.mainHandler.removeCallbacks(this.doMediaPromptTimeoutAction);
        }
        AlarmTimeout alarmTimeout = this.mediaPromptTimeoutTicker;
        if (alarmTimeout != null) {
            alarmTimeout.cancel();
        }
    }

    public final void checkTriggerSpotifyMediaService() {
        AodMediaDataListener aodMediaDataListener;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            if (this.mainHandler.hasCallbacks(this.checkTriggerSpotifyMediaServiceAction)) {
                this.mainHandler.removeCallbacks(this.checkTriggerSpotifyMediaServiceAction);
            }
            if (this.mainHandler.hasCallbacks(this.scheduleCheckMusicInactiveConnectSpotifyServiceAction)) {
                this.mainHandler.removeCallbacks(this.scheduleCheckMusicInactiveConnectSpotifyServiceAction);
            }
            this.connectServiceCheckCycleTimes = -1;
            if (this.aodController == null || (aodMediaDataListener = (AodMediaDataListener) this.aodMediaDataListener.get()) == null || !aodMediaDataListener.isAodMediaEnable()) {
                return;
            }
            AodMediaDataListener aodMediaDataListener2 = (AodMediaDataListener) this.aodMediaDataListener.get();
            if (aodMediaDataListener2 == null || !aodMediaDataListener2.hasAodMediaData()) {
                int i = this.routedToBluetoothOrWiredState.get();
                DrivingModeHelper.Companion companion = DrivingModeHelper.Companion;
                boolean z = companion.getInstance().isGmsDrivingMode;
                boolean z2 = companion.getInstance().isDrivingMode;
                if (i == 0 && !z && !z2) {
                    if (!isSpotifyActive()) {
                        cancelMediaPromptTimeout();
                        SpotifyMediaBrowserHelper.Companion.getInstance().requestDisconnectMediaService();
                        return;
                    } else {
                        BaseAodClockLayoutController baseAodClockLayoutController = this.aodController;
                        if (baseAodClockLayoutController != null) {
                            baseAodClockLayoutController.unbindAodMedia();
                            return;
                        }
                        return;
                    }
                }
                StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("routedToBluetoothOrWiredState.get() = ", i, "isGmsDrivingMode = ", "isDrivingMode = ", z);
                sbM.append(z2);
                OplusNotificationLogger.logAodMediaPanel(TAG, sbM.toString());
                if (!isSpotifyActive()) {
                    this.scheduleCheckMusicInactiveConnectSpotifyServiceAction.run();
                    return;
                }
                long prompt_media_timeout = AodMediaPromptControllerKt.getPROMPT_MEDIA_TIMEOUT() + SystemClock.elapsedRealtime();
                this.mediaPromptExpiration = prompt_media_timeout;
                OplusNotificationLogger.logAodMediaPanel(TAG, "checkTriggerSpotifyMediaService mediaPromptExpiration: " + prompt_media_timeout);
            }
        }
    }

    public final boolean isSpotifyActive() {
        MediaData boundMediaData;
        String handlingKey;
        AodMediaController aodMediaController = this.aodMediaController;
        if (aodMediaController == null || (handlingKey = aodMediaController.getHandlingKey()) == null || !StringsKt__StringsKt.contains(handlingKey, "com.spotify.music", false)) {
            AodMediaController aodMediaController2 = this.aodMediaController;
            if (!"com.spotify.music".equals((aodMediaController2 == null || (boundMediaData = aodMediaController2.getBoundMediaData()) == null) ? null : boundMediaData.getPackageName())) {
                return false;
            }
        }
        return true;
    }

    public final void onBindMediaDataForViewBefore(MediaData mediaData) {
        if (!isSpotifyActive()) {
            removeMediaPromptRelatedActions();
            SpotifyMediaBrowserHelper.Companion.getInstance().requestDisconnectMediaService();
            return;
        }
        AodMediaDataListener aodMediaDataListener = (AodMediaDataListener) this.aodMediaDataListener.get();
        if (aodMediaDataListener != null && aodMediaDataListener.hasAodMediaData()) {
            removeMediaPromptRelatedActions();
        }
        SpotifyMediaBrowserHelper.Companion companion = SpotifyMediaBrowserHelper.Companion;
        companion.getInstance().requestConnectMediaService(this.spotifyPluginCallBack);
        companion.getInstance().convertMediaItemsToOplusCustomActions(mediaData != null ? mediaData.getSemanticActions() : null);
    }

    public final void onBindMediaWhenAodInit(BaseAodClockLayoutController baseAodClockLayoutController, AodMediaController aodMediaController) {
        this.aodController = baseAodClockLayoutController;
        this.aodMediaController = aodMediaController;
    }

    public final void onDrivingModeChange() {
        if (this.mainHandler.hasCallbacks(this.checkTriggerSpotifyMediaServiceAction)) {
            return;
        }
        this.mainHandler.post(this.checkTriggerSpotifyMediaServiceAction);
    }

    public final void onRehearsingClick() {
        AodMediaController aodMediaController = this.aodMediaController;
        if (aodMediaController != null) {
            aodMediaController.setRehearsing(false, true);
        }
        if (isSpotifyActive()) {
            SpotifyMediaBrowserHelper.Companion.getInstance().play();
        }
    }

    public final void onReleasedMedia() {
        this.aodController = null;
        this.aodMediaController = null;
    }

    public final void removeMediaPromptRelatedActions() {
        if (this.mainHandler.hasCallbacks(this.checkTriggerSpotifyMediaServiceAction)) {
            this.mainHandler.removeCallbacks(this.checkTriggerSpotifyMediaServiceAction);
        }
        if (this.mainHandler.hasCallbacks(this.scheduleCheckMusicInactiveConnectSpotifyServiceAction)) {
            this.mainHandler.removeCallbacks(this.scheduleCheckMusicInactiveConnectSpotifyServiceAction);
        }
        cancelMediaPromptTimeout();
    }

    public final void scheduleMediaPromptTimeout(long j, long j2) {
        AlarmTimeout alarmTimeout;
        cancelMediaPromptTimeout();
        this.mediaPromptExpiration = j2 + j;
        this.mainHandler.postDelayed(this.doMediaPromptTimeoutAction, j);
        StatusBarStateController statusBarStateController = this.statusBarStateController;
        if (statusBarStateController == null || !statusBarStateController.isDozing() || (alarmTimeout = this.mediaPromptTimeoutTicker) == null) {
            return;
        }
        alarmTimeout.schedule(2, 2, j);
    }
}
