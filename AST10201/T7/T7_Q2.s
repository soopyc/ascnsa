# isEven program
# ----------------------------------
# Data Segment
.data
msgEnter:		.asciiz	"\nEnter an integer: "
msgEVEN:		.asciiz	" is EVEN!\n"
msgODD:			.asciiz	" is ODD!\n"
repeat:			.asciiz	"Do you want to continue (y/n)?\n"
end:			.asciiz	"\n-- End --\n"
# ----------------------------------
# Text/Code Segment

.text
.globl	main
main:
	la	$a0, msgEnter	# show string msgEnter
	li	$v0, 4
	syscall

	li	$v0, 5		# read integer
	syscall
	move	$a0, $v0	# copy integer to $a0
	li	$v0, 1		# print integer
	syscall

	jal	isEven

	# TODO
	# show string msgEVEN or msgODD according to the value of $v0
	
	beq	$v0, 0, printODD
	
printEVEN:
	la	$a0, msgEVEN
	li	$v0, 4
	syscall
	b	continue

printODD:
	la	$a0, msgODD
	li	$v0, 4
	syscall

continue:
	la	$a0, repeat	# show string repeat
	li	$v0, 4
	syscall

	li	$v0, 12		# read char
	syscall

	bne	$v0, 121, noty 	# 'y'
	li	$a0, 10
	li	$v0, 11
	syscall
	j	main

noty:	bne	$v0, 110, notn	# 'n'
	la	$a0, end
	li	$v0, 4
	syscall
	li	$v0, 10		# exit
	syscall
notn:	j	main
.end main

# ----------------------------------
#	isEven
# argument:	$a0 = integer
# return:	$v0 = 0 (false) / 1 (true)
# ----------------------------------

.globl	isEven
isEven:
	div	$zero, $a0, 2 # ignore return value. we care about the remainder.
	mfhi	$t0
	
	beq	$t0, 0, isEvenTrue	# note: branches are relative, jumps are absolute to addresses.
	b	isEvenFalse
	
isEvenTrue:
	li	$v0, 1
	b	isEvenEnd

isEvenFalse:
	li	$v0, 0
	
isEvenEnd:
	jr	$ra		# return to $ra

.end	isEven
