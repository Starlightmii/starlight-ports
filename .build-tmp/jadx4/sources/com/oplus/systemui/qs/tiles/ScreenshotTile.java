package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.content.res.AppCompatResources;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.keyguard.data.repository.KeyguardRepository;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import com.oplus.systemui.qs.cache.BinderCacheManager;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.UUID;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScreenshotTile extends QSTileImpl {
    private static final String ACTION_SETTING = "oplus.intent.action.SCREENSHOT_SETTINGS";
    private static final String APP_ID = "20088";
    private static final String END_TIME = "endTime";
    private static final String EVENT_ID = "screenshot_performance";
    private static final String LOG_TAG = "QuickPanelScreenshotTile";
    private static final String QUICK_PANEL_SOURCE = "systemQuickTileScreenshotOut";
    private static final String QUICK_PANEL_SOURCE_AREA = "systemQuickTileArea";
    private static final String QUICK_PANEL_SOURCE_LONG = "systemQuickTileLongshot";
    private static final String QUICK_PANEL_SOURCE_SECOND = "systemQuickTileScreenshotIn";
    private static final String RELATION_ID = "RelationId";
    private static final String SCREENSHOT_DESC = "screenshotDesc";
    private static final String SCREENSHOT_PACKAGE_NAME = "com.oplus.screenshot";
    private static final String SCREENSHOT_RELATION_ID = "screenshot_relation_id";
    private static final String SCREENSHOT_SETTING_START_KEY = "startSource";
    private static final String SCREENSHOT_SETTING_START_LONG_CLICK = "qsLongClick";
    private static final String SCREENSHOT_SETTING_START_MORE_CLICK = "startSource";
    private static final String SCREENSHOT_START_TIME = "screenshot_start_time";
    private static final String START_TIME = "startTime";
    private static final String SUPPORT_META_DATA = "quickTileSupportLong";
    public static final String TILE_SPEC = "screenshot";
    private final BinderCacheManager binderCacheManager;
    private final OplusScreenshotDetailAdapter detailAdapter;
    private final OplusQSDetailInteractor detailInteractor;
    private final KeyguardRepository keyguardRepository;
    private final PanelInteractor panelInteractor;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final OplusSeparateQSManager separateQSManager;
    private final StatusBarStateController statusBarStateController;
    public static final Companion Companion = new Companion();
    private static final int[] itemRes = {2131956569, 2131956561, 2131956565};
    private static final int[] itemIconRes = {2131237215, 2131237218, 2131237202};
    private static final int[] itemIconResNight = {2131237216, 2131237219, 2131237203};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusScreenshotDetailAdapter implements DetailAdapter {
        private final Handler clickHandler;
        private final Context mainContext;

        public OplusScreenshotDetailAdapter(Context context, Handler handler) {
            this.mainContext = context;
            this.clickHandler = handler;
        }

        public static final void access$onItemClick(OplusScreenshotDetailAdapter oplusScreenshotDetailAdapter, int i) {
            ScreenshotTile screenshotTile = ScreenshotTile.this;
            long jUptimeMillis = SystemClock.uptimeMillis();
            String strReplace = StringsKt__StringsJVMKt.replace(UUID.randomUUID().toString(), "-", "", false);
            int i2 = ScreenshotTile.itemRes[i];
            if (i2 == 2131956569) {
                screenshotTile.collapseBeforeScreenshot();
                oplusScreenshotDetailAdapter.clickHandler.postDelayed(new ScreenShotRunnable(ScreenshotTile.QUICK_PANEL_SOURCE_SECOND, oplusScreenshotDetailAdapter.mainContext, jUptimeMillis, strReplace), 750L);
            } else if (i2 == 2131956561) {
                screenshotTile.collapseBeforeScreenshot();
                oplusScreenshotDetailAdapter.clickHandler.postDelayed(new ScreenShotRunnable(ScreenshotTile.QUICK_PANEL_SOURCE_LONG, oplusScreenshotDetailAdapter.mainContext, jUptimeMillis, strReplace), 750L);
            } else if (i2 == 2131956565) {
                screenshotTile.collapseBeforeScreenshot();
                oplusScreenshotDetailAdapter.clickHandler.postDelayed(new ScreenShotRunnable(ScreenshotTile.QUICK_PANEL_SOURCE_AREA, oplusScreenshotDetailAdapter.mainContext, jUptimeMillis, strReplace), 750L);
            }
        }

        public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            OplusQSDetailItems oplusQSDetailItemsConvertOrInflate = OplusQSDetailItems.convertOrInflate(context, viewGroup, z);
            if (oplusQSDetailItemsConvertOrInflate == null) {
                return null;
            }
            oplusQSDetailItemsConvertOrInflate.setTagSuffix(ScreenshotTile.TILE_SPEC);
            oplusQSDetailItemsConvertOrInflate.setTag(ScreenshotTile.TILE_SPEC);
            final boolean zIsDarkTheme = ScreenshotTile.this.detailInteractor.isDarkTheme();
            int length = ScreenshotTile.itemRes.length;
            ArrayList arrayList = new ArrayList();
            for (final int i = 0; i < length; i++) {
                OplusQSDetailItems.Item item = new OplusQSDetailItems.Item();
                item.icon = new QSTile.Icon() { // from class: com.oplus.systemui.qs.tiles.ScreenshotTile$OplusScreenshotDetailAdapter$updateItem$1$1
                    public final Drawable getDrawable(Context context2) {
                        boolean z2 = zIsDarkTheme;
                        int i2 = i;
                        return z2 ? AppCompatResources.getDrawable(context2, ScreenshotTile.itemIconResNight[i2]) : AppCompatResources.getDrawable(context2, ScreenshotTile.itemIconRes[i2]);
                    }
                };
                item.line1 = context.getString(ScreenshotTile.itemRes[i]);
                item.viewType = 10;
                arrayList.add(item);
            }
            oplusQSDetailItemsConvertOrInflate.setListItems(0, arrayList, false);
            ScreenshotTile$OplusScreenshotDetailAdapter$updateItem$2 screenshotTile$OplusScreenshotDetailAdapter$updateItem$2 = new ScreenshotTile$OplusScreenshotDetailAdapter$updateItem$2();
            screenshotTile$OplusScreenshotDetailAdapter$updateItem$2.this$0 = this;
            screenshotTile$OplusScreenshotDetailAdapter$updateItem$2.$list = arrayList;
            VarHandle.storeStoreFence();
            oplusQSDetailItemsConvertOrInflate.setCallback(screenshotTile$OplusScreenshotDetailAdapter$updateItem$2);
            return oplusQSDetailItemsConvertOrInflate;
        }

        public Intent getSettingsIntent() {
            Intent intent = new Intent();
            intent.setAction(ScreenshotTile.ACTION_SETTING);
            intent.putExtra("startSource", "startSource");
            intent.setPackage(ScreenshotTile.SCREENSHOT_PACKAGE_NAME);
            return intent;
        }

        public String getTag() {
            return ScreenshotTile.TILE_SPEC;
        }

        public CharSequence getTitle() {
            return this.mainContext.getString(2131956569);
        }

        public final boolean hasFooterDivider() {
            return DeviceUtil.isLandscape(this.mainContext);
        }
    }

    public ScreenshotTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardRepository keyguardRepository, PanelInteractor panelInteractor, OplusQSDetailInteractor.Factory factory, OplusSeparateQSManager oplusSeparateQSManager, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, BinderCacheManager binderCacheManager) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.statusBarStateController = statusBarStateController;
        this.keyguardRepository = keyguardRepository;
        this.panelInteractor = panelInteractor;
        this.separateQSManager = oplusSeparateQSManager;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.binderCacheManager = binderCacheManager;
        OplusScreenshotDetailAdapter oplusScreenshotDetailAdapter = new OplusScreenshotDetailAdapter(((QSTileImpl) this).mContext, ((QSTileImpl) this).mClickHandler);
        this.detailAdapter = oplusScreenshotDetailAdapter;
        this.detailInteractor = factory.create(oplusScreenshotDetailAdapter);
    }

    public final void collapseBeforeScreenshot() {
        if (this.statusBarStateController.getState() != 1) {
            this.panelInteractor.forceCollapsePanels();
        } else if (((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
            Message messageObtain = Message.obtain();
            collapseBeforeScreenshot.1 r2 = new collapseBeforeScreenshot.1();
            r2.this$0 = this;
            VarHandle.storeStoreFence();
            ((QSTileImpl) this).mUiHandler.executeOrSendMessage(messageObtain.setCallback(r2));
        } else {
            this.panelInteractor.animateCollapseQs();
        }
        this.detailInteractor.dismiss(true);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237217);
    }

    public Intent getLongClickIntent() {
        Intent intent = new Intent();
        intent.setAction(ACTION_SETTING);
        intent.putExtra("startSource", SCREENSHOT_SETTING_START_LONG_CLICK);
        intent.setPackage(SCREENSHOT_PACKAGE_NAME);
        return intent;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956569);
    }

    public void handleClick(Expandable expandable) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        String strReplace = StringsKt__StringsJVMKt.replace(UUID.randomUUID().toString(), "-", "", false);
        collapseBeforeScreenshot();
        this.separateQSManager.setScreenShotClick();
        ((QSTileImpl) this).mClickHandler.postDelayed(new ScreenShotRunnable(QUICK_PANEL_SOURCE, ((QSTileImpl) this).mContext, jUptimeMillis, strReplace), 650L);
    }

    public void handleSecondaryClick(Expandable expandable) {
        this.detailInteractor.show(expandable != null ? expandable.getView() : null);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        Bundle bundle;
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131956569);
        ((QSTile.AdapterState) booleanState).value = true;
        ApplicationInfo applicationInfoCache = this.binderCacheManager.getApplicationInfoCache(SCREENSHOT_PACKAGE_NAME, false);
        ((QSTile.State) booleanState).dualTarget = (applicationInfoCache == null || (bundle = applicationInfoCache.metaData) == null) ? false : bundle.getBoolean(SUPPORT_META_DATA, false);
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237217);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956569);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        ((QSTile.State) booleanState).state = 1;
        if (CustomizeFeatureOption.getForbidRecordScreenState()) {
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237214);
        }
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m346newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }

    public void preloadForSecondaryClick(Expandable expandable) {
        this.detailInteractor.prepareDetailView();
    }

    public void releasePreloadedForSecondaryClick(Expandable expandable) {
        this.detailInteractor.releaseDetailView();
    }
}
