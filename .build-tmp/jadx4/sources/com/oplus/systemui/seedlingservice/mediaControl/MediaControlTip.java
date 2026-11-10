package com.oplus.systemui.seedlingservice.mediaControl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.oplus.pantanal.seedling.bean.SeedlingCard;
import com.oplus.pantanal.seedling.bean.SeedlingHostEnum;
import com.oplus.pantanal.seedling.update.SeedlingCardOptions;
import com.oplus.systemui.seedlingservice.datamanager.UiData;
import com.oplus.systemui.seedlingservice.eventmanager.Event;
import com.oplus.systemui.seedlingservice.statemanager.CardLifeCycleState;
import com.oplus.systemui.seedlingservice.statemanager.CardState;
import com.oplus.systemui.seedlingservice.statemanager.CardStateManager;
import com.oplus.systemui.seedlingservice.statemanager.StateManager;
import com.oplus.systemui.seedlingservice.tip.NormalTip;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import com.oplus.systemui.seedlingservice.utils.SeedlingMediaDataHandleUtils;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaControlTip extends NormalTip implements StateManager.StateListener {
    private static final String CARD_ENTRANCE_EVENT = "CardEntranceEvent";
    private static final int DISABLE_STATUS_BAR = 1;
    private static final long HIDE_STATUS_BAR_OUT_TIME = 5000;
    private static final int MAX_CAROUSEL_COUNT = 3;
    private static final String MEDIA_ACTION_NAME = "MediaActionName";
    private static final String MEDIA_ACTION_SEEK = "MediaActionSeek";
    private static final String MEDIA_ACTION_TYPE = "MediaActionType";
    private static final String MEDIA_EVENT = "MediaEvent";
    private static final String MEDIA_PLAYER_ID = "MediaPlayId";
    private static final String MEDIA_SECOND_HOME_LOCK_SCREEN_ENTRANCE = "SecondaryLockScreen";
    private static final String MEDIA_SECOND_HOME_NOTIFICATION_ENTRANCE = "SecondaryNotification";
    private static final String MEDIA_SEEDLING_ACTION = "pantanal.intent.business.app.system.AUDIO_CONTROL_PANEL_SYSTEMUI";
    private static final String MEDIA_SERVICE_ID = "268452006";
    private static final String METHOD_RECOMMENDATION = "MediaRecommendation";
    private static final String TAG = "MediaControllerTip";
    private static final String UPLOAD_VALUE_DEFAULT = "na";
    private static int disableSeedlingFlags;
    private static boolean hasMediaCard;
    private static boolean isCardCreated;
    private static boolean isFold;
    private static boolean isFoldRemapDisabled;
    private static boolean isMilestone;
    private static boolean isNotificationSeedlingHidden;
    private static boolean isOccluded;
    private static boolean isPanoramicAod;
    private static boolean isSecondHomeSeedlingHidden;
    private static boolean isShowKeyguard;
    private static boolean isStatusBarCardState;
    private static boolean isStatusBarPlaying;
    private static boolean isStatusBarSeedlingHidden;
    private static boolean isUmsSeedlingShowOnLockScreen;
    private static boolean isUpdateMedia;
    private static SeedlingCardOptions mediaCardOptions;
    private static boolean notificationSortedAllowed;
    private static MediaSeedlingTools seedlingTools;
    private static boolean shouldNotificationSeedlingCardHidden;
    private static boolean shouldSecondHomeSeedlingCardHidden;
    private static boolean staticVoicePrintShow;
    public static final MediaControlTip INSTANCE = new MediaControlTip();
    private static final CopyOnWriteArrayList<SeedlingMediaData> notificationMediaDataList = new CopyOnWriteArrayList<>();
    private static String lastStatusBarPlayerId = "";
    private static List<SeedlingMediaData> mediaDataList = CollectionsKt__CollectionsKt.emptyList();
    private static boolean shouldPlayAnim = true;
    private static boolean isMediaSeedlingShowOnStatusBar = true;
    private static boolean isScreenOn = true;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final HashSet<String> MediaClickSet = SetsKt__SetsKt.hashSetOf(new String[]{SeedlingMediaDataHandleUtils.METHOD_BTN_E5_ONCLICK, SeedlingMediaDataHandleUtils.METHOD_BTN_E1_ONCLICK, SeedlingMediaDataHandleUtils.METHOD_BTN_E2_ONCLICK, SeedlingMediaDataHandleUtils.METHOD_BTN_E3_ONCLICK, SeedlingMediaDataHandleUtils.METHOD_BTN_E4_ONCLICK, SeedlingMediaDataHandleUtils.METHOD_SLIDER_CHANGE, SeedlingMediaDataHandleUtils.METHOD_SWIPER_ONCLICK, SeedlingMediaDataHandleUtils.METHOD_RECOMMENDATION_ONCLICK});
    private static final Runnable statusBarTimeoutRunnable = new Runnable() { // from class: com.oplus.systemui.seedlingservice.mediaControl.MediaControlTip$statusBarTimeoutRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            Object next;
            Iterator<T> it = MediaControlTip.INSTANCE.getCardStateManager().getCardMap().keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                SeedlingCard seedlingCard = (SeedlingCard) next;
                if (Intrinsics.areEqual(seedlingCard.getServiceId(), OplusCustomRowControllerManager.MEDIA_CONTROLLER_SERVICE_ID) && seedlingCard.getHost() == SeedlingHostEnum.StatusBar) {
                    break;
                }
            }
            SeedlingCard seedlingCard2 = (SeedlingCard) next;
            if (seedlingCard2 != null) {
                MediaControlTip.INSTANCE.hideStatusBarSeedlingCard(seedlingCard2, false);
            }
        }
    };

    private MediaControlTip() {
    }

    private final void createSeedlingIfNeeded(List<SeedlingMediaData> list, Bundle bundle) throws JSONException {
        Context mContext;
        int size = list.size();
        boolean z = isCardCreated;
        Context mContext2 = getMContext();
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("createSeedling: size=", size, " isCardCreated=", ", mcontext=", z);
        sbM.append(mContext2);
        LogUtil.i(TAG, sbM.toString());
        if (isCardCreated || (mContext = getMContext()) == null) {
            return;
        }
        UiData uiData = SeedlingMediaDataHandleUtils.toUiData(mContext, list, staticVoicePrintShow, shouldPlayAnim);
        SeedlingMediaData seedlingMediaData = (SeedlingMediaData) CollectionsKt___CollectionsKt.firstOrNull(list);
        String packageName = seedlingMediaData != null ? seedlingMediaData.getPackageName() : null;
        boolean z2 = (isMediaSeedlingShowOnStatusBar && (seedlingMediaData != null ? SeedlingMediaData.Companion.isPlayState(seedlingMediaData.getState()) : false)) ? false : true;
        isStatusBarSeedlingHidden = z2;
        boolean z3 = shouldNotificationSeedlingCardHidden;
        isNotificationSeedlingHidden = z3;
        MediaSeedlingTools mediaSeedlingTools = seedlingTools;
        mediaCardOptions = MediaSeedlingTools.getOptions$default(mediaSeedlingTools == null ? null : mediaSeedlingTools, z2, z3, false, true, packageName, 4, null);
        INSTANCE.createOrUpdateCard(uiData, bundle);
        isCardCreated = true;
    }

    private final JSONObject getExtraData(int i) throws JSONException {
        Set andResetChangedState = MediaGlobalExtraDataManager.INSTANCE.getAndResetChangedState(i);
        if (andResetChangedState.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        Iterator it = andResetChangedState.iterator();
        while (it.hasNext()) {
            jSONObject.put(((Uri) it.next()).toString(), true);
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideStatusBarSeedlingCard(SeedlingCard seedlingCard, boolean z) {
        LogUtil.i(TAG, "hideStatusBarSeedlingCard: isStatusBarSeedlingHidden=" + isStatusBarSeedlingHidden + ", isForceHide=" + z);
        if (!isStatusBarSeedlingHidden || z) {
            isStatusBarSeedlingHidden = true;
            MediaSeedlingTools mediaSeedlingTools = seedlingTools;
            SeedlingCardOptions options$default = MediaSeedlingTools.getOptions$default(mediaSeedlingTools == null ? null : mediaSeedlingTools, true, false, false, false, null, 30, null);
            MediaSeedlingTools mediaSeedlingTools2 = seedlingTools;
            (mediaSeedlingTools2 != null ? mediaSeedlingTools2 : null).hideSeedlingCard(seedlingCard, options$default);
        }
    }

    private final boolean isSeedlingCardUpdateAllowed(SeedlingCard seedlingCard, boolean z) {
        int i = WhenMappings.$EnumSwitchMapping$0[seedlingCard.getHost().ordinal()];
        if (i == 1) {
            return isShowKeyguard && isUmsSeedlingShowOnLockScreen && !isFold && (isScreenOn || isPanoramicAod);
        }
        if (i == 2) {
            return isMediaSeedlingShowOnStatusBar && (!isShowKeyguard || isOccluded) && !isFold;
        }
        if (i == 3 || i == 4) {
            return isFold && isScreenOn;
        }
        return true;
    }

    private final void onEventForSeedling(Event event) throws JSONException {
        boolean z;
        Bundle info = event.getInfo();
        if (info != null) {
            mediaDataList = SeedlingMediaDataHandleUtils.parseMediaDataList(info);
            staticVoicePrintShow = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_STATIC_VOICE_PRINT_SHOW);
            isMediaSeedlingShowOnStatusBar = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_MEDIA_SEEDLING_SHOW_ON_STATUS_BAR);
            z = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_ALLOW_CREATE_CARD);
            shouldNotificationSeedlingCardHidden = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_SHOULD_NOTIFICATION_SEEDLING_CARD_HIDDEN);
            shouldSecondHomeSeedlingCardHidden = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_SHOULD_SECOND_HOME_SEEDLING_CARD_HIDDEN);
            shouldPlayAnim = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_SHOULD_PLAY_ANIM);
            isShowKeyguard = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_SHOW_KEYGUARD);
            isOccluded = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_OCCLUDED);
            isUmsSeedlingShowOnLockScreen = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_UMS_SEEDLING_SHOW_ON_LOCK_SCREEN);
            isFold = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_FOLD);
            isScreenOn = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_SCREEN_ON);
            isPanoramicAod = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_PANORAMIC_AOD);
            isMilestone = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_MILESTONE);
            isUpdateMedia = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_UPDATE_MEDIA);
            disableSeedlingFlags = info.getInt(SeedlingMediaDataHandleUtils.KEY_DISABLE_SEEDLING_FLAGS);
            notificationSortedAllowed = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_NOTIFICATION_SORTED_ALLOWED);
            isFoldRemapDisabled = info.getBoolean(SeedlingMediaDataHandleUtils.KEY_IS_FOLD_REMAP_DISABLED);
        } else {
            z = false;
        }
        if (mediaDataList.isEmpty()) {
            destroySeedling();
            return;
        }
        SeedlingMediaDataHandleUtils.parseAndUpdateExtraData(mediaDataList, isFoldRemapDisabled);
        if (z) {
            createSeedlingIfNeeded(mediaDataList, info);
        }
        updateSeedlings(mediaDataList, isUpdateMedia || isMilestone);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onStatusBarCardStateChanged(SeedlingCard seedlingCard, boolean z) throws JSONException {
        if (seedlingCard.getHost() == SeedlingHostEnum.StatusBar && z != isStatusBarCardState) {
            isStatusBarCardState = z;
            LogUtil.i(TAG, "isStatusBarCardState: " + z);
            updateSeedlings(mediaDataList, true);
            updateStatusBarTimeoutRunnable();
        }
    }

    private final void release() {
        boolean z;
        Set<SeedlingCard> setKeySet = getCardStateManager().getCardMap().keySet();
        if (!(setKeySet instanceof Collection) || !setKeySet.isEmpty()) {
            Iterator<T> it = setKeySet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (Intrinsics.areEqual(((SeedlingCard) it.next()).getServiceId(), "268452006")) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        hasMediaCard = z;
        setWaitingCreateCard(false);
        isCardCreated = false;
        boolean z2 = hasMediaCard;
        ConcurrentHashMap<SeedlingCard, CardState> cardMap = getCardStateManager().getCardMap();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onDestroy, isCardCreated=", " hasMediaCard=", " cardMap=", false, z2);
        sbM.append(cardMap);
        LogUtil.i(TAG, sbM.toString());
        if (hasMediaCard) {
            return;
        }
        MediaGlobalExtraDataManager.INSTANCE.reset();
    }

    private final void sendMessage(Bundle bundle) {
        Message messageObtain = Message.obtain();
        Messenger messengerFromEventCode = CardStateManager.Companion.getInstance().getMessengerFromEventCode("MediaEvent");
        bundle.putString("MediaEvent", "MediaEvent");
        messageObtain.what = 2;
        messageObtain.setData(bundle);
        try {
            LogUtil.i(TAG, "sendMessage: messengerInner=" + messengerFromEventCode);
            if (messengerFromEventCode != null) {
                messengerFromEventCode.send(messageObtain);
            }
        } catch (RemoteException e) {
            LogUtil.e(TAG, "sendMessage failed. " + e.getMessage());
        }
    }

    private final boolean shouldStatusBarSeedlingCardHidden(SeedlingMediaData seedlingMediaData, String str, boolean z) {
        if ((disableSeedlingFlags & 1) != 0 || !isMediaSeedlingShowOnStatusBar) {
            return true;
        }
        if (isStatusBarSeedlingHidden && !z) {
            return true;
        }
        if (z || Intrinsics.areEqual(str, lastStatusBarPlayerId)) {
            return false;
        }
        LogUtil.debug(TAG, "shouldStatusBarSeedlingCardHidden: isInactiveLongTime");
        return true;
    }

    private final void updateNormalSeedling(SeedlingCard seedlingCard, List<SeedlingMediaData> list, boolean z) {
        Context mContext;
        if (isSeedlingCardUpdateAllowed(seedlingCard, z) && (mContext = getMContext()) != null) {
            int size = list.size();
            SeedlingHostEnum host = seedlingCard.getHost();
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("updateSeedling: size=", size, " isMilestone=", " card: ", z);
            sbM.append(host);
            LogUtil.debug(TAG, sbM.toString());
            UiData uiData = SeedlingMediaDataHandleUtils.toUiData(mContext, list, staticVoicePrintShow, shouldPlayAnim);
            MediaSeedlingTools mediaSeedlingTools = seedlingTools;
            SeedlingCardOptions options$default = MediaSeedlingTools.getOptions$default(mediaSeedlingTools == null ? null : mediaSeedlingTools, false, false, false, z, null, 23, null);
            MediaSeedlingTools mediaSeedlingTools2 = seedlingTools;
            (mediaSeedlingTools2 != null ? mediaSeedlingTools2 : null).updateSeedling(seedlingCard, uiData, options$default);
        }
    }

    private final void updateNotificationMediaDataList(List<SeedlingMediaData> list) {
        int i = 0;
        if (notificationSortedAllowed) {
            notificationMediaDataList.clear();
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                SeedlingMediaData seedlingMediaData = (SeedlingMediaData) obj;
                CopyOnWriteArrayList<SeedlingMediaData> copyOnWriteArrayList = notificationMediaDataList;
                if (copyOnWriteArrayList.size() >= 3) {
                    return;
                }
                copyOnWriteArrayList.add(seedlingMediaData);
                i = i2;
            }
            return;
        }
        List<SeedlingMediaData> list2 = list;
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (Object obj2 : list2) {
            linkedHashMap.put(((SeedlingMediaData) obj2).getPlayerId(), obj2);
        }
        CopyOnWriteArrayList<SeedlingMediaData> copyOnWriteArrayList2 = notificationMediaDataList;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = copyOnWriteArrayList2.iterator();
        while (it.hasNext()) {
            SeedlingMediaData seedlingMediaData2 = (SeedlingMediaData) linkedHashMap.get(((SeedlingMediaData) it.next()).getPlayerId());
            if (seedlingMediaData2 != null) {
                arrayList.add(seedlingMediaData2);
            }
        }
        List mutableList = CollectionsKt___CollectionsKt.toMutableList(arrayList);
        for (Object obj3 : list2) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            SeedlingMediaData seedlingMediaData3 = (SeedlingMediaData) obj3;
            if (mutableList.size() < 3) {
                List list3 = mutableList;
                if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                    Iterator it2 = list3.iterator();
                    do {
                        if (!it2.hasNext()) {
                            mutableList.add(seedlingMediaData3);
                            break;
                        }
                    } while (!Intrinsics.areEqual(((SeedlingMediaData) it2.next()).getPlayerId(), seedlingMediaData3.getPlayerId()));
                } else {
                    mutableList.add(seedlingMediaData3);
                    break;
                    break;
                }
            }
            i = i3;
        }
        CopyOnWriteArrayList<SeedlingMediaData> copyOnWriteArrayList3 = notificationMediaDataList;
        copyOnWriteArrayList3.clear();
        copyOnWriteArrayList3.addAll(mutableList);
    }

    private final void updateNotificationSeedling(SeedlingCard seedlingCard, List<SeedlingMediaData> list, boolean z) throws JSONException {
        MediaSeedlingTools mediaSeedlingTools = seedlingTools;
        MediaSeedlingTools mediaSeedlingTools2 = mediaSeedlingTools == null ? null : mediaSeedlingTools;
        boolean z2 = shouldNotificationSeedlingCardHidden;
        boolean z3 = z2 != isNotificationSeedlingHidden;
        if (z2) {
            if (z3) {
                LogUtil.d(TAG, "updateNotificationSeedling hideSeedlingCard");
                isNotificationSeedlingHidden = true;
                mediaSeedlingTools2.hideSeedlingCard(seedlingCard, MediaSeedlingTools.getOptions$default(mediaSeedlingTools2, false, true, false, false, null, 29, null));
                return;
            }
            return;
        }
        MediaControlTip mediaControlTip = INSTANCE;
        mediaControlTip.updateNotificationMediaDataList(list);
        boolean z4 = z | z3;
        if (mediaControlTip.isSeedlingCardUpdateAllowed(seedlingCard, z4)) {
            isNotificationSeedlingHidden = false;
            UiData uiData = new UiData();
            Context mContext = mediaControlTip.getMContext();
            if (mContext != null) {
                uiData = SeedlingMediaDataHandleUtils.toUiData(mContext, notificationMediaDataList, staticVoicePrintShow, shouldPlayAnim);
            }
            UiData uiData2 = uiData;
            JSONObject extraData = mediaControlTip.getExtraData(2);
            UiData uiDataPut = extraData != null ? uiData2.put(SeedlingMediaDataHandleUtils.KEY_RELOAD_PICTURES, extraData) : null;
            if (LogUtil.INSTANCE.getPanicEnabled()) {
                LogUtil.d(TAG, DynamicRouteAttributes$.ExternalSyntheticOutline0.m(" extraData null=", " ", TextFieldImplKt$.ExternalSyntheticOutline0.m("updateNotificationSeedling: ", " isMilestone: ", " isHideChanged: ", notificationSortedAllowed, z), z3, uiDataPut == null));
                Iterator<T> it = notificationMediaDataList.iterator();
                while (it.hasNext()) {
                    LogUtil.d(TAG, "notificationMediaDataList forEach: " + ((SeedlingMediaData) it.next()) + " ");
                }
            }
            mediaSeedlingTools2.updateSeedling(seedlingCard, uiData2, MediaSeedlingTools.getOptions$default(mediaSeedlingTools2, false, false, false, z4, null, 21, null));
        }
    }

    private final void updateSecondHomeSeedling(SeedlingCard seedlingCard, List<SeedlingMediaData> list, boolean z) throws JSONException {
        Context mContext;
        SeedlingMediaData seedlingMediaData;
        boolean z2 = shouldSecondHomeSeedlingCardHidden;
        boolean z3 = z2 != isSecondHomeSeedlingHidden;
        if (z2 && z3) {
            isSecondHomeSeedlingHidden = true;
            MediaSeedlingTools mediaSeedlingTools = seedlingTools;
            SeedlingCardOptions options$default = MediaSeedlingTools.getOptions$default(mediaSeedlingTools == null ? null : mediaSeedlingTools, false, false, true, false, null, 27, null);
            MediaSeedlingTools mediaSeedlingTools2 = seedlingTools;
            (mediaSeedlingTools2 != null ? mediaSeedlingTools2 : null).hideSeedlingCard(seedlingCard, options$default);
            return;
        }
        if (z2 || (mContext = getMContext()) == null) {
            return;
        }
        MediaControlTip mediaControlTip = INSTANCE;
        boolean z4 = z | z3;
        if (!mediaControlTip.isSeedlingCardUpdateAllowed(seedlingCard, z4) || (seedlingMediaData = (SeedlingMediaData) CollectionsKt___CollectionsKt.firstOrNull(list)) == null) {
            return;
        }
        isSecondHomeSeedlingHidden = false;
        UiData uiDataSecondHomeToUiData = SeedlingMediaDataHandleUtils.secondHomeToUiData(mContext, CollectionsKt__CollectionsKt.mutableListOf(new SeedlingMediaData[]{seedlingMediaData}), staticVoicePrintShow, shouldPlayAnim);
        JSONObject extraData = mediaControlTip.getExtraData(seedlingCard.getHost() == SeedlingHostEnum.SecondaryNotification ? 4 : 8);
        if (extraData != null) {
            uiDataSecondHomeToUiData.put(SeedlingMediaDataHandleUtils.KEY_RELOAD_PICTURES, extraData);
        }
        LogUtil.debug(TAG, "update" + seedlingCard.getHost() + ", Seedling=" + uiDataSecondHomeToUiData);
        MediaSeedlingTools mediaSeedlingTools3 = seedlingTools;
        SeedlingCardOptions options$default2 = MediaSeedlingTools.getOptions$default(mediaSeedlingTools3 == null ? null : mediaSeedlingTools3, false, false, false, z4, null, 19, null);
        MediaSeedlingTools mediaSeedlingTools4 = seedlingTools;
        (mediaSeedlingTools4 != null ? mediaSeedlingTools4 : null).updateSeedling(seedlingCard, uiDataSecondHomeToUiData, options$default2);
    }

    private final void updateSeedlings(List<SeedlingMediaData> list, boolean z) throws JSONException {
        LogUtil.d(TAG, "updateSeedlings: cardMap=" + getCardStateManager().getCardMap());
        for (Map.Entry<SeedlingCard, CardState> entry : getCardStateManager().getCardMap().entrySet()) {
            SeedlingCard key = entry.getKey();
            CardState value = entry.getValue();
            if (Intrinsics.areEqual(key.getServiceId(), "268452006") && value.getLifeCycleState() != CardLifeCycleState.CREATED && value.getLifeCycleState() != CardLifeCycleState.DESTROY) {
                INSTANCE.updateSeedlingsInternal(key, list, z);
            }
        }
        if (z) {
            MediaGlobalExtraDataManager mediaGlobalExtraDataManager = MediaGlobalExtraDataManager.INSTANCE;
            mediaGlobalExtraDataManager.resetChangedState(4);
            mediaGlobalExtraDataManager.resetChangedState(8);
        }
    }

    private final void updateSeedlingsInternal(SeedlingCard seedlingCard, List<SeedlingMediaData> list, boolean z) throws JSONException {
        int i = WhenMappings.$EnumSwitchMapping$0[seedlingCard.getHost().ordinal()];
        if (i == 1) {
            updateNotificationSeedling(seedlingCard, list, z);
            return;
        }
        if (i == 2) {
            updateStatusBarSeedling(seedlingCard, list, z);
            return;
        }
        if (i != 3 && i != 4) {
            updateNormalSeedling(seedlingCard, list, z);
        } else {
            if (SeedlingMediaDataHandleUtils.INSTANCE.isExpRegion()) {
                return;
            }
            updateSecondHomeSeedling(seedlingCard, list, z);
        }
    }

    private final void updateStatusBarSeedling(SeedlingCard seedlingCard, List<SeedlingMediaData> list, boolean z) throws JSONException {
        MediaSeedlingTools mediaSeedlingTools = seedlingTools;
        MediaSeedlingTools mediaSeedlingTools2 = mediaSeedlingTools == null ? null : mediaSeedlingTools;
        SeedlingMediaData seedlingMediaData = (SeedlingMediaData) CollectionsKt___CollectionsKt.firstOrNull(list);
        if (seedlingMediaData != null) {
            boolean zIsPlayState = SeedlingMediaData.Companion.isPlayState(seedlingMediaData.getState());
            if (zIsPlayState) {
                lastStatusBarPlayerId = seedlingMediaData.getPlayerId();
            }
            MediaControlTip mediaControlTip = INSTANCE;
            boolean zShouldStatusBarSeedlingCardHidden = mediaControlTip.shouldStatusBarSeedlingCardHidden(seedlingMediaData, seedlingMediaData.getPlayerId(), zIsPlayState);
            boolean z2 = isStatusBarSeedlingHidden;
            boolean z3 = zShouldStatusBarSeedlingCardHidden != z2;
            boolean z4 = isStatusBarPlaying;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateStatusBarSeedling shouldHide=", " isHideChanged=", " isStatusBarSeedlingHidden=", zShouldStatusBarSeedlingCardHidden, z3);
            sbM.append(z2);
            sbM.append(" isStatusBarPlaying=");
            sbM.append(z4);
            LogUtil.debug(TAG, sbM.toString());
            if (zShouldStatusBarSeedlingCardHidden) {
                mediaControlTip.hideStatusBarSeedlingCard(seedlingCard, false);
                return;
            }
            if (zIsPlayState != isStatusBarPlaying) {
                isStatusBarPlaying = zIsPlayState;
                mediaControlTip.updateStatusBarTimeoutRunnable();
            }
            boolean z5 = z | z3;
            if (mediaControlTip.isSeedlingCardUpdateAllowed(seedlingCard, z5)) {
                List listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new SeedlingMediaData[]{seedlingMediaData});
                String packageName = seedlingMediaData.getPackageName();
                Iterator it = listMutableListOf.iterator();
                while (it.hasNext()) {
                    ((SeedlingMediaData) it.next()).setPackageName(packageName);
                }
                UiData uiData = new UiData();
                MediaControlTip mediaControlTip2 = INSTANCE;
                Context mContext = mediaControlTip2.getMContext();
                if (mContext != null) {
                    uiData = SeedlingMediaDataHandleUtils.toUiData(mContext, listMutableListOf, staticVoicePrintShow, shouldPlayAnim);
                }
                UiData uiData2 = uiData;
                JSONObject extraData = mediaControlTip2.getExtraData(1);
                UiData uiDataPut = extraData != null ? uiData2.put(SeedlingMediaDataHandleUtils.KEY_RELOAD_PICTURES, extraData) : null;
                boolean z6 = isStatusBarSeedlingHidden;
                boolean z7 = uiDataPut == null;
                StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateStatusBarSeedling isMilestone=", " isHideChanged=", " isStatusBarSeedlingHidden=", z, z3);
                KeyguardLogger$.ExternalSyntheticOutline0.m(" extraData null=", " data=", sbM2, z6, z7);
                sbM2.append(uiData2);
                LogUtil.debug(TAG, sbM2.toString());
                SeedlingCardOptions options$default = MediaSeedlingTools.getOptions$default(mediaSeedlingTools2, false, false, false, z5, packageName, 6, null);
                isStatusBarSeedlingHidden = false;
                mediaSeedlingTools2.updateSeedling(seedlingCard, uiData2, options$default);
            }
        }
    }

    private final void updateStatusBarTimeoutRunnable() {
        boolean z = (isStatusBarCardState || isStatusBarPlaying) ? false : true;
        Handler handler = mainHandler;
        Runnable runnable = statusBarTimeoutRunnable;
        handler.removeCallbacks(runnable);
        if (z) {
            handler.postDelayed(runnable, HIDE_STATUS_BAR_OUT_TIME);
        }
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void cardUpdateData(SeedlingCard seedlingCard, Bundle bundle) throws JSONException {
        LogUtil.d(TAG, "cardUpdateData data: " + bundle);
        updateSeedlingsInternal(seedlingCard, mediaDataList, true);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public boolean checkCardInvalid(CardState cardState) {
        return false;
    }

    public final void destroySeedling() {
        isCardCreated = false;
        Context mContext = getMContext();
        if (mContext != null) {
            LogUtil.d(TAG, "destroySeedling");
            INSTANCE.cancelIntent(mContext, "268452006");
        }
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisCardCreated=", isCardCreated);
        printWriter.println("\thasMediaCard=" + hasMediaCard);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\twaitingCreateCard: ", getWaitingCreateCard());
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tnotificationSortedAllowed: ", notificationSortedAllowed);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisStatusBarSeedlingHidden: ", isStatusBarSeedlingHidden);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisNotificationSeedlingHidden: ", isNotificationSeedlingHidden);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisSecondHomeSeedlingHidden: ", isSecondHomeSeedlingHidden);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisStatusBarCardState: ", isStatusBarCardState);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisStatusBarPlaying: ", isStatusBarPlaying);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tshouldNotificationSeedlingCardHidden: ", shouldNotificationSeedlingCardHidden);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tshouldSecondHomeSeedlingCardHidden: ", shouldSecondHomeSeedlingCardHidden);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tstaticVoicePrintShow: ", staticVoicePrintShow);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tshouldPlayAnim: ", shouldPlayAnim);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisShowKeyguard: ", isShowKeyguard);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisOccluded: ", isOccluded);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisUmsSeedlingShowOnLockScreen: ", isUmsSeedlingShowOnLockScreen);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisFold: ", isFold);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisScreenOn: ", isScreenOn);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisMilestone: ", isMilestone);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisUpdateMedia: ", isUpdateMedia);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("\tdisableSeedlingFlags: ", disableSeedlingFlags, printWriter);
        for (Map.Entry entry : MediaGlobalExtraDataManager.INSTANCE.getChangedState().entrySet()) {
            printWriter.println("    extraData: " + entry.getKey() + " : " + entry.getValue());
        }
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getEventCode() {
        return "MediaEvent";
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getSeedlingAction() {
        return MEDIA_SEEDLING_ACTION;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public SeedlingCardOptions getSeedlingCardOptions() {
        SeedlingCardOptions seedlingCardOptions = mediaCardOptions;
        if (seedlingCardOptions != null) {
            return seedlingCardOptions;
        }
        MediaSeedlingTools mediaSeedlingTools = seedlingTools;
        if (mediaSeedlingTools == null) {
            mediaSeedlingTools = null;
        }
        return MediaSeedlingTools.getOptions$default(mediaSeedlingTools, false, false, false, false, null, 31, null);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getServiceId() {
        return "268452006";
    }

    public final void handleClick(String str, Bundle bundle) {
        String string;
        if (MediaClickSet.contains(str)) {
            Bundle bundle2 = new Bundle();
            String string2 = bundle != null ? bundle.getString(SeedlingMediaDataHandleUtils.PARAM_MEDIA_ID) : null;
            if (bundle == null || (string = bundle.getString(SeedlingMediaDataHandleUtils.CARD_ENTRANCE)) == null) {
                string = "na";
            }
            int i = 0;
            boolean z = string.equals(MEDIA_SECOND_HOME_NOTIFICATION_ENTRANCE) || string.equals(MEDIA_SECOND_HOME_LOCK_SCREEN_ENTRANCE);
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("handleClick: method=", str, " playId=", string2, " cardEntrance=");
            sbM.append(string);
            LogUtil.i(TAG, sbM.toString());
            if (string2 != null) {
                int iHashCode = str.hashCode();
                if (iHashCode != -637593744) {
                    if (iHashCode != 1345197088) {
                        if (iHashCode == 1706590448 && str.equals(SeedlingMediaDataHandleUtils.METHOD_RECOMMENDATION_ONCLICK)) {
                            try {
                                String string3 = bundle.getString(SeedlingMediaDataHandleUtils.PARAM_INDEX, "0");
                                if (string3 != null) {
                                    i = Integer.parseInt(string3);
                                }
                            } catch (NumberFormatException e) {
                                LogUtil.e(TAG, "进度值类型转换异常：" + e);
                            }
                            LogUtil.i(TAG, "recommendationOnClick: " + i);
                            bundle2.putString(MEDIA_ACTION_TYPE, METHOD_RECOMMENDATION);
                            bundle2.putInt(METHOD_RECOMMENDATION, i);
                            bundle2.putString(MEDIA_PLAYER_ID, string2);
                            INSTANCE.sendMessage(bundle2);
                            return;
                        }
                    } else if (str.equals(SeedlingMediaDataHandleUtils.METHOD_SWIPER_ONCLICK)) {
                        LogUtil.i(TAG, "launchApp!");
                        return;
                    }
                } else if (str.equals(SeedlingMediaDataHandleUtils.METHOD_SLIDER_CHANGE)) {
                    long j = 0;
                    try {
                        String string4 = bundle.getString(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE, "0");
                        if (string4 != null) {
                            j = Long.parseLong(string4);
                        }
                    } catch (NumberFormatException e2) {
                        LogUtil.e(TAG, "进度值类型转换异常：" + e2);
                    }
                    LogUtil.i(TAG, "seekToPosition: " + j);
                    bundle2.putString(MEDIA_ACTION_TYPE, MEDIA_ACTION_SEEK);
                    bundle2.putLong(MEDIA_ACTION_SEEK, j * 1000);
                    bundle2.putString(MEDIA_PLAYER_ID, string2);
                    INSTANCE.sendMessage(bundle2);
                    return;
                }
                String str2 = SeedlingMediaDataHandleUtils.INSTANCE.getMethodToPositionMap().get(str);
                if (str2 == null) {
                    LogUtil.w(TAG, "Unknown button method: ".concat(str));
                    return;
                }
                Map<String, SeedlingMediaDataHandleUtils.ButtonInfo> currentButtonLayout = SeedlingMediaDataHandleUtils.getCurrentButtonLayout(string2, z);
                SeedlingMediaDataHandleUtils.ButtonInfo buttonInfo = currentButtonLayout != null ? currentButtonLayout.get(str2) : null;
                if (buttonInfo == null || !buttonInfo.getHasButton()) {
                    LogUtil.w(TAG, "Button not available in current layout: " + str2 + ", buttonInfo = " + buttonInfo);
                    return;
                }
                String buttonType = buttonInfo.getButtonType();
                StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("Button click: method=", str, ", originalPos=", str2, ", actualFunction=");
                sbM2.append(buttonType);
                LogUtil.i(TAG, sbM2.toString());
                bundle2.putString(MEDIA_ACTION_TYPE, MEDIA_ACTION_NAME);
                bundle2.putString(MEDIA_ACTION_NAME, buttonType);
                bundle2.putString(CARD_ENTRANCE_EVENT, string);
                bundle2.putString(MEDIA_PLAYER_ID, string2);
                INSTANCE.sendMessage(bundle2);
            }
        }
    }

    @Override // com.oplus.systemui.seedlingservice.statemanager.StateManager.StateListener
    public void onCancelIntent() {
        LogUtil.i(TAG, "onCancelIntent");
        release();
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void onCardCreate(SeedlingCard seedlingCard) {
        isCardCreated = true;
        if (isStatusBarSeedlingHidden) {
            hideStatusBarSeedlingCard(seedlingCard, true);
        }
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void onDestroy(SeedlingCard seedlingCard) {
        super.onDestroy(seedlingCard);
        release();
    }

    @Override // com.oplus.systemui.seedlingservice.eventmanager.EventListener
    public void onEvent(Event event) throws JSONException {
        LogUtil.debug(TAG, "onEvent " + event.getInfo());
        onEventForSeedling(event);
    }

    @Override // com.oplus.systemui.seedlingservice.statemanager.StateManager.StateListener
    public void onLocked() {
        super.onLocked();
    }

    public final void onSizeChanged(SeedlingCard seedlingCard, int i) {
        mainHandler.post(new onSizeChanged.1(seedlingCard, i));
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareCreateData(UiData uiData, Bundle bundle) {
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareUpdateData(UiData uiData, Bundle bundle) {
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip, com.oplus.systemui.seedlingservice.tip.CoreModule
    public void start(Context context) {
        super.start(context);
        seedlingTools = new MediaSeedlingTools();
        StateManager.INSTANCE.addListener("MediaEvent", this);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip, com.oplus.systemui.seedlingservice.tip.CoreModule
    public void stop(Context context) {
        LogUtil.i(TAG, "stop");
        release();
        StateManager.INSTANCE.removeListener("MediaEvent");
        super.stop(context);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void updateData(UiData uiData) {
    }
}
