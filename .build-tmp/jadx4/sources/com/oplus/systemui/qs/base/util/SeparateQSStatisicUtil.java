package com.oplus.systemui.qs.base.util;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.MotionEvent;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.compatui.OplusCompatDcsUtils$;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil$$ExternalSyntheticOutline0;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.lang.invoke.VarHandle;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSStatisicUtil {
    private static final String AREA_AFTER = "area_after";
    private static final String AREA_BEFORE = "area_before";
    public static final String CLICK_QS_PANEL_EVENT_CLICK = "click";
    private static final String CLICK_QS_PANEL_EVENT_ID = "click_qs_panel";
    public static final String CLICK_QS_PANEL_EVENT_LONG_CLICK = "longClick";
    public static final String CLICK_QS_PANEL_EVENT_MOVE = "move";
    public static final String CLICK_QS_PANEL_TYPE_BRIGHTNESS = "brightness";
    public static final String CLICK_QS_PANEL_TYPE_VOLUME = "volume";
    private static final String DEFAULT_KEY = "-1";
    private static final String EDIT_TITLE = "edit_title";
    private static final String EDIT_TITLE_TYPE = "edit_title_type";
    private static final String EVENT_EXPAND_NOTI_FROM_QS_SCROLL_LEFTTORIGHT = "expand_noti_form_qs_scroll_leftoright";
    private static final String EVENT_EXPAND_NOTI_FROM_QS_SCROLL_TOPTOBOTTOM_ON_LEFT = "expand_noti_form_qs_scroll_toptobottom";
    private static final String EVENT_EXPAND_NOTI_ON_DESKTOP = "expand_noti_on_desktop";
    private static final String EVENT_EXPAND_NOTI_ON_KEYGUARD = "expand_noti_on_keyguard";
    private static final String EVENT_EXPAND_NOTI_ON_STATUSBAR = "expand_noti_on_statusbar";
    private static final String EVENT_EXPAND_QS_FROM_NOTI_SCROLL_RIGHTTOLEFT = "expand_qs_form_noti_scroll_righttoleft";
    private static final String EVENT_EXPAND_QS_FROM_NOTI_SCROLL_TOPTOBOTTOM_ON_RIGHT = "expand_qs_form_noti_scroll_toptobottom";
    private static final String EVENT_EXPAND_QS_ON_DESKTOP = "expand_qs_on_desktop";
    private static final String EVENT_EXPAND_QS_ON_KEYGUARD = "expand_qs_on_keyguard_status";
    private static final String EVENT_EXPAND_QS_ON_STATUSBAR = "expand_qs_on_statusbar";
    private static final String EVENT_LEFT_TO_RIGHT_PANEL_SWITCH_COUNT = "switch_count";
    private static final String EVENT_QS_TILES_COUNT = "state_count";
    private static final String EVENT_QS_TILES_DOTS_COUNT = "dots_count";
    private static final String EVENT_QS_TILES_SPEC = "spec";
    private static final String HORIZONTAL = "1";
    private static final String KEYGUARD = "keyguard";
    private static final String KEY_EVENT = "event";
    private static final String KEY_FROM = "from";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_OP = "op";
    private static final String KEY_SEPARATE_QS_ENABLED = "separate_qs_enabled";
    private static final String KEY_SWITCH_QUICKLY = "switchquickly";
    private static final String KEY_TO = "to";
    private static final String KEY_TYPE = "type";
    private static final String LOCK_SCREEN = "1";
    private static final String MODE = "mode";
    public static final String OPTYPE_SCROLL_LEFTTORIGHT = "lefttoright";
    public static final String OPTYPE_SCROLL_RIGHTTOLEFT = "righttoleft";
    public static final String OPTYPE_SCROLL_TOPTOBOTTOM = "toptobottom";
    private static final String ORITATION = "oritation";
    private static final String PERSONALIZED_EDIT_STATISTICS_TYPE = "personalized_edit_statistics_type";
    private static final String QSMODE = "0";
    private static final String QS_EDIT_AFTER = "after";
    private static final String QS_EDIT_BEFORE = "before";
    private static final String QS_TILE_EDIT_CHANGE_EVENT_ID = "qs_tile_edit_change";
    private static final String QS_TOP_CAPSULE_CLICK = "qs_top_capsule_click";
    public static final String SCENARIO_ENTRY_MODE_CLICK = "w1";
    public static final String SCENARIO_ENTRY_MODE_TEMP_ENTER = "w2";
    private static final String SCENE_CLICK = "scene_click";
    private static final String SCENE_NAME = "scene_name";
    private static final String SEPARATEQSMODE = "1";
    private static final String SHORTCUTS_EVENT_ID = "shortcuts_center_new";
    private static final String SHORTCUTS_KEY_ACTIVATE = "shortcuts_activate";
    private static final String SHORTCUTS_KEY_STATUS = "shortcuts_switch_status";
    private static final String SIZE_AFTER = "size_after";
    private static final String SIZE_BEFORE = "size_before";
    private static final String TAG = "SeparateQSStatisicUtil";
    private static final String TAG_ID = "20082";
    private static final String TYPE = "type";
    private static final String UNLOCK_SCREEN = "0";
    public static final String VALUE_BRIGHTNESS = "brightness";
    public static final String VALUE_BRIGHTNESS_CLICK = "click";
    public static final String VALUE_BRIGHTNESS_MOVE = "move";
    public static final String VALUE_KEYGUARD = "keyguard";
    public static final String VALUE_PANEL_NOTIFY = "notify";
    public static final String VALUE_PANEL_QS = "qs";
    public static final String VALUE_PULL_DOWN_SHADE = "pull_down_shade";
    public static final String VALUE_SWITCH_PANEL_LEFTTORIGHT = "switch_panel_scroll_lefttoright";
    public static final String VALUE_SWITCH_PANEL_RIGHTTOLEFT = "switch_panel_scroll_righttoleft";
    public static final String VALUE_SWITCH_PANEL_TOPTOBOTTOM = "switch_panel_scroll_toptobottom";
    public static final String VALUE_SWITCH_QUICKLY = "true";
    private static final String VERTICAL = "2";
    private static Context context;
    public static boolean isSceneTileClick;
    public static boolean qsEnabled;
    public static final SeparateQSStatisicUtil INSTANCE = new SeparateQSStatisicUtil();
    private static SeparateEditTitleData separateEditTitleData = new SeparateEditTitleData("", "", "", "", "");
    private static ScenarioStatUtil.ScenarioStat scenarioStat = new ScenarioStatUtil.ScenarioStat();
    private static final char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static LruCache<String, String> sPackageNameEncryptionMap = new LruCache<>(ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION);
    private static SeparateDragTileData separateDragTileData = new SeparateDragTileData("", "", "", false, false);
    private static SeparateTileInfoData separateTileInfoData = new SeparateTileInfoData("", 0, "");
    private static final AtomicInteger leftToRightSwitchCount = new AtomicInteger(0);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class EXPANDED {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ EXPANDED[] $VALUES;
        public static final EXPANDED NONE;
        public static final EXPANDED NOTIFICATION;
        public static final EXPANDED QS;

        static {
            EXPANDED expanded = new EXPANDED("NONE", 0);
            NONE = expanded;
            EXPANDED expanded2 = new EXPANDED("NOTIFICATION", 1);
            NOTIFICATION = expanded2;
            EXPANDED expanded3 = new EXPANDED(OplusFakeViewProvider.QS, 2);
            QS = expanded3;
            EXPANDED[] expandedArr = {expanded, expanded2, expanded3};
            $VALUES = expandedArr;
            $ENTRIES = EnumEntriesKt.enumEntries(expandedArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static EXPANDED valueOf(String str) {
            return (EXPANDED) Enum.valueOf(EXPANDED.class, str);
        }

        public static EXPANDED[] values() {
            return (EXPANDED[]) $VALUES.clone();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil$trackEventAsync$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Context $context;
        public /* synthetic */ String $eventID;
        public /* synthetic */ Map $logMap;

        @Override // java.lang.Runnable
        public final void run() {
            String str = this.$eventID;
            Map map = this.$logMap;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SeparateQSStatisicUtil-->".concat("eventID:" + str + " logMap:" + map));
            }
            OplusTrackProxyUtil.onCommon(this.$context, SeparateQSStatisicUtil.TAG_ID, this.$eventID, this.$logMap);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil$updateScenarioType$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02541 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object $scenarioType;

        public /* synthetic */ RunnableC02541(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    SeparateQSStatisicUtil.INSTANCE.getScenarioStat().setScenarioType((String) this.$scenarioType);
                    break;
                case 1:
                    SeparateQSStatisicUtil.INSTANCE.getScenarioStat().setEntryMode((String) this.$scenarioType);
                    break;
                default:
                    String statsString = ScenarioStatUtil.INSTANCE.getStatsString();
                    if (!TextUtils.isEmpty(statsString)) {
                        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m(SeparateQSStatisicUtil.SCENE_CLICK, statsString);
                        OplusTrackProxyUtil.onCommon((Context) this.$scenarioType, SeparateQSStatisicUtil.TAG_ID, SeparateQSStatisicUtil.QS_TOP_CAPSULE_CLICK, mapM);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "SeparateQSStatisicUtil-->".concat("collectQsTopCapsuleClickTypeEvent eventMap:" + mapM));
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public static final String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static final void collectEditTitleTypeEvent(Context context2, String str, boolean z) {
        if (context2 == null) {
            context2 = context;
        }
        if (context2 != null) {
            HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m(EDIT_TITLE, str);
            if (context2.getResources().getConfiguration().orientation == 2) {
                mapM.put(ORITATION, "horizontal");
            } else {
                mapM.put(ORITATION, "vertical");
            }
            if (z) {
                mapM.put("keyguard", "lock_screen");
            } else {
                mapM.put("keyguard", "unlock");
            }
            mapM.put(MODE, "1");
            mapM.put("type", separateEditTitleData.getType());
            mapM.put(AREA_BEFORE, separateEditTitleData.getAreaBefore());
            mapM.put(AREA_AFTER, separateEditTitleData.getAreaAfter());
            mapM.put(SIZE_BEFORE, separateEditTitleData.getSizeBefore());
            mapM.put(SIZE_AFTER, separateEditTitleData.getSizeAfter());
            OplusTrackProxyUtil.onCommon(context2, TAG_ID, EDIT_TITLE_TYPE, mapM);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SeparateQSStatisicUtil-->".concat("collectEditTitleTypeEvent target " + str + " eventMap:" + mapM));
            }
            separateEditTitleData.resetData();
        }
    }

    public static final void collectQsPanelActionClick(Context context2, String str, String str2) {
        HashMap mapM = OplusCompatDcsUtils$.ExternalSyntheticOutline0.m("type", str, KEY_EVENT, str2);
        mapM.put(KEY_SEPARATE_QS_ENABLED, String.valueOf(qsEnabled));
        if (context2.getResources().getConfiguration().orientation == 2) {
            mapM.put(ORITATION, "1");
        } else {
            mapM.put(ORITATION, "2");
        }
        if (((KeyguardManager) context2.getSystemService(KeyguardManager.class)).isKeyguardLocked()) {
            mapM.put("keyguard", "1");
        } else {
            mapM.put("keyguard", "0");
        }
        trackEventAsync(context2, CLICK_QS_PANEL_EVENT_ID, mapM);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("collectQsPanelActionClick  type: ", str, ", event: ", str2, "SeparateQSStatisicUtil-->"));
        }
    }

    public static final void collectQsTileEditChangeEvent(Context context2, String str, String str2) {
        HashMap mapM = OplusCompatDcsUtils$.ExternalSyntheticOutline0.m(QS_EDIT_BEFORE, str, QS_EDIT_AFTER, str2);
        if (context2.getResources().getConfiguration().orientation == 2) {
            mapM.put(ORITATION, "horizontal");
        } else {
            mapM.put(ORITATION, "vertical");
        }
        mapM.put("keyguard", "unlock");
        OplusTrackProxyUtil.onCommon(context2, TAG_ID, QS_TILE_EDIT_CHANGE_EVENT_ID, mapM);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("collectQsTileEditChangeEvent before:", str, " after:", str2, "SeparateQSStatisicUtil-->"));
        }
    }

    public static final void collectQsTopCapsuleClickTypeEvent(Context context2) {
        OplusUiOffloadThread companion = OplusUiOffloadThread.Companion.getInstance();
        RunnableC02541 runnableC02541 = new RunnableC02541(2);
        runnableC02541.$scenarioType = context2;
        VarHandle.storeStoreFence();
        companion.runOnSubThread(runnableC02541);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0147  */
    /* JADX WARN: Code duplicated, block: B:50:0x014f  */
    /* JADX WARN: Code duplicated, block: B:52:0x015a  */
    /* JADX WARN: Code duplicated, block: B:53:0x0163  */
    /* JADX WARN: Code duplicated, block: B:55:0x016f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0175  */
    /* JADX WARN: Code duplicated, block: B:62:0x0191  */
    /* JADX WARN: Code duplicated, block: B:66:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:68:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:70:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:74:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:77:0x01cd  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static final void collectSeparateQsAndNotiExpandEvent(Context context2, SeparateStaticInfo separateStaticInfo) {
        HashMap map;
        String str;
        String str2;
        HashMap map2;
        ?? r0;
        EXPANDED from;
        String str3;
        HashMap map3;
        String str4;
        MotionEvent downEvent;
        HashMap map4;
        Object obj;
        String str5;
        String str6;
        String str7;
        EXPANDED to = separateStaticInfo.getTo();
        EXPANDED expanded = EXPANDED.QS;
        Object objM = null;
        if (to == expanded && separateStaticInfo.getFrom() == EXPANDED.NONE) {
            map = new HashMap();
            if (separateStaticInfo.getKeyGuardShow()) {
                map.put("type", VALUE_PANEL_QS);
                map.put(SCENE_NAME, scenarioStat.getScenarioType());
                map.put(KEY_FROM, "keyguard");
                str7 = EVENT_EXPAND_QS_ON_KEYGUARD;
            } else {
                String str8 = separateStaticInfo.getDownOnHotArea() ? EVENT_EXPAND_QS_ON_STATUSBAR : EVENT_EXPAND_QS_ON_DESKTOP;
                map.put("type", VALUE_PANEL_QS);
                String str9 = str8;
                map.put(SCENE_NAME, scenarioStat.getScenarioType());
                MotionEvent downEvent2 = separateStaticInfo.getDownEvent();
                map.put(KEY_LOCATION, String.valueOf(downEvent2 != null ? ListPopupWindow$.ExternalSyntheticOutline0.m("(", downEvent2.getX(), OplusPreventModeData.SEPARATOR, downEvent2.getY(), ")") : null));
                str7 = str9;
            }
            map.put(KEY_EVENT, VALUE_PULL_DOWN_SHADE);
            map.put(KEY_OP, str7);
        } else {
            map = null;
        }
        HashMap map5 = map;
        if (separateStaticInfo.getTo() == expanded) {
            EXPANDED from2 = separateStaticInfo.getFrom();
            str = OplusPreventModeData.SEPARATOR;
            if (from2 == EXPANDED.NOTIFICATION) {
                HashMap map6 = new HashMap();
                if (separateStaticInfo.getKeyGuardShow()) {
                    map6.put("type", VALUE_PANEL_QS);
                    map6.put(SCENE_NAME, scenarioStat.getScenarioType());
                    map6.put(KEY_FROM, "keyguard");
                    str5 = EVENT_EXPAND_QS_ON_KEYGUARD;
                    str2 = "(";
                    str6 = VALUE_PULL_DOWN_SHADE;
                } else {
                    str2 = "(";
                    if (Intrinsics.areEqual(separateStaticInfo.getOpType(), OPTYPE_SCROLL_RIGHTTOLEFT)) {
                        map6.put(KEY_FROM, VALUE_PANEL_NOTIFY);
                        map6.put(KEY_TO, VALUE_PANEL_QS);
                        map6.put(SCENE_NAME, scenarioStat.getScenarioType());
                        if (isSwitchLessThanThreeseconds(separateStaticInfo)) {
                            map6.put(KEY_SWITCH_QUICKLY, "true");
                        }
                        str5 = EVENT_EXPAND_QS_FROM_NOTI_SCROLL_RIGHTTOLEFT;
                        str6 = VALUE_SWITCH_PANEL_RIGHTTOLEFT;
                    } else if (Intrinsics.areEqual(separateStaticInfo.getOpType(), OPTYPE_SCROLL_TOPTOBOTTOM) && separateStaticInfo.getDownOnHotArea()) {
                        map6.put(KEY_FROM, VALUE_PANEL_NOTIFY);
                        map6.put(KEY_TO, VALUE_PANEL_QS);
                        if (isSwitchLessThanThreeseconds(separateStaticInfo)) {
                            map6.put(KEY_SWITCH_QUICKLY, "true");
                        }
                        str5 = EVENT_EXPAND_QS_FROM_NOTI_SCROLL_TOPTOBOTTOM_ON_RIGHT;
                        str6 = VALUE_SWITCH_PANEL_TOPTOBOTTOM;
                    } else {
                        map2 = null;
                    }
                }
                map6.put(KEY_EVENT, str6);
                map6.put(KEY_OP, str5);
                map2 = map6;
            }
            r0 = map2;
            if (separateStaticInfo.getTo() == EXPANDED.NOTIFICATION) {
                from = separateStaticInfo.getFrom();
                str3 = EVENT_EXPAND_NOTI_ON_KEYGUARD;
                if (from == expanded) {
                    map4 = new HashMap();
                    if (separateStaticInfo.getKeyGuardShow()) {
                        map4.put("type", VALUE_PANEL_NOTIFY);
                        map4.put(KEY_FROM, "keyguard");
                        obj = VALUE_PULL_DOWN_SHADE;
                    } else {
                        if (Intrinsics.areEqual(separateStaticInfo.getOpType(), OPTYPE_SCROLL_LEFTTORIGHT)) {
                            leftToRightSwitchCount.incrementAndGet();
                        } else if (Intrinsics.areEqual(separateStaticInfo.getOpType(), OPTYPE_SCROLL_TOPTOBOTTOM) && separateStaticInfo.getDownOnHotArea()) {
                            map4.put(KEY_FROM, VALUE_PANEL_QS);
                            map4.put(KEY_TO, VALUE_PANEL_NOTIFY);
                            if (isSwitchLessThanThreeseconds(separateStaticInfo)) {
                                map4.put(KEY_SWITCH_QUICKLY, "true");
                            }
                            str3 = EVENT_EXPAND_NOTI_FROM_QS_SCROLL_TOPTOBOTTOM_ON_LEFT;
                            obj = VALUE_SWITCH_PANEL_TOPTOBOTTOM;
                        }
                        r0 = objM;
                    }
                    map4.put(KEY_EVENT, obj);
                    map4.put(KEY_OP, str3);
                    objM = map4;
                    r0 = objM;
                } else {
                    map3 = new HashMap();
                    if (separateStaticInfo.getKeyGuardShow()) {
                        map3.put("type", VALUE_PANEL_NOTIFY);
                        map3.put(KEY_FROM, "keyguard");
                    } else {
                        if (separateStaticInfo.getDownOnHotArea()) {
                            str4 = EVENT_EXPAND_NOTI_ON_STATUSBAR;
                        } else {
                            str4 = EVENT_EXPAND_NOTI_ON_DESKTOP;
                        }
                        str3 = str4;
                        map3.put("type", VALUE_PANEL_NOTIFY);
                        downEvent = separateStaticInfo.getDownEvent();
                        if (downEvent != null) {
                            objM = ListPopupWindow$.ExternalSyntheticOutline0.m(str2, downEvent.getX(), str, downEvent.getY(), ")");
                        }
                        map3.put(KEY_LOCATION, String.valueOf(objM));
                    }
                    map3.put(KEY_EVENT, VALUE_PULL_DOWN_SHADE);
                    map3.put(KEY_OP, str3);
                    r0 = map3;
                }
            }
            if (context2 != null || r0 == 0) {
            }
            r0.put(KEY_SEPARATE_QS_ENABLED, String.valueOf(qsEnabled));
            String str10 = (String) r0.get(KEY_EVENT);
            if (str10 == null) {
                str10 = "";
            }
            r0.remove(KEY_EVENT);
            trackEventAsync(context2, str10, r0);
            separateStaticInfo.setLastTrackTime(System.currentTimeMillis());
            separateStaticInfo.setLastTrackTo(separateStaticInfo.getTo());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("collectSeparateQsAndNotiExpandEvent  op: ", r0.get(KEY_OP), "SeparateQSStatisicUtil-->", "SystemUi--QuickSettings");
                return;
            }
            return;
        }
        str = OplusPreventModeData.SEPARATOR;
        str2 = "(";
        map2 = map5;
        r0 = map2;
        if (separateStaticInfo.getTo() == EXPANDED.NOTIFICATION) {
            from = separateStaticInfo.getFrom();
            str3 = EVENT_EXPAND_NOTI_ON_KEYGUARD;
            if (from == expanded) {
                map4 = new HashMap();
                if (separateStaticInfo.getKeyGuardShow()) {
                    map4.put("type", VALUE_PANEL_NOTIFY);
                    map4.put(KEY_FROM, "keyguard");
                    obj = VALUE_PULL_DOWN_SHADE;
                } else {
                    if (Intrinsics.areEqual(separateStaticInfo.getOpType(), OPTYPE_SCROLL_LEFTTORIGHT)) {
                        leftToRightSwitchCount.incrementAndGet();
                    } else if (Intrinsics.areEqual(separateStaticInfo.getOpType(), OPTYPE_SCROLL_TOPTOBOTTOM)) {
                        map4.put(KEY_FROM, VALUE_PANEL_QS);
                        map4.put(KEY_TO, VALUE_PANEL_NOTIFY);
                        if (isSwitchLessThanThreeseconds(separateStaticInfo)) {
                            map4.put(KEY_SWITCH_QUICKLY, "true");
                        }
                        str3 = EVENT_EXPAND_NOTI_FROM_QS_SCROLL_TOPTOBOTTOM_ON_LEFT;
                        obj = VALUE_SWITCH_PANEL_TOPTOBOTTOM;
                    }
                    r0 = objM;
                }
                map4.put(KEY_EVENT, obj);
                map4.put(KEY_OP, str3);
                objM = map4;
                r0 = objM;
            } else {
                map3 = new HashMap();
                if (separateStaticInfo.getKeyGuardShow()) {
                    map3.put("type", VALUE_PANEL_NOTIFY);
                    map3.put(KEY_FROM, "keyguard");
                } else {
                    if (separateStaticInfo.getDownOnHotArea()) {
                        str4 = EVENT_EXPAND_NOTI_ON_STATUSBAR;
                    } else {
                        str4 = EVENT_EXPAND_NOTI_ON_DESKTOP;
                    }
                    str3 = str4;
                    map3.put("type", VALUE_PANEL_NOTIFY);
                    downEvent = separateStaticInfo.getDownEvent();
                    if (downEvent != null) {
                        objM = ListPopupWindow$.ExternalSyntheticOutline0.m(str2, downEvent.getX(), str, downEvent.getY(), ")");
                    }
                    map3.put(KEY_LOCATION, String.valueOf(objM));
                }
                map3.put(KEY_EVENT, VALUE_PULL_DOWN_SHADE);
                map3.put(KEY_OP, str3);
                r0 = map3;
            }
        }
        if (context2 != null) {
        }
    }

    public static final void collectShortcutsCenterEvent(Context context2, String str, boolean z, String str2) {
        HashMap mapM = OplusCompatDcsUtils$.ExternalSyntheticOutline0.m(SHORTCUTS_KEY_ACTIVATE, str, SHORTCUTS_KEY_STATUS, str2);
        mapM.put(KEY_SEPARATE_QS_ENABLED, String.valueOf(qsEnabled));
        if (context2.getResources().getConfiguration().orientation == 2) {
            mapM.put(ORITATION, "horizontal");
        } else {
            mapM.put(ORITATION, "vertical");
        }
        if (z) {
            mapM.put("keyguard", "lock_screen");
        } else {
            mapM.put("keyguard", "unlock");
        }
        OplusTrackProxyUtil.onCommon(context2, TAG_ID, SHORTCUTS_EVENT_ID, mapM);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSStatisicUtil-->".concat("collectShortcutsCenterEvent eventMap " + mapM));
        }
    }

    public static final SeparateEditTitleData getEditTitleData() {
        return separateEditTitleData;
    }

    public static final String getPackageNameHash(String str) {
        Object obj;
        byte[] bArrDigest;
        if (str.length() == 0) {
            return "";
        }
        try {
            String str2 = sPackageNameEncryptionMap.get(str);
            if (str2 != null && str2.length() != 0) {
                return str2;
            }
            String str3 = SystemProperties.get("ro.build.version.release", "null");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (messageDigest != null) {
                Charset charset = StandardCharsets.UTF_8;
                byte[] bArrDigest2 = messageDigest.digest(str3.getBytes(charset));
                if (bArrDigest2 != null && bArrDigest2.length > 0 && (bArrDigest = messageDigest.digest(str.concat(bytesToHex(bArrDigest2)).getBytes(charset))) != null && bArrDigest.length > 0) {
                    String strBytesToHex = bytesToHex(bArrDigest);
                    sPackageNameEncryptionMap.put(str, strBytesToHex);
                    return strBytesToHex;
                }
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return "";
        }
        LogUtil.e$default(12, TAG, "CatchException getHashPackageName():" + th2.getMessage(), null);
        return "";
    }

    public static final SeparateDragTileData getSeparateDragTileData() {
        return separateDragTileData;
    }

    public static final SeparateTileInfoData getSeparateTileInfoData() {
        return separateTileInfoData;
    }

    public static final boolean isSwitchLessThanThreeseconds(SeparateStaticInfo separateStaticInfo) {
        return (separateStaticInfo.getLastTrackTime() == -1 || separateStaticInfo.getTo() == separateStaticInfo.getLastTrackTo() || System.currentTimeMillis() - separateStaticInfo.getLastTrackTime() >= 3000) ? false : true;
    }

    public static final void setContext(Context context2) {
        context = context2;
    }

    public static final void trackEventAsync(Context context2, String str, Map map) {
        OplusUiOffloadThread companion = OplusUiOffloadThread.Companion.getInstance();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$context = context2;
        anonymousClass1.$eventID = str;
        anonymousClass1.$logMap = map;
        VarHandle.storeStoreFence();
        companion.runOnSubThread(anonymousClass1);
    }

    public static final void updateScenarioEntryMode(String str) {
        OplusUiOffloadThread companion = OplusUiOffloadThread.Companion.getInstance();
        RunnableC02541 runnableC02541 = new RunnableC02541(1);
        runnableC02541.$scenarioType = str;
        VarHandle.storeStoreFence();
        companion.runOnSubThread(runnableC02541);
    }

    public static final void updateScenarioType(String str) {
        OplusUiOffloadThread companion = OplusUiOffloadThread.Companion.getInstance();
        RunnableC02541 runnableC02541 = new RunnableC02541(0);
        runnableC02541.$scenarioType = str;
        VarHandle.storeStoreFence();
        companion.runOnSubThread(runnableC02541);
    }

    public static final void uploadLeftToRightSwitchEvent(Context context2) {
        HashMap map = new HashMap();
        LockScreenStatisticUtil$$ExternalSyntheticOutline0.m(leftToRightSwitchCount, 0, map, EVENT_LEFT_TO_RIGHT_PANEL_SWITCH_COUNT);
        trackEventAsync(context2, VALUE_SWITCH_PANEL_LEFTTORIGHT, map);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSStatisicUtil-->".concat("uploadLeftToRightSwitchEvent, it: " + map));
        }
    }

    public static final void uploadQsPersonalizedEditStatisticsEvent(Context context2) {
        HashMap map = new HashMap();
        if (context2.getResources().getConfiguration().orientation == 2) {
            map.put(ORITATION, "1");
        } else {
            map.put(ORITATION, "2");
        }
        if (QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS()) {
            map.put(MODE, "1");
        } else {
            map.put(MODE, "0");
        }
        map.put(EVENT_QS_TILES_COUNT, separateTileInfoData.getQsTilesCount());
        map.put(EVENT_QS_TILES_DOTS_COUNT, String.valueOf(separateTileInfoData.getQsTilesDotsCount()));
        map.put(EVENT_QS_TILES_SPEC, separateTileInfoData.getQsTilesSpec());
        OplusTrackProxyUtil.onCommon(context2, TAG_ID, PERSONALIZED_EDIT_STATISTICS_TYPE, map);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSStatisicUtil-->".concat("uploadQsPersonalizedEditStatisticsEvent eventMap:" + map));
        }
    }

    public final boolean getQsEnabled() {
        return qsEnabled;
    }

    public final ScenarioStatUtil.ScenarioStat getScenarioStat() {
        return scenarioStat;
    }

    public final void setQsEnabled(boolean z) {
        qsEnabled = z;
    }

    public final void setScenarioStat(ScenarioStatUtil.ScenarioStat scenarioStat2) {
        scenarioStat = scenarioStat2;
    }

    public final void setSceneTileClick(boolean z) {
        isSceneTileClick = z;
    }
}
