package com.oplus.systemui.shutdown;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.DependencyEx;
import com.android.systemui.shutdown.OplusManuallyLockEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusManuallyLockExImpl extends OplusManuallyLockEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusManuallyLockExImpl";
    private Context mContext;
    private ContentObserver mManuallyLockToggleObserver;
    public boolean mNeedsVerifyPwdAfterManuallyLocked;
    private final String lockToggle = "manually_lock_toggle";
    public final int lockOn = 1;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final boolean isManuallyLockedOn() {
        return this.mNeedsVerifyPwdAfterManuallyLocked;
    }

    public final boolean isManuallyLockedOnInternal() {
        Context context = this.mContext;
        return Settings.Secure.getIntForUser(context != null ? context.getContentResolver() : null, this.lockToggle, 0, -2) == this.lockOn;
    }

    public final boolean isSupported() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        return FlavorOneFeatureOption.isFlavorOneDevice();
    }

    public void onCreate(Context context, Handler handler) {
        ContentResolver contentResolver;
        this.mContext = context != null ? context.getApplicationContext() : null;
        this.mNeedsVerifyPwdAfterManuallyLocked = isManuallyLockedOnInternal();
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.shutdown.OplusManuallyLockExImpl.onCreate.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusManuallyLockExImpl oplusManuallyLockExImpl = this;
                oplusManuallyLockExImpl.mNeedsVerifyPwdAfterManuallyLocked = oplusManuallyLockExImpl.isManuallyLockedOnInternal();
                ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mHandler.sendEmptyMessage(5);
            }
        };
        this.mManuallyLockToggleObserver = contentObserver;
        Context context2 = this.mContext;
        if (context2 == null || (contentResolver = context2.getContentResolver()) == null) {
            return;
        }
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(this.lockToggle), false, contentObserver);
    }

    public void setManuallyLockedOn(boolean z) {
        Context context = this.mContext;
        Settings.Secure.putIntForUser(context != null ? context.getContentResolver() : null, this.lockToggle, z ? this.lockOn : 0, -2);
    }
}
