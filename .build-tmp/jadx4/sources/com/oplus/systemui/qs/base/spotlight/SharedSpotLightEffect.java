package com.oplus.systemui.qs.base.spotlight;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.car.app.SurfaceContainer$;
import androidx.car.app.model.constraints.ActionsConstraints$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.coui.appcompat.spotlight.COUISpotLightEffect;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.oplus.graphics.OplusPathAdapter;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SharedSpotLightEffect {
    public static final SharedSpotLightEffect INSTANCE = new SharedSpotLightEffect();
    private static final String TAG = "SharedSpotLightEffect";
    public static boolean isOneHandedEnable;
    private static MotionEvent lastSpotLightDownEvent;
    private static final OplusPathAdapter oplusPathAdapter;
    public static int screenHeight;
    private static final int[] spotLightTmpCardLoc;
    private static final Path tempPath;
    private static final Map<Integer, TypeState> typeStates;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SpotLightSession {
        public int activePointerId;
        public boolean awaitingExtinguishEnd;
        private final Path clipPath;
        private final COUISpotLightEffectDrawable drawable;
        public boolean isSupportSpotLight;
        private COUISpotLightEffect.SpotLightType lastAppliedConfig;
        public boolean layerActive;
        public boolean pausedOutsideBounds;
        public boolean recycling;

        public SpotLightSession(COUISpotLightEffectDrawable cOUISpotLightEffectDrawable, Path path, int i, boolean z, boolean z2, boolean z3, boolean z4, COUISpotLightEffect.SpotLightType spotLightType, boolean z5) {
            this.drawable = cOUISpotLightEffectDrawable;
            this.clipPath = path;
            this.activePointerId = i;
            this.pausedOutsideBounds = z;
            this.layerActive = z2;
            this.awaitingExtinguishEnd = z3;
            this.recycling = z4;
            this.lastAppliedConfig = spotLightType;
            this.isSupportSpotLight = z5;
        }

        public final COUISpotLightEffectDrawable component1() {
            return this.drawable;
        }

        public final Path component2() {
            return this.clipPath;
        }

        public final COUISpotLightEffect.SpotLightType component8() {
            return this.lastAppliedConfig;
        }

        public final SpotLightSession copy(COUISpotLightEffectDrawable cOUISpotLightEffectDrawable, Path path, int i, boolean z, boolean z2, boolean z3, boolean z4, COUISpotLightEffect.SpotLightType spotLightType, boolean z5) {
            return new SpotLightSession(cOUISpotLightEffectDrawable, path, i, z, z2, z3, z4, spotLightType, z5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpotLightSession)) {
                return false;
            }
            SpotLightSession spotLightSession = (SpotLightSession) obj;
            return Intrinsics.areEqual(this.drawable, spotLightSession.drawable) && Intrinsics.areEqual(this.clipPath, spotLightSession.clipPath) && this.activePointerId == spotLightSession.activePointerId && this.pausedOutsideBounds == spotLightSession.pausedOutsideBounds && this.layerActive == spotLightSession.layerActive && this.awaitingExtinguishEnd == spotLightSession.awaitingExtinguishEnd && this.recycling == spotLightSession.recycling && this.lastAppliedConfig == spotLightSession.lastAppliedConfig && this.isSupportSpotLight == spotLightSession.isSupportSpotLight;
        }

        public final int getActivePointerId() {
            return this.activePointerId;
        }

        public final boolean getAwaitingExtinguishEnd() {
            return this.awaitingExtinguishEnd;
        }

        public final Path getClipPath() {
            return this.clipPath;
        }

        public final COUISpotLightEffectDrawable getDrawable() {
            return this.drawable;
        }

        public final COUISpotLightEffect.SpotLightType getLastAppliedConfig() {
            return this.lastAppliedConfig;
        }

        public final boolean getLayerActive() {
            return this.layerActive;
        }

        public final boolean getPausedOutsideBounds() {
            return this.pausedOutsideBounds;
        }

        public final boolean getRecycling() {
            return this.recycling;
        }

        public final int hashCode() {
            int iM = ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.activePointerId, (this.clipPath.hashCode() + (this.drawable.hashCode() * 31)) * 31, 31), 31, this.pausedOutsideBounds), 31, this.layerActive), 31, this.awaitingExtinguishEnd), 31, this.recycling);
            COUISpotLightEffect.SpotLightType spotLightType = this.lastAppliedConfig;
            return Boolean.hashCode(this.isSupportSpotLight) + ((iM + (spotLightType == null ? 0 : spotLightType.hashCode())) * 31);
        }

        public final void setActivePointerId(int i) {
            this.activePointerId = i;
        }

        public final void setAwaitingExtinguishEnd(boolean z) {
            this.awaitingExtinguishEnd = z;
        }

        public final void setLastAppliedConfig(COUISpotLightEffect.SpotLightType spotLightType) {
            this.lastAppliedConfig = spotLightType;
        }

        public final void setLayerActive(boolean z) {
            this.layerActive = z;
        }

        public final void setPausedOutsideBounds(boolean z) {
            this.pausedOutsideBounds = z;
        }

        public final void setRecycling(boolean z) {
            this.recycling = z;
        }

        public final void setSupportSpotLight(boolean z) {
            this.isSupportSpotLight = z;
        }

        public String toString() {
            COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.drawable;
            Path path = this.clipPath;
            int i = this.activePointerId;
            boolean z = this.pausedOutsideBounds;
            boolean z2 = this.layerActive;
            boolean z3 = this.awaitingExtinguishEnd;
            boolean z4 = this.recycling;
            COUISpotLightEffect.SpotLightType spotLightType = this.lastAppliedConfig;
            boolean z5 = this.isSupportSpotLight;
            StringBuilder sb = new StringBuilder("SpotLightSession(drawable=");
            sb.append(cOUISpotLightEffectDrawable);
            sb.append(", clipPath=");
            sb.append(path);
            sb.append(", activePointerId=");
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i, ", pausedOutsideBounds=", ", layerActive=", sb, z);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", awaitingExtinguishEnd=", ", recycling=", sb, z2, z3);
            sb.append(z4);
            sb.append(", lastAppliedConfig=");
            sb.append(spotLightType);
            sb.append(", isSupportSpotLight=");
            return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sb, z5);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TypeState {
        private final ArrayDeque idleSessions;
        private final SparseArray<SpotLightSession> runningSessions;

        public TypeState(ArrayDeque arrayDeque, SparseArray<SpotLightSession> sparseArray) {
            this.idleSessions = arrayDeque;
            this.runningSessions = sparseArray;
        }

        public final ArrayDeque component1() {
            return this.idleSessions;
        }

        public final SparseArray<SpotLightSession> component2() {
            return this.runningSessions;
        }

        public final TypeState copy(ArrayDeque arrayDeque, SparseArray<SpotLightSession> sparseArray) {
            return new TypeState(arrayDeque, sparseArray);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TypeState)) {
                return false;
            }
            TypeState typeState = (TypeState) obj;
            return Intrinsics.areEqual(this.idleSessions, typeState.idleSessions) && Intrinsics.areEqual(this.runningSessions, typeState.runningSessions);
        }

        public final ArrayDeque getIdleSessions() {
            return this.idleSessions;
        }

        public final SparseArray<SpotLightSession> getRunningSessions() {
            return this.runningSessions;
        }

        public final int hashCode() {
            return this.runningSessions.hashCode() + (this.idleSessions.hashCode() * 31);
        }

        public String toString() {
            return "TypeState(idleSessions=" + this.idleSessions + ", runningSessions=" + this.runningSessions + ")";
        }
    }

    static {
        Path path = new Path();
        tempPath = path;
        oplusPathAdapter = new OplusPathAdapter(path, 1);
        typeStates = new LinkedHashMap();
        spotLightTmpCardLoc = new int[2];
    }

    public static final int access$resolveSessionType(SharedSpotLightEffect sharedSpotLightEffect, TypeState typeState) {
        Object next;
        sharedSpotLightEffect.getClass();
        Iterator<T> it = typeStates.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((Map.Entry) next).getValue() != typeState);
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return ((Number) entry.getKey()).intValue();
        }
        return -1;
    }

    public static void applySpotLightPosition(COUISpotLightEffectDrawable cOUISpotLightEffectDrawable, float f, float f2, int i, int i2) {
        float f3 = i;
        cOUISpotLightEffectDrawable.setLightPosition(RangesKt___RangesKt.coerceIn(f, 0.0f, f3) / f3, (isOneHandedEnable ? RangesKt___RangesKt.coerceIn(f2 - (screenHeight / 2), 0.0f, i2) : RangesKt___RangesKt.coerceIn(f2, 0.0f, i2)) / i2);
    }

    public static final void detach(View view, String str) {
        TypeState typeState;
        IQSSpotLightConfig iQSSpotLightConfig = view instanceof IQSSpotLightConfig ? (IQSSpotLightConfig) view : null;
        if (iQSSpotLightConfig == null || (typeState = typeStates.get(Integer.valueOf(iQSSpotLightConfig.getSpotLightType()))) == null) {
            return;
        }
        INSTANCE.getClass();
        int iIdentityHashCode = System.identityHashCode(view);
        SpotLightSession spotLightSessionFindRunningSession = findRunningSession(typeState, iIdentityHashCode, view);
        if (spotLightSessionFindRunningSession != null && typeState.getRunningSessions().get(iIdentityHashCode) == spotLightSessionFindRunningSession) {
            typeState.getRunningSessions().remove(iIdentityHashCode);
            if (spotLightSessionFindRunningSession.getRecycling()) {
                return;
            }
            spotLightSessionFindRunningSession.setRecycling(true);
            spotLightSessionFindRunningSession.setAwaitingExtinguishEnd(false);
            spotLightSessionFindRunningSession.setLayerActive(false);
            spotLightSessionFindRunningSession.setActivePointerId(-1);
            spotLightSessionFindRunningSession.setPausedOutsideBounds(false);
            spotLightSessionFindRunningSession.setSupportSpotLight(false);
            spotLightSessionFindRunningSession.getDrawable().invalidateSelf();
            recycleSession(iQSSpotLightConfig.getSpotLightType(), typeState, spotLightSessionFindRunningSession, str);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                String strTypeToString = typeToString(iQSSpotLightConfig.getSpotLightType());
                String strHostLog = hostLog(view);
                String strSessionLog = sessionLog(spotLightSessionFindRunningSession);
                String strTypeStateLog = typeStateLog(iQSSpotLightConfig.getSpotLightType(), typeState);
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("detach ", strTypeToString, " ", strHostLog, " session=");
                sbM.append(strSessionLog);
                sbM.append(" ");
                sbM.append(strTypeStateLog);
                Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat(sbM.toString()));
            }
        }
    }

    public static void dispatchSpotLightToCard(SpotLightSession spotLightSession, View view, MotionEvent motionEvent, float f, float f2) {
        if (view == null) {
            return;
        }
        float left = (f - view.getLeft()) - view.getTranslationX();
        float top = (f2 - view.getTop()) - view.getTranslationY();
        int width = view.getWidth();
        int height = view.getHeight();
        SharedSpotLightEffect$$ExternalSyntheticLambda0 sharedSpotLightEffect$$ExternalSyntheticLambda0 = new SharedSpotLightEffect$$ExternalSyntheticLambda0();
        sharedSpotLightEffect$$ExternalSyntheticLambda0.f$0 = spotLightSession;
        sharedSpotLightEffect$$ExternalSyntheticLambda0.f$1 = motionEvent;
        VarHandle.storeStoreFence();
        if (width <= 0 || height <= 0) {
            sharedSpotLightEffect$$ExternalSyntheticLambda0.invoke(Float.valueOf(left), Float.valueOf(top));
            return;
        }
        int[] iArr = spotLightTmpCardLoc;
        view.getLocationOnScreen(iArr);
        sharedSpotLightEffect$$ExternalSyntheticLambda0.invoke(Float.valueOf(left + ((motionEvent.getRawX() - iArr[0]) - left)), Float.valueOf(top + ((motionEvent.getRawY() - iArr[1]) - top)));
    }

    public static final void draw(View view, Canvas canvas) {
        TypeState typeState;
        IQSSpotLightConfig iQSSpotLightConfig = view instanceof IQSSpotLightConfig ? (IQSSpotLightConfig) view : null;
        if (iQSSpotLightConfig == null || !iQSSpotLightConfig.isViewSupportSpotLight() || (typeState = typeStates.get(Integer.valueOf(iQSSpotLightConfig.getSpotLightType()))) == null) {
            return;
        }
        INSTANCE.getClass();
        SpotLightSession spotLightSessionFindRunningSession = findRunningSession(typeState, System.identityHashCode(view), view);
        if (spotLightSessionFindRunningSession != null && spotLightSessionFindRunningSession.isSupportSpotLight && spotLightSessionFindRunningSession.getLayerActive()) {
            if (spotLightSessionFindRunningSession.getClipPath().isEmpty()) {
                spotLightSessionFindRunningSession.getDrawable().draw(canvas);
                return;
            }
            Path clipPath = spotLightSessionFindRunningSession.getClipPath();
            int iSave = canvas.save();
            canvas.clipPath(clipPath);
            try {
                spotLightSessionFindRunningSession.getDrawable().draw(canvas);
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public static SpotLightSession findRunningSession(TypeState typeState, int i, View view) {
        SpotLightSession spotLightSession = typeState.getRunningSessions().get(i);
        if (spotLightSession != null && spotLightSession.getDrawable().getCallback() == view) {
            return spotLightSession;
        }
        return null;
    }

    public static String hostLog(View view) {
        return view == null ? "host=null" : CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(view.getClass().getSimpleName(), " hostKey=0x", Integer.toHexString(System.identityHashCode(view)));
    }

    /* JADX WARN: Code duplicated, block: B:104:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:118:0x0311  */
    /* JADX WARN: Code duplicated, block: B:120:0x0314 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:121:0x0316  */
    /* JADX WARN: Code duplicated, block: B:123:0x0319  */
    /* JADX WARN: Code duplicated, block: B:125:0x031c  */
    /* JADX WARN: Code duplicated, block: B:127:0x031f  */
    /* JADX WARN: Code duplicated, block: B:128:0x0324  */
    /* JADX WARN: Code duplicated, block: B:129:0x0327  */
    /* JADX WARN: Code duplicated, block: B:130:0x032a  */
    /* JADX WARN: Code duplicated, block: B:131:0x032d  */
    /* JADX WARN: Code duplicated, block: B:132:0x0330  */
    /* JADX WARN: Code duplicated, block: B:133:0x0333  */
    /* JADX WARN: Code duplicated, block: B:137:0x034a  */
    /* JADX WARN: Code duplicated, block: B:139:0x034d  */
    /* JADX WARN: Code duplicated, block: B:141:0x0350  */
    /* JADX WARN: Code duplicated, block: B:144:0x0354  */
    /* JADX WARN: Code duplicated, block: B:152:0x0368  */
    /* JADX WARN: Code duplicated, block: B:167:0x03af  */
    /* JADX WARN: Code duplicated, block: B:172:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:175:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x01b9  */
    /* JADX WARN: Instruction removed from duplicated block: B:139:0x034d, please report this as an issue */
    public static final void onTouchEvent(View view, MotionEvent motionEvent) {
        IQSSpotLightConfig iQSSpotLightConfig;
        TypeState typeState;
        SpotLightSession spotLightSessionFindRunningSession;
        int actionMasked;
        int step;
        int i;
        int actionMasked2;
        String strValueOf;
        SpotLightSession spotLightSession;
        SpotLightSession spotLightSession2;
        COUISpotLightEffect.SpotLightType spotLightType;
        IQSSpotLightConfig iQSSpotLightConfig2 = view instanceof IQSSpotLightConfig ? (IQSSpotLightConfig) view : null;
        if (iQSSpotLightConfig2 != null && iQSSpotLightConfig2.isViewSupportSpotLight()) {
            int actionMasked3 = motionEvent.getActionMasked();
            String str = LogUtil.TAG_QS;
            if (actionMasked3 == 0) {
                Path path = tempPath;
                path.rewind();
                iQSSpotLightConfig2.updateSpotLightPath(path, oplusPathAdapter);
                SharedSpotLightEffect sharedSpotLightEffect = INSTANCE;
                int spotLightType2 = iQSSpotLightConfig2.getSpotLightType();
                sharedSpotLightEffect.getClass();
                Map<Integer, TypeState> map = typeStates;
                Integer numValueOf = Integer.valueOf(spotLightType2);
                TypeState typeState2 = map.get(numValueOf);
                if (typeState2 == null) {
                    TypeState typeState3 = new TypeState(new ArrayDeque(), new SparseArray());
                    map.put(numValueOf, typeState3);
                    typeState2 = typeState3;
                }
                TypeState typeState4 = typeState2;
                if (!(view.getTag(2131365593) instanceof View.OnAttachStateChangeListener)) {
                    SharedSpotLightEffect$bindHostLifecycle$listener$1 sharedSpotLightEffect$bindHostLifecycle$listener$1 = new SharedSpotLightEffect$bindHostLifecycle$listener$1();
                    view.addOnAttachStateChangeListener(sharedSpotLightEffect$bindHostLifecycle$listener$1);
                    view.setTag(2131365593, sharedSpotLightEffect$bindHostLifecycle$listener$1);
                }
                int iIdentityHashCode = System.identityHashCode(view);
                SpotLightSession spotLightSessionFindRunningSession2 = findRunningSession(typeState4, iIdentityHashCode, view);
                if (spotLightSessionFindRunningSession2 == null) {
                    spotLightSession2 = (SpotLightSession) typeState4.getIdleSessions().removeFirstOrNull();
                    if (spotLightSession2 != null) {
                        resetSessionState(spotLightSession2);
                        spotLightSession2.setRecycling(false);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            iQSSpotLightConfig = iQSSpotLightConfig2;
                            spotLightSession = spotLightSessionFindRunningSession2;
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(typeStateLog(spotLightType2, typeState4), "SharedSpotLightEffect-->", "SystemUi--QuickSettings", ExifInterface$.ExternalSyntheticOutline1.m("obtainSession reuse ", typeToString(spotLightType2), " session=", sessionLog(spotLightSession2), " "));
                        } else {
                            iQSSpotLightConfig = iQSSpotLightConfig2;
                            spotLightSession = spotLightSessionFindRunningSession2;
                        }
                    } else {
                        iQSSpotLightConfig = iQSSpotLightConfig2;
                        spotLightSession = spotLightSessionFindRunningSession2;
                        spotLightSession2 = new SpotLightSession(new COUISpotLightEffectDrawable(), new Path(), -1, false, false, false, false, null, false);
                        COUISpotLightEffectDrawable drawable = spotLightSession2.getDrawable();
                        SharedSpotLightEffect$setupProgressListener$1 sharedSpotLightEffect$setupProgressListener$1 = new SharedSpotLightEffect$setupProgressListener$1();
                        sharedSpotLightEffect$setupProgressListener$1.$session = spotLightSession2;
                        sharedSpotLightEffect$setupProgressListener$1.$typeState = typeState4;
                        VarHandle.storeStoreFence();
                        drawable.setSpotLightProgressScopeListener(sharedSpotLightEffect$setupProgressListener$1);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(typeStateLog(spotLightType2, typeState4), "SharedSpotLightEffect-->", "SystemUi--QuickSettings", ExifInterface$.ExternalSyntheticOutline1.m("newSession ", typeToString(spotLightType2), " session=", sessionLog(spotLightSession2), " "));
                        }
                    }
                } else {
                    iQSSpotLightConfig = iQSSpotLightConfig2;
                    spotLightSession = spotLightSessionFindRunningSession2;
                    spotLightSession2 = spotLightSession;
                }
                spotLightSession2.setSupportSpotLight(BaseQSResPool.isSupportSpotLight());
                if (spotLightSession2.isSupportSpotLight) {
                    spotLightSession2.getDrawable().setCallback(view);
                    DrawableKt.updateBounds(spotLightSession2.getDrawable(), 0, 0, view.getWidth(), view.getHeight());
                    spotLightSession2.getClipPath().rewind();
                    if (!path.isEmpty()) {
                        spotLightSession2.getClipPath().addPath(path);
                    }
                    screenHeight = view.getContext().getResources().getDisplayMetrics().heightPixels;
                    Context context = view.getContext();
                    if (spotLightType2 == 1) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat("apply tile spotLightType ".concat(typeToString(spotLightType2))));
                        }
                        spotLightType = COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_SMALL_1;
                    } else if (spotLightType2 == 2) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat("apply highlight seekbar spotLightType ".concat(typeToString(spotLightType2))));
                        }
                        spotLightType = COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_MEDIUM_1;
                    } else if (spotLightType2 == 3) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat("apply detail spotLightType ".concat(typeToString(spotLightType2))));
                        }
                        spotLightType = COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_LARGE_1;
                    } else if (spotLightType2 != 4) {
                        LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "apply spotLightType ".concat(typeToString(spotLightType2)));
                        spotLightType = null;
                    } else {
                        if (LogUtil.getNormalDebug()) {
                            Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat("apply highlight seekbar spotLightType ".concat(typeToString(spotLightType2))));
                        }
                        spotLightType = COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_MEDIUM_1;
                    }
                    if (spotLightType == null) {
                        str = LogUtil.TAG_QS;
                    } else if (spotLightSession2.getLastAppliedConfig() == spotLightType) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat("skip applyConfig same config " + typeToString(spotLightType2) + " drawable=" + spotLightSession2.getDrawable()));
                        }
                        str = LogUtil.TAG_QS;
                    } else {
                        COUISpotLightEffectDrawable drawable2 = spotLightSession2.getDrawable();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat("applyConfig drawable=" + drawable2 + " spotLightType=" + spotLightType));
                        }
                        drawable2.setSpotLightType(context, spotLightType);
                        spotLightSession2.setLastAppliedConfig(spotLightType);
                    }
                    typeState4.getRunningSessions().put(iIdentityHashCode, spotLightSession2);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                        String strTypeToString = typeToString(spotLightType2);
                        int width = view.getWidth();
                        int height = view.getHeight();
                        boolean z = spotLightSession != null;
                        String strSessionLog = sessionLog(spotLightSession2);
                        String strHostLog = hostLog(view);
                        String strTypeStateLog = typeStateLog(spotLightType2, typeState4);
                        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(iIdentityHashCode, "attach ", strTypeToString, " ", " ");
                        RectList$.ExternalSyntheticOutline0.m(width, height, "x", " path=", sbM);
                        sbM.append(path);
                        sbM.append(" reused=");
                        sbM.append(z);
                        sbM.append(" session=");
                        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, strSessionLog, " ", strHostLog, " ");
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(strTypeStateLog, "SharedSpotLightEffect-->", "SystemUi--QuickSettings", sbM);
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->attach failed, because isSupportSpotLight is false");
                }
                typeState = typeStates.get(Integer.valueOf(iQSSpotLightConfig.getSpotLightType()));
                if (typeState == null) {
                    return;
                }
                INSTANCE.getClass();
                spotLightSessionFindRunningSession = findRunningSession(typeState, System.identityHashCode(view), view);
                if (spotLightSessionFindRunningSession == null && spotLightSessionFindRunningSession.isSupportSpotLight) {
                    if (motionEvent.getActionMasked() != 2 && LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                        String strTypeToString2 = typeToString(iQSSpotLightConfig.getSpotLightType());
                        actionMasked2 = motionEvent.getActionMasked();
                        if (actionMasked2 != 0) {
                            strValueOf = "DOWN";
                        } else if (actionMasked2 != 1) {
                            strValueOf = "UP";
                        } else if (actionMasked2 != 2) {
                            strValueOf = "MOVE";
                        } else if (actionMasked2 != 3) {
                            strValueOf = "CANCEL";
                        } else if (actionMasked2 != 5) {
                            strValueOf = "POINTER_DOWN";
                        } else if (actionMasked2 != 6) {
                            strValueOf = String.valueOf(actionMasked2);
                        } else {
                            strValueOf = "POINTER_UP";
                        }
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(hostLog(view), "SharedSpotLightEffect-->", "SystemUi--QuickSettings", ExifInterface$.ExternalSyntheticOutline1.m("onTouchEvent ", strTypeToString2, " action=", strValueOf, " "));
                    }
                    actionMasked = motionEvent.getActionMasked();
                    if (actionMasked != 0) {
                        isOneHandedEnable = BaseQSResPool.isOneHandedEnable();
                        dispatchSpotLightToCard(spotLightSessionFindRunningSession, view, motionEvent, motionEvent.getX(), motionEvent.getY());
                        return;
                    }
                    if (actionMasked != 1) {
                        if (actionMasked != 2) {
                            if (view == null && motionEvent.getPointerCount() > 0) {
                                int historySize = motionEvent.getHistorySize();
                                IntProgression intProgressionStep = RangesKt___RangesKt.step(RangesKt___RangesKt.until(0, historySize), historySize <= 10 ? 1 : 2);
                                int first = intProgressionStep.getFirst();
                                int last = intProgressionStep.getLast();
                                step = intProgressionStep.getStep();
                                if ((step <= 0 && first <= last) || (step < 0 && last <= first)) {
                                    i = 0;
                                    while (true) {
                                        dispatchSpotLightToCard(spotLightSessionFindRunningSession, view, motionEvent, motionEvent.getHistoricalX(0, first), motionEvent.getHistoricalY(0, first));
                                        if (first == last) {
                                            break;
                                        } else {
                                            first += step;
                                        }
                                    }
                                }
                                dispatchSpotLightToCard(spotLightSessionFindRunningSession, view, motionEvent, motionEvent.getX(i), motionEvent.getY(i));
                                return;
                            }
                            return;
                        }
                        if (actionMasked != 3) {
                            return;
                        }
                    }
                    dispatchSpotLightToCard(spotLightSessionFindRunningSession, view, motionEvent, motionEvent.getX(), motionEvent.getY());
                }
                return;
            }
            iQSSpotLightConfig = iQSSpotLightConfig2;
            str = LogUtil.TAG_QS;
            typeState = typeStates.get(Integer.valueOf(iQSSpotLightConfig.getSpotLightType()));
            if (typeState == null) {
                return;
            }
            INSTANCE.getClass();
            spotLightSessionFindRunningSession = findRunningSession(typeState, System.identityHashCode(view), view);
            if (spotLightSessionFindRunningSession == null) {
                return;
            }
            if (motionEvent.getActionMasked() != 2) {
                String strTypeToString3 = typeToString(iQSSpotLightConfig.getSpotLightType());
                actionMasked2 = motionEvent.getActionMasked();
                if (actionMasked2 != 0) {
                    strValueOf = "DOWN";
                } else if (actionMasked2 != 1) {
                    strValueOf = "UP";
                } else if (actionMasked2 != 2) {
                    strValueOf = "MOVE";
                } else if (actionMasked2 != 3) {
                    strValueOf = "CANCEL";
                } else if (actionMasked2 != 5) {
                    strValueOf = "POINTER_DOWN";
                } else if (actionMasked2 != 6) {
                    strValueOf = String.valueOf(actionMasked2);
                } else {
                    strValueOf = "POINTER_UP";
                }
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(hostLog(view), "SharedSpotLightEffect-->", "SystemUi--QuickSettings", ExifInterface$.ExternalSyntheticOutline1.m("onTouchEvent ", strTypeToString3, " action=", strValueOf, " "));
            }
            actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                isOneHandedEnable = BaseQSResPool.isOneHandedEnable();
                dispatchSpotLightToCard(spotLightSessionFindRunningSession, view, motionEvent, motionEvent.getX(), motionEvent.getY());
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (view == null) {
                        return;
                    }
                    int historySize2 = motionEvent.getHistorySize();
                    IntProgression intProgressionStep2 = RangesKt___RangesKt.step(RangesKt___RangesKt.until(0, historySize2), historySize2 <= 10 ? 1 : 2);
                    int first2 = intProgressionStep2.getFirst();
                    int last2 = intProgressionStep2.getLast();
                    step = intProgressionStep2.getStep();
                    i = step <= 0 ? 0 : 0;
                    dispatchSpotLightToCard(spotLightSessionFindRunningSession, view, motionEvent, motionEvent.getX(i), motionEvent.getY(i));
                    return;
                }
                if (actionMasked != 3) {
                    return;
                }
            }
            dispatchSpotLightToCard(spotLightSessionFindRunningSession, view, motionEvent, motionEvent.getX(), motionEvent.getY());
        }
    }

    public static final void recycleLastTouchEvent() {
        MotionEvent motionEvent = lastSpotLightDownEvent;
        if (motionEvent != null) {
            motionEvent.recycle();
        }
        lastSpotLightDownEvent = null;
    }

    public static void recycleSession(int i, TypeState typeState, SpotLightSession spotLightSession, String str) {
        Drawable.Callback callback = spotLightSession.getDrawable().getCallback();
        String strHostLog = hostLog(callback instanceof View ? (View) callback : null);
        spotLightSession.getDrawable().setCallback(null);
        spotLightSession.getDrawable().cancel();
        resetSessionState(spotLightSession);
        if (typeState.getIdleSessions().getSize() < 1) {
            typeState.getIdleSessions().addLast(spotLightSession);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                INSTANCE.getClass();
                String strTypeToString = typeToString(i);
                String strSessionLog = sessionLog(spotLightSession);
                String strTypeStateLog = typeStateLog(i, typeState);
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("recycleSession keep ", strTypeToString, " reason=", str, " ");
                ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, strHostLog, " session=", strSessionLog, " ");
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(strTypeStateLog, "SharedSpotLightEffect-->", "SystemUi--QuickSettings", sbM);
                return;
            }
            return;
        }
        spotLightSession.getDrawable().setCallback(null);
        spotLightSession.getDrawable().cancel();
        resetSessionState(spotLightSession);
        spotLightSession.setLastAppliedConfig(null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            INSTANCE.getClass();
            String strTypeToString2 = typeToString(i);
            String strSessionLog2 = sessionLog(spotLightSession);
            String strTypeStateLog2 = typeStateLog(i, typeState);
            StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("recycleSession release ", strTypeToString2, " reason=", str, " ");
            ConstraintWidget$.ExternalSyntheticOutline0.m(sbM2, strHostLog, " session=", strSessionLog2, " ");
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(strTypeStateLog2, "SharedSpotLightEffect-->", "SystemUi--QuickSettings", sbM2);
        }
    }

    public static void resetSessionState(SpotLightSession spotLightSession) {
        spotLightSession.setActivePointerId(-1);
        spotLightSession.setPausedOutsideBounds(false);
        spotLightSession.setLayerActive(false);
        spotLightSession.setAwaitingExtinguishEnd(false);
        spotLightSession.getClipPath().rewind();
    }

    public static String sessionLog(SpotLightSession spotLightSession) {
        String hexString = Integer.toHexString(System.identityHashCode(spotLightSession));
        String hexString2 = Integer.toHexString(System.identityHashCode(spotLightSession.getDrawable()));
        boolean layerActive = spotLightSession.getLayerActive();
        boolean awaitingExtinguishEnd = spotLightSession.getAwaitingExtinguishEnd();
        boolean recycling = spotLightSession.getRecycling();
        int activePointerId = spotLightSession.getActivePointerId();
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("Session@", hexString, "(drawable@", hexString2, ",active=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(",awaiting=", ",recycling=", sbM, layerActive, awaitingExtinguishEnd);
        sbM.append(recycling);
        sbM.append(",ptr=");
        sbM.append(activePointerId);
        sbM.append(")");
        return sbM.toString();
    }

    public static String typeStateLog(int i, TypeState typeState) {
        String strTypeToString = typeToString(i);
        return SurfaceContainer$.ExternalSyntheticOutline0.m(typeState.getRunningSessions().size(), ")", ConstraintSet$.ExternalSyntheticOutline0.m(typeState.getIdleSessions().getSize(), "state(", strTypeToString, " idle=", ", running="));
    }

    public static String typeToString(int i) {
        if (i == 1) {
            return "TYPE_TILE";
        }
        if (i == 2) {
            return "TYPE_HIGHLIGHT";
        }
        if (i != 3) {
            return i != 4 ? ActionsConstraints$.ExternalSyntheticOutline0.m(i, "UNKNOWN(", ")") : "TYPE_SEEKBAR";
        }
        return "TYPE_DETAIL";
    }

    public static final void updateBounds(View view, int i, int i2) {
        TypeState typeState;
        if (i <= 0 || i2 <= 0) {
            detach(view, "view size is invalid");
            return;
        }
        IQSSpotLightConfig iQSSpotLightConfig = view instanceof IQSSpotLightConfig ? (IQSSpotLightConfig) view : null;
        if (iQSSpotLightConfig == null || (typeState = typeStates.get(Integer.valueOf(iQSSpotLightConfig.getSpotLightType()))) == null) {
            return;
        }
        INSTANCE.getClass();
        SpotLightSession spotLightSessionFindRunningSession = findRunningSession(typeState, System.identityHashCode(view), view);
        if (spotLightSessionFindRunningSession != null && spotLightSessionFindRunningSession.getLayerActive() && spotLightSessionFindRunningSession.isSupportSpotLight) {
            Path path = tempPath;
            path.rewind();
            iQSSpotLightConfig.updateSpotLightPath(path, oplusPathAdapter);
            spotLightSessionFindRunningSession.getDrawable().setBounds(0, 0, i, i2);
            spotLightSessionFindRunningSession.getClipPath().rewind();
            spotLightSessionFindRunningSession.getClipPath().addPath(path);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                String strTypeToString = typeToString(iQSSpotLightConfig.getSpotLightType());
                String strHostLog = hostLog(view);
                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "updateBounds ", strTypeToString, " ", " x ");
                sbM.append(i2);
                sbM.append(" ");
                sbM.append(strHostLog);
                Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat(sbM.toString()));
            }
        }
    }

    public final MotionEvent getLastSpotLightDownEvent() {
        return lastSpotLightDownEvent;
    }

    public final void setLastSpotLightDownEvent(MotionEvent motionEvent) {
        lastSpotLightDownEvent = motionEvent;
    }
}
