package com.oplus.systemui.notification.lockscreen.statemanager;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ICardControl {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
    }

    static /* synthetic */ void getViewTypeFlag$annotations() {
    }

    ExpandableView findSeedingView(String str);

    int getViewTypeFlag();

    void hide(int i, int i2, boolean z, String str);

    void show(int i, int i2, boolean z, String str);

    /* JADX INFO: renamed from: switch */
    void mo73switch(int i, int i2, boolean z, String str);

    default void updateViewStateToExtState(int i, List<Object> list) {
    }
}
