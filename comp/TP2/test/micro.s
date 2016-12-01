.rdata
.align 2
$LC0:
       .ascii	"%d\000"
.text
# label main
# beginfunc 
	.align	2
	.globl main
	.ent main
main:
	 addiu	$sp,$sp,-76
	 sw	$31,16($29)
	 sw	$16,24($29)
	 sw	$17,28($29)
	 sw	$18,32($29)
	 sw	$19,36($29)
	 sw	$20,40($29)
	 sw	$21,44($29)
	 sw	$22,48($29)
	 sw	$23,52($29)
# var t
# var n
# t [ 1 ] = 3
       li   $8, 1
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       li   $9, 3
       sw  $9,0($8)
# var T_0
# T_0 = t [ 1 ]
       li   $8, 1
       li   $10,4
       mult   $8,$10
       mflo   $8
       add   $10,$29,56
       add   $8,$8,$10
       lw  $8,0($8)
# n = T_0
       sw  $8,68($29)
# var T_1
# T_1 = t [ 1 ]
       li   $10, 1
       li   $11,4
       mult   $10,$11
       mflo   $10
       add   $11,$29,56
       add   $10,$10,$11
       lw  $10,0($10)
# arg T_1
       sw  $10,72($29)
	 sw   $10,0($29)
	move   $4,$10
# call L2
       sw  $8,64($29)
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
	 jal   n_printf
	 nop
# arg n
       lw  $8,64($29)
	 sw   $8,0($29)
	move   $4,$8
# call L2
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
	 addiu	$sp,$sp,76
	 jr  $31
	 nop
	.end main
