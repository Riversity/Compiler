; ModuleID = 'builtin.c'
source_filename = "builtin.c"
target datalayout = "e-m:e-p:32:32-i64:64-n32-S128"
target triple = "riscv32"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: nounwind
define dso_local void @print(ptr noundef %0) local_unnamed_addr #0 {
  %2 = tail call i32 (ptr, ...) @printf(ptr noundef nonnull @.str, ptr noundef %0) #7
  ret void
}

declare dso_local i32 @printf(ptr noundef, ...) local_unnamed_addr #1

; Function Attrs: nounwind
define dso_local void @println(ptr noundef %0) local_unnamed_addr #0 {
  %2 = tail call i32 (ptr, ...) @printf(ptr noundef nonnull @.str.1, ptr noundef %0) #7
  ret void
}

; Function Attrs: nounwind
define dso_local void @printInt(i32 noundef %0) local_unnamed_addr #0 {
  %2 = tail call i32 (ptr, ...) @printf(ptr noundef nonnull @.str.2, i32 noundef %0) #7
  ret void
}

; Function Attrs: nounwind
define dso_local void @printlnInt(i32 noundef %0) local_unnamed_addr #0 {
  %2 = tail call i32 (ptr, ...) @printf(ptr noundef nonnull @.str.3, i32 noundef %0) #7
  ret void
}

; Function Attrs: nounwind
define dso_local ptr @getString() local_unnamed_addr #0 {
  %1 = tail call ptr @malloc(i32 noundef 256) #7
  %2 = tail call i32 (ptr, ...) @scanf(ptr noundef nonnull @.str, ptr noundef %1) #8
  ret ptr %1
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #2

declare dso_local ptr @malloc(i32 noundef) local_unnamed_addr #1

; Function Attrs: nofree nounwind
declare dso_local noundef i32 @scanf(ptr nocapture noundef readonly, ...) local_unnamed_addr #3

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #2

; Function Attrs: nofree nounwind
define dso_local i32 @getInt() local_unnamed_addr #3 {
  %1 = alloca i32, align 4
  call void @llvm.lifetime.start.p0(i64 4, ptr nonnull %1) #9
  %2 = call i32 (ptr, ...) @scanf(ptr noundef nonnull @.str.2, ptr noundef nonnull %1) #8
  %3 = load i32, ptr %1, align 4, !tbaa !4
  call void @llvm.lifetime.end.p0(i64 4, ptr nonnull %1) #9
  ret i32 %3
}

; Function Attrs: nounwind
define dso_local ptr @toString(i32 noundef %0) local_unnamed_addr #0 {
  %2 = tail call ptr @malloc(i32 noundef 12) #7
  %3 = tail call i32 (ptr, ptr, ...) @sprintf(ptr noundef nonnull dereferenceable(1) %2, ptr noundef nonnull dereferenceable(1) @.str.2, i32 noundef %0) #8
  ret ptr %2
}

; Function Attrs: nofree nounwind
declare dso_local noundef i32 @sprintf(ptr noalias nocapture noundef writeonly, ptr nocapture noundef readonly, ...) local_unnamed_addr #3

; Function Attrs: nounwind
define dso_local nonnull ptr @__alloc_single(i32 noundef %0, i32 noundef %1) local_unnamed_addr #0 {
  %3 = mul nsw i32 %1, %0
  %4 = add nsw i32 %3, 4
  %5 = tail call ptr @malloc(i32 noundef %4) #7
  store i32 %1, ptr %5, align 4, !tbaa !4
  %6 = getelementptr inbounds i32, ptr %5, i32 1
  ret ptr %6
}

; Function Attrs: nounwind
define dso_local nonnull ptr @__alloc_multi(i32 noundef %0, i32 noundef %1, ptr nocapture noundef readonly %2, i32 noundef %3) local_unnamed_addr #0 {
  %5 = icmp eq i32 %1, 1
  %6 = load i32, ptr %2, align 4, !tbaa !4
  br i1 %5, label %7, label %12

7:                                                ; preds = %4
  %8 = mul nsw i32 %6, %0
  %9 = add nsw i32 %8, 4
  %10 = tail call ptr @malloc(i32 noundef %9) #7
  store i32 %6, ptr %10, align 4, !tbaa !4
  %11 = getelementptr inbounds i32, ptr %10, i32 1
  br label %32

