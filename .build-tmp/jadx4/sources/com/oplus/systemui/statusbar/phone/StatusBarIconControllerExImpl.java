package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.phone.OplusStatusIconStateSaver;
import com.android.systemui.statusbar.phone.StatusBarIconControllerEx;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.ui.IconManager;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconList;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.statusbar.notification.util.OplusNotificationSmallIconUtil;
import com.oplus.systemui.statusbar.widget.StatIconView;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarIconControllerExImpl extends StatusBarIconControllerEx {
    public static final Companion Companion = new Companion();
    public static final String TAG = "StatusBarIconControllerExImpl";
    public static float sIconRadiusFraction;
    public static int sNotificationRoundIconSize;
    private final CoroutineScope applicationScope;
    private final Lazy notifInflateDispatcher$delegate;
    private final Executor notifInflateExecutor;
    private final dagger.Lazy statusBarIconController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final float getSIconRadiusFraction() {
            return StatusBarIconControllerExImpl.sIconRadiusFraction;
        }

        public final int getSNotificationRoundIconSize() {
            return StatusBarIconControllerExImpl.sNotificationRoundIconSize;
        }

        public final void setSIconRadiusFraction(float f) {
            StatusBarIconControllerExImpl.sIconRadiusFraction = f;
        }

        public final void setSNotificationRoundIconSize(int i) {
            StatusBarIconControllerExImpl.sNotificationRoundIconSize = i;
        }
    }

    public StatusBarIconControllerExImpl(dagger.Lazy lazy, CoroutineScope coroutineScope, Executor executor) {
        this.statusBarIconController = lazy;
        this.applicationScope = coroutineScope;
        this.notifInflateExecutor = executor;
        StatusBarIconControllerExImpl$$ExternalSyntheticLambda0 statusBarIconControllerExImpl$$ExternalSyntheticLambda0 = new StatusBarIconControllerExImpl$$ExternalSyntheticLambda0();
        statusBarIconControllerExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.notifInflateDispatcher$delegate = LazyKt__LazyJVMKt.lazy(statusBarIconControllerExImpl$$ExternalSyntheticLambda0);
    }

    private final CoroutineDispatcher getNotifInflateDispatcher() {
        return (CoroutineDispatcher) this.notifInflateDispatcher$delegate.getValue();
    }

    public OplusStatusIconStateSaver createIconStateSaver() {
        return new OplusStatusIconStateSaverImpl();
    }

    public StatusBarIconView onCreateStatusBarIconView(Context context, String str, boolean z) {
        return new StatIconView(context, str, z);
    }

    public void restoreIconInstanceState(List<? extends IconManager> list, Bundle bundle) {
        OplusStatusIconStateSaver iconStateSaver;
        for (IconManager iconManager : list) {
            Bundle bundle2 = bundle.getBundle(iconManager.getTag());
            if (bundle2 != null && (iconStateSaver = iconManager.getIconStateSaver()) != null) {
                for (int i = 0; i < iconManager.mGroup.getChildCount(); i++) {
                    iconStateSaver.onRestoreIconInstanceState(iconManager.mGroup.getChildAt(i), bundle2);
                }
            }
        }
    }

    public Bundle saveIconInstanceState(List<? extends IconManager> list) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        for (IconManager iconManager : list) {
            OplusStatusIconStateSaver iconStateSaver = iconManager.getIconStateSaver();
            if (iconStateSaver == null) {
                bundle = null;
            } else {
                Bundle bundle3 = new Bundle();
                for (int i = 0; i < iconManager.mGroup.getChildCount(); i++) {
                    iconStateSaver.onSaveIconInstanceState(iconManager.mGroup.getChildAt(i), bundle3);
                }
                bundle = bundle3;
            }
            if (bundle != null) {
                bundle2.putBundle(iconManager.getTag(), bundle);
            }
        }
        return bundle2;
    }

    public void setIcon(Context context, String str, int i, int i2, CharSequence charSequence, StatusBarIconList statusBarIconList) {
        StatusBarIconHolder iconHolder = statusBarIconList.getIconHolder(0, str);
        if (iconHolder == null) {
            OplusStatusBarIcon oplusStatusBarIcon = new OplusStatusBarIcon(UserHandle.SYSTEM, context.getPackageName(), Icon.createWithResource(context, i), 0, 0, charSequence, StatusBarIcon.Type.SystemIcon);
            oplusStatusBarIcon.setForeDrawableResId(i2);
            ((StatusBarIconController) this.statusBarIconController.get()).setIcon(str, StatusBarIconHolder.Companion.fromIcon(oplusStatusBarIcon));
        } else {
            StatusBarIcon icon = iconHolder.getIcon();
            OplusStatusBarIcon oplusStatusBarIcon2 = icon instanceof OplusStatusBarIcon ? (OplusStatusBarIcon) icon : null;
            if (oplusStatusBarIcon2 != null) {
                oplusStatusBarIcon2.setForeDrawableResId(i2);
            }
            ((StatusBarIconController) this.statusBarIconController.get()).setIcon(str, i, charSequence);
        }
    }

    public boolean updateStatusBarIconDrawable(boolean z, Drawable drawable, StatusBarIconView statusBarIconView, StatusBarNotification statusBarNotification) {
        int dimensionPixelOffset;
        if (sNotificationRoundIconSize == 0) {
            sNotificationRoundIconSize = statusBarIconView.getResources().getDimensionPixelSize(2131170767);
        }
        if (sIconRadiusFraction == 0.0f && (dimensionPixelOffset = statusBarIconView.getResources().getDimensionPixelOffset(2131170678)) != 0) {
            sIconRadiusFraction = statusBarIconView.getResources().getDimensionPixelOffset(2131173086) / dimensionPixelOffset;
        }
        if (statusBarNotification == null) {
            return false;
        }
        Drawable.ConstantState constantState = drawable.mutate().getConstantState();
        Drawable drawableNewDrawable = constantState != null ? constantState.newDrawable() : null;
        if (drawableNewDrawable == null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "constantState is null, fallback to sync, key=", statusBarNotification.getKey(), LogUtil.TAG_STATUSBAR, TAG);
            return false;
        }
        Object tag = statusBarIconView.getTag(2131363404);
        Job job = tag instanceof Job ? (Job) tag : null;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        statusBarIconView.setTag(2131363404, BuildersKt.launch$default(this.applicationScope, getNotifInflateDispatcher(), (CoroutineStart) null, new StatusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1(drawableNewDrawable, statusBarIconView.getContext().getApplicationContext(), statusBarIconView, null), 2));
        return true;
    }

    public void updateStatusBarIconGrayScale(Drawable drawable, StatusBarIconView statusBarIconView, StatusBarNotification statusBarNotification) {
        if (statusBarNotification != null) {
            statusBarIconView.getTag(2131363396);
            boolean z = !OplusNotificationSmallIconUtil.useAppIconForSmallIcon(statusBarNotification.getNotification()) && OplusContrastColorUtil.getInstance(statusBarIconView.getContext().getApplicationContext()).isGrayscaleIcon(drawable);
            statusBarIconView.setTag(2131363396, Boolean.valueOf(z));
            statusBarIconView.setIsIconColorable(z);
        }
    }
}
