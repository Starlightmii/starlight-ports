package com.oplus.systemui.notification.lockscreen.statistic;

import android.app.Notification;
import android.content.Context;
import android.hidl.base.V1_0.DebugInfo$;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewParent;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.fragment.app.Fragment$5$;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationCardView;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationContainerView;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import oplus.util.OplusEncryptionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenStatisticUtil {
    private static final String APP_ID = "20082";
    private static final String EVENT_ID_CAPSULE_EXPOSE = "capsule_expose";
    private static final String EVENT_ID_CAPSULE_LEFT_RIGHT_SLIDE = "capsule_left_right_slide";
    private static final String EVENT_ID_CAPSULE_LIST_STYLE_SWITCH = "capsule_list_style_switch";
    private static final String EVENT_ID_FLUID_IMMERSE_STATE = "fluid_immerse_state";
    private static final String EVENT_ID_LOCKSCREEN_STACK_EXPAND_DAILY = "lockscreen_stack_expand_daily";
    private static final String EVENT_ID_LOCKSCREEN_STATIC_TO_DYNAMIC_DAILY = "lockscreen_static_to_dynamic_daily";
    private static final String FIELD_ENTER_IMMERSE_CNT_CAPSULE = "enter_immerse_state_cnt_capsule";
    private static final String FIELD_ENTER_IMMERSE_CNT_DEFAULT = "enter_immerse_state_cnt_default";
    private static final String FIELD_ENTER_IMMERSE_CNT_LIST = "enter_immerse_state_cnt_list";
    private static final String FIELD_ENTER_IMMERSE_DUR_CAPSULE = "enter_immerse_state_dur_capsule";
    private static final String FIELD_ENTER_IMMERSE_DUR_DEFAULT = "enter_immerse_state_dur_default";
    private static final String FIELD_ENTER_IMMERSE_DUR_LIST = "enter_immerse_state_dur_list";
    private static final String GL_OTA = "glance_ota";
    private static final String IS_FOCUS = "1";
    private static final String KEY_AGGRE_CARD = "aggre_card";
    private static final String KEY_ALL_CARDS = "all_cards";
    private static final String KEY_CAPSULE_INFO = "capsule_info";
    private static final String KEY_CARD_ID = "card_id";
    private static final String KEY_CLOSE_OVERLAP = "0";
    private static final String KEY_CNT = "cnt";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_FINGERPRINT_OVERLAY_NOTIFICATION = "fingerprint_overlay_notification";
    private static final String KEY_IS_FOCUS = "is_focus";
    private static final String KEY_IS_VISIBLE = "is_visible";
    private static final String KEY_LOW_DEVICE_NONE = "2";
    private static final String KEY_NOTIFICATION_CNT = "notification_cnt";
    private static final String KEY_NOTIFICATION_ID = "id";
    private static final String KEY_OPEN_OVERLAP = "1";
    private static final String KEY_OVERLAP_TYPE = "fingerprint_overlap";
    private static final String KEY_SCREEN_STATE_AOD = "aod";
    private static final String KEY_SCREEN_STATE_ON = "screen_on";
    private static final String KEY_SHOW_STATUS = "show_status";
    public static final String KEY_STACK_EXPAND_FLAT_COUNT = "expand_flat_count";
    public static final String KEY_STACK_EXPAND_TOTAL_COUNT = "expand_total_count";
    public static final String KEY_STACK_EXPAND_WITH_TAIL_COUNT = "expand_with_tail_stack_count";
    private static final String KEY_STACK_FLUID_CLOUD_COUNT_1 = "fluid_cloud_count_1";
    private static final String KEY_STACK_FLUID_CLOUD_COUNT_2 = "fluid_cloud_count_2";
    private static final String KEY_STACK_FLUID_CLOUD_COUNT_3_PLUS = "fluid_cloud_count_3_plus";
    public static final String KEY_STACK_SCREEN_ON_CNT = "stack_screen_on_count";
    private static final String KEY_STACK_STATIC_TO_DYNAMIC_COUNT = "static_to_dynamic_count";
    private static final String KEY_STATE_SWITCH = "state_switch";
    private static final String KEY_SWITCH_CNT = "swtich_cnt";
    private static final String KEY_SWITCH_REASON = "switch_reason";
    private static final String KEY_TEMPLATE_ID = "template_id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_USER_TYPE = "user_type";
    private static final String LOG_TAG = "20082003";
    private static final String MEDIA_CONTROLLER_SERVICE_ID = "268452006";
    private static final String MEDIA_CONTROLLER_SERVICE_ID_NEW = "default_com.android.systemui_268452006";
    private static final String NEW_USER = "0";
    private static final String NOTIFICATION_CARD_TYPE = "notification";
    private static final String NOT_FOCUS = "0";
    private static final String OTA_LEVEL_USER = "1";
    private static final String OTA_VERSION = "OTA_VERSION";
    public static final String REPORT_ERROR = "error";
    private static final String SEEDLING_CARD_TYPE = "fluid";
    private static final String SYSTEM_UI_PACKAGE_NAME = "com.android.systemui";
    private static final String TAG = "LockScreenStatisticUtil";
    private static String currenCardPkg;
    private static String currentCardId;
    public static long currentImmerseStartTime;
    public static int currentImmersiveType;
    public static final LockScreenStatisticUtil INSTANCE = new LockScreenStatisticUtil();
    private static final Map<Integer, Integer> switchCntMap = new LinkedHashMap();
    public static int currRecordTimeType = -1;
    private static List<CardCount> enterImmerseStateCntList = new ArrayList();
    private static List<CardCount> enterImmerseStateCntCapsule = new ArrayList();
    private static List<CardCount> enterImmerseStateCntDefault = new ArrayList();
    private static Map<String, List<CardDuration>> enterImmerseStateDurList = new LinkedHashMap();
    private static Map<String, List<CardDuration>> enterImmerseStateDurCapsule = new LinkedHashMap();
    private static Map<String, List<CardDuration>> enterImmerseStateDurDefault = new LinkedHashMap();
    private static final AtomicInteger sinkScreenExpandTotalCount = new AtomicInteger(0);
    private static final AtomicInteger sinkScreenExpandFlatCounter = new AtomicInteger(0);
    private static final AtomicInteger sinkScreenExpandWithTailCounter = new AtomicInteger(0);
    private static final AtomicInteger sinkScreenStatic2DynamicCounter = new AtomicInteger(0);
    private static final AtomicInteger sinkScreenFluidCloud1Counter = new AtomicInteger(0);
    private static final AtomicInteger sinkScreenFluidCloud2Counter = new AtomicInteger(0);
    private static final AtomicInteger sinkScreenFluidCloudPlusCounter = new AtomicInteger(0);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CardCount {
        private final String cardId;
        public int count;
        private final String pkg;

        public CardCount(String str, int i, String str2) {
            this.cardId = str;
            this.count = i;
            this.pkg = str2;
        }

        public final String component1() {
            return this.cardId;
        }

        public final String component3() {
            return this.pkg;
        }

        public final CardCount copy(String str, int i, String str2) {
            return new CardCount(str, i, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CardCount)) {
                return false;
            }
            CardCount cardCount = (CardCount) obj;
            return Intrinsics.areEqual(this.cardId, cardCount.cardId) && this.count == cardCount.count && Intrinsics.areEqual(this.pkg, cardCount.pkg);
        }

        public final String getCardId() {
            return this.cardId;
        }

        public final int getCount() {
            return this.count;
        }

        public final String getPkg() {
            return this.pkg;
        }

        public final int hashCode() {
            int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.count, this.cardId.hashCode() * 31, 31);
            String str = this.pkg;
            return iM + (str == null ? 0 : str.hashCode());
        }

        public final void setCount(int i) {
            this.count = i;
        }

        public String toString() {
            String str = this.cardId;
            int i = this.count;
            return DebugInfo$.ExternalSyntheticOutline0.m(ConstraintSet$.ExternalSyntheticOutline0.m(i, "CardCount(cardId=", str, ", count=", ", pkg="), this.pkg, ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CardDuration {
        private final String cardId;
        public long duration;
        private final String pkg;

        public CardDuration(String str, long j, String str2) {
            this.cardId = str;
            this.duration = j;
            this.pkg = str2;
        }

        public final String component1() {
            return this.cardId;
        }

        public final String component3() {
            return this.pkg;
        }

        public final CardDuration copy(String str, long j, String str2) {
            return new CardDuration(str, j, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CardDuration)) {
                return false;
            }
            CardDuration cardDuration = (CardDuration) obj;
            return Intrinsics.areEqual(this.cardId, cardDuration.cardId) && this.duration == cardDuration.duration && Intrinsics.areEqual(this.pkg, cardDuration.pkg);
        }

        public final String getCardId() {
            return this.cardId;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final String getPkg() {
            return this.pkg;
        }

        public final int hashCode() {
            int iM = ChangeSize$.ExternalSyntheticOutline0.m(this.cardId.hashCode() * 31, 31, this.duration);
            String str = this.pkg;
            return iM + (str == null ? 0 : str.hashCode());
        }

        public final void setDuration(long j) {
            this.duration = j;
        }

        public String toString() {
            String str = this.cardId;
            return Fragment$5$.ExternalSyntheticOutline0.m(", pkg=", this.pkg, ")", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(this.duration, "CardDuration(cardId=", str, ", duration="));
        }
    }

    public static Unit $r8$lambda$HFJVjZZvxnysoSkJh4Ri0ttphmY(int i) {
        sinkScreenStatic2DynamicCounter.incrementAndGet();
        if (i == 1) {
            sinkScreenFluidCloud1Counter.incrementAndGet();
        } else if (i == 2) {
            sinkScreenFluidCloud2Counter.incrementAndGet();
        } else if (i >= 3) {
            sinkScreenFluidCloudPlusCounter.incrementAndGet();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$j0ltI_ey8g-Qv96ODBziHOZGQ3A, reason: not valid java name */
    public static Unit m92$r8$lambda$j0ltI_ey8gQv96ODBziHOZGQ3A(String str) {
        int iHashCode = str.hashCode();
        if (iHashCode != -538152547) {
            if (iHashCode != 294037390) {
                if (iHashCode == 339489327 && str.equals(KEY_STACK_EXPAND_TOTAL_COUNT)) {
                    sinkScreenExpandTotalCount.incrementAndGet();
                }
            } else if (str.equals(KEY_STACK_EXPAND_FLAT_COUNT)) {
                sinkScreenExpandFlatCounter.incrementAndGet();
            }
        } else if (str.equals(KEY_STACK_EXPAND_WITH_TAIL_COUNT)) {
            sinkScreenExpandWithTailCounter.incrementAndGet();
        }
        return Unit.INSTANCE;
    }

    public static final String generateCountJsonObject(List list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CardCount cardCount = (CardCount) it.next();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_CARD_ID, replacePackageNameInKey(cardCount.getCardId(), cardCount.getPkg()));
            String pkg = cardCount.getPkg();
            if (pkg != null) {
                jSONObject.put("pkg", OplusEncryptionUtils.getPackageNameHash(pkg));
            }
            jSONObject.put(KEY_CNT, cardCount.getCount());
            jSONArray.put(jSONObject);
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(KEY_ALL_CARDS, jSONArray);
        return jSONObject2.toString();
    }

    public static final String generateDurationJsonObject(Map map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : CollectionsKt__CollectionsKt.listOf(new String[]{KEY_SCREEN_STATE_ON, KEY_SCREEN_STATE_AOD})) {
            List<CardDuration> list = (List) map.get(str);
            List list2 = list;
            if (list2 != null && !list2.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (CardDuration cardDuration : list) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(KEY_CARD_ID, replacePackageNameInKey(cardDuration.getCardId(), cardDuration.getPkg()));
                    String pkg = cardDuration.getPkg();
                    if (pkg != null) {
                        jSONObject2.put("pkg", OplusEncryptionUtils.getPackageNameHash(pkg));
                    }
                    jSONObject2.put(KEY_DURATION, cardDuration.getDuration());
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put(str, jSONArray);
            }
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00c0  */
    public static final JSONObject getCapsuleDetailJson(String str, List<? extends CapsuleItemData> list, String str2) throws JSONException {
        CapsuleItemView baseCard;
        String str3;
        CapsuleNotificationDataController.CapsuleNotificationInnerData innerData;
        List<CapsuleNotificationDataController.CapsuleNotificationReportData> reportDataList;
        JSONObject jSONObject;
        Notification notification;
        Bundle bundle;
        String string;
        UserHandle user;
        StatusBarNotification sbn;
        CapsuleNotificationDataController.CapsuleNotificationInnerData innerData2;
        CapsuleNotificationDataController.CapsuleNotificationInnerData innerData3;
        boolean z;
        CapsuleItemData entry;
        ViewParent parent;
        String str4 = str2;
        String str5 = "2";
        if (str.equals("2") && str4 == null) {
            return new JSONObject();
        }
        List<? extends CapsuleItemData> list2 = list;
        Iterator<T> it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                baseCard = null;
                break;
            }
            ViewParent parent2 = ((CapsuleItemData) it.next()).getContentView().getParent();
            ViewParent parent3 = (parent2 == null || (parent = parent2.getParent()) == null) ? null : parent.getParent();
            if (parent3 instanceof NotificationCapsuleContainer) {
                baseCard = ((NotificationCapsuleContainer) parent3).getBaseCard();
                break;
            }
        }
        boolean z2 = (baseCard != null ? baseCard.getEntry() : null) instanceof CapsuleSeedingData;
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            CapsuleItemData capsuleItemData = (CapsuleItemData) it2.next();
            String str6 = "0";
            Iterator it3 = it2;
            if (capsuleItemData instanceof CapsuleSeedingData) {
                CapsuleSeedingData capsuleSeedingData = (CapsuleSeedingData) capsuleItemData;
                if (str.equals(str5) && StringsKt__StringsJVMKt.equals(str4, capsuleSeedingData.getCustomRowEntry().getKey(), false)) {
                    str3 = str5;
                } else {
                    if (!z2) {
                        z = false;
                    } else if (capsuleSeedingData.getCustomRowEntry().getKey().equals((baseCard == null || (entry = baseCard.getEntry()) == null) ? null : entry.getKey())) {
                        z = true;
                    } else {
                        z = false;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    str3 = str5;
                    jSONObject2.put(KEY_CARD_ID, replacePackageNameInKey(capsuleSeedingData.getCustomRowEntry().getKey(), capsuleSeedingData.getCustomRowEntry().getPackageName()));
                    if (isMediaSeedlingCard(capsuleSeedingData.getCustomRowEntry())) {
                        jSONObject2.put("pkg", OplusEncryptionUtils.getPackageNameHash(capsuleSeedingData.getCustomRowEntry().getPackageName()));
                    }
                    jSONObject2.put("type", SEEDLING_CARD_TYPE);
                    jSONObject2.put(KEY_IS_FOCUS, z ? "1" : "0");
                    jSONObject2.put(KEY_SHOW_STATUS, str);
                    if (jSONObject2.length() <= 0) {
                        jSONObject2 = null;
                    }
                    if (jSONObject2 != null) {
                        jSONArray.put(jSONObject2);
                    }
                }
            } else {
                str3 = str5;
                if (capsuleItemData.getContentView() instanceof CapsuleNotificationContainerView) {
                    CapsuleNotificationContainerView capsuleNotificationContainerView = (CapsuleNotificationContainerView) capsuleItemData.getContentView();
                    boolean z3 = (z2 || baseCard == null) ? false : true;
                    CapsuleNotificationCardView currentView = capsuleNotificationContainerView.getCurrentView();
                    int notifCount = (currentView == null || (innerData3 = currentView.getInnerData()) == null) ? 0 : innerData3.getNotifCount();
                    if (notifCount == 1) {
                        CapsuleNotificationCardView currentView2 = capsuleNotificationContainerView.getCurrentView();
                        CapsuleNotificationDataController.NotificationIconData iconData = (currentView2 == null || (innerData2 = currentView2.getInnerData()) == null) ? null : innerData2.getIconData();
                        NotificationEntry entry2 = iconData != null ? iconData.getEntry() : null;
                        if (((entry2 == null || (sbn = entry2.getSbn()) == null) ? null : sbn.getNotification()) != null) {
                            entry2.getClass();
                            iconData.getClass();
                            jSONObject = new JSONObject();
                            CapsuleNotificationDataController.NotificationIconData notificationIconData = iconData;
                            jSONObject.put("id", replacePackageNameInKey(entry2.getKey(), notificationIconData.getPackageName()));
                            jSONObject.put("type", NOTIFICATION_CARD_TYPE);
                            jSONObject.put(KEY_IS_FOCUS, z3 ? "1" : "0");
                            jSONObject.put("notification_type", NotificationStatisticUtil.getNotificationType(entry2.getSbn()));
                            jSONObject.put("notification_source", MCSUtil.isMCSPkg(entry2.getSbn()) ? "opush" : "local");
                            jSONObject.put("pkg", OplusEncryptionUtils.getPackageNameHash(notificationIconData.getPackageName()));
                            StatusBarNotification sbn2 = entry2.getSbn();
                            jSONObject.put("user_id", String.valueOf((sbn2 == null || (user = sbn2.getUser()) == null) ? null : Integer.valueOf(user.getIdentifier())));
                            StatusBarNotification sbn3 = entry2.getSbn();
                            if (sbn3 != null && (notification = sbn3.getNotification()) != null && (bundle = notification.extras) != null && (string = Integer.valueOf(bundle.getInt("is_commercial", 0)).toString()) != null) {
                                str6 = string;
                            }
                            jSONObject.put("is_commercial", str6);
                            jSONObject.put("is_top", NotificationUtil.getForceTop(entry2.getSbn()));
                            jSONObject.put("is_unfold", NotificationUtil.getForceExpand(entry2.getSbn()));
                            jSONObject.put(KEY_SHOW_STATUS, str);
                        } else {
                            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "createNotificationCardJson has error,return empty json.");
                            jSONObject = new JSONObject();
                        }
                        if (jSONObject.length() <= 0) {
                            jSONObject = null;
                        }
                        if (jSONObject != null) {
                            jSONArray.put(jSONObject);
                        }
                    } else if (notifCount > 1) {
                        CapsuleNotificationCardView currentView3 = capsuleNotificationContainerView.getCurrentView();
                        if (currentView3 != null && (innerData = currentView3.getInnerData()) != null && (reportDataList = innerData.getReportDataList()) != null) {
                            if (reportDataList.isEmpty()) {
                                reportDataList = null;
                            }
                            if (reportDataList != null) {
                                JSONArray jSONArray3 = new JSONArray();
                                for (CapsuleNotificationDataController.CapsuleNotificationReportData capsuleNotificationReportData : reportDataList) {
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("pkg", OplusEncryptionUtils.getPackageNameHash(capsuleNotificationReportData.getPkg()));
                                    jSONObject3.put(KEY_NOTIFICATION_CNT, capsuleNotificationReportData.getCount());
                                    jSONObject3.put(KEY_IS_VISIBLE, capsuleNotificationReportData.isIconShow);
                                    jSONArray3.put(jSONObject3);
                                }
                                int length = jSONArray3.length();
                                for (int i = 0; i < length; i++) {
                                    jSONArray2.put(jSONArray3.opt(i));
                                }
                            }
                        }
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "LockScreenStatisticUtil-->createSeedingCardJson has error,return empty json.");
                    }
                }
            }
            str4 = str2;
            it2 = it3;
            str5 = str3;
        }
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put(KEY_ALL_CARDS, jSONArray);
        if (jSONArray2.length() > 0) {
            jSONObject4.put(KEY_AGGRE_CARD, jSONArray2);
        }
        return jSONObject4;
    }

    public static final int getCurrRecordTimeType() {
        return currRecordTimeType;
    }

    public static final int getCurrentImmerseType() {
        return currentImmersiveType;
    }

    private final String getKeyguardClockStyle() {
        String pkgForCurrentClock = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().getPkgForCurrentClock();
        return pkgForCurrentClock == null ? "" : pkgForCurrentClock;
    }

    public static final String getRecordCardId() {
        return currentCardId;
    }

    public static final String getRecordCardPkg() {
        return currenCardPkg;
    }

    public static final long getRecordImmerseStartTime() {
        return currentImmerseStartTime;
    }

    public static final String getReportExtStateValue(int i) {
        if (i == 0) {
            return "1";
        }
        if (i == 1) {
            return "3";
        }
        if (i != 2) {
            return i != 3 ? REPORT_ERROR : "4";
        }
        return "2";
    }

    public static final void incrementCardCount(int i, String str, String str2) {
        List<CardCount> list;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        Object obj = null;
        if (i == 1) {
            list = enterImmerseStateCntList;
        } else if (i != 2) {
            list = i != 3 ? null : enterImmerseStateCntDefault;
        } else {
            list = enterImmerseStateCntCapsule;
        }
        if (list == null) {
            return;
        }
        for (Object obj2 : list) {
            if (Intrinsics.areEqual(((CardCount) obj2).getCardId(), str2)) {
                obj = obj2;
                break;
            }
        }
        CardCount cardCount = (CardCount) obj;
        if (cardCount != null) {
            cardCount.setCount(cardCount.getCount() + 1);
        } else {
            list.add(new CardCount(str2, 1, str));
        }
    }

    public static final void incrementOneCounter(String str) {
        LockScreenStatisticUtil lockScreenStatisticUtil = INSTANCE;
        LockScreenStatisticUtil$$ExternalSyntheticLambda7 lockScreenStatisticUtil$$ExternalSyntheticLambda7 = new LockScreenStatisticUtil$$ExternalSyntheticLambda7(0);
        lockScreenStatisticUtil$$ExternalSyntheticLambda7.f$0 = str;
        VarHandle.storeStoreFence();
        lockScreenStatisticUtil.getClass();
        postThread(lockScreenStatisticUtil$$ExternalSyntheticLambda7);
    }

    public static final boolean isMediaSeedlingCard(CustomRowEntry customRowEntry) {
        return "268452006".equals(customRowEntry.getServiceId()) || "default_com.android.systemui_268452006".equals(customRowEntry.getServiceId());
    }

    public static void postThread(Function0 function0) {
        ThreadManager companion = ThreadManager.Companion.getInstance();
        LockScreenStatisticUtil$$ExternalSyntheticLambda7 lockScreenStatisticUtil$$ExternalSyntheticLambda7 = new LockScreenStatisticUtil$$ExternalSyntheticLambda7(1);
        lockScreenStatisticUtil$$ExternalSyntheticLambda7.f$0 = function0;
        VarHandle.storeStoreFence();
        companion.postInThread(lockScreenStatisticUtil$$ExternalSyntheticLambda7);
    }

    public static final void recordCapsuleCount(int i) {
        Integer numValueOf = Integer.valueOf(i);
        if (numValueOf.intValue() == 1) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            Map<Integer, Integer> map = switchCntMap;
            Integer numValueOf2 = Integer.valueOf(i);
            Integer num = map.get(Integer.valueOf(i));
            map.put(numValueOf2, Integer.valueOf((num != null ? num.intValue() : 0) + 1));
        }
    }

    public static final void recordImmerseTime(int i, int i2, String str, String str2, long j) {
        Map<String, List<CardDuration>> map;
        String str3;
        if (j < 0) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "recordImmerseTime: duration time is invalid, duration=" + j + ", cardId=" + str2);
            return;
        }
        if (str2 == null || str2.length() == 0) {
            return;
        }
        Object obj = null;
        if (i == 1) {
            map = enterImmerseStateDurList;
        } else if (i != 2) {
            map = i != 3 ? null : enterImmerseStateDurDefault;
        } else {
            map = enterImmerseStateDurCapsule;
        }
        if (map == null) {
            return;
        }
        if (i2 == 0) {
            str3 = KEY_SCREEN_STATE_ON;
        } else if (i2 != 1) {
            return;
        } else {
            str3 = KEY_SCREEN_STATE_AOD;
        }
        List<CardDuration> arrayList = map.get(str3);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(str3, arrayList);
        }
        for (Object obj2 : arrayList) {
            CardDuration cardDuration = (CardDuration) obj2;
            if (Intrinsics.areEqual(cardDuration.getCardId(), str2) && Intrinsics.areEqual(cardDuration.getPkg(), str)) {
                obj = obj2;
                break;
            }
        }
        CardDuration cardDuration2 = (CardDuration) obj;
        if (cardDuration2 != null) {
            cardDuration2.setDuration(cardDuration2.getDuration() + j);
        } else {
            arrayList.add(new CardDuration(str2, j, str));
        }
    }

    public static final String replacePackageNameInKey(String str, String str2) {
        if (StringsKt__StringsKt.isBlank(str)) {
            return str;
        }
        if (str2 == null || StringsKt__StringsKt.isBlank(str2) || !StringsKt__StringsKt.contains(str, str2, false)) {
            return StringsKt__StringsKt.contains(str, "com.android.systemui", false) ? StringsKt__StringsJVMKt.replace(str, "com.android.systemui", "", false) : str;
        }
        return StringsKt__StringsJVMKt.replace(str, str2, "", false);
    }

    public static final void reportCapsuleInfo(Context context, int i, List<? extends CapsuleItemData> list, String str) {
        if ((!list.isEmpty() ? list : null) == null) {
            return;
        }
        String reportExtStateValue = getReportExtStateValue(i);
        if ((REPORT_ERROR.equals(reportExtStateValue) ? null : reportExtStateValue) == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (i == 3) {
            LockScreenStatisticUtil lockScreenStatisticUtil = INSTANCE;
            LockScreenStatisticUtil$$ExternalSyntheticLambda7 lockScreenStatisticUtil$$ExternalSyntheticLambda7 = new LockScreenStatisticUtil$$ExternalSyntheticLambda7(2);
            lockScreenStatisticUtil$$ExternalSyntheticLambda7.f$0 = applicationContext;
            VarHandle.storeStoreFence();
            lockScreenStatisticUtil.getClass();
            postThread(lockScreenStatisticUtil$$ExternalSyntheticLambda7);
            return;
        }
        LockScreenStatisticUtil lockScreenStatisticUtil2 = INSTANCE;
        LockScreenStatisticUtil$$ExternalSyntheticLambda9 lockScreenStatisticUtil$$ExternalSyntheticLambda9 = new LockScreenStatisticUtil$$ExternalSyntheticLambda9();
        lockScreenStatisticUtil$$ExternalSyntheticLambda9.f$0 = reportExtStateValue;
        lockScreenStatisticUtil$$ExternalSyntheticLambda9.f$1 = list;
        lockScreenStatisticUtil$$ExternalSyntheticLambda9.f$2 = str;
        lockScreenStatisticUtil$$ExternalSyntheticLambda9.f$3 = applicationContext;
        VarHandle.storeStoreFence();
        lockScreenStatisticUtil2.getClass();
        postThread(lockScreenStatisticUtil$$ExternalSyntheticLambda9);
    }

    public static final void reportImmerseStateStatistics(Context context) {
        int i;
        int i2;
        String str = currentCardId;
        if (str != null && (i = currentImmersiveType) != 0 && (i2 = currRecordTimeType) != -1) {
            recordImmerseTime(i, i2, currenCardPkg, str, (SystemClock.elapsedRealtime() - currentImmerseStartTime) / 1000);
            currentImmerseStartTime = SystemClock.elapsedRealtime();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!enterImmerseStateCntList.isEmpty()) {
            linkedHashMap.put(FIELD_ENTER_IMMERSE_CNT_LIST, generateCountJsonObject(enterImmerseStateCntList));
        }
        if (!enterImmerseStateCntCapsule.isEmpty()) {
            linkedHashMap.put(FIELD_ENTER_IMMERSE_CNT_CAPSULE, generateCountJsonObject(enterImmerseStateCntCapsule));
        }
        if (!enterImmerseStateCntDefault.isEmpty()) {
            linkedHashMap.put(FIELD_ENTER_IMMERSE_CNT_DEFAULT, generateCountJsonObject(enterImmerseStateCntDefault));
        }
        if (!enterImmerseStateDurList.isEmpty()) {
            linkedHashMap.put(FIELD_ENTER_IMMERSE_DUR_LIST, generateDurationJsonObject(enterImmerseStateDurList));
        }
        if (!enterImmerseStateDurCapsule.isEmpty()) {
            linkedHashMap.put(FIELD_ENTER_IMMERSE_DUR_CAPSULE, generateDurationJsonObject(enterImmerseStateDurCapsule));
        }
        if (!enterImmerseStateDurDefault.isEmpty()) {
            linkedHashMap.put(FIELD_ENTER_IMMERSE_DUR_DEFAULT, generateDurationJsonObject(enterImmerseStateDurDefault));
        }
        if (linkedHashMap.isEmpty()) {
            return;
        }
        ThreadManager companion = ThreadManager.Companion.getInstance();
        LockScreenStatisticUtil$$ExternalSyntheticLambda1 lockScreenStatisticUtil$$ExternalSyntheticLambda1 = new LockScreenStatisticUtil$$ExternalSyntheticLambda1(0);
        lockScreenStatisticUtil$$ExternalSyntheticLambda1.f$0 = context;
        lockScreenStatisticUtil$$ExternalSyntheticLambda1.f$1 = linkedHashMap;
        VarHandle.storeStoreFence();
        companion.postInThread(lockScreenStatisticUtil$$ExternalSyntheticLambda1);
        enterImmerseStateCntList.clear();
        enterImmerseStateCntCapsule.clear();
        enterImmerseStateCntDefault.clear();
        enterImmerseStateDurList.clear();
        enterImmerseStateDurCapsule.clear();
        enterImmerseStateDurDefault.clear();
    }

    public static final void reportListSystleSwitch(Context context, int i, int i2, int i3, List<? extends CapsuleItemData> list, String str) {
        String strConcat = getReportExtStateValue(i).concat(getReportExtStateValue(i2));
        if (StringsKt__StringsKt.contains(strConcat, REPORT_ERROR, false)) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        LockScreenStatisticUtil lockScreenStatisticUtil = INSTANCE;
        LockScreenStatisticUtil$$ExternalSyntheticLambda3 lockScreenStatisticUtil$$ExternalSyntheticLambda3 = new LockScreenStatisticUtil$$ExternalSyntheticLambda3();
        lockScreenStatisticUtil$$ExternalSyntheticLambda3.f$0 = strConcat;
        lockScreenStatisticUtil$$ExternalSyntheticLambda3.f$1 = i3;
        lockScreenStatisticUtil$$ExternalSyntheticLambda3.f$2 = i2;
        lockScreenStatisticUtil$$ExternalSyntheticLambda3.f$3 = list;
        lockScreenStatisticUtil$$ExternalSyntheticLambda3.f$4 = str;
        lockScreenStatisticUtil$$ExternalSyntheticLambda3.f$5 = applicationContext;
        VarHandle.storeStoreFence();
        lockScreenStatisticUtil.getClass();
        postThread(lockScreenStatisticUtil$$ExternalSyntheticLambda3);
    }

    public static final void reportSinkEvent(Context context) {
        Map sinkCommonMap = INSTANCE.getSinkCommonMap(context);
        HashMap map = new HashMap();
        map.putAll(sinkCommonMap);
        LockScreenStatisticUtil$$ExternalSyntheticOutline0.m(sinkScreenExpandTotalCount, 0, map, KEY_STACK_EXPAND_TOTAL_COUNT);
        LockScreenStatisticUtil$$ExternalSyntheticOutline0.m(sinkScreenExpandFlatCounter, 0, map, KEY_STACK_EXPAND_FLAT_COUNT);
        LockScreenStatisticUtil$$ExternalSyntheticOutline0.m(sinkScreenExpandWithTailCounter, 0, map, KEY_STACK_EXPAND_WITH_TAIL_COUNT);
        ThreadManager.Companion companion = ThreadManager.Companion;
        ThreadManager companion2 = companion.getInstance();
        LockScreenStatisticUtil$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new LockScreenStatisticUtil$.ExternalSyntheticLambda5(0);
        externalSyntheticLambda5.f$0 = context;
        externalSyntheticLambda5.f$1 = map;
        VarHandle.storeStoreFence();
        companion2.postInThread(externalSyntheticLambda5);
        HashMap map2 = new HashMap();
        map2.putAll(sinkCommonMap);
        LockScreenStatisticUtil$$ExternalSyntheticOutline0.m(sinkScreenStatic2DynamicCounter, 0, map2, KEY_STACK_STATIC_TO_DYNAMIC_COUNT);
        LockScreenStatisticUtil$$ExternalSyntheticOutline0.m(sinkScreenFluidCloud1Counter, 0, map2, KEY_STACK_FLUID_CLOUD_COUNT_1);
        LockScreenStatisticUtil$$ExternalSyntheticOutline0.m(sinkScreenFluidCloud2Counter, 0, map2, KEY_STACK_FLUID_CLOUD_COUNT_2);
        map2.put(KEY_STACK_FLUID_CLOUD_COUNT_3_PLUS, String.valueOf(sinkScreenFluidCloudPlusCounter.getAndSet(0)));
        ThreadManager companion3 = companion.getInstance();
        LockScreenStatisticUtil$.ExternalSyntheticLambda5 externalSyntheticLambda6 = new LockScreenStatisticUtil$.ExternalSyntheticLambda5(1);
        externalSyntheticLambda6.f$0 = context;
        externalSyntheticLambda6.f$1 = map2;
        VarHandle.storeStoreFence();
        companion3.postInThread(externalSyntheticLambda6);
    }

    public static final void reportSwitchCount(Context context) throws JSONException {
        Map<Integer, Integer> map = switchCntMap;
        if (map.isEmpty()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            jSONObject.put(String.valueOf(iIntValue), entry.getValue().intValue());
        }
        HashMap map2 = new HashMap();
        map2.put(KEY_SWITCH_CNT, jSONObject.toString());
        ThreadManager companion = ThreadManager.Companion.getInstance();
        LockScreenStatisticUtil$$ExternalSyntheticLambda1 lockScreenStatisticUtil$$ExternalSyntheticLambda1 = new LockScreenStatisticUtil$$ExternalSyntheticLambda1(1);
        lockScreenStatisticUtil$$ExternalSyntheticLambda1.f$0 = context;
        lockScreenStatisticUtil$$ExternalSyntheticLambda1.f$1 = map2;
        VarHandle.storeStoreFence();
        companion.postInThread(lockScreenStatisticUtil$$ExternalSyntheticLambda1);
        switchCntMap.clear();
    }

    public static final void resetCounters(boolean z) {
        if (z) {
            return;
        }
        currRecordTimeType = -1;
        currentCardId = null;
        currentImmersiveType = 0;
        currenCardPkg = null;
        currentImmerseStartTime = 0L;
    }

    public static final void setCurrRecordTimeType(int i) {
        currRecordTimeType = i;
    }

    public static final void setCurrentImmerseType(int i) {
        currentImmersiveType = i;
    }

    public static final void setRecordCardId(String str) {
        currentCardId = str;
    }

    public static final void setRecordCardPkg(String str) {
        currenCardPkg = str;
    }

    public static final void setRecordImmerseStartTime(long j) {
        currentImmerseStartTime = j;
    }

    public final Map getSinkCommonMap(Context context) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String str = SystemProperties.get("ro.build.version.ota", "");
        String string = context.getSharedPreferences(OTA_VERSION, 0).getString(GL_OTA, "");
        String str2 = "1";
        linkedHashMap.put(KEY_USER_TYPE, (TextUtils.isEmpty(string) || Intrinsics.areEqual(string, str)) ? "1" : "0");
        linkedHashMap.put(KEY_TEMPLATE_ID, getKeyguardClockStyle());
        if (((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().getFpType(true) != 2) {
            str2 = "2";
        } else if (MultiUserContentHelper.Companion.getSecureIntValue(context, KEY_FINGERPRINT_OVERLAY_NOTIFICATION, -1) != 1) {
            str2 = "0";
        }
        linkedHashMap.put(KEY_OVERLAP_TYPE, str2);
        return linkedHashMap;
    }
}