12:                                               ; preds = %4
  %13 = shl nsw i32 %6, 2
  %14 = add nsw i32 %13, 4
  %15 = tail call ptr @malloc(i32 noundef %14) #7
  store i32 %6, ptr %15, align 4, !tbaa !4
  %16 = getelementptr inbounds i32, ptr %15, i32 1
  %17 = icmp eq i32 %3, 1
  br i1 %17, label %32, label %18

18:                                               ; preds = %12
  %19 = load i32, ptr %2, align 4, !tbaa !4
  %20 = icmp sgt i32 %19, 0
  br i1 %20, label %21, label %32

21:                                               ; preds = %18
  %22 = add nsw i32 %1, -1
  %23 = getelementptr inbounds i32, ptr %2, i32 1
  %24 = add nsw i32 %3, -1
  br label %25

25:                                               ; preds = %21, %25
  %26 = phi i32 [ 0, %21 ], [ %29, %25 ]
  %27 = tail call ptr @__alloc_multi(i32 noundef %0, i32 noundef %22, ptr noundef nonnull %23, i32 noundef %24) #8
  %28 = getelementptr inbounds ptr, ptr %16, i32 %26
  store ptr %27, ptr %28, align 4, !tbaa !8
  %29 = add nuw nsw i32 %26, 1
  %30 = load i32, ptr %2, align 4, !tbaa !4
  %31 = icmp slt i32 %29, %30
  br i1 %31, label %25, label %32, !llvm.loop !10

32:                                               ; preds = %25, %18, %12, %7
  %33 = phi ptr [ %11, %7 ], [ %16, %12 ], [ %16, %18 ], [ %16, %25 ]
  ret ptr %33
}

; Function Attrs: nounwind
define dso_local nonnull ptr @__alloc(i32 noundef %0, i32 noundef %1, i32 noundef %2, ...) local_unnamed_addr #0 {
  %4 = alloca ptr, align 4
  %5 = shl i32 %2, 2
  %6 = tail call ptr @malloc(i32 noundef %5) #7
  call void @llvm.lifetime.start.p0(i64 4, ptr nonnull %4) #9
  call void @llvm.va_start(ptr nonnull %4)
  %7 = icmp sgt i32 %2, 0
  br i1 %7, label %10, label %8

8:                                                ; preds = %10, %3
  call void @llvm.va_end(ptr nonnull %4)
  %9 = call ptr @__alloc_multi(i32 noundef %0, i32 noundef %1, ptr noundef %6, i32 noundef %2) #8
  call void @llvm.lifetime.end.p0(i64 4, ptr nonnull %4) #9
  ret ptr %9

10:                                               ; preds = %3, %10
  %11 = phi i32 [ %16, %10 ], [ 0, %3 ]
  %12 = load ptr, ptr %4, align 4
  %13 = getelementptr inbounds i8, ptr %12, i32 4
  store ptr %13, ptr %4, align 4
  %14 = load i32, ptr %12, align 4
  %15 = getelementptr inbounds i32, ptr %6, i32 %11
  store i32 %14, ptr %15, align 4, !tbaa !4
  %16 = add nuw nsw i32 %11, 1
  %17 = icmp eq i32 %16, %2
  br i1 %17, label %8, label %10, !llvm.loop !12
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn
declare void @llvm.va_start(ptr) #4

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn
declare void @llvm.va_end(ptr) #4

; Function Attrs: mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read)
define dso_local i32 @__array.size(ptr nocapture noundef readonly %0) local_unnamed_addr #5 {
  %2 = getelementptr inbounds i32, ptr %0, i32 -1
  %3 = load i32, ptr %2, align 4, !tbaa !4
  ret i32 %3
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read)
define dso_local i32 @__string.length(ptr nocapture noundef readonly %0) local_unnamed_addr #6 {
  br label %2

2:                                                ; preds = %2, %1
  %3 = phi i32 [ 0, %1 ], [ %7, %2 ]
  %4 = getelementptr inbounds i8, ptr %0, i32 %3
  %5 = load i8, ptr %4, align 1, !tbaa !13
  %6 = icmp eq i8 %5, 0
  %7 = add nuw nsw i32 %3, 1
  br i1 %6, label %8, label %2, !llvm.loop !14

