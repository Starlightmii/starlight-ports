package com.oplus.systemui.seedlingservice.mediaControl;

import android.net.Uri;
import java.util.HashMap;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IconMapsTuple {
    private final HashMap<String, Pair<String, Uri>> custom0;
    private final HashMap<String, Pair<String, Uri>> custom1;
    private final HashMap<String, Pair<String, Uri>> next;
    private final HashMap<String, Pair<String, Uri>> playPause;
    private final HashMap<String, Pair<String, Uri>> pre;

    public IconMapsTuple(HashMap<String, Pair<String, Uri>> map, HashMap<String, Pair<String, Uri>> map2, HashMap<String, Pair<String, Uri>> map3, HashMap<String, Pair<String, Uri>> map4, HashMap<String, Pair<String, Uri>> map5) {
        this.custom0 = map;
        this.custom1 = map2;
        this.playPause = map3;
        this.pre = map4;
        this.next = map5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IconMapsTuple copy$default(IconMapsTuple iconMapsTuple, HashMap map, HashMap map2, HashMap map3, HashMap map4, HashMap map5, int i, Object obj) {
        if ((i & 1) != 0) {
            map = iconMapsTuple.custom0;
        }
        if ((i & 2) != 0) {
            map2 = iconMapsTuple.custom1;
        }
        if ((i & 4) != 0) {
            map3 = iconMapsTuple.playPause;
        }
        if ((i & 8) != 0) {
            map4 = iconMapsTuple.pre;
        }
        if ((i & 16) != 0) {
            map5 = iconMapsTuple.next;
        }
        HashMap map6 = map5;
        HashMap map7 = map3;
        return iconMapsTuple.copy(map, map2, map7, map4, map6);
    }

    public final HashMap<String, Pair<String, Uri>> component1() {
        return this.custom0;
    }

    public final HashMap<String, Pair<String, Uri>> component2() {
        return this.custom1;
    }

    public final HashMap<String, Pair<String, Uri>> component3() {
        return this.playPause;
    }

    public final HashMap<String, Pair<String, Uri>> component4() {
        return this.pre;
    }

    public final HashMap<String, Pair<String, Uri>> component5() {
        return this.next;
    }

    public final IconMapsTuple copy(HashMap<String, Pair<String, Uri>> map, HashMap<String, Pair<String, Uri>> map2, HashMap<String, Pair<String, Uri>> map3, HashMap<String, Pair<String, Uri>> map4, HashMap<String, Pair<String, Uri>> map5) {
        return new IconMapsTuple(map, map2, map3, map4, map5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IconMapsTuple)) {
            return false;
        }
        IconMapsTuple iconMapsTuple = (IconMapsTuple) obj;
        return Intrinsics.areEqual(this.custom0, iconMapsTuple.custom0) && Intrinsics.areEqual(this.custom1, iconMapsTuple.custom1) && Intrinsics.areEqual(this.playPause, iconMapsTuple.playPause) && Intrinsics.areEqual(this.pre, iconMapsTuple.pre) && Intrinsics.areEqual(this.next, iconMapsTuple.next);
    }

    public final HashMap<String, Pair<String, Uri>> getCustom0() {
        return this.custom0;
    }

    public final HashMap<String, Pair<String, Uri>> getCustom1() {
        return this.custom1;
    }

    public final HashMap<String, Pair<String, Uri>> getNext() {
        return this.next;
    }

    public final HashMap<String, Pair<String, Uri>> getPlayPause() {
        return this.playPause;
    }

    public final HashMap<String, Pair<String, Uri>> getPre() {
        return this.pre;
    }

    public int hashCode() {
        return this.next.hashCode() + ((this.pre.hashCode() + ((this.playPause.hashCode() + ((this.custom1.hashCode() + (this.custom0.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "IconMapsTuple(custom0=" + this.custom0 + ", custom1=" + this.custom1 + ", playPause=" + this.playPause + ", pre=" + this.pre + ", next=" + this.next + ")";
    }
}
