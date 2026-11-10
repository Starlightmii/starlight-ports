package com.oplus.systemui.seedlingservice.utils;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.systemui.seedlingservice.R$string;
import com.oplus.systemui.seedlingservice.datamanager.UiData;
import com.oplus.systemui.seedlingservice.mediaControl.LyricData;
import com.oplus.systemui.seedlingservice.mediaControl.MediaAction;
import com.oplus.systemui.seedlingservice.mediaControl.MediaButton;
import com.oplus.systemui.seedlingservice.mediaControl.MediaGlobalExtraDataManager;
import com.oplus.systemui.seedlingservice.mediaControl.MediaRecommendationData;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingMediaDataHandleUtils {
    private static final int BITMAP_COMPRESS_VALUE = 100;
    private static final int BUTTON_SIZE_FIVE = 5;
    private static final int BUTTON_SIZE_FOUR = 4;
    private static final int BUTTON_SIZE_ONE = 1;
    private static final int BUTTON_SIZE_THREE = 3;
    private static final int BUTTON_SIZE_TWO = 2;
    public static final String CARD_ENTRANCE = "cardEntrance";
    private static final String CIRCLE = "circle";
    private static final String HAS_RECOMMENDATION_PREFIX = "hasRecommendation";
    private static final String KEY_ACTION = "action";
    private static final String KEY_ALBUM_ROTATION_DIRECTION = "rotate";
    public static final String KEY_ALLOW_CREATE_CARD = "allowCreateCard";
    private static final String KEY_ANIM_RES = "animRes";
    private static final String KEY_APP_ICON_RES_ID = "appIconResId";
    private static final String KEY_APP_ICON_URI = "appIconUri";
    private static final String KEY_ARTWORK_BACKGROUND_COLOR = "artworkBackgroundUri";
    private static final String KEY_ARTWORK_URI = "artworkUri";
    private static final String KEY_ARTWORK_URI_CONTENT_CHANGED = "artworkUriContentChanged";
    private static final String KEY_AUTOPLAY = "autoplay";
    private static final String KEY_BACKGROUND_COLOR = "bgColor";
    private static final String KEY_BG_IMAGE_URI = "bg-image";
    private static final String KEY_BUTTON_NAME = "buttonName";
    private static final String KEY_CAPSULE_ICON = "smallPic";
    private static final String KEY_CAPSULE_TITLE = "title";
    private static final String KEY_CARD_APP_ICON = "appIcon";
    private static final String KEY_CARD_ARTIST = "artist";
    private static final String KEY_CARD_BUTTON_ANIM = "buttonAnim";
    private static final String KEY_CARD_BUTTON_ICON = "buttonIcon";
    private static final String KEY_CARD_CHECKED = "checked";
    private static final String KEY_CARD_CUSTOM0 = "custom0";
    private static final String KEY_CARD_CUSTOM1 = "custom1";
    private static final String KEY_CARD_HAS_BUTTON = "hasButton";
    private static final String KEY_CARD_ICON_OFF = "iconOff";
    private static final String KEY_CARD_ICON_ON = "iconOn";
    private static final String KEY_CARD_MEDIA_PLAYER_ID = "mediaId";
    private static final String KEY_CARD_NEXT_OR_CUSTOM = "nextOrCustom";
    private static final String KEY_CARD_PICTURE = "picture";
    private static final String KEY_CARD_PLAY_OR_PAUSE = "playOrPause";
    private static final String KEY_CARD_PREVIOUS_OR_CUSTOM = "previousOrCustom";
    private static final String KEY_CARD_SLIDER_DURATION = "duration";
    private static final String KEY_CARD_SLIDER_VALUE = "value";
    private static final String KEY_CARD_SONG_NAME = "songName";
    private static final String KEY_CARD_TRANSITION_OFF = "transitionOff";
    private static final String KEY_CARD_TRANSITION_ON = "transitionOn";
    private static final String KEY_CARD_TYPE = "type";
    private static final String KEY_CARD_VOICE_LABEL = "voiceLabel";
    public static final String KEY_CURRENT_LYRIC = "currentLyric";
    private static final String KEY_DEVICE_ICON_URI = "deviceIconUri";
    private static final String KEY_DEVICE_ICON_URI_CHANGED = "deviceIconUriChanged";
    private static final String KEY_DEVICE_SHOW = "deviceShow";
    public static final String KEY_DISABLE_SEEDLING_FLAGS = "disableSeedlingFlags";
    private static final String KEY_FIRST = "first";
    private static final String KEY_ICON_MAP = "iconMap";
    private static final String KEY_IS_AUTO_PLAY = "isAutoPlay";
    public static final String KEY_IS_FOLD = "isFold";
    public static final String KEY_IS_FOLD_REMAP_DISABLED = "isFoldRemapDisabled";
    public static final String KEY_IS_MEDIA_SEEDLING_SHOW_ON_STATUS_BAR = "isMediaSeedlingShowOnStatusBar";
    public static final String KEY_IS_MILESTONE = "isMilestone";
    public static final String KEY_IS_OCCLUDED = "isOccluded";
    public static final String KEY_IS_PANORAMIC_AOD = "isPanoramicAod";
    private static final String KEY_IS_RECOMMENDATIONS = "isRecommendations";
    public static final String KEY_IS_SCREEN_ON = "isScreenOn";
    public static final String KEY_IS_SCREEN_TURNING_ON = "isScreenTurningOn";
    public static final String KEY_IS_SHOW_KEYGUARD = "isShowKeyguard";
    private static final String KEY_IS_SHOW_VOICE_PRINT = "spectrumShow";
    private static final String KEY_IS_SPOTIFY_WHICH_SUPPORTS_OPLUS_MEDIA_CONTROLLER = "isSpotifyWhichSupportsOplusMediaController";
    private static final String KEY_IS_STANDARD = "isStandard";
    public static final String KEY_IS_UMS_SEEDLING_SHOW_ON_LOCK_SCREEN = "isUmsSeedlingShowOnLockScreen";
    public static final String KEY_IS_UPDATE_MEDIA = "isUpdateMedia";
    private static final String KEY_LAST_POSITION_UPDATE_TIME = "lastPositionUpdateTime";
    private static final String KEY_LAUNCH_INTENT_PACKAGE = "package";
    private static final String KEY_LAUNCH_INTENT_URI = "uri";
    private static final String KEY_LOOP = "loop";
    private static final String KEY_MEDIA_BUTTON = "mediaButton";
    private static final String KEY_MEDIA_BUTTON_URI_CHANGED = "mediaButtonUriChanged";
    private static final String KEY_MEDIA_ITEM = "mediaItem";
    private static final String KEY_MEDIA_LIST = "mediaList";
    public static final String KEY_NOTIFICATION_SORTED_ALLOWED = "notificationSortedAllowed";
    private static final String KEY_OLD_STATE = "oldState";
    public static final String KEY_ORIGINAL_LYRIC = "originalLyric";
    private static final String KEY_PACKAGE_NAME = "packageName";
    private static final String KEY_PLAYBACK_SPEED = "playbackSpeed";
    private static final String KEY_PLAYER_ARR = "playerArr";
    private static final String KEY_PLAY_ID = "playId";
    private static final String KEY_PLAY_STATE = "pageState";
    private static final String KEY_POSITION = "position";
    private static final String KEY_RECOMMENDATIONS = "recommendations";
    public static final String KEY_RELOAD_PICTURES = "reloadPictures";
    private static final String KEY_SECOND = "second";
    private static final String KEY_SEEKBAR_AVAILABLE = "seekAvailable";
    private static final String KEY_SEEKBAR_ENABLE = "seekEnable";
    private static final String KEY_SHAPE_ARTWORK = "shapeArtWork";
    public static final String KEY_SHOULD_NOTIFICATION_SEEDLING_CARD_HIDDEN = "shouldNotificationSeedlingCardHidden";
    public static final String KEY_SHOULD_PLAY_ANIM = "shouldPlayAnim";
    public static final String KEY_SHOULD_SECOND_HOME_SEEDLING_CARD_HIDDEN = "shouldSecondHomeSeedlingCardHidden";
    public static final String KEY_SHOULD_SHOW_LYRIC = "shouldShowLyric";
    private static final String KEY_SHOW_APP_LOGO_ON_ALBUM_ARTWORK = "showAppLogoOnAlbumArtwork";
    private static final String KEY_SHOW_SMALL_ICON_BEFORE_ARTIST = "showSmallIconBeforeArtist";
    private static final String KEY_SMALL_ICON_BEFORE_ARTIST = "smallIconBeforeArtist";
    private static final String KEY_SMALL_ICON_BEFORE_ARTIST_VOICE_LABEL = "smallIconBeforeArtistVoiceLabel";
    public static final String KEY_SONG_INFO = "songInfo";
    private static final String KEY_STATE = "state";
    private static final String KEY_STATIC_VOICE_PRINT_PLAYING = "staticVoicePrintPlayOrPause";
    public static final String KEY_STATIC_VOICE_PRINT_SHOW = "staticVoicePrintShow";
    private static final String KEY_VOICE_ANIM_DURATION = "interval";
    private static final String KEY_VOICE_LABEL = "voiceLabel";
    private static final String KEY_VOICE_PRINT = "spectrumIcon";
    private static final String KEY_VOICE_PRINT_IS_SHOW = "voicePrintIsShow";
    public static final int MAX_RECOMMENDATION_COUNT = 6;
    public static final String METHOD_RECOMMENDATION_ONCLICK = "recommendationOnClick";
    public static final String METHOD_SLIDER_CHANGE = "onSliderChange";
    public static final String METHOD_SWIPER_ONCLICK = "clickSwiper";
    public static final int MILLISECOND_TO_SECOND = 1000;
    public static final String PARAM_INDEX = "index";
    public static final String PARAM_MEDIA_ID = "mediaId";
    public static final String PARAM_SLIDER_VALUE = "value";
    private static final String PLAYBACK_STATE_EXTRA_KEY_IS_RECOMMENDATION = "com.spotify.music.extra.IS_RECOMMENDATION";
    private static final String RECOMMENDATION_PREFIX = "recommendation";
    private static final String RECOMMENDATION_VOICE_LABEL_PREFIX = "recommendationVoiceLabel";
    public static final String SPOTIFY_PACKAGE_NAME = "com.spotify.music";
    private static final String TAG = "SeedlingMediaDataHandleUtils";
    private static final boolean TEST_DEBUG = false;
    private static final String TOGGLE = "toggle";
    public static final SeedlingMediaDataHandleUtils INSTANCE = new SeedlingMediaDataHandleUtils();
    private static final Lazy isExpRegion$delegate = LazyKt__LazyJVMKt.lazy(new SeedlingMediaDataHandleUtils$$ExternalSyntheticLambda0());
    public static final String METHOD_BTN_E5_ONCLICK = "btnE5OnClick";
    private static final String E5 = "E5";
    public static final String METHOD_BTN_E1_ONCLICK = "btnE1OnClick";
    private static final String E1 = "E1";
    public static final String METHOD_BTN_E2_ONCLICK = "btnE2OnClick";
    private static final String E2 = "E2";
    public static final String METHOD_BTN_E3_ONCLICK = "btnE3OnClick";
    private static final String E3 = "E3";
    public static final String METHOD_BTN_E4_ONCLICK = "btnE4OnClick";
    private static final String E4 = "E4";
    private static final Map<String, String> methodToPositionMap = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(METHOD_BTN_E5_ONCLICK, E5), TuplesKt.to(METHOD_BTN_E1_ONCLICK, E1), TuplesKt.to(METHOD_BTN_E2_ONCLICK, E2), TuplesKt.to(METHOD_BTN_E3_ONCLICK, E3), TuplesKt.to(METHOD_BTN_E4_ONCLICK, E4)});
    private static final Map<String, Map<String, ButtonInfo>> currentButtonLayouts = new LinkedHashMap();
    private static final Map<String, Map<String, ButtonInfo>> secondHomeButtonLayouts = new LinkedHashMap();

    private SeedlingMediaDataHandleUtils() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static final void applyButtonLayout(UiData uiData, Map<String, ButtonInfo> map) throws JSONException {
        for (Map.Entry<String, ButtonInfo> entry : map.entrySet()) {
            String key = entry.getKey();
            ButtonInfo value = entry.getValue();
            uiData.put(FileProvider$.ExternalSyntheticOutline0.m(KEY_CARD_HAS_BUTTON, key), Boolean.valueOf(value.getHasButton()));
            if (value.getHasButton()) {
                uiData.put(FileProvider$.ExternalSyntheticOutline0.m(KEY_CARD_BUTTON_ICON, key), value.getIcon());
                uiData.put("type" + key, value.getType());
                uiData.put("voiceLabel" + key, value.getVoiceLabel());
                switch (key.hashCode()) {
                    case 2188:
                        if (key.equals(E1)) {
                        }
                        break;
                    case 2189:
                        if (key.equals(E2)) {
                        }
                        break;
                    case 2190:
                        if (key.equals(E3)) {
                        }
                        break;
                    default:
                        continue;
                }
                uiData.put(KEY_CARD_BUTTON_ANIM.concat(key), value.getAnimRes());
                uiData.put(KEY_CARD_ICON_ON.concat(key), value.getIconOn());
                uiData.put(KEY_CARD_ICON_OFF.concat(key), value.getIconOff());
                uiData.put(KEY_CARD_TRANSITION_ON.concat(key), value.getTransitionOn());
                uiData.put(KEY_CARD_TRANSITION_OFF.concat(key), value.getTransitionOff());
                uiData.put(KEY_CARD_CHECKED.concat(key), Boolean.valueOf(value.getChecked()));
            }
        }
    }

    private static final List<ButtonInfo> createButtonInfoList(SeedlingMediaData seedlingMediaData, Context context, boolean z, boolean z2) {
        List listListOf = CollectionsKt__CollectionsKt.listOf(new ButtonInfo[]{createCustom0Resources(seedlingMediaData), createPreResources(seedlingMediaData, context, z2), createPlayOrPauseResources(seedlingMediaData, context, z2, z), createNextResources(seedlingMediaData, context, z2), createCustom1Resources(seedlingMediaData)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListOf) {
            if (((ButtonInfo) obj).getHasButton()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private static final Map<String, ButtonInfo> createButtonLayout(List<ButtonInfo> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (list.size() > 4) {
            LogUtil.w(TAG, "createButtonLayout Error");
            return MapsKt__MapsKt.emptyMap();
        }
        List listListOf = CollectionsKt__CollectionsKt.listOf(new String[]{E1, E2, E3, E4});
        Iterator<T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            linkedHashMap.put(listListOf.get(i), (ButtonInfo) it.next());
            i++;
        }
        return linkedHashMap;
    }

    private static final Map<String, ButtonInfo> createButtonLayoutForSecondHome(SeedlingMediaData seedlingMediaData, Context context, boolean z, boolean z2) {
        return MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(E1, createPreResources(seedlingMediaData, context, z2)), TuplesKt.to(E2, createPlayOrPauseResources(seedlingMediaData, context, z2, z)), TuplesKt.to(E3, createNextResources(seedlingMediaData, context, z2))});
    }

    private static final ButtonInfo createCustom0Resources(SeedlingMediaData seedlingMediaData) {
        String voiceLabel;
        MediaButton mediaButton = seedlingMediaData.getMediaButton();
        MediaAction custom0 = mediaButton != null ? mediaButton.getCustom0() : null;
        boolean z = custom0 != null ? true : TEST_DEBUG;
        Object icon = custom0 != null ? custom0.getIcon(MediaAction.ICON_DEFAULT) : null;
        if (custom0 == null || (voiceLabel = custom0.getVoiceLabel()) == null) {
            voiceLabel = seedlingMediaData.getVoiceLabel();
        }
        return new ButtonInfo(z, "custom0", CIRCLE, icon, voiceLabel, (String) null, (Object) null, (Object) null, (String) null, (String) null, TEST_DEBUG, 2016, (DefaultConstructorMarker) null);
    }

    private static final ButtonInfo createCustom1Resources(SeedlingMediaData seedlingMediaData) {
        String voiceLabel;
        MediaButton mediaButton = seedlingMediaData.getMediaButton();
        MediaAction custom1 = mediaButton != null ? mediaButton.getCustom1() : null;
        boolean z = custom1 != null ? true : TEST_DEBUG;
        Object icon = custom1 != null ? custom1.getIcon(MediaAction.ICON_DEFAULT) : null;
        if (custom1 == null || (voiceLabel = custom1.getVoiceLabel()) == null) {
            voiceLabel = seedlingMediaData.getVoiceLabel();
        }
        return new ButtonInfo(z, "custom1", CIRCLE, icon, voiceLabel, (String) null, (Object) null, (Object) null, (String) null, (String) null, TEST_DEBUG, 2016, (DefaultConstructorMarker) null);
    }

    private static final Map<String, ButtonInfo> createFiveButtonLayout(List<ButtonInfo> list) {
        if (list.size() == 5) {
            return MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(E5, list.get(0)), TuplesKt.to(E1, list.get(1)), TuplesKt.to(E2, list.get(2)), TuplesKt.to(E3, list.get(3)), TuplesKt.to(E4, list.get(4))});
        }
        LogUtil.w(TAG, "createFiveButtonLayout Error");
        return MapsKt__MapsKt.emptyMap();
    }

    private static final ButtonInfo createNextResources(SeedlingMediaData seedlingMediaData, Context context, boolean z) {
        String animRes;
        MediaButton mediaButton = seedlingMediaData.getMediaButton();
        MediaAction nextOrCustom = mediaButton != null ? mediaButton.getNextOrCustom() : null;
        boolean z2 = TEST_DEBUG;
        boolean zIsStandardAction = nextOrCustom != null ? nextOrCustom.isStandardAction() : false;
        Object icon = nextOrCustom != null ? nextOrCustom.getIcon(MediaAction.ICON_DEFAULT) : null;
        String voiceLabel = nextOrCustom != null ? nextOrCustom.getVoiceLabel() : null;
        if (z || nextOrCustom != null) {
            z2 = true;
        }
        boolean z3 = z2;
        if (z) {
            if (nextOrCustom == null || (icon = nextOrCustom.getIcon(MediaAction.ICON_DEFAULT)) == null) {
                icon = context.getString(R$string.spotifyNextDisableBtnIconRes);
            }
        } else if (icon == null) {
            icon = context.getString(R$string.nextBtnIconRes);
        }
        Object obj = icon;
        if (z) {
            if (voiceLabel == null) {
                voiceLabel = context.getString(R$string.controls_media_button_next_disabled);
            }
        } else if (voiceLabel == null) {
            voiceLabel = seedlingMediaData.getVoiceLabel();
        }
        return new ButtonInfo(z3, "nextOrCustom", CIRCLE, obj, voiceLabel, ((!z && !zIsStandardAction) || nextOrCustom == null || (animRes = nextOrCustom.getAnimRes()) == null) ? "" : animRes, (Object) null, (Object) null, (String) null, (String) null, TEST_DEBUG, 1984, (DefaultConstructorMarker) null);
    }

    private static final Map<String, ButtonInfo> createOneButtonLayout(List<ButtonInfo> list) {
        Object next;
        Iterator<T> it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((ButtonInfo) next).getHasButton());
        ButtonInfo buttonInfo = (ButtonInfo) next;
        if (buttonInfo != null) {
            return MapsKt__MapsJVMKt.mapOf(TuplesKt.to(E2, buttonInfo));
        }
        LogUtil.w(TAG, "createOneButtonLayout Error");
        return MapsKt__MapsKt.emptyMap();
    }

    private static final ButtonInfo createPlayOrPauseResources(SeedlingMediaData seedlingMediaData, Context context, boolean z, boolean z2) {
        Object string;
        String string2;
        Object obj;
        Object obj2;
        String str;
        String voiceLabel;
        MediaButton mediaButton = seedlingMediaData.getMediaButton();
        MediaAction playOrPause = mediaButton != null ? mediaButton.getPlayOrPause() : null;
        boolean z3 = TEST_DEBUG;
        boolean zIsStandardAction = playOrPause != null ? playOrPause.isStandardAction() : false;
        int i = z2 ? R$string.pauseBtnIconRes : R$string.playBtnIconRes;
        if (playOrPause == null || (string = playOrPause.getIcon(MediaAction.ICON_DEFAULT)) == null) {
            string = context.getString(i);
        }
        Object obj3 = string;
        if (z || zIsStandardAction) {
            String string3 = context.getString(R$string.iconOnRes);
            String string4 = context.getString(R$string.iconOffRes);
            String string5 = context.getString(R$string.transitionOnRes);
            string2 = context.getString(R$string.transitionOffRes);
            obj = string3;
            obj2 = string4;
            str = string5;
        } else {
            str = "";
            string2 = str;
            obj = obj3;
            obj2 = obj;
        }
        if (z || playOrPause != null) {
            z3 = true;
        }
        boolean z4 = z3;
        if (playOrPause == null || (voiceLabel = playOrPause.getVoiceLabel()) == null) {
            voiceLabel = seedlingMediaData.getVoiceLabel();
        }
        return new ButtonInfo(z4, "playOrPause", TOGGLE, obj3, voiceLabel, (String) null, obj, obj2, str, string2, z2, 32, (DefaultConstructorMarker) null);
    }

    private static final ButtonInfo createPreResources(SeedlingMediaData seedlingMediaData, Context context, boolean z) {
        String animRes;
        MediaButton mediaButton = seedlingMediaData.getMediaButton();
        MediaAction prevOrCustom = mediaButton != null ? mediaButton.getPrevOrCustom() : null;
        boolean z2 = TEST_DEBUG;
        boolean zIsStandardAction = prevOrCustom != null ? prevOrCustom.isStandardAction() : false;
        Object icon = prevOrCustom != null ? prevOrCustom.getIcon(MediaAction.ICON_DEFAULT) : null;
        String voiceLabel = prevOrCustom != null ? prevOrCustom.getVoiceLabel() : null;
        if (z || prevOrCustom != null) {
            z2 = true;
        }
        boolean z3 = z2;
        if (z) {
            if (prevOrCustom == null || (icon = prevOrCustom.getIcon(MediaAction.ICON_DEFAULT)) == null) {
                icon = context.getString(R$string.spotifyPrevDisableBtnIconRes);
            }
        } else if (icon == null) {
            icon = context.getString(R$string.preBtnIconRes);
        }
        Object obj = icon;
        if (z) {
            if (voiceLabel == null) {
                voiceLabel = context.getString(R$string.controls_media_button_prev_disabled);
            }
        } else if (voiceLabel == null) {
            voiceLabel = seedlingMediaData.getVoiceLabel();
        }
        return new ButtonInfo(z3, MediaButton.BTN_NAME_PREV_OR_CUSTOM, CIRCLE, obj, voiceLabel, ((!z && !zIsStandardAction) || prevOrCustom == null || (animRes = prevOrCustom.getAnimRes()) == null) ? "" : animRes, (Object) null, (Object) null, (String) null, (String) null, TEST_DEBUG, 1984, (DefaultConstructorMarker) null);
    }

    public static final Map<String, ButtonInfo> getCurrentButtonLayout(String str, boolean z) {
        return !z ? currentButtonLayouts.get(str) : secondHomeButtonLayouts.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isExpRegion_delegate$lambda$0() {
        return !TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic");
    }

    private static final Bundle mediaActionToBundle(MediaAction mediaAction) {
        if (mediaAction == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_BUTTON_NAME, mediaAction.getButtonName());
        Bundle bundle2 = new Bundle();
        for (Map.Entry<String, Pair<String, Uri>> entry : mediaAction.getIconMap().entrySet()) {
            String key = entry.getKey();
            Pair<String, Uri> value = entry.getValue();
            Bundle bundle3 = new Bundle();
            bundle3.putString(KEY_FIRST, (String) value.getFirst());
            bundle3.putParcelable(KEY_SECOND, (Parcelable) value.getSecond());
            bundle2.putBundle(key, bundle3);
        }
        bundle.putBundle(KEY_ICON_MAP, bundle2);
        bundle.putString(KEY_ANIM_RES, mediaAction.getAnimRes());
        bundle.putString("voiceLabel", mediaAction.getVoiceLabel());
        bundle.putBoolean(KEY_IS_STANDARD, mediaAction.isStandardAction());
        return bundle;
    }

    private static final Bundle mediaButtonToBundle(MediaButton mediaButton) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PLAY_ID, mediaButton.getPlayId());
        bundle.putBundle("playOrPause", mediaActionToBundle(mediaButton.getPlayOrPause()));
        bundle.putBundle("nextOrCustom", mediaActionToBundle(mediaButton.getNextOrCustom()));
        bundle.putBundle(KEY_CARD_PREVIOUS_OR_CUSTOM, mediaActionToBundle(mediaButton.getPrevOrCustom()));
        bundle.putBundle("custom0", mediaActionToBundle(mediaButton.getCustom0()));
        bundle.putBundle("custom1", mediaActionToBundle(mediaButton.getCustom1()));
        return bundle;
    }

    public static final Bundle mediaDataToBundle(List<SeedlingMediaData> list, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(mediaDataToSingleBundle((SeedlingMediaData) it.next(), z, z2));
        }
        bundle.putParcelableArrayList(KEY_MEDIA_LIST, arrayList);
        return bundle;
    }

    private static final Bundle mediaDataToSingleBundle(SeedlingMediaData seedlingMediaData, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        long jComputerPosition = seedlingMediaData.computerPosition();
        bundle.putString("mediaId", seedlingMediaData.getPlayerId());
        if (z ? seedlingMediaData.getLyricData().getEnabledAod() : seedlingMediaData.getLyricData().getEnabledNotification()) {
            bundle.putBoolean(KEY_SHOULD_SHOW_LYRIC, true);
            bundle.putString(KEY_ORIGINAL_LYRIC, seedlingMediaData.getLyricData().getOriginalLines());
            bundle.putCharSequence(KEY_CURRENT_LYRIC, seedlingMediaData.getLyricData().getCurrentLyric(jComputerPosition));
            bundle.putCharSequence(KEY_SONG_INFO, seedlingMediaData.getLyricData().getSongInfo());
        }
        bundle.putCharSequence(KEY_CARD_ARTIST, seedlingMediaData.getArtist());
        bundle.putCharSequence(KEY_CARD_SONG_NAME, seedlingMediaData.getSong());
        bundle.putLong(KEY_CARD_SLIDER_DURATION, seedlingMediaData.getDuration());
        bundle.putString(KEY_APP_ICON_RES_ID, seedlingMediaData.getAppIconResId());
        bundle.putInt(KEY_STATE, seedlingMediaData.getState());
        bundle.putInt(KEY_OLD_STATE, seedlingMediaData.getOldState());
        bundle.putLong(KEY_POSITION, seedlingMediaData.getPosition());
        bundle.putBoolean(KEY_SEEKBAR_AVAILABLE, seedlingMediaData.getSeekAvailable());
        bundle.putBoolean(KEY_SEEKBAR_ENABLE, seedlingMediaData.getSeekEnable());
        bundle.putLong(KEY_LAST_POSITION_UPDATE_TIME, seedlingMediaData.getLastPositionUpdateTime());
        bundle.putFloat(KEY_PLAYBACK_SPEED, seedlingMediaData.getPlaybackSpeed());
        bundle.putBoolean(KEY_VOICE_PRINT_IS_SHOW, seedlingMediaData.getVoicePrintIsShow());
        bundle.putString(KEY_ACTION, seedlingMediaData.getAction());
        bundle.putString("packageName", seedlingMediaData.getPackageName());
        bundle.putBoolean(KEY_IS_AUTO_PLAY, seedlingMediaData.isAutoPlay());
        bundle.putString("voiceLabel", seedlingMediaData.getVoiceLabel());
        bundle.putString(KEY_ALBUM_ROTATION_DIRECTION, seedlingMediaData.getAlbumRotationDirection());
        bundle.putBoolean(KEY_IS_SPOTIFY_WHICH_SUPPORTS_OPLUS_MEDIA_CONTROLLER, seedlingMediaData.isSpotifyWhichSupportsOplusMediaController());
        if (z2) {
            bundle.putBoolean(KEY_ARTWORK_URI_CONTENT_CHANGED, seedlingMediaData.getArtworkUriContentChanged());
            bundle.putBoolean(KEY_DEVICE_ICON_URI_CHANGED, seedlingMediaData.getDeviceIconUriChanged());
            bundle.putBoolean(KEY_MEDIA_BUTTON_URI_CHANGED, seedlingMediaData.getMediaButtonUriChanged());
            seedlingMediaData.setArtworkUriContentChanged(TEST_DEBUG);
        } else {
            bundle.putBoolean(KEY_ARTWORK_URI_CONTENT_CHANGED, TEST_DEBUG);
            bundle.putBoolean(KEY_DEVICE_ICON_URI_CHANGED, TEST_DEBUG);
            bundle.putBoolean(KEY_MEDIA_BUTTON_URI_CHANGED, TEST_DEBUG);
        }
        bundle.putParcelable(KEY_APP_ICON_URI, seedlingMediaData.getAppIconUri());
        bundle.putParcelable(KEY_ARTWORK_URI, seedlingMediaData.getArtworkUri());
        bundle.putParcelable(KEY_DEVICE_ICON_URI, seedlingMediaData.getDeviceIconUri());
        bundle.putString(KEY_ARTWORK_BACKGROUND_COLOR, seedlingMediaData.getArtworkBgColor());
        MediaButton mediaButton = seedlingMediaData.getMediaButton();
        if (mediaButton != null) {
            bundle.putBundle(KEY_MEDIA_BUTTON, mediaButtonToBundle(mediaButton));
        }
        bundle.putBoolean(KEY_IS_RECOMMENDATIONS, seedlingMediaData.isRecommendation());
        bundle.putParcelableArrayList(KEY_RECOMMENDATIONS, mediaRecommendationsToBundleList(seedlingMediaData.getRecommendations()));
        return bundle;
    }

    private static final ArrayList<Bundle> mediaRecommendationsToBundleList(CopyOnWriteArrayList<MediaRecommendationData> copyOnWriteArrayList) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(copyOnWriteArrayList, 10));
        for (MediaRecommendationData mediaRecommendationData : copyOnWriteArrayList) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_MEDIA_ITEM, mediaRecommendationData.getMediaItem());
            bundle.putParcelable(KEY_ARTWORK_URI, mediaRecommendationData.getArtworkUri());
            bundle.putString("voiceLabel", mediaRecommendationData.getVoiceLabel());
            arrayList.add(bundle);
        }
        return new ArrayList<>(arrayList);
    }

    public static final void parseAndUpdateExtraData(List<SeedlingMediaData> list, boolean z) {
        Uri deviceIconUri;
        Uri artworkUri;
        MediaAction custom1;
        MediaAction nextOrCustom;
        MediaAction playOrPause;
        MediaAction prevOrCustom;
        MediaAction custom0;
        MediaAction custom2;
        MediaAction nextOrCustom2;
        MediaAction playOrPause2;
        MediaAction prevOrCustom2;
        MediaAction custom3;
        for (SeedlingMediaData seedlingMediaData : list) {
            MediaButton mediaButton = seedlingMediaData.getMediaButton();
            if (seedlingMediaData.getMediaButtonUriChanged()) {
                Object icon = (mediaButton == null || (custom3 = mediaButton.getCustom0()) == null) ? null : custom3.getIcon(MediaAction.ICON_DEFAULT);
                Uri uri = icon instanceof Uri ? (Uri) icon : null;
                if (uri != null) {
                    MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri);
                }
                Object icon2 = (mediaButton == null || (prevOrCustom2 = mediaButton.getPrevOrCustom()) == null) ? null : prevOrCustom2.getIcon(MediaAction.ICON_DEFAULT);
                Uri uri2 = icon2 instanceof Uri ? (Uri) icon2 : null;
                if (uri2 != null) {
                    MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri2);
                }
                Object icon3 = (mediaButton == null || (playOrPause2 = mediaButton.getPlayOrPause()) == null) ? null : playOrPause2.getIcon(MediaAction.ICON_DEFAULT);
                Uri uri3 = icon3 instanceof Uri ? (Uri) icon3 : null;
                if (uri3 != null) {
                    MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri3);
                }
                Object icon4 = (mediaButton == null || (nextOrCustom2 = mediaButton.getNextOrCustom()) == null) ? null : nextOrCustom2.getIcon(MediaAction.ICON_DEFAULT);
                Uri uri4 = icon4 instanceof Uri ? (Uri) icon4 : null;
                if (uri4 != null) {
                    MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri4);
                }
                Object icon5 = (mediaButton == null || (custom2 = mediaButton.getCustom1()) == null) ? null : custom2.getIcon(MediaAction.ICON_DEFAULT);
                Uri uri5 = icon5 instanceof Uri ? (Uri) icon5 : null;
                if (uri5 != null) {
                    MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri5);
                }
                if (z) {
                    Object icon6 = (mediaButton == null || (custom0 = mediaButton.getCustom0()) == null) ? null : custom0.getIcon(MediaAction.ICON_SECOND_HOME);
                    Uri uri6 = icon6 instanceof Uri ? (Uri) icon6 : null;
                    if (uri6 != null) {
                        MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri6);
                    }
                    Object icon7 = (mediaButton == null || (prevOrCustom = mediaButton.getPrevOrCustom()) == null) ? null : prevOrCustom.getIcon(MediaAction.ICON_SECOND_HOME);
                    Uri uri7 = icon7 instanceof Uri ? (Uri) icon7 : null;
                    if (uri7 != null) {
                        MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri7);
                    }
                    Object icon8 = (mediaButton == null || (playOrPause = mediaButton.getPlayOrPause()) == null) ? null : playOrPause.getIcon(MediaAction.ICON_SECOND_HOME);
                    Uri uri8 = icon8 instanceof Uri ? (Uri) icon8 : null;
                    if (uri8 != null) {
                        MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri8);
                    }
                    Object icon9 = (mediaButton == null || (nextOrCustom = mediaButton.getNextOrCustom()) == null) ? null : nextOrCustom.getIcon(MediaAction.ICON_SECOND_HOME);
                    Uri uri9 = icon9 instanceof Uri ? (Uri) icon9 : null;
                    if (uri9 != null) {
                        MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri9);
                    }
                    Object icon10 = (mediaButton == null || (custom1 = mediaButton.getCustom1()) == null) ? null : custom1.getIcon(MediaAction.ICON_SECOND_HOME);
                    Uri uri10 = icon10 instanceof Uri ? (Uri) icon10 : null;
                    if (uri10 != null) {
                        MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(uri10);
                    }
                }
            }
            if (seedlingMediaData.getArtworkUriContentChanged() && (artworkUri = seedlingMediaData.getArtworkUri()) != null) {
                MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(artworkUri);
            }
            if (seedlingMediaData.getDeviceIconUriChanged() && (deviceIconUri = seedlingMediaData.getDeviceIconUri()) != null) {
                MediaGlobalExtraDataManager.INSTANCE.setContentChangedUri(deviceIconUri);
            }
        }
    }

    private static final MediaAction parseMediaAction(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(KEY_ICON_MAP);
        HashMap map = new HashMap();
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                Bundle bundle3 = bundle2.getBundle(str);
                if (bundle3 != null) {
                    map.put(str, new Pair(bundle3.getString(KEY_FIRST), (Uri) bundle3.getParcelable(KEY_SECOND, Uri.class)));
                }
            }
        }
        String string = bundle.getString(KEY_BUTTON_NAME);
        if (string == null) {
            string = "";
        }
        String string2 = bundle.getString(KEY_ANIM_RES);
        String string3 = bundle.getString("voiceLabel");
        if (string3 == null) {
            string3 = "";
        }
        return new MediaAction(string, map, string2, string3, bundle.getBoolean(KEY_IS_STANDARD, TEST_DEBUG), null, 32, null);
    }

    private static final MediaButton parseMediaButton(Bundle bundle) {
        String string = bundle.getString(KEY_PLAY_ID);
        if (string == null) {
            string = "";
        }
        String str = string;
        Bundle bundle2 = bundle.getBundle("playOrPause");
        MediaAction mediaAction = bundle2 != null ? parseMediaAction(bundle2) : null;
        Bundle bundle3 = bundle.getBundle("nextOrCustom");
        MediaAction mediaAction2 = bundle3 != null ? parseMediaAction(bundle3) : null;
        Bundle bundle4 = bundle.getBundle(KEY_CARD_PREVIOUS_OR_CUSTOM);
        MediaAction mediaAction3 = bundle4 != null ? parseMediaAction(bundle4) : null;
        Bundle bundle5 = bundle.getBundle("custom0");
        MediaAction mediaAction4 = bundle5 != null ? parseMediaAction(bundle5) : null;
        Bundle bundle6 = bundle.getBundle("custom1");
        return new MediaButton(str, mediaAction, mediaAction2, mediaAction3, mediaAction4, bundle6 != null ? parseMediaAction(bundle6) : null);
    }

    public static final List<SeedlingMediaData> parseMediaDataList(Bundle bundle) {
        Object objEmptyList;
        Collection collectionEmptyList;
        try {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_MEDIA_LIST, Bundle.class);
            if (parcelableArrayList != null) {
                collectionEmptyList = new ArrayList();
                int size = parcelableArrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = parcelableArrayList.get(i);
                    i++;
                    Bundle bundle2 = (Bundle) obj;
                    bundle2.getClass();
                    SeedlingMediaData singleMediaData = parseSingleMediaData(bundle2);
                    if (singleMediaData != null) {
                        collectionEmptyList.add(singleMediaData);
                    }
                }
            } else {
                collectionEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            objEmptyList = Result.constructor-impl(collectionEmptyList);
        } catch (Throwable th) {
            objEmptyList = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(objEmptyList);
        if (th2 != null) {
            LogUtil.e(TAG, "parseMediaDataList Failed to parse media list: " + th2.getMessage());
            objEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        return (List) objEmptyList;
    }

    private static final CopyOnWriteArrayList<MediaRecommendationData> parseRecommendations(ArrayList<Bundle> arrayList) {
        MediaRecommendationData mediaRecommendationData;
        if (arrayList == null) {
            return new CopyOnWriteArrayList<>();
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Bundle bundle = arrayList.get(i);
            i++;
            Bundle bundle2 = bundle;
            MediaBrowser.MediaItem mediaItem = (MediaBrowser.MediaItem) bundle2.getParcelable(KEY_MEDIA_ITEM, MediaBrowser.MediaItem.class);
            if (mediaItem == null) {
                mediaRecommendationData = null;
            } else {
                Uri uri = (Uri) bundle2.getParcelable(KEY_ARTWORK_URI, Uri.class);
                String string = bundle2.getString("voiceLabel");
                if (string == null) {
                    string = "";
                }
                mediaRecommendationData = new MediaRecommendationData(mediaItem, null, uri, string, parseRecommendations.1.1.INSTANCE);
            }
            if (mediaRecommendationData != null) {
                arrayList2.add(mediaRecommendationData);
            }
        }
        return new CopyOnWriteArrayList<>(arrayList2);
    }

    private static final SeedlingMediaData parseSingleMediaData(Bundle bundle) {
        Object obj;
        try {
            SeedlingMediaData seedlingMediaData = new SeedlingMediaData(null, null, null, null, 0L, 0, null, null, null, null, null, null, null, null, TEST_DEBUG, null, null, null, null, null, TEST_DEBUG, null, TEST_DEBUG, null, 0, 0, 0, 0L, TEST_DEBUG, TEST_DEBUG, 0L, 0.0f, TEST_DEBUG, null, null, 0L, TEST_DEBUG, 0L, TEST_DEBUG, null, 0, TEST_DEBUG, null, null, TEST_DEBUG, TEST_DEBUG, null, null, null, null, TEST_DEBUG, TEST_DEBUG, -1, 1048575, null);
            String string = bundle.getString("mediaId");
            String str = "";
            if (string == null) {
                string = "";
            }
            seedlingMediaData.setPlayerId(string);
            seedlingMediaData.setArtist(bundle.getCharSequence(KEY_CARD_ARTIST));
            seedlingMediaData.setSong(bundle.getCharSequence(KEY_CARD_SONG_NAME));
            seedlingMediaData.setShouldShowLyric(bundle.getBoolean(KEY_SHOULD_SHOW_LYRIC, TEST_DEBUG));
            seedlingMediaData.setCurrentLyric(bundle.getCharSequence(KEY_CURRENT_LYRIC, null));
            seedlingMediaData.setSongInfoInLyric(bundle.getCharSequence(KEY_SONG_INFO, null));
            seedlingMediaData.setDuration(bundle.getLong(KEY_CARD_SLIDER_DURATION));
            seedlingMediaData.setAppIconResId(bundle.getString(KEY_APP_ICON_RES_ID));
            seedlingMediaData.setState(bundle.getInt(KEY_STATE));
            seedlingMediaData.setOldState(bundle.getInt(KEY_OLD_STATE));
            seedlingMediaData.setPosition(bundle.getLong(KEY_POSITION));
            seedlingMediaData.setSeekAvailable(bundle.getBoolean(KEY_SEEKBAR_AVAILABLE));
            seedlingMediaData.setSeekEnable(bundle.getBoolean(KEY_SEEKBAR_ENABLE));
            seedlingMediaData.setLastPositionUpdateTime(bundle.getLong(KEY_LAST_POSITION_UPDATE_TIME));
            seedlingMediaData.setPlaybackSpeed(bundle.getFloat(KEY_PLAYBACK_SPEED));
            seedlingMediaData.setVoicePrintIsShow(bundle.getBoolean(KEY_VOICE_PRINT_IS_SHOW));
            seedlingMediaData.setAction(bundle.getString(KEY_ACTION));
            String string2 = bundle.getString("packageName");
            if (string2 == null) {
                string2 = "";
            }
            seedlingMediaData.setPackageName(string2);
            seedlingMediaData.setAutoPlay(bundle.getBoolean(KEY_IS_AUTO_PLAY));
            String string3 = bundle.getString("voiceLabel");
            if (string3 == null) {
                string3 = "";
            }
            seedlingMediaData.setVoiceLabel(string3);
            String string4 = bundle.getString(KEY_ALBUM_ROTATION_DIRECTION);
            if (string4 == null) {
                string4 = "";
            }
            seedlingMediaData.setAlbumRotationDirection(string4);
            seedlingMediaData.setSpotifyWhichSupportsOplusMediaController(bundle.getBoolean(KEY_IS_SPOTIFY_WHICH_SUPPORTS_OPLUS_MEDIA_CONTROLLER));
            Bundle bundle2 = bundle.getBundle(KEY_MEDIA_BUTTON);
            seedlingMediaData.setMediaButton(bundle2 != null ? parseMediaButton(bundle2) : null);
            seedlingMediaData.setArtworkUriContentChanged(bundle.getBoolean(KEY_ARTWORK_URI_CONTENT_CHANGED));
            seedlingMediaData.setDeviceIconUriChanged(bundle.getBoolean(KEY_DEVICE_ICON_URI_CHANGED));
            seedlingMediaData.setMediaButtonUriChanged(bundle.getBoolean(KEY_MEDIA_BUTTON_URI_CHANGED));
            seedlingMediaData.setAppIconUri((Uri) bundle.getParcelable(KEY_APP_ICON_URI, Uri.class));
            seedlingMediaData.setArtworkUri((Uri) bundle.getParcelable(KEY_ARTWORK_URI, Uri.class));
            seedlingMediaData.setDeviceIconUri((Uri) bundle.getParcelable(KEY_DEVICE_ICON_URI, Uri.class));
            String string5 = bundle.getString(KEY_ARTWORK_BACKGROUND_COLOR);
            if (string5 != null) {
                str = string5;
            }
            seedlingMediaData.setArtworkBgColor(str);
            seedlingMediaData.setRecommendation(bundle.getBoolean(KEY_IS_RECOMMENDATIONS));
            seedlingMediaData.setRecommendations(parseRecommendations(bundle.getParcelableArrayList(KEY_RECOMMENDATIONS, Bundle.class)));
            seedlingMediaData.setLyricData(new LyricData(null, null, null, bundle.getString(KEY_ORIGINAL_LYRIC), TEST_DEBUG, TEST_DEBUG, 0, 119, null));
            obj = Result.constructor-impl(seedlingMediaData);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e(TAG, "parseSingleMediaData Failed to parse single media data: " + ExceptionsKt__ExceptionsKt.stackTraceToString(th2));
        }
        return (SeedlingMediaData) (obj instanceof Result.Failure ? null : obj);
    }

    public static final void saveBitmapUriToFile(Uri uri, String str, Context context) {
    }

    public static final UiData secondHomeToUiData(Context context, List<SeedlingMediaData> list, boolean z, boolean z2) {
        Object string;
        Object string2;
        Object string3;
        Object string4;
        Object string5;
        String string6;
        String string7;
        MediaAction playOrPause;
        MediaAction playOrPause2;
        MediaAction prevOrCustom;
        MediaAction nextOrCustom;
        UiData uiData = new UiData();
        JSONArray jSONArray = new JSONArray();
        secondHomeButtonLayouts.clear();
        for (Iterator it = list.iterator(); it.hasNext(); it = it) {
            SeedlingMediaData seedlingMediaData = (SeedlingMediaData) it.next();
            SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
            int oldState = companion.isConnectingState(seedlingMediaData.getState()) ? seedlingMediaData.getOldState() : seedlingMediaData.getState();
            boolean zIsPlayState = companion.isPlayState(oldState);
            boolean zEquals = "com.spotify.music".equals(seedlingMediaData.getPackageName());
            UiData uiDataWithoutButton = toUiDataWithoutButton(context, seedlingMediaData, z2, true);
            Map<String, ButtonInfo> mapCreateButtonLayoutForSecondHome = createButtonLayoutForSecondHome(seedlingMediaData, context, zIsPlayState, zEquals);
            secondHomeButtonLayouts.put(seedlingMediaData.getPlayerId(), mapCreateButtonLayoutForSecondHome);
            applyButtonLayout(uiDataWithoutButton, mapCreateButtonLayoutForSecondHome);
            MediaButton mediaButton = seedlingMediaData.getMediaButton();
            if (mediaButton == null || (nextOrCustom = mediaButton.getNextOrCustom()) == null || (string = nextOrCustom.getIcon(MediaAction.ICON_SECOND_HOME)) == null) {
                string = context.getString(R$string.nextBtnIconResSecondHome);
            }
            MediaButton mediaButton2 = seedlingMediaData.getMediaButton();
            if (mediaButton2 == null || (prevOrCustom = mediaButton2.getPrevOrCustom()) == null || (string2 = prevOrCustom.getIcon(MediaAction.ICON_SECOND_HOME)) == null) {
                string2 = context.getString(R$string.preBtnIconResSecondHome);
            }
            MediaButton mediaButton3 = seedlingMediaData.getMediaButton();
            if (mediaButton3 == null || (playOrPause2 = mediaButton3.getPlayOrPause()) == null || (string3 = playOrPause2.getIcon(MediaAction.ICON_SECOND_HOME)) == null) {
                string3 = companion.isPlayState(oldState) ? context.getString(R$string.pauseBtnIconResSecondHome) : context.getString(R$string.playBtnIconResSecondHome);
                string3.getClass();
            }
            MediaButton mediaButton4 = seedlingMediaData.getMediaButton();
            if ((mediaButton4 == null || (playOrPause = mediaButton4.getPlayOrPause()) == null) ? TEST_DEBUG : playOrPause.isStandardAction()) {
                string4 = context.getString(R$string.pauseBtnIconResSecondHome);
                string5 = context.getString(R$string.playBtnIconResSecondHome);
                string7 = context.getString(R$string.transitionOnResSecondHome);
                string6 = context.getString(R$string.transitionOffResSecondHome);
            } else {
                string4 = string3;
                string5 = string4;
                string6 = "";
                string7 = string6;
            }
            saveBitmapUriToFile(string3 instanceof Uri ? (Uri) string3 : null, "playOrPauseRes", context);
            String string8 = context.getString(R$string.staticVoicePrintPlayingSecondHome);
            String string9 = context.getString(R$string.staticVoicePrintPauseSecondHome);
            if (!companion.isPlayState(oldState)) {
                string8 = string9;
            }
            String string10 = context.getString(R$string.voicePrintResSecondHome);
            uiDataWithoutButton.put("buttonIconE3", string);
            uiDataWithoutButton.put("buttonAnimE3", "");
            uiDataWithoutButton.put("buttonIconE1", string2);
            uiDataWithoutButton.put("buttonAnimE1", "");
            uiDataWithoutButton.put("buttonIconE2", string3);
            uiDataWithoutButton.put(KEY_STATIC_VOICE_PRINT_PLAYING, string8);
            uiDataWithoutButton.put(KEY_VOICE_PRINT, string10);
            uiDataWithoutButton.put(KEY_AUTOPLAY, Boolean.valueOf(seedlingMediaData.isAutoPlay()));
            uiDataWithoutButton.put("iconOnE2", string4);
            uiDataWithoutButton.put("iconOffE2", string5);
            uiDataWithoutButton.put("transitionOnE2", string7);
            uiDataWithoutButton.put("transitionOffE2", string6);
            jSONArray.put(uiDataWithoutButton.build());
        }
        uiData.put(KEY_PLAYER_ARR, jSONArray).put(KEY_STATIC_VOICE_PRINT_SHOW, Boolean.valueOf(z));
        return uiData;
    }

    public static /* synthetic */ UiData secondHomeToUiData$default(Context context, List list, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = TEST_DEBUG;
        }
        return secondHomeToUiData(context, list, z, z2);
    }

    public static final UiData toUiData(Context context, List<SeedlingMediaData> list, boolean z, boolean z2) {
        UiData uiData = new UiData();
        JSONArray jSONArray = new JSONArray();
        currentButtonLayouts.clear();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(toUiDataInternal(context, (SeedlingMediaData) it.next(), z2).build());
        }
        uiData.put(KEY_PLAYER_ARR, jSONArray).put(KEY_STATIC_VOICE_PRINT_SHOW, Boolean.valueOf(z)).put(KEY_SHAPE_ARTWORK, INSTANCE.isExpRegion() ? context.getString(R$string.shapeArtworkRoundRectangle) : context.getString(R$string.shapeArtworkCircle));
        return uiData;
    }

    public static /* synthetic */ UiData toUiData$default(Context context, List list, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = TEST_DEBUG;
        }
        return toUiData(context, list, z, z2);
    }

    private static final UiData toUiDataInternal(Context context, SeedlingMediaData seedlingMediaData, boolean z) throws JSONException {
        Map<String, ButtonInfo> mapCreateOneButtonLayout;
        SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
        boolean zIsPlayState = companion.isPlayState(companion.isConnectingState(seedlingMediaData.getState()) ? seedlingMediaData.getOldState() : seedlingMediaData.getState());
        boolean zEquals = "com.spotify.music".equals(seedlingMediaData.getPackageName());
        UiData uiDataWithoutButton = toUiDataWithoutButton(context, seedlingMediaData, z, TEST_DEBUG);
        List<ButtonInfo> listCreateButtonInfoList = createButtonInfoList(seedlingMediaData, context, zIsPlayState, zEquals);
        int size = listCreateButtonInfoList.size();
        if (size == 1) {
            mapCreateOneButtonLayout = createOneButtonLayout(listCreateButtonInfoList);
        } else if (size == 2 || size == 3 || size == 4) {
            mapCreateOneButtonLayout = createButtonLayout(listCreateButtonInfoList);
        } else {
            mapCreateOneButtonLayout = size != 5 ? MapsKt__MapsKt.emptyMap() : createFiveButtonLayout(listCreateButtonInfoList);
        }
        currentButtonLayouts.put(seedlingMediaData.getPlayerId(), mapCreateOneButtonLayout);
        applyButtonLayout(uiDataWithoutButton, mapCreateOneButtonLayout);
        return uiDataWithoutButton;
    }

    public static /* synthetic */ UiData toUiDataInternal$default(Context context, SeedlingMediaData seedlingMediaData, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return toUiDataInternal(context, seedlingMediaData, z);
    }

    private static final UiData toUiDataWithoutButton(Context context, SeedlingMediaData seedlingMediaData, boolean z, boolean z2) throws JSONException {
        long jComputerPosition = seedlingMediaData.computerPosition() / 1000;
        UiData uiData = new UiData();
        SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
        int oldState = companion.isConnectingState(seedlingMediaData.getState()) ? seedlingMediaData.getOldState() : seedlingMediaData.getState();
        boolean zIsPlayState = companion.isPlayState(oldState);
        String string = context.getString(R$string.staticVoicePrintPlaying);
        String string2 = context.getString(R$string.staticVoicePrintPause);
        if (!zIsPlayState) {
            string = string2;
        }
        String string3 = context.getString(R$string.voicePrintRes);
        Object string4 = context.getString(R$string.defaultArtworkIconRes);
        Object string5 = context.getString(R$string.defaultAppIconRes);
        int i = zIsPlayState ? 0 : -1;
        boolean z3 = UserHandle.myUserId() == 0 ? true : TEST_DEBUG;
        boolean zIsSpotifyWhichSupportsOplusMediaController = seedlingMediaData.isSpotifyWhichSupportsOplusMediaController();
        boolean z4 = !zIsSpotifyWhichSupportsOplusMediaController;
        Triple triple = zIsSpotifyWhichSupportsOplusMediaController ? new Triple(Boolean.valueOf(seedlingMediaData.isRecommendation()), context.getString(R$string.spotifyEnhanceBadge), context.getString(R$string.spotify_smart_shuffle_voice_label)) : new Triple(Boolean.FALSE, (Object) null, (Object) null);
        Boolean bool = (Boolean) triple.component1();
        bool.booleanValue();
        String str = (String) triple.component2();
        String str2 = (String) triple.component3();
        if (!seedlingMediaData.getShouldShowLyric() || z2) {
            CharSequence song = seedlingMediaData.getSong();
            if (song == null) {
                song = "";
            }
            UiData uiDataPut = uiData.put(KEY_CARD_SONG_NAME, song);
            CharSequence artist = seedlingMediaData.getArtist();
            if (artist == null) {
                artist = "";
            }
            uiDataPut.put(KEY_CARD_ARTIST, artist);
        } else {
            CharSequence currentLyric = seedlingMediaData.getCurrentLyric();
            if (currentLyric == null) {
                currentLyric = "";
            }
            UiData uiDataPut2 = uiData.put(KEY_CARD_SONG_NAME, currentLyric);
            CharSequence songInfoInLyric = seedlingMediaData.getSongInfoInLyric();
            if (songInfoInLyric == null) {
                songInfoInLyric = "";
            }
            uiDataPut2.put(KEY_CARD_ARTIST, songInfoInLyric);
        }
        Object artworkUri = seedlingMediaData.getArtworkUri();
        if (artworkUri == null) {
            artworkUri = string4;
        }
        UiData uiDataPut3 = uiData.put(KEY_CAPSULE_ICON, artworkUri).put(KEY_CAPSULE_TITLE, Long.valueOf(jComputerPosition));
        Object appIconResId = seedlingMediaData.getAppIconResId();
        if (appIconResId != null || (appIconResId = seedlingMediaData.getAppIconUri()) != null) {
            string5 = appIconResId;
        }
        UiData uiDataPut4 = uiDataPut3.put(KEY_CARD_APP_ICON, string5);
        Object artworkUri2 = seedlingMediaData.getArtworkUri();
        if (artworkUri2 == null) {
            artworkUri2 = string4;
        }
        UiData uiDataPut5 = uiDataPut4.put(KEY_CARD_PICTURE, artworkUri2).put(KEY_IS_SHOW_VOICE_PRINT, Boolean.valueOf(seedlingMediaData.getVoicePrintIsShow())).put(KEY_AUTOPLAY, Boolean.valueOf((z && seedlingMediaData.isAutoPlay()) ? true : TEST_DEBUG)).put(KEY_LOOP, Boolean.TRUE).put(KEY_VOICE_PRINT, string3).put("value", Long.valueOf(jComputerPosition)).put(KEY_CARD_SLIDER_DURATION, Long.valueOf(seedlingMediaData.getDuration() / 1000)).put(KEY_CARD_CHECKED, Boolean.valueOf(companion.isPlayState(oldState))).put(KEY_DEVICE_SHOW, Boolean.valueOf(z3));
        String action = seedlingMediaData.getAction();
        UiData uiDataPut6 = uiDataPut5.put(KEY_LAUNCH_INTENT_URI, action != null ? action : "").put(KEY_LAUNCH_INTENT_PACKAGE, seedlingMediaData.getPackageName()).put("mediaId", seedlingMediaData.getPlayerId()).put("voiceLabel", seedlingMediaData.getVoiceLabel()).put(KEY_VOICE_ANIM_DURATION, Integer.valueOf(seedlingMediaData.getInterval()));
        Uri artworkUri3 = seedlingMediaData.getArtworkUri();
        if (artworkUri3 != null) {
            string4 = artworkUri3;
        }
        uiDataPut6.put(KEY_BG_IMAGE_URI, string4).put(KEY_PLAY_STATE, Integer.valueOf(i)).put(KEY_STATIC_VOICE_PRINT_PLAYING, string).put(KEY_BACKGROUND_COLOR, seedlingMediaData.getArtworkBgColor()).put(KEY_ALBUM_ROTATION_DIRECTION, seedlingMediaData.getAlbumRotationDirection()).put(KEY_SHOW_APP_LOGO_ON_ALBUM_ARTWORK, Boolean.valueOf(z4)).put(KEY_SHOW_SMALL_ICON_BEFORE_ARTIST, bool).put(KEY_SMALL_ICON_BEFORE_ARTIST, str).put(KEY_SMALL_ICON_BEFORE_ARTIST_VOICE_LABEL, str2);
        for (int i2 = 0; i2 < 7; i2++) {
            MediaRecommendationData mediaRecommendationData = (MediaRecommendationData) CollectionsKt___CollectionsKt.getOrNull(seedlingMediaData.getRecommendations(), i2);
            uiData.put(ListPopupWindow$.ExternalSyntheticOutline0.m(i2, HAS_RECOMMENDATION_PREFIX), Boolean.valueOf(mediaRecommendationData != null ? true : TEST_DEBUG));
            if (mediaRecommendationData != null) {
                uiData.put(ListPopupWindow$.ExternalSyntheticOutline0.m(i2, RECOMMENDATION_PREFIX), mediaRecommendationData.getArtworkUri());
                uiData.put(RECOMMENDATION_VOICE_LABEL_PREFIX + i2, mediaRecommendationData.getVoiceLabel());
            }
        }
        return uiData;
    }

    public static /* synthetic */ UiData toUiDataWithoutButton$default(Context context, SeedlingMediaData seedlingMediaData, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            z2 = TEST_DEBUG;
        }
        return toUiDataWithoutButton(context, seedlingMediaData, z, z2);
    }

    public final Map<String, String> getMethodToPositionMap() {
        return methodToPositionMap;
    }

    public final boolean isExpRegion() {
        return ((Boolean) isExpRegion$delegate.getValue()).booleanValue();
    }
}
