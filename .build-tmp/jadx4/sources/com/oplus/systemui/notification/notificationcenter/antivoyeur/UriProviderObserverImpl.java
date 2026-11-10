package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UriProviderObserverImpl implements UriProviderObserver {
    public static final Companion Companion = new Companion();
    private static final String TAG = "UriProviderObserverImpl";
    private final Lazy handler$delegate = LazyKt__LazyJVMKt.lazy(new UriProviderObserverImpl$$ExternalSyntheticLambda0());
    public boolean mHasRegister;
    private ContentObserver mObserver;
    private final Function1 onChange;
    private final Uri uri;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public UriProviderObserverImpl(Uri uri, Function1 function1) {
        this.uri = uri;
        this.onChange = function1;
    }

    private final Handler getHandler() {
        return (Handler) this.handler$delegate.getValue();
    }

    @Override // com.oplus.systemui.notification.notificationcenter.antivoyeur.UriProviderObserver
    public void register(Context context) {
        unRegister(context);
        if (this.mHasRegister) {
            return;
        }
        if (this.mObserver == null) {
            this.mObserver = new ContentObserver(getHandler()) { // from class: com.oplus.systemui.notification.notificationcenter.antivoyeur.UriProviderObserverImpl.register.1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    super.onChange(z);
                    UriProviderObserverImpl.this.onChange.invoke(Boolean.valueOf(z));
                }
            };
        }
        context.getContentResolver().registerContentObserver(this.uri, false, this.mObserver, -2);
        this.mHasRegister = true;
    }

    @Override // com.oplus.systemui.notification.notificationcenter.antivoyeur.UriProviderObserver
    public void unRegister(Context context) {
        if (this.mHasRegister) {
            ContentObserver contentObserver = this.mObserver;
            if (contentObserver != null) {
                context.getContentResolver().unregisterContentObserver(contentObserver);
            }
            this.mHasRegister = false;
        }
    }
}
