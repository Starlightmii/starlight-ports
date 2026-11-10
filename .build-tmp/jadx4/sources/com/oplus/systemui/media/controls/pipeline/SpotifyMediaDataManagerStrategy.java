package com.oplus.systemui.media.controls.pipeline;

import android.R;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.text.TextUtils;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.media.controls.models.player.OplusMediaActionEx;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SpotifyMediaDataManagerStrategy implements BaseMediaDataManagerStrategy {
    private static final String ACTION_ADD_TO = "ADD_TO";
    private static final String ACTION_ADD_TO_YOUR_EPISODES = "ADD_TO_YOUR_EPISODES";
    private static final String ACTION_CHECK_FILL = "CHECK_FILL";
    private static final String ACTION_REMOVE_FROM_YOUR_EPISODES = "REMOVE_FROM_YOUR_EPISODES";
    private static final String ACTION_SEEK_15_SECONDS_BACK = "SEEK_15_SECONDS_BACK";
    private static final String ACTION_SEEK_15_SECONDS_FORWARD = "SEEK_15_SECONDS_FORWARD";
    private static final String CONTEXT_URI_METADATA_KEY = "com.spotify.music.extra.CONTEXT_URI";
    public static final Companion Companion = new Companion();
    private static final String SPOTIFY_LIVE_STREAM_CODE_FORMAT = "spotify:room:";
    public static final String SPOTIFY_PKG = "com.spotify.music";
    private static final String TAG = "SpotifyMediaDataManager";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isSpotify(String str) {
            return "com.spotify.music".equals(str);
        }
    }

    public SpotifyMediaDataManagerStrategy(Context context) {
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.media.controls.pipeline.BaseMediaDataManagerStrategy
    public MediaButton createActionsFromState(String str, MediaData mediaData, MediaController mediaController) {
        String string;
        SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0 externalSyntheticLambda0;
        MediaAction mediaAction;
        SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0 externalSyntheticLambda1;
        SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0 externalSyntheticLambda2;
        MediaAction prevAction;
        MediaAction nextAction;
        MediaAction customAction;
        MediaAction customAction2;
        MediaAction customAction3;
        PlaybackState playbackState = mediaController.getPlaybackState();
        if (playbackState == null) {
            return null;
        }
        MediaMetadata metadata = mediaController.getMetadata();
        if (metadata == null || (string = metadata.getString(CONTEXT_URI_METADATA_KEY)) == null) {
            string = "";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (playbackState.getCustomActions() != null) {
            for (PlaybackState.CustomAction customAction4 : playbackState.getCustomActions()) {
                if (customAction4 != null) {
                }
            }
        }
        if (LogUtil.getSInternal()) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "createActionsFromState contextUri: ".concat(string));
        }
        if (NotificationMediaManager.CONNECTING_MEDIA_STATES.contains(Integer.valueOf(playbackState.getState()))) {
            Icon iconCreateWithResource = Icon.createWithResource(this.context, R.drawable.rate_star_small_on_holo_light);
            Drawable drawableLoadDrawable = iconCreateWithResource.loadDrawable(this.context);
            if (drawableLoadDrawable instanceof Animatable) {
                ((Animatable) drawableLoadDrawable).start();
            }
            mediaAction = new MediaAction(drawableLoadDrawable, (Runnable) null, this.context.getString(2131952778), (Drawable) null, Integer.valueOf(R.drawable.rate_star_small_on_holo_light), new OplusMediaActionEx(false, iconCreateWithResource, (Uri) null, false, (Boolean) null, false, (String) null, 125));
        } else if (!NotificationMediaManager.isPlayingState(playbackState.getState())) {
            boolean z = (playbackState.getActions() & 4) != 0;
            Icon iconCreateWithResource2 = Icon.createWithResource(this.context, z ? 2131234548 : 2131234549);
            Drawable drawableLoadDrawable2 = iconCreateWithResource2.loadDrawable(this.context);
            if (z) {
                externalSyntheticLambda0 = new SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0(4);
                externalSyntheticLambda0.f$0 = mediaController;
                VarHandle.storeStoreFence();
            } else {
                externalSyntheticLambda0 = null;
            }
            mediaAction = new MediaAction(drawableLoadDrawable2, externalSyntheticLambda0 != null ? new sam.java_lang_Runnable.0(externalSyntheticLambda0) : null, this.context.getString(2131952782), (Drawable) null, (Integer) null, new OplusMediaActionEx(z, iconCreateWithResource2, (Uri) null, false, (Boolean) null, true, (String) null, 92), 16);
        } else if (StringsKt__StringsJVMKt.startsWith(string, SPOTIFY_LIVE_STREAM_CODE_FORMAT, false)) {
            boolean z2 = (playbackState.getActions() & 1) != 0;
            Icon iconCreateWithResource3 = Icon.createWithResource(this.context, z2 ? 2131234559 : 2131234560);
            Drawable drawableLoadDrawable3 = iconCreateWithResource3.loadDrawable(this.context);
            if (z2) {
                externalSyntheticLambda2 = new SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0(3);
                externalSyntheticLambda2.f$0 = mediaController;
                VarHandle.storeStoreFence();
            } else {
                externalSyntheticLambda2 = null;
            }
            mediaAction = new MediaAction(drawableLoadDrawable3, externalSyntheticLambda2 != null ? new sam.java_lang_Runnable.0(externalSyntheticLambda2) : null, this.context.getString(2131953666), (Drawable) null, (Integer) null, new OplusMediaActionEx(z2, iconCreateWithResource3, (Uri) null, false, (Boolean) null, false, (String) null, 124), 16);
        } else {
            boolean z3 = (playbackState.getActions() & 2) != 0;
            Icon iconCreateWithResource4 = Icon.createWithResource(this.context, z3 ? 2131234546 : 2131234547);
            Drawable drawableLoadDrawable4 = iconCreateWithResource4.loadDrawable(this.context);
            if (z3) {
                externalSyntheticLambda1 = new SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0(1);
                externalSyntheticLambda1.f$0 = mediaController;
                VarHandle.storeStoreFence();
            } else {
                externalSyntheticLambda1 = null;
            }
            mediaAction = new MediaAction(drawableLoadDrawable4, externalSyntheticLambda1 != null ? new sam.java_lang_Runnable.0(externalSyntheticLambda1) : null, this.context.getString(2131952781), (Drawable) null, (Integer) null, new OplusMediaActionEx(z3, iconCreateWithResource4, (Uri) null, false, (Boolean) null, true, (String) null, 92), 16);
        }
        MediaAction mediaAction2 = mediaAction;
        boolean z4 = (playbackState.getActions() & 16) != 0;
        boolean z5 = (playbackState.getActions() & 32) != 0;
        if (z4 || z5) {
            prevAction = getPrevAction(mediaController, z4);
            nextAction = getNextAction(mediaController, z5);
        } else {
            prevAction = null;
            nextAction = null;
        }
        if (!linkedHashMap.containsKey(ACTION_SEEK_15_SECONDS_FORWARD) && !linkedHashMap.containsKey(ACTION_SEEK_15_SECONDS_BACK)) {
            if (linkedHashMap.containsKey(ACTION_CHECK_FILL)) {
                customAction3 = getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_CHECK_FILL), mediaController, 2131234553, 2131234554);
            } else if (linkedHashMap.containsKey(ACTION_ADD_TO)) {
                customAction3 = getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_ADD_TO), mediaController, 2131234539, 2131234540);
            } else if (linkedHashMap.containsKey(ACTION_ADD_TO_YOUR_EPISODES)) {
                customAction3 = getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_ADD_TO_YOUR_EPISODES), mediaController, 2131234539, 2131234540);
            } else {
                customAction3 = linkedHashMap.containsKey(ACTION_REMOVE_FROM_YOUR_EPISODES) ? getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_REMOVE_FROM_YOUR_EPISODES), mediaController, 2131234553, 2131234554) : null;
            }
            customAction2 = null;
            customAction = customAction3;
        } else if (prevAction == null && nextAction == null) {
            prevAction = getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_SEEK_15_SECONDS_BACK), mediaController, 2131234555, 2131234556);
            nextAction = getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_SEEK_15_SECONDS_FORWARD), mediaController, 2131234557, 2131234558);
            customAction = null;
            customAction2 = null;
        } else {
            customAction = getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_SEEK_15_SECONDS_FORWARD), mediaController, 2131234557, 2131234558);
            customAction2 = getCustomAction((PlaybackState.CustomAction) linkedHashMap.get(ACTION_SEEK_15_SECONDS_BACK), mediaController, 2131234555, 2131234556);
        }
        if (prevAction == null) {
            prevAction = getPrevAction(mediaController, z4);
        }
        MediaAction mediaAction3 = prevAction;
        if (nextAction == null) {
            nextAction = getNextAction(mediaController, z5);
        }
        return new MediaButton(mediaAction2, nextAction, mediaAction3, customAction, customAction2, false, false, 224);
    }

    public final MediaAction getCustomAction(PlaybackState.CustomAction customAction, MediaController mediaController, int i, int i2) {
        SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda5 externalSyntheticLambda5;
        if (customAction == null) {
            Icon iconCreateWithResource = Icon.createWithResource(this.context, i2);
            return new MediaAction(iconCreateWithResource.loadDrawable(this.context), (Runnable) null, "", (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource, (Uri) null, false, (Boolean) null, false, (String) null, 124), 16);
        }
        boolean zIsEmpty = TextUtils.isEmpty(customAction.getAction());
        boolean z = !zIsEmpty;
        Icon iconCreateWithResource2 = Icon.createWithResource(this.context, i);
        Drawable drawableLoadDrawable = iconCreateWithResource2.loadDrawable(this.context);
        if (zIsEmpty) {
            externalSyntheticLambda5 = null;
        } else {
            externalSyntheticLambda5 = new SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda5();
            externalSyntheticLambda5.f$0 = customAction;
            externalSyntheticLambda5.f$1 = mediaController;
            VarHandle.storeStoreFence();
        }
        return new MediaAction(drawableLoadDrawable, externalSyntheticLambda5 != null ? new sam.java_lang_Runnable.0(externalSyntheticLambda5) : null, customAction.getName(), (Drawable) null, (Integer) null, new OplusMediaActionEx(z, iconCreateWithResource2, (Uri) null, false, (Boolean) null, false, (String) null, 124), 16);
    }

    @Override // com.oplus.systemui.media.controls.pipeline.BaseMediaDataManagerStrategy
    public /* bridge */ /* synthetic */ MediaAction getHeartAction(MediaController mediaController, String str) {
        return super.getHeartAction(mediaController, str);
    }

    public final MediaAction getNextAction(MediaController mediaController, boolean z) {
        SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0 externalSyntheticLambda0;
        Icon iconCreateWithResource = Icon.createWithResource(this.context, z ? 2131234544 : 2131234545);
        Drawable drawableLoadDrawable = iconCreateWithResource.loadDrawable(this.context);
        if (z) {
            externalSyntheticLambda0 = new SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0(2);
            externalSyntheticLambda0.f$0 = mediaController;
            VarHandle.storeStoreFence();
        } else {
            externalSyntheticLambda0 = null;
        }
        return new MediaAction(drawableLoadDrawable, externalSyntheticLambda0 != null ? new sam.java_lang_Runnable.0(externalSyntheticLambda0) : null, this.context.getString(2131952779), (Drawable) null, (Integer) null, new OplusMediaActionEx(z, iconCreateWithResource, (Uri) null, false, (Boolean) null, true, (String) null, 92), 16);
    }

    public final MediaAction getPrevAction(MediaController mediaController, boolean z) {
        SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0 externalSyntheticLambda0;
        Icon iconCreateWithResource = Icon.createWithResource(this.context, z ? 2131234550 : 2131234551);
        Drawable drawableLoadDrawable = iconCreateWithResource.loadDrawable(this.context);
        if (z) {
            externalSyntheticLambda0 = new SpotifyMediaDataManagerStrategy$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = mediaController;
            VarHandle.storeStoreFence();
        } else {
            externalSyntheticLambda0 = null;
        }
        return new MediaAction(drawableLoadDrawable, externalSyntheticLambda0 != null ? new sam.java_lang_Runnable.0(externalSyntheticLambda0) : null, this.context.getString(2131952783), (Drawable) null, (Integer) null, new OplusMediaActionEx(z, iconCreateWithResource, (Uri) null, false, (Boolean) null, true, (String) null, 92), 16);
    }

    @Override // com.oplus.systemui.media.controls.pipeline.BaseMediaDataManagerStrategy
    public boolean onMatching(String str) {
        return Companion.isSpotify(str);
    }
}
