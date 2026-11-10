package com.oplus.systemui.notification.row.overlay;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationOverlayInteractor implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationOverlayInteractor";
    private final Lazy aodTransition;
    private final Lazy capsuleCardControl;
    private final Context context;
    private OplusNotificationOverlayController removing;
    private OplusNotificationOverlayController showing;
    private final Set<OplusNotificationOverlayController> cache = new LinkedHashSet();
    private OverlayInfo overlayInfo = new OverlayInfo(null, null);

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ OplusNotificationOverlayInteractor this$0;

        public final void onConfigChanged(Configuration configuration) {
            OplusNotificationOverlayInteractor.access$updateResource(this.this$0);
        }

        public final void onDensityOrFontScaleChanged() {
            OplusNotificationOverlayInteractor.access$updateResource(this.this$0);
        }

        public final void onThemeChanged() {
            OplusNotificationOverlayInteractor.access$updateResource(this.this$0);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OverlayInfo {
        private final ExpandableView expandableView;
        private final CharSequence info;

        public OverlayInfo(ExpandableView expandableView, CharSequence charSequence) {
            this.expandableView = expandableView;
            this.info = charSequence;
        }

        public final ExpandableView component1() {
            return this.expandableView;
        }

        public final CharSequence component2() {
            return this.info;
        }

        public final OverlayInfo copy(ExpandableView expandableView, CharSequence charSequence) {
            return new OverlayInfo(expandableView, charSequence);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OverlayInfo)) {
                return false;
            }
            OverlayInfo overlayInfo = (OverlayInfo) obj;
            return Intrinsics.areEqual(this.expandableView, overlayInfo.expandableView) && Intrinsics.areEqual(this.info, overlayInfo.info);
        }

        public final ExpandableView getExpandableView() {
            return this.expandableView;
        }

        public final CharSequence getInfo() {
            return this.info;
        }

        public final int hashCode() {
            ExpandableView expandableView = this.expandableView;
            int iHashCode = (expandableView == null ? 0 : expandableView.hashCode()) * 31;
            CharSequence charSequence = this.info;
            return iHashCode + (charSequence != null ? charSequence.hashCode() : 0);
        }

        public String toString() {
            return "OverlayInfo(expandableView=" + this.expandableView + ", info=" + ((Object) this.info) + ")";
        }
    }

    public static Unit $r8$lambda$flpFXOetXJUhQZxPdZQJjAlaGPg(OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, OplusNotificationOverlayController oplusNotificationOverlayController, ExpandableView expandableView, String str) {
        oplusNotificationOverlayInteractor.removing = null;
        ((NotificationAodTransition) oplusNotificationOverlayInteractor.aodTransition.get()).onStackSecondRemoveView(oplusNotificationOverlayController.getOplusNotificationOverlay());
        oplusNotificationOverlayInteractor.cache.add(oplusNotificationOverlayController);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusNotificationOverlayInteractor-->".concat("clearCurrent-" + (expandableView != null ? expandableView.hashCode() : 0) + " end: cause=" + str));
        }
        return Unit.INSTANCE;
    }

    public OplusNotificationOverlayInteractor(Context context, ConfigurationController configurationController, DumpManager dumpManager, Lazy lazy, Lazy lazy2) {
        this.context = context;
        this.aodTransition = lazy;
        this.capsuleCardControl = lazy2;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
        dumpManager.registerNormalDumpable(TAG, this);
    }

    public static final void access$updateResource(OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor) {
        OplusNotificationOverlayController oplusNotificationOverlayController = oplusNotificationOverlayInteractor.showing;
        if (oplusNotificationOverlayController != null) {
            ((OplusNotificationOverlayControllerImpl) oplusNotificationOverlayController).updateResource();
        }
        OplusNotificationOverlayController oplusNotificationOverlayController2 = oplusNotificationOverlayInteractor.removing;
        if (oplusNotificationOverlayController2 != null) {
            ((OplusNotificationOverlayControllerImpl) oplusNotificationOverlayController2).updateResource();
        }
        Iterator<T> it = oplusNotificationOverlayInteractor.cache.iterator();
        while (it.hasNext()) {
            ((OplusNotificationOverlayControllerImpl) ((OplusNotificationOverlayController) it.next())).updateResource();
        }
    }

    public static void applyOverlayInfo$default(OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, long j, int i) {
        long j2 = (i & 1) != 0 ? 0L : j;
        boolean z = (i & 2) != 0;
        OplusNotificationOverlayController oplusNotificationOverlayController = oplusNotificationOverlayInteractor.showing;
        OplusNotificationOverlayController oplusNotificationOverlayController2 = oplusNotificationOverlayInteractor.removing;
        OverlayInfo overlayInfo = oplusNotificationOverlayInteractor.overlayInfo;
        ExpandableView expandableViewComponent1 = overlayInfo.component1();
        CharSequence charSequenceComponent2 = overlayInfo.component2();
        if (charSequenceComponent2 == null) {
            oplusNotificationOverlayInteractor.clearCurrent("info is null", j2, z);
            return;
        }
        if (expandableViewComponent1 == null) {
            oplusNotificationOverlayInteractor.clearCurrent("expandableView is null", j2, z);
            return;
        }
        if (expandableViewComponent1.equals(oplusNotificationOverlayController != null ? oplusNotificationOverlayController.getAuthorView() : null)) {
            oplusNotificationOverlayController.setNotificationOverlayInfo(charSequenceComponent2, j2, z);
            return;
        }
        if (expandableViewComponent1.equals(oplusNotificationOverlayController2 != null ? oplusNotificationOverlayController2.getAuthorView() : null)) {
            ExpandableView authorView = oplusNotificationOverlayController2.getAuthorView();
            String strM = ListPopupWindow$.ExternalSyntheticOutline0.m(authorView != null ? authorView.hashCode() : 0, "showAgain-");
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sb = new StringBuilder();
                sb.append(strM);
                sb.append(" start: ");
                sb.append(oplusNotificationOverlayController2);
                sb.append(", info=");
                sb.append((Object) charSequenceComponent2);
                DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sb, ", delay=", j2, ", animateOverlay=");
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusNotificationOverlayInteractor-->", "SystemUi--Notification", sb, z);
            }
            oplusNotificationOverlayInteractor.removing = null;
            oplusNotificationOverlayController2.bindAuthorView(oplusNotificationOverlayController2.getAuthorView());
            oplusNotificationOverlayController2.setNotificationOverlayInfo(charSequenceComponent2, j2, z);
            ((NotificationAodTransition) oplusNotificationOverlayInteractor.aodTransition.get()).onStackSecondAddView(oplusNotificationOverlayController2.getAuthorView());
            oplusNotificationOverlayInteractor.clearCurrent(strM, j2, z);
            oplusNotificationOverlayInteractor.showing = oplusNotificationOverlayController2;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m(strM, " end", "OplusNotificationOverlayInteractor-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        OplusNotificationOverlayController oplusNotificationOverlayControllerNewInstance = (OplusNotificationOverlayController) CollectionsKt___CollectionsKt.elementAtOrNull(oplusNotificationOverlayInteractor.cache, 0);
        if (oplusNotificationOverlayControllerNewInstance == null) {
            oplusNotificationOverlayControllerNewInstance = OplusNotificationOverlayController.Companion.newInstance(oplusNotificationOverlayInteractor.context, (CapsuleCardControl) oplusNotificationOverlayInteractor.capsuleCardControl.get());
        }
        boolean zRemove = oplusNotificationOverlayInteractor.cache.remove(oplusNotificationOverlayControllerNewInstance);
        String strM2 = ListPopupWindow$.ExternalSyntheticOutline0.m(expandableViewComponent1.hashCode(), "attachNew-");
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusNotificationOverlayInteractor-->".concat(strM2 + " start: useCache=" + zRemove + ", " + oplusNotificationOverlayControllerNewInstance + ", info=" + ((Object) charSequenceComponent2) + ", delay=" + j2 + ", animateOverlay=" + z));
        }
        oplusNotificationOverlayInteractor.clearCurrent(strM2, j2, z);
        oplusNotificationOverlayControllerNewInstance.bindAuthorView(expandableViewComponent1);
        oplusNotificationOverlayControllerNewInstance.setNotificationOverlayInfo(charSequenceComponent2, j2, z);
        ((NotificationAodTransition) oplusNotificationOverlayInteractor.aodTransition.get()).onStackSecondAddView(oplusNotificationOverlayControllerNewInstance.getAuthorView());
        oplusNotificationOverlayInteractor.showing = oplusNotificationOverlayControllerNewInstance;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m(strM2, " end.", "OplusNotificationOverlayInteractor-->", "SystemUi--Notification");
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0023  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void clearCurrent(String str, long j, boolean z) {
        long j2;
        OplusNotificationOverlayController oplusNotificationOverlayController = this.showing;
        if (oplusNotificationOverlayController == null) {
            return;
        }
        this.removing = oplusNotificationOverlayController;
        this.showing = null;
        ExpandableView authorView = oplusNotificationOverlayController.getAuthorView();
        if ((authorView != null ? authorView.getTransientContainer() : null) != null && authorView.getVisibility() == 0) {
            j2 = j >= 300 ? j : 300L;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(authorView != null ? authorView.hashCode() : 0, "clearCurrent-", " start: cause=", str, ", ");
            sbM.append(oplusNotificationOverlayController);
            sbM.append(", delay=");
            sbM.append(j);
            DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, ", realDelay=", j2, ", animateOverlay=");
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusNotificationOverlayInteractor-->", "SystemUi--Notification", sbM, z);
        }
        OplusNotificationOverlayInteractor$$ExternalSyntheticLambda0 oplusNotificationOverlayInteractor$$ExternalSyntheticLambda0 = new OplusNotificationOverlayInteractor$$ExternalSyntheticLambda0();
        oplusNotificationOverlayInteractor$$ExternalSyntheticLambda0.f$0 = this;
        oplusNotificationOverlayInteractor$$ExternalSyntheticLambda0.f$1 = oplusNotificationOverlayController;
        oplusNotificationOverlayInteractor$$ExternalSyntheticLambda0.f$2 = authorView;
        oplusNotificationOverlayInteractor$$ExternalSyntheticLambda0.f$3 = str;
        VarHandle.storeStoreFence();
        oplusNotificationOverlayController.unbindAuthorView(j2, z, oplusNotificationOverlayInteractor$$ExternalSyntheticLambda0);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("overlayInfo=" + this.overlayInfo + OplusPreventModeData.SEPARATOR);
        printWriter.println("showing=" + this.showing + OplusPreventModeData.SEPARATOR);
        printWriter.println("removing=" + this.removing + OplusPreventModeData.SEPARATOR);
        printWriter.println("cache=" + this.cache + OplusPreventModeData.SEPARATOR);
    }

    public final ViewBlurProxy getOverlayBackgroundViewBlurProxy() {
        View oplusNotificationOverlay;
        OplusNotificationOverlayController oplusNotificationOverlayController = this.showing;
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = (oplusNotificationOverlayController == null || (oplusNotificationOverlay = oplusNotificationOverlayController.getOplusNotificationOverlay()) == null) ? null : (NotificationOverlayBackgroundView) oplusNotificationOverlay.findViewById(2131364389);
        if (notificationOverlayBackgroundView != null) {
            return notificationOverlayBackgroundView.getCurrentBlurProxy();
        }
        return null;
    }

    public final boolean isOverlayShowing(ExpandableView expandableView, boolean z) {
        if (Intrinsics.areEqual(expandableView, this.overlayInfo.getExpandableView())) {
            return true;
        }
        if (!z) {
            return false;
        }
        OplusNotificationOverlayController oplusNotificationOverlayController = this.showing;
        if (!Intrinsics.areEqual(expandableView, oplusNotificationOverlayController != null ? oplusNotificationOverlayController.getAuthorView() : null)) {
            OplusNotificationOverlayController oplusNotificationOverlayController2 = this.removing;
            if (!Intrinsics.areEqual(expandableView, oplusNotificationOverlayController2 != null ? oplusNotificationOverlayController2.getAuthorView() : null)) {
                return false;
            }
        }
        return true;
    }

    public final void setOverlayInfo(ExpandableView expandableView, CharSequence charSequence) {
        if (!Intrinsics.areEqual(this.overlayInfo.getExpandableView(), expandableView) || !Intrinsics.areEqual(this.overlayInfo.getInfo(), charSequence)) {
            ExpandableView expandableView2 = this.overlayInfo.getExpandableView();
            Integer numValueOf = expandableView2 != null ? Integer.valueOf(expandableView2.hashCode()) : null;
            CharSequence info = this.overlayInfo.getInfo();
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "setOverlayInfo, (" + numValueOf + ", " + ((Object) info) + ") -> (" + (expandableView != null ? Integer.valueOf(expandableView.hashCode()) : null) + ", " + ((Object) charSequence) + ")", null, 8);
        }
        this.overlayInfo = new OverlayInfo(expandableView, charSequence);
    }
}
