package com.oplusos.systemui.common.view;

import android.graphics.Rect;
import android.graphics.RenderNode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import androidx.recyclerview.widget.ChildHelper$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ViewExtKt {
    private static final String TAG = "ViewExt";

    /* JADX INFO: renamed from: com.oplusos.systemui.common.view.ViewExtKt$preloadAndRelease$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements DisposableHandle {
        public /* synthetic */ ViewExtKt$preloadAndRelease$onTouchListener$1 $onTouchListener;
        public /* synthetic */ View $this_preloadAndRelease;

        public final void dispose() {
            ViewExtKt.removeOnTouchListener(this.$this_preloadAndRelease, this.$onTouchListener);
        }
    }

    public static final void addOnTouchListener(View view, View.OnTouchListener onTouchListener, boolean z) {
        Object tag = view.getTag(2131364239);
        OnTouchListenerWrapper onTouchListenerWrapper = tag instanceof OnTouchListenerWrapper ? (OnTouchListenerWrapper) tag : null;
        if (onTouchListenerWrapper == null) {
            onTouchListenerWrapper = new OnTouchListenerWrapper();
            view.setTag(2131364239, onTouchListenerWrapper);
        }
        if (onTouchListenerWrapper.getOnTouchListeners().isEmpty()) {
            view.setOnTouchListener(onTouchListenerWrapper);
        }
        onTouchListenerWrapper.addTouchListener(onTouchListener, z);
    }

    public static final void addPositionUpdateListener(View view, String str, Function1 function1) {
        Object value = ReflectionUtils.getValue(View.class, view, "mRenderNode");
        RenderNode renderNode = value instanceof RenderNode ? (RenderNode) value : null;
        FrameInfo frameInfo = new FrameInfo(new Rect(), 0L);
        if (renderNode != null) {
            ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1 viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1 = new ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1();
            viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1.$this_addPositionUpdateListener = view;
            viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1.$frameInfo = frameInfo;
            viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1.$positionUpdated = function1;
            VarHandle.storeStoreFence();
            renderNode.addPositionUpdateListener(viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1);
            Object tag = view.getTag(2131364886);
            HashMap map = tag instanceof HashMap ? (HashMap) tag : null;
            if (map == null) {
                map = new HashMap();
                view.setTag(2131364886, map);
            }
            map.put(str, viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1);
        }
    }

    public static final void clearOnTouchListener(View view) {
        Object tag = view.getTag(2131364239);
        OnTouchListenerWrapper onTouchListenerWrapper = tag instanceof OnTouchListenerWrapper ? (OnTouchListenerWrapper) tag : null;
        if (onTouchListenerWrapper == null) {
            return;
        }
        onTouchListenerWrapper.clearTouchListener();
        view.setOnTouchListener(null);
    }

    public static final void layoutViewToCenter(View view) {
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        if (view2 == null) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ChildHelper$.ExternalSyntheticOutline0.m(view, "[layoutViewToCenter]： this.parent is null, "), null, 8);
            return;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        int width = (view2.getWidth() - view2.getPaddingLeft()) - view2.getPaddingRight();
        int height = (view2.getHeight() - view2.getPaddingTop()) - view2.getPaddingBottom();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int iM = ListPopupWindow$.ExternalSyntheticOutline0.m(width, measuredWidth, 2, view2.getPaddingLeft());
        int iM2 = ListPopupWindow$.ExternalSyntheticOutline0.m(height, measuredHeight, 2, view2.getPaddingTop());
        int i = iM + measuredWidth;
        int i2 = iM2 + measuredHeight;
        view.layout(iM, iM2, i, i2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[layoutViewToCenter]：layout(", iM, iM2, ", ", ", ");
            RectList$.ExternalSyntheticOutline0.m(i, i2, ", ", "),by child[w=", sbM);
            RectList$.ExternalSyntheticOutline0.m(measuredWidth, measuredHeight, ",h=", "] ,parent[w=", sbM);
            sbM.append(view2.getWidth());
            sbM.append(", h=");
            sbM.append(view2.getHeight());
            sbM.append(",padding=(");
            sbM.append(view2.getPaddingLeft());
            sbM.append(", ");
            sbM.append(view2.getPaddingTop());
            sbM.append(", ");
            sbM.append(view2.getPaddingRight());
            sbM.append(", ");
            sbM.append(view2.getPaddingBottom());
            sbM.append(")]");
            Log.d("SystemUi--Common", "ViewExt-->".concat(sbM.toString()));
        }
    }

    public static final void pivotAlongWith(View view, View view2) {
        if (!Intrinsics.areEqual(view.getViewRootImpl(), view2.getViewRootImpl())) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[pivotAlongWith] don't support alongWithPivot across windows");
            return;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int right = ((view.getRight() - view.getLeft()) / 2) + iArr[0];
        int bottom = ((view.getBottom() - view.getTop()) / 2) + iArr[1];
        view2.getLocationOnScreen(iArr);
        int right2 = ((view2.getRight() - view2.getLeft()) / 2) + iArr[0];
        int bottom2 = ((view2.getBottom() - view2.getTop()) / 2) + iArr[1];
        view.setPivotX(view.getPivotX() + (right2 - right));
        view.setPivotY(view.getPivotY() + (bottom2 - bottom));
    }

    public static final DisposableHandle preloadAndRelease(View view, Function0 function0, Function0 function1) {
        ViewExtKt$preloadAndRelease$onTouchListener$1 viewExtKt$preloadAndRelease$onTouchListener$1 = new ViewExtKt$preloadAndRelease$onTouchListener$1();
        viewExtKt$preloadAndRelease$onTouchListener$1.$this_preloadAndRelease = view;
        viewExtKt$preloadAndRelease$onTouchListener$1.$preloadCancelAction = function1;
        viewExtKt$preloadAndRelease$onTouchListener$1.$preloadAction = function0;
        VarHandle.storeStoreFence();
        addOnTouchListener(view, viewExtKt$preloadAndRelease$onTouchListener$1, true);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$this_preloadAndRelease = view;
        anonymousClass1.$onTouchListener = viewExtKt$preloadAndRelease$onTouchListener$1;
        VarHandle.storeStoreFence();
        return anonymousClass1;
    }

    public static final void removeOnTouchListener(View view, View.OnTouchListener onTouchListener) {
        Object tag = view.getTag(2131364239);
        OnTouchListenerWrapper onTouchListenerWrapper = tag instanceof OnTouchListenerWrapper ? (OnTouchListenerWrapper) tag : null;
        if (onTouchListenerWrapper == null) {
            return;
        }
        onTouchListenerWrapper.removeTouchListener(onTouchListener);
        if (onTouchListenerWrapper.getOnTouchListeners().isEmpty()) {
            view.setOnTouchListener(null);
        }
    }

    public static final void removePositionUpdateListener(View view, String str) {
        RenderNode.PositionUpdateListener positionUpdateListener;
        Object tag = view.getTag(2131364886);
        HashMap map = tag instanceof HashMap ? (HashMap) tag : null;
        if (map == null || (positionUpdateListener = (RenderNode.PositionUpdateListener) map.get(str)) == null) {
            return;
        }
        Object value = ReflectionUtils.getValue(View.class, view, "mRenderNode");
        RenderNode renderNode = value instanceof RenderNode ? (RenderNode) value : null;
        if (renderNode != null) {
            renderNode.removePositionUpdateListener(positionUpdateListener);
        }
        map.remove(str);
        if (map.isEmpty()) {
            view.setTag(2131364886, null);
        }
    }

    public static final void scaleViewWithPivot(View view, float f, float f2, float f3) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        view.setPivotX(f2 - iArr[0]);
        view.setPivotY(f3 - iArr[1]);
        view.setScaleX(f);
        view.setScaleY(f);
    }

    public static final void scaleViewWithPivot2(View view, float f, float f2, float f3) {
        view.setPivotX(f2);
        view.setPivotY(f3);
        view.setScaleX(f);
        view.setScaleY(f);
    }

    public static final void setPerformClickedFlag(View view, boolean z) {
        view.setTag(2131364527, Boolean.valueOf(z));
    }

    public static final void setTalkBackEnabled(View view, boolean z, CharSequence charSequence) {
        if (z) {
            view.setImportantForAccessibility(1);
            view.setContentDescription(charSequence);
        } else {
            view.setImportantForAccessibility(2);
            view.setContentDescription(null);
        }
    }

    public static final void startAlongWithPosition(View view, View view2) {
        if (!Intrinsics.areEqual(view.getViewRootImpl(), view2.getViewRootImpl())) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[startAlongWithPosition] don't support alongWith across windows");
            return;
        }
        if (view.getParent() instanceof ViewGroup) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[startAlongWithPosition] " + view + " alongWith " + view2, null, 8);
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            StringBuilder sb = new StringBuilder();
            sb.append(view.hashCode());
            sb.append('@');
            sb.append(view2.hashCode());
            String string = sb.toString();
            View view3 = (View) view.getParent();
            ViewExtKt$$ExternalSyntheticLambda0 viewExtKt$$ExternalSyntheticLambda0 = new ViewExtKt$$ExternalSyntheticLambda0(0);
            viewExtKt$$ExternalSyntheticLambda0.f$0 = objectRef;
            viewExtKt$$ExternalSyntheticLambda0.f$1 = objectRef2;
            viewExtKt$$ExternalSyntheticLambda0.f$2 = view;
            VarHandle.storeStoreFence();
            addPositionUpdateListener(view3, string, viewExtKt$$ExternalSyntheticLambda0);
            ViewExtKt$$ExternalSyntheticLambda0 viewExtKt$$ExternalSyntheticLambda1 = new ViewExtKt$$ExternalSyntheticLambda0(1);
            viewExtKt$$ExternalSyntheticLambda1.f$0 = objectRef2;
            viewExtKt$$ExternalSyntheticLambda1.f$1 = objectRef;
            viewExtKt$$ExternalSyntheticLambda1.f$2 = view;
            VarHandle.storeStoreFence();
            addPositionUpdateListener(view2, string, viewExtKt$$ExternalSyntheticLambda1);
        }
    }

    public static final void startAlongWithPosition$tryUpdatePosition(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, View view) {
        FrameInfo frameInfo = (FrameInfo) objectRef.element;
        FrameInfo frameInfo2 = (FrameInfo) objectRef2.element;
        if (frameInfo == null || frameInfo2 == null) {
            return;
        }
        view.setTranslationX(frameInfo2.getRect().left - frameInfo.getRect().left);
        view.setTranslationY(frameInfo2.getRect().top - frameInfo.getRect().top);
    }

    public static final void stopAlongWithPosition(View view, View view2) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[stopAlongWithPosition] " + view + " alongWith " + view2, null, 8);
        StringBuilder sb = new StringBuilder();
        sb.append(view.hashCode());
        sb.append('@');
        sb.append(view2.hashCode());
        String string = sb.toString();
        Object parent = view.getParent();
        View view3 = parent instanceof View ? (View) parent : null;
        if (view3 != null) {
            removePositionUpdateListener(view3, string);
        }
        removePositionUpdateListener(view2, string);
    }

    public static final <T extends ViewGroup.LayoutParams> void updateLayoutParamsOptimized(View view, Function1 function1) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (((Boolean) function1.invoke(layoutParams)).booleanValue()) {
            view.setLayoutParams(layoutParams);
        }
    }
}
