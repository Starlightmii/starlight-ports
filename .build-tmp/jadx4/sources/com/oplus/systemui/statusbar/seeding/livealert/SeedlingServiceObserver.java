package com.oplus.systemui.statusbar.seeding.livealert;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingServiceObserver extends AbstractObserver {
    private static final String PATH_RESULT_SYSTEMUI = "systemui/services";
    private static final String SERVICE_DECISION_AUTHORITY = "com.oplus.pantanal.ums.decision";
    public static final Companion Companion = new Companion();
    private static final Uri RESULT_SYSTEMUI_URI = Uri.parse("content://com.oplus.pantanal.ums.decision/systemui/services");
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(new SeedlingServiceObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final SeedlingServiceObserver getInstance() {
            return (SeedlingServiceObserver) SeedlingServiceObserver.instance$delegate.getValue();
        }
    }

    public static final SeedlingServiceObserver getInstance() {
        return Companion.getInstance();
    }

    public final boolean existSeedlingService(Context context) {
        boolean z = false;
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(RESULT_SYSTEMUI_URI, null, null, null, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "";
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Uri getUri(Context context) {
        return RESULT_SYSTEMUI_URI;
    }
}
