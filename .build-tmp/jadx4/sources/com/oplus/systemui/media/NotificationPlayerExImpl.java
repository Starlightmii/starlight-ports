package com.oplus.systemui.media;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.MediaStore;
import com.android.systemui.media.NotificationPlayerEx;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPlayerExImpl extends NotificationPlayerEx implements OplusNotificationEx {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public UserHandle getCallingUserHandle() {
        if (1000 == Binder.getCallingUid()) {
            return new UserHandle(OsBinderCacheUtils.getCurrentUserId());
        }
        UserHandle callingUserHandle = Binder.getCallingUserHandle();
        callingUserHandle.getClass();
        return callingUserHandle;
    }

    public boolean isSilentOrVibrate(AudioManager audioManager) {
        int ringerModeInternal = audioManager.getRingerModeInternal();
        return ringerModeInternal == 0 || ringerModeInternal == 1;
    }

    public boolean needUseSystemUser(UserHandle userHandle, Uri uri) {
        return OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(userHandle.getIdentifier())) && StringsKt__StringsJVMKt.startsWith(uri.toString(), MediaStore.Audio.Media.INTERNAL_CONTENT_URI.toString(), false);
    }

    public void onAcquireWakeLock(PowerManager.WakeLock wakeLock) {
        wakeLock.acquire(10000L);
    }

    public boolean shouldNotificationSoundPlay(AudioManager audioManager, AudioAttributes audioAttributes) {
        int ringerModeInternal = audioManager.getRingerModeInternal();
        return audioManager.shouldNotificationSoundPlay(audioAttributes) && !(ringerModeInternal == 0 || ringerModeInternal == 1);
    }

    public final boolean shouldReleaseWakeLock(boolean z) {
        return z;
    }
}
