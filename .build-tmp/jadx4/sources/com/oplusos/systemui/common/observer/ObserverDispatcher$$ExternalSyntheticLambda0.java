package com.oplusos.systemui.common.observer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ObserverDispatcher$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ ObserverDispatcher f$0;
    public /* synthetic */ ContentObserver f$1;

    @Override // java.lang.Runnable
    public final void run() {
        ObserverDispatcher observerDispatcher = this.f$0;
        ContentObserver contentObserver = this.f$1;
        Context context = observerDispatcher.mAppContext;
        if (context == null) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        synchronized (observerDispatcher.mObservers) {
            try {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = observerDispatcher.mObservers;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    UriObserver uriObserver = (UriObserver) obj;
                    if (!uriObserver.unregisterObserver(contentResolver, contentObserver)) {
                        arrayList.add(uriObserver);
                    }
                }
                observerDispatcher.mObservers.removeAll(arrayList);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
