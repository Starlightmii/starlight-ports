package com.oplus.systemui.notification.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import android.util.Log;
import androidx.activity.result.ActivityResultRegistry$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.settingslib.metadata.UtilsKt$;
import com.android.systemui.CoreStartable;
import com.android.systemui.DependencyEx;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusStorageNotification implements CoreStartable {
    public final Context mContext;
    public final AnonymousClass1 mDoUnmountReceiver;
    public final NotificationManager mNotificationManager;
    public final AnonymousClass1 mReadOnlyReceiver;
    public final StorageManager mStorageManager;
    public final AnonymousClass1 mStorageStateReceiver;
    public final BroadcastDispatcher mBroadcastDispatcher = (BroadcastDispatcher) DependencyEx.sDependency.getDependency(BroadcastDispatcher.class);
    public final AnonymousClass3 mHandler = new Handler() { // from class: com.oplus.systemui.notification.util.OplusStorageNotification.3
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            PendingIntent activity;
            Object obj = message.obj;
            if (!(obj instanceof StorageVolume)) {
                LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "no StorageVolume");
                return;
            }
            StorageVolume storageVolume = (StorageVolume) obj;
            int i = message.what;
            OplusStorageNotification oplusStorageNotification = OplusStorageNotification.this;
            switch (i) {
                case 1:
                    if (oplusStorageNotification.isSd(storageVolume)) {
                        Notification.Builder builder = oplusStorageNotification.getBuilder(oplusStorageNotification.mContext.getString(2131953187), oplusStorageNotification.mContext.getString(2131953186), 2131236037);
                        builder.setOngoing(true);
                        oplusStorageNotification.mNotificationManager.notifyAsUser(storageVolume.getId(), 1, builder.build(), UserHandle.ALL);
                    } else if (!oplusStorageNotification.shouldSkipUsbStorageNotification(storageVolume)) {
                        Notification.Builder builder2 = oplusStorageNotification.getBuilder(oplusStorageNotification.mContext.getString(2131953191), oplusStorageNotification.mContext.getString(2131953190), 2131236039);
                        builder2.setOngoing(true);
                        oplusStorageNotification.mNotificationManager.notifyAsUser(storageVolume.getId(), 2, builder2.build(), UserHandle.ALL);
                    }
                    break;
                case 2:
                    if (oplusStorageNotification.isSd(storageVolume)) {
                        oplusStorageNotification.onVolumeReadOnlySdcard(storageVolume);
                    } else if (!oplusStorageNotification.shouldSkipUsbStorageNotification(storageVolume)) {
                        Notification.Builder builder3 = oplusStorageNotification.getBuilder(oplusStorageNotification.mContext.getString(2131955295), oplusStorageNotification.mContext.getString(2131955294), 2131236039);
                        builder3.setAutoCancel(true);
                        oplusStorageNotification.mNotificationManager.notifyAsUser(storageVolume.getId(), 2, builder3.build(), UserHandle.ALL);
                    }
                    break;
                case 3:
                    if (oplusStorageNotification.isSd(storageVolume)) {
                        Notification.Builder builder4 = oplusStorageNotification.getBuilder(oplusStorageNotification.mContext.getString(2131953189), oplusStorageNotification.mContext.getString(2131953188), 2131236037);
                        builder4.setAutoCancel(true);
                        VolumeInfo volumeInfoFindVolumeById = oplusStorageNotification.mStorageManager.findVolumeById(storageVolume.getId());
                        if (volumeInfoFindVolumeById == null) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "build format intent wrong, vol is null");
                            activity = null;
                        } else {
                            Intent intentM = UtilsKt$.ExternalSyntheticOutline0.m("com.android.settings.MEDIA_FORMAT", "com.android.settings");
                            intentM.putExtra("diskId", volumeInfoFindVolumeById.getDiskId());
                            intentM.putExtra("SD_TYPE", 1);
                            activity = PendingIntent.getActivity(oplusStorageNotification.mContext, 0, intentM, 67108864);
                        }
                        builder4.setContentIntent(activity);
                        oplusStorageNotification.mNotificationManager.notifyAsUser(storageVolume.getId(), 1, builder4.build(), UserHandle.ALL);
                    } else if (!oplusStorageNotification.shouldSkipUsbStorageNotification(storageVolume)) {
                        Notification.Builder builder5 = oplusStorageNotification.getBuilder(oplusStorageNotification.mContext.getString(2131953193), oplusStorageNotification.mContext.getString(2131953192), 2131236038);
                        builder5.setAutoCancel(true);
                        oplusStorageNotification.mNotificationManager.notifyAsUser(storageVolume.getId(), 2, builder5.build(), UserHandle.ALL);
                    }
                    break;
                case 4:
                    if (oplusStorageNotification.isSd(storageVolume)) {
                        oplusStorageNotification.mNotificationManager.cancelAsUser(storageVolume.getId(), 1, UserHandle.ALL);
                    } else if (!oplusStorageNotification.shouldSkipUsbStorageNotification(storageVolume)) {
                        Notification.Builder builder6 = oplusStorageNotification.getBuilder(oplusStorageNotification.mContext.getString(2131955254), oplusStorageNotification.mContext.getString(2131955297), 2131236040);
                        builder6.setOngoing(true);
                        Intent intent = new Intent("com.oplus.storage.unmount");
                        intent.setPackage(oplusStorageNotification.mContext.getPackageName());
                        intent.putExtra("android.os.storage.extra.STORAGE_VOLUME", storageVolume);
                        builder6.setContentIntent(PendingIntent.getBroadcast(oplusStorageNotification.mContext, storageVolume.getId().hashCode(), intent, 335544320));
                        oplusStorageNotification.mNotificationManager.notifyAsUser(storageVolume.getId(), 2, builder6.build(), UserHandle.ALL);
                    }
                    break;
                case 5:
                    oplusStorageNotification.mNotificationManager.cancelAsUser(storageVolume.getId(), oplusStorageNotification.isSd(storageVolume) ? 1 : 2, UserHandle.ALL);
                    break;
                case 6:
                    oplusStorageNotification.mNotificationManager.cancelAsUser(storageVolume.getId(), oplusStorageNotification.isSd(storageVolume) ? 1 : 2, UserHandle.ALL);
                    break;
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                    oplusStorageNotification.mNotificationManager.cancelAsUser(storageVolume.getId(), oplusStorageNotification.isSd(storageVolume) ? 1 : 2, UserHandle.ALL);
                    break;
                case 8:
                    oplusStorageNotification.mNotificationManager.cancelAsUser(storageVolume.getId(), oplusStorageNotification.isSd(storageVolume) ? 1 : 2, UserHandle.ALL);
                    break;
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v3, types: [com.oplus.systemui.notification.util.OplusStorageNotification$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.oplus.systemui.notification.util.OplusStorageNotification$1] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.oplus.systemui.notification.util.OplusStorageNotification$3] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.oplus.systemui.notification.util.OplusStorageNotification$1] */
    public OplusStorageNotification(Context context, NotificationManager notificationManager, StorageManager storageManager) {
        final int i = 0;
        this.mDoUnmountReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.notification.util.OplusStorageNotification.1
            public final /* synthetic */ OplusStorageNotification this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i) {
                    case 0:
                        StorageVolume storageVolume = (StorageVolume) intent.getParcelableExtra("android.os.storage.extra.STORAGE_VOLUME");
                        if (storageVolume == null) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "receive unmount action for null volume");
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "receive unmount action for :" + storageVolume);
                            if (this.this$0.shouldSkipUsbStorageNotification(storageVolume)) {
                                LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "skip unmount action for hidden volume: " + storageVolume.getId());
                            } else {
                                this.this$0.mNotificationManager.cancelAsUser(storageVolume.getId(), this.this$0.isSd(storageVolume) ? 1 : 2, UserHandle.ALL);
                                final String id = storageVolume.getId();
                                new Thread() { // from class: com.oplus.systemui.notification.util.OplusStorageNotification.1.1
                                    @Override // java.lang.Thread, java.lang.Runnable
                                    public final void run() {
                                        try {
                                            AnonymousClass1.this.this$0.mStorageManager.unmount(id);
                                        } catch (IllegalArgumentException | IllegalStateException e) {
                                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "unmount wrong!" + e.getMessage());
                                        } catch (Exception e2) {
                                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "unmount exception wrong!" + e2.getMessage());
                                        }
                                    }
                                }.start();
                            }
                        }
                        break;
                    case 1:
                        String action = intent.getAction();
                        String stringExtra = intent.getStringExtra("storage_type");
                        String stringExtra2 = intent.getStringExtra("package_name");
                        boolean zEquals = "sdcard".equals(stringExtra);
                        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("ReadOnlyReceiver receive action:", action, ", type:", stringExtra, ", caller:");
                        sbM.append(stringExtra2);
                        LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", sbM.toString());
                        if (action.equals("oplus.intent.action.BAD_STORAGE_NOTICE") && zEquals) {
                            for (StorageVolume storageVolume2 : this.this$0.mStorageManager.getStorageVolumes()) {
                                if (this.this$0.isSd(storageVolume2)) {
                                    this.this$0.onVolumeReadOnlySdcard(storageVolume2);
                                    break;
                                }
                            }
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "Receiver ReadOnly from other app, but no sdcard in SM");
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "only support sdcard bad storage notification from other app");
                        }
                        break;
                    default:
                        String action2 = intent.getAction();
                        StorageVolume storageVolume3 = (StorageVolume) intent.getParcelableExtra("android.os.storage.extra.STORAGE_VOLUME");
                        if (storageVolume3 == null) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "volume is null!");
                        } else if ("/storage/sdcard0".equals(storageVolume3.getPath())) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "inner sdcard!");
                        } else if (storageVolume3.isEmulated()) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "Emulated storage!");
                        } else {
                            StringBuilder sbM2 = ActivityResultRegistry$.ExternalSyntheticOutline0.m("receive action = ", action2, ", volume = ");
                            sbM2.append(storageVolume3.dump());
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", sbM2.toString());
                            if ("android.intent.action.MEDIA_CHECKING".equals(action2)) {
                                obtainMessage(1, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_MOUNTED_RO".equals(action2)) {
                                obtainMessage(2, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_UNMOUNTABLE".equals(action2)) {
                                obtainMessage(3, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_MOUNTED".equals(action2)) {
                                obtainMessage(4, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action2)) {
                                obtainMessage(5, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_REMOVED".equals(action2)) {
                                obtainMessage(6, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_BAD_REMOVAL".equals(action2)) {
                                obtainMessage(7, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_EJECT".equals(action2)) {
                                obtainMessage(8, storageVolume3).sendToTarget();
                            }
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mReadOnlyReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.notification.util.OplusStorageNotification.1
            public final /* synthetic */ OplusStorageNotification this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        StorageVolume storageVolume = (StorageVolume) intent.getParcelableExtra("android.os.storage.extra.STORAGE_VOLUME");
                        if (storageVolume == null) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "receive unmount action for null volume");
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "receive unmount action for :" + storageVolume);
                            if (this.this$0.shouldSkipUsbStorageNotification(storageVolume)) {
                                LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "skip unmount action for hidden volume: " + storageVolume.getId());
                            } else {
                                this.this$0.mNotificationManager.cancelAsUser(storageVolume.getId(), this.this$0.isSd(storageVolume) ? 1 : 2, UserHandle.ALL);
                                final String id = storageVolume.getId();
                                new Thread() { // from class: com.oplus.systemui.notification.util.OplusStorageNotification.1.1
                                    @Override // java.lang.Thread, java.lang.Runnable
                                    public final void run() {
                                        try {
                                            AnonymousClass1.this.this$0.mStorageManager.unmount(id);
                                        } catch (IllegalArgumentException | IllegalStateException e) {
                                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "unmount wrong!" + e.getMessage());
                                        } catch (Exception e2) {
                                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "unmount exception wrong!" + e2.getMessage());
                                        }
                                    }
                                }.start();
                            }
                        }
                        break;
                    case 1:
                        String action = intent.getAction();
                        String stringExtra = intent.getStringExtra("storage_type");
                        String stringExtra2 = intent.getStringExtra("package_name");
                        boolean zEquals = "sdcard".equals(stringExtra);
                        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("ReadOnlyReceiver receive action:", action, ", type:", stringExtra, ", caller:");
                        sbM.append(stringExtra2);
                        LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", sbM.toString());
                        if (action.equals("oplus.intent.action.BAD_STORAGE_NOTICE") && zEquals) {
                            for (StorageVolume storageVolume2 : this.this$0.mStorageManager.getStorageVolumes()) {
                                if (this.this$0.isSd(storageVolume2)) {
                                    this.this$0.onVolumeReadOnlySdcard(storageVolume2);
                                    break;
                                }
                            }
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "Receiver ReadOnly from other app, but no sdcard in SM");
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "only support sdcard bad storage notification from other app");
                        }
                        break;
                    default:
                        String action2 = intent.getAction();
                        StorageVolume storageVolume3 = (StorageVolume) intent.getParcelableExtra("android.os.storage.extra.STORAGE_VOLUME");
                        if (storageVolume3 == null) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "volume is null!");
                        } else if ("/storage/sdcard0".equals(storageVolume3.getPath())) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "inner sdcard!");
                        } else if (storageVolume3.isEmulated()) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "Emulated storage!");
                        } else {
                            StringBuilder sbM2 = ActivityResultRegistry$.ExternalSyntheticOutline0.m("receive action = ", action2, ", volume = ");
                            sbM2.append(storageVolume3.dump());
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", sbM2.toString());
                            if ("android.intent.action.MEDIA_CHECKING".equals(action2)) {
                                obtainMessage(1, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_MOUNTED_RO".equals(action2)) {
                                obtainMessage(2, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_UNMOUNTABLE".equals(action2)) {
                                obtainMessage(3, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_MOUNTED".equals(action2)) {
                                obtainMessage(4, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action2)) {
                                obtainMessage(5, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_REMOVED".equals(action2)) {
                                obtainMessage(6, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_BAD_REMOVAL".equals(action2)) {
                                obtainMessage(7, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_EJECT".equals(action2)) {
                                obtainMessage(8, storageVolume3).sendToTarget();
                            }
                        }
                        break;
                }
            }
        };
        final int i3 = 2;
        this.mStorageStateReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.notification.util.OplusStorageNotification.1
            public final /* synthetic */ OplusStorageNotification this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i3) {
                    case 0:
                        StorageVolume storageVolume = (StorageVolume) intent.getParcelableExtra("android.os.storage.extra.STORAGE_VOLUME");
                        if (storageVolume == null) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "receive unmount action for null volume");
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "receive unmount action for :" + storageVolume);
                            if (this.this$0.shouldSkipUsbStorageNotification(storageVolume)) {
                                LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "skip unmount action for hidden volume: " + storageVolume.getId());
                            } else {
                                this.this$0.mNotificationManager.cancelAsUser(storageVolume.getId(), this.this$0.isSd(storageVolume) ? 1 : 2, UserHandle.ALL);
                                final String id = storageVolume.getId();
                                new Thread() { // from class: com.oplus.systemui.notification.util.OplusStorageNotification.1.1
                                    @Override // java.lang.Thread, java.lang.Runnable
                                    public final void run() {
                                        try {
                                            AnonymousClass1.this.this$0.mStorageManager.unmount(id);
                                        } catch (IllegalArgumentException | IllegalStateException e) {
                                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "unmount wrong!" + e.getMessage());
                                        } catch (Exception e2) {
                                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "unmount exception wrong!" + e2.getMessage());
                                        }
                                    }
                                }.start();
                            }
                        }
                        break;
                    case 1:
                        String action = intent.getAction();
                        String stringExtra = intent.getStringExtra("storage_type");
                        String stringExtra2 = intent.getStringExtra("package_name");
                        boolean zEquals = "sdcard".equals(stringExtra);
                        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("ReadOnlyReceiver receive action:", action, ", type:", stringExtra, ", caller:");
                        sbM.append(stringExtra2);
                        LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", sbM.toString());
                        if (action.equals("oplus.intent.action.BAD_STORAGE_NOTICE") && zEquals) {
                            for (StorageVolume storageVolume2 : this.this$0.mStorageManager.getStorageVolumes()) {
                                if (this.this$0.isSd(storageVolume2)) {
                                    this.this$0.onVolumeReadOnlySdcard(storageVolume2);
                                    break;
                                }
                            }
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "Receiver ReadOnly from other app, but no sdcard in SM");
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "only support sdcard bad storage notification from other app");
                        }
                        break;
                    default:
                        String action2 = intent.getAction();
                        StorageVolume storageVolume3 = (StorageVolume) intent.getParcelableExtra("android.os.storage.extra.STORAGE_VOLUME");
                        if (storageVolume3 == null) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "volume is null!");
                        } else if ("/storage/sdcard0".equals(storageVolume3.getPath())) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "inner sdcard!");
                        } else if (storageVolume3.isEmulated()) {
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "Emulated storage!");
                        } else {
                            StringBuilder sbM2 = ActivityResultRegistry$.ExternalSyntheticOutline0.m("receive action = ", action2, ", volume = ");
                            sbM2.append(storageVolume3.dump());
                            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", sbM2.toString());
                            if ("android.intent.action.MEDIA_CHECKING".equals(action2)) {
                                obtainMessage(1, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_MOUNTED_RO".equals(action2)) {
                                obtainMessage(2, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_UNMOUNTABLE".equals(action2)) {
                                obtainMessage(3, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_MOUNTED".equals(action2)) {
                                obtainMessage(4, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action2)) {
                                obtainMessage(5, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_REMOVED".equals(action2)) {
                                obtainMessage(6, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_BAD_REMOVAL".equals(action2)) {
                                obtainMessage(7, storageVolume3).sendToTarget();
                            } else if ("android.intent.action.MEDIA_EJECT".equals(action2)) {
                                obtainMessage(8, storageVolume3).sendToTarget();
                            }
                        }
                        break;
                }
            }
        };
        this.mContext = context;
        this.mNotificationManager = notificationManager;
        this.mStorageManager = storageManager;
    }

    public final Notification.Builder getBuilder(CharSequence charSequence, CharSequence charSequence2, int i) {
        Notification.Builder category = new Notification.Builder(this.mContext, "DSK").setSmallIcon(i).setContentTitle(charSequence).setTicker(charSequence).setShowWhen(true).setVisibility(1).setCategory("sys");
        if (!TextUtils.isEmpty(charSequence2)) {
            category.setContentText(charSequence2);
        }
        return category;
    }

    public final boolean isSd(StorageVolume storageVolume) {
        boolean zContains;
        VolumeInfo volumeInfoFindVolumeById = this.mStorageManager.findVolumeById(storageVolume.getId());
        if (volumeInfoFindVolumeById == null || volumeInfoFindVolumeById.getDisk() == null) {
            LogUtil.d(LogUtil.TAG_COMMON, "OplusStorageNotification", "can't find Volume or disk");
            zContains = storageVolume.getId().contains("179:");
        } else {
            zContains = volumeInfoFindVolumeById.getDisk().isSd();
        }
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("Storage is sd card :", LogUtil.TAG_COMMON, "OplusStorageNotification", zContains);
        return zContains;
    }

    public final void onVolumeReadOnlySdcard(StorageVolume storageVolume) {
        Notification.Builder builder = getBuilder(this.mContext.getString(2131955258), this.mContext.getString(2131955257), 2131236037);
        builder.setAutoCancel(true);
        this.mNotificationManager.notifyAsUser(storageVolume.getId(), 1, builder.build(), UserHandle.ALL);
    }

    public final boolean shouldSkipUsbStorageNotification(StorageVolume storageVolume) {
        String path;
        VolumeInfo volumeInfoFindVolumeById = this.mStorageManager.findVolumeById(storageVolume.getId());
        if (volumeInfoFindVolumeById == null) {
            return false;
        }
        String fsUuid = volumeInfoFindVolumeById.getFsUuid();
        File path2 = volumeInfoFindVolumeById.getPath();
        if (fsUuid == null || !fsUuid.regionMatches(true, 0, "6f646673", 0, 8)) {
            return (path2 == null || (path = path2.getPath()) == null || !path.toLowerCase(Locale.ROOT).contains("6f646673")) ? false : true;
        }
        return true;
    }

    public final void start() {
        Log.d("OplusStorageNotification", "start: OplusStorageNotifications");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oplus.storage.unmount");
        UserHandle userHandle = UserHandle.ALL;
        this.mBroadcastDispatcher.registerReceiver(this.mDoUnmountReceiver, intentFilter, (Executor) null, userHandle, 2, ProviderUtil.OPLUS_COMPONENT_SAFE);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.MEDIA_CHECKING");
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED_RO");
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter2.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        intentFilter2.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter2.addDataScheme("file");
        this.mContext.registerReceiverAsUser(this.mStorageStateReceiver, userHandle, intentFilter2, ProviderUtil.OPLUS_COMPONENT_SAFE, null, 2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("oplus.intent.action.BAD_STORAGE_NOTICE");
        this.mBroadcastDispatcher.registerReceiver(this.mReadOnlyReceiver, intentFilter3, (Executor) null, userHandle, 2, ProviderUtil.OPLUS_COMPONENT_SAFE);
    }
}
