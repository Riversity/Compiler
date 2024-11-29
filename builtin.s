	.text
	.attribute	4, 16
	.attribute	5, "rv32i2p1_m2p0_a2p1_c2p0"
	.file	"builtin.c"
	.globl	print                           # -- Begin function print
	.p2align	1
	.type	print,@function
print:                                  # @print
# %bb.0:
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end0:
	.size	print, .Lfunc_end0-print
                                        # -- End function
	.globl	println                         # -- Begin function println
	.p2align	1
	.type	println,@function
println:                                # @println
# %bb.0:
	lui	a1, %hi(.L.str.1)
	addi	a1, a1, %lo(.L.str.1)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end1:
	.size	println, .Lfunc_end1-println
                                        # -- End function
	.globl	printInt                        # -- Begin function printInt
	.p2align	1
	.type	printInt,@function
printInt:                               # @printInt
# %bb.0:
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt
                                        # -- End function
	.globl	printlnInt                      # -- Begin function printlnInt
	.p2align	1
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
# %bb.0:
	lui	a1, %hi(.L.str.3)
	addi	a1, a1, %lo(.L.str.3)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt
                                        # -- End function
	.globl	getString                       # -- Begin function getString
	.p2align	1
	.type	getString,@function
getString:                              # @getString
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	li	a0, 256
	call	malloc
	mv	s0, a0
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	mv	a1, s0
	call	scanf
	mv	a0, s0
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString
                                        # -- End function
	.globl	getInt                          # -- Begin function getInt
	.p2align	1
	.type	getInt,@function
getInt:                                 # @getInt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt
                                        # -- End function
	.globl	toString                        # -- Begin function toString
	.p2align	1
	.type	toString,@function
