package com.oplus.systemui.notification.lockscreen.statemanager;

import android.util.Log;
import androidx.collection.FloatList$;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.biometrics.OplusBiometricAuthController;
import com.oplus.systemui.biometrics.finger.FingerSwitchHelper;
import com.oplus.systemui.biometrics.finger.KeyguardFingerprintUtils;
import com.oplus.systemui.keyguard.ui.viewmodel.OplusKeyguardClockViewModel;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationRectManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LockScreenNotificationRectManager";
    private final Lazy ambientState;
    private final Lazy biometricAuthController;
    private final Lazy lockScreenExtStateController;
    private final NotificationKeyguardHelper notificationKeyguardHelper;
    private final Lazy npvc;
    private final Lazy nssl;
    private final Lazy nsslExt;
    private final Lazy nsslc;
    private final Lazy oplusKeyguardClockViewModel;
    public boolean rectMayChangeNextFrame;
    private final Lazy stackCardControl;
    private final SysuiStatusBarStateController statusBarStateController;
    public int expectedHeight = -1;
    private final kotlin.Lazy tmpVisibleExpandableView$delegate = LazyKt__LazyJVMKt.lazy(new LockScreenNotificationRectManager$$ExternalSyntheticLambda0());
    public int currHighFingerprintIconTop = -1;
    public int nextRequestedBasedHighFingerprintIconTop = -1;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationRectManager$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements StatusBarStateController.StateListener {
        public /* synthetic */ LockScreenNotificationRectManager this$0;

        public final void onStateChanged(int i) {
            if (i == 0) {
                this.this$0.getTmpVisibleExpandableView().clear();
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public LockScreenNotificationRectManager(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, NotificationKeyguardHelper notificationKeyguardHelper, SysuiStatusBarStateController sysuiStatusBarStateController) {
        this.npvc = lazy;
        this.lockScreenExtStateController = lazy2;
        this.ambientState = lazy3;
        this.nsslc = lazy4;
        this.oplusKeyguardClockViewModel = lazy5;
        this.stackCardControl = lazy6;
        this.biometricAuthController = lazy7;
        this.nssl = lazy8;
        this.nsslExt = lazy9;
        this.notificationKeyguardHelper = notificationKeyguardHelper;
        this.statusBarStateController = sysuiStatusBarStateController;
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = (LockScreenNotificationExtStateManager) lazy2.get();
        LockScreenNotificationRectManager$$ExternalSyntheticLambda1 lockScreenNotificationRectManager$$ExternalSyntheticLambda1 = new LockScreenNotificationRectManager$$ExternalSyntheticLambda1();
        lockScreenNotificationRectManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        lockScreenNotificationExtStateManager.setOnExtStateChanged(lockScreenNotificationRectManager$$ExternalSyntheticLambda1);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        sysuiStatusBarStateController.addCallback(anonymousClass2);
    }

    private final int getHighFingerprintIconTop() {
        return (KeyguardUtils.Companion.getRealScreenHeight() - KeyguardFingerprintUtils.INSTANCE.getIconMarginBottom()) - 87;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ExpandableView> getTmpVisibleExpandableView() {
        return (List) this.tmpVisibleExpandableView$delegate.getValue();
    }

    private final void setNextRequestedBasedHighFingerprintIconTop(int i) {
        if (this.nextRequestedBasedHighFingerprintIconTop != i) {
            if (this.currHighFingerprintIconTop != i) {
                ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
                int newState = ((ILockScreenExtStateController.ExtStateEvent) ((LockScreenNotificationExtStateManager) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getNewState();
                companion.getClass();
                if (newState == 0 || newState == 3) {
                    int i2 = this.nextRequestedBasedHighFingerprintIconTop;
                    NotificationStackScrollLayout notificationStackScrollLayout = (NotificationStackScrollLayout) this.nssl.get();
                    LockScreenNotificationRectManager$requestHeight$2 lockScreenNotificationRectManager$requestHeight$2 = new LockScreenNotificationRectManager$requestHeight$2(1);
                    lockScreenNotificationRectManager$requestHeight$2.$newExpectedHeight = i2;
                    lockScreenNotificationRectManager$requestHeight$2.this$0 = this;
                    VarHandle.storeStoreFence();
                    notificationStackScrollLayout.postOnAnimation(lockScreenNotificationRectManager$requestHeight$2);
                }
            }
            this.nextRequestedBasedHighFingerprintIconTop = i;
        }
    }

    public static void tryRequestNewHeight$default(LockScreenNotificationRectManager lockScreenNotificationRectManager, int i) {
        int iNextIndex;
        int firstStackedVisibleHeight;
        int iMax;
        int i2;
        int i3;
        int iNextIndex2;
        int newState = ((ILockScreenExtStateController.ExtStateEvent) ((LockScreenNotificationExtStateManager) lockScreenNotificationRectManager.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getNewState();
        int i4 = 0;
        boolean z = (i & 2) == 0;
        lockScreenNotificationRectManager.rectMayChangeNextFrame = false;
        if (newState != 0) {
            if (newState == 1 || newState == 2) {
                lockScreenNotificationRectManager.expectedHeight = -1;
                lockScreenNotificationRectManager.getTmpVisibleExpandableView().clear();
                List visibleChildren = ((NotificationStackScrollLayoutController) lockScreenNotificationRectManager.nsslc.get()).getView().getVisibleChildren();
                Collection tmpVisibleExpandableView = lockScreenNotificationRectManager.getTmpVisibleExpandableView();
                for (Object obj : visibleChildren) {
                    ExpandableView expandableView = (ExpandableView) obj;
                    if (!((ViewState) expandableView.getViewState()).hidden && !(expandableView instanceof EmptyShadeView)) {
                        tmpVisibleExpandableView.add(obj);
                    }
                }
                return;
            }
            if (newState != 3) {
                return;
            }
        }
        lockScreenNotificationRectManager.currHighFingerprintIconTop = lockScreenNotificationRectManager.nextRequestedBasedHighFingerprintIconTop;
        if (!z) {
            lockScreenNotificationRectManager.getTmpVisibleExpandableView().clear();
            List visibleChildren2 = ((NotificationStackScrollLayoutController) lockScreenNotificationRectManager.nsslc.get()).getView().getVisibleChildren();
            Collection tmpVisibleExpandableView2 = lockScreenNotificationRectManager.getTmpVisibleExpandableView();
            for (Object obj2 : visibleChildren2) {
                ExpandableView expandableView2 = (ExpandableView) obj2;
                if (!((ViewState) expandableView2.getViewState()).hidden && !(expandableView2 instanceof EmptyShadeView)) {
                    tmpVisibleExpandableView2.add(obj2);
                }
            }
        } else if (lockScreenNotificationRectManager.getTmpVisibleExpandableView().isEmpty()) {
            return;
        }
        ILockScreenExtStateController.Companion.getClass();
        if (newState == 0) {
            List<ExpandableView> tmpVisibleExpandableView3 = lockScreenNotificationRectManager.getTmpVisibleExpandableView();
            ListIterator<ExpandableView> listIterator = tmpVisibleExpandableView3.listIterator(tmpVisibleExpandableView3.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (listIterator.previous() instanceof OplusCustomRow) {
                        iNextIndex2 = listIterator.nextIndex();
                        break;
                    }
                } else {
                    iNextIndex2 = -1;
                    break;
                }
            }
            int i5 = -1 == iNextIndex2 ? 0 : iNextIndex2 + 1;
            int iCalculateAllExpandedHeight = lockScreenNotificationRectManager.calculateAllExpandedHeight(0, i5, tmpVisibleExpandableView3);
            int iCalculateAllExpandedHeight2 = lockScreenNotificationRectManager.calculateAllExpandedHeight(i5, tmpVisibleExpandableView3.size() - i5, tmpVisibleExpandableView3);
            int paddingBetweenElements = (i5 <= 0 || i5 >= tmpVisibleExpandableView3.size()) ? 0 : ((StackCardControl) lockScreenNotificationRectManager.stackCardControl.get()).getPaddingBetweenElements();
            if (i5 <= 0 || !lockScreenNotificationRectManager.needAvoidFingerprint()) {
                lockScreenNotificationRectManager.setNextRequestedBasedHighFingerprintIconTop(-1);
                iMax = iCalculateAllExpandedHeight + paddingBetweenElements + iCalculateAllExpandedHeight2;
            } else {
                lockScreenNotificationRectManager.setNextRequestedBasedHighFingerprintIconTop(lockScreenNotificationRectManager.getHighFingerprintIconTop());
                int iHeight = ((NotificationPanelViewController) lockScreenNotificationRectManager.npvc.get()).getView().getResources().getConfiguration().windowConfiguration.getBounds().height() - ((NotificationPanelViewController) lockScreenNotificationRectManager.npvc.get()).getExt().getKeyguardNotificationBottomPadding();
                int gapBetweenSeedlingCardAndFingerprint = ((StackCardControl) lockScreenNotificationRectManager.stackCardControl.get()).getGapBetweenSeedlingCardAndFingerprint();
                iMax = Math.max(iCalculateAllExpandedHeight2 - (gapBetweenSeedlingCardAndFingerprint - paddingBetweenElements), iHeight - lockScreenNotificationRectManager.getHighFingerprintIconTop()) + iCalculateAllExpandedHeight + gapBetweenSeedlingCardAndFingerprint;
            }
        } else {
            List<ExpandableView> tmpVisibleExpandableView4 = lockScreenNotificationRectManager.getTmpVisibleExpandableView();
            ListIterator<ExpandableView> listIterator2 = tmpVisibleExpandableView4.listIterator(tmpVisibleExpandableView4.size());
            while (true) {
                if (listIterator2.hasPrevious()) {
                    if (listIterator2.previous() instanceof OplusCustomRow) {
                        iNextIndex = listIterator2.nextIndex();
                        break;
                    }
                } else {
                    iNextIndex = -1;
                    break;
                }
            }
            int i6 = -1 == iNextIndex ? 0 : iNextIndex + 1;
            int iCalculateAllExpandedHeight3 = lockScreenNotificationRectManager.calculateAllExpandedHeight(0, i6, tmpVisibleExpandableView4);
            int size = tmpVisibleExpandableView4.size() - i6;
            if (size <= 0 || i6 < 0 || i6 + size > tmpVisibleExpandableView4.size()) {
                firstStackedVisibleHeight = 0;
            } else if (1 == size) {
                firstStackedVisibleHeight = tmpVisibleExpandableView4.get(i6).getViewState().height;
            } else {
                firstStackedVisibleHeight = 2 == size ? tmpVisibleExpandableView4.get(i6).getViewState().height + ((StackCardControl) lockScreenNotificationRectManager.stackCardControl.get()).getFirstStackedVisibleHeight() : ((StackCardControl) lockScreenNotificationRectManager.stackCardControl.get()).getSecondStackedVisibleHeight() + ((StackCardControl) lockScreenNotificationRectManager.stackCardControl.get()).getFirstStackedVisibleHeight() + tmpVisibleExpandableView4.get(i6).getViewState().height;
            }
            int paddingBetweenElements2 = (i6 <= 0 || i6 >= tmpVisibleExpandableView4.size()) ? 0 : ((StackCardControl) lockScreenNotificationRectManager.stackCardControl.get()).getPaddingBetweenElements();
            if (i6 <= 0 || !lockScreenNotificationRectManager.needAvoidFingerprint()) {
                lockScreenNotificationRectManager.setNextRequestedBasedHighFingerprintIconTop(-1);
                iMax = iCalculateAllExpandedHeight3 + paddingBetweenElements2 + firstStackedVisibleHeight;
            } else {
                lockScreenNotificationRectManager.setNextRequestedBasedHighFingerprintIconTop(lockScreenNotificationRectManager.getHighFingerprintIconTop());
                int iHeight2 = ((NotificationPanelViewController) lockScreenNotificationRectManager.npvc.get()).getView().getResources().getConfiguration().windowConfiguration.getBounds().height() - ((NotificationPanelViewController) lockScreenNotificationRectManager.npvc.get()).getExt().getKeyguardNotificationBottomPadding();
                int gapBetweenSeedlingCardAndFingerprint2 = ((StackCardControl) lockScreenNotificationRectManager.stackCardControl.get()).getGapBetweenSeedlingCardAndFingerprint();
                iMax = Math.max(firstStackedVisibleHeight - (gapBetweenSeedlingCardAndFingerprint2 - paddingBetweenElements2), iHeight2 - lockScreenNotificationRectManager.getHighFingerprintIconTop()) + iCalculateAllExpandedHeight3 + gapBetweenSeedlingCardAndFingerprint2;
            }
        }
        if (iMax < 0 || (i2 = lockScreenNotificationRectManager.expectedHeight) == iMax) {
            return;
        }
        boolean z2 = -1 == i2;
        float lockscreenNotificationDisplayHeight$default = NotificationStackScrollLayoutExt.getLockscreenNotificationDisplayHeight$default((NotificationStackScrollLayoutExt) lockScreenNotificationRectManager.nsslExt.get());
        if (!z2) {
            float f = iMax;
            z2 = (f < lockscreenNotificationDisplayHeight$default || !lockScreenNotificationRectManager.isMinClock()) && (iMax <= (i3 = lockScreenNotificationRectManager.expectedHeight) || f > lockscreenNotificationDisplayHeight$default) && (iMax >= i3 || f <= lockscreenNotificationDisplayHeight$default);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int i7 = lockScreenNotificationRectManager.expectedHeight;
            boolean zIsMinClock = lockScreenNotificationRectManager.isMinClock();
            int newState2 = ((ILockScreenExtStateController.ExtStateEvent) ((LockScreenNotificationExtStateManager) lockScreenNotificationRectManager.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getNewState();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[requestHeight] newExpectedHeight=", iMax, i7, ",lastExpectedHeight=", ", currDisplayHeight=");
            sbM.append(lockscreenNotificationDisplayHeight$default);
            sbM.append(",isMinClock=");
            sbM.append(zIsMinClock);
            sbM.append(",needReLocationClock=");
            sbM.append(z2);
            sbM.append(",extState=");
            sbM.append(newState2);
            Log.d("SystemUi--Notification", "LockScreenNotificationRectManager-->".concat(sbM.toString()));
        }
        if (!z2) {
            NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) lockScreenNotificationRectManager.npvc.get();
            notificationPanelViewController.mExt.onNotificationExpectedHeightChanged(iMax);
            notificationPanelViewController.positionClockAndNotifications(true);
            lockScreenNotificationRectManager.expectedHeight = iMax;
            return;
        }
        lockScreenNotificationRectManager.rectMayChangeNextFrame = true;
        NotificationStackScrollLayout notificationStackScrollLayout = (NotificationStackScrollLayout) lockScreenNotificationRectManager.nssl.get();
        LockScreenNotificationRectManager$requestHeight$2 lockScreenNotificationRectManager$requestHeight$2 = new LockScreenNotificationRectManager$requestHeight$2(i4);
        lockScreenNotificationRectManager$requestHeight$2.this$0 = lockScreenNotificationRectManager;
        lockScreenNotificationRectManager$requestHeight$2.$newExpectedHeight = iMax;
        VarHandle.storeStoreFence();
        notificationStackScrollLayout.postOnAnimation(lockScreenNotificationRectManager$requestHeight$2);
    }

    public final int calculateAllExpandedHeight(int i, int i2, List list) {
        int i3;
        if (i2 <= 0 || i < 0 || (i3 = i + i2) > list.size()) {
            return 0;
        }
        if (1 == i2) {
            return ((ExpandableView) list.get(i)).getViewState().height;
        }
        int paddingBetweenElements = ((StackCardControl) this.stackCardControl.get()).getPaddingBetweenElements() * (i2 - 1);
        while (i < i3) {
            paddingBetweenElements += ((ExpandableView) list.get(i)).getViewState().height;
            i++;
        }
        return paddingBetweenElements;
    }

    public final int getCurrHighFingerprintIconTop() {
        return this.currHighFingerprintIconTop;
    }

    public final boolean getRectMayChangeNextFrame() {
        return this.rectMayChangeNextFrame;
    }

    public final boolean isMinClock() {
        return ((OplusKeyguardClockViewModel.ClockSizeState) ((OplusKeyguardClockViewModel) this.oplusKeyguardClockViewModel.get()).getClockSizeStateFlow().getValue()).getClockSize() == ClockSize.SMALL && !((OplusKeyguardClockViewModel.ClockSizeState) ((OplusKeyguardClockViewModel) this.oplusKeyguardClockViewModel.get()).getClockSizeStateFlow().getValue()).getSuppressSmallSizeDeliver();
    }

    public final boolean needAvoidFingerprint() {
        OplusBiometricAuthController oplusBiometricAuthController = (OplusBiometricAuthController) this.biometricAuthController.get();
        return oplusBiometricAuthController.isNeedNotificationOverlayFingerPrint() && oplusBiometricAuthController.getFpType(true) == 2 && FingerSwitchHelper.Companion.getInstance(((NotificationStackScrollLayout) this.nssl.get()).getContext()).isFingerprintUnlockEnable();
    }
}
