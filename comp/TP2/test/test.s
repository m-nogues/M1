.rdata
.align 2
$LC0:
       .ascii	"%d\000"
       .align 2
L12:
       .ascii	"Test sample\000"
       .align 2
L13:
       .ascii	"Second test sample\000"
.text
# label test
# beginfunc 
	.align	2
	.globl test
	.ent test
test:
	 addiu	$sp,$sp,-24
	 sw	$31,16($29)
# T_0 = tab [ 0 ]
       li   $8, 0
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       lw  $8,0($8)
# arg T_0
       sw  $8,0($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# return 1
       li   $2, 1
	 lw	$31,16($29)
	 addiu	$sp,$sp,24
	 jr  $31
	 nop
# endfunc 
	 lw	$31,16($29)
	 addiu	$sp,$sp,24
	 jr  $31
	 nop
	.end test
# label test2
# beginfunc 
	.align	2
	.globl test2
	.ent test2
test2:
	 addiu	$sp,$sp,-24
	 sw	$31,16($29)
# arg a
       lw  $8,0($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# endfunc 
	 lw	$31,16($29)
	 addiu	$sp,$sp,24
	 jr  $31
	 nop
	.end test2
# label main
# beginfunc 
	.align	2
	.globl main
	.ent main
main:
	 addiu	$sp,$sp,-88
	 sw	$31,16($29)
	 sw	$16,24($29)
	 sw	$17,28($29)
	 sw	$18,32($29)
	 sw	$19,36($29)
	 sw	$20,40($29)
	 sw	$21,44($29)
	 sw	$22,48($29)
	 sw	$23,52($29)
# var x
# var y
# var z
# var a
# y = 0
       li   $8, 0
# var T_1
# T_1 = 1 + y
       li   $10, 1
       lw  $9,60($29)
	add $9, $10, $9
# x = T_1
       sw  $9,76($29)
# ifz x goto L10
       sw  $8,0($29)
       sw  $9,0($29)
       lw  $11,56($29)
	beq  $11,0,L10
# x = 5
       li   $12, 5
# goto L11
       sw  $12,0($29)
	j  L11
# label L10
L10:
# x = 6
       li   $8, 6
# label L11
       sw  $8,0($29)
L11:
# a [ 0 ] = 1
       li   $8, 0
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       li   $9, 1
       sw  $9,0($8)
# var T_2
# arg a
	 add   $8,$29,68
	 sw   $8,0($29)
	move   $4,$8
# T_2 =  call test
	 jal   test
	 nop
# a [ 1 ] = T_2
       li   $8, 1
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       move  $9,$2
       sw  $9,0($8)
# var T_3
# arg a
	 add   $8,$29,68
	 sw   $8,0($29)
	move   $4,$8
# T_3 =  call test
       sw  $2,80($29)
       sw  $9,80($29)
	 jal   test
	 nop
# z = T_3
       move  $8,$2
# arg x
       lw  $9,56($29)
	 sw   $9,0($29)
	move   $4,$9
# call test2
       sw  $2,84($29)
       sw  $8,0($29)
	 jal   test2
	 nop
# arg 1
       li   $8, 1
	 sw   $8,0($29)
	move   $4,$8
# call test2
	 jal   test2
	 nop
# arg L12
       lui   $8,%hi(L12)
       addiu   $8,$8,%lo(L12)
	 sw   $8,0($29)
	move   $4,$8
# call L4
	 jal   n_printf
	 nop
# arg x
       lw  $8,56($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# T_4 = a [ 1 ]
       li   $8, 1
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       lw  $8,0($8)
# arg T_4
       sw  $8,0($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# arg z
       lw  $8,64($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# arg L13
       lui   $8,%hi(L13)
       addiu   $8,$8,%lo(L13)
	 sw   $8,0($29)
	move   $4,$8
# call L4
	 jal   n_printf
	 nop
# z =  call L8
       addiu   $5,$29,64
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_read_int
	 nop
       sw   $2,64($29)
# endfunc 
	 lw	$16,20($29)
	 lw	$17,24($29)
	 lw	$18,28($29)
	 lw	$19,32($29)
	 lw	$20,36($29)
	 lw	$21,40($29)
	 lw	$22,44($29)
	 lw	$23,48($29)
	 lw	$31,16($29)
	 addiu	$sp,$sp,88
	 jr  $31
	 nop
	.end main
