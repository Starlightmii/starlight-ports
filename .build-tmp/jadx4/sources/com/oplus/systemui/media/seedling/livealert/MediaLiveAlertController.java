package com.oplus.systemui.media.seedling.livealert;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.collection.FloatList$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.content.OplusIntent;
import com.oplus.systemui.media.livealert.MediaLiveAlertData;
import com.oplus.systemui.media.seedling.MediaSeedlingBgThread;
import com.oplus.systemui.seedlingservice.R$drawable;
import com.oplus.systemui.seedlingservice.R$string;
import com.oplus.systemui.seedlingservice.mediaControl.MediaAction;
import com.oplus.systemui.seedlingservice.mediaControl.MediaButton;
import com.oplus.systemui.seedlingservice.mediaControl.MediaRecommendationData;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplus.systemui.seedlingservice.utils.UriExtKt;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaLiveAlertController {
    private static final String ASSET_PREFIX = "./assets/";
    public static final Companion Companion = new Companion();
    private static final String EVENT_SCHEDULE_MEDIA_SEEDLING = "scheduleForMediaSeeding";
    private static final String KEY_ALBUM_ROTATION_DIRECTION = "rotate";
    private static final String KEY_ART_FULLSCREEN_ENABLE = "immersiveFullBgEnable";
    private static final String KEY_BTN_ACTION_EXTRA = "actionExtra";
    private static final String KEY_BTN_ACTION_NAME = "action_name";
    private static final String KEY_BTN_ACTION_URI = "actionUri";
    private static final String KEY_BTN_ANIM = "anim";
    private static final String KEY_BTN_CLOSE_ONCE_EVENT = "CloseOnceEvent";
    private static final String KEY_BTN_CONTENT_DESC = "contentDescription";
    private static final String KEY_BTN_EVENT_TYPE = "EventType";
    private static final String KEY_BTN_MEDIA_ACTION_NAME = "MediaActionName";
    private static final String KEY_BTN_MEDIA_ACTION_SEEK = "MediaActionSeek";
    private static final String KEY_BTN_MEDIA_ACTION_TYPE = "MediaActionType";
    private static final String KEY_BTN_MEDIA_ENTRANCE = "CardEntranceEvent";
    private static final String KEY_BTN_MEDIA_ENTRANCE_STATUS_BAR = "StatusBar";
    private static final String KEY_BTN_MEDIA_EVENT = "MediaEvent";
    private static final String KEY_BTN_MEDIA_LABEL = "MediaLabel";
    private static final String KEY_BTN_MEDIA_PLAY_ID = "MediaPlayId";
    private static final String KEY_BTN_MEDIA_RECOMMENDATION = "MediaRecommendation";
    private static final String KEY_BTN_MEDIA_TOTAL_DURATION = "MediaTotalDuration";
    private static final String KEY_BTN_NAME_PROGRESS_BAR = "progress_bar";
    private static final String KEY_BTN_NEXT_ACTION = "nextAction";
    private static final String KEY_CAPSULE = "oplus.livealert.capsule";
    private static final String KEY_CAPSULE_EXTRA = "extraData";
    private static final String KEY_CAPSULE_ICON = "smallPic";
    private static final String KEY_CAPSULE_SHOW_LOTTIE = "isShowStaticLottie";
    private static final String KEY_CARD = "oplus.livealert.card";
    private static final String KEY_CARD_BTN_LIST = "btnList";
    private static final String KEY_CARD_PIC = "desPic";
    private static final String KEY_CARD_PICTURE = "picture";
    private static final String KEY_CARD_PIC_LIST = "picList";
    private static final String KEY_CARD_RIGHT_ICON = "topRightIcon";
    private static final String KEY_CARD_SLIDER_VALUE = "value";
    private static final String KEY_EXTENSION_DATA = "extensionData";
    private static final String KEY_EXTRA = "extra";
    private static final String KEY_IS_MILESTONE = "isMilestone";
    private static final String KEY_LA_OPTIONS = "oplusLiveAlertOptions";
    private static final String KEY_LYRIC = "lyric";
    private static final String KEY_LYRIC_SUPPORTED = "isLyricSupported";
    private static final String KEY_MEDIA_DATA = "oplus.mediaData";
    private static final String KEY_NEW_SIZE = "newSize";
    private static final String KEY_OLD_SIZE = "oldSize";
    private static final String KEY_OPTION_EXTENSIBLE_ACTION_MAP = "extensibleActionMap";
    private static final String KEY_OPTION_ID = "id";
    private static final String KEY_OPTION_IMMERSE_CARD_TYPE = "immersiveCardType";
    private static final String KEY_OPTION_RECEIVER_URI = "receiverUri";
    private static final String KEY_OPTION_SHOW_HOST_MAP = "showHostMap";
    private static final String KEY_OPTION_SHOW_HOST_MAP_LOCKED = "lockScreenShowHostMap";
    private static final String KEY_OPTION_SOURCE_PKG = "dataSourcePkgName";
    private static final String KEY_PLAYER_ARR = "playerArr";
    private static final String KEY_PRIMARY_ACTIVE = "active";
    private static final String KEY_PRIMARY_BACKGROUND = "background";
    private static final String KEY_PRIMARY_CONTENT = "content";
    private static final String KEY_PRIMARY_CONTENT_ICON = "iconBeforeArtist";
    private static final String KEY_PRIMARY_CONTENT_ICON_VOICE_LABEL = "smallIconBeforeArtistVoiceLabel";
    private static final String KEY_PRIMARY_CONTENT_LABEL = "contentLabel";
    private static final String KEY_PRIMARY_DATA = "primaryData";
    private static final String KEY_PRIMARY_DESC = "desc";
    private static final String KEY_PRIMARY_ICON = "icon";
    private static final String KEY_PRIMARY_INTENT = "intent";
    private static final String KEY_PRIMARY_MODEL = "processModel";
    private static final String KEY_PRIMARY_MODEL_NORMAL = "normal";
    private static final String KEY_PRIMARY_PKG = "pkg";
    private static final String KEY_PRIMARY_PROGRESS = "progress";
    private static final String KEY_PRIMARY_PROGRESS_CURRENT = "currentTime";
    private static final String KEY_PRIMARY_PROGRESS_TOTAL = "duration";
    private static final String KEY_RESULT_CODE = "resultCode";
    private static final String KEY_SBN_KEY = "key";
    private static final String KEY_SEEKBAR_AVAILABLE = "seekAvailable";
    private static final String KEY_SEEKBAR_ENABLE = "seekEnable";
    private static final String KEY_SHAPE_ARTWORK = "shapeArtWork";
    private static final String KEY_TITLE = "title";
    private static final String KEY_UNIQUE_ID = "uniqueId";
    private static final String KEY_USER_ID = "userId";
    private static final String LOTTIE_PAUSE = "images/pauseTransitionDark.json";
    private static final String LOTTIE_PAUSE_SPOTIFY = "images/spotifyTransitionToPlay.json";
    private static final String LOTTIE_PLAY = "images/playTransitionDark.json";
    private static final String LOTTIE_PLAY_SPOTIFY = "images/spotifyTransitionToPause.json";
    private static final String MEDIA_ACTION = "com.oplus.systemui.seedling.livealert.LiveAlertPendingIntentService";
    private static final String MEDIA_PROVIDER = "com.oplus.systemui.media.seedling.livealert.MediaLiveAlertProvider";
    private static final String MEDIA_SERVICE_ID = "268452006";
    private static final String METHOD_CANCEL = "cancelLiveAlert";
    private static final String METHOD_SEND = "sendLiveAlert";
    private static final String PROVIDER = "com.oplus.card.server.systemui.provider";
    private static final String SPOTIFY_PACKAGE_NAME = "com.spotify.music";
    private static final String TAG = "MediaLiveAlertController";
    private static MediaLiveAlertController instance;
    public final boolean isExpRegion;
    public int mediaCurrentState;
    private StatusBarNotification sbn;
    private final Runnable scheduleMediaSeedingEndRunnable;
    private final Runnable statusBarTimeoutRunnable;
    private final Context sysContext;
    private CopyOnWriteArrayList<SeedlingMediaData> mediaDataList = new CopyOnWriteArrayList<>();
    private final Handler myHandler = new Handler(MediaSeedlingBgThread.INSTANCE.getThreadLooper());
    private MediaLiveAlertData mediaLiveAlertData = new MediaLiveAlertData(CollectionsKt__CollectionsKt.emptyList(), false, true, true, false, false, false, false, false, false, false, true, false, false, false, false);
    private final Map<String, PendingIntent> activityIntentMap = new LinkedHashMap();
    private String timeStamp = String.valueOf(System.currentTimeMillis());
    public boolean isMediaCardDestroy = true;
    private String lastStatusBarPlayerId = "";
    public boolean showOnStatusBar = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MediaLiveAlertController getInstance(Context context) {
            MediaLiveAlertController mediaLiveAlertController;
            MediaLiveAlertController mediaLiveAlertController2 = MediaLiveAlertController.instance;
            if (mediaLiveAlertController2 != null) {
                return mediaLiveAlertController2;
            }
            synchronized (this) {
                mediaLiveAlertController = MediaLiveAlertController.instance;
                if (mediaLiveAlertController == null) {
                    mediaLiveAlertController = new MediaLiveAlertController(context);
                    MediaLiveAlertController.instance = mediaLiveAlertController;
                }
            }
            return mediaLiveAlertController;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.livealert.MediaLiveAlertController$cancel$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Object $bundle;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ MediaLiveAlertController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    Bundle bundle = (Bundle) this.$bundle;
                    String string = bundle != null ? bundle.getString(MediaLiveAlertController.KEY_SBN_KEY, "") : null;
                    StatusBarNotification statusBarNotification = this.this$0.sbn;
                    String key = statusBarNotification != null ? statusBarNotification.getKey() : null;
                    boolean z = this.this$0.isMediaCardDestroy;
                    StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("cancel-", key, ": ", string, ", ");
                    sbM.append(z);
                    OplusNotificationLogger.logMediaSeedling(MediaLiveAlertController.TAG, sbM.toString());
                    StatusBarNotification statusBarNotification2 = this.this$0.sbn;
                    if (Intrinsics.areEqual(string, statusBarNotification2 != null ? statusBarNotification2.getKey() : null)) {
                        MediaLiveAlertController.access$closeThisEvent(this.this$0);
                        MediaLiveAlertController.access$onDestroy(this.this$0);
                    }
                    break;
                case 1:
                    Bundle bundle2 = (Bundle) this.$bundle;
                    String string2 = bundle2 != null ? bundle2.getString(MediaLiveAlertController.KEY_SBN_KEY, "") : null;
                    StatusBarNotification statusBarNotification3 = this.this$0.sbn;
                    String key2 = statusBarNotification3 != null ? statusBarNotification3.getKey() : null;
                    boolean z2 = this.this$0.isMediaCardDestroy;
                    StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("destroy-", key2, ": ", string2, ", ");
                    sbM2.append(z2);
                    OplusNotificationLogger.logMediaSeedling(MediaLiveAlertController.TAG, sbM2.toString());
                    StatusBarNotification statusBarNotification4 = this.this$0.sbn;
                    if (Intrinsics.areEqual(string2, statusBarNotification4 != null ? statusBarNotification4.getKey() : null)) {
                        MediaLiveAlertController.access$onDestroy(this.this$0);
                    }
                    break;
                case 2:
                    int i = ((Bundle) this.$bundle).getInt(MediaLiveAlertController.KEY_OLD_SIZE);
                    int i2 = ((Bundle) this.$bundle).getInt(MediaLiveAlertController.KEY_NEW_SIZE);
                    String string3 = ((Bundle) this.$bundle).getString(MediaLiveAlertController.KEY_SBN_KEY);
                    StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("onSizeChange, oldSize=", i, i2, ", newSize=", ", sbnKey=");
                    sbM3.append(string3);
                    OplusNotificationLogger.logMediaSeedling(MediaLiveAlertController.TAG, sbM3.toString());
                    StatusBarNotification statusBarNotification5 = this.this$0.sbn;
                    if (!Intrinsics.areEqual(string3, statusBarNotification5 != null ? statusBarNotification5.getKey() : null)) {
                        OplusNotificationLogger.logMediaSeedling(MediaLiveAlertController.TAG, "sbn key is not available, ignore");
                    } else if (i2 == 1 || i2 == 3) {
                        MediaLiveAlertController mediaLiveAlertController = this.this$0;
                        if (mediaLiveAlertController.mediaCurrentState != i2) {
                            mediaLiveAlertController.mediaCurrentState = i2;
                            mediaLiveAlertController.myHandler.removeCallbacks(this.this$0.statusBarTimeoutRunnable);
                            this.this$0.sendLiveAlertData(MediaLiveAlertController.METHOD_SEND);
                        }
                    }
                    break;
                default:
                    this.this$0.mediaLiveAlertData = (MediaLiveAlertData) this.$bundle;
                    MediaLiveAlertController.access$updateNotificationMediaDataList(this.this$0, ((MediaLiveAlertData) this.$bundle).getMediaData());
                    boolean zIsEmpty = this.this$0.mediaDataList.isEmpty();
                    MediaLiveAlertController mediaLiveAlertController2 = this.this$0;
                    if (!zIsEmpty) {
                        mediaLiveAlertController2.sendLiveAlertData(MediaLiveAlertController.METHOD_SEND);
                    } else {
                        MediaLiveAlertController.access$closeThisEvent(mediaLiveAlertController2);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.livealert.MediaLiveAlertController$release$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC01811 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ MediaLiveAlertController this$0;

        public /* synthetic */ RunnableC01811(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    StatusBarNotification statusBarNotification = this.this$0.sbn;
                    String key = statusBarNotification != null ? statusBarNotification.getKey() : null;
                    OplusNotificationLogger.logMediaSeedling(MediaLiveAlertController.TAG, "release-" + key + ": " + this.this$0.isMediaCardDestroy);
                    MediaLiveAlertController.access$closeThisEvent(this.this$0);
                    MediaLiveAlertController.access$onDestroy(this.this$0);
                    break;
                default:
                    MediaLiveAlertController mediaLiveAlertController = this.this$0;
                    mediaLiveAlertController.showOnStatusBar = false;
                    mediaLiveAlertController.sendLiveAlertData(MediaLiveAlertController.METHOD_SEND);
                    break;
            }
        }
    }

    public MediaLiveAlertController(Context context) {
        this.sysContext = context;
        RunnableC01811 runnableC01811 = new RunnableC01811(1);
        runnableC01811.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarTimeoutRunnable = runnableC01811;
        this.scheduleMediaSeedingEndRunnable = scheduleMediaSeedingEndRunnable.1.INSTANCE;
        this.mediaCurrentState = 1;
        this.isExpRegion = true ^ TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic");
    }

    public static final void access$closeThisEvent(MediaLiveAlertController mediaLiveAlertController) {
        if (mediaLiveAlertController.sendLiveAlertData(METHOD_CANCEL)) {
            mediaLiveAlertController.timeStamp = String.valueOf(System.currentTimeMillis());
        }
        mediaLiveAlertController.myHandler.removeCallbacks(mediaLiveAlertController.statusBarTimeoutRunnable);
    }

    public static final void access$onDestroy(MediaLiveAlertController mediaLiveAlertController) {
        if (mediaLiveAlertController.isMediaCardDestroy) {
            return;
        }
        mediaLiveAlertController.isMediaCardDestroy = true;
        mediaLiveAlertController.activityIntentMap.clear();
        mediaLiveAlertController.myHandler.removeCallbacks(mediaLiveAlertController.statusBarTimeoutRunnable);
        mediaLiveAlertController.myHandler.removeCallbacks(mediaLiveAlertController.scheduleMediaSeedingEndRunnable);
        OplusSysUiSchedMgr.Companion.getInstance().schedForSeedlingEventTriggered(false, EVENT_SCHEDULE_MEDIA_SEEDLING);
    }

    public static final void access$updateNotificationMediaDataList(MediaLiveAlertController mediaLiveAlertController, List list) {
        int i = 0;
        if (!mediaLiveAlertController.mediaLiveAlertData.isShowKeyguard) {
            mediaLiveAlertController.mediaDataList.clear();
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                SeedlingMediaData seedlingMediaData = (SeedlingMediaData) obj;
                if (mediaLiveAlertController.mediaDataList.size() >= 3) {
                    return;
                }
                mediaLiveAlertController.mediaDataList.add(seedlingMediaData);
                i = i2;
            }
            return;
        }
        List list2 = list;
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (Object obj2 : list2) {
            linkedHashMap.put(((SeedlingMediaData) obj2).getPlayerId(), obj2);
        }
        CopyOnWriteArrayList<SeedlingMediaData> copyOnWriteArrayList = mediaLiveAlertController.mediaDataList;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = copyOnWriteArrayList.iterator();
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
        mediaLiveAlertController.mediaDataList.clear();
        mediaLiveAlertController.mediaDataList.addAll(mutableList);
    }

    public static Notification.Action createMediaButton(Icon icon, String str, String str2, MediaAction mediaAction, String str3, Notification.Action action) {
        Notification.Action.Builder builder = new Notification.Action.Builder(icon, str, (PendingIntent) null);
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m(KEY_BTN_ACTION_URI, MEDIA_PROVIDER);
        bundleM.putBundle(KEY_BTN_ACTION_EXTRA, createMediaButtonActionExtra(str2, mediaAction));
        bundleM.putString(KEY_TITLE, str);
        bundleM.putString(KEY_BTN_ACTION_NAME, str2);
        bundleM.putString(KEY_BTN_CONTENT_DESC, str);
        bundleM.putString(KEY_BTN_ANIM, str3 != null ? StringsKt__StringsKt.removePrefix(str3, ASSET_PREFIX) : null);
        bundleM.putParcelable(KEY_BTN_NEXT_ACTION, action);
        return builder.addExtras(bundleM).build();
    }

    public static Bundle createMediaButtonActionExtra(String str, MediaAction mediaAction) {
        if (mediaAction == null || mediaAction.getButtonName() == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("EventType", "MediaEvent");
        bundle.putString("MediaEvent", "MediaEvent");
        bundle.putString(KEY_BTN_MEDIA_ACTION_TYPE, KEY_BTN_MEDIA_ACTION_NAME);
        bundle.putString(KEY_BTN_MEDIA_ACTION_NAME, mediaAction.getButtonName());
        bundle.putString(KEY_BTN_MEDIA_ENTRANCE, KEY_BTN_MEDIA_ENTRANCE_STATUS_BAR);
        bundle.putString(KEY_BTN_MEDIA_PLAY_ID, str);
        return bundle;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0043  */
    /* JADX WARN: Code duplicated, block: B:8:0x0011 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:9:0x0013  */
    public static Notification.Action createMediaCustomButton(String str, MediaAction mediaAction, Context context) {
        Icon buttonIcon = getButtonIcon(mediaAction, context);
        if (buttonIcon == null) {
            if ((mediaAction != null ? mediaAction.getAnimRes() : null) != null) {
                if (mediaAction != null) {
                    String voiceLabel = mediaAction.getVoiceLabel();
                    Notification.Action.Builder builder = new Notification.Action.Builder(buttonIcon, voiceLabel, (PendingIntent) null);
                    Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m(KEY_BTN_ACTION_URI, MEDIA_PROVIDER);
                    bundleM.putBundle(KEY_BTN_ACTION_EXTRA, createMediaButtonActionExtra(str, mediaAction));
                    bundleM.putString(KEY_TITLE, voiceLabel);
                    bundleM.putString(KEY_BTN_ACTION_NAME, str);
                    bundleM.putString(KEY_BTN_CONTENT_DESC, voiceLabel);
                    String animRes = mediaAction.getAnimRes();
                    bundleM.putString(KEY_BTN_ANIM, animRes != null ? StringsKt__StringsKt.removePrefix(animRes, ASSET_PREFIX) : null);
                    Notification.Action actionBuild = builder.addExtras(bundleM).build();
                    actionBuild.getClass();
                    return actionBuild;
                }
            }
        } else if (mediaAction != null) {
            String voiceLabel2 = mediaAction.getVoiceLabel();
            Notification.Action.Builder builder2 = new Notification.Action.Builder(buttonIcon, voiceLabel2, (PendingIntent) null);
            Bundle bundleM2 = FlowControlConfig$.ExternalSyntheticOutline0.m(KEY_BTN_ACTION_URI, MEDIA_PROVIDER);
            bundleM2.putBundle(KEY_BTN_ACTION_EXTRA, createMediaButtonActionExtra(str, mediaAction));
            bundleM2.putString(KEY_TITLE, voiceLabel2);
            bundleM2.putString(KEY_BTN_ACTION_NAME, str);
            bundleM2.putString(KEY_BTN_CONTENT_DESC, voiceLabel2);
            String animRes2 = mediaAction.getAnimRes();
            bundleM2.putString(KEY_BTN_ANIM, animRes2 != null ? StringsKt__StringsKt.removePrefix(animRes2, ASSET_PREFIX) : null);
            Notification.Action actionBuild2 = builder2.addExtras(bundleM2).build();
            actionBuild2.getClass();
            return actionBuild2;
        }
        return new Notification.Action.Builder((Icon) null, (CharSequence) null, (PendingIntent) null).build();
    }

    public static Icon getButtonIcon(MediaAction mediaAction, Context context) {
        HashMap<String, Pair<String, Uri>> iconMap;
        Pair<String, Uri> pair;
        Uri uri;
        Icon iconCreateWithResource;
        Icon icon;
        if (mediaAction != null && (icon = mediaAction.getIcon()) != null) {
            return icon;
        }
        if (mediaAction == null || (iconMap = mediaAction.getIconMap()) == null || (pair = iconMap.get(MediaAction.ICON_DEFAULT)) == null || (uri = (Uri) pair.getSecond()) == null) {
            return null;
        }
        Integer resourceId = UriExtKt.toResourceId(uri);
        return (resourceId == null || (iconCreateWithResource = Icon.createWithResource(context, resourceId.intValue())) == null) ? Icon.createWithContentUri(uri.toString()) : iconCreateWithResource;
    }

    public final void cancel(Bundle bundle) {
        Handler handler = this.myHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.$bundle = bundle;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void destroy(Bundle bundle) {
        Handler handler = this.myHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.$bundle = bundle;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final Context getSysContext() {
        return this.sysContext;
    }

    public final void onEventForLiveAlert(MediaLiveAlertData mediaLiveAlertData) {
        Handler handler = this.myHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(3);
        anonymousClass1.this$0 = this;
        anonymousClass1.$bundle = mediaLiveAlertData;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void onSizeChange(Bundle bundle) {
        Handler handler = this.myHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.$bundle = bundle;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void release() {
        Handler handler = this.myHandler;
        RunnableC01811 runnableC01811 = new RunnableC01811(0);
        runnableC01811.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(runnableC01811);
    }

    public final boolean sendDataToPlugin(String str, StatusBarNotification statusBarNotification) {
        Object obj;
        Bundle bundleCall = null;
        String str2 = "sendDataToPlugin, method=" + str + ", notification.key=" + (statusBarNotification != null ? statusBarNotification.getKey() : null);
        OplusNotificationLogger.logMediaSeedling(TAG, str2);
        if (!Trace.isTagEnabled(4096L)) {
            str2 = null;
        }
        boolean z = true;
        boolean z2 = str2 != null;
        if (z2) {
            TraceUtilsKt.beginSlice(4096L, str2);
        }
        try {
            if (statusBarNotification != null) {
                try {
                    ContentResolver contentResolver = this.sysContext.getContentResolver();
                    if (contentResolver != null) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(KEY_PRIMARY_CONTENT, statusBarNotification);
                        bundleCall = contentResolver.call(PROVIDER, str, (String) null, bundle);
                    }
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
            }
            if (bundleCall == null || bundleCall.getInt(KEY_RESULT_CODE, 0) != -1) {
                z = false;
            }
            obj = Result.constructor-impl(Boolean.valueOf(z));
            if (Result.exceptionOrNull-impl(obj) != null) {
                OplusNotificationLogger.logMediaSeedling(TAG, "provider call sendLiveAlert failure");
            }
            Boolean bool = Boolean.FALSE;
            if (obj instanceof Result.Failure) {
                obj = bool;
            }
            return ((Boolean) obj).booleanValue();
        } finally {
            if (z2) {
                Trace.traceEnd(4096L);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:129:0x045c  */
    /* JADX WARN: Code duplicated, block: B:131:0x0462  */
    /* JADX WARN: Code duplicated, block: B:132:0x0468  */
    /* JADX WARN: Code duplicated, block: B:134:0x046b  */
    /* JADX WARN: Code duplicated, block: B:135:0x046e  */
    /* JADX WARN: Code duplicated, block: B:138:0x0476  */
    /* JADX WARN: Code duplicated, block: B:140:0x047c  */
    /* JADX WARN: Code duplicated, block: B:141:0x0483  */
    /* JADX WARN: Code duplicated, block: B:146:0x0492  */
    /* JADX WARN: Code duplicated, block: B:149:0x0499  */
    /* JADX WARN: Code duplicated, block: B:150:0x049f  */
    /* JADX WARN: Code duplicated, block: B:162:0x04c7  */
    /* JADX WARN: Code duplicated, block: B:164:0x04cd  */
    /* JADX WARN: Code duplicated, block: B:165:0x04d4  */
    /* JADX WARN: Code duplicated, block: B:167:0x04d8  */
    /* JADX WARN: Code duplicated, block: B:168:0x04db  */
    /* JADX WARN: Code duplicated, block: B:173:0x04e9  */
    /* JADX WARN: Code duplicated, block: B:175:0x04ef  */
    /* JADX WARN: Code duplicated, block: B:176:0x04f2  */
    /* JADX WARN: Code duplicated, block: B:178:0x04f6  */
    /* JADX WARN: Code duplicated, block: B:179:0x04f9  */
    /* JADX WARN: Code duplicated, block: B:186:0x050b  */
    /* JADX WARN: Code duplicated, block: B:188:0x0512  */
    /* JADX WARN: Code duplicated, block: B:190:0x0517  */
    /* JADX WARN: Code duplicated, block: B:202:0x0555  */
    /* JADX WARN: Code duplicated, block: B:204:0x055b  */
    /* JADX WARN: Code duplicated, block: B:205:0x0560  */
    /* JADX WARN: Code duplicated, block: B:207:0x0563  */
    /* JADX WARN: Code duplicated, block: B:208:0x0566  */
    /* JADX WARN: Code duplicated, block: B:211:0x056e  */
    /* JADX WARN: Code duplicated, block: B:214:0x0576  */
    /* JADX WARN: Code duplicated, block: B:215:0x057d  */
    /* JADX WARN: Code duplicated, block: B:222:0x0590  */
    /* JADX WARN: Code duplicated, block: B:225:0x0598  */
    /* JADX WARN: Code duplicated, block: B:226:0x059f  */
    public final boolean sendLiveAlertData(String str) {
        int i;
        String string;
        Notification notification;
        Bundle bundle;
        char c;
        long j;
        String str2;
        String string2;
        Icon appIcon;
        String string3;
        MediaButton mediaButton;
        MediaAction prevOrCustom;
        int i2;
        Icon buttonIcon;
        String voiceLabel;
        String voiceLabel2;
        String animRes;
        Notification.Action actionCreateMediaButton;
        MediaButton mediaButton2;
        MediaAction playOrPause;
        int i3;
        String voiceLabel3;
        String str3;
        int i4;
        String voiceLabel4;
        String str4;
        Notification.Action actionCreateMediaButton2;
        MediaButton mediaButton3;
        MediaAction nextOrCustom;
        int i5;
        Icon buttonIcon2;
        String voiceLabel5;
        String str5;
        String animRes2;
        Notification.Action actionCreateMediaButton3;
        String voiceLabel6;
        String str6;
        String str7;
        MediaLiveAlertController mediaLiveAlertController = this;
        if (mediaLiveAlertController.isMediaCardDestroy) {
            mediaLiveAlertController.isMediaCardDestroy = !mediaLiveAlertController.mediaLiveAlertData.getAllowCreateCard();
        }
        boolean z = mediaLiveAlertController.isMediaCardDestroy;
        String str8 = TAG;
        if (z) {
            OplusNotificationLogger.logMediaSeedling(TAG, "Media liveAlert is already destroy");
            return false;
        }
        if (mediaLiveAlertController.mediaDataList.isEmpty() && str.equals(METHOD_SEND)) {
            OplusNotificationLogger.logMediaSeedling(TAG, "Media liveAlert is empty");
            return false;
        }
        int currentUserId = OsBinderCacheUtils.getCurrentUserId();
        int userId = mediaLiveAlertController.sysContext.getUserId();
        Context contextCreateContextAsUser = mediaLiveAlertController.sysContext;
        if (currentUserId != userId) {
            contextCreateContextAsUser = contextCreateContextAsUser.createContextAsUser(UserHandle.of(currentUserId), 4);
        }
        Context context = contextCreateContextAsUser;
        CopyOnWriteArrayList<SeedlingMediaData> copyOnWriteArrayList = mediaLiveAlertController.mediaDataList;
        MediaLiveAlertData mediaLiveAlertData = mediaLiveAlertController.mediaLiveAlertData;
        String packageName = context.getPackageName();
        int i6 = Integer.parseInt("268452006");
        String str9 = mediaLiveAlertController.timeStamp;
        Notification.Builder builder = new Notification.Builder(context, "268452006");
        builder.setStyle(new Notification.MediaStyle());
        builder.setSmallIcon(R$drawable.app_icon_dark);
        builder.setAutoCancel(false);
        Bundle bundle2 = new Bundle();
        SeedlingMediaData seedlingMediaData = (SeedlingMediaData) CollectionsKt___CollectionsKt.firstOrNull(copyOnWriteArrayList);
        String str10 = MEDIA_PROVIDER;
        Notification.Builder builder2 = builder;
        if (seedlingMediaData == null) {
            string = "";
            copyOnWriteArrayList = copyOnWriteArrayList;
        } else {
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            if (SeedlingMediaData.Companion.isPlayState(seedlingMediaData.getState())) {
                copyOnWriteArrayList = copyOnWriteArrayList;
                mediaLiveAlertController.lastStatusBarPlayerId = seedlingMediaData.getPlayerId();
                mediaLiveAlertController.showOnStatusBar = true;
                mediaLiveAlertController.myHandler.removeCallbacks(mediaLiveAlertController.statusBarTimeoutRunnable);
            } else {
                if (mediaLiveAlertController.mediaCurrentState == 1 && !mediaLiveAlertController.myHandler.hasCallbacks(mediaLiveAlertController.statusBarTimeoutRunnable) && mediaLiveAlertController.showOnStatusBar) {
                    mediaLiveAlertController.myHandler.postDelayed(mediaLiveAlertController.statusBarTimeoutRunnable, 5000L);
                }
                if (!Intrinsics.areEqual(mediaLiveAlertController.lastStatusBarPlayerId, seedlingMediaData.getPlayerId())) {
                    mediaLiveAlertController.showOnStatusBar = false;
                }
            }
            if (mediaLiveAlertData.isMediaSeedlingShowOnStatusBar && mediaLiveAlertController.showOnStatusBar) {
                jSONArray.put(8);
            }
            if (mediaLiveAlertData.getShouldNotificationSeedlingCardHidden()) {
                i = 16;
            } else {
                i = 16;
                jSONArray.put(16);
            }
            if (mediaLiveAlertData.isUmsSeedlingShowOnLockScreen) {
                jSONArray2.put(i);
            }
            string = new JSONObject().put("id", seedlingMediaData.getPlayerId()).put(KEY_OPTION_SOURCE_PKG, seedlingMediaData.getPackageName()).put("isMilestone", mediaLiveAlertData.isMilestone || mediaLiveAlertData.isUpdateMedia).put(KEY_OPTION_SHOW_HOST_MAP, jSONArray).put(KEY_OPTION_SHOW_HOST_MAP_LOCKED, jSONArray2).put(KEY_OPTION_EXTENSIBLE_ACTION_MAP, new JSONObject().put("immersiveCardType", 2).put(KEY_OPTION_RECEIVER_URI, MEDIA_PROVIDER)).put(KEY_USER_ID, seedlingMediaData.getUserId()).toString();
            OplusNotificationLogger.logMediaSeedlingDebug(TAG, "createLiveAlertOptions, optionJson=" + string);
        }
        bundle2.putString(KEY_LA_OPTIONS, string);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        Iterator<SeedlingMediaData> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            SeedlingMediaData next = it.next();
            Bundle bundle3 = new Bundle();
            PendingIntent activityAsUser = mediaLiveAlertController.activityIntentMap.get(next.getPlayerId());
            if (activityAsUser == null) {
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(next.getPackageName());
                if (launchIntentForPackage != null) {
                    OplusIntent.setOplusFlags(launchIntentForPackage, OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT);
                    UserHandle userHandleOf = UserHandle.of(next.getUserId());
                    bundle = bundle2;
                    c = 16;
                    j = 1000;
                    str2 = str10;
                    activityAsUser = PendingIntent.getActivityAsUser(context, 0, launchIntentForPackage, 201326592, null, userHandleOf);
                } else {
                    bundle = bundle2;
                    c = 16;
                    j = 1000;
                    str2 = str10;
                    activityAsUser = null;
                }
                mediaLiveAlertController.activityIntentMap.put(next.getPlayerId(), activityAsUser);
            } else {
                next = next;
                bundle = bundle2;
                str9 = str9;
                builder2 = builder2;
                c = 16;
                j = 1000;
                it = it;
                str2 = str10;
            }
            Bundle bundle4 = new Bundle();
            Bundle bundle5 = new Bundle();
            Bundle bundle6 = new Bundle();
            Icon artworkIcon = next.getArtworkIcon();
            if (artworkIcon == null) {
                artworkIcon = Icon.createWithResource(context, R$drawable.artwork_dark);
            }
            bundle6.putParcelable(KEY_CAPSULE_ICON, artworkIcon);
            boolean z2 = mediaLiveAlertController.isExpRegion;
            if (z2) {
                string2 = context.getString(R$string.shapeArtworkRoundRectangle);
                string2.getClass();
            } else {
                string2 = context.getString(R$string.shapeArtworkCircle);
                string2.getClass();
            }
            bundle6.putString(KEY_SHAPE_ARTWORK, string2);
            bundle6.putString(KEY_ALBUM_ROTATION_DIRECTION, next.getAlbumRotationDirection());
            bundle5.putParcelable(KEY_PRIMARY_ICON, bundle6);
            SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
            String str11 = packageName;
            int i7 = i6;
            bundle5.putBoolean(KEY_PRIMARY_ACTIVE, companion.isPlayState(companion.isConnectingState(next.getState()) ? next.getOldState() : next.getState()));
            bundle4.putParcelable(KEY_PRIMARY_DATA, bundle5);
            Bundle bundle7 = new Bundle();
            String str12 = str8;
            bundle7.putBoolean(KEY_CAPSULE_SHOW_LOTTIE, false);
            bundle4.putParcelable(KEY_CAPSULE_EXTRA, bundle7);
            CharSequence song = next.getSong();
            if (song != null) {
                bundle4.putString(KEY_PRIMARY_DESC, song.toString());
            }
            bundle4.putString(KEY_PRIMARY_BACKGROUND, next.getArtworkBgColor());
            bundle4.putParcelable(KEY_PRIMARY_INTENT, activityAsUser);
            ArrayList<? extends Parcelable> arrayList2 = arrayList;
            bundle3.putParcelable(KEY_CAPSULE, bundle4);
            Bundle bundle8 = new Bundle();
            Bundle bundle9 = new Bundle();
            if ("com.spotify.music".equals(next.getPackageName())) {
                appIcon = Icon.createWithResource(context, R$drawable.spotify_logo);
            } else {
                appIcon = next.getAppIcon();
                if (appIcon == null) {
                    appIcon = Icon.createWithResource(context, R$drawable.app_icon_dark);
                }
            }
            appIcon.getClass();
            Parcelable parcelable = activityAsUser;
            Bundle bundle10 = new Bundle();
            Icon artworkIcon2 = next.getArtworkIcon();
            if (artworkIcon2 == null) {
                artworkIcon2 = Icon.createWithResource(context, R$drawable.artwork_dark);
            }
            bundle10.putParcelable(KEY_CARD_PICTURE, artworkIcon2);
            if (z2) {
                string3 = context.getString(R$string.shapeArtworkRoundRectangle);
                string3.getClass();
            } else {
                string3 = context.getString(R$string.shapeArtworkCircle);
                string3.getClass();
            }
            bundle10.putString(KEY_SHAPE_ARTWORK, string3);
            bundle10.putString(KEY_ALBUM_ROTATION_DIRECTION, next.getAlbumRotationDirection());
            bundle9.putParcelable(KEY_PRIMARY_ICON, bundle10);
            bundle9.putString(KEY_ALBUM_ROTATION_DIRECTION, next.getAlbumRotationDirection());
            Bundle bundle11 = new Bundle();
            bundle11.putParcelable(KEY_CARD_RIGHT_ICON, appIcon);
            bundle9.putParcelable(KEY_CARD_PIC, bundle11);
            String song2 = next.getLyricData().getSong();
            if (song2 == null) {
                CharSequence song3 = next.getSong();
                song2 = song3 != null ? song3.toString() : null;
            }
            bundle9.putString(KEY_TITLE, song2);
            String artist = next.getLyricData().getArtist();
            if (artist == null) {
                CharSequence artist2 = next.getArtist();
                artist = artist2 != null ? artist2.toString() : null;
            }
            bundle9.putString(KEY_PRIMARY_CONTENT, artist);
            Bundle bundle12 = new Bundle();
            if (next.isSpotifyWhichSupportsOplusMediaController() && next.isRecommendation()) {
                bundle12.putParcelable(KEY_PRIMARY_CONTENT_ICON, Icon.createWithResource(context, R$drawable.spotify_enhancebadge));
                bundle12.putString(KEY_PRIMARY_CONTENT_ICON_VOICE_LABEL, context.getString(R$string.spotify_smart_shuffle_voice_label));
            }
            bundle9.putParcelable(KEY_PRIMARY_CONTENT_LABEL, bundle12);
            long jComputerPosition = next.computerPosition();
            Bundle bundleM = CommunalWidgetHost$.ExternalSyntheticOutline0.m(next.getDuration() > 0 ? MathKt.roundToInt((jComputerPosition * 1000.0f) / next.getDuration()) : 0, "value");
            bundleM.putBoolean(KEY_SEEKBAR_AVAILABLE, next.getSeekAvailable());
            bundleM.putBoolean(KEY_SEEKBAR_ENABLE, next.getSeekEnable());
            bundleM.putLong(KEY_PRIMARY_PROGRESS_CURRENT, jComputerPosition);
            bundleM.putLong(KEY_PRIMARY_PROGRESS_TOTAL, next.getDuration() / j);
            bundle9.putParcelable(KEY_PRIMARY_PROGRESS, bundleM);
            bundle9.putString(KEY_PRIMARY_MODEL, KEY_PRIMARY_MODEL_NORMAL);
            bundle9.putString("pkg", next.getPackageName());
            bundle9.putBoolean(KEY_PRIMARY_ACTIVE, companion.isPlayState(companion.isConnectingState(next.getState()) ? next.getOldState() : next.getState()));
            bundle8.putParcelable(KEY_PRIMARY_DATA, bundle9);
            Bundle bundle13 = new Bundle();
            ArrayList<? extends Parcelable> arrayList3 = new ArrayList<>(6);
            boolean zEquals = "com.spotify.music".equals(next.getPackageName());
            if (zEquals) {
                mediaButton = next.getMediaButton();
                if (mediaButton != null) {
                    prevOrCustom = mediaButton.getPrevOrCustom();
                } else {
                    prevOrCustom = null;
                }
                if (zEquals) {
                    i2 = R$drawable.spotify_previous_dark_disable;
                } else {
                    i2 = R$drawable.previous_dark_disable;
                }
                buttonIcon = getButtonIcon(prevOrCustom, context);
                if (buttonIcon == null) {
                    buttonIcon = Icon.createWithResource(context, i2);
                }
                if (zEquals) {
                    voiceLabel = context.getString(R$string.controls_media_button_prev_disabled);
                } else {
                    voiceLabel = next.getVoiceLabel();
                }
                voiceLabel.getClass();
                if (prevOrCustom != null || (voiceLabel2 = prevOrCustom.getVoiceLabel()) == null) {
                    voiceLabel2 = voiceLabel;
                }
                String playerId = next.getPlayerId();
                if (prevOrCustom != null) {
                    animRes = prevOrCustom.getAnimRes();
                } else {
                    animRes = null;
                }
                actionCreateMediaButton = createMediaButton(buttonIcon, voiceLabel2, playerId, prevOrCustom, animRes, null);
            } else {
                MediaButton mediaButton4 = next.getMediaButton();
                if ((mediaButton4 != null ? mediaButton4.getPrevOrCustom() : null) != null) {
                    mediaButton = next.getMediaButton();
                    if (mediaButton != null) {
                        prevOrCustom = mediaButton.getPrevOrCustom();
                    } else {
                        prevOrCustom = null;
                    }
                    if (zEquals) {
                        i2 = R$drawable.spotify_previous_dark_disable;
                    } else {
                        i2 = R$drawable.previous_dark_disable;
                    }
                    buttonIcon = getButtonIcon(prevOrCustom, context);
                    if (buttonIcon == null) {
                        buttonIcon = Icon.createWithResource(context, i2);
                    }
                    if (zEquals) {
                        voiceLabel = context.getString(R$string.controls_media_button_prev_disabled);
                    } else {
                        voiceLabel = next.getVoiceLabel();
                    }
                    voiceLabel.getClass();
                    if (prevOrCustom != null) {
                        voiceLabel2 = voiceLabel;
                    } else {
                        voiceLabel2 = voiceLabel;
                    }
                    String playerId2 = next.getPlayerId();
                    if (prevOrCustom != null) {
                        animRes = prevOrCustom.getAnimRes();
                    } else {
                        animRes = null;
                    }
                    actionCreateMediaButton = createMediaButton(buttonIcon, voiceLabel2, playerId2, prevOrCustom, animRes, null);
                } else {
                    actionCreateMediaButton = new Notification.Action.Builder((Icon) null, (CharSequence) null, (PendingIntent) null).build();
                }
            }
            arrayList3.add(0, actionCreateMediaButton);
            if (zEquals) {
                mediaButton2 = next.getMediaButton();
                if (mediaButton2 != null) {
                    playOrPause = mediaButton2.getPlayOrPause();
                } else {
                    playOrPause = null;
                }
                if (zEquals) {
                    i3 = R$drawable.spotify_pause_dark;
                } else {
                    i3 = R$drawable.pause_dark;
                }
                Icon iconCreateWithResource = Icon.createWithResource(context, i3);
                if (playOrPause != null || (voiceLabel3 = playOrPause.getVoiceLabel()) == null) {
                    voiceLabel3 = next.getVoiceLabel();
                }
                if (zEquals) {
                    str3 = LOTTIE_PLAY_SPOTIFY;
                } else {
                    str3 = LOTTIE_PLAY;
                }
                if (zEquals) {
                    i4 = R$drawable.spotify_play_dark;
                } else {
                    i4 = R$drawable.play_dark;
                }
                Icon iconCreateWithResource2 = Icon.createWithResource(context, i4);
                if (playOrPause != null || (voiceLabel4 = playOrPause.getVoiceLabel()) == null) {
                    voiceLabel4 = next.getVoiceLabel();
                }
                String str13 = voiceLabel4;
                if (zEquals) {
                    str4 = LOTTIE_PAUSE_SPOTIFY;
                } else {
                    str4 = LOTTIE_PAUSE;
                }
                actionCreateMediaButton2 = createMediaButton(iconCreateWithResource, voiceLabel3, next.getPlayerId(), playOrPause, str3, createMediaButton(iconCreateWithResource2, str13, next.getPlayerId(), playOrPause, str4, null));
            } else {
                MediaButton mediaButton5 = next.getMediaButton();
                if ((mediaButton5 != null ? mediaButton5.getPlayOrPause() : null) != null) {
                    mediaButton2 = next.getMediaButton();
                    if (mediaButton2 != null) {
                        playOrPause = mediaButton2.getPlayOrPause();
                    } else {
                        playOrPause = null;
                    }
                    if (zEquals) {
                        i3 = R$drawable.spotify_pause_dark;
                    } else {
                        i3 = R$drawable.pause_dark;
                    }
                    Icon iconCreateWithResource3 = Icon.createWithResource(context, i3);
                    if (playOrPause != null) {
                        voiceLabel3 = next.getVoiceLabel();
                    } else {
                        voiceLabel3 = next.getVoiceLabel();
                    }
                    if (zEquals) {
                        str3 = LOTTIE_PLAY_SPOTIFY;
                    } else {
                        str3 = LOTTIE_PLAY;
                    }
                    if (zEquals) {
                        i4 = R$drawable.spotify_play_dark;
                    } else {
                        i4 = R$drawable.play_dark;
                    }
                    Icon iconCreateWithResource4 = Icon.createWithResource(context, i4);
                    if (playOrPause != null) {
                        voiceLabel4 = next.getVoiceLabel();
                    } else {
                        voiceLabel4 = next.getVoiceLabel();
                    }
                    String str14 = voiceLabel4;
                    if (zEquals) {
                        str4 = LOTTIE_PAUSE_SPOTIFY;
                    } else {
                        str4 = LOTTIE_PAUSE;
                    }
                    actionCreateMediaButton2 = createMediaButton(iconCreateWithResource3, voiceLabel3, next.getPlayerId(), playOrPause, str3, createMediaButton(iconCreateWithResource4, str14, next.getPlayerId(), playOrPause, str4, null));
                } else {
                    actionCreateMediaButton2 = new Notification.Action.Builder((Icon) null, (CharSequence) null, (PendingIntent) null).build();
                }
            }
            arrayList3.add(1, actionCreateMediaButton2);
            if (zEquals) {
                mediaButton3 = next.getMediaButton();
                if (mediaButton3 != null) {
                    nextOrCustom = mediaButton3.getNextOrCustom();
                } else {
                    nextOrCustom = null;
                }
                if (zEquals) {
                    i5 = R$drawable.spotify_next_dark_disable;
                } else {
                    i5 = R$drawable.next_dark;
                }
                buttonIcon2 = getButtonIcon(nextOrCustom, context);
                if (buttonIcon2 == null) {
                    buttonIcon2 = Icon.createWithResource(context, i5);
                }
                Icon icon = buttonIcon2;
                if (zEquals) {
                    voiceLabel5 = context.getString(R$string.controls_media_button_next_disabled);
                } else {
                    voiceLabel5 = next.getVoiceLabel();
                }
                voiceLabel5.getClass();
                if (nextOrCustom != null || (voiceLabel6 = nextOrCustom.getVoiceLabel()) == null) {
                    str5 = voiceLabel5;
                } else {
                    str5 = voiceLabel6;
                }
                String playerId3 = next.getPlayerId();
                if (nextOrCustom != null) {
                    animRes2 = nextOrCustom.getAnimRes();
                } else {
                    animRes2 = null;
                }
                actionCreateMediaButton3 = createMediaButton(icon, str5, playerId3, nextOrCustom, animRes2, null);
            } else {
                MediaButton mediaButton6 = next.getMediaButton();
                if ((mediaButton6 != null ? mediaButton6.getNextOrCustom() : null) != null) {
                    mediaButton3 = next.getMediaButton();
                    if (mediaButton3 != null) {
                        nextOrCustom = mediaButton3.getNextOrCustom();
                    } else {
                        nextOrCustom = null;
                    }
                    if (zEquals) {
                        i5 = R$drawable.spotify_next_dark_disable;
                    } else {
                        i5 = R$drawable.next_dark;
                    }
                    buttonIcon2 = getButtonIcon(nextOrCustom, context);
                    if (buttonIcon2 == null) {
                        buttonIcon2 = Icon.createWithResource(context, i5);
                    }
                    Icon icon2 = buttonIcon2;
                    if (zEquals) {
                        voiceLabel5 = context.getString(R$string.controls_media_button_next_disabled);
                    } else {
                        voiceLabel5 = next.getVoiceLabel();
                    }
                    voiceLabel5.getClass();
                    if (nextOrCustom != null) {
                        str5 = voiceLabel5;
                    } else {
                        str5 = voiceLabel5;
                    }
                    String playerId4 = next.getPlayerId();
                    if (nextOrCustom != null) {
                        animRes2 = nextOrCustom.getAnimRes();
                    } else {
                        animRes2 = null;
                    }
                    actionCreateMediaButton3 = createMediaButton(icon2, str5, playerId4, nextOrCustom, animRes2, null);
                } else {
                    actionCreateMediaButton3 = new Notification.Action.Builder((Icon) null, (CharSequence) null, (PendingIntent) null).build();
                }
            }
            arrayList3.add(2, actionCreateMediaButton3);
            String playerId5 = next.getPlayerId();
            MediaButton mediaButton7 = next.getMediaButton();
            arrayList3.add(3, createMediaCustomButton(playerId5, mediaButton7 != null ? mediaButton7.getCustom1() : null, context));
            String playerId6 = next.getPlayerId();
            long duration = next.getDuration();
            Notification.Action.Builder builder3 = new Notification.Action.Builder((Icon) null, KEY_BTN_NAME_PROGRESS_BAR, (PendingIntent) null);
            Bundle bundleM2 = FlowControlConfig$.ExternalSyntheticOutline0.m(KEY_BTN_ACTION_URI, str2);
            Bundle bundle14 = new Bundle();
            bundle14.putString("EventType", "MediaEvent");
            bundle14.putString("MediaEvent", "MediaEvent");
            bundle14.putString(KEY_BTN_MEDIA_ACTION_TYPE, KEY_BTN_MEDIA_ACTION_SEEK);
            String str15 = KEY_BTN_MEDIA_ACTION_TYPE;
            bundle14.putString(KEY_BTN_MEDIA_ACTION_NAME, KEY_BTN_MEDIA_ACTION_SEEK);
            bundle14.putString(KEY_BTN_MEDIA_PLAY_ID, playerId6);
            String str16 = "EventType";
            bundle14.putLong(KEY_BTN_MEDIA_ACTION_SEEK, 0L);
            bundle14.putLong(KEY_BTN_MEDIA_TOTAL_DURATION, duration);
            bundleM2.putBundle(KEY_BTN_ACTION_EXTRA, bundle14);
            bundleM2.putString(KEY_TITLE, KEY_BTN_NAME_PROGRESS_BAR);
            bundleM2.putString(KEY_BTN_ACTION_NAME, playerId6);
            bundleM2.putString(KEY_BTN_CONTENT_DESC, KEY_BTN_NAME_PROGRESS_BAR);
            arrayList3.add(4, builder3.addExtras(bundleM2).build());
            String playerId7 = next.getPlayerId();
            MediaButton mediaButton8 = next.getMediaButton();
            arrayList3.add(5, createMediaCustomButton(playerId7, mediaButton8 != null ? mediaButton8.getCustom0() : null, context));
            bundle13.putParcelableArrayList(KEY_CARD_BTN_LIST, arrayList3);
            ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>(6);
            Iterator it2 = next.getRecommendations().iterator();
            int i8 = 0;
            while (it2.hasNext()) {
                Object next2 = it2.next();
                int i9 = i8 + 1;
                if (i8 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                MediaRecommendationData mediaRecommendationData = (MediaRecommendationData) next2;
                Uri artworkUri = mediaRecommendationData.getArtworkUri();
                if (artworkUri != null) {
                    Notification.Action.Builder builder4 = new Notification.Action.Builder(Icon.createWithContentUri(artworkUri), mediaRecommendationData.getVoiceLabel(), (PendingIntent) null);
                    Bundle bundleM3 = FlowControlConfig$.ExternalSyntheticOutline0.m(KEY_BTN_ACTION_URI, str2);
                    String playerId8 = next.getPlayerId();
                    String voiceLabel7 = mediaRecommendationData.getVoiceLabel();
                    Bundle bundle15 = new Bundle();
                    str7 = str16;
                    bundle15.putString(str7, "MediaEvent");
                    bundle15.putString("MediaEvent", "MediaEvent");
                    str6 = str15;
                    bundle15.putString(str6, KEY_BTN_MEDIA_RECOMMENDATION);
                    bundle15.putInt(KEY_BTN_MEDIA_RECOMMENDATION, i8);
                    bundle15.putString(KEY_BTN_MEDIA_PLAY_ID, playerId8);
                    bundle15.putString(KEY_BTN_MEDIA_LABEL, voiceLabel7);
                    bundleM3.putBundle(KEY_BTN_ACTION_EXTRA, bundle15);
                    arrayList4.add(builder4.addExtras(bundleM3).build());
                } else {
                    str6 = str15;
                    str7 = str16;
                }
                str15 = str6;
                str16 = str7;
                i8 = i9;
                it2 = it2;
                str2 = str2;
            }
            String str17 = str2;
            bundle13.putParcelableArrayList(KEY_CARD_PIC_LIST, arrayList4);
            bundle8.putParcelable(KEY_EXTENSION_DATA, bundle13);
            CharSequence song4 = next.getSong();
            if (song4 != null) {
                bundle8.putString(KEY_PRIMARY_DESC, song4.toString());
            }
            bundle8.putString(KEY_PRIMARY_BACKGROUND, next.getArtworkBgColor());
            bundle8.putParcelable(KEY_PRIMARY_INTENT, parcelable);
            Bundle bundle16 = new Bundle();
            String originalLines = next.getLyricData().getOriginalLines();
            if (originalLines != null) {
                bundle16.putString(KEY_LYRIC, originalLines);
            }
            bundle16.putBoolean(KEY_LYRIC_SUPPORTED, next.getLyricSupport() && (next.getLyricData().getDisplayPolicy() & 16) != 0);
            bundle16.putBoolean(KEY_ART_FULLSCREEN_ENABLE, FeatureOption.isMediaImmersiveFullEnable);
            bundle8.putParcelable("extra", bundle16);
            bundle8.putString(KEY_UNIQUE_ID, next.getPlayerId());
            bundle3.putParcelable(KEY_CARD, bundle8);
            arrayList2.add(bundle3);
            arrayList = arrayList2;
            it = it;
            str9 = str9;
            builder2 = builder2;
            bundle2 = bundle;
            str10 = str17;
            packageName = str11;
            i6 = i7;
            str8 = str12;
            mediaLiveAlertController = this;
        }
        String str18 = packageName;
        Bundle bundle17 = bundle2;
        Notification.Builder builder5 = builder2;
        OplusNotificationLogger.logMediaPanelNormal(str8, "createLiveAlertMediaData, userid:" + context.getUserId() + ", playArray=" + copyOnWriteArrayList);
        Bundle bundle18 = new Bundle();
        bundle18.putParcelableArrayList(KEY_PLAYER_ARR, arrayList);
        bundle17.putBundle(KEY_MEDIA_DATA, bundle18);
        builder5.addExtras(bundle17);
        StatusBarNotification statusBarNotification = new StatusBarNotification(str18, str18, i6, str9, 0, 0, 0, builder5.build(), UserHandle.of(context.getUserId()), 0L);
        String key = statusBarNotification.getKey();
        StatusBarNotification statusBarNotification2 = this.sbn;
        if (Intrinsics.areEqual(key, statusBarNotification2 != null ? statusBarNotification2.getKey() : null)) {
            Notification notification2 = statusBarNotification.getNotification();
            StatusBarNotification statusBarNotification3 = this.sbn;
            notification2.deleteIntent = (statusBarNotification3 == null || (notification = statusBarNotification3.getNotification()) == null) ? null : notification.deleteIntent;
        } else {
            this.myHandler.removeCallbacks(this.scheduleMediaSeedingEndRunnable);
            OplusSysUiSchedMgr.Companion.getInstance().schedForSeedlingEventTriggered(true, EVENT_SCHEDULE_MEDIA_SEEDLING);
            this.myHandler.postDelayed(this.scheduleMediaSeedingEndRunnable, 1000L);
            Bundle bundle19 = new Bundle();
            bundle19.putString("EventType", KEY_BTN_CLOSE_ONCE_EVENT);
            bundle19.putString(KEY_SBN_KEY, statusBarNotification.getKey());
            statusBarNotification.getNotification().deleteIntent = PendingIntent.getService(this.sysContext, bundle19.hashCode(), new Intent(MEDIA_ACTION).setPackage(context.getPackageName()).putExtras(bundle19), 67108864);
            sendDataToPlugin(METHOD_CANCEL, this.sbn);
        }
        this.sbn = statusBarNotification;
        return sendDataToPlugin(str, statusBarNotification);
    }
}
