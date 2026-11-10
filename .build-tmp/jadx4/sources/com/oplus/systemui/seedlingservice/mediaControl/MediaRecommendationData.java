package com.oplus.systemui.seedlingservice.mediaControl;

import android.graphics.Bitmap;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaRecommendationData {
    private final Runnable action;
    private Bitmap artworkBitmap;
    private Uri artworkUri;
    private final MediaBrowser.MediaItem mediaItem;
    private String voiceLabel;

    public /* synthetic */ MediaRecommendationData(MediaBrowser.MediaItem mediaItem, Bitmap bitmap, Uri uri, String str, Runnable runnable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mediaItem, bitmap, uri, (i & 8) != 0 ? "" : str, runnable);
    }

    public static /* synthetic */ MediaRecommendationData copy$default(MediaRecommendationData mediaRecommendationData, MediaBrowser.MediaItem mediaItem, Bitmap bitmap, Uri uri, String str, Runnable runnable, int i, Object obj) {
        if ((i & 1) != 0) {
            mediaItem = mediaRecommendationData.mediaItem;
        }
        if ((i & 2) != 0) {
            bitmap = mediaRecommendationData.artworkBitmap;
        }
        if ((i & 4) != 0) {
            uri = mediaRecommendationData.artworkUri;
        }
        if ((i & 8) != 0) {
            str = mediaRecommendationData.voiceLabel;
        }
        if ((i & 16) != 0) {
            runnable = mediaRecommendationData.action;
        }
        Runnable runnable2 = runnable;
        Uri uri2 = uri;
        return mediaRecommendationData.copy(mediaItem, bitmap, uri2, str, runnable2);
    }

    public final MediaBrowser.MediaItem component1() {
        return this.mediaItem;
    }

    public final Bitmap component2() {
        return this.artworkBitmap;
    }

    public final Uri component3() {
        return this.artworkUri;
    }

    public final String component4() {
        return this.voiceLabel;
    }

    public final Runnable component5() {
        return this.action;
    }

    public final MediaRecommendationData copy(MediaBrowser.MediaItem mediaItem, Bitmap bitmap, Uri uri, String str, Runnable runnable) {
        return new MediaRecommendationData(mediaItem, bitmap, uri, str, runnable);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRecommendationData)) {
            return false;
        }
        MediaRecommendationData mediaRecommendationData = (MediaRecommendationData) obj;
        return Intrinsics.areEqual(this.mediaItem, mediaRecommendationData.mediaItem) && Intrinsics.areEqual(this.artworkBitmap, mediaRecommendationData.artworkBitmap) && Intrinsics.areEqual(this.artworkUri, mediaRecommendationData.artworkUri) && Intrinsics.areEqual(this.voiceLabel, mediaRecommendationData.voiceLabel) && Intrinsics.areEqual(this.action, mediaRecommendationData.action);
    }

    public final Runnable getAction() {
        return this.action;
    }

    public final Bitmap getArtworkBitmap() {
        return this.artworkBitmap;
    }

    public final Uri getArtworkUri() {
        return this.artworkUri;
    }

    public final MediaBrowser.MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public final String getVoiceLabel() {
        return this.voiceLabel;
    }

    public int hashCode() {
        int iHashCode = this.mediaItem.hashCode() * 31;
        Bitmap bitmap = this.artworkBitmap;
        int iHashCode2 = (iHashCode + (bitmap == null ? 0 : bitmap.hashCode())) * 31;
        Uri uri = this.artworkUri;
        return this.action.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iHashCode2 + (uri != null ? uri.hashCode() : 0)) * 31, 31, this.voiceLabel);
    }

    public final void setArtworkBitmap(Bitmap bitmap) {
        this.artworkBitmap = bitmap;
    }

    public final void setArtworkUri(Uri uri) {
        this.artworkUri = uri;
    }

    public final void setVoiceLabel(String str) {
        this.voiceLabel = str;
    }

    public String toString() {
        return "MediaRecommendationData(mediaItem=" + this.mediaItem + ", artworkBitmap=" + this.artworkBitmap + ", artworkUri=" + this.artworkUri + ", voiceLabel=" + this.voiceLabel + ", action=" + this.action + ")";
    }

    public MediaRecommendationData(MediaBrowser.MediaItem mediaItem, Bitmap bitmap, Uri uri, String str, Runnable runnable) {
        this.mediaItem = mediaItem;
        this.artworkBitmap = bitmap;
        this.artworkUri = uri;
        this.voiceLabel = str;
        this.action = runnable;
    }
}
