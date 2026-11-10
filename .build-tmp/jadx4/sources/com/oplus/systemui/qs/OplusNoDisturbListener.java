package com.oplus.systemui.qs;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import androidx.databinding.ObservableField;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeSnackBarController;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNoDisturbListener {
    public static final Companion Companion = new Companion();
    private static final OplusNoDisturbListener INSTANCE = new OplusNoDisturbListener();
    public static final String ZEN_MODE_SETTING = "zen_mode";
    private final HashSet<OplusNoDisturbObserver> observers;
    private final NonNullObservableField<Integer> zenMode;
    private final OplusNoDisturbListener$zenModeUpdater$1 zenModeUpdater;
    private final SettingObserver settingObserver = new SettingObserver();
    private final InternalHandler handler = new InternalHandler(Looper.getMainLooper());
    private final AtomicReference<Context> applicationContext = new AtomicReference<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusNoDisturbListener getInstance() {
            return OplusNoDisturbListener.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InternalHandler extends Handler {
        public InternalHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 100) {
                OplusNoDisturbListener.access$notifyObservers(OplusNoDisturbListener.this, ((Integer) message.obj).intValue());
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SettingObserver extends ContentObserver {
        private final AtomicBoolean isRegistered;
        public final Uri zenModeUri;

        public SettingObserver() {
            super(OplusNoDisturbListener.this.handler);
            this.zenModeUri = Settings.Global.getUriFor(OplusNoDisturbListener.ZEN_MODE_SETTING);
            this.isRegistered = new AtomicBoolean(false);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            Uri uri2;
            int zenMode;
            super.onChange(z, uri);
            Context context = (Context) OplusNoDisturbListener.this.applicationContext.get();
            if (context == null || (uri2 = this.zenModeUri) == null || !uri2.equals(uri) || (zenMode = OplusNoDisturbListener.this.getZenMode(context)) == -1) {
                return;
            }
            OplusNoDisturbListener.this.zenMode.set(Integer.valueOf(zenMode));
        }

        public final void register(Context context) {
            if (this.zenModeUri == null || !this.isRegistered.compareAndSet(false, true)) {
                return;
            }
            context.getContentResolver().registerContentObserver(this.zenModeUri, false, this, -2);
            OplusNoDisturbListener.this.zenMode.set(Integer.valueOf(OplusNoDisturbListener.this.getZenMode(context)));
        }

        public final void unregister(Context context) {
            if (this.isRegistered.compareAndSet(true, false)) {
                context.getContentResolver().unregisterContentObserver(this);
            }
        }
    }

    public OplusNoDisturbListener() {
        NonNullObservableField<Integer> nonNullObservableField = new NonNullObservableField<>(-1, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.zenMode = nonNullObservableField;
        this.observers = new HashSet<>();
        OplusNoDisturbListener$zenModeUpdater$1 oplusNoDisturbListener$zenModeUpdater$1 = new OplusNoDisturbListener$zenModeUpdater$1();
        oplusNoDisturbListener$zenModeUpdater$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.zenModeUpdater = oplusNoDisturbListener$zenModeUpdater$1;
        nonNullObservableField.addOnPropertyChangedCallback(oplusNoDisturbListener$zenModeUpdater$1);
    }

    public static final void access$notifyObservers(OplusNoDisturbListener oplusNoDisturbListener, int i) {
        Iterator<OplusNoDisturbObserver> it = oplusNoDisturbListener.observers.iterator();
        while (it.hasNext()) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) it.next();
            if (i == 1) {
                OplusVolumeSnackBarController oplusVolumeSnackBarController = oplusVolumeDialogImpl.mOplusVolumeSnackBarController;
                oplusVolumeSnackBarController.mNeedShowSnackBar = true;
                oplusVolumeSnackBarController.mSnackerShowCounter = 0;
            } else {
                oplusVolumeDialogImpl.getClass();
            }
        }
    }

    public static final OplusNoDisturbListener getInstance() {
        return Companion.getInstance();
    }

    public final void addObserver(Context context, OplusNoDisturbObserver oplusNoDisturbObserver) {
        Context context2;
        if (context != null) {
            this.applicationContext.compareAndSet(null, context.getApplicationContext());
        }
        if (this.observers.isEmpty() && (context2 = this.applicationContext.get()) != null) {
            this.settingObserver.register(context2);
        }
        this.observers.add(oplusNoDisturbObserver);
    }

    public final int getZenMode(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), ZEN_MODE_SETTING, -1);
    }

    public final int getZenModeTemp() {
        return ((Number) this.zenMode.get()).intValue();
    }

    public final void removeObserver(Context context, OplusNoDisturbObserver oplusNoDisturbObserver) {
        Context context2;
        if (context != null) {
            this.applicationContext.compareAndSet(null, context.getApplicationContext());
        }
        this.observers.remove(oplusNoDisturbObserver);
        if (!this.observers.isEmpty() || (context2 = this.applicationContext.get()) == null) {
            return;
        }
        this.settingObserver.unregister(context2);
    }
}
