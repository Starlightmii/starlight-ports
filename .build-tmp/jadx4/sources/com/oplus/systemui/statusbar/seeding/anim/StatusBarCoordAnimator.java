package com.oplus.systemui.statusbar.seeding.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Property;
import android.view.View;
import com.coui.appcompat.animation.COUIMoveEaseInterpolator;
import com.oplus.util.OplusPlatformLevelUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarCoordAnimator {
    private static final Companion Companion = new Companion();
    private static final String TAG = "StatusBarCoordAnimator";
    private final Context context;
    private final List<View> hiddenIconList;
    private final IStatusBarViewProvider statusBar;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatusBarCoordAnimator(Context context, IStatusBarViewProvider iStatusBarViewProvider) {
        this.context = context;
        this.statusBar = iStatusBarViewProvider;
        OplusPlatformLevelUtils.getInstance(context).getPlatformAnimationLevel();
        this.hiddenIconList = new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Animator coordAnimator(Pair pair, List list) {
        list.size();
        ArrayList arrayList = new ArrayList();
        boolean zIsLayoutRtl = this.statusBar.getStatusBarContent().isLayoutRtl();
        IStatusBarViewProvider iStatusBarViewProvider = this.statusBar;
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(zIsLayoutRtl ? iStatusBarViewProvider.getEndAreaContent() : iStatusBarViewProvider.getStartAreaContent());
        IStatusBarViewProvider iStatusBarViewProvider2 = this.statusBar;
        List listListOf2 = CollectionsKt__CollectionsJVMKt.listOf(zIsLayoutRtl ? iStatusBarViewProvider2.getStartAreaContent() : iStatusBarViewProvider2.getEndAreaContent());
        float fMin = Math.min(1.0f * this.context.getResources().getDisplayMetrics().density * Math.max(listListOf.size(), listListOf2.size()), 12.0f);
        int size = listListOf.size();
        ArrayList arrayList2 = new ArrayList(size);
        int i = 0;
        while (i < size) {
            i++;
            arrayList2.add(Float.valueOf((i * fMin) / listListOf.size()));
        }
        int size2 = listListOf2.size();
        ArrayList arrayList3 = new ArrayList(size2);
        for (int i2 = 0; i2 < size2; i2++) {
            arrayList3.add(Float.valueOf(((listListOf2.size() - i2) * fMin) / listListOf2.size()));
        }
        int size3 = listListOf2.size() + listListOf.size();
        ArrayList arrayList4 = new ArrayList(size3);
        int i3 = 0;
        while (i3 < size3) {
            boolean z = i3 < listListOf.size();
            View view = (View) (z ? listListOf.get(i3) : listListOf2.get(i3 - listListOf.size()));
            float fFloatValue = ((Number) (z ? arrayList2.get(i3) : arrayList3.get(i3 - listListOf.size()))).floatValue();
            Direction direction = (Direction) (z ? pair.getFirst() : pair.getSecond());
            boolean z2 = view instanceof CoordAnimIcon;
            float transXForCoord = z2 ? ((CoordAnimIcon) view).getTransXForCoord() : view.getTranslationX();
            List list2 = list;
            ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            int i4 = 0;
            for (Object obj : list2) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                List list3 = listListOf2;
                List list4 = listListOf;
                long jLongValue = ((Number) obj).longValue();
                int i6 = size3;
                ArrayList arrayList6 = arrayList2;
                float translationX = (z2 ? 0.0f : view.getTranslationX()) + (i4 == list.size() + (-1) ? 0.0f : ((float) Math.pow(0.3333333432674408d, i4)) * direction.getValue() * (i4 % 2 == 0 ? 1 : -1) * fFloatValue);
                TimeInterpolator cOUIMoveEaseInterpolator = new COUIMoveEaseInterpolator();
                ObjectAnimator objectAnimatorOfFloat = z2 ? ObjectAnimator.ofFloat(view, CoordAnimIcon.Companion.getTRANS_X_FOR_COORD(), transXForCoord, translationX) : ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_X, transXForCoord, translationX);
                objectAnimatorOfFloat.setDuration(jLongValue);
                objectAnimatorOfFloat.setInterpolator(cOUIMoveEaseInterpolator);
                arrayList5.add(objectAnimatorOfFloat);
                transXForCoord = translationX;
                i4 = i5;
                listListOf = list4;
                listListOf2 = list3;
                arrayList2 = arrayList6;
                size3 = i6;
                arrayList3 = arrayList3;
                i3 = i3;
            }
            List list5 = listListOf2;
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(arrayList5);
            arrayList4.add(animatorSet);
            i3++;
            listListOf2 = list5;
        }
        arrayList.addAll(arrayList4);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(arrayList);
        return animatorSet2;
    }

    public final Animator createFromAnimationData(StatusBarAnimationData statusBarAnimationData) {
        int iOrdinal = statusBarAnimationData.getType().ordinal();
        if (iOrdinal == 0) {
            return shrinkIn(3.0f, statusBarAnimationData.getDurations()[0], statusBarAnimationData.getDurations()[1], Intervals.INSTANCE.getZERO());
        }
        if (iOrdinal == 1) {
            return expandOut(3.0f, statusBarAnimationData.getDurations()[0], statusBarAnimationData.getDurations()[1], Intervals.INSTANCE.getZERO());
        }
        if (iOrdinal == 2) {
            return shrinkInExpandOut(3.0f, statusBarAnimationData.getDurations()[0], statusBarAnimationData.getDurations()[1], statusBarAnimationData.getDurations()[2], Intervals.INSTANCE.getZERO());
        }
        if (iOrdinal == 3) {
            return moveLeft(3.0f, statusBarAnimationData.getDurations()[0], statusBarAnimationData.getDurations()[1], Intervals.INSTANCE.getZERO());
        }
        if (iOrdinal == 4) {
            return moveRight(3.0f, statusBarAnimationData.getDurations()[0], statusBarAnimationData.getDurations()[1], Intervals.INSTANCE.getZERO());
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final Animator expandOut(float f, long j, long j2, Interval interval) {
        return coordAnimator(new Pair(Direction.BACKWARD, Direction.FORWARD), CollectionsKt__CollectionsKt.listOf(new Long[]{Long.valueOf(j), Long.valueOf(j2)}));
    }

    public final Animator moveLeft(float f, long j, long j2, Interval interval) {
        List listListOf = CollectionsKt__CollectionsKt.listOf(new Long[]{Long.valueOf(j), Long.valueOf(j2)});
        Direction direction = Direction.BACKWARD;
        return coordAnimator(new Pair(direction, direction), listListOf);
    }

    public final Animator moveRight(float f, long j, long j2, Interval interval) {
        List listListOf = CollectionsKt__CollectionsKt.listOf(new Long[]{Long.valueOf(j), Long.valueOf(j2)});
        Direction direction = Direction.FORWARD;
        return coordAnimator(new Pair(direction, direction), listListOf);
    }

    public final Animator shrinkIn(float f, long j, long j2, Interval interval) {
        return coordAnimator(new Pair(Direction.FORWARD, Direction.BACKWARD), CollectionsKt__CollectionsKt.listOf(new Long[]{Long.valueOf(j), Long.valueOf(j2)}));
    }

    public final Animator shrinkInExpandOut(float f, long j, long j2, long j3, Interval interval) {
        return coordAnimator(new Pair(Direction.FORWARD, Direction.BACKWARD), CollectionsKt__CollectionsKt.listOf(new Long[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
    }
}
