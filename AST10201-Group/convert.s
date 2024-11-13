
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
	# TODO:
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

# this step prompts the user if they want to redo the entire thing.
continue:
	la    $a0, repeat    # show string repeat
	li    $v0, 4
	syscall

	li    $v0, 12		# read char
	syscall

	bne    $v0, 121, noty     # 'y'
	li    $a0, 10
	li    $v0, 11
	syscall
	j    main

noty:
	bne    $v0, 110, notn    # 'n'
	la    $a0, end
	li    $v0, 4
	syscall
	li    $v0, 10
	syscall
notn:
	j    print_promt

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

	# TODO: what is this supposed to do
check_alpha:

.end str2int

# ----------
# procedure: print2
# desc: decimal to binary
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print2
print2:

	# TODO

.end print2

# ----------
# procedure: print10
# desc: decimal to decimal
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print10
print10:

	# TODO

.end print10

# -----------
# procedure: print8
# desc: decimal to octal
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print8
print8:

	# TODO

.end print8

# ----------
# procedure: print16
# desc: decimal to hexadecimal
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print16
print16:

	# TODO

.end print16

# ----------
# procedure: count_str_len
# desc: count a string's length
# Arguments:
#   - $a0: starting location of the string buffer
# Returns:
#   - $v0: length of string
# ----------
count_str_len:
	# copy starting address to $a1
	move	$a1, $a0
count_str_len_loop:
	# load the starting byte to $t0
	lb	$t0, ($a1)
	# check if it is \n or NUL, if not we add 1; if so we branch.
	beq	$t0, 10, count_str_len_end # 10 = \n (LF)
	beq	$t0, 0, count_str_len_end # 0 = \0 (NUL)
	# treat $a1 as an accumulator, add 1 when we reach here, then go back.
	addi	$a1, $a1, 1
	j	count_str_len_loop
count_str_len_end:
	# the final length of the string is the value of $a1 (end value) minus $a0 (starting value)
	sub	$v0, $a1, $a0
	jr	$ra
