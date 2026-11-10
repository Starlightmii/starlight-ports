package io.github.andrea_lyz.assistrestore.ui;

import android.app.Application;
import io.github.libxposed.service.XposedService;
import io.github.libxposed.service.XposedServiceHelper;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class App extends Application implements XposedServiceHelper.OnServiceListener {
    private static volatile XposedService service;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final CopyOnWriteArraySet<ServiceStateListener> listeners = new CopyOnWriteArraySet<>();

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(b4.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void dispatch(XposedService xposedService) {
            Iterator it = App.listeners.iterator();
            b4.l.d("iterator(...)", it);
            while (it.hasNext()) {
                ((ServiceStateListener) it.next()).onServiceStateChanged(xposedService);
            }
        }

        public final void addServiceStateListener(ServiceStateListener serviceStateListener, boolean z2) {
            b4.l.e("listener", serviceStateListener);
            App.listeners.add(serviceStateListener);
            if (z2) {
                serviceStateListener.onServiceStateChanged(getService());
            }
        }

        public final XposedService getService() {
            return App.service;
        }

        public final void removeServiceStateListener(ServiceStateListener serviceStateListener) {
            b4.l.e("listener", serviceStateListener);
            App.listeners.remove(serviceStateListener);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public interface ServiceStateListener {
        void onServiceStateChanged(XposedService xposedService);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        XposedServiceHelper.registerListener(this);
    }

    @Override // io.github.libxposed.service.XposedServiceHelper.OnServiceListener
    public void onServiceBind(XposedService xposedService) {
        b4.l.e("service", xposedService);
        service = xposedService;
        Companion.dispatch(xposedService);
    }

    @Override // io.github.libxposed.service.XposedServiceHelper.OnServiceListener
    public void onServiceDied(XposedService xposedService) {
        b4.l.e("service", xposedService);
        if (service == xposedService) {
            service = null;
        }
        Companion.dispatch(service);
    }
}
