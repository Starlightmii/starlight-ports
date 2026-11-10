package com.oplus.systemui.plugins.qs.customize.view.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.addtile.AddTileCalculator;
import com.oplus.systemui.plugins.qs.customize.view.PreviewTileAdapter;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.SpanSizeLookUp;
import com.oplus.systemui.qs.base.widget.SpannedGridLayoutManager;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsAddTileViewManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QsAddTileViewManager";
    private ViewGroup addTilePanel;
    private ViewGroup contentView;
    private Context context;
    public boolean filterOutBigTile;
    public boolean isNeedItemAnimator;
    private List<? extends Object> lastData = CollectionsKt__CollectionsKt.emptyList();
    private final Lazy layoutManager$delegate;
    private Function1 onTileClickListener;
    private Context originalContext;
    private RecyclerView.ItemAnimator originalItemAnimator;
    public COUIRecyclerView recyclerView;
    private final Lazy tileAdapter$delegate;
    private final TileEditViewModel tileEditViewModel;

    public static Unit $r8$lambda$2DE1J78AgJAHZ3Dq4ehhTeLA768(QsAddTileViewManager qsAddTileViewManager, GridData gridData) {
        Function1 function1 = qsAddTileViewManager.onTileClickListener;
        if (function1 != null) {
            function1.invoke(gridData);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$J6TgN6Sg1cTD_9b-XXvrM76XgrM, reason: not valid java name */
    public static PreviewTileAdapter m168$r8$lambda$J6TgN6Sg1cTD_9bXXvrM76XgrM(QsAddTileViewManager qsAddTileViewManager) {
        Context context = qsAddTileViewManager.context;
        TileEditViewModel tileEditViewModel = qsAddTileViewManager.tileEditViewModel;
        QsAddTileViewManager$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new QsAddTileViewManager$.ExternalSyntheticLambda2();
        externalSyntheticLambda2.f$0 = qsAddTileViewManager;
        VarHandle.storeStoreFence();
        return new PreviewTileAdapter(context, tileEditViewModel, externalSyntheticLambda2);
    }

    public static SpannedGridLayoutManager $r8$lambda$JWPOEZ2NpRqgFFhHYQ_fgLsYjVA(QsAddTileViewManager qsAddTileViewManager) {
        int iMin = Math.min((int) (AddTileCalculator.INSTANCE.getScale() * CellCalculatorManager.Companion.lockDpi(qsAddTileViewManager.context, 2131171513)), qsAddTileViewManager.context.getResources().getDisplayMetrics().widthPixels / 4);
        return new SpannedGridLayoutManager(iMin, iMin);
    }

    public QsAddTileViewManager(Context context, TileEditViewModel tileEditViewModel, ViewGroup viewGroup) {
        this.originalContext = context;
        this.tileEditViewModel = tileEditViewModel;
        this.addTilePanel = viewGroup;
        this.context = context;
        this.contentView = viewGroup;
        QsAddTileViewManager$$ExternalSyntheticLambda0 qsAddTileViewManager$$ExternalSyntheticLambda0 = new QsAddTileViewManager$$ExternalSyntheticLambda0(0);
        qsAddTileViewManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.layoutManager$delegate = LazyKt__LazyJVMKt.lazy(qsAddTileViewManager$$ExternalSyntheticLambda0);
        QsAddTileViewManager$$ExternalSyntheticLambda0 qsAddTileViewManager$$ExternalSyntheticLambda1 = new QsAddTileViewManager$$ExternalSyntheticLambda0(1);
        qsAddTileViewManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.tileAdapter$delegate = LazyKt__LazyJVMKt.lazy(qsAddTileViewManager$$ExternalSyntheticLambda1);
        Context context2 = this.originalContext;
        this.context = QsColorUtil.createDarkColorContext(context2, !OplusDarkModeUtil.isNightMode(context2));
        this.contentView = this.addTilePanel;
        this.isNeedItemAnimator = true;
    }

    private final SpannedGridLayoutManager getLayoutManager() {
        return (SpannedGridLayoutManager) this.layoutManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PreviewTileAdapter getTileAdapter() {
        return (PreviewTileAdapter) this.tileAdapter$delegate.getValue();
    }

    public final List filterNotBigTile(List list) {
        String string = this.context.getResources().getString(2131955235);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean zEquals = false;
        for (Object obj : list) {
            if (obj instanceof String) {
                zEquals = obj.equals(string);
            }
            if (!zEquals && (!(obj instanceof GridData) || Intrinsics.areEqual(((GridData) obj).getSize(), SpanCache.INSTANCE.spanSize(1, 1)))) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj2 = arrayList.get(i2);
            i2++;
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if ((obj2 instanceof GridData) || (i3 < arrayList.size() && (arrayList.get(i3) instanceof GridData))) {
                arrayList2.add(obj2);
            }
            i = i3;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[filterNotBigTile] result is " + arrayList2, null, 8);
        return arrayList2;
    }

    public final boolean getFilterOutBigTile() {
        return this.filterOutBigTile;
    }

    public final Function1 getOnTileClickListener() {
        return this.onTileClickListener;
    }

    public final void onCreate() {
        Drawable drawable;
        ViewGroup viewGroup = this.contentView;
        Context context = this.context;
        if (ScrimUtil.isWallpaperBlurDisable()) {
            Resources resources = context.getResources();
            Resources.Theme theme = context.getTheme();
            ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
            drawable = resources.getDrawable(2131235816, theme);
        } else {
            Resources resources2 = context.getResources();
            Resources.Theme theme2 = context.getTheme();
            ThreadLocal threadLocal2 = ResourcesCompat.sTempTypedValue;
            drawable = resources2.getDrawable(2131235815, theme2);
        }
        if (!ScrimUtil.isLowGaussianLevel(context)) {
            BlurConfig blurConfig = new BlurConfig(0, 0, null, null, null, null, false, null, null, 0, null, 33554431);
            float dimensionPixelSize = context.getResources().getDimensionPixelSize(2131171508);
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, dimensionPixelSize);
            if (roundParams != null) {
                dimensionPixelSize = roundParams.getRadius();
            }
            float weight = roundParams != null ? roundParams.getWeight() : 3.0f;
            blurConfig.setBlurRadius(context.getResources().getInteger(2131427742));
            blurConfig.setLeftTopCornerRadius(dimensionPixelSize);
            blurConfig.setRightTopCornerRadius(dimensionPixelSize);
            blurConfig.setLeftBottomCornerRadius(context.getResources().getDimensionPixelSize(2131171507));
            blurConfig.setRightBottomCornerRadius(context.getResources().getDimensionPixelSize(2131171507));
            blurConfig.setEnableStaticBlurCorner(true);
            blurConfig.setRadiusWeight(Float.valueOf(weight));
            blurConfig.setPlatformMixConfig(QSBlurConfigProvider.getSepInactiveBlurConfig(context.getResources().getConfiguration().isNightModeActive(), SepQSResPool.isSupportStrokeCorner(), BaseQSResPool.getMirrorBlurScaleValue()));
            ViewBlurProxy viewBlurProxy = new ViewBlurProxy(viewGroup, blurConfig, null, 28);
            viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
            drawable = new AutoBlurDrawable(viewBlurProxy, drawable);
        }
        viewGroup.setBackground(drawable);
        COUIRecyclerView cOUIRecyclerViewRequireViewById = this.contentView.requireViewById(2131362016);
        this.recyclerView = cOUIRecyclerViewRequireViewById;
        if (cOUIRecyclerViewRequireViewById == null) {
            cOUIRecyclerViewRequireViewById = null;
        }
        cOUIRecyclerViewRequireViewById.setAdapter(getTileAdapter());
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        if (cOUIRecyclerView == null) {
            cOUIRecyclerView = null;
        }
        SpannedGridLayoutManager layoutManager = getLayoutManager();
        layoutManager.setSpanSizeLookUp(new SpanSizeLookUp() { // from class: com.oplus.systemui.plugins.qs.customize.view.dialog.QsAddTileViewManager$initView$1$1
            {
                super(null);
            }

            @Override // com.oplus.systemui.qs.base.widget.SpanSizeLookUp
            public final SpanSize getSpanSize(int i, int i2, int i3) {
                return this.this$0.getTileAdapter().getSpanSize(i, 4);
            }

            @Override // com.oplus.systemui.qs.base.widget.SpanSizeLookUp
            public final Integer getSpecialSizeForFullSpan(int i) {
                QsAddTileViewManager qsAddTileViewManager = this.this$0;
                return qsAddTileViewManager.getTileAdapter().getSpecialSize(i, qsAddTileViewManager.context);
            }
        });
        cOUIRecyclerView.setLayoutManager(layoutManager);
        if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            RecyclerView recyclerView = this.recyclerView;
            this.originalItemAnimator = (recyclerView != null ? recyclerView : null).getItemAnimator();
        }
    }

    public final void setFilterOutBigTile(boolean z) {
        this.filterOutBigTile = z;
    }

    public final void setNeedItemAnimator(boolean z) {
        if (this.isNeedItemAnimator == z || !OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isNeedItemAnimator : ", z), null, 8);
        this.isNeedItemAnimator = z;
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            recyclerView = null;
        }
        recyclerView.setItemAnimator(z ? this.originalItemAnimator : null);
    }

    public final void setOnTileClickListener(Function1 function1) {
        this.onTileClickListener = function1;
    }

    public final void updateBackGround() {
        if (this.contentView.getBackground() instanceof AutoBlurDrawable) {
            ((AutoBlurDrawable) this.contentView.getBackground()).getViewBlurProxy().applyBlurConfig();
        }
    }

    public final void updateTileAdapterData(boolean z) {
        if (this.lastData.isEmpty()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileAdapterData lastData=nul", null, 8);
            return;
        }
        PreviewTileAdapter tileAdapter = getTileAdapter();
        boolean z2 = this.filterOutBigTile;
        List<? extends Object> listFilterNotBigTile = this.lastData;
        if (z2) {
            listFilterNotBigTile = filterNotBigTile(listFilterNotBigTile);
        }
        tileAdapter.submitList(listFilterNotBigTile);
        if (z) {
            getLayoutManager().currentScroll = 0;
        }
    }

    public final void updateTileAdapterDataWhenFlow(List<? extends Object> list) {
        this.lastData = list;
        PreviewTileAdapter tileAdapter = getTileAdapter();
        if (this.filterOutBigTile) {
            list = filterNotBigTile(list);
        }
        tileAdapter.submitList(list);
    }
}
