package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.ui.spatial.RectList$;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dumpable;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.ShouldShowForDisplay;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsulePluginContainerController extends ViewController implements ConfigurationController.ConfigurationListener, Listener, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String HIDDEN_ICON_LIST = "hiddenIconList";
    public static final String TAG = "CapsulePluginContainerController";
    private ViewGroup capsuleFakeContainer;
    private final CapsulePluginContainer capsulePluginContainer;
    private ConcurrentHashMap<StatusBarLocation, Integer> capsuleWithMap;
    private final Lazy centralSurfacesImpl;
    private final SeedlingPlugin.ChangeListener changeListener;
    private final ConfigurationController configurationController;
    private final DumpManager dumpManager;
    private final HiddenIconDispatcher hiddenIconDispatcher;
    private DisposableHandle mLiverAlertDisposableHandle;
    private final OplusLiveAlertViewModel oplusLiveAlertViewModel;
    private PhoneStatusBarView phoneStatusBarView;
    public int screenWidth;
    private final SeedlingPluginManager seedlingPluginManager;
    public final boolean shouldOccupy;
    private final ShouldShowForDisplay shouldShowForDisplay;
    public final int statusBarHeight;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.CapsulePluginContainerController$hiddenStatusBarIconView$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ ArrayList $hiddenIcons;
        public /* synthetic */ CapsulePluginContainerController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.hiddenIconDispatcher.setHiddenStatusBarIcon(StatusBarLocation.HOME, this.$hiddenIcons);
            this.this$0.hiddenIconDispatcher.setHiddenStatusBarIcon(StatusBarLocation.KEYGUARD, this.$hiddenIcons);
        }
    }

    public CapsulePluginContainerController(SeedlingPluginManager seedlingPluginManager, ConfigurationController configurationController, CapsulePluginContainer capsulePluginContainer, HiddenIconDispatcher hiddenIconDispatcher, DumpManager dumpManager, OplusLiveAlertViewModel oplusLiveAlertViewModel, Lazy lazy, ShouldShowForDisplay shouldShowForDisplay) {
        super(capsulePluginContainer);
        this.seedlingPluginManager = seedlingPluginManager;
        this.configurationController = configurationController;
        this.capsulePluginContainer = capsulePluginContainer;
        this.hiddenIconDispatcher = hiddenIconDispatcher;
        this.dumpManager = dumpManager;
        this.oplusLiveAlertViewModel = oplusLiveAlertViewModel;
        this.centralSurfacesImpl = lazy;
        this.shouldShowForDisplay = shouldShowForDisplay;
        this.capsuleWithMap = new ConcurrentHashMap<>();
        this.statusBarHeight = SystemBarUtils.getStatusBarHeight(getContext());
        this.screenWidth = capsulePluginContainer.getContext().getResources().getConfiguration().windowConfiguration.getBounds().right;
        this.shouldOccupy = true;
        CapsulePluginContainerController$changeListener$1 capsulePluginContainerController$changeListener$1 = new CapsulePluginContainerController$changeListener$1();
        capsulePluginContainerController$changeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.changeListener = capsulePluginContainerController$changeListener$1;
    }

    public final void createCapsuleFakeContainer(SeedlingPlugin seedlingPlugin) {
        NotificationShadeWindowView notificationShadeWindowView;
        NotificationShadeWindowView notificationShadeWindowView2;
        ViewStub viewStub;
        if (seedlingPlugin == null || !SeedlingPluginManager.Companion.isSupportCapsuleFake(getContext())) {
            return;
        }
        ViewGroup viewGroup = this.capsuleFakeContainer;
        if (viewGroup == null) {
            CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) this.centralSurfacesImpl.get();
            View viewInflate = (centralSurfacesImpl == null || (notificationShadeWindowView2 = centralSurfacesImpl.getNotificationShadeWindowView()) == null || (viewStub = (ViewStub) notificationShadeWindowView2.findViewById(2131365360)) == null) ? null : viewStub.inflate();
            viewGroup = viewInflate instanceof ViewGroup ? (ViewGroup) viewInflate : null;
            if (viewGroup == null) {
                CentralSurfacesImpl centralSurfacesImpl2 = (CentralSurfacesImpl) this.centralSurfacesImpl.get();
                viewGroup = (centralSurfacesImpl2 == null || (notificationShadeWindowView = centralSurfacesImpl2.getNotificationShadeWindowView()) == null) ? null : (ViewGroup) notificationShadeWindowView.findViewById(2131362470);
                if (viewGroup == null) {
                    viewGroup = null;
                }
            }
        }
        this.capsuleFakeContainer = viewGroup;
        if (viewGroup != null) {
            seedlingPlugin.onCreateView(4, viewGroup);
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("CapsulePluginContainerController  state: ");
        printWriter.println("  withMap: " + this.capsuleWithMap);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  sbH: ", this.statusBarHeight, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  screenWidth: ", this.screenWidth, printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  shouldOccupy: ", this.shouldOccupy);
    }

    public final void hiddenStatusBarIconView(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList(HIDDEN_ICON_LIST);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "hiddenStatusBarIconView: " + stringArrayList, null, 8);
        if (!((CapsulePluginContainer) ((ViewController) this).mView).isAttachedToWindow()) {
            this.hiddenIconDispatcher.setHiddenStatusBarIcon(StatusBarLocation.HOME, stringArrayList);
            this.hiddenIconDispatcher.setHiddenStatusBarIcon(StatusBarLocation.KEYGUARD, stringArrayList);
            return;
        }
        CapsulePluginContainer capsulePluginContainer = (CapsulePluginContainer) ((ViewController) this).mView;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$hiddenIcons = stringArrayList;
        VarHandle.storeStoreFence();
        capsulePluginContainer.post(anonymousClass1);
    }

    public final void notifyRect(StatusBarLocation statusBarLocation) {
        Rect rect;
        Integer num = this.capsuleWithMap.get(statusBarLocation);
        int iIntValue = num != null ? num.intValue() : 0;
        boolean z = this.shouldOccupy;
        int i = this.statusBarHeight;
        if (z) {
            int i2 = this.screenWidth;
            rect = new Rect((i2 - iIntValue) / 2, 0, (i2 + iIntValue) / 2, i);
        } else {
            rect = new Rect();
        }
        setHiddenIconAreas$1(statusBarLocation, CollectionsKt__CollectionsJVMKt.listOf(rect));
        int i3 = this.screenWidth;
        StringBuilder sb = new StringBuilder("location： ");
        sb.append(statusBarLocation);
        sb.append(", screenWidth: ");
        sb.append(i3);
        sb.append(", capsuleWith: ");
        RectList$.ExternalSyntheticOutline0.m(iIntValue, i, ", statusBarHeight: ", ", rect: ", sb);
        sb.append(rect);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sb.toString(), null, 8);
    }

    public void onConfigChanged(Configuration configuration) {
        if (configuration != null) {
            int i = this.capsulePluginContainer.getContext().getResources().getConfiguration().windowConfiguration.getBounds().right;
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(this.screenWidth, i, "onConfigChanged ", ", "), null, 8);
            if (i != this.screenWidth) {
                this.screenWidth = i;
                notifyRect(StatusBarLocation.HOME);
                notifyRect(StatusBarLocation.KEYGUARD);
            }
        }
    }

    public final void onDensityOrFontScaleChanged() {
        ViewGroup viewGroup = this.capsuleFakeContainer;
        if (viewGroup != null) {
            int statusBarHeight = SystemBarUtils.getStatusBarHeight(getContext());
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = -1;
                layoutParams.height = statusBarHeight;
            } else {
                layoutParams = new ViewGroup.LayoutParams(-1, statusBarHeight);
            }
            viewGroup.setLayoutParams(layoutParams);
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onDispatchTouchEvent(MotionEvent motionEvent) {
        Gefingerpoken gefingerpoken;
        if ((motionEvent != null && motionEvent.getAction() == 0) || ((motionEvent != null && motionEvent.getAction() == 1) || (motionEvent != null && motionEvent.getAction() == 3))) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onDispatchTouchEvent: " + this.phoneStatusBarView + " -> " + motionEvent, null, 8);
        }
        PhoneStatusBarView phoneStatusBarView = this.phoneStatusBarView;
        if (phoneStatusBarView == null || (gefingerpoken = phoneStatusBarView.mTouchEventHandler) == null) {
            return;
        }
        gefingerpoken.onTouchEvent(motionEvent);
    }

    public final void onInit() {
        PhoneStatusBarView parent = ((CapsulePluginContainer) ((ViewController) this).mView).getParent();
        this.phoneStatusBarView = parent instanceof PhoneStatusBarView ? parent : null;
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context) {
        if (this.shouldShowForDisplay.getShowSeedling()) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onPluginAdded", null, 8);
            if (seedlingPlugin != null) {
                seedlingPlugin.onCreateView(0, this.capsulePluginContainer);
            }
            createCapsuleFakeContainer(seedlingPlugin);
            if (seedlingPlugin != null) {
                seedlingPlugin.addChangeListener(this.changeListener);
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginRemove(SeedlingPlugin seedlingPlugin) {
        if (this.shouldShowForDisplay.getShowSeedling()) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onPluginRemove", null, 8);
            this.capsulePluginContainer.removeAllViews();
            this.capsuleWithMap.clear();
            setHiddenIconAreas$1(StatusBarLocation.HOME, CollectionsKt__CollectionsJVMKt.listOf(new Rect()));
            setHiddenIconAreas$1(StatusBarLocation.KEYGUARD, CollectionsKt__CollectionsJVMKt.listOf(new Rect()));
            if (seedlingPlugin != null) {
                seedlingPlugin.removeChangeListener(this.changeListener);
            }
            ViewGroup viewGroup = this.capsuleFakeContainer;
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
        }
    }

    public final void onSeamlessCollapseEnd() {
        this.seedlingPluginManager.onSeamlessCollapseEnd();
    }

    public final void onSeamlessExpandStart() {
        this.seedlingPluginManager.onSeamlessExpandStart();
    }

    public final void onViewAttached() {
        if (!this.shouldShowForDisplay.getShowSeedling()) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(this.capsulePluginContainer.getContext().getDisplayId(), "onViewAttached skip seedling bind on display "), null, 8);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onViewAttached " + this.seedlingPluginManager.getSeedlingPlugin(), null, 8);
        this.configurationController.addCallback(this);
        this.seedlingPluginManager.addCallback(this);
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.isStatusBarChipSupport) {
            this.mLiverAlertDisposableHandle = OplusLiveAlertAdapter.INSTANCE.bind(this.oplusLiveAlertViewModel, this.capsulePluginContainer);
        }
        SeedlingPluginManager seedlingPluginManager = this.seedlingPluginManager;
        if (seedlingPluginManager.isPluginAvailable) {
            SeedlingPlugin seedlingPlugin = seedlingPluginManager.getSeedlingPlugin();
            if (seedlingPlugin != null) {
                seedlingPlugin.onCreateView(0, this.capsulePluginContainer);
            }
            createCapsuleFakeContainer(this.seedlingPluginManager.getSeedlingPlugin());
            SeedlingPlugin seedlingPlugin2 = this.seedlingPluginManager.getSeedlingPlugin();
            if (seedlingPlugin2 != null) {
                seedlingPlugin2.addChangeListener(this.changeListener);
            }
        }
        this.dumpManager.registerDumpable(this);
        onConfigChanged(this.capsulePluginContainer.getContext().getResources().getConfiguration());
    }

    public final void onViewDetached() {
        if (this.shouldShowForDisplay.getShowSeedling()) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onViewDetached", null, 8);
            SeedlingPlugin seedlingPlugin = this.seedlingPluginManager.getSeedlingPlugin();
            if (seedlingPlugin != null) {
                seedlingPlugin.removeChangeListener(this.changeListener);
            }
            this.configurationController.removeCallback(this);
            this.seedlingPluginManager.removeCallback(this);
            this.dumpManager.unregisterDumpable(TAG);
            DisposableHandle disposableHandle = this.mLiverAlertDisposableHandle;
            if (disposableHandle != null) {
                disposableHandle.dispose();
            }
            this.mLiverAlertDisposableHandle = null;
        }
    }

    public final void setCapsuleFakeContainerAlpha(float f) {
        ViewGroup viewGroup = this.capsuleFakeContainer;
        if (viewGroup != null) {
            viewGroup.setAlpha(f);
        }
    }

    public final void setCapsuleFakeContainerVisibility(boolean z) {
        ViewGroup viewGroup = this.capsuleFakeContainer;
        if (viewGroup != null) {
            viewGroup.setVisibility(z ? 0 : 4);
        }
    }

    public final void setHiddenIconAreas$1(StatusBarLocation statusBarLocation, List list) {
        if (!((CapsulePluginContainer) ((ViewController) this).mView).isAttachedToWindow()) {
            this.hiddenIconDispatcher.setHiddenIconAreas(statusBarLocation, list);
            return;
        }
        CapsulePluginContainer capsulePluginContainer = (CapsulePluginContainer) ((ViewController) this).mView;
        CapsulePluginContainerController$setHiddenIconAreas$1 capsulePluginContainerController$setHiddenIconAreas$1 = new CapsulePluginContainerController$setHiddenIconAreas$1();
        capsulePluginContainerController$setHiddenIconAreas$1.this$0 = this;
        capsulePluginContainerController$setHiddenIconAreas$1.$location = statusBarLocation;
        capsulePluginContainerController$setHiddenIconAreas$1.$areas = list;
        VarHandle.storeStoreFence();
        capsulePluginContainer.post(capsulePluginContainerController$setHiddenIconAreas$1);
    }
}
