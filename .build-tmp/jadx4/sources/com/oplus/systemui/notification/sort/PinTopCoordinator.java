package com.oplus.systemui.notification.sort;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PinTopCoordinator implements CoreStartable {
    private static final Companion Companion = new Companion();
    private static final String SPLIT_DELIMITER = "|";
    private static final String TAG = "PinTopCoordinator";
    private final Context context;
    private final Lazy userPinTopNotifSectioner$delegate;
    private final List<Pair<String, String>> fixedPinTopWhiteList = new ArrayList();
    private final NotifSectioner fixedPinTopNotifSectioner = new PinTopCoordinator$fixedPinTopNotifSectioner$1(this);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PinTopCoordinator(Context context) {
        this.context = context;
        PinTopCoordinator$$ExternalSyntheticLambda0 pinTopCoordinator$$ExternalSyntheticLambda0 = new PinTopCoordinator$$ExternalSyntheticLambda0();
        pinTopCoordinator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.userPinTopNotifSectioner$delegate = LazyKt__LazyJVMKt.lazy(pinTopCoordinator$$ExternalSyntheticLambda0);
    }

    public final NotifSectioner getFixedPinTopNotifSectioner() {
        return this.fixedPinTopNotifSectioner;
    }

    public final NotifSectioner getUserPinTopNotifSectioner() {
        return (NotifSectioner) this.userPinTopNotifSectioner$delegate.getValue();
    }

    public final void start() {
        for (String str : this.context.getResources().getStringArray(2130903257)) {
            str.getClass();
            List listSplit$default = StringsKt__StringsKt.split$default(str, new String[]{SPLIT_DELIMITER}, 0, 6);
            if (listSplit$default.size() == 2) {
                this.fixedPinTopWhiteList.add(new Pair<>(listSplit$default.get(0), listSplit$default.get(1)));
            }
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "initPinTopWhiteList:" + this.fixedPinTopWhiteList, null, 8);
    }
}
