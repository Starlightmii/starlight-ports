package com.oplus.systemui.media.controls.ui;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaActions {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusMediaActions";
    private final MediaAction action0;
    private final MediaAction action1;
    private final MediaAction nextOrCustom;
    private final MediaAction playPauseOrCustom;
    private final MediaAction preOrCustom;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ OplusMediaActions(MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5, int i) {
        this((i & 1) != 0 ? null : mediaAction, (i & 2) != 0 ? null : mediaAction2, (i & 4) != 0 ? null : mediaAction3, (i & 8) != 0 ? null : mediaAction4, (i & 16) != 0 ? null : mediaAction5);
    }

    public static boolean equalNullableContent(MediaAction mediaAction, MediaAction mediaAction2) {
        boolean zSameAs;
        if (mediaAction != null || mediaAction2 != null) {
            if (mediaAction == null || mediaAction2 == null) {
                OplusNotificationLogger.logMediaSeedling(TAG, "equalNullableContent a is " + mediaAction + " b is " + mediaAction2);
                return false;
            }
            if (mediaAction != mediaAction2) {
                if (!Intrinsics.areEqual(mediaAction.getContentDescription(), mediaAction2.getContentDescription())) {
                    OplusNotificationLogger.logMediaSeedling(TAG, "contentDescription this=" + MediaDataExtKt.toSimpleInfo(mediaAction.getContentDescription()) + " other=" + MediaDataExtKt.toSimpleInfo(mediaAction2.getContentDescription()));
                    return false;
                }
                if (mediaAction.getMediaActionEx().getEnabled() != mediaAction2.getMediaActionEx().getEnabled()) {
                    OplusNotificationLogger.logMediaSeedling(TAG, "enabled this=" + mediaAction.getMediaActionEx().getEnabled() + " other=" + mediaAction2.getMediaActionEx().getEnabled());
                    return false;
                }
                if (!Intrinsics.areEqual(mediaAction.getMediaActionEx().getHasHeartFromRating(), mediaAction2.getMediaActionEx().getHasHeartFromRating())) {
                    OplusNotificationLogger.logMediaSeedling(TAG, "hasHeartFromRating this=" + mediaAction.getMediaActionEx().getHasHeartFromRating() + " other=" + mediaAction2.getMediaActionEx().getHasHeartFromRating());
                    return false;
                }
                Icon icon = mediaAction.getMediaActionEx().getIcon();
                Icon icon2 = mediaAction2.getMediaActionEx().getIcon();
                if (icon == null) {
                    Drawable icon3 = mediaAction.getIcon();
                    Drawable icon4 = mediaAction2.getIcon();
                    if (icon3 == icon4) {
                        zSameAs = true;
                    } else {
                        zSameAs = (icon3 == null || icon4 == null || !(icon3 instanceof BitmapDrawable) || !(icon4 instanceof BitmapDrawable)) ? false : ((BitmapDrawable) icon3).getBitmap().sameAs(((BitmapDrawable) icon4).getBitmap());
                    }
                    if (!zSameAs) {
                        OplusNotificationLogger.logMediaSeedling(TAG, "drawableEquals false");
                        return false;
                    }
                } else if (icon != icon2) {
                    if (icon2 == null) {
                        return false;
                    }
                    return icon.sameAs(icon2);
                }
            }
        }
        return true;
    }

    public final MediaAction component1() {
        return this.action0;
    }

    public final MediaAction component2() {
        return this.preOrCustom;
    }

    public final MediaAction component3() {
        return this.playPauseOrCustom;
    }

    public final MediaAction component4() {
        return this.nextOrCustom;
    }

    public final MediaAction component5() {
        return this.action1;
    }

    public final OplusMediaActions copy(MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5) {
        return new OplusMediaActions(mediaAction, mediaAction2, mediaAction3, mediaAction4, mediaAction5);
    }

    public final boolean equalWithContent(OplusMediaActions oplusMediaActions) {
        if (oplusMediaActions != null) {
            return equalNullableContent(this.action0, oplusMediaActions.action0) && equalNullableContent(this.preOrCustom, oplusMediaActions.preOrCustom) && equalNullableContent(this.playPauseOrCustom, oplusMediaActions.playPauseOrCustom) && equalNullableContent(this.nextOrCustom, oplusMediaActions.nextOrCustom) && equalNullableContent(this.action1, oplusMediaActions.action1);
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "equalWithContent other is null");
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusMediaActions)) {
            return false;
        }
        OplusMediaActions oplusMediaActions = (OplusMediaActions) obj;
        return Intrinsics.areEqual(this.action0, oplusMediaActions.action0) && Intrinsics.areEqual(this.preOrCustom, oplusMediaActions.preOrCustom) && Intrinsics.areEqual(this.playPauseOrCustom, oplusMediaActions.playPauseOrCustom) && Intrinsics.areEqual(this.nextOrCustom, oplusMediaActions.nextOrCustom) && Intrinsics.areEqual(this.action1, oplusMediaActions.action1);
    }

    public final MediaAction getAction0() {
        return this.action0;
    }

    public final MediaAction getAction1() {
        return this.action1;
    }

    public final MediaAction getNextOrCustom() {
        return this.nextOrCustom;
    }

    public final List<MediaAction> getNotNullActions() {
        return CollectionsKt__CollectionsKt.listOfNotNull(new MediaAction[]{this.action0, this.preOrCustom, this.playPauseOrCustom, this.nextOrCustom, this.action1});
    }

    public final MediaAction getPlayPauseOrCustom() {
        return this.playPauseOrCustom;
    }

    public final MediaAction getPreOrCustom() {
        return this.preOrCustom;
    }

    public final int hashCode() {
        MediaAction mediaAction = this.action0;
        int iHashCode = (mediaAction == null ? 0 : mediaAction.hashCode()) * 31;
        MediaAction mediaAction2 = this.preOrCustom;
        int iHashCode2 = (iHashCode + (mediaAction2 == null ? 0 : mediaAction2.hashCode())) * 31;
        MediaAction mediaAction3 = this.playPauseOrCustom;
        int iHashCode3 = (iHashCode2 + (mediaAction3 == null ? 0 : mediaAction3.hashCode())) * 31;
        MediaAction mediaAction4 = this.nextOrCustom;
        int iHashCode4 = (iHashCode3 + (mediaAction4 == null ? 0 : mediaAction4.hashCode())) * 31;
        MediaAction mediaAction5 = this.action1;
        return iHashCode4 + (mediaAction5 != null ? mediaAction5.hashCode() : 0);
    }

    public String toString() {
        return "OplusMediaActions(action0=" + this.action0 + ", preOrCustom=" + this.preOrCustom + ", playPauseOrCustom=" + this.playPauseOrCustom + ", nextOrCustom=" + this.nextOrCustom + ", action1=" + this.action1 + ")";
    }

    public OplusMediaActions(MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5) {
        this.action0 = mediaAction;
        this.preOrCustom = mediaAction2;
        this.playPauseOrCustom = mediaAction3;
        this.nextOrCustom = mediaAction4;
        this.action1 = mediaAction5;
    }
}
