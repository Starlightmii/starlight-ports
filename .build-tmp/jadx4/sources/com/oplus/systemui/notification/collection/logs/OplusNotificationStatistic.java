package com.oplus.systemui.notification.collection.logs;

import android.content.Context;
import android.os.Handler;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dependency;
import com.android.systemui.InitController;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleClockControllerImpl;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleWidgetUIControllerImpl;
import com.oplus.systemui.keyguard.data.widgetinfo.WidgetInfoUploadHelper;
import com.oplus.systemui.notification.statistic.CustomRowTrackLinkHelper;
import com.oplus.systemui.notification.statistic.NotificationTrackLinkHelper;
import com.oplus.systemui.statusbar.util.NotificationTrackHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationStatistic implements CoreStartable {
    private final Context context;
    private final InitController initController;
    private final KeyguardStyleClockControllerImpl keyguardStyleClockController;
    private final KeyguardStyleWidgetUIControllerImpl keyguardStyleWidgetUIController;
    private final NotifPipeline pipeline;
    private final IStatusBarService statusBarService;

    public OplusNotificationStatistic(Context context, NotifPipeline notifPipeline, InitController initController, IStatusBarService iStatusBarService, KeyguardStyleClockControllerImpl keyguardStyleClockControllerImpl, KeyguardStyleWidgetUIControllerImpl keyguardStyleWidgetUIControllerImpl) {
        this.context = context;
        this.pipeline = notifPipeline;
        this.initController = initController;
        this.statusBarService = iStatusBarService;
        this.keyguardStyleClockController = keyguardStyleClockControllerImpl;
        this.keyguardStyleWidgetUIController = keyguardStyleWidgetUIControllerImpl;
    }

    public final void start() {
        Handler handler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
        NotificationTrackHelper companion = NotificationTrackHelper.Companion.getInstance();
        Context context = this.context;
        handler.getClass();
        companion.init(context, handler);
        NotificationTrackLinkHelper.Companion.getInstance().init(this.context, handler, this.pipeline, this.initController, this.statusBarService);
        WidgetInfoUploadHelper.Companion.getInstance().init(this.context, handler, this.keyguardStyleClockController, this.keyguardStyleWidgetUIController);
        CustomRowTrackLinkHelper.Companion.getInstance().init(this.context, handler);
    }
}
