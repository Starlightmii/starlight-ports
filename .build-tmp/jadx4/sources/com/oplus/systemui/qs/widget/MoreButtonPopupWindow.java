package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.DependencyEx;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.util.LargeScreenUtils;
import com.coui.appcompat.poplist.COUIPopupListWindow;
import com.coui.appcompat.poplist.PopupListItem;
import com.coui.appcompat.uiutil.AnimLevel;
import com.oplus.systemui.qs.OplusQSFooterImpl;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.res.data.repository.QSColorfulIconRepository;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.util.ScreenUtils;
import com.oplus.util.OplusContextUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.OnePlusQsColorUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MoreButtonPopupWindow extends COUIPopupListWindow {
    private static final String ACTION_MYDEVICE_HOME_PAGE = "com.oplus.mydevices.ACTION_DEVICE_CARD_HOME_ACTIVITY";
    private static final String ACTION_QS_CENTER_SETTINGS_PAGE = "com.oplus.notificationmanager.action.QS_CENTER_SHOW";
    public static final Companion Companion = new Companion();
    private static final String FSG_NUM_PKG_MAX_STRING = "99+";
    private static final String HAS_OPEN = "true";
    private static final String HIDE_DEVICE = "false";
    private static final String KEY_SEPARATE_MODE = "key_separate_mode";
    private static final String QS_DEVICE_SPACE = "qs_device_space";
    private static final String QS_SETTINGS_HAS_OPEN = "qs_settings_has_open";
    private static final String SHOW_DEVICE = "true";
    private static final String TAG = "MoreButtonPopupWindow";
    private View editButton;
    private View fgsButton;
    private final Context mContext;
    private List<PopupListItem> mCustomList;
    public boolean mDeviceShow;
    private TextView mMyDeviceTitle;
    private final OplusFgsManagerController mOplusFgsManagerController;
    private final ShadeViewController mShadeViewController;
    private final OplusQSFooterImpl mView;
    private View moreIcon;
    private View myDeviceLayout;
    private final NotificationDependOnCommon notificationDependOnCommon;
    private final OplusQSActivityStarter oplusQSActivityStarter;
    private View outSwitchButton;
    private final QSPanelController qsPanelController;
    private View redDotView;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private View settingsView;
    private CentralSurfaces statusBar;

    /* JADX WARN: Multi-variable type inference failed */
    public MoreButtonPopupWindow(Context context, OplusFgsManagerController oplusFgsManagerController, OplusQSFooterImpl oplusQSFooterImpl, ShadeViewController shadeViewController, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, NotificationDependOnCommon notificationDependOnCommon, OplusQSActivityStarter oplusQSActivityStarter, QSPanelController qSPanelController) {
        super(context);
        this.mContext = context;
        this.mOplusFgsManagerController = oplusFgsManagerController;
        this.mView = oplusQSFooterImpl;
        this.mShadeViewController = shadeViewController;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.notificationDependOnCommon = notificationDependOnCommon;
        this.oplusQSActivityStarter = oplusQSActivityStarter;
        this.qsPanelController = qSPanelController;
        this.mDeviceShow = true;
        setInputMethodMode(2);
        this.statusBar = (CentralSurfaces) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
        View viewInflate = LayoutInflater.from(context).inflate(2131559301, (ViewGroup) null);
        this.myDeviceLayout = viewInflate;
        this.mMyDeviceTitle = viewInflate != null ? (TextView) viewInflate.findViewById(2131364114) : null;
        this.editButton = LayoutInflater.from(context).inflate(2131559302, (ViewGroup) null);
        this.outSwitchButton = LayoutInflater.from(context).inflate(2131559304, (ViewGroup) null);
        View viewInflate2 = LayoutInflater.from(context).inflate(2131559305, (ViewGroup) null);
        this.settingsView = viewInflate2;
        this.redDotView = viewInflate2 != null ? viewInflate2.findViewById(2131364802) : null;
        this.fgsButton = LayoutInflater.from(context).inflate(2131559303, (ViewGroup) null);
        this.moreIcon = oplusQSFooterImpl.findViewById(2131364004);
        boolean zShouldShowEditOption = LargeScreenUtils.shouldShowEditOption(context.getResources());
        View view = this.editButton;
        int i = 8;
        if (view != null) {
            view.setVisibility(zShouldShowEditOption ? 0 : 8);
        }
        if (zShouldShowEditOption) {
            View view2 = this.editButton;
            TextView textView = view2 != null ? (TextView) view2.findViewById(2131363013) : null;
            if (textView != null) {
                textView.setSelected(true);
            }
        }
        View view3 = this.outSwitchButton;
        if (view3 != null) {
            if (OsBinderCacheUtils.getCurrentUserId() == 0) {
                QSFeatureOption.INSTANCE.getClass();
                if (!QSFeatureOption.isMediaShowSupport) {
                    i = 0;
                }
            }
            view3.setVisibility(i);
        }
        if (OsBinderCacheUtils.getCurrentUserId() == 0) {
            QSFeatureOption.INSTANCE.getClass();
            if (!QSFeatureOption.isMediaShowSupport) {
                View view4 = this.outSwitchButton;
                TextView textView2 = view4 != null ? (TextView) view4.findViewById(2131363904) : null;
                if (textView2 != null) {
                    textView2.setSelected(true);
                }
            }
        }
        FeatureOption.INSTANCE.getClass();
        String str = FeatureOption.isExpRegion() ? "false" : "true";
        String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), QS_DEVICE_SPACE, -2);
        this.mDeviceShow = (stringForUser != null ? stringForUser : str).equals("true");
        updateDevice();
        updateClick.1 r1 = new updateClick.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        setOnItemClickListener(r1);
        initList$1();
        setIsAdaptiveFontSize(true);
        QSFeatureOption.INSTANCE.getClass();
        if (!QSFeatureOption.isDisableBGBlurNonAnim) {
            setUseBackgroundBlur(true, AnimLevel.MID_END);
        }
        setDismissWhenWindowSizeChange(false);
    }

    public final int[] getMeasureXYOffset(View view, boolean z) {
        int statusBarHeight = z ? ScreenUtils.getStatusBarHeight(this.mContext) : 0;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new int[]{DeviceUtil.isRtl() ? iArr[0] : statusBarHeight + ((view.getResources().getDisplayMetrics().widthPixels - iArr[0]) - view.getWidth()), view.getHeight() + iArr[1]};
    }

    public final void initList$1() {
        ArrayList arrayList = new ArrayList();
        this.mCustomList = arrayList;
        PopupListItem.Builder builder = new PopupListItem.Builder();
        View view = this.editButton;
        if (view != null && view.getVisibility() == 0) {
            builder.setItemType(2).setCustomItemView(this.editButton);
            arrayList.add(builder.build());
        }
        View view2 = this.outSwitchButton;
        if (view2 != null && view2.getVisibility() == 0) {
            builder.reset();
            builder.setItemType(2).setCustomItemView(this.outSwitchButton);
            arrayList.add(builder.build());
        }
        View view3 = this.myDeviceLayout;
        if (view3 != null && view3.getVisibility() == 0) {
            builder.reset();
            builder.setItemType(2).setCustomItemView(this.myDeviceLayout);
            arrayList.add(builder.build());
        }
        View view4 = this.settingsView;
        if (view4 != null && view4.getVisibility() == 0) {
            builder.reset();
            builder.setItemType(2).setCustomItemView(this.settingsView);
            arrayList.add(builder.build());
        }
        View view5 = this.fgsButton;
        if (view5 != null && view5.getVisibility() == 0) {
            builder.reset();
            builder.setItemType(2).setCustomItemView(this.fgsButton);
            arrayList.add(builder.build());
        }
        setItemList(this.mCustomList);
    }

    public final boolean isDevicePanelLandSpace() {
        return DeviceUtil.isLandscape(this.mContext) && !DeviceUtil.shouldUseLargeFoldConfig(this.mContext.getResources());
    }

    /* JADX WARN: Code duplicated, block: B:18:0x003f  */
    public final void updateDevice() {
        View view = this.myDeviceLayout;
        if (view != null) {
            FeatureOption.INSTANCE.getClass();
            view.setVisibility(FeatureOption.isSupportMyDevice() ? 0 : 8);
        }
        TextView textView = this.mMyDeviceTitle;
        if (textView != null) {
            String string = this.mContext.getString(2131955631);
            if (!isDevicePanelLandSpace()) {
                boolean z = this.mDeviceShow;
                Context context = this.mContext;
                if (z) {
                    if (context != null) {
                        string = context.getString(2131955660, string);
                    } else {
                        string = null;
                    }
                } else if (context != null) {
                    string = context.getString(2131955691, string);
                } else {
                    string = null;
                }
            }
            textView.setText(string);
        }
        TextView textView2 = this.mMyDeviceTitle;
        if (textView2 != null) {
            textView2.setSelected(true);
        }
    }

    public final void updateFsg(int i, boolean z) {
        int activeColorWithDarkMode;
        View view = this.fgsButton;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
        View view2 = this.fgsButton;
        TextView textView = view2 != null ? (TextView) view2.findViewById(2131363125) : null;
        String strValueOf = i >= 100 ? FSG_NUM_PKG_MAX_STRING : String.valueOf(i);
        GradientDrawable gradientDrawable = (GradientDrawable) (textView != null ? textView.getBackground() : null);
        if (i == 0) {
            gradientDrawable.setColor(OplusContextUtil.getAttrColor(this.mContext, 2130969293));
        } else {
            FlavorTwoFeatureOption.INSTANCE.getClass();
            if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp() && OnePlusQsColorUtil.INSTANCE.getEnableTwoToneTheme()) {
                QSColorfulIconRepository.Companion companion = QSColorfulIconRepository.Companion;
                Context context = this.mContext;
                activeColorWithDarkMode = companion.getStdTwoToneAccentColor(context, ScrimUtil.isLowGaussianLevel(context));
            } else {
                activeColorWithDarkMode = QSFragmentHelper.getInstance().getActiveColorWithDarkMode(this.mContext);
            }
            gradientDrawable.setColor(activeColorWithDarkMode);
        }
        textView.setBackground(gradientDrawable);
        textView.setText(strValueOf);
        initList$1();
    }

    public final void updateRedDotView(boolean z) {
        View view = this.redDotView;
        if (view != null) {
            view.setVisibility(z ? 8 : 0);
        }
    }
}
