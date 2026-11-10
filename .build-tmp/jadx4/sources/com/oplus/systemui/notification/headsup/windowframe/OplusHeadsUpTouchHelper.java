package com.oplus.systemui.notification.headsup.windowframe;

import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.internal.statusbar.IStatusBarService;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpTouchHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusHeadsUpTouchHelper implements Gefingerpoken {
    private static final Companion Companion = new Companion();
    private static final String TAG = "OplusHeadsUpTouchHelper";
    private HeadsUpViewControl control;
    private CouiMotionForwarder couiMotionForwarder;
    private final HeadsUpTouchHelper.Callback mCallback;
    private final HeadsUpManager mHeadsUpManager;
    public float mInitialTouchX;
    public float mInitialTouchY;
    private ExpandableNotificationRow mPickedChild;
    private final IStatusBarService mStatusBarService;
    public final float mTouchSlop;
    public boolean mTouchingHeadsUpView;
    public boolean mTrackingHeadsUp;
    public int mTrackingPointer;
    private final VelocityTracker mVelocityTracker = VelocityTracker.obtain();
    private final HeadsUpLayoutController panel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$Nuqh08Mw0oo9QDT51TsAfiO5-jk, reason: not valid java name */
    public static float m60$r8$lambda$Nuqh08Mw0oo9QDT51TsAfiO5jk(OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper) {
        oplusHeadsUpTouchHelper.mVelocityTracker.computeCurrentVelocity(1000, 8000.0f);
        return oplusHeadsUpTouchHelper.mVelocityTracker.getYVelocity();
    }

    /* JADX INFO: renamed from: $r8$lambda$Uruv-KTTyKZaaAY_YFfwVx9IFUc, reason: not valid java name */
    public static Unit m61$r8$lambda$UruvKTTyKZaaAY_YFfwVx9IFUc(OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper, View view) {
        if (view instanceof ExpandableNotificationRow) {
            oplusHeadsUpTouchHelper.mHeadsUpManager.setPulled(((ExpandableNotificationRow) view).getEntry(), false);
        }
        return Unit.INSTANCE;
    }

    public OplusHeadsUpTouchHelper(HeadsUpManager headsUpManager, IStatusBarService iStatusBarService, HeadsUpTouchHelper.Callback callback, HeadsUpLayoutController headsUpLayoutController) {
        this.mHeadsUpManager = headsUpManager;
        this.mStatusBarService = iStatusBarService;
        this.mCallback = callback;
        this.panel = headsUpLayoutController;
        this.mTouchSlop = ViewConfiguration.get(callback.getContext()).getScaledTouchSlop();
    }

    public final void cancelCouiSequence() {
        CouiMotionForwarder couiMotionForwarder = this.couiMotionForwarder;
        if (couiMotionForwarder != null) {
            ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder).forwardCancel();
        }
    }

    public final void endMotion(String str) {
        this.mTrackingPointer = -1;
        this.mPickedChild = null;
        this.mTouchingHeadsUpView = false;
    }

    public final HeadsUpViewControl getControl() {
        return this.control;
    }

    public final CouiMotionForwarder getCouiMotionForwarder() {
        return this.couiMotionForwarder;
    }

    public final HeadsUpTouchHelper.Callback getMCallback() {
        return this.mCallback;
    }

    public final boolean getMTouchingHeadsUpView() {
        return this.mTouchingHeadsUpView;
    }

    public final HeadsUpLayoutController getPanel() {
        return this.panel;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        String key;
        StatusBarNotification sbn;
        int pointerId;
        if (this.mTouchingHeadsUpView || motionEvent.getActionMasked() == 0) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.mTrackingPointer);
            if (iFindPointerIndex < 0) {
                this.mTrackingPointer = motionEvent.getPointerId(0);
                iFindPointerIndex = 0;
            }
            float x = motionEvent.getX(iFindPointerIndex);
            float y = motionEvent.getY(iFindPointerIndex);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked == 2) {
                        float f = y - this.mInitialTouchY;
                        if (this.mTouchingHeadsUpView) {
                            double d = f;
                            if (Math.abs(d) > this.mTouchSlop && Math.abs(d) > Math.abs(x - this.mInitialTouchX)) {
                                setTrackingHeadsUp(true);
                                this.mInitialTouchX = x;
                                this.mInitialTouchY = y;
                                HeadsUpViewControl headsUpViewControl = this.control;
                                if (headsUpViewControl != null) {
                                    ((OplusHeadsUpAnimator) headsUpViewControl).onStartDrag();
                                }
                                ExpandableNotificationRow expandableNotificationRow = this.mPickedChild;
                                if (expandableNotificationRow != null) {
                                    this.mHeadsUpManager.unpinAllExceptPickedChild(true, expandableNotificationRow.getEntry());
                                    this.mHeadsUpManager.setPulled(expandableNotificationRow.getEntry(), true);
                                }
                                try {
                                    this.mStatusBarService.clearNotificationEffects();
                                } catch (RemoteException unused) {
                                }
                                endMotion(null);
                                return true;
                            }
                        }
                    } else if (actionMasked != 3) {
                        if (actionMasked == 6 && this.mTrackingPointer == (pointerId = motionEvent.getPointerId(motionEvent.getActionIndex()))) {
                            int i = motionEvent.getPointerId(0) != pointerId ? 0 : 1;
                            this.mTrackingPointer = motionEvent.getPointerId(i);
                            this.mInitialTouchX = motionEvent.getX(i);
                            this.mInitialTouchY = motionEvent.getY(i);
                            return false;
                        }
                    }
                }
                ExpandableNotificationRow expandableNotificationRow2 = this.mPickedChild;
                if (expandableNotificationRow2 != null && this.mTouchingHeadsUpView) {
                    HeadsUpManager headsUpManager = this.mHeadsUpManager;
                    NotificationEntry entry = expandableNotificationRow2.getEntry();
                    if (entry == null || (sbn = entry.getSbn()) == null || (key = sbn.getKey()) == null) {
                        key = "";
                    }
                    if (headsUpManager.shouldSwallowClick(key)) {
                        endMotion(null);
                        return true;
                    }
                }
                endMotion(null);
                return false;
            }
            cancelCouiSequence();
            this.mInitialTouchY = y;
            this.mInitialTouchX = x;
            setTrackingHeadsUp(false);
            ExpandableNotificationRow childAtRawPosition = this.mCallback.getChildAtRawPosition(x, y);
            this.mTouchingHeadsUpView = false;
            if (childAtRawPosition == null) {
                NotificationEntry topEntry = this.mHeadsUpManager.getTopEntry();
                if (topEntry != null && topEntry.isRowPinned()) {
                    this.mPickedChild = topEntry.getRow();
                    this.mTouchingHeadsUpView = true;
                    return false;
                }
            } else if (childAtRawPosition instanceof ExpandableNotificationRow) {
                this.mPickedChild = childAtRawPosition;
                this.mTouchingHeadsUpView = true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001d, code lost:
    
        if (r0 != 4) goto L22;
     */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.mTrackingHeadsUp
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            com.oplus.systemui.notification.headsup.windowframe.CouiMotionForwarder r0 = r5.couiMotionForwarder
            if (r0 == 0) goto Lf
            com.oplus.systemui.notification.headsup.windowframe.HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1 r0 = (com.oplus.systemui.notification.headsup.windowframe.HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) r0
            r0.forwardMotionEvent(r6)
        Lf:
            int r0 = r6.getActionMasked()
            r2 = 1
            if (r0 == r2) goto L42
            r3 = 2
            if (r0 == r3) goto L20
            r3 = 3
            if (r0 == r3) goto L42
            r3 = 4
            if (r0 == r3) goto L42
            goto L41
        L20:
            float r0 = r6.getY()
            float r1 = r5.mInitialTouchY
            float r0 = r0 - r1
            android.view.VelocityTracker r1 = r5.mVelocityTracker
            r1.addMovement(r6)
            com.oplus.systemui.notification.headsup.windowframe.CouiMotionForwarder r6 = r5.couiMotionForwarder
            if (r6 != 0) goto L41
            com.oplus.systemui.notification.headsup.windowframe.HeadsUpViewControl r6 = r5.control
            if (r6 == 0) goto L41
            com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpTouchHelper$$ExternalSyntheticLambda0 r1 = new com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpTouchHelper$$ExternalSyntheticLambda0
            r1.<init>()
            r1.f$0 = r5
            java.lang.invoke.VarHandle.storeStoreFence()
            r6.onY(r0, r1)
        L41:
            return r2
        L42:
            android.view.VelocityTracker r0 = r5.mVelocityTracker
            r0.addMovement(r6)
            android.view.VelocityTracker r6 = r5.mVelocityTracker
            r0 = 1000(0x3e8, float:1.401E-42)
            r3 = 1165623296(0x457a0000, float:4000.0)
            r6.computeCurrentVelocity(r0, r3)
            com.oplus.systemui.notification.headsup.windowframe.HeadsUpViewControl r6 = r5.control
            r0 = 0
            if (r6 == 0) goto L6e
            android.view.VelocityTracker r3 = r5.mVelocityTracker
            float r3 = r3.getYVelocity()
            com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpTouchHelper$$ExternalSyntheticLambda1 r4 = new com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpTouchHelper$$ExternalSyntheticLambda1
            r4.<init>()
            r4.f$0 = r5
            java.lang.invoke.VarHandle.storeStoreFence()
            boolean r6 = r6.onEndDrag(r3, r4)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            goto L6f
        L6e:
            r6 = r0
        L6f:
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r3)
            if (r6 == 0) goto L98
            boolean r6 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
            if (r6 == 0) goto L8c
            java.lang.String r6 = "Notification"
            boolean r6 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r6)
            if (r6 == 0) goto L8c
            java.lang.String r6 = "SystemUi--Notification"
            java.lang.String r3 = "OplusHeadsUpTouchHelper-->onTouchEvent:isDismiss."
            android.util.Log.d(r6, r3)
        L8c:
            com.android.systemui.statusbar.notification.headsup.HeadsUpManager r6 = r5.mHeadsUpManager
            r6.snooze()
            com.android.systemui.statusbar.notification.headsup.HeadsUpManager r6 = r5.mHeadsUpManager
            java.lang.String r3 = "OplusHeadsUpTouchHelper.onTouchEvent"
            r6.releaseAllImmediately(r3)
        L98:
            android.view.VelocityTracker r6 = r5.mVelocityTracker
            r6.clear()
            r5.endMotion(r0)
            r5.setTrackingHeadsUp(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpTouchHelper.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void resetDrag() {
        HeadsUpViewControl headsUpViewControl = this.control;
        if (headsUpViewControl != null) {
            ((OplusHeadsUpAnimator) headsUpViewControl).resetDrag();
        }
    }

    public final void setControl(HeadsUpViewControl headsUpViewControl) {
        OplusHeadsUpTouchHelper$syncDragYCallback$1$1 oplusHeadsUpTouchHelper$syncDragYCallback$1$1;
        this.control = headsUpViewControl;
        CouiMotionForwarder couiMotionForwarder = this.couiMotionForwarder;
        if (couiMotionForwarder != null) {
            if (headsUpViewControl != null) {
                oplusHeadsUpTouchHelper$syncDragYCallback$1$1 = new OplusHeadsUpTouchHelper$syncDragYCallback$1$1();
                oplusHeadsUpTouchHelper$syncDragYCallback$1$1.$it = headsUpViewControl;
                VarHandle.storeStoreFence();
            } else {
                oplusHeadsUpTouchHelper$syncDragYCallback$1$1 = null;
            }
            ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder).setDragYCallback(oplusHeadsUpTouchHelper$syncDragYCallback$1$1);
        }
    }

    public final void setCouiMotionForwarder(CouiMotionForwarder couiMotionForwarder) {
        OplusHeadsUpTouchHelper$syncDragYCallback$1$1 oplusHeadsUpTouchHelper$syncDragYCallback$1$1;
        this.couiMotionForwarder = couiMotionForwarder;
        HeadsUpViewControl headsUpViewControl = this.control;
        if (couiMotionForwarder != null) {
            if (headsUpViewControl != null) {
                oplusHeadsUpTouchHelper$syncDragYCallback$1$1 = new OplusHeadsUpTouchHelper$syncDragYCallback$1$1();
                oplusHeadsUpTouchHelper$syncDragYCallback$1$1.$it = headsUpViewControl;
                VarHandle.storeStoreFence();
            } else {
                oplusHeadsUpTouchHelper$syncDragYCallback$1$1 = null;
            }
            ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) couiMotionForwarder).setDragYCallback(oplusHeadsUpTouchHelper$syncDragYCallback$1$1);
        }
    }

    public final void setMTouchingHeadsUpView(boolean z) {
        this.mTouchingHeadsUpView = z;
    }

    public void setTrackingHeadsUp(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setTrackingHeadsUp:", "OplusHeadsUpTouchHelper-->", "SystemUi--Notification", z);
        }
        this.mTrackingHeadsUp = z;
        this.mHeadsUpManager.setTrackingHeadsUp(z);
        this.panel.setTrackedHeadsUp(z ? this.mPickedChild : null, "OplusHeadsUpTouchHelper.isTrackingHeadsUp");
    }
}
