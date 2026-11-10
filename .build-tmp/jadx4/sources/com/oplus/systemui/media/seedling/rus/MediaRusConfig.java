package com.oplus.systemui.media.seedling.rus;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaRusConfig {
    public static final Companion Companion = new Companion();
    private static final MediaRusConfig EMPTY = new MediaRusConfig(0, CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap());
    private final Map<String, String> actionConfigMap;
    private final Map<String, String> actionIndexMap;
    private final List<String> blackList;
    private final Map<String, String> lyricEnableMap;
    private final Map<String, String> lyricEntranceMap;
    private final Map<String, String> pkgRuleMap;
    private final Map<String, String> pkgRuleQsMap;
    public final int version;
    private final List<String> whiteList;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MediaRusConfig getEMPTY() {
            return MediaRusConfig.EMPTY;
        }
    }

    public MediaRusConfig(int i, List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, Map<String, String> map5, Map<String, String> map6) {
        this.version = i;
        this.whiteList = list;
        this.blackList = list2;
        this.pkgRuleMap = map;
        this.pkgRuleQsMap = map2;
        this.actionIndexMap = map3;
        this.lyricEntranceMap = map4;
        this.lyricEnableMap = map5;
        this.actionConfigMap = map6;
    }

    public final List<String> component2() {
        return this.whiteList;
    }

    public final List<String> component3() {
        return this.blackList;
    }

    public final Map<String, String> component4() {
        return this.pkgRuleMap;
    }

    public final Map<String, String> component5() {
        return this.pkgRuleQsMap;
    }

    public final Map<String, String> component6() {
        return this.actionIndexMap;
    }

    public final Map<String, String> component7() {
        return this.lyricEntranceMap;
    }

    public final Map<String, String> component8() {
        return this.lyricEnableMap;
    }

    public final Map<String, String> component9() {
        return this.actionConfigMap;
    }

    public final MediaRusConfig copy(int i, List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, Map<String, String> map5, Map<String, String> map6) {
        return new MediaRusConfig(i, list, list2, map, map2, map3, map4, map5, map6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRusConfig)) {
            return false;
        }
        MediaRusConfig mediaRusConfig = (MediaRusConfig) obj;
        return this.version == mediaRusConfig.version && Intrinsics.areEqual(this.whiteList, mediaRusConfig.whiteList) && Intrinsics.areEqual(this.blackList, mediaRusConfig.blackList) && Intrinsics.areEqual(this.pkgRuleMap, mediaRusConfig.pkgRuleMap) && Intrinsics.areEqual(this.pkgRuleQsMap, mediaRusConfig.pkgRuleQsMap) && Intrinsics.areEqual(this.actionIndexMap, mediaRusConfig.actionIndexMap) && Intrinsics.areEqual(this.lyricEntranceMap, mediaRusConfig.lyricEntranceMap) && Intrinsics.areEqual(this.lyricEnableMap, mediaRusConfig.lyricEnableMap) && Intrinsics.areEqual(this.actionConfigMap, mediaRusConfig.actionConfigMap);
    }

    public final Map<String, String> getActionConfigMap() {
        return this.actionConfigMap;
    }

    public final Map<String, String> getActionIndexMap() {
        return this.actionIndexMap;
    }

    public final List<String> getBlackList() {
        return this.blackList;
    }

    public final Map<String, String> getLyricEnableMap() {
        return this.lyricEnableMap;
    }

    public final Map<String, String> getLyricEntranceMap() {
        return this.lyricEntranceMap;
    }

    public final Map<String, String> getPkgRuleMap() {
        return this.pkgRuleMap;
    }

    public final Map<String, String> getPkgRuleQsMap() {
        return this.pkgRuleQsMap;
    }

    public final int getVersion() {
        return this.version;
    }

    public final List<String> getWhiteList() {
        return this.whiteList;
    }

    public final int hashCode() {
        return this.actionConfigMap.hashCode() + ((this.lyricEnableMap.hashCode() + ((this.lyricEntranceMap.hashCode() + ((this.actionIndexMap.hashCode() + ((this.pkgRuleQsMap.hashCode() + ((this.pkgRuleMap.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.version) * 31, 31, this.whiteList), 31, this.blackList)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "MediaRusConfig(version=" + this.version + ", whiteList=" + this.whiteList + ", blackList=" + this.blackList + ", pkgRuleMap=" + this.pkgRuleMap + ", pkgRuleQsMap=" + this.pkgRuleQsMap + ", actionIndexMap=" + this.actionIndexMap + ", lyricEntranceMap=" + this.lyricEntranceMap + ", lyricEnableMap=" + this.lyricEnableMap + ", actionConfigMap=" + this.actionConfigMap + ")";
    }
}
