package com.oplus.systemui.volume;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.volume.anim.VolumeItemAnimator;
import com.oplus.systemui.volume.utils.VolumeAnimUtil;
import com.oplus.systemui.volume.utils.VolumeLayoutUtil;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeListAdapter extends ListAdapter {
    public static final Companion Companion = new Companion();
    private static final List<Integer> STREAM_PRI_LIST = CollectionsKt__CollectionsKt.listOf(new Integer[]{10, 3, 11, 2, 5, 4, 0, 6, 1});
    public static final String TAG = "VolumeListAdapter";
    public static final String TAG_ANIM = "OplusVolume-Anim";
    private Context context;
    public int currentDataSource;
    public boolean isMediaRefreshLayout;
    public boolean isSystemPanel;
    private ArrayList<OplusVolumeRow> panelShowList;
    public boolean pendingMediaRefresh;
    public int pendingMediaRefreshToken;
    private final RecyclerView recyclerView;
    private ArrayList<OplusVolumeRow> sideShowList;
    public int textColor;
    public boolean touchExplorationEnabled;
    private VolumeItemAnimator volumeItemAnimator;
    private ArrayList<OplusVolumeRow> volumeRowList;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VolumeRowDiffCallback extends DiffUtil.ItemCallback {
        public static final Companion Companion = new Companion();
        private static VolumeListAdapter adapter;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final void attach(VolumeListAdapter volumeListAdapter) {
                VolumeRowDiffCallback.adapter = volumeListAdapter;
            }
        }

        public boolean areContentsTheSame(OplusVolumeRow oplusVolumeRow, OplusVolumeRow oplusVolumeRow2) {
            VolumeListAdapter volumeListAdapter = adapter;
            if (volumeListAdapter == null) {
                return false;
            }
            Companion companion = VolumeListAdapter.Companion;
            if (oplusVolumeRow != oplusVolumeRow2 || oplusVolumeRow.getView() == null) {
                return false;
            }
            int volumeListBindDataSource = oplusVolumeRow.getVolumeListBindDataSource();
            int i = volumeListAdapter.currentDataSource;
            if (volumeListBindDataSource != i) {
                return false;
            }
            if (i == 1 && oplusVolumeRow.getVolumeListBoundTextColor() != volumeListAdapter.textColor) {
                return false;
            }
            View view = oplusVolumeRow.getView();
            Integer numValueOf = view != null ? Integer.valueOf(view.getVisibility()) : null;
            View view2 = oplusVolumeRow2.getView();
            return Intrinsics.areEqual(numValueOf, view2 != null ? Integer.valueOf(view2.getVisibility()) : null) && oplusVolumeRow.isMultiMediaVolume == oplusVolumeRow2.isMultiMediaVolume && Intrinsics.areEqual(oplusVolumeRow.getMediaPackageName(), oplusVolumeRow2.getMediaPackageName()) && Intrinsics.areEqual(VolumeListAdapter.sliderAccessibilityLabel(oplusVolumeRow).toString(), VolumeListAdapter.sliderAccessibilityLabel(oplusVolumeRow2).toString());
        }

        public boolean areItemsTheSame(OplusVolumeRow oplusVolumeRow, OplusVolumeRow oplusVolumeRow2) {
            return oplusVolumeRow.getStream() == oplusVolumeRow2.getStream();
        }

        /* JADX WARN: Code duplicated, block: B:39:0x0098  */
        public Object getChangePayload(OplusVolumeRow oplusVolumeRow, OplusVolumeRow oplusVolumeRow2) {
            VolumeListAdapter volumeListAdapter = adapter;
            if (volumeListAdapter != null) {
                Companion companion = VolumeListAdapter.Companion;
                int volumeListBindDataSource = oplusVolumeRow.getVolumeListBindDataSource();
                int i = volumeListAdapter.currentDataSource;
                Integer num = null;
                if (volumeListBindDataSource != i) {
                    if (i == 1) {
                        num = 102;
                    }
                } else if (oplusVolumeRow == oplusVolumeRow2 && oplusVolumeRow.getView() != null) {
                    int volumeListBindDataSource2 = oplusVolumeRow.getVolumeListBindDataSource();
                    int i2 = volumeListAdapter.currentDataSource;
                    if (volumeListBindDataSource2 == i2 && (i2 != 1 || oplusVolumeRow.getVolumeListBoundTextColor() == volumeListAdapter.textColor)) {
                        View view = oplusVolumeRow.getView();
                        Integer numValueOf = view != null ? Integer.valueOf(view.getVisibility()) : null;
                        View view2 = oplusVolumeRow2.getView();
                        if (Intrinsics.areEqual(numValueOf, view2 != null ? Integer.valueOf(view2.getVisibility()) : null) && oplusVolumeRow.isMultiMediaVolume == oplusVolumeRow2.isMultiMediaVolume && Intrinsics.areEqual(oplusVolumeRow.getMediaPackageName(), oplusVolumeRow2.getMediaPackageName()) && !Intrinsics.areEqual(VolumeListAdapter.sliderAccessibilityLabel(oplusVolumeRow).toString(), VolumeListAdapter.sliderAccessibilityLabel(oplusVolumeRow2).toString())) {
                            num = 100;
                        } else {
                            num = 101;
                        }
                    } else {
                        num = 101;
                    }
                } else {
                    num = 101;
                }
                if (num != null) {
                    return num;
                }
            }
            return 101;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VolumeViewHolder extends RecyclerView.ViewHolder {
        private final VolumeSpringAnimation mVolumeSpringAnimation;
        private final View v;

        public VolumeViewHolder(VolumeListAdapter volumeListAdapter, View view) {
            super(view);
            this.v = view;
            VolumeSpringAnimation volumeSpringAnimation = new VolumeSpringAnimation();
            volumeSpringAnimation.mIsDirectionUp = false;
            volumeSpringAnimation.mIsAnimEnabled = false;
            this.mVolumeSpringAnimation = volumeSpringAnimation;
        }

        public final View getV() {
            return this.v;
        }

        public final void resetAnim() {
            this.mVolumeSpringAnimation.mProxyRow = null;
        }

        public final void setAnimEnableState(boolean z) {
            this.mVolumeSpringAnimation.setAnimEnableState(z);
        }

        public final void startAnim(OplusVolumeRow oplusVolumeRow) {
            VolumeSpringAnimation volumeSpringAnimation = this.mVolumeSpringAnimation;
            if (volumeSpringAnimation.mIsAnimEnabled) {
                volumeSpringAnimation.mProxyRow = oplusVolumeRow;
                if (oplusVolumeRow != null) {
                    volumeSpringAnimation.mIsDirectionUp = oplusVolumeRow.getSs().level == oplusVolumeRow.getSs().levelMax;
                    ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "OplusVolume-Anim", new StringBuilder("startAnim : mIsDirectionUp:"), volumeSpringAnimation.mIsDirectionUp);
                    volumeSpringAnimation.mProxyRow.getSlider().stopPhysicsMove();
                    boolean z = volumeSpringAnimation.mIsDirectionUp;
                    OplusVolumeRow oplusVolumeRow2 = volumeSpringAnimation.mProxyRow;
                    if (z) {
                        oplusVolumeRow2.getSlider().startCustomDeformation(2.0f, VolumeAnimUtil.VOLUME_DEFORMATION_MOVE_INTERPOLATOR);
                    } else {
                        oplusVolumeRow2.getSlider().startCustomDeformation(-1.0f, VolumeAnimUtil.VOLUME_DEFORMATION_MOVE_INTERPOLATOR);
                    }
                }
            }
        }

        public final void startBackAim() {
            VolumeSpringAnimation volumeSpringAnimation = this.mVolumeSpringAnimation;
            if (volumeSpringAnimation.mProxyRow != null) {
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "OplusVolume-Anim", new StringBuilder("startBackAim : mIsDirectionUp:"), volumeSpringAnimation.mIsDirectionUp);
                boolean z = volumeSpringAnimation.mIsDirectionUp;
                OplusVolumeRow oplusVolumeRow = volumeSpringAnimation.mProxyRow;
                if (z) {
                    oplusVolumeRow.getSlider().startCustomDeformation(1.0f, VolumeAnimUtil.VOLUME_DEFORMATION_BACK_INTERPOLATOR);
                } else {
                    oplusVolumeRow.getSlider().startCustomDeformation(0.0f, VolumeAnimUtil.VOLUME_DEFORMATION_BACK_INTERPOLATOR);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.VolumeListAdapter$clearPanel$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Object $onComplete;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ VolumeListAdapter this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.volume.VolumeListAdapter$clearPanel$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class RunnableC01751 implements Runnable {
            public /* synthetic */ Object $onComplete;
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ RunnableC01751(int i) {
                this.$r8$classId = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (this.$r8$classId) {
                    case 0:
                        Function0 function0 = (Function0) this.$onComplete;
                        if (function0 != null) {
                            function0.invoke();
                        }
                        break;
                    case 1:
                        Function0 function1 = (Function0) this.$onComplete;
                        if (function1 != null) {
                            function1.invoke();
                        }
                        break;
                    case 2:
                        ((Function0) this.$onComplete).invoke();
                        break;
                    default:
                        ((ListAdapter) ((VolumeListAdapter) this.$onComplete)).mDiffer.submitList((List) null, (Runnable) null);
                        break;
                }
            }
        }

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    VolumeListAdapter volumeListAdapter = this.this$0;
                    Function0 function0 = (Function0) this.$onComplete;
                    RunnableC01751 runnableC01751 = new RunnableC01751(0);
                    runnableC01751.$onComplete = function0;
                    VarHandle.storeStoreFence();
                    ((ListAdapter) volumeListAdapter).mDiffer.submitList((List) null, runnableC01751);
                    break;
                case 1:
                    VolumeListAdapter volumeListAdapter2 = this.this$0;
                    Function0 function1 = (Function0) this.$onComplete;
                    Companion companion = VolumeListAdapter.Companion;
                    volumeListAdapter2.dispatchSideEnterCommit(function1);
                    break;
                case 2:
                    VolumeListAdapter volumeListAdapter3 = this.this$0;
                    Companion companion2 = VolumeListAdapter.Companion;
                    volumeListAdapter3.resumeItemAnimatorAfterSideEnterBind();
                    this.this$0.dispatchSideEnterCommit((Function0) this.$onComplete);
                    break;
                default:
                    VolumeListAdapter volumeListAdapter4 = this.this$0;
                    ((ListAdapter) volumeListAdapter4).mDiffer.submitList((List) this.$onComplete, (Runnable) null);
                    break;
            }
        }
    }

    public VolumeListAdapter(RecyclerView recyclerView) {
        super(new VolumeRowDiffCallback());
        this.recyclerView = recyclerView;
        this.context = recyclerView.getContext();
        this.volumeRowList = new ArrayList<>();
        this.panelShowList = new ArrayList<>();
        this.sideShowList = new ArrayList<>();
        this.isSystemPanel = true;
        this.textColor = this.context.getColor(2131103138);
        VolumeRowDiffCallback.Companion.attach(this);
    }

    public static void attachRowViewIfNeeded(View view, FrameLayout frameLayout) {
        if (view == null || frameLayout == null || frameLayout.getChildCount() != 1 || frameLayout.getChildAt(0) != view) {
            ViewParent parent = view != null ? view.getParent() : null;
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            if (view == null || frameLayout == null) {
                return;
            }
            frameLayout.addView(view);
        }
    }

    public static void bindSideAccessibility(VolumeViewHolder volumeViewHolder) {
        View viewFindViewById = ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131364971);
        if (viewFindViewById != null) {
            viewFindViewById.setAccessibilityDelegate(null);
        }
        TextView textView = (TextView) ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131366045);
        if (textView != null) {
            textView.setVisibility(4);
            textView.setAccessibilityDelegate(null);
        }
    }

    public static CharSequence sliderAccessibilityLabel(OplusVolumeRow oplusVolumeRow) {
        String str;
        CharSequence text = null;
        if (oplusVolumeRow.isMultiMediaVolume) {
            VolumeDialogController.StreamState ss = oplusVolumeRow.getSs();
            if (ss == null || (str = ss.remoteLabel) == null) {
                TextView nameText = oplusVolumeRow.getNameText();
                if (nameText != null) {
                    text = nameText.getText();
                }
            } else {
                text = str;
            }
        } else {
            TextView nameText2 = oplusVolumeRow.getNameText();
            if (nameText2 != null) {
                text = nameText2.getText();
            }
        }
        return text == null ? "" : text;
    }

    public final void addItem(OplusVolumeRow oplusVolumeRow, Integer num) {
        int stream = oplusVolumeRow.getStream();
        TextView nameText = oplusVolumeRow.getNameText();
        CharSequence text = nameText != null ? nameText.getText() : null;
        View view = oplusVolumeRow.getView();
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "addItem: " + stream + " index: " + num + ((Object) text) + (view != null && view.getVisibility() == 0), null, 8);
        int size = (num == null || !new IntRange(0, this.volumeRowList.size(), 1).contains(num.intValue())) ? this.volumeRowList.size() : num.intValue();
        int iIndexOf = this.volumeRowList.indexOf(oplusVolumeRow);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("addItem ", size, iIndexOf, ", ", "VolumeListAdapter-->"));
        }
        if (iIndexOf < 0 || size == iIndexOf) {
            ArrayList<OplusVolumeRow> arrayList = this.volumeRowList;
            if (iIndexOf < 0) {
                arrayList.add(size, oplusVolumeRow);
                return;
            } else {
                arrayList.set(iIndexOf, oplusVolumeRow);
                return;
            }
        }
        ArrayList<OplusVolumeRow> arrayList2 = this.volumeRowList;
        if (size > iIndexOf) {
            arrayList2.add(size, oplusVolumeRow);
            this.volumeRowList.remove(iIndexOf);
        } else {
            arrayList2.add(size, oplusVolumeRow);
            this.volumeRowList.remove(iIndexOf + 1);
        }
    }

    public final void attachVolumeItemAnimator(VolumeItemAnimator volumeItemAnimator) {
        this.volumeItemAnimator = volumeItemAnimator;
        if (this.recyclerView.getItemAnimator() != volumeItemAnimator) {
            this.recyclerView.setItemAnimator(volumeItemAnimator);
        }
    }

    public final void bindPanelAccessibility(VolumeViewHolder volumeViewHolder, OplusVolumeRow oplusVolumeRow) {
        View viewFindViewById = ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131364971);
        if (viewFindViewById != null) {
            viewFindViewById.setContentDescription(sliderAccessibilityLabel(oplusVolumeRow));
            if (this.touchExplorationEnabled) {
                viewFindViewById.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.oplus.systemui.volume.VolumeListAdapter$touchExploration$1
                    @Override // android.view.View.AccessibilityDelegate
                    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
                        return new getAccessibilityNodeProvider.1(this.this$0, view);
                    }
                });
            } else {
                viewFindViewById.setAccessibilityDelegate(null);
            }
        }
        TextView textView = (TextView) ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131366045);
        textView.setVisibility(0);
        TextView nameText = oplusVolumeRow.getNameText();
        textView.setText(nameText != null ? nameText.getText() : null);
        textView.setSelected(true);
        textView.setTextColor(this.textColor);
        if (this.touchExplorationEnabled) {
            textView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.oplus.systemui.volume.VolumeListAdapter$touchExploration$1
                @Override // android.view.View.AccessibilityDelegate
                public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
                    return new getAccessibilityNodeProvider.1(this.this$0, view);
                }
            });
        } else {
            textView.setAccessibilityDelegate(null);
        }
    }

    public final void clearPanel(Function0 function0) {
        ensureVolumeItemAnimator("clearPanel");
        resetAnim();
        this.isMediaRefreshLayout = false;
        this.pendingMediaRefresh = false;
        this.pendingMediaRefreshToken++;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        anonymousClass1.$onComplete = function0;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(anonymousClass1);
    }

    public final void dispatchSideEnterCommit(Function0 function0) {
        boolean zIsAttachedToWindow = this.recyclerView.isAttachedToWindow();
        RecyclerView recyclerView = this.recyclerView;
        if (zIsAttachedToWindow) {
            AnonymousClass1.RunnableC01751 runnableC01751 = new AnonymousClass1.RunnableC01751(2);
            runnableC01751.$onComplete = function0;
            VarHandle.storeStoreFence();
            recyclerView.postOnAnimation(runnableC01751);
            return;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        anonymousClass1.$onComplete = function0;
        VarHandle.storeStoreFence();
        recyclerView.post(anonymousClass1);
    }

    public final void ensureVolumeItemAnimator(String str) {
        RecyclerView.ItemAnimator itemAnimator = this.volumeItemAnimator;
        if (itemAnimator == null || this.recyclerView.getItemAnimator() == itemAnimator) {
            return;
        }
        RecyclerView.ItemAnimator itemAnimator2 = this.recyclerView.getItemAnimator();
        LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, CursorUtil$.ExternalSyntheticOutline0.m("ensureVolumeItemAnimator restore reason=", str, " was=", itemAnimator2 != null ? itemAnimator2.getClass().getSimpleName() : null));
        this.recyclerView.setItemAnimator(itemAnimator);
    }

    public final boolean filterSideList() {
        ArrayList arrayList = new ArrayList();
        Iterator<OplusVolumeRow> it = this.volumeRowList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            OplusVolumeRow next = it.next();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                StringBuilder sb = new StringBuilder("VolumeListAdapter-->");
                TextView nameText = next.getNameText();
                CharSequence text = nameText != null ? nameText.getText() : null;
                sb.append("filterSideList row.nameText " + ((Object) text) + " volumeRow = " + next.isShowInSide);
                Log.d("SystemUi--Volume", sb.toString());
            }
            if (!next.isMultiMediaVolume && next.isShowInSide) {
                arrayList.add(next);
            }
        }
        int i = 0;
        if (arrayList.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList, new VolumeListAdapter$filterSideList$$inlined$sortBy$1(i));
        }
        boolean z = (this.sideShowList.size() == arrayList.size() && this.sideShowList.containsAll(arrayList)) ? false : true;
        if (!z) {
            ArrayList<OplusVolumeRow> arrayList2 = this.sideShowList;
            int size = arrayList2.size();
            int i2 = 0;
            int i3 = 0;
            while (i3 < size) {
                OplusVolumeRow oplusVolumeRow = arrayList2.get(i3);
                i3++;
                int i4 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                z = z || !Intrinsics.areEqual(arrayList.get(i2), oplusVolumeRow);
                i2 = i4;
            }
        }
        this.sideShowList.clear();
        this.sideShowList.addAll(arrayList);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", MobileInputLogger$.ExternalSyntheticOutline0.m("onRecycleVisibleChange sideShowList count = ", this.sideShowList.size(), ", ", "VolumeListAdapter-->", z));
        }
        return z;
    }

    public final void flushPendingSideList(Function0 function0) {
        resumeItemAnimatorAfterSideEnterBind();
        prepareSideDataSource();
        filterSideList();
        ArrayList arrayList = new ArrayList(this.sideShowList);
        if (isSameSideStreamList(arrayList)) {
            if (function0 != null) {
                function0.invoke();
            }
        } else {
            AnonymousClass1.RunnableC01751 runnableC01751 = new AnonymousClass1.RunnableC01751(1);
            runnableC01751.$onComplete = function0;
            VarHandle.storeStoreFence();
            ((ListAdapter) this).mDiffer.submitList(arrayList, runnableC01751);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getCurrentDataSource() {
        return this.currentDataSource;
    }

    public final RecyclerView getHostRecyclerView$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        return this.recyclerView;
    }

    public final ArrayList<OplusVolumeRow> getPanelShowList() {
        return this.panelShowList;
    }

    public final boolean getPendingMediaRefresh() {
        return this.pendingMediaRefresh;
    }

    public final ArrayList<OplusVolumeRow> getSideShowList() {
        return this.sideShowList;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final boolean getTouchExplorationEnabled() {
        return this.touchExplorationEnabled;
    }

    public final ArrayList<OplusVolumeRow> getVolumeRowList() {
        return this.volumeRowList;
    }

    public final int indexOf(OplusVolumeRow oplusVolumeRow) {
        return this.volumeRowList.indexOf(oplusVolumeRow);
    }

    public final boolean isSameSideStreamList(List list) {
        if (getCurrentList().size() != ((ArrayList) list).size()) {
            return false;
        }
        List currentList = getCurrentList();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(currentList, 10));
        Iterator it = currentList.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((OplusVolumeRow) it.next()).getStream()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Integer.valueOf(((OplusVolumeRow) it2.next()).getStream()));
        }
        return arrayList.equals(arrayList2);
    }

    public final boolean needsSideStructuralSubmit() {
        filterSideList();
        return !isSameSideStreamList(new ArrayList(this.sideShowList));
    }

    public void onBindViewHolder(VolumeViewHolder volumeViewHolder, int i, List<Object> list) {
        if (list.contains(102)) {
            onBindViewHolder(volumeViewHolder, i);
            return;
        }
        if (list.isEmpty()) {
            onBindViewHolder(volumeViewHolder, i);
            return;
        }
        ((RecyclerView.ViewHolder) volumeViewHolder).itemView.setAlpha(1.0f);
        for (Object obj : list) {
            if (Intrinsics.areEqual(obj, 100)) {
                OplusVolumeRow oplusVolumeRow = (OplusVolumeRow) getItem(i);
                TextView textView = (TextView) ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131366045);
                if (textView != null) {
                    TextView nameText = oplusVolumeRow.getNameText();
                    textView.setText(nameText != null ? nameText.getText() : null);
                    textView.setSelected(true);
                }
                View viewFindViewById = ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131364971);
                if (viewFindViewById != null) {
                    oplusVolumeRow.getClass();
                    viewFindViewById.setContentDescription(sliderAccessibilityLabel(oplusVolumeRow));
                }
            } else if (Intrinsics.areEqual(obj, 101)) {
                OplusVolumeRow oplusVolumeRow2 = (OplusVolumeRow) getItem(i);
                FrameLayout frameLayout = (FrameLayout) ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131365996);
                View view = oplusVolumeRow2.getView();
                attachRowViewIfNeeded(view, frameLayout);
                int i2 = this.currentDataSource;
                if (i2 == 1) {
                    VolumeLayoutUtil.resetSideVolumeProgressRevealLayout(view, frameLayout);
                    bindPanelAccessibility(volumeViewHolder, oplusVolumeRow2);
                } else if (i2 == 0) {
                    VolumeLayoutUtil.prepareSideVolumeProgressOverflow(view, frameLayout);
                    bindSideAccessibility(volumeViewHolder);
                }
                oplusVolumeRow2.setVolumeListBindDataSource(this.currentDataSource);
                if (this.currentDataSource == 1) {
                    oplusVolumeRow2.setVolumeListBoundTextColor(this.textColor);
                }
            }
        }
    }

    public VolumeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(this.context).inflate(2131558894, viewGroup, false);
        viewInflate.getClass();
        return new VolumeViewHolder(this, viewInflate);
    }

    public final void prepareSideDataSource() {
        resetAnim();
        this.isMediaRefreshLayout = false;
        this.pendingMediaRefresh = false;
        this.pendingMediaRefreshToken++;
        VolumeItemAnimator itemAnimator = this.recyclerView.getItemAnimator();
        VolumeItemAnimator volumeItemAnimator = itemAnimator instanceof VolumeItemAnimator ? itemAnimator : null;
        if (volumeItemAnimator != null) {
            volumeItemAnimator.cachedPanelBiasTranslationY = Float.NaN;
        }
        this.currentDataSource = 0;
    }

    public final void removeItem(OplusVolumeRow oplusVolumeRow) {
        TextView nameText = oplusVolumeRow.getNameText();
        CharSequence text = nameText != null ? nameText.getText() : null;
        View view = oplusVolumeRow.getView();
        boolean z = false;
        if (view != null && view.getVisibility() == 0) {
            z = true;
        }
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "removeItem " + ((Object) text) + z, null, 8);
        this.volumeRowList.remove(oplusVolumeRow);
    }

    public final void requestPanelMediaRefresh() {
        VolumeItemAnimator itemAnimator = this.recyclerView.getItemAnimator();
        VolumeItemAnimator volumeItemAnimator = itemAnimator instanceof VolumeItemAnimator ? itemAnimator : null;
        if (volumeItemAnimator == null || !volumeItemAnimator.shouldDeferMediaRefreshForPanelExpand()) {
            this.pendingMediaRefresh = false;
            this.pendingMediaRefreshToken++;
            switchToDataSourcePanel(true);
            return;
        }
        this.pendingMediaRefresh = true;
        int i = this.pendingMediaRefreshToken + 1;
        this.pendingMediaRefreshToken = i;
        RecyclerView recyclerView = this.recyclerView;
        requestPanelMediaRefresh.1 r2 = new requestPanelMediaRefresh.1();
        r2.this$0 = this;
        r2.$token = i;
        VarHandle.storeStoreFence();
        recyclerView.postDelayed(r2, 1000L);
    }

    public final void resetAnim() {
        for (OplusVolumeRow oplusVolumeRow : getCurrentList()) {
            LogUtil.i$default(LogUtil.TAG_VOLUME, "OplusVolume-Anim", ListPopupWindow$.ExternalSyntheticOutline0.m(oplusVolumeRow.getStream(), "resetAnim volumeRow.stream: "), null, 8);
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.recyclerView.findViewHolderForAdapterPosition(getCurrentList().indexOf(oplusVolumeRow));
            VolumeViewHolder volumeViewHolder = viewHolderFindViewHolderForAdapterPosition instanceof VolumeViewHolder ? (VolumeViewHolder) viewHolderFindViewHolderForAdapterPosition : null;
            if (volumeViewHolder != null) {
                volumeViewHolder.resetAnim();
            }
        }
    }

    public final void resumeItemAnimatorAfterSideEnterBind() {
        if (this.recyclerView.getItemAnimator() != null) {
            return;
        }
        RecyclerView recyclerView = this.recyclerView;
        VolumeItemAnimator volumeItemAnimator = this.volumeItemAnimator;
        if (volumeItemAnimator != null) {
            recyclerView.setItemAnimator(volumeItemAnimator);
        }
    }

    public final void setAnimEnableState(boolean z) {
        for (OplusVolumeRow oplusVolumeRow : getCurrentList()) {
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setAnimEnableState enable: ", z, " volumeRow.stream: ", oplusVolumeRow.getStream()), null, 8);
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.recyclerView.findViewHolderForAdapterPosition(getCurrentList().indexOf(oplusVolumeRow));
            VolumeViewHolder volumeViewHolder = viewHolderFindViewHolderForAdapterPosition instanceof VolumeViewHolder ? (VolumeViewHolder) viewHolderFindViewHolderForAdapterPosition : null;
            if (volumeViewHolder != null) {
                volumeViewHolder.setAnimEnableState(z);
            }
        }
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setCurrentDataSource(int i) {
        this.currentDataSource = i;
    }

    public final void setMediaRefreshLayout(boolean z) {
        this.isMediaRefreshLayout = z;
    }

    public final void setPanelShowList(ArrayList<OplusVolumeRow> arrayList) {
        this.panelShowList = arrayList;
    }

    public final void setPanelType(boolean z) {
        this.isSystemPanel = z;
    }

    public final void setPendingMediaRefresh(boolean z) {
        this.pendingMediaRefresh = z;
    }

    public final void setSideShowList(ArrayList<OplusVolumeRow> arrayList) {
        this.sideShowList = arrayList;
    }

    public final void setSystemPanel(boolean z) {
        this.isSystemPanel = z;
    }

    public final void setTextColor(int i) {
        this.textColor = i;
    }

    public final void setTouchExplorationEnabled(boolean z) {
        this.touchExplorationEnabled = z;
    }

    public final void setVolumeRowList(ArrayList<OplusVolumeRow> arrayList) {
        this.volumeRowList = arrayList;
    }

    public final void startAnimAndBackAnim(int i) {
        VolumeViewHolder volumeViewHolder;
        for (OplusVolumeRow oplusVolumeRow : getCurrentList()) {
            if (oplusVolumeRow.getStream() != i) {
                TextView nameText = oplusVolumeRow.getNameText();
                LogUtil.i$default(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "startAnimAndBackAnim BackAnim volumeRow.name: " + ((Object) (nameText != null ? nameText.getText() : null)), null, 8);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.recyclerView.findViewHolderForAdapterPosition(getCurrentList().indexOf(oplusVolumeRow));
                volumeViewHolder = viewHolderFindViewHolderForAdapterPosition instanceof VolumeViewHolder ? (VolumeViewHolder) viewHolderFindViewHolderForAdapterPosition : null;
                if (volumeViewHolder != null) {
                    volumeViewHolder.startBackAim();
                }
            } else {
                TextView nameText2 = oplusVolumeRow.getNameText();
                LogUtil.i$default(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "startAnimAndBackAnim startAnim volumeRow.name: " + ((Object) (nameText2 != null ? nameText2.getText() : null)), null, 8);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition2 = this.recyclerView.findViewHolderForAdapterPosition(getCurrentList().indexOf(oplusVolumeRow));
                volumeViewHolder = viewHolderFindViewHolderForAdapterPosition2 instanceof VolumeViewHolder ? (VolumeViewHolder) viewHolderFindViewHolderForAdapterPosition2 : null;
                if (volumeViewHolder != null) {
                    volumeViewHolder.startAnim(oplusVolumeRow);
                }
            }
        }
    }

    public final void startBackAim() {
        for (OplusVolumeRow oplusVolumeRow : getCurrentList()) {
            TextView nameText = oplusVolumeRow.getNameText();
            LogUtil.i$default(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "startBackAim volumeRow.name: " + ((Object) (nameText != null ? nameText.getText() : null)), null, 8);
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.recyclerView.findViewHolderForAdapterPosition(getCurrentList().indexOf(oplusVolumeRow));
            VolumeViewHolder volumeViewHolder = viewHolderFindViewHolderForAdapterPosition instanceof VolumeViewHolder ? (VolumeViewHolder) viewHolderFindViewHolderForAdapterPosition : null;
            if (volumeViewHolder != null) {
                volumeViewHolder.startBackAim();
            }
        }
    }

    public final void submitSideListForEnter(Function0 function0) {
        prepareSideDataSource();
        filterSideList();
        ArrayList arrayList = new ArrayList(this.sideShowList);
        if (isSameSideStreamList(arrayList)) {
            ensureVolumeItemAnimator("sideEnterSameList");
            dispatchSideEnterCommit(function0);
            return;
        }
        if (this.recyclerView.getItemAnimator() != null) {
            this.recyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = this;
        anonymousClass1.$onComplete = function0;
        VarHandle.storeStoreFence();
        ((ListAdapter) this).mDiffer.submitList(arrayList, anonymousClass1);
    }

    public final void switchToDataSourcePanel(boolean z) {
        int i;
        CharSequence charSequence;
        View view;
        Object obj;
        ensureVolumeItemAnimator(z ? "panelMediaRefresh" : "panelExpand");
        this.isMediaRefreshLayout = z;
        int i2 = 1;
        if (!z) {
            this.pendingMediaRefresh = false;
            this.pendingMediaRefreshToken++;
            resetAnim();
            this.currentDataSource = 1;
        }
        Object systemService = this.context.getSystemService("accessibility");
        AccessibilityManager accessibilityManager = systemService instanceof AccessibilityManager ? (AccessibilityManager) systemService : null;
        this.touchExplorationEnabled = accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled();
        ArrayList arrayList = new ArrayList();
        ArrayList<OplusVolumeRow> arrayList2 = this.volumeRowList;
        int size = arrayList2.size();
        int i3 = 0;
        while (true) {
            i = 3;
            if (i3 >= size) {
                break;
            }
            OplusVolumeRow oplusVolumeRow = arrayList2.get(i3);
            i3++;
            OplusVolumeRow oplusVolumeRow2 = oplusVolumeRow;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                TextView nameText = oplusVolumeRow2.getNameText();
                CharSequence text = nameText != null ? nameText.getText() : null;
                charSequence = null;
                Log.d("SystemUi--Volume", "VolumeListAdapter-->".concat("onRecycleVisibleChange row.nameText " + ((Object) text) + " volumeRow = " + oplusVolumeRow2.isShowInPanel));
            } else {
                charSequence = null;
            }
            boolean z2 = this.isSystemPanel;
            if (!z2 || oplusVolumeRow2.isMultiMediaVolume || !oplusVolumeRow2.isShowInPanel) {
                if (!z2 && ((oplusVolumeRow2.isMultiMediaVolume || 3 == oplusVolumeRow2.getStream()) && (view = oplusVolumeRow2.getView()) != null && view.getVisibility() == 0)) {
                    if (!arrayList.isEmpty()) {
                        int size2 = arrayList.size();
                        int i4 = 0;
                        do {
                            if (i4 >= size2) {
                                arrayList.add(oplusVolumeRow2);
                                break;
                                break;
                            } else {
                                obj = arrayList.get(i4);
                                i4++;
                            }
                        } while (!Intrinsics.areEqual(((OplusVolumeRow) obj).getMediaPackageName(), oplusVolumeRow2.getMediaPackageName()));
                    } else {
                        arrayList.add(oplusVolumeRow2);
                        break;
                    }
                }
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    TextView nameText2 = oplusVolumeRow2.getNameText();
                    CharSequence text2 = nameText2 != null ? nameText2.getText() : charSequence;
                    Log.d("SystemUi--Volume", "VolumeListAdapter-->".concat("filterPanelList tmpList row.nameText " + ((Object) text2) + " volumeRow = " + oplusVolumeRow2.getStream()));
                }
                arrayList.add(oplusVolumeRow2);
            }
        }
        if (this.isSystemPanel) {
            if (arrayList.size() > 1) {
                CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList, new VolumeListAdapter$filterSideList$$inlined$sortBy$1(i2));
            }
        } else if (arrayList.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList, new VolumeListAdapter$filterSideList$$inlined$sortBy$1(2));
        }
        boolean z3 = (this.panelShowList.size() == arrayList.size() && this.panelShowList.containsAll(arrayList)) ? false : true;
        if (!z3) {
            ArrayList<OplusVolumeRow> arrayList3 = this.panelShowList;
            int size3 = arrayList3.size();
            int i5 = 0;
            int i6 = 0;
            while (i6 < size3) {
                OplusVolumeRow oplusVolumeRow3 = arrayList3.get(i6);
                i6++;
                int i7 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                z3 = z3 || !Intrinsics.areEqual(arrayList.get(i5), oplusVolumeRow3);
                i5 = i7;
            }
        }
        this.panelShowList.clear();
        int size4 = arrayList.size();
        ArrayList<OplusVolumeRow> arrayList4 = this.panelShowList;
        if (size4 > 4) {
            arrayList4.addAll(arrayList.subList(0, 4));
        } else {
            arrayList4.addAll(arrayList);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", MobileInputLogger$.ExternalSyntheticOutline0.m("onRecycleVisibleChange panelShowList count = ", this.panelShowList.size(), ", ", "VolumeListAdapter-->", z3));
        }
        ArrayList arrayList5 = new ArrayList();
        arrayList5.addAll(this.panelShowList);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
        anonymousClass1.this$0 = this;
        anonymousClass1.$onComplete = arrayList5;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(anonymousClass1);
    }

    public final void updateShowingRow(OplusVolumeRow oplusVolumeRow, int i) {
        int iIndexOf = this.panelShowList.indexOf(oplusVolumeRow);
        if (iIndexOf >= 0) {
            notifyItemChanged(iIndexOf, Integer.valueOf(i));
        }
    }

    public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((VolumeViewHolder) viewHolder, i, (List<Object>) list);
    }

    public void onBindViewHolder(VolumeViewHolder volumeViewHolder, int i) {
        View view = ((RecyclerView.ViewHolder) volumeViewHolder).itemView;
        view.setAlpha(1.0f);
        if (!this.isMediaRefreshLayout || this.currentDataSource != 1) {
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }
        OplusVolumeRow oplusVolumeRow = (OplusVolumeRow) getItem(i);
        FrameLayout frameLayout = (FrameLayout) ((RecyclerView.ViewHolder) volumeViewHolder).itemView.findViewById(2131365996);
        View view2 = oplusVolumeRow.getView();
        attachRowViewIfNeeded(view2, frameLayout);
        int i2 = this.currentDataSource;
        if (1 == i2) {
            VolumeLayoutUtil.resetSideVolumeProgressRevealLayout(view2, frameLayout);
            bindPanelAccessibility(volumeViewHolder, oplusVolumeRow);
        } else if (i2 == 0) {
            VolumeLayoutUtil.prepareSideVolumeProgressOverflow(view2, frameLayout);
            bindSideAccessibility(volumeViewHolder);
        }
        oplusVolumeRow.setVolumeListBindDataSource(this.currentDataSource);
        if (this.currentDataSource == 1) {
            oplusVolumeRow.setVolumeListBoundTextColor(this.textColor);
        }
    }
}
