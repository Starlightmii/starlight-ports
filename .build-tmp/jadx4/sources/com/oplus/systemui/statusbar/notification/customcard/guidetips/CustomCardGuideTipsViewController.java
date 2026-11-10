package com.oplus.systemui.statusbar.notification.customcard.guidetips;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.systemui.notification.row.customrow.guidetips.OplusCustomRowGuideTipsCallback;
import com.oplus.systemui.notification.row.customrow.guidetips.OplusCustomRowLockScreenInteractor;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomCardGuideTipsViewController implements OplusCustomRowGuideTipsCallback, GuideTipsAnimatorListener {
    public static final Companion Companion = new Companion();
    private static final String IMMERSIVE_GUIDE_SP = "la_immersive_tip_count";
    private static final String KEY_LOCK_IMMERSIVE_ENABLE = "lockImmersiveEnable";
    private static final String METHOD_GET_IMMERSIVE_AREA = "getImmersiveArea";
    private static final String TAG = "CustomCardGuideTipsVC";
    private FrameLayout containerLightView;
    private final Context context;
    private View customRowView;
    private final Executor executor;
    private CustomRowEntry guideShowEntry;
    public boolean guideTipsShowing;
    private SeedlingGuideLightView guideView;
    private Rect hotRect = new Rect();
    private final ViewTreeObserver.OnGlobalLayoutListener layoutChangeListener;
    private final OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor;
    private SharedPreferences sharedPreferences;
    private CountDownTimer timerCountDown;
    private final UserFileManager userFileManager;
    private final UserTracker userTracker;
    private final CustomCardGuideTipsViewController$userTrackerCallback$1 userTrackerCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CustomCardGuideTipsViewController(Context context, UserTracker userTracker, Executor executor, UserFileManager userFileManager, OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor) {
        this.context = context;
        this.userTracker = userTracker;
        this.executor = executor;
        this.userFileManager = userFileManager;
        this.oplusCustomRowLockScreenInteractor = oplusCustomRowLockScreenInteractor;
        CustomCardGuideTipsViewController$layoutChangeListener$1 customCardGuideTipsViewController$layoutChangeListener$1 = new CustomCardGuideTipsViewController$layoutChangeListener$1();
        customCardGuideTipsViewController$layoutChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutChangeListener = customCardGuideTipsViewController$layoutChangeListener$1;
        CustomCardGuideTipsViewController$userTrackerCallback$1 customCardGuideTipsViewController$userTrackerCallback$1 = new CustomCardGuideTipsViewController$userTrackerCallback$1();
        customCardGuideTipsViewController$userTrackerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userTrackerCallback = customCardGuideTipsViewController$userTrackerCallback$1;
        oplusCustomRowLockScreenInteractor.addCallback(this);
        userTracker.addCallback(customCardGuideTipsViewController$userTrackerCallback$1, executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getImmersiveArea() {
        Rect rect;
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", METHOD_GET_IMMERSIVE_AREA);
        CustomRowEntry customRowEntry = this.guideShowEntry;
        Bundle bundle = customRowEntry != null ? customRowEntry.getBundle(bundleM) : null;
        if (bundle == null || (rect = (Rect) bundle.getParcelable("rect")) == null) {
            rect = new Rect();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            CustomRowEntry customRowEntry2 = this.guideShowEntry;
            Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->".concat("getImmersiveArea, guideShowEntry:" + (customRowEntry2 != null ? customRowEntry2.getKey() : null) + ", rect: " + rect));
        }
        return rect;
    }

    @Override // com.oplus.systemui.notification.row.customrow.guidetips.OplusCustomRowGuideTipsCallback
    public void onCardExposedOnLockScreen(List<? extends CustomRowEntry> list) {
        ViewTreeObserver viewTreeObserver;
        String key;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("CustomCardGuideTipsVC-->");
            List<? extends CustomRowEntry> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(((CustomRowEntry) it.next()).getKey());
            }
            sb.append("seedlingCardExposedLockScreen, exposedList=" + arrayList + ", guideTipsShowing=" + this.guideTipsShowing);
            Log.d("SystemUi--Notification", sb.toString());
        }
        if (this.guideTipsShowing) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        for (CustomRowEntry customRowEntry : list) {
            if (this.sharedPreferences == null) {
                this.sharedPreferences = this.userFileManager.getSharedPreferences(IMMERSIVE_GUIDE_SP, 0, this.userTracker.getUserId());
            }
            String serviceId = customRowEntry.getServiceId();
            SharedPreferences sharedPreferences = this.sharedPreferences;
            int i = sharedPreferences != null ? sharedPreferences.getInt(serviceId, 0) : 2;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->".concat("getEntryGuideTimes, key: " + serviceId + ", guideTimes: " + i));
            }
            boolean z = customRowEntry.getExtras().getBoolean("lockImmersiveEnable", false);
            if (i < 2 && z) {
                arrayList2.add(customRowEntry);
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        CustomRowEntry customRowEntry2 = (CustomRowEntry) CollectionsKt___CollectionsKt.first(arrayList2);
        this.guideShowEntry = customRowEntry2;
        this.customRowView = customRowEntry2 != null ? customRowEntry2.getCustomView() : null;
        CustomRowEntry customRowEntry3 = this.guideShowEntry;
        if (customRowEntry3 != null) {
            this.hotRect = getImmersiveArea();
            CustomRowEntry customRowEntry4 = this.guideShowEntry;
            this.containerLightView = (customRowEntry4 == null || (key = customRowEntry4.getKey()) == null) ? null : this.oplusCustomRowLockScreenInteractor.requestGuideTipsViewToShow(key);
            SeedlingGuideLightView seedlingGuideLightView = new SeedlingGuideLightView(this.context, null);
            this.guideView = seedlingGuideLightView;
            seedlingGuideLightView.setAnimatorListener(this);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->".concat("showGuideTips, entry=" + customRowEntry3.getKey() + ", hotRect=" + this.hotRect + ", containerLightView=" + this.containerLightView + ", guideView=" + this.guideView));
            }
            if (this.containerLightView == null || this.guideView == null || this.hotRect.width() <= 0 || this.hotRect.height() <= 0) {
                startEndAnimator();
                return;
            }
            this.guideTipsShowing = true;
            FrameLayout frameLayout = this.containerLightView;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            FrameLayout frameLayout2 = this.containerLightView;
            if (frameLayout2 != null) {
                frameLayout2.addView(this.guideView);
            }
            FrameLayout frameLayout3 = this.containerLightView;
            if (frameLayout3 != null) {
                if (!frameLayout3.isLaidOut() || frameLayout3.isLayoutRequested()) {
                    CustomCardGuideTipsViewController$showGuideTips$$inlined$doOnLayout$1 customCardGuideTipsViewController$showGuideTips$$inlined$doOnLayout$1 = new CustomCardGuideTipsViewController$showGuideTips$$inlined$doOnLayout$1();
                    customCardGuideTipsViewController$showGuideTips$$inlined$doOnLayout$1.this$0 = this;
                    VarHandle.storeStoreFence();
                    frameLayout3.addOnLayoutChangeListener(customCardGuideTipsViewController$showGuideTips$$inlined$doOnLayout$1);
                } else {
                    SeedlingGuideLightView seedlingGuideLightView2 = this.guideView;
                    if (seedlingGuideLightView2 != null) {
                        seedlingGuideLightView2.setPosition(this.hotRect);
                    }
                    View view = this.customRowView;
                    if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
                        viewTreeObserver.addOnGlobalLayoutListener(this.layoutChangeListener);
                    }
                    SeedlingGuideLightView seedlingGuideLightView3 = this.guideView;
                    if (seedlingGuideLightView3 != null) {
                        seedlingGuideLightView3.startAnimator();
                    }
                }
            }
            CountDownTimer countDownTimer = this.timerCountDown;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.timerCountDown = new CountDownTimer() { // from class: com.oplus.systemui.statusbar.notification.customcard.guidetips.CustomCardGuideTipsViewController$startCountDown$1
                {
                    super(5000L, 5000L);
                }

                @Override // android.os.CountDownTimer
                public final void onFinish() {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->startCountDown, onFinish");
                    }
                    this.this$0.startEndAnimator();
                    this.this$0.timerCountDown = null;
                }

                @Override // android.os.CountDownTimer
                public final void onTick(long j) {
                }
            }.start();
            this.oplusCustomRowLockScreenInteractor.showSeedlingCardFooter(customRowEntry3.getKey(), true);
            setEntryGuideTimes(customRowEntry3, false);
        }
    }

    public final void onDataChanged(List<? extends CustomRowEntry> list) {
        Object obj;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("CustomCardGuideTipsVC-->");
            List<? extends CustomRowEntry> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(((CustomRowEntry) it.next()).getKey());
            }
            sb.append("onDataChanged: entryList=" + arrayList);
            Log.d("SystemUi--Notification", sb.toString());
        }
        if (this.guideShowEntry != null) {
            Iterator<T> it2 = list.iterator();
            while (true) {
                obj = null;
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                String key = ((CustomRowEntry) next).getKey();
                CustomRowEntry customRowEntry = this.guideShowEntry;
                if (Intrinsics.areEqual(key, customRowEntry != null ? customRowEntry.getKey() : null)) {
                    obj = next;
                    break;
                }
            }
            CustomRowEntry customRowEntry2 = (CustomRowEntry) obj;
            if (customRowEntry2 == null) {
                startEndAnimator();
            } else {
                if (customRowEntry2.getExtras().getBoolean("lockImmersiveEnable", false)) {
                    return;
                }
                startEndAnimator();
            }
        }
    }

    public final void onGuideTipsAnimatorEnd() {
        ViewTreeObserver viewTreeObserver;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->onGuideTipsAnimatorEnd");
        }
        this.guideTipsShowing = false;
        this.guideShowEntry = null;
        FrameLayout frameLayout = this.containerLightView;
        if (frameLayout != null) {
            frameLayout.removeView(this.guideView);
        }
        this.containerLightView = null;
        View view = this.customRowView;
        if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this.layoutChangeListener);
        }
        this.customRowView = null;
        SeedlingGuideLightView seedlingGuideLightView = this.guideView;
        if (seedlingGuideLightView != null) {
            seedlingGuideLightView.removeAnimatorListener();
        }
        this.guideView = null;
        this.hotRect = new Rect();
    }

    public final void setEntryGuideTimes(CustomRowEntry customRowEntry, boolean z) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = this.userFileManager.getSharedPreferences(IMMERSIVE_GUIDE_SP, 0, this.userTracker.getUserId());
        }
        String serviceId = customRowEntry.getServiceId();
        SharedPreferences sharedPreferences = this.sharedPreferences;
        int i = z ? 2 : (sharedPreferences != null ? sharedPreferences.getInt(serviceId, 0) : 2) + 1;
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        if (sharedPreferences2 != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
            editorEdit.putInt(serviceId, i);
            editorEdit.apply();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("CustomCardGuideTipsVC-->", "SystemUi--Notification", ConstraintSet$.ExternalSyntheticOutline0.m(i, "setEntryGuideTimes, key: ", serviceId, ", guideTimes: ", ", isEnterImmersiveState="), z);
        }
    }

    public final void setImmersiveGuideTimes(int i, String str, int i2, String str2) {
        CustomRowEntry customRowEntry;
        if (i != 2 || i2 != 201 || str2 == null || (customRowEntry = this.oplusCustomRowLockScreenInteractor.getCustomRowEntry(str2)) == null) {
            return;
        }
        setEntryGuideTimes(customRowEntry, true);
    }

    public final void startEndAnimator() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->onEnd");
        }
        SeedlingGuideLightView seedlingGuideLightView = this.guideView;
        if (seedlingGuideLightView != null) {
            seedlingGuideLightView.endAnimator();
        }
        CustomRowEntry customRowEntry = this.guideShowEntry;
        if (customRowEntry != null) {
            this.oplusCustomRowLockScreenInteractor.hideSeedlingCardFooter(customRowEntry.getKey(), true);
        }
    }

    public final void stopCountDown() {
        CountDownTimer countDownTimer = this.timerCountDown;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.timerCountDown = null;
    }
}
