package com.oplus.systemui.seedlingservice.mediaControl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaButton {
    public static final String BTN_NAME_CUSTOM0 = "custom0";
    public static final String BTN_NAME_CUSTOM1 = "custom1";
    public static final String BTN_NAME_NEXT_OR_CUSTOM = "nextOrCustom";
    public static final String BTN_NAME_PLAY_OR_PAUSE = "playOrPause";
    public static final String BTN_NAME_PREV_OR_CUSTOM = "prevOrCustom";
    public static final Companion Companion = new Companion(null);
    private final MediaAction custom0;
    private final MediaAction custom1;
    private final MediaAction nextOrCustom;
    private final String playId;
    private final MediaAction playOrPause;
    private final MediaAction prevOrCustom;

    public /* synthetic */ MediaButton(String str, MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : mediaAction, (i & 4) != 0 ? null : mediaAction2, (i & 8) != 0 ? null : mediaAction3, (i & 16) != 0 ? null : mediaAction4, (i & 32) != 0 ? null : mediaAction5);
    }

    public static /* synthetic */ MediaButton copy$default(MediaButton mediaButton, String str, MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaButton.playId;
        }
        if ((i & 2) != 0) {
            mediaAction = mediaButton.playOrPause;
        }
        if ((i & 4) != 0) {
            mediaAction2 = mediaButton.nextOrCustom;
        }
        if ((i & 8) != 0) {
            mediaAction3 = mediaButton.prevOrCustom;
        }
        if ((i & 16) != 0) {
            mediaAction4 = mediaButton.custom0;
        }
        if ((i & 32) != 0) {
            mediaAction5 = mediaButton.custom1;
        }
        MediaAction mediaAction6 = mediaAction4;
        MediaAction mediaAction7 = mediaAction5;
        return mediaButton.copy(str, mediaAction, mediaAction2, mediaAction3, mediaAction6, mediaAction7);
    }

    public final String component1() {
        return this.playId;
    }

    public final MediaAction component2() {
        return this.playOrPause;
    }

    public final MediaAction component3() {
        return this.nextOrCustom;
    }

    public final MediaAction component4() {
        return this.prevOrCustom;
    }

    public final MediaAction component5() {
        return this.custom0;
    }

    public final MediaAction component6() {
        return this.custom1;
    }

    public final MediaButton copy(String str, MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5) {
        return new MediaButton(str, mediaAction, mediaAction2, mediaAction3, mediaAction4, mediaAction5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaButton)) {
            return false;
        }
        MediaButton mediaButton = (MediaButton) obj;
        return Intrinsics.areEqual(this.playId, mediaButton.playId) && Intrinsics.areEqual(this.playOrPause, mediaButton.playOrPause) && Intrinsics.areEqual(this.nextOrCustom, mediaButton.nextOrCustom) && Intrinsics.areEqual(this.prevOrCustom, mediaButton.prevOrCustom) && Intrinsics.areEqual(this.custom0, mediaButton.custom0) && Intrinsics.areEqual(this.custom1, mediaButton.custom1);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final MediaAction getActionByButtonName(String str) {
        switch (str.hashCode()) {
            case -1802491905:
                if (str.equals(BTN_NAME_PLAY_OR_PAUSE)) {
                    return this.playOrPause;
                }
                return null;
            case -738354361:
                if (str.equals(BTN_NAME_NEXT_OR_CUSTOM)) {
                    return this.nextOrCustom;
                }
                return null;
            case 1127932639:
                if (str.equals(BTN_NAME_CUSTOM0)) {
                    return this.custom0;
                }
                return null;
            case 1127932640:
                if (str.equals(BTN_NAME_CUSTOM1)) {
                    return this.custom1;
                }
                return null;
            case 2048094855:
                if (str.equals(BTN_NAME_PREV_OR_CUSTOM)) {
                    return this.prevOrCustom;
                }
                return null;
            default:
                return null;
        }
    }

    public final MediaAction getCustom0() {
        return this.custom0;
    }

    public final MediaAction getCustom1() {
        return this.custom1;
    }

    public final MediaAction getNextOrCustom() {
        return this.nextOrCustom;
    }

    public final String getPlayId() {
        return this.playId;
    }

    public final MediaAction getPlayOrPause() {
        return this.playOrPause;
    }

    public final MediaAction getPrevOrCustom() {
        return this.prevOrCustom;
    }

    public int hashCode() {
        int iHashCode = this.playId.hashCode() * 31;
        MediaAction mediaAction = this.playOrPause;
        int iHashCode2 = (iHashCode + (mediaAction == null ? 0 : mediaAction.hashCode())) * 31;
        MediaAction mediaAction2 = this.nextOrCustom;
        int iHashCode3 = (iHashCode2 + (mediaAction2 == null ? 0 : mediaAction2.hashCode())) * 31;
        MediaAction mediaAction3 = this.prevOrCustom;
        int iHashCode4 = (iHashCode3 + (mediaAction3 == null ? 0 : mediaAction3.hashCode())) * 31;
        MediaAction mediaAction4 = this.custom0;
        int iHashCode5 = (iHashCode4 + (mediaAction4 == null ? 0 : mediaAction4.hashCode())) * 31;
        MediaAction mediaAction5 = this.custom1;
        return iHashCode5 + (mediaAction5 != null ? mediaAction5.hashCode() : 0);
    }

    public String toString() {
        return "MediaButton(playId=" + this.playId + ", playOrPause=" + this.playOrPause + ", nextOrCustom=" + this.nextOrCustom + ", prevOrCustom=" + this.prevOrCustom + ", custom0=" + this.custom0 + ", custom1=" + this.custom1 + ")";
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public MediaButton(String str, MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5) {
        this.playId = str;
        this.playOrPause = mediaAction;
        this.nextOrCustom = mediaAction2;
        this.prevOrCustom = mediaAction3;
        this.custom0 = mediaAction4;
        this.custom1 = mediaAction5;
    }
}
