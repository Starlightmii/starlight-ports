package com.oplus.systemui.seedlingservice.mediaControl;

import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.SystemClock;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.transition.OplusAnimationController;
import com.oplus.transition.ShellContinuousTransitionController;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Pair;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingMediaData {
    public static final int DEFAULT_VALUE = 0;
    public static final int INTERVAL_DURATION = 50;
    public static final int PLAYBACK_CAST_LOCAL = 1;
    public static final int PLAYBACK_CAST_REMOTE = 2;
    public static final int PLAYBACK_LOCAL = 0;
    private String action;
    private boolean active;
    private String albumRotationDirection;
    private Icon appIcon;
    private String appIconResId;
    private Uri appIconUri;
    private String appLabel;
    private CharSequence artist;
    private String artworkBgColor;
    private String artworkColor;
    private Icon artworkIcon;
    private Uri artworkUri;
    private boolean artworkUriContentChanged;
    private final long createTime;
    private CharSequence currentLyric;
    private Pair<? extends Uri, ? extends Uri> custom0IconUri;
    private Pair<? extends Uri, ? extends Uri> custom1IconUri;
    private Uri deviceIconUri;
    private boolean deviceIconUriChanged;
    private String deviceNameLabel;
    private long duration;
    private int interval;
    private boolean isAutoPlay;
    private boolean isPlayStateChanged;
    private boolean isRecommendation;
    private boolean isSpotifyWhichSupportsOplusMediaController;
    private long lastActiveTime;
    private long lastPositionUpdateTime;
    private LyricData lyricData;
    private boolean lyricSupport;
    private MediaButton mediaButton;
    private boolean mediaButtonUriChanged;
    private Pair<? extends Uri, ? extends Uri> nextIconUri;
    private int oldState;
    private String packageName;
    private Pair<? extends Uri, ? extends Uri> playOrPauseIconUri;
    private int playbackLocation;
    private float playbackSpeed;
    private String playerId;
    private long position;
    private Pair<? extends Uri, ? extends Uri> preIconUri;
    private CopyOnWriteArrayList<MediaRecommendationData> recommendations;
    private boolean seekAvailable;
    private boolean seekEnable;
    private boolean shouldShowLyric;
    private CharSequence song;
    private CharSequence songInfoInLyric;
    private int state;
    private MediaSession.Token token;
    private int userId;
    private String voiceLabel;
    private boolean voicePrintIsShow;
    public static final Companion Companion = new Companion(null);
    private static final HashSet<Integer> PAUSED_MEDIA_STATES = SetsKt__SetsKt.hashSetOf(new Integer[]{0, 1, 2, 7});
    private static final HashSet<Integer> CONNECTING_MEDIA_STATES = SetsKt__SetsKt.hashSetOf(new Integer[]{8, 6});

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ SeedlingMediaData(String str, MediaSession.Token token, CharSequence charSequence, CharSequence charSequence2, long j, int i, String str2, Uri uri, Icon icon, String str3, Uri uri2, String str4, String str5, Icon icon2, boolean z, Pair pair, Pair pair2, Pair pair3, Pair pair4, Pair pair5, boolean z2, Uri uri3, boolean z3, String str6, int i2, int i3, int i4, long j2, boolean z4, boolean z5, long j3, float f, boolean z6, String str7, String str8, long j4, boolean z7, long j5, boolean z8, String str9, int i5, boolean z9, String str10, MediaButton mediaButton, boolean z10, boolean z11, CopyOnWriteArrayList copyOnWriteArrayList, LyricData lyricData, CharSequence charSequence3, CharSequence charSequence4, boolean z12, boolean z13, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        Uri uri4;
        Pair pair6;
        String str11 = (i6 & 1) != 0 ? "" : str;
        MediaSession.Token token2 = (i6 & 2) != 0 ? null : token;
        CharSequence charSequence5 = (i6 & 4) != 0 ? null : charSequence;
        CharSequence charSequence6 = (i6 & 8) != 0 ? null : charSequence2;
        long j6 = (i6 & 16) != 0 ? 0L : j;
        int i8 = (i6 & 32) != 0 ? 0 : i;
        String str12 = (i6 & 64) != 0 ? null : str2;
        Uri uri5 = (i6 & 128) != 0 ? null : uri;
        Icon icon3 = (i6 & 256) != 0 ? null : icon;
        String str13 = (i6 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? null : str3;
        Uri uri6 = (i6 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? null : uri2;
        String str14 = (i6 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? "#1acc5151" : str4;
        String str15 = str11;
        String str16 = (i6 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? null : str5;
        Icon icon4 = (i6 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? null : icon2;
        boolean z14 = (i6 & 16384) != 0 ? false : z;
        if ((i6 & 32768) != 0) {
            uri4 = null;
            pair6 = new Pair((Object) null, (Object) null);
        } else {
            uri4 = null;
            pair6 = pair;
        }
        this(str15, token2, charSequence5, charSequence6, j6, i8, str12, uri5, icon3, str13, uri6, str14, str16, icon4, z14, pair6, (i6 & OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS) != 0 ? new Pair(uri4, uri4) : pair2, (i6 & 131072) != 0 ? new Pair(uri4, uri4) : pair3, (i6 & OplusAnimationController.FLAG_CANCEL_FIXED_ROTATION) != 0 ? new Pair(uri4, uri4) : pair4, (i6 & 524288) != 0 ? new Pair(uri4, uri4) : pair5, (i6 & ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0 ? false : z2, (i6 & 2097152) != 0 ? uri4 : uri3, (i6 & 4194304) != 0 ? false : z3, (i6 & 8388608) != 0 ? uri4 : str6, (i6 & 16777216) != 0 ? 0 : i2, (i6 & 33554432) != 0 ? 0 : i3, (i6 & 67108864) != 0 ? 0 : i4, (i6 & 134217728) != 0 ? 0L : j2, (i6 & 268435456) != 0 ? true : z4, (i6 & 536870912) != 0 ? true : z5, (i6 & 1073741824) != 0 ? 0L : j3, (i6 & Integer.MIN_VALUE) != 0 ? 0.0f : f, (i7 & 1) != 0 ? true : z6, (i7 & 2) != 0 ? uri4 : str7, (i7 & 4) != 0 ? "" : str8, (i7 & 8) != 0 ? System.currentTimeMillis() : j4, (i7 & 16) != 0 ? true : z7, (i7 & 32) != 0 ? 0L : j5, (i7 & 64) != 0 ? false : z8, (i7 & 128) != 0 ? "" : str9, (i7 & 256) != 0 ? 50 : i5, (i7 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? false : z9, (i7 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) == 0 ? str10 : "", (i7 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? null : mediaButton, (i7 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? false : z10, (i7 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? false : z11, (i7 & 16384) != 0 ? new CopyOnWriteArrayList() : copyOnWriteArrayList, (i7 & 32768) != 0 ? new LyricData(null, null, null, null, false, false, 0, 79, null) : lyricData, (i7 & OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS) != 0 ? null : charSequence3, (i7 & 131072) != 0 ? null : charSequence4, (i7 & OplusAnimationController.FLAG_CANCEL_FIXED_ROTATION) != 0 ? false : z12, (i7 & 524288) != 0 ? true : z13);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SeedlingMediaData copy$default(SeedlingMediaData seedlingMediaData, String str, MediaSession.Token token, CharSequence charSequence, CharSequence charSequence2, long j, int i, String str2, Uri uri, Icon icon, String str3, Uri uri2, String str4, String str5, Icon icon2, boolean z, Pair pair, Pair pair2, Pair pair3, Pair pair4, Pair pair5, boolean z2, Uri uri3, boolean z3, String str6, int i2, int i3, int i4, long j2, boolean z4, boolean z5, long j3, float f, boolean z6, String str7, String str8, long j4, boolean z7, long j5, boolean z8, String str9, int i5, boolean z9, String str10, MediaButton mediaButton, boolean z10, boolean z11, CopyOnWriteArrayList copyOnWriteArrayList, LyricData lyricData, CharSequence charSequence3, CharSequence charSequence4, boolean z12, boolean z13, int i6, int i7, Object obj) {
        String str11 = (i6 & 1) != 0 ? seedlingMediaData.playerId : str;
        MediaSession.Token token2 = (i6 & 2) != 0 ? seedlingMediaData.token : token;
        CharSequence charSequence5 = (i6 & 4) != 0 ? seedlingMediaData.artist : charSequence;
        CharSequence charSequence6 = (i6 & 8) != 0 ? seedlingMediaData.song : charSequence2;
        long j6 = (i6 & 16) != 0 ? seedlingMediaData.duration : j;
        int i8 = (i6 & 32) != 0 ? seedlingMediaData.userId : i;
        String str12 = (i6 & 64) != 0 ? seedlingMediaData.appIconResId : str2;
        Uri uri4 = (i6 & 128) != 0 ? seedlingMediaData.appIconUri : uri;
        Icon icon3 = (i6 & 256) != 0 ? seedlingMediaData.appIcon : icon;
        String str13 = (i6 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? seedlingMediaData.appLabel : str3;
        Uri uri5 = (i6 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? seedlingMediaData.artworkUri : uri2;
        String str14 = (i6 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? seedlingMediaData.artworkBgColor : str4;
        String str15 = str11;
        String str16 = (i6 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? seedlingMediaData.artworkColor : str5;
        Icon icon4 = (i6 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? seedlingMediaData.artworkIcon : icon2;
        boolean z14 = (i6 & 16384) != 0 ? seedlingMediaData.artworkUriContentChanged : z;
        Pair pair6 = (i6 & 32768) != 0 ? seedlingMediaData.custom0IconUri : pair;
        Pair pair7 = (i6 & OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS) != 0 ? seedlingMediaData.playOrPauseIconUri : pair2;
        Pair pair8 = (i6 & 131072) != 0 ? seedlingMediaData.preIconUri : pair3;
        Pair pair9 = (i6 & OplusAnimationController.FLAG_CANCEL_FIXED_ROTATION) != 0 ? seedlingMediaData.nextIconUri : pair4;
        Pair pair10 = (i6 & 524288) != 0 ? seedlingMediaData.custom1IconUri : pair5;
        boolean z15 = (i6 & ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0 ? seedlingMediaData.mediaButtonUriChanged : z2;
        Uri uri6 = (i6 & 2097152) != 0 ? seedlingMediaData.deviceIconUri : uri3;
        boolean z16 = (i6 & 4194304) != 0 ? seedlingMediaData.deviceIconUriChanged : z3;
        String str17 = (i6 & 8388608) != 0 ? seedlingMediaData.deviceNameLabel : str6;
        int i9 = (i6 & 16777216) != 0 ? seedlingMediaData.playbackLocation : i2;
        int i10 = (i6 & 33554432) != 0 ? seedlingMediaData.state : i3;
        int i11 = (i6 & 67108864) != 0 ? seedlingMediaData.oldState : i4;
        boolean z17 = z14;
        MediaSession.Token token3 = token2;
        long j7 = (i6 & 134217728) != 0 ? seedlingMediaData.position : j2;
        int i12 = i11;
        boolean z18 = (i6 & 268435456) != 0 ? seedlingMediaData.seekAvailable : z4;
        boolean z19 = (i6 & 536870912) != 0 ? seedlingMediaData.seekEnable : z5;
        long j8 = j7;
        long j9 = (i6 & 1073741824) != 0 ? seedlingMediaData.lastPositionUpdateTime : j3;
        boolean z20 = z19;
        float f2 = (i6 & Integer.MIN_VALUE) != 0 ? seedlingMediaData.playbackSpeed : f;
        boolean z21 = (i7 & 1) != 0 ? seedlingMediaData.voicePrintIsShow : z6;
        String str18 = (i7 & 2) != 0 ? seedlingMediaData.action : str7;
        String str19 = (i7 & 4) != 0 ? seedlingMediaData.packageName : str8;
        return seedlingMediaData.copy(str15, token3, charSequence5, charSequence6, j6, i8, str12, uri4, icon3, str13, uri5, str14, str16, icon4, z17, pair6, pair7, pair8, pair9, pair10, z15, uri6, z16, str17, i9, i10, i12, j8, z18, z20, j9, f2, z21, str18, str19, (i7 & 8) != 0 ? seedlingMediaData.createTime : j4, (i7 & 16) != 0 ? seedlingMediaData.active : z7, (i7 & 32) != 0 ? seedlingMediaData.lastActiveTime : j5, (i7 & 64) != 0 ? seedlingMediaData.isAutoPlay : z8, (i7 & 128) != 0 ? seedlingMediaData.voiceLabel : str9, (i7 & 256) != 0 ? seedlingMediaData.interval : i5, (i7 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? seedlingMediaData.isPlayStateChanged : z9, (i7 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? seedlingMediaData.albumRotationDirection : str10, (i7 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? seedlingMediaData.mediaButton : mediaButton, (i7 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? seedlingMediaData.isRecommendation : z10, (i7 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? seedlingMediaData.isSpotifyWhichSupportsOplusMediaController : z11, (i7 & 16384) != 0 ? seedlingMediaData.recommendations : copyOnWriteArrayList, (i7 & 32768) != 0 ? seedlingMediaData.lyricData : lyricData, (i7 & OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS) != 0 ? seedlingMediaData.currentLyric : charSequence3, (i7 & 131072) != 0 ? seedlingMediaData.songInfoInLyric : charSequence4, (i7 & OplusAnimationController.FLAG_CANCEL_FIXED_ROTATION) != 0 ? seedlingMediaData.shouldShowLyric : z12, (i7 & 524288) != 0 ? seedlingMediaData.lyricSupport : z13);
    }

    public final String component1() {
        return this.playerId;
    }

    public final String component10() {
        return this.appLabel;
    }

    public final Uri component11() {
        return this.artworkUri;
    }

    public final String component12() {
        return this.artworkBgColor;
    }

    public final String component13() {
        return this.artworkColor;
    }

    public final Icon component14() {
        return this.artworkIcon;
    }

    public final boolean component15() {
        return this.artworkUriContentChanged;
    }

    public final Pair<Uri, Uri> component16() {
        return this.custom0IconUri;
    }

    public final Pair<Uri, Uri> component17() {
        return this.playOrPauseIconUri;
    }

    public final Pair<Uri, Uri> component18() {
        return this.preIconUri;
    }

    public final Pair<Uri, Uri> component19() {
        return this.nextIconUri;
    }

    public final MediaSession.Token component2() {
        return this.token;
    }

    public final Pair<Uri, Uri> component20() {
        return this.custom1IconUri;
    }

    public final boolean component21() {
        return this.mediaButtonUriChanged;
    }

    public final Uri component22() {
        return this.deviceIconUri;
    }

    public final boolean component23() {
        return this.deviceIconUriChanged;
    }

    public final String component24() {
        return this.deviceNameLabel;
    }

    public final int component25() {
        return this.playbackLocation;
    }

    public final int component26() {
        return this.state;
    }

    public final int component27() {
        return this.oldState;
    }

    public final long component28() {
        return this.position;
    }

    public final boolean component29() {
        return this.seekAvailable;
    }

    public final CharSequence component3() {
        return this.artist;
    }

    public final boolean component30() {
        return this.seekEnable;
    }

    public final long component31() {
        return this.lastPositionUpdateTime;
    }

    public final float component32() {
        return this.playbackSpeed;
    }

    public final boolean component33() {
        return this.voicePrintIsShow;
    }

    public final String component34() {
        return this.action;
    }

    public final String component35() {
        return this.packageName;
    }

    public final long component36() {
        return this.createTime;
    }

    public final boolean component37() {
        return this.active;
    }

    public final long component38() {
        return this.lastActiveTime;
    }

    public final boolean component39() {
        return this.isAutoPlay;
    }

    public final CharSequence component4() {
        return this.song;
    }

    public final String component40() {
        return this.voiceLabel;
    }

    public final int component41() {
        return this.interval;
    }

    public final boolean component42() {
        return this.isPlayStateChanged;
    }

    public final String component43() {
        return this.albumRotationDirection;
    }

    public final MediaButton component44() {
        return this.mediaButton;
    }

    public final boolean component45() {
        return this.isRecommendation;
    }

    public final boolean component46() {
        return this.isSpotifyWhichSupportsOplusMediaController;
    }

    public final CopyOnWriteArrayList<MediaRecommendationData> component47() {
        return this.recommendations;
    }

    public final LyricData component48() {
        return this.lyricData;
    }

    public final CharSequence component49() {
        return this.currentLyric;
    }

    public final long component5() {
        return this.duration;
    }

    public final CharSequence component50() {
        return this.songInfoInLyric;
    }

    public final boolean component51() {
        return this.shouldShowLyric;
    }

    public final boolean component52() {
        return this.lyricSupport;
    }

    public final int component6() {
        return this.userId;
    }

    public final String component7() {
        return this.appIconResId;
    }

    public final Uri component8() {
        return this.appIconUri;
    }

    public final Icon component9() {
        return this.appIcon;
    }

    public final long computerPosition() {
        if (Companion.isInMotion(this.state)) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.lastPositionUpdateTime;
            if (j > 0) {
                long j2 = ((long) (this.playbackSpeed * (jElapsedRealtime - j))) + this.position;
                long j3 = this.duration;
                if (0 <= j3 && j3 < j2) {
                    return j3;
                }
                if (j2 < 0) {
                    return 0L;
                }
                return j2;
            }
        }
        return this.position;
    }

    public final SeedlingMediaData copy(String str, MediaSession.Token token, CharSequence charSequence, CharSequence charSequence2, long j, int i, String str2, Uri uri, Icon icon, String str3, Uri uri2, String str4, String str5, Icon icon2, boolean z, Pair<? extends Uri, ? extends Uri> pair, Pair<? extends Uri, ? extends Uri> pair2, Pair<? extends Uri, ? extends Uri> pair3, Pair<? extends Uri, ? extends Uri> pair4, Pair<? extends Uri, ? extends Uri> pair5, boolean z2, Uri uri3, boolean z3, String str6, int i2, int i3, int i4, long j2, boolean z4, boolean z5, long j3, float f, boolean z6, String str7, String str8, long j4, boolean z7, long j5, boolean z8, String str9, int i5, boolean z9, String str10, MediaButton mediaButton, boolean z10, boolean z11, CopyOnWriteArrayList<MediaRecommendationData> copyOnWriteArrayList, LyricData lyricData, CharSequence charSequence3, CharSequence charSequence4, boolean z12, boolean z13) {
        return new SeedlingMediaData(str, token, charSequence, charSequence2, j, i, str2, uri, icon, str3, uri2, str4, str5, icon2, z, pair, pair2, pair3, pair4, pair5, z2, uri3, z3, str6, i2, i3, i4, j2, z4, z5, j3, f, z6, str7, str8, j4, z7, j5, z8, str9, i5, z9, str10, mediaButton, z10, z11, copyOnWriteArrayList, lyricData, charSequence3, charSequence4, z12, z13);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeedlingMediaData)) {
            return false;
        }
        SeedlingMediaData seedlingMediaData = (SeedlingMediaData) obj;
        return Intrinsics.areEqual(this.playerId, seedlingMediaData.playerId) && Intrinsics.areEqual(this.token, seedlingMediaData.token) && Intrinsics.areEqual(this.artist, seedlingMediaData.artist) && Intrinsics.areEqual(this.song, seedlingMediaData.song) && this.duration == seedlingMediaData.duration && this.userId == seedlingMediaData.userId && Intrinsics.areEqual(this.appIconResId, seedlingMediaData.appIconResId) && Intrinsics.areEqual(this.appIconUri, seedlingMediaData.appIconUri) && Intrinsics.areEqual(this.appIcon, seedlingMediaData.appIcon) && Intrinsics.areEqual(this.appLabel, seedlingMediaData.appLabel) && Intrinsics.areEqual(this.artworkUri, seedlingMediaData.artworkUri) && Intrinsics.areEqual(this.artworkBgColor, seedlingMediaData.artworkBgColor) && Intrinsics.areEqual(this.artworkColor, seedlingMediaData.artworkColor) && Intrinsics.areEqual(this.artworkIcon, seedlingMediaData.artworkIcon) && this.artworkUriContentChanged == seedlingMediaData.artworkUriContentChanged && Intrinsics.areEqual(this.custom0IconUri, seedlingMediaData.custom0IconUri) && Intrinsics.areEqual(this.playOrPauseIconUri, seedlingMediaData.playOrPauseIconUri) && Intrinsics.areEqual(this.preIconUri, seedlingMediaData.preIconUri) && Intrinsics.areEqual(this.nextIconUri, seedlingMediaData.nextIconUri) && Intrinsics.areEqual(this.custom1IconUri, seedlingMediaData.custom1IconUri) && this.mediaButtonUriChanged == seedlingMediaData.mediaButtonUriChanged && Intrinsics.areEqual(this.deviceIconUri, seedlingMediaData.deviceIconUri) && this.deviceIconUriChanged == seedlingMediaData.deviceIconUriChanged && Intrinsics.areEqual(this.deviceNameLabel, seedlingMediaData.deviceNameLabel) && this.playbackLocation == seedlingMediaData.playbackLocation && this.state == seedlingMediaData.state && this.oldState == seedlingMediaData.oldState && this.position == seedlingMediaData.position && this.seekAvailable == seedlingMediaData.seekAvailable && this.seekEnable == seedlingMediaData.seekEnable && this.lastPositionUpdateTime == seedlingMediaData.lastPositionUpdateTime && Float.compare(this.playbackSpeed, seedlingMediaData.playbackSpeed) == 0 && this.voicePrintIsShow == seedlingMediaData.voicePrintIsShow && Intrinsics.areEqual(this.action, seedlingMediaData.action) && Intrinsics.areEqual(this.packageName, seedlingMediaData.packageName) && this.createTime == seedlingMediaData.createTime && this.active == seedlingMediaData.active && this.lastActiveTime == seedlingMediaData.lastActiveTime && this.isAutoPlay == seedlingMediaData.isAutoPlay && Intrinsics.areEqual(this.voiceLabel, seedlingMediaData.voiceLabel) && this.interval == seedlingMediaData.interval && this.isPlayStateChanged == seedlingMediaData.isPlayStateChanged && Intrinsics.areEqual(this.albumRotationDirection, seedlingMediaData.albumRotationDirection) && Intrinsics.areEqual(this.mediaButton, seedlingMediaData.mediaButton) && this.isRecommendation == seedlingMediaData.isRecommendation && this.isSpotifyWhichSupportsOplusMediaController == seedlingMediaData.isSpotifyWhichSupportsOplusMediaController && Intrinsics.areEqual(this.recommendations, seedlingMediaData.recommendations) && Intrinsics.areEqual(this.lyricData, seedlingMediaData.lyricData) && Intrinsics.areEqual(this.currentLyric, seedlingMediaData.currentLyric) && Intrinsics.areEqual(this.songInfoInLyric, seedlingMediaData.songInfoInLyric) && this.shouldShowLyric == seedlingMediaData.shouldShowLyric && this.lyricSupport == seedlingMediaData.lyricSupport;
    }

    public final String getAction() {
        return this.action;
    }

    public final boolean getActive() {
        return this.active;
    }

    public final String getAlbumRotationDirection() {
        return this.albumRotationDirection;
    }

    public final Icon getAppIcon() {
        return this.appIcon;
    }

    public final String getAppIconResId() {
        return this.appIconResId;
    }

    public final Uri getAppIconUri() {
        return this.appIconUri;
    }

    public final String getAppLabel() {
        return this.appLabel;
    }

    public final CharSequence getArtist() {
        return this.artist;
    }

    public final String getArtworkBgColor() {
        return this.artworkBgColor;
    }

    public final String getArtworkColor() {
        return this.artworkColor;
    }

    public final Icon getArtworkIcon() {
        return this.artworkIcon;
    }

    public final Uri getArtworkUri() {
        return this.artworkUri;
    }

    public final boolean getArtworkUriContentChanged() {
        return this.artworkUriContentChanged;
    }

    public final long getCreateTime() {
        return this.createTime;
    }

    public final CharSequence getCurrentLyric() {
        return this.currentLyric;
    }

    public final Pair<Uri, Uri> getCustom0IconUri() {
        return this.custom0IconUri;
    }

    public final Pair<Uri, Uri> getCustom1IconUri() {
        return this.custom1IconUri;
    }

    public final Uri getDeviceIconUri() {
        return this.deviceIconUri;
    }

    public final boolean getDeviceIconUriChanged() {
        return this.deviceIconUriChanged;
    }

    public final String getDeviceNameLabel() {
        return this.deviceNameLabel;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final long getLastActiveTime() {
        return this.lastActiveTime;
    }

    public final long getLastPositionUpdateTime() {
        return this.lastPositionUpdateTime;
    }

    public final LyricData getLyricData() {
        return this.lyricData;
    }

    public final boolean getLyricSupport() {
        return this.lyricSupport;
    }

    public final MediaButton getMediaButton() {
        return this.mediaButton;
    }

    public final boolean getMediaButtonUriChanged() {
        return this.mediaButtonUriChanged;
    }

    public final Pair<Uri, Uri> getNextIconUri() {
        return this.nextIconUri;
    }

    public final int getOldState() {
        return this.oldState;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Pair<Uri, Uri> getPlayOrPauseIconUri() {
        return this.playOrPauseIconUri;
    }

    public final int getPlaybackLocation() {
        return this.playbackLocation;
    }

    public final float getPlaybackSpeed() {
        return this.playbackSpeed;
    }

    public final String getPlayerId() {
        return this.playerId;
    }

    public final long getPosition() {
        return this.position;
    }

    public final Pair<Uri, Uri> getPreIconUri() {
        return this.preIconUri;
    }

    public final CopyOnWriteArrayList<MediaRecommendationData> getRecommendations() {
        return this.recommendations;
    }

    public final boolean getSeekAvailable() {
        return this.seekAvailable;
    }

    public final boolean getSeekEnable() {
        return this.seekEnable;
    }

    public final boolean getShouldShowLyric() {
        return this.shouldShowLyric;
    }

    public final CharSequence getSong() {
        return this.song;
    }

    public final CharSequence getSongInfoInLyric() {
        return this.songInfoInLyric;
    }

    public final int getState() {
        return this.state;
    }

    public final MediaSession.Token getToken() {
        return this.token;
    }

    public final int getUserId() {
        return this.userId;
    }

    public final String getVoiceLabel() {
        return this.voiceLabel;
    }

    public final boolean getVoicePrintIsShow() {
        return this.voicePrintIsShow;
    }

    public int hashCode() {
        int iHashCode = this.playerId.hashCode() * 31;
        MediaSession.Token token = this.token;
        int iHashCode2 = (iHashCode + (token == null ? 0 : token.hashCode())) * 31;
        CharSequence charSequence = this.artist;
        int iHashCode3 = (iHashCode2 + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
        CharSequence charSequence2 = this.song;
        int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.userId, ChangeSize$.ExternalSyntheticOutline0.m((iHashCode3 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31, 31, this.duration), 31);
        String str = this.appIconResId;
        int iHashCode4 = (iM + (str == null ? 0 : str.hashCode())) * 31;
        Uri uri = this.appIconUri;
        int iHashCode5 = (iHashCode4 + (uri == null ? 0 : uri.hashCode())) * 31;
        Icon icon = this.appIcon;
        int iHashCode6 = (iHashCode5 + (icon == null ? 0 : icon.hashCode())) * 31;
        String str2 = this.appLabel;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Uri uri2 = this.artworkUri;
        int iM2 = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iHashCode7 + (uri2 == null ? 0 : uri2.hashCode())) * 31, 31, this.artworkBgColor);
        String str3 = this.artworkColor;
        int iHashCode8 = (iM2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Icon icon2 = this.artworkIcon;
        int iM3 = ChangeSize$.ExternalSyntheticOutline0.m((this.custom1IconUri.hashCode() + ((this.nextIconUri.hashCode() + ((this.preIconUri.hashCode() + ((this.playOrPauseIconUri.hashCode() + ((this.custom0IconUri.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m((iHashCode8 + (icon2 == null ? 0 : icon2.hashCode())) * 31, 31, this.artworkUriContentChanged)) * 31)) * 31)) * 31)) * 31)) * 31, 31, this.mediaButtonUriChanged);
        Uri uri3 = this.deviceIconUri;
        int iM4 = ChangeSize$.ExternalSyntheticOutline0.m((iM3 + (uri3 == null ? 0 : uri3.hashCode())) * 31, 31, this.deviceIconUriChanged);
        String str4 = this.deviceNameLabel;
        int iM5 = ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.playbackSpeed, ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.oldState, MonoSpline$.ExternalSyntheticOutline0.m(this.state, MonoSpline$.ExternalSyntheticOutline0.m(this.playbackLocation, (iM4 + (str4 == null ? 0 : str4.hashCode())) * 31, 31), 31), 31), 31, this.position), 31, this.seekAvailable), 31, this.seekEnable), 31, this.lastPositionUpdateTime), 31), 31, this.voicePrintIsShow);
        String str5 = this.action;
        int iM6 = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.interval, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iM5 + (str5 == null ? 0 : str5.hashCode())) * 31, 31, this.packageName), 31, this.createTime), 31, this.active), 31, this.lastActiveTime), 31, this.isAutoPlay), 31, this.voiceLabel), 31), 31, this.isPlayStateChanged), 31, this.albumRotationDirection);
        MediaButton mediaButton = this.mediaButton;
        int iHashCode9 = (this.lyricData.hashCode() + ((this.recommendations.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m((iM6 + (mediaButton == null ? 0 : mediaButton.hashCode())) * 31, 31, this.isRecommendation), 31, this.isSpotifyWhichSupportsOplusMediaController)) * 31)) * 31;
        CharSequence charSequence3 = this.currentLyric;
        int iHashCode10 = (iHashCode9 + (charSequence3 == null ? 0 : charSequence3.hashCode())) * 31;
        CharSequence charSequence4 = this.songInfoInLyric;
        return Boolean.hashCode(this.lyricSupport) + ChangeSize$.ExternalSyntheticOutline0.m((iHashCode10 + (charSequence4 != null ? charSequence4.hashCode() : 0)) * 31, 31, this.shouldShowLyric);
    }

    public final boolean isAutoPlay() {
        return this.isAutoPlay;
    }

    public final boolean isPlayStateChanged() {
        return this.isPlayStateChanged;
    }

    public final boolean isRecommendation() {
        return this.isRecommendation;
    }

    public final boolean isSpotifyWhichSupportsOplusMediaController() {
        return this.isSpotifyWhichSupportsOplusMediaController;
    }

    public final void setAction(String str) {
        this.action = str;
    }

    public final void setActive(boolean z) {
        this.active = z;
    }

    public final void setAlbumRotationDirection(String str) {
        this.albumRotationDirection = str;
    }

    public final void setAppIcon(Icon icon) {
        this.appIcon = icon;
    }

    public final void setAppIconResId(String str) {
        this.appIconResId = str;
    }

    public final void setAppIconUri(Uri uri) {
        this.appIconUri = uri;
    }

    public final void setAppLabel(String str) {
        this.appLabel = str;
    }

    public final void setArtist(CharSequence charSequence) {
        this.artist = charSequence;
    }

    public final void setArtworkBgColor(String str) {
        this.artworkBgColor = str;
    }

    public final void setArtworkColor(String str) {
        this.artworkColor = str;
    }

    public final void setArtworkIcon(Icon icon) {
        this.artworkIcon = icon;
    }

    public final void setArtworkUri(Uri uri) {
        this.artworkUri = uri;
    }

    public final void setArtworkUriContentChanged(boolean z) {
        this.artworkUriContentChanged = z;
    }

    public final void setAutoPlay(boolean z) {
        this.isAutoPlay = z;
    }

    public final void setCurrentLyric(CharSequence charSequence) {
        this.currentLyric = charSequence;
    }

    public final void setCustom0IconUri(Pair<? extends Uri, ? extends Uri> pair) {
        this.custom0IconUri = pair;
    }

    public final void setCustom1IconUri(Pair<? extends Uri, ? extends Uri> pair) {
        this.custom1IconUri = pair;
    }

    public final void setDeviceIconUri(Uri uri) {
        this.deviceIconUri = uri;
    }

    public final void setDeviceIconUriChanged(boolean z) {
        this.deviceIconUriChanged = z;
    }

    public final void setDeviceNameLabel(String str) {
        this.deviceNameLabel = str;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }

    public final void setInterval(int i) {
        this.interval = i;
    }

    public final void setLastActiveTime(long j) {
        this.lastActiveTime = j;
    }

    public final void setLastPositionUpdateTime(long j) {
        this.lastPositionUpdateTime = j;
    }

    public final void setLyricData(LyricData lyricData) {
        this.lyricData = lyricData;
    }

    public final void setLyricSupport(boolean z) {
        this.lyricSupport = z;
    }

    public final void setMediaButton(MediaButton mediaButton) {
        this.mediaButton = mediaButton;
    }

    public final void setMediaButtonUriChanged(boolean z) {
        this.mediaButtonUriChanged = z;
    }

    public final void setNextIconUri(Pair<? extends Uri, ? extends Uri> pair) {
        this.nextIconUri = pair;
    }

    public final void setOldState(int i) {
        this.oldState = i;
    }

    public final void setPackageName(String str) {
        this.packageName = str;
    }

    public final void setPlayOrPauseIconUri(Pair<? extends Uri, ? extends Uri> pair) {
        this.playOrPauseIconUri = pair;
    }

    public final void setPlayStateChanged(boolean z) {
        this.isPlayStateChanged = z;
    }

    public final void setPlaybackLocation(int i) {
        this.playbackLocation = i;
    }

    public final void setPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    public final void setPlayerId(String str) {
        this.playerId = str;
    }

    public final void setPosition(long j) {
        this.position = j;
    }

    public final void setPreIconUri(Pair<? extends Uri, ? extends Uri> pair) {
        this.preIconUri = pair;
    }

    public final void setRecommendation(boolean z) {
        this.isRecommendation = z;
    }

    public final void setRecommendations(CopyOnWriteArrayList<MediaRecommendationData> copyOnWriteArrayList) {
        this.recommendations = copyOnWriteArrayList;
    }

    public final void setSeekAvailable(boolean z) {
        this.seekAvailable = z;
    }

    public final void setSeekEnable(boolean z) {
        this.seekEnable = z;
    }

    public final void setShouldShowLyric(boolean z) {
        this.shouldShowLyric = z;
    }

    public final void setSong(CharSequence charSequence) {
        this.song = charSequence;
    }

    public final void setSongInfoInLyric(CharSequence charSequence) {
        this.songInfoInLyric = charSequence;
    }

    public final void setSpotifyWhichSupportsOplusMediaController(boolean z) {
        this.isSpotifyWhichSupportsOplusMediaController = z;
    }

    public final void setState(int i) {
        this.state = i;
    }

    public final void setToken(MediaSession.Token token) {
        this.token = token;
    }

    public final void setUserId(int i) {
        this.userId = i;
    }

    public final void setVoiceLabel(String str) {
        this.voiceLabel = str;
    }

    public final void setVoicePrintIsShow(boolean z) {
        this.voicePrintIsShow = z;
    }

    public final String toShortString() {
        return this.packageName;
    }

    public String toString() {
        return this.packageName + OplusPreventModeData.SEPARATOR + this.playerId + OplusPreventModeData.SEPARATOR + this.duration + OplusPreventModeData.SEPARATOR + this.appIconUri + OplusPreventModeData.SEPARATOR + this.artworkUri + OplusPreventModeData.SEPARATOR + this.deviceIconUri + OplusPreventModeData.SEPARATOR + this.playbackLocation + OplusPreventModeData.SEPARATOR + this.state + OplusPreventModeData.SEPARATOR + this.position + OplusPreventModeData.SEPARATOR + this.seekAvailable + OplusPreventModeData.SEPARATOR + this.seekEnable + OplusPreventModeData.SEPARATOR + this.lastPositionUpdateTime + OplusPreventModeData.SEPARATOR + this.playbackSpeed + OplusPreventModeData.SEPARATOR + this.voicePrintIsShow + OplusPreventModeData.SEPARATOR + this.createTime + OplusPreventModeData.SEPARATOR + this.active + OplusPreventModeData.SEPARATOR + this.lastActiveTime + OplusPreventModeData.SEPARATOR + this.isAutoPlay + OplusPreventModeData.SEPARATOR + this.mediaButton + OplusPreventModeData.SEPARATOR + this.lyricData.getDisplayPolicy();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isConnectingState(int i) {
            return SeedlingMediaData.CONNECTING_MEDIA_STATES.contains(Integer.valueOf(i));
        }

        public final boolean isInMotion(int i) {
            return i == 3 || i == 4 || i == 5;
        }

        public final boolean isPlayState(int i) {
            return (SeedlingMediaData.PAUSED_MEDIA_STATES.contains(Integer.valueOf(i)) || SeedlingMediaData.CONNECTING_MEDIA_STATES.contains(Integer.valueOf(i))) ? false : true;
        }

        private Companion() {
        }
    }

    public SeedlingMediaData(String str, MediaSession.Token token, CharSequence charSequence, CharSequence charSequence2, long j, int i, String str2, Uri uri, Icon icon, String str3, Uri uri2, String str4, String str5, Icon icon2, boolean z, Pair<? extends Uri, ? extends Uri> pair, Pair<? extends Uri, ? extends Uri> pair2, Pair<? extends Uri, ? extends Uri> pair3, Pair<? extends Uri, ? extends Uri> pair4, Pair<? extends Uri, ? extends Uri> pair5, boolean z2, Uri uri3, boolean z3, String str6, int i2, int i3, int i4, long j2, boolean z4, boolean z5, long j3, float f, boolean z6, String str7, String str8, long j4, boolean z7, long j5, boolean z8, String str9, int i5, boolean z9, String str10, MediaButton mediaButton, boolean z10, boolean z11, CopyOnWriteArrayList<MediaRecommendationData> copyOnWriteArrayList, LyricData lyricData, CharSequence charSequence3, CharSequence charSequence4, boolean z12, boolean z13) {
        this.playerId = str;
        this.token = token;
        this.artist = charSequence;
        this.song = charSequence2;
        this.duration = j;
        this.userId = i;
        this.appIconResId = str2;
        this.appIconUri = uri;
        this.appIcon = icon;
        this.appLabel = str3;
        this.artworkUri = uri2;
        this.artworkBgColor = str4;
        this.artworkColor = str5;
        this.artworkIcon = icon2;
        this.artworkUriContentChanged = z;
        this.custom0IconUri = pair;
        this.playOrPauseIconUri = pair2;
        this.preIconUri = pair3;
        this.nextIconUri = pair4;
        this.custom1IconUri = pair5;
        this.mediaButtonUriChanged = z2;
        this.deviceIconUri = uri3;
        this.deviceIconUriChanged = z3;
        this.deviceNameLabel = str6;
        this.playbackLocation = i2;
        this.state = i3;
        this.oldState = i4;
        this.position = j2;
        this.seekAvailable = z4;
        this.seekEnable = z5;
        this.lastPositionUpdateTime = j3;
        this.playbackSpeed = f;
        this.voicePrintIsShow = z6;
        this.action = str7;
        this.packageName = str8;
        this.createTime = j4;
        this.active = z7;
        this.lastActiveTime = j5;
        this.isAutoPlay = z8;
        this.voiceLabel = str9;
        this.interval = i5;
        this.isPlayStateChanged = z9;
        this.albumRotationDirection = str10;
        this.mediaButton = mediaButton;
        this.isRecommendation = z10;
        this.isSpotifyWhichSupportsOplusMediaController = z11;
        this.recommendations = copyOnWriteArrayList;
        this.lyricData = lyricData;
        this.currentLyric = charSequence3;
        this.songInfoInLyric = charSequence4;
        this.shouldShowLyric = z12;
        this.lyricSupport = z13;
    }

    public SeedlingMediaData() {
        this(null, null, null, null, 0L, 0, null, null, null, null, null, null, null, null, false, null, null, null, null, null, false, null, false, null, 0, 0, 0, 0L, false, false, 0L, 0.0f, false, null, null, 0L, false, 0L, false, null, 0, false, null, null, false, false, null, null, null, null, false, false, -1, 1048575, null);
    }
}
