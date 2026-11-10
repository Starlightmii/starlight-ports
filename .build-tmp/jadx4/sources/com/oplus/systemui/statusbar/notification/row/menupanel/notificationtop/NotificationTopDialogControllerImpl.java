package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.content.Context;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTopDialogControllerImpl extends NotificationTopDialogController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationToppedDialogControllerImpl";
    private NotificationTopItemAdapter adapter;
    private AlertDialog alertDialog;
    private final Context context;
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null));
    private NotificationTopDialogCreator notificationTopDialogCreator;
    private List<NotificationTopDialogItemCreatorImpl.DialogItem> notificationTopDialogItems;
    private final NotificationTopListManage notificationTopListManage;
    private String waitAddNotificationTop;
    private Integer waitAddNotificationTopUid;
    private Set<String> waitRemoveNotificationTopSet;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationTopDialogControllerImpl(Context context) {
        this.context = context;
        this.notificationTopListManage = NotificationTopListManage.Companion.newInstance(context);
    }

    public static final void access$onCancel(NotificationTopDialogControllerImpl notificationTopDialogControllerImpl) {
        notificationTopDialogControllerImpl.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationToppedDialogControllerImpl-->NotificationToppedDialog onCancel");
        }
        notificationTopDialogControllerImpl.alertDialog = null;
        notificationTopDialogControllerImpl.adapter = null;
        notificationTopDialogControllerImpl.waitRemoveNotificationTopSet = null;
        notificationTopDialogControllerImpl.notificationTopDialogItems = null;
        notificationTopDialogControllerImpl.waitAddNotificationTop = null;
        notificationTopDialogControllerImpl.waitAddNotificationTopUid = null;
        notificationTopDialogControllerImpl.notificationTopDialogCreator = null;
    }

    public static final void access$onNegativeOperation(NotificationTopDialogControllerImpl notificationTopDialogControllerImpl) {
        notificationTopDialogControllerImpl.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationToppedDialogControllerImpl-->enter onNegativeOperation");
        }
        AlertDialog alertDialog = notificationTopDialogControllerImpl.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public static final void access$onPositiveOperation(NotificationTopDialogControllerImpl notificationTopDialogControllerImpl) {
        NotificationTopListManage notificationTopListManage;
        notificationTopDialogControllerImpl.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationToppedDialogControllerImpl-->enter onPositiveOperation");
        }
        Set<String> set = notificationTopDialogControllerImpl.waitRemoveNotificationTopSet;
        if ((set == null || true != set.isEmpty()) && (notificationTopListManage = notificationTopDialogControllerImpl.notificationTopListManage) != null) {
            ((NotificationTopListManageImpl) notificationTopListManage).notificationTopBatchOperation(notificationTopDialogControllerImpl.context, notificationTopDialogControllerImpl.waitAddNotificationTop, notificationTopDialogControllerImpl.waitAddNotificationTopUid, notificationTopDialogControllerImpl.waitRemoveNotificationTopSet);
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopDialogController
    public void dismissDialog(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("dismissDialog reason: ", str, "NotificationToppedDialogControllerImpl-->", "SystemUi--Notification");
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopDialogController
    public void showDialog(String str, Integer num) {
        Set<String> notificationTopList;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationToppedDialogControllerImpl-->NotificationToppedDialog show");
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationToppedDialogControllerImpl-->NotificationToppedDialog prepare");
        }
        this.waitRemoveNotificationTopSet = new LinkedHashSet();
        NotificationTopListManage notificationTopListManage = this.notificationTopListManage;
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new prepare.2(this, (notificationTopListManage == null || (notificationTopList = ((NotificationTopListManageImpl) notificationTopListManage).getNotificationTopList()) == null) ? null : CollectionsKt___CollectionsKt.toMutableList(notificationTopList), (Continuation) null), 2);
        this.waitAddNotificationTop = str;
        this.waitAddNotificationTopUid = num;
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.show();
        }
    }
}
