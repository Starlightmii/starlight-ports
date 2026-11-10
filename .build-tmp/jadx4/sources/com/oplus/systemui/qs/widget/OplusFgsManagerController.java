package com.oplus.systemui.qs.widget;

import android.app.IActivityManager;
import android.app.IForegroundServiceObserver;
import android.app.OplusActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.content.FileProvider$;
import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.oplus.systemui.common.dialog.OplusThemeSystemUiDialog;
import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFgsManagerController extends IForegroundServiceObserver.Stub implements OplusThemeSystemUiDialog.ILifeCycleCallback {
    private static final long MIN_TIME_DURATION = 60000;
    private final OplusQSDetailInteractor detailInteractor;
    private boolean hasDialog;
    private int lastNumberOfVisiblePackages;
    private final IActivityManager mActivityManager;
    private final Context mContext;
    private final FgsListAdapter mFgsListAdapter;
    private boolean mInitialized;
    private final Executor mMainExecutor;
    private int mOriFgsNum;
    private final PackageManager mPackageManager;
    private ArrayMap<UserPackage, RunningApp> mRunningApps;
    private final SystemClock mSystemClock;
    private int mUserId;
    private final Set<OnNumberOfPackagesChangedListener> onNumberOfPackagesChangedListeners;
    private final UserTracker userTracker;
    private final OplusFgsManagerController$userTrackerCallback$1 userTrackerCallback;
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusFgsManagerController";
    private final Executor mBackgroundExecutor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
    private final Object mLock = new Object();
    private final Map<UserPackage, StartTimeAndTokens> mRunningServiceTokens = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FgsListAdapter implements DetailAdapter, OplusQSDetailItems.Callback {
        private OplusQSDetailItems mDetailItems;
        public /* synthetic */ OplusFgsManagerController this$0;

        public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            OplusQSDetailItems oplusQSDetailItemsConvertOrInflate = OplusQSDetailItems.convertOrInflate(context, viewGroup, z);
            this.mDetailItems = oplusQSDetailItemsConvertOrInflate;
            if (oplusQSDetailItemsConvertOrInflate != null) {
                oplusQSDetailItemsConvertOrInflate.setTagSuffix("FgsManager");
            }
            OplusQSDetailItems oplusQSDetailItems = this.mDetailItems;
            if (oplusQSDetailItems != null) {
                oplusQSDetailItems.setCallback(this);
            }
            OplusQSDetailItems oplusQSDetailItems2 = this.mDetailItems;
            if (oplusQSDetailItems2 != null) {
                oplusQSDetailItems2.mActiveTitle = null;
                oplusQSDetailItems2.mAvailableTitle = 2131955661;
                oplusQSDetailItems2.mAvailableRefreshTitle = null;
            }
            int i = OplusDarkModeUtil.isNightMode(context) ? 2131236018 : 2131236019;
            OplusQSDetailItems oplusQSDetailItems3 = this.mDetailItems;
            if (oplusQSDetailItems3 != null) {
                oplusQSDetailItems3.setEmptyState(i, 2131955662);
            }
            return this.mDetailItems;
        }

        public DetailAdapter.DetailHeightType getDetailHeightType() {
            return DetailAdapter.DetailHeightType.IMMUTABLE_ONE;
        }

        public int getMetricsCategory() {
            return 404;
        }

        public String getTag() {
            return OplusFgsManagerController.TAG;
        }

        public CharSequence getTitle() {
            return this.this$0.mContext.getString(2131955704);
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public void onDetailItemClick(OplusQSDetailItems.Item item) {
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public void onDetailItemDisconnect(OplusQSDetailItems.Item item) {
            OplusFgsManagerController oplusFgsManagerController = this.this$0;
            RunningApp runningApp = (RunningApp) (item != null ? item.tag : null);
            oplusFgsManagerController.stopPackage(runningApp.getUserId(), runningApp.getPackageName());
            QsStatisticUtil.collectShortcutsCenterEvent$default(oplusFgsManagerController.mContext, "stop_fgs_click", (String) null, 12);
        }

        public final void onDismiss() {
            OplusFgsManagerController oplusFgsManagerController = this.this$0;
            oplusFgsManagerController.setHasDialog(false);
            oplusFgsManagerController.onStop();
        }

        public final void onShow() {
            OplusFgsManagerController oplusFgsManagerController = this.this$0;
            oplusFgsManagerController.setHasDialog(true);
            oplusFgsManagerController.onStart();
        }

        public final void updateItems() {
            if (this.mDetailItems == null) {
                return;
            }
            Object obj = this.this$0.mLock;
            OplusFgsManagerController oplusFgsManagerController = this.this$0;
            synchronized (obj) {
                try {
                    List<RunningApp> listSortedWith = CollectionsKt___CollectionsKt.sortedWith(CollectionsKt___CollectionsKt.toList(oplusFgsManagerController.mRunningApps.values()), new OplusFgsManagerController$getRunningApps$lambda$12$$inlined$sortedByDescending$1(1));
                    ArrayList arrayList = new ArrayList();
                    for (RunningApp runningApp : listSortedWith) {
                        OplusQSDetailItems.Item item = new OplusQSDetailItems.Item();
                        item.tag = runningApp;
                        item.tint = true;
                        item.icon = new TileIcon(runningApp.getIcon());
                        item.line1 = runningApp.getAppLabel();
                        oplusFgsManagerController.mSystemClock.getClass();
                        item.line2 = DateUtils.formatDuration(Math.max(android.os.SystemClock.elapsedRealtime() - runningApp.getTimeStarted(), OplusFgsManagerController.MIN_TIME_DURATION), 20);
                        String string = null;
                        Integer num = runningApp.getUiControl() == UIControl.HIDE_BUTTON ? null : 2131953228;
                        if (num != null) {
                            string = oplusFgsManagerController.mContext.getString(num.intValue());
                        }
                        item.action = string;
                        item.viewType = 0;
                        arrayList.add(item);
                    }
                    OplusQSDetailItems oplusQSDetailItems = this.mDetailItems;
                    if (oplusQSDetailItems != null) {
                        oplusQSDetailItems.setListItems(0, arrayList, true);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnNumberOfPackagesChangedListener {
        void onNumberOfPackagesChanged(int i);

        default void onRunningAppsChanged(List<RunningApp> list) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StartTimeAndTokens {
        public final long startTime;
        private final SystemClock systemClock;
        private final Set<IBinder> tokens;

        public StartTimeAndTokens(SystemClock systemClock) {
            this.systemClock = systemClock;
            ((SystemClockImpl) systemClock).getClass();
            this.startTime = android.os.SystemClock.elapsedRealtime();
            this.tokens = new LinkedHashSet();
        }

        public final void addToken(IBinder iBinder) {
            this.tokens.add(iBinder);
        }

        public final SystemClock component1() {
            return this.systemClock;
        }

        public final StartTimeAndTokens copy(SystemClock systemClock) {
            return new StartTimeAndTokens(systemClock);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof StartTimeAndTokens) && Intrinsics.areEqual(this.systemClock, ((StartTimeAndTokens) obj).systemClock);
        }

        public final long getStartTime() {
            return this.startTime;
        }

        public final SystemClock getSystemClock() {
            return this.systemClock;
        }

        public final Set<IBinder> getTokens() {
            return this.tokens;
        }

        public final int hashCode() {
            return this.systemClock.hashCode();
        }

        public final boolean isEmpty() {
            return this.tokens.isEmpty();
        }

        public final void removeToken(IBinder iBinder) {
            this.tokens.remove(iBinder);
        }

        public String toString() {
            return "StartTimeAndTokens(systemClock=" + this.systemClock + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TileIcon extends QSTile.Icon {
        private final Drawable mDrawable;

        public TileIcon(Drawable drawable) {
            this.mDrawable = drawable;
        }

        public Drawable getDrawable(Context context) {
            return this.mDrawable;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UIControl {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ UIControl[] $VALUES;
        public static final UIControl HIDE_BUTTON;
        public static final UIControl HIDE_ENTRY;
        public static final UIControl NORMAL;

        static {
            UIControl uIControl = new UIControl("NORMAL", 0);
            NORMAL = uIControl;
            UIControl uIControl2 = new UIControl("HIDE_BUTTON", 1);
            HIDE_BUTTON = uIControl2;
            UIControl uIControl3 = new UIControl("HIDE_ENTRY", 2);
            HIDE_ENTRY = uIControl3;
            UIControl[] uIControlArr = {uIControl, uIControl2, uIControl3};
            $VALUES = uIControlArr;
            $ENTRIES = EnumEntriesKt.enumEntries(uIControlArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static UIControl valueOf(String str) {
            return (UIControl) Enum.valueOf(UIControl.class, str);
        }

        public static UIControl[] values() {
            return (UIControl[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UserPackage {
        private final String packageName;
        private UIControl uiControl;
        public boolean uiControlInitialized;
        private final Lazy uid$delegate;
        public final int userId;

        /* JADX INFO: renamed from: $r8$lambda$-uOABHAF3udHFGYxM9cpHW17e3I, reason: not valid java name */
        public static int m351$r8$lambda$uOABHAF3udHFGYxM9cpHW17e3I(OplusFgsManagerController oplusFgsManagerController, UserPackage userPackage) {
            try {
                return oplusFgsManagerController.mPackageManager.getPackageUidAsUser(userPackage.packageName, userPackage.userId);
            } catch (PackageManager.NameNotFoundException unused) {
                return 0;
            }
        }

        public UserPackage(int i, String str) {
            this.userId = i;
            this.packageName = str;
            OplusFgsManagerController$UserPackage$$ExternalSyntheticLambda0 oplusFgsManagerController$UserPackage$$ExternalSyntheticLambda0 = new OplusFgsManagerController$UserPackage$$ExternalSyntheticLambda0();
            oplusFgsManagerController$UserPackage$$ExternalSyntheticLambda0.f$0 = OplusFgsManagerController.this;
            oplusFgsManagerController$UserPackage$$ExternalSyntheticLambda0.f$1 = this;
            VarHandle.storeStoreFence();
            this.uid$delegate = LazyKt__LazyJVMKt.lazy(oplusFgsManagerController$UserPackage$$ExternalSyntheticLambda0);
            this.uiControl = UIControl.NORMAL;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof UserPackage)) {
                return false;
            }
            UserPackage userPackage = (UserPackage) obj;
            return Intrinsics.areEqual(userPackage.packageName, this.packageName) && userPackage.userId == this.userId;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final UIControl getUiControl() {
            if (!this.uiControlInitialized) {
                updateUiControl();
            }
            return this.uiControl;
        }

        public final int getUid() {
            return ((Number) this.uid$delegate.getValue()).intValue();
        }

        public final int getUserId() {
            return this.userId;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.userId), this.packageName);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x003f  */
        public final void updateUiControl() {
            UIControl uIControl;
            if (this.uiControlInitialized) {
                return;
            }
            OplusFgsManagerController oplusFgsManagerController = OplusFgsManagerController.this;
            int backgroundRestrictionExemptionReason = oplusFgsManagerController.mActivityManager.getBackgroundRestrictionExemptionReason(getUid());
            if (backgroundRestrictionExemptionReason != 10 && backgroundRestrictionExemptionReason != 11) {
                if (backgroundRestrictionExemptionReason != 51 && backgroundRestrictionExemptionReason != 63) {
                    if (backgroundRestrictionExemptionReason != 300 && backgroundRestrictionExemptionReason != 318 && backgroundRestrictionExemptionReason != 320 && backgroundRestrictionExemptionReason != 55 && backgroundRestrictionExemptionReason != 56) {
                        switch (backgroundRestrictionExemptionReason) {
                            case 322:
                            case 323:
                            case 324:
                                uIControl = UIControl.HIDE_BUTTON;
                                break;
                            default:
                                uIControl = UIControl.NORMAL;
                                break;
                        }
                    } else {
                        uIControl = UIControl.HIDE_BUTTON;
                    }
                } else {
                    uIControl = UIControl.HIDE_ENTRY;
                }
            } else {
                uIControl = UIControl.HIDE_BUTTON;
            }
            this.uiControl = uIControl;
            if (AppInfoUtil.isSystemApk(oplusFgsManagerController.mContext, this.packageName)) {
                this.uiControl = UIControl.HIDE_ENTRY;
            }
            this.uiControlInitialized = true;
        }
    }

    public OplusFgsManagerController(Context context, Executor executor, SystemClock systemClock, OplusQSDetailInteractor.Factory factory, IActivityManager iActivityManager, PackageManager packageManager, UserTracker userTracker) {
        this.mContext = context;
        this.mMainExecutor = executor;
        this.mSystemClock = systemClock;
        this.mActivityManager = iActivityManager;
        this.mPackageManager = packageManager;
        this.userTracker = userTracker;
        this.mUserId = context.getUserId();
        FgsListAdapter fgsListAdapter = new FgsListAdapter();
        fgsListAdapter.this$0 = this;
        VarHandle.storeStoreFence();
        this.mFgsListAdapter = fgsListAdapter;
        this.detailInteractor = factory.create(fgsListAdapter);
        this.mRunningApps = new ArrayMap<>();
        OplusFgsManagerController$userTrackerCallback$1 oplusFgsManagerController$userTrackerCallback$1 = new OplusFgsManagerController$userTrackerCallback$1();
        oplusFgsManagerController$userTrackerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userTrackerCallback = oplusFgsManagerController$userTrackerCallback$1;
        this.onNumberOfPackagesChangedListeners = new LinkedHashSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getNumRunningPackagesLocked() {
        Set<UserPackage> setKeySet = this.mRunningServiceTokens.keySet();
        int i = 0;
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            return 0;
        }
        for (UserPackage userPackage : setKeySet) {
            if (userPackage.getUiControl() != UIControl.HIDE_ENTRY && this.mUserId == userPackage.getUserId() && (i = i + 1) < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        return i;
    }

    private final void onRunningAppsChanged() {
        Iterator<T> it = this.onNumberOfPackagesChangedListeners.iterator();
        while (it.hasNext()) {
            ((OnNumberOfPackagesChangedListener) it.next()).onRunningAppsChanged(CollectionsKt___CollectionsKt.sortedWith(CollectionsKt___CollectionsKt.toList(this.mRunningApps.values()), new OplusFgsManagerController$getRunningApps$lambda$12$$inlined$sortedByDescending$1(3)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPackage(int i, String str) {
        try {
            this.mActivityManager.stopAppForUser(str, i);
        } catch (RemoteException e) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "stopPackage exception: ", e.getMessage(), LogUtil.TAG_STATUSBAR, TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAppItemsLocked() {
        if (this.hasDialog) {
            updateRunningApps();
            Executor executor = this.mMainExecutor;
            OplusFgsManagerController$init$1$1 oplusFgsManagerController$init$1$1 = new OplusFgsManagerController$init$1$1(2);
            oplusFgsManagerController$init$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(oplusFgsManagerController$init$1$1);
            if (this.hasDialog) {
                onRunningAppsChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNumberOfVisibleRunningPackagesLocked(int i) {
        this.lastNumberOfVisiblePackages = i;
        for (OnNumberOfPackagesChangedListener onNumberOfPackagesChangedListener : this.onNumberOfPackagesChangedListeners) {
            Executor executor = this.mBackgroundExecutor;
            OplusFgsManagerController$updateNumberOfVisibleRunningPackagesLocked$1$1 oplusFgsManagerController$updateNumberOfVisibleRunningPackagesLocked$1$1 = new OplusFgsManagerController$updateNumberOfVisibleRunningPackagesLocked$1$1();
            oplusFgsManagerController$updateNumberOfVisibleRunningPackagesLocked$1$1.$it = onNumberOfPackagesChangedListener;
            oplusFgsManagerController$updateNumberOfVisibleRunningPackagesLocked$1$1.$numberPackages = i;
            VarHandle.storeStoreFence();
            executor.execute(oplusFgsManagerController$updateNumberOfVisibleRunningPackagesLocked$1$1);
        }
    }

    private final void updateRunningApps() {
        ApplicationInfo applicationInfoAsUser;
        Object obj;
        CharSequence packageName;
        Drawable drawableLoadIcon;
        RunningApp runningApp;
        Iterator<T> it = this.mRunningServiceTokens.keySet().iterator();
        while (it.hasNext()) {
            ((UserPackage) it.next()).updateUiControl();
        }
        this.mRunningApps.clear();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(this.mUserId, "updateAppItemsLocked: "), null, 8);
        Set<UserPackage> setKeySet = this.mRunningServiceTokens.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : setKeySet) {
            UserPackage userPackage = (UserPackage) obj2;
            if (this.mUserId == userPackage.getUserId() && userPackage.getUiControl() != UIControl.HIDE_ENTRY && ((runningApp = this.mRunningApps.get(userPackage)) == null || !runningApp.getStopped())) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (CustomizeFeatureOption.sIsSupportProtectServices) {
            arrayList2.addAll(new OplusActivityManager().getStageProtectListFromPkg("OplusCustomizeService", 0));
            arrayList2.addAll(new OplusActivityManager().getStageProtectListFromPkg("OplusCustomizeProtectService", 0));
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            UserPackage userPackage2 = (UserPackage) arrayList.get(i);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("addedPackages packageName : ", userPackage2.getPackageName(), " , ", userPackage2.getUserId()), null, 8);
            if (this.mUserId != userPackage2.getUserId()) {
                this.mUserId = userPackage2.getUserId();
            } else if (!arrayList2.contains(userPackage2.getPackageName())) {
                try {
                    applicationInfoAsUser = this.mPackageManager.getApplicationInfoAsUser(userPackage2.getPackageName(), 0, userPackage2.getUserId());
                    try {
                        obj = Result.constructor-impl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        th = th;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    applicationInfoAsUser = null;
                }
                Throwable th3 = Result.exceptionOrNull-impl(obj);
                if (th3 != null) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("exception: ", th3.getMessage()), null, 8);
                }
                if (applicationInfoAsUser == null || (packageName = applicationInfoAsUser.loadLabel(this.mPackageManager)) == null) {
                    packageName = userPackage2.getPackageName();
                }
                CharSequence charSequence = packageName;
                Drawable drawable = (applicationInfoAsUser == null || (drawableLoadIcon = applicationInfoAsUser.loadIcon(this.mPackageManager)) == null) ? null : drawableLoadIcon;
                ArrayMap<UserPackage, RunningApp> arrayMap = this.mRunningApps;
                int userId = userPackage2.getUserId();
                String packageName2 = userPackage2.getPackageName();
                StartTimeAndTokens startTimeAndTokens = this.mRunningServiceTokens.get(userPackage2);
                startTimeAndTokens.getClass();
                arrayMap.put(userPackage2, new RunningApp(userId, packageName2, startTimeAndTokens.getStartTime(), userPackage2.getUiControl(), charSequence, drawable));
            }
            i = i2;
        }
    }

    public final void addOnNumberOfPackagesChangedListener(OnNumberOfPackagesChangedListener onNumberOfPackagesChangedListener) {
        synchronized (this.mLock) {
            this.onNumberOfPackagesChangedListeners.add(onNumberOfPackagesChangedListener);
            onNumberOfPackagesChangedListener.onNumberOfPackagesChanged(getNumRunningPackagesLocked());
            onNumberOfPackagesChangedListener.onRunningAppsChanged(CollectionsKt___CollectionsKt.sortedWith(CollectionsKt___CollectionsKt.toList(this.mRunningApps.values()), new OplusFgsManagerController$getRunningApps$lambda$12$$inlined$sortedByDescending$1(2)));
        }
    }

    public final DetailAdapter getDetailAdapter() {
        return this.mFgsListAdapter;
    }

    public final boolean getMInitialized() {
        return this.mInitialized;
    }

    public final int getNumRunningPackages() {
        int numRunningPackagesLocked;
        synchronized (this.mLock) {
            numRunningPackagesLocked = getNumRunningPackagesLocked();
        }
        return numRunningPackagesLocked;
    }

    public final Set<OnNumberOfPackagesChangedListener> getOnNumberOfPackagesChangedListeners() {
        return this.onNumberOfPackagesChangedListeners;
    }

    public final List<RunningApp> getRunningApps() {
        List<RunningApp> listSortedWith;
        synchronized (this.mLock) {
            updateRunningApps();
            listSortedWith = CollectionsKt___CollectionsKt.sortedWith(CollectionsKt___CollectionsKt.toList(this.mRunningApps.values()), new OplusFgsManagerController$getRunningApps$lambda$12$$inlined$sortedByDescending$1(0));
        }
        return listSortedWith;
    }

    public final void init() {
        synchronized (this.mLock) {
            if (this.mInitialized) {
                return;
            }
            Executor executor = this.mBackgroundExecutor;
            OplusFgsManagerController$init$1$1 oplusFgsManagerController$init$1$1 = new OplusFgsManagerController$init$1$1(0);
            oplusFgsManagerController$init$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(oplusFgsManagerController$init$1$1);
            this.userTracker.addCallback(this.userTrackerCallback, this.mBackgroundExecutor);
            this.mInitialized = true;
        }
    }

    public final boolean isFgsShow() {
        return this.mOriFgsNum > 0;
    }

    public void onForegroundStateChanged(IBinder iBinder, String str, int i, boolean z) {
        synchronized (this.mLock) {
            int numRunningPackagesLocked = getNumRunningPackagesLocked();
            String str2 = TAG;
            LogUtil.i$default(LogUtil.TAG_QS, str2, "onForegroundStateChanged: " + str + " " + i + " " + z + " " + numRunningPackagesLocked, null, 8);
            UserPackage userPackage = new UserPackage(i, str);
            if (this.mUserId != i) {
                this.mUserId = i;
                this.mRunningServiceTokens.clear();
                this.mOriFgsNum = 0;
            }
            Map<UserPackage, StartTimeAndTokens> map = this.mRunningServiceTokens;
            if (z) {
                StartTimeAndTokens startTimeAndTokens = map.get(userPackage);
                if (startTimeAndTokens == null) {
                    startTimeAndTokens = new StartTimeAndTokens(this.mSystemClock);
                    map.put(userPackage, startTimeAndTokens);
                }
                startTimeAndTokens.addToken(iBinder);
            } else {
                StartTimeAndTokens startTimeAndTokens2 = map.get(userPackage);
                if (startTimeAndTokens2 != null) {
                    startTimeAndTokens2.removeToken(iBinder);
                    if (startTimeAndTokens2.isEmpty()) {
                        this.mRunningServiceTokens.remove(userPackage);
                    }
                }
            }
            int numRunningPackagesLocked2 = getNumRunningPackagesLocked();
            LogUtil.i$default(LogUtil.TAG_QS, str2, "onForegroundStateChanged numPackagesAfter: " + numRunningPackagesLocked2, null, 8);
            if (numRunningPackagesLocked2 != numRunningPackagesLocked) {
                this.mOriFgsNum = numRunningPackagesLocked2;
                updateNumberOfVisibleRunningPackagesLocked(numRunningPackagesLocked2);
            }
            updateAppItemsLocked();
        }
    }

    public void onStart() {
        Executor executor = this.mBackgroundExecutor;
        OplusFgsManagerController$init$1$1 oplusFgsManagerController$init$1$1 = new OplusFgsManagerController$init$1$1(1);
        oplusFgsManagerController$init$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        executor.execute(oplusFgsManagerController$init$1$1);
    }

    public void onStop() {
    }

    public final void removeOnNumberOfPackagesChangedListener(OnNumberOfPackagesChangedListener onNumberOfPackagesChangedListener) {
        synchronized (this.mLock) {
            this.onNumberOfPackagesChangedListeners.remove(onNumberOfPackagesChangedListener);
        }
    }

    public final void setHasDialog(boolean z) {
        this.hasDialog = z;
    }

    public final void setMInitialized(boolean z) {
        this.mInitialized = z;
    }

    public final void showDetail() {
        this.detailInteractor.show(null);
    }
}