8:                                                ; preds = %2
  ret i32 %3
}

; Function Attrs: nounwind
define dso_local ptr @__string.substring(ptr nocapture noundef readonly %0, i32 noundef %1, i32 noundef %2) local_unnamed_addr #0 {
  %4 = sub nsw i32 %2, %1
  %5 = add nsw i32 %4, 1
  %6 = tail call ptr @malloc(i32 noundef %5) #7
  %7 = icmp sgt i32 %4, 0
  br i1 %7, label %10, label %8

8:                                                ; preds = %10, %3
  %9 = getelementptr inbounds i8, ptr %6, i32 %4
  store i8 0, ptr %9, align 1, !tbaa !13
  ret ptr %6

10:                                               ; preds = %3, %10
  %11 = phi i32 [ %16, %10 ], [ 0, %3 ]
  %12 = add nsw i32 %11, %1
  %13 = getelementptr inbounds i8, ptr %0, i32 %12
  %14 = load i8, ptr %13, align 1, !tbaa !13
  %15 = getelementptr inbounds i8, ptr %6, i32 %11
  store i8 %14, ptr %15, align 1, !tbaa !13
  %16 = add nuw nsw i32 %11, 1
  %17 = icmp eq i32 %16, %4
  br i1 %17, label %8, label %10, !llvm.loop !15
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read)
define dso_local i32 @__string.parseInt(ptr nocapture noundef readonly %0) local_unnamed_addr #6 {
  %2 = load i8, ptr %0, align 1, !tbaa !13
  %3 = icmp eq i8 %2, 45
  %4 = zext i1 %3 to i32
  %5 = getelementptr inbounds i8, ptr %0, i32 %4
  %6 = load i8, ptr %5, align 1, !tbaa !13
  %7 = icmp eq i8 %6, 0
  br i1 %7, label %8, label %12

8:                                                ; preds = %12, %1
  %9 = phi i32 [ 0, %1 ], [ %19, %12 ]
  %10 = sub nsw i32 0, %9
  %11 = select i1 %3, i32 %10, i32 %9
  ret i32 %11

12:                                               ; preds = %1, %12
  %13 = phi i8 [ %22, %12 ], [ %6, %1 ]
  %14 = phi i32 [ %20, %12 ], [ %4, %1 ]
  %15 = phi i32 [ %19, %12 ], [ 0, %1 ]
  %16 = zext i8 %13 to i32
  %17 = mul nsw i32 %15, 10
  %18 = add i32 %17, -48
  %19 = add i32 %18, %16
  %20 = add nuw nsw i32 %14, 1
  %21 = getelementptr inbounds i8, ptr %0, i32 %20
  %22 = load i8, ptr %21, align 1, !tbaa !13
  %23 = icmp eq i8 %22, 0
  br i1 %23, label %8, label %12, !llvm.loop !16
}

; Function Attrs: mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read)
define dso_local i32 @__string.ord(ptr nocapture noundef readonly %0, i32 noundef %1) local_unnamed_addr #5 {
  %3 = getelementptr inbounds i8, ptr %0, i32 %1
  %4 = load i8, ptr %3, align 1, !tbaa !13
  %5 = zext i8 %4 to i32
  ret i32 %5
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read)
define dso_local i32 @__string.compare(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #6 {
  %3 = load i8, ptr %0, align 1, !tbaa !13
  %4 = icmp eq i8 %3, 0
  br i1 %4, label %18, label %5

5:                                                ; preds = %2, %13
  %6 = phi i8 [ %16, %13 ], [ %3, %2 ]
  %7 = phi i32 [ %14, %13 ], [ 0, %2 ]
  %8 = getelementptr inbounds i8, ptr %1, i32 %7
  %9 = load i8, ptr %8, align 1, !tbaa !13
  %10 = icmp eq i8 %9, 0
  br i1 %10, label %18, label %11

11:                                               ; preds = %5
  %12 = icmp eq i8 %6, %9
  br i1 %12, label %13, label %23

13:                                               ; preds = %11
  %14 = add nuw nsw i32 %7, 1
  %15 = getelementptr inbounds i8, ptr %0, i32 %14
  %16 = load i8, ptr %15, align 1, !tbaa !13
  %17 = icmp eq i8 %16, 0
  br i1 %17, label %18, label %5, !llvm.loop !17

18:                                               ; preds = %5, %13, %2
  %19 = phi i32 [ 0, %2 ], [ %14, %13 ], [ %7, %5 ]
  %20 = phi i8 [ 0, %2 ], [ 0, %13 ], [ %6, %5 ]
  %21 = getelementptr inbounds i8, ptr %1, i32 %19
  %22 = load i8, ptr %21, align 1, !tbaa !13
  br label %23

23:                                               ; preds = %11, %18
  %24 = phi i8 [ %20, %18 ], [ %6, %11 ]
  %25 = phi i8 [ %22, %18 ], [ %9, %11 ]
  %26 = zext i8 %24 to i32
  %27 = zext i8 %25 to i32
  %28 = sub nsw i32 %26, %27
  ret i32 %28
}

