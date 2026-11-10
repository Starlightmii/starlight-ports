package com.oplus.systemui.media.controls.pipeline;

import android.media.MediaMetadata;
import android.os.SystemProperties;
import android.util.Log;
import com.android.personalcontext.ace.visualizer.templates.message.MessageTemplateData$;
import com.android.systemui.media.controls.models.player.MediaActionPriority;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaNotificationAction;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaActionPrioritySelectorImpl implements MediaActionPrioritySelector {
    public static final Companion Companion = new Companion();
    private static final String LYRIC_CONFIG_SUPPORTED = "1";
    private static final String TAG = "MediaActionPrioritySelector";
    private static final ActionRule ruleForTest;
    private volatile Map<String, ? extends List<? extends ActionRule>> pkgRule = MapsKt__MapsKt.emptyMap();
    private volatile Map<String, ? extends List<? extends ActionRule>> pkgRuleQs = MapsKt__MapsKt.emptyMap();
    private volatile Map<String, ? extends List<Integer>> pkgActionIndex = MapsKt__MapsKt.emptyMap();
    private volatile Map<String, Integer> pkgLyricEntrance = MapsKt__MapsKt.emptyMap();
    private volatile Map<String, Integer> pkgLyricEnable = MapsKt__MapsKt.emptyMap();
    private volatile Map<String, ? extends List<OplusActionConfig>> pkgOplusActionConfig = MapsKt__MapsKt.emptyMap();
    private volatile List<String> pkgWithRule0 = CollectionsKt__CollectionsKt.emptyList();
    private final List<ActionRule> tempRuleList = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ActionRule {

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Rule0 implements ActionRule {
            public static final Rule0 INSTANCE = new Rule0();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof Rule0);
            }

            public final int hashCode() {
                return 369908000;
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public MediaActionPriority matchMediaActionPriority(String str, List<MediaNotificationAction> list, MediaButton mediaButton) {
                if ((mediaButton != null ? mediaButton.getPlayOrPause() : null) != null) {
                    return MediaActionPriority.PreferSemanticActionsRule0.INSTANCE;
                }
                return null;
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public /* bridge */ /* synthetic */ int size(MediaButton mediaButton) {
                return super.size(mediaButton);
            }

            public String toString() {
                return "Rule0";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Rule1 implements ActionRule {
            public static final Rule1 INSTANCE = new Rule1();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof Rule1);
            }

            public final int hashCode() {
                return 369908001;
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public MediaActionPriority matchMediaActionPriority(String str, List<MediaNotificationAction> list, MediaButton mediaButton) {
                return (mediaButton != null ? mediaButton.getPlayOrPause() : null) != null ? MediaActionPriority.PreferSemanticActions.INSTANCE : MediaActionPriority.PreferNotificationActions.INSTANCE;
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public /* bridge */ /* synthetic */ int size(MediaButton mediaButton) {
                return super.size(mediaButton);
            }

            public String toString() {
                return "Rule1";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Rule2 implements ActionRule {
            public static final Rule2 INSTANCE = new Rule2();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof Rule2);
            }

            public final int hashCode() {
                return 369908002;
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public MediaActionPriority matchMediaActionPriority(String str, List<MediaNotificationAction> list, MediaButton mediaButton) {
                if (list.size() >= 3) {
                    return MediaActionPriority.PreferNotificationActions.INSTANCE;
                }
                return null;
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public /* bridge */ /* synthetic */ int size(MediaButton mediaButton) {
                return super.size(mediaButton);
            }

            public String toString() {
                return "Rule2";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Rule4 implements ActionRule {
            private final List<Integer> notificationActionIndex;

            public Rule4(List<Integer> list) {
                this.notificationActionIndex = list;
            }

            public final List<Integer> component1() {
                return this.notificationActionIndex;
            }

            public final Rule4 copy(List<Integer> list) {
                return new Rule4(list);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Rule4) && Intrinsics.areEqual(this.notificationActionIndex, ((Rule4) obj).notificationActionIndex);
            }

            public final List<Integer> getNotificationActionIndex() {
                return this.notificationActionIndex;
            }

            public final int hashCode() {
                return this.notificationActionIndex.hashCode();
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public MediaActionPriority matchMediaActionPriority(String str, List<MediaNotificationAction> list, MediaButton mediaButton) {
                if (mediaButton != null) {
                    return new MediaActionPriority.PreferSemanticMixNotificationActions(this.notificationActionIndex);
                }
                return null;
            }

            @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl.ActionRule
            public /* bridge */ /* synthetic */ int size(MediaButton mediaButton) {
                return super.size(mediaButton);
            }

            public String toString() {
                return MessageTemplateData$.ExternalSyntheticOutline0.m("Rule4(notificationActionIndex=", this.notificationActionIndex, ")");
            }
        }

        MediaActionPriority matchMediaActionPriority(String str, List<MediaNotificationAction> list, MediaButton mediaButton);

        default int size(MediaButton mediaButton) {
            if (mediaButton == null) {
                return 0;
            }
            int i = mediaButton.getCustom0() != null ? 1 : 0;
            if (mediaButton.getCustom1() != null) {
                i++;
            }
            if (mediaButton.getPrevOrCustom() != null) {
                i++;
            }
            if (mediaButton.getPlayOrPause() != null) {
                i++;
            }
            return mediaButton.getNextOrCustom() != null ? i + 1 : i;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    static {
        ActionRule.Rule3 rule4;
        int i = SystemProperties.getInt("debug.systemui.media_action_rule", -1);
        int i2 = SystemProperties.getInt("debug.systemui.media_action_rule_index_1", 0);
        int i3 = SystemProperties.getInt("debug.systemui.media_action_rule_index_2", 1);
        if (i == 0) {
            rule4 = ActionRule.Rule0.INSTANCE;
        } else if (i == 1) {
            rule4 = ActionRule.Rule1.INSTANCE;
        } else if (i == 2) {
            rule4 = ActionRule.Rule2.INSTANCE;
        } else if (i != 3) {
            rule4 = i != 4 ? null : new ActionRule.Rule4(CollectionsKt__CollectionsKt.listOf(new Integer[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
        } else {
            rule4 = ActionRule.Rule3.INSTANCE;
        }
        ruleForTest = rule4;
    }

    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public List<OplusActionConfig> getActionConfigListforPkg(String str, MediaMetadata mediaMetadata) {
        String string;
        if (mediaMetadata == null || (string = mediaMetadata.getString(OplusActionConfig.METADATA_KEY_OPLUS_ACTION_CONFIG)) == null) {
            string = "";
        }
        List<OplusActionConfig> from = OplusActionConfig.Companion.parseFrom(string);
        List<OplusActionConfig> list = from;
        if (list != null && !list.isEmpty()) {
            return from;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m("oplusActionConfig=", string, "MediaActionPrioritySelector-->", "SystemUi--Media");
        }
        return this.pkgOplusActionConfig.get(str);
    }

    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public int getLyricEnable(String str) {
        Integer num = this.pkgLyricEnable.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public int getLyricEntrance(String str) {
        Integer num = this.pkgLyricEntrance.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public Pair<MediaActionPriority, String> getMediaActionPriority(String str, List<MediaNotificationAction> list, MediaButton mediaButton) {
        return handlePkgRule(str, list, mediaButton, false);
    }

    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public Pair<MediaActionPriority, String> getMediaActionPriorityQs(String str, List<MediaNotificationAction> list, MediaButton mediaButton) {
        return handlePkgRule(str, list, mediaButton, true);
    }

    public final Pair handlePkgRule(String str, List list, MediaButton mediaButton, boolean z) {
        ActionRule actionRule = ruleForTest;
        if (actionRule != null) {
            MediaActionPriority mediaActionPriorityMatchMediaActionPriority = actionRule.matchMediaActionPriority(str, list, mediaButton);
            if (mediaActionPriorityMatchMediaActionPriority == null) {
                LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "Test MediaAction, but not rule, use notification Actions");
                return new Pair(MediaActionPriority.PreferNotificationActions.INSTANCE, "Test MediaAction, but not rule, use notification Actions");
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "MediaActionPrioritySelector-->".concat("Test MediaAction, use testRule=" + actionRule));
            }
            return new Pair(mediaActionPriorityMatchMediaActionPriority, actionRule.toString());
        }
        this.tempRuleList.clear();
        if (z) {
            List<? extends ActionRule> list2 = this.pkgRuleQs.get(str);
            if (list2 != null) {
                this.tempRuleList.addAll(list2);
            }
        } else {
            List<? extends ActionRule> list3 = this.pkgRule.get(str);
            if (list3 != null) {
                this.tempRuleList.addAll(list3);
            }
        }
        this.tempRuleList.add(ActionRule.Rule1.INSTANCE);
        for (ActionRule actionRule2 : this.tempRuleList) {
            MediaActionPriority mediaActionPriorityMatchMediaActionPriority2 = actionRule2.matchMediaActionPriority(str, list, mediaButton);
            if (mediaActionPriorityMatchMediaActionPriority2 != null) {
                return new Pair(mediaActionPriorityMatchMediaActionPriority2, actionRule2.toString());
            }
        }
        return new Pair(MediaActionPriority.PreferNotificationActions.INSTANCE, "not rule, use Actions");
    }

    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public boolean resolveLyricSupport(String str, MediaMetadata mediaMetadata) {
        if (getLyricEnable(str) != 1) {
            return true;
        }
        return Intrinsics.areEqual(mediaMetadata != null ? mediaMetadata.getString("oplus.media.config") : null, "1");
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0021 A[RETURN] */
    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public boolean supportOplusActionConfig(String str) {
        for (Object obj : this.pkgWithRule0) {
            if (Intrinsics.areEqual((String) obj, str)) {
                if (obj != null) {
                    return true;
                }
                return false;
            }
        }
        obj = null;
        if (obj != null) {
            return true;
        }
        return false;
    }

    @Override // com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector
    public void updatePkgActionsRule(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, Map<String, String> map5, Map<String, String> map6) {
        List<Integer> list;
        ActionRule.Rule3 rule4;
        List<Integer> list2;
        ActionRule.Rule3 rule5;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map3.size()));
        Iterator<T> it = map3.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) entry.getValue(), new String[]{OplusPreventModeData.SEPARATOR}, 0, 6);
            ArrayList arrayList = new ArrayList();
            Iterator it2 = listSplit$default.iterator();
            while (it2.hasNext()) {
                Integer intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull((String) it2.next());
                if (intOrNull != null) {
                    arrayList.add(intOrNull);
                }
            }
            linkedHashMap.put(key, arrayList);
        }
        this.pkgActionIndex = linkedHashMap;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map.size()));
        Iterator<T> it3 = map.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it3.next();
            Object key2 = entry2.getKey();
            List listSplit$default2 = StringsKt__StringsKt.split$default((CharSequence) entry2.getValue(), new String[]{OplusPreventModeData.SEPARATOR}, 0, 6);
            ArrayList arrayList2 = new ArrayList();
            Iterator it4 = listSplit$default2.iterator();
            while (it4.hasNext()) {
                Integer intOrNull2 = StringsKt__StringNumberConversionsKt.toIntOrNull((String) it4.next());
                if (intOrNull2 != null && intOrNull2.intValue() == 0) {
                    rule5 = ActionRule.Rule0.INSTANCE;
                } else if (intOrNull2 != null && intOrNull2.intValue() == 1) {
                    rule5 = ActionRule.Rule1.INSTANCE;
                } else if (intOrNull2 != null && intOrNull2.intValue() == 2) {
                    rule5 = ActionRule.Rule2.INSTANCE;
                } else if (intOrNull2 != null && intOrNull2.intValue() == 3) {
                    rule5 = ActionRule.Rule3.INSTANCE;
                } else {
                    rule5 = (intOrNull2 == null || intOrNull2.intValue() != 4 || (list2 = this.pkgActionIndex.get(entry2.getKey())) == null) ? null : new ActionRule.Rule4(list2);
                }
                if (rule5 != null) {
                    arrayList2.add(rule5);
                }
            }
            linkedHashMap2.put(key2, arrayList2);
        }
        this.pkgRule = linkedHashMap2;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map2.size()));
        Iterator<T> it5 = map2.entrySet().iterator();
        while (it5.hasNext()) {
            Map.Entry entry3 = (Map.Entry) it5.next();
            Object key3 = entry3.getKey();
            List listSplit$default3 = StringsKt__StringsKt.split$default((CharSequence) entry3.getValue(), new String[]{OplusPreventModeData.SEPARATOR}, 0, 6);
            ArrayList arrayList3 = new ArrayList();
            Iterator it6 = listSplit$default3.iterator();
            while (it6.hasNext()) {
                Integer intOrNull3 = StringsKt__StringNumberConversionsKt.toIntOrNull((String) it6.next());
                if (intOrNull3 != null && intOrNull3.intValue() == 0) {
                    rule4 = ActionRule.Rule0.INSTANCE;
                } else if (intOrNull3 != null && intOrNull3.intValue() == 1) {
                    rule4 = ActionRule.Rule1.INSTANCE;
                } else if (intOrNull3 != null && intOrNull3.intValue() == 2) {
                    rule4 = ActionRule.Rule2.INSTANCE;
                } else if (intOrNull3 != null && intOrNull3.intValue() == 3) {
                    rule4 = ActionRule.Rule3.INSTANCE;
                } else {
                    rule4 = (intOrNull3 == null || intOrNull3.intValue() != 4 || (list = this.pkgActionIndex.get(entry3.getKey())) == null) ? null : new ActionRule.Rule4(list);
                }
                if (rule4 != null) {
                    arrayList3.add(rule4);
                }
            }
            linkedHashMap3.put(key3, arrayList3);
        }
        this.pkgRuleQs = linkedHashMap3;
        LinkedHashMap linkedHashMap4 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map4.size()));
        Iterator<T> it7 = map4.entrySet().iterator();
        while (it7.hasNext()) {
            Map.Entry entry4 = (Map.Entry) it7.next();
            Object key4 = entry4.getKey();
            Integer intOrNull4 = StringsKt__StringNumberConversionsKt.toIntOrNull((String) entry4.getValue());
            linkedHashMap4.put(key4, Integer.valueOf(intOrNull4 != null ? intOrNull4.intValue() : 0));
        }
        this.pkgLyricEntrance = linkedHashMap4;
        LinkedHashMap linkedHashMap5 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map6.size()));
        Iterator<T> it8 = map6.entrySet().iterator();
        while (it8.hasNext()) {
            Map.Entry entry5 = (Map.Entry) it8.next();
            Object key5 = entry5.getKey();
            Integer intOrNull5 = StringsKt__StringNumberConversionsKt.toIntOrNull((String) entry5.getValue());
            linkedHashMap5.put(key5, Integer.valueOf(intOrNull5 != null ? intOrNull5.intValue() : 0));
        }
        this.pkgLyricEnable = linkedHashMap5;
        LinkedHashMap linkedHashMap6 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map5.size()));
        Iterator<T> it9 = map5.entrySet().iterator();
        while (it9.hasNext()) {
            Map.Entry entry6 = (Map.Entry) it9.next();
            linkedHashMap6.put(entry6.getKey(), OplusActionConfig.Companion.parseFrom((String) entry6.getValue()));
        }
        this.pkgOplusActionConfig = linkedHashMap6;
        Map<String, ? extends List<? extends ActionRule>> map7 = this.pkgRule;
        LinkedHashMap linkedHashMap7 = new LinkedHashMap();
        for (Map.Entry<String, ? extends List<? extends ActionRule>> entry7 : map7.entrySet()) {
            if (entry7.getValue().contains(ActionRule.Rule0.INSTANCE)) {
                linkedHashMap7.put(entry7.getKey(), entry7.getValue());
            }
        }
        this.pkgWithRule0 = CollectionsKt___CollectionsKt.toList(linkedHashMap7.keySet());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "MediaActionPrioritySelector-->".concat("updatePkgRuleAndIndex. pkgRule=" + this.pkgRule + ".pkgRuleQs=" + this.pkgRuleQs + ". pkgActionIndex=" + this.pkgActionIndex + "pkgLyricEntrance=" + this.pkgLyricEntrance + ". pkgLyricEnable=" + this.pkgLyricEnable + ".pkgOplusActionConfig=" + this.pkgOplusActionConfig));
        }
    }
}
