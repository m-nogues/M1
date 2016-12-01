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
	 addiu	$sp,$sp,-64
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
# i = 1
       li   $8, 1
# var i
# i = 2
       li   $9, 2
# arg i
       sw  $9,60($29)
	 sw   $9,0($29)
	move   $4,$9
# call L2
       sw  $8,56($29)
       move $5,$4
       lui   $4,%hi($LC0)
       addiu   $4,$4,%lo($LC0)
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
	 addiu	$sp,$sp,64
	 jr  $31
	 nop
	.end main
