package com.oplus.systemui.qs.tiles;

import android.R;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.OplusOSTelephonyManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.exifinterface.media.ExifInterface$;
import androidx.lifecycle.Lifecycle;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import com.android.settingslib.net.DataUsageController;
import com.android.systemui.Dependency;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.biometrics.BiometricNotificationDialogFactory$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.data.utils.QSFlowExKt;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.telephony.domain.interactor.TelephonyInteractor;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SettingObserver;
import com.android.systemui.util.settings.SystemSettings;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.oplus.systemui.keyguard.telephony.SimStateHelper;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.qs.OplusDataUsageController;
import com.oplus.systemui.qs.OplusQSCarrierTextController;
import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.detail.adapter.QSTileInlineDetailAdapter;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper$configCellularDetailView$1;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusCellularTile extends CellularTile implements TelephonyCallback.ActiveDataSubscriptionIdListener {
    public int mActiveDataSlot;
    public final AirplaneModeRepository mAirplaneModeRepository;
    public final CoroutineContext mBgCoroutineContext;
    public KeyguardUpdateMonitorCallback mCallback;
    public final CharSequence[] mCarrierDataUsage;
    public final int[] mCarrierIcon;
    public final CharSequence[] mCarrierLineNumber;
    public final CharSequence[] mCarrierPhoneNumber;
    public final AnonymousClass4 mDataPrimaryObserver;
    public final AnonymousClass3 mDateUsageSetting;
    public final DetailAdapter mDetailAdapter;
    public final OplusQSDetailInteractor mDetailInteractor;
    public final CharSequence[] mDisplayName;
    public boolean mHasHint;
    public volatile boolean mIsDetailValid;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public volatile boolean mLockSimState;
    public final Handler mMainLooperHandler;
    public AnonymousClass5 mMobileDataObserver;
    public final OplusDataUsageController mOplusDataUsageController;
    public final SatellitePolicy mOplusSatellitePolicy;
    public final OplusSeparateNotificationAndQSState mOplusSeparateNotificationAndQSState;
    public final PanelInteractor mPanelInteractor;
    public final AnonymousClass1 mSatellitePolicyCallback;
    public final int[] mSimState;
    public final ArrayList mSubIdList;
    public final TelephonyInteractor mTelephonyInteractor;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TelephonyManager mTelephonyManager;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusCellularTile$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements SatellitePolicy.IConnectionCallback {
        public /* synthetic */ OplusCellularTile this$0;

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public /* bridge */ /* synthetic */ void onCarrierNameChanged(String str) {
            super.onCarrierNameChanged(str);
        }

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onSatelliteConnected() {
            this.this$0.refreshState((Object) null);
        }

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onSatelliteDisConnected() {
            this.this$0.refreshState((Object) null);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusCellularTile$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ OplusCellularTile this$0;

        public final void onRefreshCarrierInfo() {
            OplusCellularTile$2$$ExternalSyntheticLambda0 oplusCellularTile$2$$ExternalSyntheticLambda0 = new OplusCellularTile$2$$ExternalSyntheticLambda0(0);
            oplusCellularTile$2$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(oplusCellularTile$2$$ExternalSyntheticLambda0);
        }

        public final void onSimStateChanged(int i, int i2, int i3) {
            if (!CustomizeFeatureOption.checkSlotIdValid(i2) ? false : CustomizeFeatureOption.sIsSupportShowLineNumberCust[i2]) {
                String str = ((QSTileImpl) this.this$0).TAG;
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onSimStateChanged(subId=", i, i2, ", slotId=", ",state=");
                sbM.append(i3);
                sbM.append(")");
                LogUtil.i(LogUtil.TAG_QS, str, sbM.toString());
                OplusCellularTile$2$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusCellularTile$2$.ExternalSyntheticLambda1();
                externalSyntheticLambda1.f$0 = this;
                externalSyntheticLambda1.f$1 = i;
                externalSyntheticLambda1.f$2 = i2;
                VarHandle.storeStoreFence();
                KeyguardThreadUtil.runOnWorkThread(externalSyntheticLambda1);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusCellularTile$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends UserSettingObserver {
        public final void handleValueChanged(int i, boolean z) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CellularDetailAdapter implements DetailAdapter, OplusQSDetailItems.Callback {
        public OplusQSDetailItems mItems;
        public /* synthetic */ OplusCellularTile this$0;

        public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            OplusQSDetailItems oplusQSDetailItemsConvertOrInflate = OplusQSDetailItems.convertOrInflate(context, viewGroup, z);
            this.mItems = oplusQSDetailItemsConvertOrInflate;
            oplusQSDetailItemsConvertOrInflate.setTagSuffix("cellular");
            this.mItems.setTag("cellular");
            this.mItems.setCallback(this);
            updateItem$1();
            return this.mItems;
        }

        public DetailAdapter.DetailHeightType getDetailHeightType() {
            return DetailAdapter.DetailHeightType.CHANGE;
        }

        public int getMetricsCategory() {
            return 115;
        }

        public Intent getSettingsIntent() {
            return OplusCellularTile.getCellularSettingIntent();
        }

        public String getTag() {
            return OplusQSHighlightTileViewLabelTextHandler.CELL;
        }

        public CharSequence getTitle() {
            return ((QSTileImpl) this.this$0).mContext.getString(2131955756);
        }

        public Boolean getToggleState() {
            DataUsageController dataUsageController = this.this$0.mDataController;
            return Boolean.valueOf(dataUsageController.isMobileDataSupported() && dataUsageController.getTelephonyManager().isDataEnabled());
        }

        public final boolean hasFooterDivider() {
            return DeviceUtil.isLandscape(((QSTileImpl) this.this$0).mContext);
        }

        public final boolean hasHint() {
            updateItem$1();
            return this.this$0.mHasHint;
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public final void onDetailItemClick(OplusQSDetailItems.Item item) {
            Object obj;
            if (item == null || (obj = item.tag) == null || !(obj instanceof Integer)) {
                return;
            }
            this.this$0.setPrimarySlotId(((Integer) obj).intValue());
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public final void onDetailItemDisconnect(OplusQSDetailItems.Item item) {
        }

        public final void onDismiss() {
            this.this$0.setDetailListening(false);
        }

        public final void onShow() {
            this.this$0.setDetailListening(true);
        }

        public void setToggleState(boolean z) {
            OplusCellularTile.m330$$Nest$monDetailAdapterSetToggleState(this.this$0, z);
        }

        public final void updateItem$1() {
            OplusCellularTile oplusCellularTile = this.this$0;
            if (this.mItems == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            int primarySlotId = oplusCellularTile.getPrimarySlotId();
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(primarySlotId, "updateItem: defaultDataSlotId=", LogUtil.TAG_QS, ((QSTileImpl) oplusCellularTile).TAG);
            int i = 0;
            while (true) {
                CharSequence[] charSequenceArr = oplusCellularTile.mDisplayName;
                if (i >= charSequenceArr.length) {
                    break;
                }
                CharSequence charSequence = charSequenceArr[i];
                if (!TextUtils.isEmpty(charSequence)) {
                    OplusQSDetailItems.Item item = new OplusQSDetailItems.Item();
                    item.line1 = charSequence;
                    if (TextUtils.isEmpty(oplusCellularTile.mCarrierPhoneNumber[i])) {
                        item.line2 = ((QSTileImpl) oplusCellularTile).mContext.getResources().getString(2131955693);
                    } else {
                        item.line2 = oplusCellularTile.mCarrierPhoneNumber[i];
                    }
                    item.iconResId = oplusCellularTile.mCarrierIcon[i];
                    item.line3 = oplusCellularTile.mCarrierDataUsage[i];
                    item.viewType = 5;
                    item.active = primarySlotId == i;
                    item.tag = Integer.valueOf(i);
                    item.select = true;
                    arrayList.add(item);
                }
                i++;
            }
            oplusCellularTile.mHasHint = arrayList.size() >= 2;
            if (!DeviceUtil.isLandscape(((QSTileImpl) oplusCellularTile).mContext) || !oplusCellularTile.mHasHint) {
                this.mItems.setListItems(0, arrayList, false);
                return;
            }
            oplusCellularTile.mHasHint = false;
            OplusQSDetailItems.Item item2 = new OplusQSDetailItems.Item();
            item2.viewType = 9;
            item2.tintText = ((QSTileImpl) oplusCellularTile).mContext.getResources().getString(2131955692);
            arrayList.add(item2);
            this.mItems.setListItems(0, arrayList, false);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CellularInlineDetailAdapter implements DetailAdapter {
        public QSTileInlineDetailAdapter mDelegate;
        public /* synthetic */ OplusCellularTile this$0;

        public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            return this.mDelegate.createDetailView(context, viewGroup, z);
        }

        public DetailAdapter.DetailHeightType getDetailHeightType() {
            return DetailAdapter.DetailHeightType.IMMUTABLE_ONE;
        }

        public Intent getSettingsIntent() {
            return OplusCellularTile.getCellularSettingIntent();
        }

        public String getTag() {
            return OplusQSHighlightTileViewLabelTextHandler.CELL;
        }

        public CharSequence getTitle() {
            return ((QSTileImpl) this.this$0).mContext.getString(2131955756);
        }

        public Boolean getToggleState() {
            DataUsageController dataUsageController = this.this$0.mDataController;
            return Boolean.valueOf(dataUsageController.isMobileDataSupported() && dataUsageController.getTelephonyManager().isDataEnabled());
        }

        public final boolean hasFooterDivider() {
            return DeviceUtil.isLandscape(((QSTileImpl) this.this$0).mContext);
        }

        public void setToggleState(boolean z) {
            OplusCellularTile.m330$$Nest$monDetailAdapterSetToggleState(this.this$0, z);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$monDetailAdapterSetToggleState, reason: not valid java name */
    public static void m330$$Nest$monDetailAdapterSetToggleState(OplusCellularTile oplusCellularTile, boolean z) {
        MetricsLogger.action(((QSTileImpl) oplusCellularTile).mContext, 155, z);
        if (z) {
            int primarySlotId = oplusCellularTile.getPrimarySlotId();
            if (!CustomizeFeatureOption.checkSlotIdValid(primarySlotId) ? false : CustomizeFeatureOption.sIsSupportShowDataPrompting[primarySlotId]) {
                oplusCellularTile.maybeShowEnableDialog();
                oplusCellularTile.refreshState((Object) null);
                PanelInteractorImpl panelInteractorImpl = oplusCellularTile.mPanelInteractor;
                if (panelInteractorImpl != null) {
                    panelInteractorImpl.collapsePanels();
                    return;
                }
                return;
            }
        }
        oplusCellularTile.onDetailToggle(Boolean.valueOf(z));
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.oplus.systemui.qs.tiles.OplusCellularTile$4] */
    public OplusCellularTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, NetworkController networkController, KeyguardStateController keyguardStateController, PanelInteractor panelInteractor, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, SatellitePolicy satellitePolicy, SystemSettings systemSettings, GlobalSettings globalSettings, KeyguardUpdateMonitor keyguardUpdateMonitor, TelephonyListenerManager telephonyListenerManager, AirplaneModeRepository airplaneModeRepository, CoroutineContext coroutineContext, TelephonyInteractor telephonyInteractor, OplusQSDetailInteractor.Factory factory, QSTileInlineDetailAdapter.Factory factory2) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, networkController, keyguardStateController);
        this.mSubIdList = new ArrayList();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSatellitePolicyCallback = anonymousClass1;
        this.mLockSimState = false;
        this.mIsDetailValid = false;
        this.mHasHint = true;
        this.mActiveDataSlot = -1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = anonymousClass2;
        this.mOplusDataUsageController = new OplusDataUsageController(((QSTileImpl) this).mContext, networkController);
        this.mTelephonyListenerManager = telephonyListenerManager;
        if (SystemUIInlineViewHelper.isSupportCellularDetail(((QSTileImpl) this).mContext)) {
            SystemUIInlineViewHelper.setComponentProvider("oplus.inlineui.category.QS_DETAIL_SIM_SETTINGS", SystemUIInlineViewHelper$configCellularDetailView$1.INSTANCE);
            SystemUIInlineViewHelper.setViewFactory$default("oplus.inlineui.category.QS_DETAIL_SIM_SETTINGS");
            QSTileInlineDetailAdapter qSTileInlineDetailAdapterCreate = factory2.create(OplusQSHighlightTileViewLabelTextHandler.CELL, getCellularSettingIntent());
            CellularInlineDetailAdapter cellularInlineDetailAdapter = new CellularInlineDetailAdapter();
            cellularInlineDetailAdapter.this$0 = this;
            cellularInlineDetailAdapter.mDelegate = qSTileInlineDetailAdapterCreate;
            VarHandle.storeStoreFence();
            this.mDetailAdapter = cellularInlineDetailAdapter;
        } else {
            CellularDetailAdapter cellularDetailAdapter = new CellularDetailAdapter();
            cellularDetailAdapter.this$0 = this;
            VarHandle.storeStoreFence();
            this.mDetailAdapter = cellularDetailAdapter;
        }
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mMainLooperHandler = new Handler(Looper.getMainLooper());
        this.mDisplayName = new CharSequence[2];
        this.mCarrierPhoneNumber = new CharSequence[2];
        this.mCarrierLineNumber = new CharSequence[2];
        this.mCarrierDataUsage = new CharSequence[2];
        this.mCarrierIcon = new int[2];
        this.mSimState = new int[2];
        this.mTelephonyManager = TelephonyManager.from(((QSTileImpl) this).mContext);
        this.mPanelInteractor = panelInteractor;
        this.mOplusSeparateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.mOplusSatellitePolicy = satellitePolicy;
        this.mDateUsageSetting = new AnonymousClass3(systemSettings, ((QSTileImpl) this).mHandler, "oplus_customize_disaplay_calculate_data_traffic", qSHost.getUserContext().getUserId(), 0);
        this.mDataPrimaryObserver = new SettingObserver(globalSettings, ((QSTileImpl) this).mHandler) { // from class: com.oplus.systemui.qs.tiles.OplusCellularTile.4
            public final void handleValueChanged(int i, boolean z) {
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "PrimarySlotId changed:value=", LogUtil.TAG_QS, ((QSTileImpl) OplusCellularTile.this).TAG);
            }
        };
        this.mAirplaneModeRepository = airplaneModeRepository;
        this.mBgCoroutineContext = coroutineContext;
        this.mTelephonyInteractor = telephonyInteractor;
        this.mDetailInteractor = factory.create(this.mDetailAdapter);
    }

    public static Intent getCellularSettingIntent() {
        Intent intent = new Intent("android.settings.NETWORK_OPERATOR_SETTINGS");
        if (SubscriptionManager.getDefaultDataSubscriptionId() != -1) {
            intent.putExtra("android.provider.extra.SUB_ID", SubscriptionManager.getDefaultDataSubscriptionId());
        }
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPrimarySlotId() {
        return getValue();
    }

    private boolean getShowLabelDesc() {
        return ((Boolean) this.mOplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() && this.mDateUsageSetting.getValue() == 1;
    }

    public static Intent getSoftSimCardSettingIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri uri = Uri.parse("redtearoaming://home/mainPage?import_s=CONTROL_CENTER");
        intent.addFlags(268435456);
        intent.setData(uri);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimarySlotId(int i) {
        if (SubscriptionManager.isValidSlotIndex(i)) {
            setValue(i);
        } else {
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "setPrimarySlotId error slotId = slotIndex");
        }
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final CharSequence appendMobileDataType(CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence2)) {
            return charSequence;
        }
        return TextUtils.isEmpty(charSequence) ? charSequence2 : ((QSTileImpl) this).mContext.getString(2131956630, charSequence, charSequence2);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
        printWriter.println("OplusCellularTile Data:");
        printWriter.println("  - dialog data ------");
        for (CharSequence charSequence : this.mDisplayName) {
            printWriter.println("    mDisplayName：" + ((Object) charSequence));
        }
        for (CharSequence charSequence2 : this.mCarrierPhoneNumber) {
            printWriter.println("    mCarrierPhoneNumber：" + ((Object) charSequence2));
        }
        for (CharSequence charSequence3 : this.mCarrierDataUsage) {
            printWriter.println("    mCarrierDataUsage：" + ((Object) charSequence3));
        }
        for (int i : this.mCarrierIcon) {
            printWriter.println("    mCarrierIcon：" + i);
        }
        for (int i2 : this.mSimState) {
            printWriter.println("    mSimState：" + i2);
        }
        printWriter.println("    mSimSlot：" + getValue());
    }

    public final void executeMobileDataToggle() {
        DataUsageController dataUsageController = this.mDataController;
        if (dataUsageController.getTelephonyManager().isDataEnabled()) {
            dataUsageController.setMobileDataEnabled(false);
            return;
        }
        int primarySlotId = getPrimarySlotId();
        if (CustomizeFeatureOption.checkSlotIdValid(primarySlotId) ? CustomizeFeatureOption.sIsSupportShowDataPrompting[primarySlotId] : false) {
            maybeShowEnableDialog();
        } else {
            dataUsageController.setMobileDataEnabled(true);
            refreshState(QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING);
        }
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237138);
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public Intent getLongClickIntent() {
        return getCellularSettingIntent();
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final CharSequence getMobileDataContentName(CellularTile.CallbackInfo callbackInfo) {
        LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "cb.roaming=" + callbackInfo.roaming + " cb.dataContentDescription=" + ((Object) callbackInfo.dataContentDescription));
        StatusBarFeatureOption.INSTANCE.getClass();
        if (!callbackInfo.roaming || TextUtils.isEmpty(callbackInfo.dataContentDescription)) {
            return callbackInfo.roaming ? ((QSTileImpl) this).mContext.getString(2131952965) : callbackInfo.dataContentDescription;
        }
        return ((QSTileImpl) this).mContext.getString(2131954624, ((QSTileImpl) this).mContext.getString(2131952965), callbackInfo.dataContentDescription.toString());
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956563);
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final void handleClick(Expandable expandable) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_CELLULAR)) {
            return;
        }
        if (((QSTile.State) getState()).state == 1 && OplusOSTelephonyManager.getDefault(((QSTileImpl) this).mContext).isOplusHasSoftSimCard()) {
            getTileEx().postStartActivityDismissingKeyguard(expandable, getSoftSimCardSettingIntent(), false);
            return;
        }
        if (((QSTile.State) getState()).state == 0) {
            return;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
            if (keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing) {
                OplusCellularTile$$ExternalSyntheticLambda0 oplusCellularTile$$ExternalSyntheticLambda0 = new OplusCellularTile$$ExternalSyntheticLambda0(1);
                oplusCellularTile$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                ((QSTileImpl) this).mActivityStarter.postQSRunnableDismissingKeyguard(oplusCellularTile$$ExternalSyntheticLambda0);
                return;
            }
        }
        executeMobileDataToggle();
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final void handleDestroy() {
        super.handleDestroy();
        Handler handler = this.mMainLooperHandler;
        OplusCellularTile$$ExternalSyntheticLambda0 oplusCellularTile$$ExternalSyntheticLambda0 = new OplusCellularTile$$ExternalSyntheticLambda0(2);
        oplusCellularTile$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        handler.post(oplusCellularTile$$ExternalSyntheticLambda0);
        if (this.mMobileDataObserver != null) {
            ((QSTileImpl) this).mContext.getContentResolver().unregisterContentObserver(this.mMobileDataObserver);
        }
        TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
        telephonyListenerManager.mTelephonyCallback.mActiveDataSubscriptionIdListeners.remove(this);
        telephonyListenerManager.updateListening();
        setListening(false);
        this.mDateUsageSetting.setListening(false);
        this.mOplusSatellitePolicy.removeCallback((SatellitePolicy.IConnectionCallback) this.mSatellitePolicyCallback);
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final void handleInitialize() {
        super.handleInitialize();
        Handler handler = this.mMainLooperHandler;
        OplusCellularTile$$ExternalSyntheticLambda0 oplusCellularTile$$ExternalSyntheticLambda0 = new OplusCellularTile$$ExternalSyntheticLambda0(0);
        oplusCellularTile$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        handler.post(oplusCellularTile$$ExternalSyntheticLambda0);
        TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
        telephonyListenerManager.mTelephonyCallback.mActiveDataSubscriptionIdListeners.add(this);
        telephonyListenerManager.updateListening();
        this.mDateUsageSetting.setListening(true);
        setListening(true);
        this.mOplusSatellitePolicy.addCallback((SatellitePolicy.IConnectionCallback) this.mSatellitePolicyCallback);
        Lifecycle lifecycle = getLifecycle();
        StateFlow stateFlowIsAirplaneMode = this.mAirplaneModeRepository.isAirplaneMode();
        CoroutineContext coroutineContext = this.mBgCoroutineContext;
        OplusCellularTile$$ExternalSyntheticLambda1 oplusCellularTile$$ExternalSyntheticLambda1 = new OplusCellularTile$$ExternalSyntheticLambda1();
        oplusCellularTile$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        QSFlowExKt.alwaysCollectFlow(lifecycle, stateFlowIsAirplaneMode, coroutineContext, oplusCellularTile$$ExternalSyntheticLambda1);
        QSFlowExKt.alwaysCollectFlow(getLifecycle(), this.mTelephonyInteractor.isInCall(), this.mBgCoroutineContext);
    }

    public final void handleLongClick(Expandable expandable) {
        if (this.mIsDetailValid && this.mDataController.isMobileDataSupported()) {
            this.mCallback.onRefreshCarrierInfo();
        }
        super.handleLongClick(expandable);
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final void handleSecondaryClick(Expandable expandable) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
            if (keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing) {
                OplusCellularTile$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new OplusCellularTile$.ExternalSyntheticLambda3(0);
                externalSyntheticLambda3.f$0 = this;
                externalSyntheticLambda3.f$1 = expandable;
                VarHandle.storeStoreFence();
                ((QSTileImpl) this).mActivityStarter.postQSRunnableDismissingKeyguard(externalSyntheticLambda3);
                return;
            }
        }
        updateLockSimState();
        showDetail(expandable);
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i;
        int i2;
        CharSequence string;
        CellularTile.CallbackInfo callbackInfo = this.mSignalCallback.mInfo;
        boolean z = obj == QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING;
        Resources resources = ((QSTileImpl) this).mContext.getResources();
        int slotIndex = this.mActiveDataSlot;
        if (slotIndex == -1) {
            slotIndex = SubscriptionManager.getSlotIndex(callbackInfo.subId);
        }
        SubscriptionInfo subscriptionInfoForSubId = this.mKeyguardUpdateMonitor.getSubscriptionInfoForSubId(SubscriptionManager.getSubscriptionId(slotIndex));
        ((QSTile.State) booleanState).label = subscriptionInfoForSubId == null ? ((QSTileImpl) this).mContext.getResources().getString(2131956563) : subscriptionInfoForSubId.getDisplayName().toString();
        ((QSTile.State) booleanState).importantLabel = null;
        boolean zIsAnySimEnabled = OplusQSCarrierTextController.isAnySimEnabled();
        boolean zIsMobileDataSupported = this.mDataController.isMobileDataSupported();
        boolean zIsOplusHasSoftSimCard = OplusOSTelephonyManager.getDefault(((QSTileImpl) this).mContext).isOplusHasSoftSimCard();
        boolean z2 = zIsMobileDataSupported && (z || this.mDataController.getTelephonyManager().isDataEnabled());
        boolean showLabelDesc = getShowLabelDesc();
        boolean z3 = zIsMobileDataSupported && z2;
        boolean zIsVirtualCommConsumerDevice = QSFeatureOption.INSTANCE.isVirtualCommConsumerDevice(((QSTileImpl) this).mContext);
        String str = ((QSTileImpl) this).TAG;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("isMobileDataSupported = ", " transientEnabling = ", " isMobileDataEnabled = ", zIsMobileDataSupported, z);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" isSimEnabled = ", " isShowLabelDesc = ", sbM, z2, zIsAnySimEnabled);
        PromptIconState$.ExternalSyntheticOutline0.m(slotIndex, " currentSlot = ", " isOplusHasSoftSimCard = ", sbM, showLabelDesc);
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, str, sbM, zIsOplusHasSoftSimCard);
        ((QSTile.AdapterState) booleanState).value = z3;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        String string2 = "";
        if (!zIsAnySimEnabled && !zIsVirtualCommConsumerDevice) {
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).secondaryLabel = resources.getString(2131955139);
            ((QSTile.State) booleanState).dualTarget = false;
            ((QSTile.State) booleanState).labelDesc = showLabelDesc ? resources.getString(2131955139) : "";
        } else if (((Boolean) this.mAirplaneModeRepository.isAirplaneMode().getValue()).booleanValue()) {
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).secondaryLabel = resources.getString(2131956585);
            ((QSTile.State) booleanState).dualTarget = false;
            ((QSTile.State) booleanState).labelDesc = showLabelDesc ? resources.getString(2131956585) : "";
        } else if (z3) {
            ((QSTile.State) booleanState).state = 2;
            String dataUsageInfo = (showLabelDesc && zIsAnySimEnabled) ? this.mOplusDataUsageController.getDataUsageInfo(-1) : "";
            ((QSTile.State) booleanState).secondaryLabel = dataUsageInfo;
            if (!TextUtils.isEmpty(dataUsageInfo)) {
                ((QSTile.State) booleanState).importantLabel = dataUsageInfo;
            }
            if (!TextUtils.isEmpty(dataUsageInfo)) {
                string2 = dataUsageInfo;
            } else if (((Boolean) this.mOplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
                string2 = resources.getString(2131955246);
            }
            ((QSTile.State) booleanState).labelDesc = string2;
            ((QSTile.State) booleanState).dualTarget = true;
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "cb.multipleSubs=" + callbackInfo.multipleSubs + ", cb.dataSubscriptionName=" + ((Object) LogUtil.getLogSafeMessage(callbackInfo.dataSubscriptionName, 1, 1)) + ", dataUsage=" + dataUsageInfo);
        } else if (!zIsVirtualCommConsumerDevice || zIsAnySimEnabled) {
            ((QSTile.State) booleanState).secondaryLabel = "";
            ((QSTile.State) booleanState).state = 1;
            ((QSTile.State) booleanState).dualTarget = true;
            ((QSTile.State) booleanState).labelDesc = showLabelDesc ? resources.getString(2131952592) : "";
        } else {
            ((QSTile.State) booleanState).secondaryLabel = null;
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).dualTarget = false;
            ((QSTile.State) booleanState).labelDesc = "";
        }
        if (!zIsAnySimEnabled || QSFeatureOption.isCellularDisabledByCustomize) {
            if (zIsOplusHasSoftSimCard) {
                ((QSTile.State) booleanState).state = 1;
            } else {
                ((QSTile.State) booleanState).state = 0;
            }
            ((QSTile.State) booleanState).dualTarget = false;
        } else if (zIsOplusHasSoftSimCard) {
            ((QSTile.State) booleanState).state = 1;
            ((QSTile.State) booleanState).dualTarget = false;
        }
        if (((QSTile.State) booleanState).dualTarget && (((Boolean) this.mTelephonyInteractor.isInCall().getValue()).booleanValue() || zIsVirtualCommConsumerDevice)) {
            ((QSTile.State) booleanState).dualTarget = false;
        }
        int i3 = ((QSTile.State) booleanState).state;
        if (i3 == 2) {
            i2 = 2131237137;
            i = 1;
        } else {
            i = 1;
            i2 = i3 == 1 ? 2131237138 : 2131237139;
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(i2);
        int i4 = ((QSTile.State) booleanState).state;
        if (i4 == i) {
            string = resources.getString(2131952593);
        } else {
            CharSequence charSequence = ((QSTile.State) booleanState).secondaryLabel;
            if (i4 == 0) {
                string = ((Object) charSequence) + resources.getString(2131955193);
            } else {
                string = charSequence;
            }
        }
        ((QSTile.State) booleanState).contentDescription = ((Object) ((QSTile.State) booleanState).label) + ", " + ((Object) string);
        this.mIsDetailValid = ((QSTile.State) booleanState).dualTarget;
    }

    public final boolean handleUserOperation(Object obj) {
        LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperation: operation=" + obj);
        this.mDataController.setMobileDataEnabled(Boolean.TRUE.equals(obj));
        return true;
    }

    public final void handleUserSwitch(int i) {
        super.handleUserSwitch(i);
        this.mDateUsageSetting.setUserId(i);
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final boolean isAvailable() {
        return this.mController.mHasMobileDataFeature;
    }

    public final void maybeShowEnableDialog() {
        try {
            COUIAlertDialogBuilder title = new COUIAlertDialogBuilder(((QSTileImpl) this).mContext).setTitle(2131955318);
            OplusCellularTile$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new OplusCellularTile$.ExternalSyntheticLambda7();
            externalSyntheticLambda7.f$0 = this;
            VarHandle.storeStoreFence();
            title.setPositiveButton(2131955319, externalSyntheticLambda7);
            title.setNegativeButton(R.string.cancel, new BiometricNotificationDialogFactory$.ExternalSyntheticLambda1());
            AlertDialog alertDialogCreate = title.create();
            alertDialogCreate.getWindow().setType(2009);
            SystemUIDialog.setShowForAllUsers(alertDialogCreate, true);
            SystemUIDialog.registerDismissListener(alertDialogCreate, (Runnable) null);
            SystemUIDialog.setWindowOnTop(alertDialogCreate, ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing);
            alertDialogCreate.show();
        } catch (Exception e) {
            LogUtil.w(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "Fail to show data alert dialog: " + e.getMessage());
        }
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    /* JADX INFO: renamed from: newTileState */
    public final QSTile.BooleanState mo315newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_cellular_lottie";
        ((QSTile.State) booleanState).lottieSupport = 17;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_GREEN_COLOR;
        return booleanState;
    }

    @Override // android.telephony.TelephonyCallback.ActiveDataSubscriptionIdListener
    public final void onActiveDataSubscriptionIdChanged(int i) {
        this.mActiveDataSlot = SubscriptionManager.getSlotIndex(i);
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mActiveDataSlot, LogUtil.TAG_QS, ((QSTileImpl) this).TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onActiveDataSubscriptionIdChanged:subId=", ",mActiveDataSlot="));
        refreshState((Object) null);
    }

    public final void showDetail(Expandable expandable) {
        if (!this.mIsDetailValid || !this.mDataController.isMobileDataSupported() || this.mLockSimState) {
            ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(getCellularSettingIntent(), 0);
        } else {
            updateCarrierText$1(true);
            this.mDetailInteractor.show(expandable != null ? expandable.getView() : null);
        }
    }

    @Override // com.oplus.systemui.qs.tiles.CellularTile
    public final void toggleMobileDataEnabled(boolean z) {
        this.mDetailInteractor.updateSwitchState(z);
    }

    /* JADX WARN: Type inference failed for: r6v14, types: [com.oplus.systemui.qs.tiles.OplusCellularTile$5] */
    public final void updateCarrierText$1(boolean z) {
        if (!z && !this.mDetailInteractor.isShowing()) {
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "updateCarrierText: dialog not showing, ignore");
            return;
        }
        List filteredSubscriptionInfo = this.mKeyguardUpdateMonitor.getFilteredSubscriptionInfo();
        int size = filteredSubscriptionInfo.size();
        int i = 0;
        while (true) {
            CharSequence[] charSequenceArr = this.mDisplayName;
            if (i >= charSequenceArr.length) {
                break;
            }
            charSequenceArr[i] = "";
            this.mCarrierPhoneNumber[i] = "";
            this.mCarrierDataUsage[i] = "";
            this.mCarrierIcon[i] = 0;
            this.mSimState[i] = -1;
            i++;
        }
        for (int i2 = 0; i2 < size; i2++) {
            int subscriptionId = ((SubscriptionInfo) filteredSubscriptionInfo.get(i2)).getSubscriptionId();
            int simStateForSlotId = this.mKeyguardUpdateMonitor.getSimStateForSlotId(((SubscriptionInfo) filteredSubscriptionInfo.get(i2)).getSimSlotIndex());
            if (!this.mSubIdList.contains(Integer.valueOf(subscriptionId))) {
                this.mSubIdList.add(Integer.valueOf(subscriptionId));
                if (this.mMobileDataObserver == null) {
                    this.mMobileDataObserver = new ContentObserver(((QSTileImpl) this).mHandler) { // from class: com.oplus.systemui.qs.tiles.OplusCellularTile.5
                        @Override // android.database.ContentObserver
                        public final void onChange(boolean z2) {
                            OplusCellularTile.this.refreshState((Object) null);
                        }
                    };
                }
                ContentResolver contentResolver = ((QSTileImpl) this).mContext.getContentResolver();
                String strM = "mobile_data";
                if (TelephonyManager.from(((QSTileImpl) this).mContext).getSimCount() > 1 && SubscriptionManager.isValidSubscriptionId(subscriptionId)) {
                    strM = ListPopupWindow$.ExternalSyntheticOutline0.m(subscriptionId, "mobile_data");
                }
                contentResolver.registerContentObserver(Settings.Global.getUriFor(strM), false, this.mMobileDataObserver);
            }
            CharSequence displayName = ((SubscriptionInfo) filteredSubscriptionInfo.get(i2)).getDisplayName();
            int simSlotIndex = ((SubscriptionInfo) filteredSubscriptionInfo.get(i2)).getSimSlotIndex();
            this.mSimState[simSlotIndex] = simStateForSlotId;
            if (!TextUtils.isEmpty(displayName) && simStateForSlotId == 5 && SubscriptionManager.isValidSlotIndex(simSlotIndex)) {
                this.mDisplayName[simSlotIndex] = displayName;
                this.mCarrierPhoneNumber[simSlotIndex] = ((SubscriptionInfo) filteredSubscriptionInfo.get(i2)).getNumber();
                this.mCarrierDataUsage[simSlotIndex] = this.mOplusDataUsageController.getDataUsageInfo(subscriptionId);
                this.mCarrierIcon[simSlotIndex] = simSlotIndex == 0 ? 2131236330 : 2131236331;
                if (!CustomizeFeatureOption.checkSlotIdValid(simSlotIndex) ? false : CustomizeFeatureOption.sIsSupportShowLineNumberCust[simSlotIndex]) {
                    LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "support show line number");
                    if (TextUtils.isEmpty(this.mCarrierPhoneNumber[simSlotIndex]) && !TextUtils.isEmpty(this.mCarrierLineNumber[simSlotIndex])) {
                        LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "set line number");
                        this.mCarrierPhoneNumber[simSlotIndex] = this.mCarrierLineNumber[simSlotIndex];
                    }
                }
            }
        }
        DetailAdapter detailAdapter = this.mDetailAdapter;
        if (detailAdapter instanceof CellularDetailAdapter) {
            ((CellularDetailAdapter) detailAdapter).updateItem$1();
        }
        Handler handler = this.mMainLooperHandler;
        OplusCellularTile$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new OplusCellularTile$.ExternalSyntheticLambda5();
        externalSyntheticLambda5.f$0 = this;
        externalSyntheticLambda5.f$1 = size;
        VarHandle.storeStoreFence();
        handler.post(externalSyntheticLambda5);
    }

    public final void updateLockSimState() {
        SimStateHelper simStateHelper = SimStateHelper.getInstance(((QSTileImpl) this).mContext);
        boolean z = true;
        if (!simStateHelper.isLockRestrictOperator()) {
            int lockState = simStateHelper.getLockState(0);
            int lockState2 = simStateHelper.getLockState(1);
            ExifInterface$.ExternalSyntheticOutline1.m("needDisableMenu: slot1State=", lockState, lockState2, "; slot2State=", "SimStateHelper");
            boolean z2 = 2 == lockState || 2 == lockState2;
            BluetoothUtils$.ExternalSyntheticOutline0.m("needDisableMenu:", "SimStateHelper", z2);
            if (!z2 || !simStateHelper.isLockOperator() || simStateHelper.isFeatureValid(9) || simStateHelper.isFeatureValid(10)) {
                z = false;
            }
        }
        this.mLockSimState = z;
    }
}
