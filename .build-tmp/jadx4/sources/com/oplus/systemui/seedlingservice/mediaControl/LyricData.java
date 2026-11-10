package com.oplus.systemui.seedlingservice.mediaControl;

import _COROUTINE.CoroutineDebuggingKt$;
import androidx.car.app.SurfaceContainer$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LyricData {
    public static final Companion Companion = new Companion(null);
    public static final int ENTRANCE_TYPE_NOTIFICATION = 16;
    private String artist;
    private final int displayPolicy;
    private final boolean enabledAod;
    private final boolean enabledNotification;
    private int lastIndex;
    private long lastPosition;
    private final List<Pair<Long, String>> lines;
    private final String originalLines;
    private String song;

    public /* synthetic */ LyricData(String str, String str2, List list, String str3, boolean z, boolean z2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2, (i2 & 64) != 0 ? 0 : i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LyricData copy$default(LyricData lyricData, String str, String str2, List list, String str3, boolean z, boolean z2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = lyricData.song;
        }
        if ((i2 & 2) != 0) {
            str2 = lyricData.artist;
        }
        if ((i2 & 4) != 0) {
            list = lyricData.lines;
        }
        if ((i2 & 8) != 0) {
            str3 = lyricData.originalLines;
        }
        if ((i2 & 16) != 0) {
            z = lyricData.enabledAod;
        }
        if ((i2 & 32) != 0) {
            z2 = lyricData.enabledNotification;
        }
        if ((i2 & 64) != 0) {
            i = lyricData.displayPolicy;
        }
        boolean z3 = z2;
        int i3 = i;
        boolean z4 = z;
        List list2 = list;
        return lyricData.copy(str, str2, list2, str3, z4, z3, i3);
    }

    public final String component1() {
        return this.song;
    }

    public final String component2() {
        return this.artist;
    }

    public final List<Pair<Long, String>> component3() {
        return this.lines;
    }

    public final String component4() {
        return this.originalLines;
    }

    public final boolean component5() {
        return this.enabledAod;
    }

    public final boolean component6() {
        return this.enabledNotification;
    }

    public final int component7() {
        return this.displayPolicy;
    }

    public final LyricData copy(String str, String str2, List<Pair<Long, String>> list, String str3, boolean z, boolean z2, int i) {
        return new LyricData(str, str2, list, str3, z, z2, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LyricData)) {
            return false;
        }
        LyricData lyricData = (LyricData) obj;
        return Intrinsics.areEqual(this.song, lyricData.song) && Intrinsics.areEqual(this.artist, lyricData.artist) && Intrinsics.areEqual(this.lines, lyricData.lines) && Intrinsics.areEqual(this.originalLines, lyricData.originalLines) && this.enabledAod == lyricData.enabledAod && this.enabledNotification == lyricData.enabledNotification && this.displayPolicy == lyricData.displayPolicy;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final String getCurrentLyric(long j) {
        String str;
        if (j < this.lastPosition) {
            this.lastPosition = 0L;
            this.lastIndex = 0;
        }
        int size = this.lines.size();
        Pair<Long, String> pair = null;
        for (int i = this.lastIndex; i < size && ((Number) this.lines.get(i).getFirst()).longValue() <= j; i++) {
            pair = this.lines.get(i);
            this.lastIndex = i;
        }
        this.lastPosition = j;
        if (this.lastIndex == 0) {
            return (pair == null || (str = (String) pair.getSecond()) == null) ? getSongInfo() : str;
        }
        if (pair != null) {
            return (String) pair.getSecond();
        }
        return null;
    }

    public final int getDisplayPolicy() {
        return this.displayPolicy;
    }

    public final boolean getEnabledAod() {
        return this.enabledAod;
    }

    public final boolean getEnabledNotification() {
        return this.enabledNotification;
    }

    public final List<Pair<Long, String>> getLines() {
        return this.lines;
    }

    public final String getOriginalLines() {
        return this.originalLines;
    }

    public final String getSong() {
        return this.song;
    }

    public final String getSongInfo() {
        return CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(this.song, " - ", this.artist);
    }

    public int hashCode() {
        String str = this.song;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.artist;
        int iM = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31, this.lines);
        String str3 = this.originalLines;
        return Integer.hashCode(this.displayPolicy) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m((iM + (str3 != null ? str3.hashCode() : 0)) * 31, 31, this.enabledAod), 31, this.enabledNotification);
    }

    public final void setArtist(String str) {
        this.artist = str;
    }

    public final void setSong(String str) {
        this.song = str;
    }

    public String toString() {
        String str = this.song;
        String str2 = this.artist;
        List<Pair<Long, String>> list = this.lines;
        String str3 = this.originalLines;
        boolean z = this.enabledAod;
        boolean z2 = this.enabledNotification;
        int i = this.displayPolicy;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("LyricData(song=", str, ", artist=", str2, ", lines=");
        sbM.append(list);
        sbM.append(", originalLines=");
        sbM.append(str3);
        sbM.append(", enabledAod=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", enabledNotification=", ", displayPolicy=", sbM, z, z2);
        return SurfaceContainer$.ExternalSyntheticOutline0.m(i, ")", sbM);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public LyricData(String str, String str2, List<Pair<Long, String>> list, String str3, boolean z, boolean z2, int i) {
        this.song = str;
        this.artist = str2;
        this.lines = list;
        this.originalLines = str3;
        this.enabledAod = z;
        this.enabledNotification = z2;
        this.displayPolicy = i;
    }

    public LyricData() {
        this(null, null, null, null, false, false, 0, 127, null);
    }
}
