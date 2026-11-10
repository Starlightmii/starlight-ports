package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.content.FileProvider$;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileRestoreInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileRestoreInteractor";
    private final CoroutineScope bgScope;
    private final ContextInstance contextInstance;
    private final TileRestoreInteractor$deviceSpaceShowObserver$1 deviceSpaceShowObserver;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    public SepUserTileConfigRepository mainUserTileConfigRepository;
    private final TileConfigRepoSwitcher repoSwitcher;
    private Job restoreJob;
    private final SepStaticTileConfigRepository staticTileConfigRepository;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTiles$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileRestoreInteractor.this.updateTiles(false, this);
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$deviceSpaceShowObserver$1] */
    public TileRestoreInteractor(ContextInstance contextInstance, SepStaticTileConfigRepository sepStaticTileConfigRepository, TileConfigRepoSwitcher tileConfigRepoSwitcher, CoroutineScope coroutineScope) {
        this.contextInstance = contextInstance;
        this.staticTileConfigRepository = sepStaticTileConfigRepository;
        this.repoSwitcher = tileConfigRepoSwitcher;
        this.bgScope = coroutineScope;
        final Handler handler = new Handler();
        this.deviceSpaceShowObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$deviceSpaceShowObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                TileRestoreInteractor.access$unRegisterDeviceSpaceShow(this.this$0);
                ContentResolver contentResolver = this.this$0.contextInstance.getSysUiContext().getContentResolver();
                String stringForUser = Settings.Secure.getStringForUser(contentResolver, SepRestoreTileConfig.SHOW_SEPARATE_DEVICE_ROOM_KEY, 0);
                LogUtil.i$default(LogUtil.TAG_QS, "TileRestoreInteractor", FileProvider$.ExternalSyntheticOutline0.m("showDeviceSpace ", stringForUser), null, 8);
                Settings.Secure.putStringForUser(contentResolver, SepRestoreTileConfig.SHOW_SEPARATE_DEVICE_ROOM_KEY, null, 0);
                TileRestoreInteractor tileRestoreInteractor = this.this$0;
                tileRestoreInteractor.restoreJob = CoroutineTracingKt.launchTraced$default(tileRestoreInteractor.bgScope, "TileRestoreInteractor-add-restore", (CoroutineContext) null, (CoroutineStart) null, new onChange.1(this.this$0, stringForUser, (Continuation) null), 6);
            }
        };
    }

    public static final void access$registerDeviceSpaceShow(TileRestoreInteractor tileRestoreInteractor) {
        ContentResolver contentResolver;
        tileRestoreInteractor.getClass();
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportMyDevice() && (contentResolver = tileRestoreInteractor.contextInstance.getSysUiContext().getContentResolver()) != null) {
            contentResolver.registerContentObserver(Settings.Secure.getUriFor(SepRestoreTileConfig.SHOW_SEPARATE_DEVICE_ROOM_KEY), false, tileRestoreInteractor.deviceSpaceShowObserver, 0);
        }
    }

    public static final void access$unRegisterDeviceSpaceShow(TileRestoreInteractor tileRestoreInteractor) {
        ContentResolver contentResolver;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportMyDevice() && (contentResolver = tileRestoreInteractor.contextInstance.getSysUiContext().getContentResolver()) != null) {
            contentResolver.unregisterContentObserver(tileRestoreInteractor.deviceSpaceShowObserver);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0082, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.collectLatest(r5, r6, r0) == r1) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:
    
        if (r7.updateTiles(r8, r0) == r1) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$updateTilesOnlyMainUser(com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor r7, boolean r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r7.getClass()
            boolean r0 = r9 instanceof com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTilesOnlyMainUser$1
            if (r0 == 0) goto L16
            r0 = r9
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTilesOnlyMainUser$1 r0 = (com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTilesOnlyMainUser$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTilesOnlyMainUser$1 r0 = new com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTilesOnlyMainUser$1
            r0.<init>(r7, r9)
        L1b:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L3a
            if (r2 == r5) goto L36
            if (r2 != r3) goto L30
            kotlin.ResultKt.throwOnFailure(r9)
            goto L85
        L30:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            return r4
        L36:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L97
        L3a:
            kotlin.ResultKt.throwOnFailure(r9)
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher r9 = r7.repoSwitcher
            kotlinx.coroutines.flow.StateFlow r9 = r9.getUserId()
            java.lang.Object r9 = r9.getValue()
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            r2 = 0
            if (r9 != 0) goto L52
            r9 = r5
            goto L53
        L52:
            r9 = r2
        L53:
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher r6 = r7.repoSwitcher
            kotlinx.coroutines.flow.StateFlow r6 = r6.getScene()
            java.lang.Object r6 = r6.getValue()
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            if (r6 != r5) goto L66
            r2 = r5
        L66:
            if (r9 == 0) goto L88
            if (r2 == 0) goto L6b
            goto L88
        L6b:
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher r5 = r7.repoSwitcher
            kotlinx.coroutines.flow.StateFlow r5 = r5.getScene()
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTilesOnlyMainUser$2 r6 = new com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor$updateTilesOnlyMainUser$2
            r6.<init>(r7, r8, r4)
            r0.Z$0 = r8
            r0.I$0 = r9
            r0.I$1 = r2
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.collectLatest(r5, r6, r0)
            if (r7 != r1) goto L85
            goto L96
        L85:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L88:
            r0.Z$0 = r8
            r0.I$0 = r9
            r0.I$1 = r2
            r0.label = r5
            java.lang.Object r8 = r7.updateTiles(r8, r0)
            if (r8 != r1) goto L97
        L96:
            return r1
        L97:
            kotlinx.coroutines.Job r7 = r7.restoreJob
            if (r7 == 0) goto La0
            kotlinx.coroutines.Job$Key r8 = kotlinx.coroutines.Job.Key
            r7.cancel(r4)
        La0:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor.access$updateTilesOnlyMainUser(com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void destroy() {
        if (!this.initialized.compareAndSet(true, false)) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "destroy already!");
            return;
        }
        Job job = this.restoreJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
    }

    public final void restore() {
        this.restoreJob = CoroutineTracingKt.launchTraced$default(this.bgScope, "TileRestoreInteractor-launch-restore", (CoroutineContext) null, (CoroutineStart) null, new restore.1(this, (Continuation) null), 6);
    }

    public final void start(SepTileConfigRepository sepTileConfigRepository) {
        if (this.initialized.compareAndSet(false, true)) {
            this.mainUserTileConfigRepository = sepTileConfigRepository.ensureUserTileConfigRepository(0);
        } else {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "start already!");
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0190 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:104:0x018b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0140  */
    /* JADX WARN: Code duplicated, block: B:34:0x0150  */
    /* JADX WARN: Code duplicated, block: B:38:0x017b  */
    /* JADX WARN: Code duplicated, block: B:41:0x018d A[LOOP:0: B:36:0x0172->B:41:0x018d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:44:0x0193  */
    /* JADX WARN: Code duplicated, block: B:46:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:48:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:50:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:52:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:55:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:57:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:61:0x0232  */
    /* JADX WARN: Code duplicated, block: B:63:0x0241  */
    /* JADX WARN: Code duplicated, block: B:66:0x0248  */
    /* JADX WARN: Code duplicated, block: B:67:0x024a  */
    /* JADX WARN: Code duplicated, block: B:73:0x028d  */
    /* JADX WARN: Code duplicated, block: B:76:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:79:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:81:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:83:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:87:0x031e  */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    /* JADX WARN: Code duplicated, block: B:90:0x0327  */
    /* JADX WARN: Code duplicated, block: B:94:0x035c A[PHI: r1 r3 r5 r6 r7 r9 r11 r12 r13 r14
      0x035c: PHI (r1v35 java.lang.Object) = (r1v33 java.lang.Object), (r1v1 java.lang.Object) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r3v30 int) = (r3v28 int), (r3v32 int) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r5v13 boolean) = (r5v11 boolean), (r5v15 boolean) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r6v39 com.oplusos.systemui.common.model.Loc) = (r6v36 com.oplusos.systemui.common.model.Loc), (r6v41 com.oplusos.systemui.common.model.Loc) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r7v19 com.oplusos.systemui.common.model.SpanSize) = (r7v16 com.oplusos.systemui.common.model.SpanSize), (r7v24 com.oplusos.systemui.common.model.SpanSize) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r9v7 com.oplusos.systemui.common.model.Loc) = (r9v4 com.oplusos.systemui.common.model.Loc), (r9v10 com.oplusos.systemui.common.model.Loc) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r11v14 com.oplus.systemui.plugins.qs.customize.data.model.TileConfig) = 
      (r11v11 com.oplus.systemui.plugins.qs.customize.data.model.TileConfig)
      (r11v17 com.oplus.systemui.plugins.qs.customize.data.model.TileConfig)
     binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r12v11 java.util.List) = (r12v8 java.util.List), (r12v14 java.util.List) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r13v11 com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity) = 
      (r13v8 com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity)
      (r13v14 com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity)
     binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]
      0x035c: PHI (r14v13 java.util.List) = (r14v10 java.util.List), (r14v16 java.util.List) binds: [B:92:0x0359, B:14:0x0059] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:96:0x0374  */
    /* JADX WARN: Code duplicated, block: B:97:0x0376  */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0286, code lost:
    
        if (r10.deform(r1, r0, true, r8) == r2) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x03bd, code lost:
    
        if (r10.moveTile(r0, -1, r6, true, r8) == r2) goto L100;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateTiles(boolean r21, kotlin.coroutines.jvm.internal.ContinuationImpl r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 986
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor.updateTiles(boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
