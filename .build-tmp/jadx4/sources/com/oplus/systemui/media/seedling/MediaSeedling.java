package com.oplus.systemui.media.seedling;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.systemui.media.livealert.MediaLiveAlertData;
import com.oplus.systemui.media.seedling.livealert.MediaLiveAlertController;
import com.oplus.systemui.media.seedling.livealert.MediaLiveAlertProvider;
import com.oplus.systemui.media.seedling.utils.FeatureOption;
import com.oplus.systemui.seedling.livealert.LiveAlertPendingIntentService;
import com.oplus.systemui.seedlingservice.mediaControl.LyricData;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplus.systemui.seedlingservice.utils.SeedlingMediaDataHandleUtils;
import com.oplus.systemui.statusbar.seeding.StartSeedlingService;
import com.oplus.wrapper.os.SystemProperties;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaSeedling {
    private static final String CARD_ENTRANCE_EVENT = "CardEntranceEvent";
    public static final Companion Companion = new Companion();
    private static final String EVENT = "event";
    private static final String MEDIA_ACTION_NAME = "MediaActionName";
    private static final String MEDIA_ACTION_SEEK = "MediaActionSeek";
    private static final String MEDIA_ACTION_TYPE = "MediaActionType";
    public static final String MEDIA_EVENT = "MediaEvent";
    private static final String MEDIA_PLAYER_ID = "MediaPlayId";
    private static final String METHOD_RECOMMENDATION = "MediaRecommendation";
    private static final String TAG = "MediaSeedling";
    public boolean allowCreateCard;
    private final Context context;
    public boolean disableSpectrumAnim;
    public boolean isFold;
    public boolean isMediaSeedlingShowOnStatusBar;
    public boolean isOccluded;
    public boolean isPanoramicAod;
    public boolean isScreenOn;
    public boolean isShowKeyguard;
    public boolean isUmsSeedlingShowOnLockScreen;
    private final MediaSeedling$mainHandler$1 mainHandler;
    private final Lazy mediaLiveAlertController$delegate;
    public boolean notificationSortedAllowed;
    private final dagger.Lazy oplusMediaSeedlingController;
    private final StartSeedlingService startSeedlingService;
    public boolean superPowerMode;
    public final int disableSeedlingFlags = SystemProperties.getInt("debug_disable_mediaSeedling_flags", 0);
    public boolean shouldPlayAnim = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$W8jYs13AcgP0mKiC55mWUU4-788, reason: not valid java name */
    public static Unit m20$r8$lambda$W8jYs13AcgP0mKiC55mWUU4788(MediaSeedling mediaSeedling, Bundle bundle) {
        mediaSeedling.getMediaLiveAlertController().cancel(bundle);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.oplus.systemui.media.seedling.MediaSeedling$mainHandler$1] */
    public MediaSeedling(Context context, StartSeedlingService startSeedlingService, dagger.Lazy lazy) {
        this.context = context;
        this.startSeedlingService = startSeedlingService;
        this.oplusMediaSeedlingController = lazy;
        MediaSeedling$$ExternalSyntheticLambda0 mediaSeedling$$ExternalSyntheticLambda0 = new MediaSeedling$$ExternalSyntheticLambda0();
        mediaSeedling$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mediaLiveAlertController$delegate = LazyKt__LazyJVMKt.lazy(mediaSeedling$$ExternalSyntheticLambda0);
        final Looper mainLooper = Looper.getMainLooper();
        this.mainHandler = new Handler(mainLooper) { // from class: com.oplus.systemui.media.seedling.MediaSeedling$mainHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                LyricData lyricData;
                Object obj = message.obj;
                Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
                boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
                int i = message.what;
                MediaSeedling mediaSeedling = this.this$0;
                if (i == 1) {
                    List<SeedlingMediaData> sortedEntries = ((OplusMediaSeedlingController) mediaSeedling.oplusMediaSeedlingController.get()).getSortedEntries();
                    boolean z = mediaSeedling.allowCreateCard;
                    OplusNotificationLogger.logMediaSeedling("MediaSeedling", "handleMediaDataUpdate allowCreateCard: " + z);
                    mediaSeedling.updateSeedlings(z, true, true);
                    mediaSeedling.allowCreateCard = false;
                    if (!mediaSeedling.isFold && ((mediaSeedling.isScreenOn || mediaSeedling.isPanoramicAod) && MediaSeedling.isAnyPlaying(message.arg1, sortedEntries))) {
                        mediaSeedling.postSeedlingsUpdate(message.arg1, 1000L, "handleMessage UpdateInterval", zBooleanValue);
                    }
                } else if (i == 2) {
                    List<SeedlingMediaData> sortedEntries2 = ((OplusMediaSeedlingController) mediaSeedling.oplusMediaSeedlingController.get()).getSortedEntries();
                    int i2 = message.arg2;
                    boolean zIsEmpty = sortedEntries2.isEmpty();
                    SeedlingMediaData seedlingMediaData = (SeedlingMediaData) CollectionsKt___CollectionsKt.firstOrNull(sortedEntries2);
                    boolean enabledAod = (seedlingMediaData == null || (lyricData = seedlingMediaData.getLyricData()) == null) ? false : lyricData.getEnabledAod();
                    if (!zIsEmpty) {
                        mediaSeedling.updateSeedlings(false, zBooleanValue, false);
                        boolean z2 = mediaSeedling.isPanoramicAod;
                        long j = (!z2 || enabledAod) ? 1000L : 5000L;
                        if (!mediaSeedling.isFold && ((mediaSeedling.isScreenOn || z2) && MediaSeedling.isAnyPlaying(i2, sortedEntries2))) {
                            mediaSeedling.postSeedlingsUpdate(i2, j, "handleSeedlingsUpdate", zBooleanValue);
                        }
                    }
                }
                super.handleMessage(message);
            }
        };
        this.isScreenOn = true;
        this.isMediaSeedlingShowOnStatusBar = true;
    }

    private final MediaLiveAlertController getMediaLiveAlertController() {
        return (MediaLiveAlertController) this.mediaLiveAlertController$delegate.getValue();
    }

    private final boolean getStaticVoicePrintShow() {
        return ((Boolean) FeatureOption.INSTANCE.getFluidConfigFeature().getFirst()).booleanValue() || this.disableSpectrumAnim || this.superPowerMode;
    }

    public static boolean isAnyPlaying(int i, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SeedlingMediaData seedlingMediaData = (SeedlingMediaData) it.next();
            if (i == 1 || SeedlingMediaData.Companion.isPlayState(seedlingMediaData.getState())) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void postSeedlingsUpdate$default(MediaSeedling mediaSeedling, String str, int i) {
        mediaSeedling.postSeedlingsUpdate(0, 0L, str, (i & 2) == 0);
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        boolean z = this.allowCreateCard;
        boolean z2 = this.isShowKeyguard;
        boolean z3 = this.isOccluded;
        boolean z4 = this.isUmsSeedlingShowOnLockScreen;
        boolean z5 = this.isMediaSeedlingShowOnStatusBar;
        boolean z6 = this.isFold;
        boolean z7 = this.isScreenOn;
        boolean z8 = this.shouldPlayAnim;
        boolean z9 = this.disableSpectrumAnim;
        boolean z10 = this.notificationSortedAllowed;
        boolean z11 = this.superPowerMode;
        Object obj = this.oplusMediaSeedlingController.get();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("    allowCreateCard=", " isShowKeyguard=", " isOccluded=", z, z2);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" isUmsSeedlingShowOnLockScreen=", " isMediaSeedlingShowOnStatusBar=", sbM, z3, z4);
        KeyguardLogger$.ExternalSyntheticOutline0.m("  isFold=", "  isScreenOn=", sbM, z5, z6);
        PromptIconState$.ExternalSyntheticOutline0.m(this.disableSeedlingFlags, " disableSeedlingFlags=", " shouldVoicePrintShown=false shouldPlayAnim=", sbM, z7);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" disableSpectrumAnim=", " notificationSortedAllowed=", sbM, z8, z9);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" superPowerMode=", " oplusMediaSeedlingController=", sbM, z10, z11);
        sbM.append(obj);
        printWriter.println(sbM.toString());
    }

    public final boolean getDisableSpectrumAnim() {
        return this.disableSpectrumAnim;
    }

    public final boolean getSuperPowerMode() {
        return this.superPowerMode;
    }

    public final void handleEvent(Bundle bundle) {
        String string;
        String string2;
        if (Intrinsics.areEqual(bundle != null ? bundle.getString(MEDIA_EVENT) : null, MEDIA_EVENT) && (string = bundle.getString(MEDIA_PLAYER_ID)) != null) {
            OplusMediaSeedlingController oplusMediaSeedlingController = (OplusMediaSeedlingController) this.oplusMediaSeedlingController.get();
            String string3 = bundle.getString(MEDIA_ACTION_TYPE);
            if (string3 != null) {
                int iHashCode = string3.hashCode();
                if (iHashCode != -966600507) {
                    if (iHashCode != -966447950) {
                        if (iHashCode == 68592797 && string3.equals(METHOD_RECOMMENDATION)) {
                            oplusMediaSeedlingController.executeRecommendationAction(string, bundle.getInt(METHOD_RECOMMENDATION));
                            return;
                        }
                    } else if (string3.equals(MEDIA_ACTION_SEEK)) {
                        oplusMediaSeedlingController.executeSeekToAction(string, bundle.getLong(MEDIA_ACTION_SEEK));
                        return;
                    }
                } else if (string3.equals(MEDIA_ACTION_NAME)) {
                    String string4 = bundle.getString(MEDIA_ACTION_NAME);
                    if (string4 == null || (string2 = bundle.getString(CARD_ENTRANCE_EVENT)) == null) {
                        return;
                    }
                    oplusMediaSeedlingController.executeMediaAction(string, string4, string2);
                    return;
                }
            }
            OplusNotificationLogger.logMediaSeedling(TAG, "illegal mediaEvent ");
        }
    }

    public final void onMediaDataLoaded(String str, SeedlingMediaData seedlingMediaData, boolean z, int i) {
        OplusNotificationLogger.logMediaSeedlingDebug(TAG, "onMediaDataLoaded: mediaData=" + seedlingMediaData);
        seedlingMediaData.setVoicePrintIsShow(false);
        SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
        seedlingMediaData.setAutoPlay(companion.isPlayState(seedlingMediaData.getState()));
        boolean zIsPlayStateChanged = seedlingMediaData.isPlayStateChanged();
        boolean zIsPlayState = companion.isPlayState(seedlingMediaData.getState());
        boolean z2 = this.allowCreateCard || (zIsPlayStateChanged && zIsPlayState) || z;
        this.allowCreateCard = z2;
        int state = seedlingMediaData.getState();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onMediaDataLoaded: allowCreateCard=", "  isPlayStateChanged=", " isPlayState=", z2, zIsPlayStateChanged);
        PromptIconState$.ExternalSyntheticOutline0.m(state, "playbackState.state=", " isFirstUpdate=", sbM, zIsPlayState);
        sbM.append(z);
        sbM.append(" key=");
        sbM.append(str);
        OplusNotificationLogger.logMediaSeedlingDebug(TAG, sbM.toString());
        postMediaDataUpdate(i, "onMediaDataLoaded");
    }

    public final void onMediaDataRemoved(String str) {
        OplusNotificationLogger.logMediaSeedlingDebug(TAG, "onMediaDataRemoved: playId=" + str);
        this.allowCreateCard = false;
        postMediaDataUpdate(0, "onMediaDataRemoved");
    }

    public final void postMediaDataUpdate(int i, String str) {
        OplusNotificationLogger.logMediaSeedling(TAG, "postMediaDataUpdate needUpdate: " + i + " reason:" + str);
        removeMessages(1);
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = i;
        sendMessage(messageObtain);
    }

    public final void postSeedlingsUpdate(int i, long j, String str, boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "postSeedlingsUpdate delay: " + j + " needUpdate: " + i + " reason:" + str);
        removeMessages(2);
        Message messageObtain = Message.obtain();
        messageObtain.obj = Boolean.valueOf(z);
        messageObtain.what = 2;
        messageObtain.arg2 = i;
        sendMessageDelayed(messageObtain, j);
    }

    public final void release() {
        this.startSeedlingService.sendDestroySeedling(FlowControlConfig$.ExternalSyntheticOutline0.m(EVENT, MEDIA_EVENT));
        getMediaLiveAlertController().release();
        removeMessages(1);
        removeMessages(2);
    }

    public final void setDisableSpectrumAnim(boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "disableSpectrumAnim; value: " + z + "; field: " + this.disableSpectrumAnim);
        if (z != this.disableSpectrumAnim) {
            this.disableSpectrumAnim = z;
            postSeedlingsUpdate$default(this, "disableSpectrumAnim", 7);
        }
    }

    public final void setFold(boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "isFold; value: " + z + "; field: " + this.isFold);
        if (z != this.isFold) {
            this.isFold = z;
            postSeedlingsUpdate$default(this, SeedlingMediaDataHandleUtils.KEY_IS_FOLD, 7);
        }
    }

    public final void setMediaSeedlingShowOnStatusBar(boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "isMediaSeedlingShowOnStatusBar; value: " + z + "; field: " + this.isMediaSeedlingShowOnStatusBar);
        if (z != this.isMediaSeedlingShowOnStatusBar) {
            this.isMediaSeedlingShowOnStatusBar = z;
            postMediaDataUpdate(0, "isMediaSeedlingShowOnStatusBar " + z);
        }
    }

    public final void setPanoramicAod(boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "isPanoramicAod; value: " + z + "; field: " + this.isPanoramicAod);
        if (z != this.isPanoramicAod) {
            this.isPanoramicAod = z;
            postMediaDataUpdate(0, SeedlingMediaDataHandleUtils.KEY_IS_PANORAMIC_AOD);
        }
    }

    public final void setScreenOn(boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "isScreenOn; value: " + z + "; field: " + this.isScreenOn);
        this.shouldPlayAnim = z;
        if (z != this.isScreenOn) {
            this.isScreenOn = z;
            boolean z2 = false;
            if (z) {
                postMediaDataUpdate(0, SeedlingMediaDataHandleUtils.KEY_IS_SCREEN_TURNING_ON);
            }
            if (!this.isScreenOn && this.isShowKeyguard) {
                z2 = true;
            }
            if (this.notificationSortedAllowed != z2) {
                OplusNotificationLogger.logMediaSeedlingDebug(TAG, "updateSortedAllowedState: sortAllowed=" + z2);
                this.notificationSortedAllowed = z2;
                if (z2) {
                    postSeedlingsUpdate$default(this, "updateSortedAllowedState", 5);
                }
            }
        }
    }

    public final void setShowKeyguard(boolean z) {
        this.isShowKeyguard = z;
    }

    public final void setSuperPowerMode(boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "superPowerMode; value: " + z + "; field: " + this.superPowerMode);
        if (z != this.superPowerMode) {
            this.superPowerMode = z;
            postSeedlingsUpdate$default(this, "superPowerMode", 7);
        }
    }

    public final void setUmsSeedlingShowOnLockScreen(boolean z) {
        OplusNotificationLogger.logMediaSeedling(TAG, "isUmsSeedlingShowOnLockScreen; value: " + z + "; field: " + this.isUmsSeedlingShowOnLockScreen);
        if (z != this.isUmsSeedlingShowOnLockScreen) {
            this.isUmsSeedlingShowOnLockScreen = z;
            postSeedlingsUpdate$default(this, SeedlingMediaDataHandleUtils.KEY_IS_UMS_SEEDLING_SHOW_ON_LOCK_SCREEN, 7);
        }
    }

    public final void start() {
        int i = 0;
        int i2 = 1;
        if (!((OplusMediaSeedlingController) this.oplusMediaSeedlingController.get()).getSortedEntries().isEmpty()) {
            this.allowCreateCard = true;
            postMediaDataUpdate(0, "start post");
        }
        MediaSeedling$$ExternalSyntheticLambda1 mediaSeedling$$ExternalSyntheticLambda1 = new MediaSeedling$$ExternalSyntheticLambda1(i);
        mediaSeedling$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.startSeedlingService.registerServerEventListener(MEDIA_EVENT, mediaSeedling$$ExternalSyntheticLambda1);
        MediaLiveAlertProvider.Companion companion = MediaLiveAlertProvider.Companion;
        MediaSeedling$$ExternalSyntheticLambda1 mediaSeedling$$ExternalSyntheticLambda2 = new MediaSeedling$$ExternalSyntheticLambda1(i2);
        mediaSeedling$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        companion.registerServerEvent(MEDIA_EVENT, mediaSeedling$$ExternalSyntheticLambda2);
        LiveAlertPendingIntentService.Companion companion2 = LiveAlertPendingIntentService.Companion;
        MediaSeedling$$ExternalSyntheticLambda1 mediaSeedling$$ExternalSyntheticLambda3 = new MediaSeedling$$ExternalSyntheticLambda1(2);
        mediaSeedling$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        companion2.registerServerEvent("CloseOnceEvent", mediaSeedling$$ExternalSyntheticLambda3);
    }

    public final void updateSeedlings(boolean z, boolean z2, boolean z3) {
        List<SeedlingMediaData> sortedEntries = ((OplusMediaSeedlingController) this.oplusMediaSeedlingController.get()).getSortedEntries();
        FeatureOption.INSTANCE.getClass();
        boolean z4 = FeatureOption.isMediaUMSEnabled;
        int i = this.disableSeedlingFlags;
        if (!z4) {
            getMediaLiveAlertController().onEventForLiveAlert(new MediaLiveAlertData(sortedEntries, getStaticVoicePrintShow(), this.isMediaSeedlingShowOnStatusBar, z, !((i & 2) == 0 && this.isUmsSeedlingShowOnLockScreen), (i & 4) != 0, this.shouldPlayAnim, this.isShowKeyguard, this.isOccluded, this.isUmsSeedlingShowOnLockScreen, this.isFold, this.isScreenOn, this.isPanoramicAod, this.notificationSortedAllowed, z2, z3));
            return;
        }
        Bundle bundleMediaDataToBundle = SeedlingMediaDataHandleUtils.mediaDataToBundle(sortedEntries, this.isPanoramicAod, z3);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_STATIC_VOICE_PRINT_SHOW, getStaticVoicePrintShow());
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_MEDIA_SEEDLING_SHOW_ON_STATUS_BAR, this.isMediaSeedlingShowOnStatusBar);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_ALLOW_CREATE_CARD, z);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_SHOULD_NOTIFICATION_SEEDLING_CARD_HIDDEN, !((i & 2) == 0 && this.isUmsSeedlingShowOnLockScreen));
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_SHOULD_SECOND_HOME_SEEDLING_CARD_HIDDEN, (i & 4) != 0);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_SHOULD_PLAY_ANIM, this.shouldPlayAnim);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_SHOW_KEYGUARD, this.isShowKeyguard);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_OCCLUDED, this.isOccluded);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_UMS_SEEDLING_SHOW_ON_LOCK_SCREEN, this.isUmsSeedlingShowOnLockScreen);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_FOLD, this.isFold);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_SCREEN_ON, this.isScreenOn);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_PANORAMIC_AOD, this.isPanoramicAod);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_NOTIFICATION_SORTED_ALLOWED, this.notificationSortedAllowed);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_MILESTONE, z2);
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_UPDATE_MEDIA, z3);
        bundleMediaDataToBundle.putInt(SeedlingMediaDataHandleUtils.KEY_DISABLE_SEEDLING_FLAGS, i);
        com.oplusos.systemui.common.feature.FeatureOption.INSTANCE.getClass();
        bundleMediaDataToBundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_FOLD_REMAP_DISABLED, com.oplusos.systemui.common.feature.FeatureOption.isFoldRemapDisabled());
        this.startSeedlingService.updateData(this.context, MEDIA_EVENT, bundleMediaDataToBundle);
    }
}
