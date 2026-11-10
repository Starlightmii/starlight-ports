package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.car.app.model.TimerSpan$;
import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.stack.OplusStackScrollAlgorithmExImpl;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationAodTransition implements OplusPanoramicAodRepository.PanoramicAodProgressChangeListener, CoreStartable, OplusStackScrollAlgorithmExImpl.LifecycleListener {
    private static final Companion Companion = new Companion();
    private static final String TAG = "NotificationAodTransition";
    private NotificationDetailColorChanger colorChanger;
    private final Provider colorChangerProvider;
    private final Context context;
    private final InitController initController;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Lazy notifCollection;
    private final NotificationStackScrollLayout nssl;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    private final OplusStackScrollAlgorithmExImpl oplusStackScrollAlgorithmEx;
    public int state;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.aod.notification.NotificationAodTransition$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ NotificationAodTransition this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.oplusPanoramicAodRepository.addCallback(this.this$0);
                    break;
                default:
                    NotificationAodTransition notificationAodTransition = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationAodTransition-->".concat("onSensitivityChange " + notificationAodTransition.state + ", panoramicAodProgress: " + notificationAodTransition.oplusPanoramicAodRepository.getPanoramicAodProgress()));
                    }
                    NotificationAodTransition notificationAodTransition2 = this.this$0;
                    notificationAodTransition2.onProgressChanged(notificationAodTransition2.oplusPanoramicAodRepository.getPanoramicAodProgress());
                    break;
            }
        }
    }

    public NotificationAodTransition(NotificationStackScrollLayout notificationStackScrollLayout, OplusStackScrollAlgorithmExImpl oplusStackScrollAlgorithmExImpl, OplusPanoramicAodRepository oplusPanoramicAodRepository, InitController initController, Lazy lazy, Provider provider, Context context) {
        this.nssl = notificationStackScrollLayout;
        this.oplusStackScrollAlgorithmEx = oplusStackScrollAlgorithmExImpl;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.initController = initController;
        this.notifCollection = lazy;
        this.colorChangerProvider = provider;
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void applyProgressToChildView(View view, NotificationDetailColorChanger notificationDetailColorChanger, float f, boolean z, boolean z2) {
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            notificationDetailColorChanger.changeAodViewColor(expandableNotificationRow, f, z);
            if (z2) {
                notificationDetailColorChanger.checkFirstStackIdle(expandableNotificationRow);
                return;
            }
            return;
        }
        if (view instanceof MediaContainerView) {
            notificationDetailColorChanger.changeMediaViewColor((MediaContainerView) view, f);
        } else if (view instanceof OplusCustomRow) {
            notificationDetailColorChanger.changeAodCustomRowColor((OplusCustomRow) view, f, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void notifyDataChanged(View view, String str) {
        NotificationDetailColorChanger notificationDetailColorChanger;
        if (this.oplusPanoramicAodRepository.getPanoramicAodProgress() == 1.0f) {
            if (!(view instanceof ExpandableNotificationRow)) {
                if (!(view instanceof OplusCustomRow) || (notificationDetailColorChanger = this.colorChanger) == null) {
                    return;
                }
                notificationDetailColorChanger.changeAodCustomRowColor((OplusCustomRow) view, 1.0f, OplusDarkModeUtil.isNightMode(notificationDetailColorChanger.context));
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("notifyDataChanged: reason=", str, " row=", ((ExpandableNotificationRow) view).getKey(), "NotificationAodTransition-->"));
            }
            NotificationDetailColorChanger notificationDetailColorChanger2 = this.colorChanger;
            if (notificationDetailColorChanger2 != null) {
                notificationDetailColorChanger2.changeAodViewColor((ExpandableNotificationRow) view, 1.0f, OplusDarkModeUtil.isNightMode(notificationDetailColorChanger2.context));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void notifyProgressChanged(View view, float f, String str) {
        NotificationDetailColorChanger notificationDetailColorChanger;
        if (!(view instanceof ExpandableNotificationRow)) {
            if (!(view instanceof OplusCustomRow) || (notificationDetailColorChanger = this.colorChanger) == null) {
                return;
            }
            notificationDetailColorChanger.changeAodCustomRowColor((OplusCustomRow) view, f, OplusDarkModeUtil.isNightMode(notificationDetailColorChanger.context));
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("notifyProgressChanged: reason=", str, " row=", ((ExpandableNotificationRow) view).getKey(), "NotificationAodTransition-->"));
        }
        NotificationDetailColorChanger notificationDetailColorChanger2 = this.colorChanger;
        if (notificationDetailColorChanger2 != null) {
            notificationDetailColorChanger2.changeAodViewColor((ExpandableNotificationRow) view, f, OplusDarkModeUtil.isNightMode(notificationDetailColorChanger2.context));
        }
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onPanoramicAodStateChanged(boolean z, boolean z2) {
        if (z && z2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationAodTransition-->".concat("onShouldUpdateUiChanged " + this.state + ", progress: " + this.oplusPanoramicAodRepository.getPanoramicAodProgress()));
            }
            onProgressChanged(this.oplusPanoramicAodRepository.getPanoramicAodProgress());
        }
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onProgressChanged(float f) {
        int i;
        int i2 = this.state;
        Companion.getClass();
        int i3 = 0;
        if (f <= 0.0f || i2 != 0) {
            i = 2;
            if ((f <= 0.0f || i2 != 1) && (f <= 0.0f || i2 != 2)) {
                i = f == 0.0f ? 0 : i2;
            }
        } else {
            i = 1;
        }
        this.state = i;
        if (i2 == 0 && i == 0) {
            return;
        }
        List visibleChildren = this.nssl.getVisibleChildren();
        int size = visibleChildren.size();
        int i4 = this.state;
        if (i4 == 0) {
            this.oplusStackScrollAlgorithmEx.removeListener(this);
        } else if (i4 == 1) {
            this.oplusStackScrollAlgorithmEx.removeListener(this);
            this.oplusStackScrollAlgorithmEx.addListener(this);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onProgressChanged:start progress=" + f + ",size=" + size, null, 8);
        }
        NotificationDetailColorChanger notificationDetailColorChanger = this.colorChanger;
        if (notificationDetailColorChanger == null) {
            notificationDetailColorChanger = (NotificationDetailColorChanger) this.colorChangerProvider.get();
            this.colorChanger = notificationDetailColorChanger;
        }
        notificationDetailColorChanger.setGlobalIdle(this.state == 0);
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(this.context);
        if (this.state != 0) {
            Iterator it = visibleChildren.iterator();
            while (it.hasNext()) {
                applyProgressToChildView((View) it.next(), notificationDetailColorChanger, f, zIsNightMode, false);
            }
            if (this.nssl.getTransientViewCount() != 0) {
                int transientViewCount = this.nssl.getTransientViewCount();
                while (i3 < transientViewCount) {
                    ExpandableNotificationRow transientView = this.nssl.getTransientView(i3);
                    if (transientView instanceof ExpandableNotificationRow) {
                        notificationDetailColorChanger.changeAodViewColor(transientView, f, zIsNightMode);
                    } else if (transientView instanceof OplusCustomRow) {
                        notificationDetailColorChanger.changeAodCustomRowColor((OplusCustomRow) transientView, f, zIsNightMode);
                    }
                    i3++;
                }
                return;
            }
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (View view : ConvenienceExtensionsKt.getChildren(this.nssl)) {
            view.getClass();
            applyProgressToChildView(view, notificationDetailColorChanger, 0.0f, zIsNightMode, true);
        }
        if (this.nssl.getTransientViewCount() != 0) {
            int transientViewCount2 = this.nssl.getTransientViewCount();
            for (int i5 = 0; i5 < transientViewCount2; i5++) {
                ExpandableNotificationRow transientView2 = this.nssl.getTransientView(i5);
                if (transientView2 instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow = transientView2;
                    notificationDetailColorChanger.changeAodViewColor(expandableNotificationRow, 0.0f, zIsNightMode);
                    notificationDetailColorChanger.checkFirstStackIdle(expandableNotificationRow);
                } else if (transientView2 instanceof OplusCustomRow) {
                    notificationDetailColorChanger.changeAodCustomRowColor((OplusCustomRow) transientView2, 0.0f, zIsNightMode);
                }
            }
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        Collection allNotifs = ((NotifCollection) this.notifCollection.get()).getAllNotifs();
        ArrayList arrayList = new ArrayList();
        Iterator it2 = allNotifs.iterator();
        while (it2.hasNext()) {
            ExpandableNotificationRow row = ((NotificationEntry) it2.next()).getRow();
            if (row != null) {
                arrayList.add(row);
            }
        }
        int size2 = arrayList.size();
        while (i3 < size2) {
            Object obj = arrayList.get(i3);
            i3++;
            ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) obj;
            notificationDetailColorChanger.checkNormalBgIdle(expandableNotificationRow2);
            notificationDetailColorChanger.changeAodViewColor(expandableNotificationRow2, 0.0f, zIsNightMode);
        }
        long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis3;
        NotificationDetailColorChanger notificationDetailColorChanger2 = this.colorChanger;
        if (notificationDetailColorChanger2 != null) {
            notificationDetailColorChanger2.removeConfigurationCallback();
        }
        this.colorChanger = null;
        StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "onProgressChanged:end cost=", ",cost2=");
        sbM.append(jCurrentTimeMillis4);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onRemoveView(View view) {
        NotificationDetailColorChanger notificationDetailColorChanger;
        if (this.state == 0) {
            return;
        }
        if (!(view instanceof ExpandableNotificationRow)) {
            if (!(view instanceof OplusCustomRow) || (notificationDetailColorChanger = this.colorChanger) == null) {
                return;
            }
            notificationDetailColorChanger.changeAodCustomRowColor((OplusCustomRow) view, 0.0f, OplusDarkModeUtil.isNightMode(notificationDetailColorChanger.context));
            return;
        }
        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
        if (Intrinsics.areEqual(expandableNotificationRow.getParent(), this.nssl)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationAodTransition-->onRemoveView: but is a Transient view");
                return;
            }
            return;
        }
        if (!(expandableNotificationRow.getParent() instanceof NotificationChildrenContainer)) {
            NotificationDetailColorChanger notificationDetailColorChanger2 = this.colorChanger;
            if (notificationDetailColorChanger2 != null) {
                notificationDetailColorChanger2.changeAodViewColor(expandableNotificationRow, 0.0f, OplusDarkModeUtil.isNightMode(notificationDetailColorChanger2.context));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationAodTransition-->onRemoveView: but is a sub-notification view in group");
        }
        NotificationDetailColorChanger notificationDetailColorChanger3 = this.colorChanger;
        if (notificationDetailColorChanger3 != null) {
            notificationDetailColorChanger3.changeAodViewColor(expandableNotificationRow, this.oplusPanoramicAodRepository.getPanoramicAodProgress(), OplusDarkModeUtil.isNightMode(notificationDetailColorChanger3.context));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onStackSecondAddView(View view) {
        NotificationDetailColorChanger notificationDetailColorChanger;
        if (this.state == 0 || view == 0) {
            return;
        }
        if (view instanceof ExpandableNotificationRow) {
            NotificationDetailColorChanger notificationDetailColorChanger2 = this.colorChanger;
            if (notificationDetailColorChanger2 != null) {
                notificationDetailColorChanger2.addBackgroundView((ExpandableNotificationRow) view);
                return;
            }
            return;
        }
        if (!(view instanceof OplusCustomRow) || (notificationDetailColorChanger = this.colorChanger) == null) {
            return;
        }
        notificationDetailColorChanger.addBackgroundView((OplusCustomRow) view);
    }

    public final void onStackSecondRemoveView(View view) {
        NotificationDetailColorChanger notificationDetailColorChanger;
        if (this.state == 0 || view == null || (notificationDetailColorChanger = this.colorChanger) == null) {
            return;
        }
        notificationDetailColorChanger.clearContentView(view);
    }

    public final void onStateChange() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationAodTransition-->".concat("onStateChange " + this.state + ", panoramicAodProgress: " + this.oplusPanoramicAodRepository.getPanoramicAodProgress()));
        }
        onProgressChanged(this.oplusPanoramicAodRepository.getPanoramicAodProgress());
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }

    public final void updateStateForSensitivityChanged() {
        Handler handler = this.mainHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.postDelayed(anonymousClass1, 8L);
    }
}
