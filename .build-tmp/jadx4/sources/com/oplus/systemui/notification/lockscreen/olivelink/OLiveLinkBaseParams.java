package com.oplus.systemui.notification.lockscreen.olivelink;

import com.android.systemui.statusbar.notification.OplusPhysicsInterpolator;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.olivelink.SpringType;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OLiveLinkBaseParams {
    public final void loadOLiveLinkParam(COUISpringAnimation cOUISpringAnimation, Spring spring) {
        if (cOUISpringAnimation.getSpring() == null) {
            cOUISpringAnimation.setSpring(new COUISpringForce());
        }
        cOUISpringAnimation.getSpring().setResponse(spring.response()).setBounce(spring.bounce());
    }

    public final COUISpringInterpolator toSpringInterpolation(Spring spring) {
        return new COUISpringInterpolator(spring.response(), spring.bounce());
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class OLiveLinkGroupEx extends OLiveLinkGroup {
        private final String serverName;

        public OLiveLinkGroupEx(int i, String str, String str2) {
            super(i, str);
            this.serverName = str2;
        }

        public final String getServerName() {
            return this.serverName;
        }

        public final <T extends Number> Scalar safeScalar(String str, T t) {
            return safeScalar(this.serverName, str, t);
        }

        public final Spring safeSpring(String str, float f, float f2, float f3, float f4, float f5, SpringType springType) {
            return safeSpring(this.serverName, str, f, f2, f3, f4, f5, springType);
        }

        public final OplusPhysicsInterpolator toInterpolator(Spring spring) {
            return new OplusPhysicsInterpolator(spring.bounce(), spring.response(), (Float) null);
        }

        public final Spring toScalar(ExtStateAnimParams.SpringParams springParams, String str) {
            return safeSpring(str, springParams.getBounce(), springParams.getResponse(), 0.0f, 0.0f, 0.0f, SpringType.SPRING_ANIMATION);
        }

        public final <T extends Number> Scalar toScalar(T t, String str) {
            return safeScalar(str, t);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class OLiveLinkGroup {
        public final int groupIndex;
        private final Lazy groupName$delegate;
        public int index;

        public OLiveLinkGroup(int i, String str) {
            this.groupIndex = i;
            OLiveLinkBaseParams$OLiveLinkGroup$$ExternalSyntheticLambda0 oLiveLinkBaseParams$OLiveLinkGroup$$ExternalSyntheticLambda0 = new OLiveLinkBaseParams$OLiveLinkGroup$$ExternalSyntheticLambda0();
            oLiveLinkBaseParams$OLiveLinkGroup$$ExternalSyntheticLambda0.f$0 = this;
            oLiveLinkBaseParams$OLiveLinkGroup$$ExternalSyntheticLambda0.f$1 = str;
            VarHandle.storeStoreFence();
            this.groupName$delegate = LazyKt__LazyJVMKt.lazy(oLiveLinkBaseParams$OLiveLinkGroup$$ExternalSyntheticLambda0);
        }

        private final String getGroupName() {
            return (String) this.groupName$delegate.getValue();
        }

        public final int getGroupIndex() {
            return this.groupIndex;
        }

        public final <T extends Number> Scalar safeScalar(String str, String str2, String str3, T t) {
            OliveLinkConnector oliveLinkConnector = OliveLinkConnector.INSTANCE;
            int i = this.index;
            this.index = i + 1;
            return OliveLinkConnector.safeScalar$default(oliveLinkConnector, str, String.format("%02d_%02d    %s", Arrays.copyOf(new Object[]{Integer.valueOf(this.groupIndex), Integer.valueOf(i), str2}, 3)), str3, t, 16);
        }

        public final Spring safeSpring(String str, String str2, String str3, float f, float f2, float f3, float f4, float f5, SpringType springType) {
            int i = this.index;
            this.index = i + 1;
            return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, str, String.format("%02d_%02d    %s", Arrays.copyOf(new Object[]{Integer.valueOf(this.groupIndex), Integer.valueOf(i), str2}, 3)), f, f2, f3, f4, f5, springType, null, str3, 256);
        }

        public final <T extends Number> Scalar safeScalar(String str, String str2, T t) {
            return safeScalar(str, str2, getGroupName(), t);
        }

        public final Spring safeSpring(String str, String str2, float f, float f2, float f3, float f4, float f5, SpringType springType) {
            return safeSpring(str, str2, getGroupName(), f, f2, f3, f4, f5, springType);
        }
    }
}
