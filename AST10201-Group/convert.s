
# ----------------------------------
# Data Segment
.data
msg:	.asciiz	" Info: this program is to convert a number from any base (between 2 and 16) to binary, octal, decimal and hexadecimal number\n"

promptBase:	.asciiz "\n Enter any base between 2 and 16: "
promptNumb:	.asciiz "\n Enter a number: "

msgWrong:	.asciiz "\n Each digit should be between 0 and "

ansBin:	.asciiz "\tConvert it to Bin: "
ansDec:	.asciiz "\tConvert it to Dec: "
ansOct:	.asciiz "\tConvert it to Oct: "
ansHex:	.asciiz "\tConvert it to Hex: "
comp:	.asciiz "\n Do you wanna continue (y/n)? "
newLine:	.asciiz	"\n"
end:	.asciiz "\n End\n"
buffer:	.space	32

invalid_base: .asciiz "   The base must between 2 and 16! Please enter again!"

# ----------------------------------
# Text/Code Segment

.text
.globl main
main:
	la	$a0, msg
	li	$v0, 4
	syscall

print_promt:
	# TODO 
	# prompt to user to input base between 2 and 16
	la	$a0, promptBase
	li	$v0, 4
	syscall
	
	# read integer
	li $v0, 5
	syscall
	
	# make $s0 = base
	move $s0, $v0
	
	# check if base is valid
	bgt $s0, 16, outofRange_base
	blt $s0, 2, outofRange_base
	
	# if base is valid, then continue the code
	j print_enterNo
	
outofRange_base:
	la $a0, invalid_base	# prompt user input again
	li $v0, 4
	syscall
	
	j print_promt	# prompt user input again
	
print_enterNo:
	
	# TODO
	la	$a0, promptNumb
	li	$v0, 4
	syscall
	
	# read string
	li $v0, 8
	la $a0, buffer
	li $a1, 32
	syscall
	
	# initialize register
	li $t0, 0
	li $t1, 0
	la $a0, buffer
	
	 
.end main

# ----------
# procedure: str2int
# $a0 = starting address of buffer
# $a2 = base
# return value = $v1
# return flag $v0: 0-wrong, 1-correct
# -----------
.globl str2int
str2int:
	move	$a1, $a0
length:	lb	$t0, 0($a1)
	beq	$t0, 10, endString
	addi	$a1, $a1, 1
	j	length
endString:
	sub	$a1, $a1, $a0

	# TODO
check_alpha:
	


.end str2int

# ----------
# procedure: print2
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print2
print2:

	# TODO

.end print2
# ----------
# procedure: print10
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print10
print10:

	# TODO

.end print10

# -----------
# procedure: print8
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print8
print8:

	# TODO

.end print8

# ----------
# procedure: print16
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print16
print16:

	# TODO

.end print16
