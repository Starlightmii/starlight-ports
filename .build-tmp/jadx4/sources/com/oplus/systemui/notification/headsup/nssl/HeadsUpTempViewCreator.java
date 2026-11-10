package com.oplus.systemui.notification.headsup.nssl;

import android.R;
import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Process;
import android.os.UserHandle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflater;
import com.android.systemui.statusbar.notification.collection.render.NotifViewBarn;
import com.android.systemui.statusbar.notification.collection.render.NotifViewController;
import com.android.systemui.statusbar.notification.row.NotifBindPipeline;
import com.android.systemui.statusbar.notification.row.RowContentBindParams;
import com.oplus.transition.OplusAnimationController;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpTempViewCreator {
    private final Context context;
    private final NotifBindPipeline notifBindPipeline;
    private final NotifInflater notifInflater;
    private final NotifViewBarn notifViewBarn;
    private final Lazy temp$delegate;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.nssl.HeadsUpTempViewCreator$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements NotifInflater.InflationCallback {
        public /* synthetic */ Function1 $onViewGet;
        public /* synthetic */ HeadsUpTempViewCreator this$0;

        public final void onInflationFinished(NotificationEntry notificationEntry, NotifViewController notifViewController) {
            this.this$0.notifViewBarn.registerViewForEntry(notificationEntry, notifViewController);
            this.$onViewGet.invoke(notificationEntry.getRow());
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$TaapOV37Cz8RlEqMB-tj5dHoTSQ, reason: not valid java name */
    public static NotificationEntry m55$r8$lambda$TaapOV37Cz8RlEqMBtj5dHoTSQ(HeadsUpTempViewCreator headsUpTempViewCreator) {
        StatusBarNotification statusBarNotification = new StatusBarNotification(headsUpTempViewCreator.context.getPackageName(), headsUpTempViewCreator.context.getPackageName(), 1, (String) null, headsUpTempViewCreator.context.getUserId(), Process.myPid(), new Notification.Builder(headsUpTempViewCreator.context, "tempHeasUpView").setContentTitle("TempHeadsUpView").setContentText("TempHeadsUpView").setSmallIcon(Icon.createWithResource(OplusAnimationController.PACKAGE_SYSTEM, R.drawable.ic_menu_view)).build(), UserHandle.CURRENT, (String) null, System.currentTimeMillis());
        NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
        ranking.populate(statusBarNotification.getKey(), 0, false, 0, 0, 0, null, null, null, new ArrayList(), false, 0, false, 0L, new ArrayList(), new ArrayList(), false, false, false, null, false, 0, false, "null");
        return new NotificationEntry(statusBarNotification, ranking, System.currentTimeMillis());
    }

    public HeadsUpTempViewCreator(Context context, NotifInflater notifInflater, NotifViewBarn notifViewBarn, NotifBindPipeline notifBindPipeline) {
        this.context = context;
        this.notifInflater = notifInflater;
        this.notifViewBarn = notifViewBarn;
        this.notifBindPipeline = notifBindPipeline;
        HeadsUpTempViewCreator$$ExternalSyntheticLambda0 headsUpTempViewCreator$$ExternalSyntheticLambda0 = new HeadsUpTempViewCreator$$ExternalSyntheticLambda0();
        headsUpTempViewCreator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.temp$delegate = LazyKt__LazyJVMKt.lazy(headsUpTempViewCreator$$ExternalSyntheticLambda0);
    }

    public final NotificationEntry getTemp() {
        return (NotificationEntry) this.temp$delegate.getValue();
    }

    public final boolean hasInit() {
        return getTemp().getRow() != null;
    }

    public final void init(Function1 function1) {
        NotifInflater.Params params = new NotifInflater.Params(false, "tempHeadsViewInit", false, false, false, 0);
        NotifBindPipeline notifBindPipeline = this.notifBindPipeline;
        NotificationEntry temp = getTemp();
        ((ArrayMap) notifBindPipeline.mBindEntries).put(temp, new NotifBindPipeline.BindEntry());
        Map map = notifBindPipeline.mStage.mContentParams;
        RowContentBindParams rowContentBindParams = new RowContentBindParams();
        rowContentBindParams.mContentViews = 3;
        rowContentBindParams.mRedactionType = 0;
        rowContentBindParams.mDirtyContentViews = 3;
        ((ArrayMap) map).put(temp, rowContentBindParams);
        NotifInflater notifInflater = this.notifInflater;
        NotificationEntry temp2 = getTemp();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$onViewGet = function1;
        VarHandle.storeStoreFence();
        notifInflater.inflateViews(temp2, params, anonymousClass1);
    }
}
