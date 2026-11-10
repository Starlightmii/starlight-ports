package com.oplus.systemui.statusbar.notification.collection.render;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.util.time.SystemClock;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShadeViewDifferExImpl extends ShadeViewDifferEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "VisualStabilityHelperImpl";
    private final SystemClock clock;
    private final Context context;
    private final Lazy lockScreenExtStateController;
    private final Set<Runnable> mAppUpdateListeners = new ArraySet();
    private final Lazy mMainHandler;
    private final VisualStabilityProvider mVisualStabilityProvider;
    private final NotificationPanelViewController panel;
    private final kotlin.Lazy pkgList$delegate;
    private final Lazy statusBarStateController;
    private final kotlin.Lazy windowManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static WindowManager $r8$lambda$TZEg0eD0nvqMSxWwvZzCxMWFuyI(ShadeViewDifferExImpl shadeViewDifferExImpl) {
        return (WindowManager) shadeViewDifferExImpl.context.getSystemService("window");
    }

    public ShadeViewDifferExImpl(VisualStabilityProvider visualStabilityProvider, Lazy lazy, SystemClock systemClock, Context context, NotificationPanelViewController notificationPanelViewController, Lazy lazy2, Lazy lazy3) {
        this.mVisualStabilityProvider = visualStabilityProvider;
        this.mMainHandler = lazy;
        this.clock = systemClock;
        this.context = context;
        this.panel = notificationPanelViewController;
        this.lockScreenExtStateController = lazy2;
        this.statusBarStateController = lazy3;
        ShadeViewDifferExImpl$$ExternalSyntheticLambda0 shadeViewDifferExImpl$$ExternalSyntheticLambda0 = new ShadeViewDifferExImpl$$ExternalSyntheticLambda0(0);
        shadeViewDifferExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.pkgList$delegate = LazyKt__LazyJVMKt.lazy(shadeViewDifferExImpl$$ExternalSyntheticLambda0);
        ShadeViewDifferExImpl$$ExternalSyntheticLambda0 shadeViewDifferExImpl$$ExternalSyntheticLambda1 = new ShadeViewDifferExImpl$$ExternalSyntheticLambda0(1);
        shadeViewDifferExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.windowManager$delegate = LazyKt__LazyJVMKt.lazy(shadeViewDifferExImpl$$ExternalSyntheticLambda1);
    }

    private final String[] getPkgList() {
        return (String[]) this.pkgList$delegate.getValue();
    }

    private final WindowManager getWindowManager() {
        return (WindowManager) this.windowManager$delegate.getValue();
    }

    public void addAppUpdateListener(Runnable runnable) {
        this.mAppUpdateListeners.add(runnable);
    }

    /* JADX WARN: Code duplicated, block: B:39:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:47:0x00e3  */
    public void checkNodeVisibility(boolean z, View view) {
        boolean z2;
        String str;
        Rect bounds;
        String str2 = "VISIBLE";
        if (view instanceof SectionHeaderView) {
            int iExpectKeepGoneVisibility = expectKeepGoneVisibility(z);
            SectionHeaderView sectionHeaderView = (SectionHeaderView) view;
            if (sectionHeaderView.getVisibility() != iExpectKeepGoneVisibility) {
                sectionHeaderView.setVisibility(iExpectKeepGoneVisibility);
                if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    if (iExpectKeepGoneVisibility != 0) {
                        if (iExpectKeepGoneVisibility != 4) {
                            str2 = iExpectKeepGoneVisibility != 8 ? "UNKNOWN" : "GONE";
                        } else {
                            str2 = "INVISIBLE";
                        }
                    }
                    KeyguardSettingProvider$.ExternalSyntheticOutline0.m("checkNodeVisibility(header), to ", str2, "VisualStabilityHelperImpl-->", "SystemUi--Notification");
                    return;
                }
                return;
            }
            return;
        }
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isKeepGone == z) {
                return;
            }
            int iExpectKeepGoneVisibility2 = expectKeepGoneVisibility(z);
            if (!z) {
                this.clock.getClass();
                long jElapsedRealtime = android.os.SystemClock.elapsedRealtime();
                List attachedChildren = expandableNotificationRow.getAttachedChildren();
                if (attachedChildren != null) {
                    Iterator it = attachedChildren.iterator();
                    while (it.hasNext()) {
                        ((ExpandableNotificationRow) it.next()).setInitializationTime(jElapsedRealtime);
                    }
                }
                expandableNotificationRow.setInitializationTime(jElapsedRealtime);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "VisualStabilityHelperImpl-->".concat("checkNodeVisibility, setInitializationTime=" + jElapsedRealtime + ", for " + expandableNotificationRow.getEntry().getEx().getEncryptionKey()));
                }
            }
            if (expandableNotificationRow.getVisibility() != iExpectKeepGoneVisibility2) {
                QuickSettingsController qsController = this.panel.getQsController();
                int iHeight = 0;
                if (qsController != null) {
                    z2 = qsController.isCustomizing();
                }
                if (z2 && ((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isKeepGone && !z) {
                    WindowMetrics currentWindowMetrics = getWindowManager().getCurrentWindowMetrics();
                    if (currentWindowMetrics != null && (bounds = currentWindowMetrics.getBounds()) != null) {
                        iHeight = bounds.height();
                    }
                    expandableNotificationRow.setTranslationY(iHeight);
                }
                expandableNotificationRow.setVisibility(iExpectKeepGoneVisibility2);
                if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    int visibility = expandableNotificationRow.getVisibility();
                    if (visibility == 0) {
                        str = "VISIBLE";
                    } else if (visibility != 4) {
                        str = visibility != 8 ? "UNKNOWN" : "GONE";
                    } else {
                        str = "INVISIBLE";
                    }
                    SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("VisualStabilityHelperImpl-->", "SystemUi--Notification", ExifInterface$.ExternalSyntheticOutline1.m("checkNodeVisibility, to ", str, ", for ", expandableNotificationRow.getEntry().getEx().getEncryptionKey(), " isInQsCustom == "), z2);
                }
            }
            expandableNotificationRow.mRowEx.setKeepGone(z);
        }
    }

    public final int expectKeepGoneVisibility(boolean z) {
        if (z) {
            return 8;
        }
        return (((StatusBarStateController) this.statusBarStateController.get()).getState() == 0 || ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isStackState() || ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isLockScreenNtfCenterState()) ? 0 : 4;
    }

    public final boolean isReorderingAllowed() {
        return this.mVisualStabilityProvider.isReorderingAllowed;
    }

    public boolean isSupportToFirst(View view) {
        if (view instanceof ExpandableNotificationRow) {
            return ArraysKt___ArraysKt.contains(getPkgList(), ((ExpandableNotificationRow) view).getEntry().getSbn().getPackageName());
        }
        return false;
    }

    public final void notifyAppUpdateListener() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "VisualStabilityHelperImpl-->updateNotifyView");
        }
        Handler handler = (Handler) this.mMainHandler.get();
        notifyAppUpdateListener.2 r1 = new notifyAppUpdateListener.2();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(r1);
    }

    public void removeAppUpdateListener(Runnable runnable) {
        this.mAppUpdateListeners.remove(runnable);
    }
}