toString:                               # @toString
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	sw	s1, 4(sp)                       # 4-byte Folded Spill
	mv	s0, a0
	li	a0, 12
	call	malloc
	mv	s1, a0
	lui	a0, %hi(.L.str.2)
	addi	a1, a0, %lo(.L.str.2)
	mv	a0, s1
	mv	a2, s0
	call	sprintf
	mv	a0, s1
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	lw	s1, 4(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString
                                        # -- End function
	.globl	__alloc_single                  # -- Begin function __alloc_single
	.p2align	1
	.type	__alloc_single,@function
__alloc_single:                         # @__alloc_single
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	mv	s0, a1
	mul	a0, a1, a0
	addi	a0, a0, 4
	call	malloc
	addi	a1, a0, 4
	sw	s0, 0(a0)
	mv	a0, a1
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	__alloc_single, .Lfunc_end7-__alloc_single
                                        # -- End function
	.globl	__alloc_multi                   # -- Begin function __alloc_multi
	.p2align	1
	.type	__alloc_multi,@function
__alloc_multi:                          # @__alloc_multi
# %bb.0:
	addi	sp, sp, -48
	sw	ra, 44(sp)                      # 4-byte Folded Spill
	sw	s0, 40(sp)                      # 4-byte Folded Spill
	sw	s1, 36(sp)                      # 4-byte Folded Spill
	sw	s2, 32(sp)                      # 4-byte Folded Spill
	sw	s3, 28(sp)                      # 4-byte Folded Spill
	sw	s4, 24(sp)                      # 4-byte Folded Spill
	sw	s5, 20(sp)                      # 4-byte Folded Spill
	sw	s6, 16(sp)                      # 4-byte Folded Spill
	sw	s7, 12(sp)                      # 4-byte Folded Spill
	lw	s0, 0(a2)
	li	s1, 1
	mv	s5, a0
	bne	a1, s1, .LBB8_2
# %bb.1:
	mul	a0, s0, s5
	addi	a0, a0, 4
	call	malloc
	sw	s0, 0(a0)
	addi	s2, a0, 4
	j	.LBB8_6
.LBB8_2:
	mv	s6, a2
	mv	s3, a3
	mv	s4, a1
	slli	a0, s0, 2
	addi	a0, a0, 4
	call	malloc
	sw	s0, 0(a0)
	addi	s2, a0, 4
	beq	s3, s1, .LBB8_6
# %bb.3:
	lw	a0, 0(s6)
	blez	a0, .LBB8_6
# %bb.4:
	li	s1, 0
	addi	s4, s4, -1
	addi	s7, s6, 4
	addi	s3, s3, -1
	mv	s0, s2
.LBB8_5:                                # =>This Inner Loop Header: Depth=1
	mv	a0, s5
	mv	a1, s4
	mv	a2, s7
	mv	a3, s3
	call	__alloc_multi
	sw	a0, 0(s0)
	lw	a0, 0(s6)
	addi	s1, s1, 1
	addi	s0, s0, 4
	blt	s1, a0, .LBB8_5
.LBB8_6:                                # %.loopexit
	mv	a0, s2
	lw	ra, 44(sp)                      # 4-byte Folded Reload
	lw	s0, 40(sp)                      # 4-byte Folded Reload
	lw	s1, 36(sp)                      # 4-byte Folded Reload
	lw	s2, 32(sp)                      # 4-byte Folded Reload
	lw	s3, 28(sp)                      # 4-byte Folded Reload
	lw	s4, 24(sp)                      # 4-byte Folded Reload
	lw	s5, 20(sp)                      # 4-byte Folded Reload
	lw	s6, 16(sp)                      # 4-byte Folded Reload
	lw	s7, 12(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 48
	ret
.Lfunc_end8:
	.size	__alloc_multi, .Lfunc_end8-__alloc_multi
                                        # -- End function
	.globl	__alloc                         # -- Begin function __alloc
	.p2align	1
	.type	__alloc,@function
__alloc:                                # @__alloc
# %bb.0:
	addi	sp, sp, -48
	sw	ra, 20(sp)                      # 4-byte Folded Spill
	sw	s0, 16(sp)                      # 4-byte Folded Spill
	sw	s1, 12(sp)                      # 4-byte Folded Spill
	sw	s2, 8(sp)                       # 4-byte Folded Spill
	mv	s0, a2
	mv	s2, a1
	mv	s1, a0
	sw	a7, 44(sp)
	sw	a6, 40(sp)
	sw	a5, 36(sp)
	sw	a4, 32(sp)
	sw	a3, 28(sp)
	slli	a0, a2, 2
	call	malloc
	mv	a2, a0
	addi	a0, sp, 28
	sw	a0, 4(sp)
	blez	s0, .LBB9_3
# %bb.1:
	mv	a0, a2
	mv	a1, s0
.LBB9_2:                                # %.preheader
                                        # =>This Inner Loop Header: Depth=1
	lw	a3, 4(sp)
	addi	a4, a3, 4
	sw	a4, 4(sp)
	lw	a3, 0(a3)
	sw	a3, 0(a0)
	addi	a1, a1, -1
	addi	a0, a0, 4
	bnez	a1, .LBB9_2
.LBB9_3:                                # %.loopexit
	mv	a0, s1
	mv	a1, s2
	mv	a3, s0
	call	__alloc_multi
	lw	ra, 20(sp)                      # 4-byte Folded Reload
	lw	s0, 16(sp)                      # 4-byte Folded Reload
	lw	s1, 12(sp)                      # 4-byte Folded Reload
	lw	s2, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 48
	ret
.Lfunc_end9:
	.size	__alloc, .Lfunc_end9-__alloc
                                        # -- End function
	.globl	__array.size                    # -- Begin function __array.size
	.p2align	1
	.type	__array.size,@function
__array.size:                           # @__array.size
# %bb.0:
	lw	a0, -4(a0)
	ret
.Lfunc_end10:
	.size	__array.size, .Lfunc_end10-__array.size
                                        # -- End function
	.globl	__string.length                 # -- Begin function __string.length
	.p2align	1
	.type	__string.length,@function
__string.length:                        # @__string.length
# %bb.0:
	li	a1, 0
.LBB11_1:                               # =>This Inner Loop Header: Depth=1
	add	a2, a0, a1
	lbu	a2, 0(a2)
	addi	a1, a1, 1
	bnez	a2, .LBB11_1
# %bb.2:
	addi	a0, a1, -1
	ret
.Lfunc_end11:
	.size	__string.length, .Lfunc_end11-__string.length
                                        # -- End function
	.globl	__string.substring              # -- Begin function __string.substring
	.p2align	1
	.type	__string.substring,@function
__string.substring:                     # @__string.substring
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	sw	s1, 4(sp)                       # 4-byte Folded Spill
	sw	s2, 0(sp)                       # 4-byte Folded Spill
	mv	s0, a1
	mv	s2, a0
	sub	s1, a2, a1
	addi	a0, s1, 1
	call	malloc
	blez	s1, .LBB12_3
# %bb.1:                                # %.preheader.preheader
	add	s0, s0, s2
	mv	a1, a0
	mv	a2, s1
.LBB12_2:                               # %.preheader
                                        # =>This Inner Loop Header: Depth=1
	lbu	a3, 0(s0)
	sb	a3, 0(a1)
	addi	a2, a2, -1
	addi	a1, a1, 1
	addi	s0, s0, 1
	bnez	a2, .LBB12_2
.LBB12_3:                               # %.loopexit
	add	s1, s1, a0
	sb	zero, 0(s1)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	lw	s1, 4(sp)                       # 4-byte Folded Reload
	lw	s2, 0(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	__string.substring, .Lfunc_end12-__string.substring
                                        # -- End function
	.globl	__string.parseInt               # -- Begin function __string.parseInt
	.p2align	1
	.type	__string.parseInt,@function
__string.parseInt:                      # @__string.parseInt
# %bb.0:
	lbu	a1, 0(a0)
	addi	a2, a1, -45
	seqz	a3, a2
	add	a3, a3, a0
	lbu	a2, 0(a3)
	beqz	a2, .LBB13_6
# %bb.1:                                # %.preheader.preheader
	li	a0, 0
	addi	a3, a3, 1
	li	a4, 10
.LBB13_2:                               # %.preheader
                                        # =>This Inner Loop Header: Depth=1
	andi	a5, a2, 255
	lbu	a2, 0(a3)
	mul	a0, a0, a4
	add	a0, a0, a5
	addi	a0, a0, -48
	addi	a3, a3, 1
	bnez	a2, .LBB13_2
# %bb.3:                                # %.loopexit
	li	a2, 45
	bne	a1, a2, .LBB13_5
.LBB13_4:
	neg	a0, a0
.LBB13_5:                               # %.loopexit
	ret
.LBB13_6:
	li	a0, 0
	li	a2, 45
	beq	a1, a2, .LBB13_4
	j	.LBB13_5
.Lfunc_end13:
	.size	__string.parseInt, .Lfunc_end13-__string.parseInt
                                        # -- End function
	.globl	__string.ord                    # -- Begin function __string.ord
	.p2align	1
	.type	__string.ord,@function
__string.ord:                           # @__string.ord
# %bb.0:
	add	a0, a0, a1
	lbu	a0, 0(a0)
	ret
.Lfunc_end14:
	.size	__string.ord, .Lfunc_end14-__string.ord
                                        # -- End function
	.globl	__string.compare                # -- Begin function __string.compare
	.p2align	1
	.type	__string.compare,@function
__string.compare:                       # @__string.compare
# %bb.0:
	lbu	a2, 0(a0)
	beqz	a2, .LBB15_5
# %bb.1:                                # %.preheader.preheader
	li	a3, 0
	addi	a0, a0, 1
.LBB15_2:                               # %.preheader
                                        # =>This Inner Loop Header: Depth=1
	add	a4, a1, a3
	lbu	a4, 0(a4)
	beqz	a4, .LBB15_6
# %bb.3:                                #   in Loop: Header=BB15_2 Depth=1
	andi	a5, a2, 255
	bne	a5, a4, .LBB15_8
# %bb.4:                                #   in Loop: Header=BB15_2 Depth=1
	add	a2, a0, a3
	lbu	a2, 0(a2)
	addi	a4, a3, 1
	mv	a3, a4
	bnez	a2, .LBB15_2
	j	.LBB15_7
.LBB15_5:
	li	a4, 0
	j	.LBB15_7
.LBB15_6:
	mv	a4, a3
.LBB15_7:                               # %.loopexit
	add	a1, a1, a4
	lbu	a4, 0(a1)
.LBB15_8:                               # %.loopexit5
	andi	a0, a2, 255
	sub	a0, a0, a4
	ret
.Lfunc_end15:
	.size	__string.compare, .Lfunc_end15-__string.compare
                                        # -- End function
	.globl	__string.concat                 # -- Begin function __string.concat
	.p2align	1
	.type	__string.concat,@function
__string.concat:                        # @__string.concat
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	sw	s1, 20(sp)                      # 4-byte Folded Spill
	sw	s2, 16(sp)                      # 4-byte Folded Spill
	sw	s3, 12(sp)                      # 4-byte Folded Spill
	sw	s4, 8(sp)                       # 4-byte Folded Spill
	mv	s2, a1
	mv	s4, a0
	li	a0, 0
	li	a1, -1
.LBB16_1:                               # =>This Inner Loop Header: Depth=1
	mv	s3, a0
	add	a0, a0, s4
	lbu	a2, 0(a0)
	mv	s0, a1
	addi	a0, s3, 1
	addi	a1, a1, 1
	bnez	a2, .LBB16_1
# %bb.2:                                # %.preheader3.preheader
	li	s1, 1
	mv	a1, s2
.LBB16_3:                               # %.preheader3
                                        # =>This Inner Loop Header: Depth=1
	lbu	a2, 0(a1)
	addi	s1, s1, -1
	addi	s0, s0, 1
	addi	a1, a1, 1
	bnez	a2, .LBB16_3
# %bb.4:
	sub	a0, a0, s1
	call	malloc
	beqz	s3, .LBB16_7
# %bb.5:
	mv	a1, a0
	mv	a2, s3
.LBB16_6:                               # %.preheader1
                                        # =>This Inner Loop Header: Depth=1
	lbu	a3, 0(s4)
	sb	a3, 0(a1)
	addi	a2, a2, -1
	addi	a1, a1, 1
	addi	s4, s4, 1
	bnez	a2, .LBB16_6
.LBB16_7:                               # %.loopexit2
	beqz	s1, .LBB16_10
# %bb.8:                                # %.preheader.preheader
	neg	a1, s1
	add	s3, s3, a0
.LBB16_9:                               # %.preheader
                                        # =>This Inner Loop Header: Depth=1
	lbu	a2, 0(s2)
	sb	a2, 0(s3)
	addi	a1, a1, -1
	addi	s3, s3, 1
	addi	s2, s2, 1
	bnez	a1, .LBB16_9
.LBB16_10:                              # %.loopexit
	add	s0, s0, a0
	sb	zero, 0(s0)
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	lw	s1, 20(sp)                      # 4-byte Folded Reload
	lw	s2, 16(sp)                      # 4-byte Folded Reload
	lw	s3, 12(sp)                      # 4-byte Folded Reload
	lw	s4, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end16:
	.size	__string.concat, .Lfunc_end16-__string.concat
                                        # -- End function
	.globl	__string.copy                   # -- Begin function __string.copy
	.p2align	1
	.type	__string.copy,@function
__string.copy:                          # @__string.copy
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	sw	s1, 4(sp)                       # 4-byte Folded Spill
	sw	s2, 0(sp)                       # 4-byte Folded Spill
	mv	s1, a1
	mv	s2, a0
	li	s0, -1
.LBB17_1:                               # =>This Inner Loop Header: Depth=1
	add	a0, s1, s0
	lbu	a0, 1(a0)
	addi	s0, s0, 1
	bnez	a0, .LBB17_1
# %bb.2:
	addi	a0, s0, 1
	call	malloc
	sw	a0, 0(s2)
	beqz	s0, .LBB17_6
# %bb.3:                                # %.preheader.preheader
	li	a0, 0
.LBB17_4:                               # %.preheader
                                        # =>This Inner Loop Header: Depth=1
	lw	a1, 0(s2)
	add	a2, s1, a0
	lbu	a2, 0(a2)
	add	a1, a1, a0
	addi	a0, a0, 1
	sb	a2, 0(a1)
	bne	s0, a0, .LBB17_4
# %bb.5:
	lw	a0, 0(s2)
.LBB17_6:
	add	a0, a0, s0
	sb	zero, 0(a0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	lw	s1, 4(sp)                       # 4-byte Folded Reload
	lw	s2, 0(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end17:
	.size	__string.copy, .Lfunc_end17-__string.copy
                                        # -- End function
	.type	.L.str,@object                  # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object                # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object                # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"Ubuntu clang version 17.0.6 (++20231208085846+6009708b4367-1~exp1~20231208085949.74)"
	.section	".note.GNU-stack","",@progbits
	.addrsig
