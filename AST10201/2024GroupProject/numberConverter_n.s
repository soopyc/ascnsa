# Number converter
# @author Cassie Cheung (soopyc)
# @date 2024-09-14

# Since "macros" is not a thing in MIPS ASM but rather a MARS implementation,
# we will have to deal with subroutines, jal and jr.

# Memory data
.data
# static strings
s_info:		.asciiz	"Info: This program prints out a number from with base to bin/oct/dec/hex form.\n\n"
s_text_len:	.asciiz	"The length of the string entered is: "
input_buffer:	.space	32

# Program section
.text
main:
	# print info text
	la	$a0, s_info
	jal	print_str

	# get input from user
	li	$v0, 8	# invoke syscall read_string
	la	$a0, input_buffer
	li	$a1, 32
	syscall
	
	# get the length of input string
	la	$a0, input_buffer # maybe set this again just in case it changed
	jal	count_str_len
	move	$s0, $v0 # copy result to save value register

	# print result (part 1: string).
	la	$a0, s_text_len
	jal	print_str
	# print result (part 2: integer).
	li	$v0, 1 # invoke syscall print_int
	la	$a0, ($s0)
	syscall
		
	# exit program
	li	$v0, 10
	syscall

# Subroutines
#
# print_str: Print a string
# Required arguments
#   - $a0: string address to print, handled by syscall.
print_str:
	li	$v0, 4	# invoke syscall 4 (print_str)
	syscall
	jr	$ra

# count_str_len: Count string length.
# Arguments:
#   - $a0: starting location of the string buffer
# Returns:
#   - $v0: length of string
count_str_len:
	# copy starting address to $a1
	move	$a1, $a0
	#j count_str_len_loop  # maybe not needed?
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
