package com.oplus.systemui.notification.lockscreen.effect;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.compose.animation.ChangeSize$;
import com.android.systemui.Dumpable;
import com.oplus.graphics.OplusPath;
import com.oplus.posteffect.drawable.BaseDrawable;
import com.oplus.posteffect.path.BaseRoundRectPathProvider;
import com.oplus.systemui.notification.base.radius.NotificationPath;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AutoRoundRectPathProvider extends BaseRoundRectPathProvider implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AutoRoundRectPath";
    private final NotificationPath clipHelpOplusPath;
    private final Path clipPath;
    private final PathMetaData pathMetaData;
    private NotificationSpotLightDelegate.ClipSpec spotClipSpec;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PathMetaData {
        private RectF clipRect;
        public float radius;
        public boolean useSmoothRadius;

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class ChangeType {
            public static final /* synthetic */ EnumEntries $ENTRIES;
            public static final /* synthetic */ ChangeType[] $VALUES;
            public static final ChangeType CLIP_RECT = null;
            public static final ChangeType RADIUS = null;
            public static final ChangeType SMOOTH_FLAG = null;

            /* JADX INFO: Fake field, exist only in values array */
            ChangeType EF5;

            static {
                ChangeType[] changeTypeArr = {new ChangeType("RADIUS", 0), new ChangeType("SMOOTH_FLAG", 1), new ChangeType("CLIP_RECT", 2)};
                $VALUES = changeTypeArr;
                $ENTRIES = EnumEntriesKt.enumEntries(changeTypeArr);
            }

            public static EnumEntries getEntries() {
                return $ENTRIES;
            }

            public static ChangeType valueOf(String str) {
                return (ChangeType) Enum.valueOf(ChangeType.class, str);
            }

            public static ChangeType[] values() {
                return (ChangeType[]) $VALUES.clone();
            }
        }

        /* JADX INFO: renamed from: $r8$lambda$SPDi54ZlOC-QOoLNUSx5yyfclas, reason: not valid java name */
        public static Unit m84$r8$lambda$SPDi54ZlOCQOoLNUSx5yyfclas(PathMetaData pathMetaData, RectF rectF) {
            pathMetaData.clipRect = rectF;
            return Unit.INSTANCE;
        }

        public PathMetaData(float f, boolean z, RectF rectF) {
            this.radius = f;
            this.useSmoothRadius = z;
            this.clipRect = rectF;
        }

        public final boolean checkDataChangedAndApply(Float f, Boolean bool, RectF rectF) {
            boolean z;
            boolean z2;
            boolean z3;
            if (f == null || f.floatValue() <= 0.0f) {
                f = null;
            }
            ChangeType changeType = ChangeType.RADIUS;
            Float fValueOf = Float.valueOf(this.radius);
            if (f == null) {
                z = false;
            } else {
                boolean zEquals = f.equals(fValueOf);
                z = !zEquals;
                if (!zEquals) {
                    this.radius = f.floatValue();
                }
            }
            ChangeType changeType2 = ChangeType.RADIUS;
            Boolean boolValueOf = Boolean.valueOf(this.useSmoothRadius);
            if (bool == null) {
                z2 = false;
            } else {
                boolean zEquals2 = bool.equals(boolValueOf);
                z2 = !zEquals2;
                if (!zEquals2) {
                    this.useSmoothRadius = bool.booleanValue();
                }
            }
            ChangeType changeType3 = ChangeType.RADIUS;
            RectF rectF2 = this.clipRect;
            AutoRoundRectPathProvider$PathMetaData$$ExternalSyntheticLambda0 autoRoundRectPathProvider$PathMetaData$$ExternalSyntheticLambda0 = new AutoRoundRectPathProvider$PathMetaData$$ExternalSyntheticLambda0();
            autoRoundRectPathProvider$PathMetaData$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            if (rectF == null) {
                z3 = false;
            } else {
                boolean zEquals3 = rectF.equals(rectF2);
                z3 = !zEquals3;
                if (!zEquals3) {
                    autoRoundRectPathProvider$PathMetaData$$ExternalSyntheticLambda0.invoke(rectF);
                }
            }
            return z || z2 || z3;
        }

        public final RectF component3() {
            return this.clipRect;
        }

        public final PathMetaData copy(float f, boolean z, RectF rectF) {
            return new PathMetaData(f, z, rectF);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PathMetaData)) {
                return false;
            }
            PathMetaData pathMetaData = (PathMetaData) obj;
            return Float.compare(this.radius, pathMetaData.radius) == 0 && this.useSmoothRadius == pathMetaData.useSmoothRadius && Intrinsics.areEqual(this.clipRect, pathMetaData.clipRect);
        }

        public final RectF getClipRect() {
            return this.clipRect;
        }

        public final float getRadius() {
            return this.radius;
        }

        public final boolean getUseSmoothRadius() {
            return this.useSmoothRadius;
        }

        public final int hashCode() {
            return this.clipRect.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(Float.hashCode(this.radius) * 31, 31, this.useSmoothRadius);
        }

        public final void setClipRect(RectF rectF) {
            this.clipRect = rectF;
        }

        public final void setRadius(float f) {
            this.radius = f;
        }

        public final void setUseSmoothRadius(boolean z) {
            this.useSmoothRadius = z;
        }

        public String toString() {
            return "PathMetaData(radius=" + this.radius + ", useSmoothRadius=" + this.useSmoothRadius + ", clipRect=" + this.clipRect + ")";
        }
    }

    public AutoRoundRectPathProvider(float f) {
        super(f, f);
        this.pathMetaData = new PathMetaData(f, false, new RectF());
        Path path = new Path();
        this.clipPath = path;
        this.spotClipSpec = new NotificationSpotLightDelegate.ClipSpec(0, 0, 0, 0, 0.0f, 0.0f, false);
        this.clipHelpOplusPath = new NotificationPath(path);
        updateRadiusAndPath();
    }

    public static /* synthetic */ void updateMetaData$default(AutoRoundRectPathProvider autoRoundRectPathProvider, Float f, Boolean bool, RectF rectF, int i) {
        if ((i & 1) != 0) {
            f = null;
        }
        if ((i & 2) != 0) {
            bool = null;
        }
        if ((i & 4) != 0) {
            rectF = null;
        }
        autoRoundRectPathProvider.updateMetaData(f, bool, rectF);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("CapsulePathProvider: " + this.pathMetaData);
    }

    public final Path getClipPath() {
        return this.clipPath;
    }

    public final Rect getClipRect() {
        RectF clipRect = this.pathMetaData.getClipRect();
        Rect rect = new Rect();
        clipRect.roundOut(rect);
        return rect;
    }

    public void getPath(BaseDrawable baseDrawable, Path path, OplusPath oplusPath, Object obj, boolean z) {
    }

    public final NotificationSpotLightDelegate.ClipSpec getSpotClipSpec() {
        return this.spotClipSpec;
    }

    public final void updateMetaData(Float f, Boolean bool, RectF rectF) {
        if (this.pathMetaData.checkDataChangedAndApply(f, bool, rectF)) {
            updateRadiusAndPath();
            if (NotificationSpotLightEffectManager.spotLightEnabled) {
                this.spotClipSpec = new NotificationSpotLightDelegate.ClipSpec(getClipRect().left, getClipRect().top, getClipRect().right, getClipRect().bottom, this.pathMetaData.getRadius(), this.pathMetaData.getRadius(), this.pathMetaData.getUseSmoothRadius());
            }
        }
    }

    public final void updateRadiusAndPath() {
        setCornerRadii(this.pathMetaData.getRadius(), this.pathMetaData.getRadius());
        RectF clipRect = this.pathMetaData.getClipRect();
        Path path = this.clipPath;
        NotificationPath notificationPath = this.clipHelpOplusPath;
        path.reset();
        if (!this.pathMetaData.getUseSmoothRadius()) {
            path.addRoundRect(clipRect, getCornerRadii(), Path.Direction.CW);
        } else if (notificationPath != null) {
            SmoothRoundExKt.addSmoothRoundRect(notificationPath, clipRect, getCornerRadii(), SmoothRoundEx.INSTANCE.getDefaultWeight(), Path.Direction.CCW);
        } else {
            path.addRoundRect(clipRect, getCornerRadii(), Path.Direction.CW);
        }
    }

    public final boolean useSmoothRadius() {
        return this.pathMetaData.getUseSmoothRadius();
    }
}
