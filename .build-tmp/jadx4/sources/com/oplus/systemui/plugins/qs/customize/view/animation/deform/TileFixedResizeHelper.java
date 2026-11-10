package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.util.Log;
import android.util.Size;
import android.view.GhostView;
import android.view.View;
import android.view.ViewGroup;
import androidx.car.app.hardware.common.CarZone$;
import androidx.exifinterface.media.ExifInterface$;
import com.coui.appcompat.animation.COUISpringDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.callback.IFixedResizeHelperCallback;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout$fixedResizeHelperCallback$1;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileFixedResizeHelper {
    public static final Companion Companion = new Companion();
    private static final SpanSize SIZE_1X1;
    private static final SpanSize SIZE_2X1;
    private static final String TAG = "TileResizeHelper";
    private IDeformationAnimationController animationController;
    private final ViewGroup cellHostView;
    public ViewGroup currHostView;
    private SpanSize currentFromSize;
    private final IFixedResizeHelperCallback resizeCallback;
    private final Lazy computeResult$delegate = LazyKt__LazyJVMKt.lazy(new TileFixedResizeHelper$$ExternalSyntheticLambda0());
    private final Map<SpanSize, Size> validSizeMap = new LinkedHashMap();
    private final Map<SpanSize, ViewGroup> allSupportHost = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SpanSize getSIZE_1X1() {
            return TileFixedResizeHelper.SIZE_1X1;
        }

        public final SpanSize getSIZE_2X1() {
            return TileFixedResizeHelper.SIZE_2X1;
        }
    }

    public static Unit $r8$lambda$IWd2vk266DBTmker7k6HA96cyPQ(TileFixedResizeHelper tileFixedResizeHelper, ViewGroup viewGroup) {
        ViewGroup viewGroup2 = tileFixedResizeHelper.currHostView;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        if (!Intrinsics.areEqual(viewGroup, viewGroup2)) {
            ((DeformationFrameLayout$fixedResizeHelperCallback$1) tileFixedResizeHelper.resizeCallback).recycle(viewGroup);
        }
        return Unit.INSTANCE;
    }

    static {
        SpanCache spanCache = SpanCache.INSTANCE;
        SIZE_1X1 = spanCache.spanSize(1, 1);
        SIZE_2X1 = spanCache.spanSize(2, 1);
    }

    public TileFixedResizeHelper(ViewGroup viewGroup, IFixedResizeHelperCallback iFixedResizeHelperCallback) {
        this.cellHostView = viewGroup;
        this.resizeCallback = iFixedResizeHelperCallback;
    }

    public static final void access$onSizeChange(TileFixedResizeHelper tileFixedResizeHelper, int i) {
        IFixedResizeHelperCallback iFixedResizeHelperCallback = tileFixedResizeHelper.resizeCallback;
        Size size = tileFixedResizeHelper.validSizeMap.get(SIZE_2X1);
        float width = ((size != null ? size.getWidth() : 0) - i) / 2.0f;
        if (tileFixedResizeHelper.cellHostView.isLayoutRtl()) {
            width = (tileFixedResizeHelper.cellHostView.getWidth() - tileFixedResizeHelper.cellHostView.getMeasuredWidth()) - width;
        }
        ((DeformationFrameLayout$fixedResizeHelperCallback$1) iFixedResizeHelperCallback).setRemoveIconTranslationX(width);
        ViewGroup viewGroup = tileFixedResizeHelper.currHostView;
        if (viewGroup == null) {
            viewGroup = null;
        }
        if (viewGroup.getParent() == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "TileResizeHelper-->onSizeChange  currHostView.parent == null ");
                return;
            }
            return;
        }
        ViewGroup viewGroup2 = tileFixedResizeHelper.currHostView;
        ViewGroup viewGroup3 = (ViewGroup) (viewGroup2 != null ? viewGroup2 : null).getParent();
        if (GhostView.getGhost(viewGroup3) != null) {
            viewGroup3.invalidate();
        }
    }

    private final IDeformationAnimationController.ComputeResult getComputeResult() {
        return (IDeformationAnimationController.ComputeResult) this.computeResult$delegate.getValue();
    }

    private final void setStableSizeList(Map<SpanSize, Size> map) {
        this.validSizeMap.clear();
        this.validSizeMap.putAll(map);
        Collection<Size> collectionValues = map.values();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionValues, 10));
        for (Size size : collectionValues) {
            arrayList.add(new COUISpringDragHelper.Vec2(size.getWidth(), size.getHeight()));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileResizeHelper-->".concat("setValidSize -- " + arrayList));
        }
    }

    public final void continueAnimation(IDeformationAnimationController iDeformationAnimationController, ViewGroup viewGroup, Map<SpanSize, Size> map, Function1 function1) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "continueAnimation controller:" + iDeformationAnimationController + ",  targetHostView:" + viewGroup, null, 8);
        ((DeformationFrameLayout$fixedResizeHelperCallback$1) this.resizeCallback).setDeforming(true);
        this.currHostView = viewGroup;
        this.allSupportHost.clear();
        setStableSizeList(map);
        Iterator<Map.Entry<SpanSize, Size>> it = this.validSizeMap.entrySet().iterator();
        while (it.hasNext()) {
            SpanSize key = it.next().getKey();
            View innerViewAttachedData = ((DeformationFrameLayout$fixedResizeHelperCallback$1) this.resizeCallback).getInnerViewAttachedData(key);
            ViewGroup viewGroup2 = innerViewAttachedData instanceof ViewGroup ? (ViewGroup) innerViewAttachedData : null;
            if (viewGroup2 != null) {
                this.allSupportHost.put(key, viewGroup2);
            }
        }
        IDeformationAnimationController iDeformationAnimationController2 = this.animationController;
        if (iDeformationAnimationController2 == null) {
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "continueAnimation failed: animationController is null");
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileResizeHelper-->continueAnimation ");
        }
        iDeformationAnimationController2.transferFrom(iDeformationAnimationController, viewGroup, this.allSupportHost, this.validSizeMap);
        BaseDeformationAnimationController baseDeformationAnimationController = iDeformationAnimationController2 instanceof BaseDeformationAnimationController ? (BaseDeformationAnimationController) iDeformationAnimationController2 : null;
        if (baseDeformationAnimationController != null) {
            sizeTransform.3 r9 = new sizeTransform.3(1);
            r9.this$0 = this;
            r9.$currHostView = viewGroup;
            r9.$onAnimationEnd = function1;
            VarHandle.storeStoreFence();
            baseDeformationAnimationController.setOnAnimateListener(r9);
        }
    }

    public final IDeformationAnimationController getAnimationController() {
        return this.animationController;
    }

    public final void onEnd() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            ViewGroup viewGroup = this.currHostView;
            if (viewGroup == null) {
                viewGroup = null;
            }
            Log.d("SystemUi--QuickSettings", "TileResizeHelper-->".concat("[onEnd] currHostView=" + viewGroup));
        }
        this.cellHostView.suppressLayout(false);
        this.cellHostView.setElevation(0.0f);
        IDeformationAnimationController iDeformationAnimationController = this.animationController;
        if (iDeformationAnimationController != null) {
            TileFixedResizeHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new TileFixedResizeHelper$.ExternalSyntheticLambda1();
            externalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            iDeformationAnimationController.stop(externalSyntheticLambda1);
        }
        ((DeformationFrameLayout$fixedResizeHelperCallback$1) this.resizeCallback).setRemoveIconTranslationX(0.0f);
        IDeformationAnimationController iDeformationAnimationController2 = this.animationController;
        BaseDeformationAnimationController baseDeformationAnimationController = iDeformationAnimationController2 instanceof BaseDeformationAnimationController ? (BaseDeformationAnimationController) iDeformationAnimationController2 : null;
        if (baseDeformationAnimationController != null) {
            baseDeformationAnimationController.setOnAnimateListener(null);
        }
        this.allSupportHost.clear();
        this.validSizeMap.clear();
        ((DeformationFrameLayout$fixedResizeHelperCallback$1) this.resizeCallback).setDeforming(false);
    }

    public final void setAnimationController(IDeformationAnimationController iDeformationAnimationController) {
        this.animationController = iDeformationAnimationController;
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    public final void sizeTransform(ViewGroup viewGroup, SpanSize spanSize, SpanSize spanSize2, Map<SpanSize, Size> map, Function1 function1) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileResizeHelper-->".concat("sizeTransform transferFrom controller :" + this.animationController + ", currHostView:" + viewGroup));
        }
        this.currentFromSize = spanSize;
        ((DeformationFrameLayout$fixedResizeHelperCallback$1) this.resizeCallback).setDeforming(true);
        this.currHostView = viewGroup;
        this.allSupportHost.clear();
        setStableSizeList(map);
        IDeformationAnimationController iDeformationAnimationController = this.animationController;
        if (iDeformationAnimationController != null) {
            Map<SpanSize, Size> map2 = this.validSizeMap;
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map2.size()));
            Iterator<T> it = map2.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                ViewGroup viewGroup2 = (ViewGroup) ((DeformationFrameLayout$fixedResizeHelperCallback$1) this.resizeCallback).getInnerViewAttachedData((SpanSize) entry.getKey());
                this.allSupportHost.put(entry.getKey(), viewGroup2);
                linkedHashMap.put(key, viewGroup2);
            }
            iDeformationAnimationController.prepare(viewGroup, linkedHashMap, this.validSizeMap, viewGroup.isLayoutRtl(), false);
        }
        this.cellHostView.setElevation(1.0f);
        this.cellHostView.suppressLayout(true);
        IDeformationAnimationController iDeformationAnimationController2 = this.animationController;
        BaseDeformationAnimationController baseDeformationAnimationController = iDeformationAnimationController2 instanceof BaseDeformationAnimationController ? (BaseDeformationAnimationController) iDeformationAnimationController2 : null;
        if (baseDeformationAnimationController != null) {
            sizeTransform.3 r5 = new sizeTransform.3(0);
            r5.this$0 = this;
            r5.$currHostView = viewGroup;
            r5.$onAnimationEnd = function1;
            VarHandle.storeStoreFence();
            baseDeformationAnimationController.setOnAnimateListener(r5);
        }
        IDeformationAnimationController iDeformationAnimationController3 = this.animationController;
        if (iDeformationAnimationController3 != null) {
            getComputeResult().reset();
            IDeformationAnimationController.ComputeResult computeResult = getComputeResult();
            computeResult.setStartHost(this.allSupportHost.get(spanSize));
            computeResult.setEndHost(this.allSupportHost.get(spanSize2));
            computeResult.setXProgressFromCurr(0.0f);
            computeResult.setYProgressFromCurr(0.0f);
            computeResult.setXProgress(0.0f);
            computeResult.setYProgress(0.0f);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sb = new StringBuilder("TileResizeHelper-->");
                ViewGroup startHost = computeResult.getStartHost();
                String strSimpleNameAndHash = startHost != null ? ShareTransitionProperty.Companion.simpleNameAndHash(startHost) : null;
                ViewGroup endHost = computeResult.getEndHost();
                ExifInterface$.ExternalSyntheticOutline1.m(sb, CarZone$.ExternalSyntheticOutline0.m("updateComputeResult FrameData[start=", strSimpleNameAndHash, ", end=", endHost != null ? ShareTransitionProperty.Companion.simpleNameAndHash(endHost) : null, "]"), "SystemUi--QuickSettings");
            }
            iDeformationAnimationController3.updateFrame(getComputeResult());
        }
    }
}
