.rdata
.align 2
$LC0:
       .ascii	"%d\000"
       .align 2
L12:
       .ascii	"f(\000"
       .align 2
L13:
       .ascii	") = \000"
       .align 2
L14:
       .ascii	"\012\000"
.text
# label main
# beginfunc 
	.align	2
	.globl main
	.ent main
main:
	 addiu	$sp,$sp,-124
	 sw	$31,16($29)
	 sw	$16,24($29)
	 sw	$17,28($29)
	 sw	$18,32($29)
	 sw	$19,36($29)
	 sw	$20,40($29)
	 sw	$21,44($29)
	 sw	$22,48($29)
	 sw	$23,52($29)
# var i
# var t
# i = 0
       li   $8, 0
# label L10
       sw  $8,0($29)
L10:
# var T_0
# T_0 = 11 - i
       li   $9, 11
       lw  $8,56($29)
	sub $8, $9, $8
# ifz T_0 goto L11
       sw  $8,104($29)
	beq  $8,0,L11
# var T_1
# arg i
       lw  $10,56($29)
	 sw   $10,0($29)
	move   $4,$10
# T_1 =  call fact
	 jal   fact
	 nop
# t [ i ] = T_1
       lw  $8,56($29)
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,0
       add   $8,$8,$9
       move  $9,$2
       sw  $9,0($8)
# var T_2
# T_2 = i + 1
       lw  $10,56($29)
       li   $8, 1
	add $8, $10, $8
# i = T_2
       sw  $8,112($29)
# goto L10
       sw  $2,108($29)
       sw  $8,0($29)
       sw  $9,108($29)
	j  L10
# label L11
L11:
# i = 0
       li   $8, 0
# label L15
       sw  $8,0($29)
L15:
# var T_3
# T_3 = 11 - i
       li   $9, 11
       lw  $8,56($29)
	sub $8, $9, $8
# ifz T_3 goto L16
       sw  $8,116($29)
	beq  $8,0,L16
# arg L12
       lui   $10,%hi(L12)
       addiu   $10,$10,%lo(L12)
	 sw   $10,0($29)
	move   $4,$10
# call L4
	 jal   n_printf
	 nop
# arg i
       lw  $8,56($29)
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
# T_4 = t [ i ]
       lw  $8,56($29)
       li   $9,4
       mult   $8,$9
       mflo   $8
       add   $9,$29,60
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
# arg L14
       lui   $8,%hi(L14)
       addiu   $8,$8,%lo(L14)
	 sw   $8,0($29)
	move   $4,$8
# call L4
	 jal   n_printf
	 nop
# var T_5
# T_5 = i + 1
       lw  $9,56($29)
       li   $8, 1
	add $8, $9, $8
# i = T_5
       sw  $8,120($29)
# goto L15
       sw  $8,0($29)
	j  L15
# label L16
L16:
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
	 addiu	$sp,$sp,124
	 jr  $31
	 nop
	.end main
# label fact
# beginfunc 
	.align	2
	.globl fact
	.ent fact
fact:
	 addiu	$sp,$sp,-36
	 sw	$31,16($29)
# ifz n goto L17
       lw  $8,0($29)
	beq  $8,0,L17
# var T_7
# var T_6
# T_6 = n - 1
       li   $9, 1
	sub $9, $8, $9
# arg T_6
       sw  $9,28($29)
	 sw   $9,0($29)
	move   $4,$9
# T_7 =  call fact
	 jal   fact
	 nop
# var T_8
# T_8 = n * T_7
       lw  $9,0($29)
       move  $8,$2
	mult $9, $8
	mflo $8
# return T_8
       sw  $2,24($29)
       move  $2,$8
	 lw	$31,16($29)
	 addiu	$sp,$sp,36
	 jr  $31
	 nop
# goto L18
       sw  $2,32($29)
	j  L18
# label L17
L17:
# return 1
       li   $2, 1
	 lw	$31,16($29)
	 addiu	$sp,$sp,36
	 jr  $31
	 nop
# label L18
L18:
# endfunc 
	 lw	$31,16($29)
	 addiu	$sp,$sp,36
	 jr  $31
	 nop
	.end fact
