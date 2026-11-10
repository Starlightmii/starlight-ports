package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.oplus.systemui.notification.row.overlay.NotificationOverlayBackgroundView;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomRowBgAodWrapper implements AodViewWrapper {
    private final OplusCustomRowAodBackgroundChanger aodBackgroundChanger;
    private final NotificationAodLogger aodLog;
    private final Context context;
    private Pair<Integer, Integer> layer2;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    public OplusCustomRow view;

    public CustomRowBgAodWrapper(Context context, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, OplusCustomRowAodBackgroundChanger oplusCustomRowAodBackgroundChanger, NotificationAodLogger notificationAodLogger) {
        this.context = context;
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.aodBackgroundChanger = oplusCustomRowAodBackgroundChanger;
        this.aodLog = notificationAodLogger;
        this.layer2 = TuplesKt.to(Integer.valueOf(context.getColor(2131099870)), Integer.valueOf(context.getColor(2131099869)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PlatformBlurDrawable getSecondStackOverlayDrawable(OplusCustomRow oplusCustomRow) {
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = (NotificationOverlayBackgroundView) oplusCustomRow.findViewById(2131364389);
        Drawable blurDrawable = notificationOverlayBackgroundView != null ? notificationOverlayBackgroundView.getBlurDrawable() : null;
        if (blurDrawable instanceof PlatformBlurDrawable) {
            return (PlatformBlurDrawable) blurDrawable;
        }
        return null;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void clearStackColor(NotificationOverlayBackgroundView notificationOverlayBackgroundView) {
        Drawable blurDrawable = notificationOverlayBackgroundView.getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable == null || !platformBlurDrawable.isAodDirty) {
            return;
        }
        this.aodLog.logClearAodDirty(platformBlurDrawable, "clearStackColor", getView(), 2);
        platformBlurDrawable.changeBlurColor(platformBlurDrawable.getCurrentBlurMixConfig(), true);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public NormalBgAodColor createAodColor(int i) {
        BgAodData empty;
        Set linkedHashSet;
        PlatformBlurDrawable secondStackOverlayDrawable = getSecondStackOverlayDrawable(getView());
        if (secondStackOverlayDrawable == null || (empty = AodBgViewWrapperKt.access$readBgAodData(secondStackOverlayDrawable, 2)) == null) {
            empty = AodBgConstants.INSTANCE.getEMPTY();
        }
        Pair<Integer, Integer> pair = this.layer2;
        NormalBgAodColor aodColor = getAodColor();
        if (aodColor == null || (linkedHashSet = aodColor.getFirstStackCache()) == null) {
            linkedHashSet = new LinkedHashSet();
        }
        return AodBgViewWrapperKt.access$createBgAodColor(empty, pair, 2, linkedHashSet);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public NormalBgAodColor getAodColor() {
        NotificationBackgroundView mBackgroundNormal = getView().getMBackgroundNormal();
        AodColor aodColor = mBackgroundNormal != null ? AodViewWrapper.Companion.getAodColor(mBackgroundNormal) : null;
        if (aodColor instanceof NormalBgAodColor) {
            return (NormalBgAodColor) aodColor;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public OplusCustomRow getView() {
        OplusCustomRow oplusCustomRow = this.view;
        if (oplusCustomRow != null) {
            return oplusCustomRow;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(NormalBgAodColor normalBgAodColor) {
        BgAodData bgAodDataAccess$readBgAodData;
        PlatformBlurDrawable secondStackOverlayDrawable = getSecondStackOverlayDrawable(getView());
        return Intrinsics.areEqual((secondStackOverlayDrawable == null || (bgAodDataAccess$readBgAodData = AodBgViewWrapperKt.access$readBgAodData(secondStackOverlayDrawable, 2)) == null) ? null : bgAodDataAccess$readBgAodData.getOrigin(), normalBgAodColor.getStart().getOrigin());
    }

    public final boolean isSecondStackOverlay(OplusCustomRow oplusCustomRow) {
        return this.overlayInteractor.isOverlayShowing(oplusCustomRow, true);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(NormalBgAodColor normalBgAodColor) {
        PlatformBlurDrawable secondStackOverlayDrawable = getSecondStackOverlayDrawable(getView());
        if (secondStackOverlayDrawable != null) {
            this.aodLog.logClearAodDirty(secondStackOverlayDrawable, "restoreColor", getView(), 2);
            secondStackOverlayDrawable.setAodState(false);
            secondStackOverlayDrawable.changeBlurColor(secondStackOverlayDrawable.getCurrentBlurMixConfig(), true);
        }
        this.aodBackgroundChanger.changeAodOverlayViewColor(getView(), 0.0f);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setAodColor(NormalBgAodColor normalBgAodColor) {
        Set<PlatformBlurDrawable> firstStackCache;
        NormalBgAodColor aodColor = getAodColor();
        NotificationBackgroundView mBackgroundNormal = getView().getMBackgroundNormal();
        if (mBackgroundNormal != null) {
            AodViewWrapper.Companion.setAodColor(mBackgroundNormal, normalBgAodColor);
        }
        if (normalBgAodColor != null || aodColor == null || (firstStackCache = aodColor.getFirstStackCache()) == null) {
            return;
        }
        for (PlatformBlurDrawable platformBlurDrawable : firstStackCache) {
            if (platformBlurDrawable.isAodDirty) {
                this.aodLog.logClearAodDirty(platformBlurDrawable, "setNull", getView(), 2);
                platformBlurDrawable.changeBlurColor(platformBlurDrawable.getCurrentBlurMixConfig(), true);
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void setStackColor(NotificationOverlayBackgroundView notificationOverlayBackgroundView) {
        NormalBgAodColor aodColor = getAodColor();
        BgAodData curr = aodColor != null ? aodColor.getCurr() : null;
        notificationOverlayBackgroundView.preApplyBlurConfig();
        Drawable blurDrawable = notificationOverlayBackgroundView.getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable != null) {
            this.aodLog.tryLogStart(platformBlurDrawable, "setStackColor", getView(), 2);
            if (curr == null) {
                curr = AodBgConstants.INSTANCE.getEMPTY();
            }
            AodBgViewWrapperKt.writeBgAodData(platformBlurDrawable, curr);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(NormalBgAodColor normalBgAodColor, float f) {
        normalBgAodColor.setCurr(normalBgAodColor.getColor(f));
        PlatformBlurDrawable secondStackOverlayDrawable = getSecondStackOverlayDrawable(getView());
        if (secondStackOverlayDrawable != null) {
            this.aodLog.tryLogStart(secondStackOverlayDrawable, "updateColor", getView(), 2);
            normalBgAodColor.getFirstStackCache().add(secondStackOverlayDrawable);
            AodBgViewWrapperKt.writeBgAodData(secondStackOverlayDrawable, normalBgAodColor.getCurr());
        }
    }

    public final void updateResource() {
        this.layer2 = TuplesKt.to(Integer.valueOf(this.context.getColor(2131099870)), Integer.valueOf(this.context.getColor(2131099869)));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(OplusCustomRow oplusCustomRow) {
        this.view = oplusCustomRow;
    }
}
