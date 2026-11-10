package com.oplus.systemui.plugins.qs.dagger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.COUIRecyclerView;
import com.android.systemui.plugins.qs.IQSPanelPluginScanner;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSFakeStatusElement;
import com.android.systemui.plugins.qs.QSFgs;
import com.android.systemui.plugins.qs.QSPrivary;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.android.systemui.plugins.qs.QSSecurity;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.privacy.ui.view.OplusQSPrivacyContainerView;
import com.oplus.systemui.plugins.qs.quickentrance.ui.view.OplusQSQuickEntranceContainerView;
import com.oplus.systemui.qs.base.widget.OplusQSPageIndicator;
import com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface SeparateQSViewModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final CellAreaProvider provideCellAreaProvider(OplusQSRootView oplusQSRootView) {
            return new CellAreaProvider(oplusQSRootView);
        }

        public final Context provideContext(OplusQSRootView oplusQSRootView) {
            return oplusQSRootView.getContext();
        }

        public final FrameLayout provideQSBottomContainer(OplusQSRootView oplusQSRootView) {
            return (FrameLayout) oplusQSRootView.requireViewById(2131364712);
        }

        public final QSFakeStatusElement provideQSFakeCarrier(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSFakeElement("fake_carrier", context);
        }

        public final QSFakeStatusElement provideQSFakeClock(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSFakeElement("fake_clock", context);
        }

        public final QSFakeStatusElement provideQSFakeKeyguardCarrier(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSFakeElement("keyguard_fake_carrier", context);
        }

        public final QSFakeStatusElement provideQSFakeNotification(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSFakeElement("fake_notification", context);
        }

        public final QSFakeStatusElement provideQSFakePrivacy(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSFakeElement("fake_pricacy", context);
        }

        public final QSFakeStatusElement provideQSFakeStatus(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSFakeElement("fake_status", context);
        }

        public final QSFgs provideQSFgs(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.bindFgs();
        }

        public final QSFakeStatusElement provideQSKeyguardFakeStatus(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSFakeElement("keyguard_fake_status", context);
        }

        public final QSElement provideQSMedia(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSElement(OplusNotificationTemplateViewWrapperExImpl.MEDIA_TAG, context);
        }

        public final IQSPanelPluginScanner provideQSMyDevice(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.bindQSPanelPluginScanner();
        }

        public final QSPrivary provideQSPrivary(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.bindPrivary();
        }

        public final QSQuickEntrance provideQSQuickEntrance(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.bindQuickEntrance();
        }

        public final QSSecurity provideQSSecurity(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.bindQSSecurity();
        }

        public final QSElement provideQSStatusBarHeader(OplusSeparateQSPlugin.IStub iStub, Context context) {
            return iStub.bindQSElement("header", context);
        }

        public final QSTileQueryHelper provideQSTileQueryHelper(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.bindQSTileQueryHelper();
        }

        public final QSVolume provideQSVolume(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.bindQSVolume();
        }

        public final RecyclerViewDragHelper provideRecyclerViewDragHelper(OplusQSRootView oplusQSRootView) {
            return new RecyclerViewDragHelper(oplusQSRootView);
        }

        public final COUIRecyclerView providersLeftBTilesContainer(OplusQSRootView oplusQSRootView) {
            return oplusQSRootView.requireViewById(2131364436);
        }

        public final OplusQSPageIndicator providesIndicator(OplusQSRootView oplusQSRootView) {
            return (OplusQSPageIndicator) oplusQSRootView.requireViewById(2131364787);
        }

        public final COUIRecyclerView providesOtherTilesContainer(OplusQSRootView oplusQSRootView) {
            return oplusQSRootView.requireViewById(2131364435);
        }

        public final ConstraintLayout providesPanelContainer(OplusQSRootView oplusQSRootView) {
            return oplusQSRootView.requireViewById(2131364722);
        }

        public final COUIRecyclerView providesPersonalContainer(OplusQSRootView oplusQSRootView) {
            return oplusQSRootView.requireViewById(2131364532);
        }

        public final OplusQSPrivacyContainerView providesQSPrivacyContainerView(OplusQSRootView oplusQSRootView) {
            return (OplusQSPrivacyContainerView) LayoutInflater.from(oplusQSRootView.getContext()).inflate(2131559354, (ViewGroup) null, false);
        }

        public final OplusQSQuickEntranceContainerView providesQSQuickEntranceContainerView(OplusQSRootView oplusQSRootView) {
            return (OplusQSQuickEntranceContainerView) oplusQSRootView.requireViewById(2131364792);
        }

        public final FrameLayout providesSeparaQsFakeView(OplusQSRootView oplusQSRootView) {
            return (FrameLayout) oplusQSRootView.requireViewById(2131365150);
        }

        public final ViewGroup providesStatusBarContainer(OplusQSRootView oplusQSRootView) {
            return (ViewGroup) oplusQSRootView.requireViewById(2131364804);
        }
    }
}
