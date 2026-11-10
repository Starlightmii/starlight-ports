package com.oplusos.systemui.common.observer;

import android.app.Application;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ObserverDispatcher {
    public static ObserverDispatcher sInstance;
    public Context mAppContext;
    public Handler mHandler;
    public ArrayList mObservers;

    public static synchronized ObserverDispatcher getInstance() {
        try {
            if (sInstance == null) {
                ObserverDispatcher observerDispatcher = new ObserverDispatcher();
                observerDispatcher.mHandler = SystemUIThreadUtil.getWorkHandler();
                observerDispatcher.mObservers = new ArrayList();
                VarHandle.storeStoreFence();
                sInstance = observerDispatcher;
            }
        } catch (Throwable th) {
            throw th;
        }
        return sInstance;
    }

    public final void init(Context context) {
        Log.i("ObserverDispatcher", "init context: " + context);
        Context context2 = this.mAppContext;
        if (context2 == null || !(context2 instanceof Application)) {
            if (context instanceof Application) {
                this.mAppContext = context;
            } else {
                this.mAppContext = context.getApplicationContext();
            }
        }
    }

    public final void registerObserver(Uri uri, ContentObserver contentObserver, int i) {
        Handler handler = this.mHandler;
        ObserverDispatcher$$ExternalSyntheticLambda1 observerDispatcher$$ExternalSyntheticLambda1 = new ObserverDispatcher$$ExternalSyntheticLambda1();
        observerDispatcher$$ExternalSyntheticLambda1.f$0 = this;
        observerDispatcher$$ExternalSyntheticLambda1.f$1 = uri;
        observerDispatcher$$ExternalSyntheticLambda1.f$2 = contentObserver;
        observerDispatcher$$ExternalSyntheticLambda1.f$3 = i;
        VarHandle.storeStoreFence();
        handler.post(observerDispatcher$$ExternalSyntheticLambda1);
    }

    public final void unregisterObserver(ContentObserver contentObserver) {
        Handler handler = this.mHandler;
        ObserverDispatcher$$ExternalSyntheticLambda0 observerDispatcher$$ExternalSyntheticLambda0 = new ObserverDispatcher$$ExternalSyntheticLambda0();
        observerDispatcher$$ExternalSyntheticLambda0.f$0 = this;
        observerDispatcher$$ExternalSyntheticLambda0.f$1 = contentObserver;
        VarHandle.storeStoreFence();
        handler.post(observerDispatcher$$ExternalSyntheticLambda0);
    }

    public final void registerObserver(Uri uri, ContentObserver contentObserver) {
        registerObserver(uri, contentObserver, -2);
    }
}
