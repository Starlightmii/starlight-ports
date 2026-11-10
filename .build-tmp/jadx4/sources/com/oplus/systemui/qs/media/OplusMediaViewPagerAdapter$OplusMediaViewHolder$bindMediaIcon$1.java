package com.oplus.systemui.qs.media;

import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageView;
import com.android.systemui.media.controls.shared.model.MediaData;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MediaData $data;
    Object L$0;
    int label;
    final /* synthetic */ OplusMediaViewPagerAdapter.OplusMediaViewHolder this$0;
    final /* synthetic */ OplusMediaViewPagerAdapter this$1;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Drawable $icon;
        int label;
        final /* synthetic */ OplusMediaViewPagerAdapter this$0;
        final /* synthetic */ OplusMediaViewPagerAdapter.OplusMediaViewHolder this$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter, OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder, Drawable drawable, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusMediaViewPagerAdapter;
            this.this$1 = oplusMediaViewHolder;
            this.$icon = drawable;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.this$1, this.$icon, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            if (!this.this$0.mIsDestroyed) {
                AppCompatImageView mAppIcon = this.this$1.getMAppIcon();
                if (mAppIcon != null) {
                    mAppIcon.setImageDrawable(this.$icon);
                }
                AppCompatImageView mAppIcon2 = this.this$1.getMAppIcon();
                if (mAppIcon2 != null) {
                    mAppIcon2.setVisibility(0);
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1(OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder, MediaData mediaData, OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusMediaViewHolder;
        this.$data = mediaData;
        this.this$1 = oplusMediaViewPagerAdapter;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1(this.this$0, this.$data, this.this$1, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0052, code lost:
    
        if (kotlinx.coroutines.BuildersKt.withContext(r1, r4, r7) == r0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L21
            if (r1 == r4) goto L1d
            if (r1 != r3) goto L17
            java.lang.Object r7 = r7.L$0
            android.graphics.drawable.Drawable r7 = (android.graphics.drawable.Drawable) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5a
        L17:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            return r2
        L1d:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L35
        L21:
            kotlin.ResultKt.throwOnFailure(r8)
            com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder r8 = r7.this$0
            com.android.systemui.media.controls.shared.model.MediaData r1 = r7.$data
            java.lang.String r1 = r1.getPackageName()
            r7.label = r4
            java.lang.Object r8 = com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter.OplusMediaViewHolder.access$getAppIcon(r8, r1, r7)
            if (r8 != r0) goto L35
            goto L54
        L35:
            android.graphics.drawable.Drawable r8 = (android.graphics.drawable.Drawable) r8
            if (r8 == 0) goto L55
            kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
            com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1$1 r4 = new com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1$1
            com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter r5 = r7.this$1
            com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder r6 = r7.this$0
            r4.<init>(r5, r6, r8, r2)
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r7.L$0 = r8
            r7.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r1, r4, r7)
            if (r7 != r0) goto L5a
        L54:
            return r0
        L55:
            com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder r7 = r7.this$0
            com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter.OplusMediaViewHolder.access$handleIconException(r7)
        L5a:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
