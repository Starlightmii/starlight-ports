package com.oplus.systemui.statusbar.seeding.livealert;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.chips.screenrecord.domain.model.ScreenRecordChipModel;
import com.android.systemui.statusbar.chips.ui.model.ChipCardModel;
import com.android.systemui.statusbar.chips.ui.view.OplusEndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.seedling.ChipCardModelFactory;
import com.oplus.systemui.statusbar.seeding.OplusLiveAlertViewModel;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusChipCardModelFactory extends ChipCardModelFactory {
    private final Context context;
    private final EndMediaProjectionDialogHelper endMediaProjectionDialogHelper;
    private final OplusEndMediaProjectionDialogHelper oplusEndMediaProjectionDialogHelper;

    public OplusChipCardModelFactory(Context context, EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, OplusEndMediaProjectionDialogHelper oplusEndMediaProjectionDialogHelper) {
        this.context = context;
        this.endMediaProjectionDialogHelper = endMediaProjectionDialogHelper;
        this.oplusEndMediaProjectionDialogHelper = oplusEndMediaProjectionDialogHelper;
    }

    public ChipCardModel endCastScreenToOtherDevice(ProjectionChipModel.Projecting projecting, Function1 function1) {
        String hostPackage = projecting.getProjectionState().getHostPackage();
        Context context = this.context;
        OplusLiveAlertViewModel.OplusChipType oplusChipType = OplusLiveAlertViewModel.OplusChipType.CastToOtherDevice;
        Intent intent = new Intent();
        intent.setAction(LiveAlertService.LIVE_ALERT_SERVICE_ACTION);
        intent.putExtra(LiveAlertService.EXTRA_CHIP_TYPE, oplusChipType);
        ChipStopAction chipStopAction = ChipStopAction.END_CAST_SCREEN_TO_OTHER_DEVICE;
        intent.putExtra(LiveAlertService.EXTRA_STOP_ID, chipStopAction.getStopAction());
        intent.putExtra(LiveAlertService.EXTRA_PACKAGE_NAME, hostPackage);
        return new ChipCardModel(2131233774, 2131952566, 2131952585, 2131952577, hostPackage, (String) null, PendingIntent.getService(context, 2, intent, 201326592), (Notification.Action) null, (Notification.Action) null, new Pair(chipStopAction.getStopAction(), function1));
    }

    public ChipCardModel endGenericCastToOtherDevice(String str, String str2, Function1 function1) {
        Context context = this.context;
        OplusLiveAlertViewModel.OplusChipType oplusChipType = OplusLiveAlertViewModel.OplusChipType.CastToOtherDevice;
        Intent intent = new Intent();
        intent.setAction(LiveAlertService.LIVE_ALERT_SERVICE_ACTION);
        intent.putExtra(LiveAlertService.EXTRA_CHIP_TYPE, oplusChipType);
        ChipStopAction chipStopAction = ChipStopAction.END_GENERIC_CAST_TO_OTHER_DEVICE;
        intent.putExtra(LiveAlertService.EXTRA_STOP_ID, chipStopAction.getStopAction());
        intent.putExtra(LiveAlertService.EXTRA_PACKAGE_NAME, str);
        return new ChipCardModel(2131233774, 2131952566, 2131952585, 2131952577, str, (String) null, PendingIntent.getService(context, 2, intent, 201326592), (Notification.Action) null, (Notification.Action) null, new Pair(chipStopAction.getStopAction(), function1));
    }

    public ChipCardModel endGenericShareToApp(ProjectionChipModel.Projecting projecting, Function1 function1) {
        String hostPackage = projecting.getProjectionState().getHostPackage();
        Context context = this.context;
        OplusLiveAlertViewModel.OplusChipType oplusChipType = OplusLiveAlertViewModel.OplusChipType.ShareToApp;
        Intent intent = new Intent();
        intent.setAction(LiveAlertService.LIVE_ALERT_SERVICE_ACTION);
        intent.putExtra(LiveAlertService.EXTRA_CHIP_TYPE, oplusChipType);
        ChipStopAction chipStopAction = ChipStopAction.END_GENERIC_SHARE_TO_APP;
        intent.putExtra(LiveAlertService.EXTRA_STOP_ID, chipStopAction.getStopAction());
        intent.putExtra(LiveAlertService.EXTRA_PACKAGE_NAME, hostPackage);
        return new ChipCardModel(2131234415, 2131952568, 2131956297, 2131956281, hostPackage, (String) null, PendingIntent.getService(context, 1, intent, 201326592), (Notification.Action) null, (Notification.Action) null, new Pair(chipStopAction.getStopAction(), function1));
    }

    public ChipCardModel endScreenRecording(ScreenRecordChipModel.Recording recording, Function1 function1) {
        Context context = this.context;
        OplusLiveAlertViewModel.OplusChipType oplusChipType = OplusLiveAlertViewModel.OplusChipType.ScreenRecord;
        Intent intent = new Intent();
        intent.setAction(LiveAlertService.LIVE_ALERT_SERVICE_ACTION);
        intent.putExtra(LiveAlertService.EXTRA_CHIP_TYPE, oplusChipType);
        ChipStopAction chipStopAction = ChipStopAction.END_SCREEN_RECORDING;
        intent.putExtra(LiveAlertService.EXTRA_STOP_ID, chipStopAction.getStopAction());
        return new ChipCardModel(2131234497, 2131952567, 2131955909, 2131956118, recording.getHostPackage(), (String) null, PendingIntent.getService(context, 0, intent, 201326592), (Notification.Action) null, (Notification.Action) null, new Pair(chipStopAction.getStopAction(), function1));
    }

    public ChipCardModel endShareScreenToApp(ProjectionChipModel.Projecting projecting, Function1 function1) {
        String hostPackage = projecting.getProjectionState().getHostPackage();
        Context context = this.context;
        OplusLiveAlertViewModel.OplusChipType oplusChipType = OplusLiveAlertViewModel.OplusChipType.ShareToApp;
        Intent intent = new Intent();
        intent.setAction(LiveAlertService.LIVE_ALERT_SERVICE_ACTION);
        intent.putExtra(LiveAlertService.EXTRA_CHIP_TYPE, oplusChipType);
        ChipStopAction chipStopAction = ChipStopAction.END_SHARE_SCREEN_TO_APP;
        intent.putExtra(LiveAlertService.EXTRA_STOP_ID, chipStopAction.getStopAction());
        intent.putExtra(LiveAlertService.EXTRA_PACKAGE_NAME, hostPackage);
        return new ChipCardModel(2131234415, 2131952568, 2131956297, 2131956281, hostPackage, (String) null, PendingIntent.getService(context, 1, intent, 201326592), (Notification.Action) null, (Notification.Action) null, new Pair(chipStopAction.getStopAction(), function1));
    }
}
