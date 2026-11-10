package com.oplus.systemui.statusbar.icon.data;

import android.content.Context;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.dump.DumpManager;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.statusbar.observer.NotificationPromptModeObserver;
import com.oplus.systemui.statusbar.proxy.StatusBarSettingsValueProxy;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.FocusModeSettingsObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaRepository implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationIconAreaRepository";
    private final IObserverListener childModeListener;
    private final Context context;
    private final IObserverListener focusModeListener;
    private final MutableStateFlow isChildrenMode;
    private final MutableStateFlow isFocusMode;
    private final StateFlow notificationIconAreaType;
    private final NotificationPromptModeObserver notificationPromptModeObserver;
    private final MutableStateFlow notificationShowMode;
    private final CoroutineScope scope;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationIconAreaRepository(Context context, CoroutineScope coroutineScope, DumpManager dumpManager) {
        this.context = context;
        this.scope = coroutineScope;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(0);
        this.notificationShowMode = MutableStateFlow;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this.isChildrenMode = MutableStateFlow2;
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this.isFocusMode = MutableStateFlow3;
        NotificationPromptModeObserver notificationPromptModeObserver = new NotificationPromptModeObserver() { // from class: com.oplus.systemui.statusbar.icon.data.OplusNotificationIconAreaRepository$notificationPromptModeObserver$1
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusNotificationIconAreaRepository.access$updateNotificationShowMode(this.this$0);
            }
        };
        this.notificationPromptModeObserver = notificationPromptModeObserver;
        OplusNotificationIconAreaRepository$childModeListener$1 oplusNotificationIconAreaRepository$childModeListener$1 = new OplusNotificationIconAreaRepository$childModeListener$1(0);
        oplusNotificationIconAreaRepository$childModeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.childModeListener = oplusNotificationIconAreaRepository$childModeListener$1;
        OplusNotificationIconAreaRepository$childModeListener$1 oplusNotificationIconAreaRepository$childModeListener$2 = new OplusNotificationIconAreaRepository$childModeListener$1(1);
        oplusNotificationIconAreaRepository$childModeListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.focusModeListener = oplusNotificationIconAreaRepository$childModeListener$2;
        this.notificationIconAreaType = FlowKt.stateIn(FlowKt.combine(MutableStateFlow, MutableStateFlow2, MutableStateFlow3, new OplusNotificationIconAreaRepository$notificationIconAreaType$1(4, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), NotificationIconAreaType.NOTIFICATION_SHOW_ICON);
        notificationPromptModeObserver.registerForUserSwitch(context, false);
        MutableStateFlow2.setValue(Boolean.valueOf(ChildrenModeObserver.getInstance().mIsChildrenMode));
        ChildrenModeObserver.getInstance().addListener(oplusNotificationIconAreaRepository$childModeListener$1);
        FocusModeSettingsObserver.getsInstance().addListener(oplusNotificationIconAreaRepository$childModeListener$2);
        dumpManager.registerCriticalDumpable(this);
    }

    public static final void access$updateNotificationShowMode(OplusNotificationIconAreaRepository oplusNotificationIconAreaRepository) {
        int notificationPromptModeState = StatusBarSettingsValueProxy.Companion.getNotificationPromptModeState(oplusNotificationIconAreaRepository.context);
        if (notificationPromptModeState != ((Number) oplusNotificationIconAreaRepository.notificationShowMode.getValue()).intValue()) {
            oplusNotificationIconAreaRepository.notificationShowMode.setValue(Integer.valueOf(notificationPromptModeState));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onChange---setting change mNotificationShowMode = ", oplusNotificationIconAreaRepository.notificationShowMode.getValue(), "OplusNotificationIconAreaRepository-->", "SystemUi--Statusbar");
            }
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusNotificationIconAreaRepository: ");
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("notificationShowMode: ", this.notificationShowMode.getValue(), printWriter);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("isChildrenMode: ", this.isChildrenMode.getValue(), printWriter);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("isFocusMode: ", this.isFocusMode.getValue(), printWriter);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("notificationIconAreaType: ", this.notificationIconAreaType.getValue(), printWriter);
    }

    public final StateFlow getNotificationIconAreaType() {
        return this.notificationIconAreaType;
    }
}
