
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

msg_invalid_base:	.asciiz "   The base must between 2 and 16! Please enter again!"
msg_illegal_char:	.asciiz "	One of the characters is illegal for the base."

# ----------------------------------
# Text/Code Segment

.text
.globl main
main:
	la	$a0, msg
	li	$v0, 4
	syscall

prompt_base:
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
	la $a0, msg_invalid_base	# prompt user input again
	li $v0, 4
	syscall

	j prompt_base	# prompt user input again

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
	
	# call str2int
	la	$a0, buffer
	move	$a1, $s0
	jal	str2int
	
	# print out data to check
	# TODO: DELETE ME
	li	$v0, 1
	move	$a0, $v1
	syscall

	# initialize register (??)
	#li $t0, 0
	#li $t1, 0
	#la $a0, buffer

# this step prompts the user if they want to restart the entire thing.
continue:
	la	$a0, comp	# show string repeat
	li	$v0, 4
	syscall

	li	$v0, 12		# read char
	syscall

	bne	$v0, 'y', noty	 # 'y'
	li	$a0, 10
	li	$v0, 11
	syscall
	j	main

noty:
	bne	$v0, 'n', notn	# 'n'
	la	$a0, end
	li	$v0, 4
	syscall
	li	$v0, 10
	syscall
notn:
	j	prompt_base

.end main

# ----------
# procedure: str2int
# desc: parse string to integer
# input: $a0 = starting address of buffer
# input: $a1 = base
#  temp: $t0 = character to be checked and added to $v1.
#  temp: $t1 = the next character after $t0.
#  temp: $t2 = sometimes mutated main current index 
#  temp: $t3 = often-mutated current index for adding to the value in the addBaseLoop label.
#  temp: $t6 = count string length: address of buffer
#  temp: $t7 = count string length: temporary storage of current character.
# return value = $v1
# return flag $v0: 0-wrong, 1-correct
# -----------
.globl str2int
str2int:
	# count string length
	# i would use the stack to store '$ra's and registers but that's way too complicated to write by hand.
	move	$t6, $a0	# set $t6 to starting address of buffer.
	# initialize $v0 and $v1.
	move	$v0, $zero
	move	$v1, $zero

countStringLoop:
	lb	$t7, ($t6) # load char into $t7
	beq	$t7, '\0', countStringLoopEnd # end if char is NUL
	beq	$t7, '\n', countStringLoopEnd # end if char is LF
	addiu	$t6, $t6, 1 # shift address by 1 byte.
	j countStringLoop
countStringLoopEnd:
	# the length is the ending address minus the starting address.
	sub	$t2, $t6, $a0
	sub	$t2, $t2, 1 # subtract one so we get 0 at the ones place.

str2intLoop:
	lb	$t0, 0($a0) # load the next char into $t0
	lb	$t1, 1($a0) # load the next-next char into $t1
	bgt	$t0, '9', handleAlpha # if loaded char is greater than 9's ascii codepoint, it's an alphabet character.
	sub	$t0, $t0, '0' # effectively turn ascii "0" into 0.
	b	checkAndAdd
handleAlpha:
	sub $t0, $t0, 55 # ('F'-15 = 55) effectively turn ascii "F" into 15
	# b	checkAndAdd	# nop, for consistency

# check the validity, and if so add the value to $v1.
checkAndAdd:
	blt	$t0, 0, checkFailure	# all values below 0 is invalid.
	bgt	$t0, 16, checkFailure	# all values above 15 is invalid.
	bgt	$t0, $a1, checkFailure	# all values above the base is invalid.
	move	$t3, $t2	# set $t3 to current index.

addBaseLoop:
	beqz	$t3, addBaseLoopEnd
	# if we passed those checks, we can add it to the value.
	mul	$t0, $t0, $a1	# multiply the value by the base by (index) times.
	subi	$t3, $t3, 1
	j	addBaseLoop

addBaseLoopEnd:
	add	$v1, $v1, $t0 # finally add the value to $v1.
	j	endOrBump

checkFailure:
	li	$v0, 4
	la	$a0, msg_illegal_char
	syscall # print out an error message.
	jr	$ra

# check if the next char is \n or NUL. if so, return. if not, add 4 to $t0.
endOrBump:
	beq	$t1, '\n', endStr2int
	beq	$t1, '\0', endStr2int
	
	addiu	$a0, $a0, 1	# bump $a0 by 1
	subi	$t2, $t2, 1	# subtract index by 1
	j	str2intLoop

endStr2int:
	li	$v0, 1	# if it reached this far it should be correct.
	jr	$ra

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
# desc: decimal to decimal (??)
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
