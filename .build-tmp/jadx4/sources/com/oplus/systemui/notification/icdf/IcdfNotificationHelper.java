package com.oplus.systemui.notification.icdf;

import android.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.android.internal.widget.NotificationActionListLayout;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.oplus.synergynotificationsdk.icdf.IcdfNotificationManagerImpl;
import com.oplus.systemui.notification.icdf.helper.ICDFConnectionSwitchState;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.ArrayIteratorKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationHelper {
    public static final Companion Companion = new Companion();
    public static final String TAG = "icdfSmartConnection";
    public HeadsUpManager headsUpManager;
    public IcdfBuilder icdfBuilder;
    public NotificationRemoteInputManager notificationRemoteInputManager;
    public Lazy stackScrollLayoutControllerLazy;
    public StatusBarRemoteInputCallback statusBarRemoteInputCallback;
    private final CopyOnWriteArraySet<IcdfNotificationManagerImpl.Invoker> addNotificationInvokers = new CopyOnWriteArraySet<>();
    private final HashSet<IcdfNotificationManagerImpl.Invoker> deleteNotificationInvokers = new HashSet<>();
    private final HashSet<IcdfNotificationManagerImpl.Invoker> updateRankingInvokers = new HashSet<>();
    private final HashSet<IcdfNotificationManagerImpl.Invoker> errorInvokers = new HashSet<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void alert(int i) {
        Iterator<IcdfNotificationManagerImpl.Invoker> it = this.errorInvokers.iterator();
        while (it.hasNext()) {
            it.next().error(i);
        }
    }

    public final boolean canSyncNotification() {
        ICDFConnectionSwitchState iCDFConnectionSwitchState = (ICDFConnectionSwitchState) DependencyEx.sDependency.getDependency(ICDFConnectionSwitchState.class);
        if (iCDFConnectionSwitchState.getConnectionPcSwitchOn() || iCDFConnectionSwitchState.getConnectionPadSwitchOn()) {
            return !this.addNotificationInvokers.isEmpty();
        }
        return false;
    }

    public final View getActionButton(NotificationContentView notificationContentView, int i) {
        NotificationActionListLayout notificationActionListLayoutFindViewById = notificationContentView.getExpandedChild().findViewById(R.id.alertTitle);
        if (notificationActionListLayoutFindViewById == null || i >= notificationActionListLayoutFindViewById.getChildCount()) {
            return null;
        }
        return notificationActionListLayoutFindViewById.getChildAt(i);
    }

    public final HeadsUpManager getHeadsUpManager() {
        HeadsUpManager headsUpManager = this.headsUpManager;
        if (headsUpManager != null) {
            return headsUpManager;
        }
        return null;
    }

    public final IcdfBuilder getIcdfBuilder() {
        IcdfBuilder icdfBuilder = this.icdfBuilder;
        if (icdfBuilder != null) {
            return icdfBuilder;
        }
        return null;
    }

    public final NotificationRemoteInputManager getNotificationRemoteInputManager() {
        NotificationRemoteInputManager notificationRemoteInputManager = this.notificationRemoteInputManager;
        if (notificationRemoteInputManager != null) {
            return notificationRemoteInputManager;
        }
        return null;
    }

    public final Lazy getStackScrollLayoutControllerLazy() {
        Lazy lazy = this.stackScrollLayoutControllerLazy;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final StatusBarRemoteInputCallback getStatusBarRemoteInputCallback() {
        StatusBarRemoteInputCallback statusBarRemoteInputCallback = this.statusBarRemoteInputCallback;
        if (statusBarRemoteInputCallback != null) {
            return statusBarRemoteInputCallback;
        }
        return null;
    }

    public final boolean onActionClicked(NotificationEntry notificationEntry, int i) {
        View actionButton = getActionButton(notificationEntry.getRow().getPrivateLayout(), i);
        return actionButton != null && actionButton.performClick();
    }

    public final void onAddNotification(NotificationEntry notificationEntry, int i, boolean z, boolean z2, IcdfNotificationManagerImpl.Invoker invoker) {
        if (!((ICDFConnectionSwitchState) DependencyEx.sDependency.getDependency(ICDFConnectionSwitchState.class)).getConnectionPcSwitchOn() && !((ICDFConnectionSwitchState) DependencyEx.sDependency.getDependency(ICDFConnectionSwitchState.class)).getConnectionPadSwitchOn()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "icdfSmartConnection-->don't send notification to PC due to connection switch off.");
                return;
            }
            return;
        }
        if (invoker == null && this.addNotificationInvokers.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "icdfSmartConnection-->smart_connection_notification_sync is in off state");
                return;
            }
            return;
        }
        IcdfNotificationManagerOuterClass$IcdfNotification icdfNotificationManagerOuterClass$IcdfNotificationCreateIcdfNotification = getIcdfBuilder().createIcdfNotification(notificationEntry, i, z, z2);
        if (invoker != null) {
            invoker.addNotification(icdfNotificationManagerOuterClass$IcdfNotificationCreateIcdfNotification);
            return;
        }
        Iterator<IcdfNotificationManagerImpl.Invoker> it = this.addNotificationInvokers.iterator();
        while (it.hasNext()) {
            it.next().addNotification(icdfNotificationManagerOuterClass$IcdfNotificationCreateIcdfNotification);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "icdfSmartConnection-->send notification to PC.");
        }
    }

    public final void onConsumerDelete(NotificationEntry notificationEntry) {
        ((NotificationStackScrollLayoutController) getStackScrollLayoutControllerLazy().get()).getNotificationListContainer().getSwipeActionHelper().dismiss(notificationEntry.getRow(), 0.0f);
    }

    public final void onDeleteNotification(String str) {
        Iterator<IcdfNotificationManagerImpl.Invoker> it = this.deleteNotificationInvokers.iterator();
        while (it.hasNext()) {
            it.next().deleteNotification(str);
        }
    }

    public final void onHeadsUpUnpinned(String str) {
        getHeadsUpManager().removeNotification(str, true, "icdf onHeadsUpUnpinned");
    }

    public final void onNotificationClicked(NotificationEntry notificationEntry) {
        View.OnClickListener clickerListener;
        View row = notificationEntry.getRow();
        if (row == null || (clickerListener = row.getClickerListener()) == null) {
            return;
        }
        notificationEntry.getEx().setClickFromIcdf(true);
        clickerListener.onClick(row);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x002d  */
    public final boolean onReplyMessage(NotificationEntry notificationEntry, String str, int i) {
        boolean zActivateRemoteInput;
        RemoteInput[] remoteInputArr;
        RemoteInput remoteInput;
        Notification.Action[] actionArr = notificationEntry.getSbn().getNotification().actions;
        if (actionArr != null) {
            int length = actionArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                View actionButton = getActionButton(notificationEntry.getRow().getPrivateLayout(), i2);
                PendingIntent pendingIntent = actionArr[i2].actionIntent;
                if ((getStatusBarRemoteInputCallback().mDisabled2 & 4) == 0 || actionButton == null) {
                    zActivateRemoteInput = false;
                } else {
                    Object tag = actionButton.getTag(R.id.search_mag_icon);
                    RemoteInput remoteInput2 = null;
                    if (tag instanceof Object[]) {
                        Object[] objArr = (Object[]) tag;
                        for (Object obj : objArr) {
                            if (!(obj instanceof RemoteInput)) {
                                objArr = null;
                                break;
                            }
                        }
                        remoteInputArr = (RemoteInput[]) objArr;
                    } else {
                        remoteInputArr = null;
                    }
                    if (remoteInputArr == null) {
                        zActivateRemoteInput = false;
                    } else {
                        Iterator it = ArrayIteratorKt.iterator(remoteInputArr);
                        while (true) {
                            remoteInput = remoteInput2;
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                remoteInput2 = (RemoteInput) it.next();
                                if (remoteInput2.getAllowFreeFormInput()) {
                                }
                            }
                        }
                        if (remoteInput == null) {
                            zActivateRemoteInput = false;
                        } else {
                            zActivateRemoteInput = getNotificationRemoteInputManager().activateRemoteInput(actionButton, remoteInputArr, remoteInput, pendingIntent, (NotificationEntry.EditedSuggestionInfo) null);
                        }
                    }
                }
                if (zActivateRemoteInput) {
                    replyMessage(notificationEntry.getRow().getPrivateLayout().getExpandedRemoteInput(), str);
                    return true;
                }
            }
        }
        return false;
    }

    public final void removeInvoker(int i, IcdfNotificationManagerImpl.Invoker invoker) {
        if (i == 0) {
            this.addNotificationInvokers.remove(invoker);
            return;
        }
        if (i == 1) {
            this.deleteNotificationInvokers.remove(invoker);
        } else if (i == 2) {
            this.updateRankingInvokers.remove(invoker);
        } else {
            if (i != 3) {
                return;
            }
            this.errorInvokers.remove(invoker);
        }
    }

    public final void replyMessage(RemoteInputView remoteInputView, String str) {
        ((EditText) remoteInputView.getChildAt(0)).setText(str);
        View viewFindViewById = remoteInputView.findViewById(2131364882);
        if (viewFindViewById != null) {
            viewFindViewById.performClick();
        }
    }

    public final void setHeadsUpManager(HeadsUpManager headsUpManager) {
        this.headsUpManager = headsUpManager;
    }

    public final void setIcdfBuilder(IcdfBuilder icdfBuilder) {
        this.icdfBuilder = icdfBuilder;
    }

    public final void setInvoker(int i, IcdfNotificationManagerImpl.Invoker invoker) {
        if (i == 0) {
            this.addNotificationInvokers.add(invoker);
            return;
        }
        if (i == 1) {
            this.deleteNotificationInvokers.add(invoker);
        } else if (i == 2) {
            this.updateRankingInvokers.add(invoker);
        } else {
            if (i != 3) {
                return;
            }
            this.errorInvokers.add(invoker);
        }
    }

    public final void setNotificationRemoteInputManager(NotificationRemoteInputManager notificationRemoteInputManager) {
        this.notificationRemoteInputManager = notificationRemoteInputManager;
    }

    public final void setStackScrollLayoutControllerLazy(Lazy lazy) {
        this.stackScrollLayoutControllerLazy = lazy;
    }

    public final void setStatusBarRemoteInputCallback(StatusBarRemoteInputCallback statusBarRemoteInputCallback) {
        this.statusBarRemoteInputCallback = statusBarRemoteInputCallback;
    }

    public final void updateRanking(Map<String, Integer> map) {
        Iterator<IcdfNotificationManagerImpl.Invoker> it = this.updateRankingInvokers.iterator();
        while (it.hasNext()) {
            it.next().updateRanking(map);
        }
    }
}
