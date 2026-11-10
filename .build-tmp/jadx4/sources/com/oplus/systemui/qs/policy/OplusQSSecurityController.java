package com.oplus.systemui.qs.policy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.databinding.ObservableField;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.NullableObservableField;
import com.android.systemui.plugins.qs.QSSecurity;
import com.android.systemui.qs.QSSecurityFooterUtils;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.statusbar.policy.SecurityController;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSecurityController implements QSSecurity {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSSecurityController";
    private final ActivityStarter activityStarter;
    private final Handler bgHandler;
    private final SecurityController.SecurityControllerCallback callback;
    private final NullableObservableField<CharSequence> contentText = new NullableObservableField<>("", (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
    private final Context context;
    private COUIAlertDialogBuilder couiAlertDialogBuilder;
    private AlertDialog dialog;
    private final NullableObservableField<Integer> footerIconId;
    private final Handler mainHandler;
    private final PanelInteractor panelInteractor;
    private final QSSecurityFooterUtils qsSecurityFooterUtils;
    private final SecurityController securityController;
    private final Runnable showDialog;
    private final NullableObservableField<Boolean> showSecurity;
    private final MutableStateFlow showSecurityFlow;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Factory {
        private final ActivityStarter activityStarter;
        private final Looper bgLooper;
        private final Context context;
        private final Handler mainHandler;
        private final PanelInteractor panelInteractor;
        private final QSSecurityFooterUtils qsSecurityFooterUtils;
        private final SecurityController securityController;

        public Factory(Context context, ActivityStarter activityStarter, SecurityController securityController, PanelInteractor panelInteractor, Looper looper, Handler handler, QSSecurityFooterUtils qSSecurityFooterUtils) {
            this.context = context;
            this.activityStarter = activityStarter;
            this.securityController = securityController;
            this.panelInteractor = panelInteractor;
            this.bgLooper = looper;
            this.mainHandler = handler;
            this.qsSecurityFooterUtils = qSSecurityFooterUtils;
        }

        public final QSSecurity create() {
            return new OplusQSSecurityController(this.context, this.activityStarter, this.securityController, this.panelInteractor, new Handler(this.bgLooper), this.mainHandler, this.qsSecurityFooterUtils);
        }
    }

    public OplusQSSecurityController(Context context, ActivityStarter activityStarter, SecurityController securityController, PanelInteractor panelInteractor, Handler handler, Handler handler2, QSSecurityFooterUtils qSSecurityFooterUtils) {
        this.context = context;
        this.activityStarter = activityStarter;
        this.securityController = securityController;
        this.panelInteractor = panelInteractor;
        this.bgHandler = handler;
        this.mainHandler = handler2;
        this.qsSecurityFooterUtils = qSSecurityFooterUtils;
        Boolean bool = Boolean.FALSE;
        this.showSecurity = new NullableObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.showSecurityFlow = StateFlowKt.MutableStateFlow(bool);
        this.footerIconId = new NullableObservableField<>(0, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        OplusQSSecurityController$callback$1.AnonymousClass1 anonymousClass1 = new OplusQSSecurityController$callback$1.AnonymousClass1(3);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.showDialog = anonymousClass1;
        OplusQSSecurityController$callback$1 oplusQSSecurityController$callback$1 = new OplusQSSecurityController$callback$1();
        oplusQSSecurityController$callback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.callback = oplusQSSecurityController$callback$1;
    }

    public static final void access$actionPrivacySettings(OplusQSSecurityController oplusQSSecurityController) {
        oplusQSSecurityController.panelInteractor.collapsePanels();
        oplusQSSecurityController.activityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.ENTERPRISE_PRIVACY_SETTINGS"), 0);
        AlertDialog alertDialog = oplusQSSecurityController.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public static final void access$actionVpnSettings(OplusQSSecurityController oplusQSSecurityController) {
        oplusQSSecurityController.panelInteractor.collapsePanels();
        oplusQSSecurityController.activityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.VPN_SETTINGS"), 0);
        AlertDialog alertDialog = oplusQSSecurityController.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public static final void access$createDialog(OplusQSSecurityController oplusQSSecurityController, CharSequence charSequence) {
        int i;
        View viewFindViewById;
        View viewFindViewById2;
        View viewFindViewById3;
        boolean zIsDeviceManaged = oplusQSSecurityController.securityController.mDevicePolicyManager.isDeviceManaged();
        boolean zIsOrganizationOwnedDeviceWithManagedProfile = oplusQSSecurityController.securityController.mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile();
        boolean zHasWorkProfile$3 = oplusQSSecurityController.securityController.hasWorkProfile$3();
        CharSequence deviceOwnerOrganizationName = oplusQSSecurityController.securityController.getDeviceOwnerOrganizationName();
        CharSequence workProfileOrganizationName = oplusQSSecurityController.securityController.getWorkProfileOrganizationName();
        boolean zHasCACertInCurrentUser = oplusQSSecurityController.securityController.hasCACertInCurrentUser();
        boolean zHasCACertInWorkProfile = oplusQSSecurityController.securityController.hasCACertInWorkProfile();
        boolean zIsNetworkLoggingEnabled = oplusQSSecurityController.securityController.isNetworkLoggingEnabled();
        String primaryVpnName = oplusQSSecurityController.securityController.getPrimaryVpnName();
        String workProfileVpnName = oplusQSSecurityController.securityController.getWorkProfileVpnName();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder(" isDeviceManaged: ");
            sb.append(zIsDeviceManaged);
            sb.append(" deviceOwnerOrganization: ");
            sb.append((Object) deviceOwnerOrganizationName);
            sb.append(" hasCACerts: ");
            KeyguardLogger$.ExternalSyntheticOutline0.m(" hasCACertsInWorkProfile: ", " isNetworkLoggingEnabled: ", sb, zHasCACertInCurrentUser, zHasCACertInWorkProfile);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" hasWorkProfile: ", " vpnName: ", sb, zIsNetworkLoggingEnabled, zHasWorkProfile$3);
            ConstraintWidget$.ExternalSyntheticOutline0.m(sb, primaryVpnName, " vpnNameWorkProfile: ", workProfileVpnName, " isProfileOwnerOfOrganizationOwnedDevice: ");
            sb.append(zIsOrganizationOwnedDeviceWithManagedProfile);
            sb.append(" workProfileOrganizationName: ");
            sb.append((Object) workProfileOrganizationName);
            Log.d("SystemUi--QuickSettings", "OplusQSSecurityController-->".concat(sb.toString()));
        }
        AlertDialog alertDialog = oplusQSSecurityController.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (isShowVpn(primaryVpnName, zIsDeviceManaged, zHasWorkProfile$3)) {
            i = 2131954650;
        } else {
            i = zIsDeviceManaged ? 2131954630 : 2131952564;
        }
        COUIAlertDialogBuilder title = new COUIAlertDialogBuilder(oplusQSSecurityController.context, 2132017612).setTitle(charSequence);
        title.setPositiveButton(2131952526, (DialogInterface.OnClickListener) null);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(oplusQSSecurityController.context, 2132017183);
        COUIThemeOverlay.getInstance().applyThemeOverlays(contextThemeWrapper);
        View viewInflate = LayoutInflater.from(contextThemeWrapper).inflate(2131559541, (ViewGroup) null, false);
        CharSequence managementMessage = oplusQSSecurityController.getManagementMessage(zIsDeviceManaged, deviceOwnerOrganizationName, zIsOrganizationOwnedDeviceWithManagedProfile, workProfileOrganizationName);
        if (managementMessage == null) {
            View viewFindViewById4 = viewInflate.findViewById(2131362870);
            if (viewFindViewById4 != null) {
                viewFindViewById4.setVisibility(8);
            }
        } else {
            View viewFindViewById5 = viewInflate.findViewById(2131362870);
            if (viewFindViewById5 != null) {
                viewFindViewById5.setVisibility(0);
            }
            TextView textView = (TextView) viewInflate.findViewById(2131362872);
            if (textView != null) {
                textView.setText(managementMessage);
            }
        }
        CharSequence caCertsMessage = oplusQSSecurityController.getCaCertsMessage(zIsDeviceManaged, zHasCACertInCurrentUser, zHasCACertInWorkProfile);
        if (caCertsMessage == null) {
            View viewFindViewById6 = viewInflate.findViewById(2131362457);
            if (viewFindViewById6 != null) {
                viewFindViewById6.setVisibility(8);
            }
        } else {
            View viewFindViewById7 = viewInflate.findViewById(2131362457);
            if (viewFindViewById7 != null) {
                viewFindViewById7.setVisibility(0);
            }
            TextView textView2 = (TextView) viewInflate.findViewById(2131362459);
            if (textView2 != null) {
                textView2.setText(caCertsMessage);
            }
            if (textView2 != null) {
                textView2.setMovementMethod(new LinkMovementMethod());
            }
        }
        String string = zIsNetworkLoggingEnabled ? oplusQSSecurityController.context.getString(2131954638) : null;
        if (string == null) {
            View viewFindViewById8 = viewInflate.findViewById(2131364136);
            if (viewFindViewById8 != null) {
                viewFindViewById8.setVisibility(8);
            }
        } else {
            View viewFindViewById9 = viewInflate.findViewById(2131364136);
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
            TextView textView3 = (TextView) viewInflate.findViewById(2131364138);
            if (textView3 != null) {
                textView3.setText(string);
            }
        }
        CharSequence vpnMessage = oplusQSSecurityController.getVpnMessage(primaryVpnName, workProfileVpnName, zIsDeviceManaged, zHasWorkProfile$3);
        if (vpnMessage == null) {
            View viewFindViewById10 = viewInflate.findViewById(2131366047);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(8);
            }
        } else {
            View viewFindViewById11 = viewInflate.findViewById(2131366047);
            if (viewFindViewById11 != null) {
                viewFindViewById11.setVisibility(0);
            }
            TextView textView4 = (TextView) viewInflate.findViewById(2131366049);
            if (textView4 != null) {
                textView4.setText(vpnMessage);
            }
            if (textView4 != null) {
                textView4.setMovementMethod(new LinkMovementMethod());
            }
        }
        boolean z = managementMessage != null;
        int i2 = caCertsMessage != null ? 1 : 0;
        boolean z2 = string != null;
        boolean z3 = vpnMessage != null;
        if (!z) {
            int i3 = z2 ? i2 + 1 : i2;
            if (z3) {
                i3++;
            }
            if (i3 == 1) {
                if (i2 != 0 && (viewFindViewById3 = viewInflate.findViewById(2131362458)) != null) {
                    viewFindViewById3.setVisibility(8);
                }
                if (z2 && (viewFindViewById2 = viewInflate.findViewById(2131364137)) != null) {
                    viewFindViewById2.setVisibility(8);
                }
                if (z3 && (viewFindViewById = viewInflate.findViewById(2131366048)) != null) {
                    viewFindViewById.setVisibility(8);
                }
            }
        }
        title.setView(viewInflate);
        oplusQSSecurityController.couiAlertDialogBuilder = title;
        if (!zIsOrganizationOwnedDeviceWithManagedProfile) {
            createDialog.3 r2 = new createDialog.3();
            r2.this$0 = oplusQSSecurityController;
            r2.$isDeviceManaged = zIsDeviceManaged;
            r2.$vpnName = primaryVpnName;
            r2.$hasWorkProfile = zHasWorkProfile$3;
            VarHandle.storeStoreFence();
            title.setNegativeButton(i, r2);
        }
        oplusQSSecurityController.mainHandler.post(oplusQSSecurityController.showDialog);
    }

    public static final void access$handleRefreshState(OplusQSSecurityController oplusQSSecurityController) {
        boolean zIsDeviceManaged = oplusQSSecurityController.securityController.mDevicePolicyManager.isDeviceManaged();
        UserManager userManager = UserManager.get(oplusQSSecurityController.context);
        UserInfo userInfo = userManager != null ? userManager.getUserInfo(KeyguardUpdateMonitor.getCurrentUser()) : null;
        boolean z = false;
        boolean z2 = UserManager.isDeviceInDemoMode(oplusQSSecurityController.context) && userInfo != null && userInfo.isDemo();
        boolean zHasWorkProfile$3 = oplusQSSecurityController.securityController.hasWorkProfile$3();
        boolean zHasCACertInCurrentUser = oplusQSSecurityController.securityController.hasCACertInCurrentUser();
        boolean zHasCACertInWorkProfile = oplusQSSecurityController.securityController.hasCACertInWorkProfile();
        boolean zIsNetworkLoggingEnabled = oplusQSSecurityController.securityController.isNetworkLoggingEnabled();
        String primaryVpnName = oplusQSSecurityController.securityController.getPrimaryVpnName();
        String workProfileVpnName = oplusQSSecurityController.securityController.getWorkProfileVpnName();
        CharSequence deviceOwnerOrganizationName = oplusQSSecurityController.securityController.getDeviceOwnerOrganizationName();
        CharSequence workProfileOrganizationName = oplusQSSecurityController.securityController.getWorkProfileOrganizationName();
        boolean zIsOrganizationOwnedDeviceWithManagedProfile = oplusQSSecurityController.securityController.mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile();
        NullableObservableField<Boolean> showSecurity = oplusQSSecurityController.getShowSecurity();
        if (((zIsDeviceManaged && !z2) || zHasCACertInCurrentUser || zHasCACertInWorkProfile || primaryVpnName != null || workProfileVpnName != null || zIsOrganizationOwnedDeviceWithManagedProfile) && !CustomizeFeatureOption.sHideOrganizationMsg) {
            z = true;
        }
        showSecurity.set(Boolean.valueOf(z));
        MutableStateFlow showSecurityFlow = oplusQSSecurityController.getShowSecurityFlow();
        Object obj = oplusQSSecurityController.getShowSecurity().get();
        Boolean bool = Boolean.TRUE;
        showSecurityFlow.setValue(Boolean.valueOf(Intrinsics.areEqual(obj, bool)));
        oplusQSSecurityController.getContentText().set(oplusQSSecurityController.getFooterText(zIsDeviceManaged, zHasWorkProfile$3, zHasCACertInCurrentUser, zHasCACertInWorkProfile, zIsNetworkLoggingEnabled, primaryVpnName, workProfileVpnName, deviceOwnerOrganizationName, workProfileOrganizationName, zIsOrganizationOwnedDeviceWithManagedProfile));
        if (zIsDeviceManaged || zIsOrganizationOwnedDeviceWithManagedProfile) {
            oplusQSSecurityController.getFooterIconId().set(2131237147);
        }
        QsStatisticUtil.vpnEnable = Intrinsics.areEqual(oplusQSSecurityController.getShowSecurity().get(), bool);
    }

    public static boolean isShowVpn(String str, boolean z, boolean z2) {
        return (z || z2 || str == null || str.length() <= 0) ? false : true;
    }

    public final void destroy() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.securityController.removeCallback(this.callback);
        this.bgHandler.removeCallbacksAndMessages(null);
    }

    public final CharSequence getCaCertsMessage(boolean z, boolean z2, boolean z3) {
        if (!z2 && !z3) {
            return null;
        }
        if (z) {
            return this.context.getString(2131954637);
        }
        Context context = this.context;
        return z3 ? context.getString(2131954633) : context.getString(2131954631);
    }

    public NullableObservableField<CharSequence> getContentText() {
        return this.contentText;
    }

    public NullableObservableField<Integer> getFooterIconId() {
        return this.footerIconId;
    }

    public final CharSequence getFooterText(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str, String str2, CharSequence charSequence, CharSequence charSequence2, boolean z6) {
        if (!z) {
            if (z4) {
                Context context = this.context;
                return charSequence2 == null ? context.getString(2131955770) : context.getString(2131955778, charSequence2);
            }
            if (z3) {
                return this.context.getString(2131955777);
            }
            if (str != null && str2 != null) {
                return this.context.getString(2131955788);
            }
            if (str2 != null) {
                return this.context.getString(2131955771, str2);
            }
            if (str != null) {
                Context context2 = this.context;
                return z2 ? context2.getString(2131955786, str) : context2.getString(2131955783, str);
            }
            if (!z6) {
                return null;
            }
            Context context3 = this.context;
            return charSequence2 == null ? context3.getString(2131955773) : context3.getString(2131955779, charSequence2);
        }
        if (z3 || z4 || z5) {
            Context context4 = this.context;
            return charSequence == null ? context4.getString(2131955774) : context4.getString(2131955780, charSequence);
        }
        if (str != null && str2 != null) {
            Context context5 = this.context;
            return charSequence == null ? context5.getString(2131955776) : context5.getString(2131955782, charSequence);
        }
        if (str == null && str2 == null) {
            Context context6 = this.context;
            return charSequence == null ? context6.getString(2131955773) : context6.getString(2131955779, charSequence);
        }
        Context context7 = this.context;
        if (charSequence == null) {
            if (str == null) {
                str = str2;
            }
            return context7.getString(2131955775, str);
        }
        if (str == null) {
            str = str2;
        }
        return context7.getString(2131955781, charSequence, str);
    }

    public final CharSequence getManagementMessage(boolean z, CharSequence charSequence, boolean z2, CharSequence charSequence2) {
        if (!z && !z2) {
            return null;
        }
        if (!z || charSequence == null) {
            return (!z2 || charSequence2 == null) ? this.context.getString(2131954636) : this.context.getString(2131954639, charSequence2);
        }
        return this.context.getString(2131954639, charSequence);
    }

    public NullableObservableField<Boolean> getShowSecurity() {
        return this.showSecurity;
    }

    public MutableStateFlow getShowSecurityFlow() {
        return this.showSecurityFlow;
    }

    public final CharSequence getVpnMessage(String str, String str2, boolean z, boolean z2) {
        if ((str == null || str.length() == 0) && (str2 == null || str2.length() == 0)) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (z) {
            if (str != null && str2 != null) {
                spannableStringBuilder.append((CharSequence) this.context.getString(2131954645, str, str2));
                return spannableStringBuilder;
            }
            Context context = this.context;
            if (str == null) {
                str = str2;
            }
            spannableStringBuilder.append((CharSequence) context.getString(2131954640, str));
            return spannableStringBuilder;
        }
        if (str != null && str2 != null) {
            spannableStringBuilder.append((CharSequence) this.context.getString(2131954645, str, str2));
            return spannableStringBuilder;
        }
        if (str2 != null) {
            spannableStringBuilder.append((CharSequence) this.context.getString(2131954634, str2));
            return spannableStringBuilder;
        }
        Context context2 = this.context;
        if (z2) {
            spannableStringBuilder.append((CharSequence) context2.getString(2131954643, str));
            return spannableStringBuilder;
        }
        spannableStringBuilder.append((CharSequence) context2.getString(2131954649));
        return spannableStringBuilder;
    }

    public final boolean isVpnMode() {
        boolean zIsDeviceManaged = this.securityController.mDevicePolicyManager.isDeviceManaged();
        boolean zHasCACertInCurrentUser = this.securityController.hasCACertInCurrentUser();
        boolean zHasCACertInWorkProfile = this.securityController.hasCACertInWorkProfile();
        boolean zIsNetworkLoggingEnabled = this.securityController.isNetworkLoggingEnabled();
        String primaryVpnName = this.securityController.getPrimaryVpnName();
        String workProfileVpnName = this.securityController.getWorkProfileVpnName();
        if (!zIsDeviceManaged) {
            if (zHasCACertInCurrentUser || zHasCACertInWorkProfile) {
                return false;
            }
            return (primaryVpnName == null && workProfileVpnName == null) ? false : true;
        }
        if (zHasCACertInCurrentUser || zHasCACertInWorkProfile || zIsNetworkLoggingEnabled) {
            return false;
        }
        return (primaryVpnName == null && workProfileVpnName == null) ? false : true;
    }

    public final void registerCallBack() {
        this.securityController.addCallback(this.callback);
        Handler handler = this.bgHandler;
        OplusQSSecurityController$callback$1.AnonymousClass1 anonymousClass1 = new OplusQSSecurityController$callback$1.AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:34:0x0103  */
    /* JADX WARN: Code duplicated, block: B:35:0x0115  */
    /* JADX WARN: Code duplicated, block: B:38:0x0120 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:39:0x0122  */
    /* JADX WARN: Code duplicated, block: B:41:0x013e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0146  */
    /* JADX WARN: Code duplicated, block: B:44:0x0150  */
    /* JADX WARN: Code duplicated, block: B:46:0x0153 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x0155  */
    /* JADX WARN: Code duplicated, block: B:51:0x0173 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x0175  */
    /* JADX WARN: Code duplicated, block: B:56:0x0192 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:57:0x0194  */
    /* JADX WARN: Code duplicated, block: B:58:0x019c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:59:0x019e  */
    /* JADX WARN: Code duplicated, block: B:61:0x01a3  */
    public void setSecurityDialogEvents(View view, TextView textView, TextView textView2) {
        boolean z;
        boolean z2;
        int i;
        CharSequence footerText;
        CharSequence managementMessage;
        StringBuilder sb;
        CharSequence caCertsMessage;
        CharSequence charSequence;
        String string;
        CharSequence vpnMessage;
        int i2;
        boolean zIsDeviceManaged = this.securityController.mDevicePolicyManager.isDeviceManaged();
        boolean zIsOrganizationOwnedDeviceWithManagedProfile = this.securityController.mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile();
        boolean zHasWorkProfile$3 = this.securityController.hasWorkProfile$3();
        CharSequence deviceOwnerOrganizationName = this.securityController.getDeviceOwnerOrganizationName();
        CharSequence workProfileOrganizationName = this.securityController.getWorkProfileOrganizationName();
        boolean zHasCACertInCurrentUser = this.securityController.hasCACertInCurrentUser();
        boolean zHasCACertInWorkProfile = this.securityController.hasCACertInWorkProfile();
        boolean zIsNetworkLoggingEnabled = this.securityController.isNetworkLoggingEnabled();
        String primaryVpnName = this.securityController.getPrimaryVpnName();
        String workProfileVpnName = this.securityController.getWorkProfileVpnName();
        CharSequence deviceOwnerOrganizationName2 = this.securityController.getDeviceOwnerOrganizationName();
        CharSequence workProfileOrganizationName2 = this.securityController.getWorkProfileOrganizationName();
        if (zIsOrganizationOwnedDeviceWithManagedProfile) {
            z = zIsDeviceManaged;
            if (view != null) {
                view.setVisibility(8);
            }
        } else {
            if (isShowVpn(primaryVpnName, zIsDeviceManaged, zHasWorkProfile$3)) {
                if (view != null) {
                    view.setVisibility(0);
                }
                z = zIsDeviceManaged;
                if (view != null) {
                    setSecurityDialogEvents.1 r2 = new setSecurityDialogEvents.1(0);
                    r2.this$0 = this;
                    VarHandle.storeStoreFence();
                    view.setOnClickListener(r2);
                }
                if (textView2 != null) {
                    setSecurityDialogEvents.1 r1 = new setSecurityDialogEvents.1(1);
                    r1.this$0 = this;
                    VarHandle.storeStoreFence();
                    textView2.setOnClickListener(r1);
                    z2 = z;
                    i = 0;
                }
                footerText = getFooterText(z2, zHasWorkProfile$3, r16, zHasCACertInWorkProfile, zIsNetworkLoggingEnabled, primaryVpnName, workProfileVpnName, deviceOwnerOrganizationName2, workProfileOrganizationName2, zIsOrganizationOwnedDeviceWithManagedProfile);
                if (textView != null) {
                    textView.setText(footerText);
                }
                managementMessage = getManagementMessage(z2, deviceOwnerOrganizationName, zIsOrganizationOwnedDeviceWithManagedProfile, workProfileOrganizationName);
                sb = new StringBuilder();
                if (managementMessage != null) {
                    sb.append(this.qsSecurityFooterUtils.getManagementTitle(deviceOwnerOrganizationName));
                    sb.append(":");
                    sb.append(managementMessage);
                    i = 1;
                } else {
                    managementMessage = null;
                }
                caCertsMessage = getCaCertsMessage(z2, r16, zHasCACertInWorkProfile);
                charSequence = managementMessage;
                if (caCertsMessage != null) {
                    if (charSequence != null) {
                        sb.append("\n");
                    }
                    i++;
                    sb.append(this.context.getString(2131954651));
                    sb.append(":");
                    sb.append(caCertsMessage);
                } else {
                    caCertsMessage = charSequence;
                }
                if (zIsNetworkLoggingEnabled != 0) {
                    string = this.context.getString(2131954638);
                } else {
                    string = null;
                }
                if (string != null) {
                    if (caCertsMessage != null) {
                        sb.append("\n");
                    }
                    i++;
                    sb.append(this.context.getString(2131954652));
                    sb.append(":");
                    sb.append((CharSequence) string);
                    caCertsMessage = string;
                }
                vpnMessage = getVpnMessage(primaryVpnName, workProfileVpnName, z2, zHasWorkProfile$3);
                if (vpnMessage != null) {
                    if (caCertsMessage != null) {
                        sb.append("\n");
                    }
                    i++;
                    sb.append(this.context.getString(2131954653));
                    sb.append(":");
                    sb.append(vpnMessage);
                    caCertsMessage = vpnMessage;
                }
                i2 = i;
                if (i2 > 1) {
                    if (textView2 != null) {
                        textView2.setText(sb.toString());
                    }
                } else if (textView2 != null) {
                    textView2.setText(caCertsMessage);
                }
                if (textView2 != null) {
                    textView2.setMovementMethod(new LinkMovementMethod());
                }
                if (LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
                }
                StringBuilder sb2 = new StringBuilder(" setSecurityDialogEvents isDeviceManaged: ");
                sb2.append(z2);
                sb2.append(" deviceOwnerOrganization: ");
                sb2.append((Object) deviceOwnerOrganizationName);
                sb2.append(" hasCACerts: ");
                KeyguardLogger$.ExternalSyntheticOutline0.m(" hasCACertsInWorkProfile: ", " isNetworkLoggingEnabled: ", sb2, zHasCACertInCurrentUser, zHasCACertInWorkProfile);
                KeyguardLogger$.ExternalSyntheticOutline0.m(" hasWorkProfile: ", " vpnName: ", sb2, zIsNetworkLoggingEnabled, zHasWorkProfile$3);
                ConstraintWidget$.ExternalSyntheticOutline0.m(sb2, primaryVpnName, " vpnNameWorkProfile: ", workProfileVpnName, " isProfileOwnerOfOrganizationOwnedDevice: ");
                sb2.append(zIsOrganizationOwnedDeviceWithManagedProfile);
                sb2.append(" workProfileOrganizationName: ");
                sb2.append((Object) workProfileOrganizationName);
                sb2.append(" isNeedWrap: ");
                sb2.append(i2);
                sb2.append(" content: ");
                sb2.append((Object) caCertsMessage);
                Log.d("SystemUi--QuickSettings", "OplusQSSecurityController-->".concat(sb2.toString()));
                return;
            }
            z = zIsDeviceManaged;
            if (z) {
                if (view != null) {
                    view.setVisibility(0);
                }
                if (view != null) {
                    setSecurityDialogEvents.1 r3 = new setSecurityDialogEvents.1(2);
                    r3.this$0 = this;
                    VarHandle.storeStoreFence();
                    view.setOnClickListener(r3);
                }
                if (textView2 != null) {
                    setSecurityDialogEvents.1 r4 = new setSecurityDialogEvents.1(3);
                    r4.this$0 = this;
                    VarHandle.storeStoreFence();
                    textView2.setOnClickListener(r4);
                }
            } else if (view != null) {
                view.setVisibility(8);
            }
        }
        z2 = z;
        i = 0;
        footerText = getFooterText(z2, zHasWorkProfile$3, r16, zHasCACertInWorkProfile, zIsNetworkLoggingEnabled, primaryVpnName, workProfileVpnName, deviceOwnerOrganizationName2, workProfileOrganizationName2, zIsOrganizationOwnedDeviceWithManagedProfile);
        if (textView != null) {
            textView.setText(footerText);
        }
        managementMessage = getManagementMessage(z2, deviceOwnerOrganizationName, zIsOrganizationOwnedDeviceWithManagedProfile, workProfileOrganizationName);
        sb = new StringBuilder();
        if (managementMessage != null) {
            sb.append(this.qsSecurityFooterUtils.getManagementTitle(deviceOwnerOrganizationName));
            sb.append(":");
            sb.append(managementMessage);
            i = 1;
        } else {
            managementMessage = null;
        }
        caCertsMessage = getCaCertsMessage(z2, r16, zHasCACertInWorkProfile);
        charSequence = managementMessage;
        if (caCertsMessage != null) {
            if (charSequence != null) {
                sb.append("\n");
            }
            i++;
            sb.append(this.context.getString(2131954651));
            sb.append(":");
            sb.append(caCertsMessage);
        } else {
            caCertsMessage = charSequence;
        }
        if (zIsNetworkLoggingEnabled != 0) {
            string = this.context.getString(2131954638);
        } else {
            string = null;
        }
        if (string != null) {
            if (caCertsMessage != null) {
                sb.append("\n");
            }
            i++;
            sb.append(this.context.getString(2131954652));
            sb.append(":");
            sb.append((CharSequence) string);
            caCertsMessage = string;
        }
        vpnMessage = getVpnMessage(primaryVpnName, workProfileVpnName, z2, zHasWorkProfile$3);
        if (vpnMessage != null) {
            if (caCertsMessage != null) {
                sb.append("\n");
            }
            i++;
            sb.append(this.context.getString(2131954653));
            sb.append(":");
            sb.append(vpnMessage);
            caCertsMessage = vpnMessage;
        }
        i2 = i;
        if (i2 > 1) {
            if (textView2 != null) {
                textView2.setText(sb.toString());
            }
        } else if (textView2 != null) {
            textView2.setText(caCertsMessage);
        }
        if (textView2 != null) {
            textView2.setMovementMethod(new LinkMovementMethod());
        }
        if (LogUtil.getNormalDebug()) {
        }
    }

    public final void showDeviceMonitoringDialog() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.panelInteractor.collapsePanels();
            Handler handler = this.bgHandler;
            OplusQSSecurityController$callback$1.AnonymousClass1 anonymousClass1 = new OplusQSSecurityController$callback$1.AnonymousClass1(2);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
        }
    }

    public final void unRegisterCallBack() {
        this.securityController.removeCallback(this.callback);
        this.bgHandler.removeCallbacksAndMessages(null);
    }
}
