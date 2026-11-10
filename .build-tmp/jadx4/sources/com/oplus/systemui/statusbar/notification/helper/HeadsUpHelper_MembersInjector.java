package com.oplus.systemui.statusbar.notification.helper;

import com.android.app.displaylib.PerDisplayRepository;
import com.android.systemui.shade.data.repository.ShadeDisplaysRepository;
import com.oplus.systemui.notification.envelope.EnvelopeHelper;
import dagger.Lazy;
import dagger.MembersInjector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class HeadsUpHelper_MembersInjector implements MembersInjector {
    public static void injectMDisplaySubComponentRepository(HeadsUpHelper headsUpHelper, PerDisplayRepository perDisplayRepository) {
        headsUpHelper.mDisplaySubComponentRepository = perDisplayRepository;
    }

    public static void injectMEnvelopeHelper(HeadsUpHelper headsUpHelper, EnvelopeHelper envelopeHelper) {
        headsUpHelper.mEnvelopeHelper = envelopeHelper;
    }

    public static void injectMNotificationCenterHelper(HeadsUpHelper headsUpHelper, NotificationCenterHelper notificationCenterHelper) {
        headsUpHelper.mNotificationCenterHelper = notificationCenterHelper;
    }

    public static void injectMNotificationDependOnKeyguard(HeadsUpHelper headsUpHelper, Lazy lazy) {
        headsUpHelper.mNotificationDependOnKeyguard = lazy;
    }

    public static void injectMShadeDisplaysRepository(HeadsUpHelper headsUpHelper, ShadeDisplaysRepository shadeDisplaysRepository) {
        headsUpHelper.mShadeDisplaysRepository = shadeDisplaysRepository;
    }

    public static void injectMSmallAppHelper(HeadsUpHelper headsUpHelper, SmallAppHelper smallAppHelper) {
        headsUpHelper.mSmallAppHelper = smallAppHelper;
    }
}