; Function Attrs: nounwind
define dso_local ptr @__string.concat(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #0 {
  br label %3

3:                                                ; preds = %3, %2
  %4 = phi i32 [ 0, %2 ], [ %8, %3 ]
  %5 = getelementptr inbounds i8, ptr %0, i32 %4
  %6 = load i8, ptr %5, align 1, !tbaa !13
  %7 = icmp eq i8 %6, 0
  %8 = add nuw nsw i32 %4, 1
  br i1 %7, label %9, label %3, !llvm.loop !14

9:                                                ; preds = %3, %9
  %10 = phi i32 [ %14, %9 ], [ 0, %3 ]
  %11 = getelementptr inbounds i8, ptr %1, i32 %10
  %12 = load i8, ptr %11, align 1, !tbaa !13
  %13 = icmp eq i8 %12, 0
  %14 = add nuw nsw i32 %10, 1
  br i1 %13, label %15, label %9, !llvm.loop !14

15:                                               ; preds = %9
  %16 = add nuw nsw i32 %10, %4
  %17 = add nuw nsw i32 %16, 1
  %18 = tail call ptr @malloc(i32 noundef %17) #7
  %19 = icmp eq i32 %4, 0
  br i1 %19, label %20, label %22

20:                                               ; preds = %22, %15
  %21 = icmp eq i32 %10, 0
  br i1 %21, label %29, label %31

22:                                               ; preds = %15, %22
  %23 = phi i32 [ %27, %22 ], [ 0, %15 ]
  %24 = getelementptr inbounds i8, ptr %0, i32 %23
  %25 = load i8, ptr %24, align 1, !tbaa !13
  %26 = getelementptr inbounds i8, ptr %18, i32 %23
  store i8 %25, ptr %26, align 1, !tbaa !13
  %27 = add nuw nsw i32 %23, 1
  %28 = icmp eq i32 %27, %4
  br i1 %28, label %20, label %22, !llvm.loop !18

29:                                               ; preds = %31, %20
  %30 = getelementptr inbounds i8, ptr %18, i32 %16
  store i8 0, ptr %30, align 1, !tbaa !13
  ret ptr %18

31:                                               ; preds = %20, %31
  %32 = phi i32 [ %37, %31 ], [ 0, %20 ]
  %33 = getelementptr inbounds i8, ptr %1, i32 %32
  %34 = load i8, ptr %33, align 1, !tbaa !13
  %35 = add nuw nsw i32 %32, %4
  %36 = getelementptr inbounds i8, ptr %18, i32 %35
  store i8 %34, ptr %36, align 1, !tbaa !13
  %37 = add nuw nsw i32 %32, 1
  %38 = icmp eq i32 %37, %10
  br i1 %38, label %29, label %31, !llvm.loop !19
}

; Function Attrs: nounwind
define dso_local void @__string.copy(ptr nocapture noundef %0, ptr nocapture noundef readonly %1) local_unnamed_addr #0 {
  br label %3

3:                                                ; preds = %3, %2
  %4 = phi i32 [ 0, %2 ], [ %8, %3 ]
  %5 = getelementptr inbounds i8, ptr %1, i32 %4
  %6 = load i8, ptr %5, align 1, !tbaa !13
  %7 = icmp eq i8 %6, 0
  %8 = add nuw nsw i32 %4, 1
  br i1 %7, label %9, label %3, !llvm.loop !14

9:                                                ; preds = %3
  %10 = tail call ptr @malloc(i32 noundef %8) #7
  store ptr %10, ptr %0, align 4, !tbaa !8
  %11 = icmp eq i32 %4, 0
  br i1 %11, label %14, label %17

