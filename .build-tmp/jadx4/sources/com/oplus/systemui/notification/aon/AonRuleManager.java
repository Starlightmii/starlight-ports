package com.oplus.systemui.notification.aon;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonRuleManager {
    public static final Companion Companion = new Companion();
    private static final String KEY_AON_INTERVAL_TIME = "aon_interval_time";
    private static final String KEY_AON_RULE_SWITCH = "aon_effect_switch";
    private static final String KEY_AON_RULE_SWITCH_AND_LISTS = "aon_rules";
    private static final String KEY_AON_RULE_SWITCH_AND_LISTS_SETTINGS = "aon_rules_settings";
    private static final String TAG = "AonRuleManager";
    private final Handler bgHandler;
    private final Context context;
    private final Runnable coolFalseTask;
    public volatile boolean cooling;
    private final Handler mainHandler;
    public volatile int notificationCount;
    private final Function1 onCoolingStateChanged;
    private final Runnable resetNotificationCountTask;
    public volatile boolean isEnabled = true;
    public long countIntervalTime = 3600000;
    private final List<Pair<Integer, Long>> ruleDetail = CollectionsKt___CollectionsKt.toMutableList(CollectionsKt__CollectionsKt.listOf(new Pair[]{TuplesKt.to(50, 10000L), TuplesKt.to(100, 20000L), TuplesKt.to(Integer.valueOf(ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION), 40000L), TuplesKt.to(300, 60000L), TuplesKt.to(400, 80000L)}));

    /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonRuleManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ AonRuleManager this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    AonRuleManager.access$loadDataFromSettingProvider(this.this$0, "init");
                    return;
                case 1:
                    this.this$0.setCooling(false);
                    return;
                default:
                    AonRuleManager aonRuleManager = this.this$0;
                    synchronized (aonRuleManager) {
                        aonRuleManager.notificationCount = 0;
                        aonRuleManager.tryResetNotificationCountLater();
                    }
                    return;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AonRuleManager(Context context, Handler handler, final Handler handler2, Function1 function1) {
        this.context = context;
        this.mainHandler = handler;
        this.bgHandler = handler2;
        this.onCoolingStateChanged = function1;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.resetNotificationCountTask = anonymousClass1;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(1);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.coolFalseTask = anonymousClass2;
        AnonymousClass1 anonymousClass3 = new AnonymousClass1(0);
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        handler2.post(anonymousClass3);
        ContentObserver contentObserver = new ContentObserver(handler2) { // from class: com.oplus.systemui.notification.aon.AonRuleManager$registerContentObserver$aonRuleObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                AonRuleManager.access$loadDataFromSettingProvider(this.this$0, "onChange");
            }
        };
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(KEY_AON_RULE_SWITCH_AND_LISTS_SETTINGS), false, contentObserver, 0);
        tryResetNotificationCountLater();
    }

    public static final void access$loadDataFromSettingProvider(AonRuleManager aonRuleManager, String str) {
        Object obj;
        String stringForUser = Settings.Secure.getStringForUser(aonRuleManager.context.getContentResolver(), KEY_AON_RULE_SWITCH_AND_LISTS_SETTINGS, 0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("[loadDataFromSettingProvider]", stringForUser, ", reason=", str, "AonRuleManager-->"));
        }
        if (TextUtils.isEmpty(stringForUser)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(stringForUser);
            boolean z = jSONObject.getInt(KEY_AON_RULE_SWITCH) == 1;
            String string = jSONObject.getString(KEY_AON_INTERVAL_TIME);
            string.getClass();
            long j = (long) (Double.parseDouble(string) * 3600000.0d);
            JSONObject jSONObject2 = jSONObject.getJSONObject(KEY_AON_RULE_SWITCH_AND_LISTS);
            Iterator<String> itKeys = jSONObject2.keys();
            ArrayList arrayList = new ArrayList();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                next.getClass();
                arrayList.add(new Pair(Integer.valueOf(Integer.parseInt(next)), Long.valueOf(jSONObject2.getLong(next) * 1000)));
            }
            Handler handler = aonRuleManager.mainHandler;
            AonRuleManager$loadDataFromSettingProvider$2$1 aonRuleManager$loadDataFromSettingProvider$2$1 = new AonRuleManager$loadDataFromSettingProvider$2$1();
            aonRuleManager$loadDataFromSettingProvider$2$1.this$0 = aonRuleManager;
            aonRuleManager$loadDataFromSettingProvider$2$1.$isEnabled = z;
            aonRuleManager$loadDataFromSettingProvider$2$1.$intervalTime = j;
            aonRuleManager$loadDataFromSettingProvider$2$1.$rules = arrayList;
            VarHandle.storeStoreFence();
            obj = Result.constructor-impl(Boolean.valueOf(handler.postAtFrontOfQueue(aonRuleManager$loadDataFromSettingProvider$2$1)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "[loadDataFromSettingProvider] ", th2.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCooling(boolean z) {
        if (this.cooling != z) {
            this.cooling = z;
            Function1 function1 = this.onCoolingStateChanged;
            if (function1 != null) {
                function1.invoke(Boolean.valueOf(z));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCountIntervalTime(long j) {
        if (this.countIntervalTime != j) {
            this.countIntervalTime = j;
            tryResetNotificationCountLater();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEnabled(boolean z) {
        if (this.isEnabled != z) {
            this.isEnabled = z;
            if (z) {
                tryResetNotificationCountLater();
            } else {
                this.mainHandler.removeCallbacks(this.resetNotificationCountTask);
                reset();
            }
        }
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter) {
        printWriter.println("\n isEnabled: " + this.isEnabled + "\n currentCount: " + this.notificationCount + "\n intervalTime: " + this.countIntervalTime + "\n cooling: " + this.cooling + "\n ruleDetail: " + this.ruleDetail);
    }

    public final boolean getCooling() {
        return this.cooling;
    }

    public final synchronized void reset() {
        this.mainHandler.removeCallbacks(this.coolFalseTask);
        setCooling(false);
        this.notificationCount = 0;
    }

    public final void tryResetNotificationCountLater() {
        if (this.isEnabled) {
            this.mainHandler.removeCallbacks(this.resetNotificationCountTask);
            this.mainHandler.postDelayed(this.resetNotificationCountTask, this.countIntervalTime);
        }
    }

    public final synchronized void trySetCooling() {
        try {
            if (this.isEnabled && !this.cooling) {
                int i = this.notificationCount;
                synchronized (this) {
                    try {
                        Iterator<Pair<Integer, Long>> it = this.ruleDetail.iterator();
                        int i2 = -1;
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (i < ((Number) it.next().component1()).intValue()) {
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            String str = LogUtil.TAG + LogUtil.TAG_NOTIFICATION;
                            StringBuilder sb = new StringBuilder();
                            sb.append(TAG);
                            sb.append(LogUtil.SEPARATOR);
                            sb.append("[trySetCooling] notificationCount:" + this.notificationCount + ", index=" + i2 + ", cooling=" + this.cooling);
                            Log.d(str, sb.toString());
                        }
                        if (i2 >= 0) {
                            long jLongValue = ((Number) this.ruleDetail.get(i2).getSecond()).longValue();
                            if (jLongValue > 0) {
                                setCooling(true);
                                this.mainHandler.removeCallbacks(this.coolFalseTask);
                                this.mainHandler.postDelayed(this.coolFalseTask, jLongValue);
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
