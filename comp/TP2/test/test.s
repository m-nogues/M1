.rdata
.align 2
$LC0:
       .ascii	"%d\000"
       .align 2
L10:
       .ascii	"\012\000"
       .align 2
L11:
       .ascii	"\012\000"
       .align 2
L14:
       .ascii	"Test sample\012\000"
       .align 2
L15:
       .ascii	"\012\000"
       .align 2
L16:
       .ascii	"\012\000"
       .align 2
L17:
       .ascii	"\012Second test sample\012\000"
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
# arg L10
       lui   $8,%hi(L10)
       addiu   $8,$8,%lo(L10)
	 sw   $8,0($29)
	move   $4,$8
# call L4
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
# arg L11
       lui   $8,%hi(L11)
       addiu   $8,$8,%lo(L11)
	 sw   $8,0($29)
	move   $4,$8
# call L4
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
# ifz x goto L12
       sw  $8,0($29)
       sw  $9,0($29)
       lw  $11,56($29)
	beq  $11,0,L12
# x = 5
       li   $12, 5
# goto L13
       sw  $12,0($29)
	j  L13
# label L12
L12:
# x = 6
       li   $8, 6
# label L13
       sw  $8,0($29)
L13:
# a [ 0 ] = 1
       li   $8, 0
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       li   $9, 1
       sw  $9,0($8)
# T_2 = a [ 0 ]
       li   $8, 0
       li   $10,4
       mult   $8,$10
       mflo   $8
       add   $10,$29,68
       add   $8,$8,$10
       lw  $8,0($8)
# arg T_2
       sw  $8,0($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# var T_3
# arg a
	 add   $8,$29,68
	 sw   $8,0($29)
	move   $4,$8
# T_3 =  call test
	 jal   test
	 nop
# a [ 1 ] = T_3
       li   $8, 1
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       move  $9,$2
       sw  $9,0($8)
# var T_4
# arg a
	 add   $8,$29,68
	 sw   $8,0($29)
	move   $4,$8
# T_4 =  call test
       sw  $2,80($29)
       sw  $9,80($29)
	 jal   test
	 nop
# z = T_4
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
# arg L14
       lui   $8,%hi(L14)
       addiu   $8,$8,%lo(L14)
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
# arg L15
       lui   $8,%hi(L15)
       addiu   $8,$8,%lo(L15)
	 sw   $8,0($29)
	move   $4,$8
# call L4
	 jal   n_printf
	 nop
# T_5 = a [ 1 ]
       li   $8, 1
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,68
       add   $8,$8,$9
       lw  $8,0($8)
# arg T_5
       sw  $8,0($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# arg L16
       lui   $8,%hi(L16)
       addiu   $8,$8,%lo(L16)
	 sw   $8,0($29)
	move   $4,$8
# call L4
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
# arg L17
       lui   $8,%hi(L17)
       addiu   $8,$8,%lo(L17)
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
# arg z
       move  $8,$2
	 sw   $8,0($29)
	move   $4,$8
# call L2
       sw  $2,64($29)
       sw  $8,64($29)
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
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
