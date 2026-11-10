package com.oplus.systemui.statusbar.notification.dnd.seedling;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.qs.OplusNoDisturbListener;
import com.oplus.systemui.statusbar.seeding.StartSeedlingService;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DndSeedling {
    public static final Companion Companion = new Companion();
    private static final String DND_EVENT = "DndEvent";
    private static final String KEY_DND_MODE_TYPE = "DndModeType";
    private static final String TAG = "DndSeedling";
    private final Context context;
    public boolean isZenModeOn;
    private final StartSeedlingService startSeedlingService;
    public final Handler workHandler = SystemUIThreadUtil.getWorkHandler();
    private ContentObserver zenModeObserver = new ContentObserver() { // from class: com.oplus.systemui.statusbar.notification.dnd.seedling.DndSeedling$zenModeObserver$1
        {
            super(null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            DndSeedling dndSeedling = this.this$0;
            Handler handler = dndSeedling.workHandler;
            onChange.1 r0 = new onChange.1();
            r0.this$0 = dndSeedling;
            VarHandle.storeStoreFence();
            handler.post(r0);
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DndSeedling(Context context, StartSeedlingService startSeedlingService) {
        this.context = context;
        this.startSeedlingService = startSeedlingService;
    }

    public static final void access$updateDndSeeding(DndSeedling dndSeedling, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("updateDndSeeding, zenMode=", "DndSeedling-->", "SystemUi--Common", z);
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt(KEY_DND_MODE_TYPE, 1);
        } else {
            bundle.putInt(KEY_DND_MODE_TYPE, 0);
        }
        dndSeedling.startSeedlingService.updateData(dndSeedling.context, DND_EVENT, bundle);
    }

    public final void init() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "DndSeedling-->DndSeedling init");
        }
        ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(OplusNoDisturbListener.ZEN_MODE_SETTING), this.zenModeObserver);
        this.isZenModeOn = isZenModeOn();
    }

    public final boolean isZenModeOn() {
        return Settings.Global.getInt(this.context.getContentResolver(), OplusNoDisturbListener.ZEN_MODE_SETTING, 0) > 0;
    }
}