12:                                               ; preds = %17
  %13 = load ptr, ptr %0, align 4, !tbaa !8
  br label %14

14:                                               ; preds = %12, %9
  %15 = phi ptr [ %13, %12 ], [ %10, %9 ]
  %16 = getelementptr inbounds i8, ptr %15, i32 %4
  store i8 0, ptr %16, align 1, !tbaa !13
  ret void

17:                                               ; preds = %9, %17
  %18 = phi i32 [ %23, %17 ], [ 0, %9 ]
  %19 = getelementptr inbounds i8, ptr %1, i32 %18
  %20 = load i8, ptr %19, align 1, !tbaa !13
  %21 = load ptr, ptr %0, align 4, !tbaa !8
  %22 = getelementptr inbounds i8, ptr %21, i32 %18
  store i8 %20, ptr %22, align 1, !tbaa !13
  %23 = add nuw nsw i32 %18, 1
  %24 = icmp eq i32 %23, %4
  br i1 %24, label %12, label %17, !llvm.loop !20
}

attributes #0 = { nounwind "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="generic-rv32" "target-features"="+32bit,+a,+c,+m,+relax,-d,-e,-experimental-smaia,-experimental-ssaia,-experimental-zacas,-experimental-zfa,-experimental-zfbfmin,-experimental-zicond,-experimental-zihintntl,-experimental-ztso,-experimental-zvbb,-experimental-zvbc,-experimental-zvfbfmin,-experimental-zvfbfwma,-experimental-zvkg,-experimental-zvkn,-experimental-zvknc,-experimental-zvkned,-experimental-zvkng,-experimental-zvknha,-experimental-zvknhb,-experimental-zvks,-experimental-zvksc,-experimental-zvksed,-experimental-zvksg,-experimental-zvksh,-experimental-zvkt,-f,-h,-save-restore,-svinval,-svnapot,-svpbmt,-v,-xcvbitmanip,-xcvmac,-xsfcie,-xsfvcp,-xtheadba,-xtheadbb,-xtheadbs,-xtheadcmo,-xtheadcondmov,-xtheadfmemidx,-xtheadmac,-xtheadmemidx,-xtheadmempair,-xtheadsync,-xtheadvdot,-xventanacondops,-zawrs,-zba,-zbb,-zbc,-zbkb,-zbkc,-zbkx,-zbs,-zca,-zcb,-zcd,-zce,-zcf,-zcmp,-zcmt,-zdinx,-zfh,-zfhmin,-zfinx,-zhinx,-zhinxmin,-zicbom,-zicbop,-zicboz,-zicntr,-zicsr,-zifencei,-zihintpause,-zihpm,-zk,-zkn,-zknd,-zkne,-zknh,-zkr,-zks,-zksed,-zksh,-zkt,-zmmul,-zve32f,-zve32x,-zve64d,-zve64f,-zve64x,-zvfh,-zvl1024b,-zvl128b,-zvl16384b,-zvl2048b,-zvl256b,-zvl32768b,-zvl32b,-zvl4096b,-zvl512b,-zvl64b,-zvl65536b,-zvl8192b" }
attributes #1 = { "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="generic-rv32" "target-features"="+32bit,+a,+c,+m,+relax,-d,-e,-experimental-smaia,-experimental-ssaia,-experimental-zacas,-experimental-zfa,-experimental-zfbfmin,-experimental-zicond,-experimental-zihintntl,-experimental-ztso,-experimental-zvbb,-experimental-zvbc,-experimental-zvfbfmin,-experimental-zvfbfwma,-experimental-zvkg,-experimental-zvkn,-experimental-zvknc,-experimental-zvkned,-experimental-zvkng,-experimental-zvknha,-experimental-zvknhb,-experimental-zvks,-experimental-zvksc,-experimental-zvksed,-experimental-zvksg,-experimental-zvksh,-experimental-zvkt,-f,-h,-save-restore,-svinval,-svnapot,-svpbmt,-v,-xcvbitmanip,-xcvmac,-xsfcie,-xsfvcp,-xtheadba,-xtheadbb,-xtheadbs,-xtheadcmo,-xtheadcondmov,-xtheadfmemidx,-xtheadmac,-xtheadmemidx,-xtheadmempair,-xtheadsync,-xtheadvdot,-xventanacondops,-zawrs,-zba,-zbb,-zbc,-zbkb,-zbkc,-zbkx,-zbs,-zca,-zcb,-zcd,-zce,-zcf,-zcmp,-zcmt,-zdinx,-zfh,-zfhmin,-zfinx,-zhinx,-zhinxmin,-zicbom,-zicbop,-zicboz,-zicntr,-zicsr,-zifencei,-zihintpause,-zihpm,-zk,-zkn,-zknd,-zkne,-zknh,-zkr,-zks,-zksed,-zksh,-zkt,-zmmul,-zve32f,-zve32x,-zve64d,-zve64f,-zve64x,-zvfh,-zvl1024b,-zvl128b,-zvl16384b,-zvl2048b,-zvl256b,-zvl32768b,-zvl32b,-zvl4096b,-zvl512b,-zvl64b,-zvl65536b,-zvl8192b" }
attributes #2 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #3 = { nofree nounwind "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="generic-rv32" "target-features"="+32bit,+a,+c,+m,+relax,-d,-e,-experimental-smaia,-experimental-ssaia,-experimental-zacas,-experimental-zfa,-experimental-zfbfmin,-experimental-zicond,-experimental-zihintntl,-experimental-ztso,-experimental-zvbb,-experimental-zvbc,-experimental-zvfbfmin,-experimental-zvfbfwma,-experimental-zvkg,-experimental-zvkn,-experimental-zvknc,-experimental-zvkned,-experimental-zvkng,-experimental-zvknha,-experimental-zvknhb,-experimental-zvks,-experimental-zvksc,-experimental-zvksed,-experimental-zvksg,-experimental-zvksh,-experimental-zvkt,-f,-h,-save-restore,-svinval,-svnapot,-svpbmt,-v,-xcvbitmanip,-xcvmac,-xsfcie,-xsfvcp,-xtheadba,-xtheadbb,-xtheadbs,-xtheadcmo,-xtheadcondmov,-xtheadfmemidx,-xtheadmac,-xtheadmemidx,-xtheadmempair,-xtheadsync,-xtheadvdot,-xventanacondops,-zawrs,-zba,-zbb,-zbc,-zbkb,-zbkc,-zbkx,-zbs,-zca,-zcb,-zcd,-zce,-zcf,-zcmp,-zcmt,-zdinx,-zfh,-zfhmin,-zfinx,-zhinx,-zhinxmin,-zicbom,-zicbop,-zicboz,-zicntr,-zicsr,-zifencei,-zihintpause,-zihpm,-zk,-zkn,-zknd,-zkne,-zknh,-zkr,-zks,-zksed,-zksh,-zkt,-zmmul,-zve32f,-zve32x,-zve64d,-zve64f,-zve64x,-zvfh,-zvl1024b,-zvl128b,-zvl16384b,-zvl2048b,-zvl256b,-zvl32768b,-zvl32b,-zvl4096b,-zvl512b,-zvl64b,-zvl65536b,-zvl8192b" }
attributes #4 = { mustprogress nocallback nofree nosync nounwind willreturn }
attributes #5 = { mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read) "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="generic-rv32" "target-features"="+32bit,+a,+c,+m,+relax,-d,-e,-experimental-smaia,-experimental-ssaia,-experimental-zacas,-experimental-zfa,-experimental-zfbfmin,-experimental-zicond,-experimental-zihintntl,-experimental-ztso,-experimental-zvbb,-experimental-zvbc,-experimental-zvfbfmin,-experimental-zvfbfwma,-experimental-zvkg,-experimental-zvkn,-experimental-zvknc,-experimental-zvkned,-experimental-zvkng,-experimental-zvknha,-experimental-zvknhb,-experimental-zvks,-experimental-zvksc,-experimental-zvksed,-experimental-zvksg,-experimental-zvksh,-experimental-zvkt,-f,-h,-save-restore,-svinval,-svnapot,-svpbmt,-v,-xcvbitmanip,-xcvmac,-xsfcie,-xsfvcp,-xtheadba,-xtheadbb,-xtheadbs,-xtheadcmo,-xtheadcondmov,-xtheadfmemidx,-xtheadmac,-xtheadmemidx,-xtheadmempair,-xtheadsync,-xtheadvdot,-xventanacondops,-zawrs,-zba,-zbb,-zbc,-zbkb,-zbkc,-zbkx,-zbs,-zca,-zcb,-zcd,-zce,-zcf,-zcmp,-zcmt,-zdinx,-zfh,-zfhmin,-zfinx,-zhinx,-zhinxmin,-zicbom,-zicbop,-zicboz,-zicntr,-zicsr,-zifencei,-zihintpause,-zihpm,-zk,-zkn,-zknd,-zkne,-zknh,-zkr,-zks,-zksed,-zksh,-zkt,-zmmul,-zve32f,-zve32x,-zve64d,-zve64f,-zve64x,-zvfh,-zvl1024b,-zvl128b,-zvl16384b,-zvl2048b,-zvl256b,-zvl32768b,-zvl32b,-zvl4096b,-zvl512b,-zvl64b,-zvl65536b,-zvl8192b" }
attributes #6 = { nofree norecurse nosync nounwind memory(argmem: read) "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="generic-rv32" "target-features"="+32bit,+a,+c,+m,+relax,-d,-e,-experimental-smaia,-experimental-ssaia,-experimental-zacas,-experimental-zfa,-experimental-zfbfmin,-experimental-zicond,-experimental-zihintntl,-experimental-ztso,-experimental-zvbb,-experimental-zvbc,-experimental-zvfbfmin,-experimental-zvfbfwma,-experimental-zvkg,-experimental-zvkn,-experimental-zvknc,-experimental-zvkned,-experimental-zvkng,-experimental-zvknha,-experimental-zvknhb,-experimental-zvks,-experimental-zvksc,-experimental-zvksed,-experimental-zvksg,-experimental-zvksh,-experimental-zvkt,-f,-h,-save-restore,-svinval,-svnapot,-svpbmt,-v,-xcvbitmanip,-xcvmac,-xsfcie,-xsfvcp,-xtheadba,-xtheadbb,-xtheadbs,-xtheadcmo,-xtheadcondmov,-xtheadfmemidx,-xtheadmac,-xtheadmemidx,-xtheadmempair,-xtheadsync,-xtheadvdot,-xventanacondops,-zawrs,-zba,-zbb,-zbc,-zbkb,-zbkc,-zbkx,-zbs,-zca,-zcb,-zcd,-zce,-zcf,-zcmp,-zcmt,-zdinx,-zfh,-zfhmin,-zfinx,-zhinx,-zhinxmin,-zicbom,-zicbop,-zicboz,-zicntr,-zicsr,-zifencei,-zihintpause,-zihpm,-zk,-zkn,-zknd,-zkne,-zknh,-zkr,-zks,-zksed,-zksh,-zkt,-zmmul,-zve32f,-zve32x,-zve64d,-zve64f,-zve64x,-zvfh,-zvl1024b,-zvl128b,-zvl16384b,-zvl2048b,-zvl256b,-zvl32768b,-zvl32b,-zvl4096b,-zvl512b,-zvl64b,-zvl65536b,-zvl8192b" }
attributes #7 = { nobuiltin nounwind "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" }
attributes #8 = { "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" }
attributes #9 = { nounwind }

!llvm.module.flags = !{!0, !1, !2}
!llvm.ident = !{!3}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 1, !"target-abi", !"ilp32"}
!2 = !{i32 8, !"SmallDataLimit", i32 8}
!3 = !{!"Ubuntu clang version 17.0.6 (++20231208085846+6009708b4367-1~exp1~20231208085949.74)"}
!4 = !{!5, !5, i64 0}
!5 = !{!"int", !6, i64 0}
!6 = !{!"omnipotent char", !7, i64 0}
!7 = !{!"Simple C/C++ TBAA"}
!8 = !{!9, !9, i64 0}
!9 = !{!"any pointer", !6, i64 0}
!10 = distinct !{!10, !11}
!11 = !{!"llvm.loop.mustprogress"}
!12 = distinct !{!12, !11}
!13 = !{!6, !6, i64 0}
!14 = distinct !{!14, !11}
!15 = distinct !{!15, !11}
!16 = distinct !{!16, !11}
!17 = distinct !{!17, !11}
!18 = distinct !{!18, !11}
!19 = distinct !{!19, !11}
!20 = distinct !{!20, !11}
