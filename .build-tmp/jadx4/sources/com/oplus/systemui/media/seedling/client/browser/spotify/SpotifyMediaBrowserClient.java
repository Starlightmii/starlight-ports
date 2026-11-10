package com.oplus.systemui.media.seedling.client.browser.spotify;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.media.browse.MediaBrowser;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.systemui.media.seedling.OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0;
import com.oplus.systemui.media.seedling.OplusSeedlingMediaDataCombineLatest;
import com.oplus.systemui.media.seedling.client.browser.MediaBrowserClient;
import com.oplus.systemui.media.seedling.client.loader.album.CombinedAlbumLoader;
import com.oplus.systemui.media.seedling.client.loader.album.artwrok.UriArtworkLoader;
import com.oplus.systemui.media.seedling.client.loader.album.background.OplusArtworkBgColorLoader;
import com.oplus.systemui.media.seedling.utils.BitmapUtils;
import com.oplus.systemui.media.seedling.utils.FileUtil;
import com.oplus.systemui.seedlingservice.R;
import com.oplus.systemui.seedlingservice.R$drawable;
import com.oplus.systemui.seedlingservice.R$string;
import com.oplus.systemui.seedlingservice.mediaControl.MediaAction;
import com.oplus.systemui.seedlingservice.mediaControl.MediaButton;
import com.oplus.systemui.seedlingservice.mediaControl.MediaRecommendationData;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplus.systemui.seedlingservice.utils.UriExtKt;
import com.oplus.wrapper.os.UserHandle;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpotifyMediaBrowserClient extends MediaBrowserClient {
    private static final String ACTION_ADD_TO = "ADD_TO";
    private static final String ACTION_ADD_TO_YOUR_EPISODES = "ADD_TO_YOUR_EPISODES";
    private static final String ACTION_CHECK_FILL = "CHECK_FILL";
    private static final String ACTION_REMOVE_FROM_YOUR_EPISODES = "REMOVE_FROM_YOUR_EPISODES";
    private static final String ACTION_SEEK_15_SECONDS_BACK = "SEEK_15_SECONDS_BACK";
    private static final String ACTION_SEEK_15_SECONDS_FORWARD = "SEEK_15_SECONDS_FORWARD";
    private static final String BURIED_POINT_KEY = "com.spotify.music.extra.CONNECTION_POINT";
    private static final String BURIED_POINT_VALUE = "OplusAquaDynamics";
    public static final Companion Companion = new Companion();
    private static final String ICON_URI_KEY_TRANSFORMATION = "transformation";
    private static final String ICON_URI_VALUE_TRANSFORMATION_NONE = "NONE";
    public static final String PLAYBACK_STATE_EXTRA_KEY_IS_RECOMMENDATION = "com.spotify.music.extra.IS_RECOMMENDATION";
    private static final String TAG = "SpotifyMediaBrowserClient";
    private final Context context;
    private SeedlingMediaData mediaData;
    public final int recommendationArtworkHeight;
    public final int recommendationArtworkWidth;
    public int recommendationCount;
    private final Lazy recommendationNumber$delegate;

    public SpotifyMediaBrowserClient(Context context, SeedlingMediaData seedlingMediaData, ActivityStarter activityStarter, MediaOutputDialogManager mediaOutputDialogManager, OplusSeedlingMediaDataCombineLatest oplusSeedlingMediaDataCombineLatest, Handler handler, Handler handler2, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        super(context, seedlingMediaData, activityStarter, mediaOutputDialogManager, oplusSeedlingMediaDataCombineLatest, new SpotifyAppIconLoader(context), new CombinedAlbumLoader(new UriArtworkLoader(context, 0), new SpotifyArtworkBgColorLoader(), new OplusArtworkBgColorLoader()), handler, handler2, coroutineDispatcher, coroutineScope);
        this.context = context;
        this.mediaData = seedlingMediaData;
        this.recommendationNumber$delegate = LazyKt__LazyJVMKt.lazy(new SpotifyMediaBrowserClient$.ExternalSyntheticLambda0());
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.recommendation_artwork_width);
        this.recommendationArtworkWidth = dimensionPixelSize;
        this.recommendationArtworkHeight = dimensionPixelSize;
    }

    public static final void access$loadRecommendations(SpotifyMediaBrowserClient spotifyMediaBrowserClient, List list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = false;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            MediaBrowser.MediaItem mediaItem = (MediaBrowser.MediaItem) obj;
            OplusNotificationLogger.logMediaSeedling(TAG, "index " + i + " mediaItem " + mediaItem);
            if (arrayList.size() >= spotifyMediaBrowserClient.getRecommendationNumber()) {
                break;
            }
            String mediaId = mediaItem.getMediaId();
            MediaDescription description = mediaItem.getDescription();
            Bitmap iconBitmap = description.getIconBitmap();
            Uri iconUri = description.getIconUri();
            if (iconBitmap == null && iconUri != null) {
                Uri uriUpdateQueryParameter = UriExtKt.updateQueryParameter(iconUri, ICON_URI_KEY_TRANSFORMATION, ICON_URI_VALUE_TRANSFORMATION_NONE);
                if (!Intrinsics.areEqual(uriUpdateQueryParameter, iconUri)) {
                    OplusNotificationLogger.logMediaSeedling(TAG, "iconUri " + iconUri + " was updated to " + uriUpdateQueryParameter);
                }
                iconBitmap = BitmapUtils.loadBitmapFromUri(spotifyMediaBrowserClient.getContext(), uriUpdateQueryParameter, spotifyMediaBrowserClient.recommendationArtworkWidth, spotifyMediaBrowserClient.recommendationArtworkHeight);
            }
            if (iconBitmap != null) {
                try {
                    MediaRecommendationData mediaRecommendationDataCreateRecommendationIfNeed = spotifyMediaBrowserClient.createRecommendationIfNeed(mediaId, mediaItem, description);
                    arrayList.add(mediaRecommendationDataCreateRecommendationIfNeed);
                    if (iconBitmap.sameAs(mediaRecommendationDataCreateRecommendationIfNeed.getArtworkBitmap())) {
                        OplusNotificationLogger.logMediaSeedling(TAG, "loadMediaRecommendations artworkBitmap == albumBitmapMap[" + mediaId + "],don't update data");
                    } else {
                        OplusNotificationLogger.logMediaSeedling(TAG, "recommendationArtworkBitmap not same on " + mediaId);
                        mediaRecommendationDataCreateRecommendationIfNeed.setArtworkBitmap(iconBitmap);
                        int i3 = spotifyMediaBrowserClient.recommendationCount;
                        spotifyMediaBrowserClient.recommendationCount = i3 + 1;
                        String str = "com.spotify.music_" + (i3 % spotifyMediaBrowserClient.getRecommendationNumber()) + "_recommendationArtworkBitmap.png";
                        mediaRecommendationDataCreateRecommendationIfNeed.setArtworkUri(FileUtil.convertBitmapToUri(spotifyMediaBrowserClient.getContext(), iconBitmap, str));
                        OplusNotificationLogger.logMediaSeedlingDebug(TAG, "get recommendation artworkBitmap success: " + str + "; mediaId: " + mediaId);
                        z = true;
                    }
                } catch (IllegalArgumentException e) {
                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("loadMediaRecommendations occurs IllegalArgumentException: ", e.getMessage(), TAG);
                } catch (IllegalStateException e2) {
                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("loadMediaRecommendations occurs IllegalStateException: ", e2.getMessage(), TAG);
                }
            }
            i = i2;
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "newRecommendations size " + arrayList.size() + " hasChanged " + z);
        if (arrayList.size() < spotifyMediaBrowserClient.getRecommendationNumber()) {
            spotifyMediaBrowserClient.clearLocalRecommendations();
            spotifyMediaBrowserClient.dispatchMediaDataLoaded(0, "loadRecommendations clearLocalRecommendations");
        } else if (z) {
            spotifyMediaBrowserClient.getMediaData().getRecommendations().clear();
            spotifyMediaBrowserClient.getMediaData().getRecommendations().addAll(arrayList);
            spotifyMediaBrowserClient.dispatchMediaDataLoaded(0, "loadAlbumIconAndUpdate hasChanged");
        }
    }

    private final int getRecommendationNumber() {
        return ((Number) this.recommendationNumber$delegate.getValue()).intValue();
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public MediaButton createActionsFromState(PlaybackState playbackState) {
        MediaAction customAction;
        Object next;
        Object next2;
        MediaAction customAction2;
        MediaAction spotifyMediaAction;
        SpotifyMediaBrowserClient spotifyMediaBrowserClient;
        MediaAction mediaAction;
        Object next3;
        Object next4;
        Object next5;
        Object next6;
        List<PlaybackState.CustomAction> customActions = playbackState.getCustomActions();
        MediaAction spotifyMediaAction2 = SeedlingMediaData.Companion.isPlayState(playbackState.getState()) ? getSpotifyMediaAction(playbackState.getActions(), 2L, MediaButton.BTN_NAME_PLAY_OR_PAUSE) : getSpotifyMediaAction(playbackState.getActions(), 4L, MediaButton.BTN_NAME_PLAY_OR_PAUSE);
        customActions.getClass();
        List<PlaybackState.CustomAction> list = customActions;
        Iterator<T> it = list.iterator();
        do {
            customAction = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((PlaybackState.CustomAction) next).getAction(), ACTION_SEEK_15_SECONDS_FORWARD));
        PlaybackState.CustomAction customAction3 = (PlaybackState.CustomAction) next;
        Iterator<T> it2 = list.iterator();
        do {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
        } while (!Intrinsics.areEqual(((PlaybackState.CustomAction) next2).getAction(), ACTION_SEEK_15_SECONDS_BACK));
        PlaybackState.CustomAction customAction4 = (PlaybackState.CustomAction) next2;
        if (customAction3 == null && customAction4 == null) {
            spotifyMediaAction = getSpotifyMediaAction(playbackState.getActions(), 32L, MediaButton.BTN_NAME_NEXT_OR_CUSTOM);
            customAction2 = getSpotifyMediaAction(playbackState.getActions(), 16L, MediaButton.BTN_NAME_PREV_OR_CUSTOM);
        } else {
            MediaAction customAction5 = getCustomAction(customAction3, getContext().getString(R$string.spotifySeek15SecondsForwardBtnIconRes), getContext().getString(R$string.spotifySeek15SecondsForwardBtnIconRes), getContext().getString(R$string.spotifySeek15SecondsForwardBtnAnimRes), getContext().getString(R$string.controls_media_button_seek_15_seconds_forward), MediaButton.BTN_NAME_NEXT_OR_CUSTOM, R$drawable.spotify_seek15_forward);
            customAction2 = getCustomAction(customAction4, getContext().getString(R$string.spotifySeek15SecondsBackBtnIconRes), getContext().getString(R$string.spotifySeek15SecondsBackBtnIconRes), getContext().getString(R$string.spotifySeek15SecondsBackBtnAnimRes), getContext().getString(R$string.controls_media_button_seek_15_seconds_back), MediaButton.BTN_NAME_PREV_OR_CUSTOM, R$drawable.spotify_seek15_back);
            spotifyMediaAction = customAction5;
        }
        Pair pair = new Pair(customAction2, spotifyMediaAction);
        MediaAction mediaAction2 = (MediaAction) pair.component1();
        MediaAction mediaAction3 = (MediaAction) pair.component2();
        if (OsBinderCacheUtils.getCurrentUserId() == UserHandle.USER_SYSTEM) {
            HashMap mapHashMapOf = MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(getContext().getString(R$string.spotifyDeviceIcon), UriExtKt.toUri(R$drawable.spotify_devices)))});
            String deviceNameLabel = getMediaData().getDeviceNameLabel();
            if (deviceNameLabel == null) {
                deviceNameLabel = "";
            }
            mediaAction = new MediaAction(MediaButton.BTN_NAME_CUSTOM0, mapHashMapOf, null, deviceNameLabel, false, null, 52, null);
            spotifyMediaBrowserClient = this;
            mediaAction.setAction(new createActionsFromState.custom0Action.1.1(spotifyMediaBrowserClient));
        } else {
            spotifyMediaBrowserClient = this;
            mediaAction = null;
        }
        Iterator<T> it3 = list.iterator();
        do {
            if (!it3.hasNext()) {
                next3 = null;
                break;
            }
            next3 = it3.next();
        } while (!Intrinsics.areEqual(((PlaybackState.CustomAction) next3).getAction(), ACTION_ADD_TO));
        PlaybackState.CustomAction customAction6 = (PlaybackState.CustomAction) next3;
        Iterator<T> it4 = list.iterator();
        do {
            if (!it4.hasNext()) {
                next4 = null;
                break;
            }
            next4 = it4.next();
        } while (!Intrinsics.areEqual(((PlaybackState.CustomAction) next4).getAction(), ACTION_CHECK_FILL));
        PlaybackState.CustomAction customAction7 = (PlaybackState.CustomAction) next4;
        Iterator<T> it5 = list.iterator();
        do {
            if (!it5.hasNext()) {
                next5 = null;
                break;
            }
            next5 = it5.next();
        } while (!Intrinsics.areEqual(((PlaybackState.CustomAction) next5).getAction(), ACTION_ADD_TO_YOUR_EPISODES));
        PlaybackState.CustomAction customAction8 = (PlaybackState.CustomAction) next5;
        Iterator<T> it6 = list.iterator();
        do {
            if (!it6.hasNext()) {
                next6 = null;
                break;
            }
            next6 = it6.next();
        } while (!Intrinsics.areEqual(((PlaybackState.CustomAction) next6).getAction(), ACTION_REMOVE_FROM_YOUR_EPISODES));
        PlaybackState.CustomAction customAction9 = (PlaybackState.CustomAction) next6;
        if (customAction6 != null) {
            customAction = spotifyMediaBrowserClient.getCustomAction(customAction6, spotifyMediaBrowserClient.getContext().getString(R$string.spotifyAdd), spotifyMediaBrowserClient.getContext().getString(R$string.spotifyAdd), null, spotifyMediaBrowserClient.getContext().getString(R$string.controls_media_button_add_to_collection), MediaButton.BTN_NAME_CUSTOM1, R$drawable.spotify_add);
        } else if (customAction7 != null) {
            customAction = getCustomAction(customAction7, getContext().getString(R$string.spotifyRemove), getContext().getString(R$string.spotifyRemove), null, getContext().getString(R$string.controls_media_button_remove_from_collection), MediaButton.BTN_NAME_CUSTOM1, R$drawable.spotify_remove);
        } else if (customAction8 != null) {
            customAction = getCustomAction(customAction8, getContext().getString(R$string.spotifyAdd), getContext().getString(R$string.spotifyAdd), null, getContext().getString(R$string.controls_media_button_add_to_collection), MediaButton.BTN_NAME_CUSTOM1, R$drawable.spotify_add);
        } else if (customAction9 != null) {
            customAction = getCustomAction(customAction9, getContext().getString(R$string.spotifyRemove), getContext().getString(R$string.spotifyRemove), null, getContext().getString(R$string.controls_media_button_remove_from_collection), MediaButton.BTN_NAME_CUSTOM1, R$drawable.spotify_remove);
        }
        return new MediaButton(getMediaData().getPlayerId(), spotifyMediaAction2, mediaAction3, mediaAction2, mediaAction, customAction);
    }

    public final MediaRecommendationData createRecommendationIfNeed(String str, MediaBrowser.MediaItem mediaItem, MediaDescription mediaDescription) {
        Object next;
        Iterator<T> it = getMediaData().getRecommendations().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((MediaRecommendationData) next).getMediaItem().getMediaId(), str));
        MediaRecommendationData mediaRecommendationData = (MediaRecommendationData) next;
        if (mediaRecommendationData != null) {
            return mediaRecommendationData;
        }
        createRecommendationIfNeed.1 r7 = new createRecommendationIfNeed.1();
        r7.$mediaId = str;
        r7.$mediaItem = mediaItem;
        r7.this$0 = this;
        r7.$mediaDescription = mediaDescription;
        VarHandle.storeStoreFence();
        return new MediaRecommendationData(mediaItem, null, null, null, r7, 8, null);
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public Long executePlaybackStateChangedTaskTimeThreshold() {
        return 50L;
    }

    @Override // com.oplus.systemui.media.seedling.client.browser.MediaBrowserClient
    public void fillRootHints(Bundle bundle) {
        bundle.putBoolean("android.service.media.extra.SUGGESTED", true);
        bundle.putString(BURIED_POINT_KEY, BURIED_POINT_VALUE);
    }

    @Override // com.oplus.systemui.media.seedling.client.browser.MediaBrowserClient, com.oplus.systemui.media.seedling.client.AbsMediaClient
    public Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.media.seedling.client.browser.MediaBrowserClient, com.oplus.systemui.media.seedling.client.AbsMediaClient
    public SeedlingMediaData getMediaData() {
        return this.mediaData;
    }

    public final MediaAction getSpotifyMediaAction(long j, long j2, String str) {
        int i;
        MediaAction standardAction = getStandardAction(j, j2, str);
        if (standardAction != null) {
            if (j2 == 4) {
                i = R$string.spotifyPlayBtnIconRes;
            } else if (j2 == 2) {
                i = R$string.spotifyPauseBtnIconRes;
            } else if (j2 == 16) {
                i = R$string.spotifyPrevBtnIconRes;
            } else if (j2 == 32) {
                i = R$string.spotifyNextBtnIconRes;
            }
            if (j2 == 16 || j2 == 32) {
                standardAction.setAnimRes(j2 == 32 ? getContext().getString(R$string.spotifyNextAnimRes) : getContext().getString(R$string.spotifyPrevAnimRes));
            }
            standardAction.getIconMap().put(MediaAction.ICON_DEFAULT, TuplesKt.to(getContext().getString(i), (Object) null));
        }
        return standardAction;
    }

    @Override // com.oplus.systemui.media.seedling.client.browser.MediaBrowserClient
    public void handleOnChildrenLoaded(String str, List<? extends MediaBrowser.MediaItem> list) {
        if (list.isEmpty()) {
            return;
        }
        Handler bgHandler = getBgHandler();
        handleOnChildrenLoaded.1 r0 = new handleOnChildrenLoaded.1();
        r0.this$0 = this;
        r0.$children = list;
        VarHandle.storeStoreFence();
        bgHandler.post(r0);
    }

    @Override // com.oplus.systemui.media.seedling.client.browser.MediaBrowserClient, com.oplus.systemui.media.seedling.client.AbsMediaClient
    public void setMediaData(SeedlingMediaData seedlingMediaData) {
        this.mediaData = seedlingMediaData;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public boolean updateMediaButton(PlaybackState playbackState) {
        boolean z;
        Bundle extras;
        boolean zUpdateMediaButton = super.updateMediaButton(playbackState);
        Boolean boolValueOf = (playbackState == null || (extras = playbackState.getExtras()) == null) ? null : Boolean.valueOf(extras.getBoolean(PLAYBACK_STATE_EXTRA_KEY_IS_RECOMMENDATION));
        boolean zIsRecommendation = getMediaData().isRecommendation();
        OplusNotificationLogger.logMediaSeedling(TAG, "new isRecommendation " + boolValueOf + " current IsRecommendation " + zIsRecommendation);
        if (Intrinsics.areEqual(boolValueOf, Boolean.valueOf(zIsRecommendation))) {
            z = false;
        } else {
            getMediaData().setRecommendation(boolValueOf != null ? boolValueOf.booleanValue() : false);
            z = true;
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "baseInfoChanged " + zUpdateMediaButton + " isRecommendationsChanged " + z);
        return zUpdateMediaButton || z;
    }
}
